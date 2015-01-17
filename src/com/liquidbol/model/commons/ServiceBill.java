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
 * Class that represents a service bill.
 * @author Allan Leon
 */
public class ServiceBill extends Bill {

    private Collection<ServiceReception> serviceReceptions;
    private Collection<BillPayment> payments;
    
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
        this.payments = new HashSet<>();
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
    
    public int getNumberOfPayments() {
        return payments.size();
    }
    
    public Collection<BillPayment> getAllPayments() {
        return payments;
    }
    
    public void addPayment(BillPayment payment) {
        payments.add(payment);
    }
}
