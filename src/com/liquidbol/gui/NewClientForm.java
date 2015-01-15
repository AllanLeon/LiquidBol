package com.liquidbol.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class NewClientForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idShower;
    private JLabel nitLbl;
    private JTextField nitBox;
    private JLabel nameLbl;
    private JTextField clientName;
    private JLabel companyLbl;
    private JTextField clientCompany;
    private JCheckBox jCheckBox1;
    private JLabel addressLbl;
    private JTextField clientAddress;
    private JLabel phoneLbl;
    private JTextField clientPhone;
    private JLabel phoneLbl2;
    private JTextField clientPhone2;
    private JLabel emailLbl;
    private JTextField clientEmail;
    private JLabel clientPhoto;
    private JLabel companyPhoto;
    private JButton jButton1;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewClientForm().setVisible(true);
            }
        });
    }

    public NewClientForm() {
        setStyle();
        initComponents();
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
        
        title = new JLabel("NUEVO CLIENTE");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nitLbl = new JLabel("NIT/CI");
        nitBox = new JTextField();
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        companyLbl =  new JLabel("Empresa/Taller");
        clientCompany = new JTextField();
        jCheckBox1 = new JCheckBox("Ruta");
        addressLbl = new JLabel("Dirección");
        clientAddress = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();
        phoneLbl2 = new JLabel("Telf/Cel 2");
        clientPhone2 = new JTextField();
        emailLbl = new JLabel("Email");
        clientEmail = new JTextField();
        
        try {
            clientPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/chap.jpg"))));
           // clientPhoto.setHorizontalAlignment(SwingConstants.CENTER);
           // companyPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/chap.jpg"))));
           // companyPhoto.setHorizontalAlignment(SwingConstants.RIGHT);
        } catch (IOException ex) {
            Logger.getLogger(NewClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jButton1 = new JButton("Add");

        title.setBounds(120, 30, 350, 30);
        idShower.setBounds(350, 80, 150, 30);
        nitLbl.setBounds(80, 80, 70, 30);
        nitBox.setBounds(120, 80, 100, 30);
        nameLbl.setBounds(40, 120, 70, 30);
        clientName.setBounds(100, 120, 350, 30);
        companyLbl.setBounds(40, 160, 100, 30);
        clientCompany.setBounds(130, 160, 300, 30);
        jCheckBox1.setBounds(470, 210, 100, 30);
        addressLbl.setBounds(40, 210, 70, 30);
        clientAddress.setBounds(100, 210, 350, 30);
        phoneLbl.setBounds(50, 250, 70, 30);
        clientPhone.setBounds(100, 250, 150, 30);
        phoneLbl2.setBounds(270, 250, 70, 30);
        clientPhone2.setBounds(330, 250, 150, 30);
        emailLbl.setBounds(50, 290, 70, 30);
        clientEmail.setBounds(100, 290, 250, 30);
        clientPhoto.setBounds(75, 330, 100, 100);
        //companyPhoto.setBounds(75, 330, 100, 100);
        jButton1.setBounds(400, 380, 70, 30);
        
        getContentPane().add(title);
        getContentPane().add(idShower);
        getContentPane().add(nitLbl);
        getContentPane().add(nitBox);
        getContentPane().add(nameLbl);
        getContentPane().add(clientName);
        getContentPane().add(companyLbl);
        getContentPane().add(clientCompany);
        getContentPane().add(jCheckBox1);
        getContentPane().add(addressLbl);
        getContentPane().add(clientAddress);
        getContentPane().add(phoneLbl);
        getContentPane().add(clientPhone);
        getContentPane().add(phoneLbl2);
        getContentPane().add(clientPhone2);
        getContentPane().add(emailLbl);
        getContentPane().add(clientEmail);
        getContentPane().add(clientPhoto);
//        getContentPane().add(companyPhoto);
        getContentPane().add(jButton1);
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
