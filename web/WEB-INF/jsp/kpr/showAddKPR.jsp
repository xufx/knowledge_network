<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>知识网络管理系统——添加知识点</title>
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
			.kpDiv
			{
				margin-top:30px;
			}
		</style>

</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：知识点管理  &gt; 添加知识点关系</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<div class="kpDiv">
	<select id="firstKP">
		<c:forEach items="${requestScope.kps}" var="kp" varStatus="stat">
			<option value="${kp.id}">${kp.name}</option>
		</c:forEach>
	</select>
	<select name="relation" id="relation">
		<option value="1">后续知识点</option>
		<option value="2">前续知识点</option>
		<option value="3">相关知识点</option>
	</select>
	<select id="secondKP">
		<c:forEach items="${requestScope.kps}" var="kp" varStatus="stat">
			<option value="${kp.id}">${kp.name}</option>
		</c:forEach>
	</select>
	<button id="btn_search">添加</button>
</div>
<div style="height:10px;"></div>
<script type="text/javascript">
	$(document).ready(function ()
	{
		/*点击查询知识点之间的关系是否存在*/
			$("#btn_search").click(function ()
			{
				//alert("进入jquery函数");
				var firstID=$("#firstKP").val();
				var relation=$("#relation").val();
				var secondID=$("#secondKP").val();
				alert("firstID="+firstID+",relation="+relation+",secondID="+secondID);
				var url="${ctx}/kpr/searchRelations?firstID="+firstID+"&relation="+relation+"&secondID="+secondID;
				if(firstID!=secondID)
				{
					$.get(url,function (data)
					{
						if(data[0]==true||data[1]==true||data[2]==true)
						{
							alert("此种关系已经存在，请重新选择知识点");
						}else
						{
							//alert("此种关系不存在，可以添加");
							addRelation(firstID,relation,secondID);
						}
					});
				}else
				{
					alert("请选择两个不同的知识点");
				}

			});
	});
	function addRelation(firstID,relation,secondID)
	{
			//alert("进入addRelation函数");
			var url="${ctx}/kpr/addRelations?firstID="+firstID+"&relation="+relation+"&secondID="+secondID;
		window.location.href=url;
	}
</script>
</body>
</html>