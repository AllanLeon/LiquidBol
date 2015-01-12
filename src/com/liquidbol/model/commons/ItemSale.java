/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.sql.Date;

/**
 * Class that represents an item sale.
 * @author Allan Leon
 */
public class ItemSale {
    
    private int id;
    private Item item;
    private Store store;
    private Date payDate;
    private int quantity;
    private Double ammount;
    private Double paidAmmount;
    private boolean route;
    private String obs;

    /**
     * Constructor method.
     * @param id
     * @param item
     * @param store
     * @param payDate
     * @param quantity
     * @param paidAmmount
     * @param route
     * @param obs 
     */
    public ItemSale(int id, Item item, Store store, Date payDate, int quantity, Double paidAmmount, boolean route, String obs) {
        this.id = id;
        this.item = item;
        this.store = store;
        this.payDate = payDate;
        this.quantity = quantity;
        this.ammount = item.getPrice() * quantity;
        this.paidAmmount = paidAmmount;
        this.route = route;
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
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
    }

    /**
     * @return the paidAmmount
     */
    public Double getPaidAmmount() {
        return paidAmmount;
    }

    /**
     * @return the route
     */
    public boolean isRoute() {
        return route;
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
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

    /**
     * @param paidAmmount the paidAmmount to set
     */
    public void setPaidAmmount(Double paidAmmount) {
        this.paidAmmount = paidAmmount;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(boolean route) {
        this.route = route;
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
        final ItemSale other = (ItemSale) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
