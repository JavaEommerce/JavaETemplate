<%@ page language="java" import="java.sql.*,Author.Author" contentType="text/html; charset=ISO-8859-1"
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
<%
String articleTitle="";
String url="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
String sqlStr = "select articlename from AuthorArticle where authorname=";
String authorname="";
int num = -1;

Author currentAuthor = (Author)session.getAttribute("Author");
authorname = currentAuthor.getAuthorName();
sqlStr = sqlStr+ "\""+authorname+"\"";

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection( url );
Statement st = con.createStatement();
ResultSet rs = st.executeQuery( sqlStr );
    


	while(rs.next())
            {   
		articleTitle = rs.getString("articlename");
		System.out.println(articleTitle);

           

            
            
            String sqlStr2 = "select currentreviewnum,ispublish from Article where articlename=";
            sqlStr2 = sqlStr2 + "\""+articleTitle+"\"";
            System.out.println(sqlStr);
            System.out.println(sqlStr2);
            
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery( sqlStr2 );
            

                
        	while(rs2.next()) {   
        		
        		if(!rs2.getBoolean("ispublish")){
      			num = rs2.getInt("currentreviewnum");
        		}
              }
                       
            rs2.close();
            st2.close();
            }          	
            	
                rs.close();
                st.close();

       
            con.close();
            
            out.println("Please waiting for reviewer, there are "+num+" reviewer(s) have reviewed your article.");
            out.println("You can get all feedback after 3 reviewer reviewing your article");
 %>
</body>
</html>