<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<section class="menu-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="navbar-collapse collapse ">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li><a href="userDashboard.jsp">Enroll Course</a></li>
						<li><a href="user-feedback.jsp">Give Feedback</a></li>
						<li><a href="contact-us.jsp">Contact Us</a></li>
						<li><a href="view-user-profile.jsp">View Profile</a></li>
						<li><a href="user-change-password.jsp">Change Password</a></li>
						<li><a href=""><font color="#ff8c00"><%=session.getAttribute("email") %></font></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>