/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import com.liquidbol.commons.model.Supplier;
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
 * Class responsible of all persistence operations related to suppliers.
 * @author Allan Leon
 */
public class SupplierCrud implements DBCrud<Supplier> {

    private static final Logger LOG = Logger.getLogger(SupplierCrud.class.getName());

    private Connection connection;
    
    @Override
    public Supplier save(Supplier supplier) throws PersistenceException, ClassNotFoundException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
            String insert = "INSERT INTO suppliers(supplier_name,"
                    + "supplier_lastname, supplier_phone, supplier_phone2,"
                    + "supplier_company, supplier_address, supplier_email,"
                    + "supplier_city, supplier_regdate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareCall(insert);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getLastname());
            statement.setInt(3, supplier.getPhone());
            statement.setInt(4, supplier.getPhone2());
            statement.setString(5, supplier.getCompany());
            statement.setString(6, supplier.getAddress());
            statement.setString(7, supplier.getEmail());
            statement.setString(8, supplier.getCity());
            statement.setDate(9, supplier.getRegDate());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenceException("Supplier was not saved");
            }
            LOG.info(String.format("Supplier: %s %s successfuly saved", supplier.getName(), supplier.getLastname()));
            return supplier;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to save supplier: %d", supplier.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Supplier find(String id) throws PersistenceException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Supplier find(int id) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM suppliers WHERE supplier_id = ?";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            } else {
                throw new PersistenceException(String.format("Couldn't find supplier with code %d", id));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read supplier", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Supplier merge(Supplier supplier) throws PersistenceException, ClassNotFoundException {
        try {
            String query = "UPDATE suppliers SET supplier_phone=?, supplier_phone2=?, "
                    + "supplier_company=?, supplier_address=?, supplier_email=?, "
                    + "supplier_city=? WHERE supplier_id=?";
            PreparedStatement statement = 
                ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, supplier.getPhone());
            statement.setInt(2, supplier.getPhone2());
            statement.setString(3, supplier.getCompany());
            statement.setString(4, supplier.getAddress());
            statement.setString(5, supplier.getEmail());
            statement.setString(6, supplier.getCity());
            statement.setInt(7, supplier.getId());
            return supplier;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException(String.format("Failed to update supplier: %d", supplier.getId()), ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Collection<Supplier> getAll() throws PersistenceException, ClassNotFoundException {
        try {
            String query = "SELECT * FROM suppliers";
            connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Collection<Supplier> result = new HashSet<>();
            while (resultSet.next()) {
                Supplier supplier = createElementFromResultSet(resultSet);
                result.add(supplier);
            }
            return result;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new PersistenceException("Failed to read the suppliers", ex);
        } finally {
            try {
                ConnectionManager.getInstance().releaseConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Supplier refresh(Supplier supplier) throws PersistenceException, ClassNotFoundException {
        return find(supplier.getId());
    }

    @Override
    public Supplier createElementFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String lastname = resultSet.getString(3);
        int phone = resultSet.getInt(4);
        int phone2 = resultSet.getInt(5);
        String company = resultSet.getString(6);
        String address = resultSet.getString(7);
        String city = resultSet.getString(8);
        Date regDate = resultSet.getDate(9);
        LOG.log(Level.FINE, "Creating supplier %d", id);
        Supplier result = new Supplier(id, name, lastname, phone, phone2, company, address, name, city, regDate);
        return result;
    }
}
