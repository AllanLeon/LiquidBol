package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Employee;
import com.liquidbol.model.Store;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of employees.
 * @author Allan Leon
 */
public class EmployeeTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod./C.I.", "Nombre", "Telefono", "Tipo", "Sucursal"};
    private final List<Store> stores;
    private final List<Employee> employees;

    public EmployeeTableModel(Collection<Store> stores) {
        this.stores = new ArrayList<>();
        this.employees = new ArrayList<>();
        updateLists(stores);
    }
    
    private void updateLists(Collection<Store> stores) {
        this.stores.clear();
        employees.clear();
        for (Store store : stores) {
            for (Employee employee : store.getAllEmployees()) {
                this.stores.add(store);
                employees.add(employee);
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1: case 3:
                return Integer.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Employee employee = employees.get(row);
        Store store = stores.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return employee.getId();
            case 2:
                return String.format("%s %s", employee.getName(), employee.getLastname());
            case 3:
                return employee.getPhone();
            case 4:
                return employee.getType();
            case 5:
                return store.getName();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }
    
    public void setStores(Collection<Store> stores) {
        updateLists(stores);
    }
    
    public void updateListsByEmployeeId(Collection<Store> stores, String employeeId) {
        this.stores.clear();
        employees.clear();
        for (Store store : stores) {
            for (Employee employee : store.searchEmployeesById(employeeId)) {
                this.stores.add(store);
                employees.add(employee);
            }
        }
        fireTableDataChanged();
    }
    
    public void updateListsByEmployeeName(Collection<Store> stores, String employeeName) {
        this.stores.clear();
        employees.clear();
        for (Store store : stores) {
            for (Employee employee : store.searchEmployeesByName(employeeName)) {
                this.stores.add(store);
                employees.add(employee);
            }
        }
        fireTableDataChanged();
    }
    
    public void updateListsByEmployeeType(Collection<Store> stores, String employeeType) {
        this.stores.clear();
        employees.clear();
        for (Store store : stores) {
            for (Employee employee : store.searchEmployeesByType(employeeType)) {
                this.stores.add(store);
                employees.add(employee);
            }
        }
        fireTableDataChanged();
    }
}