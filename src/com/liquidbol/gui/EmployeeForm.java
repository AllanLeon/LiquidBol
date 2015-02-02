package com.liquidbol.gui;

import com.liquidbol.addons.MagikarpScreen;
import com.liquidbol.addons.UIStyle;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Company;
import com.liquidbol.model.Employee;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.model.Store;
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
import javax.swing.JComboBox;
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
public class EmployeeForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel ciLbl;
    private Component employeeCI;
    private JLabel passLbl;
    private Component employeePass;
    private JLabel typeLbl;
    private Component employeeType;
    private JLabel nameLbl;
    private Component employeeName;
    private JLabel lnameLbl;
    private Component employeeLName;
    private JLabel addressLbl;
    private Component employeeAddress;
    private JLabel phoneLbl;
    private Component employeePhone;
    private JLabel phoneLbl2;
    private Component employeePhone2;
    private JLabel emailLbl;
    private Component employeeEmail;
    private JLabel employeePhoto;
    private JButton submitBtn;
    private MouseListener ml;
    private JButton backBtn;
    private Object[] readItData;
    
    public EmployeeForm(int state) {
        UIStyle sty = new UIStyle();
        switch (state) {
            case 1: //Add new employee
                initComponents();
                setVisible(true);
                break;
            case 2: //show employee data
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            case 3: //edit employee data
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
        setSize(550, 450);
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
        ciLbl = new JLabel("CI");
        employeeCI = new JTextField();
        passLbl = new JLabel("Contraseña");
        employeePass = new JTextField();
        typeLbl = new JLabel("Tipo");
        Object[] CBdata = new Object[] {"Rookie", "Midway", "Pro"};
        employeeType = new JComboBox(CBdata);
        nameLbl = new JLabel("Nombre(s)");
        employeeName = new JTextField();
        lnameLbl = new JLabel("Apellido(s)");
        employeeLName = new JTextField();
        addressLbl = new JLabel("Dirección");
        employeeAddress = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        employeePhone = new JTextField();
        phoneLbl2 = new JLabel("Telf/Cel 2");
        employeePhone2 = new JTextField();
        emailLbl = new JLabel("Email");
        employeeEmail = new JTextField();

        try {
            employeePhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/weld.jpg"))));
            employeePhoto.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn = new JButton("Add");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readIt();
                try {
                    saveIt(readItData);
                } catch (PersistenceException | ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Employee added! \n Respect+");
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListEmployeesForm lef = new ListEmployeesForm();
                dispose();
            }
        });

        title.setBounds(100, 30, 400, 30);
        ciLbl.setBounds(40, 80, 30, 30);
        employeeCI.setBounds(60, 80, 100, 30);
        passLbl.setBounds(180, 80, 70, 30);
        employeePass.setBounds(250, 80, 100, 30);
        typeLbl.setBounds(390, 80, 50, 30);
        employeeType.setBounds(420, 80, 100, 30);
        nameLbl.setBounds(40, 120, 70, 30);
        employeeName.setBounds(100, 120, 160, 30);
        lnameLbl.setBounds(290, 120, 70, 30);
        employeeLName.setBounds(350, 120, 160, 30);
        addressLbl.setBounds(40, 170, 70, 30);
        employeeAddress.setBounds(100, 170, 350, 30);
        phoneLbl.setBounds(50, 210, 70, 30);
        employeePhone.setBounds(100, 210, 150, 30);
        phoneLbl2.setBounds(270, 210, 70, 30);
        employeePhone2.setBounds(330, 210, 150, 30);
        emailLbl.setBounds(50, 250, 70, 30);
        employeeEmail.setBounds(100, 250, 250, 30);
        employeePhoto.setBounds(210, 290, 100, 100);
        submitBtn.setBounds(400, 340, 70, 30);
        backBtn.setBounds(50, 340, 70, 30);

        contentPane.add(title);
        contentPane.add(ciLbl);
        contentPane.add(employeeCI);
        contentPane.add(passLbl);
        contentPane.add(employeePass);
        contentPane.add(typeLbl);
        contentPane.add(employeeType);
        contentPane.add(nameLbl);
        contentPane.add(employeeName);
        contentPane.add(lnameLbl);
        contentPane.add(employeeLName);
        contentPane.add(addressLbl);
        contentPane.add(employeeAddress);
        contentPane.add(phoneLbl);
        contentPane.add(employeePhone);
        contentPane.add(phoneLbl2);
        contentPane.add(employeePhone2);
        contentPane.add(emailLbl);
        contentPane.add(employeeEmail);
        contentPane.add(employeePhoto);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
        onMouseHover(employeePhoto);
    }

    private void readIt() {
        int id = Integer.parseInt(((JTextField) employeeCI).getText());
        String fname = ((JTextField) employeeName).getText();
        String lname = ((JTextField) employeeLName).getText();
        String adrs = ((JTextField) employeeAddress).getText();
        int telf1 = Integer.parseInt(((JTextField) employeePhone).getText());
        int telf2 = Integer.parseInt(((JTextField) employeePhone2).getText());
        String mail = ((JTextField) employeeEmail).getText();
        String pass = ((JTextField) employeePass).getText();
        String type = ((JComboBox) employeeType).getSelectedItem().toString();
        if(1 == 0) {
            JOptionPane.showMessageDialog(this,"WARNING.","Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            readItData = new Object[] {id, fname, lname, adrs, telf1, telf2, mail, pass, type};
        }
    }

    private void saveIt(Object[] data) throws PersistenceException, ClassNotFoundException {
        Employee temp = MagikarpScreen.storeServ.createEmployee((int)data[0],(String)data[1],(String)data[2],(String)data[3],
                    (int)data[4],(int)data[5],(String)data[6],(String)data[7],(String)data[8]);
        Store temp1;
        try {
            temp1 = Company.findStoreById(1);
            MagikarpScreen.storeServ.addEmployeeToStore(temp, temp1);
        } catch (OperationFailedException ex) {
            Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, null, ex);
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
        Icon temp = employeePhoto.getIcon();
        contentPane.remove(employeeCI);
        contentPane.remove(employeePass);
        contentPane.remove(employeeType);
        contentPane.remove(employeeName);
        contentPane.remove(employeeLName);
        contentPane.remove(employeeAddress);
        contentPane.remove(employeePhone);
        contentPane.remove(employeePhone2);
        contentPane.remove(employeeEmail);
        contentPane.remove(employeePhoto);
        contentPane.remove(submitBtn);

        employeeCI = new JLabel();
        employeePass = new JLabel();
        employeeType = new JLabel();
        employeeName = new JLabel();
        employeeLName = new JLabel();
        employeeAddress = new JLabel();
        employeePhone = new JLabel();
        employeePhone2 = new JLabel();
        employeeEmail = new JLabel();
        employeePhoto = new JLabel(temp);
        title.setText("VER EMPLEADO"); //CHANGE!!!!

        employeeCI.setFont(new Font("Arial", Font.PLAIN, 20));
        employeePass.setFont(new Font("Arial", Font.PLAIN, 20));
        employeeType.setFont(new Font("Arial", Font.PLAIN, 20));
        employeeName.setFont(new Font("Arial", Font.PLAIN, 20));
        employeeLName.setFont(new Font("Arial", Font.PLAIN, 20));
        employeeAddress.setFont(new Font("Arial", Font.PLAIN, 20));
        employeePhone.setFont(new Font("Arial", Font.PLAIN, 20));
        employeePhone2.setFont(new Font("Arial", Font.PLAIN, 20));
        employeeEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        
        employeeCI.setBounds(60, 80, 100, 30);
        employeePass.setBounds(250, 80, 100, 30);
        employeeType.setBounds(420, 80, 100, 30);
        employeeName.setBounds(100, 120, 160, 30);
        employeeLName.setBounds(350, 120, 160, 30);
        employeeAddress.setBounds(100, 170, 350, 30);
        employeePhone.setBounds(100, 210, 150, 30);
        employeePhone2.setBounds(330, 210, 150, 30);
        employeeEmail.setBounds(100, 250, 250, 30);
        employeePhoto.setBounds(210, 290, 100, 100);
                
        contentPane.add(employeeCI);
        contentPane.add(employeePass);
        contentPane.add(employeeType);
        contentPane.add(employeeName);
        contentPane.add(employeeLName);
        contentPane.add(employeeAddress);
        contentPane.add(employeePhone);
        contentPane.add(employeePhone2);
        contentPane.add(employeeEmail);
        contentPane.add(employeePhoto);
    }
}