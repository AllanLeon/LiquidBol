/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.commons.model.BillPayment;
import com.liquidbol.commons.model.Employee;
import com.liquidbol.commons.model.ItemBill;
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
 * Class responsible of all persistence operations related to item payments.
 * @author Allan Leon
 */
public class ItemPaymentCrud implements DBCrud<BillPayment> {
    
    private static final Logger LOG = Logger.getLogger(ItemPaymentCrud.class.getName());

    private Connection connection;
    
    public BillPayment save(BillPayment element, ItemBill parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_payments(itembill_id, "
                    + "employee_id, pay_date, amount_paid, obs) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, parent.getId());
            statement.setInt(2, element.getEmployee().getId());
            statement.setDate(3, element.getPayDate());
            statement.setDouble(4, element.getAmountPaid());
            statement.setString(5, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item payment was not saved");
            }
            LOG.info(String.format("item payment: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item payment: %d", element.getId()), ex);
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
            String query = "SELECT * FROM item_payments WHERE itempayment_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item payment with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item payment", ex);
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
            String query = "UPDATE item_payments SET pay_date=?, amount_paid=?, "
                    + "obs=? WHERE itempayment_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getPayDate());
            statement.setDouble(2, element.getAmountPaid());
            statement.setString(3, element.getObs());
            statement.setInt(4, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item payment was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item payment: %d", element.getId()), ex);
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
            String query = "SELECT * FROM item_payments";
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
            throw new PersistenceException("Failed to read the item payments", ex);
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
        Employee employee = new Employee(resultSet.getInt(3));
        Date payDate = resultSet.getDate(4);
        Double amountPaid = resultSet.getDouble(5);
        String obs = resultSet.getString(6);
        LOG.log(Level.FINE, "Creating item payment %d", id);
        BillPayment result = new BillPayment(id, employee, payDate, amountPaid, obs);
        return result;
    }

    @Override
    public BillPayment save(BillPayment element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
