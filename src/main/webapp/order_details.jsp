<%@ page import="com.tastyExpress.model.Orders" %>
<%@ page import="com.tastyExpress.model.OrderItem" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order Details | Tasty ExpressS</title>
    <link rel="stylesheet" href="order_details.css?v=2">
</head>
<body>

<%
    Orders order = (Orders) request.getAttribute("order");
    List<OrderItem> items = (List<OrderItem>) request.getAttribute("items");
%>

<h2 class="title">Order Details (#<%= order.getOrderId() %>)</h2>

<div class="order-info-box">
    <p><b>Order Date:</b> <%= order.getOrderDate() %></p>
    <p><b>Payment Mode:</b> <%= order.getPaymentMode() %></p>
    <p><b>Status:</b> <%= order.getStatus() %></p>
    <p><b>Total Amount:</b> &#8377;<%= order.getTotalAmount() %></p>
</div>

<h3 class="subtitle">Items Ordered</h3>

<table class="order-table">
    <thead>
        <tr>
            <th>Item</th>
            <th>Qty</th>
            <th>Total</th>
        </tr>
    </thead>

    <tbody>
    <% for (OrderItem oi : items) { %>
        <tr>
            <td><%= oi.getItemName() %></td>
            <td><%= oi.getQuantity() %></td>
            <td>&#8377;<%= oi.getTotalPrice() %></td>
        </tr>
    <% } %>
    </tbody>
</table>

<a href="orderHistory" class="back-btn">&#8592; Back to Orders</a>

</body>
</html>
