package com.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.sql.*;
import com.connection.DatabaseConnection;

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/UserAccount")
public class UserAccount extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "F:/project-workspace/CourseManagementSystem/WebContent/uploads/";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String imageName = null;
				String uid = null;
				String username = null;
				String userphone = null;
				String useremail = null;
				String useraddress = null;
				String password = null;

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));

						FileItem userid = (FileItem) multiparts.get(0);
						uid = userid.getString();
						

						FileItem name = (FileItem) multiparts.get(1);
						username = name.getString();
						

						FileItem phoneno = (FileItem) multiparts.get(2);
						userphone = phoneno.getString();
						

						FileItem email = (FileItem) multiparts.get(3);
						useremail = email.getString();
						

						FileItem address = (FileItem) multiparts.get(4);
						useraddress = address.getString();
						

						FileItem upass = (FileItem) multiparts.get(5);
						password = upass.getString();
						

					}
				}
				try {

					int id = 0;
					String imagePath = UPLOAD_DIRECTORY + imageName;
					Connection con = DatabaseConnection.getConnection();
					Statement st = con.createStatement();
					int i = st.executeUpdate("insert into user(user_id,name,phone,email,address,password,upload_photo,photo_name) values('"
									+ uid + "','" + username + "','" + userphone + "','" + useremail + "','"
									+ useraddress + "','" + password + "','" + imagePath + "','" + imageName + "')");

					if (i > 0) {
						String success = "User account created successfully.";
						session.setAttribute("message", success);
						response.sendRedirect("user-register.jsp");
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
