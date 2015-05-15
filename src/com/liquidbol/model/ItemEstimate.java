package com.liquidbol.model;

import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.StoreCrud;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Class that represents and item estimate.
 * @author Allan Leon
 */
public class ItemEstimate implements Serializable {
    
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
    
    /**
     * @param requests the requests to set
     */
    public void setRequests(Collection<ItemRequest> requests) {
        this.requests = requests;
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
    
    public Collection<ItemRequest> searchItemRequestsByItemId(String itemId) {
        Set<ItemRequest> result = new HashSet<>();
        for (ItemRequest current : requests) {
            if (StringUtils.containsIgnoreCase(current.getItem().getId(),itemId)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ItemRequest> searchItemRequestsByItemDescription(String itemDescription) {
        Set<ItemRequest> result = new HashSet<>();
        for (ItemRequest current : requests) {
            if (StringUtils.containsIgnoreCase(current.getItem().getDescription(),itemDescription)) {
                result.add(current);
            }
        }
        return result;
    }
    
    public void refresh() {
        try {
            store = new StoreCrud().refresh(store);
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(ItemEstimate.class.getName()).log(Level.SEVERE, null, ex);
        }
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