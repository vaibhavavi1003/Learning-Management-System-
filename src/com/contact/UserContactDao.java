package com.contact;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class UserContactUs
 */
@WebServlet("/UserContactUs")
public class UserContactDao extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		int status = 0;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneno = request.getParameter("phoneno");
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		try {

			Contact c = new Contact();
			c.setName(name);
			c.setEmail(email);
			c.setPhoneno(phoneno);
			c.setMessage(message);

			Connection con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into contact values(?,?,?,?,?,?)");
			ps.setString(1, (String) session.getAttribute("user_id"));
			ps.setString(2, c.getName());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPhoneno());
			ps.setString(5, c.getMessage());
			ps.setInt(6, id);

			status = ps.executeUpdate();
			if (status > 0) {
				String success="Thank you for contacting with us, we will get back and touch with you.";
				session.setAttribute("contact-us", success);
				response.sendRedirect("contact-us.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
