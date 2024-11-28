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

@WebServlet("/depositAmount")
public class Deposite extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String accno = req.getParameter("accountNumber");
		String adharno = req.getParameter("aadhaarNumber");
		int depamt = Integer.parseInt(req.getParameter("depositAmount"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/aditya";
			String username = "root";
			String password = "password@123";
			Connection con = DriverManager.getConnection(url, username, password);

			PreparedStatement ps = con.prepareStatement("select * from bms where accno=?");
			ps.setString(1, accno);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve the current balance
				int currentBalance = rs.getInt("bal");

				// Calculate the new balance
				int newBalance = currentBalance + depamt;

				// Update the balance in the database
				PreparedStatement updatePs = con.prepareStatement("UPDATE bms SET bal=? WHERE accno=?");
				updatePs.setInt(1, newBalance);
				updatePs.setString(2, accno);
				
				
				// Log the transaction in the 'transactions' table
				PreparedStatement dTransactionPs = con.prepareStatement(
				    "INSERT INTO transactions (accno, operation_type, amount, balance_after) VALUES (?, ?, ?, ?)"
				);
				dTransactionPs.setString(1, accno);
				dTransactionPs.setString(2, "Deposit");
				dTransactionPs.setInt(3, depamt);
				dTransactionPs.setInt(4, newBalance);
				dTransactionPs.executeUpdate();

				int rowsUpdated = updatePs.executeUpdate();

				if (rowsUpdated > 0) {
					HttpSession session = req.getSession();
					session.setAttribute("new_balance", newBalance);

					resp.setContentType("text/html");
					out.print("<h3 style='color:#fff'>Amount Deposited Successfully. Current Balance: " + newBalance
							+ "</h3>");
				} else {
					resp.setContentType("text/html");
					out.print("<h3 style='color:red'>Failed to update balance. Please try again.</h3>");
				}

				RequestDispatcher rd = req.getRequestDispatcher("/deposite.jsp");
				rd.include(req, resp);


			} else {
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'> Account Number and Aadhaar Number didn't Match</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/deposite.jsp");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();

			resp.setContentType("text/html");
			out.print("<h3 style='color:red'> " + e.getMessage() + "</h3>");

			RequestDispatcher rd = req.getRequestDispatcher("/deposite.jsp");
			rd.include(req, resp);
		}

	}
}
