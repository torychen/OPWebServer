<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    
<%@  page import="myutil.*"%>

<%
	String path = request.getContextPath();
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listQuestion");
	DividePage pDividePage=(DividePage)request.getAttribute("pDividePage");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello this is queryResult</title>
<style>
th, tr, td, table {
	border: 1px solid black;
}
</style>

<script type="text/javascript">
	function login() {
		alert("用户名 不能为空!!!!");
	
		return;

		
		/*
		if (th.password.value == "") {
			alert("密码 不能为空!!!!");
			th.password.focus();
			return;
		}
		th.action = "<%=path%>/servlet/LoginAction";
		th.submit();
		*/
	}
</script>


</head>

<body>
<form action="" name="form1">
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
		
		<a href="javascript:login();">首页</a>
		<a href="javascript:login();">上一页</a>
		<a href="javascript:login();">下一页</a>
		<a href="javascript:login();">末页</a>
		<a href="javascript:login();">共<%=pDividePage.getCountpager()%>页 共<%=pDividePage.getRecoderCount() %>条记录</a>
</form>
</body>
</html>