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
 * Class that represents a store.
 * @author Allan Leon
 */
public class Store {
    
    private int id;
    private String name;
    private String address;
    private int phone;
    private Collection<Expense> expenses;

    /**
     * Constructor method.
     * @param id
     * @param name
     * @param address
     * @param phone 
     */
    public Store(int id, String name, String address, int phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.expenses = new HashSet<>();
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
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
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public int getNumberOfExpenses() {
        return expenses.size();
    }
    
    public Collection<Expense> getAllExpenses() {
        return expenses;
    }
    
    public Collection<Expense> findExpensesByDescription(String desc) {
        Set<Expense> result = new HashSet<>();
        for (Expense expense : expenses) {
            if (expense.getDescription().contains(desc)) {
                result.add(expense);
            }
        }
        return result;
    }
    
    public Collection<Expense> findExpensesByDate(Date date) {
        Set<Expense> result = new HashSet<>();
        for (Expense expense : expenses) {
            if (expense.getPayDate().compareTo(date) == 0) {
                result.add(expense);
            }
        }
        return result;
    }
    
    public Collection<Expense> findExpensesBetweenDates(Date startDate, Date endDate) {
        Set<Expense> result = new HashSet<>();
        for (Expense expense : expenses) {
            Date expenseDate = expense.getPayDate();
            if (expenseDate.compareTo(startDate) >= 0 && expenseDate.compareTo(endDate) <= 0) {
                result.add(expense);
            }
        }
        return result;
    }
    
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Store other = (Store) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
