package com.tastyExpress.servlet;

import java.io.IOException;
import java.util.List;

import com.tastyExpress.dao.OrdersDAO;
import com.tastyExpress.dao.OrderItemDAO;
import com.tastyExpress.dao.MenuDAO;
import com.tastyExpress.daoimpl.OrdersDAOImpl;
import com.tastyExpress.daoimpl.OrderItemDAOImpl;
import com.tastyExpress.daoimpl.MenuDAOImpl;
import com.tastyExpress.model.OrderItem;
import com.tastyExpress.model.Orders;
import com.tastyExpress.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderIdStr = req.getParameter("orderId");

        if (orderIdStr == null) {
            resp.sendRedirect("orderHistory");
            return;
        }

        int orderId = Integer.parseInt(orderIdStr);

        OrdersDAO orderDao = new OrdersDAOImpl();
        OrderItemDAO itemDao = new OrderItemDAOImpl();
        MenuDAO menuDao = new MenuDAOImpl();

        Orders order = orderDao.getOrder(orderId);
        List<OrderItem> items = itemDao.getAllOrderItems();

        // Filter only items from this order
        items.removeIf(i -> i.getOrderId() != orderId);

        // Attach menu details (name, price)
        for (OrderItem oi : items) {
            Menu m = menuDao.getMenu(oi.getMenuId());
            oi.setItemName(m.getItemName());
        }

        req.setAttribute("order", order);
        req.setAttribute("items", items);

        RequestDispatcher rd = req.getRequestDispatcher("order_details.jsp");
        rd.forward(req, resp);
    }
}
