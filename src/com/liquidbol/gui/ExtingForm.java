package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ExtingForm extends JFrame {

    private JPanel contentPane;
    private JButton submitBtn;
    private JDatePickerImpl datePicker;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTextField clientPhone;
    private JLabel phoneLbl;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JLabel compLbl;
    private JTextField clientComp;
    private JLabel quantLbl;
    private JTextField quantAmount;
    private JLabel ACLbl;
    private JTextField ACAmount;
    private JLabel balanceLbl;
    private JTextField balanceAmount;
    private JLabel dateLbl;
    private JDatePickerImpl dateField;
    private JTextField hourField;
    private JLabel hourLbl;
    private JLabel amountLbl;
    private JButton backBtn;

    public ExtingForm() {
        setStyle();
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

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        title = new JLabel("NOTA DE RECEPCIÓN DE EXTINTORES");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();
        compLbl = new JLabel("Empresa");
        clientComp = new JTextField();

        String[] columnNames = {"Cant.",
            "No. Extintor",
            "Descripcion/Marca",
            "Tipo",
            "Capacidad",
            "Obs"
        };

        Object[][] tempData = {
            {1, "57885", "Extintor chino c/Pico", "PQ", "3 Lb", "En periodo de garantia"}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(5).setMinWidth(170);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane tablesp = new JScrollPane(contentTable);

        quantLbl = new JLabel("Cant. Total");
        quantAmount = new JTextField();
        amountLbl = new JLabel("Monto a pagar (Bs)");
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        ACLbl = new JLabel("A/C");
        ACAmount = new JTextField();
        balanceLbl = new JLabel("Saldo");
        balanceAmount = new JTextField();
        dateLbl = new JLabel("Fecha de entrega");
        dateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        hourLbl = new JLabel("Hora");
        hourField = new JTextField();
        submitBtn = new JButton("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Article recieved! \n Respect+");
                LoginForm.LF.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });

        title.setBounds(120, 20, 500, 30);
        datePicker.setBounds(300, 90, 150, 30);
        idShower.setBounds(500, 90, 150, 30);
        nameLbl.setBounds(40, 130, 150, 30);
        clientName.setBounds(100, 130, 350, 30);
        compLbl.setBounds(40, 160, 150, 30);
        clientComp.setBounds(100, 160, 350, 30);
        phoneLbl.setBounds(465, 130, 150, 30);
        clientPhone.setBounds(510, 130, 150, 30);
        tablesp.setBounds(30, 200, 640, 150);
        quantLbl.setBounds(30, 350, 70, 30);
        quantAmount.setBounds(90, 350, 50, 30);
        dateLbl.setBounds(150, 345, 110, 30);
        dateField.setBounds(150, 370, 130, 30);
        hourLbl.setBounds(150, 390, 50, 30);
        hourField.setBounds(150, 410, 90, 30);
        amountLbl.setBounds(320, 370, 120, 30);
        totalLbl.setBounds(430, 350, 50, 30);
        totalAmount.setBounds(430, 370, 70, 30);
        ACLbl.setBounds(500, 350, 50, 30);
        ACAmount.setBounds(500, 370, 70, 30);
        balanceLbl.setBounds(570, 350, 50, 30);
        balanceAmount.setBounds(570, 370, 70, 30);
        submitBtn.setBounds(350, 430, 50, 30);
        backBtn.setBounds(50, 430, 70, 30);

        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(compLbl);
        contentPane.add(clientComp);
        contentPane.add(phoneLbl);
        contentPane.add(clientPhone);
        contentPane.add(tablesp);
        contentPane.add(quantLbl);
        contentPane.add(quantAmount);
        contentPane.add(dateLbl);
        contentPane.add(dateField);
        contentPane.add(hourLbl);
        contentPane.add(hourField);
        contentPane.add(amountLbl);
        contentPane.add(totalLbl);
        contentPane.add(totalAmount);
        contentPane.add(ACLbl);
        contentPane.add(ACAmount);
        contentPane.add(balanceLbl);
        contentPane.add(balanceAmount);
        contentPane.add(submitBtn);
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
}
