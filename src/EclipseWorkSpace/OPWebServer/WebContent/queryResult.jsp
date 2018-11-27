<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
       
<%
	String path = request.getContextPath();
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listQuestion");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello this queryResult.jsp</title>
<style>
th, tr, td, table {
	border: 1px solid red;
}
</style>
</head>

<body>
	<table>
		<tr>
			<th>title</th>
			<th>body</th>
			<th>answer</th>
			<th>submitter</th>
			<th>datetime</th>
		</tr>

		<%
		if (!list.isEmpty()) {
			for (Map<String, Object> map : list) {
		%>
		<tr>
				<td><%=map.get("title")%></td>
				<td><%=map.get("body")%></td>
				<td><%=map.get("answer")%></td>
				<td><%=map.get("submitter")%></td>
				<td><%=map.get("datetime")%></td>
		</tr>
		<%
				}
			}
		%>




	</table>
</body>
</html>