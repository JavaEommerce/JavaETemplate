<%@page import="reviewer.SubmittedReview"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.TreeSet"%>
<%@page import="reviewer.ReviewingArticle"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="loginSystem.User"%>
<%@page import="editor.userInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/editorStyle.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reviewer Centre</title>
<%	
	User currentUser = (User)session.getAttribute("User");  
	String name = currentUser.getUserName();
%>
</head>
<body>

	<%
		List<String> chosenArticles = new ArrayList<String>();
		List<ReviewingArticle> reviewingArticles = new ArrayList<ReviewingArticle>();
		List<SubmittedReview> submittedReviews = new ArrayList<SubmittedReview>();
		submittedReviews.clear();
		chosenArticles.clear();
		reviewingArticles.clear();
		chosenArticles = (ArrayList)session.getAttribute("ChosenArticles");
		reviewingArticles = (ArrayList<ReviewingArticle>)session.getAttribute("reviewingArticles");
		submittedReviews = (ArrayList<SubmittedReview>)session.getAttribute("submittedReviews");
		
		String saDisplay = "";
		String raDisplay= "";
		String srDisplay= "";

		if(chosenArticles!=null&&reviewingArticles!=null&&submittedReviews!=null){
			if(chosenArticles.isEmpty()){
				saDisplay = "None";
			}
			
			if(reviewingArticles.isEmpty()){
				raDisplay = "None";
			}
			if(submittedReviews.isEmpty()){
				srDisplay = "None";
			}
		}
		
		System.out.println(saDisplay);
		System.out.println(raDisplay);
		System.out.println(srDisplay);

	%>
	<section id="body" class="width">
			<aside id="sidebar" class="column-left">

			<header>
				<h1><a href="EditorTest.jsp">Reviewer Center</a></h1>

				<h2>Hello Reviewer <%=name %></h2>
				<h2><a href="index.jsp">Home page</a></h2>
			</header>

			<nav id="mainnav">
  				<ul>
                           			<li ><a href="reviewerIndex.jsp">Review home</a></li>
                           		 	<li  class="selected-item"><a href="ReviewerLogin">Go to reviewer center</a></li>
                 </ul>
			</nav>

			</aside>
			<section id="content" class="column-right">
			<article>
				<h2>this is reviewer center</h2>
				<h3>see the details of the User<h3>
				<li><a href="index.jsp">Home</a></li>
				<li> <a href="AccessUnpublishedArticles">Choose unpublished articles</a></li>
				<li> <a href="SubmitReview.jsp" >Submit Review</a></li>
				
				<br>
				
		</article>
		<article>
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
		</article>
		
		<article>
			<form action="DownloadArticle" name="downloaded" method="post">
			<p>Reviewing Articles: <%=raDisplay%>
			<table>
			<tr>
				<th>Article Name</th>
				<th>Review Status</th>
				<th>DownLoad</th>
			</tr>
			<tr>
				<%
				if(reviewingArticles!=null){
					String ra="";
					String status = "";
					for (ReviewingArticle rArticle : reviewingArticles) {
						ra = rArticle.getArticleName();
						status = rArticle.getReviewStatus();
						%>
						<tr>
						<td><%=ra%></td> 
						<td><%=status %></td>
						<td><input type="submit" name="<%=ra%>" value="Download">
						<input type="hidden" name="downloaded" value="<%=ra%>">
						</td></tr>
						
				<% }
					}%>
		</table>
	</form>
		</article>
		
		<article>
			<form action="Revision" name="submittedReviews" method="post">
		<p>Submitted Reviews: <%=srDisplay%>
		<table>
			<tr>
				<th>Article Name</th>
				<th>Overall judgement</th>
				<th>Reviewer Level</th>
				<th>Summary</th>
				<th>Criticism</th>
				<th>Small errors</th>
				<th>Revise Information from author</th>
				<th>Total revise time</th>
				<th>Revise accepted</th>
			</tr>
			
				<%
				if(submittedReviews!=null){
					

					
					for (SubmittedReview sReview : submittedReviews) {
						String artcileName="";
						String overallJudgement = "";
						String level = "";
						String summary = "";
						String criticism = "";
						String smallerrors = "";
						String reviseInfo = "";
						int reviseTime = 0;
						boolean reviseAccepted = false;
						String raString = "";
						String acc = "";
						String rej = "";
						
						artcileName = sReview.getArticleName();
						overallJudgement = sReview.getOverallJudgement();
						level=sReview.getLevel();
						summary = sReview.getSummary();
						criticism = sReview.getCriticism();
						smallerrors = sReview.getSmallerrors();
						reviseInfo = sReview.getReviseInfo();
						reviseTime = sReview.getReviseTime();
						reviseAccepted = sReview.getReviseAccepted();
						if(reviseAccepted){
							raString = "Accepted";
							
						}
						else{
							raString = "Not Accepted yet";
							if(!reviseInfo.equalsIgnoreCase("")){
								acc="Accept";
								rej="Reject";	
							}
						}
						
						%><tr><td><%=artcileName%></td>  
						<td><%=overallJudgement %></td>
						<td><%=level %></td>
						<td><%=summary %></td>
						<td><%=criticism %></td>
						<td><%=smallerrors %></td>
						<td><%=reviseInfo %></td>
						<td><%=reviseTime %></td>
						<td><%=raString %></td>
						
						
						<td>
						<%if(!acc.equalsIgnoreCase("")){
							
						%>
						<input type="submit" name="revision" value=<%=acc %>>
						<input type="hidden" name="artcileNN" value="<%=artcileName%>">
						<%} %>
						<%if(!rej.equalsIgnoreCase("")){%>
						<input type="submit" name="revision" value=<%=rej %>>
						<input type="hidden" name="artcileNN" value="<%=artcileName%>">
						<%} %>
						<%
						
						
						%>
						
						</td>
						
				<% }}%>
			</tr>
		</table>
	</form>
		</article>
                		
	    <article><h1> </h1><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><h1> </h1></article>

		</section>

		<div class="clear"></div>
	</section>
	<%-- <h1>Welcome to Reviewer Centre!</h1>
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
	
	<form action="Revision" name="submittedReviews" method="post">
		<p>Submitted Reviews: <%=srDisplay%>
		<table>
			<tr>
				<th>Article Name</th>
				<th>Overall judgement</th>
				<th>Reviewer Level</th>
				<th>Summary</th>
				<th>Criticism</th>
				<th>Small errors</th>
				<th>Revise Information from author</th>
				<th>Total revise time</th>
				<th>Revise accepted</th>
			</tr>
			
				<%
				if(submittedReviews!=null){
					String artcileName="";
					String overallJudgement = "";
					String level = "";
					String summary = "";
					String criticism = "";
					String smallerrors = "";
					String reviseInfo = "";
					int reviseTime = 0;
					boolean reviseAccepted = false;
					String raString = "";
					String acc = "";
					String rej = "";

					
					for (SubmittedReview sReview : submittedReviews) {
						artcileName = sReview.getArticleName();
						overallJudgement = sReview.getOverallJudgement();
						level=sReview.getLevel();
						summary = sReview.getSummary();
						criticism = sReview.getCriticism();
						smallerrors = sReview.getSmallerrors();
						reviseInfo = sReview.getReviseInfo();
						reviseTime = sReview.getReviseTime();
						reviseAccepted = sReview.getReviseAccepted();
						if(reviseAccepted){
							raString = "Accepted";
							
						}
						else{
							raString = "Not Accepted yet";
							if(!reviseInfo.equalsIgnoreCase("")){
								acc="Accept";
								rej="Reject";	
							}
						}
						
						%><tr><td><%=artcileName%></td>  
						<td><%=overallJudgement %></td>
						<td><%=level %></td>
						<td><%=summary %></td>
						<td><%=criticism %></td>
						<td><%=smallerrors %></td>
						<td><%=reviseInfo %></td>
						<td><%=reviseTime %></td>
						<td><%=raString %></td>
						
						
						<td>
						<%if(!acc.equalsIgnoreCase("")){%>
						<input type="submit" name="revision" value=<%=acc %>>
						<%} %>
						<%if(!rej.equalsIgnoreCase("")){%>
						<input type="submit" name="revision" value=<%=rej %>>
						<%} %>
						<%
						if(reviseAccepted){
							artcileName=null;
						}
						
						%>
						<input type="hidden" name="submittedReviews" value="<%=artcileName%>">
						</td>
						
				<% }}%>
			</tr>
		</table>
	</form> --%>
	
	
</body>
</html>

