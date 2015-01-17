/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Class that represents a service reception.
 * @author Allan Leon
 */
public class ServiceReception {
    
    private int id;
    private Service service;
    private RechargeableItem item;
    private Date receptionDate;
    private Timestamp deliverTime;
    private Double totalAmmount;
    private String obs;

    /**
     * Constructor method.
     * @param id
     * @param service
     * @param item
     * @param receptionDate
     * @param deliverTime
     * @param totalAmmount
     * @param obs 
     */
    public ServiceReception(int id, Service service, RechargeableItem item, Date receptionDate, Timestamp deliverTime, Double totalAmmount, String obs) {
        this.id = id;
        this.service = service;
        this.item = item;
        this.receptionDate = receptionDate;
        this.deliverTime = deliverTime;
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
     * @return the item
     */
    public RechargeableItem getItem() {
        return item;
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
     * @param item the item to set
     */
    public void setItem(RechargeableItem item) {
        this.item = item;
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
