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
import java.util.logging.Level;
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
    
    public ItemPurchase createItemPurchase(int id, Item item, Double unitCost, int quantity) {
        ItemPurchase itemPurchase = new ItemPurchase(id, item, unitCost, quantity);
        return itemPurchase;
    }
    
    public ItemPurchase addItemPurchaseToPurchase(ItemPurchase element, Purchase parent) throws PersistenceException, ClassNotFoundException {
        element = itemPurchaseCrudManager.save(element, parent);
        parent.addItemPurchase(element);
        LOG.info(String.format("Item purchase: %d saved", element.getId()));
        return element;
    }
    
    public void loadPurchaseItemPurchases(Purchase parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemPurchases(itemPurchaseCrudManager.findByPurchaseId(parent.getId()));
        LOG.info(String.format("%d item purchases loaded", parent.getAllItemPurchases().size()));
    }
    
    public void loadAllPurchaseInfo(Purchase parent) {
        try {
            loadPurchaseItemPurchases(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
