/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.DebtPayment;
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
 * Class responsible of all persistence operations related to debts payments.
 * @author Allan Leon
 */
public class DebtPaymentCrud implements DBCrud<DebtPayment> {

    private static final Logger LOG = Logger.getLogger(DebtPaymentCrud.class.getName());

    private Connection connection;
    
    @Override
    public DebtPayment save(DebtPayment payment) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO debt_payments(debt_id, pay_date, amount) "
                    + "VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, payment.getId());
            statement.setDate(2, payment.getPayDate());
            statement.setDouble(3, payment.getAmount());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Debt payment was not saved");
            }
            LOG.info(String.format("Debt payment: %d successfuly saved", payment.getId()));
            return payment;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save debt payment:%d", payment.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            return payment;
        }
    }

    @Override
    public DebtPayment find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DebtPayment find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM debt_payments WHERE debt_payment_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find debt payment with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read debt payment", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public DebtPayment merge(DebtPayment payment) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE debt_payments SET pay_date=?, amount=? WHERE debt_payment_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, payment.getPayDate());
            statement.setDouble(2, payment.getAmount());
            statement.setInt(3, payment.getId());
            return payment;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update debt payment: %d", payment.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<DebtPayment> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM debt_payments";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<DebtPayment> result = new HashSet<>();
            while (resultSet.next()) {
                DebtPayment payment = createElementFromResultSet(resultSet);
                result.add(payment);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the debt payments", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public DebtPayment refresh(DebtPayment payment) throws PersistenceException, ClassNotFoundException {
        return find(payment.getId());
    }

    @Override
    public DebtPayment createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Date payDate = resultSet.getDate(3);
        Double amount = resultSet.getDouble(4);
        LOG.log(Level.FINE, "Creating debt payment %d", id);
        DebtPayment result = new DebtPayment(id, payDate, amount);
        return result;
    }
    
}
