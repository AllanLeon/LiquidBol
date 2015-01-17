/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a group item´s purchases.
 * @author Allan Leon
 */
public class Purchase {
    
    private int id;
    private Double totalAmmount;
    private Date date;
    private Collection<ItemPurchase> itemPurchases;

    /**
     * Constructor method.
     * @param id
     * @param totalAmmount
     * @param date 
     */
    public Purchase(int id, Double totalAmmount, Date date) {
        this.id = id;
        this.totalAmmount = totalAmmount;
        this.date = date;
        this.itemPurchases = new HashSet<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the totalAmmount
     */
    public Double getTotalAmmount() {
        return totalAmmount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the itemPurchases
     */
    public Collection<ItemPurchase> getAllItemPurchases() {
        return itemPurchases;
    }
    
    public int getNumberOfItemPurchases() {
        return itemPurchases.size();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param totalAmmount the totalAmmount to set
     */
    public void setTotalAmmount(Double totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    /**
     * @param date the purchaseDate to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void addItemPurchase(ItemPurchase itemPurchase) {
        itemPurchases.add(itemPurchase);
    }
    
    public Collection<ItemPurchase> findItemPurchasesByItemId(String itemId) {
        Set<ItemPurchase> result = new HashSet<>();
        for (ItemPurchase purchase : itemPurchases) {
            if (purchase.getItem().getId().equals(itemId)) {
                result.add(purchase);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final Purchase other = (Purchase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
