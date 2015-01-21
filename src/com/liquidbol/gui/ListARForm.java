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
public class ListARForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable clientsTable;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListARForm().setVisible(true);
            }
        });
    }

    public ListARForm() {
        setStyle();
        initComponents();
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

        title = new JLabel("ARTICULOS RECARGABLES");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        addBtn = new JButton("+");

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListARForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] columnNames = {"Cod",
            "Cliente",
            "Descripcion",
            "Fecha Garantia",
            "Obs"
        };
        Object[][] tempData = {
            {"00001", "Gabino Quispia", "Extintor de Polvo Quimico 3 Lb.", "30/06/2015", "-"},
            {"00002", "Efrain Choque", "Tubo de Oxigeno industrial 6 Mts3", "01/07/2015", "-"}
        };
        clientsTable = new JTable(tempData, columnNames);
        clientsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        clientsTable.setRowHeight(25);
        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane arsTableSP = new JScrollPane(clientsTable);

        title.setBounds(100, 30, 500, 30);
        addBtn.setBounds(470, 80, 100, 30);
        searchCB.setBounds(50, 120, 150, 30);
        searchBox.setBounds(210, 120, 250, 30);
        searchBtn.setBounds(450, 120, 50, 30);
        arsTableSP.setBounds(30, 170, 730, 200);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(arsTableSP);
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