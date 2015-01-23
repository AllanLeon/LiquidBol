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
public class ListCxcForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable clientsTable;
    private JButton backBtn;

    public ListCxcForm() {
        setStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(600, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("CUENTAS X COBRAR");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CxcForm cf = new CxcForm(1);
                dispose();
            }
        });
        
        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListCxcForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] columnNames = {"Cod",
            "Nombre",
            "Saldo",
            "Credito Limite",
            "Fecha Limite"
        };
        Object[][] tempData = {
            {"00001", "Gabino Quispia", "50.00", "2000", "30/01/2015"},
            {"00002", "Efrain Choque", "320.50", "5000", "01/02/2015"}
        };
        clientsTable = new JTable(tempData, columnNames);
        clientsTable.setFont(new Font("Arial", Font.PLAIN, 20));
        clientsTable.setRowHeight(25);
        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane cxcsTableSP = new JScrollPane(clientsTable);
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm mm = new MainMenuForm();
                dispose();
            }
        });
        
        title.setBounds(100, 30, 500, 30);
        addBtn.setBounds(470, 80, 100, 30);
        searchCB.setBounds(50, 120, 150, 30);
        searchBox.setBounds(210, 120, 250, 30);
        searchBtn.setBounds(450, 120, 50, 30);
        cxcsTableSP.setBounds(30, 170, 530, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(cxcsTableSP);
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
