/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model;

import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.StoreCrud;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents an item bill.
 * @author Allan Leon
 */
public class ItemBill extends Bill implements Serializable {
    
    private Store store;
    private boolean route;
    private Collection<ItemSale> itemSales;

    /**
     * Constructor method.
     * @param id
     * @param employee
     * @param date
     * @param obs
     * @param billed
     * @param store
     * @param route 
     */
    public ItemBill(int id, Employee employee, Store store, Date date, boolean billed, boolean route, String obs) {
        super(id, employee, date, billed, obs);
        this.store = store;
        this.route = route;
        this.itemSales = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param store
     * @param route
     * @param id
     * @param employee
     * @param date
     * @param billed
     * @param totalAmount
     * @param obs 
     */
    public ItemBill(int id, Employee employee, Store store, Date date, Double totalAmount, boolean billed, boolean route, String obs) {
        super(id, employee, date, totalAmount, billed, obs);
        this.store = store;
        this.route = route;
        this.itemSales = new HashSet<>();
    }

    /**
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * @return the route
     */
    public boolean isRoute() {
        return route;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(boolean route) {
        this.route = route;
    }
    
    /**
     * @param itemSales the itemSales to set
     */
    public void setItemSales(Collection<ItemSale> itemSales) {
        this.itemSales = itemSales;
    }
    
    public int getNumberOfItemSales() {
        return itemSales.size();
    }
    
    public Collection<ItemSale> getAllItemSales() {
        return itemSales;
    }
    
    public void addItemSale(ItemSale itemSale) {
        itemSales.add(itemSale);
    }
    
    @Override
    public void refresh() {
        super.refresh();
        try {
            store = new StoreCrud().refresh(store);
        } catch (PersistenceException ex) {
            Logger.getLogger(ItemBill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
