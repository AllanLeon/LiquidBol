/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Date;

/**
 * Class that represents a employee.
 * @author Allan Leon
 */
public class Employee extends Person {

    /**
     * Constructor method.
     * @param id
     * @param name
     * @param lastname
     * @param address
     * @param phone
     * @param phone2
     * @param email
     * @param regDate 
     */
    public Employee(int id, String name, String lastname, String address, int phone, int phone2, String email, Date regDate) {
        super(id, name, lastname, address, phone, phone2, email, regDate);
    }
}
