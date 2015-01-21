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
 * Class that represents a store.
 * @author Allan Leon
 */
public class Store implements Serializable {
    
    private int id;
    private String name;
    private String address;
    private int phone;
    private Collection<Expense> expenses;
    private Collection<Inventory> inventorys;
    private Collection<Employee> employees;

    /**
     * Simple constructor method.
     * @param id 
     */
    public Store(int id) {
        this.id = id;
    }

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
        this.inventorys = new HashSet<>();
        this.employees = new HashSet<>();
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
    
    public int getNumberOfInventorys() {
        return inventorys.size();
    }
    
    public Collection<Inventory> getAllInventorys() {
        return inventorys;
    }
    
    public Collection<Inventory> findInventoryByItemId(String itemId) {
        Set<Inventory> result = new HashSet<>();
        for (Inventory inventory : inventorys) {
            if (inventory.getItem().getId().equals(itemId)) {
                result.add(inventory);
            }
        }
        return result;
    }

    public void addInventory(Inventory inventory) {
       inventorys.add(inventory);
    }
    
    public int getNumberOfEmployees() {
        return employees.size();
    }
    
    public Collection<Employee> getAllEmployees() {
        return employees;
    }
    
    public Collection<Employee> findEmployeesByName(String name) {
        Set<Employee> result = new HashSet<>();
        for (Employee employee : employees) {
            if (employee.getName().contains(name)) {
                result.add(employee);
            }
        }
        return result;
    }

    public void addEmployee(Employee employee) {
       employees.add(employee);
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
