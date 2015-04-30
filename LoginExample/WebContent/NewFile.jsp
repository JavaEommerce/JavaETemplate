<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="xiaoming" scope="session" class="reviewer.Reviewer"></jsp:useBean>
	<%	xiaoming.setTest("123?");
		System.out.println(xiaoming.getTest());
	%>
	<jsp:setProperty property="test" name="xiaoming"/>
	<%
		xiaoming.setTest("abc");
	System.out.println(xiaoming.getTest());
	%>
</body>
</html>