/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the jdbc connection to the database.
 * @author Allan Leon
 */
public class ConnectionManager {
    
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String PASSWORD = "134679";
    private static final String USER = "liquiduser";
    private static final String JDBC_URL = "jdbc:derby:liquidbol_db";
    
    private Connection connection;
    private boolean isConnectionUsed = false;
    
    /**
     * @return a ConnectionManager instance.
     */
    public static ConnectionManager getInstance() {
        return ConnectionManagerHolder.INSTANCE;
    }
    
    /**
     * Connects to the embedded database.
     * @return a new connection to the database
     * @throws SQLException if there is a problem with the database
     * @throws ClassNotFoundException if the driver is not found
     */
    private Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        LOGGER.info("New connection successfuly opened on liquidbol_db");
        return result;
    }
    
    /**
     * Returns a connection to the database, but first checks and waits 
     * if the connection is used.
     * @return a connection to the database
     * @throws SQLException if there is a problem with the database
     * @throws ClassNotFoundException if the driver is not found
     */
    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        if (isConnectionUsed) {
            try {
                wait();
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        LOGGER.info("Locking a connection to liquidbol_db");
        isConnectionUsed = true;
        if (connection == null || connection.isClosed()) {
            connection = connect();
        }
        connection.setAutoCommit(false);
        return connection;
    }
    
    /**
     * Releases the connection that is currently being used and makes a commit.
     * @throws SQLException if there is a problem with the database
     */
    public synchronized void releaseConnection() throws SQLException {
        LOGGER.info("Releasing connection");
        isConnectionUsed = false;
        if (connection == null) {
            LOGGER.info("There is no connection to the database to release");
            return;
        }
        notify();
        connection.commit();
    }
    
    /**
     * Aborts the connection that is currently being used and makes a rollback.
     * @throws SQLException if there is a problem with the database
     */
    public synchronized void abortConnection() throws SQLException {
        LOGGER.warning("Database connection cancelled");
        connection.rollback();
        isConnectionUsed = false;
        notify();
    }
    
    /**
     * Closes the connection to the database.
     * @throws SQLException if there is a problem with the database
     */
    public void close() throws SQLException {
        if (connection != null || !connection.isClosed()) {
            connection.close();
            isConnectionUsed = false;
            LOGGER.warning("Database connection closed");
        }
    }
    
    /**
     * Auxiliary class that contains a static instance of ConnectionManager.
     */
    private static class ConnectionManagerHolder {
        private static final ConnectionManager INSTANCE = new ConnectionManager();
        
        static {
            System.setProperty("derby.language.sequence.preallocator", "1");
        }
    }
}
