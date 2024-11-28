<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image"
	href="https://img.freepik.com/premium-vector/banking-finance-conceptual-logo-unique-vector-symbol-banking-system-global-financial-system-circulation-money_570429-35840.jpg">
</head>
<body>
	 <h1>View Transaction History</h1>
    <form action="transactionHistory" method="get">
        <label for="accno">Account Number:</label>
        <input type="number" id="accno" name="accno" required>
        <input type="submit" value="View History">
    </form>
</body>
</html>