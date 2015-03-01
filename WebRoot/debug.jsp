<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>Ice's Blog | Home</title>
<link href="resources/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/dist/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="resources/blog.css" rel="stylesheet">

<script src="resources/jquery/1.11.2/jquery.min.js"></script>
<script src="resources/dist/js/bootstrap.min.js"></script>
</head>

<body>
	<s:debug></s:debug>
</body>
</html>
