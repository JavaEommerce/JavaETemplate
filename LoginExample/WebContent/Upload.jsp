<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="Upload" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" /> <br />
<input type="submit" value="Upload File" /> <br />





<input name="author" type="text" id="author">


</form>
</body>
</html>