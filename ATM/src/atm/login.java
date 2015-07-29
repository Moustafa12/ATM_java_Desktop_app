
package atm;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;




public class login extends JFrame implements ActionListener{
        JLabel lname = new JLabel("User Name");
        JLabel lpass = new JLabel("PassWord");
        
        JTextField tname = new JTextField();
        JPasswordField tpass = new JPasswordField();
        
        JButton signin = new JButton("Sing in");
        JButton signup = new JButton("Sing up");
        
        JPanel pane=new JPanel();

     
        Connection cn;
	
	Statement st;
	PreparedStatement ps; 
       
        

   public void database()
   {
       
   
       try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn = DriverManager.getConnection("jdbc:odbc:ATM");
		}catch(ClassNotFoundException e)  {
 			System.out.println("Failed to load driver");
 			
 	
 		}catch(SQLException e){
 			System.out.println("not able to connect");
 			
 			
 		}
        }
   
   
   
   
   public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		 String Name="";
		if(source == signin){
			try{
				String s1=tname.getText();
				String s2=tpass.getText();
				if((s1.length()==0 || s2.length()==0)){
    					JOptionPane.showMessageDialog(null,"Must Fill all Fields");
    				}
    				else{
				st=cn.createStatement();
				String s11="";
				String s22="";
				
				ResultSet s=st.executeQuery("SELECT * FROM Table1 WHERE username='"+s1+"'");
                               
				while(s.next()){
					s11=s.getString(1);
					s22=s.getString(3);
                                        Name =s.getString(2);
				}
				
				
				
				
				
				st.close();
		
				//if(strUser.equals(str1)){
                                if(s1 .equals(s11)){
					if(s2.equals(s22)){
						
					
    					
						JOptionPane.showMessageDialog(null,"Welcome "+Name);
						
						transaction ob = new transaction();
						
						dispose();
						
					
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the password is incorrect!");
						
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!");
					
				}
    				}	
			}catch(SQLException w){
			}
		}else if(source==signup){
			
				signup ob = new signup();
				
				dispose();
		
		}

	}
	 
   
        public login()
        
        {
            
            
                this.setLocation(400,250);
		this.setSize(250,150);
		this.setTitle("Log-In");
		this.setResizable(false);
		this.setVisible(true);
            
            
                lname.setBounds(10,20,80,20);
		lpass.setBounds(10,40,80,20);
		tname.setBounds(100,20,100,20);
		tpass.setBounds(100,40,100,20);
		signin.setBounds(50,70,75,20);
		signup.setBounds(125,70,83,20);
                
                              
            
           pane.setLayout(null);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.add(pane);   
           pane.add(lname);
           pane.add(lpass);
           pane.add(tname);
           pane.add(tpass);
           pane.add(signin);
           pane.add(signup);
           signin.addActionListener(this);
	   signup.addActionListener(this); 
         
           database();
           
} 
    public static void main(String[] args) {
      login ob =new login();
                
                
    
        
    }
}
