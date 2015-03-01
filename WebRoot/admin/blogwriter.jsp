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

<title>Ice's Blog</title>
<link href="favicon.ico" rel="shortcut icon">

<link href="resources/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/dist/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="resources/blog.css" rel="stylesheet">

<script src="resources/jquery/1.11.2/jquery.min.js"></script>
<script src="resources/dist/js/bootstrap.min.js"></script>
</head>

<body>
	<%-- Navigation Begin --%>
	<%@ include file="/templates/navigation_index.jsp"%>
	<%-- End --%>

	<div class="container">

		<div class="blog-header">
			<h1 class="blog-title">Ice's Blog</h1>
			<p class="lead blog-description">My personal blog.</p>
		</div>

		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10 blog-main">
				<%-- Blog Begin --%>
				<%@ include file="/templates/editor.jsp"%>
				<%-- End --%>
			</div>
			<div class="col-xs-1"></div>
		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<%-- Footer Begin --%>
	<%@ include file="/templates/footer.jsp"%>
	<%-- End --%>

</body>
</html>
