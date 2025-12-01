package com.tastyExpress.servlet;

import java.io.IOException;

import com.tastyExpress.dao.MenuDAO;
import com.tastyExpress.daoimpl.MenuDAOImpl;
import com.tastyExpress.model.Cart;
import com.tastyExpress.model.CartItem;
import com.tastyExpress.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Simply forward to JSP
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // action must NOT be null
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        int menuId = Integer.parseInt(req.getParameter("menuId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));

        Cart cart = (Cart) session.getAttribute("cart");
        Integer currentRestaurantId = (Integer) session.getAttribute("cartRestaurantId");

        // New restaurant OR first time → reset cart
        if (cart == null || currentRestaurantId == null ||
                !currentRestaurantId.equals(restaurantId)) {

            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("cartRestaurantId", restaurantId);
        }

        MenuDAO menuDAO = new MenuDAOImpl();

        switch (action) {

            case "add":
                Menu menu = menuDAO.getMenu(menuId);

                if (menu != null) {
                    CartItem item = new CartItem(
                            menu.getMenuId(),
                            menu.getItemName(),
                            menu.getPrice(),
                            quantity
                    );
                    cart.addItem(item);
                }
                break;

            case "update":
                if (quantity <= 0) {
                    cart.removeItem(menuId);
                } else {
                    cart.updateItem(menuId, quantity);
                }
                break;

            case "remove":
                cart.removeItem(menuId);
                break;

            default:
                break;
        }

        resp.sendRedirect("cart.jsp");
    }
}
