/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represents LiquidBol company.
 * @author Allan Leon
 */
public class Company {
    
    private Collection<Client> clients;
    private Collection<Item> items;
    private Collection<Service> services;
    private Collection<Offer> offers;
    private Collection<Store> stores;
    private Collection<Supplier> suppliers;

    /**
     * Constructor method.
     */
    public Company() {
        this.clients = new HashSet<>();
        this.items = new HashSet<>();
        this.services = new HashSet<>();
        this.offers = new HashSet<>();
        this.stores = new HashSet<>();
        this.suppliers = new HashSet<>();
    }

    /**
     * @return the clients
     */
    public Collection<Client> getAllClients() {
        return clients;
    }

    /**
     * @return the items
     */
    public Collection<Item> getAllItems() {
        return items;
    }

    /**
     * @return the services
     */
    public Collection<Service> getAllServices() {
        return services;
    }

    /**
     * @return the offers
     */
    public Collection<Offer> getAllOffers() {
        return offers;
    }

    /**
     * @return the stores
     */
    public Collection<Store> getAllStores() {
        return stores;
    }

    /**
     * @return the suppliers
     */
    public Collection<Supplier> getAllSuppliers() {
        return suppliers;
    }
    
    public void addClient(Client client) {
        clients.add(client);
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void addService(Service service) {
        services.add(service);
    }
    
    public void addOffer(Offer offer) {
        offers.add(offer);
    }
    
    public void addStore(Store store) {
        stores.add(store);
    }
    
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }
}
