/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Employee;
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
 * Class responsible of all persistence operations related to employees.
 * @author Allan Leon
 */
public class EmployeeCrud implements DBCrud<Employee> {
    
    private static final Logger LOG = Logger.getLogger(EmployeeCrud.class.getName());

    private Connection connection;

    public Employee save(Employee element, Store parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO employees(employee_id, store_id, employee_name, "
                    + "employee_lastname, employee_address, employee_phone, "
                    + "employee_phone2, employee_email, employee_regdate, "
                    + "employee_password, employee_type) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, element.getId());
            statement.setInt(2, parent.getId());
            statement.setString(3, element.getName());
            statement.setString(4, element.getLastname());
            statement.setString(5, element.getAddress());
            statement.setInt(6, element.getPhone());
            statement.setInt(7, element.getPhone2());
            statement.setString(8, element.getEmail());
            statement.setDate(9, element.getRegDate());
            statement.setString(10, element.getPassword());
            statement.setString(11, element.getType());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("employee was not saved");
            }
            LOG.info(String.format("employee: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save employee: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Employee find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM employees WHERE employee_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find employee with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read employee", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Employee autenticate(int id, String password) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM employees WHERE employee_id = ? AND employee_password = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException("Invalid employee id or password");
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read employee", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<Employee> findByStoreId(int storeId) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM employees WHERE store_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, storeId);
            ResultSet resultSet = statement.executeQuery();
            Collection<Employee> result = new HashSet<>();
            while (resultSet.next()) {
                Employee element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read employee", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Employee merge(Employee element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE employees SET employee_address=?, employee_phone=?, "
                    + "employee_phone2=?, employee_email=?, employee_password=?, employee_type "
                    + "WHERE employee_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, element.getAddress());
            statement.setInt(2, element.getPhone());
            statement.setInt(3, element.getPhone2());
            statement.setString(4, element.getEmail());
            statement.setString(5, element.getPassword());
            statement.setString(6, element.getType());
            statement.setInt(7, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("employee was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update employee: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Employee> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM employees";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Employee> result = new HashSet<>();
            while (resultSet.next()) {
                Employee element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the employees", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Employee refresh(Employee element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Employee createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(3);
        String lastname = resultSet.getString(4);
        String address = resultSet.getString(5);
        int phone = resultSet.getInt(6);
        int phone2 = resultSet.getInt(7);
        String email = resultSet.getString(8);
        Date regDate = resultSet.getDate(9);
        String password = resultSet.getString(10);
        String type = resultSet.getString(11);
        LOG.log(Level.FINE, "Creating employee %d", id);
        Employee result = new Employee(id, name, lastname, address, phone, phone2, email, regDate, password, type);
        return result;
    }

    @Override
    public Employee save(Employee element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
