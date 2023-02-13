
package com.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/trace")
public class fortrace extends HttpServlet
{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out=res.getWriter();
        try
        {
            String fname=req.getParameter("fname");
            String email=req.getParameter("email");
            String mode=req.getParameter("pmodel");
            String phoneNumber=req.getParameter("pnumber");
            String serial=req.getParameter("snumber");
            String date=req.getParameter("date");
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/theman";
            String user="root";
            String pass="";
            String query="insert into trace (Email,FullNames,PhoneModel,PhoneNumber,SerialNumber,DateLost) values (?,?,?,?,?,?)";
            Connection conn=DriverManager.getConnection(url, user, pass);
            if(conn!=null)
            {
                PreparedStatement st=conn.prepareStatement(query);
                st.setString(1, email);
                st.setString(2, fname);
                st.setString(3, mode);
                st.setString(4, phoneNumber);
                st.setString(5, serial);
                st.setString(6, date);
                st.executeUpdate();
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1 style='font-size:50px';>");
                out.println("Please send M300 and send screenshot of payment to :");
                out.println("1.lcodemafereka064@gmail.com or 58485442 whatsapp<br/>");
                out.println("Make Payment via <br/>");
                out.println("1.Mpesa +26658485442 Luka Mafereka, or <br/>");
                out.println("2.Ecocash +26663711566 Luka Mafereka,or <br/>");
                out.println("</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        catch(ClassNotFoundException ex)
        {
            out.println("Error: "+ex);
        } catch (SQLException ex) {
            Logger.getLogger(fortrace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
