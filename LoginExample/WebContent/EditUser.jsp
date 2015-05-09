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
                           		 	<li  class="selected-item"><a href="EditUser.jsp">Users</a></li>
                            		<li><form id="form2" action = "EditorGetAllJournals" method = "post" >
									<input type = "hidden" id="submit_2" name="journal" value="Edit Journal Page"/>
									<a href="EditJournal.jsp" onclick="this.parentNode.submit()">Journals</a>
									</form></li>
                            		<li><a href="EditorWaitingArticle.jsp">Waiting Article List</a></li>
                 </ul>
			</nav>

			</aside>
			<section id="content" class="column-right">
			<article>
				<h2>this is Edit User</h2>
				<h3>see the details of the User<h3>
				<form id="form2" action = "EditorRetireAndAppointOne" method = "get" >
				<input type = "submit" id="submit_2" value="Search all users"/>
				</form>
				<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
				<table>
				<tr><th>User name</th><th>User role</th><th>Detail link</th><th>appoint</th></tr>
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
				<tr>
					<tr>
					<td><%= username %></td><td><%= role%></td><td><%=role %></td>
					<td> 
					<form action="EditorAppointOne" method="post">
					<input type="text" name="appointname" value = <%=username %>/> 
					<input type="submit" value="appoint"/> </form> 
					</td>
					</tr>
				<%}%>
				<p><input type="submit" name="submit" value="Back to editor center"></p>
				<!-- <input type="hidden" name="pendingSelection" value="valid">  -->
				</table>				
		</form>
			</article>
                		
	    <article><h1> </h1><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><h1> </h1></article>

		</section>

		<div class="clear"></div>

	</section>
</body>
</html>
<%--   --%>

				<%-- <table>
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
				<tr>
					<td><%= username %></td><td><%= role%></td><td></td><td><a href="#">Set as Editor</a></td>
					<td><%= username %></td><td><%= role%></td><td></td>
					<td> <form action="EditorAppointOne" method="post">
					<input type="text" name="appointname" value = <%=username %>/> 
					<input type="submit" value="appoint"/> </form> 
					</td>
				<%}%>						
				 
				</table> --%>
