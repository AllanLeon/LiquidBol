/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.util.Objects;

/**
 * Class that represents a rechargeable item.
 * @author Allan Leon
 */
public class RechargeableItem {
    
    private String id;
    private String description;
    private Double capacity;
    private String unit;
    private String type;
    private Date warrantyLimitDate;
    private String obs;

    /**
     * Simple constructor method.
     * @param id 
     */
    public RechargeableItem(String id) {
        this.id = id;
    }

    /**
     * Constructor method.
     * @param id
     * @param description
     * @param capacity
     * @param unit
     * @param type
     * @param warrantyLimitDate
     * @param obs 
     */
    public RechargeableItem(String id, String description, Double capacity, String unit, String type, Date warrantyLimitDate, String obs) {
        this.id = id;
        this.description = description;
        this.capacity = capacity;
        this.unit = unit;
        this.type = type;
        this.warrantyLimitDate = warrantyLimitDate;
        this.obs = obs;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the warrantyLimitDate
     */
    public Date getWarrantyLimitDate() {
        return warrantyLimitDate;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param warrantyLimitDate the warrantyLimitDate to set
     */
    public void setWarrantyLimitDate(Date warrantyLimitDate) {
        this.warrantyLimitDate = warrantyLimitDate;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final RechargeableItem other = (RechargeableItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
