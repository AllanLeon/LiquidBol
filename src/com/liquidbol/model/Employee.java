package com.liquidbol.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class that represents a employee.
 * @author Allan Leon
 */
public class Employee extends Person implements Serializable {

    private String password;
    private String type;

    /**
     * Simple constructor method.
     * @param id 
     */
    public Employee(int id) {
        super(id);
    }

    /**
     * Constructor method.
     * @param password
     * @param type
     * @param id
     * @param name
     * @param lastname
     * @param address
     * @param phone
     * @param phone2
     * @param email
     * @param regDate 
     */
    public Employee(int id, String name, String lastname, String address, int phone, int phone2, String email, Date regDate, String password, String type) {
        super(id, name, lastname, address, phone, phone2, email, regDate);
        this.password = password;
        this.type = type;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}