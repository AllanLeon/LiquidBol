package com.liquidbol.db.persistence;

import com.liquidbol.model.Item;
import com.liquidbol.model.ItemEstimate;
import com.liquidbol.model.ItemRequest;
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
 * Class responsible of all persistence operations related to item requests.
 * @author Allan Leon
 */
public class ItemRequestCrud implements DBCrud<ItemRequest> {
    
    private static final Logger LOG = Logger.getLogger(ItemRequestCrud.class.getName());
    private Connection connection;

    public ItemRequest save(ItemRequest element, ItemEstimate parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO item_requests(itemestimate_id, item_id, "
                    + "quantity, total_amount) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parent.getId());
            statement.setString(2, element.getItem().getId());
            statement.setInt(3, element.getQuantity());
            statement.setDouble(4, element.getAmount());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item request was not saved");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                element.setId(id);
            }
            LOG.info(String.format("item request: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item request: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemRequest find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemRequest find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_requests WHERE itemrequest_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find item request with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item request", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<ItemRequest> findByItemEstimateId(int itemEstimateId) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_requests WHERE itemestimate_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itemEstimateId);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemRequest> result = new HashSet<>();
            while (resultSet.next()) {
                ItemRequest element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read item request", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemRequest merge(ItemRequest element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE item_requests SET quantity=?, total_amount=? "
                    + "WHERE itemrequest_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, element.getQuantity());
            statement.setDouble(2, element.getAmount());
            statement.setInt(3, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("item request was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update item request: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ItemRequest> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM item_requests";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ItemRequest> result = new HashSet<>();
            while (resultSet.next()) {
                ItemRequest element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the item requests", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ItemRequest refresh(ItemRequest element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public ItemRequest createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Item item = new Item(resultSet.getString(3));
        int quantity = resultSet.getInt(4);
        Double amount = resultSet.getDouble(5);
        LOG.log(Level.FINE, "Creating item request %d", id);
        ItemRequest result = new ItemRequest(id, item, quantity, amount);
        return result;
    }

    @Override
    public ItemRequest save(ItemRequest element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}