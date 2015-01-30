/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.model;

import com.liquidbol.model.Store;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of stores.
 * @author Allan Leon
 */
public class StoreTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Nombre", "Direccion", "Telefono"};
    
    private final List<Store> stores;

    public StoreTableModel(List<Store> stores) {
        this.stores = stores;
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1: case 4:
                return Integer.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Store store = stores.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return store.getId();
            case 2:
                return store.getName();
            case 3:
                return store.getAddress();
            case 4:
                return store.getPhone();
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
        return stores.size();
    }
    
}
