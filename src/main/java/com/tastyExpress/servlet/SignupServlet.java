package com.tastyExpress.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.tastyExpress.dao.UserDAO;
import com.tastyExpress.daoimpl.UserDAOImpl;
import com.tastyExpress.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long phone = Long.parseLong(req.getParameter("phone"));
        String address = req.getParameter("address");
        String role = req.getParameter("role"); // Only "Customer"

        UserDAO dao = new UserDAOImpl();

        // Check duplicates
        for (User u : dao.getAllUsers()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                req.setAttribute("error", "Email already exists!");
                req.getRequestDispatcher("signup.jsp").forward(req, resp);
                return;
            }
        }

        // Create new user
        User user = new User();
        user.setUserId((int) (System.currentTimeMillis() % 100000)); // simple unique id
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));

        dao.addUser(user);

        // Auto login
        HttpSession session = req.getSession();
        session.setAttribute("loggedUser", user);
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("role", user.getRole());

        resp.sendRedirect("home");
    }
}
