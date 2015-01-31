/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Supplier;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of suppliers.
 * @author Allan Leon
 */
public class SupplierTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Nombre", "Compañia"};
    
    private final List<Supplier> suppliers;

    public SupplierTableModel(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1:
                return Integer.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Supplier supplier = suppliers.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return supplier.getId();
            case 2:
                return String.format("%s %s", supplier.getName(), supplier.getLastname());
            case 3:
                return supplier.getCompany();
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
        return suppliers.size();
    }
    
}
