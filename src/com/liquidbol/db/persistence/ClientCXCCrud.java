/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.commons.CXC;
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
 * Class responsible of all persistence operations related to client's
 * receivable accounts.
 * @author Allan Leon
 */
public class ClientCXCCrud implements DBCrud<CXC> {
    
    private static final Logger LOG = Logger.getLogger(ClientCXCCrud.class.getName());

    private Connection connection;

    @Override
    public CXC save(CXC element) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO clients_cxc(client_id, clientscxc_debt, "
                    + "clientscxc_creditamount, clientscxc_creditdate, "
                    + "clientscxc_state) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, 0);
            statement.setDouble(2, element.getDebt());
            statement.setDouble(3, element.getCreditMaxAmount());
            statement.setDate(4, element.getCreditLimitDate());
            statement.setString(5, element.getState());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("client receivable account was not saved");
            }
            LOG.info(String.format("client receivable account: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save client receivable account: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public CXC find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CXC find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM clients_cxc WHERE clientscxc_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find client receivable account with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read client receivable account", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public CXC merge(CXC element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE table SET clientscxc_debt=?, clientscxc_creditamount=?, "
                    + "clientscxc_creditdate=?, clientscxc_state=? WHERE clientscxc_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDouble(1, element.getDebt());;
            statement.setDouble(2, element.getCreditMaxAmount());
            statement.setDate(3, element.getCreditLimitDate());
            statement.setString(4, element.getState());
            statement.setInt(5, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("client receivable account was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update client receivable account: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<CXC> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM clients_cxc";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<CXC> result = new HashSet<>();
            while (resultSet.next()) {
                CXC element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the client receivable accounts", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public CXC refresh(CXC element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public CXC createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Double debt = resultSet.getDouble(3);
        Double creditMaxAmount = resultSet.getDouble(4);
        Date creaditLimitDate = resultSet.getDate(5);
        String state = resultSet.getString(6);
        LOG.log(Level.FINE, "Creating client receivable account %d", id);
        CXC result = new CXC(id, debt, creditMaxAmount, creaditLimitDate, state);
        return result;
    }
}
