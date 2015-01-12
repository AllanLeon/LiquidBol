/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Date;

/**
 * Class that represents a receivable account.
 * @author Allan Leon
 */
public class CXC {
    
    private int id;
    private Double debt;
    private Double creditMaxAmmount;
    private Date creditLimitDate;
    private String state;

    /**
     * Constructor method.
     * @param id
     * @param debt
     * @param creditMaxAmmount
     * @param creditLimitDate
     * @param state 
     */
    public CXC(int id, Double debt, Double creditMaxAmmount, Date creditLimitDate, String state) {
        this.id = id;
        this.debt = debt;
        this.creditMaxAmmount = creditMaxAmmount;
        this.creditLimitDate = creditLimitDate;
        this.state = state;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the debt
     */
    public Double getDebt() {
        return debt;
    }

    /**
     * @return the creditMaxAmmount
     */
    public Double getCreditMaxAmmount() {
        return creditMaxAmmount;
    }

    /**
     * @return the creditLimitDate
     */
    public Date getCreditLimitDate() {
        return creditLimitDate;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param debt the debt to set
     */
    public void setDebt(Double debt) {
        this.debt = debt;
    }

    /**
     * @param creditMaxAmmount the creditMaxAmmount to set
     */
    public void setCreditMaxAmmount(Double creditMaxAmmount) {
        this.creditMaxAmmount = creditMaxAmmount;
    }

    /**
     * @param creditLimitDate the creditLimitDate to set
     */
    public void setCreditLimitDate(Date creditLimitDate) {
        this.creditLimitDate = creditLimitDate;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final CXC other = (CXC) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
