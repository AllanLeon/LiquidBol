package com.liquidbol.gui;

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

/**
 * @author Franco
 */
public class SupplierForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private Component supplierName;
    private JLabel lnameLbl;
    private Component supplierLName;
    private JLabel companyLbl;
    private Component supplierCompany;
    private JLabel addressLbl;
    private Component supplierAddress;
    private JLabel cityLbl;
    private Component supplierCity;
    private JLabel phoneLbl;
    private Component supplierPhone;
    private JLabel phoneLbl2;
    private Component supplierPhone2;
    private JLabel emailLbl;
    private Component supplierEmail;
    private JLabel clientPhoto;
    private JButton submitBtn;
    private MouseListener ml;
    private JButton backBtn;

    public SupplierForm(int state) {
        switch(state){
            case 1: //Add new supplier
                setStyle();
                initComponents();
                setVisible(true);
                break;
            case 2: //show supplier data
                setStyle();
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            case 3: //edit supplier data
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
        title.setText("NUEVO PROVEEDOR");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Nombre(s)");
        supplierName = new JTextField();
        lnameLbl = new JLabel("Apellido(s)");
        supplierLName = new JTextField();
        companyLbl = new JLabel("Empresa");
        supplierCompany = new JTextField();
        addressLbl = new JLabel("Dirección");
        supplierAddress = new JTextField();
        cityLbl = new JLabel("Ciudad");
        supplierCity = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        supplierPhone = new JTextField();
        phoneLbl2 = new JLabel("Telf/Cel 2");
        supplierPhone2 = new JTextField();
        emailLbl = new JLabel("Email");
        supplierEmail = new JTextField();

        try {
            clientPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/weld.jpg"))));
            clientPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(SupplierForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn = new JButton("Add");
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSuppliersForm lsf = new ListSuppliersForm();
                dispose();
            }
        });
        
        title.setBounds(120, 30, 350, 30);
        idShower.setBounds(350, 80, 150, 30);
        nameLbl.setBounds(40, 120, 70, 30);
        supplierName.setBounds(100, 120, 160, 30);
        lnameLbl.setBounds(290, 120, 70, 30);
        supplierLName.setBounds(350, 120, 160, 30);
        companyLbl.setBounds(40, 160, 100, 30);
        supplierCompany.setBounds(100, 160, 300, 30);
        addressLbl.setBounds(40, 210, 70, 30);
        supplierAddress.setBounds(100, 210, 350, 30);
        cityLbl.setBounds(40, 250, 70, 30);
        supplierCity.setBounds(100, 250, 200, 30);
        phoneLbl.setBounds(50, 290, 70, 30);
        supplierPhone.setBounds(100, 290, 150, 30);
        phoneLbl2.setBounds(270, 290, 70, 30);
        supplierPhone2.setBounds(330, 290, 150, 30);
        emailLbl.setBounds(50, 330, 70, 30);
        supplierEmail.setBounds(100, 330, 250, 30);
        clientPhoto.setBounds(400, 330, 100, 100);
        submitBtn.setBounds(175, 400, 100, 30);
        backBtn.setBounds(50, 400, 70, 30);

        contentPane.add(title);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(supplierName);
        contentPane.add(lnameLbl);
        contentPane.add(supplierLName);
        contentPane.add(companyLbl);
        contentPane.add(supplierCompany);
        contentPane.add(addressLbl);
        contentPane.add(supplierAddress);
        contentPane.add(cityLbl);
        contentPane.add(supplierCity);
        contentPane.add(phoneLbl);
        contentPane.add(supplierPhone);
        contentPane.add(phoneLbl2);
        contentPane.add(supplierPhone2);
        contentPane.add(emailLbl);
        contentPane.add(supplierEmail);
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
        contentPane.remove(supplierName);
        contentPane.remove(supplierLName);
        contentPane.remove(supplierCompany);
        contentPane.remove(supplierAddress);
        contentPane.remove(supplierCity);
        contentPane.remove(supplierPhone);
        contentPane.remove(supplierPhone2);
        contentPane.remove(supplierEmail);
        contentPane.remove(clientPhoto);
        contentPane.remove(submitBtn);

        supplierName = new JLabel();
        supplierLName = new JLabel();
        supplierCompany = new JLabel();
        supplierAddress = new JLabel();
        supplierCity = new JLabel();
        supplierPhone = new JLabel();
        supplierPhone2 = new JLabel();
        supplierEmail = new JLabel();
        clientPhoto = new JLabel(temp);
        title.setText("VER PROVEEDOR"); //CHANGE!!!!

        supplierName.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierLName.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierCompany.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierAddress.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierCity.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierPhone.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierPhone2.setFont(new Font("Arial", Font.PLAIN, 20));
        supplierEmail.setFont(new Font("Arial", Font.PLAIN, 20));

        supplierName.setBounds(100, 120, 160, 30);
        supplierLName.setBounds(350, 120, 160, 30);
        supplierCompany.setBounds(100, 160, 300, 30);
        supplierAddress.setBounds(100, 210, 350, 30);
        supplierCity.setBounds(100, 250, 200, 30);
        supplierPhone.setBounds(100, 290, 150, 30);
        supplierPhone2.setBounds(330, 290, 150, 30);
        supplierEmail.setBounds(100, 330, 250, 30);
        clientPhoto.setBounds(400, 330, 100, 100);
        
        contentPane.add(supplierName);
        contentPane.add(supplierLName);
        contentPane.add(supplierCompany);
        contentPane.add(supplierAddress);
        contentPane.add(supplierCity);
        contentPane.add(supplierPhone);
        contentPane.add(supplierPhone2);
        contentPane.add(supplierEmail);
        contentPane.add(clientPhoto);
    }
}
