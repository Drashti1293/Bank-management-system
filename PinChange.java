package bank.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
    
    JButton change,back;
    JPasswordField cpin, repin;
    String pin;
    
    PinChange(String pin){
        this.pin = pin;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank\\management\\sysytem\\icons\\atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text = new JLabel(" CHANGE PIN NUMBER ");
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.WHITE);
        text.setBounds(250, 280, 500, 35);
        image.add(text);
        
        JLabel pintext = new JLabel("NEW PIN:");
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setForeground(Color.WHITE);
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);
        
        cpin= new JPasswordField();
        cpin.setFont(new Font("Raleway", Font.BOLD, 22));
        cpin.setBounds(330, 360, 180, 25);
        image.add(cpin);
        
        JLabel repintext = new JLabel("RE-ENTER NEW PIN:");
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setForeground(Color.WHITE);
        repintext.setBounds(165, 360, 180, 25);
        image.add(repintext);
        
        repin= new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 22));
        repin.setBounds(330, 320, 180, 25);
        image.add(repin);
        
        change = new JButton("CHANGE");
        change.setBounds(335, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);
        
        back = new JButton("BACK");
        back.setBounds(335, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == change){
            try{
                String npin = cpin.getText();
                String rpin = repin.getText();

                if(!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null, "Entered PIN doesn't match");
                    return;
                }
                
                if(npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                
                if(rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }
                Conn conn= new Conn();
                String query1 = "update bank set pin = '"+rpin+"' where pin = '"+pin+"'";
                String query2 = "update login set pin = '"+rpin+"' where pin = '"+pin+"'";
                String query3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pin+"'";
                
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                
                JOptionPane.showMessageDialog(null, "PIN Changed successfully");
                
                setVisible(false);
                new Transactions(rpin).setVisible(true);
                
            }   
            
        catch(Exception  e){
            System.out.println(e);
        }
            } else {
                setVisible(false);
                new Transactions(pin).setVisible(true);
                }
    }
    
    
    public static void main(String args[]){
        new PinChange("").setVisible(true);
    }
}
