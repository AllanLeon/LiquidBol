package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.ShopCartItemTableModel;
import com.liquidbol.gui.tables.model.ShopCartServiceTableModel;
import com.liquidbol.gui.tables.model.ShopCartTableModel;
import com.liquidbol.model.Bill;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.Employee;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemSale;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.Service;
import com.liquidbol.model.ServiceReception;
import com.liquidbol.model.Store;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Franco
 */
public class ShopCartForm extends JFrame {

    private JPanel parentPane;
    private JPanel inventoryPane;
    private JLabel title;
    private JLabel branchLbl;
    private JComboBox branchNameCB;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JLabel itemsLbl;
    private JTable itemsTable;
    private JLabel serviceLbl;
    private JTable serviceTable;
    private JPanel cartPane;
    private JTable wholeTable;
    private JLabel totalLbl;
    private JTextField cartTotal;
    private JButton toNoteBtn;
    private JButton toBillBtn;
    private JButton backBtn;
    private double total;
    private Bill newBill;

    public ShopCartForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(1100, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        parentPane = new JPanel();
        parentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(parentPane);
        parentPane.setLayout(null);
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });

        title = new JLabel("INVENTARIO");
        title.setFont(new Font("Arial", Font.PLAIN, 40));

        inventoryPane = new JPanel();
        inventoryPane.setBorder(BorderFactory.createTitledBorder("Inventario"));
        inventoryPane.setLayout(null);

        branchLbl = new JLabel("Sucursal");
        branchNameCB = new JComboBox();

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ShopCartForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        itemsLbl = new JLabel("Articulos");
        /*String[] columnNames = {"Cod",
            "STOCK",
            "Unidad",
            "Descripcion",
            "Precio"
        };
        Object[][] tempData = {
            {"00126", 19.5, "Kg.", "Electrodo 7018 1/8", 18.00},
            {"00119", 29.75, "Kg.", "Electrodo 6013 1/8", 18.00}
        };
        itemsTable = new JTable(new DefaultTableModel(tempData, columnNames) */
        List<Store> stores = new ArrayList<>(Company.getAllStores());
        itemsTable = new JTable(new ShopCartItemTableModel(stores) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        itemsTable.getTableHeader().setReorderingAllowed(false);
        itemsTable.setFont(new Font("Arial", Font.BOLD, 16));
        itemsTable.setRowHeight(25);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(240);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> itemSorter = new TableRowSorter<>(itemsTable.getModel());
        itemsTable.setRowSorter(itemSorter);
        JScrollPane itemsTableSP = new JScrollPane(itemsTable);

        serviceLbl = new JLabel("Servicios");
        /*String[] columnNames2 = {"Cod",
            "Descripcion",
            "Precio"
        };
        Object[][] tempData2 = {
            {"00126", "Electrodo 7018 1/8", 18.00},
            {"00119", "Electrodo 6013 1/8", 18.00}
        };
        serviceTable = new JTable(new DefaultTableModel(tempData2, columnNames2)*/
        List<Service> services = new ArrayList<>(Company.getAllServices());
        serviceTable = new JTable(new ShopCartServiceTableModel(services) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        serviceTable.getTableHeader().setReorderingAllowed(false);
        serviceTable.setFont(new Font("Arial", Font.BOLD, 16));
        serviceTable.setRowHeight(25);
        serviceTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        serviceTable.getColumnModel().getColumn(1).setPreferredWidth(380);
        serviceTable.getColumnModel().getColumn(2).setMinWidth(40);
        serviceTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> servSorter = new TableRowSorter<>(serviceTable.getModel());
        serviceTable.setRowSorter(servSorter);
        JScrollPane serviceTableSP = new JScrollPane(serviceTable);

        cartPane = new JPanel();
        cartPane.setBorder(BorderFactory.createTitledBorder("Carrito"));
        cartPane.setLayout(null);

        String[] columnNames3 = {"Cod",
            "CANT",
            "Unidad",
            "Descripcion",
            "Precio Unit.",
            "Precio"
        };
        Object[][] tempData3 = {
  //          {"00126", "", "Kg.", "Electrodo 7018 1/8", 18.00, 36.00},
  //          {"00119", "", "Kg.", "Electrodo 6013 1/8", 18.00, 36.00}
        };
        List<Employee> employees = new ArrayList<>(Company.getAllEmployees());
        newBill = new Bill(0, stores.get(0), employees.get(0), new Date(new java.util.Date().getTime()), false, false, "");
        wholeTable = new JTable(new ShopCartTableModel(newBill));
        /*wholeTable = new JTable(new DefaultTableModel(tempData3, columnNames3) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) {
                    return true;
                }
                return false;
            }
        });
        wholeTable.getTableHeader().setReorderingAllowed(false);
        wholeTable.setFont(new Font("Arial", Font.BOLD, 16));
        wholeTable.setRowHeight(25);
        wholeTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        wholeTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        wholeTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        wholeTable.getColumnModel().getColumn(3).setPreferredWidth(240);
        wholeTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        wholeTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        wholeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(wholeTable.getModel());
        wholeTable.setRowSorter(sorter);
        wholeTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    total = 0;
                    TableModel wholeTM = wholeTable.getModel();
                    for (int i = 0, rows = wholeTM.getRowCount(); i < rows; i++) {
                        double quant = (double) wholeTM.getValueAt(i, 1);
                        double unitprice = (double) wholeTM.getValueAt(i, 4);
                        double unitTotal = quant * unitprice;
                        wholeTM.setValueAt(unitTotal, i, 5);
                        total += (double) wholeTM.getValueAt(i, 5);
                    }
                    cartTotal.setText(String.format("%f", total));
                } catch (Exception ex) {
                }
            }
        });*/
        JScrollPane wholeTableSP = new JScrollPane(wholeTable);

        totalLbl = new JLabel("Total");
        cartTotal = new JTextField();
        cartTotal.setFont(new Font("Arial", Font.BOLD, 16));
        cartTotal.setText(newBill.getTotalAmount().toString());

        toNoteBtn = new JButton("A nota de venta");
        toNoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteForm nf = new NoteForm(wholeTable.getModel(), newBill);
                setVisible(false);
            }
        });
        toBillBtn = new JButton("A facturar");
        toBillBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillForm bf = new BillForm(wholeTable.getModel(), newBill);
                setVisible(false);
            }
        });

        title.setBounds(440, 20, 300, 30);
        inventoryPane.setBounds(0, 50, 550, 470);
        cartPane.setBounds(550, 50, 540, 370);
        branchLbl.setBounds(20, 40, 80, 30);
        branchNameCB.setBounds(80, 40, 150, 30);
        searchCB.setBounds(20, 80, 150, 30);
        searchBox.setBounds(180, 80, 300, 30);
        searchBtn.setBounds(470, 80, 50, 30);
        itemsLbl.setBounds(10, 110, 50, 30);
        itemsTableSP.setBounds(10, 130, 530, 170);
        serviceLbl.setBounds(10, 310, 50, 30);
        serviceTableSP.setBounds(10, 330, 530, 130);
        wholeTableSP.setBounds(10, 40, 520, 200);
        totalLbl.setBounds(430, 250, 50, 30);
        cartTotal.setBounds(460, 250, 70, 30);
        toNoteBtn.setBounds(70, 290, 200, 50);
        toBillBtn.setBounds(290, 290, 200, 50);
        backBtn.setBounds(50, 530, 70, 30);

        add(title);
        add(backBtn);
        parentPane.add(inventoryPane);
        parentPane.add(cartPane);
        inventoryPane.add(branchLbl);
        inventoryPane.add(branchNameCB);
        inventoryPane.add(searchCB);
        inventoryPane.add(searchBox);
        inventoryPane.add(searchBtn);
        inventoryPane.add(itemsLbl);
        inventoryPane.add(itemsTableSP);
        inventoryPane.add(serviceLbl);
        inventoryPane.add(serviceTableSP);
        cartPane.add(wholeTableSP);
        cartPane.add(totalLbl);
        cartPane.add(cartTotal);
        cartPane.add(toNoteBtn);
        cartPane.add(toBillBtn);

        addDoubleClickList(itemsTable, wholeTable, false);
        addDoubleClickList(serviceTable, wholeTable, true);
    }

    private void addDoubleClickList(JTable aTable, JTable bTable, boolean isService) {
        aTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                //int colCount = aTable.getColumnCount();
                if (me.getClickCount() == 2) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);
                    ShopCartTableModel shopCart = (ShopCartTableModel) bTable.getModel();
                    if (isService) {
                        ShopCartServiceTableModel model = (ShopCartServiceTableModel) aTable.getModel();
                        Service reqService = model.getServiceAt(row);
                        List<Client> clients = new ArrayList<>(Company.getAllClients());
                        List<RechargeableItem> recItems = new ArrayList<>(clients.get(0).getAllRechargeableItems());
                        newBill.addServiceReception(new ServiceReception(1, reqService, recItems.get(0), new Date(new java.util.Date().getTime()), new Timestamp(new java.util.Date().getTime()), 1.0, ""));
                    } else {
                        ShopCartItemTableModel model = (ShopCartItemTableModel) aTable.getModel();
                        Item reqItem = model.getItemAt(row);
                        newBill.addItemSale(new ItemSale(0, reqItem, 1, ""));
                    }
                    shopCart.updateLists();
                    /*Object[] rowdata = {};
                    Object[] obj = new Object[]{};
                    ArrayList<Object> newObj = new ArrayList<Object>(Arrays.asList(obj));
                    if (!isService) {
                        ShopCartServiceTableModel model = (ShopCartServiceTableModel) bTable.getModel();
                        for (int i = 0; i < colCount; i++) {
                            if (i == 1) {
                                newObj.add("");
                            } else {
                                newObj.add(table.getValueAt(row, i));
                            }
                        }
                        model.addRow(newObj.toArray());
                    } else {
                        ShopCartItemTableModel sourceModel = (ShopCartItemTableModel) bTable.getModel();
                        newObj.add(table.getValueAt(row, 0));
                        newObj.add("");
                        newObj.add("");
                        newObj.add(table.getValueAt(row, 1));
                        newObj.add(table.getValueAt(row, 2));
                        model.addRow(newObj.toArray());
                    }*/
                }
            }
        });
    }
}