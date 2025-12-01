package com.tastyExpress.servlet;

import java.io.IOException;
import java.sql.*;
import java.sql.Timestamp;

import com.tastyExpress.model.Cart;
import com.tastyExpress.model.CartItem;
import com.tastyExpress.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // ✔ USER LOGIN CHECK
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // ✔ CART CHECK
        Cart cart = (Cart) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("cartRestaurantId");

        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        String paymentMode = req.getParameter("paymentMode");
        int totalPrice = cart.getTotalPrice();

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);  // START TRANSACTION

            // -------------------------------
            // INSERT ORDER INTO `orders`
            // -------------------------------
            String insertOrderSQL =
                    "INSERT INTO orders (userId, restaurentId, orderDate, totalAmount, status, paymentMode) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement orderStmt =
                    con.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);

            orderStmt.setInt(1, userId);
            orderStmt.setInt(2, restaurantId);
            orderStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            orderStmt.setInt(4, totalPrice);
            orderStmt.setString(5, "Placed");
            orderStmt.setString(6, paymentMode);

            orderStmt.executeUpdate();

            // GET AUTO orderId
            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // ---------------------------------
            // INSERT ORDER ITEMS INTO orderitem
            // ---------------------------------
            String insertItemSQL =
                    "INSERT INTO orderitem (orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?)";

            PreparedStatement itemStmt = con.prepareStatement(insertItemSQL);

            for (CartItem item : cart.getItems().values()) {

                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, item.getItemId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setInt(4, item.getTotalPrice());

                itemStmt.addBatch();
            }

            itemStmt.executeBatch();

            con.commit();

            // CLEAR CART
            session.removeAttribute("cart");
            session.removeAttribute("cartRestaurantId");

            // REDIRECT TO success page
            resp.sendRedirect("orderSuccess.jsp?orderId=" + orderId);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (Exception ignore) {}

            resp.sendRedirect("checkout.jsp?error=OrderFailed");
        }
    }
}
