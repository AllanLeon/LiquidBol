/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.Item;
import com.liquidbol.model.commons.ItemPurchase;
import com.liquidbol.model.commons.Purchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to item purchases.
 * @author Allan Leon
 */
public class ItemPurchaseCrud implements DBCrud<ItemPurchase> {

    private static final Logger LOG = Logger.getLogger(ItemPurchaseCrud.class.getName());

    private Connection connection;
    
    public ItemPurchase save(ItemPurchase purchase, Purchase parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_purchases(item_id, purchase_id, quantity, "
                    + "total_amount) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, purchase.getItem().getId());
            statement.setInt(2, parent.getId());
            statement.setInt(3, purchase.getQuantity());
            statement.setDouble(4, purchase.getAmount());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Item purchase was not saved");
            }
            LOG.info(String.format("Item purchase: %d successfuly saved", purchase.getId()));
            return purchase;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item purchase: %d", purchase.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemPurchase find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPurchase find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_purchases WHERE item_purchase_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item purchase with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item purchase", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemPurchase merge(ItemPurchase purchase) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE item_purchases SET quantity=?, total_amount=? "
                    + "WHERE item_purchase_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, purchase.getQuantity());
            statement.setDouble(2, purchase.getAmount());
            statement.setInt(3, purchase.getId());
            return purchase;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item purchase: %d", purchase.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ItemPurchase> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_purchases";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemPurchase> result = new HashSet<>();
            while (resultSet.next()) {
                ItemPurchase purchase = createElementFromResultSet(resultSet);
                result.add(purchase);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the item purchases", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemPurchase refresh(ItemPurchase purchase) throws PersistenceException, ClassNotFoundException {
        return find(purchase.getId());
    }

    @Override
    public ItemPurchase createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Item item = new Item(resultSet.getString(2));
        int quantity = resultSet.getInt(4);
        Double amount = resultSet.getDouble(5);
        LOG.log(Level.FINE, "Creating purchase %d", id);
        ItemPurchase result = new ItemPurchase(id, item, quantity, amount);
        return result;
    }

    @Override
    public ItemPurchase save(ItemPurchase element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
