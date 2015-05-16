package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Inventory;
import com.liquidbol.model.Item;
import com.liquidbol.model.Store;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of inventory.
 * @author Allan Leon
 */
public class InventoryTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod. Articulo",
        "Articulo", "Tipo", "Subtipo", "Cantidad"};
    private Store store;
    private List<Inventory> inventorys;

    public InventoryTableModel(Store store) {
        this.store = store;
        inventorys = new ArrayList<>();
        updateInventorys();
    }
    
    private void updateInventorys() {
        inventorys.clear();
        for (Inventory inventory : store.getAllInventorys()) {
            inventorys.add(inventory);
        }
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 5:
                return Integer.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Inventory inventory = inventorys.get(row);
        Item item = inventory.getItem();
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return item.getId();
            case 2:
                return item.getDescription();
            case 3:
                return item.getType();
            case 4:
                return item.getSubtype();
            case 5:
                return inventory.getQuantity();
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
        return inventorys.size();
    }
    
    public void setStore(Store store) {
        this.store = store;
        updateInventorys();
    }
    
    public void setInventorys(Collection<Inventory> inventorys) {
        this.inventorys = new ArrayList<>(inventorys);
        fireTableDataChanged();
    }
    
    public int getItemStock(int row) {
        return inventorys.get(row).getQuantity();
    }
}