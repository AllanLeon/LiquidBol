package com.liquidbol.gui;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * @author Franco
 */
public class LoginForm extends JFrame {

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
        setResizable(false);
        setLocationRelativeTo(null);
                
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new AbsoluteLayout());
        
        jTextField1 = new JTextField();
        jButton1 = new JButton();
        jButton1.setText("OK");
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/liquidbol/images/logo.jpg")));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        
        getContentPane().add(jTextField1, new AbsoluteConstraints(70, 160, 200, 30));
        getContentPane().add(jButton1, new AbsoluteConstraints(280, 160, -1, 30));
        getContentPane().add(jLabel1, new AbsoluteConstraints(0, 0, 400, 200));
        pack();
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