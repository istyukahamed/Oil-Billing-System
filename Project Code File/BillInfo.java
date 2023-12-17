
package Oil.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class BillInfo extends JFrame implements ActionListener {
    
    JTextField tfname , tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next;
    JLabel lblbiller;
    Choice billlocation, phasecode, billtype;
    String billnumber;
    
    BillInfo(String billnumber) {
        
        this.billnumber = billnumber;
       
        setSize(700, 500);
        setLocation(400, 200);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216 ,230));
        add(p);
        
        JLabel heading = new JLabel("Bill Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Bill Number");
        lblname.setBounds(100, 80, 120, 20);
        p.add(lblname);
        
        JLabel lblbillnumber = new JLabel("billnumber");
        lblbillnumber.setBounds(240, 80, 120, 20);
        p.add(lblbillnumber);        
        
        JLabel lblmeterno = new JLabel("Bill Location");
        lblmeterno.setBounds(100, 120, 120, 20);
        p.add(lblmeterno);
        
        billlocation = new Choice();
        billlocation.add("Outside");
        billlocation.add("Inside");
        billlocation.setBounds(240, 120, 200, 20);
        p.add(billlocation);
        
     
        
      
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 120, 20);
        p.add(lblcity);
        
        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        
        phasecode.setBounds(240, 200, 200, 20);
        p.add(phasecode);
        
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 120, 20);
        p.add(lblstate);
        
        billtype = new Choice();
        billtype.add("Normal Meter");
        billtype.add("Industial Meter");
        billtype.setBounds(240, 240, 200, 20);
        p.add(billtype);
        
        JLabel lblemail = new JLabel("Days ");
        lblemail.setBounds(100, 280, 120, 20);
        p.add(lblemail);
        
        JLabel lblemails = new JLabel("30 Days ");
        lblemails.setBounds(240, 280, 120, 20);
        p.add(lblemails);
        
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 120, 20);
        p.add(lblphone);
        
        JLabel lblphones = new JLabel("By Default Bill is Calculated for 30 Days Only");
        lblphones.setBounds(240, 320, 500, 20);
        p.add(lblphones);
        
        
        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        p.add(next);
        
        
        
        
        
        setLayout(new BorderLayout()); 
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        
        getContentPane().setBackground(Color.WHITE);
        
        

        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String biller = billnumber;
            String location = billlocation.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info values ('"+biller+"', '"+location+"','"+code+"', '"+typebill+"', '"+days+"')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
               
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        
        new BillInfo("");
        
    }
    
}
