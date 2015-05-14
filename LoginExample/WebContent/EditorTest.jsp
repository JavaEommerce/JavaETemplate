<%@page import="editor.EditorCharacter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="editor.EditorAllArticlesInfo" %>
<%@ page import="loginSystem.User"%>
<%@ page import="editor.userInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}
table.hovertable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.hovertable tr {
	background-color:#d4e3e5;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Editor Center</title>
<link rel="stylesheet" href="css/editorStyle.css" type="text/css" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
        <script src="http://code.jquery.com/jquery-latest.js">   
        </script>
        <script>
            $(document).ready(function() {                        
                $('#submit').click(function(event) {  
                    var username=$('#user').val();
                 $.get('EditorRetireAndAppointOne',{user:username},function(responseText) { 
                        $('#welcometext').text(responseText);         
                    });
                });
            });
            
            $(document).ready(function() {                        
                $('#submit_2').click(function(event) {  
                 $.get('EditorAccessToAllArticle',function(responseText) { 
                        $('#article_name').text(jQuery.parseJson(responseText));         
                    });
                });
            }); 
            
        </script>
</head>
<body>
<%	
	User currentUser = (User)session.getAttribute("User");  
	String name = currentUser.getUserName();
%>
		<section id="body" class="width">
			<aside id="sidebar" class="column-left">
			<header>
				<h1><a href="EditorTest.jsp">Editor Center</a></h1>
				<h2>Hello Editor <%=name %></h2>
				<h2><a href="index.jsp">Home page</a></h2>
			</header>
			<nav id="mainnav">
  				<ul>
  						       		<li class="selected-item"><form id="formArticles" atcion = "EditorAccessToAllArticle" method="get">
                            		<input type="hidden" id="submit_article" name="article" value="Articles"/>
                            		<a href="EditorTest.jsp" onclick="this.parentNode.submit()">Articles</a></li>
                            		</form>
                            		
                           		 	<li><a href="EditUser.jsp">Users</a></li>
                           		 	
                            		<li><form id="form2" action = "EditorGetAllJournals" method = "post" >
									<input type = "hidden" id="submit_2" name="journal" value="Edit Journal Page"/>
									<a href="EditJournal.jsp" onclick="this.parentNode.submit()">Journals</a>
									</form></li>
									
                            		<li><a href="EditorWaitingArticle.jsp">Waiting Article List</a></li>
                            		<li><a href="EditorAllReviewList.jsp">Reviews</a></li>
                </ul>
			</nav>
			</aside>
			<section id="content" class="column-right">
			
		<article>
				<h2>Show all aritlces</h2>
				<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
			<table >
			<tr><th>Article name</th><th>Article Abstract</th><th>Reviewed Times</th><th>Is published</th><th>Detail link</th><th>Publish article</th></tr>
			<%
				List<EditorAllArticlesInfo> articles=new ArrayList<EditorAllArticlesInfo>();
				if(session.getAttribute("allArticles") instanceof List){
					articles = (ArrayList<EditorAllArticlesInfo>)session.getAttribute("allArticles");
				}
				System.out.println(articles.toString());
				for(EditorAllArticlesInfo pa : articles){
				
					String title = pa.getArticleName();
					String abstracT = pa.getArticleAbstract();
					String reviewedTime = pa.getArticleProfileUrl();
					String articlePublished = pa.getArticleIsPublished();
					System.out.println(title+abstracT+reviewedTime);
					
				%>
				<tr>
					<td><%=title %></td><td><%=abstracT %></td><td><%=reviewedTime %></td><td><%=articlePublished %></td>
					<%-- <td><form action="EditorArticleDetail" method="post">
					<input type="text" name="appointname" value = <%=title%>/> 
					<input type="submit" value="viewDetail"/></form> </td> --%>
					<td><a href = "DealArticleInfo?articlename=<%=title %>">Detail</a></td>
					<td><a href = "EditorSetArticlePublished?articlename=<%=title%>">Publish</a></td>
				</tr>
				<%}%>
				<p><input class="btnExample" type="submit" name="submit" value="Show articles"></p>
				<!-- <input type="hidden" name="pendingSelection" value="valid">  -->
		</table>				
		</form>
			</article>
			<footer class="clear">
				 <%! private int count = 0; %>
    			<P>Hello! Today's date is
      			<%= new java.util.Date() %>
    			</p><p>You have asked for the date<%= ++count %> times since the server was last restarted.</p>
			</footer>
		<article>
		<h1> </h1><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><h1> </h1>
		<article>
		
		</section>

		<div class="clear"></div>

	</section>
	

</body>
<%-- <body>
<h1 style= center >Editor Page</h1>
<%	
	User currentUser = (User)session.getAttribute("User");  
	String name = currentUser.getUserName();
%>
<h1>Hello Editor <%=name %></h1>
<li><a href="index.jsp">Back to Homepage</a></li><br>
<form action = "EditorRetire" method = "post" accept-charset="utf-8" class="simform">
            <input class="sumbit" type="submit" value="Ready to retire"/>
</form>

<form id="form2" action = "EditorGetAllJournals" method = "post" >
<input type = "submit" id="submit_2" value="Edit Journal Page"/>
</form>

<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
			<table class="hovertable" align = "center">
			<tr><th>Article name</th><th>Article Abstract</th><th>Reviewed Times</th><th>Is published</th><th>Detail link</th></tr>
			<%
				List<EditorAllArticlesInfo> articles=new ArrayList<EditorAllArticlesInfo>();
				if(session.getAttribute("allArticles") instanceof List){
					articles = (ArrayList<EditorAllArticlesInfo>)session.getAttribute("allArticles");
				}
				System.out.println(articles.toString());
				for(EditorAllArticlesInfo pa : articles){
				
					String title = pa.getArticleName();
					String abstracT = pa.getArticleAbstract();
					String reviewedTime = pa.getArticleProfileUrl();
					String articlePublished = pa.getArticleIsPublished();
					System.out.println(title+abstracT+reviewedTime);
					
				%>
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td><%=title %></td><td><%=abstracT %></td><td><%=reviewedTime %></td><td><%=articlePublished %></td>
					<td><form action="EditorArticleDetail" method="post">
					<input type="text" name="appointname" value = <%=title%>/> 
					<input type="submit" value="viewDetail"/></form> </td>
					</tr>
				<%}%>
				<p><input type="submit" name="submit" value="Show articles"></p>
				<!-- <input type="hidden" name="pendingSelection" value="valid">  -->
		</table>				
	</form> 
	<h4>appoint one user as a new editor<h4>
	<p><form name="selectUser" action="EditorRetireAndAppointOne" method="get">
			<table class="hovertable" align = "center">
			<tr><th>User name</th><th>User role</th><th>Detail link</th></tr>
			<%
				List<userInfo> uI=new ArrayList<userInfo>();
				if(session.getAttribute("userInfo") instanceof List){
					uI = (ArrayList<userInfo>)session.getAttribute("userInfo");
				}
				for (userInfo u : uI) {
					String username = u.getUserName();
					String userRole = u.getUserRole();
					String role = null;
					if(userRole.equals("1")){role = "user";}
					else if(userRole.equals("2")){role = "author";}
					else role = "editor";
				%>
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td><%= username %></td><td><%= role%></td><td></td><td><a href="#">Set as Editor</a></td>
					<td><%= username %></td><td><%= role%></td><td></td>
					<td> <form action="EditorAppointOne" method="post">
					<input type="text" name="appointname" value = <%=username %>/> 
					<input type="submit" value="appoint"/> </form> 
					</td>
				<%}%>
				<p><input type="submit" name="submit" value="Show all users"></p>			
			</table>				
			</form>
</form>

<h3>show all journals</h3>




</body> --%>
</html>
