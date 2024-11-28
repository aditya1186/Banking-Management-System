import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkbal")
public class CheckBalance extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
		String accno = req.getParameter("accountNumber");
		String adharno =req.getParameter("aadhaarNumber");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/aditya";
			String username = "root";
			String password = "password@123";
			Connection con = DriverManager.getConnection(url, username, password);

			PreparedStatement ps = con.prepareStatement("select * from bms where accno=? and adharno=?");
			ps.setString(1, accno);
			ps.setString(2, adharno);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) 
			{
//				HttpSession session = req.getSession();
//				session.setAttribute("session_name", rs.getInt("bal"));
				
				int currentBalance = rs.getInt("bal");
				
				resp.setContentType("text/html");
				out.print("<h3 style='color:#fff'>Your Current Balance: " + currentBalance
						+ "</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/checkbalance.jsp");
				rd.include(req, resp);
			}
			 else {
					resp.setContentType("text/html");
					out.print("<h3 style='color:red'> Account Number and Aadhaar Number didn't Match</h3>");

					RequestDispatcher rd = req.getRequestDispatcher("/checkbalance.jsp");
					rd.include(req, resp);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

			resp.setContentType("text/html");
			out.print("<h3 style='color:red'> " + e.getMessage() + "</h3>");

			RequestDispatcher rd = req.getRequestDispatcher("/checkbalance.jsp");
			rd.include(req, resp);
		}
	}
}
