package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.gui.tables.model.PurchaseTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Item;
import com.liquidbol.model.ItemPurchase;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.model.Purchase;
import com.liquidbol.model.Store;
import com.liquidbol.model.Supplier;
import com.liquidbol.services.StoreServices;
import com.liquidbol.services.SupplierServices;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class PurchaseForm extends JFrame {
    
    private final String[] SEARCH_PARAMETERS = {"Cod."};

    private JPanel contentPane;
    private Component datePicker;
    private JLabel idShower;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JLabel itemLbl;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JButton submitBtn;
    private JButton backBtn;
    private Object[] readItData;
    private JComboBox supplierCombo;
    private JLabel supplierLbl;
    private Purchase newPurchase;
    private List<Supplier> suppliers;
    private SupplierServices supplierServices;
    private StoreServices storeServices;
    
    public PurchaseForm(int state) {
        UIStyle sty = new UIStyle();
        newPurchase = new Purchase(0, new Date(new java.util.Date().getTime()));
        suppliers = new ArrayList<>(Company.getAllSuppliers());
        supplierServices = new SupplierServices();
        storeServices = new StoreServices();
        switch (state) {
            case 1: //Add/edit new purchase
                initComponents();
                setVisible(true);
                break;
            case 2: //show purchase data
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            default:
                initComponents();
                setVisible(true);
                break;
        }
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(640, 470);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVA COMPRA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        addBtn = new JButton("+");
        searchCB = new JComboBox();
        searchCB.setModel(new DefaultComboBoxModel(SEARCH_PARAMETERS));
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListCxcForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        itemLbl = new JLabel("Items");
        contentTable = new JTable(new PurchaseTableModel(newPurchase));
        /*String[] columnNames = {"Cod",
            "Cant.",
            "Unidad",
            "Nombre",
            "Costo",
            "Monto"
        };

        Object[][] tempData = {
            {"00126", 120, "Kg.", "Electrodo 7018 1/8", 18, 0},
            {"00119", 50, "Kg.", "Electrodo 6013 1/8", 19.5, 0}
        };contentTable = new JTable(tempData, columnNames);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 20));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(190);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(contentTable.getModel());
        contentTable.setRowSorter(sorter);*/
        contentTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                totalAmount.setText(String.format("%.2f",newPurchase.calculateTotalAmount()));
            }
        });
        JScrollPane tablesp = new JScrollPane(contentTable);

        //calculate import for each article
        //calculateEachArticlePrice(1,4,5);

        supplierLbl = new JLabel("Proveedor:");
        supplierCombo = new JComboBox();
        try {
            supplierCombo.setModel(new DefaultComboBoxModel(loadSupplierNames()));
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField(String.valueOf(newPurchase.getTotalAmount()));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        totalAmount.setEditable(false);
        
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Item res = Company.findItemById(searchBox.getText());
                    ItemPurchase itemPurchase = new ItemPurchase(0, res, res.getCost(), 0);
                    newPurchase.addItemPurchase(itemPurchase);
                    PurchaseTableModel tableModel = (PurchaseTableModel) contentTable.getModel();
                    tableModel.updateList();
                } catch (OperationFailedException ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        submitBtn = new JButton("Add");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoice();
                JOptionPane.showMessageDialog(null, "Purchase completed! \n Respect+");
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListPurchasesForm lpf = new ListPurchasesForm();
                dispose();
            }
        });

        title.setBounds(130, 30, 350, 30);
        datePicker.setBounds(170, 80, 150, 30);
        idShower.setBounds(370, 80, 150, 30);
        addBtn.setBounds(450, 120, 100, 30);
        searchCB.setBounds(50, 120, 120, 30);
        searchBox.setBounds(180, 120, 200, 30);
        searchBtn.setBounds(370, 120, 50, 30);
        itemLbl.setBounds(40, 150, 50, 30);
        tablesp.setBounds(30, 170, 570, 180);
        supplierLbl.setBounds(40, 360, 60, 30);
        supplierCombo.setBounds(100, 360, 200, 30);
        totalLbl.setBounds(470, 360, 30, 30);
        totalAmount.setBounds(500, 360, 90, 30);
        submitBtn.setBounds(470, 400, 70, 30);
        backBtn.setBounds(70, 400, 70, 30);

        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(idShower);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(itemLbl);
        contentPane.add(tablesp);
        contentPane.add(supplierLbl);
        contentPane.add(supplierCombo);
        contentPane.add(totalLbl);
        contentPane.add(totalAmount);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
    }
    
    public void invoice() {
        try {
            newPurchase.execute();
            List<Store> stores = new ArrayList<>(Company.getAllStores());
            supplierServices.addPurchaseToSupplier(newPurchase, suppliers.get(supplierCombo.getSelectedIndex()));
            storeServices.updateInventorys(stores.get(0));
            storeServices.loadStoreInventorys(stores.get(0));
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/*
    private void readIt() {
            //id, item, unitCost, quantity
        String name = ((JTextField) supplierName).getText();
        String lname = ((JTextField) supplierLName).getText();
        int phone = Integer.parseInt(((JTextField) supplierPhone).getText());
        int phone2 = Integer.parseInt(((JTextField) supplierPhone2).getText());
        String company = ((JTextField) supplierCompany).getText();
        String address = ((JTextField) supplierAddress).getText();
        String mail = ((JTextField) supplierEmail).getText();
        String city = ((JTextField) supplierCity).getText();
        if(1 == 0) {
            JOptionPane.showMessageDialog(this,"MISSING!","Missing some important data input!", JOptionPane.WARNING_MESSAGE);
        } else {
            readItData = new Object[] {totalamount, phone2,company, address, mail, city};
        }
    }

    private void saveIt(Object[] data) throws PersistenceException, ClassNotFoundException {
        Purchase temp = MagikarpScreen.purchServ.createPurchase(231,(String)data[0],(String)data[1],(int)data[2],
                (int)data[3],(String)data[4],(String)data[5],(String)data[6],(String)data[7]);
        MagikarpScreen.purchServ.savePurchase(temp);
        MagikarpScreen.suppServ.addPurchaseToSupplier(temp, temp2);
    }
    */
    private void convertToReadOnly() {        
        contentPane.remove(datePicker);
        contentPane.remove(searchCB);
        contentPane.remove(searchBox);
        contentPane.remove(searchBtn);
        contentPane.remove(addBtn);
        supplierCombo.setEnabled(false);
        contentPane.remove(totalAmount);

        title.setText("VER COMPRA"); //CHANGE!!!!
        datePicker = new JLabel();
        totalAmount = new JTextField();
        contentTable.setEnabled(false);
        
        datePicker.setFont(new Font("Arial", Font.PLAIN, 20));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 20));

        datePicker.setBounds(170, 80, 150, 30);
        totalAmount.setBounds(480, 360, 90, 30);
        
        contentPane.add(datePicker);
        contentPane.add(totalAmount);
    }
    
    public void calculateEachArticlePrice(int qValueCol, int upValueCol, int resValueCol) {
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            double quantity = Double.parseDouble(contentTable.getModel().getValueAt(i, qValueCol).toString());
            double unitPrice = Double.parseDouble(contentTable.getModel().getValueAt(i, upValueCol).toString());
            double calcdSubtotal = quantity * unitPrice;
            contentTable.getModel().setValueAt(calcdSubtotal, i, resValueCol);
        }
    }

    private Object[] loadSupplierNames() throws PersistenceException, ClassNotFoundException {
        List<String> data = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            String name = supplier.getName() + " " + supplier.getLastname();
            data.add(name);
        }
        return data.toArray();
    }
}