    <%@page import="Author.Author" %>
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>       
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    
    <html>  
      <head>          
        <title>Upload</title>  
      </head>  
        
		<body>  
        <form id="form5" method="post" enctype="multipart/form-data" action="Upload" >  
         <%
         String authorname="";
         String email="";
         Author currentAuthor = (Author)session.getAttribute("Author");
         if(currentAuthor!=null){
         authorname = currentAuthor.getAuthorName();
         email = currentAuthor.getEmail();    
         }
         %>
        main author name:<br />
        
        <%if(currentAuthor==null){ %>
        <input name="mainAuthor" type="text" id="mainAuthor"><br />   
        <%}else { %>
        <input name="mainAuthor" type="text" id="mainAuthor" value=<%=authorname %> readonly><br />   
        <%} %>
		email address:<br />
		
		 <%if(currentAuthor==null){ %>
        <input name="mainEmail" type="text" id="mainEmail"><br />  
        <%}else { %>
        <input name="mainEmail" type="text" id="mainEmail" value=<%=email %> readonly><br />   
        <%} %>
	   
        affiliation:<br />  <input name="mainAffiliation" type="text" id="mainAffiliation"><br />                          
        other author name:(use","after each author's name)<br />  <input name="otherAuthor" type="text" id="otherAuthor"><br />   
		other email address:(use","after each author's email address)<br />  <input name="otherEmail" type="text" id="otherEmail"><br />   
        other affiliation:(use","after each author's affiliation)<br />  <input name="otherAffiliation" type="text" id="mainAffiliation"><br />            
                
        title:<br />  <input name="title" type="text" id="title"><br /> 
        abstract:<br />  <textarea name="abstract" cols="250" rows="5" id="abstract"></textarea><br />
        keywords:(Do not need to fill in all 10 keyword forms)<br />  <input name="key1" type="text" id="key1"><input name="key2" type="text" id="key2"><br /> 
        <input name="key3" type="text" id="key3"><input name="key4" type="text" id="key4"><br /> 
        <input name="key5" type="text" id="key5"><input name="key6" type="text" id="key6"><br /> 
        <input name="key7" type="text" id="key7"><input name="key8" type="text" id="key8"><br /> 
        <input name="key9" type="text" id="key9"><input name="key10" type="text" id="key10"><br />             
        choose your article:<br />            
        <input type="file" value="upload"  name="input_value5">
        <input type="submit"" value="submit" id="save5">  

     
     </form>  
      </body>
    </html>  