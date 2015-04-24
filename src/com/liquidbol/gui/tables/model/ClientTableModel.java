package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Client;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of clients.
 * @author Allan Leon
 */
public class ClientTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Nombre", "NIT",
        "Factura", "Taller/Emp.", "Ruta", "Frec."};
    private final List<Client> clients;

    public ClientTableModel(List<Client> clients) {
        this.clients = clients;
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1: case 3: case 7:
                return Integer.class;
            case 6:
                return Boolean.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Client client = clients.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return client.getId();
            case 2:
                return String.format("%s %s", client.getName(), client.getLastname());
            case 3:
                return client.getNit();
            case 4:
                return client.getBillName();
            case 5:
                return client.getCompanyName();
            case 6:
                return client.isRoute();
            case 7:
                return client.getFrequency();
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