/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model;

import com.liquidbol.db.persistence.EmployeeCrud;
import com.liquidbol.db.persistence.PersistenceException;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents a bill.
 * @author Allan Leon
 */
public class Bill implements Serializable {
    
    private int id;
    private Employee employee;
    private Date date;
    private Double totalAmount;
    private boolean billed;
    private String obs;
    private Collection<BillPayment> payments;

    /**
     * Constructor method.
     * @param id
     * @param employee
     * @param date
     * @param billed
     * @param obs 
     */
    public Bill(int id, Employee employee, Date date, boolean billed, String obs) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.totalAmount = 0.0;
        this.billed = billed;
        this.obs = obs;
        this.payments = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param employee
     * @param date
     * @param totalAmount
     * @param obs 
     */
    public Bill(int id, Employee employee, Date date, Double totalAmount, boolean billed, String obs) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.totalAmount = totalAmount;
        this.billed = billed;
        this.obs = obs;
        this.payments = new HashSet<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the totalAmount
     */
    public Double getTotalAmount() {
        return totalAmount;
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
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public int getNumberOfPayments() {
        return payments.size();
    }
    
    public Collection<BillPayment> getAllPayments() {
        return payments;
    }
    
    public void addPayment(BillPayment payment) {
        payments.add(payment);
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public void refresh() {
        try {
            employee = new EmployeeCrud().refresh(employee);
        } catch (PersistenceException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Bill other = (Bill) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * @return the billed
     */
    public boolean isBilled() {
        return billed;
    }

    /**
     * @param billed the billed to set
     */
    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(Collection<BillPayment> payments) {
        this.payments = payments;
    }
}
