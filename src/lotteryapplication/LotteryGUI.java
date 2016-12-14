/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotteryapplication;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Dan
 */
public class LotteryGUI extends JFrame {
    Lottery lottery = new Lottery();

    
    private JPanel panel;
    private JLabel num1Label, num2Label;
    private JTextField num1TextField, num2TextField;
    private JButton addButton, mulButton;
    
    public LotteryGUI() {
        // Calls method with set up GUI information
        setUpGUI();
        
        addControlsTest();
    }
    
    private void setUpGUI() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.red);
        add(panel);
        setSize(500, 250);
        setLocation(10,10);
        setTitle("Super fun Happy Lotto App you Win Big In!!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    private void addControlsTest() {
        num1Label = new JLabel("Enter Number 1: ");
        num2Label = new JLabel("Enter Number 2: ");
    
        num1TextField = new JTextField(10);
        num2TextField = new JTextField(10);
        
        addButton = new JButton("Add");
        mulButton = new JButton("Multiply");
        
        num1Label.setBounds(10,10,200,100);
        num2Label.setBounds(10,50,200,100);
               
        num1TextField.setBounds(230, 10, 200, 30);
        num2TextField.setBounds(230,50,200,30);
                
        addButton.setBounds(10, 100, 200, 30);
        mulButton.setBounds(230, 100, 200, 30);
               
        panel.add(num1Label);
        panel.add(num2Label);
        panel.add(num1TextField);
        panel.add(num2TextField);
        panel.add(addButton);
        panel.add(mulButton);        
    }
}
