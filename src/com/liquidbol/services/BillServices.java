package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
import com.liquidbol.db.persistence.BillPaymentCrud;
import com.liquidbol.db.persistence.ItemSaleCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.ServiceReceptionCrud;
import com.liquidbol.model.Bill;
import com.liquidbol.model.BillPayment;
import com.liquidbol.model.Employee;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemSale;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.Service;
import com.liquidbol.model.ServiceReception;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations a bill can execute.
 * @author Allan Leon
 */
public class BillServices {
    
    private static final Logger LOG = Logger.getLogger(BillServices.class.getName());
    
    private final BillPaymentCrud billPaymentCrudManager;
    private final ItemSaleCrud itemSaleCrudManager;
    private final ServiceReceptionCrud serviceReceptionCrudManager;
    
    public BillServices() {
        this.billPaymentCrudManager = new BillPaymentCrud();
        this.itemSaleCrudManager = new ItemSaleCrud();
        this.serviceReceptionCrudManager = new ServiceReceptionCrud();
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
    
    public ServiceReception createServiceReception(int id, Service service,
            RechargeableItem item, Timestamp deliverTime, Double quantity, String obs) {
        ServiceReception serviceReception = new ServiceReception(id, service, item,
                new Date(new java.util.Date().getTime()), deliverTime, quantity, obs);
        return serviceReception;
    }
    
    public BillPayment addPaymentToBill(BillPayment element, Bill parent) throws PersistenceException, ClassNotFoundException {
        element = billPaymentCrudManager.save(element, parent);
        parent.addPayment(element);
        LOG.info(String.format(ANSI_PURPLE + "Bill payment: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public ItemSale addItemSaleToBill(ItemSale element, Bill parent) throws PersistenceException, ClassNotFoundException {
        element = itemSaleCrudManager.save(element, parent);
        parent.addItemSale(element);
        LOG.info(String.format(ANSI_PURPLE + "Item sale: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public ServiceReception addServiceReceptionToBill(ServiceReception element, Bill parent)
            throws PersistenceException, ClassNotFoundException {
        element = serviceReceptionCrudManager.save(element, parent);
        parent.addServiceReception(element);
        LOG.info(String.format(ANSI_PURPLE + "Service reception: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public void loadBillPayments(Bill parent) throws PersistenceException, ClassNotFoundException {
        parent.setPayments(billPaymentCrudManager.findByBillId(parent.getId()));
        
        LOG.info(String.format(ANSI_CYAN + "%d item payments loaded" + ANSI_RESET, parent.getAllPayments().size()));
    }
    
    public void loadBillItemSales(Bill parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemSales(itemSaleCrudManager.findByItemBillId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d item sales loaded" + ANSI_RESET, parent.getAllItemSales().size()));
    }
    
    public void loadBillServiceReceptions(Bill parent) throws PersistenceException, ClassNotFoundException {
        parent.setServiceReceptions(serviceReceptionCrudManager.findByServiceBillId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d service receptions loaded" + ANSI_RESET, parent.getAllServiceReceptions().size()));
    }
    
    public void loadAllItemBillInfo(Bill parent) {
        try {
            loadBillItemSales(parent);
            loadBillPayments(parent);
            loadBillServiceReceptions(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public BillPayment mergeBillPayment(int id, String obs) throws PersistenceException, ClassNotFoundException {
        BillPayment oldPayment = billPaymentCrudManager.find(id);
        BillPayment newPayment = new BillPayment(id, oldPayment.getEmployee(), oldPayment.getPayDate(), oldPayment.getAmountPaid(), obs);
        oldPayment = billPaymentCrudManager.merge(newPayment);
        return oldPayment;
    }
    
    public ItemSale mergeItemSale(int id, String obs) throws PersistenceException, ClassNotFoundException {
        ItemSale oldSale = itemSaleCrudManager.find(id);
        ItemSale newSale = new ItemSale(id, oldSale.getItem(), oldSale.getQuantity(), oldSale.getAmount(), obs);
        oldSale = itemSaleCrudManager.merge(newSale);
        return oldSale;
    }
    
    public ServiceReception mergeServiceReception(int id, Timestamp deliverTime, String obs) throws PersistenceException, ClassNotFoundException {
        ServiceReception oldReception = serviceReceptionCrudManager.find(id);
        ServiceReception newReception = new ServiceReception(id, oldReception.getService(), oldReception.getItem(), oldReception.getReceptionDate(), deliverTime, oldReception.getQuantity(), oldReception.getAmount(), obs);
        oldReception = serviceReceptionCrudManager.merge(newReception);
        return oldReception;
    }
}