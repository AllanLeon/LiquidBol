/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Discount;
import com.liquidbol.model.Item;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of item discounts.
 * @author Allan Leon
 */
public class ItemDiscountTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Articulo",
        "Cantidad minima", "%% descuento"};
    
    private final List<Item> items;
    private final List<Discount> discounts;

    public ItemDiscountTableModel(List<Item> items) {
        this.discounts = new ArrayList<>();
        this.items = new ArrayList<>();
        initializeLists(items);
    }
    
    private void initializeLists(List<Item> items) {
        for (Item item : items) {
            for (Discount discount : item.getAllDiscounts()) {
                items.add(item);
                discounts.add(discount);
            }
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1: case 3:
                return Integer.class;
            case 4:
                return Double.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Item item = items.get(row);
        Discount discount = discounts.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return discount.getId();
            case 2:
                return item.getDescription();
            case 3:
                return discount.getMinQuantity();
            case 4:
                return discount.getPercentage();
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
        return discounts.size();
    }
}
