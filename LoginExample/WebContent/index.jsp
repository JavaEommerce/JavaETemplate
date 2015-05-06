
<%@page import="loginSystem.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css.loginform.css" rel="stylesheet" type="text/css" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
 
    // Check if JavaScript is enabled
    $('body').addClass('js');
 
    // Make the checkbox checked on load
    $('.login-form span').addClass('checked').children('input').attr('checked', true);
 
    // Click function
    $('.login-form span').on('click', function() {
 
        if ($(this).children('input').attr('checked')) {
            $(this).children('input').attr('checked', false);
            $(this).removeClass('checked');
        }
 
        else {
            $(this).children('input').attr('checked', true);
            $(this).addClass('checked');
        }
 
    });
 
});
// 希望大家在每次更新之后能再submit的时候说明自己做了什么
function showtime(){
	  var myDate = new Date();
	  document.getElementById("time").innerHTML = myDate.getHours()
	  + ":" + myDate.getMinutes() + ":"
	  + myDate.getSeconds() + "" ;
	  setTimeout(showtime,1000);
	}
	window.onload=function(){
	  showtime();
	}
</script>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Page title - Sitename</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="Description" lang="en" content="ADD SITE DESCRIPTION">
		<meta name="author" content="ADD AUTHOR INFORMATION">
		<meta name="robots" content="index, follow">

		<!-- icons -->
		<link rel="apple-touch-icon" href="assets/img/apple-touch-icon.png">
		<link rel="shortcut icon" href="favicon.ico">

		<!-- Bootstrap Core CSS file -->
		<link rel="stylesheet" href="css/bootstrap.min.css">

		<!-- Override CSS file - add your own CSS rules -->
		<link rel="stylesheet" href="css/styles.css">

		<!-- Conditional comment containing JS files for IE6 - 8 -->
		<!--[if lt IE 9]>
			<script src="assets/js/html5.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>

		<!-- Navigation -->
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
					<a class="navbar-brand" href="index.jsp">E--Jounalise</a>
				</div>
				<!-- /.navbar-header -->

				<!-- Collect the nav links, forms, and other content for toggling -->
				<%
							User currentUser = (User)session.getAttribute("User"); 
							String name = "please login";
							int role = 0;
							String showName = " ";
							String welcomeInfo = "";
							if(currentUser!=null){
								name= currentUser.getUserName();
								role= currentUser.getRole();
								if(role == 3)
								{
									showName = "Editor";
								}
								else if(role==2)
								{
									showName = "Other";
								}
								welcomeInfo="Welcome!";
							}
						
						%>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="login.jsp">Login and Signup</a></li>
						<li><a href="reviewerIndex.jsp">I'm a reviewer</a></li>
						<%if(role==3){ %>
						<li><a href="EditorTest.jsp"><%=showName %>></a></li>
						<%}else{ %>
						<li><a><%=showName %>></a></li>
						<%} %>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!-- /.navbar -->

		<!-- Page Content -->
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-8 col-sm-push-4">
					<div class="page-header">
						<h1>New Journal Information</h1>
						<p><%=welcomeInfo %> <span class="glyphicon glyphicon-user"></span><%=name %><span class="glyphicon glyphicon-time"></span><div id="time"></div></p>
					</div>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-sm-8 col-sm-push-4">

					<!-- Image -->
					

	<!--插入显示Jounal List -->			

					<hr>

					<!-- Comments -->
					<h3>Comments</h3>
					<div class="well">
						<div class="media">
							<div class="media-left">
								<img src="http://placehold.it/70x70" alt="">
							</div>
							<div class="media-body">
								<h4 class="margin-t-0"><a href="#">Mayahuel Rodriguez</a></h4>
								<p><a href="#">12 January 2015 11:13 pm</a></p>
								<p>Lorem ipsum dolor sit amet consect etuer adipi scing elit sed diam nonummy nibh euismod tinunt ut laoreet dolore magna aliquam erat volut</p>
								<p>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-thumbs-up"></span> Upvote
									</button>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-thumbs-down"></span> Downvote
									</button>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-comment"></span> Reply
									</button>
								</p>
							</div>
						</div>
					</div>

					<div class="well">
						<div class="media">
							<div class="media-left">
								<img src="http://placehold.it/70x70" alt="">
							</div>
							<div class="media-body">
								<h4 class="margin-t-0"><a href="#">Mayahuel Rodriguez</a></h4>
								<p><a href="#">12 January 2015 11:13 pm</a></p>
								<p>Lorem ipsum dolor sit amet consect etuer adipi scing elit sed diam nonummy nibh euismod tinunt ut laoreet dolore magna aliquam erat volut</p>
								<p>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-thumbs-up"></span> Upvote
									</button>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-thumbs-down"></span> Downvote
									</button>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-comment"></span> Reply
									</button>
								</p>
							</div>
						</div>
					</div>

					<div class="well">
						<div class="media">
							<div class="media-left">
								<img src="http://placehold.it/70x70" alt="">
							</div>
							<div class="media-body">
								<h4 class="margin-t-0"><a href="#">Mayahuel Rodriguez</a></h4>
								<p><a href="#">12 January 2015 11:13 pm</a></p>
								<p>Lorem ipsum dolor sit amet consect etuer adipi scing elit sed diam nonummy nibh euismod tinunt ut laoreet dolore magna aliquam erat volut</p>
								<p>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-thumbs-up"></span> Upvote
									</button>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-thumbs-down"></span> Downvote
									</button>
									<button class="btn btn-sm btn-default">
										<span class="glyphicon glyphicon-comment"></span> Reply
									</button>
								</p>
							</div>
						</div>
					</div>
					<hr>

					<!-- Comment form -->
					<h3>Register for email notification</h3>
					<p>please fill in your email address and choose a subject you like
                    or a particular journal you like, if new journal or article are 				                     publish, we will notify you by email
                    </p>

					<div class="well">
						<form>
                        
							<div class="form-group">
								<label for="contactEmail">Email</label>
								<input type="email" class="form-control" id="contactEmail" placeholder="Enter email">
								<p class="help-block">Make sure you use a valid email address</p>
							</div>
							<div class="form-group">
								<label for="contactComment">Comment</label>
								<textarea class="form-control" id="contactComment"></textarea>
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
					<hr>

					<!-- Pager -->
					<nav>
						<ul class="pager">
							<li class="previous"><a href="#"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span> Older</a></li>
							<li class="next"><a href="#">Newer <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
						</ul>
					</nav>

				</div>
				<div class="col-sm-4 col-sm-pull-8">
					<!-- Search -->
					<div class="well">
						<h4 class="margin-t-0">Search</h4>
						<form action="#">
							<div class="input-group">
								<label class="sr-only" for="search-form">Search the site</label>
								<input type="text" class="form-control" id="search-form">
								<span class="input-group-btn">
									<button class="btn btn-default" type="button">
										<span class="glyphicon glyphicon-search"></span>
										<span class="sr-only">Search</span>
									</button>
								</span>
							</div>
						</form>
					</div>

					<!-- list group -->
					<div class="list-group margin-b-3">
					    <a href="#" class="list-group-item">2015</a>
					    <a href="#" class="list-group-item">2014</a>
					    <a href="#" class="list-group-item">2013</a>
                        <a href="#" class="list-group-item">2012</a>
                        <a href="#" class="list-group-item">earlier</a>
					</div>

					<!-- Panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Sidebar panel widget</h4>
						</div>
						<div class="panel-body">
							<p>Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</p>
						</div>
					</div>

					<!-- Panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Sidebar panel widget</h4>
						</div>
						<div class="panel-body">
							<p>Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</p>
						</div>
					</div>

				</div>
			</div>
			<!-- /.row -->

			<hr>
			<footer class="margin-tb-3">
				<div class="row">
					<div class="col-lg-12">
						<p>Copyright &copy; TEAM153 YanZhang WeiZhao ShengyanZhao ShangshuLu 2015</p>
					</div>
				</div>
			</footer>
		</div>
		<!-- /.container-fluid -->

		<!-- JQuery scripts -->
	    <script src="assets/js/jquery-1.11.2.min.js"></script>

		<!-- Bootstrap Core scripts -->
		<script src="assets/js/bootstrap.min.js"></script>
  </body>
</html>


<%-- <html>
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
</html> --%>