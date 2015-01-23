package com.liquidbol.gui;

import java.awt.Component;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class StoreForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private Component storeName;
    private JLabel addressLbl;
    private Component storeAddress;
    private JLabel phoneLbl;
    private Component storePhone;
    private JButton submitBtn;

    public StoreForm(int state) {
        switch (state) {
            case 1: //Add new store
                setStyle();
                initComponents();
                setVisible(true);
                break;
            case 2: //show store data
                setStyle();
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            case 3: //edit store data
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
        setSize(550, 340);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVA TIENDA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Nombre");
        storeName = new JTextField();
        addressLbl = new JLabel("Direccion");
        storeAddress = new JTextField();
        phoneLbl = new JLabel("Telefono");
        storePhone = new JTextField();
        submitBtn = new JButton("Add");

        title.setBounds(100, 30, 400, 30);
        idShower.setBounds(350, 90, 150, 30);
        nameLbl.setBounds(80, 140, 70, 30);
        storeName.setBounds(140, 140, 150, 30);
        addressLbl.setBounds(80, 190, 70, 30);
        storeAddress.setBounds(140, 190, 240, 30);
        phoneLbl.setBounds(80, 240, 50, 30);
        storePhone.setBounds(140, 240, 120, 30);
        submitBtn.setBounds(370, 250, 100, 30);

        contentPane.add(title);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(storeName);
        contentPane.add(addressLbl);
        contentPane.add(storeAddress);
        contentPane.add(phoneLbl);
        contentPane.add(storePhone);
        contentPane.add(submitBtn);
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
        
        contentPane.remove(storeName);
        contentPane.remove(storeAddress);
        contentPane.remove(storePhone);
        contentPane.remove(submitBtn);

        storeName = new JLabel();
        storeAddress = new JLabel();
        storePhone = new JLabel();
        title.setText("VER TIENDA"); //CHANGE!!!!

        storeName.setFont(new Font("Arial", Font.PLAIN, 20));
        storeAddress.setFont(new Font("Arial", Font.PLAIN, 20));
        storePhone.setFont(new Font("Arial", Font.PLAIN, 20));

        storeName.setBounds(140, 140, 150, 30);
        storeAddress.setBounds(140, 190, 240, 30);
        storePhone.setBounds(140, 240, 120, 30);

        contentPane.add(storeName);
        contentPane.add(storeAddress);
        contentPane.add(storePhone);
    }
}
