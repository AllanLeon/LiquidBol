/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.gui;

import com.liquidbol.addons.suggestor.AutoSuggestor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Allan Leon
 */
public class RechargeableItemDialog extends JDialog {
    
    private JPanel contentPane;
    private JTextField searchBox;
    private JButton submitBtn;
    private final List<String> itemsId;
    
    public RechargeableItemDialog (JFrame parent, List<String> itemsId) {
        super(parent);
        this.itemsId = itemsId;
        initializeComponents();
    }
    
    private void initializeComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        
    }
}
