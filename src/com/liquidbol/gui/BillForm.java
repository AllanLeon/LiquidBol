package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.sun.corba.se.spi.orbutil.closure.Closure;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * @author Franco
 */
public class BillForm extends JFrame {

    private JButton jButton1;
    private JDatePickerImpl datePicker;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTextField declarate;
    private JLabel sonLbl;
    private JTable contentTable;
    private JLabel nitLbl;
    private JTextField clientNit;
    private JLabel bsLbl;
    private JLabel totalLbl;
    private JTextField totalAmount;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BillForm().setVisible(true);
            }
        });
    }
    
    public BillForm() {
        setStyle();
        initComponents();
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new AbsoluteLayout());

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        title = new JLabel("FACTURA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        nitLbl = new JLabel("NIT/C.I.");
        clientNit = new JTextField();

        String[] columnNames = {"Cod",
            "Cant.",
            "Unidad",
            "Descripcion",
            "Precio Unit.",
            "Importe"
        };

        Object[][] tempData = {
            {"00126", 20, "Kg.", "Electrodo 7018 1/8", 22.8, 0},
            {"00119", 20, "Kg.", "Electrodo 6013 1/8", 22.8, 0}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 20));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(280);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        contentTable.getColumnModel().getColumn(5).setMinWidth(20);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        //calculate import for each article
        calculateEachArticlePrice(1,4,5);
        
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        double total = 0;
        //calculate Total
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            total += Double.parseDouble(contentTable.getModel().getValueAt(i,5).toString());
        }
        totalAmount.setText(String.valueOf(total));
        sonLbl = new JLabel("Son:");
        declarate = new JTextField();
        bsLbl = new JLabel("Bolivianos");
        jButton1 = new JButton();
        jButton1.setText("PRINT");

        getContentPane().add(title, new AbsoluteConstraints(270, 30, 200, 30));
        getContentPane().add(datePicker, new AbsoluteConstraints(300, 80, 150, 30));
        getContentPane().add(idShower, new AbsoluteConstraints(500, 80, 160, 30));
        getContentPane().add(nameLbl, new AbsoluteConstraints(40, 120, 140, 30));
        getContentPane().add(clientName, new AbsoluteConstraints(100, 120, 350, 30));
        getContentPane().add(nitLbl, new AbsoluteConstraints(460, 120, 60, 30));
        getContentPane().add(clientNit, new AbsoluteConstraints(510, 120, 150, 30));
        getContentPane().add(new JScrollPane(contentTable), new AbsoluteConstraints(30, 160, 640, 200));
        getContentPane().add(sonLbl, new AbsoluteConstraints(40, 390, 50, 30));
        getContentPane().add(declarate, new AbsoluteConstraints(70, 390, 350, 30));
        getContentPane().add(totalLbl, new AbsoluteConstraints(540, 360, 50, 30));
        getContentPane().add(totalAmount, new AbsoluteConstraints(570, 360, 100, 30));
        getContentPane().add(bsLbl, new AbsoluteConstraints(425, 390, 70, 30));
        getContentPane().add(jButton1, new AbsoluteConstraints(580, 420, -1, 30));
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
            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
