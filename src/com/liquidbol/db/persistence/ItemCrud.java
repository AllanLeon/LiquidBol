/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.commons.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to items.
 * @author Allan Leon
 */
public class ItemCrud implements DBCrud<Item> {
    
    private static final Logger LOG = Logger.getLogger(ItemCrud.class.getName());

    private Connection connection;

    /**
     * {@inheritDoc }
     * @param item to be saved
     */
    @Override
    public Item save(Item item) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO items(item_id, item_measure, item_description,"
                    + "item_brand, item_industry, item_type, item_subtype, item_cost"
                    + "item_price, item_dif, item_profit) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, item.getId());
            statement.setString(2, item.getMeasure());
            statement.setString(3, item.getDescription());
            statement.setString(4, item.getBrand());
            statement.setString(5, item.getIndustry());
            statement.setString(6, item.getType());
            statement.setString(7, item.getSubtype());
            statement.setDouble(8, item.getCost());
            statement.setDouble(9, item.getPrice());
            statement.setDouble(10, item.getDif());
            statement.setDouble(11, item.getProfit());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Item was not saved");
            }
            LOG.info(String.format("Item: %s successfuly saved", item.getDescription()));
            return item;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item: %s", item.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Item find(String id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM items WHERE item_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item with code %s", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Item find(int id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     * @param item to be synchronized
     */
    @Override
    public Item merge(Item item) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE items SET item_brand=?, item_industry=?, item_cost=?"
                    + "item_price=?, item_dif=?, item_profit=? WHERE item_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, item.getBrand());
            statement.setString(2, item.getIndustry());
            statement.setDouble(3, item.getCost());
            statement.setDouble(4, item.getPrice());
            statement.setDouble(5, item.getDif());
            statement.setDouble(6, item.getProfit());
            statement.setString(7, item.getId());
            return item;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item: %s", item.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Item> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM items";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Item> result = new HashSet<>();
            while (resultSet.next()) {
                Item item = createElementFromResultSet(resultSet);
                result.add(item);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the items", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * {@inheritDoc }
     * @param item to be updated
     */
    @Override
    public Item refresh(Item item) throws PersistenceException, ClassNotFoundException {
        return find(item.getId());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Item createElementFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(1);
        String measure = resultSet.getString(2);
        String description = resultSet.getString(3);
        String brand = resultSet.getString(4);
        String industry = resultSet.getString(5);
        String type = resultSet.getString(6);
        String subtype = resultSet.getString(7);
        Double cost = resultSet.getDouble(8);
        Double price = resultSet.getDouble(9);
        LOG.log(Level.FINE, "Creating Item %s", id);
        Item result = new Item(id, measure, description, brand, industry, type, subtype, cost, price);
        return result;
    }
}
