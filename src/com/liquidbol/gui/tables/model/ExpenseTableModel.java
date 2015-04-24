package com.liquidbol.gui.tables.model;

import com.liquidbol.model.Expense;
import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Represents a table of expenses.
 * @author Allan Leon
 */
public class ExpenseTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Nro.", "Cod.", "Descripcion", "Monto",
        "Fecha", "Observaciones"};
    private final List<Expense> expenses;

    public ExpenseTableModel(List<Expense> expenses) {
        this.expenses = expenses;
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
            default :
                return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Expense expense = expenses.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return expense.getId();
            case 2:
                return expense.getDescription();
            case 3:
                return expense.getAmount();
            case 4:
                return expense.getPayDate();
            case 5:
                return expense.getObs();
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
        return expenses.size();
    }
}