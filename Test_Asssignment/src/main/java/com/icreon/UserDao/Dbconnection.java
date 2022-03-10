package com.icreon.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class Dbconnection {

	

	public static Connection con ;
	public PreparedStatement pst;
		
		public static Connection getConnection() {

			String url = "jdbc:mysql://localhost:3306/newdb";
			String username = "root";
			String password = "icreon123#";
			
			try {
				if(con==null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
				}
				} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			} return con ;
		
		}
}
