    <%@page import="Author.Author" %>
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>       
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    
<html>  
   <head>  
      	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	  	<link rel="stylesheet" href="css/upload.css" type="text/css" />	
	  	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
	  	<link rel="stylesheet" href="css/loginform.css" type="text/css"/>
	  	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />        
        <title>Upload</title>  
   </head>  
        
<body>  
	
	<div class = "logmod">
	<div class="logmod__wrapper">
		<div class="smart-green">
        <form class="smart-green" id="form5" method="post" enctype="multipart/form-data" action="Upload">  
        <h1>Upload Center
        <span><a href="index.jsp">Back to home page</a></span>
        </h1>
         <fieldset>
         <legend><span class="number"></span>Upload Form</legend>
         <%
         String authorname="";
         String email="";
         Author currentAuthor = (Author)session.getAttribute("Author");
         if(currentAuthor!=null){
         authorname = currentAuthor.getAuthorName();
         email = currentAuthor.getEmail();    
         }
         %>
        <label>main author name:</label>
        <%if(currentAuthor==null){ %>
        <input name="mainAuthor" type="text" id="mainAuthor"><br />   
        <%}else { %>
        <input name="mainAuthor" type="text" id="mainAuthor" value=<%=authorname %> readonly><br />   
        <%} %>
		<label>email address:</label>
		
		 <%if(currentAuthor==null){ %>
        <input name="mainEmail" type="text" id="mainEmail"><br />  
        <%}else { %>
        <input name="mainEmail" type="text" id="mainEmail" value=<%=email %> readonly><br />   
        <%} %>
	   
        <label>affiliation:</label>
        <input name="mainAffiliation" type="text" id="mainAffiliation">                         
        <label>other author name:(use","after each author's name)</label>
        <input name="otherAuthor" type="text" id="otherAuthor"><br />   
		<label>other email address:(use","after each author's email address)</label>
		<input name="otherEmail" type="text" id="otherEmail"><br />   
        <label>other affiliation:(use","after each author's affiliation)</label>
        <input name="otherAffiliation" type="text" id="mainAffiliation"><br />            
                
        <label>title:</label>
        <input name="title" type="text" id="title"><br /> 
        <label>abstract:</label>
        <textarea name="abstract" cols="250" rows="5" id="abstract"></textarea><br />
        </fieldset>
        
        <fieldset>
        <legend><span class="number"></span>keywords:(Do not need to fill in all 10 keyword forms)</legend>
          <input name="key1" type="text" id="key1"><input name="key2" type="text" id="key2"><br /> 
        <input name="key3" type="text" id="key3"><input name="key4" type="text" id="key4"><br /> 
        <input name="key5" type="text" id="key5"><input name="key6" type="text" id="key6"><br /> 
        <input name="key7" type="text" id="key7"><input name="key8" type="text" id="key8"><br /> 
        <input name="key9" type="text" id="key9"><input name="key10" type="text" id="key10"><br />             
        <label>choose your article:</label>            
        </fieldset>
        <input type="file" value="upload"  name="input_value5">
        <input type="submit"" class="button" value="submit" id="save5"> 
        
    </form> </div> </div></div>
     
     
 </body>
 </html>  