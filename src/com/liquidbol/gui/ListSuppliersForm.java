package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.SupplierTableModel;
import com.liquidbol.model.Company;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Franco
 */
public class ListSuppliersForm extends JFrame {
    
    private final String[] SEARCH_PARAMETERS = {"Nombre", "Compañia", "Ciudad"};

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JTable suppliersTable;
    private JButton backBtn;
    private SupplierTableModel suppliersTableModel;

    public ListSuppliersForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("PROVEEDORES");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierForm sf = new SupplierForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox(new DefaultComboBoxModel(SEARCH_PARAMETERS));
        searchBox = new JTextField();
        searchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) { }

            @Override
            public void keyPressed(KeyEvent ke) { }

            @Override
            public void keyReleased(KeyEvent ke) {
                updateSupplierTableModel();
            }
        });

        suppliersTableModel = new SupplierTableModel(Company.getAllSuppliers());
        suppliersTable = new JTable(suppliersTableModel);
        suppliersTable.getTableHeader().setReorderingAllowed(false);
        suppliersTable.setFont(new Font("Arial", Font.PLAIN, 20));
        suppliersTable.setRowHeight(25);
        suppliersTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        suppliersTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        suppliersTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        suppliersTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        suppliersTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(suppliersTable.getModel());
        suppliersTable.setRowSorter(sorter);
        JScrollPane clientsTableSP = new JScrollPane(suppliersTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  

        title.setBounds(100, 30, 350, 30);
        addBtn.setBounds(350, 80, 100, 30);
        searchCB.setBounds(60, 120, 100, 30);
        searchBox.setBounds(170, 120, 230, 30);
        clientsTableSP.setBounds(30, 170, 430, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(clientsTableSP);
        contentPane.add(backBtn);
    }
    
    private void updateSupplierTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                suppliersTableModel.setSuppliers(Company.searchSuppliersByName(searchBox.getText()));
                break;
            case 1:
                suppliersTableModel.setSuppliers(Company.searchSuppliersByCompany(searchBox.getText()));
                break;
            case 2:
                suppliersTableModel.setSuppliers(Company.searchSuppliersByCity(searchBox.getText()));
                break;
            default:;
        }
    }
}