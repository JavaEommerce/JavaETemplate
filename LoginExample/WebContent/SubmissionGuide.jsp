<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
First click Upload button in the index page,
fill all the information and choose a article in pdf style then press submit.

<form action="SubmissionGuide" enctype="multipart/form-data" method="POST" target="aa">
<input type="submit" name="button" value="down template" onclick="SubmissionGuide"/>
</form>
<iframe name="aa" src="" style="display:none"></iframe>

</body>
</html>