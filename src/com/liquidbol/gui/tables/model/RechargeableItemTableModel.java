package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Client;
import com.liquidbol.model.RechargeableItem;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of rechargeable items.
 * @author Allan Leon
 */
public class RechargeableItemTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Cliente", "Descripcion", "Tipo", "Fecha garantia", "Observaciones"};
    private final List<Client> clients;
    private final List<RechargeableItem> rechargeableItems;

    public RechargeableItemTableModel(Collection<Client> clients) {
        this.clients = new ArrayList<>();
        rechargeableItems = new ArrayList<>();
        updateLists(clients);
    }
    
    private void updateLists(Collection<Client> clients) {
        this.clients.clear();
        rechargeableItems.clear();
        for (Client client : clients) {
            for (RechargeableItem rechargeableItem : client.getAllRechargeableItems()) {
                this.clients.add(client);
                rechargeableItems.add(rechargeableItem);
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 5:
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
                return rechargeableItem.getType();
            case 5:
                return rechargeableItem.getWarrantyLimitDate();
            case 6:
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
        return rechargeableItems.size();
    }
    
    public void updateListsByRechargeableItemId(Collection<Client> clients, String rechargeableItemId) {
        this.clients.clear();
        rechargeableItems.clear();
        for (Client client : clients) {
            for (RechargeableItem rechargeableItem : client.searchRechargeableItemsById(rechargeableItemId)) {
                this.clients.add(client);
                rechargeableItems.add(rechargeableItem);
            }
        }
        fireTableDataChanged();
    }
    
    public void updateListsByRechargeableItemDescription(Collection<Client> clients, String rechargeableItemDesc) {
        this.clients.clear();
        rechargeableItems.clear();
        for (Client client : clients) {
            for (RechargeableItem rechargeableItem : client.searchRechargeableItemsByDescription(rechargeableItemDesc)) {
                this.clients.add(client);
                rechargeableItems.add(rechargeableItem);
            }
        }
        fireTableDataChanged();
    }
    
    public void updateListsByRechargeableItemType(Collection<Client> clients, String rechargeableItemType) {
        this.clients.clear();
        rechargeableItems.clear();
        for (Client client : clients) {
            for (RechargeableItem rechargeableItem : client.searchRechargeableItemsByType(rechargeableItemType)) {
                this.clients.add(client);
                rechargeableItems.add(rechargeableItem);
            }
        }
        fireTableDataChanged();
    }
    
    public void setClients(Collection<Client> clients) {
        updateLists(clients);
    }
}