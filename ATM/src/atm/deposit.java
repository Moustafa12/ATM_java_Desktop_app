
package atm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;



public class deposit extends JFrame implements ActionListener{
    JPanel pane = new JPanel();
    JLabel lpass = new JLabel("Password");
    JLabel lbalance = new JLabel("Balance");
    JLabel lnbalance = new JLabel("New balance");
    
    JPasswordField tpass = new JPasswordField();
    JTextField tbalance = new JTextField();
    JTextField tnbalance = new JTextField();
    
    JButton bcheck = new JButton("Deposit");
    JButton bmenu = new JButton("Back Menu");
    
        Connection cn;
	//ResultSet rs;
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
    
    
    
    
    public deposit()
    {
         pane.setLayout(null);
                setLocation(400,250);
		setSize(300,200);
		setTitle("Deposit transaction");
		setResizable(false);
		setVisible(true);
                
                lpass.setBounds(10,20,80,20);
		lbalance.setBounds(10,40,80,20);
		tpass.setBounds(100,20,100,20);
		tbalance.setBounds(100,40,100,20);
                lnbalance.setBounds(10, 60, 80, 20);
                tnbalance.setBounds(100, 60, 100, 20);
                tnbalance.setEditable(false);
               
		
		bcheck.setBounds(80,100,130,20);
                bmenu.setBounds(80,140,140,20);
                bcheck.addActionListener(this);
                bmenu.addActionListener(this);
                
                pane.add(lpass);
                pane.add(lbalance);
                pane.add(tpass);
                pane.add(tbalance);
                pane.add(lnbalance);
                pane.add(tnbalance);
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
        
        else if(source==bcheck){
            if(tpass.getText().length() == 0 || tbalance.getText().length() == 0)
            {
             JOptionPane.showMessageDialog(null,"Please Fill the fields");   
            }
            else
            {
                boolean bb = false;
                try
                {
                                         st= cn.createStatement();	
		 			ResultSet rs=st.executeQuery("SELECT * FROM Table1 WHERE password ='"+tpass.getText()+"'");
						while(rs.next()){
						String s = (rs.getString(8));
							int a = Integer.parseInt(s);
							int b = Integer.parseInt(tbalance.getText());
							
							
							bb = true;
							
							int sum = a+b;
							tnbalance.setText((sum+""));
							tbalance.setText("");
						JOptionPane.showMessageDialog(null,"You Deposited successfully");
                                                
                                                ps = cn.prepareStatement("UPDATE Table1 SET balance = '" + sum +  "'WHERE Password = '" + tpass.getText() + "'");  
                                                ps.executeUpdate();
                                                }
                                                
                                                st.close();
                }catch(SQLException s)
                {
                    System.out.println(s);
                }
                if( bb = false)
                {
                    JOptionPane.showMessageDialog(null,"Your account not found");
                }
            
        }
        }
    }		
    
     public static void main(String[]args){
        deposit ob = new deposit();
    } 

}
