<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@taglib uri="/struts-tags" prefix="s"%> --%>

<div class="sidebar-module">
	<h4>Archives</h4>
	<ol class="list-unstyled">
		<s:iterator value="recentMonth" var="m">
			<li><a href="#"><s:property value="#m" /></a></li>
		</s:iterator>
	</ol>
</div>
