<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<ul class="nav nav-tabs comment-margin-bottom" role="tablist">
	<li role="presentation" class="active"><a><span
			class="text-primary">评论区</span></a></li>
</ul>
<form id="commentPublishForm" class="form-horizontal">
	<div class="form-group" id="nameInput">
		<label class="control-label col-xs-2 ">昵称：</label>
		<div class="col-xs-4">
			<s:if test="#session.user_id > 0">
				<input type="text" name="comment_name" id="comment_name" class="form-control" value="<s:property value="#session.user_name"/>"/>
			</s:if>
			<s:else>
				<input type="text" name="comment_name" id="comment_name" class="form-control" value="<s:property value="comment_name"/>"/>
			</s:else>
		</div>
		<div class="col-xs-2"></div>
		<div class="col-xs-4">
			<button type="button" class="btn btn-primary" id="commentPublishBtn">发表评论</button>
		</div>
	</div>
	
	<div class="form-group" id="contentInput">
		<label class="col-xs-2 control-label">内容：</label>
		<textarea class="form-control" rows="5" name="comment_content" id="comment_content"></textarea>
	</div>
	<input type="hidden" name="blog_id" value="<s:property value="blog.id"/>">
</form>


<script>
$("button#commentPublishBtn").click(
		function() {
			if($("input#comment_name").val()==""){
				if(!$("div#nameInput").hasClass("has-error")){
					$("div#nameInput").addClass("has-error");
				}
			}else if($("textarea#comment_content").val()==""){
				if(!$("div#contentInput").hasClass("has-error")){
					$("div#contentInput").addClass("has-error");
				}
			}else{
				$("div#nameInput").removeClass("has-error");
				$("div#contentInput").removeClass("has-error");
				$(this).attr("disabled", "disabled");
				$.post("comment", $("form#commentPublishForm")
						.serialize(), function(data) {
					$("div.comment-area").html(data);
					$("button#commentPublishBtn").removeAttr("disabled");
					$("textarea#comment_content").val("");
				})
			};
		});
</script>