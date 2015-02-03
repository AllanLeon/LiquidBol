/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.db;

import com.liquidbol.model.Bill;
import com.liquidbol.model.BillPayment;
import com.liquidbol.model.CXC;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.Debt;
import com.liquidbol.model.Inventory;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemEstimate;
import com.liquidbol.model.ItemPurchase;
import com.liquidbol.model.ItemRequest;
import com.liquidbol.model.ItemSale;
import com.liquidbol.model.Purchase;
import com.liquidbol.model.ServiceReception;
import com.liquidbol.model.Store;
import com.liquidbol.model.Supplier;
import com.liquidbol.services.BillServices;
import com.liquidbol.services.CXCServices;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.DebtServices;
import com.liquidbol.services.ItemEstimateServices;
import com.liquidbol.services.ItemServices;
import com.liquidbol.services.PurchaseServices;
import com.liquidbol.services.StoreServices;
import com.liquidbol.services.SupplierServices;

/**
 * Loads all the info from the database.
 * @author Allan Leon
 */
public class DatabaseLoader {
    
    private final BillServices billServices;
    private final CXCServices cxcServices;
    private final ClientServices clientServices;
    private final CompanyServices companyServices;
    private final DebtServices debtServices;
    private final ItemEstimateServices itemEstimateServices;
    private final ItemServices itemServices;
    private final PurchaseServices purchaseServices;
    private final StoreServices storeServices;
    private final SupplierServices supplierServices;
    //private final Company Company;
    
    public DatabaseLoader(/*Company Company*/) {
        billServices = new BillServices();
        cxcServices = new CXCServices();
        clientServices = new ClientServices();
        companyServices = new CompanyServices(/*Company*/);
        debtServices = new DebtServices();
        itemEstimateServices = new ItemEstimateServices();
        itemServices = new ItemServices();
        purchaseServices = new PurchaseServices();
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
        for (Bill bill : client.getAllBills()) {
            loadBillInfo(bill);
            bill.refresh();
        }
        for (ItemEstimate itemEstimate : client.getAllItemEstimates()) {
            loadItemEstimateInfo(itemEstimate);
            itemEstimate.refresh();
        }
        for (CXC cxc : client.getAllReceivableAccounts()) {
            loadReceivableAccountInfo(cxc);
        }
    }
    
    public void loadBillInfo(Bill bill) {
        billServices.loadAllItemBillInfo(bill);
        for (BillPayment payment : bill.getAllPayments()) {
            payment.refresh();
        }
        for (ItemSale itemSale : bill.getAllItemSales()) {
            itemSale.refresh();
        }
        for (ServiceReception serviceReception : bill.getAllServiceReceptions()) {
            serviceReception.refresh();
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
