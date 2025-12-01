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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDao = new UserDAOImpl();

        User loggedUser = null;

        // ❗ Instead of scanning all users, find user by email
        for (User u : userDao.getAllUsers()) {
            if (u.getEmail().equalsIgnoreCase(email) &&
                u.getPassword().equals(password)) {
                loggedUser = u;
                break;
            }
        }

        if (loggedUser == null) {
            req.setAttribute("error", "❌ Invalid Email or Password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        // Start session
        HttpSession session = req.getSession();
        session.setAttribute("loggedUser", loggedUser);
        session.setAttribute("userId", loggedUser.getUserId());
        session.setAttribute("userName", loggedUser.getUserName());
        session.setAttribute("role", loggedUser.getRole());

        // redirect to home
        resp.sendRedirect("home");
    }
}
