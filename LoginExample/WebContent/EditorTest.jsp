<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
                        $('#article_name').text(responseText);         
                    });
                });
            }); 
            
           /*  $(document).ready(function(){
            	$('#updateUsername').submit(function(){
            		$.ajax({
            			url:'EditorAccessToAllArticle',
            			type:'get',
            			dataType:'json',
            			success:function(data){
            				alert("hahha");
            				$('#article_name').text(data);
            				
            			}
            		});
            		return false;
            	});
            });
             */
        </script>
</head>
<body>
<h1>Editor</h1>
<li><a href="index.jsp">Back to Homepage</a></li><br>
<form id="form1">
<input type="button" id="submit" value="Editor Retire"/>
<br/>

<div id="welcometext">
</div>
</form>
<form id="form2">
<input type = "button" id="submit_2" value="show articles"/>
<div id="article_name">
</div></form>
<h3>show all journals</h3>

 <%! private int count = 0; %>
    <P>Hello! Today's date is
      <%= new java.util.Date() %>
    </p><p>
      You have asked for the date
      <%= ++count %> times since the
      server was last restarted.
    </p>

<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>
<h1>Editor</h1>
<li><a href="index.jsp">Back to Homepage</a></li>
<form action = "Editor" method = post>
<input type = "submit" name = "button" value = "Test" onclick=""></input>
</form>
<form action = "#" method = post>
<input type = "submit" name = "button" value = "Retire" onclick=""></input>
</form>
<form action = "#" method = post>
<input type = "submit" name = "Show_Articles" value = "show" onclick=""></input>
</form>
<!-- <form id="updateUsername">
<label for = "username">This is ajax test</label>
<input type = "text" id="username" name="username"/>
<input type = "submit"/>
</form>
<button id="somebutton">press here</button>
<div id="somediv"></div>
<p id="displayName" />
<hr /> -->
<form id="form1">
<h1>AJAX Demo using Jquery in JSP and Servlet</h1>
Enter your Name:
<input type="text" id="user"/>
<input type="button" id="submit" value="Ajax Submit"/>
<br/>
<div id="welcometext">
</div>
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
 --%>

</body>
</html>
