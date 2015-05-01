<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>write e-mail</title>
</head>

<body>
<form name="form1" method="post" action="SendEmail">
<table width="75" border="0" align="center" cellspacing="1" bgcolor="#006600" class="black">
<tr bgcolor="#FFFFFF"> 
<td width="24%">to:</td>
<td width="76%"> 
<input name="to" type="text" id="to"></td>
</tr>
<tr bgcolor="#FFFFFF"> 
<td>title:</td>
<td> 
<input name="title" type="text" id="title"></td>
</tr>
<tr> 
<td height="107" colspan="2" bgcolor="#FFFFFF"> 
<textarea name="content" cols="50" rows="5" id="content"></textarea></td>
</tr>
<tr align="center"> 
<td colspan="2" bgcolor="#FFFFFF"> 
<input type="submit" name="Submit" value="submit">
<input type="reset" name="Submit2" value="reset">
</td>
</tr>
</table>
</form>
</body>
</html> 