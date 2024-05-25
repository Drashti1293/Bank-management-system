package bank.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener{
    
    JButton deposit,withdraw,ministatemant,fastcash,pinchange,balanceenquiry,exit;
    String pin;
    
    Transactions(String pin){
        this.pin=pin;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank\\management\\sysytem\\icons\\atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(215, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(170, 385, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdraw = new JButton("Cash Withdrawal");
        withdraw.setBounds(355, 385, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);
        
        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170, 420, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        ministatemant = new JButton("Mini Statement");
        ministatemant.setBounds(355, 420, 150, 30);
        ministatemant.addActionListener(this);
        image.add(ministatemant);
        
        pinchange = new JButton("Pin Change");
        pinchange.setBounds(170, 455, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);
        
        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(355, 455, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);
        
        exit = new JButton("Exit");
        exit.setBounds(355, 490, 150, 30);
        exit.addActionListener(this);
        image.add(exit);
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            System.exit(0);
        }else if (ae.getSource()== deposit){
            setVisible(false);
            new Deposit(pin).setVisible(true);
        }else if (ae.getSource()== withdraw){
            setVisible(false);
            new Withdrawal(pin).setVisible(true);
        } else if (ae.getSource()== fastcash){
            setVisible(false);
            new FastCash(pin).setVisible(true);
        } else if (ae.getSource() == pinchange){
            setVisible(false);
            new PinChange(pin).setVisible(true);
        } else if (ae.getSource()== balanceenquiry){
            setVisible(true);
            new BalanceInquiry(pin).setVisible(true);
        } else if (ae.getSource() == ministatemant){
            setVisible(false);
            new MiniStatement(pin).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Transactions("");
    }
}
