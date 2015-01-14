/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db.persistence;

/**
 * Represents a problem on a persistence operation.
 * @author Allan Leon
 */
public class PersistenceException extends Exception {

    /**
     * Creates a new instance of PersistenceException without detail message.
     */
    public PersistenceException() {
    }

    /**
     * Constructs an instance of PersistenceException with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public PersistenceException(String msg) {
        super(msg);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
