/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Bill;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemSale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to item sales.
 * @author Allan Leon
 */
public class ItemSaleCrud implements DBCrud<ItemSale> {
    
    private static final Logger LOG = Logger.getLogger(ItemSaleCrud.class.getName());

    private Connection connection;

    public ItemSale save(ItemSale element, Bill parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_sales(itembill_id, item_id, quantity, "
                    + "total_amount, obs) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parent.getId());
            statement.setString(2, element.getItem().getId());
            statement.setInt(3, element.getQuantity());
            statement.setDouble(4, element.getAmount());
            statement.setString(5, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item sale was not saved");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                element.setId(id);
            }
            LOG.info(String.format("item sale: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item sale: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemSale find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemSale find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_sales WHERE itemsale_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item sale with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item sale", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<ItemSale> findByItemBillId(int itemBillId) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_sales WHERE itembill_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itemBillId);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemSale> result = new HashSet<>();
            while (resultSet.next()) {
                ItemSale element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item sale", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemSale merge(ItemSale element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE item_sales SET quantity=?, total_amount=?, "
                    + "obs=? WHERE itemsale_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, element.getQuantity());
            statement.setDouble(2, element.getAmount());
            statement.setString(3, element.getObs());
            statement.setInt(4, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item sale was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item sale: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ItemSale> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_sales";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemSale> result = new HashSet<>();
            while (resultSet.next()) {
                ItemSale element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the item sales", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemSale refresh(ItemSale element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public ItemSale createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Item item = new Item(resultSet.getString(3));
        int quantity = resultSet.getInt(4);
        Double amount = resultSet.getDouble(5);
        String obs = resultSet.getString(6);
        LOG.log(Level.FINE, "Creating item sale %d", id);
        ItemSale result = new ItemSale(id, item, quantity, amount, obs);
        return result;
    }

    @Override
    public ItemSale save(ItemSale element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
