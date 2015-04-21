/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Item;
import com.liquidbol.model.ItemPurchase;
import com.liquidbol.model.Purchase;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Allan Leon
 */
public class PurchaseTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"#", "Cod.", "Cantidad",
        "Unidad", "Descripcion", "Costo", "Monto"};
    
    private List<ItemPurchase> itemPurchases;
    private Purchase purchase;

    public PurchaseTableModel(Purchase purchase) {
        this.purchase = purchase;
        updateList();
    }
    
    public void updateList() {
        this.itemPurchases = new ArrayList<>(purchase.getAllItemPurchases());
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
        ItemPurchase itemPurchase = itemPurchases.get(row);
        Item item = itemPurchase.getItem();
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return item.getId();
            case 2:
                return itemPurchase.getQuantity();
            case 3:
                return item.getMeasure();
            case 4:
                return item.getDescription();
            case 5:
                return itemPurchase.getUnitCost();
            case 6:
                return itemPurchase.getAmount();
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
        super.setValueAt(value, row, column);
        ItemPurchase itemPurchase = itemPurchases.get(row);
        switch (column) {
            case 2:
                itemPurchase.setQuantity((int) value);
            default:;
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
        return itemPurchases.size();
    }
}
