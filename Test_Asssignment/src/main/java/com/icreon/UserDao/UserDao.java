package com.icreon.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.icreon.model.User;

@Service
public class UserDao {

	@Autowired
	User users;
	

	private static Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public UserDao() {
		
	}

	public static User getUserByUsernameAndPassword(String username, String password) {
		User user = null;

		try {

			String query = "select * from login where username =? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				user = new User();

                user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}
}
