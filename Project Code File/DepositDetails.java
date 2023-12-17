
package Oil.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;


public class DepositDetails extends JFrame implements ActionListener {
    
    Choice billnumber, cmonth;
    JTable table;
    JButton search, print;
    
    DepositDetails() {
        super("Deposit Details");
        
        
        setSize(800, 700);
        setLocation(400, 100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber = new JLabel("Search By Bill Number");
        lblmeternumber.setBounds(20, 20, 160, 20);
        add(lblmeternumber);
        
        billnumber = new Choice();
        billnumber.setBounds(180, 20, 200, 20);
        add(billnumber);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()) {
                billnumber.add(rs.getString("bill_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 120, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(520, 20, 200, 20);
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
        
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
        ResultSet rs = c.s.executeQuery("select * from bill");
        
        
        table.setModel(DbUtils.resultSetToTableModel(rs));
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 800, 700);
        add(sp);
        
        search = new JButton("search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        
        setVisible(true);
        
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == search){
            String query = "select * from bill where bill_no = '"+billnumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";
            
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
             } catch (Exception e) {
                e.printStackTrace();
             }
            
        } else {
            try {
                table.print();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        }
        
    }
    
    
    
    public static void main(String[] args) {
        
        new DepositDetails();
    }
}
