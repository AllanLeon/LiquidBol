package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.ItemTableModel;
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
public class ListItemsForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Cod.", "Descripcion", "Marca", "Industria", "Tipo", "Subtipo"};
    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JTable itemsTable;
    private JButton backBtn;
    private ItemTableModel itemsTableModel;
    
    public ListItemsForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(900, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("ITEMS");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemForm itf = new ItemForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox(new DefaultComboBoxModel(SEARCH_PARAMETERS));
        searchBox = new JTextField();
        searchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //updateItemTableModel();
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //updateItemTableModel();
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                updateItemTableModel();
            }
        });
        
        itemsTableModel = new ItemTableModel(Company.getAllItems());
        itemsTable = new JTable(itemsTableModel);
        itemsTable.getTableHeader().setReorderingAllowed(false);
        itemsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        itemsTable.setRowHeight(25);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        itemsTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        itemsTable.getColumnModel().getColumn(6).setPreferredWidth(80);
        itemsTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        itemsTable.getColumnModel().getColumn(8).setPreferredWidth(40);
        itemsTable.getColumnModel().getColumn(9).setPreferredWidth(40);
        itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(itemsTable.getModel());
        itemsTable.setRowSorter(sorter);
        JScrollPane itemsTableSP = new JScrollPane(itemsTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  

        title.setBounds(350, 30, 500, 30);
        addBtn.setBounds(570, 80, 100, 30);
        searchCB.setBounds(150, 120, 150, 30);
        searchBox.setBounds(310, 120, 280, 30);
        itemsTableSP.setBounds(30, 170, 830, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(itemsTableSP);
        contentPane.add(backBtn);
    }
    
    private void updateItemTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                itemsTableModel.setItems(Company.searchItemsById(searchBox.getText()));
                break;
            case 1:
                itemsTableModel.setItems(Company.searchItemsByDescription(searchBox.getText()));
                break;
            case 2:
                itemsTableModel.setItems(Company.searchItemsByBrand(searchBox.getText()));
                break;
            case 3:
                itemsTableModel.setItems(Company.searchItemsByIndustry(searchBox.getText()));
                break;
            case 4:
                itemsTableModel.setItems(Company.searchItemsByType(searchBox.getText()));
                break;
            case 5:
                itemsTableModel.setItems(Company.searchItemsBySubtype(searchBox.getText()));
                break;
            default:;
        }
    }
}