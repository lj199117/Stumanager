<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	  $("#b01").click(function(){
	  htmlobj=$.ajax({url:"getUserList.action?start="+0+"&limit="+2,async:false});
	  
	  var jsonObj = window.JSON.parse(htmlobj.responseText); 
	    var str = "结果如下：<br>";
	    str += "<span>Total:"+jsonObj.total+"</span><br><span>Data:";  
	   for (var i=0;i<jsonObj.data.length ; i++){  
	       str += "<br>id:" + jsonObj.data[i].stuId + 
	       ",<br>name:" + jsonObj.data[i].stuName+
	       ",<br>remark:"+ jsonObj.data[i].remark;  
	   }  
	   str += "</span><br>";  
   
	  $("#myDiv").html(str);
	  });
	});
</script>
</head>

<body>
	<div id="myDiv"><h2></h2></div>
	<button id="b01" type="button">获取内容</button>
</body>
</html>
