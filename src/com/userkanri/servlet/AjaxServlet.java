package com.userkanri.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.userkanri.dao.MySqlConnect;
import com.userkanri.dao.UserDAO;
import com.userkanri.model.User;

@WebServlet(urlPatterns = { "/ajax" })
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxServlet() {
		super();
	}

	@Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
			
		String id = request.getParameter("id");
		int intID = Integer.parseInt(id);
		String role = request.getParameter("role");
		System.out.println(intID);
		
		Connection conn = null;
		User user = null;
		
		try {
			conn = MySqlConnect.getConnection();
			user = UserDAO.findUser(conn, intID, role);
			if(user == null) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().write("null"); 
			} else {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().write(user.getEmail()); 
			}	
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	   }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
