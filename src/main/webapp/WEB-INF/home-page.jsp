<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
	<h1>This is homepage.</h1>
	
	<%for(int i=0; i<10; i++){%>
		<h2>This is line <%=i + 1%></h2>
	<%} %>
</body>
</html>