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
String UnpublishedName="";
int num = 0;

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
            
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery( sqlStr2 );
            

                
        	while(rs2.next()) {   
        		
        		if(!rs2.getBoolean("ispublish")){
        		UnpublishedName = articleTitle;
        		}
              }
                       
            rs2.close();
            st2.close();
            }          	
            	
                rs.close();
                st.close();

                
                
                String sqlStr3 = "select reviewername from ArticleReview where articlename=";
                sqlStr3 = sqlStr3 + "\""+UnpublishedName+"\"";
                
                Statement st3 = con.createStatement();
                ResultSet rs3 = st3.executeQuery( sqlStr3 );
                
                while(rs3.next()){
                	
                	num=num+1;
                }
                rs3.close();
                st3.close();
                
                System.out.println(num);
       
            con.close();
            
            out.println("Please waiting for reviewer, there are "+num+" reviewer(s) have reviewed your article.");
            out.println("You can get all feedback after 3 reviewer reviewing your article");
            out.println("If the article you uploaded have already reviewed by 3 reviewers, please try log out and enter your account again to see updated state.");
 %>
</body>
</html>