<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<s:iterator value="blogs" var="b">
	<div class="blog-post">
		<h2 class="blog-post-title">
			<a href="detail?blog_id=<s:property value="#b.id"/>"><s:property value="#b.title" /></a>
		</h2>
		<p class="blog-post-meta"><s:property value="#b.formatPT()" />&nbsp;&nbsp;by&nbsp;&nbsp;<a><s:property value="#b.author.name"/></a>&nbsp;&nbsp;-&nbsp;&nbsp;<a href="detail?blog_id=<s:property value="#b.id"/>#comment_list"><s:property value="#b.comments.size()"/>&nbsp;comments</a></p>

		<s:property value="#b.briefContent()" escape="false"/>
	</div>
</s:iterator>

<nav>
  <ul class="pagination">

	<s:iterator value="pageNav" var="p" status="status">
		<s:if test="#status.first">
			<li class="<s:if test="#p==-1">disabled</s:if>"><a <s:if test="#p!=-1">href="index?page=<s:property value="page-1"/>"</s:if>>&laquo;</a></li>
		</s:if>
		<s:elseif test="#status.last">
			<li class="<s:if test="#p==-1">disabled</s:if>"><a <s:if test="#p!=-1">href="index?page=<s:property value="page+1"/>"</s:if>>&raquo;</a></li>
		</s:elseif>
		<s:else>
			<li class="<s:if test="#p==page">active</s:if>"><a href="index?page=<s:property value="#p"/>"><s:property value="#p"/></a></li>
		</s:else>
	</s:iterator>
	
  </ul>
</nav>

<script>

</script>