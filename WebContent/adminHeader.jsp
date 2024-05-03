<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<section class="menu-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="navbar-collapse collapse ">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li><a href="admin-dashboard.jsp">Dashboard</a></li>
						<li><a href="admin-add-course.jsp">Add Course</a></li>
						<li><a href="admin-view-course.jsp">View Courses</a></li>
						<li><a href="admin-view-users.jsp"> View Users</a></li>
						<li><a href="admin-view-feedback.jsp">View Feedback</a></li>
						<li><a href="admin-view-contacts.jsp">View Contacts</a></li>
						<li><a href="changeAdminPassword.jsp">Change Password</a></li>
						<li><a href=""><font color="#ff8c00"><%=session.getAttribute("email") %></font></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>