package com.liquidbol.model;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;

/**
 * Class that represents a receivable account.
 * @author Allan Leon
 */
public class CXC implements Serializable {
    
    private int id;
    private Double debt;
    private Double creditMaxAmount;
    private Date creditLimitDate;
    private String state;
    private Collection<CXCC> collectedReceivableAccounts;

    /**
     * Constructor method.
     * @param id
     * @param debt
     * @param creditMaxAmount
     * @param creditLimitDate
     * @param state 
     */
    public CXC(int id, Double debt, Double creditMaxAmount, Date creditLimitDate, String state) {
        this.id = id;
        this.debt = debt;
        this.creditMaxAmount = creditMaxAmount;
        this.creditLimitDate = creditLimitDate;
        this.state = state;
        this.collectedReceivableAccounts = new HashSet<>();
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
     * @return the creditMaxAmount
     */
    public Double getCreditMaxAmount() {
        return creditMaxAmount;
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
     * @param creditMaxAmount the creditMaxAmount to set
     */
    public void setCreditMaxAmount(Double creditMaxAmount) {
        this.creditMaxAmount = creditMaxAmount;
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
    
    /**
     * @param collectedReceivableAccounts the collectedReceivableAccounts to set
     */
    public void setCollectedReceivableAccounts(Collection<CXCC> collectedReceivableAccounts) {
        this.collectedReceivableAccounts = collectedReceivableAccounts;
    }
    
    public int getNumberOfCollectedReceivableAccounts() {
        return collectedReceivableAccounts.size();
    }
    
    public Collection<CXCC> getAllCollectedReceivableAccounts() {
        return collectedReceivableAccounts;
    }
    
    public void addCollectedReceivableAccount(CXCC cxcc) {
        collectedReceivableAccounts.add(cxcc);
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