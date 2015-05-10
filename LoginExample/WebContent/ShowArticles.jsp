<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map"%>
<%@ page import="reader.Article" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<link rel="stylesheet" href="css/upload.css" type="text/css" />	
	  	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	  	<link rel="stylesheet" href="css/loginform.css" type="text/css"/>
	  	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

</head>

<body>
<%
Article article;
article = (Article)session.getAttribute("selectedarticle");
String url="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
String sqlauthor ="select Author.authorname,Author.email  from Author, AuthorArticle where AuthorArticle.articlename='"+article.getArticlename()+"' and Author.authorname= AuthorArticle.authorname";
System.out.println(article.getArticlename());
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection( url );

Statement st1 = con.createStatement();
ResultSet Authorresult = st1.executeQuery(sqlauthor);
String authorname=null;
String email=null;
while(Authorresult.next())
{  
   authorname = Authorresult.getString("authorname");
   email = Authorresult.getString("email");
}

%>
<div class = "logmod">
	<div class="logmod__wrapper">
		<div class="smart-green">
		<form class="smart-green" id="form5">
		<h1>Article Detail
		<%= article.getArticlename()%>
		</h1>
		<span><a href="index.jsp">Back to home page</a></span>
		<h2>Article Name
		<p><%= article.getArticlename()%></p>
		</h2>
		<h2>Main author Name
		<p><%=authorname%></p>
		</h2>
		<h2>Key Words
		<p><%= article.getKeywords()%></p></h2>
		<h2>Abstract
		<p><%= article.getAbstractinfo()%></p></h2>
		<h2>Author Email
		<p><%=email%></p></h2>
	
		<h2><a href = "DownLoadArticle?downloadAname=<%= article.getArticlename()%>"> DownLoad PDF</a></h2>
		<h2>Copyright Team153</h2>
		</form>


	</div></div></div>

</body>
</html>