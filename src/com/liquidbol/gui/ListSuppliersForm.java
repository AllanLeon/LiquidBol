package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.SupplierTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Supplier;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Franco
 */
public class ListSuppliersForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable suppliersTable;
    private JButton backBtn;

    public ListSuppliersForm() {
        UIStyle sty = new UIStyle();
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

        title = new JLabel("PROVEEDORES");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierForm sf = new SupplierForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListSuppliersForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*String[] columnNames = {"Id",
            "Nombre",
            "Compañia"
        };
        Object[][] tempData = {
            {"PR-001", "Remberto Flores", "Esa empresa"},
            {"PR-002", "Jose Jose", "La otra"}
        };
        suppliersTable = new JTable(tempData, columnNames); */
        List<Supplier> suppliers = new ArrayList<>(Company.getAllSuppliers());
        suppliersTable = new JTable(new SupplierTableModel(suppliers));
        suppliersTable.getTableHeader().setReorderingAllowed(false);
        suppliersTable.setFont(new Font("Arial", Font.PLAIN, 20));
        suppliersTable.setRowHeight(25);
        suppliersTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        suppliersTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        suppliersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        suppliersTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(suppliersTable.getModel());
        suppliersTable.setRowSorter(sorter);
        JScrollPane clientsTableSP = new JScrollPane(suppliersTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  

        title.setBounds(100, 30, 350, 30);
        addBtn.setBounds(350, 80, 100, 30);
        searchCB.setBounds(60, 120, 100, 30);
        searchBox.setBounds(170, 120, 200, 30);
        searchBtn.setBounds(360, 120, 50, 30);
        clientsTableSP.setBounds(30, 170, 430, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(clientsTableSP);
        contentPane.add(backBtn);
    }
}