package com.liquidbol.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class InventoryForm extends JFrame {
   
    private JPanel parentPane;
    private JPanel inventoryPane;
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
    private JPanel cartPane;
    private JTable wholeTable;
    private JButton toNoteBtn;
    private JButton toBillBtn;
    private JButton backBtn;

    public InventoryForm() {
        setStyle();
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
                MainMenuForm mm = new MainMenuForm();
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
            "CANT",
            "Unidad",
            "Descripcion",
            "Precio"
        };
        Object[][] tempData = {
            {"00126", 19.5, "Kg.", "Electrodo 7018 1/8", 18.00},
            {"00119", 29.75, "Kg.", "Electrodo 6013 1/8", 18.00}
        };
        itemsTable = new JTable(tempData, columnNames);
        itemsTable.setFont(new Font("Arial", Font.BOLD, 16));
        itemsTable.setRowHeight(25);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(240);
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
        serviceTable.setFont(new Font("Arial", Font.BOLD, 16));
        serviceTable.setRowHeight(25);
        serviceTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        serviceTable.getColumnModel().getColumn(1).setPreferredWidth(380);
        serviceTable.getColumnModel().getColumn(2).setMinWidth(40);
        serviceTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane serviceTableSP = new JScrollPane(serviceTable);

        cartPane = new JPanel();
        cartPane.setBorder(BorderFactory.createTitledBorder("Carrito"));
        cartPane.setLayout(null);

        String[] columnNames3 = {"Cod",
            "CANT",
            "Unidad",
            "Descripcion",
            "Precio"
        };
        Object[][] tempData3 = {
            {"00126", 1, "Kg.", "Electrodo 7018 1/8", 18.00},
            {"00119", 2, "Kg.", "Electrodo 6013 1/8", 36.00}
        };
        wholeTable = new JTable(tempData3, columnNames3);
        wholeTable.setFont(new Font("Arial", Font.BOLD, 16));
        wholeTable.setRowHeight(25);
        wholeTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        wholeTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        wholeTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        wholeTable.getColumnModel().getColumn(3).setPreferredWidth(240);
        wholeTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        wholeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane wholeTableSP = new JScrollPane(wholeTable);
        
        toNoteBtn = new JButton("A nota de venta");
        toBillBtn = new JButton("A facturar");
        
        title.setBounds(440, 20, 300, 30);
        inventoryPane.setBounds(0, 50, 550, 470);
        cartPane.setBounds(550, 50, 540, 370);
        branchLbl.setBounds(20, 40, 80, 30);
        branchNameCB.setBounds(80, 40, 150, 30);
        addBtn.setBounds(430, 40, 100, 30);
        searchCB.setBounds(20, 80, 150, 30);
        searchBox.setBounds(180, 80, 300, 30);
        searchBtn.setBounds(470, 80, 50, 30);
        itemsLbl.setBounds(10, 110, 50, 30);
        itemsTableSP.setBounds(10, 130, 530, 170);
        serviceLbl.setBounds(10, 310, 50, 30);
        serviceTableSP.setBounds(10, 330, 530, 130);
        wholeTableSP.setBounds(10, 70, 520, 200);
        toNoteBtn.setBounds(70, 280, 200, 50);
        toBillBtn.setBounds(290, 280, 200, 50);
        backBtn.setBounds(50, 560, 70, 30);

        add(title);
        add(backBtn);
        parentPane.add(inventoryPane);
        parentPane.add(cartPane);
        inventoryPane.add(branchLbl);
        inventoryPane.add(branchNameCB);
        inventoryPane.add(addBtn);
        inventoryPane.add(searchCB);
        inventoryPane.add(searchBox);
        inventoryPane.add(searchBtn);
        inventoryPane.add(itemsLbl);
        inventoryPane.add(itemsTableSP);
        inventoryPane.add(serviceLbl);
        inventoryPane.add(serviceTableSP);
        cartPane.add(wholeTableSP);
        cartPane.add(toNoteBtn);
        cartPane.add(toBillBtn);
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