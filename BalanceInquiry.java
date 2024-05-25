package bank.management.sysytem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceInquiry extends JFrame implements ActionListener{
    
    JButton back;
    String pin;
    
    BalanceInquiry(String pin){
        
        this.pin = pin;
        setLayout(null);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank\\management\\sysytem\\icons\\atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
         back= new JButton("BACK");
         back.setBounds(355 ,520 ,150 ,30);
         back.addActionListener(this);
         image.add(back);
         
         
         
         int balance=0;
            try{
                Conn c = new Conn();
                ResultSet rs=c.s.executeQuery("Select * from bank where pin = '"+pin+"'");
                
                while(rs.next()){
                    if (rs.getString("type").equalsIgnoreCase("deposit")){
                        balance += Integer.parseInt(rs.getString("amount")); 
                    } 
                    else
                    {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                    
                }
                }catch (Exception e){
                        System.out.println(e);
                }
            
            
        JLabel text = new JLabel(" YOUR CURRENT ACCOUNT BALANCE IS RS."+balance);
        text.setFont(new Font("System", Font.BOLD, 14));
        text.setForeground(Color.WHITE);
        text.setBounds(170, 280, 500, 35);
        image.add(text);
        
        
            
         
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
    }
         public void actionPerformed(ActionEvent ae){
             setVisible(false);
             new Transactions(pin).setVisible(true);
         }
    
    
    public static void main(String args[]){
        new BalanceInquiry("");
    }
    
}
