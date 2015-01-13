/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.Item;
import com.liquidbol.model.commons.Purchase;
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
 * Class responsible of all persistence operations related to purchases.
 * @author Allan Leon
 */
public class PurchaseCrud implements DBCrud<Purchase> {

    private static final Logger LOG = Logger.getLogger(PurchaseCrud.class.getName());

    private Connection connection;
    
    @Override
    public Purchase save(Purchase purchase) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO purchases(item_id, supplier_id, quantity, "
                    + "purchase_date, total_ammount) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, purchase.getItem().getId());
            statement.setInt(2, 0);
            statement.setInt(3, purchase.getQuantity());
            statement.setDate(4, purchase.getDate());
            statement.setDouble(5, purchase.getAmmount());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Purchase was not saved");
            }
            LOG.info(String.format("Purchase: %d successfuly saved", purchase.getId()));
            return purchase;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save purchase: %d", purchase.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Purchase find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Purchase find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM purchases WHERE purchase_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find purchase with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read purchase", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Purchase merge(Purchase purchase) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE purchases SET quantity=?, purchase_date=?, "
                    + "total_ammount=? WHERE purchase_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, purchase.getQuantity());
            statement.setDate(2, purchase.getDate());
            statement.setDouble(3, purchase.getAmmount());
            statement.setInt(4, purchase.getId());
            return purchase;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update purchase: %d", purchase.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Purchase> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM purchases";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Purchase> result = new HashSet<>();
            while (resultSet.next()) {
                Purchase purchase = createElementFromResultSet(resultSet);
                result.add(purchase);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the purchases", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Purchase refresh(Purchase purchase) throws PersistenceException, ClassNotFoundException {
        return find(purchase.getId());
    }

    @Override
    public Purchase createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Item item = null;
        int quantity = resultSet.getInt(4);
        Date purchaseDate = resultSet.getDate(5);
        LOG.log(Level.FINE, "Creating purchase %d", id);
        Purchase result = new Purchase(id, item, quantity, purchaseDate);
        return result;
    }
    
}
