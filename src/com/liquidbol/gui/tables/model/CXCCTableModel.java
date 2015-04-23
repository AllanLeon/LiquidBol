package com.liquidbol.gui.tables.model;

import com.liquidbol.model.CXCC;
import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of CXCCs.
 * @author Allan Leon
 */
public class CXCCTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Monto", "Fecha"};
    private final List<CXCC> collectedReceivableAccounts;

    public CXCCTableModel(List<CXCC> cxcc) {
        this.collectedReceivableAccounts = cxcc;
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:  case 1:
                return Integer.class;
            case 2:
                return Double.class;
            case 3:
                return Date.class;
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        CXCC cxcc = collectedReceivableAccounts.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return cxcc.getId();
            case 2:
                return cxcc.getAmountPaid();
            case 3:
                return cxcc.getPayDate();
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
        return collectedReceivableAccounts.size();
    }
}