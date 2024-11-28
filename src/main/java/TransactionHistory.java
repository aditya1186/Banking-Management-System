import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/transactionHistory")
public class TransactionHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int accno = Integer.parseInt(req.getParameter("accno"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/aditya";
            String username = "root";
            String password = "password@123";
            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE accno = ?");
            ps.setInt(1, accno);
            ResultSet rs = ps.executeQuery();
                        
            if (rs.next()) {
                // If data exists, store it in request attributes and forward to JSP
                req.setAttribute("resultSet", rs);
                RequestDispatcher rd = req.getRequestDispatcher("/transactionHistoryStyled.jsp");
                rd.forward(req, resp);
            } else {
                // If no data, display an error message
                resp.setContentType("text/html");
                out.print("<h3 style='color:red; text-align:center;'>No transactions found for Account No: " + accno + "</h3>");
                out.print("<a href='index.html' style='text-decoration:none; color:blue;'>Back to Home</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3 style='color:red'>Error fetching transaction history</h3>");
        }
    }
}
