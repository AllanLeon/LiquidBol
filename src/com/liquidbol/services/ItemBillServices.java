/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.ItemPaymentCrud;
import com.liquidbol.db.persistence.ItemSaleCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.BillPayment;
import com.liquidbol.model.Employee;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemBill;
import com.liquidbol.model.ItemSale;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations an item bill can execute.
 * @author Allan Leon
 */
public class ItemBillServices {
    
    private static final Logger LOG = Logger.getLogger(ItemBillServices.class.getName());
    
    private final ItemPaymentCrud itemPaymentCrudManager;
    private final ItemSaleCrud itemSaleCrudManager;
    
    public ItemBillServices() {
        this.itemPaymentCrudManager = new ItemPaymentCrud();
        this.itemSaleCrudManager = new ItemSaleCrud();
    }
    
    public BillPayment createPayment(int id, Employee employee, Date payDate,
            Double amountPaid, String obs) {
        BillPayment payment = new BillPayment(id, employee, payDate, amountPaid, obs);
        return payment;
    }
    
    public ItemSale createItemSale(int id, Item item, int quantity, String obs) {
        ItemSale itemSale = new ItemSale(id, item, quantity, obs);
        return itemSale;
    }
    
    public BillPayment addPaymentToItemBill(BillPayment element, ItemBill parent) throws PersistenceException, ClassNotFoundException {
        element = itemPaymentCrudManager.save(element, parent);
        parent.addPayment(element);
        return element;
    }
    
    public ItemSale addItemSaleToItemBill(ItemSale element, ItemBill parent) throws PersistenceException, ClassNotFoundException {
        element = itemSaleCrudManager.save(element, parent);
        parent.addItemSale(element);
        return element;
    }
    
    public void loadItemBillPayments(ItemBill parent) throws PersistenceException, ClassNotFoundException {
        parent.setPayments(itemPaymentCrudManager.findByItemBillId(parent.getId()));
    }
    
    public void loadItemBillItemSales(ItemBill parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemSales(itemSaleCrudManager.findByItemBillId(parent.getId()));
    }
    
    public void loadAllItemBillInfo(ItemBill parent) {
        try {
            loadItemBillItemSales(parent);
            loadItemBillPayments(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public BillPayment mergeItemPayment(int id, String obs) throws PersistenceException, ClassNotFoundException {
        BillPayment oldPayment = itemPaymentCrudManager.find(id);
        BillPayment newPayment = new BillPayment(id, oldPayment.getEmployee(), oldPayment.getPayDate(), oldPayment.getAmountPaid(), obs);
        oldPayment = itemPaymentCrudManager.merge(newPayment);
        return oldPayment;
    }
    
    public ItemSale mergeItemSale(int id, String obs) throws PersistenceException, ClassNotFoundException {
        ItemSale oldSale = itemSaleCrudManager.find(id);
        ItemSale newSale = new ItemSale(id, oldSale.getItem(), oldSale.getQuantity(), oldSale.getAmount(), obs);
        oldSale = itemSaleCrudManager.merge(newSale);
        return oldSale;
    }
}
