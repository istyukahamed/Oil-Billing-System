
package Oil.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class PayBill  extends JFrame implements ActionListener{
    
    Choice cmonth;
    JButton pay, back;
    String biller;
    
    PayBill (String biller) {
        this.biller = biller;
        setLayout(null);
        setBounds(300, 150, 900, 600);
        
        
        JLabel heading = new JLabel("Oil Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);
        
        
        JLabel lblbillnumber = new JLabel("Bill Number");
        lblbillnumber.setBounds(35, 80, 200, 20);
        add(lblbillnumber);
        
        JLabel billnumber = new JLabel("");
        billnumber.setBounds(300, 80, 200, 20);
        add(billnumber);
        
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);
        
        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);
        
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
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
        add(cmonth);
        
        
        JLabel lblgallons = new JLabel("Gallons");
        lblgallons.setBounds(35, 260, 200, 20);
        add(lblgallons);
        
        JLabel labelgallons = new JLabel("");
        labelgallons.setBounds(300, 260, 200, 20);
        add(labelgallons);
        
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);
        
        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);
        
         JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);
        
        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);
        
         try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from customer where bill_no = '"+biller+"'");
                while(rs.next()) {
                    billnumber.setText(biller);
                    labelname.setText(rs.getString("name"));
                    
                }
                
                 rs = c.s.executeQuery("select * from bill where bill_no = '"+biller+"' and month = '"+cmonth.getSelectedItem()+"'");
                 while(rs.next()) {
                    labelgallons.setText(rs.getString("gallons"));
                    labeltotalbill.setText(rs.getString("totalbill"));
                    labelstatus.setText(rs.getString("status"));
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
         
         
         cmonth.addItemListener(new ItemListener() {
           
            @Override
             public void itemStateChanged(ItemEvent ae) {
                 try {
                 Conn c = new Conn();
                
                
                ResultSet rs = c.s.executeQuery("select * from bill where bill_no = '"+biller+"' AND month = '"+cmonth.getSelectedItem()+"'");
                 while(rs.next()) {
                    labelgallons.setText(rs.getString("gallons"));
                    labeltotalbill.setText(rs.getString("totalbill"));
                    labelstatus.setText(rs.getString("status"));
                    
                  }
                
                 } catch (Exception e) {
                 e.printStackTrace();
                }
               }
             
         });
         
         pay = new JButton("Pay");
         pay.setBackground(Color.WHITE);
         pay.setForeground(Color.BLACK);
         pay.setBounds(100, 460, 100, 25);
         pay.addActionListener(this);
         add(pay);
         
         
         back = new JButton("Back");
         back.setBackground(Color.WHITE);
         back.setForeground(Color.BLACK);
         back.setBounds(230, 460, 100, 25);
         back.addActionListener(this);
         add(back);
         
         getContentPane().setBackground(Color.WHITE);
         
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
         Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);
         JLabel image = new JLabel(i3);
         image.setBounds(400, 120, 600, 300);
         add(image);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == pay) {
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where bill_no = '"+biller+"' and month = '"+cmonth.getSelectedItem()+"'");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
             setVisible(false);
            new Paytm(biller);
            
        } else {
            setVisible(false);
            
        }
        
    }
    
    public static void main(String[] args) {
       
       new PayBill("");
   }
    
}
