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
public class ListQuotesForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable quotesTable;
    private JButton backBtn;
    
    public ListQuotesForm() {
        setStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(900, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("COTIZACIONES");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        addBtn = new JButton("+");

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListQuotesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] columnNames = {"Cod",
            "Tienda",
            "Fecha Cotizacion",
            "Fecha Limite",
            "Monto",
            "Observaciones"
        };
        Object[][] tempData = {            
            {"COT-001", "Central", "23/01/2015", "30/01/2015", "20.00", "Requiere Id Cotizante!!!"},
            {"COT-002", "S.I.", "10/01/2015", "17/01/2015", "20.00", "Requiere Id Cotizante!!!"}
        };
        quotesTable = new JTable(tempData, columnNames);
        quotesTable.setFont(new Font("Arial", Font.PLAIN, 16));
        quotesTable.setRowHeight(25);
        quotesTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        quotesTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        quotesTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        quotesTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        quotesTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        quotesTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        quotesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane quotesTableSP = new JScrollPane(quotesTable);
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm mm = new MainMenuForm();
                dispose();
            }
        });

        title.setBounds(350, 30, 500, 30);
        addBtn.setBounds(570, 80, 100, 30);
        searchCB.setBounds(150, 120, 150, 30);
        searchBox.setBounds(310, 120, 250, 30);
        searchBtn.setBounds(550, 120, 50, 30);
        quotesTableSP.setBounds(30, 170, 830, 200);
        backBtn.setBounds(50, 400, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(quotesTableSP);
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
            Logger.getLogger(JFrame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}