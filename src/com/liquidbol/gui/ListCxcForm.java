package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.gui.tables.model.CXCTableModel;
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
public class ListCxcForm extends JFrame {

    private final String[] SEARCH_PARAMETERS = {"Estado", "Cliente"};
    
    private JPanel contentPane;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JTable cxcTable;
    private JButton backBtn;
    private CXCTableModel cxcTableModel;

    public ListCxcForm() {
        UIStyle sty = new UIStyle();
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
        
        searchCB = new JComboBox(new DefaultComboBoxModel(SEARCH_PARAMETERS));
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListCxcForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchBox.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) { }

            @Override
            public void keyPressed(KeyEvent ke) { }

            @Override
            public void keyReleased(KeyEvent ke) {
                updateCXCTableModel();
            }
        });

        cxcTableModel = new CXCTableModel(Company.getAllClients());
        cxcTable = new JTable(cxcTableModel);
        cxcTable.getTableHeader().setReorderingAllowed(false);
        cxcTable.setFont(new Font("Arial", Font.PLAIN, 20));
        cxcTable.setRowHeight(25);
        cxcTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        cxcTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        cxcTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        cxcTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        cxcTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        cxcTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(cxcTable.getModel());
        cxcTable.setRowSorter(sorter);
        JScrollPane cxcsTableSP = new JScrollPane(cxcTable);

        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
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
    
    private void updateCXCTableModel() {
        switch (searchCB.getSelectedIndex()) {
            case 0:
                cxcTableModel.updateListsByCXCState(Company.getAllClients(), searchBox.getText());
                break;
            case 1:
                cxcTableModel.setClients(Company.searchClientsByName(searchBox.getText()));
                break;
            default:;
        }
    }
}