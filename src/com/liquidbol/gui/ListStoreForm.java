package com.liquidbol.gui;

import com.liquidbol.gui.tables.model.StoreTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Store;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
public class ListStoreForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JTable storeTable;
    private JButton backBtn;
    private JButton addBtn;

    public ListStoreForm() {
        setStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(550, 360);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        title = new JLabel();
        title.setText("NUEVA TIENDA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        addBtn = new JButton("+");
        
        /*String[] columnNames = {"Id",
            "Nombre",
            "Dirección",
            "Telefono"
        };
        Object[][] tempData = {
            {"00001", "Central", "C. Pagador esq. Herrera", "52 79947"},
            {"00002", "S.I.", "C. Pagador entre Herrera y 1ro de noviembre", "52 32190"}
        };
        storeTable = new JTable(tempData, columnNames);
        storeTable.getTableHeader().setReorderingAllowed(false);
        storeTable.setFont(new Font("Arial", Font.PLAIN, 16));
        storeTable.setRowHeight(25);
        storeTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        storeTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        storeTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        storeTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        storeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);*/
        List<Store> stores = new ArrayList<>(Company.getAllStores());
        storeTable = new JTable(new StoreTableModel(stores));
        JScrollPane storeTableSP = new JScrollPane(storeTable);
        
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
;
        title.setBounds(140, 30, 500, 30);
        addBtn.setBounds(370, 80, 100, 30);
        storeTableSP.setBounds(30, 120, 470, 150);
        backBtn.setBounds(50, 280, 70, 30);
        
        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(storeTableSP);
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