/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Discount;
import com.liquidbol.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to item discounts.
 * @author Allan Leon
 */
public class ItemDiscountCrud implements DBCrud<Discount> {

    private static final Logger LOG = Logger.getLogger(ItemDiscountCrud.class.getName());

    private Connection connection;
    
    public Discount save(Discount discount, Item parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_discounts(item_id, min_quantity, "
                    + "percentage) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, parent.getId());
            statement.setInt(2, discount.getMinQuantity());
            statement.setDouble(3, discount.getPercentage());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Item discount was not saved");
            }
            LOG.info(String.format("Item discount: %d successfuly saved", discount.getId()));
            return discount;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save Item discount:%d", discount.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Discount find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Discount find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_discounts WHERE discount_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item discount with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item discount", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Discount merge(Discount discount) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE item_discounts SET min_quantity=?, percentage=? "
                    + "WHERE discount_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, discount.getMinQuantity());
            statement.setDouble(2, discount.getPercentage());
            statement.setInt(3, discount.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Item discount was not updated");
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item discount:%d", discount.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return discount;
    }

    @Override
    public Collection<Discount> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_discounts";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Discount> result = new HashSet<>();
            while (resultSet.next()) {
                Discount element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the item discounts", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Discount refresh(Discount element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Discount createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        int minQuantity = resultSet.getInt(3);
        Double percentage = resultSet.getDouble(4);
        LOG.log(Level.FINE, "Creating item discount %d", id);
        Discount result = new Discount(id, minQuantity, percentage);
        return result;
    }

    @Override
    public Discount save(Discount element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
