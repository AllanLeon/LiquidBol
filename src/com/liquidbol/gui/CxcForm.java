package com.liquidbol.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

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

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CxcForm(0).setVisible(true);
            }
        });
    }

    public CxcForm(int state) {
        switch (state) {
            case 1: //Add/edit new cxc
                setStyle();
                initComponents();
                break;
            case 2: //show cxc data
                setStyle();
                initComponents();
                convertToReadOnly();
                break;
            default:
                setStyle();
                initComponents();
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

        title.setBounds(140, 30, 350, 30);
        idLbl.setBounds(80, 80, 70, 30);
        idBox.setBounds(120, 80, 100, 30);
        nameLbl.setBounds(40, 120, 70, 30);
        clientName.setBounds(100, 120, 250, 30);
        cxccLbl.setBounds(40, 150, 50, 30);
        tablesp.setBounds(40, 170, 400, 200);

        contentPane.add(title);
        contentPane.add(idLbl);
        contentPane.add(idBox);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(cxccLbl);
        contentPane.add(tablesp);
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

        idBox = new JLabel();
        clientName = new JLabel();
        title.setText("VER CxC"); //CHANGE!!!!
        contentTable.setEnabled(false);
        
        idBox.setFont(new Font("Arial", Font.PLAIN, 20));
        clientName.setFont(new Font("Arial", Font.PLAIN, 20));

        idBox.setBounds(120, 80, 100, 30);
        clientName.setBounds(100, 120, 350, 30);

        contentPane.add(idBox);
        contentPane.add(clientName);
    }
}
