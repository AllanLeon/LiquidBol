package com.liquidbol.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represents LiquidBol company.
 * @author Allan Leon
 */
public class Company implements Serializable {
    
    private static Collection<Client> clients;
    private static Collection<Item> items;
    private static Collection<Service> services;
    private static Collection<Offer> offers;
    private static Collection<Store> stores;
    private static Collection<Supplier> suppliers;

    /**
     * Constructor method.
     */
    static {
        clients = new HashSet<>();
        items = new HashSet<>();
        services = new HashSet<>();
        offers = new HashSet<>();
        stores = new HashSet<>();
        suppliers = new HashSet<>();
    }

    /**
     * @return the clients
     */
    public static Collection<Client> getAllClients() {
        return clients;
    }

    /**
     * @return the items
     */
    public static Collection<Item> getAllItems() {
        return items;
    }

    /**
     * @return the services
     */
    public static Collection<Service> getAllServices() {
        return services;
    }

    /**
     * @return the offers
     */
    public static Collection<Offer> getAllOffers() {
        return offers;
    }

    /**
     * @return the stores
     */
    public static Collection<Store> getAllStores() {
        return stores;
    }

    /**
     * @return the suppliers
     */
    public static Collection<Supplier> getAllSuppliers() {
        return suppliers;
    }

    /**
     * @param clients the clients to set
     */
    public static void setClients(Collection<Client> clients) {
        Company.clients = clients;
    }

    /**
     * @param items the items to set
     */
    public static void setItems(Collection<Item> items) {
        Company.items = items;
    }

    /**
     * @param services the services to set
     */
    public static void setServices(Collection<Service> services) {
        Company.services = services;
    }

    /**
     * @param offers the offers to set
     */
    public static void setOffers(Collection<Offer> offers) {
        Company.offers = offers;
    }

    /**
     * @param stores the stores to set
     */
    public static void setStores(Collection<Store> stores) {
        Company.stores = stores;
    }

    /**
     * @param suppliers the suppliers to set
     */
    public static void setSuppliers(Collection<Supplier> suppliers) {
        Company.suppliers = suppliers;
    }
    
    public static void addClient(Client client) {
        clients.add(client);
    }
    
    public static void addItem(Item item) {
        items.add(item);
    }
    
    public static void addService(Service service) {
        services.add(service);
    }
    
    public static void addOffer(Offer offer) {
        offers.add(offer);
    }
    
    public static void addStore(Store store) {
        stores.add(store);
    }
    
    public static void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }
    
    public static Item findItemById(String id) throws OperationFailedException {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new OperationFailedException(String.format("Item: %s not found", id));
    }
    
    public static Store findStoreById(int id) throws OperationFailedException {
        for (Store store : stores) {
            if (store.getId() == id) {
                return store;
            }
        }
        throw new OperationFailedException(String.format("Store: %d not found", id));
    }
    
    public static Client findClientByNit(int nit) throws OperationFailedException {
        for (Client client : clients) {
            if (client.getNit() == nit) {
                System.out.println(nit);
                return client;
            }
        }
        throw new OperationFailedException(String.format("Client nit: %d not found", nit));
    }
    
    public static Collection<Employee> getAllEmployees() {
        Collection<Employee> result = new HashSet<>();
        for (Store store : stores) {
            result.addAll(store.getAllEmployees());
        }
        return result;
    }
    
    public static Collection<Purchase> getAllPurchases() {
        Collection<Purchase> result = new HashSet<>();
        for (Supplier supplier : suppliers) {
            result.addAll(supplier.getAllPurchases());
        }
        return result;
    }
}