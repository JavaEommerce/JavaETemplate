<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<!-- Bootstrap Core CSS file -->
		<link rel="stylesheet" href="css/bootstrap.min.css">

		<!-- Override CSS file - add your own CSS rules -->
		<link rel="stylesheet" href="css/styles.css">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String url="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
String sqljname =  "select distinct journalname, Info from Journal where publishtime >'2014-12-30'";

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection( url );

Statement st1 = con.createStatement();
ResultSet Jnameresult = st1.executeQuery( sqljname );
Statement st2 = con.createStatement();

    
	while(Jnameresult.next())
            {%>   
            <div class="well">
						<div class="media">
							<div class="media-left">
								<img src="http://placehold.it/70x70" alt="">
							</div>
							<div class="media-body">
							
								<h4 class="margin-t-0"><a href="#"><%=Jnameresult.getString("journalname")%></a></h4>
								<p><%=Jnameresult.getString("Info")%></p>
								<%
								String sqljvsersion =  "select distinct version , publishtime, journalID from Journal where publishtime >'2014-12-30'and journalname ="+"\""+Jnameresult.getString("journalname")+"\"";
								//System.out.println(nameresult.getString("journalname"));
								ResultSet versionresult = st2.executeQuery( sqljvsersion );
								while(versionresult.next())
								{%>	
								<form>
								 <p><a href="Search?JID=<%=versionresult.getString("journalID") %>" target=_blank>Edition: <%=versionresult.getString("version")%> &nbsp;&nbsp;&nbsp;&nbsp; Publish time:<%=versionresult.getString("publishtime")%></a> </p>	
								</form>		
								<%} 
								   versionresult.close();
								%>
							
							</div>
						</div>
					</div>   
    
           <% }%>
           <%
        
           Jnameresult.close();
           st1.close();
           st2.close();
           con.close();
 %>
</body>
</html>