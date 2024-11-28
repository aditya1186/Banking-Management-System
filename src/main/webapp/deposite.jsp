<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit Amount</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image"
	href="https://img.freepik.com/premium-vector/banking-finance-conceptual-logo-unique-vector-symbol-banking-system-global-financial-system-circulation-money_570429-35840.jpg">
</head>
<body>
    <form action="depositAmount" method="post">
        <h1>Deposit Amount</h1>
        <!-- Account Number Input -->
        <label for="accountNumber">Account Number:</label>
        <input type="number" id="accountNumber" name="accountNumber" required /> <br>

        <!-- Deposit Amount Input -->
        <label for="depositAmount">Deposit Amount:</label>
        <input type="number" id="depositAmount" name="depositAmount" min="1" required /> <br>

        <!-- Submit Button -->
        <input type="submit" value="Deposit" />
    </form>
</body>
</html>
