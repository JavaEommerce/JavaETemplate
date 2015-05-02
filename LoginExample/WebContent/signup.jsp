<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="css.signup.css"  type="text/css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
</head>
<body>
	<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container-fluid">

				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="http://localhost:8080/JavaEE/index.jsp">E--Jounalise</a>
				</div>
				
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!-- /.navbar -->
	
		<form name = "signup form" action = "signup" method = "post">
		<p>Enter User name :<input type = "text" name = "username"><br>
		Enter password:<input type = "password" name = "password"><br>
		Identify password:<input type = "text" name ="passwordVerify"><br>
		
		<input type = "submit">
		</p></form>
</body>
</html>