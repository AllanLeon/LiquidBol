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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents a bill payment.
 * @author Allan Leon
 */
public class BillPayment implements Serializable {
    
    private int id;
    private Employee employee;
    private Date payDate;
    private Double amountPaid;
    private String obs;

    /**
     * Constructor method.
     * @param id
     * @param employee
     * @param payDate
     * @param amountPaid
     * @param obs 
     */
    public BillPayment(int id, Employee employee, Date payDate, Double amountPaid, String obs) {
        this.id = id;
        this.employee = employee;
        this.payDate = payDate;
        this.amountPaid = amountPaid;
        this.obs = obs;
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
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * @return the amountPaid
     */
    public Double getAmountPaid() {
        return amountPaid;
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
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
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
            Logger.getLogger(BillPayment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BillPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
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
        final BillPayment other = (BillPayment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
