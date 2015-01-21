/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.model.Item;
import com.liquidbol.db.persistence.ItemCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Company;

/**
 * Contains all the operation the Company can execute.
 * @author Allan Leon
 */
public class CompanyServices {
    
    private final ItemCrud itemCrudManager;
    private final Company company;
    
    /**
     * Constructor method.
     * @param company
     */
    public CompanyServices(Company company) {
        this.itemCrudManager = new ItemCrud();
        this.company = company;
    }
    
    /**
     * Creates an item with the given information.
     * @param id
     * @param measure
     * @param description
     * @param brand
     * @param industry
     * @param type
     * @param subtype
     * @param cost
     * @param price
     * @return 
     */
    public Item createItem(String id, String measure, String description, String brand,
            String industry, String type, String subtype, Double cost, Double price) {
        if (id.equals("")) {
            throw new IllegalArgumentException("Invalid item id");
        } else if (description.equals("")){
            throw new IllegalArgumentException("Invalid item description");
        }
            else {
            Item item = new Item(id, measure, description, brand, industry, type, subtype, cost, price);
            return item;
        }
    }
    
    /**
     * Saves and adds an item to the company.
     * @param item to be saved
     * @return the saved item
     * @throws com.liquidbol.db.persistence.PersistenceException
     * @throws java.lang.ClassNotFoundException
     */
    public Item saveItem(Item item) throws PersistenceException, ClassNotFoundException {
        itemCrudManager.save(item);
        item = itemCrudManager.refresh(item);
        company.addItem(item);
        return item;
    }
    
    /**
     * Loads and adds the persisted items to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadItems() throws PersistenceException, ClassNotFoundException {
        company.setItems(itemCrudManager.getAll());
    }
}
