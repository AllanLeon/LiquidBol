package com.liquidbol.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

/**
 * @author Franco
 */
public class InventoryForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel branchLbl;
    private JComboBox branchNameCB;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JLabel itemsLbl;
    private JTable itemsTable;
    private JLabel serviceLbl;
    private JTable serviceTable;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryForm().setVisible(true);
            }
        });
    }

    public InventoryForm() {
        setStyle();
        initComponents();
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(700, 550);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("INVENTARIO");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        branchLbl = new JLabel("Sucursal");
        branchNameCB = new JComboBox();
        addBtn = new JButton("+");

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(InventoryForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        itemsLbl = new JLabel("Articulos");
        String[] columnNames = {"Cod",
            "CANTIDAD",
            "Unidad",
            "Descripcion",
            "Precio"
        };
        Object[][] tempData = {
            {"00126", 19.5, "Kg.", "Electrodo 7018 1/8", 18.00},
            {"00119", 29.75, "Kg.", "Electrodo 6013 1/8", 18.00}
        };
        itemsTable = new JTable(tempData, columnNames);
        itemsTable.setFont(new Font("Arial", Font.PLAIN, 20));
        itemsTable.setRowHeight(25);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane itemsTableSP = new JScrollPane(itemsTable);

        serviceLbl = new JLabel("Servicios");
        String[] columnNames2 = {"Cod",
            "Descripcion",
            "Precio"
        };
        Object[][] tempData2 = {
            {"00126", "Electrodo 7018 1/8", 18.00},
            {"00119", "Electrodo 6013 1/8", 18.00}
        };
        serviceTable = new JTable(tempData2, columnNames2);
        serviceTable.setFont(new Font("Arial", Font.PLAIN, 20));
        serviceTable.setRowHeight(25);
        serviceTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        serviceTable.getColumnModel().getColumn(1).setPreferredWidth(380);
        serviceTable.getColumnModel().getColumn(2).setMinWidth(40);
        serviceTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane serviceTableSP = new JScrollPane(serviceTable);

        title.setBounds(250, 30, 300, 30);
        branchLbl.setBounds(40, 80, 80, 30);
        branchNameCB.setBounds(100, 80, 150, 30);
        addBtn.setBounds(550, 80, 100, 30);
        searchCB.setBounds(40, 120, 150, 30);
        searchBox.setBounds(200, 120, 300, 30);
        searchBtn.setBounds(490, 120, 50, 30);
        itemsLbl.setBounds(30, 150, 50, 30);
        itemsTableSP.setBounds(30, 170, 630, 170);
        serviceLbl.setBounds(30, 350, 50, 30);
        serviceTableSP.setBounds(30, 370, 630, 130);

        contentPane.add(title);
        contentPane.add(branchLbl);
        contentPane.add(branchNameCB);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(itemsLbl);
        contentPane.add(itemsTableSP);
        contentPane.add(serviceLbl);
        contentPane.add(serviceTableSP);
    }

    public static void setStyle() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
