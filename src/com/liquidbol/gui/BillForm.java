package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.NumberToWords;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Bill;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.StoreServices;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class BillForm extends JFrame implements KeyListener {

    private JPanel contentPane;
    private JLabel title;
    private JDatePickerImpl datePicker;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTextField declarate;
    private JLabel sonLbl;
    private JTable contentTable;
    private JLabel nitLbl;
    private JTextField clientNit;
    private JLabel bsLbl;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JButton backBtn;
    private JButton submitBtn;
    private final TableModel passed;
    private final Bill bill;
    private Client client;
    private final ClientServices clientServices;
    private final StoreServices storeServices;
    private final CompanyServices companyServices;

    public BillForm(TableModel tm, Bill bill) {
        UIStyle sty = new UIStyle();
        passed = tm;
        this.bill = bill;
        this.clientServices = new ClientServices();
        this.storeServices = new StoreServices();
        this.companyServices = new CompanyServices();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        UtilDateModel model = new UtilDateModel(bill.getDate());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        title = new JLabel("FACTURA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        nitLbl = new JLabel("NIT/C.I.");
        clientNit = new JTextField();
        /*
         String[] columnNames = {"Cod",
         "Cant.",
         "Unidad",
         "Descripcion",
         "Precio Unit.",
         "Importe"
         };

         Object[][] tempData = {
         {"00126", 20, "Kg.", "Electrodo 7018 1/8", 22.8, 0},
         {"00119", 20, "Kg.", "Electrodo 6013 1/8", 22.8, 0}
         };
         contentTable = new JTable(tempData, columnNames); */
        contentTable = new JTable(passed);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 20));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(280);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        contentTable.getColumnModel().getColumn(6).setMinWidth(20);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(contentTable.getModel());
        contentTable.setRowSorter(sorter);
        JScrollPane tablesp = new JScrollPane(contentTable);

        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        totalAmount.setEditable(false);
        totalAmount.setText(String.valueOf(bill.calculateTotalAmount()));
        sonLbl = new JLabel("Son:");
        declarate = new JTextField();
        declarate.setText(NumberToWords.convert(bill.getTotalAmount().intValue()));
        bsLbl = new JLabel("Bolivianos");
        submitBtn = new JButton("PRINT");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You just printed a Bill! \n Respect+");
                invoice();
                LoginForm.LF.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm.scf.setVisible(true);
                dispose();
            }
        });

        title.setBounds(270, 20, 200, 30);
        datePicker.setBounds(300, 70, 150, 30);
        idShower.setBounds(500, 70, 160, 30);
        nameLbl.setBounds(40, 110, 140, 30);
        clientName.setBounds(100, 110, 350, 30);
        nitLbl.setBounds(460, 110, 60, 30);
        clientNit.setBounds(510, 110, 150, 30);
        tablesp.setBounds(30, 150, 640, 200);
        sonLbl.setBounds(40, 380, 50, 30);
        declarate.setBounds(70, 380, 350, 30);
        totalLbl.setBounds(540, 350, 50, 30);
        totalAmount.setBounds(570, 350, 100, 30);
        bsLbl.setBounds(425, 380, 70, 30);
        submitBtn.setBounds(580, 430, 80, 30);
        backBtn.setBounds(50, 430, 80, 30);

        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(nitLbl);
        contentPane.add(clientNit);
        contentPane.add(tablesp);
        contentPane.add(sonLbl);
        contentPane.add(declarate);
        contentPane.add(totalLbl);
        contentPane.add(totalAmount);
        contentPane.add(bsLbl);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);

        //clientNit.setText("9813");
        //clientNit.addKeyListener(this);
        clientNit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                findNIT();
            }
        });
    }

    private void invoice() {
        try {
            bill.execute();
            clientServices.addBillToClient(bill, client);
            companyServices.mergeClient(client);
            storeServices.updateInventorys(bill.getStore());
            storeServices.loadStoreInventorys(bill.getStore());
        } catch (OperationFailedException | PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void findNIT() {
        try {
            client = Company.findClientByNit(Integer.parseInt(clientNit.getText()));
            clientName.setText(client.getBillName());
        } catch (OperationFailedException ex) {
            Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                client = Company.findClientByNit(Integer.parseInt(clientNit.getText()));
                clientName.setText(client.getBillName());
            } catch (OperationFailedException ex) {
                Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}