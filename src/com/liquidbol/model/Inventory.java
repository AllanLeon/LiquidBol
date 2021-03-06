package com.liquidbol.model;

import com.liquidbol.db.persistence.ItemCrud;
import com.liquidbol.db.persistence.PersistenceException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents an inventory.
 * @author Allan Leon
 */
public class Inventory implements Serializable {
    
    private int id;
    private Item item;
    private int quantity;

    /**
     * Constructor method.
     * @param id
     * @param item
     * @param quantity 
     */
    public Inventory(int id, Item item, int quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
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
    
    public int increaseQuantityBy(int number) {
        quantity += number;
        return quantity;
    }
    
    public int reduceQuantityBy(int number) {
        quantity -= number;
        return quantity;
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
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Inventory other = (Inventory) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}