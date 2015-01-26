package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class PurchaseForm extends JFrame {

    private JPanel contentPane;
    private Component datePicker;
    private JLabel idShower;
    private JLabel title;
    private JButton addBtn;
    private JComboBox searchCB;
    private JTextField searchBox;
    private JButton searchBtn;
    private JLabel itemLbl;
    private JTable contentTable;
    private JLabel totalLbl;
    private Component totalAmount;
    private JButton submitBtn;
    private JButton backBtn;
    
    public PurchaseForm(int state) {
        switch (state) {
            case 1: //Add/edit new purchase
                setStyle();
                initComponents();
                setVisible(true);
                break;
            case 2: //show purchase data
                setStyle();
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            default:
                setStyle();
                initComponents();
                setVisible(true);
                break;
        }
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(640, 470);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVA COMPRA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        addBtn = new JButton("+");
        searchCB = new JComboBox();
        searchBox = new JTextField();
        try {
            searchBtn = new JButton(null, new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/zoom.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ListCxcForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        itemLbl = new JLabel("Items");
        String[] columnNames = {"Cod",
            "Cant.",
            "Unidad",
            "Nombre",
            "Costo",
            "Monto"
        };

        Object[][] tempData = {
            {"00126", 120, "Kg.", "Electrodo 7018 1/8", 18, 0},
            {"00119", 50, "Kg.", "Electrodo 6013 1/8", 19.5, 0}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 20));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(190);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane tablesp = new JScrollPane(contentTable);

        //calculate import for each article
        calculateEachArticlePrice(1,4,5);

        totalLbl = new JLabel("Total");
        double total = 0;
        //calculate Total
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            total += Double.parseDouble(contentTable.getModel().getValueAt(i,5).toString());
        }
        totalAmount = new JTextField(String.valueOf(total));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        submitBtn = new JButton("Add");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Purchase completed! \n Respect+");
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListPurchasesForm lpf = new ListPurchasesForm();
                dispose();
            }
        });

        title.setBounds(130, 30, 350, 30);
        datePicker.setBounds(170, 80, 150, 30);
        idShower.setBounds(370, 80, 150, 30);
        addBtn.setBounds(450, 120, 100, 30);
        searchCB.setBounds(50, 120, 120, 30);
        searchBox.setBounds(180, 120, 200, 30);
        searchBtn.setBounds(370, 120, 50, 30);
        itemLbl.setBounds(40, 150, 50, 30);
        tablesp.setBounds(30, 170, 570, 180);
        totalLbl.setBounds(450, 360, 30, 30);
        totalAmount.setBounds(480, 360, 90, 30);
        submitBtn.setBounds(470, 400, 70, 30);
        backBtn.setBounds(70, 400, 70, 30);

        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(idShower);
        contentPane.add(addBtn);
        contentPane.add(searchCB);
        contentPane.add(searchBox);
        contentPane.add(searchBtn);
        contentPane.add(itemLbl);
        contentPane.add(tablesp);
        contentPane.add(totalLbl);
        contentPane.add(totalAmount);
        contentPane.add(submitBtn);
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

    private void convertToReadOnly() {        
        contentPane.remove(datePicker);
        contentPane.remove(searchCB);
        contentPane.remove(searchBox);
        contentPane.remove(searchBtn);
        contentPane.remove(addBtn);
        contentPane.remove(totalAmount);

        title.setText("VER COMPRA"); //CHANGE!!!!
        datePicker = new JLabel();
        totalAmount = new JLabel();
        contentTable.setEnabled(false);
        
        datePicker.setFont(new Font("Arial", Font.PLAIN, 20));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 20));

        datePicker.setBounds(170, 80, 150, 30);
        totalAmount.setBounds(480, 360, 90, 30);
        
        contentPane.add(datePicker);
        contentPane.add(totalAmount);
    }
    
    public void calculateEachArticlePrice(int qValueCol, int upValueCol, int resValueCol) {
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            double quantity = Double.parseDouble(contentTable.getModel().getValueAt(i, qValueCol).toString());
            double unitPrice = Double.parseDouble(contentTable.getModel().getValueAt(i, upValueCol).toString());
            double calcdSubtotal = quantity * unitPrice;
            contentTable.getModel().setValueAt(calcdSubtotal, i, resValueCol);
        }
    }
}