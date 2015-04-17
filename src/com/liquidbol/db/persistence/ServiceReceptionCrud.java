/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Bill;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.Service;
import com.liquidbol.model.ServiceReception;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to service receptions.
 * @author Allan Leon
 */
public class ServiceReceptionCrud implements DBCrud<ServiceReception> {
    
    private static final Logger LOG = Logger.getLogger(ServiceReceptionCrud.class.getName());

    private Connection connection;

    public ServiceReception save(ServiceReception element, Bill parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO service_receptions(servicebill_id, "
                    + "service_id, rechargeableitem_id, reception_date, "
                    + "deliver_time, quantity, total_amount, obs) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parent.getId());
            statement.setString(2, element.getService().getId());
            statement.setString(3, element.getItem().getId());
            statement.setDate(4, element.getReceptionDate());
            statement.setTimestamp(5, element.getDeliverTime());
            statement.setDouble(6, element.getQuantity());
            statement.setDouble(7, element.getAmount());
            statement.setString(8, element.getObs());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service reception was not saved");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                element.setId(id);
            }
            LOG.info(String.format("service reception: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save service reception: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ServiceReception find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceReception find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_receptions WHERE servicereception_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find service reception with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read service reception", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<ServiceReception> findByServiceBillId(int serviceBillId) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_receptions WHERE servicereception_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, serviceBillId);
            ResultSet resultSet = statement.executeQuery();
            Collection<ServiceReception> result = new HashSet<>();
            while (resultSet.next()) {
                ServiceReception element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read service reception", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ServiceReception merge(ServiceReception element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE service_receptions SET reception_date=?, "
                    + "deliver_time=?, quantity=?, total_amount=?, obs=? WHERE servicereception_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDate(1, element.getReceptionDate());
            statement.setTimestamp(2, element.getDeliverTime());
            statement.setDouble(3, element.getQuantity());
            statement.setDouble(4, element.getAmount());
            statement.setString(5, element.getObs());
            statement.setInt(6, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service reception was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update service reception: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<ServiceReception> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM service_receptions";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<ServiceReception> result = new HashSet<>();
            while (resultSet.next()) {
                ServiceReception element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the service receptions", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ServiceReception refresh(ServiceReception element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public ServiceReception createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Service service = new Service(resultSet.getString(3));
        RechargeableItem item = new RechargeableItem(resultSet.getString(4));
        Date receptionDate = resultSet.getDate(5);
        Timestamp deliverTime = resultSet.getTimestamp(6);
        Double quantity = resultSet.getDouble(7);
        Double amount = resultSet.getDouble(8);
        String obs = resultSet.getString(9);
        LOG.log(Level.FINE, "Creating service reception %d", id);
        ServiceReception result = new ServiceReception(id, service, item, receptionDate, deliverTime, quantity, amount, obs);
        return result;
    }

    @Override
    public ServiceReception save(ServiceReception element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
