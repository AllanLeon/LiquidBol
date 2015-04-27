package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Debt;
import com.liquidbol.model.Supplier;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of debts.
 * @author Allan Leon
 */
public class DebtTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Nombre", "Saldo", "Credito", "Fecha Limite"};
    private final List<Supplier> suppliers;
    private final List<Debt> debts;

    public DebtTableModel(Collection<Supplier> suppliers) {
        this.suppliers = new ArrayList<>();
        debts = new ArrayList<>();
        initializeValidDebtLists(suppliers);
    }
    
    private void initializeValidDebtLists(Collection<Supplier> suppliers) {
        for (Supplier supplier : suppliers) {
            for (Debt debt : supplier.getValidDebts()) {
                this.suppliers.add(supplier);
                debts.add(debt);
            }
        }
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
        Supplier supplier = suppliers.get(row);
        Debt debt = debts.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return debt.getId();
            case 2:
                return String.format("%s %s", supplier.getName(), supplier.getLastname());
            case 3:
                return debt.getAmount();
            case 4:
                return debt.getMaxAmount();
            case 5:
                return debt.getLimitDate();
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
        return debts.size();
    }
}