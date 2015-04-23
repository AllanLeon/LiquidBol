package com.liquidbol.model;

import com.liquidbol.db.persistence.ItemCrud;
import com.liquidbol.db.persistence.PersistenceException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents a purchase of a single item.
 * @author Allan Leon
 */
public class ItemPurchase implements Serializable {
    
    private int id;
    private Item item;
    private Double unitCost;
    private int quantity;
    private Double amount;

    /**
     * Constructor method.
     * @param id
     * @param item
     * @param unitCost
     * @param quantity
     */
    public ItemPurchase(int id, Item item, Double unitCost, int quantity) {
        this.id = id;
        item.setCost(unitCost);
        this.item = item;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.amount = unitCost * quantity;
    }

    /**
     * Constructor method with amount.
     * @param id
     * @param item
     * @param unitCost
     * @param quantity
     * @param amount 
     */
    public ItemPurchase(int id, Item item, Double unitCost, int quantity, Double amount) {
        this.id = id;
        item.setCost(unitCost);
        this.item = item;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.amount = amount;
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
     * @return the unitCost
     */
    public Double getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost the unitCost to set
     */
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
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
        this.amount = unitCost * quantity;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void refresh() {
        try {
            item = new ItemCrud().refresh(item);
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(ItemSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
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
        final ItemPurchase other = (ItemPurchase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}