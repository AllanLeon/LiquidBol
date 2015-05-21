/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui;

import com.liquidbol.addons.suggestor.AutoSuggestor;
import com.liquidbol.gui.tables.model.ShopCartTableModel;
import com.liquidbol.model.Bill;
import com.liquidbol.model.Company;
import com.liquidbol.model.OperationFailedException;
import com.liquidbol.model.RechargeableItem;
import com.liquidbol.model.Service;
import com.liquidbol.model.ServiceReception;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Allan Leon
 */
public class RechargeableItemDialog extends JDialog {
    
    private JPanel contentPane;
    private JTextField searchBox;
    private JButton submitBtn;
    
    private final Bill parentBill;
    private final Service reqService;
    private final List<String> itemsId;
    private final ShopCartTableModel shopCart;
    
    public RechargeableItemDialog(JFrame parent, List<String> itemsId, Bill bill, Service service, ShopCartTableModel shopCart) {
        super(parent);
        this.itemsId = itemsId;
        this.shopCart = shopCart;
        parentBill = bill;
        reqService = service;
        initializeComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack(); 
        setModal(true);
        setVisible(true);
    }
    
    private void initializeComponents() {
        setTitle("Liquid");
        setPreferredSize(new Dimension(350, 100));
        setResizable(false);
        setLocationRelativeTo(null);
        
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        searchBox = new JTextField();
        AutoSuggestor suggestor = new AutoSuggestor(searchBox, this, itemsId, Color.DARK_GRAY, Color.WHITE, Color.RED, 0.8f);
        
        submitBtn = new JButton();
        submitBtn.setText("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRechargeableItem();
            }
        });
        
        searchBox.setBounds(50, 30, 200, 30);
        submitBtn.setBounds(260, 30, 50, 30);
        
        contentPane.add(searchBox);
        contentPane.add(submitBtn);
    }
    
    private void addRechargeableItem() {
        try {
            RechargeableItem recItem = Company.findRechargeableItemById(searchBox.getText());
            parentBill.addServiceReception(new ServiceReception(1, reqService, recItem, new Date(new java.util.Date().getTime()), new Timestamp(new java.util.Date().getTime()), 1.0, ""));
            setVisible(false);
            dispose();
            shopCart.updateLists();
        } catch (OperationFailedException ex) {
            Logger.getLogger(RechargeableItemDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
