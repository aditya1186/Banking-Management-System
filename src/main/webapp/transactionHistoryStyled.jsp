<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }
        h2 {
            text-align: center;
            margin-top: 20px;
            color: #555;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            background-color: #fff;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .back-button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
            font-size: 16px;
        }
        .back-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
	 <h2>Transaction History</h2>
    <%
        // Fetch the account number dynamically from the request
        String accno = request.getParameter("accno");
        if (accno != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/aditya";
                String username = "root";
                String password = "password@123";
                Connection con = DriverManager.getConnection(url, username, password);

                PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE accno = ? order by timestamp");
                ps.setInt(1, Integer.parseInt(accno));
                ResultSet rs = ps.executeQuery();

                out.print("<table>");
                out.print("<tr><th>Operation</th><th>Amount</th><th>Balance After</th><th>Timestamp</th></tr>");

                while (rs.next()) {
                    out.print("<tr>");
                    
                    out.print("<td>" + rs.getString("operation_type") + "</td>");
                    out.print("<td>" + rs.getDouble("amount") + "</td>");
                    out.print("<td>" + rs.getDouble("balance_after") + "</td>");
                    out.print("<td>" + rs.getTimestamp("timestamp") + "</td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            } catch (Exception e) {
                out.print("<h3 style='color:red; text-align:center;'>Error fetching transaction history</h3>");
                e.printStackTrace();
            }
        } else {
            out.print("<h3 style='color:red; text-align:center;'>Account number is missing</h3>");
        }
    %>
    <a href="index.html" class="back-button">Back to Home</a>

</body>
</html>
