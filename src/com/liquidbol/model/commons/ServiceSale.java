/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;

/**
 * Class that represents a service sale.
 * @author Allan Leon
 */
public class ServiceSale {
    
    private int id;
    private Date payDate;
    private Double ammountPaid;
    private String obs;

    /**
     * Constructor method.
     * @param id
     * @param payDate
     * @param ammountPaid
     * @param obs 
     */
    public ServiceSale(int id, Date payDate, Double ammountPaid, String obs) {
        this.id = id;
        this.payDate = payDate;
        this.ammountPaid = ammountPaid;
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
     * @return the ammountPaid
     */
    public Double getAmmountPaid() {
        return ammountPaid;
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
     * @param ammountPaid the ammountPaid to set
     */
    public void setAmmountPaid(Double ammountPaid) {
        this.ammountPaid = ammountPaid;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.id;
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
        final ServiceSale other = (ServiceSale) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
