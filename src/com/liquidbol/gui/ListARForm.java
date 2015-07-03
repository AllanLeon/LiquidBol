package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.RechargeableItemTableModel;
import com.liquidbol.model.Company;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ListARForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Cod.", "Descripcion", "Tipo", "Cliente"};
    
    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JTable arsTable;
    private JButton backBtn;
    private RechargeableItemTableModel rechargeableItemTableModel;

    public ListARForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(800, 470);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("ARTICULOS RECARGABLES");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ARForm cf = new ARForm(1);
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
                updateRechargeableItemTableModel();
            }
        });

        rechargeableItemTableModel = new RechargeableItemTableModel(Company.getAllClients());
        arsTable = new JTable(rechargeableItemTableModel);
        arsTable.getTableHeader().setReorderingAllowed(false);
        arsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        arsTable.setRowHeight(25);
        arsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        arsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        arsTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        arsTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        arsTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        arsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        arsTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        arsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(arsTable.getModel());
        arsTable.setRowSorter(sorter);
        JScrollPane arsTableSP = new JScrollPane(arsTable);
        addDoubleClickViewer(arsTable);
        
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });

        title.setBounds(140, 30, 550, 30);
        addBtn.setBounds(550, 80, 100, 30);
        searchCB.setBounds(130, 120, 150, 30);
        searchBox.setBounds(290, 120, 280, 30);
        arsTableSP.setBounds(30, 170, 730, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(arsTableSP);
        contentPane.add(backBtn);
    }
    
    private void updateRechargeableItemTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                rechargeableItemTableModel.updateListsByRechargeableItemId(Company.getAllClients(), searchBox.getText());
                break;
            case 1:
                rechargeableItemTableModel.updateListsByRechargeableItemDescription(Company.getAllClients(), searchBox.getText());
                break;
            case 2:
                rechargeableItemTableModel.updateListsByRechargeableItemType(Company.getAllClients(), searchBox.getText());
                break;
            case 3:
                rechargeableItemTableModel.setClients(Company.searchClientsByName(searchBox.getText()));
                break;
            default:;
        }
    }
    
    private void addDoubleClickViewer(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    int arNumber = (int) table.getModel().getValueAt(row, 0);
                    String clientName = table.getModel().getValueAt(row, 2).toString();
                    ARForm arf = new ARForm(arNumber, clientName);
                    dispose();
                }
            }
        });
    }
}