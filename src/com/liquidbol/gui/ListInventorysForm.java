package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.gui.tables.model.InventoryTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Store;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Franco
 */
public class ListInventorysForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Cod.", "Descripcion", "Tipo", "Subtipo"};
    private final int MIN_STOCK = 10;
    
    private JPanel contentPane;
    private JLabel title;
    //private JButton addBtn;
    private JLabel branchLbl;
    private JComboBox branchNameCB;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JTable itemsTable;
    private JButton backBtn;
    private InventoryTableModel inventorysTableModel;
    private List<Store> stores;
    private Store selectedStore;
    
    public ListInventorysForm() {
        UIStyle sty = new UIStyle();
        stores = new ArrayList<>(Company.getAllStores());
        selectedStore = stores.get(0);
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

        title = new JLabel("INVENTARIO");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        /*addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemForm itf = new ItemForm(1);
                dispose();
            }
        });*/
        
        branchLbl = new JLabel("Sucursal");
        branchNameCB = new JComboBox();
        try {
            branchNameCB.setModel(new DefaultComboBoxModel(loadBranchNames()));
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(ShopCartForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        branchNameCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectedStore = stores.get(branchNameCB.getSelectedIndex());
                inventorysTableModel.setStore(selectedStore);
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
                updateInventoryTableModel();
            }
        });
                
        inventorysTableModel = new InventoryTableModel(selectedStore);
        itemsTable = new JTable(inventorysTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (inventorysTableModel.getItemStock(row) < MIN_STOCK) {
                        c.setBackground(Color.RED);
                }
                return c;
            }

        };
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
        //addBtn.setBounds(570, 80, 100, 30);
        branchLbl.setBounds(20, 40, 80, 30);
        branchNameCB.setBounds(80, 40, 150, 30);
        searchCB.setBounds(150, 120, 150, 30);
        searchBox.setBounds(310, 120, 280, 30);
        itemsTableSP.setBounds(30, 170, 830, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        //contentPane.add(addBtn);
        contentPane.add(branchLbl);
        contentPane.add(branchNameCB);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(itemsTableSP);
        contentPane.add(backBtn);
    }
    
    private Object[] loadBranchNames() throws PersistenceException, ClassNotFoundException {
        List<String> data = new ArrayList<>();
        for (Store store : stores) {
            String name = store.getName();
            data.add(name);
        }
        return data.toArray();
    }
    
    private void updateInventoryTableModel() {
        String searchQuery = searchBox.getText().trim();
        switch (searchCB.getSelectedIndex()) {
            case 0:
                inventorysTableModel.setInventorys(selectedStore.searchInventorysByItemId(searchQuery));
                break;
            case 1:
                inventorysTableModel.setInventorys(selectedStore.searchInventorysByItemDescription(searchQuery));
                break;
            case 2:
                inventorysTableModel.setInventorys(selectedStore.searchInventorysByItemType(searchQuery));
                break;
            case 3:
                inventorysTableModel.setInventorys(selectedStore.searchInventorysByItemSubtype(searchQuery));
                break;
            default:;
        }
    }
}