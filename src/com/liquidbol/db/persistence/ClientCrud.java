/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.Client;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * Class responsible of all persistence operations related to clients.
 * @author Allan Leon
 */
public class ClientCrud implements DBCrud<Client> {
    
    private static final Logger LOG = Logger.getLogger(ClientCrud.class.getName());

    private Connection connection;

    @Override
    public Client save(Client element) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO clients(client_name, client_lastname, "
                    + "client_nit, client_address, client_phone, client_phone2, "
                    + "client_email, client_companyname, client_frequency, "
                    + "client_regdate) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, element.getName());
            statement.setString(2, element.getLastname());
            statement.setInt(3, element.getNit());
            statement.setString(4, element.getAddress());
            statement.setInt(5, element.getPhone());
            statement.setInt(6, element.getPhone2());
            statement.setString(7, element.getEmail());
            statement.setString(8, element.getCompanyName());
            statement.setInt(9, element.getFrequency());
            statement.setDate(10, element.getRegDate());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("tableex was not saved");
            }
            LOG.info(String.format("tableex: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save tableex: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Client find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM clients WHERE client_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find tableex with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read tableex", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Client merge(Client element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE table SET client_nit=?, client_address=?, "
                    + "client_phone=?, client_phone2=?, client_email=?, "
                    + "client_companyname=?, client_frequency=?  WHERE table_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, element.getNit());
            statement.setString(2, element.getAddress());
            statement.setInt(3, element.getPhone());
            statement.setInt(4, element.getPhone2());
            statement.setString(5, element.getEmail());
            statement.setString(6, element.getCompanyName());
            statement.setInt(7, element.getFrequency());
            statement.setInt(8, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("tableex was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update tableex: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Client> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM clients";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Client> result = new HashSet<>();
            while (resultSet.next()) {
                Client element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the tableexs", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Client refresh(Client element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public Client createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String lastname = resultSet.getString(3);
        int nit = resultSet.getInt(4);
        String address = resultSet.getString(5);
        int phone = resultSet.getInt(6);
        int phone2 = resultSet.getInt(7);
        String email = resultSet.getString(8);
        String companyName = resultSet.getString(9);
        int frequency = resultSet.getInt(10);
        Date regDate = resultSet.getDate(11);
        LOG.log(Level.FINE, "Creating tableex %d", id);
        Client result = new Client(nit, companyName, frequency, id, name, lastname, address, phone, phone2, email, regDate);
        return result;
    }
}
