package com.userkanri.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.userkanri.dao.MySqlConnect;
import com.userkanri.dao.UserDAO;
import com.userkanri.model.User;

@WebServlet(urlPatterns = { "/userList" })
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserListServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorString = null;
		List<User> listUser = null;
		Connection conn = null;

			try {
				conn = MySqlConnect.getConnection();
				listUser = UserDAO.listAllUser(conn);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorString = e.getMessage();
			} 
			
			request.setAttribute("errorString", errorString);
		    request.setAttribute("userList", listUser);
		    String title = "利用者一覧";
		    request.setAttribute("title", title);
		    RequestDispatcher dispatcher = request.getServletContext()
		              .getRequestDispatcher("/jsp/userList.jsp");
		      dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
