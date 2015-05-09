<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<!-- Bootstrap Core CSS file -->
		<link rel="stylesheet" href="css/bootstrap.min.css">

		<!-- Override CSS file - add your own CSS rules -->
		<link rel="stylesheet" href="css/styles.css">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result Page</title>
</head>
<body>
Search Result:

<%
  System.out.println("New page");
 ArrayList<String> names = new ArrayList<String>();
	if(session.getAttribute("articlenamelist") instanceof List){
		names = (ArrayList<String>)session.getAttribute("namelist");
	}
	for(String na : names){
		System.out.println(na);
	%>
 	<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
	   <td><%=na %></td>
		<td><form action="EditorArticleDetail" method="post">
		<input type="text" name="appointname" value = /> 
		<input type="submit" value="viewDetail"/></form> </td>
		</tr>

	<%}%>





</body>
</html>