 <%@ page language="java" import="java.util.*,Author.Author,java.sql.*" pageEncoding="UTF-8"%>       
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
      <head> 
      	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	  	<link rel="stylesheet" href="css/upload.css" type="text/css" />	
	  	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	  	<link rel="stylesheet" href="css/loginform.css" type="text/css"/>
	  	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />         
        <title>My JSP 'index.jsp' starting page</title>  
      </head>  
        
      <body>  
      <div class = "logmod">
		<div class="logmod__wrapper">
		<div class="smart-green">
      
      <form id="form5" method="post" enctype="multipart/form-data" action="ReUpload" >  
      <h1>Please write revision information to all reviewers even they accept your article</h1>
<%
String url="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
String sqlStr = "select reviseaccepted,revisetime,reviewername,reviseinfo,overalljudgement,reviewerlevel,summary,criticism,smallerrors,submitdate from AuthorReviewer where authorname=";
String authorname="";
String reviewername="";
String reviseinfo="";
String overalljudgement="";
String reviewerlevel="";
String summary="";
String criticism="";
String smallerrors="";
String displayword="";
Boolean canRe1 = true;
Boolean canRe2 = true;
Boolean accepted = true;
Boolean canpublish = true;
Boolean display = true;
int reviewerNum = 0;
java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
java.sql.Date submitDate = new java.sql.Date(System.currentTimeMillis());

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
	submitDate=rs.getDate("submitdate");
	System.out.println(rs.getBoolean("reviseaccepted"));
	reviewerNum += 1;
	if(!rs.getString("reviseinfo").equals("")){
		canRe1 = false;
	}
	if(rs.getInt("revisetime")>=2&&!rs.getBoolean("reviseaccepted")){
		canRe2 = false;
	}
	if(!rs.getBoolean("reviseaccepted")){
		canpublish = false;
		displayword="rejects your article";
	}else{
		displayword="accepts your article";
	}
	
	long differ = currentDate.getTime()-submitDate.getTime();
	long time = 7*24*60*60*1000;
	long difference = differ/time;
	if(difference<1){		display=false;%>

		<font color=red >reviewer <%=reviewername %> is reviewing, please waiting for result</font>
		<br />
<%}else{
	
	
%>
<fieldset>
reviewer name:<br />
<%if(reviewerNum==1){ %>
<input name="reviewername1" type="text" id="reviewername1" value=<%=reviewername %> readonly>&nbsp&nbsp<%=displayword %><br />
<%} %>
<%if(reviewerNum==2){ %>
<input name="reviewername1" type="text" id="reviewername1" value=<%=reviewername %> readonly>&nbsp&nbsp<%=displayword %><br />
<%} %>
<%if(reviewerNum==3){ %>
<input name="reviewername1" type="text" id="reviewername1" value=<%=reviewername %> readonly>&nbsp&nbsp<%=displayword %><br />
<%} %>
overalljudgement:<br />
<%=overalljudgement %><br /><br />   
reviewerlevel:<br />
<%=reviewerlevel %><br /><br />   
summary:<br />
<%=summary %><br /><br />   
criticism:<br />
<%=criticism%><br /><br />   
smallerrors:<br />
<%=smallerrors %><br />   
revision information<br />
<%if(reviewerNum==1){ %>
<textarea name="reviseinfo1" cols="250" rows="5" id="reviseinfo1" ><%=reviseinfo %></textarea><br />
<%} %>
<%if(reviewerNum==2){ %>
<textarea name="reviseinfo2" cols="250" rows="5" id="reviseinfo2" ><%=reviseinfo %></textarea><br />
<%} %>
<%if(reviewerNum==3){ %>
<textarea name="reviseinfo3" cols="250" rows="5" id="reviseinfo3" ><%=reviseinfo %></textarea><br />
<%} %>

<br />   	




</fieldset>

<%
	}
}
rs.close();
st.close();
con.close();
%>

<%if(display){ %>

<%if(!canRe1){ %>
<font color=red >please waiting for reviewing.......</font>
<%} %>         
<%if(!canRe2){ %>
<font color=red >Sorry, your article cannot be published...you can upload another article later...</font>
<%} %>     
<%if(canpublish) {%>
<font color=red >your article will be published,please wait......</font>
<%} %>
            
<%if(canRe1&&canRe2&&!canpublish){System.out.println(canRe1);System.out.println(canRe2);System.out.println(!canpublish); %>                   
        <input type="file" value="upload"  name="input_value5">
        <input type="submit"" value="submit" id="save5">  
<%} %>
 <%} %>    
     </form> </div></div></div> 
      </body>  
    </html>  