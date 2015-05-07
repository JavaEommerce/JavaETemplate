    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>       
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
      <head>          
        <title>My JSP 'index.jsp' starting page</title>  
      </head>  
        
      <body>  
        <form id="form5" method="post" enctype="multipart/form-data" action="Upload" >  
         
        main author name:<input name="mainAuthor" type="text" id="mainAuthor"><br />   
		email address:<input name="mainEmail" type="text" id="mainEmail"><br />   
        affiliation:<input name="mainAffiliation" type="text" id="mainAffiliation"><br />           
                
        other author name:<input name="otherAuthor" type="text" id="otherAuthor"><br />   
		other email address:<input name="otherEmail" type="text" id="otherEmail"><br />   
        other affiliation:<input name="otherAffiliation" type="text" id="mainAffiliation"><br />            
                
        title:<input name="title" type="text" id="title"><br /> 
        abstract:<textarea name="abstract" cols="250" rows="5" id="abstract"></textarea><br />
        keywords:<input name="key1" type="text" id="key1"><input name="key2" type="text" id="key2">
        <input name="key3" type="text" id="key3"><input name="key4" type="text" id="key4">
        <input name="key5" type="text" id="key5"><input name="key6" type="text" id="key6">
        <input name="key7" type="text" id="key7"><input name="key8" type="text" id="key8">
        <input name="key9" type="text" id="key9"><input name="key10" type="text" id="key10"><br />             
                   
        <input type="file" value="upload"  name="input_value5">
        <input type="submit"" value="submit" id="save5">  

     
     </form>  
      </body>  
    </html>  