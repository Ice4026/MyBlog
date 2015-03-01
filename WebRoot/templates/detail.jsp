<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="blog-post">
	<h2 class="blog-post-title">
		<s:property value="blog.title" />
	</h2>
	<div class="row">
		<div class="col-xs-9">
			<p class="blog-post-meta"><s:property value="blog.formatPT()" />&nbsp;&nbsp;by&nbsp;&nbsp;<a><s:property value="blog.author.name"/></a></p>
		</div>
		<s:if test="#session.user_id > 0">
			<div class="col-xs-1">
				<%-- 编辑按钮 --%>
				<form id="editSubmit" action="admin/writer_update" method="post">
					<a id="editIcon"><span class="glyphicon glyphicon-pencil"></span></a>
					<input type="hidden" name="blog_id"
						value="<s:property value="blog.id"/>" />
				</form>
			</div>
			<div class="col-xs-1">
				<%-- 删除按钮 --%>
				<form id="deleteSubmit" action="admin/blog_delete" method="post">
					<a id="deleteIcon"><span class="glyphicon glyphicon-remove"></span></a>
					<input type="hidden" name="blog_id"
						value="<s:property value="blog.id"/>" />
				</form>
			</div>
		</s:if>
	</div>
	<s:property value="blog.content" escape="false" />
</div>

<!-- 模态框 -->
<div class="modal fade" id="deleteModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">删除确认</h4>
			</div>
			<div class="modal-body">
				<p>该日志删除后将无法恢复！是否确认删除？</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="yes">是的</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
	$(document).ready(function() {
		$("a#editIcon").click(function() {
			$("form#editSubmit").submit();
		});
		$("a#deleteIcon").click(function() {
			$("#deleteModal").modal({
				keyboard : false
			});
		});
		$("button#yes").click(function() {
			$("form#deleteSubmit").submit();
		});
	});
</script>