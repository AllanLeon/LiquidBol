/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.ItemPurchaseCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemPurchase;
import com.liquidbol.model.Purchase;
import java.util.logging.Logger;

/**
 * Contains all the operations a purchase can execute.
 * @author Allan Leon
 */
public class PurchaseServices {
    
    private static final Logger LOG = Logger.getLogger(PurchaseServices.class.getName());
    
    private final ItemPurchaseCrud itemPurchaseCrudManager;
    
    public PurchaseServices() {
        this.itemPurchaseCrudManager = new ItemPurchaseCrud();
    }
    
    public ItemPurchase createItemPurchase(int id, Item item, int quantity) {
        ItemPurchase itemPurchase = new ItemPurchase(id, item, quantity);
        return itemPurchase;
    }
    
    public ItemPurchase addItemPurchaseToPurchase(ItemPurchase element, Purchase parent) throws PersistenceException, ClassNotFoundException {
        itemPurchaseCrudManager.save(element, parent);
        element = itemPurchaseCrudManager.refresh(element);
        parent.addItemPurchase(element);
        return element;
    }
    
    public void loadPurchaseItemPurchases(Purchase parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemPurchases(itemPurchaseCrudManager.findByPurchaseId(parent.getId()));
    }
}
