/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

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
        return element;
    }
    
    public void loadCXCPayments(CXC parent) throws PersistenceException, ClassNotFoundException {
        parent.setCollectedReceivableAccounts(cxccCrudManager.findByCXCId(parent.getId()));
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
