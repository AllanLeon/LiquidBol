package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.MagikarpScreen;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Item;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class ARForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idLbl;
    private Component ritemId;
    private JLabel clientLbl;
    private Component ritemClient;
    private JLabel descLbl;
    private Component ritemDesc;
    private JLabel brandLbl;
    private Component itemBrand;
    private JLabel madeLbl;
    private Component itemMade;
    private JLabel typeLbl;
    private Component itemType;
    private JLabel subtypeLbl;
    private Component itemSubtype;
    private JLabel obsLbl;
    private Component ritemObs;
    private JLabel costLbl;
    private Component itemCost;
    private JLabel ritemPhoto;
    private JButton submitBtn;
    private MouseListener ml;
    private JButton backBtn;
    private Object[] readItData;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JDatePickerImpl datePicker;
    private JLabel dateLbl;

    public ARForm(int state) {
        UIStyle sty = new UIStyle();
        switch (state) {
            case 1: //Add new rechargable item
                initComponents();
                setVisible(true);
                break;
            case 2: //show rechargeable item data
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            case 3: //edit rechargable item data
                initComponents();
                setVisible(true);
                break;
            default:
                initComponents();
                setVisible(true);
                break;
        }
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(550, 430);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVO ARTICULO RECARGABLE");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        clientLbl = new JLabel("Cliente");
        ritemClient = new JComboBox();
        idLbl = new JLabel("Codigo");
        ritemId = new JTextField();
        descLbl = new JLabel("Descripcion");
        ritemDesc = new JTextField();
        typeLbl = new JLabel("Tipo");
        jRadioButton1 = new JRadioButton("Cilindro");
        jRadioButton2 = new JRadioButton("Extintor");
        dateLbl = new JLabel("Vencimiento de garantía");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        obsLbl = new JLabel("Observación");
        ritemObs = new JTextField();

        try {
            ritemPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/upload.png"))));
            ritemPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(ARForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn = new JButton("Add");
         submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readIt();
                try {
                    saveIt(readItData);
                } catch (PersistenceException | ClassNotFoundException ex) {
                    Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Rechargble ttem added! \n Respect+");
                ListARForm larf = new ListARForm();
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListARForm larf = new ListARForm();
                dispose();
            }
        });
        
        title.setBounds(40, 30, 500, 30);
        clientLbl.setBounds(70, 90, 70, 30);
        ritemClient.setBounds(110, 90, 200, 30);
        idLbl.setBounds(340, 90, 70, 30);
        ritemId.setBounds(380, 90, 80, 30);
        descLbl.setBounds(40, 130, 70, 30);
        ritemDesc.setBounds(110, 130, 400, 30);
        typeLbl.setBounds(280, 180, 70, 30);
        jRadioButton1.setBounds(320, 170, 150, 30);
        jRadioButton2.setBounds(320, 190, 150, 30);
        dateLbl.setBounds(280, 220, 170, 30);
        datePicker.setBounds(280, 245, 150, 30);
        obsLbl.setBounds(280, 275, 100, 30);
        ritemObs.setBounds(280, 300, 220, 30);
        ritemPhoto.setBounds(80, 190, 150, 150);
        submitBtn.setBounds(360, 360, 100, 30);
        backBtn.setBounds(60, 360, 100, 30);
        
        contentPane.add(title);
        contentPane.add(clientLbl);
        contentPane.add(ritemClient);
        contentPane.add(idLbl);
        contentPane.add(ritemId);
        contentPane.add(descLbl);
        contentPane.add(ritemDesc);
        contentPane.add(typeLbl);
        contentPane.add(jRadioButton1);
        contentPane.add(jRadioButton2);
        contentPane.add(dateLbl);
        contentPane.add(datePicker);
        contentPane.add(obsLbl);
        contentPane.add(ritemObs);
        contentPane.add(ritemPhoto);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
        onMouseHover(ritemPhoto);
    }

    private void readIt() {
        String id = ((JTextField) ritemId).getText();
        String meas = ((JTextField) ritemClient).getText();
        String desc = ((JTextField) ritemDesc).getText();
        String brand = ((JTextField) itemBrand).getText();
        String made = ((JTextField) itemMade).getText();
        String type = ((JTextField) itemType).getText();
        String subtype = ((JTextField) itemSubtype).getText();
        double cost = Double.parseDouble(((JTextField) itemCost).getText());
        double price = Double.parseDouble(((JTextField) ritemObs).getText());
        if(1 == 0) {
            JOptionPane.showMessageDialog(this,"MISSING!","Missing some important data input!", JOptionPane.WARNING_MESSAGE);
        } else {
            readItData = new Object[] {id, meas, desc, brand, made, type, subtype, cost, price};
        }
    }

    private void saveIt(Object[] data) throws PersistenceException, ClassNotFoundException {
        Item temp = MagikarpScreen.compServ.createItem((String)data[0],(String)data[1],(String)data[2],(String)data[3],(String)data[4],
                (String)data[5],(String)data[6],(double)data[7],(double)data[8]);
        MagikarpScreen.compServ.saveItem(temp);
    }

    private void onMouseHover(JLabel lbl) {
        ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((Component) e.getSource()).removeMouseListener(this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg"));
                    fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
                    fc.showOpenDialog(fc);

                    File selectedIMG = fc.getSelectedFile();
                    if (selectedIMG != null) {
                        BufferedImage img = ImageIO.read(selectedIMG);
                        Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
                        lbl.setIcon(new ImageIcon(dimg));
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex + ""
                            + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lbl.setEnabled(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl.setEnabled(true);
            }
        };
        lbl.addMouseListener(ml);
    }

    private void convertToReadOnly() {    
        Icon temp = ritemPhoto.getIcon();
        contentPane.remove(ritemId);
        contentPane.remove(ritemClient);
        contentPane.remove(ritemDesc);
        contentPane.remove(itemBrand);
        contentPane.remove(itemMade);
        contentPane.remove(itemType);
        contentPane.remove(itemSubtype);
        contentPane.remove(itemCost);
        contentPane.remove(ritemObs);
        contentPane.remove(ritemPhoto);
        contentPane.remove(submitBtn);

        ritemId = new JLabel();
        ritemClient = new JLabel();
        ritemDesc = new JLabel();
        itemBrand = new JLabel();
        itemMade = new JLabel();
        itemType = new JLabel();
        itemSubtype = new JLabel();
        itemCost = new JLabel();
        ritemObs = new JLabel();
        ritemPhoto = new JLabel(temp);
        title.setText("VER ARTICULO"); //CHANGE!!!!

        ritemId.setFont(new Font("Arial", Font.PLAIN, 20));
        ritemClient.setFont(new Font("Arial", Font.PLAIN, 20));
        ritemDesc.setFont(new Font("Arial", Font.PLAIN, 20));
        itemBrand.setFont(new Font("Arial", Font.PLAIN, 20));
        itemMade.setFont(new Font("Arial", Font.PLAIN, 20));
        itemType.setFont(new Font("Arial", Font.PLAIN, 20));
        itemSubtype.setFont(new Font("Arial", Font.PLAIN, 20));
        itemCost.setFont(new Font("Arial", Font.PLAIN, 20));
        ritemObs.setFont(new Font("Arial", Font.PLAIN, 20));

        ritemId.setBounds(110, 80, 50, 30);
        ritemClient.setBounds(110, 120, 50, 30);
        ritemDesc.setBounds(110, 160, 400, 30);
        itemBrand.setBounds(80, 200, 180, 30);
        itemMade.setBounds(340, 200, 170, 30);
        itemType.setBounds(80, 240, 150, 30);
        itemSubtype.setBounds(320, 240, 150, 30);
        itemCost.setBounds(330, 300, 100, 30);
        ritemObs.setBounds(330, 340, 100, 30);
        ritemPhoto.setBounds(80, 300, 150, 150);

        contentPane.add(ritemId);
        contentPane.add(ritemClient);
        contentPane.add(ritemDesc);
        contentPane.add(itemBrand);
        contentPane.add(itemMade);
        contentPane.add(itemType);
        contentPane.add(itemSubtype);
        contentPane.add(itemCost);
        contentPane.add(ritemObs);
        contentPane.add(ritemPhoto);
    }
}