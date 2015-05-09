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
		String raDisplay= "";
		if(chosenArticles!=null&&reviewingArticles!=null){
			if(chosenArticles.isEmpty()){
				saDisplay = "None";
			}
			
			if(reviewingArticles.isEmpty()){
				raDisplay = "None";
			}
		}
		
		System.out.println(saDisplay);
		System.out.println(raDisplay);
	%>
	<h1>Welcome to Reviewer Centre!</h1>
	<div class="container">
		<div id="menubar"> 
			<li><a href="index.jsp">Home</a></li>
			<li> <a href="AccessUnpublishedArticles">Choose unpublished articles</a></li>
			<li> <a href="SubmitReview.jsp" >Submit Review</a></li>
		</div>
	
	
	
	<form action="DownloadArticle" name="selected" method="post">
		<p>Selected Articles: <%=saDisplay %>
		<ol>
			<%
			
			if(chosenArticles!=null){
				String ca="";
				for (String cArticle : chosenArticles) {
					ca = cArticle;
					%><li><%=ca%><input type="submit" name="confirm" value="Confirm & Download">
					<input type="submit" name="cancel" value="cancel">
					<input type="hidden" name="selected" value="<%=ca%>"></li>
					
			<% }}
			
			%>
		</ol>
		<input type="hidden" name="selected" value="valid"> 
	</form>
	
	<form action="DownloadArticle" name="downloaded" method="post">
		<p>Reviewing Articles: <%=raDisplay%>
		<table>
			<tr>
				<td>Article Name</td>
				<td>Review Status</td>
				<td>DownLoad</td>
			</tr>
			<tr>
				<%
				if(reviewingArticles!=null){
					String ra="";
					String status = "";
					for (ReviewingArticle rArticle : reviewingArticles) {
						ra = rArticle.getArticleName();
						status = rArticle.getReviewStatus();
						%><td><%=ra%></td> <td><%=status %></td> <td><input type="submit" name="<%=ra%>" value="Download">
						<input type="hidden" name="downloaded" value="<%=ra%>">
						</td>
						
				<% }}%>
			</tr>
		</table>
	</form>
	
	<form action="SubmitReview" name="submittedReviews" method="post">
		<p>Submitted Reviews: <%=raDisplay%>
		<table>
			<tr>
				<td>Article Name</td>
				<td>Review Status</td>
				<td>DownLoad</td>
			</tr>
			<tr>
				<%
				if(reviewingArticles!=null){
					String ra="";
					String status = "";
					for (ReviewingArticle rArticle : reviewingArticles) {
						ra = rArticle.getArticleName();
						status = rArticle.getReviewStatus();
						%><td><%=ra%></td> <td><%=status %></td> <td><input type="submit" name="<%=ra%>" value="Download">
						<input type="hidden" name="downloaded" value="<%=ra%>">
						</td>
						
				<% }}%>
			</tr>
		</table>
	</form>
	
	
</body>
</html>

