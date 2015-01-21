/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents a debt.
 * @author Allan Leon
 */
public class Debt implements Serializable {
    
    private int id;
    private Double amount;
    private Double maxAmount;
    private Date limitDate;
    private Collection<DebtPayment> payments;

    /**
     * Constructor method that includes all the variables as parameters
     * @param id
     * @param amount
     * @param maxAmount
     * @param limitDate 
     */
    public Debt(int id, Double amount, Double maxAmount, Date limitDate) {
        this.id = id;
        this.amount = amount;
        this.maxAmount = maxAmount;
        this.limitDate = limitDate;
        this.payments = new HashSet<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @return the maxAmount
     */
    public Double getMaxAmount() {
        return maxAmount;
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
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @param maxAmount the maxAmount to set
     */
    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * @param limitDate the limitDate to set
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }
    
    /**
     * @return the number of payments
     */
    public int getNumberOfPayments() {
        return payments.size();
    }
    
    /**
     * @return all the payments
     */
    public Collection<DebtPayment> getAllPayments() {
        return payments;
    }
    
    public Collection<DebtPayment> findPaymentsBetweenDates(Date startDate, Date endDate) {
        Set<DebtPayment> result = new HashSet<>();
        for (DebtPayment payment : payments) {
            Date paymentDate = payment.getPayDate();
            if (paymentDate.compareTo(startDate) >= 0 && paymentDate.compareTo(endDate) <= 0) {
                result.add(payment);
            }
        }
        return result;
    }
    
    public void addPayment(DebtPayment payment) {
        payments.add(payment);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.amount);
        hash = 79 * hash + Objects.hashCode(this.limitDate);
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
        final Debt other = (Debt) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.limitDate, other.limitDate)) {
            return false;
        }
        return true;
    }
}
