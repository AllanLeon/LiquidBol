/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a service reception.
 * @author Allan Leon
 */
public class ServiceReception {
    
    private int id;
    private Service service;
    private Employee employee;
    private Date receptionDate;
    private Timestamp deliverTime;
    private Double ammountPaid;
    private Double totalAmmount;
    private String obs;
    private Collection<ServiceSale> sales;

    public ServiceReception(int id, Service service, Employee employee, Date receptionDate, Timestamp deliverTime, Double ammountPaid, Double totalAmmount, String obs) {
        this.id = id;
        this.service = service;
        this.employee = employee;
        this.receptionDate = receptionDate;
        this.deliverTime = deliverTime;
        this.ammountPaid = ammountPaid;
        this.totalAmmount = totalAmmount;
        this.obs = obs;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @return the receptionDate
     */
    public Date getReceptionDate() {
        return receptionDate;
    }

    /**
     * @return the deliverTime
     */
    public Timestamp getDeliverTime() {
        return deliverTime;
    }

    /**
     * @return the ammountPaid
     */
    public Double getAmmountPaid() {
        return ammountPaid;
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
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @param receptionDate the receptionDate to set
     */
    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    /**
     * @param deliverTime the deliverTime to set
     */
    public void setDeliverTime(Timestamp deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * @param ammountPaid the ammountPaid to set
     */
    public void setAmmountPaid(Double ammountPaid) {
        this.ammountPaid = ammountPaid;
    }

    /**
     * @param totalAmmount the totalAmmount to set
     */
    public void setTotalAmmount(Double totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public Collection<ServiceSale> getAllSales() {
        return sales;
    }
    
    public Collection<ServiceSale> findSalesBetweenDates(Date startDate, Date endDate) {
        Set<ServiceSale> result = new HashSet<>();
        for (ServiceSale sale : sales) {
            Date paymentDate = sale.getPayDate();
            if (paymentDate.compareTo(startDate) >= 0 && paymentDate.compareTo(endDate) <= 0) {
                result.add(sale);
            }
        }
        return result;
    }
    
    public void addSale(ServiceSale sale) {
        sales.add(sale);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.getId();
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
        final ServiceReception other = (ServiceReception) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }
}
