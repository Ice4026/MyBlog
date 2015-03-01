<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<%@ include file="templates/header.jsp"%>

<link href="resources/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/dist/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="resources/blog.css" rel="stylesheet">

<script src="resources/jquery/1.11.2/jquery.min.js"></script>
<script src="resources/dist/js/bootstrap.min.js"></script>


<body>
	<%-- Navigation Begin --%>
	<%@ include file="templates/navigation_index.jsp"%>
	<%-- End --%>

	<div class="container">

		<div class="blog-header">
			<h1 class="blog-title">Ice's Blog</h1>
			<p class="lead blog-description">My personal blog.</p>
		</div>

		<div class="row">
			<div class="col-sm-8 blog-main">
				<%-- Blog Begin --%>
				<%@ include file="templates/blogpost.jsp"%>
				<%-- End --%>
			</div>
			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<%-- About Begin --%>
				<%@ include file="templates/about.jsp"%>
				<%-- End --%>

				<%-- Archives Begin --%>
				<%@ include file="templates/archives.jsp"%>
				<%-- End --%>

				<%-- Elsewhere Begin --%>
				<%@ include file="templates/elsewhere.jsp"%>
				<%-- End --%>
			</div>
			<!-- /.blog-sidebar -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<%-- Footer Begin --%>
	<%@ include file="templates/footer.jsp"%>
	<%-- End --%>

</body>
</html>
