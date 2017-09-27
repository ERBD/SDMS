
package sdms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;


public class teacherMain extends JFrame{
public String course_code;
JFrame f__dash; 
JButton b__cc;
JPanel p__course;

    // JDBC driver name and database URL 
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/SDMS";
   
   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   
    public teacherMain() {
        
        //Frame declaration
        f__dash=new JFrame("SDMS");
        f__dash.setBounds(100,100,1100,1000);
        f__dash.setLayout(null);
        f__dash.setVisible(true);
        f__dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p__course = new JPanel();
        
        
        b__cc = new JButton();

        
        JButton b__add_course=new JButton("Add Course");
        b__add_course.setBounds(0, 0, 100, 50);
        b__add_course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JTextField cc = new JTextField();
                JTextField nos = new JTextField();
                
                Object[] obj = {
                    "Course Code", cc,
                    "Number of students", nos
                };
                
                int op = JOptionPane.showConfirmDialog(null, obj, "Course Details", JOptionPane.OK_CANCEL_OPTION);
                if(op==JOptionPane.OK_OPTION){
                    
                    String cc_get = cc.getText();
                    String nos_get = nos.getText();
                    b__cc = addCourseButton(cc_get);
                    JLabel l=new JLabel("Testing");
                    l.setBounds(100, 100, 100, 100);
                    p__course.add(l);
                    f__dash.add(b__cc);
                }
               
//                String[] dept = {"CE","CSE","EECE","IP","AE"};
//                String s1 = (String) JOptionPane.showInputDialog(b__add_course,"Select Department", "Course Details",JOptionPane.INFORMATION_MESSAGE,null,dept,dept[0]);
            }
        });
        
        
        
        f__dash.add(b__add_course);
        f__dash.add(p__course);
        
    
    }
    
    
    public JButton addCourseButton(String course_name){
        JButton b__create = new JButton(course_name);
        b__create.setBounds(0, 50, 100, 50);
        b__create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 course_code = b__create.getText();
                 String course_title = course_data(course_code);
                 JLabel l__cc = new JLabel("Welcome to "+ course_title);
                 
                 
                 //course_data cd = new course_data(course_code);
                 
                 l__cc.setBounds(200, 200, 300, 200);
                 
                 p__course.setBounds(100,0, 1100, 1000);
                 p__course.setBackground(Color.lightGray);
                 p__course.add(l__cc);
            }
        });
        return b__create;
    }
    
    public String course_data(String c_code){
        
        Connection conn = null;
        Statement stmt = null;
        String c_title = new String();
        try{
             //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String query = "select course_title from course_list where course_code ='" + c_code +"'"; 
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println("Connected database successfully...");
                System.out.println(rs.getString("course_title"));
                c_title = rs.getString("course_title");
                
            }
            
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return c_title;
    }



    
}

