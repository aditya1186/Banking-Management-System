<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw Amount</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image"
	href="https://img.freepik.com/premium-vector/banking-finance-conceptual-logo-unique-vector-symbol-banking-system-global-financial-system-circulation-money_570429-35840.jpg">
</head>
<body>
    <form action="withdrawAmount" method="post">
        <h1>Withdraw Amount</h1>
        <!-- Account Number Input -->
        <label for="accountNumber">Account Number:</label>
        <input type="number" id="accountNumber" name="accountNumber" required /> <br>

        <!-- Withdraw Amount Input -->
        <label for="withdrawAmount">Withdraw Amount:</label>
        <input type="number" id="withdrawAmount" name="withdrawAmount" min="1" required /> <br>

        <!-- Submit Button -->
        <input type="submit" value="Withdraw" />
    </form>
</body>
</html>
