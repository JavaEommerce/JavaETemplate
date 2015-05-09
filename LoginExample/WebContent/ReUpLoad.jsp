 <%@ page language="java" import="java.util.*,Author.Author,java.sql.*" pageEncoding="UTF-8"%>       
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
      <head>          
        <title>My JSP 'index.jsp' starting page</title>  
      </head>  
        
      <body>  
      <form id="form5" method="post" enctype="multipart/form-data" action="ReUpload" >  
<%
String url="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
String sqlStr = "select reviewername,reviseinfo,overalljudgement,reviewerlevel,summary,criticism,smallerrors from AuthorReviewer where authorname=";
String authorname="";
String reviewername="";
String reviseinfo="";
String overalljudgement="";
String reviewerlevel="";
String summary="";
String criticism="";
String smallerrors="";
Boolean canRe = true;
int reviewerNum = 0;

Author currentAuthor = (Author)session.getAttribute("Author");
authorname = currentAuthor.getAuthorName();
sqlStr = sqlStr+ "\""+authorname+"\"";

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection( url );
Statement st = con.createStatement();
ResultSet rs = st.executeQuery( sqlStr );

while(rs.next()){
	reviewername=rs.getString("reviewername");
	reviseinfo=rs.getString("reviseinfo");
	overalljudgement=rs.getString("overalljudgement");
	reviewerlevel=rs.getString("reviewerlevel");
	summary=rs.getString("summary");
	criticism=rs.getString("criticism");
	smallerrors=rs.getString("smallerrors");
	reviewerNum += 1;
	if(!rs.getString("reviseinfo").equals("")){
		canRe = false;
	}
%>
<input name="reviewername" type="hidden" id="reviewername" value=<%=reviewername %> readonly>
<input name="overalljudgement" type="text" id="overalljudgement" value="overalljudgement" readonly><br />   
<input name="reviewerlevel" type="text" id="reviewerlevel" value="reviewerlevel" readonly><br />   
<input name="summary" type="text" id="summary" value="summary" readonly><br />   
<input name="criticism" type="text" id="criticism" value="criticism" readonly><br />   
<input name="smallerrors" type="text" id="smallerrors" value="smallerrors" readonly><br />   
information:::<input name="reviseinfo" type="text" id="reviseinfo" value=<%=reviseinfo %> ><br />   
<br />   	


<%
}
rs.close();
st.close();
con.close();
%>

         
            
<%if(canRe){ %>                   
        <input type="file" value="upload"  name="input_value5">
        <input type="submit"" value="submit" id="save5">  

 <%} %>    
     </form>  
      </body>  
    </html>  