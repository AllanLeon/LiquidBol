package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of items.
 * @author Allan Leon
 */
public class ItemTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Unidad", "Descripcion",
        "Marca", "Industria", "Tipo", "Subtipo", "Costo", "Precio"};
    
    private List<Item> items;

    public ItemTableModel(Collection<Item> item) {
        this.items = new ArrayList<>(item);
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 8: case 9:
                return Double.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Item item = items.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return item.getId();
            case 2:
                return item.getMeasure();
            case 3:
                return item.getDescription();
            case 4:
                return item.getBrand();
            case 5:
                return item.getIndustry();
            case 6:
                return item.getType();
            case 7:
                return item.getSubtype();
            case 8:
                return item.getCost();
            case 9:
                return item.getPrice();
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
        return items.size();
    }
    
    public void setItems(Collection<Item> item) {
        this.items = new ArrayList<>(item);
        fireTableDataChanged();
    }
}