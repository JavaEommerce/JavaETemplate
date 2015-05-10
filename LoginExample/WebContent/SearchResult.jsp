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
		
		
<html>
<head>
		<link rel="stylesheet" href="css/upload.css" type="text/css" />	
	  	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	  	<link rel="stylesheet" href="css/loginform.css" type="text/css"/>
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
  System.out.println("New page");
 ArrayList<Article> names = new ArrayList<Article>();
	if(session.getAttribute("articlenamelist") instanceof List){
		names = (ArrayList<Article>)session.getAttribute("articlenamelist");
	}
%>
<div class = "logmod">
	<div class="logmod__wrapper">
		<div class="smart-green">

	<form class="smart-green" action="">
	<h1>Search Result:
	<span><a href="index.jsp">Back to home page</a></span></h1>
	<table >
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
		<tr >
	    <td ><a href = "DealArticleInfo?articlename=<%=articlename %>"><%=articlename %></a></td><td ><%=keywords %></td> <td ><%=domain %></td> <td ><%=uploadString %></td>
		</tr>
	
	<%} %>
	</table>
	</form>

	</div></div></div>

</body>
</html>