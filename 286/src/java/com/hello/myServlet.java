
package com.hello;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myServlet extends HttpServlet
{
    @Override
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
        PrintWriter out=res.getWriter();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String email=req.getParameter("email");
            String password=req.getParameter("password");
            String serial=req.getParameter("serial");
            String url="jdbc:mysql://localhost:3306/theman";
            String pass="";
            String user="root";
            String query="insert into users(Email,Password,Serial) values(?,?,?)";
            
            Connection conn=(Connection) DriverManager.getConnection(url, user, pass);
            
            if(conn!=null)
            {
                PreparedStatement prep=conn.prepareStatement(query);
                prep.setString(1, email);
                prep.setString(2, password);
                prep.setString(3, serial);
                prep.executeUpdate();
                out.println("Your data has been added to the database");
                conn.close();
            }
            
        }
        catch(ClassNotFoundException e )
        {
            out.println("Error: "+e);
        } catch (SQLException ex) {
            Logger.getLogger(myServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
