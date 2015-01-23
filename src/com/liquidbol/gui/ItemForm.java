package com.liquidbol.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
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
public class ItemForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idShower;
    private JLabel measureLbl;
    private Component measureBox;
    private JLabel descLbl;
    private Component itemDesc;
    private JLabel brandLbl;
    private Component itemBrand;
    private JLabel madeLbl;
    private Component itemMade;
    private JLabel typeLbl;
    private Component itemType;
    private JLabel subtypeLbl;
    private Component itemSubtype;
    private JLabel priceLbl;
    private Component itemPrice;
    private JLabel costLbl;
    private Component itemCost;
    private JLabel itemPhoto;
    private JButton submitBtn;
    private MouseListener ml;

    public ItemForm(int state) {
        switch (state) {
            case 1: //Add new item
                setStyle();
                initComponents();
                setVisible(true);
                break;
            case 2: //show item data
                setStyle();
                initComponents();
                convertToReadOnly();
                setVisible(true);
                break;
            case 3: //edit item data
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
        title.setText("NUEVO ARTICULO");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        measureLbl = new JLabel("Medida");
        measureBox = new JTextField();
        descLbl = new JLabel("Descripcion");
        itemDesc = new JTextField();
        brandLbl = new JLabel("Marca");
        itemBrand = new JTextField();
        madeLbl = new JLabel("Industria");
        itemMade = new JTextField();
        typeLbl = new JLabel("Tipo");
        itemType = new JTextField();
        subtypeLbl = new JLabel("Subtipo");
        itemSubtype = new JTextField();
        costLbl = new JLabel("Costo");
        itemCost = new JTextField();
        priceLbl = new JLabel("Precio");
        itemPrice = new JTextField();

        try {
            itemPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/upload.png"))));
            itemPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(ItemForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn = new JButton("Add");

        title.setBounds(100, 30, 400, 30);
        idShower.setBounds(350, 80, 150, 30);
        measureLbl.setBounds(60, 120, 70, 30);
        measureBox.setBounds(110, 120, 50, 30);
        descLbl.setBounds(40, 160, 70, 30);
        itemDesc.setBounds(110, 160, 400, 30);
        brandLbl.setBounds(40, 200, 40, 30);
        itemBrand.setBounds(80, 200, 180, 30);
        madeLbl.setBounds(285, 200, 50, 30);
        itemMade.setBounds(340, 200, 170, 30);
        typeLbl.setBounds(50, 240, 70, 30);
        itemType.setBounds(80, 240, 150, 30);
        subtypeLbl.setBounds(270, 240, 70, 30);
        itemSubtype.setBounds(320, 240, 150, 30);
        costLbl.setBounds(290, 300, 40, 30);
        itemCost.setBounds(330, 300, 100, 30);
        priceLbl.setBounds(290, 340, 40, 30);
        itemPrice.setBounds(330, 340, 100, 30);
        itemPhoto.setBounds(80, 300, 150, 150);
        submitBtn.setBounds(350, 400, 100, 30);

        contentPane.add(title);
        contentPane.add(idShower);
        contentPane.add(measureLbl);
        contentPane.add(measureBox);
        contentPane.add(descLbl);
        contentPane.add(itemDesc);
        contentPane.add(brandLbl);
        contentPane.add(itemBrand);
        contentPane.add(madeLbl);
        contentPane.add(itemMade);
        contentPane.add(typeLbl);
        contentPane.add(itemType);
        contentPane.add(subtypeLbl);
        contentPane.add(itemSubtype);
        contentPane.add(costLbl);
        contentPane.add(itemCost);
        contentPane.add(priceLbl);
        contentPane.add(itemPrice);
        contentPane.add(itemPhoto);
        contentPane.add(submitBtn);
        onMouseHover(itemPhoto);
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
        Icon temp = itemPhoto.getIcon();
        contentPane.remove(measureBox);
        contentPane.remove(itemDesc);
        contentPane.remove(itemBrand);
        contentPane.remove(itemMade);
        contentPane.remove(itemType);
        contentPane.remove(itemSubtype);
        contentPane.remove(itemCost);
        contentPane.remove(itemPrice);
        contentPane.remove(itemPhoto);
        contentPane.remove(submitBtn);

        measureBox = new JLabel();
        itemDesc = new JLabel();
        itemBrand = new JLabel();
        itemMade = new JLabel();
        itemType = new JLabel();
        itemSubtype = new JLabel();
        itemCost = new JLabel();
        itemPrice = new JLabel();
        itemPhoto = new JLabel(temp);
        title.setText("VER ARTICULO"); //CHANGE!!!!

        measureBox.setFont(new Font("Arial", Font.PLAIN, 20));
        itemDesc.setFont(new Font("Arial", Font.PLAIN, 20));
        itemBrand.setFont(new Font("Arial", Font.PLAIN, 20));
        itemMade.setFont(new Font("Arial", Font.PLAIN, 20));
        itemType.setFont(new Font("Arial", Font.PLAIN, 20));
        itemSubtype.setFont(new Font("Arial", Font.PLAIN, 20));
        itemCost.setFont(new Font("Arial", Font.PLAIN, 20));
        itemPrice.setFont(new Font("Arial", Font.PLAIN, 20));

        measureBox.setBounds(110, 120, 50, 30);
        itemDesc.setBounds(110, 160, 400, 30);
        itemBrand.setBounds(80, 200, 180, 30);
        itemMade.setBounds(340, 200, 170, 30);
        itemType.setBounds(80, 240, 150, 30);
        itemSubtype.setBounds(320, 240, 150, 30);
        itemCost.setBounds(330, 300, 100, 30);
        itemPrice.setBounds(330, 340, 100, 30);
        itemPhoto.setBounds(80, 300, 150, 150);

        contentPane.add(measureBox);
        contentPane.add(itemDesc);
        contentPane.add(itemBrand);
        contentPane.add(itemMade);
        contentPane.add(itemType);
        contentPane.add(itemSubtype);
        contentPane.add(itemCost);
        contentPane.add(itemPrice);
        contentPane.add(itemPhoto);
    }
}