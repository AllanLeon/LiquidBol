/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Allan Leon
 */
public class SupplierDebt {
    
    private int id;
    private Double ammount;
    private Double maxAmmount;
    private Date limitDate;

    /**
     * Constructor method that includes all the variables as parameters
     * @param id
     * @param ammount
     * @param maxAmmount
     * @param limitDate 
     */
    public SupplierDebt(int id, Double ammount, Double maxAmmount, Date limitDate) {
        this.id = id;
        this.ammount = ammount;
        this.maxAmmount = maxAmmount;
        this.limitDate = limitDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
    }

    /**
     * @return the maxAmmount
     */
    public Double getMaxAmmount() {
        return maxAmmount;
    }

    /**
     * @return the limitDate
     */
    public Date getLimitDate() {
        return limitDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

    /**
     * @param maxAmmount the maxAmmount to set
     */
    public void setMaxAmmount(Double maxAmmount) {
        this.maxAmmount = maxAmmount;
    }

    /**
     * @param limitDate the limitDate to set
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.ammount);
        hash = 71 * hash + Objects.hashCode(this.maxAmmount);
        hash = 71 * hash + Objects.hashCode(this.limitDate);
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
        final SupplierDebt other = (SupplierDebt) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ammount, other.ammount)) {
            return false;
        }
        if (!Objects.equals(this.maxAmmount, other.maxAmmount)) {
            return false;
        }
        if (!Objects.equals(this.limitDate, other.limitDate)) {
            return false;
        }
        return true;
    }
}
