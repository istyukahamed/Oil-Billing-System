
package Oil.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {
    
    JTextField tfname , tfaddress, tfstate, tfgallons, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblname, labeladdress;
    Choice billnumber, cmonth;
    
    CalculateBill() {
       
        setSize(700, 500);
        setLocation(400, 150);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216 ,230));
        add(p);
        
        JLabel heading = new JLabel("Calculate Oil Bill");
        heading.setBounds(100, 10, 400, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblbillnumber = new JLabel("bill Number");
        lblbillnumber.setBounds(100, 80, 120, 20);
        p.add(lblbillnumber);
        
        billnumber = new Choice();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()) {
                billnumber.add(rs.getString("bill_no"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        billnumber.setBounds(240, 80, 200, 20);
        p.add(billnumber);
        
        
        JLabel lblmeterno = new JLabel("Name");
        lblmeterno.setBounds(100, 120, 120, 20);
        p.add(lblmeterno);
        
        lblname = new JLabel("");
        lblname.setBounds(240, 120, 120, 20);
        p.add(lblname);
        
       
        
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 120, 20);
        p.add(lbladdress);
        
        labeladdress = new JLabel();
        labeladdress.setBounds(240, 160, 200, 20);
        p.add(labeladdress);
        
        try {
           Conn c = new Conn();
           ResultSet rs = c.s.executeQuery("select * from customer where bill_no = '"+billnumber.getSelectedItem()+"'");
           while(rs.next()) {
               lblname.setText(rs.getString("name"));
               labeladdress.setText(rs.getString("address"));

           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        billnumber.addItemListener(new ItemListener() {
            
            public void itemStateChanged(ItemEvent ie){
                 try {
                       Conn c = new Conn();
                       ResultSet rs = c.s.executeQuery("select * from customer where bill_no = '"+billnumber.getSelectedItem()+"'");
                       while(rs.next()) {
                       lblname.setText(rs.getString("name"));
                       labeladdress.setText(rs.getString("address"));

                    }
            
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        
        
        
        JLabel lblcity = new JLabel("Gallons selled");
        lblcity.setBounds(100, 200, 120, 20);
        p.add(lblcity);
        
        tfgallons = new JTextField();
        tfgallons.setBounds(240, 200, 200, 20);
        p.add(tfgallons);
        
        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100, 240, 120, 20);
        p.add(lblstate);
        
        cmonth = new Choice();
        cmonth.setBounds(240, 240, 200, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
  
        p.add(cmonth);
        
      
        
        next = new JButton("Submit");
        next.setBounds(120, 350, 100, 25);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(250, 350, 100, 25);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);

        p.add(cancel);
        
        
        
        setLayout(new BorderLayout()); 
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        
        getContentPane().setBackground(Color.WHITE);
        
        

        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            
            String biller = billnumber.getSelectedItem();
            String gallons = tfgallons.getText();
            String month = cmonth.getSelectedItem();
            
            int totalbill = 0;
            int gallons_selled = Integer.parseInt(gallons);
            
            
            String query = "select *from tax";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                
                while(rs.next()) {
                  totalbill += gallons_selled * Integer.parseInt(rs.getString("cost_per_gallon"));
                  totalbill += Integer.parseInt(rs.getString("service_charge"));
                  totalbill += Integer.parseInt(rs.getString("service_tax"));
                  totalbill += Integer.parseInt(rs.getString("clean_bd_tax"));
                  totalbill += Integer.parseInt(rs.getString("fixed_tax"));
                }
               
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            String query2 = "insert into bill values('"+biller+"', '"+month+"', '"+gallons+"', '"+totalbill+"','Not Paid')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Client Bill Updated Successfully");
                setVisible(false);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        
        new CalculateBill();
        
    }
    
}
