<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="Download" enctype="multipart/form-data" method="POST" target="aa">
<input type="file" name="myfile" />
<input type="submit" />
</form>
<iframe name="aa" src="" style="display:none"></iframe>



</body>
</html>