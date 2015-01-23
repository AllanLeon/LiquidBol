package com.liquidbol.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class MainMenuForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton sellBtn;
    private JButton recpBtn;
    private JButton quoteBtn;
    private JButton repoBtn;
    private JButton invBtn;
    private JButton clientBtn;
    private JButton cxcBtn;
    private JButton arBtn;
    private JButton provBtn;
    private JButton branchBtn;
    private JButton buyBtn;
    private JButton empBtn;

    public MainMenuForm() {
        setStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel("LIQUID");
        title.setFont(new Font("Arial", Font.PLAIN, 40));

        sellBtn = new JButton("Ventas");
        
        recpBtn = new JButton("Recepciones");
        
        quoteBtn = new JButton("Cotizaciones");
        quoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuoteForm qf = new QuoteForm();
                dispose();
            }
        });
        
        //sellBtn = new JButton("Registros");
        repoBtn = new JButton("Reportes");
        addDDL(repoBtn);
        
        invBtn = new JButton("Inventario");
        
        clientBtn = new JButton("Clientes");
        clientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListClientsForm lcf = new ListClientsForm();
                dispose();
            }
        });
        
        cxcBtn = new JButton("CXC");
        cxcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListCxcForm lcf = new ListCxcForm();
                dispose();
            }
        });
        
        arBtn = new JButton("Articulos Recargables");
        arBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListARForm laf = new ListARForm();
                dispose();
            }
        });
        
        provBtn = new JButton("Proveedores");
        provBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSuppliersForm lsf = new ListSuppliersForm();
                dispose();
            }
        });
        
        branchBtn = new JButton("Sucursales");

        empBtn = new JButton("Empleados");
        empBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListEmployeesForm lef = new ListEmployeesForm();
                dispose();
            }
        });

        buyBtn = new JButton("Compras");
        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListPurchasesForm lpf = new ListPurchasesForm();
                dispose();
            }
        });

        title.setBounds(420, 30, 200, 100);
        sellBtn.setBounds(60, 40, 110, 50);
        recpBtn.setBounds(160, 40, 110, 50);
        quoteBtn.setBounds(260, 40, 110, 50);
        repoBtn.setBounds(60, 100, 110, 50);
        invBtn.setBounds(60, 160, 110, 50);
        clientBtn.setBounds(60, 220, 110, 50);
        cxcBtn.setBounds(160, 220, 110, 50);
        arBtn.setBounds(260, 220, 150, 50);
        provBtn.setBounds(60, 280, 110, 50);
        branchBtn.setBounds(60, 340, 110, 50);
        empBtn.setBounds(160, 340, 110, 50);
        buyBtn.setBounds(60, 400, 110, 50);

        contentPane.add(title);
        contentPane.add(sellBtn);
        contentPane.add(recpBtn);
        contentPane.add(quoteBtn);
        //contentPane.add(sellBtn);
        contentPane.add(repoBtn);
        contentPane.add(invBtn);
        contentPane.add(clientBtn);
        contentPane.add(cxcBtn);
        contentPane.add(arBtn);
        contentPane.add(provBtn);
        contentPane.add(empBtn);
        contentPane.add(branchBtn);
        contentPane.add(buyBtn);
    }

    public static void setStyle() {
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

    private void addDDL(JButton btn) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem(new AbstractAction("Diarios") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 1 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Mensuales") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 2 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Anuales") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 3 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Por cliente") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 4 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Por empleado") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 5 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Por producto") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 6 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Por gastos") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 7 selected");
            }
        }));

        btn.setBounds(260, 400, 110, 50);
        btn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
