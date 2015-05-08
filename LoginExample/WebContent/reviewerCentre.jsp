<%@page import="java.util.HashSet"%>
<%@page import="java.util.TreeSet"%>
<%@page import="reviewer.ReviewingArticle"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reviewer Centre</title>
</head>
<body>

	<%
		Set<String> chosenArticles = new HashSet<String>();
		Set<ReviewingArticle> reviewingArticles = new HashSet<ReviewingArticle>();
		chosenArticles = (HashSet)session.getAttribute("chosenArticles");
		reviewingArticles = (HashSet<ReviewingArticle>)session.getAttribute("reviewingArticles");
		
	%>
	<h1>Welcome to Reviewer Centre!</h1>
	<p><a href="index.jsp">Home</a>
	
	<form action="ReviewerLogin">
		<p>Selected Articles: 
		<ol>
			<%
			
			if(chosenArticles!=null){
				String ca="";
				for (String cArticle : chosenArticles) {
					ca = cArticle;
					%><li><%=ca%><input type="submit" name="<%=ca%>" value="Confirm & Downoad"></li>
				
			<% }}%>
		</ol>
	</form>
	
	
	
	<p><a href="AccessUnpublishedArticles">Choose unpublished articles</a>
</body>
</html>

