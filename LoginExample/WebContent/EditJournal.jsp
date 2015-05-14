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
                            		<li class="selected-item"><form id="form2" action = "EditorGetAllJournals" method = "post" >
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
				<h2>this is Journals panel</h2>
				<h3>see the details of the panel<h3>
				<form id="form2" action = "EditorGetAllJournals" method = "post" >
				<input class="btnExample"  type = "submit" id="submit_2" value="Edit Journal Page"/>
				</form>
				<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
				<table>
				<tr><th>Journal id</th><th>Title______Aims______Goal______profileurl </th></tr>
			<%
				List<Journal> journals=new ArrayList<Journal>();
				if(session.getAttribute("allJournals") instanceof List){
					journals = (ArrayList<Journal>)session.getAttribute("allJournals");
				}
				System.out.println(journals.toString());
				for(Journal pa : journals){
					String id = pa.getjournalID();
					String title = pa.getjournalTitle();
					String aims =  pa.getjournalAims();
					String goals = pa.getjournalGoals();
					String profileUrl = pa.getjournalProfileUrl();
					System.out.println("");
				%>
				<tr>
					<td><%=id %></td>
					<td>
					<form action="EditorEditJournal" method="post">
					<input type="hidden"  name="id" value=<%=id %>/>
					<input type="text" name="title" value = <%=title%>/>
					<input type="text" name="aims" value = <%=aims%>/> 
					<input type="text" name="goals" value = <%=goals%>/> 
					<input type="text" name="profileUrl" value = <%=profileUrl%>/> 
					<input type="submit" value="Update Journal Detail"/>
					</form> </td>
					</tr>
				<%}%>
				<p><input class="btnExample"  type="submit" name="submit" value="Back to editor center"></p>
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
