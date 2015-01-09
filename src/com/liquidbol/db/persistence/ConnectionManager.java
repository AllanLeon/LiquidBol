/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Manages the jdbc connection to the database.
 * @author Allan Leon
 */
public class ConnectionManager {
    
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private static final String PASSWORD = "134679";
    private static final String USER = "liquiduser";
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:liquidbol_db";
    
    private Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        Connection result = DriverManager.getConnection(JDBC_URL);
        LOGGER.info("new connection successfuly opened on liquidbol_db");
        return result;
    }
}
