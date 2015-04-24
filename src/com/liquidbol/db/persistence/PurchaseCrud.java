package com.liquidbol.db.persistence;

import com.liquidbol.model.Purchase;
import com.liquidbol.model.Supplier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public Purchase save(Purchase element, Supplier parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO purchases(supplier_id, total_amount, "
                    + "purchase_date) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parent.getId());
            statement.setDouble(2, element.getTotalAmount());
            statement.setDate(3, element.getDate());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("purchase was not saved");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                element.setId(id);
            }
            LOG.info(String.format("purchase: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save purchase: %d", element.getId()), ex);
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
    
    public Collection<Purchase> findBySupplierId(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM purchases WHERE supplier_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Collection<Purchase> result = new HashSet<>();
            while (resultSet.next()) {
                Purchase element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read purchases", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Purchase merge(Purchase element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE purchases SET total_amount=?, purchase_date=? "
                    + "WHERE purchase_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDouble(1, element.getTotalAmount());
            statement.setDate(2, element.getDate());
            statement.setInt(3, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("purchase was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update purchase: %d", element.getId()), ex);
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
                Purchase element = createElementFromResultSet(resultSet);
                result.add(element);
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
    public Purchase refresh(Purchase element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Purchase createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Double ammount = resultSet.getDouble(3);
        Date date = resultSet.getDate(4);
        LOG.log(Level.FINE, "Creating purchase %d", id);
        Purchase result = new Purchase(id, ammount, date);
        return result;
    }

    @Override
    public Purchase save(Purchase element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}