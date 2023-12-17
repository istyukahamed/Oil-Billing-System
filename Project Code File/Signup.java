
package Oil.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    
    JButton create, back;
    Choice  accountType;
    JTextField biller, username, name, password;
    
    Signup(){
        
        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216,230),2),"Create-Account", TitledBorder.LEADING,TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
        JLabel heading = new JLabel("Create Account as");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD, 14));
        panel.add(heading);
        
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260, 50, 150, 20);
        panel.add(accountType);
        
        JLabel lblbiller = new JLabel("Bill Number");
        lblbiller.setBounds(100, 90, 140, 20);
        lblbiller.setForeground(Color.GRAY);
        lblbiller.setFont(new Font("Tahoma",Font.BOLD, 14));
        lblbiller.setVisible(false);
        panel.add(lblbiller);
        
        biller = new JTextField();
        biller.setBounds(260, 90, 150,20);
        biller.setVisible(false);
        panel.add(biller);
        
       
        
        JLabel Iblusername = new JLabel("Username");
        Iblusername.setBounds(100, 130, 140, 20);
        Iblusername.setForeground(Color.GRAY);
        Iblusername.setFont(new Font("Tahoma",Font.BOLD, 14));
        panel.add(Iblusername);
        
        username = new JTextField();
        username.setBounds(260, 130, 150,20);
        panel.add(username);
        
        JLabel Iblname = new JLabel("Name");
        Iblname.setBounds(100, 170, 140, 20);
        Iblname.setForeground(Color.GRAY);
        Iblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        panel.add(Iblname);
        
         biller.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {}
            
            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    Conn c = new Conn();
                    
                ResultSet rs = c.s.executeQuery("select * from login where bill_no = '"+biller.getText()+"'");
                while(rs.next()) {
                    name.setText(rs.getString("name"));
                }
   
                 } catch (Exception e) {
                
                 e.printStackTrace();
            }
            
            
            
            }
            
        });
        
        name = new JTextField();
        name.setBounds(260, 170, 150,20);
        panel.add(name);
        
        JLabel Iblpassword = new JLabel("password");
        Iblpassword.setBounds(100, 210, 140, 20);
        Iblpassword.setForeground(Color.GRAY);
        Iblpassword.setFont(new Font("Tahoma",Font.BOLD, 14));
        panel.add(Iblpassword);
        
        password = new JTextField();
        password.setBounds(260, 210, 150,20);
        panel.add(password);
        
        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
               
               String user = accountType.getSelectedItem();
               if(user.equals("Customer")) {
                   lblbiller.setVisible(true);
                   biller.setVisible(true);
                   name.setEditable(false);
                   
               } else {
                    lblbiller.setVisible(false);
                   biller.setVisible(false);
                   name.setEditable(true);
               }
                
            }
               
        });
        
        
        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setBounds(140, 260, 120, 25 );
        create.addActionListener(this);
        panel.add(create);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setBounds(300, 260, 120, 25 );
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupimage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);
        
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == create) {
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String sbiller = biller.getText();
            
            try {
                Conn c = new Conn();
                
                String query = null;
                
                if(atype.equals("Admin")) {
               query = "insert into login values('"+sbiller+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                
                } else {
                    query = "update login set username = '"+susername+"', password = '"+spassword+"', user = '"+atype+"' where bill_no = '"+sbiller+"'";
                    
                }
            
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account Create Successfully");
                
                setVisible(false);
                new Login();
                
            
            } catch (Exception e) {
                
                e.printStackTrace();
            }
            
        } else if (ae.getSource() == back) {
            
            setVisible(false);
            
            new Login();
            
        }
        
    }
    
    public static void main(String[] args){
        
        new Signup();
    } 
}
