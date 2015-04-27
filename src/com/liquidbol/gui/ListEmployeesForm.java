package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.EmployeeTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Store;
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
public class ListEmployeesForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable employeesTable;
    private JButton backBtn;

    public ListEmployeesForm() {
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

        title = new JLabel("EMPLEADOS");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeForm ef = new EmployeeForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListEmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*String[] columnNames = {"C.I.",
            "Nombre",
            "Telf/Celular 1"
        };
        Object[][] tempData = {
            {"5382191", "Jesus Ledezma", "74823991"},
            {"4809321", "Lupe Cardenas", "71109254"}
        };
        employeesTable = new JTable(tempData, columnNames); */
        employeesTable = new JTable(new EmployeeTableModel(Company.getAllStores()));
        employeesTable.getTableHeader().setReorderingAllowed(false);
        employeesTable.setFont(new Font("Arial", Font.PLAIN, 20));
        employeesTable.setRowHeight(25);
        employeesTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        employeesTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        employeesTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        employeesTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        employeesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(employeesTable.getModel());
        employeesTable.setRowSorter(sorter);
        JScrollPane employeesTableSP = new JScrollPane(employeesTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  

        title.setBounds(120, 30, 300, 30);
        addBtn.setBounds(340, 80, 100, 30);
        searchCB.setBounds(80, 120, 130, 30);
        searchBox.setBounds(210, 120, 150, 30);
        searchBtn.setBounds(350, 120, 50, 30);
        employeesTableSP.setBounds(30, 170, 450, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(employeesTableSP);
        contentPane.add(backBtn);
    }
}