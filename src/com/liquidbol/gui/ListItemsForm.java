package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.ItemTableModel;
import com.liquidbol.model.Company;
import com.liquidbol.model.Item;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class ListItemsForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable itemsTable;
    private JButton backBtn;
    
    public ListItemsForm() {
        UIStyle sty = new UIStyle();
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

        title = new JLabel("ITEMS");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemForm itf = new ItemForm(1);
                dispose();
            }
        });

        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListItemsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*String[] columnNames = {"Cod",
            "Unidad",
            "Descripcion",
            "Marca",
            "Industria",
            "Tipo",
            "Subtipo",
            "Costo",
            "Precio"
        };
        Object[][] tempData = {            
            {"00561", "Pza", "Dado de 27mm Hexagonal", "Inafor", "Argentina", "Auxiliares", "Dados", "20.00", "38.00"},
            {"00562", "Pza", "Dado de 16mm Hexagonal", "Inafor", "Argentina", "Auxiliares", "Dados", "20.00", "35.00"}
        };
        itemsTable = new JTable(tempData, columnNames); */
        List<Item> items = new ArrayList<>(Company.getAllItems());
        itemsTable = new JTable(new ItemTableModel(items));
        itemsTable.getTableHeader().setReorderingAllowed(false);
        itemsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        itemsTable.setRowHeight(25);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        itemsTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        itemsTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        itemsTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        itemsTable.getColumnModel().getColumn(8).setPreferredWidth(50);
        itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane itemsTableSP = new JScrollPane(itemsTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });  

        title.setBounds(350, 30, 500, 30);
        addBtn.setBounds(570, 80, 100, 30);
        searchCB.setBounds(150, 120, 150, 30);
        searchBox.setBounds(310, 120, 250, 30);
        searchBtn.setBounds(550, 120, 50, 30);
        itemsTableSP.setBounds(30, 170, 830, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(itemsTableSP);
        contentPane.add(backBtn);
    }
}