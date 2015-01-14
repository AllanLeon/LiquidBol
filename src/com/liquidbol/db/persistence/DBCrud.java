/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Interface that contains all the necessary methods for a crud implementation.
 * @author Allan Leon
 * @param <T> the table class of which the crud will be implemented
 */
public interface DBCrud<T> {
    
    /**
     * Saves a new instance of the class this crud is implementing.
     * @param element to be saved
     * @return the saved item
     * @throws PersistenceException if the saving operation fails
     * @throws java.lang.ClassNotFoundException if the driver is missing
     */
    public T save(T element) throws PersistenceException, ClassNotFoundException;
    
    /**
     * Reads from database an instance of the class this crud is implementing
     * with the given id.
     * @param id the id key of the item
     * @return the instance of the item
     * @throws PersistenceException if cannot read the instance
     * @throws java.lang.ClassNotFoundException if the driver is missing
     */
    public T find(String id) throws PersistenceException, ClassNotFoundException;
    public T find(int id) throws PersistenceException, ClassNotFoundException;
    
    /**
     * Updates and synchronize the instance of the class this crud is
     * implementing state with the database.
     * @param element the instance to be synchronized
     * @return the synchronized instance
     * @throws java.lang.ClassNotFoundException if the driver is missing
     */
    public T merge(T element) throws PersistenceException, ClassNotFoundException;
    
    /**
     * Reads an instance of the class this crud is implementing that exist
     * in the database.
     * @return a collection with all the obtained instances
     * @throws PersistenceException if cannot read the instances
     * @throws java.lang.ClassNotFoundException if the driver is missing
     */
    public Collection<T> getAll() throws PersistenceException, ClassNotFoundException;
    
    /**
     * Updates the given instance of the class this crud is implementing 
     * with the information saved in the database of the same instance.
     * @param element instance to be updated
     * @return the updated instance
     * @throws PersistenceException if cannot read the instances
     * @throws java.lang.ClassNotFoundException if the driver is missing
     */
    public T refresh(T element) throws PersistenceException, ClassNotFoundException;
    
    /**
     * Creates a instance of the class this crud is implementing with the
     * information from the database.
     * @param resultSet information that will be contained in the instance
     * @return the created instance
     * @throws java.sql.SQLException if the information cannot be read
     */
    public T createElementFromResultSet(ResultSet resultSet) throws SQLException;
}
