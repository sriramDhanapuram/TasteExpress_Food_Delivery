package com.tastyExpress.servlet;

import java.io.IOException;
import java.util.List;

import com.tastyExpress.dao.OrdersDAO;
import com.tastyExpress.daoimpl.OrdersDAOImpl;
import com.tastyExpress.model.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // User must be logged in
        if (session == null || session.getAttribute("loggedUser") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        OrdersDAO dao = new OrdersDAOImpl();
        List<Orders> orderList = dao.getAllOrders();  // ❗ TEMP → filter next

        // Filter only user's orders
        orderList.removeIf(o -> o.getUserId() != userId);

        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("orderHistory.jsp").forward(req, resp);
    }
}
