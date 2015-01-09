package liquidbol.gui;

import java.awt.Component;
import liquidbol.addons.DateLabelFormatter;
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
public class ExtingForm extends JFrame {

    private JButton jButton1;
    private JDatePickerImpl datePicker;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTextField clientPhone;
    private JLabel phoneLbl;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JLabel compLbl;
    private JTextField clientComp;
    private JLabel quantLbl;
    private JTextField quantAmount;
    private JLabel ACLbl;
    private JTextField ACAmount;
    private JLabel balanceLbl;
    private JTextField balanceAmount;
    private JLabel dateLbl;
    private JTextField dateField;
    private JTextField hourField;
    private JLabel hourLbl;
    private JLabel amountLbl;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExtingForm().setVisible(true);
            }
        });
    }

    public ExtingForm() {
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
        title = new JLabel("NOTA DE RECEPCIÓN DE EXTINTORES");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();
        compLbl = new JLabel("Empresa");
        clientComp = new JTextField();

        String[] columnNames = {"Cant.",
            "No. Extintor",
            "Descripcion/Marca",
            "Tipo",
            "Capacidad",
            "Obs"
        };

        Object[][] tempData = {
            {1, "57885", "Extintor chino c/Pico", "PQ", "3 Lb", "En periodo de garantia"}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(5).setMinWidth(170);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        quantLbl = new JLabel("Cant. Total");
        quantAmount = new JTextField();
        amountLbl = new JLabel("Monto a pagar (Bs)");
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        ACLbl = new JLabel("A/C");
        ACAmount = new JTextField();
        balanceLbl = new JLabel("Saldo");
        balanceAmount = new JTextField();
        dateLbl = new JLabel("Fecha de entrega");
        dateField = new JTextField();
        hourLbl = new JLabel("Hora");
        hourField = new JTextField();
        jButton1 = new JButton();
        jButton1.setText("OK");

        getContentPane().add(title, new AbsoluteConstraints(120, 20, 500, 30));
        getContentPane().add(datePicker, new AbsoluteConstraints(300, 90, 150, 30));
        getContentPane().add(idShower, new AbsoluteConstraints(500, 90, 150, 30));
        getContentPane().add(nameLbl, new AbsoluteConstraints(40, 130, 150, 30));
        getContentPane().add(clientName, new AbsoluteConstraints(100, 130, 350, 30));
        getContentPane().add(compLbl, new AbsoluteConstraints(40, 160, 150, 30));
        getContentPane().add(clientComp, new AbsoluteConstraints(100, 160, 350, 30));
        getContentPane().add(phoneLbl, new AbsoluteConstraints(465, 130, 150, 30));
        getContentPane().add(clientPhone, new AbsoluteConstraints(510, 130, 150, 30));
        getContentPane().add(new JScrollPane(contentTable), new AbsoluteConstraints(30, 200, 640, 150));
        getContentPane().add(quantLbl, new AbsoluteConstraints(30, 350, 70, 30));
        getContentPane().add(quantAmount, new AbsoluteConstraints(90, 350, 50, 30));
        getContentPane().add(dateLbl, new AbsoluteConstraints(180, 350, 110, 30));
        getContentPane().add(dateField, new AbsoluteConstraints(180, 370, 90, 30));
        getContentPane().add(hourLbl, new AbsoluteConstraints(180, 390, 50, 30));
        getContentPane().add(hourField, new AbsoluteConstraints(180, 410, 90, 30));
        getContentPane().add(amountLbl, new AbsoluteConstraints(320, 370, 120, 30));
        getContentPane().add(totalLbl, new AbsoluteConstraints(430, 350, 50, 30));
        getContentPane().add(totalAmount, new AbsoluteConstraints(430, 370, 70, 30));
        getContentPane().add(ACLbl, new AbsoluteConstraints(500, 350, 50, 30));
        getContentPane().add(ACAmount, new AbsoluteConstraints(500, 370, 70, 30));
        getContentPane().add(balanceLbl, new AbsoluteConstraints(570, 350, 50, 30));
        getContentPane().add(balanceAmount, new AbsoluteConstraints(570, 370, 70, 30));
        getContentPane().add(jButton1, new AbsoluteConstraints(350, 430, -1, 30));
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
