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
	<%--<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>--%>
	<script
			src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
	</script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/js/echarts.js" charset="UTF-8" language="JavaScript"></script>

	<script type="text/javascript" src="${ctx}/js/myecharts-kp.js" language="JavaScript"></script>

 	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<style type="text/css">
		.kpid
		{
			display: none;
		}
		.kpColor{
			color: #880000;
		}
		.kpTable,.btn_manage
		{
			margin-top: 20px;
		}
		.btn_delete
		{
			padding-left: 20px;
		}
		#kp_id
		{
			display: none;
		}
	</style>

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
			<input type="text" name="kp_id" id="kp_id" size="5" class="kp_id" value="${kp.id}" readonly />
			<span class="kpColor">${kp.name}</span>
		</td>
	</tr>
</table>
<table  class="kpTable">
	<tr>
		<td id="pre_relation">前续知识点</td>
		<td ><button class="btn_delete" data-relation="1" id="pre_delete">删除</button></td>
	</tr>
	<c:choose>
		<c:when test="${empty requestScope.pre_kps}">
				<tr>
					<td  class="kpColor">无</td>
				</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.pre_kps}" var="pre_kp" varStatus="stat">
				<tr>
					<td><input type="checkbox" id="prebox_${stat.index}" value="${pre_kp.id}"></td>
					<td  class="kpColor">${pre_kp.name}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<%--<c:forEach items="${requestScope.pre_kps}" var="pre_kp" varStatus="stat">
		<tr>
			<td  class="kpColor">${pre_kp.name}</td>
		</tr>
	</c:forEach>--%>
</table>
<table  class="kpTable">
	<tr>
		<td id="next_relation">后续知识点</td>
		<td ><button class="btn_delete" data-relation="2" id="next_delete">删除</button></td>
	</tr>
	<c:choose>
		<c:when test="${empty requestScope.next_kps}">
			<tr>
				<td  class="kpColor">无</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.next_kps}" var="next_kp" varStatus="stat">
				<tr>
					<td><input type="checkbox" id="nextbox_${stat.index}" value="${next_kp.id}"></td>
					<td  class="kpColor">${next_kp.name}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<table  class="kpTable">
	<tr>
		<td id="relate_relation">相关知识点</td>
		<td ><button class="btn_delete" data-relation="2" id="relate_delete">删除</button></td>
	</tr>
	<c:choose>
		<c:when test="${empty requestScope.relate_kps}">
			<tr>
				<td  class="kpColor">无</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.relate_kps}" var="relate_kp" varStatus="stat">
				<tr>
					<td><input type="checkbox" id="relatebox_${stat.index}" value="${relate_kp.id}"></td>
					<td  class="kpColor">${relate_kp.name}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div class="btn_manage">
	<a href="${ctx}/kpr/showAddKPR" id="addRelation">添加知识点关系</a><%--请求直接跳转--%>
</div>
<div style="height:600px; width:400px;">
	<div id="affix" style="width: 600px;height:600px; float:left;"></div>
</div>
<script type="text/javascript">
	$(function()
	{
		var kp_id=$("#kp_id").val();
		/** 获取选中的知识点 */
		var pre_boxs  = $("input[type='checkbox'][id^='prebox_']");
		var next_boxs  = $("input[type='checkbox'][id^='nextbox_']");
		var relate_boxs  = $("input[type='checkbox'][id^='relatebox_']");

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


		/*点击删除前续知识点*/
		$("#pre_delete").click(function ()
		{
			var checkedBoxs = pre_boxs.filter(":checked");
			if(checkedBoxs.length < 1){
				$.ligerDialog.error("请选择一个需要删除的前续知识点！");
			}else
			{
				/** 得到用户选中的所有的需要删除的ids */
				var ids = checkedBoxs.map(function(){
					return this.value;
				})

				$.ligerDialog.confirm("确认要删除吗?","删除知识点",function(r){
					if(r){
						alert("删除："+ids.get());
						// 发送请求
						window.location = "${ctx}/kp/removePreKP?kpID="+kp_id+"&ids=" + ids.get();
					}
				});
			}
		});
		/*点击删除后续知识点*/
		$("#next_delete").click(function ()
		{
			var checkedBoxs = next_boxs.filter(":checked");
			if(checkedBoxs.length < 1){
				$.ligerDialog.error("请选择一个需要删除的后续知识点！");
			}else
			{
				/** 得到用户选中的所有的需要删除的ids */
				var ids = checkedBoxs.map(function(){
					return this.value;
				})

				$.ligerDialog.confirm("确认要删除吗?","删除知识点",function(r){
					if(r){
						alert("删除："+ids.get());
						// 发送请求
						window.location = "${ctx}/kp/removeNextKP?kpID="+kp_id+"&ids=" + ids.get();
					}
				});
			}
		});
		/*点击删除相关知识点*/
		$("#relate_delete").click(function ()
		{
			var checkedBoxs = relate_boxs.filter(":checked");
			if(checkedBoxs.length < 1){
				$.ligerDialog.error("请选择一个需要删除的相关知识点！");
			}else
			{
				/** 得到用户选中的所有的需要删除的ids */
				var ids = checkedBoxs.map(function(){
					return this.value;
				})

				$.ligerDialog.confirm("确认要删除吗?","删除知识点",function(r){
					if(r){
						alert("删除："+ids.get());
						// 发送请求
						window.location = "${ctx}/kp/removeRelateKP?kpID="+kp_id+"&ids=" + ids.get();
					}
				});
			}
		})
    })

</script>

<!--知识网络可视化-->
<script type="text/javascript">
	$(function()
	{
		var kp_id=$("#kp_id").val();
		var echarts_affix = echarts.init(document.getElementById('affix'));
		//echarts_affix.showLoading();
		$.post(
				/*发送请求，然后进入请求处理后的页面*/
				'${pageContext.request.contextPath}/echarts/knowledge-point?level=3&spread=true&id=' + kp_id,
				function (response)
				{//发送请求成功后获取后台返回的json数据response
                    getResponse(response);
					//echarts_affix.hideLoading();
					kp_echarts(echarts_affix, response);
				});
	})
    function  getResponse(response)
    {
        //alert(typeof(response));
		/*将json字符串转化为json对象*/
        var obj=eval('('+response+')');
        console.log(obj);
        alert(typeof(obj));
        for(var str in obj)
        {
			if (str=="edges")
			{
				for(var edge in obj[str])
				{/*o:数组中的每个对象*/
					var o=obj[str][edge];
					console.log(o);
					//console.log(o.source);
				}
			}else if(str=="name")
			{
				console.log(str+":"+obj[str]);
			}else if (str=="nodes")
			{
				for(var node in obj[str])
				{/*o:数组中的每个对象*/
					var o=obj[str][node];
					console.log(o);
					//console.log(o.name);
				}
			}
        }
    }

</script>

</body>
</html>