<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Tasty ExpressS</title>

    <link rel="stylesheet" href="login.css?v=1.0">
</head>

<body>

<div class="login-box">
    <div class="title">Tasty ExpressS Login</div>

    <% 
        String error = (String) request.getAttribute("error");
        if (error != null) { 
    %>
        <p class="error-msg"><%= error %></p>
    <% } %>

    <form action="login" method="post">

        <label>Email</label>
        <input type="email" name="email" placeholder="Enter email" required>

        <label>Password</label>
        <input type="password" name="password" placeholder="Enter password" required>

        <button class="login-btn" type="submit">Login</button>
    </form>

    <p class="signup-link">
        Don’t have an account? <a href="signup.jsp">Sign Up</a>
    </p>
</div>

</body>
</html>
