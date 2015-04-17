package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.StoreTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Store;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Franco
 */
public class ListStoresForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JTable storeTable;
    private JButton backBtn;
    private JButton addBtn;

    public ListStoresForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(550, 360);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("TIENDAS");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoreForm sf = new StoreForm(1);
                dispose();
            }
        });
        /*String[] columnNames = {"Id",
         "Nombre",
         "Dirección",
         "Telefono"
         };
         Object[][] tempData = {
         {"00001", "Central", "C. Pagador esq. Herrera", "52 79947"},
         {"00002", "S.I.", "C. Pagador entre Herrera y 1ro de noviembre", "52 32190"}
         };
         storeTable = new JTable(tempData, columnNames); */
        List<Store> stores = new ArrayList<>(Company.getAllStores());
        storeTable = new JTable(new StoreTableModel(stores));
        storeTable.getTableHeader().setReorderingAllowed(false);
        storeTable.setFont(new Font("Arial", Font.PLAIN, 16));
        storeTable.setRowHeight(25);
        storeTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        storeTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        storeTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        storeTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        storeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(storeTable.getModel());
        storeTable.setRowSorter(sorter);
        JScrollPane storeTableSP = new JScrollPane(storeTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
        ;
        title.setBounds(140, 30, 500, 30);
        addBtn.setBounds(370, 80, 100, 30);
        storeTableSP.setBounds(30, 120, 470, 150);
        backBtn.setBounds(50, 280, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(storeTableSP);
        contentPane.add(backBtn);
    }
}