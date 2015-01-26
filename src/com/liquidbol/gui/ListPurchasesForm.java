package com.liquidbol.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ListPurchasesForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable purchasesTable;
    private JButton backBtn;

    public ListPurchasesForm() {
        setStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("COMPRAS");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PurchaseForm pf = new PurchaseForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListPurchasesForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] columnNames = {"Cod",
            "Monto Total",
            "Fecha"
        };
        Object[][] tempData = {
            {"00002", "3820.50", "15/01/2015"},
            {"00001", "21150.00", "21/12/2014"}
        };
        purchasesTable = new JTable(tempData, columnNames);
        purchasesTable.setFont(new Font("Arial", Font.PLAIN, 20));
        purchasesTable.setRowHeight(25);
        purchasesTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        purchasesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        purchasesTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        purchasesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane purchasesTableSP = new JScrollPane(purchasesTable);
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  
        
        title.setBounds(140, 30, 400, 30);
        addBtn.setBounds(360, 80, 100, 30);
        searchCB.setBounds(80, 120, 120, 30);
        searchBox.setBounds(210, 120, 150, 30);
        searchBtn.setBounds(350, 120, 50, 30);
        purchasesTableSP.setBounds(30, 170, 430, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(purchasesTableSP);
        contentPane.add(backBtn);
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
            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
