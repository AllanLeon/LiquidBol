package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Bill;
import com.liquidbol.model.Client;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of bills.
 * @author Allan Leon
 */
public class BillTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Cliente",
        "Monto Total", "Fecha", "Ruta", "Facturado", "Observaciones"};
    private final List<Client> clients;
    private final List<Bill> bills;

    public BillTableModel(List<Client> clients) {
        this.clients = new ArrayList<>();
        bills = new ArrayList<>();
        initializeLists(clients);
    }
    
    private void initializeLists(List<Client> clients) {
        for (Client client : clients) {
            for (Bill bill : client.getAllBills()) {
                this.clients.add(client);
                bills.add(bill);
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
        Bill bill = bills.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return bill.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return bill.getTotalAmount();
            case 4:
                return bill.getDate();
            case 5:
                return bill.isRoute();
            case 6:
                return bill.isBilled();
            case 7:
                return bill.getObs();
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
        return bills.size();
    }
}