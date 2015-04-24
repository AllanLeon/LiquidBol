package com.liquidbol.db.persistence;

import com.liquidbol.model.Bill;
import com.liquidbol.model.Client;
import com.liquidbol.model.Employee;
import com.liquidbol.model.Store;
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
 * Class responsible of all persistence operations related to item bills.
 * @author Allan Leon
 */
public class BillCrud implements DBCrud<Bill> {
    
    private static final Logger LOG = Logger.getLogger(BillCrud.class.getName());
    private Connection connection;

    public Bill save(Bill element, Client parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO bills(client_id, store_id, employee_id, "
                    + "bill_date, total_amount, is_billed, is_route, obs) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parent.getId());
            statement.setInt(2, element.getStore().getId());
            statement.setInt(3, element.getEmployee().getId());
            statement.setDate(4, element.getDate());
            statement.setDouble(5, element.getTotalAmount());
            statement.setBoolean(6, element.isBilled());
            statement.setBoolean(7, element.isRoute());
            statement.setString(8, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Bill was not saved");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                element.setId(id);
            }
            LOG.info(String.format("Bill: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save item bill: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Bill find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bill find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM bills WHERE bill_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find bill with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read bill", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<Bill> findByClientId(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM bills WHERE client_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Collection<Bill> result = new HashSet<>();
            while (resultSet.next()) {
                Bill element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read bill", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Bill merge(Bill element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE bills SET bill_date=?, total_amount=?, "
                    + "is_billed=?, is_route=?, obs=? WHERE bill_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getDate());
            statement.setDouble(2, element.getTotalAmount());
            statement.setBoolean(3, element.isBilled());
            statement.setBoolean(4, element.isRoute());
            statement.setString(5, element.getObs());
            statement.setInt(6, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Bill was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update bill: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Bill> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM bills";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Bill> result = new HashSet<>();
            while (resultSet.next()) {
                Bill element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the bills", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Bill refresh(Bill element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Bill createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Store store = new Store(resultSet.getInt(3));
        Employee employee = new Employee(resultSet.getInt(4));
        Date date = resultSet.getDate(5);
        Double totalAmount = resultSet.getDouble(6);
        boolean billed = resultSet.getBoolean(7);
        boolean route = resultSet.getBoolean(8);
        String obs = resultSet.getString(9);
        LOG.log(Level.FINE, "Creating bill %d", id);
        Bill result = new Bill(id, store, employee, date, totalAmount, billed, route, obs);
        return result;
    }

    @Override
    public Bill save(Bill element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}