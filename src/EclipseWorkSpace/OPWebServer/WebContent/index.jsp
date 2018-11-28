<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>

<div align="center" style="margin-top: 50px;">
 
        <form name="submit">
        	<input type = "button" value = "提交新问题" onclick = "window.location.href = 'submitQuestion.jsp'"><br>
        </form>
        
        <br>
        <form name="query" action="queryQuestion?pageNum=1" method="post" accept-charset="UTF-8">
        	<input type = "submit" value = "查询已有问题">
        </form>
 
</div>
</body>
</html>