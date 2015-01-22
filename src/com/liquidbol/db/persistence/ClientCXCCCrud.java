/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.CXC;
import com.liquidbol.model.CXCC;
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
 * Class responsible of all persistence operations related to client's collected
 * receivable accounts.
 * @author Allan Leon
 */
public class ClientCXCCCrud implements DBCrud<CXCC> {
    
    private static final Logger LOG = Logger.getLogger(ClientCXCCCrud.class.getName());

    private Connection connection;

    public CXCC save(CXCC element, CXC parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO clients_cxcc(clientscxc_id, amount_paid, "
                    + "pay_date) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setInt(1, parent.getId());;
            statement.setDouble(2, element.getAmountPaid());
            statement.setDate(3, element.getPayDate());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("client collected receivable account was not saved");
            }
            LOG.info(String.format("client collected receivable account: %d successfuly saved", element.getId()));
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save client collected receivable account: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public CXCC find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CXCC find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM clients_cxcc WHERE clientscxcc_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find client collected receivable account with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read client collected receivable account", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public CXCC merge(CXCC element) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE clients_cxcc SET amount_paid=?, pay_date=? "
                    + "WHERE clientscxcc_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDouble(1, element.getAmountPaid());
            statement.setDate(2, element.getPayDate());
            statement.setInt(3, element.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("client collected receivable account was not updated");
            }
            return element;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update client collected receivable account: %d", element.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<CXCC> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM clients_cxcc";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<CXCC> result = new HashSet<>();
            while (resultSet.next()) {
                CXCC element = createElementFromResultSet(resultSet);
                result.add(element);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the client collected receivable accounts", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public CXCC refresh(CXCC element) throws PersistenceException, ClassNotFoundException {
        return find(element.getId());
    }

    @Override
    public CXCC createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Double amountPaid = resultSet.getDouble(3);
        Date payDate = resultSet.getDate(4);
        LOG.log(Level.FINE, "Creating client collected receivable account %d", id);
        CXCC result = new CXCC(id, amountPaid, payDate);
        return result;
    }

    @Override
    public CXCC save(CXCC element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
