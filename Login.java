package bank.management.sysytem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel text,cardno,pin;
    JTextField cardTextfield;
    JPasswordField pinTextField;
    JButton signin,clear,signup;
  
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank\\management\\sysytem\\icons\\logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);
        
        text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200,40,450,40);
        add(text);
        
        cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(125,150,375,30);
        add(cardno);
        
        cardTextfield = new JTextField(15);
        cardTextfield.setBounds(300,150,230,30);
        cardTextfield.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextfield);
        
        pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(125,220,375,30);
        add(pin);
        
        pinTextField = new JPasswordField(15);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        pinTextField.setBounds(300,220,230,30);
        add(pinTextField);
                
        signin = new JButton("SIGN IN");
        signin.setFont(new Font("Arial", Font.BOLD, 14));
        signin.setBounds(300,300,100,30);
        signin.setBackground(Color.BLACK);
        signin.setForeground(Color.WHITE);
        signin.addActionListener(this);
        add(signin);
        
        clear = new JButton("CLEAR");
        clear.setFont(new Font("Arial", Font.BOLD, 14));
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setFont(new Font("Arial", Font.BOLD, 14));
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        setLayout(null);
       
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,480);
        setLocation(550,200);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){        
            if(ae.getSource()== clear){
                cardTextfield.setText("");
                pinTextField.setText("");
            }else if(ae.getSource()==signin){
                Conn conn = new Conn();
                String cardnumber  = cardTextfield.getText();
                String pin  = pinTextField.getText();
                String query  = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pin+"'"; 
                
                try{
                    ResultSet rs = conn.s.executeQuery(query);
                    if(rs.next()){
                        setVisible(false);
                        new Transactions(pin).setVisible(true);
                    } else {
                         JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
                }catch(Exception e){
                    System.out.println(e);
                }
                
                
            }else if(ae.getSource() == signup){
                setVisible(false);
                new SignupOne().setVisible(true);
            }
                
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }  
}


