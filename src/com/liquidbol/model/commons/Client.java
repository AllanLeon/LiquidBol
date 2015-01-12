/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a client.
 * @author Allan Leon
 */
public class Client extends Person {
    
    private int nit;
    private String companyName;
    private int frequency;
    private Collection<CXC> receivableAccounts;
    private Collection<RechargeableItem> rechargeableItems;
    private Collection<ItemEstimate> itemEstimates;
    private Collection<ItemSale> itemSales;

    /**
     * Constructor method.
     * @param nit
     * @param companyName
     * @param frequency
     * @param id
     * @param name
     * @param lastname
     * @param address
     * @param phone
     * @param phone2
     * @param email
     * @param regDate 
     */
    public Client(int nit, String companyName, int frequency, int id, String name, String lastname, String address, int phone, int phone2, String email, Date regDate) {
        super(id, name, lastname, address, phone, phone2, email, regDate);
        this.nit = nit;
        this.companyName = companyName;
        this.frequency = frequency;
        this.receivableAccounts = new HashSet<>();
        this.rechargeableItems = new HashSet<>();
        this.itemEstimates = new HashSet<>();
        this.itemSales = new HashSet<>();
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
    
    public int getNumberOfItemSales() {
        return itemSales.size();
    }
    
    public Collection<ItemSale> getAllItemSales() {
        return itemSales;
    }
    
    public void addItemSale(ItemSale itemSale) {
        itemSales.add(itemSale);
    }
}
