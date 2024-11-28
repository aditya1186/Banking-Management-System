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

@WebServlet("/loginForm")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();

		String cname = req.getParameter("number1");
		String mob = req.getParameter("adhar1");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/aditya";
			String username = "root";
			String password = "password@123";
			Connection con = DriverManager.getConnection(url, username, password);

			PreparedStatement ps = con.prepareStatement("select * from bms where cname=? and mob=?");
			ps.setString(1, cname);
			ps.setString(2, mob);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("session_name", rs.getString("cname"));

				RequestDispatcher rd = req.getRequestDispatcher("/otherservices.jsp");
				rd.include(req, resp);
			} else {
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'> Account Number and Adhar Number didn't Match</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.include(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();

			resp.setContentType("text/html");
			out.print("<h3 style='color:red'> " + e.getMessage() + "</h3>");

			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
	}
}
