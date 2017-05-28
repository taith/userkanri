package com.userkanri.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.userkanri.dao.MySqlConnect;
import com.userkanri.dao.UserDAO;
import com.userkanri.model.User;

@WebServlet(urlPatterns = { "/doLogin" })
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoLoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String errorString = null;
		boolean hasError = false;

		User user = null;
		Connection conn = null;

		if (email == null || password == null || email.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "メールとパスワードを入力してください";
		} else {
			try {
//				Context context = new InitialContext();
//				DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/userkanri");
				conn = MySqlConnect.getConnection();
				user = UserDAO.findUser(conn, email, password);
				if (user == null) {
					hasError = true;
					errorString = "メールとパスワードが間違います";
				}
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}

		if (hasError) {
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			// /jsp/login.jspに戻る
			
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/jsp/login.jsp");

			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			UserUtil.storeLoginedUser(session, user);
			// /jsp/member.jspに行く
			response.sendRedirect(request.getContextPath() + "/member");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
