package com.liquidbol.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Class that represents a debt's payment.
 * @author Allan Leon
 */
public class DebtPayment implements Serializable {
    
    private int id;
    private final Date payDate;
    private final Double amount;

    /**
     * Constructor method of the class.
     * @param id
     * @param payDate
     * @param amount 
     */
    public DebtPayment(int id, Date payDate, Double amount) {
        this.id = id;
        this.payDate = payDate;
        this.amount = amount;
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
     * @return the amount
     */
    public Double getAmount() {
        return amount;
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
        hash = 59 * hash + Objects.hashCode(this.amount);
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
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }
}