package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.MultiLineCellRenderer;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.model.Bill;
import com.liquidbol.model.Client;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.StoreServices;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class ItemEstimateForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private Component datePicker;
    private JLabel idShower;
    private JLabel nameLbl;
    private Component clientName;
    private JTable contentTable;
    private JLabel totalLbl;
    private Component totalAmount;
    private JLabel compLbl;
    private Component clientComp;
    private final DecimalFormat df;
    private Component obsArea;
    private JLabel offerValLbl;
    private Component offerVal;
    private JButton backBtn;
    private JButton submitBtn;
    private final TableModel passed;
    private final Bill bill;
    private Client client;
    private final ClientServices clientServices;
    private final StoreServices storeServices;
    private final CompanyServices companyServices;
    private boolean isTMPassed = false;
/*    
    public ItemEstimateForm(int state) {
        this.df = new DecimalFormat("##.00");
        UIStyle sty = new UIStyle();
        flag = true;
        switch (state) {
            case 1: //Add/edit new itemestimate
                initComponents();
                setVisible(true);
                break;
            case 2: //show itemestimate data
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            default:
                initComponents();
                setVisible(true);
                break;
        }
    }
*/
    public ItemEstimateForm(TableModel tm, Bill bill) {
        isTMPassed = true;
        this.df = new DecimalFormat("##.00");
        UIStyle sty = new UIStyle();
        passed = tm;
        this.bill = bill;
        this.clientServices = new ClientServices();
        this.storeServices = new StoreServices();
        this.companyServices = new CompanyServices();
        initComponents();
        setVisible(true);
    }
  
    private void initComponents() {
        setTitle("Liquid");
        setSize(700, 550);
        setResizable(false);
        setLocationRelativeTo(null);
               
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
        contentPane.setLayout(null);
 
        UtilDateModel model = new UtilDateModel(bill.getDate());
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
        /*
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
        contentTable = new JTable(tempData, columnNames); */
        contentTable = new JTable(passed);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(270);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        contentTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        contentTable.setDefaultRenderer(contentTable.getColumnModel().getColumn(3).getClass(), new MultiLineCellRenderer());
        RowSorter<TableModel> sorter = new TableRowSorter<>(contentTable.getModel());
        contentTable.setRowSorter(sorter);
        JScrollPane tablesp = new JScrollPane(contentTable);

        totalLbl = new JLabel("Total");
        totalAmount = (JTextField) new JTextField(String.valueOf(bill.calculateTotalAmount()));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 16));      
                
        offerValLbl = new JLabel("Validez de la oferta:            días");
        offerVal = new JTextField();
        obsArea = new JTextArea("* Los precios incluyen Impuestos de Ley.\n" +
            "* Los precios incluyen descuentos por volumen.\n" +
            "* Pago al contado.\n" +
            "* Entrega del producto 24 hrs después de su orden de compra.\n" +
            "* Consultar disponibilidad de stock."
        );
        JScrollPane sp = new JScrollPane(obsArea); 
        submitBtn = new JButton("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Quote printed! \n Respect+");
                if(isTMPassed) {
                    LoginForm.LF.setVisible(true);
                } else {
                    ListItemEstimatesForm lief = new ListItemEstimatesForm();
                }
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isTMPassed) {
                    MainMenuForm.scf.setVisible(true);
                } else{
                    ListItemEstimatesForm lief = new ListItemEstimatesForm();
                }
                dispose();
            }
        });

        title.setBounds(280, 20, 500, 30);
        datePicker.setBounds(300, 70, 150, 30);
        idShower.setBounds(500, 120, 150, 30);
        nameLbl.setBounds(40, 110, 150, 30);
        clientName.setBounds(100, 110, 350, 30);
        compLbl.setBounds(40, 140, 150, 30);
        clientComp.setBounds(100, 140, 350, 30);
        tablesp.setBounds(30, 180, 640, 180);
        totalLbl.setBounds(540, 360, 50, 30);
        totalAmount.setBounds(570, 360, 100, 30);
        offerValLbl.setBounds(40, 360, 200, 30);
        offerVal.setBounds(150, 360, 30, 30);
        sp.setBounds(40, 390, 470, 70);
        submitBtn.setBounds(580, 410, 70, 30);
        backBtn.setBounds(50, 470, 70, 30);
        
        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(compLbl);
        contentPane.add(clientComp);
        contentPane.add(tablesp);
        contentPane.add(totalLbl);
        contentPane.add(totalAmount);
        contentPane.add(offerValLbl);
        contentPane.add(offerVal);
        contentPane.add(sp);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
    }
    
    private void convertToReadOnly() {        
        contentPane.remove(clientName);
        contentPane.remove(datePicker);
        contentPane.remove(totalAmount);
        contentPane.remove(clientComp);
        contentPane.remove(offerVal);
        contentPane.remove(submitBtn);

        title.setText("VER COTIZACION"); //CHANGE!!!!
        clientName = new JLabel();
        datePicker = new JLabel();
        totalAmount = new JLabel();
        clientComp = new JLabel();
        obsArea.setEnabled(false);
        offerVal = new JLabel();
        contentTable.setEnabled(false);
        
        clientName.setFont(new Font("Arial", Font.PLAIN, 20));
        datePicker.setFont(new Font("Arial", Font.PLAIN, 20));
        totalAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        clientComp.setFont(new Font("Arial", Font.PLAIN, 20));
        obsArea.setFont(new Font("Arial", Font.PLAIN, 20));
        offerVal.setFont(new Font("Arial", Font.PLAIN, 20));
        
        datePicker.setBounds(300, 70, 150, 30);
        clientName.setBounds(100, 110, 350, 30);
        clientComp.setBounds(100, 140, 350, 30);
        totalAmount.setBounds(570, 360, 100, 30);
        obsArea.setBounds(40, 390, 470, 70);
        offerVal.setBounds(150, 360, 30, 30);
        
        contentPane.add(clientName);
        contentPane.add(datePicker);
        contentPane.add(totalAmount);
        contentPane.add(clientComp);
        contentPane.add(obsArea);
        contentPane.add(offerVal);
    }
}