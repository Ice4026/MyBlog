<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script src="resources/wysiwyg/external/jquery.hotkeys.js"></script>
<script
	src="resources/wysiwyg/external/google-code-prettify/prettify.js"></script>

<script type="text/javascript" charset="utf-8" src="UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="UEditor/ueditor.all.min.js"></script>

	<form role="form"
		action="admin/<s:if test="newOrNot==0">blog_new</s:if><s:else>blog_update</s:else>"
		method="post">
		<!-- title begin -->
		<div class="row form-group form-group-lg">
			<!-- <div class="col-xs-1"></div> -->
			<div class="col-xs-12">
				<div class="input-group input-group-lg">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" id="categoryBtn" value="">
							<s:if test="newOrNot==0">
								<s:property value="categories[0].name" />
							</s:if>
							<s:else>
								<s:property value="blog.category.name" />
								<s:if test="blog.category.privacy==0">
									<span class="glyphicon glyphicon-euro"></span>
								</s:if>
							</s:else>
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<s:iterator value="categories" var="c">
								<li><a class="category_selector"
									id="<s:property value="#c.id"/>"> <s:property
											value="#c.name" /> <s:if test="#c.privacy==0">
											<span class="glyphicon glyphicon-euro"></span>
										</s:if>
								</a></li>
							</s:iterator>
						</ul>
						<%-- 用于提交category的隐藏输入框 --%>
						<input type="hidden" name="category_id" id="category_id"
							value="
								<s:if test="newOrNot==1"><s:property value="blog.category.id"/></s:if>
								<s:else><s:property value="categories[0].id"/></s:else>
							">
						<input type="hidden" name="blog_id" id="blog_id"
							value="<s:property value="blog.id"/>">
					</div>
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Please enter title here."
						value="<s:if test="newOrNot==1"><s:property value="blog.title"/></s:if>">
				</div>
			</div>
			<!-- <div class="col-xs-1"></div> -->
		</div>
		<!-- title end -->


		<%-- editor begin --%>
		<script id="editor" type="text/plain">
			<s:if test="newOrNot==1">
				<s:property value="blog.content" escape="false"/>
			</s:if>
		</script>
		<!-- 隐藏提交框 -->
		<input type="hidden" id="content" name="content">
		<%-- editor end --%>
		<br />
		<%-- btn begin --%>
		<div class="row">
			<div class="col-xs-4"></div>
			<div class="col-xs-2 form-group form-group-lg">
				<button type="submit" class="btn btn-primary form-control"
					id="submit">Submit</button>

			</div>
			<div class="col-xs-2 form-group form-group-lg">
				<button type="button" class="btn btn-primary form-control"
					id="cancel">Cancel</button>
			</div>
			<div class="col-xs-4">
				<button type="button" class="btn btn-primary" id="testBtn">TestBtn</button>
			</div>
		</div>
		<%-- btn end --%>
	</form>

<script>
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor("editor");

	/* 将editor内容提交到隐藏表单 */
	$("#submit").click(function() {
		$("#content").val(ue.getContent());
	});
	$("button#cancel").click(function() {
		window.location.href = "index";
	});
	/* 设置隐藏 */
	$(document).ready(function() {
		/* $("#content").hide(); */
		$("#testBtn").hide();
		/* Category下拉列表click事件 */
		$("a.category_selector").click(function() {
			$("#categoryBtn").html($(this).html());
			$("#category_id").val($(this).attr("id"));
		});
	});
</script>
