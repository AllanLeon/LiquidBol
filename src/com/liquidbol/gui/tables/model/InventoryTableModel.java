package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Inventory;
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
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cantidad", "Articulo", "Tienda"};
    private final List<Store> stores;
    private final List<Inventory> inventorys;

    public InventoryTableModel(Collection<Store> stores) {
        this.stores = new ArrayList<>();
        inventorys = new ArrayList<>();
        initializeLists(stores);
    }
    
    private void initializeLists(Collection<Store> stores) {
        for (Store store : stores) {
            for (Inventory inventory : store.getAllInventorys()) {
                this.stores.add(store);
                inventorys.add(inventory);
            }
        }
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
        Store store = stores.get(row);
        Inventory inventory = inventorys.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return inventory.getQuantity();
            case 2:
                return inventory.getItem().getDescription();
            case 3:
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
        return inventorys.size();
    }
}