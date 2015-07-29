
package atm;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class signup extends JFrame  implements ActionListener{
    JLabel lusername=new JLabel("User Name");
    JLabel lname=new JLabel(" Name");
    JLabel lpassword=new JLabel("Password");
    JLabel lEmail=new JLabel("E- mail");
    JLabel lDOB=new JLabel("Date Of Birth");
    JLabel lbalance=new JLabel("Initial balance");
    
    
    JTextField tusername= new JTextField();
    JTextField tname= new JTextField();
    JPasswordField tpassword= new JPasswordField();
    JTextField tEmail= new JTextField();
    JTextField day= new JTextField("Day");
    JTextField month= new JTextField("Month");
    JTextField year= new JTextField("Year");
    JTextField tbalance= new JTextField();
    
    JButton bsignup = new JButton("Register");
    
    JPanel pane = new JPanel();
    
             
         Connection cn;
	Statement st;
	PreparedStatement ps;
	ResultSet s;
	PreparedStatement ps1;
    
     
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
    
    
    
    
    public signup()
    {
        setSize(320,350);
	setVisible(true);
	setResizable(false);
	setLocation(400,250);
        setTitle("Register");
        
       
           add(pane);
           pane.setLayout(null);
                lusername.setBounds(5,50,120,25);
		pane.add(lusername);
		tusername.setBounds(125,50,150,25);
		pane.add(tusername);
		
		lname.setBounds(5,85,120,25);
		pane.add(lname);
		tname.setBounds(125,85,150,25);
		pane.add(tname);
		
		lpassword.setBounds(5,120,120,25); 
		pane.add(lpassword);
		tpassword.setBounds(125,120,150,25); 
		pane.add(tpassword);
		
		lEmail.setBounds(5,155,120,25);
		pane.add(lEmail);
		tEmail.setBounds(125,155,150,25);
		pane.add(tEmail);
		
                
                lDOB.setBounds(5,190, 120,25);
                pane.add(lDOB);
                
                day.setBounds(125, 190, 50, 25);
                pane.add(day);
                
                month.setBounds(175, 190, 50, 25);
                pane.add(month);
                
                year.setBounds(225, 190, 50, 25);
                pane.add(year);
		
                bsignup.setBounds(130, 300, 75, 20);
                pane.add(bsignup);
                
                lbalance.setBounds(5, 215, 120, 25);
                pane.add(lbalance);
                tbalance.setBounds(125, 215, 100, 25);
                pane.add(tbalance);
                bsignup.addActionListener(this);
                
         database();
      
        
    }
    
    public void actionPerformed(ActionEvent e){
        boolean x=  true;
        Object source = e.getSource();
        if(source == bsignup)
        {
            String us = tusername.getText();
            String nm = tname.getText();
            String pass = tpassword.getText();
            String mail = tEmail.getText();
            String d = day.getText();
            String m = month.getText();
            String y = year.getText();
            
                  if(us.length() == 0 || nm.length() == 0 || pass.length() == 0 || mail.length() == 0 || d.length() == 0 || m.length() == 0 || y.length() == 0)
                  {
               JOptionPane.showMessageDialog(null,"Some Fields are empty");
                  }
           
           
                  else
                   {
                           
                        
                             try{
	
				st= cn.createStatement();	
                s=st.executeQuery("SELECT * FROM Table1 WHERE username = '" + us + "'");
					
						while(s.next()){
							String us1 =s.getString(1);
							
							JOptionPane.showMessageDialog(null,"User name exist");
                                                        x= false;
                                                }
					                st.close();
                                                
					  
                           }catch(SQLException es)
                           {
                               System.out.print(es);
                           }
                             if(x == true)
                                 
                              try{
                                  st= cn.createStatement();
                                
                                ps = cn.prepareStatement("INSERT INTO Table1 " + " (username,name,password,email,day,month,year,balance) " + " VALUES(?,?,?,?,?,?,?,?)");
                                ps.setString(1,tusername.getText());	
				ps.setString(2,tname.getText());
				ps.setString(3,tpassword.getText());
				ps.setString(4,tEmail.getText());
				ps.setString(5,day.getText());
				ps.setString(6,month.getText());
				ps.setString(7,year.getText());
				ps.setString(8,tbalance.getText());
				
                                ps.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Your New Account has been successfully Created.");
                                st.close();
                                login ob1 = new login();
                                dispose();
                              }catch(SQLException ess)
                              {
                                  System.out.print(ess);
                              }
                             
                   }
        }
       
        
    }
    
    public static void main(String[] args) {
    signup ob = new signup();
                
                
    
        
    }

}
