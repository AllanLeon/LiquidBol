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
 * Class that represents and item estimate.
 * @author Allan Leon
 */
public class ItemEstimate {
    
    private int id;
    private Store store;
    private Date requestDate;
    private Date limitDate;
    private Double totalAmount;
    private String obs;
    private Collection<ItemRequest> requests;

    /**
     * Constructor method.
     * @param id
     * @param store
     * @param requestDate
     * @param limitDate
     * @param totalAmount
     * @param obs 
     */
    public ItemEstimate(int id, Store store, Date requestDate, Date limitDate, Double totalAmount, String obs) {
        this.id = id;
        this.store = store;
        this.requestDate = requestDate;
        this.limitDate = limitDate;
        this.totalAmount = totalAmount;
        this.obs = obs;
        this.requests = new HashSet<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @return the limitDate
     */
    public Date getLimitDate() {
        return limitDate;
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
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @param limitDate the limitDate to set
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public int getNumberOfRequests() {
        return requests.size();
    }
    
    public Collection<ItemRequest> getAllRequests() {
        return requests;
    }
    
    public void addRequest(ItemRequest request) {
        requests.add(request);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final ItemEstimate other = (ItemEstimate) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
