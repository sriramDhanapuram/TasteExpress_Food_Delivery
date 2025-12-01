<%@ page import="java.util.List" %>
<%@ page import="com.tastyExpress.model.Restaurent" %>
<%@ page import="com.tastyExpress.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>TastyExpressS | Home</title>
    <link rel="stylesheet" href="home.css?v=25" />
</head>

<body>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    List<Restaurent> allRestaurents =
        (List<Restaurent>) request.getAttribute("allRestaurents");
%>

<!-- ================= NAVBAR ================= -->
<header class="navbar">
    <div class="nav-left">
        <h2 class="logo">
            <img src="Images/logo.png" class="logo-img" />
            TastyExpressS
        </h2>
    </div>

    <nav class="nav-links">

        <% if (loggedUser != null) { %>
            
            <a href="profile" class="icon">&#128100; <%= loggedUser.getUserName() %></a>
            <a href="logout" class="icon logout">Logout</a>
        <% } else { %>
            <a href="login.jsp" class="icon">&#128100; Login</a>
        <% } %>
        <a href="orderHistory" class="icon">&#128230; Orders</a>
        <a href="contact.jsp">Contact</a>

        <a href="cart.jsp" class="icon">&#128722;</a>
    </nav>
</header>


<!-- ================= HERO SECTION ================= -->
<section class="hero">
    <div class="hero-left">
        <h1>Delicious Food Delivered<br>To Your Doorstep</h1>
        <p>Fresh. Fast. Easy. Explore the best restaurants around you.</p>

        <a href="#restaurants" class="cta-btn">Order Now &#8594;</a>
    </div>

    <div class="hero-right">
        <img src="Images/hero.png" alt="Food illustration">
    </div>
</section>


<!-- ================= CATEGORY SCROLL ================= -->
<div class="category-scroll">
    <div class="cat-card">&#127829; Pizza</div>
    <div class="cat-card">&#127828; Burgers</div>
    <div class="cat-card">&#127836; Chinese</div>
    <div class="cat-card">&#127857; North Indian</div>
    <div class="cat-card">&#127843; Sushi</div>
    <div class="cat-card">&#127856; Desserts</div>
    <div class="cat-card">&#129380; Beverages</div>
    <div class="cat-card">&#9749; Coffee</div>
    <div class="cat-card">&#129367; Healthy</div>
    <div class="cat-card">&#127791; Rolls</div>
</div>


<!-- ================= RESTAURANT LIST ================= -->

<%
    String[] offerList = {
        "Flat 50% OFF",
        "Flat 40% OFF",
        "Save &#8377;120",
        "Buy 1 Get 1 Free",
        "Flat 30% OFF + Free Delivery",
        "&#8377;75 OFF on Orders Above &#8377;199",
        "Special Deal: 20% OFF"
    };
    java.util.Random r = new java.util.Random();
%>

<section class="restaurants-container" id="restaurants">

<% if (allRestaurents != null) {
       for (Restaurent restaurent : allRestaurents) { %>

    <div class="restaurant-card" id="restaurants"
         onclick="location.href='menu?restaurantId=<%= restaurent.getRestaurentId() %>';">

        <div class="rest-img-box">
            <img src="<%= restaurent.getImagePath() %>" class="rest-img" />

            <!-- OFFER BANNER -->
            <div class="offer-banner">
                <span class="offer-text">
                    <%= offerList[r.nextInt(offerList.length)] %>
                </span>
            </div>

            <!-- FREE DELIVERY BADGE -->
            <span class="badge free-delivery">Free Delivery</span>

            <!-- VEG / NON-VEG BADGE -->
            <span class="badge <%= restaurent.getIsVeg().toLowerCase() %>">
                <%= restaurent.getIsVeg() %>
            </span>

            <!-- TRENDING BADGE -->
            <span class="badge trending">TRENDING</span>
        </div>

        <div class="rest-content">
            <h3 class="rest-name"><%= restaurent.getName() %></h3>

            <div class="rest-meta">
                <span class="rating">&#11088; <%= restaurent.getRating() %></span>
                <span class="eta">&#9201; <%= restaurent.getEta() %> min</span>
            </div>

            <p class="cuisine">&#127869; <%= restaurent.getCusineType() %></p>
            <p class="address">&#128205; <%= restaurent.getAddress() %></p>
        </div>

    </div>

<% } } else { %>

    <p>No restaurants found.</p>

<% } %>

</section>


<!-- ================= FOOTER ================= -->
<footer class="footer">

    <div class="footer-container">

        <!-- BRAND -->
        <div class="footer-col">
            <h3 class="footer-logo">
                <img src="Images/logo.png" class="footer-logo-img" />
                TastyExpressS
            </h3>
            <p>Your favourite food, delivered fast and fresh.</p>
        </div>

        <!-- QUICK LINKS -->
        <div class="footer-col">
            <h4>Quick Links</h4>
            <ul>
                <li><a href="home">Home</a></li>
                <li><a href="#restaurants">Restaurants</a></li>
                <li><a href="#">Offers</a></li>
                <li><a href="contact.jsp">Contact</a></li>
            </ul>
        </div>

        <!-- SUPPORT -->
        <div class="footer-col">
            <h4>Support</h4>
            <ul>
                <li><a href="#">Help Center</a></li>
                <li><a href="#">Track Order</a></li>
                <li><a href="#">Cancellation Policy</a></li>
                <li><a href="#">Refund Policy</a></li>
            </ul>
        </div>

        <!-- CONTACT -->
        <div class="footer-col">
            <h4>Contact Us</h4>
            <a href="mailto:sriramdhanapuram@gmail.com" class="contact-link">
                &#128231; sriramdhanapuram@gmail.com
            </a>
            <a href="tel:+919392505326" class="contact-link">
                &#128222; +91 93925 05326
            </a>

            <div class="social-links">
                <h3>Follow Us</h3>
                <div class="social-icons">
                    <a href="https://x.com/sriram_ran68789/"
                       target="_blank"
                       class="social-icon">
                        <img src="Images/Twitter.png" width="32" />
                    </a>

                    <a href="https://www.instagram.com/sriram_dhanapuram/"
                       target="_blank"
                       class="social-icon">
                        <img src="Images/insta.png" width="32" />
                    </a>
                </div>
            </div>
        </div>

    </div>

    <hr class="footer-line">

    <p class="footer-copy">
        &copy; 2025 TastyExpressS &mdash; All Rights Reserved.
    </p>

</footer>

</body>
</html>
