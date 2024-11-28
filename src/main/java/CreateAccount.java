import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createacc")
public class CreateAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        
        String cname = req.getParameter("cname1");
        String fname = req.getParameter("fname1");
        String adharno = req.getParameter("adhar1"); // Changed to String
        String mobno = req.getParameter("mob1");     // Changed to String
        int bal = Integer.parseInt(req.getParameter("dip1"));
        
        // Generate random account number
        Random random = new Random();
        int accno = 100000 + random.nextInt(900000); // Generates a 6-digit random account number
        //int accno = Integer.parseInt(req.getParameter("accno1"));
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/aditya";
            String username = "root";
            String password = "password@123";
            Connection con = DriverManager.getConnection(url, username, password);
            
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO bms(cname, fname, adharno, mob, bal, accno) VALUES(?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, cname);
            ps.setString(2, fname);
            ps.setString(3, adharno); // Set Aadhaar as String
            ps.setString(4, mobno);   // Set Mobile as String
            ps.setInt(5, bal);
            ps.setInt(6, accno);
            
            int count = ps.executeUpdate();
            
            if (count > 0) {
                resp.setContentType("text/html");
                out.print("<h2>Your Details Are Submitted Successfully...</h2>");
                out.print("<h3>Your Account Number is: " + accno + "</h3>"); // Display account number to the user
                
                RequestDispatcher rd = req.getRequestDispatcher("/createaccount.jsp");
                rd.include(req, resp);
            } else {
                resp.setContentType("text/html");
                out.print("<h2>Invalid input</h2>");
                
                RequestDispatcher rd = req.getRequestDispatcher("/createaccount.jsp");
                rd.include(req, resp);
            }
        } catch (Exception e) {
            resp.setContentType("text/html");
            e.printStackTrace();
            
            RequestDispatcher rd = req.getRequestDispatcher("/createaccount.jsp");
            rd.include(req, resp);
        }
    }
}
