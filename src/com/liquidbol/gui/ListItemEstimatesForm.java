package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.ItemEstimateTableModel;
import com.liquidbol.model.Company;
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
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Franco
 */
public class ListItemEstimatesForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable quotesTable;
    private JButton backBtn;
    
    public ListItemEstimatesForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(760, 450);
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
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ItemEstimateForm ief = new ItemEstimateForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListItemEstimatesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*String[] columnNames = {"Cod",
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
        quotesTable = new JTable(tempData, columnNames); */
        quotesTable = new JTable(new ItemEstimateTableModel(Company.getAllClients()));
        quotesTable.getTableHeader().setReorderingAllowed(false);
        quotesTable.setFont(new Font("Arial", Font.PLAIN, 16));
        quotesTable.setRowHeight(25);
        quotesTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        quotesTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        quotesTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        quotesTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        quotesTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        quotesTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        quotesTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        quotesTable.getColumnModel().getColumn(7).setPreferredWidth(150);
        quotesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(quotesTable.getModel());
        quotesTable.setRowSorter(sorter);
        JScrollPane quotesTableSP = new JScrollPane(quotesTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  

        title.setBounds(250, 30, 400, 30);
        addBtn.setBounds(570, 80, 100, 30);
        searchCB.setBounds(150, 120, 150, 30);
        searchBox.setBounds(310, 120, 250, 30);
        searchBtn.setBounds(550, 120, 50, 30);
        quotesTableSP.setBounds(15, 170, 730, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(quotesTableSP);
        contentPane.add(backBtn);
    }
}