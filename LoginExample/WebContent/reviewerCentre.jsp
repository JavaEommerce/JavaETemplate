<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
		List<String> chosenArticles = new ArrayList<String>();
		List<ReviewingArticle> reviewingArticles = new ArrayList<ReviewingArticle>();
		chosenArticles = (ArrayList)session.getAttribute("ChosenArticles");
		reviewingArticles = (ArrayList<ReviewingArticle>)session.getAttribute("reviewingArticles");
		String saDisplay = "";
		if(chosenArticles.isEmpty()){
			saDisplay = "None";
		}
		System.out.println(saDisplay);
	%>
	<h1>Welcome to Reviewer Centre!</h1>
	<p><a href="index.jsp">Home</a> <a href="AccessUnpublishedArticles">Choose unpublished articles</a>
	<a href="reviewSubmission.jsp">Submit Review</a>
	
	<form action="ReviewerLogin" name="forms" method="post">
		<p>Selected Articles: <%=saDisplay %>
		<ol>
			<%
			
			if(chosenArticles!=null){
				String ca="";
				for (String cArticle : chosenArticles) {
					ca = cArticle;
					%><li><%=ca%><input type="submit" name="<%=ca%>" value="Confirm & Download"></li>
					
			<% }}
			
			%>
		</ol>
	</form>
	
	<form action=""></form>
	
	
</body>
</html>

