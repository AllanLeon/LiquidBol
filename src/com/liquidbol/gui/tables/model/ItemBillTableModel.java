/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Client;
import com.liquidbol.model.ItemBill;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of item bills.
 * @author Allan Leon
 */
public class ItemBillTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Cliente",
        "Monto Total", "Fecha", "Ruta", "Facturado", "Observaciones"};
    
    private final List<Client> clients;
    private final List<ItemBill> itemBills;

    public ItemBillTableModel(List<Client> clients) {
        this.clients = new ArrayList<>();
        itemBills = new ArrayList<>();
        initializeLists(clients);
    }
    
    private void initializeLists(List<Client> clients) {
        for (Client client : clients) {
            for (ItemBill itemBill : client.getAllItemBills()) {
                this.clients.add(client);
                itemBills.add(itemBill);
            }
        }
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
            case 5: case 6:
                return Boolean.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Client client = clients.get(row);
        ItemBill itemBill = itemBills.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return itemBill.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return itemBill.getTotalAmount();
            case 4:
                return itemBill.getDate();
            case 5:
                return itemBill.isRoute();
            case 6:
                return itemBill.isBilled();
            case 7:
                return itemBill.getObs();
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
        return itemBills.size();
    }
}
