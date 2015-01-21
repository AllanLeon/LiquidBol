/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

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
     * @param obs 
     */
    public ServiceBill(int id, Employee employee, Date date, String obs) {
        super(id, employee, date, obs);
        this.serviceReceptions = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param employee
     * @param date
     * @param totalAmount
     * @param obs 
     */
    public ServiceBill(int id, Employee employee, Date date, Double totalAmount, String obs) {
        super(id, employee, date, totalAmount, obs);
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
}
