package com.connection;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

public class DatabaseConnection {
	static Connection con;

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningsystem?useSSL=false", "root", "root");
	}

	public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
		ResultSet rs = null;
		try {
			con = getConnection();
			rs = con.createStatement().executeQuery(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int insertUpdateFromSqlQuery(String SqlQueryString) {
		int i = 2;
		try {
			if (con == null) {
				getConnection();
			}
			i = con.createStatement().executeUpdate(SqlQueryString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}
	
	public static int generateUserId() {
		int uid = 0;
		Random random=new Random();
		uid = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("User Id " + uid);
		return uid;
	}
}