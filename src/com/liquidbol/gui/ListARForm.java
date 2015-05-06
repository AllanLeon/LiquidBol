package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.RechargeableItemTableModel;
import com.liquidbol.model.Company;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
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
public class ListARForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Cod.", "Descripcion", "Tipo", "Cliente"};
    
    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable arsTable;
    private JButton backBtn;
    private RechargeableItemTableModel rechargeableItemTableModel;

    public ListARForm() {
        UIStyle sty = new UIStyle();
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

        title = new JLabel("ARTICULOS RECARGABLES");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        
        addBtn = new JButton("+");

        searchCB = new JComboBox(new DefaultComboBoxModel(SEARCH_PARAMETERS));
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListARForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchBox.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) { }

            @Override
            public void keyPressed(KeyEvent ke) { }

            @Override
            public void keyReleased(KeyEvent ke) {
                updateRechargeableItemTableModel();
            }
        });

        rechargeableItemTableModel = new RechargeableItemTableModel(Company.getAllClients());
        arsTable = new JTable(rechargeableItemTableModel);
        arsTable.getTableHeader().setReorderingAllowed(false);
        arsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        arsTable.setRowHeight(25);
        arsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        arsTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        arsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        arsTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        arsTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        arsTable.getColumnModel().getColumn(5).setPreferredWidth(90);
        arsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(arsTable.getModel());
        arsTable.setRowSorter(sorter);
        JScrollPane arsTableSP = new JScrollPane(arsTable);
        
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });

        title.setBounds(140, 30, 500, 30);
        addBtn.setBounds(510, 80, 100, 30);
        searchCB.setBounds(90, 120, 150, 30);
        searchBox.setBounds(250, 120, 250, 30);
        searchBtn.setBounds(500, 120, 50, 30);
        arsTableSP.setBounds(30, 170, 730, 200);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(arsTableSP);
        contentPane.add(backBtn);
    }
    
    private void updateRechargeableItemTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                rechargeableItemTableModel.updateListsByRechargeableItemId(Company.getAllClients(), searchBox.getText());
                break;
            case 1:
                rechargeableItemTableModel.updateListsByRechargeableItemDescription(Company.getAllClients(), searchBox.getText());
                break;
            case 2:
                rechargeableItemTableModel.updateListsByRechargeableItemType(Company.getAllClients(), searchBox.getText());
                break;
            case 3:
                rechargeableItemTableModel.setClients(Company.searchClientsByName(searchBox.getText()));
                break;
            default:;
        }
    }
}