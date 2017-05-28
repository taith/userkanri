package com.userkanri.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.userkanri.model.User;

@WebServlet(urlPatterns = { "/member" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}
	
	@Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
			String title = "ユーザーの情報";
			request.setAttribute("title", title);
			HttpSession session = request.getSession();
			User loginedUser = UserUtil.getLoginedUser(session);
			if (loginedUser == null) {
		          // ログインページを戻る
		          response.sendRedirect(request.getContextPath() + "/login");
		          return;
		      }
			request.setAttribute("loginedUser", loginedUser);
	       // /jsp/member.jspに行く
	       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/member.jsp");
	       dispatcher.forward(request, response);
	        
	   }
	 
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	       doGet(request, response);
	   }

}
