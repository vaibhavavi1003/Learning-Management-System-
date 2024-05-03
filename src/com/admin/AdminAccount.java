package com.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AdminAccountCreate
 */
@WebServlet("/AdminAccount")
public class AdminAccount extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("upass");
		HttpSession session=request.getSession();
		try {
			Connection con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			int adminAccount=st.executeUpdate("insert into admin(admin_id,name,email,password) values('"+id+"','"+name+"','"+email+"','"+password+"')");
			if(adminAccount>0) {
				String success = "Admin account created successfully.";
				session.setAttribute("message", success);
				response.sendRedirect("admin-register.jsp");
			}else {
				response.sendRedirect("admin-register.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
