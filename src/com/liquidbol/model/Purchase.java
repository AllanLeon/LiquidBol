package com.liquidbol.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that represents a group item´s purchases.
 * @author Allan Leon
 */
public class Purchase implements Serializable {
    
    private int id;
    private Double totalAmount;
    private Date date;
    private String supplierName;
    private Collection<ItemPurchase> itemPurchases;

    /**
     * Constructor method.
     * @param id
     * @param supplierName
     * @param date 
     */
    public Purchase(int id, String supplierName, Date date) {
        this.id = id;
        this.supplierName = supplierName;
        this.totalAmount = 0.0;
        this.date = date;
        this.itemPurchases = new HashSet<>();
    }
    
    /**
     * Constructor method.
     * @param id
     * @param date 
     */
    public Purchase(int id, Date date) {
        this.id = id;
        this.supplierName = "";
        this.totalAmount = 0.0;
        this.date = date;
        this.itemPurchases = new HashSet<>();
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param supplierName
     * @param totalAmount
     * @param date 
     */
    public Purchase(int id, String supplierName, Double totalAmount, Date date) {
        this.id = id;
        this.supplierName = supplierName;
        this.totalAmount = totalAmount;
        this.date = date;
    }
    
    /**
     * Constructor method with amount.
     * @param id
     * @param totalAmount
     * @param date 
     */
    public Purchase(int id, Double totalAmount, Date date) {
        this.id = id;
        this.supplierName = "";
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
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
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
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
    
    public Collection<ItemPurchase> findItemPurchasesByItemId(String itemId) {
        Set<ItemPurchase> result = new HashSet<>();
        for (ItemPurchase purchase : itemPurchases) {
            if (purchase.getItem().getId().equals(itemId)) {
                result.add(purchase);
            }
        }
        return result;
    }
    
    public void execute() throws OperationFailedException {
        Inventory inventory;
        List<Store> stores = new ArrayList<>(Company.getAllStores());
        for (ItemPurchase itemPurchase : itemPurchases) {
            inventory = stores.get(0).getInventoryByItemId(itemPurchase.getItem().getId());
            inventory.increaseQuantityBy(itemPurchase.getQuantity());
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