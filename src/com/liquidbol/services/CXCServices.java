package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
import com.liquidbol.db.persistence.ClientCXCCCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.CXC;
import com.liquidbol.model.CXCC;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations a receivable account can execute.
 * @author Allan Leon
 */
public class CXCServices {
    
    private static final Logger LOG = Logger.getLogger(CXCServices.class.getName());
    private final ClientCXCCCrud cxccCrudManager;
    
    public CXCServices() {
        this.cxccCrudManager = new ClientCXCCCrud();
    }
    
    public CXCC createCXCC(int id, Double amountPaid, Date payDate) {
        CXCC cxcc = new CXCC(id, amountPaid, payDate);
        return cxcc;
    }
    
    public CXCC addCXCCToCXC(CXCC element, CXC parent) throws PersistenceException, ClassNotFoundException {
        element = cxccCrudManager.save(element, parent);
        parent.addCollectedReceivableAccount(element);
        LOG.info(String.format(ANSI_PURPLE + "Collected receivable account: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public void loadCXCPayments(CXC parent) throws PersistenceException, ClassNotFoundException {
        parent.setCollectedReceivableAccounts(cxccCrudManager.findByCXCId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d collected receivable accounts loaded" + ANSI_RESET, parent.getAllCollectedReceivableAccounts().size()));
    }
    
    public void loadAllCXCInfo(CXC parent) {
        try {
            loadCXCPayments(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}