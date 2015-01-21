/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a client.
 * @author Allan Leon
 */
public class Client extends Person implements Serializable {
    
    private int nit;
    private String companyName;
    private int frequency;
    private String billName;
    private boolean route;
    private Collection<CXC> receivableAccounts;
    private Collection<RechargeableItem> rechargeableItems;
    private Collection<ItemEstimate> itemEstimates;
    private Collection<ItemBill> itemBills;
    private Collection<ServiceBill> serviceBills;

    /**
     * Constructor method.
     * @param nit
     * @param companyName
     * @param frequency
     * @param billName
     * @param route
     * @param id
     * @param name
     * @param lastname
     * @param address
     * @param phone
     * @param phone2
     * @param email
     * @param regDate 
     */
    public Client(int id, String name, String lastname, String address, int phone, int phone2, String email, Date regDate, int nit, String companyName, int frequency, String billName, boolean route) {
        super(id, name, lastname, address, phone, phone2, email, regDate);
        this.nit = nit;
        this.companyName = companyName;
        this.frequency = frequency;
        this.billName = billName;
        this.route = route;
        this.receivableAccounts = new HashSet<>();
        this.rechargeableItems = new HashSet<>();
        this.itemEstimates = new HashSet<>();
        this.itemBills = new HashSet<>();
        this.serviceBills = new HashSet<>();
    }

    /**
     * @return the nit
     */
    public int getNit() {
        return nit;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }
    
    /**
     * @return the billName
     */
    public String getBillName() {
        return billName;
    }

    /**
     * @return the route
     */
    public boolean isRoute() {
        return route;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(int nit) {
        this.nit = nit;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    /**
     * @param billName the billName to set
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(boolean route) {
        this.route = route;
    }
    
    public int getNumberOfReceivableAccounts() {
        return receivableAccounts.size();
    }
    
    public Collection<CXC> getAllReceivableAccounts() {
        return receivableAccounts;
    }
    
    public Collection<CXC> getValidReceivableAccounts() {
        Set<CXC> result = new HashSet<>();
        Date today = new Date(new java.util.Date().getTime());
        for (CXC cxc : receivableAccounts) {
            if (cxc.getCreditLimitDate().compareTo(today) < 0 && cxc.getDebt() > 0) {
                result.add(cxc);
            }
        }
        return result;
    }
    
    public void addReceivableAccount(CXC cxc) {
        receivableAccounts.add(cxc);
    }
    
    public int getNumberOfRechargeableItems() {
        return rechargeableItems.size();
    }
    
    public Collection<RechargeableItem> getAllRechargeableItems() {
        return rechargeableItems;
    }
    
    public Collection<RechargeableItem> findRechargeableItemsByType(String type) {
        Set<RechargeableItem> result = new HashSet<>();
        for (RechargeableItem recItem : rechargeableItems) {
            if (recItem.getType().contains(type)) {
                result.add(recItem);
            }
        }
        return result;
    }
    
    public void addRechargeableItem(RechargeableItem rechargeableItem) {
        rechargeableItems.add(rechargeableItem);
    }
    
    public int getNumberOfItemEstimates() {
        return itemEstimates.size();
    }
    
    public Collection<ItemEstimate> getAllItemEstimates() {
        return itemEstimates;
    }
    
    public void addItemEstimate(ItemEstimate itemEstimate) {
        itemEstimates.add(itemEstimate);
    }
    
    public int getNumberOfItemBills() {
        return itemBills.size();
    }
    
    public Collection<ItemBill> getAllItemBills() {
        return itemBills;
    }
    
    public void addItemBill(ItemBill itemBill) {
        itemBills.add(itemBill);
    }
    
    public int getNumberOfServiceBills() {
        return serviceBills.size();
    }
    
    public Collection<ServiceBill> getAllServiceBills() {
        return serviceBills;
    }
    
    public void addServiceBill(ServiceBill serviceBill) {
        serviceBills.add(serviceBill);
    }
}