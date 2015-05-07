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
String sqlStr = "select * from Journal where publishtime >'2014-12-30'";
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection( url );
Statement st = con.createStatement();
ResultSet rs = st.executeQuery( sqlStr );
    
	while(rs.next())
            {   


            }
           
            rs.close();
            st.close();
            con.close();
 %>
</body>
</html>