<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  page import="myutil.*"%>

<%
	String path = request.getContextPath();
	String proname=(String)request.getAttribute("proname");
	proname=(proname==null?"":proname);
	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listproduct");
	DividePage pDividePage=(DividePage)request.getAttribute("pDividePage");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>查询结果</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="" name=keywords>
<META content=name=description>
<STYLE type=text/css>
.neon {
	FILTER: glow(color =                             
		                                                            
		                                    #002E60, strength =             
		                             
		 
		      
		                    
		                                                     3)
}

DIV {
	WIDTH: 70px
}

BODY {
	MARGIN: 0px
}

BODY {
	MARGIN-TOP: 0px;
	SCROLLBAR-FACE-COLOR: #005fc5;
	FONT-SIZE: 12px;
	BACKGROUND: #ffffff;
	SCROLLBAR-HIGHLIGHT-COLOR: #799ae1;
	SCROLLBAR-SHADOW-COLOR: #799ae1;
	SCROLLBAR-3DLIGHT-COLOR: #005fc5;
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-TRACK-COLOR: #aabfec;
	SCROLLBAR-DARKSHADOW-COLOR: #799ae1
}
</STYLE>

<META content="MSHTML 6.00.2800.1106" name=GENERATOR>
<style type="text/css">
<!--
.STYLE7 {
	COLOR: #003366;
	font-size: 12px;
}
-->
</style>
<script type="text/javascript">
	function search() {//在网页上进行查询的操作
		var th = document.form1;
		th.action = "<%=path%>/servlet/ProductAction?action_flag=list";//要是进行了查询的操作就使服务器中的数据库 调用这个action
		th.submit();

	}
	//javascript:first();
	function  first(){
	
	var th=document.form1;
	th.action="<%=path%>/servlet/ProductAction?action_flag=list&PagerNum=1";//先显示第一页
		th.submit();
	}
		//javascript:forward();
	function forward() {
		var th = document.form1;
		th.action = "<%=path%>/servlet/ProductAction?action_flag=list&PagerNum=<%=pDividePage.getCurrentPager()-1%>";//显示上一页
		th.submit();
	}
	//javascript:next();
	function next() {
		var th = document.form1;
			th.action = "<%=path%>/servlet/ProductAction?action_flag=list&PagerNum=<%=pDividePage.getCurrentPager()+1%>";//显示下一页
		th.submit();
	}
		function end() {
		var th = document.form1;
			th.action = "<%=path%>/servlet/ProductAction?action_flag=list&PagerNum=<%=pDividePage.getCountpager()%>";
		th.submit();
	}
	function changePage(Currentpager)//这个函数 数 是选择相应的pager
	{var th = document.form1;//找到第一个表单
			th.action = "<%=path%>/servlet/ProductAction?action_flag=list&PagerNum="+ Currentpager;//设置我要显示的页
		th.submit();

	}
	function selectAll(flag) {
		var ids = document.getElementsByName("ids");//  getElementsByName() 方法可返回带有指定名称的对象的集合。
		
		for ( var i = 0; i < ids.length; i++) {
			ids[i].checked = flag;//也就是要是全选checkbox的选择情况 就是ids的选择情况
		    
		
		}
    
	}
	function  getselectCount() {
		var count = 0;//返回的条数
		var ids = document.getElementsByName("ids");
		for ( var i = 0; i < ids.length; i++) {
			if (ids[i].checked==true) {//不知道为什么 一定要ids[i].checked==true这样写  要不然是不行的
				count++;
			}
		}
		return count;
	}

	function del() {
		var th = document.form1;
		  //alert(selectAll());

	    if (getselectCount() < 1) {
			alert("至少选择一个选项!!!!!!");
			return;
		}
		th.action = "<%=path%>/servlet/ProductAction?action_flag=del";
		th.submit();
	}
	
	function getSelectValue()
	{
	     var ids = document.getElementsByName("ids");
		for ( var i = 0; i < ids.length; i++) {
			if (ids[i].checked==true) {//不知道为什么 一定要ids[i].checked==true这样写  要不然是不行的
				return ids[i].value;
			}
		}
	}
	
	function view() {
		var th = document.form1;
		if(getselectCount()<1)
		{	
		alert("至少选择一个选项!!!!!!");
			return;	
		}
		 if(getselectCount()>1)
		{ 
		alert("只能选择一个选项!!!!!!");
			return;
		}
		//alert(getSelectValue());
        th.action="<%=path%>/servlet/ProductAction?action_flag=view&proid="+ getSelectValue();
		th.submit();
	}
