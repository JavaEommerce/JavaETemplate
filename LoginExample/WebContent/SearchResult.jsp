<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="reader.Article" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<!-- Bootstrap Core CSS file -->
		<link rel="stylesheet" href="css/bootstrap.min.css">

		<!-- Override CSS file - add your own CSS rules -->
		<link rel="stylesheet" href="css/styles.css">
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
<h3>Search Result:</h3>
<%
  System.out.println("New page");
 ArrayList<Article> names = new ArrayList<Article>();
	if(session.getAttribute("articlenamelist") instanceof List){
		names = (ArrayList<Article>)session.getAttribute("articlenamelist");
	}
	

	
	%>
	<form action="">
	<table class="hovertable" align = "left">
	<tr><th>Article name</th><th>Key words</th><th>Article domain</th><th>Publish date</th></tr>
	<%
	for(Article ar : names){
		 String articlename = ar.getArticlename();
		 String keywords  = ar.getKeywords();
		 String abstractinfo = ar.getAbstractinfo();
		 String url = ar.getUrl();
		 String domain = ar.getDomain();
		 String uploadString = ar.getUploadString();
		 String ispublish  = ar.getIspublish();
		 String affiliations = ar.getAffiliations();
		 String currentreviewnum = ar.getCurrentreviewnum();
	
	%>
	
		<tr onmouseover="this.style.backgroundColor='#d4e3e5';" onmouseout="this.style.backgroundColor='#FFFFFF';">
	    <td ><a href = "DealArticleInfo?articlename=<%=articlename %>"><%=articlename %></a></td><td ><%=keywords %></td> <td ><%=domain %></td> <td ><%=uploadString %></td>
		</tr>
	
	<%} %>
	</table>
	</form>

	

</body>
</html>