package liquidbol.gui;

import liquidbol.addons.DateLabelFormatter;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
public class DRNoteForm extends JFrame {

    private JButton jButton1;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JDatePickerImpl datePicker;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DRNoteForm().setVisible(true);
            }
        });
    }
    private JTextField clientPhone;
    private JLabel phoneLbl;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;

    public DRNoteForm() {
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
        jRadioButton1 = new JRadioButton("Entrega");
        jRadioButton2 = new JRadioButton("Recepcion");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        title = new JLabel("NOTA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();

        String[] columnNames = {"Cod",
            "Cant.",
            "Unidad",
            "Descripcion",
            "Precio Unit.",
            "Precio"
        };

        Object[][] tempData = {
            {"00126", 20, "Kg.", "Electrodo 7018 1/8", 22.8, 456},
            {"00119", 20, "Kg.", "Electrodo 6013 1/8", 22.8, 456}
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
        
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        double total = 0;
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            total += Double.parseDouble(contentTable.getModel().getValueAt(i,5).toString());
        }

        totalAmount.setText(String.valueOf(total));

        
        jCheckBox1 = new JCheckBox("x Cancelar");
        jCheckBox2 = new JCheckBox("x Facturar");
        jButton1 = new JButton();
        jButton1.setText("OK");

        getContentPane().add(title, new AbsoluteConstraints(300, 50, 150, 30));
        getContentPane().add(datePicker, new AbsoluteConstraints(300, 120, 150, 30));
        getContentPane().add(idShower, new AbsoluteConstraints(500, 120, 150, 30));
        getContentPane().add(jRadioButton1, new AbsoluteConstraints(550, 60, 100, 30));
        getContentPane().add(jRadioButton2, new AbsoluteConstraints(550, 80, 100, 30));
        getContentPane().add(nameLbl, new AbsoluteConstraints(40, 160, 150, 30));
        getContentPane().add(clientName, new AbsoluteConstraints(100, 160, 350, 30));
        getContentPane().add(phoneLbl, new AbsoluteConstraints(465, 160, 150, 30));
        getContentPane().add(clientPhone, new AbsoluteConstraints(510, 160, 150, 30));
        getContentPane().add(new JScrollPane(contentTable), new AbsoluteConstraints(30, 210, 640, 150));
        getContentPane().add(totalLbl, new AbsoluteConstraints(540, 360, 50, 30));
        getContentPane().add(totalAmount, new AbsoluteConstraints(570, 360, 100, 30));
        getContentPane().add(jCheckBox1, new AbsoluteConstraints(250, 370, 100, 30));
        getContentPane().add(jCheckBox2, new AbsoluteConstraints(350, 370, 100, 30));
        getContentPane().add(jButton1, new AbsoluteConstraints(350, 420, -1, 30));
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
