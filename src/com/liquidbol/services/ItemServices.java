/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.ItemDiscountCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Discount;
import com.liquidbol.model.Item;
import java.util.logging.Logger;

/**
 * Contains all the operations an item can execute.
 * @author Allan Leon
 */
public class ItemServices {
    
    private static final Logger LOG = Logger.getLogger(ItemServices.class.getName());
    
    private final ItemDiscountCrud discountCrudManager;
    
    public ItemServices() {
        this.discountCrudManager = new ItemDiscountCrud();
    }
    
    public Discount createDiscount(int id, int minQuantity, Double percentage) {
        if (minQuantity == 0) {
            throw new IllegalArgumentException("Invalid discount minimum quantity");
        } else {
            Discount discount = new Discount(id, minQuantity, percentage);
            return discount;
        }
    }
    
    public Discount addDiscountToItem(Discount element, Item parent) throws PersistenceException, ClassNotFoundException {
        element = discountCrudManager.save(element, parent);
        parent.addDiscount(element);
        return element;
    }
    
    public void loadItemDiscounts(Item parent) throws PersistenceException, ClassNotFoundException {
        parent.setDiscounts(discountCrudManager.findByItemId(parent.getId()));
    }
}
