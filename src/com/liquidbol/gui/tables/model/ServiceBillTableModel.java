/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Client;
import com.liquidbol.model.ServiceBill;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of service bills.
 * @author Allan Leon
 */
public class ServiceBillTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Cliente",
        "Monto Total", "Fecha", "Facturado", "Observaciones"};
    
    private final List<Client> clients;
    private final List<ServiceBill> serviceBills;

    public ServiceBillTableModel(List<Client> clients) {
        this.clients = new ArrayList<>();
        serviceBills = new ArrayList<>();
        initializeLists(clients);
    }
    
    private void initializeLists(List<Client> clients) {
        for (Client client : clients) {
            for (ServiceBill serviceBill : client.getAllServiceBills()) {
                this.clients.add(client);
                serviceBills.add(serviceBill);
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
            case 5: 
                return Boolean.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        
        Client client = clients.get(row);
        ServiceBill serviceBill = serviceBills.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return serviceBill.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return serviceBill.getTotalAmount();
            case 4:
                return serviceBill.getDate();
            case 6:
                return serviceBill.isBilled();
            case 7:
                return serviceBill.getObs();
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
        return serviceBills.size();
    }
}
