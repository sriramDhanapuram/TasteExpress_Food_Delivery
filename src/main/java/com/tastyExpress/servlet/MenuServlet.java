package com.tastyExpress.servlet;

import java.io.IOException;
import java.util.List;

import com.tastyExpress.dao.MenuDAO;
import com.tastyExpress.dao.RestaurentDAO;
import com.tastyExpress.daoimpl.MenuDAOImpl;
import com.tastyExpress.daoimpl.RestaurentDAOImpl;
import com.tastyExpress.model.Menu;
import com.tastyExpress.model.Restaurent;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Validate restaurantId parameter
        String ridParam = req.getParameter("restaurantId");
        if (ridParam == null || ridParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "restaurantId is missing");
            return;
        }

        int restaurantId = Integer.parseInt(ridParam);

        MenuDAO menuDAO = new MenuDAOImpl();
        RestaurentDAO restDAO = new RestaurentDAOImpl();

        // Fetch all menus
        List<Menu> allMenusByRestaurent = menuDAO.getAllMenusByRestaurent(restaurantId);

        // Fetch restaurant data
        Restaurent restaurent = restDAO.getRestaurent(restaurantId);

        if (restaurent == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Restaurant not found");
            return;
        }

        // Set JSP attributes
        req.setAttribute("allMenusByRestaurent", allMenusByRestaurent);
        req.setAttribute("restaurantName", restaurent.getName());

        // Forward to JSP
        RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
        rd.forward(req, resp);
    }
}
