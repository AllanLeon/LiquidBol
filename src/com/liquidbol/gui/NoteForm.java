package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Bill;
import com.liquidbol.model.Client;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.StoreServices;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public class NoteForm extends JFrame {

    private JLabel title;
    private JLabel idShower;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JDatePickerImpl datePicker;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTextField clientPhone;
    private JLabel phoneLbl;
    private JCheckBox toPay;
    private JCheckBox toBilled;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JPanel contentPane;
    private JButton backBtn;
    private JButton submitBtn;
    private final TableModel passed;
    private final Bill bill;
    private Client client; //needs constructor without billname
    private final ClientServices clientServices;
    private final StoreServices storeServices;
    private final CompanyServices companyServices;

    public NoteForm(TableModel tm, Bill bill) {
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
        jRadioButton1 = new JRadioButton("Entrega");
        jRadioButton2 = new JRadioButton("Recepcion");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        title = new JLabel("NOTA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();
/*
        String[] columnNames = {"Cod",
            "Cant.",
            "Unidad",
            "Descripcion",
            "Precio Unit.",
            "Precio"
        };

        Object[][] tempData = {
            {"00126", 20, "Kg.", "Electrodo 7018 1/8", 22.8, 456},
            {"00119", 20, "Kg.", "Electrodo 6013 1/8", 22.8, 456}
        }; 
        contentTable = new JTable(tempData, columnNames); */
        contentTable = new JTable(passed);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 20));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(280);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        contentTable.getColumnModel().getColumn(5).setMinWidth(20);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(contentTable.getModel());
        contentTable.setRowSorter(sorter);
        JScrollPane tablejs = new JScrollPane(contentTable);
        
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        totalAmount.setEditable(false);
        totalAmount.setText(String.valueOf(bill.calculateTotalAmount()));

        toPay = new JCheckBox("x Cancelar");
        toBilled = new JCheckBox("x Facturar");
        submitBtn = new JButton("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Note printed! \n Respect+");
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

        title.setBounds(300, 50, 150, 30);
        datePicker.setBounds(300, 120, 150, 30);
        idShower.setBounds(500, 120, 150, 30);
        jRadioButton1.setBounds(550, 60, 100, 30);
        jRadioButton2.setBounds(550, 80, 100, 30);
        nameLbl.setBounds(40, 160, 150, 30);
        clientName.setBounds(100, 160, 350, 30);
        phoneLbl.setBounds(465, 160, 150, 30);
        clientPhone.setBounds(510, 160, 150, 30);
        tablejs.setBounds(30, 210, 640, 150);
        totalLbl.setBounds(540, 360, 50, 30);
        totalAmount.setBounds(570, 360, 100, 30);
        toPay.setBounds(250, 370, 100, 30);
        toBilled.setBounds(350, 370, 100, 30);
        submitBtn.setBounds(350, 420, 50, 30);
        backBtn.setBounds(50, 420, 80, 30);

        getContentPane().add(title);
        getContentPane().add(datePicker);
        getContentPane().add(idShower);
        getContentPane().add(jRadioButton1);
        getContentPane().add(jRadioButton2);
        getContentPane().add(nameLbl);
        getContentPane().add(clientName);
        getContentPane().add(phoneLbl);
        getContentPane().add(clientPhone);
        getContentPane().add(tablejs);
        getContentPane().add(totalLbl);
        getContentPane().add(totalAmount);
        getContentPane().add(toPay);
        getContentPane().add(toBilled);
        getContentPane().add(submitBtn);
        getContentPane().add(backBtn);
    }
    
    private void invoice() {
        try {
            bill.setBilled(false);
            bill.execute();
            clientServices.addBillToClient(bill, client);
            companyServices.mergeClient(client);
            storeServices.updateInventorys(bill.getStore());
            storeServices.loadStoreInventorys(bill.getStore());
        } catch (OperationFailedException | PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}