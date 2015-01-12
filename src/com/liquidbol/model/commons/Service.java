/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

/**
 * Class that represents a service.
 * @author Allan Leon
 */
public class Service extends Product {
    
    private String description;

    /**
     * Constructor method.
     * @param description
     * @param id
     * @param measure
     * @param type
     * @param cost
     * @param price 
     */
    public Service(String description, String id, String measure, String type, Double cost, Double price) {
        super(id, measure, type, cost, price);
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
