package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.gui.tables.model.CXCCTableModel;
import com.liquidbol.model.CXC;
import com.liquidbol.model.CXCC;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.Purchase;
import com.liquidbol.model.Supplier;
import com.liquidbol.services.StoreServices;
import com.liquidbol.services.SupplierServices;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class CxcForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idLbl;
    private JComboBox idBox;
    private JLabel nameLbl;
    private JComboBox clientBox;
    private JTable contentTable;
    private JLabel cxccLbl;
    private Component datePicker;
    private JLabel dateLbl;
    private JLabel amountLbl;
    private Component maxAmount;
    private JLabel debtLbl;
    private Component clientDebt;
    private JButton backBtn;
    private JButton submitBtn;
    private CXC newCxc;
    private List<Client> clients;


    public CxcForm(int state) {
        UIStyle sty = new UIStyle();
        newCxc = new CXC(0, Double.NaN, Double.NaN, null, null);
        clients = new ArrayList<>(Company.getAllClients());
        switch (state) {
            case 1: //Add/edit new cxc
                initComponents();
                setVisible(true);
                break;
            case 2: //show cxc data
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
        setSize(500, 470);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVA CXC");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idLbl = new JLabel("Id");
        idBox = new JComboBox();       
        try {
            idBox.setModel(new DefaultComboBoxModel(loadClient("IDs")));
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nameLbl = new JLabel("Nombre");
        clientBox = new JComboBox();
        try {
            clientBox.setModel(new DefaultComboBoxModel(loadClient("Names")));
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        dateLbl = new JLabel("Fecha limite");
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        amountLbl = new JLabel("Monto limite");
        maxAmount = new JTextField();
        debtLbl = new JLabel("Saldo");
        clientDebt = new JTextField();

        cxccLbl = new JLabel("CxCC");
        /*String[] columnNames = {"ID",
            "Monto pagado",
            "Fecha"
        };

        Object[][] tempData = {
            {001, 40.00, "02/01/2015"},
            {002, 100.00, "05/01/2015"}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);*/
        contentTable = new JTable(new CXCCTableModel(new ArrayList<CXCC>()));
        RowSorter<TableModel> sorter = new TableRowSorter<>(contentTable.getModel());
        contentTable.setRowSorter(sorter);

        JScrollPane tablesp = new JScrollPane(contentTable);
        submitBtn = new JButton("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Debt added! \n Respect+");
                ListCxcForm lcf = new ListCxcForm();
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListCxcForm lcf = new ListCxcForm();
                dispose();
            }
        });
        
        title.setBounds(140, 30, 350, 30);
        idLbl.setBounds(40, 90, 70, 30);
        idBox.setBounds(60, 90, 100, 30);
        nameLbl.setBounds(180, 90, 70, 30);
        clientBox.setBounds(230, 90, 180, 30);
        cxccLbl.setBounds(40, 120, 50, 30);
        tablesp.setBounds(40, 140, 400, 180);
        dateLbl.setBounds(40, 330, 70, 30);
        datePicker.setBounds(110, 330, 120, 30);
        amountLbl.setBounds(40, 360, 70, 30);
        maxAmount.setBounds(110, 360, 80, 30);
        debtLbl.setBounds(320, 330, 70, 30);
        clientDebt.setBounds(360, 330, 70, 30);       
        backBtn.setBounds(40, 400, 70, 30);
        submitBtn.setBounds(400, 400, 70, 30);
        
        contentPane.add(title);
        contentPane.add(idLbl);
        contentPane.add(idBox);
        contentPane.add(nameLbl);
        contentPane.add(clientBox);
        contentPane.add(cxccLbl);
        contentPane.add(tablesp);
        contentPane.add(dateLbl);
        contentPane.add(datePicker);
        contentPane.add(amountLbl);
        contentPane.add(maxAmount);
        contentPane.add(debtLbl);
        contentPane.add(clientDebt);
        contentPane.add(backBtn);
        contentPane.add(submitBtn);
    }

    private Object[] loadClient(String query) throws PersistenceException, ClassNotFoundException {
        List<String> data = new ArrayList<>();
        if(query.equals("IDs")) {
            for (Client client : clients) {
                int id = client.getId();
                data.add(String.valueOf(id));
            }        
        } else {
            for (Client client : clients) {
                String name = client.getName();
                data.add(name);
            }
        }
        return data.toArray();
    }

    private void convertToReadOnly() {        
        idBox.setEnabled(false);
        clientBox.setEnabled(false);
        contentPane.remove(datePicker);
        contentPane.remove(maxAmount);
        contentPane.remove(clientDebt);

        title.setText("VER CxC"); //CHANGE!!!!
        datePicker = new JLabel();
        maxAmount = new JLabel();
        clientDebt = new JLabel();
        contentTable.setEnabled(false);

        datePicker.setFont(new Font("Arial", Font.PLAIN, 20));
        maxAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        clientDebt.setFont(new Font("Arial", Font.PLAIN, 20));

        datePicker.setBounds(110, 360, 120, 30);
        maxAmount.setBounds(110, 390, 80, 30);
        clientDebt.setBounds(360, 360, 70, 30);
        
        contentPane.add(datePicker);
        contentPane.add(maxAmount);
        contentPane.add(clientDebt);
    }
}