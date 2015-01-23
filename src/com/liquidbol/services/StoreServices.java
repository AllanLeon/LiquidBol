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
            Employee employee = new Employee(id, name, lastname, address, phone, phone2, email, null, password, type);
            return employee;
        }
    }
    
    public Expense addExpenseToStore(Expense element, Store parent) throws PersistenceException, ClassNotFoundException {
        expenseCrudManager.save(element, parent);
        element = expenseCrudManager.refresh(element);
        parent.addExpense(element);
        return element;
    }
    
    public Inventory addInventoryToStore(Inventory element, Store parent) throws PersistenceException, ClassNotFoundException {
        inventoryCrudManager.save(element, parent);
        element = inventoryCrudManager.refresh(element);
        parent.addInventory(element);
        return element;
    }
    
    public Employee addEmployeeToStore(Employee element, Store parent) throws PersistenceException, ClassNotFoundException {
        employeeCrudManager.save(element, parent);
        element = employeeCrudManager.refresh(element);
        parent.addEmployee(element);
        return element;
    }
    
    public void loadStoreExpenses(Store parent) throws PersistenceException, ClassNotFoundException {
        parent.setExpenses(expenseCrudManager.findByStoreId(parent.getId()));
    }
    
    public void loadStoreInventorys(Store parent) throws PersistenceException, ClassNotFoundException {
        parent.setInventorys(inventoryCrudManager.findByStoreId(parent.getId()));
    }
    
    public void loadStoreEmployees(Store parent) throws PersistenceException, ClassNotFoundException {
        parent.setEmployees(employeeCrudManager.findByStoreId(parent.getId()));
    }
}