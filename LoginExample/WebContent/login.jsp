<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style_login.css">
<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
<script src="js/index.js"></script>

</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<div class="logmod">
  <div class="logmod__wrapper">
    <!-- <span class="logmod__close">Close</span>  -->
    <span><a class="special.green" role="link" href="index.jsp">Back to Homepage</a></span>
    <div class="logmod__container">
      <ul class="logmod__tabs">
        <li data-tabtar="lgm-2"><a href="#">Login</a></li>
        <!-- <li data-tabtar="lgm-1"><a href="#">Sign Up</a></li> -->
      </ul>
      <div class="logmod__tab-wrapper">
      <div class="logmod__tab lgm-1">
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle">Enter your personal details <strong>to create an acount</strong></span>
        </div>
        <div class="logmod__form">
          <form accept-charset="utf-8" action="signup" class="simform" method="post">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">Username*</label>
                <input class="string optional" name= "username" maxlength="255" id="user-email" placeholder="Username" type="text" size="50" />
              </div>
            </div>
            <div class="sminputs">
              <div class="input string optional">
                <label class="string optional" for="user-pw">Password *</label>
                <input class="string optional" name="password" maxlength="255" id="user-pw" placeholder="Password" type="text" size="50" />
              </div>
              <div class="input string optional">
                <label class="string optional" for="user-pw-repeat">Repeat password *</label>
                <input class="string optional" name="passwordVerify" maxlength="255" id="user-pw-repeat" placeholder="Repeat password" type="text" size="50" />
              </div>
            </div>
            <div class="simform__actions">
              <input class="sumbit" name="commit" type="submit" value="Signup" />
              <span class="simform__actions-sidetext">By creating an account you agree to our <a class="special" href="#" target="_blank" role="link">Terms & Privacy</a></span>
            </div> 
          </form>
        </div> 

      </div>
      <div class="logmod__tab lgm-2">
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle">Enter your email and password <strong>to sign in</strong></span>
        </div> 
        <div class="logmod__form">
          <form action = "LoginServlet" method = "post" accept-charset="utf-8" class="simform">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">Username</label>
                <input class="string optional" name="username" maxlength="255" id="user-email" placeholder="Username" type="username" size="50" />
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-pw">Password</label>
                <input class="string optional" name="password" maxlength="255" id="user-pw" placeholder="Password" type="password" size="50" />
                						<span class="hide-password">Show</span>
              </div>
            </div>
            <div class="simform__actions">
              <input class="sumbit" type="submit" value="Login"/>
              <span class="simform__actions-sidetext"><a class="special" role="link" href="signup.jsp">Forgot your password?<br>Click here</a></span>
            </div> 
          </form>
        </div> 
        <div class="logmod__alter">
          </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>
