package com.feedback;

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
 * Servlet implementation class FeedbackDaoo
 */
@WebServlet("/FeedbackDao")
public class FeedbackDao extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			int id = 0;
			int status = 0;
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String feedback=request.getParameter("feedback");
			
			Feedback f = new Feedback();
			f.setName(name);
			f.setEmail(email);
			f.setFeedback(feedback);
			
			
			HttpSession session = request.getSession();
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into feedback values(?,?,?,?,?)");
			ps.setString(1, (String) session.getAttribute("user_id"));
			ps.setString(2, f.getName());
			ps.setString(3, f.getEmail());
			ps.setInt(4, id);
			ps.setString(5, f.getFeedback());
			
			status = ps.executeUpdate();
			if(status>0) {
				String message="Feedback submitted successfully.";
				session.setAttribute("feedback", message);
				response.sendRedirect("user-feedback.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
