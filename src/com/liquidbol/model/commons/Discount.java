/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class that represents a discount.
 * @author Allan Leon
 */
public class Discount implements Serializable {
    
    private int id;
    private int minQuantity;
    private Double percentage;

    /**
     * Constructor method of the class
     * @param id
     * @param minQuantity
     * @param percentage 
     */
    public Discount(int id, int minQuantity, Double percentage) {
        this.id = id;
        this.minQuantity = minQuantity;
        this.percentage = percentage;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the minQuantity
     */
    public int getMinQuantity() {
        return minQuantity;
    }

    /**
     * @return the percentage
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param minQuantity the minQuantity to set
     */
    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.minQuantity;
        hash = 89 * hash + Objects.hashCode(this.percentage);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Discount other = (Discount) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.minQuantity != other.minQuantity) {
            return false;
        }
        if (!Objects.equals(this.percentage, other.percentage)) {
            return false;
        }
        return true;
    }
}
