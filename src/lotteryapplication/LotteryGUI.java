/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotteryapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Dan
 */
public class LotteryGUI extends JFrame {
    private Lottery lottery = new Lottery();
    
    private JPanel panel;
    private JButton btnCheckResults;
    private JButton btnExportResults;
    private JTextField num1;
    private JTextField num2;
    private JTextField num3;
    private JTextField num4;
    private JTextField num5;
    private JTextField num6;    
    public LotteryGUI() {
        // Calls method with set up GUI information
        setUpGUI(1000, 600);
        
        addNumberControls(20, 50, 35, 35, 50, 50, "Line 1", 1);
        
        addBtnControl(20, 300);
    }
    
    private void setUpGUI(int height, int width) {
        // Name of app
        String appName = "Super fun Happy Lotto App you Win Big In!!!";
        // Create new JPanel
        panel = new JPanel();
        panel.setLayout(null);
        // Set Panel Color
        panel.setBackground(Color.gray);
        // Add Panel to display
        add(panel);
        // Set Size
        setSize(height, width);
        setLocation(10,10);
        setTitle(appName);
        JLabel headerLbl = new JLabel(appName); 
        headerLbl.setBounds(30, 10, (width - 200), 40);
        headerLbl.setFont(new Font("Arial", Font.ITALIC, 20));
        panel.add(headerLbl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    private void addControls() {
        for(int i = 0; i < 3; i++) {
            addNumberControls(20, 50, 35, 35, 50, (i * 50), ("Line " + (i + 1)), i);
        }
    }
    
    private void addBtnControl(int xPos, int yPos) {
        int w = 200, h = 30;
        btnCheckResults = new JButton("Check Results");
        btnExportResults = new JButton("Export Results");
        
        btnCheckResults.setBounds(xPos, yPos, w, h);
        btnExportResults.setBounds((xPos + w + 50), yPos, w, h);
        
        panel.add(btnCheckResults);
        panel.add(btnExportResults);
        
        btnCheckResults.addActionListener(new BtnAddResultsListener());
        btnExportResults.addActionListener(new BtnAddResultsListener());
    }
    
    /* 
        Variables x pos, y pos, width, height, individual offset is for space between input
        boxes, lineOffSet is the amount of space between each line, name is the name of the line index is for adding numbers to lottoLines Array
    */
    private void addNumberControls(int x, int y, int w, int h, int indOff, int lineOffSet, String name, int index) {
        
        JLabel lblLine = new JLabel(name);
        JButton btnAddNumbers = new JButton("Add Line");
        num1 = new JTextField(2);
        num2 = new JTextField(2);
        num3 = new JTextField(2);
        num4 = new JTextField(2);
        num5 = new JTextField(2);
        num6 = new JTextField(2);
        
        lblLine.setBounds(x, (y + lineOffSet), 100, w);
        
        num1.setBounds(x + 60, (y + lineOffSet), w, h);
        num2.setBounds((x + 60 + indOff), (y + lineOffSet), w, h);
        num3.setBounds((x + 60  + (indOff * 2)), (y + lineOffSet), w, h);
        num4.setBounds((x + 60  + (indOff * 3)), (y + lineOffSet), w, h);
        num5.setBounds((x + 60  + (indOff * 4)), (y + lineOffSet), w, h);
        num6.setBounds((x + 60  + (indOff * 5)), (y + lineOffSet), w, h);
        btnAddNumbers.setBounds((x + 60 + (indOff * 6)), (y + lineOffSet), (w * 3), h);
        
        System.out.println(num1);
        
        BtnAddLotteryListener listener = new BtnAddLotteryListener(num1, num2, num3, num4, num5, num6);
        
        btnAddNumbers.addActionListener(listener);
        
        panel.add(lblLine);
        panel.add(btnAddNumbers);
        panel.add(num1);
        panel.add(num2);
        panel.add(num3);
        panel.add(num4);
        panel.add(num5);
        panel.add(num6);
    }
    
    private void clearNumber() {
        this.num1.setText("");
        this.num2.setText("");
        this.num3.setText("");
        this.num4.setText("");
        this.num5.setText("");
        this.num6.setText("");
    }
    
    public class BtnAddResultsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnCheckResults) {
                displayResults();
            } else if(e.getSource() == btnExportResults) {
                lottery.exportLotteryResults();
            }
        }
        
        private void displayResults() {
            JOptionPane.showMessageDialog(null, lottery.runGUIImplementation());
        }
    }
    
    public class BtnAddLotteryListener implements ActionListener {
        protected JTextField num1, num2, num3, num4, num5, num6;
        private int[] line = new int[6];
        
        // Constructor to take 6 integer numbers and an index for the line array
        public BtnAddLotteryListener(JTextField n1, JTextField n2, JTextField n3, JTextField n4, JTextField n5, JTextField n6) {
            this.num1 = n1;
            this.num2 = n2;
            this.num3 = n3;
            this.num4 = n4;
            this.num5 = n5;
            this.num6 = n6;
        }
        
        @Override
        public void actionPerformed(ActionEvent event) {
            
            line[0] = Integer.valueOf(num1.getText());
            line[1] = Integer.valueOf(num2.getText());
            line[2] = Integer.valueOf(num3.getText());
            line[3] = Integer.valueOf(num4.getText());
            line[4] = Integer.valueOf(num5.getText());
            line[5] = Integer.valueOf(num6.getText());
            
            lottery.setLine(line);
            
            JOptionPane.showMessageDialog(null, "Line Added!");
            clearNumber();
        }
    }
}
