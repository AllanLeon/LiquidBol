package com.liquidbol.model;

import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.services.StoreServices;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents a group item´s purchases.
 * @author Allan Leon
 */
public class Purchase implements Serializable {
    
    private int id;
    private Double totalAmount;
    private Date date;
    private Collection<ItemPurchase> itemPurchases;

    /**
     * Constructor method.
     * @param id
     * @param date 
     */
    public Purchase(int id, Date date) {
        this.id = id;
        this.totalAmount = 0.0;
        this.date = date;
        this.itemPurchases = new HashSet<>();
    }
    
    /**
     * Constructor method with amount.
     * @param id
     * @param totalAmount
     * @param date 
     */
    public Purchase(int id, Double totalAmount, Date date) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the totalAmount
     */
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public Double calculateTotalAmount() {
        totalAmount = 0.0;
        for (ItemPurchase itemPurchase : itemPurchases) {
            totalAmount += itemPurchase.getAmount();
        }
        return totalAmount;
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
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @param date the purchaseDate to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * @param itemPurchases the itemPurchases to set
     */
    public void setItemPurchases(Collection<ItemPurchase> itemPurchases) {
        this.itemPurchases = itemPurchases;
    }
    
    public void addItemPurchase(ItemPurchase itemPurchase) {
        itemPurchases.add(itemPurchase);
    }
    
    public Collection<ItemPurchase> searchItemPurchasesByItemId(String itemId) {
        Set<ItemPurchase> result = new HashSet<>();
        for (ItemPurchase purchase : itemPurchases) {
            if (purchase.getItem().getId().equals(itemId)) {
                result.add(purchase);
            }
        }
        return result;
    }
    
    public void execute() {
        Inventory inventory;
        List<Store> stores = new ArrayList<>(Company.getAllStores());
        StoreServices storeServices = new StoreServices();
        for (ItemPurchase itemPurchase : itemPurchases) {
            try {
                inventory = stores.get(0).getInventoryByItemId(itemPurchase.getItem().getId());
                inventory.increaseQuantityBy(itemPurchase.getQuantity());
            } catch (OperationFailedException ex) {
                try {
                    inventory = storeServices.createInventory(0, itemPurchase.getItem(), 0);
                    storeServices.addInventoryToStore(inventory, stores.get(0));
                    inventory.increaseQuantityBy(id);
                } catch (PersistenceException | ClassNotFoundException ex1) {
                    Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
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