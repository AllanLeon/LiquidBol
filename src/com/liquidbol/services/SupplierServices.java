package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.PurchaseCrud;
import com.liquidbol.db.persistence.SupplierDebtCrud;
import com.liquidbol.model.Debt;
import com.liquidbol.model.Purchase;
import com.liquidbol.model.Supplier;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations a supplier can execute.
 * @author Allan Leon
 */
public class SupplierServices {
    
    private static final Logger LOG = Logger.getLogger(SupplierServices.class.getName());
    private final SupplierDebtCrud debtCrudManager;
    private final PurchaseCrud purchaseCrudManager;
    
    public SupplierServices() {
        this.debtCrudManager = new SupplierDebtCrud();
        this.purchaseCrudManager = new PurchaseCrud();
    }
    
    public Debt createDebt(int id, Double amount, Double maxAmount, Date limitDate) {
        Debt debt = new Debt(id, amount, maxAmount, limitDate);
        return debt;
    }
    
    public Purchase createPurchase(int id) {
        Purchase purchase = new Purchase(id, new Date(new java.util.Date().getTime()));
        return purchase;
    }
    
    public Debt addDebtToSupplier(Debt element, Supplier parent) throws PersistenceException, ClassNotFoundException {
        element = debtCrudManager.save(element, parent);
        parent.addDebt(element);
        LOG.info(String.format(ANSI_PURPLE + "Supplier debt: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public Purchase addPurchaseToSupplier(Purchase element, Supplier parent) throws PersistenceException, ClassNotFoundException {
        element = purchaseCrudManager.save(element, parent);
        parent.addPurchase(element);
        LOG.info(String.format(ANSI_PURPLE + "Purchase: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public void loadSupplierDebts(Supplier parent) throws PersistenceException, ClassNotFoundException {
        parent.setDebts(debtCrudManager.findBySupplierId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d supplier debts loaded" + ANSI_RESET, parent.getAllDebts().size()));
    }
    
    public void loadSupplierPurchases(Supplier parent) throws PersistenceException, ClassNotFoundException {
        parent.setPurchases(purchaseCrudManager.findBySupplierId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d purchases loaded" + ANSI_RESET, parent.getAllPurchases().size()));
    }
    
    public void loadAllSupplierInfo(Supplier parent) {
        try {
            loadSupplierDebts(parent);
            loadSupplierPurchases(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Debt mergeDebt(int id, Double amount) throws PersistenceException, ClassNotFoundException {
        Debt oldDebt = debtCrudManager.find(id);
        Debt newDebt = new Debt(id, amount, oldDebt.getMaxAmount(), oldDebt.getLimitDate());
        oldDebt = debtCrudManager.merge(newDebt);
        return oldDebt;
    }
}