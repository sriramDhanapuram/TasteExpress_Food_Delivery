<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up | Tasty ExpressS</title>
    <link rel="stylesheet" href="signup.css?v=1.0">
</head>
<body>

<div class="signup-container">
    <h2>Create Account</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error-msg"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="signup" method="post">

        <input type="text" name="userName" placeholder="Full Name" required>
        
        <input type="email" name="email" placeholder="Email Address" required>
        
        <input type="password" name="password" placeholder="Password" required>

        <input type="text" name="phone" placeholder="Phone Number" required>

        <input type="text" name="address" placeholder="Address" required>

        <select name="role" required>
            <option value="Customer">Customer</option>
        </select>

        <button type="submit" class="signup-btn">Sign Up</button>

        <p class="login-link">Already have an account? <a href="login.jsp">Login</a></p>
    </form>
</div>

</body>
</html>
