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

@WebServlet(urlPatterns = { "/delete/*" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean hasError = false;
		String errorString;
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String stringId = pathParts[1];
		int id = Integer.parseInt(stringId);

		Connection conn = null;
		try {
			conn = MySqlConnect.getConnection();
			UserDAO.deleteUser(conn, id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hasError = true;
		}

		if (hasError) {
			errorString = "Cannot delete";
			request.setAttribute("errorString", errorString);
			response.sendRedirect(request.getContextPath() + "/userInfo/" + id);
		} else {
			response.sendRedirect(request.getContextPath() + "/userList");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
