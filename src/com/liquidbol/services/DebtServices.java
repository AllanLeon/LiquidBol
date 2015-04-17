package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
import com.liquidbol.db.persistence.DebtPaymentCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Debt;
import com.liquidbol.model.DebtPayment;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operation a debt can execute.
 * @author Allan Leon
 */
public class DebtServices {
    
    private static final Logger LOG = Logger.getLogger(DebtServices.class.getName());
    
    private final DebtPaymentCrud debtPaymentCrudManager;
    
    public DebtServices() {
        this.debtPaymentCrudManager = new DebtPaymentCrud();
    }
    
    public DebtPayment createDebtPayment(int id, Date payDate, Double amount) {
        DebtPayment debtPayment = new DebtPayment(id, payDate, amount);
        return debtPayment;
    }
    
    public DebtPayment addPaymentToDebt(DebtPayment element, Debt parent)
            throws PersistenceException, ClassNotFoundException {
        element = debtPaymentCrudManager.save(element, parent);
        parent.addPayment(element);
        LOG.info(String.format(ANSI_PURPLE + "Debt payment: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public void loadDebtPayments(Debt parent) throws PersistenceException, ClassNotFoundException {
        parent.setPayments(debtPaymentCrudManager.findByDebtId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d debt payments loaded" + ANSI_RESET, parent.getAllPayments().size()));
    }
    
    public void loadAllDebtInfo(Debt parent) {
        try {
            loadDebtPayments(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}