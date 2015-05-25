package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.MagikarpScreen;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.RechargeableItem;
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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
    private JComboBox ritemClient;
    private JLabel descLbl;
    private Component ritemDesc;
    private JLabel typeLbl;
    private JRadioButton isCylinderRB;
    private JRadioButton isExtinguisherRB;
    private JDatePickerImpl datePicker;
    private JLabel dateLbl;
    private JLabel obsLbl;
    private Component ritemObs;
    private JLabel ritemPhoto;
    private JButton submitBtn;
    private MouseListener ml;
    private JButton backBtn;
    private Object[] readItData;
    private JLabel capacityLbl;
    private JTextField ritemCapacity;
    private JComboBox ritemUnits;
    private List<Client> clients;

    public ARForm(int state) {
        UIStyle sty = new UIStyle();
        clients = new ArrayList<>(Company.getAllClients());
        switch (state) {
            case 1: //Add new rechargable item
                initComponents();
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

    public ARForm(Object[] data) {
        //Show data on read-only mode.
        UIStyle sty = new UIStyle();
        clients = new ArrayList<>(Company.getAllClients());
        initComponents();
        convertToReadOnly(data);
        setVisible(true);
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
        try {
            ritemClient.setModel(new DefaultComboBoxModel(loadClientNames()));
        } catch (PersistenceException | ClassNotFoundException ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        idLbl = new JLabel("Codigo");
        ritemId = new JTextField();
        descLbl = new JLabel("Descripcion");
        ritemDesc = new JTextField();
        typeLbl = new JLabel("Tipo");
        isCylinderRB = new JRadioButton("Cilindro");
        isCylinderRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] unitContent = {"Mts3","Kg"};
                if(isCylinderRB.isSelected()){
                    ritemUnits.setModel(new DefaultComboBoxModel(unitContent));
                }
            }
        });
        isExtinguisherRB = new JRadioButton("Extintor");
        isExtinguisherRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] unitContent = {"Lb","Kg"};
                if(isExtinguisherRB.isSelected()){
                    ritemUnits.setModel(new DefaultComboBoxModel(unitContent));
                }
            }
        });
        dateLbl = new JLabel("Vencimiento de garantía");
        ButtonGroup bg = new ButtonGroup();
        bg.add(isCylinderRB);
        bg.add(isExtinguisherRB);
        capacityLbl = new JLabel("Capacidad");
        ritemCapacity = new JTextField();
        ritemUnits = new JComboBox();
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
                JOptionPane.showMessageDialog(null, "Rechargeable item added! \n Respect+");
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
        typeLbl.setBounds(270, 180, 70, 30);
        isCylinderRB.setBounds(300, 170, 100, 30);
        isExtinguisherRB.setBounds(300, 190, 100, 30);
        capacityLbl.setBounds(410, 160, 70, 30);
        ritemCapacity.setBounds(410, 185, 40, 30);
        ritemUnits.setBounds(450, 185, 60, 30);
        dateLbl.setBounds(270, 220, 170, 30);
        datePicker.setBounds(270, 245, 150, 30);
        obsLbl.setBounds(270, 275, 100, 30);
        ritemObs.setBounds(270, 300, 220, 30);
        ritemPhoto.setBounds(80, 180, 150, 150);
        submitBtn.setBounds(360, 350, 100, 30);
        backBtn.setBounds(60, 350, 100, 30);
        
        contentPane.add(title);
        contentPane.add(clientLbl);
        contentPane.add(ritemClient);
        contentPane.add(idLbl);
        contentPane.add(ritemId);
        contentPane.add(descLbl);
        contentPane.add(ritemDesc);
        contentPane.add(typeLbl);
        contentPane.add(isCylinderRB);
        contentPane.add(isExtinguisherRB);
        contentPane.add(capacityLbl);
        contentPane.add(ritemCapacity);
        contentPane.add(ritemUnits);
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
        try {
            String id = ((JTextField) ritemId).getText();
            String desc = ((JTextField) ritemDesc).getText();
            double capac = Double.parseDouble(((JTextField) ritemCapacity).getText());
            String unit = ritemUnits.getSelectedItem().toString();
            String type = "";
            if(isCylinderRB.isSelected()){
                type = isCylinderRB.getText();
            } else if (isExtinguisherRB.isSelected()) {
                type = isExtinguisherRB.getText();
            }
            
            String strDate = datePicker.getJFormattedTextField().getText();
            DateFormat localDF = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = null;
            utilDate = localDF.parse(strDate);
            Date sqlDate = new Date(utilDate.getTime());
            String obs = ((JTextField) ritemObs).getText();
            if(1 == 0) {
                JOptionPane.showMessageDialog(this,"MISSING!","Missing some important data input!", JOptionPane.WARNING_MESSAGE);
            } else {
                readItData = new Object[] {id, desc, capac, unit, type, sqlDate, obs};
            }
        } catch (ParseException ex) {
            Logger.getLogger(ARForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveIt(Object[] data) throws PersistenceException, ClassNotFoundException {
        RechargeableItem temp = MagikarpScreen.clientServ.createRechargeableItem((String)data[0],(String)data[1],(double)data[2],
                (String)data[3],(String)data[4],(Date)data[5],(String)data[6]);
        MagikarpScreen.clientServ.addRechargeableItemToClient(temp, clients.get(ritemClient.getSelectedIndex()));
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

    private void convertToReadOnly(Object[] d) {
        Icon temp = ritemPhoto.getIcon();
        ritemClient.setSelectedItem(d[2]);
        ritemClient.setEnabled(false);
        contentPane.remove(ritemId);
        contentPane.remove(ritemDesc);
        if(isCylinderRB.getText().equals((String) d[4]))
            isCylinderRB.setSelected(true);
        else if(isExtinguisherRB.getText().equals((String) d[4]))
            isExtinguisherRB.setSelected(true);            
        isCylinderRB.setEnabled(false);
        isExtinguisherRB.setEnabled(false);
        datePicker.getJFormattedTextField().setText((String) d[5]);
        datePicker.setTextEditable(false);
        contentPane.remove(ritemObs);
        contentPane.remove(ritemPhoto);
        contentPane.remove(submitBtn);

        ritemId = new JLabel((String) d[1]);
        ritemDesc = new JLabel((String) d[3]);
        ritemObs = new JLabel((String) d[6]);
        ritemPhoto = new JLabel(temp);
        title.setText("VER ARTICULO RECARGABLE"); //CHANGE!!!!

        ritemId.setFont(new Font("Arial", Font.PLAIN, 20));
        ritemDesc.setFont(new Font("Arial", Font.PLAIN, 20));
        ritemObs.setFont(new Font("Arial", Font.PLAIN, 20));

        ritemId.setBounds(380, 90, 80, 30);
        ritemDesc.setBounds(110, 130, 400, 30);
        ritemObs.setBounds(280, 300, 220, 30);
        ritemPhoto.setBounds(80, 190, 150, 150);
        
        contentPane.add(ritemId);
        contentPane.add(ritemDesc);
        contentPane.add(ritemObs);
        contentPane.add(ritemPhoto);
    }
    
    private Object[] loadClientNames() throws PersistenceException, ClassNotFoundException {
        List<String> data = new ArrayList<>();
        for (Client client : clients) {
            String name = client.getName() + " " + client.getLastname();
            data.add(name);
        }
        return data.toArray();
    }
}