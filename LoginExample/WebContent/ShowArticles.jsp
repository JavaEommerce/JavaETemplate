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
<style type="text/css">
div#container{width:500px}
div#header {background-color:#99bbbb;}
div#menu {background-color:#ffff99;height:200px;width:150px;float:left;}
div#content {background-color:#EEEEEE;height:200px;width:350px;float:left;}
div#footer {background-color:#99bbbb;clear:both;text-align:center;}
h1 {margin-bottom:0;}
h2 {margin-bottom:0;font-size:18px;}
ul {margin:0;}
li {list-style:none;}
</style>
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
<div id="container">

<div id="header">
<h1>Article Detail</h1>
</div>
<div id="Name">
<h2>Article Name</h2>
<ul>
<p><%= article.getArticlename()%></p>
</ul>
</div>
<div id="Author Name">
<h2>Main author Name</h2>
<ul>
<p><%=authorname%></p>
</ul>
</div>
<div id="Keywords">
<h2>Key Words</h2>
<ul>
<p><%= article.getKeywords()%></p>
</ul>
</div>
<div id="Abstract">
<h2>Abstract</h2>
<ul>
<p><%= article.getAbstractinfo()%></p>
</ul>
</div>
<div id="Author email">
<h2>Author Email</h2>
<ul>
<p><%=email%></p>
</ul>
</div>
<div id="url"><a href = "DownLoadArticle?downloadAname=<%= article.getArticlename()%>"> DownLoad PDF</a></div>
<div id="footer">Copyright Team153</div>

</div>

</body>
</html>