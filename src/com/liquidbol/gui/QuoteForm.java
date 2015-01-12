package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.MultiLineCellRenderer;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
public class QuoteForm extends JFrame {

    private JButton jButton1;
    private JDatePickerImpl datePicker;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JLabel compLbl;
    private JTextField clientComp;
    private DecimalFormat df;
    private JTextArea obsArea;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuoteForm().setVisible(true);
            }
        });
    }

    public QuoteForm() {
        this.df = new DecimalFormat("##.00");
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
        title = new JLabel("COTIZACION");
        title.setFont(new Font("Arial", Font.PLAIN, 26));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        compLbl = new JLabel("Empresa");
        clientComp = new JTextField();

        String[] columnNames = {"Cod",
            "Cant.",
            "Unidad",
            "Descripcion",
            "Precio Unit.",
            "Precio"
        };

        Object[][] tempData = {
            {"S-00456", 24, "Par", "Guante Zorb-it Negro, Best 4540", 40.32, 0},
            {"S-00462", 6, "Par", "Guante de cuero T/Soldador", 75.00, 0},
            {"S-00447", 12, "Par", "Guante N-Dex Plus", 5.00, 0},
            {"S-00127", 30, "Pza.", "Casco H700 c/Susp. Rachet blanco", 82.00, 0},
            {"S-00290", 30, "Pza.", "Tapon de oido 1110 con cordon", 2.20, 0},
            {"S-00823", 30, "Pza.", "Lente claro anti rayas IJ-0204-1", 21.12, 0},
            {"S-00826", 30, "Pza.", "Lente oscuro anti rayas IJ-0204-2", 21.12, 0},
            {"S-00312", 30, "Pza.", "Overol 4510 descartable 3M talla XL", 46.00, 0},
            {"S-00726", 24, "Pza.", "Respirador contra polvos desechable 8210", 8.64, 0},
            {"S-00720", 6, "Pza.", "Respirador contra humos N95 con valvula 8515", 38.00, 0},
            {"S-00034", 30, "Pza.", "Mentonera universal para casco m/Libus", 25.00, 0},
            {"S-00512", 6, "Pza.", "Arnes de posicionamiento c/Anillo de espalda 10911", 572.00, 0},
            {"S-00549", 6, "Pza.", "Linea de vida 3570-0241 c/Amortig c/Gancho 2 3/4, estiramiento de 4 a 6''", 582.00, 0}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(270);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        contentTable.setDefaultRenderer(contentTable.getColumnModel().getColumn(3).getClass(), new MultiLineCellRenderer());

        //calculate import for each article
        calculateEachArticlePrice(1, 4, 5);

        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        totalAmount.setText(String.valueOf(calculateTotal()));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        
        obsArea = new JTextArea();
        obsArea.setText(
            "* Los precios incluyen Impuestos de Ley.\n" +
            "* Los precios incluyen descuentos por volumen.\n" +
            "* Pago al contado.\n" +
            "* Entrega del producto 24 hrs después de su orden de compra.\n" +
            "* Consultar disponibilidad de stock."
        );
        JScrollPane sp = new JScrollPane(obsArea); 
        jButton1 = new JButton();
        jButton1.setText("OK");

        getContentPane().add(title, new AbsoluteConstraints(280, 20, 500, 30));
        getContentPane().add(datePicker, new AbsoluteConstraints(300, 70, 150, 30));
        getContentPane().add(idShower, new AbsoluteConstraints(500, 120, 150, 30));
        getContentPane().add(nameLbl, new AbsoluteConstraints(40, 110, 150, 30));
        getContentPane().add(clientName, new AbsoluteConstraints(100, 110, 350, 30));
        getContentPane().add(compLbl, new AbsoluteConstraints(40, 140, 150, 30));
        getContentPane().add(clientComp, new AbsoluteConstraints(100, 140, 350, 30));
        getContentPane().add(new JScrollPane(contentTable), new AbsoluteConstraints(30, 180, 640, 180));
        getContentPane().add(totalLbl, new AbsoluteConstraints(540, 360, 50, 30));
        getContentPane().add(totalAmount, new AbsoluteConstraints(570, 360, 100, 30));
        getContentPane().add(sp, new AbsoluteConstraints(40, 390, 470, 70));
        getContentPane().add(jButton1, new AbsoluteConstraints(580, 410, 70, 30));
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

    public void calculateEachArticlePrice(int qValueCol, int upValueCol, int resValueCol) {
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            double quantity = Double.parseDouble(contentTable.getModel().getValueAt(i, qValueCol).toString());
            double unitPrice = Double.parseDouble(contentTable.getModel().getValueAt(i, upValueCol).toString());
            double calcdSubtotal = quantity * unitPrice;
            df = new DecimalFormat("##.00");
            String result = df.format(calcdSubtotal).replaceAll(",",".");
            contentTable.getModel().setValueAt(result, i, resValueCol);
        }
    }

    public String calculateTotal() {
        double total = 0;
        for (int i = 0; i < contentTable.getRowCount(); i++) {
            total += Double.parseDouble(contentTable.getModel().getValueAt(i, 5).toString());
        }
        df = new DecimalFormat("##.00");
        double rounded = (double) Math.round(total * 10) / 10;
        String result = df.format(rounded).replaceAll(",",".");
        return result;
    }
}
