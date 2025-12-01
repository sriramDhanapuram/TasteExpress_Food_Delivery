<%@ page import="com.tastyExpress.model.Cart" %>
<%@ page import="com.tastyExpress.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout | Tasty ExpressS</title>

    <link rel="stylesheet" href="checkout.css?v=1.1">
</head>

<body>

		<%
    // FORCE LOGIN BEFORE CHECKOUT
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
		

<header class="navbar">
    <div class="logo">Tasty ExpressS</div>
</header>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("cartRestaurantId");

    if (cart == null || cart.getItems().isEmpty()) {
%>
        <p style="font-size:20px; text-align:center; margin-top:40px;">Your cart is empty.</p>
<%
        return;
    }
%>

<section class="checkout-container">

    <h2 class="title">Checkout</h2>

    <!-- ONLY PAYMENT + SUMMARY (because DB has no customerName, phone, address) -->
   <form action="<%= request.getContextPath() %>/placeOrder" method="post">

        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">

        <label>Payment Mode</label>
        <select name="paymentMode" required class="payment-select">
            <option value="COD">Cash on Delivery</option>
            <option value="UPI">UPI</option>
            <option value="CARD">Card</option>
        </select>

        <h3 class="subtitle">Order Summary</h3>

        <div class="summary-box">
            <% for (CartItem item : cart.getItems().values()) { %>
                <div class="summary-item">
                    <span><%= item.getItemName() %> x <%= item.getQuantity() %></span>
                    <span>&#8377;<%= item.getTotalPrice() %></span>
                </div>
            <% } %>

            <div class="summary-total">
                <span>Total:</span>
                <span>&#8377;<%= cart.getTotalPrice() %></span>
            </div>
        </div>

        <button type="submit" class="place-btn">Place Order</button>

    </form>

</section>

</body>
</html>
