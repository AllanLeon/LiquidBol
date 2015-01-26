/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.db.persistence.ServicePaymentCrud;
import com.liquidbol.db.persistence.ServiceReceptionCrud;
import com.liquidbol.model.BillPayment;
import com.liquidbol.model.Employee;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.Service;
import com.liquidbol.model.ServiceBill;
import com.liquidbol.model.ServiceReception;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * Contains all the operations a service bill can execute.
 * @author Allan Leon
 */
public class ServiceBillServices {
    
    private static final Logger LOG = Logger.getLogger(ServiceBillServices.class.getName());
     
    private final ServicePaymentCrud servicePaymentCrudManager;
    private final ServiceReceptionCrud serviceReceptionCrudManager;
     
    public ServiceBillServices() {
        this.servicePaymentCrudManager = new ServicePaymentCrud();
        this.serviceReceptionCrudManager = new ServiceReceptionCrud();
    }
     
    public BillPayment createPayment(int id, Employee employee, Date payDate,
            Double amountPaid, String obs) {
        BillPayment payment = new BillPayment(id, employee, payDate, amountPaid, obs);
        return payment;
    }
     
    public ServiceReception createServiceReception(int id, Service service,
            RechargeableItem item, Timestamp deliverTime, String obs) {
        ServiceReception serviceReception = new ServiceReception(id, service, item,
                new Date(new java.util.Date().getTime()), deliverTime, service.getPrice(), obs);
        return serviceReception;
    }
    
    public BillPayment addPaymentToServiceBill(BillPayment element, ServiceBill parent)
            throws PersistenceException, ClassNotFoundException {
        servicePaymentCrudManager.save(element, parent);
        element = servicePaymentCrudManager.refresh(element);
        parent.addPayment(element);
        return element;
    }
    
    public ServiceReception addServiceReceptionToServiceBill(ServiceReception element, ServiceBill parent)
            throws PersistenceException, ClassNotFoundException {
        serviceReceptionCrudManager.save(element, parent);
        element = serviceReceptionCrudManager.refresh(element);
        parent.addServiceReception(element);
        return element;
    }
    
    public void loadServiceBillPayments(ServiceBill parent) throws PersistenceException, ClassNotFoundException {
        parent.setPayments(servicePaymentCrudManager.findByServiceBillId(parent.getId()));
    }
    
    public void loadServiceBillServiceReceptions(ServiceBill parent) throws PersistenceException, ClassNotFoundException {
        parent.setServiceReceptions(serviceReceptionCrudManager.findByServiceBillId(parent.getId()));
    }
}
