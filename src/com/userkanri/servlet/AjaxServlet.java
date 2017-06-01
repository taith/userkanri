package com.userkanri.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
		
		Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
		
		try {
			conn = MySqlConnect.getConnection();
			user = UserDAO.findUser(conn, intID, role);
			JsonElement userObj = gson.toJsonTree(user);
			if(user == null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().write("null"); 
			} else {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				myObj.add("userObj", userObj);
//				response.getWriter().write(userObj);
				PrintWriter outPrintWriter = response.getWriter();
				outPrintWriter.print(userObj);
				outPrintWriter.close();
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
