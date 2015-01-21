/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.commons.model.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible of all persistence operations related to services.
 * @author Allan Leon
 */
public class ServiceCrud implements DBCrud<Service> {
    
    private static final Logger LOG = Logger.getLogger(ServiceCrud.class.getName());

    private Connection connection;

    @Override
    public Service save(Service element) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO services(service_id, service_capacity, service_unit "
                    + "service_description, service_type, service_cost, "
                    + "service_price, service_dif, service_profit) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, element.getId());
            statement.setDouble(2, element.getCapacity());
            statement.setString(3, element.getUnit());
            statement.setString(4, element.getDescription());
            statement.setString(5, element.getType());
            statement.setDouble(6, element.getCost());
            statement.setDouble(7, element.getPrice());
            statement.setDouble(8, element.getDif());
            statement.setDouble(9, element.getProfit());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service was not saved");
            }
            LOG.info(String.format("service: %s successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save service: %s", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Service find(String id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM services WHERE service_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find service with code %s", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read service", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Service find(int id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service merge(Service element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE services SET service_cost=?, service_price=?, "
                    + "service_dif=?, service_profit=? WHERE service_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDouble(1, element.getCost());
            statement.setDouble(2, element.getPrice());
            statement.setDouble(3, element.getDif());
            statement.setDouble(4, element.getProfit());
            statement.setString(5, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("service was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update service: %s", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Service> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM services";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Service> result = new HashSet<>();
            while (resultSet.next()) {
                Service element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the services", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Service refresh(Service element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Service createElementFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(1);
        Double capacity = resultSet.getDouble(2);
        String unit = resultSet.getString(3);
        String description = resultSet.getString(4);
        String type = resultSet.getString(5);
        Double cost = resultSet.getDouble(6);
        Double price = resultSet.getDouble(7);
        LOG.log(Level.FINE, "Creating service %d", id);
        Service result = new Service(id, description, capacity, unit, type, cost, price);
        return result;
    }
}
