<%@ page import="java.util.List" %>
<%@ page import="com.tastyExpress.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu | Tasty ExpressS</title>
    <link rel="stylesheet" href="menu.css" />
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">
    <div class="logo">Tasty ExpressS</div>
    <nav>
        <ul class="nav-links">
            <li><a href="home">Home</a></li>
            <li><a href="cart.jsp">Cart</a></li>
            <li><a href="profile.jsp">Profile</a></li>
        </ul>
    </nav>
</header>

<!-- MENU SECTION -->
<section class="menu-section">
    <h2 class="menu-title">
        Welcome to 
        <%= (request.getAttribute("restaurantName") != null) 
                ? request.getAttribute("restaurantName") 
                : "Restaurant" %>!
    </h2>

    <p class="sub-title">Explore the delicious menu below</p>

    <div class="menu-list">

        <%
            List<Menu> allMenusByRestaurent = 
                (List<Menu>) request.getAttribute("allMenusByRestaurent");

            if (allMenusByRestaurent != null) {
                for (Menu menu : allMenusByRestaurent) {
        %>

        <!-- SINGLE MENU ITEM -->
        <div class="menu-item">
            
            <img src="<%= menu.getImageUrl() %>"
                 alt="<%= menu.getItemName() %>"
                 class="menu-img" />

            <div class="menu-details">
                
                <h3 class="menu-name"><%= menu.getItemName() %></h3>

                <div class="menu-meta">
                    <span class="veg-dot 
                        <%= (menu.getItemName().toLowerCase().contains("chicken") ||
                             menu.getItemName().toLowerCase().contains("mutton") ||
                             menu.getItemName().toLowerCase().contains("fish") ||
                             menu.getItemName().toLowerCase().contains("egg"))
                              ? "nonveg" : "veg" %>">
                    </span>

                    <span class="rating">
                        <img alt="star-img" src="Images/star.png"> 
                        <%= menu.getRatings() %>
                    </span>
                </div>

                <p class="menu-desc"><%= menu.getDescription() %></p>
                <p class="price">Rs.<%= menu.getPrice() %></p>

            </div>

            <!-- ADD TO CART FORM -->
            <form action="cart" method="post">  
                <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                <input type="hidden" name="quantity" value="1">
                <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>">
                <input type="hidden" name="action" value="add">
                <input class="add-btn" type="submit" value="Add To Cart">
            </form>

        </div>

        <% 
                }  // end for
            } // end if
        %>

    </div>
</section>

<!-- FOOTER -->
<footer class="footer">
    &copy; 2025 Tasty ExpressS | All Rights Reserved
</footer>

</body>
</html>
