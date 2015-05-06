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
        keywords:<input name="key" type="text" id="key"><input name="key" type="text" id="key">
        <input name="key" type="text" id="key"><input name="key" type="text" id="key">
        <input name="key" type="text" id="key"><input name="key" type="text" id="key">
        <input name="key" type="text" id="key"><input name="key" type="text" id="key">
        <input name="key" type="text" id="key"><input name="key" type="text" id="key"><br />             
                   
        <input type="file" value="upload"  name="input_value5">
        <input type="submit"" value="submit" id="save5">  

     
     </form>  
      </body>  
    </html>  