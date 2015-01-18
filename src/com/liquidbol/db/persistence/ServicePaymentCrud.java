/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.BillPayment;
import com.liquidbol.model.commons.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to service payments.
 * @author Allan Leon
 */
public class ServicePaymentCrud implements DBCrud<BillPayment> {
    
    private static final Logger LOG = Logger.getLogger(ServicePaymentCrud.class.getName());

    private Connection connection;

    @Override
    public BillPayment save(BillPayment element) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO service_payments(servicebill_id, "
                    + "employee_id, pay_date, ammount_paid, obs) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, 0);
            statement.setInt(2, element.getEmployee().getId());
            statement.setDate(3, element.getPayDate());
            statement.setDouble(4, element.getAmmountPaid());
            statement.setString(5, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service payment was not saved");
            }
            LOG.info(String.format("service payment: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save service payment: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public BillPayment find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillPayment find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_payments WHERE servicepayment_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find service payment with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read service payment", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public BillPayment merge(BillPayment element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE service_payments SET pay_date=?, ammount_paid=?, "
                    + "obs=? WHERE servicepayment_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getPayDate());
            statement.setDouble(2, element.getAmmountPaid());
            statement.setString(3, element.getObs());
            statement.setInt(4, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service payment was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update service payment: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<BillPayment> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_payments";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<BillPayment> result = new HashSet<>();
            while (resultSet.next()) {
                BillPayment element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the service payments", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public BillPayment refresh(BillPayment element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public BillPayment createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Employee employee = null;
        Date payDate = resultSet.getDate(4);
        Double ammountPaid = resultSet.getDouble(5);
        String obs = resultSet.getString(6);
        LOG.log(Level.FINE, "Creating service payment %d", id);
        BillPayment result = new BillPayment(id, employee, payDate, ammountPaid, obs);
        return result;
    }
}
