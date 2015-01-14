/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;

/**
 * Class that represents an expense.
 * @author Allan Leon
 */
public class Expense {
    
    private int id;
    private Date payDate;
    private String description;
    private Double ammount;
    private String obs;

    public Expense(int id, Date payDate, String description, Double ammount, String obs) {
        this.id = id;
        this.payDate = payDate;
        this.description = description;
        this.ammount = ammount;
        this.obs = obs;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
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
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(Double ammount) {
        this.ammount = ammount;
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
        hash = 59 * hash + this.id;
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
        final Expense other = (Expense) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
