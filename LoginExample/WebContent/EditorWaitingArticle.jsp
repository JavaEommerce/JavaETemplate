<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="editor.EditorAllArticlesInfo" %>
<%@ page import="loginSystem.User"%>
<%@ page import="editor.userInfo" %>
<%@ page import="editor.Journal" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/editorStyle.css" type="text/css" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Edit Journal</title>
</head>
<%	
	User currentUser = (User)session.getAttribute("User");  
	String name = currentUser.getUserName();
%>
<body>
<section id="body" class="width">
			<aside id="sidebar" class="column-left">

			<header>
				<h1><a href="EditorTest.jsp">Editor Center</a></h1>

				<h2>Hello Editor <%=name %></h2>
				<h2><a href="index.jsp">Home page</a></h2>
			</header>

			<nav id="mainnav">
  				<ul>
                           			<li ><a href="EditorTest.jsp">Articles</a></li>
                           		 	<li><a href="EditUser.jsp">Users</a></li>
                            		<li ><form id="form2" action = "EditorGetAllJournals" method = "post" >
									<input type = "hidden" id="submit_2" name="journal" value="Edit Journal Page"/>
									<a href="EditJournal.jsp" onclick="this.parentNode.submit()">Journals</a>
									</form></li>
                            		<li class="selected-item"><a href="EditorWaitingArticle">Waiting Article List</a></li>
                 </ul>
			</nav>

			</aside>
			<section id="content" class="column-right">
			<article>
				<h2>this is Journals panel</h2>
				
			</article>
                		
	    <article><h1> </h1><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><h1> </h1></article>

		</section>

		<div class="clear"></div>

	</section>
</body>
</html>
<%--   --%>