</script>
</HEAD>
<BODY bgColor="#ffffff">
	<form action="" name="form1" method="post">
		<TABLE height=433 cellSpacing=0 cellPadding=0 width=580 align=center
			border=0>
			<TBODY>
				<TR>
					<TD colSpan=3 height=9></TD>
				</TR>
				<TR>
					<TD vAlign=top width=12></TD>
					<TD width=739 height=9></TD>
					<TD width=9 height=9>
					</TD>
				</TR>
				<TR>
					<TD vAlign=top align=right width=739 height=415>
						<TABLE cellSpacing=0 cellPadding=0 width=556 border=0>
							<!--DWLayoutTable-->
							<TBODY>
								<TR>
									<TD vAlign=bottom width=548 height=27> <span
										class="lbt">产品管理模块&gt;&gt;产品信息_查询</span>
									</TD>
									<TD width=8 rowSpan=3>&nbsp;</TD>
								</TR>
								<TR>
									<TD  height=22></TD>
								</TR>
								<TR>
									<TD class=unnamed1 vAlign=top height=9>
										<TABLE width="99%" border=0 cellPadding=4 cellSpacing=1
											bgColor=#0867b3>
											<TBODY>
												<TR bgColor=#ffffff height=20>
													<TD width=13% noWrap class="STYLE7">产品名称</TD>
													<TD width=31% noWrap><span class="STYLE7"> <INPUT
															class=text2 maxLength=20 size=18 name="proname"
															minLength="1" value="<%=proname%>"> </span>
													</TD>

												</TR>

											</TBODY>
										</TABLE> <br>
									</TD>
								</TR>
								<TR>
									<TD height=27 colspan="2" align="center" vAlign=top><a
										href="javascript:search();"></a>&nbsp; <a
										href="<%=path%>/product/2_1_5tj.jsp"></a>
									</TD>
								</TR>
								<TR>
									<TD height=94 vAlign=top> <span
										class="lbt">查询结果</span> <br> <br>
										<TABLE width="99%" border=0 cellPadding=4 cellSpacing=1
											bgColor=#0867b3>
											<!--DWLayoutTable-->
											<TBODY>
												<TR bgColor=#ffffff height=20>
													<TD width=24 height="20" align="center" noWrap
														class="STYLE7"><input type="checkbox" name="checkall"
														value="" onclick="javascript:selectAll(this.checked);">
													</TD>
													<TD width=70 align="center" noWrap class="STYLE7">
														产品名称</TD>
													<TD width=60 align="center" noWrap class="STYLE7">
														产品产地</TD>
													<TD width=128 align="center" noWrap class="STYLE7">
														产品价格</TD>
												</TR>
												<%
													if (!list.isEmpty()) {
														for (Map<String, Object> map : list) {
												%>
												<TR bgColor=#ffffff height=2>
													<TD height="28" align="center" valign="top" noWrap><input
														name="ids" type="checkbox" class="text2"
														value="<%=map.get("proid")%>"></TD>
													<TD align="center" valign="middle" noWrap><span
														class="foot3"><%=map.get("proname")%></span>
													</TD>
													<TD align="center" valign="middle" noWrap class="foot3"><%=map.get("proaddress")%></TD>
													<TD align="center" valign="middle"><span class="foot3"><%=map.get("proprice")%></span>
													</TD>
												</TR>
												<%
													}
													}
												%>
											</TBODY>
										</TABLE> <br> <a href="javascript:del();"> </a>&nbsp; 
											<a  href="javascript:view();"></a> <br>
										<table width="99%" border=0 cellpadding=4 cellspacing=1
											bgcolor=#0867b3>
											<!--DWLayoutTable-->
											<tbody>
												<tr bgcolor=#ffffff height=20>
													<td height="20" align="center" valign="middle" nowrap>
														<span class="STYLE7">共<%=pDividePage.getCountpager()%>页</span>&nbsp;
														<a href="javascript:first();" class="dh2">首页</a>&nbsp; <a
														href="javascript:forward();" class="dh2">上一页</a>&nbsp; <a
														href="javascript:next();" class="dh2">下一页</a>&nbsp; <a
														href="javascript:end();" class="dh2">尾页</a>&nbsp; <span
														class="STYLE7">跳转到</span> <select name="select"
														class="text2" style="WIDTH: 40px"
														onchange="changePage(this.value)">
															<%
																int len = pDividePage.getCountpager();
																for (int i = 1; i <= len; i++) {
															%>
															<option value="<%=i%>"
																<%=(i == pDividePage.getCurrentPager()) ? "selected": ""%>>
																<%=i%>
															</option>
															<%
																}
															%>
													</select>
													</td>
												</tr>
											</tbody>
										</table>
									</TD>
									<TD></TD>
								</TR>
								<TR>
									<TD height=20>&nbsp;</TD>
									<TD></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=9 ></TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>
	<IFRAME name=top align=default src="<%=path%>/bottom.jsp" frameBorder=0
		width=100% scrolling=no height=88>
		<h1>&nbsp;</h1>
	</IFRAME>
</BODY>
</HTML>
