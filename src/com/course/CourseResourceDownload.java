package com.course;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import com.connection.DatabaseConnection;

/**
 * Servlet implementation class CourseResourceDownload
 */
@WebServlet("/CourseResourceDownload")
public class CourseResourceDownload extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String courseId = request.getParameter("courseId");
			String sql = "select * from course where course_id= ?";
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, courseId);
			ResultSet result = stat.executeQuery();
			if (result.next()) {

				String file = result.getString("c_resource");
				File f = new File(file);
				String filename = f.getName();
				String type = getMimeType("file:" + file);

				response.setContentType(type);
				response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

				String name = f.getName().substring(f.getName().lastIndexOf("/") + 1, f.getName().length());
				InputStream in = new FileInputStream(f);
				ServletOutputStream outs = response.getOutputStream();

				int bit = 256;
				int i = 0;
				try {
					while ((bit) >= 0) {
						bit = in.read();
						outs.write(bit);
					}
				} catch (IOException ioe) {
					ioe.printStackTrace(System.out);
				}
				outs.flush();
				outs.close();
				in.close();

			}
		} catch (Exception e) {
			System.out.println("prolbem in dao dwonload");
			e.printStackTrace();
		}
	}

	public static String getMimeType(String fileUrl) throws java.io.IOException, MalformedURLException {
		String type = null;
		URL u = new URL(fileUrl);
		URLConnection uc = null;
		uc = u.openConnection();
		type = uc.getContentType();
		return type;
	}

}
