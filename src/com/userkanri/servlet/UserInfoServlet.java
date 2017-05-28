package com.userkanri.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.userkanri.dao.MySqlConnect;
import com.userkanri.dao.UserDAO;
import com.userkanri.model.User;

@WebServlet(urlPatterns = { "/userInfo/*" })
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
		super();
	}
	
	@Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
			
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String stringId = pathParts[1];
		int id = Integer.parseInt(stringId);
		
		Connection conn = null;
		User userInfo = null;
		try {
			conn = MySqlConnect.getConnection();
			userInfo = UserDAO.findUser(conn, id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("userInfo", userInfo);
		
		String title = "ユーザーの情報";
			request.setAttribute("title", title);
	       // /jsp/home.jspに行く
	       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/userInfo.jsp");
	        
	       dispatcher.forward(request, response);
	        
	   }
	 
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	       doGet(request, response);
	   }

}
