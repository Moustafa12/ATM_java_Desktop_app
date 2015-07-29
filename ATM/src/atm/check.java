/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

/**
 *
 * @author Moustafa(DAR4)
 */
public class check extends JFrame implements  ActionListener{
    
    
    JPanel pane = new JPanel();
    JLabel lpass = new JLabel("Password");
    JLabel lbalance = new JLabel("Balance");
    
    
    JPasswordField tpass = new JPasswordField();
    JTextField tbalance = new JTextField();
    
    JButton bcheck = new JButton("Check Balance");
    JButton bmenu = new JButton("Back Menu");
    
    Connection cn;
    Statement st;
    
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
    
    public check()
    {
           pane.setLayout(null);
                setLocation(400,250);
		setSize(300,200);
		setTitle("Check Balance");
		setResizable(false);
		setVisible(true);
                
                lpass.setBounds(10,20,80,20);
		lbalance.setBounds(10,40,80,20);
		tpass.setBounds(100,20,100,20);
		tbalance.setBounds(100,40,100,20);
                tbalance.setEditable(false);
		
		bcheck.setBounds(80,70,130,20);
                bmenu.setBounds(80,120,140,20);
                bcheck.addActionListener(this);
                bmenu.addActionListener(this);
                
                pane.add(lpass);
                pane.add(lbalance);
                pane.add(tpass);
                pane.add(tbalance);
                pane.add(bcheck);
                pane.add(bmenu);
                
                add(pane);
                database();
                
    }
    
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source == bmenu)
        {
            transaction ob1 = new transaction();
            dispose();
        }
        else if ( source == bcheck)
        {
            try{
                boolean b = false;
				st= cn.createStatement();	
					ResultSet rs=st.executeQuery("SELECT * FROM Table1 WHERE password ='"+tpass.getText()+"'");
						while(rs.next()){
                                                    b= true;
						    tbalance.setText(rs.getString(8));
						
						JOptionPane.showMessageDialog(null,"Balance Found.");
					
						}
                                                if(b == false)
                                                    JOptionPane.showMessageDialog(null,"Balance Not Found. Try again");
					st.close();
				}
				catch(SQLException s){
					 
				}
				catch(Exception x){
					
				}
			
        }
    }
   public static void main(String[]args){
        check ob = new check();
    } 
    
}
