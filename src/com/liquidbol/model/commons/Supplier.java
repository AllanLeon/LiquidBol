/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a supplier.
 * @author Allan Leon
 */
public class Supplier extends Person {
    
    private String company;
    private String city;
    private Date regDate;
    private Collection<Debt> debts;

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
        super(id, name, lastname, address, phone, phone2, email);
        this.company = company;
        this.city = city;
        this.regDate = regDate;
        this.debts = new HashSet<>();
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
     * @return the regDate
     */
    public Date getRegDate() {
        return regDate;
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
     * @param regDate the regDate to set
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
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
        Date today = new Date();
        for (Debt debt : debts) {
            if (debt.getLimitDate().compareTo(today) < 0 && debt.getAmmount() > 0) {
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
}
