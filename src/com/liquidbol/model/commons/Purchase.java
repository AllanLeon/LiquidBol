/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Date;

/**
 * Class that represents a purchase.
 * @author Allan Leon
 */
public class Purchase {
    
    private int id;
    private Item item;
    private int quantity;
    private Date date;
    private Double ammount;

    /**
     * Constructor method.
     * @param id
     * @param item
     * @param quantity
     * @param date 
     */
    public Purchase(int id, Item item, int quantity, Date date) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.date = date;
        this.ammount = item.getCost() * quantity;
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the ammount
     */
    public Double getAmmount() {
        return ammount;
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
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(Double ammount) {
        this.ammount = ammount;
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
        final Purchase other = (Purchase) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
