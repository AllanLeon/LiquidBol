/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.ClientCrud;
import com.liquidbol.model.Item;
import com.liquidbol.db.persistence.ItemCrud;
import com.liquidbol.db.persistence.OfferCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.ServiceCrud;
import com.liquidbol.db.persistence.StoreCrud;
import com.liquidbol.db.persistence.SupplierCrud;
import com.liquidbol.model.Company;
import com.liquidbol.model.Supplier;
import java.sql.Date;

/**
 * Contains all the operation the Company can execute.
 * @author Allan Leon
 */
public class CompanyServices {
    
    private final ClientCrud clientCrudManager;
    private final ItemCrud itemCrudManager;
    private final ServiceCrud serviceCrudManager;
    private final OfferCrud offerCrudManager;
    private final SupplierCrud supplierCrudManager;
    private final StoreCrud storeCrudManager;
    private final Company company;
    
    /**
     * Constructor method.
     * @param company
     */
    public CompanyServices(Company company) {
        this.clientCrudManager = new ClientCrud();
        this.itemCrudManager = new ItemCrud();
        this.serviceCrudManager = new ServiceCrud();
        this.offerCrudManager = new OfferCrud();
        this.supplierCrudManager = new SupplierCrud();
        this.storeCrudManager = new StoreCrud();
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
     * @return the created item
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
     * Creates a supplier with the given information.
     * @param id
     * @param name
     * @param lastname
     * @param phone
     * @param phone2
     * @param company
     * @param address
     * @param email
     * @param city
     * @return the created supplier
     */
    public Supplier createSupplier(int id, String name, String lastname, int phone,
            int phone2, String company, String address, String email, String city) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid supplier name");
        } else {
            Supplier supplier = new Supplier(id, name, lastname, phone, phone2,
                    company, address, email, city, new Date(new java.util.Date().getTime()));
            return supplier;
        }
    }
    
    /**
     * Saves and adds an item to the company.
     * @param item to be saved
     * @return the saved item
     * @throws PersistenceException
     * @throws ClassNotFoundException
     */
    public Item saveItem(Item item) throws PersistenceException, ClassNotFoundException {
        itemCrudManager.save(item);
        item = itemCrudManager.refresh(item);
        company.addItem(item);
        return item;
    }
    
    /**
     * Saves and adds a supplier to the company.
     * @param supplier
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Supplier saveSupplier(Supplier supplier) throws PersistenceException, ClassNotFoundException {
        supplierCrudManager.save(supplier);
        supplier = supplierCrudManager.refresh(supplier);
        company.addSupplier(supplier);
        return supplier;
    }
    
    /**
     * Loads and adds the persisted items to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadItems() throws PersistenceException, ClassNotFoundException {
        company.setItems(itemCrudManager.getAll());
    }
    
    /**
     * Loads and adds the persisted suppliers to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadSuppliers() throws PersistenceException, ClassNotFoundException {
        company.setSuppliers(supplierCrudManager.getAll());
    }
}
