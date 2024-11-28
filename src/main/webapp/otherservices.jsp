<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Other Services</title>
<link rel="icon" type="image"
	href="https://img.freepik.com/premium-vector/banking-finance-conceptual-logo-unique-vector-symbol-banking-system-global-financial-system-circulation-money_570429-35840.jpg">
</head>
<style>
    /* General Body Styling */
    body {
        font-family: 'Poppins', sans-serif;
        background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
        color: white;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        flex-direction: column;
    }

    /* Container Styling */
    .container {
        background-color: rgba(255, 255, 255, 0.1);
        border-radius: 15px;
        padding: 30px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
        text-align: center;
        width: 300px;
    }

    .container h1 {
        margin-bottom: 20px;
        font-size: 24px;
        color: #ffffff;
        text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
    }

    /* Link Styling */
    a {
        display: block;
        background: linear-gradient(135deg, #ffffff 0%, #e4e5e6 100%);
        color: #6a11cb;
        text-decoration: none;
        padding: 12px 20px;
        margin: 10px 0;
        border-radius: 25px;
        font-size: 16px;
        font-weight: bold;
        text-align: center;
        transition: all 0.3s ease;
    }

    a:hover {
        background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
        color: white;
        box-shadow: 0 5px 15px rgba(106, 17, 203, 0.5);
        transform: scale(1.05);
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        .container {
            width: 90%;
        }
        a {
            font-size: 14px;
            padding: 10px 15px;
        }
    }
</style>

<body>
<h3> Welcome : ${session_name} </h3>
	 <div class="container">
        <h1>Other Banking Services</h1>
	<a href="deposite.jsp">Deposite</a>
	<a href="withdraw.jsp">Withdraw</a>
    <a href="checkbalance.jsp">Check Balance</a>
    <a href="transactionHistory.jsp">Check Transaction History</a>
</div>
</body>
</html>