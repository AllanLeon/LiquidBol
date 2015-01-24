package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
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
    private Component idBox;
    private JLabel nameLbl;
    private Component clientName;
    private JTable contentTable;
    private JLabel cxccLbl;
    private Component datePicker;
    private JLabel dateLbl;
    private JLabel amountLbl;
    private Component maxAmount;
    private JLabel debtLbl;
    private Component clientDebt;
    private JButton backBtn;

    public CxcForm(int state) {
        switch (state) {
            case 1: //Add/edit new cxc
                setStyle();
                initComponents();
                setVisible(true);
                break;
            case 2: //show cxc data
                setStyle();
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            default:
                setStyle();
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
        nameLbl = new JLabel("Nombre");
        clientName = new JComboBox();
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
        String[] columnNames = {"ID",
            "Monto pagado",
            "Fecha"
        };

        Object[][] tempData = {
            {001, 40.00, "02/01/2015"},
            {002, 100.00, "05/01/2015"}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane tablesp = new JScrollPane(contentTable);
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
        clientName.setBounds(230, 90, 180, 30);
        cxccLbl.setBounds(40, 120, 50, 30);
        tablesp.setBounds(40, 140, 400, 180);
        dateLbl.setBounds(40, 330, 70, 30);
        datePicker.setBounds(110, 330, 120, 30);
        amountLbl.setBounds(40, 360, 70, 30);
        maxAmount.setBounds(110, 360, 80, 30);
        debtLbl.setBounds(320, 330, 70, 30);
        clientDebt.setBounds(360, 330, 70, 30);       
        backBtn.setBounds(40, 400, 70, 30);
        
        contentPane.add(title);
        contentPane.add(idLbl);
        contentPane.add(idBox);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(cxccLbl);
        contentPane.add(tablesp);
        contentPane.add(dateLbl);
        contentPane.add(datePicker);
        contentPane.add(amountLbl);
        contentPane.add(maxAmount);
        contentPane.add(debtLbl);
        contentPane.add(clientDebt);
        contentPane.add(backBtn);
    }

    private void setStyle() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void convertToReadOnly() {        
        contentPane.remove(idBox);
        contentPane.remove(clientName);
        contentPane.remove(datePicker);
        contentPane.remove(maxAmount);
        contentPane.remove(clientDebt);

        idBox = new JLabel();
        clientName = new JLabel();
        title.setText("VER CxC"); //CHANGE!!!!
        datePicker = new JLabel();
        maxAmount = new JLabel();
        clientDebt = new JLabel();
        contentTable.setEnabled(false);
        
        idBox.setFont(new Font("Arial", Font.PLAIN, 20));
        clientName.setFont(new Font("Arial", Font.PLAIN, 20));
        datePicker.setFont(new Font("Arial", Font.PLAIN, 20));
        maxAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        clientDebt.setFont(new Font("Arial", Font.PLAIN, 20));

        idBox.setBounds(120, 80, 100, 30);
        clientName.setBounds(100, 120, 350, 30);
        datePicker.setBounds(110, 360, 120, 30);
        maxAmount.setBounds(110, 390, 80, 30);
        clientDebt.setBounds(360, 360, 70, 30);
        
        contentPane.add(idBox);
        contentPane.add(clientName);
        contentPane.add(datePicker);
        contentPane.add(maxAmount);
        contentPane.add(clientDebt);
    }
}