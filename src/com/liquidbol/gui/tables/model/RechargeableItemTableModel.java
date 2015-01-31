/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.CXC;
import com.liquidbol.model.Client;
import com.liquidbol.model.RechargeableItem;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of rechargeable items.
 * @author Allan Leon
 */
public class RechargeableItemTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Cliente", "Descripcion", "Fecha garantia", "Observaciones"};
    
    private final List<Client> clients;
    private final List<RechargeableItem> rechargeableItems;

    public RechargeableItemTableModel(List<Client> clients) {
        this.clients = new ArrayList<>();
        rechargeableItems = new ArrayList<>();
        initializeLists(clients);
    }
    
    private void initializeLists(List<Client> clients) {
        for (Client client : clients) {
            for (RechargeableItem rechargeableItem : client.getAllRechargeableItems()) {
                clients.add(client);
                rechargeableItems.add(rechargeableItem);
            }
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 4:
                return Date.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Client client = clients.get(row);
        RechargeableItem rechargeableItem = rechargeableItems.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return rechargeableItem.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return rechargeableItem.getDescription();
            case 4:
                return rechargeableItem.getWarrantyLimitDate();
            case 5:
                return rechargeableItem.getObs();
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
        return clients.size();
    }
}
