/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.db.persistence.EmployeeCrud;
import com.liquidbol.db.persistence.ExpenseCrud;
import com.liquidbol.db.persistence.InventoryCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Employee;
import com.liquidbol.model.Expense;
import com.liquidbol.model.Inventory;
import com.liquidbol.model.Item;
import com.liquidbol.model.Store;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations a store can execute.
 * @author Allan Leon
 */
public class StoreServices {
    
    private static final Logger LOG = Logger.getLogger(StoreServices.class.getName());
    
    private final ExpenseCrud expenseCrudManager;
    private final InventoryCrud inventoryCrudManager;
    private final EmployeeCrud employeeCrudManager;
    
    public StoreServices() {
        this.expenseCrudManager = new ExpenseCrud();
        this.inventoryCrudManager = new InventoryCrud();
        this.employeeCrudManager = new EmployeeCrud();
    }
    
    public Expense createExpense(int id, Date payDate, String description, Double amount, String obs) {
        Expense expense = new Expense(id, payDate, description, amount, obs);
        return expense;
    }
    
    public Inventory createInventory(int id, Item item, int quantity) {
        Inventory inventory = new Inventory(id, item, quantity);
        return inventory;
    }
    
    public Employee createEmployee(int id, String name, String lastname, String address,
            int phone, int phone2, String email, String password, String type) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Invalid employee name");
        } else if (lastname.equals("")) {
            throw new IllegalArgumentException("Invalid employee lastname");
        } else {
            Employee employee = new Employee(id, name, lastname, address, phone, phone2,
                    email, new Date(new java.util.Date().getTime()), password, type);
            return employee;
        }
    }
    
    public Expense addExpenseToStore(Expense element, Store parent) throws PersistenceException, ClassNotFoundException {
        element = expenseCrudManager.save(element, parent);
        parent.addExpense(element);
        LOG.info(String.format("Expense: %d saved", element.getId()));
        return element;
    }
    
    public Inventory addInventoryToStore(Inventory element, Store parent) throws PersistenceException, ClassNotFoundException {
        element = inventoryCrudManager.save(element, parent);
        parent.addInventory(element);
        LOG.info(String.format("Inventory: %d saved", element.getId()));
        return element;
    }
    
    public Employee addEmployeeToStore(Employee element, Store parent) throws PersistenceException, ClassNotFoundException {
        element = employeeCrudManager.save(element, parent);
        parent.addEmployee(element);
        LOG.info(String.format("Employee: %d saved", element.getId()));
        return element;
    }
    
    public void loadStoreExpenses(Store parent) throws PersistenceException, ClassNotFoundException {
        parent.setExpenses(expenseCrudManager.findByStoreId(parent.getId()));
        LOG.info(String.format("%d expenses loaded", parent.getAllExpenses().size()));
    }
    
    public void loadStoreInventorys(Store parent) throws PersistenceException, ClassNotFoundException {
        parent.setInventorys(inventoryCrudManager.findByStoreId(parent.getId()));
        LOG.info(String.format("%d inventorys loaded", parent.getAllInventorys().size()));
    }
    
    public void loadStoreEmployees(Store parent) throws PersistenceException, ClassNotFoundException {
        parent.setEmployees(employeeCrudManager.findByStoreId(parent.getId()));
        LOG.info(String.format("%d employees loaded", parent.getAllEmployees().size()));
    }
    
    public void loadAllStoreInfo(Store parent) {
        try {
            loadStoreEmployees(parent);
            loadStoreExpenses(parent);
            loadStoreInventorys(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Expense mergeExpense(int id, String description, String obs) throws PersistenceException, ClassNotFoundException {
        Expense oldExpense = expenseCrudManager.find(id);
        Expense newExpense = new Expense(id, oldExpense.getPayDate(), description, oldExpense.getAmount(), obs);
        oldExpense = expenseCrudManager.merge(newExpense);
        return oldExpense;
    }
    
    public Inventory mergeInventory(int id, int quantity) throws PersistenceException, ClassNotFoundException {
        Inventory oldInventory = inventoryCrudManager.find(id);
        Inventory newInventory = new Inventory(id, oldInventory.getItem(), quantity);
        oldInventory = inventoryCrudManager.merge(newInventory);
        return oldInventory;
    }
    
    public Employee mergeEmployee(int id, int phone, int phone2, String address,
            String email, String password, String type) throws PersistenceException, ClassNotFoundException {
        Employee oldEmployee = employeeCrudManager.find(id);
        Employee newEmployee = new Employee(id, oldEmployee.getName(), oldEmployee.getLastname(), address, phone, phone2, email, oldEmployee.getRegDate(), password, type);
        oldEmployee = employeeCrudManager.merge(newEmployee);
        return oldEmployee;
    }
}
