/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.model.Debt;
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
 * Class responsible of all persistence operations related to suppliers debts.
 * @author Allan Leon
 */
public class SupplierDebtCrud implements DBCrud<Debt>{
    
    private static final Logger LOG = Logger.getLogger(SupplierDebtCrud.class.getName());

    private Connection connection;

    public Debt save(Debt debt, Supplier parent) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO supplier_debts(supplier_id,"
                    + "amount, limit_date, max_amount) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parent.getId());
            statement.setDouble(2, debt.getAmount());
            statement.setDate(3, debt.getLimitDate());
            statement.setDouble(4, debt.getMaxAmount());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Supplier debt was not saved");
            }
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                debt.setId(id);
            }
            LOG.info(String.format("Supplier debt: %d successfuly saved", debt.getId()));
            return debt;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save supplier debt: %d", debt.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Debt find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Debt find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM supplier_debts WHERE debt_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find supplier debt with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read supplier debt", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection<Debt> findBySupplierId(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM supplier_debts WHERE supplier_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Collection<Debt> result = new HashSet<>();
            while (resultSet.next()) {
                Debt debt = createElementFromResultSet(resultSet);
                result.add(debt);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read supplier debts", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Debt merge(Debt debt) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE supplier_debts SET amount=?, limit_date=?, max_amount=? WHERE debt_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setDouble(1, debt.getAmount());
            statement.setDate(2, debt.getLimitDate());
            statement.setDouble(3, debt.getMaxAmount());
            statement.setInt(4, debt.getId());
            return debt;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update supplier debt: %d", debt.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Debt> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM supplier_debts";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Debt> result = new HashSet<>();
            while (resultSet.next()) {
                Debt debt = createElementFromResultSet(resultSet);
                result.add(debt);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the suppliers debts", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Debt refresh(Debt debt) throws PersistenceException, ClassNotFoundException {
        return find(debt.getId());
    }

    @Override
    public Debt createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Double amount = resultSet.getDouble(3);
        Date limitDate = resultSet.getDate(4);
        Double maxAmount = resultSet.getDouble(5);
        LOG.log(Level.FINE, "Creating supplier debt %d", id);
        Debt result = new Debt(id, amount, maxAmount, limitDate);
        return result;
    }

    @Override
    public Debt save(Debt element) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
