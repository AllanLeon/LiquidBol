package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Purchase;
import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of purchases.
 * @author Allan Leon
 */
public class PurchaseListTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Monto Total", "Fecha"};
    private final List<Purchase> purchases;

    public PurchaseListTableModel(List<Purchase> purchases) {
        this.purchases = purchases;
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1:
                return Integer.class;
            case 2:
                return Double.class;
            case 3:
                return Date.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Purchase purchase = purchases.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return purchase.getId();
            case 2:
                return purchase.getTotalAmount();
            case 3:
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
}