<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="reviewer.Reviewer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="reviewer.PendingArticle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pending Articles</title>
<script type="text/javascript">
function home(){
	location.replace("/index.jsp");
}
</script>
</head>
<body>
<a href="reviewerCentre.jsp">Back to reviewer centre</a>
	<% 
		
		
		PendingArticle oldArticle = (PendingArticle)session.getAttribute("ForceToChoose");
		Reviewer r = (Reviewer)session.getAttribute("Reviewer");
		List<PendingArticle> pendingArticles=null;
		if(session.getAttribute("pendingArticles") instanceof List){
			pendingArticles = (ArrayList<PendingArticle>)session.getAttribute("pendingArticles");
		}
		
		int forceToChooseNum = 1;
		String taskTitle="";
		String taskAb="";
		String info = "";
		if(oldArticle==null){
			forceToChooseNum=0;
			info="no task";
		}
		else if(r.getSelectedNum()==3){
			forceToChooseNum=0;
			info="you have selected enough articles to review.\n "
					+"If you want to modify your choice, go back to Reviewer Centre.";
			pendingArticles.clear();
		}
		else{
			taskTitle = oldArticle.getArticleName();
			taskAb = oldArticle.getAbstractContent();
		}
		
		
		int availableSelection=3-forceToChooseNum-r.getSelectedNum();
		
	%>
	<h1>Here are some unpublished articles, you may choose <%=availableSelection %> of them, <%=forceToChooseNum %> forced to choose</h1>
	<div>
		<p><span><br />Available Pending Articles:
		<p><form name="selectArticle" action="ReviewerLogin" method="post">
			
					
				<p>Your review task:<%=taskTitle %></p>
				<p><%=taskAb %></p>
				
			
			<%
				
				
				for(PendingArticle pa:pendingArticles){%>
					<%
						String result = pa.toString();
						String title = pa.getArticleName();
					%>
					<p><%=result %><input type="checkbox" name="pendingArticles" value=<%=title%>></p>
				<% } %>
				<p>
				<input type="submit" name="submit" value="Select Articles">
				<input type="hidden" name="pendingSelection" value="valid"> 
				
				
		</form>
		</span>
		
	</div>
	
	
</body>
</html>