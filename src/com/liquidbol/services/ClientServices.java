package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
import com.liquidbol.db.persistence.ClientCXCCrud;
import com.liquidbol.db.persistence.BillCrud;
import com.liquidbol.db.persistence.ItemEstimateCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.RechargeableItemCrud;
import com.liquidbol.model.Bill;
import com.liquidbol.model.CXC;
import com.liquidbol.model.Client;
import com.liquidbol.model.Employee;
import com.liquidbol.model.ItemEstimate;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.Store;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
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
    private final BillCrud billCrudManager;
    
    public ClientServices() {
        this.cxcCrudManager = new ClientCXCCrud();
        this.rechargeableItemCrudManager = new RechargeableItemCrud();
        this.itemEstimateCrudManager = new ItemEstimateCrud();
        this.billCrudManager = new BillCrud();
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
    
    public Bill createBill(int id, Employee employee, Store store, Date date,
            boolean route, String obs) {
        Bill bill = new Bill(id, store, employee, date, false, route, obs);
        return bill;
    }
    
    public CXC addCXCToClient(CXC element, Client parent) throws PersistenceException, ClassNotFoundException {
        element = cxcCrudManager.save(element, parent);
        parent.addReceivableAccount(element);
        LOG.info(String.format(ANSI_PURPLE + "Receivable account: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public RechargeableItem addRechargeableItemToClient(RechargeableItem element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = rechargeableItemCrudManager.save(element, parent);
        parent.addRechargeableItem(element);
        LOG.info(String.format(ANSI_PURPLE + "Rechargeable item: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public ItemEstimate addItemEstimateToClient(ItemEstimate element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = itemEstimateCrudManager.save(element, parent);
        parent.addItemEstimate(element);
        LOG.info(String.format(ANSI_PURPLE + "Item estimate: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public Bill addBillToClient(Bill element, Client parent)
            throws PersistenceException, ClassNotFoundException {
        element = billCrudManager.save(element, parent);
        parent.addBill(element);
        parent.increaseFrequency();
        LOG.info(String.format(ANSI_PURPLE + "Item bill: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public void loadClientCXCs(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setReceivableAccounts(cxcCrudManager.findByClientId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d receivable accounts loaded" + ANSI_RESET, parent.getAllReceivableAccounts().size()));
    }
    
    public void loadClientRechargeableItems(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setRechargeableItems(rechargeableItemCrudManager.findByClientId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d rechargeable items loaded" + ANSI_RESET, parent.getAllRechargeableItems().size()));
    }
    
    public void loadClientItemEstimates(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setItemEstimates(itemEstimateCrudManager.findByClientId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d item estimates loaded" + ANSI_RESET, parent.getAllItemEstimates().size()));
    }
    
    public void loadClientItemBills(Client parent) throws PersistenceException, ClassNotFoundException {
        parent.setBills(billCrudManager.findByClientId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d item bills loaded" + ANSI_RESET, parent.getAllBills().size()));
    }
    
    public void loadAllClientInfo(Client parent) {
        try {
            loadClientCXCs(parent);
            loadClientItemBills(parent);
            loadClientItemEstimates(parent);
            loadClientRechargeableItems(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public CXC mergeCXC(int id, Double debt, Double creditMaxAmount, Date creditLimitDate, String state)
            throws PersistenceException, ClassNotFoundException {
        CXC oldCXC = cxcCrudManager.find(id);
        CXC newCXC = new CXC(id, debt, creditMaxAmount, creditLimitDate, state);
        oldCXC = cxcCrudManager.merge(newCXC);
        return oldCXC;
    }
    
    public RechargeableItem mergeRechargeableItem(String id, String obs)
            throws PersistenceException, ClassNotFoundException {
        RechargeableItem oldItem = rechargeableItemCrudManager.find(id);
        RechargeableItem newItem = new RechargeableItem(id, oldItem.getDescription(),
                oldItem.getCapacity(), oldItem.getUnit(), oldItem.getType(), oldItem.getWarrantyLimitDate(), obs);
        oldItem = rechargeableItemCrudManager.merge(newItem);
        return oldItem;
    }
    
    public ItemEstimate mergeItemEstimate(int id, Double amount, String obs)
            throws PersistenceException, ClassNotFoundException {
        ItemEstimate oldEstimate = itemEstimateCrudManager.find(id);
        ItemEstimate newEstimate = new ItemEstimate(id, oldEstimate.getStore(), oldEstimate.getRequestDate(), oldEstimate.getLimitDate(), amount, obs);
        oldEstimate = itemEstimateCrudManager.merge(newEstimate);
        return oldEstimate;
    }
    
    public Bill mergeBill(int id, Double amount, boolean billed, String obs)
            throws PersistenceException, ClassNotFoundException {
        Bill oldBill = billCrudManager.find(id);
        Bill newBill = new Bill(id, oldBill.getStore(), oldBill.getEmployee(), oldBill.getDate(), amount, billed, oldBill.isRoute(), obs);
        oldBill = billCrudManager.merge(newBill);
        return oldBill;
    }
}