package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Inventory;
import com.liquidbol.model.Item;
import com.liquidbol.model.Store;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of items displayed in the shopping cart.
 * @author Allan Leon
 */
public class ShopCartItemTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"#", "Cod.", "STOCK", "Unidad",
        "Descripcion", "P/U"};
    private List<Inventory> inventorys;
    private Store store;

    public ShopCartItemTableModel(Store store) {
        this.store = store;
        this.inventorys = new ArrayList<>();
        updateInventorys();
    }
    
    private void updateInventorys() {
        inventorys.clear();
        for (Inventory inventory : store.getValidInventorys()) {
            inventorys.add(inventory);
        }
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 2:
                return Integer.class;
            case 5:
                return Double.class;
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
                return inventory.getQuantity();
            case 3:
                return item.getMeasure();
            case 4:
                return item.getDescription();
            case 5:
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
        return inventorys.size();
    }
    
    public Item getItemAt(int row) {
        return inventorys.get(row).getItem();
    }
    
    public void setStore(Store store) {
        this.store = store;
        updateInventorys();
    }
    
    public void setInventorys(Collection<Inventory> inventorys) {
        this.inventorys = new ArrayList<>(inventorys);
        fireTableDataChanged();
    }
}