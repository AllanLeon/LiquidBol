package com.liquidbol.gui.tables.model;

import com.liquidbol.model.CXC;
import com.liquidbol.model.Client;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of CXCs.
 * @author Allan Leon
 */
public class CXCTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Nombre", "Saldo", "Credito", "Fecha Limite", "Estado"};
    private final List<Client> clients;
    private final List<CXC> receivableAccounts;

    public CXCTableModel(Collection<Client> clients) {
        this.clients = new ArrayList<>();
        receivableAccounts = new ArrayList<>();
        updateLists(clients);
    }
    
    private void updateLists(Collection<Client> clients) {
        this.clients.clear();
        receivableAccounts.clear();
        for (Client client : clients) {
            for (CXC cxc : client.getAllReceivableAccounts()) {
                this.clients.add(client);
                receivableAccounts.add(cxc);
            }
        }
        fireTableDataChanged();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1:
                return Integer.class;
            case 3: case 4:
                return Double.class;
            case 5:
                return Date.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Client client = clients.get(row);
        CXC cxc = receivableAccounts.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return cxc.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return cxc.getDebt();
            case 4:
                return cxc.getCreditMaxAmount();
            case 5:
                return cxc.getCreditLimitDate();
            case 6:
                return cxc.getState();
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
        return receivableAccounts.size();
    }
    
    public void setClients(Collection<Client> clients) {
        updateLists(clients);
    }
    
    public void updateListsByCXCState(Collection<Client> clients, String cxcState) {
        this.clients.clear();
        receivableAccounts.clear();
        for (Client client : clients) {
            for (CXC cxc : client.searchReceivableAccountsByState(cxcState)) {
                this.clients.add(client);
                receivableAccounts.add(cxc);
            }
        }
        fireTableDataChanged();
    }
}