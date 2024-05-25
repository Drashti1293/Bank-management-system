package bank.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class MiniStatement extends JFrame{
    
    MiniStatement(String pin){
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini= new JLabel();
        add(mini);
        
        JLabel back = new JLabel("Gujarat bank");
        back.setBounds(150,20,100,20);
        add(back);
        
        JLabel card= new JLabel();
        card.setBounds(20 ,80, 300,20);
        add(card);
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pin+"'");
            while(rs.next()){
                card.setText("Card Number:" + rs.getString("cardnumber").substring(0,4)+ "XXXXXXXX" + rs.getString("cardnumber"));
        }
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pin+"'");
            
            while(rs.next()){
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount")+"<br><br>");
            }
            
            
        }catch(Exception e){
            System.out.print(e);
        }
        
        mini.setBounds(20,150,400,200);
        
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    
    public static void main(String args[]){
        new MiniStatement("");
    }
    
    
}
