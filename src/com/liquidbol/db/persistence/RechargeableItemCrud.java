/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.Client;
import com.liquidbol.model.commons.RechargeableItem;
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
 * Class responsible of all persistence operations related to rechargeable items.
 * @author Allan Leon
 */
public class RechargeableItemCrud implements DBCrud<RechargeableItem> {
    
    private static final Logger LOG = Logger.getLogger(RechargeableItemCrud.class.getName());

    private Connection connection;

    public RechargeableItem save(RechargeableItem element, Client parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO rechargeable_items(rechargeableitem_id, "
                    + "client_id, description, type, capacity, unit, warranty_limit_date, "
                    + "obs) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, element.getId());
            statement.setInt(2, parent.getId());
            statement.setString(3, element.getDescription());
            statement.setString(4, element.getType());
            statement.setDouble(5, element.getCapacity());
            statement.setString(6, element.getUnit());
            statement.setDate(7, element.getWarrantyLimitDate());
            statement.setString(8, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("rechargeable item was not saved");
            }
            LOG.info(String.format("rechargeable item: %s successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save rechargeable item: %s", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public RechargeableItem find(String id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM rechargeable_items WHERE rechargeableitem_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find rechargeable item with code %s", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read rechargeable item", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public RechargeableItem find(int id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RechargeableItem merge(RechargeableItem element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE rechargeable_items SET obs=? WHERE rechargeableitem_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, element.getObs());
            statement.setString(2, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("rechargeable item was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update rechargeable item: %s", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<RechargeableItem> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM rechargeable_items";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<RechargeableItem> result = new HashSet<>();
            while (resultSet.next()) {
                RechargeableItem element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the rechargeable items", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public RechargeableItem refresh(RechargeableItem element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public RechargeableItem createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String description = resultSet.getString(3);
        String type = resultSet.getString(4);
        Double capacity = resultSet.getDouble(5);
        String unit = resultSet.getString(6);
        Date warrantyLimitDate = resultSet.getDate(7);
        String obs = resultSet.getString(8);
        LOG.log(Level.FINE, "Creating rechargeable item %d", id);
        RechargeableItem result = new RechargeableItem(obs, description, capacity, unit, type, warrantyLimitDate, obs);
        return result;
    }

    @Override
    public RechargeableItem save(RechargeableItem element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
