package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.PurchaseListTableModel;
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
public class ListPurchasesForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Proveedor"};
    
    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JTable purchasesTable;
    private JButton backBtn;
    private PurchaseListTableModel purchasesTableModel;

    public ListPurchasesForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(600, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("COMPRAS");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PurchaseForm pf = new PurchaseForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox(new DefaultComboBoxModel(SEARCH_PARAMETERS));
        searchBox = new JTextField();
        searchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {
                updatePurchaseTableModel();
            }
        });

        purchasesTableModel = new PurchaseListTableModel(Company.getAllSuppliers());
        purchasesTable = new JTable(purchasesTableModel);
        purchasesTable.getTableHeader().setReorderingAllowed(false);
        purchasesTable.setFont(new Font("Arial", Font.PLAIN, 20));
        purchasesTable.setRowHeight(25);
        purchasesTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        purchasesTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        purchasesTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        purchasesTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        purchasesTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        purchasesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(purchasesTable.getModel());
        purchasesTable.setRowSorter(sorter);
        JScrollPane purchasesTableSP = new JScrollPane(purchasesTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  
        
        title.setBounds(190, 30, 400, 30);
        addBtn.setBounds(410, 80, 100, 30);
        searchCB.setBounds(130, 120, 120, 30);
        searchBox.setBounds(260, 120, 200, 30);
        purchasesTableSP.setBounds(30, 170, 530, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(purchasesTableSP);
        contentPane.add(backBtn);
    }
    
    private void updatePurchaseTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                purchasesTableModel.setSuppliers(Company.searchSuppliersByName(searchBox.getText()));
                break;
            default:;
        }
    }
}