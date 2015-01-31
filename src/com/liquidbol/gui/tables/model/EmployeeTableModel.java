/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Employee;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of employees.
 * @author Allan Leon
 */
public class EmployeeTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod./C.I.", "Nombre", "Telefono"};
    
    private final List<Employee> employees;

    public EmployeeTableModel(List<Employee> employees) {
        this.employees = employees;
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
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return employee.getId();
            case 2:
                return String.format("%s %s", employee.getName(), employee.getLastname());
            case 3:
                return employee.getPhone();
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
}
