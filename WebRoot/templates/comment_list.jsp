<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="comment-area" id="comment_list">
	<s:iterator value="comments" var="comment">
		<hr>
		<div class="comment-post">
			<a class="comment-post-name"><s:property value="#comment.name"/></a> <span class="comment-post-meta">&nbsp;&nbsp;at&nbsp;&nbsp;<s:property value="#comment.formatTime()"/></span>
			<p class="comment-post-content"><s:property value="#comment.content"/></p>
		</div>
	</s:iterator>
</div>
