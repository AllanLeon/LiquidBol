/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Client;
import com.liquidbol.model.Employee;
import com.liquidbol.model.ItemBill;
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
 * Class responsible of all persistence operations related to item bills.
 * @author Allan Leon
 */
public class ItemBillCrud implements DBCrud<ItemBill> {
    
    private static final Logger LOG = Logger.getLogger(ItemBillCrud.class.getName());

    private Connection connection;

    public ItemBill save(ItemBill element, Client parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_bills(client_id, store_id, employee_id, "
                    + "bill_date, total_amount, is_billed, is_route, obs) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, parent.getId());
            statement.setInt(2, element.getStore().getId());
            statement.setInt(3, element.getEmployee().getId());
            statement.setDate(4, element.getDate());
            statement.setDouble(5, element.getTotalAmount());
            statement.setBoolean(6, element.isBilled());
            statement.setBoolean(7, element.isRoute());
            statement.setString(8, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item bill was not saved");
            }
            LOG.info(String.format("item bill: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item bill: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemBill find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemBill find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_bills WHERE itembill_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item bill with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item bill", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<ItemBill> findByClientId(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_bills WHERE itembill_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemBill> result = new HashSet<>();
            while (resultSet.next()) {
                ItemBill element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item bill", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemBill merge(ItemBill element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE item_bills SET bill_date=?, total_amount=?, "
                    + "is_billed=?, is_route=?, obs=? WHERE itembill_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getDate());
            statement.setDouble(2, element.getTotalAmount());
            statement.setBoolean(3, element.isBilled());
            statement.setBoolean(4, element.isRoute());
            statement.setString(5, element.getObs());
            statement.setInt(6, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item bill was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item bill: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ItemBill> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_bills";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemBill> result = new HashSet<>();
            while (resultSet.next()) {
                ItemBill element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the item bills", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemBill refresh(ItemBill element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public ItemBill createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Store store = new Store(resultSet.getInt(3));
        Employee employee = new Employee(resultSet.getInt(4));
        Date date = resultSet.getDate(5);
        Double totalAmount = resultSet.getDouble(6);
        boolean billed = resultSet.getBoolean(7);
        boolean route = resultSet.getBoolean(8);
        String obs = resultSet.getString(9);
        LOG.log(Level.FINE, "Creating item bill %d", id);
        ItemBill result = new ItemBill(id, employee, store, date, totalAmount, billed, route, obs);
        return result;
    }

    @Override
    public ItemBill save(ItemBill element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
