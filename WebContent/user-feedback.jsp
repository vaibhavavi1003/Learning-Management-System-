<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.connection.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>Course Management System</title>
<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<%
		if (session.getAttribute("email") != null && session.getAttribute("email") != "") {
	%>
	<div class="navbar navbar-inverse set-radius-zero">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">
					<h1>Course Management System</h1>
				</a>
			</div>
			<div class="right-div">
				<a href="logout.jsp" class="btn btn-danger pull-right">LOG ME
					OUT</a>
			</div>
		</div>
	</div>
	<jsp:include page="user-header.jsp"></jsp:include>
	<div class="content-wrapper">
		<div class="container">
			<div class="row pad-botm">
				<div class="col-md-12">
					<h4 class="header-line">Give Feedback</h4>
				</div>
			</div>
			<%
				String feedback = (String) session.getAttribute("feedback");
				if (feedback != null) {
					session.removeAttribute("feedback");
			%>
			<div class="alert alert-info" id="info">Feedback submitted successfully.</div>
			<%
				}
			%>
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<img src="images/user-feedback.jpeg" class="img-thumbnail"
						alt="Cinque Terre" style="width: 500px; height: 430px;">
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="panel panel-info">
						<div class="panel-heading">Give Feedback</div>
						<div class="panel-body">
							<form action="FeedbackDao" method="post">
								<div class="form-group">
									<label>Name.:</label> <input type="text" name="name"
										class="form-control" placeholder="Name">
								</div>
								<div class="form-group">
									<label>Email Id.:</label> <input type="text" name="email"
										class="form-control" placeholder="Email Id">
								</div>
								<div class="form-group">
									<label>Feedback.:</label>
									<textarea name="feedback" class="form-control"></textarea>
								</div>
								<input type="submit" value="Submit Feedback"
									class="btn btn-primary">
							</form>
						</div>
					</div>
				</div>
			</div>
			<!--/.ROW-->
		</div>
	</div>
	<section class="footer-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">&copy; 2024 Course Management System</div>

		</div>
	</div>
	</section>
	<!-- FOOTER SECTION END-->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- CORE JQUERY  -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS  -->
	<script src="assets/js/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS  -->
	<script src="assets/js/custom.js"></script>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})
				(
						window,
						document,
						'script',
						'../../../../../../www.google-analytics.com/analytics.js',
						'ga');

		ga('create', 'UA-58127580-1', 'auto');
		ga('send', 'pageview');
	</script>
	<script type="text/javascript">
		$(function() {
			$('#info').delay(1500).show().fadeOut('slow');
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$('#danger').delay(1500).show().fadeOut('slow');
		});
	</script>
	<%
		} else {
	response.sendRedirect("index.jsp");
	}
	%>
	<script type="text/javascript">
		$(function() {
			$('#success').delay(3000).show().fadeOut('slow');
		});

		$(function() {
			$('#danger').delay(3000).show().fadeOut('slow');
		});

		$(function() {
			$('#warning').delay(3000).show().fadeOut('slow');
		});

		$(function() {
			$('#info').delay(3000).show().fadeOut('slow');
		});
	</script>
</body>
</html>