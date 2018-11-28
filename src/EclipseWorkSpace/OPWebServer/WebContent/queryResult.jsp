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
	function  firstPage(){
		var th=document.form1;
		th.action="<%=path%>/queryQuestion?pageNum=1";//显示第一页
		th.submit();
	}
	
	function previousPage() {
		var th = document.form1;
		th.action = "<%=path%>/queryQuestion?pageNum=<%=pDividePage.getCurrentPager()-1%>";//显示上一页
		th.submit();
	}
	
	function nextPage() {
		var th = document.form1;
			th.action = "<%=path%>/queryQuestion?pageNum=<%=pDividePage.getCurrentPager()+1%>";//显示下一页
		th.submit();
	}
	
	function lastPage() {
		var th = document.form1;
			th.action = "<%=path%>/queryQuestion?pageNum=<%=pDividePage.getCountpager()%>";
		th.submit();
	}
</script>


</head>

<body>
<form action="" name="form1" method="post">
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
		
		<a href="javascript:firstPage();">首页</a>
		<a href="javascript:previousPage();">上一页</a>
		<a href="javascript:nextPage();">下一页</a>
		<a href="javascript:lastPage();">末页</a>
		<a href="javascript:void(0);">当前为第<%=pDividePage.getCurrentPager() %>页 共<%=pDividePage.getCountpager()%>页 共<%=pDividePage.getRecoderCount() %>条记录</a>
</form>
</body>
</html>