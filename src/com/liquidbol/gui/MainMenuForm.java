package com.liquidbol.gui;

import com.liquidbol.addons.UIStyle;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Franco
 */
public class MainMenuForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JButton cartBtn;
    private JButton recpBtn;
    private JButton quoteBtn;
    private JButton repoBtn;
    private JButton sellListBtn;
    private JButton invBtn;
    private JButton clientBtn;
    private JButton cxcBtn;
    private JButton arBtn;
    private JButton provBtn;
    private JButton branchBtn;
    private JButton buyBtn;
    private JButton empBtn;
    private JLabel bgLabel;
    protected static ShopCartForm scf;

    public MainMenuForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(700, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, getWidth(), getHeight());
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResource("/com/liquidbol/images/MenuWallp.png"));
            Image dimg = img.getScaledInstance(bgLabel.getWidth(), bgLabel.getHeight(), Image.SCALE_SMOOTH);
            bgLabel.setIcon(new ImageIcon(dimg));
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        bgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        title = new JLabel("LIQUID");
        title.setFont(new Font("Arial", Font.PLAIN, 40));

        cartBtn = new JButton("Carrito");
        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scf = new ShopCartForm();
                dispose();
            }
        });
        
        recpBtn = new JButton("Recepciones");
        addRecepDDL(recpBtn);
        
        quoteBtn = new JButton("Cotizaciones");
        quoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListItemEstimatesForm lqf = new ListItemEstimatesForm();
                dispose();
            }
        });
        
        //sellBtn = new JButton("Registros");
        repoBtn = new JButton("Reportes");
        addRepoDDL(repoBtn);
        
        sellListBtn = new JButton("Libro de ventas");
        addSellListDDL(sellListBtn);
        
        invBtn = new JButton("Inventario");
        invBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListInventorysForm lif = new ListInventorysForm();
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
        
        provBtn = new JButton("Proveedores");
        provBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSuppliersForm lsf = new ListSuppliersForm();
                dispose();
            }
        });
        
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
        
        branchBtn = new JButton("Sucursales");
        branchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListStoresForm sf = new ListStoresForm();
                dispose();
            }
        });

        empBtn = new JButton("Empleados");
        empBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListEmployeesForm lef = new ListEmployeesForm();
                dispose();
            }
        });
        
        title.setBounds(420, 30, 200, 100);
        cartBtn.setBounds(60, 40, 110, 50);
        recpBtn.setBounds(160, 40, 110, 50);
        quoteBtn.setBounds(260, 40, 110, 50);
        repoBtn.setBounds(60, 100, 110, 50);
        sellListBtn.setBounds(160, 100, 110, 50);
        invBtn.setBounds(60, 160, 110, 50);
        buyBtn.setBounds(160, 160, 110, 50);
        provBtn.setBounds(260, 160, 110, 50);
        clientBtn.setBounds(60, 220, 110, 50);
        cxcBtn.setBounds(160, 220, 110, 50);
        arBtn.setBounds(260, 220, 150, 50);
        branchBtn.setBounds(60, 280, 110, 50);
        empBtn.setBounds(160, 280, 110, 50);

        contentPane.add(title);
        contentPane.add(cartBtn);
        contentPane.add(recpBtn);
        contentPane.add(quoteBtn);
        contentPane.add(repoBtn);
        contentPane.add(sellListBtn);
        contentPane.add(invBtn);
        contentPane.add(buyBtn);
        contentPane.add(provBtn);
        contentPane.add(clientBtn);
        contentPane.add(cxcBtn);
        contentPane.add(arBtn);
        contentPane.add(branchBtn);
        contentPane.add(empBtn);
        contentPane.add(bgLabel);
    }

    private void addRecepDDL(JButton btn) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem(new AbstractAction("Extintores") {
            public void actionPerformed(ActionEvent e) {
                ExtingForm ef = new ExtingForm();
                dispose();
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Cilindros") {
            public void actionPerformed(ActionEvent e) {
                CylinderForm cf = new CylinderForm();
                dispose();
            }
        }));

        btn.setBounds(260, 400, 110, 50);
        btn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
    
    private void addRepoDDL(JButton btn) {
        JPopupMenu menu = new JPopupMenu();
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
    
    private void addSellListDDL(JButton btn) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem(new AbstractAction("Diario") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Create diary selling list form!");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Resumido/Semanal") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Create sellings-per-day form!");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Mensual") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 2 selected");
            }
        }));
        menu.add(new JMenuItem(new AbstractAction("Anual") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Option 3 selected");
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