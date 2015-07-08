package com.liquidbol.gui;

import com.liquidbol.addons.MagikarpScreen;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Client;
import com.liquidbol.model.Company;
import com.liquidbol.model.OperationFailedException;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Franco
 */
public class ClientForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idShower;
    private JLabel nitLbl;
    public Component nitBox;
    private JLabel nickLbl;
    private Component nickBox;
    private JLabel nameLbl;
    private Component clientName;
    private JLabel lnameLbl;
    private Component clientLName;
    private JLabel companyLbl;
    private Component clientCompany;
    private JCheckBox routeCB;
    private JLabel addressLbl;
    private Component clientAddress;
    private JLabel phoneLbl;
    private Component clientPhone;
    private JLabel phoneLbl2;
    private Component clientPhone2;
    private JLabel emailLbl;
    private Component clientEmail;
    private JLabel clientPhoto;
    private JLabel companyPhoto;
    private JButton submitBtn;
    private MouseListener ml;
    private JButton backBtn;
    private Object[] readItData;
    private boolean isExpress = false;

    public ClientForm(int state) {
        UIStyle sty = new UIStyle();
        switch (state) {
            case 1: //Add new client
                initComponents();
                setVisible(true);
                break;
            case 3: //edit client data
                initComponents();
                setVisible(true);
                break;
            case 4: //fastNewClient
                initComponents();
                setVisible(true);
                isExpress = true;
                break;
            default:
                initComponents();
                setVisible(true);
                break;
        }
    }

    public ClientForm(String clientnit) {
        //Show data on read-only mode.
        UIStyle sty = new UIStyle();
        initComponents();
        convertToReadOnly(clientnit);
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(550, 540);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVO CLIENTE");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nitLbl = new JLabel("NIT/CI");
        nitBox = new JTextField();
        nickLbl = new JLabel("FACTURA");
        nickBox = new JTextField();
        nameLbl = new JLabel("Nombre(s)");
        clientName = new JTextField();
        lnameLbl = new JLabel("Apellido(s)");
        clientLName = new JTextField();
        companyLbl = new JLabel("Empresa/Taller");
        clientCompany = new JTextField();
        routeCB = new JCheckBox("Ruta");
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
            companyPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/chap.jpg"))));
            companyPhoto.setHorizontalAlignment(SwingConstants.LEFT);
        } catch (IOException ex) {
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
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
                JOptionPane.showMessageDialog(null, "Client added! \n Respect+");
                if (!isExpress) {
                    ListClientsForm lcf = new ListClientsForm();
                }
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListClientsForm lcf = new ListClientsForm();
                dispose();
            }
        });

        title.setBounds(120, 30, 350, 30);
        idShower.setBounds(380, 70, 150, 30);
        nitLbl.setBounds(80, 110, 70, 30);
        nitBox.setBounds(120, 110, 100, 30);
        nickLbl.setBounds(250, 110, 70, 30);
        nickBox.setBounds(310, 110, 100, 30);
        nameLbl.setBounds(40, 150, 70, 30);
        clientName.setBounds(100, 150, 160, 30);
        lnameLbl.setBounds(290, 150, 70, 30);
        clientLName.setBounds(350, 150, 160, 30);
        companyLbl.setBounds(40, 200, 100, 30);
        clientCompany.setBounds(130, 200, 300, 30);
        routeCB.setBounds(470, 240, 100, 30);
        addressLbl.setBounds(40, 240, 70, 30);
        clientAddress.setBounds(100, 240, 350, 30);
        phoneLbl.setBounds(50, 280, 70, 30);
        clientPhone.setBounds(100, 280, 150, 30);
        phoneLbl2.setBounds(270, 280, 70, 30);
        clientPhone2.setBounds(330, 280, 150, 30);
        emailLbl.setBounds(50, 320, 70, 30);
        clientEmail.setBounds(100, 320, 250, 30);
        clientPhoto.setBounds(140, 360, 100, 100);
        companyPhoto.setBounds(260, 360, 150, 100);
        submitBtn.setBounds(440, 420, 70, 30);
        backBtn.setBounds(50, 420, 70, 30);

        contentPane.add(title);
        contentPane.add(idShower);
        contentPane.add(nitLbl);
        contentPane.add(nitBox);
        contentPane.add(nickLbl);
        contentPane.add(nickBox);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(lnameLbl);
        contentPane.add(clientLName);
        contentPane.add(companyLbl);
        contentPane.add(clientCompany);
        contentPane.add(routeCB);
        contentPane.add(addressLbl);
        contentPane.add(clientAddress);
        contentPane.add(phoneLbl);
        contentPane.add(clientPhone);
        contentPane.add(phoneLbl2);
        contentPane.add(clientPhone2);
        contentPane.add(emailLbl);
        contentPane.add(clientEmail);
        contentPane.add(clientPhoto);
        contentPane.add(companyPhoto);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
        onMouseHover(clientPhoto);
        onMouseHover(companyPhoto);
    }

    private void readIt() {
        String fname = ((JTextField) clientName).getText();
        String lname = ((JTextField) clientLName).getText();
        int nit = Integer.parseInt(((JTextField) nitBox).getText());
        String nick = ((JTextField) nickBox).getText();
        String adrs = ((JTextField) clientAddress).getText();
        int telf1 = Integer.parseInt(((JTextField) clientPhone).getText());
        int telf2 = Integer.parseInt(((JTextField) clientPhone2).getText());
        String mail = ((JTextField) clientEmail).getText();
        String emp = ((JTextField) clientCompany).getText();
        boolean route = routeCB.isSelected();
        if (1 == 0) {
            JOptionPane.showMessageDialog(this, "WARNING.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            readItData = new Object[]{fname, lname, nit, nick, adrs, telf1, telf2, mail, emp, route};
        }
    }

    private void saveIt(Object[] data) throws PersistenceException, ClassNotFoundException {
        Client temp = MagikarpScreen.compServ.createClient(0, (String) data[0], (String) data[1], (int) data[2], (String) data[3],
                (String) data[4], (int) data[5], (int) data[6], (String) data[7], (String) data[8], (boolean) data[9]);
        MagikarpScreen.compServ.saveClient(temp);
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

    private void convertToReadOnly(String clientnit) {
        try {
            Client cli = Company.findClientByNit(Integer.parseInt(clientnit));
            Icon temp = clientPhoto.getIcon();
            Icon temp2 = companyPhoto.getIcon();
            contentPane.remove(nitBox);
            contentPane.remove(nickBox);
            contentPane.remove(clientName);
            contentPane.remove(clientLName);
            contentPane.remove(clientCompany);
            contentPane.remove(clientAddress);
            contentPane.remove(routeCB);
            contentPane.remove(clientPhone);
            contentPane.remove(clientPhone2);
            contentPane.remove(clientEmail);
            contentPane.remove(clientPhoto);
            contentPane.remove(companyPhoto);
            contentPane.remove(submitBtn);

            idShower.setText("Nº " + cli.getId());
            nitBox = new JLabel(String.valueOf(cli.getNit()));
            nickBox = new JLabel(cli.getBillName());
            clientName = new JLabel(cli.getName());
            clientLName = new JLabel(cli.getLastname());
            clientCompany = new JLabel(cli.getCompanyName());
            clientAddress = new JLabel(cli.getAddress());
            JLabel routeLbl = new JLabel("Ruta ");
            JLabel readRoute = new JLabel();
            if (cli.isRoute()) {
                readRoute.setText("Si");
            } else {
                readRoute.setText("No");
            }
            clientPhone = new JLabel(String.valueOf(cli.getPhone()));
            clientPhone2 = new JLabel(String.valueOf(cli.getPhone2()));
            clientEmail = new JLabel(cli.getEmail());
            clientPhoto = new JLabel(temp);
            companyPhoto = new JLabel(temp2);
            JButton cxc = new JButton("CXC");
            JButton ar = new JButton("Art. Recargables");
            title.setText("VER CLIENTE"); //CHANGE!!!!

            nitBox.setFont(new Font("Arial", Font.PLAIN, 20));
            nickBox.setFont(new Font("Arial", Font.PLAIN, 20));
            clientName.setFont(new Font("Arial", Font.PLAIN, 20));
            clientLName.setFont(new Font("Arial", Font.PLAIN, 20));
            clientCompany.setFont(new Font("Arial", Font.PLAIN, 20));
            clientAddress.setFont(new Font("Arial", Font.PLAIN, 20));
            readRoute.setFont(new Font("Arial", Font.PLAIN, 20));
            clientPhone.setFont(new Font("Arial", Font.PLAIN, 20));
            clientPhone2.setFont(new Font("Arial", Font.PLAIN, 20));
            clientEmail.setFont(new Font("Arial", Font.PLAIN, 20));

            nitBox.setBounds(120, 110, 100, 30);
            nickBox.setBounds(310, 110, 100, 30);
            clientName.setBounds(100, 150, 160, 30);
            clientLName.setBounds(350, 150, 160, 30);
            clientCompany.setBounds(130, 200, 300, 30);
            routeLbl.setBounds(440, 240, 50, 30);
            readRoute.setBounds(470, 240, 40, 30);
            clientAddress.setBounds(100, 240, 350, 30);
            clientPhone.setBounds(100, 280, 150, 30);
            clientPhone2.setBounds(330, 280, 150, 30);
            clientEmail.setBounds(100, 320, 250, 30);
            clientPhoto.setBounds(75, 360, 100, 100);
            companyPhoto.setBounds(200, 360, 150, 100);
            cxc.setBounds(380, 380, 120, 30);
            ar.setBounds(380, 420, 120, 30);
            backBtn.setBounds(50, 470, 70, 30);

            contentPane.add(nitBox);
            contentPane.add(nickBox);
            contentPane.add(clientName);
            contentPane.add(clientLName);
            contentPane.add(clientCompany);
            contentPane.add(routeLbl);
            contentPane.add(readRoute);
            contentPane.add(clientAddress);
            contentPane.add(clientPhone);
            contentPane.add(clientPhone2);
            contentPane.add(clientEmail);
            contentPane.add(clientPhoto);
            contentPane.add(companyPhoto);
            contentPane.add(cxc);
            contentPane.add(ar);
        } catch (OperationFailedException ex) {
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
