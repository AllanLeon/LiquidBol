/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class that represents an offer.
 * @author Allan Leon
 */
public class Offer implements Serializable {
    
    private int id;
    private String type;
    private Double percentage;
    private Date startDate;
    private Date endDate;

    /**
     * Constructor method of the class.
     * @param id
     * @param type
     * @param percentage
     * @param startDate
     * @param endDate 
     */
    public Offer(int id, String type, Double percentage, Date startDate, Date endDate) {
        this.id = id;
        this.type = type;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the percentage
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
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
        final Offer other = (Offer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
