package com.course;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AddCourseDao
 */
@WebServlet("/AddCourseDao")
public class AddCourseDao extends HttpServlet {

	private final String UPLOAD_DIRECTORY = "F:/project-workspace/CourseManagementSystem/WebContent/uploads/";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String courseResources = null;
				int cid = 0;
				String cname = null;
				String cdescp = null;
				String cfees = null;

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						courseResources = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + courseResources));

						FileItem courseName = (FileItem) multiparts.get(0);
						cname = courseName.getString();

						FileItem description = (FileItem) multiparts.get(1);
						cdescp = description.getString();

						FileItem fees = (FileItem) multiparts.get(2);
						cfees = fees.getString();
					}
				}
				try {

					int id = 0;
					String imagePath = UPLOAD_DIRECTORY + courseResources;
					Connection con = DatabaseConnection.getConnection();
					Statement st = con.createStatement();
					int i = st.executeUpdate(
							"insert into course(course_id,c_name,c_description,c_fees,c_resource,c_resource_name) values('"
									+ cid + "','" + cname + "','" + cdescp + "','" + cfees + "','" + imagePath + "','"
									+ courseResources + "')");

					if (i > 0) {
						String success = "Course added successfully.";
						session.setAttribute("add-course", success);
						response.sendRedirect("admin-add-course.jsp");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
	}
}
