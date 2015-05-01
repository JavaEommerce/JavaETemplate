<%@page import="reviewer.Reviewer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reviewer Index</title>
</head>
<body>

	<h1>Welcome to reviewer's page</h1>
	<p><a href="index.jsp">Home</a></p>

	

	<%-- 	<%
		//session.invalidate();
	  Reviewer r  = (Reviewer)session.getAttribute("xiaoming");
		System.out.println(r.getTest());
		session.invalidate();
		if(session!=null){
			System.out.println("???");
		}
	%>
	<jsp:useBean id="xiaoming" scope="session" class="reviewer.Reviewer">
		<% System.out.println(xiaoming.getTest()); %>
	</jsp:useBean> --%>
	
	
</body>
</html>