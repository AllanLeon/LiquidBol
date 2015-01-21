/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.commons.model.Offer;
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
 * Class responsible of all persistence operations related to item offer.
 * @author Allan Leon
 */
public class OfferCrud implements DBCrud<Offer> {
    
    private static final Logger LOG = Logger.getLogger(OfferCrud.class.getName());

    private Connection connection;

    @Override
    public Offer save(Offer element) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO offers(type, percentage, "
                    + "start_date, end_date) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, element.getType());
            statement.setDouble(2, element.getPercentage());
            statement.setDate(3, element.getStartDate());
            statement.setDate(4, element.getEndDate());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("offer was not saved");
            }
            LOG.info(String.format("offer: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save offer: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Offer find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Offer find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM offers WHERE offer_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find offer with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read offer", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Offer merge(Offer element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE offers SET percentage=?, start_date=?, "
                    + "end_date=? WHERE offer_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDouble(1, element.getPercentage());
            statement.setDate(2, element.getStartDate());
            statement.setDate(3, element.getEndDate());
            statement.setInt(4, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("offer was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update offer: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Offer> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM offers";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Offer> result = new HashSet<>();
            while (resultSet.next()) {
                Offer element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the offers", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Offer refresh(Offer offer) throws PersistenceException, ClassNotFoundException {
        return find(offer.getId());
    }

    @Override
    public Offer createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String type = resultSet.getString(2);
        Double percentage = resultSet.getDouble(3);
        Date startDate = resultSet.getDate(4);
        Date endDate = resultSet.getDate(5);
        LOG.log(Level.FINE, "Creating offer %d", id);
        Offer result = new Offer(id, type, percentage, startDate, endDate);
        return result;
    }
    
}
