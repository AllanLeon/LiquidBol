/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

import com.liquidbol.db.persistence.ItemCrud;
import com.liquidbol.db.persistence.PersistenceException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents an item sale.
 * @author Allan Leon
 */
public class ItemSale implements Serializable {
    
    private int id;
    private Item item;
    private int quantity;
    private Double amount;
    private String obs;

    /**
     * Constructor method.
     * @param id
     * @param item
     * @param quantity
     * @param obs 
     */
    public ItemSale(int id, Item item, int quantity, String obs) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.obs = obs;
        this.amount = item.getPrice() * quantity;
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param item
     * @param quantity
     * @param amount
     * @param obs 
     */
    public ItemSale(int id, Item item, int quantity, Double amount, String obs) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.obs = obs;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
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
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public void refresh() {
        try {
            item = new ItemCrud().refresh(item);
        } catch (PersistenceException ex) {
            Logger.getLogger(ItemSale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
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
        final ItemSale other = (ItemSale) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}