<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="editor.EditorAllArticlesInfo" %>
<%@ page import="loginSystem.User"%>

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
            
           /*  $(document).ready(function(){
            	$('#updateUsername').submit(function(){
            		$.ajax({
            			url:'EditorAccessToAllArticle',
            			type:'get',
            			dataType:'json',
            			success:function(data){
            				alert("hahha");
            				$('#article_name').text(data);
            				
            			}
            		});
            		return false;
            	});
            });
             */
        </script>
</head>
<body>
<h1 style= center >Editor Page</h1>
<%	
	User currentUser = (User)session.getAttribute("User");  
	String name = currentUser.getUserName();
%>
<h1>Hello Editor <%= name %></h1>
<li><a href="index.jsp">Back to Homepage</a></li><br>
<form id="form1">
<input type="button" id="submit" value="Editor Retire"/>
<br/>

<div id="welcometext">
</div>
</form>
<form id="form2">
<input type = "button" id="submit_2" value="show articles"/>
<div id="article_name">
</div></form>
<p><form name="selectArticle" action="EditorAccessToAllArticle" method="get">
			<table class="hovertable">
			<tr><th>Article name</th><th>Article Abstract</th><th>Detail link</th></tr>
			<%
				List<EditorAllArticlesInfo> articles=null;
				if(session.getAttribute("allArticles") instanceof List){
					articles = (ArrayList<EditorAllArticlesInfo>)session.getAttribute("allArticles");
				}
				for(EditorAllArticlesInfo pa:articles){%>
					<%
						String title = pa.getArticleName();
						String abstracT = pa.getArticleAbstract();
					%>
					<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td><%=title %></td><td><%=abstracT %>></td><td><a href="#">Detail</a></td>
					</tr>
					<%-- <p><%=title +"  "+ abstracT %><a href="http://www.w3schools.com">Detail</a> --%>
					<%-- <input type="checkbox" name="pendingArticles" value=<%=title%>></p> --%>
				<% } %>
				<p><input type="submit" name="submit" value="Select Articles">
				<input type="hidden" name="pendingSelection" value="valid"> 
</table>				
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
