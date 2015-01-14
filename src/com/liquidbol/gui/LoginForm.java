package com.liquidbol.gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class LoginForm extends JFrame {

    private JPanel contentPane;
    private JButton jButton1;
    private JLabel jLabel1;
    private JTextField jTextField1;
    
    public static void main(String args[]) { 
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
    
    public LoginForm() {
        setStyle();
        initComponents();
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(400,250);
        setResizable(false);
        setLocationRelativeTo(null);
                
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
        contentPane.setLayout(null);
        
        jTextField1 = new JTextField();
        jButton1 = new JButton();
        jButton1.setText("OK");
        
        try {
            jLabel1 = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/logo.jpg"))));
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        
        jTextField1.setBounds(90, 160, 200, 30);
        jButton1.setBounds(300, 160, 50, 30);
        jLabel1.setBounds(0, 0, 400, 250);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jTextField1);
        contentPane.add(jButton1);
        contentPane.add(jLabel1);
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