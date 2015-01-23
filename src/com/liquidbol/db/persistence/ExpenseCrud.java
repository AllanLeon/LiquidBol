/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Expense;
import com.liquidbol.model.Store;
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
 * Class responsible of all persistence operations related to expenses.
 * @author Allan Leon
 */
public class ExpenseCrud implements DBCrud<Expense> {
    
    private static final Logger LOG = Logger.getLogger(ExpenseCrud.class.getName());

    private Connection connection;

    public Expense save(Expense element, Store parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO expenses(store_id, pay_date, description, "
                    + "amount, obs) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, parent.getId());
            statement.setDate(2, element.getPayDate());
            statement.setString(3, element.getDescription());
            statement.setDouble(4, element.getAmount());
            statement.setString(5, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("expense was not saved");
            }
            LOG.info(String.format("expense: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save expense: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Expense find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Expense find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM expenses WHERE expense_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find expense with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read expense", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<Expense> findByStoreId(int storeId) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM expenses WHERE store_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, storeId);
            ResultSet resultSet = statement.executeQuery();
            Collection<Expense> result = new HashSet<>();
            while (resultSet.next()) {
                Expense element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read expense", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Expense merge(Expense element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE expenses SET pay_date=?, description=?, "
                    + "amount=?, obs=? WHERE expense_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getPayDate());
            statement.setString(2, element.getDescription());
            statement.setDouble(3, element.getAmount());
            statement.setString(4, element.getObs());
            statement.setInt(5, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("expense was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update expense: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Expense> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM expenses";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Expense> result = new HashSet<>();
            while (resultSet.next()) {
                Expense element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the expenses", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Expense refresh(Expense element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Expense createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Date payDate = resultSet.getDate(3);
        String description = resultSet.getString(4);
        Double amount = resultSet.getDouble(5);
        String obs = resultSet.getString(6);
        LOG.log(Level.FINE, "Creating expense %d", id);
        Expense result = new Expense(id, payDate, description, amount, obs);
        return result;
    }

    @Override
    public Expense save(Expense element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
