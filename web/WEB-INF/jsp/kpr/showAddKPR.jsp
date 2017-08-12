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

</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：知识点管理  &gt; 添加知识点</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/kp/addKP" id="kpForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">知识点名称：<input type="text" name="name" id="name" size="30"/></td>
		    		</tr>
					<tr>
						<td class="font3 fftd">知识点简介：<br/>
							<textarea name="remark" cols="88" rows="3" id="remark">${kp.remark }</textarea>
						</td>
					</tr>
					<tr>
						<td class="font3 fftd">内容：<br/>
							<textarea name="content" cols="88" rows="3" id="content">${kp.content }</textarea></td>
					</tr>
					<tr>
						<td class="font3 fftd">学习目标：<br/>
							<textarea name="learnGoal" cols="88" rows="3" id="learnGoal">${kp.learnGoal}</textarea>
						</td>
					</tr>
					<tr>
						<td class="font3 fftd">考试频率：
							<select name="examFrequency" style="width:143px;">
								<option value="0">--请选择--</option>
								<option value="1">没考过</option>
								<option value="2">偶尔考</option>
								<option value="3">经常考</option>
								<option value="4">高频考点</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="font3 fftd">学习文档：<input type="text" name="doc" id="doc" size="20"/></td>
					</tr>
					<tr>
						<td class="font3 fftd">学习视频：<input type="text" name="video" id="video" size="20"/></td>
					</tr>
					<tr>
						<td class="font3 fftd">参考资料：<br/>
							<textarea name="reference" cols="88" rows="3" id="reference">${kp.reference }</textarea></td>
					</tr>
					<tr>
						<td class="font3 fftd">备注：<br/>
							<textarea name="memo" cols="88" rows="3" id="memo">${kp.memo }</textarea>
						</td>
					</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="添加 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
<script type="text/javascript">
	$(function(){
		/** 知识点表单提交 */
		$("#kpForm").submit(function(){
			var name = $("#name");
			var remark = $("#remark");
			var name = $("#content");
			var name = $("#learnGoal");
			var name = $("#learnAdvise");
			var name = $("#examFrequency");
			var name = $("#reference");
			var name = $("#memo");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "知识点名称不能为空！";
				name.focus();
			}
			else if ($.trim(remark.val()) == ""){
				msg = "知识点简介不能为空！";
				remark.focus();
			}
			else if ($.trim(content.val()) == ""){
				msg = "内容不能为空！";
				content.focus();
			}
			else if ($.trim(learnGoal.val()) == ""){
				msg = "学习目标不能为空！";
				learnGoal.focus();
			}
			else if ($.trim(reference.val()) == ""){
				msg = "参考资料不能为空！";
				reference.focus();
			}
			else if ($.trim(examFrequency.val()) == ""){
				msg = "考试频率不能为空！";
				examFrequency.focus();
			}

			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#kpForm").submit();
		});
	});
</script>
</body>
</html>