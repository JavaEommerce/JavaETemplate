<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="editor.Journal" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Journal Page</title>
</head>
<body>
<h1 align="center">Edit Journal</h1>
<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
			<table class="hovertable" align = "center">
			<tr><th>Journal id</th><th>Journal Aims Reviewed Goals profile  edit</th></tr>
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
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
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
				<p><input type="submit" name="submit" value="Back to editor center"></p>
				<!-- <input type="hidden" name="pendingSelection" value="valid">  -->
		</table>				
	</form> 
</body>
</html>