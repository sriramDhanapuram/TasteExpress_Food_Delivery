package com.tastyExpress.servlet;

import java.io.IOException;

import com.tastyExpress.dao.UserDAO;
import com.tastyExpress.daoimpl.UserDAOImpl;
import com.tastyExpress.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");

        req.setAttribute("user", loggedUser);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("loggedUser");

        // Read updated fields
        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        // Update values
        user.setUserName(name);
        user.setEmail(email);
        user.setPhone(Long.parseLong(phone));
        user.setAddress(address);
        user.setPassword(password);

        // Update DB
        UserDAO dao = new UserDAOImpl();
        dao.updateUser(user);

        // Update session
        session.setAttribute("loggedUser", user);

        req.setAttribute("user", user);
        req.setAttribute("success", "Profile updated successfully!");

        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
