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
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.Offer;
import com.liquidbol.model.Service;
import com.liquidbol.model.Store;
import com.liquidbol.model.Supplier;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations the Company can execute.
 * @author Allan Leon
 */
public class CompanyServices {
    
    private static final Logger LOG = Logger.getLogger(CompanyServices.class.getName());
    
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
        loadCompanyInfo();
    }
    
    private void loadCompanyInfo() {
        try {
            loadOffers();
            loadItems();
            loadServices();
            loadStores();
            loadClients();
            loadSuppliers();
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates a client with the given information.
     * @param id
     * @param name
     * @param lastname
     * @param nit
     * @param billName
     * @param address
     * @param phone
     * @param phone2
     * @param email
     * @param companyName
     * @param route
     * @return the created client
     */
    public Client createClient(int id, String name, String lastname, int nit,
            String billName, String address, int phone, int phone2, String email,
            String companyName, boolean route) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid client name");
        } else {
            Client client = new Client(id, name, lastname, nit, billName, address,
                    phone, phone2, email, null, companyName, phone2, route);
            return client;
        }
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
        } else {
            Item item = new Item(id, measure, description, brand, industry, type, subtype, cost, price);
            return item;
        }
    }
    
    /**
     * Creates a service with the given data.
     * @param id
     * @param description
     * @param capacity
     * @param unit
     * @param type
     * @param cost
     * @param price
     * @return the created service
     */
    public Service createService(String id, String description, Double capacity,
            String unit, String type, Double cost, Double price) {
        if (id.equals("")) {
            throw new IllegalArgumentException("Invalid service id");
        } else if (description.equals("")){
            throw new IllegalArgumentException("Invalid service description");
        } else {
            Service service = new Service(id, description, capacity, unit, type, cost, price);
            return service;
        }
    }
    
    /**
     * Creates an offer with the given data.
     * @param id
     * @param type
     * @param percentage
     * @param startDate
     * @param endDate
     * @return the created offer
     */
    public Offer createOffer(int id, String type, Double percentage, Date startDate,
            Date endDate) {
        if (type.equals("")) {
            throw new IllegalArgumentException("Invalid offer type");
        } else {
            Offer offer = new Offer(id, type, percentage, startDate, endDate);
            return offer;
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
     * Creates a store with the given data.
     * @param id
     * @param name
     * @param address
     * @param phone
     * @return the created store
     */
    public Store createStore(int id, String name, String address, int phone) {
        if (address.equals("")) {
            throw new IllegalArgumentException("Invalid store address");
        } else {
            Store store = new Store(id, name, address, phone);
            return store;
        }
    }
    
    /**
     * Saves and adds a client to the company.
     * @param client
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Client saveClient(Client client) throws PersistenceException, ClassNotFoundException {
        clientCrudManager.save(client);
        client = clientCrudManager.refresh(client);
        company.addClient(client);
        return client;
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
     * Saves and adds a service to the company.
     * @param service
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Service saveService(Service service) throws PersistenceException, ClassNotFoundException {
        serviceCrudManager.save(service);
        service = serviceCrudManager.refresh(service);
        company.addService(service);
        return service;
    }
    
    /**
     * Saves and adds an offer to the company.
     * @param offer
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Offer saveOffer(Offer offer) throws PersistenceException, ClassNotFoundException {
        offerCrudManager.save(offer);
        offer = offerCrudManager.refresh(offer);
        company.addOffer(offer);
        return offer;
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
     * Saves and adds a store to the company.
     * @param store
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Store saveStore(Store store) throws PersistenceException, ClassNotFoundException {
        storeCrudManager.save(store);
        store = storeCrudManager.refresh(store);
        company.addStore(store);
        return store;
    }
    
    /**
     * Loads and adds the persisted clients to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadClients() throws PersistenceException, ClassNotFoundException {
        company.setClients(clientCrudManager.getAll());
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
     * Loads and adds the persisted services to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadServices() throws PersistenceException, ClassNotFoundException {
        company.setServices(serviceCrudManager.getAll());
    }
    
    /**
     * Loads and adds the persisted offers to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadOffers() throws PersistenceException, ClassNotFoundException {
        company.setOffers(offerCrudManager.getAll());
    }
    
    /**
     * Loads and adds the persisted suppliers to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadSuppliers() throws PersistenceException, ClassNotFoundException {
        company.setSuppliers(supplierCrudManager.getAll());
    }
    
    /**
     * Loads and adds the persisted stores to the company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadStores() throws PersistenceException, ClassNotFoundException {
        company.setStores(storeCrudManager.getAll());
    }
}
