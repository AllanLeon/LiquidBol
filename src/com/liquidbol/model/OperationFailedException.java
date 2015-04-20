package com.liquidbol.model;

/**
 * Represents a problem on a model operation.
 * @author Allan Leon
 */
public class OperationFailedException extends Exception {

    /**
     * Creates a new instance of OperationFailedException without detail message.
     */
    public OperationFailedException() {
    }

    /**
     * Constructs an instance of OperationFailedException with the specified detail message.
     *
     * @param msg the detail message.
     */
    public OperationFailedException(String msg) {
        super(msg);
    }

    public OperationFailedException(String s, Throwable cause) {
        super(s, cause);
    }
}