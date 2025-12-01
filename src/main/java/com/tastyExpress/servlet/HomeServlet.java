package com.tastyExpress.servlet;

import java.io.IOException;
import java.util.List;

import com.tastyExpress.dao.RestaurentDAO;
import com.tastyExpress.daoimpl.RestaurentDAOImpl;
import com.tastyExpress.model.Restaurent;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurentDAO impl = new RestaurentDAOImpl();
		
		List<Restaurent> allRestaurents = impl.getAllRestaurents();
		
//		for (Restaurent restaurent : allRestaurents) {
//			System.out.println(restaurent);
//		}
		
		req.setAttribute("allRestaurents", allRestaurents);
		
		RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
	}
	
	
	
}
