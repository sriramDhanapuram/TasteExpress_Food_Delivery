<%@ page import="java.util.List" %>
<%@ page import="com.tastyExpress.model.Orders" %>
<%@ page import="com.tastyExpress.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Orders | TastyExpressS</title>
    <link rel="stylesheet" href="orderHistory.css?v=1">
</head>
<body>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Orders> orders = (List<Orders>)request.getAttribute("orders");
%>

<h2 class="title">My Orders</h2>
<a href="home" class="back-btn">&larr;Back to Home</a>

<% if (orders == null || orders.isEmpty()) { %>

    <p>No orders found.</p>

<% } else { %>

    <div class="order-list">
    <% for (Orders o : orders) { %>
    <div class="order-card">
        <h3>Order #<%= o.getOrderId() %></h3>
        <p><strong>Date:</strong> <%= o.getOrderDate() %></p>
        <p><strong>Total:</strong> &#8377;<%= o.getTotalAmount() %></p>
        <p><strong>Status:</strong> <%= o.getStatus() %></p>

        <!-- ✅ ADD THIS BUTTON HERE -->
        <a href="orderDetails?orderId=<%= o.getOrderId() %>" class="view-btn">
            View Details &#8594;
        </a>

    </div>
    <% } %>
</div>


<% } %>

</body>
</html>
