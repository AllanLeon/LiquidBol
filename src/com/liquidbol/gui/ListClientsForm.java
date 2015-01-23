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
public class ListClientsForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable clientsTable;
    private JButton backBtn;

    public ListClientsForm() {
        setStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(800, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("CLIENTES");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        addBtn = new JButton("+");

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListClientsForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] columnNames = {"Cod",
            "Nombre",
            "NIT",
            "Factura",
            "Taller/Emp.",
            "Ruta",
            "Frec."
        };
        Object[][] tempData = {
            {"001", "Gabino Quispia", "5923804019", "-", "Taller Coso", "SI", "A"},
            {"002", "Efrain Choque", "4182093", "CHOQUE", "Taller Coso #2", "SI", "B"}
        };
        clientsTable = new JTable(tempData, columnNames);
        clientsTable.setFont(new Font("Arial", Font.PLAIN, 20));
        clientsTable.setRowHeight(25);
        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        clientsTable.getColumnModel().getColumn(5).setPreferredWidth(20);
        clientsTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane clientsTableSP = new JScrollPane(clientsTable);
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm mm = new MainMenuForm();
                dispose();
            }
        });
        
        title.setBounds(300, 30, 300, 30);
        addBtn.setBounds(630, 80, 100, 30);
        searchCB.setBounds(90, 120, 150, 30);
        searchBox.setBounds(250, 120, 300, 30);
        searchBtn.setBounds(540, 120, 50, 30);
        clientsTableSP.setBounds(30, 170, 730, 200);
        searchBtn.setBounds(50, 400, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(clientsTableSP);
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
