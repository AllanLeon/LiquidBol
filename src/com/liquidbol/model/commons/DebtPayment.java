/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.util.Objects;

/**
 * Class that represents a debt's payment.
 * @author Allan Leon
 */
public class DebtPayment {
    
    private int id;
    private Date payDate;
    private Double ammount;

    /**
     * Constructor method of the class.
     * @param id
     * @param payDate
     * @param ammount 
     */
    public DebtPayment(int id, Date payDate, Double ammount) {
        this.id = id;
        this.payDate = payDate;
        this.ammount = ammount;
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
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.payDate);
        hash = 59 * hash + Objects.hashCode(this.ammount);
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
        final DebtPayment other = (DebtPayment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.payDate, other.payDate)) {
            return false;
        }
        if (!Objects.equals(this.ammount, other.ammount)) {
            return false;
        }
        return true;
    }
}
