/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.io.Serializable;

/**
 * Class that represents a service.
 * @author Allan Leon
 */
public class Service extends Product implements Serializable {
    
    private String description;

    /**
     * Simple constructor method.
     * @param id 
     */
    public Service(String id) {
        super(id);
    }

    /**
     * Constructor method.
     * @param description
     * @param capacity
     * @param unit
     * @param id
     * @param type
     * @param cost
     * @param price 
     */
    public Service(String id, Double capacity, String unit, String description, String type, Double cost, Double price) {
        super(id, capacity, unit, type, cost, price);
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
