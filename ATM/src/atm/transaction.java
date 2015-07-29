
package atm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


public class transaction extends JFrame implements ActionListener{
    JButton check = new JButton("Check Balance");
    JButton deposit = new JButton("Deposit");
    JButton withdraw = new JButton("Withdraw");
    JButton exit = new JButton("Exit");
    
    JLabel l = new JLabel("Select One Transaction :");
    
    JPanel pane = new JPanel();
    Connection cn;
	Statement st;
	PreparedStatement ps;
    
    
    public void database()
   {
       
   
       try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn = DriverManager.getConnection("jdbc:odbc:ATM");
		}catch(ClassNotFoundException e)  {
 			System.err.println("Failed to load driver");
 			e.printStackTrace();
 	
 		}catch(SQLException e){
 			System.err.println("Unable to connect");
 			e.printStackTrace();
 			
 		}
        }
		
    public transaction()
    {
        setSize(330,300);
	setVisible(true);
	setResizable(false);
	setLocation(400,250);
        
        pane.setLayout(null);
        
        l.setBounds(5, 10, 150, 10);
        pane.add(l);
        
        deposit.setBounds(15,60,100,25);
        pane.add(deposit);
        
        withdraw.setBounds(210,60,100,25);
        pane.add(withdraw);
        
        check.setBounds(90, 150, 150, 25);
        pane.add(check);
        
        exit.setBounds(10, 220, 60, 25);
        pane.add(exit);
        
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        check.addActionListener(this);
        exit.addActionListener(this);
        
        
        add(pane);
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
       if(e.getSource() == deposit)
       {
                deposit ob = new deposit();
                dispose();
        }
       
        
        
    else if(source == check)
    {
        check  ob = new check();
        dispose();
    }
       
    else if (source == withdraw)
    {
        withdraw ob = new withdraw();
        dispose();
    }
    else if (source == exit)
    {
        login ob = new login();
        dispose();
    }
    }
    public static void main(String[]args){
        transaction ob = new transaction();
        
    }
    
}
