<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form name="form" method="post"
		action="servlet/GetUserInfoServlet">
		Username：<input type="text" name="USER_NAME" value="" /><br /> 
		IP：<input type="text" name="IP" value="" /><br />
		Local_longitude：<input type="text" name="Local_longitude" value="" /><br />
		Local_latitude：<input type="text" name="Local_latitude" value="" /><br />
		<!-- Username：<input type="text" name="USER_NAME" value="" /><br /> 
		Password：<input type="text" name="PASSWORD" value="" /><br /> -->
		<input type="submit" name="submit" value="查询">
	</form>
</body>
</html>
