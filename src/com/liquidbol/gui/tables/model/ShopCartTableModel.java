package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Bill;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemSale;
import com.liquidbol.model.Service;
import com.liquidbol.model.ServiceReception;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of products added to the shopping cart.
 * @author Allan Leon
 */
public class ShopCartTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"#", "Cod.", "Cantidad",
        "Unidad", "Descripcion", "P/U", "Precio"};
    private List<ItemSale> itemSales;
    private List<ServiceReception> serviceReceptions;
    private final Bill bill;

    public ShopCartTableModel(Bill bill) {
        this.bill = bill;
        updateLists();
    }
    
    public void updateLists() {
        this.itemSales = new ArrayList<>(bill.getAllItemSales());
        this.serviceReceptions = new ArrayList<>(bill.getAllServiceReceptions());
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 2:
                return Integer.class;
            case 5: case 6:
                return Double.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        if (row < itemSales.size()) {
            ItemSale itemSale = itemSales.get(row);
            Item item = itemSale.getItem();
            switch (column) {
                case 0:
                    return row + 1;
                case 1:
                    return item.getId();
                case 2:
                    return itemSale.getQuantity();
                case 3:
                    return item.getMeasure();
                case 4:
                    return item.getDescription();
                case 5:
                    return item.getPrice();
                case 6:
                    return itemSale.getAmount();
                default:
                    return null;
            }
        } else {
            row -= itemSales.size();
            ServiceReception serviceReception = serviceReceptions.get(row);
            Service service = serviceReception.getService();
            switch (column) {
                case 0:
                    return row + 1;
                case 1:
                    return service.getId();
                case 2:
                    return serviceReception.getQuantity();
                case 3:
                    return "";
                case 4:
                    return service.getDescription();
                case 5:
                    return service.getPrice();
                case 6:
                    return serviceReception.getAmount();
                default:
                    return null;
            }
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
        super.setValueAt(value, row, column);
        if (row < itemSales.size()) {
            ItemSale itemSale = itemSales.get(row);
            switch (column) {
                case 2:
                    itemSale.setQuantity((int) value);
                default:;
            }
        } else {
            row -= itemSales.size();
            ServiceReception serviceReception = serviceReceptions.get(row);
            switch (column) {
                case 2:
                    serviceReception.setQuantity(new Double((int) value));
                default:;
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        switch (column) {
            case 2:
                return true;
            default:
                return false;
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
        return itemSales.size() + serviceReceptions.size();
    }
}