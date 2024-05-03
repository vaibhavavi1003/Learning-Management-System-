<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.connection.*"%>
<%@ page import="java.sql.*"%>
<%

	int eid=Integer.parseInt(request.getParameter("eid"));

	int deleteEnrollCourse=DatabaseConnection.insertUpdateFromSqlQuery("delete from enroll_course where eid='"+eid+"'");
	if(deleteEnrollCourse>0){
		String message="Enroll course deleted.";
		session.setAttribute("deleteEntroll", message);
		response.sendRedirect("check-user-courses-enroll-list.jsp");
	}

%>