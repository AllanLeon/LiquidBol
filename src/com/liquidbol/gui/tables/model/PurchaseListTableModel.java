package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Purchase;
import com.liquidbol.model.Supplier;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of purchases.
 * @author Allan Leon
 */
public class PurchaseListTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Proveedor","Monto Total", "Fecha"};
    private final List<Supplier> suppliers;
    private final List<Purchase> purchases;

    public PurchaseListTableModel(Collection<Supplier> suppliers) {
        this.suppliers = new ArrayList<>();
        purchases = new ArrayList<>();
        updateLists(suppliers);
    }
    
    private void updateLists(Collection<Supplier> suppliers) {
        this.suppliers.clear();
        purchases.clear();
        for (Supplier supplier : suppliers) {
            for (Purchase purchase : supplier.getAllPurchases()) {
                this.suppliers.add(supplier);
                purchases.add(purchase);
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1:
                return Integer.class;
            case 3:
                return Double.class;
            case 4:
                return Date.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Purchase purchase = purchases.get(row);
        Supplier supplier = suppliers.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return purchase.getId();
            case 2:
                return supplier.getName() + " " + supplier.getLastname();
            case 3:
                return purchase.getTotalAmount();
            case 4:
                return purchase.getDate();
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
        return purchases.size();
    }
    
    public void setSuppliers(Collection<Supplier> suppliers) {
        updateLists(suppliers);
    }
}