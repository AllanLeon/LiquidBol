/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Employee;
import com.liquidbol.model.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to employees and stores
 * relations.
 * @author Allan Leon
 */
public class EmployeeStoreCrud {
    
    private static final Logger LOG = Logger.getLogger(EmployeeStoreCrud.class.getName());

    private Connection connection;

    public Employee save(Employee element, Store parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO employees_stores(employee_id, store_id) "
                    + "VALUES(?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, element.getId());
            statement.setInt(2, parent.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("employee store was not saved");
            }
            LOG.info(String.format("employee store: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save employee store: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    public Employee findEmployeesByStore(Store store) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM employees_stores WHERE store_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, store.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find employees in store with code %d", store.getId()));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read employee store", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    public Employee merge(Employee element, Store parent) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE employees_stores SET store_id=? WHERE employee_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, parent.getId());
            statement.setInt(2, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("employee store was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update employee store: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    public Employee createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(2);
        LOG.log(Level.FINE, "Creating employee %d", id);
        Employee result = new Employee(id);
        return result;
    }
}
