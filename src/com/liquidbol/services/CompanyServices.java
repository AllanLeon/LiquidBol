package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
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
import com.liquidbol.model.Employee;
import com.liquidbol.model.Offer;
import com.liquidbol.model.OperationFailedException;
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
    //private final Company Company;
    
    /**
     * Constructor method.
     * @param Company
     */
    public CompanyServices(/*Company Company*/) {
        this.clientCrudManager = new ClientCrud();
        this.itemCrudManager = new ItemCrud();
        this.serviceCrudManager = new ServiceCrud();
        this.offerCrudManager = new OfferCrud();
        this.supplierCrudManager = new SupplierCrud();
        this.storeCrudManager = new StoreCrud();
        //this.Company = Company;
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
     * @param CompanyName
     * @param route
     * @return the created client
     */
    public Client createClient(int id, String name, String lastname, int nit,
            String billName, String address, int phone, int phone2, String email,
            String CompanyName, boolean route) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid client name");
        } else {
            Client client = new Client(id, name, lastname, nit, billName, address,
                    phone, phone2, email, new Date(new java.util.Date().getTime()), CompanyName, phone2, route);
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
     * @param Company
     * @param address
     * @param email
     * @param city
     * @return the created supplier
     */
    public Supplier createSupplier(int id, String name, String lastname, int phone,
            int phone2, String Company, String address, String email, String city) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid supplier name");
        } else {
            Supplier supplier = new Supplier(id, name, lastname, phone, phone2,
                    Company, address, email, city, new Date(new java.util.Date().getTime()));
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
     * Saves and adds a client to the Company.
     * @param client
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Client saveClient(Client client) throws PersistenceException, ClassNotFoundException {
        client = clientCrudManager.save(client);
        Company.addClient(client);
        LOG.info(String.format(ANSI_PURPLE + "Client: %d saved" + ANSI_RESET, client.getId()));
        return client;
    }
    
    /**
     * Saves and adds an item to the Company.
     * @param item to be saved
     * @return the saved item
     * @throws PersistenceException
     * @throws ClassNotFoundException
     */
    public Item saveItem(Item item) throws PersistenceException, ClassNotFoundException {
        item = itemCrudManager.save(item);
        Company.addItem(item);
        LOG.info(String.format(ANSI_PURPLE + "Item: %s saved" + ANSI_RESET, item.getId()));
        return item;
    }
    
    /**
     * Saves and adds a service to the Company.
     * @param service
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Service saveService(Service service) throws PersistenceException, ClassNotFoundException {
        service = serviceCrudManager.save(service);
        Company.addService(service);
        LOG.info(String.format(ANSI_PURPLE + "Service: %s saved" + ANSI_RESET, service.getId()));
        return service;
    }
    
    /**
     * Saves and adds an offer to the Company.
     * @param offer
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Offer saveOffer(Offer offer) throws PersistenceException, ClassNotFoundException {
        offer = offerCrudManager.save(offer);
        Company.addOffer(offer);
        LOG.info(String.format(ANSI_PURPLE + "Offer: %d saved" + ANSI_RESET, offer.getId()));
        return offer;
    }
    
    /**
     * Saves and adds a supplier to the Company.
     * @param supplier
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Supplier saveSupplier(Supplier supplier) throws PersistenceException, ClassNotFoundException {
        supplier = supplierCrudManager.save(supplier);
        Company.addSupplier(supplier);
        LOG.info(String.format(ANSI_PURPLE + "Supplier: %d saved" + ANSI_RESET, supplier.getId()));
        return supplier;
    }
    
    /**
     * Saves and adds a store to the Company.
     * @param store
     * @return
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public Store saveStore(Store store) throws PersistenceException, ClassNotFoundException {
        store = storeCrudManager.save(store);
        Company.addStore(store);
        LOG.info(String.format(ANSI_PURPLE + "Store: %d saved" + ANSI_RESET, store.getId()));
        return store;
    }
    
    /**
     * Loads and adds the persisted clients to the Company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadClients() throws PersistenceException, ClassNotFoundException {
        Company.setClients(clientCrudManager.getAll());
        LOG.info(String.format(ANSI_CYAN + "%d clients loaded" + ANSI_RESET, Company.getAllClients().size()));
    }
    
    /**
     * Loads and adds the persisted items to the Company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadItems() throws PersistenceException, ClassNotFoundException {
        Company.setItems(itemCrudManager.getAll());
        LOG.info(String.format(ANSI_CYAN + "%d items loaded" + ANSI_RESET, Company.getAllItems().size()));
    }
    
    /**
     * Loads and adds the persisted services to the Company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadServices() throws PersistenceException, ClassNotFoundException {
        Company.setServices(serviceCrudManager.getAll());
        LOG.info(String.format(ANSI_CYAN + "%d services loaded" + ANSI_RESET, Company.getAllServices().size()));
    }
    
    /**
     * Loads and adds the persisted offers to the Company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadOffers() throws PersistenceException, ClassNotFoundException {
        Company.setOffers(offerCrudManager.getAll());
        LOG.info(String.format(ANSI_CYAN + "%d offers loaded" + ANSI_RESET, Company.getAllOffers().size()));
    }
    
    /**
     * Loads and adds the persisted suppliers to the Company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadSuppliers() throws PersistenceException, ClassNotFoundException {
        Company.setSuppliers(supplierCrudManager.getAll());
        LOG.info(String.format(ANSI_CYAN + "%d suppliers loaded" + ANSI_RESET, Company.getAllSuppliers().size()));
    }
    
    /**
     * Loads and adds the persisted stores to the Company.
     * @throws PersistenceException
     * @throws ClassNotFoundException 
     */
    public void loadStores() throws PersistenceException, ClassNotFoundException {
        Company.setStores(storeCrudManager.getAll());
        LOG.info(String.format(ANSI_CYAN + "%d stores loaded" + ANSI_RESET, Company.getAllStores().size()));
    }
    
    public void loadAllCompanyInfo() {
        try {
            loadOffers();
            loadItems();
            loadServices();
            loadStores();
            loadClients();
            loadSuppliers();
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load Company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Client mergeClient(int id, int nit, String billName, int phone, int phone2,
            String address, String email, String CompanyName, int frequency, boolean route)
            throws PersistenceException, ClassNotFoundException {
        Client oldClient = clientCrudManager.find(id);
        Client newClient = new Client(id, oldClient.getName(), oldClient.getLastname(), nit, billName, address, phone, phone2, email, oldClient.getRegDate(), CompanyName, frequency, route);
        oldClient = clientCrudManager.merge(newClient);
        return oldClient;
    }
    
    public Item mergeItem(String id, String brand, String industry, Double cost, Double price)
            throws PersistenceException, ClassNotFoundException {
        Item oldItem = itemCrudManager.find(id);
        Item newItem = new Item(id, oldItem.getMeasure(), oldItem.getDescription(), brand, industry, oldItem.getType(), oldItem.getSubtype(), cost, price);
        oldItem = itemCrudManager.merge(newItem);
        return oldItem;
    }
    
    public Service mergeService(String id, Double cost, Double price) throws PersistenceException, ClassNotFoundException {
        Service oldService = serviceCrudManager.find(id);
        Service newService = new Service(id, oldService.getDescription(), oldService.getCapacity(), oldService.getUnit(), oldService.getType(), cost, price);
        oldService = serviceCrudManager.merge(newService);
        return oldService;
    }
    
    public Offer mergeOffer(int id, Double percentage, Date startDate, Date endDate)
            throws PersistenceException, ClassNotFoundException {
        Offer oldOffer = offerCrudManager.find(id);
        Offer newOffer = new Offer(id, oldOffer.getType(), percentage, startDate, endDate);
        oldOffer = offerCrudManager.merge(newOffer);
        return oldOffer;
    }
    
    public Supplier mergeSupplier(int id, int phone, int phone2, String Company,
            String address, String email, String city)
            throws PersistenceException, ClassNotFoundException {
        Supplier oldSupplier = supplierCrudManager.find(id);
        Supplier newSupplier = new Supplier(id, oldSupplier.getName(), oldSupplier.getLastname(), phone, phone2, Company, address, email, city, oldSupplier.getRegDate());
        oldSupplier = supplierCrudManager.merge(newSupplier);
        return oldSupplier;
    }
    
    public Store mergeStore(int id, String name, String address, int phone)
            throws PersistenceException, ClassNotFoundException {
        Store oldStore = storeCrudManager.find(id);
        Store newStore = new Store(id, name, address, phone);
        oldStore = storeCrudManager.merge(newStore);
        return oldStore;
    }
    
    public Employee autenticateEmployee(int id, String password) throws OperationFailedException {
        for (Employee employee : Company.getAllEmployees()) {
            if (employee.getId() == id) {
                if (employee.getPassword().equals(password)) {
                    return employee;
                }
                throw new OperationFailedException("Invalid password");
            }
        }
        throw new OperationFailedException("Invalid employee id");
    }
}