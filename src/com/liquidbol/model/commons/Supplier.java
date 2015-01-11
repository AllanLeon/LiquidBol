/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents a supplier.
 * @author Allan Leon
 */
public class Supplier {
    
    private int id;
    private String name;
    private String lastname;
    private int phone;
    private int phone2;
    private String company;
    private String address;
    private String email;
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
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.phone2 = phone2;
        this.company = company;
        this.address = address;
        this.email = email;
        this.city = city;
        this.regDate = regDate;
        this.debts = new HashSet<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @return the phone2
     */
    public int getPhone2() {
        return phone2;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.lastname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Supplier other = (Supplier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        return true;
    }
}
