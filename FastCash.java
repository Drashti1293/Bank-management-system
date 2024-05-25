package bank.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;


public class FastCash extends JFrame implements ActionListener{
    
    JButton deposit,withdraw,ministatemant,fastcash,pinchange,balanceenquiry,exit;
    String pin;
    
    FastCash(String pin){
        this.pin=pin;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank\\management\\sysytem\\icons\\atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text = new JLabel("Select Withdrawal amount");
        text.setBounds(215, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        deposit = new JButton("100");
        deposit.setBounds(170, 385, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdraw = new JButton("500");
        withdraw.setBounds(355, 385, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);
        
        fastcash = new JButton("1000");
        fastcash.setBounds(170, 420, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        ministatemant = new JButton("2000");
        ministatemant.setBounds(355, 420, 150, 30);
        ministatemant.addActionListener(this);
        image.add(ministatemant);
        
        pinchange = new JButton("5000");
        pinchange.setBounds(170, 455, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);
        
        balanceenquiry = new JButton("10000");
        balanceenquiry.setBounds(355, 455, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);
        
        exit = new JButton("Back");
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
            setVisible(false);
            new Transactions(pin).setVisible(true);
        }else {
            String amount = ((JButton)ae.getSource()).getText();
            Conn c = new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where pin = '"+pin+"'");
                int balance=0;
                while(rs.next()){
                    if (rs.getString("type").equalsIgnoreCase("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount")); 
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                    
                }
                
                if(ae.getSource()!= exit && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                
                Date date = new Date();
                c.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs."+ amount + "Debited Successfully");
                
                setVisible(false);
                new Transactions(pin).setVisible(true);
                
            }catch(Exception e){
                System.out.println("   "+e.getMessage());
            }
        }
    }
    
    public static void main(String args[]){
        new FastCash("");
    }
}
