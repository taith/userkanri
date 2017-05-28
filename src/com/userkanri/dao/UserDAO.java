package com.userkanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userkanri.model.User;

public class UserDAO {
	
	public static User findUser(Connection conn, String email, String password) throws SQLException {
		String sql = "SELECT u.id, u.email, u.name FROM user AS u WHERE u.email = ? AND u.password + ?";

		PreparedStatement prestate = null;
		ResultSet result = null;
		try {
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, email);
			prestate.setString(2, password);
			result = prestate.executeQuery();

			if (result.next()) {
				int id  = result.getInt("id");
				String name = result.getString("name");
				User user = new User();
				user.setId(id);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(result != null ){
				result.close();
			}
			if(prestate != null) {
				prestate.close();
			}
			if(conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public static User findUser(Connection conn, int id) throws SQLException {
		String sql = "SELECT u.email, u.name, u.password, u.created_at, u.updated_at FROM user AS u WHERE u.id = ?";
		PreparedStatement prestate = null;
		ResultSet result = null;
		try {
			prestate = conn.prepareStatement(sql);
			prestate.setInt(1, id);
			result = prestate.executeQuery();
			if (result.next()) {
				String email = result.getString("email");
				String name = result.getString("name");
				String password = result.getString("password");
				String created_at = result.getString("created_at");
				String updated_at = result.getString("updated_at");
				User user = new User();
				user.setId(id);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				user.setCreated_at(created_at);
				user.setUpdated_at(updated_at);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(result != null ){
				result.close();
			}
			if(prestate != null) {
				prestate.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<User> listUserWithRole(Connection conn, String role) {
		String sql = "SELECT u.id, u.email, u.name, u.role, u.created_at FROM user AS u WHERE u.role = ?";
		PreparedStatement prestate;
		List<User> userList = new ArrayList<User>();
		try {
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, role);
			ResultSet result = prestate.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String email = result.getString("email");
				String name = result.getString("name");
				String created_at = result.getString("create_date");
				User user = new User();
				user.setId(id);
				user.setEmail(email);
				user.setName(name);
				user.setCreated_at(created_at);
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<User> listAllUser(Connection conn) throws SQLException {
		String sql = "SELECT u.id, u.email, u.name, u.role, u.created_at FROM user AS u";
		PreparedStatement prestate = null;
		ResultSet result = null;
		List<User> userListAll = new ArrayList<User>();

		try {
			prestate = conn.prepareStatement(sql);
			result = prestate.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String email = result.getString("email");
				String name = result.getString("name");
				String role = result.getString("role");
				String created_at = result.getString("created_at");
				User user = new User();
				user.setId(id);
				user.setEmail(email);
				user.setName(name);
				user.setRole(role);
				user.setCreated_at(created_at);
				userListAll.add(user);
			}
			return userListAll;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(result != null ){
				result.close();
			}
			if(prestate != null) {
				prestate.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void createUser(Connection conn, User user) {
		String sql = "INSERT INTO user(email, name, password, role, enabled,created_at) VALUES (?, ?, ?, ?, 1, ?)";
		try {
			PreparedStatement prestate = conn.prepareStatement(sql);
			prestate.setString(1, user.getEmail());
			prestate.setString(2, user.getName());
			prestate.setString(3, user.getPassword());
			prestate.setString(4, user.getRole());
			prestate.setString(5, user.getCreated_at());
			
			prestate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upadateUser(Connection conn, User user) {
		// String sql = "UPDATE user set name =?, update_at =? where id =? ";

	}

	public static void deleteUser(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM user where id = ?";
		PreparedStatement prestate = null;
		try {
			prestate = conn.prepareStatement(sql);
			prestate.setInt(1, id);
			prestate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(prestate != null) {
				prestate.close();
			}
			if(conn != null) {
				conn.close();
			}
		}

	}
	
	public static void changePassword(Connection conn, String password, int id) throws SQLException {
		String sql = "UPDATE user SET password = ? where id = ?";
		PreparedStatement prestate = null;
		try {
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, password);
			prestate.setInt(2, id);
			prestate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(prestate != null) {
				prestate.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}


}
