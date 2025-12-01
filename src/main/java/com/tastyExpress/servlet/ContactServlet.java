package com.tastyExpress.servlet;

import java.io.IOException;

import com.tastyExpress.dao.ContactDAO;
import com.tastyExpress.daoimpl.ContactDAOImpl;
import com.tastyExpress.model.ContactMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String message = req.getParameter("message");

        ContactMessage msg = new ContactMessage(name, email, phone, message);

        ContactDAO dao = new ContactDAOImpl();
        dao.saveMessage(msg);

        req.setAttribute("success", "Message sent successfully!");
        req.getRequestDispatcher("contact.jsp").forward(req, resp);
    }
}
