<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" type="image"
	href="https://img.freepik.com/premium-vector/banking-finance-conceptual-logo-unique-vector-symbol-banking-system-global-financial-system-circulation-money_570429-35840.jpg">
</head>
<body>
	<form action="createacc" method="post">
		<h1>Create A New Account</h1>
		Customer Name : <input type="text" name="cname1" /> <br>
		Customer's Father Name : <input type="text" name="fname1" /> <br>
		Adhar Number : <input type="number" name="adhar1"> <br>
		Mobile Number : <input type="number" name="mob1"> <br>
		First Deposite Amount : <input type="number" name="dip1"> <br>
		<%-- Enter Account Number : <input type="number" name="accno1"> <br> --%>

		<input type="submit" value="Create Account">
	</form>
</body>
</html>