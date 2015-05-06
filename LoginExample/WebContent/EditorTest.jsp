<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editor</title>
</head>
<body>
<h1>Editor</h1>
<li><a href="index.jsp">Back to Homepage</a></li>
<form action = "Editor" method = post>
<input type = "submit" name = "button" value = "Test" onclick=""></input>
</form>
<h3>show all journals</h3>
<% 
try {
    String db = "jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
    Connection connection = null; 
    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
    connection = DriverManager.getConnection(db);
    if(!connection.isClosed())
         out.println("Successfully connected to " + "MySQL server using TCP/IP...");
    connection.close();
}catch(Exception ex){
    out.println("Unable to connect to database.");
}
%>
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

<!-- <h1><a href="index.jsp">Home</a></h1>
<div>
	<h1>Login Form</h1>
	<form name = "loginform" action = "LoginServlet" method = "post">
	<p>Enter User name :<input type = "text" name = "username"><br>
	   Enter password:<input type = "password" name = "password"><br>
	<input type = "submit" value="Login">
<input type = "button" value = "signup" name = "signup" onclick = 'signup.jsp'>
<input type="button" value="signup" name="signup" onclick = "location.href='signup.jsp'" /></p></form>
</div> -->
