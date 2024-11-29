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
@WebServlet("/withdrawAmount")
public class Withdraw extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String accno = req.getParameter("accountNumber");
		String adharno = req.getParameter("aadhaarNumber");
		int withdrawamt = Integer.parseInt(req.getParameter("withdrawAmount"));

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
				
				 // Check if withdrawal amount exceeds the current balance
			    if (withdrawamt > currentBalance) {
			        resp.setContentType("text/html");
			        //out.print("<h3 style='color:red'>Insufficient balance. Withdrawal amount exceeds current balance.</h3>");
			        req.setAttribute("errorMessage", "Insufficient balance. Withdrawal amount exceeds current balance.");
			        
			        RequestDispatcher rd = req.getRequestDispatcher("/withdraw.jsp");
			        rd.include(req, resp);
			        return; // Stop further execution
			    }

				// Calculate the new balance
				int newBalance = currentBalance - withdrawamt;

				// Update the balance in the database
				PreparedStatement updatePs = con.prepareStatement("UPDATE bms SET bal=? WHERE accno=?");
				updatePs.setInt(1, newBalance);
				updatePs.setString(2, accno);
				
				// Log the transaction
			    PreparedStatement logTransactionPs = con.prepareStatement(
			        "INSERT INTO transactions (accno, operation_type, amount, balance_after) VALUES (?, ?, ?, ?)"
			    );
			    logTransactionPs.setString(1, accno);
			    logTransactionPs.setString(2, "Withdrawal");
			    logTransactionPs.setInt(3, withdrawamt);
			    logTransactionPs.setInt(4, newBalance);
			    logTransactionPs.executeUpdate();

				int rowsUpdated = updatePs.executeUpdate();

				if (rowsUpdated > 0) {
					HttpSession session = req.getSession();
					session.setAttribute("new_balance", newBalance);

					resp.setContentType("text/html");
					out.print("<h3 style='color:#fff'>Amount Withdrawed Successfully. Current Balance: " + newBalance
							+ "</h3>");
				} else {
					resp.setContentType("text/html");
					out.print("<h3 style='color:red'>Failed to update balance. Please try again.</h3>");
				}

				RequestDispatcher rd = req.getRequestDispatcher("/withdraw.jsp");
				rd.include(req, resp);


			} else {
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'> Account Number didn't Match</h3>");

				RequestDispatcher rd = req.getRequestDispatcher("/withdraw.jsp");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();

			resp.setContentType("text/html");
			out.print("<h3 style='color:red'> " + e.getMessage() + "</h3>");

			RequestDispatcher rd = req.getRequestDispatcher("/withdraw.jsp");
			rd.include(req, resp);
		}

	}
}
