<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Success | Tasty ExpressS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f8fff2;
            text-align: center;
            padding-top: 100px;
        }
        .box {
            width: 450px;
            margin: auto;
            background: white;
            border-radius: 12px;
            padding: 30px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }
        .tick {
            font-size: 60px;
            color: green;
        }
        .order-id {
            font-size: 18px;
            margin-top: 10px;
            color: #555;
        }
        .btn {
            display: inline-block;
            margin-top: 25px;
            padding: 12px 25px;
            background: #ff6b00;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
        }
        .btn:hover {
            background: #e55c00;
        }
    </style>
</head>

<body>

<div class="box">
    <div class="tick">&#9989;</div>
    <h2>Order Placed Successfully!</h2>

    <p class="order-id">
        Your Order ID: <strong><%= request.getParameter("orderId") %></strong>
    </p>

    <p>Thank you for ordering with Tasty ExpressS!</p>

    <a href="home" class="btn">Back to Home</a>
</div>

</body>
</html>
