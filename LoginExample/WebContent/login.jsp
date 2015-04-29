<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login example</title>
</head>
<body>
<div>
	<h1>Login Form</h1>
	<form name = "loginform" action = "login" method = "post">
	<p>Enter User name :<input type = "text" name = "username" placeholder = "username"><br>
	   Enter password:<input type = "text" name = "password" placeholder = "password"><br>
	<input type = "submit">
<input type = "button" value = "signup" name = "signup" onclick = 'signup.jsp' action = "signup">
<input type="button" value="signup" name="signup" onclick = "location.href='signup.jsp'" />
</div>

</body>
</html>