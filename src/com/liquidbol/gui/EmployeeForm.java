package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class EmployeeForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private Component datePicker;
    private JLabel nitLbl;
    private Component nitBox;
    private JLabel nameLbl;
    private Component clientName;
    private JLabel lnameLbl;
    private Component clientLName;
    private JLabel addressLbl;
    private Component clientAddress;
    private JLabel phoneLbl;
    private Component clientPhone;
    private JLabel phoneLbl2;
    private Component clientPhone2;
    private JLabel emailLbl;
    private Component clientEmail;
    private JLabel clientPhoto;
    private JButton submitBtn;
    private MouseListener ml;
    private JButton backBtn;
    
    public EmployeeForm(int state) {
        switch (state) {
            case 1: //Add new employee
                setStyle();
                initComponents();
                setVisible(true);
                break;
            case 2: //show employee data
                setStyle();
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            case 3: //edit employee data
                setStyle();
                initComponents();
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
        setSize(550, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVO EMPLEADO");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        nitLbl = new JLabel("NIT/CI");
        nitBox = new JTextField();
        nameLbl = new JLabel("Nombre(s)");
        clientName = new JTextField();
        lnameLbl = new JLabel("Apellido(s)");
        clientLName = new JTextField();
        addressLbl = new JLabel("Dirección");
        clientAddress = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();
        phoneLbl2 = new JLabel("Telf/Cel 2");
        clientPhone2 = new JTextField();
        emailLbl = new JLabel("Email");
        clientEmail = new JTextField();

        try {
            clientPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/weld.jpg"))));
            clientPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn = new JButton("Add");
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListEmployeesForm lef = new ListEmployeesForm();
                dispose();
            }
        });

        title.setBounds(100, 30, 400, 30);
        datePicker.setBounds(160, 80, 140, 30);
        nitLbl.setBounds(80, 120, 70, 30);
        nitBox.setBounds(120, 120, 100, 30);
        nameLbl.setBounds(40, 160, 70, 30);
        clientName.setBounds(100, 160, 160, 30);
        lnameLbl.setBounds(290, 160, 70, 30);
        clientLName.setBounds(350, 160, 160, 30);
        addressLbl.setBounds(40, 210, 70, 30);
        clientAddress.setBounds(100, 210, 350, 30);
        phoneLbl.setBounds(50, 250, 70, 30);
        clientPhone.setBounds(100, 250, 150, 30);
        phoneLbl2.setBounds(270, 250, 70, 30);
        clientPhone2.setBounds(330, 250, 150, 30);
        emailLbl.setBounds(50, 290, 70, 30);
        clientEmail.setBounds(100, 290, 250, 30);
        clientPhoto.setBounds(175, 330, 100, 100);
        submitBtn.setBounds(400, 380, 70, 30);
        backBtn.setBounds(50, 380, 70, 30);

        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(nitLbl);
        contentPane.add(nitBox);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(lnameLbl);
        contentPane.add(clientLName);
        contentPane.add(addressLbl);
        contentPane.add(clientAddress);
        contentPane.add(phoneLbl);
        contentPane.add(clientPhone);
        contentPane.add(phoneLbl2);
        contentPane.add(clientPhone2);
        contentPane.add(emailLbl);
        contentPane.add(clientEmail);
        contentPane.add(clientPhoto);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
        onMouseHover(clientPhoto);
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
        Icon temp = clientPhoto.getIcon();
        contentPane.remove(datePicker);
        contentPane.remove(nitBox);
        contentPane.remove(clientName);
        contentPane.remove(clientLName);
        contentPane.remove(clientAddress);
        contentPane.remove(clientPhone);
        contentPane.remove(clientPhone2);
        contentPane.remove(clientEmail);
        contentPane.remove(clientPhoto);
        contentPane.remove(submitBtn);

        datePicker = new JLabel();
        nitBox = new JLabel();
        clientName = new JLabel();
        clientLName = new JLabel();
        clientAddress = new JLabel();
        clientPhone = new JLabel();
        clientPhone2 = new JLabel();
        clientEmail = new JLabel();
        clientPhoto = new JLabel(temp);
        title.setText("VER EMPLEADO"); //CHANGE!!!!

        datePicker.setFont(new Font("Arial", Font.PLAIN, 20));
        nitBox.setFont(new Font("Arial", Font.PLAIN, 20));
        clientName.setFont(new Font("Arial", Font.PLAIN, 20));
        clientLName.setFont(new Font("Arial", Font.PLAIN, 20));
        clientAddress.setFont(new Font("Arial", Font.PLAIN, 20));
        clientPhone.setFont(new Font("Arial", Font.PLAIN, 20));
        clientPhone2.setFont(new Font("Arial", Font.PLAIN, 20));
        clientEmail.setFont(new Font("Arial", Font.PLAIN, 20));

        datePicker.setBounds(160, 80, 140, 30);
        nitBox.setBounds(120, 120, 100, 30);
        clientName.setBounds(100, 160, 160, 30);
        clientLName.setBounds(350, 160, 160, 30);
        clientAddress.setBounds(100, 210, 350, 30);
        clientPhone.setBounds(100, 250, 150, 30);
        clientPhone2.setBounds(330, 250, 150, 30);
        clientEmail.setBounds(100, 290, 250, 30);
        clientPhoto.setBounds(175, 330, 100, 100);
        
        contentPane.add(datePicker);
        contentPane.add(nitBox);
        contentPane.add(clientName);
        contentPane.add(clientLName);
        contentPane.add(clientAddress);
        contentPane.add(clientPhone);
        contentPane.add(clientPhone2);
        contentPane.add(clientEmail);
        contentPane.add(clientPhoto);
    }
}