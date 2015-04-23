package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Client;
import com.liquidbol.model.ItemEstimate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of estimates.
 * @author Allan Leon
 */
public class ItemEstimateTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Cliente", "Tienda",
        "Fecha Cotizacion", "Fecha Limite", "Monto", "Observaciones"};
    private final List<Client> clients;
    private final List<ItemEstimate> itemEstimates;

    public ItemEstimateTableModel(List<Client> clients) {
        this.clients = new ArrayList<>();
        itemEstimates = new ArrayList<>();
        initializeLists(clients);
    }
    
    private void initializeLists(List<Client> clients) {
        for (Client client : clients) {
            for (ItemEstimate estimate : client.getAllItemEstimates()) {
                this.clients.add(client);
                itemEstimates.add(estimate);
            }
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1:
                return Integer.class;
            case 4: case 5:
                return Date.class;
            case 6:
                return Double.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {        
        Client client = clients.get(row);
        ItemEstimate estimate = itemEstimates.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return estimate.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return estimate.getStore().getName();
            case 4:
                return estimate.getRequestDate();
            case 5:
                return estimate.getLimitDate();
            case 6:
                return estimate.getTotalAmount();
            case 7:
                return estimate.getObs();
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
        return itemEstimates.size();
    }
}