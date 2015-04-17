/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Item;
import com.liquidbol.model.Service;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of services displayed in the shopping cart.
 * @author Allan Leon
 */
public class ShopCartServiceTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Descripcion", "Precio"};
    
    private final List<Service> services;

    public ShopCartServiceTableModel(List<Service> services) {
        this.services = services;
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 3:
                return Double.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Service service = services.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return service.getId();
            case 2:
                return service.getDescription();
            case 3:
                return service.getPrice();
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
        return services.size();
    }
    
    public Service getServiceAt(int row) {
        return services.get(row);
    }
}