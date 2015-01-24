/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Client;
import com.liquidbol.model.ItemEstimate;
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
 * Class responsible of- all persistence operations related to item estimates.
 * @author Allan Leon
 */
public class ItemEstimateCrud implements DBCrud<ItemEstimate> {
    
    private static final Logger LOG = Logger.getLogger(ItemEstimateCrud.class.getName());

    private Connection connection;

    public ItemEstimate save(ItemEstimate element, Client parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_estimates(client_id, store_id, "
                    + "request_date, limit_date, total_amount, obs) VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, parent.getId());
            statement.setInt(2, element.getStore().getId());
            statement.setDate(3, element.getRequestDate());
            statement.setDate(4, element.getLimitDate());
            statement.setDouble(5, element.getTotalAmount());
            statement.setString(6, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item estimate was not saved");
            }
            LOG.info(String.format("item estimate: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item estimate: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemEstimate find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemEstimate find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_estimates WHERE itemestimate_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item estimate with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item estimate", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<ItemEstimate> findByClientId(int clientId) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_estimates WHERE client_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemEstimate> result = new HashSet<>();
            while (resultSet.next()) {
                ItemEstimate element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item estimate", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemEstimate merge(ItemEstimate element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE item_estimates SET request_date=?, limit_date=?, "
                    + "total_amount=?, obs=? WHERE itemestimate_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getRequestDate());
            statement.setDate(2, element.getLimitDate());
            statement.setDouble(3, element.getTotalAmount());
            statement.setString(4, element.getObs());
            statement.setInt(5, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item estimate was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item estimate: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ItemEstimate> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_estimates";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemEstimate> result = new HashSet<>();
            while (resultSet.next()) {
                ItemEstimate element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the item estimates", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemEstimate refresh(ItemEstimate element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public ItemEstimate createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Store store = new Store(resultSet.getInt(3));
        Date requestDate = resultSet.getDate(4);
        Date limitDate = resultSet.getDate(5);
        Double totalAmount = resultSet.getDouble(6);
        String obs = resultSet.getString(7);
        LOG.log(Level.FINE, "Creating item estimate %d", id);
        ItemEstimate result = new ItemEstimate(id, store, requestDate, limitDate, totalAmount, obs);
        return result;
    }

    @Override
    public ItemEstimate save(ItemEstimate element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
