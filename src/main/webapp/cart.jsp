<%@ page import="java.util.Map" %>
<%@ page import="com.tastyExpress.model.Cart" %>
<%@ page import="com.tastyExpress.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cart | Tasty ExpressS</title>
    <link rel="stylesheet" href="cart.css?v=20">
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">
    <div class="logo">Tasty ExpressS</div>

    <nav class="nav-links">
        <a href="home">Home</a>
        <a href="menu?restaurantId=<%= session.getAttribute("cartRestaurantId") %>">Menu</a>
        <a href="cart.jsp" class="active">Cart</a>
    </nav>
</header>


<!-- BACK BUTTON -->
<div class="back-btn-container">
   <%-- <a href="menu?restaurantId=<%= session.getAttribute("cartRestaurantId") %>" class="back-btn">
    	    &larr; Back to Menu
    </a> --%> 
     <a href="home" class="back-btn">&larr;Back to Home</a>
</div>


<section class="cart-container">
    <h2 class="section-title">Your Cart</h2>

    <%
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
    %>

        <p class="empty-msg">Your cart is empty.</p>

    <%
        } else {
            for (CartItem item : cart.getItems().values()) {
    %>

    <!-- SINGLE CART ITEM -->
    <div class="cart-item">

        <div class="item-info">
            <h3><%= item.getItemName() %></h3>
            <p class="price">&#8377;<%= item.getPrice() %></p>
        </div>

        <div class="item-actions">

            <!-- DECREASE -->
            <form action="cart" method="post" class="inline-form">
                <input type="hidden" name="menuId" value="<%= item.getItemId() %>">
                <input type="hidden" name="restaurantId" value="<%= session.getAttribute("cartRestaurantId") %>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                <button class="qty-btn">&#45;</button>
            </form>

            <span class="qty"><%= item.getQuantity() %></span>

            <!-- INCREASE -->
            <form action="cart" method="post" class="inline-form">
                <input type="hidden" name="menuId" value="<%= item.getItemId() %>">
                <input type="hidden" name="restaurantId" value="<%= session.getAttribute("cartRestaurantId") %>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                <button class="qty-btn">&#43;</button>
            </form>

            <!-- REMOVE -->
            <form action="cart" method="post" class="inline-form">
                <input type="hidden" name="menuId" value="<%= item.getItemId() %>">
                <input type="hidden" name="restaurantId" value="<%= session.getAttribute("cartRestaurantId") %>">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="quantity" value="0">
                <button class="remove-btn">&#128465;</button>
            </form>

        </div>

    </div>

    <%
            } // for loop end
        } // if cart not empty end
    %>

</section>


<!-- FLOATING CHECKOUT BAR -->
<%
    if (cart != null && !cart.getItems().isEmpty()) {
%>
    <div class="checkout-bar">
        <div class="checkout-left">
            <span class="total-label">Total:</span>
            <span class="total-price">&#8377;<%= cart.getTotalPrice() %></span>
        </div>

        <a href="checkout" class="checkout-btn">
            Proceed &rarr;
        </a>
    </div>
<%
    }
%>



<!-- FOOTER -->
<footer class="footer">
    &copy; 2025 Tasty ExpressS | All Rights Reserved
</footer>

</body>
</html>
