/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.ClientCXCCrud;
import com.liquidbol.db.persistence.ItemBillCrud;
import com.liquidbol.db.persistence.ItemEstimateCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.RechargeableItemCrud;
import com.liquidbol.db.persistence.ServiceBillCrud;
import com.liquidbol.model.CXC;
import com.liquidbol.model.Client;
import com.liquidbol.model.Employee;
import com.liquidbol.model.ItemBill;
import com.liquidbol.model.ItemEstimate;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.ServiceBill;
import com.liquidbol.model.Store;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Contains all the operations a client can execute.
 * @author Allan Leon
 */
public class ClientServices {
    
    private static final Logger LOG = Logger.getLogger(ClientServices.class.getName());
    
    private final ClientCXCCrud cxcCrudManager;
    private final RechargeableItemCrud rechargeableItemCrudManager;
    private final ItemEstimateCrud itemEstimateCrudManager;
    private final ItemBillCrud itemBillCrudManager;
    private final ServiceBillCrud serviceBillCrudManager;
    
    public ClientServices() {
        this.cxcCrudManager = new ClientCXCCrud();
        this.rechargeableItemCrudManager = new RechargeableItemCrud();
        this.itemEstimateCrudManager = new ItemEstimateCrud();
        this.itemBillCrudManager = new ItemBillCrud();
        this.serviceBillCrudManager = new ServiceBillCrud();
    }
    
    public CXC createCXC(int id, Double debt, Double creditMaxAmount, Date creditLimitDate,
            String state) {
        CXC cxc = new CXC(id, debt, creditMaxAmount, creditLimitDate, state);
        return cxc;
    }
    
    public RechargeableItem createRechargeableItem(String id, String description,
            Double capacity, String unit, String type, Date warrantyLimitDate, String obs) {
        RechargeableItem rechargeableItem = new RechargeableItem(id, description,
                capacity, unit, type, warrantyLimitDate, obs);
        return rechargeableItem;
    }
    
    public ItemEstimate createItemEstimate(int id, Store store, int limitDays,
            Double totalAmount, String obs) {
        Calendar calendar = Calendar.getInstance();
        Date requestDate = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, limitDays);
        Date limitDate = new Date(calendar.getTimeInMillis());
        ItemEstimate itemEstimate = new ItemEstimate(id, store, requestDate,
                limitDate, totalAmount, obs);
        return itemEstimate;
    }
    
    public ItemBill createItemBill(int id, Employee employee, Store store, Date date,
            boolean route, String obs) {
        ItemBill itemBill = new ItemBill(id, employee, store, date, false, route, obs);
        return itemBill;
    }
    
    public ServiceBill createServiceBill(int id, Employee employee, Date date, String obs) {
        ServiceBill serviceBill = new ServiceBill(id, employee, date, false, obs);
        return serviceBill;
    }
    
    public CXC addCXCToClient(CXC element, Client parent) throws PersistenceException, ClassNotFoundException {
        element = cxcCrudManager.save(element, parent);
        parent.addReceivableAccount(element);
        return element;
    }
    
    public RechargeableItem addRechargeableItemToClient(RechargeableItem element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = rechargeableItemCrudManager.save(element, parent);
        parent.addRechargeableItem(element);
        return element;
    }
    
    public ItemEstimate addItemEstimateToClient(ItemEstimate element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = itemEstimateCrudManager.save(element, parent);
        parent.addItemEstimate(element);
        return element;
    }
    
    public ItemBill addItemBillToClient(ItemBill element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = itemBillCrudManager.save(element, parent);
        parent.addItemBill(element);
        return element;
    }
    
    public ServiceBill addServiceBillToClient(ServiceBill element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = serviceBillCrudManager.save(element, parent);
        parent.addServiceBill(element);
        return element;
    }
    
    public void loadClientCXCs(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setReceivableAccounts(cxcCrudManager.findByClientId(parent.getId()));
    }
    
    public void loadClientRechargeableItems(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setRechargeableItems(rechargeableItemCrudManager.findByClientId(parent.getId()));
    }
    
    public void loadClientItemEstimates(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemEstimates(itemEstimateCrudManager.findByClientId(parent.getId()));
    }
    
    public void loadClientItemBills(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemBills(itemBillCrudManager.findByClientId(parent.getId()));
    }
    
    public void loadClientServiceBills(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setServiceBills(serviceBillCrudManager.findByClientId(parent.getId()));
    }
}
