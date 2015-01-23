package com.liquidbol.gui;

import java.awt.EventQueue;
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
    private JButton submitBtn;
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

        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        submitBtn = new JButton();
        submitBtn.setText("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm mm = new MainMenuForm();
            }
        });

        jTextField1.setBounds(90, 160, 200, 30);
        submitBtn.setBounds(300, 160, 50, 30);
        jLabel1.setBounds(0, 0, 400, 250);

        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/com/liquidbol/images/logo2.jpg"));
            Image dimg = img.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
            jLabel1.setIcon(new ImageIcon(dimg));
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jTextField1);
        contentPane.add(submitBtn);
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
