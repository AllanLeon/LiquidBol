package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of services displayed in the shopping cart.
 * @author Allan Leon
 */
public class ShopCartServiceTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"#", "Cod.", "Descripcion", "P/U"};
    private List<Service> services;

    public ShopCartServiceTableModel(Collection<Service> services) {
        this.services = new ArrayList<>(services);
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
    
    public void setServices(Collection<Service> services) {
        this.services = new ArrayList<>(services);
        fireTableDataChanged();
    }
}