
package Oil.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener {
    
    String biller;
    JButton back;
    Paytm(String biller) {
        this.biller = biller;
       
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try {
          j.setPage("https://ibblportal.islamibankbd.com/indexLogin.do");
            
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>No page Found<html>");
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
            
        }
    
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(biller);
    }

    
    
    public static void main(String[] args) {
        new Paytm("");
}
}