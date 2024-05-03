package com.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import com.connection.DatabaseConnection;

/**
 * Servlet implementation class EnrollCourse
 */
@WebServlet("/EnrollCourse")
public class EnrollCourse extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseId = request.getParameter("courseId");
		String cname = null;
		String cdescription = null;
		String cfees = null;
		int enroll_id = 0;
		HttpSession session = request.getSession();
		
		try {
			Connection con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from course where course_id='" + courseId + "'");
			while (rs.next()) {
				cname = rs.getString("c_name");
				cdescription = rs.getString("c_description");
				cfees = rs.getString("c_fees");
			}

			int enrollCourse = st.executeUpdate("insert into enroll_course(eid,user_name,phone,email,course_name,course_description,course_fees)values('"
							+ enroll_id + "','" + session.getAttribute("name") + "','" + session.getAttribute("phone")
							+ "','" + session.getAttribute("email") + "','" + cname + "','" + cdescription + "','"
							+ cfees + "')");

			if (enrollCourse > 0) {
				String message="Course enroll successfully.";
				session.setAttribute("course-enroll", message);
				response.sendRedirect("userDashboard.jsp");
			} else {
				response.sendRedirect("userDashboard.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
