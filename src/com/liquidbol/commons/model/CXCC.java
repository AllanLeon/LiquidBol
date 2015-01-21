/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Class that represents a collected receivable account.
 * @author Allan Leon
 */
public class CXCC implements Serializable {
    
    private int id;
    private Double amountPaid;
    private Date payDate;

    /**
     * Constructor method.
     * @param id
     * @param amountPaid
     * @param payDate 
     */
    public CXCC(int id, Double amountPaid, Date payDate) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.payDate = payDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the amountPaid
     */
    public Double getAmountPaid() {
        return amountPaid;
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
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
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
