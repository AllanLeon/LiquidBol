/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.commons.model;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a supplier.
 * @author Allan Leon
 */
public class Supplier extends Person implements Serializable {
    
    private String company;
    private String city;
    private Collection<Debt> debts;
    private Collection<Purchase> purchases;

    /**
     * Constructor of the class that includes all the variables as parameters.
     * @param id
     * @param name
     * @param lastname
     * @param phone
     * @param phone2
     * @param company
     * @param address
     * @param email
     * @param city
     * @param regDate 
     */
    public Supplier(int id, String name, String lastname, int phone, int phone2, String company, String address, String email, String city, Date regDate) {
        super(id, name, lastname, address, phone, phone2, email, regDate);
        this.company = company;
        this.city = city;
        this.debts = new HashSet<>();
        this.purchases = new HashSet<>();
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * @return the supplier's number of debts
     */
    public int getNumberOfDebts() {
        return debts.size();
    }
    
    /**
     * @return all the supplier's debts
     */
    public Collection<Debt> getAllDebts() {
        return debts;
    }
    
    /**
     * @return all the supplier's valid debts
     */
    public Collection<Debt> getValidDebts() {
        Set<Debt> result = new HashSet<>();
        Date today = new Date(new java.util.Date().getTime());
        for (Debt debt : debts) {
            if (debt.getLimitDate().compareTo(today) < 0 && debt.getAmount() > 0) {
                result.add(debt);
            }
        }
        return result;
    }
    
    /**
     * Adds a debt to the supplier's debts.
     * @param debt to be added
     */
    public void addDebt(Debt debt) {
       debts.add(debt);
    }
    
    public int getNumberOfPurchases() {
        return purchases.size();
    }
    
    public Collection<Purchase> getAllPurchases() {
        return purchases;
    }
    
    public Collection<Purchase> findPurchasesBetweenDates(Date startDate, Date endDate) {
        Set<Purchase> result = new HashSet<>();
        for (Purchase purchase : purchases) {
            Date purchaseDate = purchase.getDate();
            if (purchaseDate.compareTo(startDate) >= 0 && purchaseDate.compareTo(endDate) <= 0) {
                result.add(purchase);
            }
        }
        return result;
    }

    public void addPurchase(Purchase purchase) {
       purchases.add(purchase);
    }
}
