/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db;

import com.liquidbol.model.BillPayment;
import com.liquidbol.model.CXC;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.Debt;
import com.liquidbol.model.Inventory;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemBill;
import com.liquidbol.model.ItemEstimate;
import com.liquidbol.model.ItemPurchase;
import com.liquidbol.model.ItemRequest;
import com.liquidbol.model.ItemSale;
import com.liquidbol.model.Purchase;
import com.liquidbol.model.ServiceBill;
import com.liquidbol.model.ServiceReception;
import com.liquidbol.model.Store;
import com.liquidbol.model.Supplier;
import com.liquidbol.services.CXCServices;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.DebtServices;
import com.liquidbol.services.ItemBillServices;
import com.liquidbol.services.ItemEstimateServices;
import com.liquidbol.services.ItemServices;
import com.liquidbol.services.PurchaseServices;
import com.liquidbol.services.ServiceBillServices;
import com.liquidbol.services.StoreServices;
import com.liquidbol.services.SupplierServices;

/**
 * Loads all the info from the database.
 * @author Allan Leon
 */
public class DatabaseLoader {
    
    private final CXCServices cxcServices;
    private final ClientServices clientServices;
    private final CompanyServices companyServices;
    private final DebtServices debtServices;
    private final ItemBillServices itemBillServices;
    private final ItemEstimateServices itemEstimateServices;
    private final ItemServices itemServices;
    private final PurchaseServices purchaseServices;
    private final ServiceBillServices serviceBillServices;
    private final StoreServices storeServices;
    private final SupplierServices supplierServices;
    //private final Company Company;
    
    public DatabaseLoader(/*Company Company*/) {
        cxcServices = new CXCServices();
        clientServices = new ClientServices();
        companyServices = new CompanyServices(/*Company*/);
        debtServices = new DebtServices();
        itemBillServices = new ItemBillServices();
        itemEstimateServices = new ItemEstimateServices();
        itemServices = new ItemServices();
        purchaseServices = new PurchaseServices();
        serviceBillServices = new ServiceBillServices();
        storeServices = new StoreServices();
        supplierServices = new SupplierServices();
        //this.Company = Company;
    }
    
    public void loadCompanyInfo() {
        companyServices.loadAllCompanyInfo();
        for (Client client : Company.getAllClients()) {
            loadClientInfo(client);
        }
        for (Item item : Company.getAllItems()) {
            loadItemInfo(item);
        }
        for (Store store : Company.getAllStores()) {
            loadStoreInfo(store);
        }
        for (Supplier supplier : Company.getAllSuppliers()) {
            loadSupplierInfo(supplier);
        }
    }
    
    public void loadClientInfo(Client client) {
        clientServices.loadAllClientInfo(client);
        for (ItemBill itemBill : client.getAllItemBills()) {
            loadItemBillInfo(itemBill);
            itemBill.refresh();
        }
        for (ItemEstimate itemEstimate : client.getAllItemEstimates()) {
            loadItemEstimateInfo(itemEstimate);
            itemEstimate.refresh();
        }
        for (CXC cxc : client.getAllReceivableAccounts()) {
            loadReceivableAccountInfo(cxc);
        }
        for (ServiceBill serviceBill : client.getAllServiceBills()) {
            loadServiceBillInfo(serviceBill);
            serviceBill.refresh();
        }
    }
    
    public void loadItemBillInfo(ItemBill itemBill) {
        itemBillServices.loadAllItemBillInfo(itemBill);
        for (BillPayment payment : itemBill.getAllPayments()) {
            payment.refresh();
        }
        for (ItemSale itemSale : itemBill.getAllItemSales()) {
            itemSale.refresh();
        }
    }
    
    public void loadItemEstimateInfo(ItemEstimate itemEstimate) {
        itemEstimateServices.loadAllItemEstimateInfo(itemEstimate);
        for (ItemRequest itemRequest : itemEstimate.getAllRequests()) {
            itemRequest.refresh();
        }
    }
    
    public void loadReceivableAccountInfo(CXC cxc) {
        cxcServices.loadAllCXCInfo(cxc);
    }
    
    public void loadServiceBillInfo(ServiceBill serviceBill) {
        serviceBillServices.loadAllServiceBillInfo(serviceBill);
        for (BillPayment payment : serviceBill.getAllPayments()) {
            payment.refresh();
        }
        for (ServiceReception serviceReception : serviceBill.getAllServiceReceptions()) {
            serviceReception.refresh();
        }
    }
    
    public void loadItemInfo(Item item) {
        itemServices.loadAllItemInfo(item);
    }
    
    public void loadStoreInfo(Store store) {
        storeServices.loadAllStoreInfo(store);
        for (Inventory inventory : store.getAllInventorys()) {
            inventory.refresh();
        }
    }
    
    public void loadSupplierInfo(Supplier supplier) {
        supplierServices.loadAllSupplierInfo(supplier);
        for (Debt debt : supplier.getAllDebts()) {
            loadDebtInfo(debt);
        }
        for (Purchase purchase : supplier.getAllPurchases()) {
            loadPurchaseInfo(purchase);
        }
    }
    
    public void loadDebtInfo(Debt debt) {
        debtServices.loadAllDebtInfo(debt);
    }
    
    public void loadPurchaseInfo(Purchase purchase) {
        purchaseServices.loadAllPurchaseInfo(purchase);
        for (ItemPurchase itemPurchase : purchase.getAllItemPurchases()) {
            itemPurchase.refresh();
        }
    }
}
