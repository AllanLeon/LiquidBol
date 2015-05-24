package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.ClientTableModel;
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
public class ListClientsForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Cod.", "Nombre", "Nit", "Factura"};    
    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JTable clientsTable;
    private JButton backBtn;
    private ClientTableModel clientsTableModel;

    public ListClientsForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(800, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("CLIENTES");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientForm cf = new ClientForm(1);
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
                updateClientTableModel();
            }
        });
        
        
        clientsTableModel = new ClientTableModel(Company.getAllClients());
        clientsTable = new JTable(clientsTableModel);
        clientsTable.getTableHeader().setReorderingAllowed(false);
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        clientsTable.setFont(new Font("Arial", Font.PLAIN, 20));
        clientsTable.setRowHeight(25);
        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        clientsTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        clientsTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        clientsTable.getColumnModel().getColumn(7).setPreferredWidth(40);
        RowSorter<TableModel> sorter = new TableRowSorter<>(clientsTable.getModel());
        clientsTable.setRowSorter(sorter);
        JScrollPane clientsTableSP = new JScrollPane(clientsTable);
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
        
        title.setBounds(300, 30, 300, 30);
        addBtn.setBounds(630, 80, 100, 30);
        searchCB.setBounds(90, 120, 150, 30);
        searchBox.setBounds(250, 120, 330, 30);
        clientsTableSP.setBounds(30, 170, 730, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(clientsTableSP);
        contentPane.add(backBtn);
    }
    
    private void updateClientTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                clientsTableModel.setClients(Company.searchClientsById(searchBox.getText()));
                break;
            case 1:
                clientsTableModel.setClients(Company.searchClientsByName(searchBox.getText()));
                break;
            case 2:
                clientsTableModel.setClients(Company.searchClientsByNit(searchBox.getText()));
                break;
            case 3:
                clientsTableModel.setClients(Company.searchClientsByBillName(searchBox.getText()));
                break;
            default:;
        }
    }
}