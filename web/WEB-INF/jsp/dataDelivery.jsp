<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>复杂数据传输</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script language="javascript" type="text/javascript">
        function submitData()
        {
            var idList=["1","2","3"];
            alert(idList);
            var isBatch=false;
            $.ajax(
                    alert("进入$.ajax"),
                    url="http://localhost:8080/data/listDelivery?fn=deleteCatalogSchemes",
                    alert(url),
                    dataType='json',
                    type='POST',
                    data={"idList":idList,"isBatch":isBatch},
                    success=function ()
                    {
                        alert("success");
                        console.log("成功");
                    },
                    error=function(){
                        console.log("失败");
                    }
        );
           /* $.post("${pageContext.request.contextPath}/data/listDelivery",
                    function(result){
                        if(result.success){
                            $.messager.alert("系统提示","成功");
                        }else{
                            $.messager.alert("系统提示","失败");
                        }
                    },"json");*/
}
    </script>
</head>
<body>
<%response.flushBuffer();%>
<h2>Hi ,this is dataDelivery.jsp</h2>
<button onclick="submitData()">提交List</button>
</body>
</html>