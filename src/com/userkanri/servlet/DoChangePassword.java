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

@WebServlet(urlPatterns = { "/doChangePassword" })
public class DoChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoChangePassword() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String now_password = request.getParameter("password");
		String new_password = request.getParameter("new-password");
		String confirm_password = request.getParameter("confirm-password");
		String errorString = null;
		boolean hasError = false;

		Connection conn = null;

		if (now_password.length() == 0 || new_password.length() == 0 || confirm_password.length() == 0 ) {
			hasError = true;
			errorString = "パスワードを入力してください";
		} else {
			HttpSession session = request.getSession();
			User loginedUser = UserUtil.getLoginedUser(session);
			User userCheck = null;
			try {
				conn = MySqlConnect.getConnection();
				userCheck = UserDAO.findUser(conn, loginedUser.getId());
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!userCheck.getPassword().equals(now_password)) {
				hasError = true;
				errorString = "現在のパスワードは間違います";
			} else {
				if(!new_password.equals(confirm_password)) {
					hasError = true;
					errorString = "パスワードとパスワード確認は間違います";
				} else {
					try {
						conn = MySqlConnect.getConnection();
						UserDAO.changePassword(conn, new_password, loginedUser.getId());
					} catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		if (hasError) {
			request.setAttribute("errorString", errorString);
			// /jsp//changePassword.jspに戻る
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/jsp//changePassword.jsp");

			dispatcher.forward(request, response);
		} else {
			// /jsp/login.jspに行く
			response.sendRedirect(request.getContextPath() + "/member");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
