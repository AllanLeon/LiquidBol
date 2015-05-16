package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import com.liquidbol.model.Company;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.services.CompanyServices;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class LoginForm extends JFrame {
    
    protected static MainMenuForm mm;
    protected static LoginForm LF;
    
    private JPanel contentPane;
    private JButton submitBtn;
    private JLabel bgLabel;
    private JTextField idTextField;
    private JTextField passTextField;
    private CompanyServices companyServices;

    public LoginForm() {
        UIStyle sty = new UIStyle();
        companyServices = new CompanyServices();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 400, 250);
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/com/liquidbol/images/logo2.jpg"));
            Image dimg = img.getScaledInstance(bgLabel.getWidth(), bgLabel.getHeight(), Image.SCALE_SMOOTH);
            bgLabel.setIcon(new ImageIcon(dimg));
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        bgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        idTextField = new JTextField();
        passTextField = new JTextField();
        submitBtn = new JButton();
        submitBtn.setText("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticate();
            }
        });
        
        idTextField.setBounds(90, 160, 200, 30);
        passTextField.setBounds(90, 190, 200, 30);
        submitBtn.setBounds(300, 160, 50, 30);
        
        contentPane.add(idTextField);
        contentPane.add(passTextField);
        contentPane.add(submitBtn);
        contentPane.add(bgLabel);
    }
    
    private void autenticate() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            String pass = passTextField.getText();
            Company.setLoggedEmployee(companyServices.autenticateEmployee(id, pass));
            mm = new MainMenuForm();
            setVisible(false);
        } catch (OperationFailedException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de autenticacion", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Id de empleado incorrecto", "Error de autenticacion", JOptionPane.ERROR_MESSAGE);
        }
    }
}