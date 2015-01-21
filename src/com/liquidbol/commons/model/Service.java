/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

import java.io.Serializable;

/**
 * Class that represents a service.
 * @author Allan Leon
 */
public class Service extends Product implements Serializable {
    
    private Double capacity;
    private String unit;

    /**
     * Simple constructor method.
     * @param id 
     */
    public Service(String id) {
        super(id);
    }

    /**
     * Constructor method.
     * @param id
     * @param description
     * @param capacity
     * @param unit
     * @param type
     * @param cost
     * @param price 
     */
    public Service(String id, String description, Double capacity, String unit, String type, Double cost, Double price) {
        super(id, description, type, cost, price);
        this.capacity = capacity;
        this.unit = unit;
    }

    /**
     * @return the capacity
     */
    public Double getCapacity() {
        return capacity;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
