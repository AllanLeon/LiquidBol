/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represents a service bill.
 * @author Allan Leon
 */
public class ServiceBill extends Bill implements Serializable {

    private Collection<ServiceReception> serviceReceptions;
    
    /**
     * Constructor method.
     * @param id
     * @param employee
     * @param date
     * @param billed
     * @param obs 
     */
    public ServiceBill(int id, Employee employee, Date date, boolean billed, String obs) {
        super(id, employee, date, billed, obs);
        this.serviceReceptions = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param employee
     * @param date
     * @param totalAmount
     * @param billed
     * @param obs 
     */
    public ServiceBill(int id, Employee employee, Date date, Double totalAmount, boolean billed, String obs) {
        super(id, employee, date, totalAmount, billed, obs);
        this.serviceReceptions = new HashSet<>();
    }
    
    public int getNumberOfServiceReceptions() {
        return serviceReceptions.size();
    }
    
    public Collection<ServiceReception> getAllServiceReceptions() {
        return serviceReceptions;
    }
    
    public void addServiceReception(ServiceReception serviceReception) {
        serviceReceptions.add(serviceReception);
    }

    /**
     * @param serviceReceptions the serviceReceptions to set
     */
    public void setServiceReceptions(Collection<ServiceReception> serviceReceptions) {
        this.serviceReceptions = serviceReceptions;
    }
}
