/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;

/**
 * Class that represents a collected receivable account.
 * @author Allan Leon
 */
public class CXCC {
    
    private int id;
    private Double ammountPaid;
    private Date payDate;

    /**
     * Constructor method.
     * @param id
     * @param ammountPaid
     * @param payDate 
     */
    public CXCC(int id, Double ammountPaid, Date payDate) {
        this.id = id;
        this.ammountPaid = ammountPaid;
        this.payDate = payDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the ammountPaid
     */
    public Double getAmmountPaid() {
        return ammountPaid;
    }

    /**
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param ammountPaid the ammountPaid to set
     */
    public void setAmmountPaid(Double ammountPaid) {
        this.ammountPaid = ammountPaid;
    }

    /**
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
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
        final CXCC other = (CXCC) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
