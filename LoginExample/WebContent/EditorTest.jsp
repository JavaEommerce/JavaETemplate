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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
<h1 style= center >Editor Page</h1>
<%	
	User currentUser = (User)session.getAttribute("User");  
	String name = currentUser.getUserName();
	//EditorCharacter e = (EditorCharacter)session.getAttribute("Editor");
	//String name = e.getEditorName();
%>
<h1>Hello Editor <%=name %></h1>
<li><a href="index.jsp">Back to Homepage</a></li><br>
<form id="form1">
<input type="button" id="submit" value="Editor Retire"/>
<br/>



<div id="welcometext">
</div>
</form>
<form id="form2">
<input type = "button" id="submit_2" value="Edit Journal"/>
<div id="article_name">
</div></form>
	<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
			<table class="hovertable" align = "center">
			<tr><th>Article name</th><th>Article Abstract</th><th>Reviewed Times</th><th>Detail link</th></tr>
			<%
			System.out.println("asdasdasdasdasdasds");

				List<EditorAllArticlesInfo> articles=new ArrayList<EditorAllArticlesInfo>();
				if(session.getAttribute("allArticles") instanceof List){
					articles = (ArrayList<EditorAllArticlesInfo>)session.getAttribute("allArticles");
				}
				System.out.println(articles.toString());
				for(EditorAllArticlesInfo pa : articles){
				
					String title = pa.getArticleName();
					String abstracT = pa.getArticleAbstract();
					String reviewedTime = pa.getArticleProfileUrl();
					System.out.println(title+abstracT+reviewedTime);
				%>
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td><%=title %></td><td><%=abstracT %></td><td><%=reviewedTime %>></td><td><a href="#">Detail</a></td>
					</tr>
				<%}%>
				<p><input type="submit" name="submit" value="Show articles"></p>
				<!-- <input type="hidden" name="pendingSelection" value="valid">  -->
		</table>				
	</form> 
	
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
					<%-- <td><%= username %></td><td><%= role%></td><td></td><td><a href="#">Set as Editor</a></td> --%>
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

 <%! private int count = 0; %>
    <P>Hello! Today's date is
      <%= new java.util.Date() %>
    </p><p>
      You have asked for the date
      <%= ++count %> times since the
      server was last restarted.
    </p>


</body>
</html>
