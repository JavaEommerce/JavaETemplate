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
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	  	<link rel="stylesheet" href="css/upload.css" type="text/css" />	
	  	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	  	<link rel="stylesheet" href="css/loginform.css" type="text/css"/>
	  	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" /> 
<title>Review Submission</title>
</head>
<body>
	<%
		List<ReviewingArticle> reviewingArticles = new ArrayList<ReviewingArticle>();
		reviewingArticles = (ArrayList<ReviewingArticle>)session.getAttribute("reviewingArticles");
		String raDisplay= "";
		if(reviewingArticles!=null){
			
			
			if(reviewingArticles.isEmpty()){
				raDisplay = "None";
			}
		}
		
		System.out.println(raDisplay);
	%>
	<div class = "logmod">
	<div class="logmod__wrapper">
		<div class="smart-green">
		<form class="smart-green" name="SubmitReview" action="SubmitReview" method="post">
			<h1>Submit your review here
			<span><a href="index.jsp">Back to home page</a></span></h1>
			<table>
				<tr><td><select name="articleName">
				<%
				if(reviewingArticles!=null){
					String ra="";
					for (ReviewingArticle rArticle : reviewingArticles) {
						ra = rArticle.getArticleName();
						String status = rArticle.getReviewStatus();
						if(status.equalsIgnoreCase("review submitted")){
							ra="--no available article--";
						}
						System.out.println("Available article: "+ra);
						%><option><%=ra %></option> 
				<% }}%>
				</select></td></tr>
			
				<tr><td>Overall judgement: <input type="text" name="overallJudgement" ></td></tr>	
				<tr><td>relevant expertise level: <select name="level" >
					<option>--please select--</option>
					<option>expert</option>
					<option>knowledgeable</option>
					<option>outsider</option>
				</select></td></tr>
				<tr><td>Summary of the content and novel contribution of the article: 
					<input type="text" name="summary" >
				</td></tr>
				
				<tr><td>Criticism: <input type="text" name="criticism" ></td></tr>
				<tr><td>Small Errors: <input type="text" name="smallerrors" ></td></tr>
							
			</table>
			<input type="submit" name="sumit" value="submit">
		</form>
	</div></div></div></div>
	
</body>
</html>