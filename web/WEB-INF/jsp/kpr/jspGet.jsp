<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib prefix="myfn" uri="http://www.yrom.net/jsp/string/fn" %>
<%@ taglib prefix="my" uri="http://www.jeelearning.com/myfunctions" %>

<%@ page import="cn.xufx.kn.jspGetData.MapTest" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>知识网络管理系统 ——知识点管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：知识点管理 &gt; 知识点查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>
<table width="50%" height="10%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	<!-- 获得后台List的数据，输入请求http://localhost:8080/jspGet -->
	<c:forEach var="student" items="${students}" varStatus="stat">
		<tr style="background-color: #7bbbd6">
			<td ><c:out value="${student.name}" default="wang" /></td>
			<td ><c:out value="${student.age}" default="wang" /></td>
		</tr>
	</c:forEach>
	<c:forEach var="student" items="${students}">
		<tr style="background-color: #8cc657">
			<td >${student.name}</td>
			<td >${student.age}</td>
		</tr>
	</c:forEach>

	<%--获得后台Map的数据，点击知识点关系查询--%>
	<tr>
		<td>知识点</td>
		<td>前续知识点</td>
	</tr>
</table>
<div>
	<% out.println("现在的时间："+new java.util.Date());%><br/>
</div>
<div>EL函数测试${my:reverse("poem")}</div>


</body>
</html>
