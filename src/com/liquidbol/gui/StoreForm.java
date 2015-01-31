package com.liquidbol.gui;

import com.liquidbol.addons.MagikarpScreen;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Store;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private JTextField storeName;
    private JCheckBox routeCB;
    private JLabel addressLbl;
    private JTextField storeAddress;
    private JLabel phoneLbl;
    private JTextField storePhone;
    private JButton submitBtn;
    private JButton backBtn;
    private Object[] readItData;

    public StoreForm(int state) {
        setStyle();
        initComponents();
        setVisible(true);
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

        title = new JLabel("NUEVA TIENDA");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Nombre");
        storeName = new JTextField();
        addressLbl = new JLabel("Dirección");
        storeAddress = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        storePhone = new JTextField();

        submitBtn = new JButton("Add");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readIt();
                try {
                    saveIt(readItData);
                } catch (PersistenceException | ClassNotFoundException ex) {
                    Logger.getLogger(StoreForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Store added! \n Respect+");
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListStoresForm lsf = new ListStoresForm();
                dispose();
            }
        });

        title.setBounds(120, 30, 350, 30);
        idShower.setBounds(380, 70, 150, 30);
        nameLbl.setBounds(40, 110, 70, 30);
        storeName.setBounds(100, 110, 160, 30);
        addressLbl.setBounds(40, 150, 70, 30);
        storeAddress.setBounds(100, 150, 350, 30);
        phoneLbl.setBounds(50, 190, 70, 30);
        storePhone.setBounds(100, 190, 150, 30);
        submitBtn.setBounds(440, 240, 70, 30);
        backBtn.setBounds(50, 240, 70, 30);

        contentPane.add(title);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(storeName);
        contentPane.add(addressLbl);
        contentPane.add(storeAddress);
        contentPane.add(phoneLbl);
        contentPane.add(storePhone);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
    }

    private void readIt() {
        String fname = ((JTextField) storeName).getText();
        String adrs = ((JTextField) storeAddress).getText();
        int telf1 = Integer.parseInt(((JTextField) storePhone).getText());
        if (1 == 0) {
            JOptionPane.showMessageDialog(this, "WARNING.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            readItData = new Object[]{fname, adrs, telf1};
        }
    }

    private void saveIt(Object[] data) throws PersistenceException, ClassNotFoundException {
        Store temp = MagikarpScreen.compServ.createStore(0, (String) data[0], (String) data[1], (int) data[2]);
        MagikarpScreen.compServ.saveStore(temp);
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