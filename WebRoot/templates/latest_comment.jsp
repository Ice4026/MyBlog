<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="sidebar-module">
	<h4>Latest Comments</h4>
	<ol class="list-unstyled">
		<!-- <li><a href="#">GitHub</a></li>
		<li><a href="#">Twitter</a></li>
		<li><a href="#">Facebook</a></li> -->
		<s:iterator value="comments" var="c">
			<a><li><s:property value="#c.name"/>&nbsp;&nbsp;:<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#c.content"/></li></a>
			<hr>
		</s:iterator>
	</ol>
</div>