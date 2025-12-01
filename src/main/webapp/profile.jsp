<%@ page import="com.tastyExpress.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Account | Tasty ExpressS</title>
    <link rel="stylesheet" href="profile.css?v=3">
</head>

<body>

<%
    User user = (User) request.getAttribute("user");
%>

<div class="profile-container">
    <h2 class="title">My Account</h2>

    <% if (request.getAttribute("success") != null) { %>
        <p class="success-msg"><%= request.getAttribute("success") %></p>
    <% } %>

    <form action="profile" method="post" class="profile-form">

        <label>Your Name</label>
        <input type="text" name="userName" value="<%= user.getUserName() %>" required>

        <label>Your Email</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" required>

        <label>Phone</label>
        <input type="text" name="phone" value="<%= user.getPhone() %>" required>

        <label>Address</label>
        <input type="text" name="address" value="<%= user.getAddress() %>" required>

        <label>Password</label>
        <input type="password" name="password" value="<%= user.getPassword() %>" required>

        <button class="save-btn" type="submit">Update Profile</button>
    </form>

    <a href="home" class="back-btn">← Back to Home</a>
</div>

</body>
</html>
