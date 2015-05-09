<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="editor.EditorAllArticlesInfo" %>
<%@ page import="loginSystem.User"%>
<%@ page import="editor.userInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/editorStyle.css" type="text/css" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Edit User</title>
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
				
			</header>

			<nav id="mainnav">
  				<ul>
                           <li class="selected-item"><a href="EditorTest.jsp">Articles</a></li>
                           		 	<li><a href="EditUser.jsp">Users</a></li>
                            		<li><form id="form2" action = "EditorGetAllJournals" method = "post" >
									<input type = "hidden" id="submit_2" name="journal" value="Edit Journal Page"/>
									<a href="EditJournal.jsp" onclick="this.parentNode.submit()">Journals</a>
									</form></li>
                            		<li><a href="#">Contact</a></li>
                 </ul>
			</nav>

			
			
			</aside>
			<section id="content" class="column-right">
			<article>
				<h2>this is users panel</h2>
				<h3>appoint one user as a new editor<h3>
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
			</article>
                		
	    

		</section>

		<div class="clear"></div>

	</section>
	<%-- <h4>appoint one user as a new editor<h4>
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
</form> --%>
</body>
</html>