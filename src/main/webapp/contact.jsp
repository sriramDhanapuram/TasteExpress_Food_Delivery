<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us | TastyExpressS</title>
    <link rel="stylesheet" href="contact.css?v=1.0">
</head>

<body>

<div class="contact-container">

<a href="home" class="back-home-btn">← Back to Home</a>
    
    <h2 class="title">Contact Us</h2>
    <p class="sub">We’d love to hear from you! Please fill the form below.</p>

    <form action="contact" method="post" class="contact-form">

        <label>Your Name</label>
        <input type="text" name="name" required placeholder="Enter your name">

        <label>Email</label>
        <input type="email" name="email" required placeholder="Enter your email">

        <label>Phone</label>
        <input type="text" name="phone" maxlength="10" placeholder="Enter phone number">

        <label>Your Message</label>
        <textarea name="message" required placeholder="Write your message..."></textarea>

        <button class="send-btn" type="submit">Send Message</button>
    </form>

    <% 
        String msg = (String) request.getAttribute("success");
        if (msg != null) { 
    %>
        <p class="success-msg">&#9989; <%= msg %></p>
        
    <% } %>

</div>

</body>
</html>
