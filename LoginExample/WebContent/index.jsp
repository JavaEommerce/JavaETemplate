<%@page import="reviewer.Reviewer"%>
<%@page import="Author.Author" %>
<%@page import="loginSystem.User"%>
<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    
<link href="css.loginform.css" rel="stylesheet" type="text/css" /> 

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    -->
<!DOCTYPE HTML>
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
							Author currentAuthor = (Author)session.getAttribute("Author");
							Reviewer currentReviewer = (Reviewer)session.getAttribute("Reviewer");
							//Editor currentEditor = (Editor)session.getAttribute("Editor");
							String name = "please login";
							int role = 0;
							int state = 0;
							int reviewerNum = 0;
							String showName = " ";
							String welcomeInfo = "";
							if(currentAuthor!=null){
								
								state = currentAuthor.getSubmitState();
								System.out.println(currentAuthor);

							if(state==2){
								String url="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
								String sqlStr = "select reviewername from AuthorReviewer where authorname=";
								sqlStr = sqlStr+ "\""+currentAuthor.getAuthorName()+"\"";
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection( url );
								Statement st = con.createStatement();
								ResultSet rs = st.executeQuery( sqlStr );
								while(rs.next()){
									reviewerNum+=1;
								}
								rs.close();
								st.close();
								System.out.println("reviewerNum: "+reviewerNum);
								if(reviewerNum>2){
									state = 3;
									currentAuthor.setSubmitState(3);
									
									String sqlStr1 = "update Author set submitstate=3 where authorname=";
									sqlStr1 = sqlStr1+ "\""+currentAuthor.getAuthorName()+"\"";
									
									Statement st1 = con.createStatement();
									int cishu = st1.executeUpdate( sqlStr1 );
									System.out.println(cishu);
									st1.close();
									con.close();
									
								}

							}
								

							}
							if(currentUser!=null){
								name= currentUser.getUserName();
								role= currentUser.getRole();
								if(role == 1){
									showName = "Upload";
								}else if(role == 3)
								{
									showName = "Editor";
								}
								
								welcomeInfo="Welcome!";
							}
							
							if(currentReviewer!=null){
								welcomeInfo = "Reviewer Centre";
							}
						
						%>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<%if((currentUser==null)&&(currentAuthor==null)) {%>
						<li><a href="login.jsp">Login</a></li>
						<%} %>
						<li><a href="UploadNavigator.jsp"><%="Guide" %></a></li>
						
						<%if(state==0){ %>
							<li><a href="Upload.jsp"><%="Upload" %></a></li>
						<%} %>
						<%if(state==1){ %>
						<li><a href="Upload.jsp"><%="Upload" %></a></li>
							<li><a href="Canupload.jsp"><%="Author Centre" %></a></li>
						<%} %>
						<%if(state==2){ %>
						<li><a href="Waitingpublish.jsp"><%="Upload" %></a></li>
							<li><a href="Checking.jsp"><%="Author Centre" %></a></li>
						<%} %>
						<%if(state==3){ %>
						<li><a href="Waitingpublish.jsp"><%="Upload" %></a></li>
							<li><a href="ReUpLoad.jsp"><%="Author Centre" %></a></li>
						<%}%>
						<li><a href="reviewerIndex.jsp"><%=welcomeInfo %></a></li>
						
						<%if(role==3){ %>
						<li><a href="EditorTest.jsp"><%=showName %></a></li>
						<li><a href="Logout">log out</a><li>
						<%}else if(role==1 || role==2 || role==3){ %>
						<li><a href="#"><%=showName %></a></li>
						<li><a href="Logout">logout</a><li>
						<%}else{ %>
						<li><a><%=showName %></a></li>
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
				<div class="col-sm-8 col-sm-push-0">
					<div class="page-header">
						<h1>New Journal Information</h1>
						<p><%=welcomeInfo %> <span class="glyphicon glyphicon-user"></span><%=name %><span class="glyphicon glyphicon-time"></span><div id="time"></div></p>
					</div>
				</div>
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-sm-8 col-sm-push-4">
		
			<div>
			<h3>Journal List</h3>
		 	<iframe src="showjournal2015.jsp" frameborder="0" width="100%" height="80%" id="iframe" name="i"></iframe>    
			
<!--			<iframe src="" id="iframe" name="i" frameBorder=0 align="left" scrolling=no width="100%" onLoad="iFrameHeight()" ></iframe> 
<script type="text/javascript" language="javascript"> 
function iFrameHeight() { 
var ifm= document.getElementById("iframepage"); 
var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument; 
if(ifm != null && subWeb != null) { 
ifm.height = subWeb.body.scrollHeight; 
} 
} 
</script> 
	 -->		
			</div>


					<!-- Register for email notification -->

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
						<form action="${pageContext.request.contextPath}/Search" method="post">
							<div class="input-group">
								<label class="sr-only" for="search-form">Search the site</label>
								<input type="text" class="form-control" id="search-form" name="search-form">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit">
                                    Submit
										<span class="glyphicon glyphicon-search"></span>
										<span class="sr-only">Search</span>
									</button>
								</span>
							</div>
							<div>
							      <tr>
									<select name="selectsearchtype">
 									 <option value="ArticleName">ArticleTitle</option>
 									 <option value="KeyWords">KeyWords</option>
 									 <option value="JournalName">JournalName</option>
 									 <option value="AuthorName">AuthorName</option>
 									 <option value="Domain">Domain</option>
									</select>
  	  							  </tr>
  	  							</div>  
  	  						
  	  						<tr><p>Date From:</p></p></tr>
  	  						<input  type="date"  value="2015-01-01" name="fromdate">
						</form>
					</div>

					<!-- list group -->
					<form action="${pageContext.request.contextPath}/DealJournalInfo" method="post">
					<div class="list-group margin-b-3">
					    <a href="${pageContext.request.contextPath}/showjournal2015.jsp"  class="list-group-item" id="2015" name="year2015" target="i">2015</a>
			
					    <a href="${pageContext.request.contextPath}/showjournal2014.jsp" class="list-group-item" id="2014" name="year2014" target="i">2014</a>
					    <a href="${pageContext.request.contextPath}/showjournal2013.jsp" class="list-group-item" id="2013" name="year2013" target="i">2013</a>
                        <a href="${pageContext.request.contextPath}/showjournalearlier.jsp" class="list-group-item" id="earlier" name="yearearlier" target="i">earlier</a>
					</div>
					</form
					
					>
					<!-- Panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Share us to your friends</h4>
						</div>
						<div class="panel-body">
							<a href = "http://www.facebook.com"><img src="http://static.viewbook.com/images/social_icons/facebook_32.png" alt="" ></a>
							<a href = "http://www.twitter.com"><img src="http://static.viewbook.com/images/social_icons/twitter_32.png" alt=""></a>
							<a href = "http://www.linkedin.com"><img src="http://static.viewbook.com/images/social_icons/linkedin_32.png" alt="" ></a>
							<a href = "http://www.vimeo.com"><img src="http://static.viewbook.com/images/social_icons/vimeo_32.png" alt=""></a>
							<a href = "http://www.tumblr.com"><img src="http://static.viewbook.com/images/social_icons/tumblr_32.png" alt="" ></a>
							<a href = "http://www.flickr.com"><img src="http://static.viewbook.com/images/social_icons/flickr_32.png" alt=""></a>
							<a href = "https://wordpress.com"><img src="http://static.viewbook.com/images/social_icons/wordpress_32.png" alt="" ></a>
							<a href = "https://plus.google.com/"><img src="http://static.viewbook.com/images/social_icons/google_32.png" alt=""></a>
						</div>
					</div>

					<!-- Panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title"> The Self-Resourcing Electronic Journal</h4>
						</div>
						<div class="panel-body">
							<p>
							Science and engineering journals are moving increasingly from a paper-based format to an electronic format.
							Covering the costs of publishing an electronic journal is problematic: either, the publishers charge high subscription fees
							 to academic institutions and restrict access to the journal (bad for the dissemination of scientific results); 
							 or they grant open access to all readers, 
							 but charge the authors a hefty fee for having their articles published (promoting “vanity publishing” by those who can afford it).
							 </p>
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