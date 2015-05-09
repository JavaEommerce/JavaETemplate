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


<div id="Keywords">
<h2>Key Words</h2>
<ul>
<p><%= article.getKeywords()%></p>
</ul>
</div>

<div id="Keywords">
<h2>Abstract</h2>
<ul>
<p><%= article.getAbstractinfo()%></p>
</ul>
</div>

<div id="footer">Copyright Team153</div>

</div>

</body>
</html>