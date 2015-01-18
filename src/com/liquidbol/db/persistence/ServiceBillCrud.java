/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.Employee;
import com.liquidbol.model.commons.ServiceBill;
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
 * Class responsible of all persistence operations related to service bills.
 * @author Allan Leon
 */
public class ServiceBillCrud implements DBCrud<ServiceBill> {
    
    private static final Logger LOG = Logger.getLogger(ServiceBillCrud.class.getName());

    private Connection connection;

    @Override
    public ServiceBill save(ServiceBill element) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO service_bills(client_id, employee_id, "
                    + "bill_date, total_ammount, obs) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, 0);
            statement.setInt(2, element.getEmployee().getId());
            statement.setDate(3, element.getDate());
            statement.setDouble(4, element.getTotalAmmount());
            statement.setString(5, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service bill was not saved");
            }
            LOG.info(String.format("service bill: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save service bill: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ServiceBill find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceBill find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_bills WHERE servicebill_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find service bill with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read service bill", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ServiceBill merge(ServiceBill element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE service_bills SET bill_date=?, total_ammount=?, "
                    + "obs=? WHERE servicebill_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getDate());
            statement.setDouble(2, element.getTotalAmmount());
            statement.setString(3, element.getObs());
            statement.setInt(4, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service bill was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update service bill: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ServiceBill> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_bills";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ServiceBill> result = new HashSet<>();
            while (resultSet.next()) {
                ServiceBill element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the service bills", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ServiceBill refresh(ServiceBill element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public ServiceBill createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Employee employee = null;
        Date date = resultSet.getDate(4);
        String obs = resultSet.getString(6);
        LOG.log(Level.FINE, "Creating service bill %d", id);
        ServiceBill result = new ServiceBill(id, employee, date, obs);
        return result;
    }
}