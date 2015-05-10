<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submission Guide</title>
<link rel="stylesheet" href="css/style_guide.css" type="text/css" charset="utf-8" />	
</head>

<body>
	<div id="background" background = "#80B763">
		<div id="page">
			<div id="contents">
				<div id="main">
					<p>
						<b>How to submit</b><br/>
						If you are not the author of our journal, don't worry! This Guide will tell you<br />
						how to submit a article.<br />
						1. Click <a href="Upload.jsp">Upload</a> from index.<br />
						2. Fill in all the information. Then the main author will be the author of our website.<br />
						3. Choose an article from your computer, it should be a .pdf file.
						4. Press submit button and wait for review. 
					</p>

					<p>
						<b>Remind</b><br/>
						You are recommended to write article following the style of our template.<br />
						Please click the button below to download the template.
					</p>

					<form action="SubmissionGuide" enctype="multipart/form-data" method="POST" target="aa">
					<input type="submit" name="button" value="down template" onclick="SubmissionGuide"/>
					</form>

				</div>
			</div> <!-- /#contents -->
		</div> <!-- /#page -->
	</div> <!-- /#background -->
</body>
</html>






<%-- <body>
First click Upload button in the index page,
fill all the information and choose a article in pdf style then press submit.

<form action="SubmissionGuide" enctype="multipart/form-data" method="POST" target="aa">
<input type="submit" name="button" value="down template" onclick="SubmissionGuide"/>
</form>


</body>
</html> --%>