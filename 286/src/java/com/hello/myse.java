
package com.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myse extends HttpServlet
{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out=res.getWriter();
        try
        {
            String email=req.getParameter("email");
            String password=req.getParameter("password");
            String phoneNumber=req.getParameter("number");
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/theman";
            String pass="";
            String user="root";
            String query="select * from users";
            String query1="insert into numbers (Contacts) values(?)";
            String imei=null;
            int i=1;
            
            Connection conn=DriverManager.getConnection(url, user, pass);
            if(conn!=null)
            {
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery(query);
                
                while(rs.next())
                {
                   String mail=rs.getString(1);
                   String passwe=rs.getString(2);
                   imei=rs.getString(3);
                   if(mail.equals(email)&&passwe.equals(password))
                    {
                       out.println("<html>");
                       out.println("<head>");
                       out.println("</head>");
                       out.println("<body>");
                       out.println("<h1 style='font-size:55px';>");
                       out.println("Please send proof of Payment of M20 to get Serial Number via:");
                       out.println("1.Mpesa +26658485442 Luka Mafereka, or");
                       out.println("2.Ecocash +26663711566 Luka Mafereka,or");
                       out.println("3.lcodemafereka064@gmail.com");
                       out.println("</h1>");
                       out.println("</body>");
                       out.println("</html>");
                    }
                    else if(mail.equals(email)&&(passwe == null ? password != null : !passwe.equals(password)))
                    {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1 style='font-size:55px';>");
                        out.println("Password is incorrect");
                        out.println("</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    else if(!mail.equals(email)&&(passwe == null ? password != null : !passwe.equals(password)))
                    {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1 style='font-size:55px';>");
                        out.println("User does not exist");
                        out.println("</h1>");
                        out.println("</body>");
                        out.println("</html>");
                        
                    }
                }
                
                PreparedStatement prep=conn.prepareStatement(query1);
                prep.setString(1, phoneNumber);
                prep.executeUpdate();
                conn.close();
            }
            
        }
        catch(ClassNotFoundException ex)
        {
            out.println("Error: "+ex);
        } catch (SQLException ex) {
            Logger.getLogger(myse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
