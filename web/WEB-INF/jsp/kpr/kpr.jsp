<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
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
	<style type="text/css">
		.kpid
		{
			display: none;
		}
		.kpColor{
			color: #942a25;
		}
		.kpTable
		{
			margin-top: 20px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			/** 获取上一次选中的知识点数据 */
			var boxs  = $("input[type='checkbox'][id^='box_']");

			/** 给全选按钮绑定点击事件  */
			$("#checkAll").click(function(){
				// this是checkAll  this.checked是true
				// 所有数据行的选中状态与全选的状态一致
				boxs.attr("checked",this.checked);
			})

			/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
			$("tr[id^='data_']").hover(function(){
				$(this).css("backgroundColor","#eeccff");
			},function(){
				$(this).css("backgroundColor","#ffffff");
			})


			/** 删除实验仪器绑定点击事件 */
			$("#delete").click(function(){
				/** 获取到用户选中的复选框  */
				var checkedBoxs = boxs.filter(":checked");
				if(checkedBoxs.length < 1){
					$.ligerDialog.error("请选择一个需要删除的知识点！");
				}else{
					/** 得到用户选中的所有的需要删除的ids */
					var ids = checkedBoxs.map(function(){
						return this.value;
					})

					$.ligerDialog.confirm("确认要删除吗?","删除知识点",function(r){
						if(r){
							// alert("删除："+ids.get());
							// 发送请求
							window.location = "${ctx }/kp/removeKP?ids=" + ids.get();
						}
					});
				}
			})
		})
	</script>
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10"></td></tr>
	<tr>
		<td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：知识点管理 &gt; 知识点关系查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	</tr>
</table>
<table class="kpTable">
	<tr>
		<td>知识点
			<input type="text" name="kp_id" id="kp_id" size="5" class="kpid" value="${kp.id}" readonly />
			<span class="kpColor">${kp.name}</span>
		</td>
	</tr>
</table>
<table  class="kpTable">
	<tr><td>前续知识点</td></tr>
	<c:forEach items="${requestScope.pre_kps}" var="pre_kp" varStatus="stat">
		<tr>
			<td  class="kpColor">${pre_kp.name}</td>
		</tr>
	</c:forEach>
</table>
<table  class="kpTable">
	<tr>
		<td>后续知识点
		</td>
	</tr>
	<c:forEach items="${requestScope.next_kps}" var="next_kp" varStatus="stat">
		<tr>
			<td class="kpColor">${next_kp.name}</td>
		</tr>
	</c:forEach>
</table>
<table  class="kpTable">
	<tr><td>相关知识点</td></tr>
	<c:forEach items="${requestScope.relate_kps}" var="relate_kp" varStatus="stat">
		<tr>
			<td class="kpColor">${relate_kp.name}</td>
		</tr>
	</c:forEach>
</table>
<a id="preManage" href="/kpr/preManage?kp_id=" name="kp_id">前续知识点管理</a>
<a id="nextManage" href="">后续知识点管理</a>
<a id="relateManage" href="">相关知识点管理</a>
<div style="height:10px;"></div>
<script type="application/javascript">
	$(document).ready(function ()
	{
		/*点击修改后续知识点*/
		$("#preManage").click(function ()
		{
			var kp_id=$('#kp_id').val();
		})
	})
</script>
</body>
</html>