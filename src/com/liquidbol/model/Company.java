/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represents LiquidBol company.
 * @author Allan Leon
 */
public class Company implements Serializable {
    
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

    /**
     * @param clients the clients to set
     */
    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    /**
     * @param services the services to set
     */
    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(Collection<Offer> offers) {
        this.offers = offers;
    }

    /**
     * @param stores the stores to set
     */
    public void setStores(Collection<Store> stores) {
        this.stores = stores;
    }

    /**
     * @param suppliers the suppliers to set
     */
    public void setSuppliers(Collection<Supplier> suppliers) {
        this.suppliers = suppliers;
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
    
    public Item findItemById(String id) throws OperationFailedException {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new OperationFailedException(String.format("Item: %s not found", id));
    }
}
