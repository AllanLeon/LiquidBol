/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represents a bill.
 * @author Allan Leon
 */
public class Bill {
    
    private int id;
    private Employee employee;
    private Date date;
    private Double totalAmmount;
    private String obs;
    private Collection<BillPayment> payments;

    /**
     * Constructor method.
     * @param id
     * @param employee
     * @param date
     * @param obs 
     */
    public Bill(int id, Employee employee, Date date, String obs) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.totalAmmount = 0.0;
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
     * @return the totalAmmount
     */
    public Double getTotalAmmount() {
        return totalAmmount;
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
     * @param totalAmmount the totalAmmount to set
     */
    public void setTotalAmmount(Double totalAmmount) {
        this.totalAmmount = totalAmmount;
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
}
