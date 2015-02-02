/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.ItemRequestCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemEstimate;
import com.liquidbol.model.ItemRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations an item estimate can execute.
 * @author Allan Leon
 */
public class ItemEstimateServices {
    
    private static final Logger LOG = Logger.getLogger(ItemEstimateServices.class.getName());
    
    private final ItemRequestCrud itemRequestCrudManager;
    
    public ItemEstimateServices() {
        this.itemRequestCrudManager = new ItemRequestCrud();
    }
    
    public ItemRequest createItemRequest(int id, Item item, int quantity) {
        ItemRequest itemRequest = new ItemRequest(id, item, quantity);
        return itemRequest;
    }
    
    public ItemRequest addItemRequestToItemEstimate(ItemRequest element, ItemEstimate parent)
            throws PersistenceException, ClassNotFoundException {
        element = itemRequestCrudManager.save(element, parent);
        parent.addRequest(element);
        LOG.info(String.format("Item request: %d saved", element.getId()));
        return element;
    }
    
    public void loadItemEstimateRequests(ItemEstimate parent) throws PersistenceException, ClassNotFoundException {
        parent.setRequests(itemRequestCrudManager.findByItemEstimateId(parent.getId()));
        LOG.info(String.format("%d item requests loaded", parent.getAllRequests().size()));
    }
    
    public void loadAllItemEstimateInfo(ItemEstimate parent) {
        try {
            loadItemEstimateRequests(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
