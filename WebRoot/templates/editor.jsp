<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link
	href="resources/wysiwyg/external/google-code-prettify/prettify.css"
	rel="stylesheet">
<!-- <link
	href="resources/wysiwyg/bootstrap/bootstrap-combined.no-icons.min.css"
	rel="stylesheet"> -->
<link href="resources/wysiwyg/bootstrap/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<script src="resources/wysiwyg/external/jquery.hotkeys.js"></script>
<script
	src="resources/wysiwyg/external/google-code-prettify/prettify.js"></script>
<script src="resources/wysiwyg/bootstrap-wysiwyg.js"></script>


<link href="resources/wysiwyg/editor.css" rel="stylesheet">

<div class="blog-post">
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
							<s:if test="newOrNot==0">Category</s:if>
							<s:else>
								<s:property value="blog.category.name" />
								<s:if test="blog.category.privacy==0"><span class="glyphicon glyphicon-euro"></span></s:if>
							</s:else>
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<s:iterator value="categories" var="c">
								<li><a class="category_selector"
									id="<s:property value="#c.id"/>"> <s:property
											value="#c.name" />
									<s:if test="#c.privacy==0"><span class="glyphicon glyphicon-euro"></span></s:if>
								</a></li>
							</s:iterator>
						</ul>
						<%-- 用于提交category的隐藏输入框 --%>
						<input type="hidden" name="category_id" id="category_id"
							value="
								<s:if test="newOrNot==1"><s:property value="blog.category.id"/></s:if>
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

		<div id="alerts"></div>

		<div class="row btn-toolbar" data-role="editor-toolbar"
			data-target="#editor">
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i
					class="icon-font"></i><b class="caret"></b></a>
				<ul class="dropdown-menu">
				</ul>
			</div>
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown"
					title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a data-edit="fontSize 6"><font size="6">Huge</font></a></li>
					<li><a data-edit="fontSize 5"><font size="5">Large</font></a></li>
					<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
					<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
				</ul>
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i
					class="icon-bold"></i></a> <a class="btn" data-edit="italic"
					title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a> <a
					class="btn" data-edit="strikethrough" title="Strikethrough"><i
					class="icon-strikethrough"></i></a> <a class="btn"
					data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i
					class="icon-underline"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i
					class="icon-list-ul"></i></a> <a class="btn"
					data-edit="insertorderedlist" title="Number list"><i
					class="icon-list-ol"></i></a> <a class="btn" data-edit="outdent"
					title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
				<a class="btn" data-edit="indent" title="Indent (Tab)"><i
					class="icon-indent-right"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="justifyleft"
					title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
				<a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i
					class="icon-align-center"></i></a> <a class="btn"
					data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i
					class="icon-align-right"></i></a> <a class="btn"
					data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
					class="icon-align-justify"></i></a>
			</div>
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown"
					title="Hyperlink"><i class="icon-link"></i></a>
				<div class="dropdown-menu input-append">
					<input class="span2" placeholder="URL" type="text"
						data-edit="createLink" />
					<button class="btn" type="button">Add</button>
				</div>
				<a class="btn" data-edit="unlink" title="Remove Hyperlink"><i
					class="icon-cut"></i></a>

			</div>

			<div class="btn-group">
				<a class="btn" title="Insert picture (or just drag & drop)"
					id="pictureBtn"><i class="icon-picture"></i></a> <input type="file"
					data-role="magic-overlay" data-target="#pictureBtn"
					data-edit="insertImage" />
			</div>
			<div class="btn-group">
				<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
					class="icon-undo"></i></a> <a class="btn" data-edit="redo"
					title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
			</div>
			<input type="text" data-edit="inserttext" id="voiceBtn"
				x-webkit-speech="">
		</div>
		<%-- editor begin --%>
		<div class="row">
			<input type="hidden" id="content" name="content">
			<div id="editor"><s:if test="newOrNot==1"><s:property value="blog.content" escape="false"/></s:if></div>
		</div>
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
</div>

<script>
	$(function() {
		function initToolbarBootstrapBindings() {
			var fonts = [ '微软雅黑', 'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
					'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
					'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
					'Times New Roman', 'Verdana' ], fontTarget = $(
					'[title=Font]').siblings('.dropdown-menu');
			$
					.each(
							fonts,
							function(idx, fontName) {
								fontTarget
										.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'
												+ fontName + '</a></li>'));
							});
			$('a[title]').tooltip({
				container : 'body'
			});
			$('.dropdown-menu input').click(function() {
				return false;
			}).change(
					function() {
						$(this).parent('.dropdown-menu').siblings(
								'.dropdown-toggle').dropdown('toggle');
					}).keydown('esc', function() {
				this.value = '';
				$(this).change();
			});

			$('[data-role=magic-overlay]').each(
					function() {
						var overlay = $(this), target = $(overlay
								.data('target'));
						overlay.css('opacity', 0).css('position', 'absolute')
								.offset(target.offset()).width(
										target.outerWidth()).height(
										target.outerHeight());
					});
			if ("onwebkitspeechchange" in document.createElement("input")) {
				var editorOffset = $('#editor').offset();
				$('#voiceBtn').css('position', 'absolute').offset({
					top : editorOffset.top,
					left : editorOffset.left + $('#editor').innerWidth() - 35
				});
			} else {
				$('#voiceBtn').hide();
			}
		}
		;
		function showErrorAlert(reason, detail) {
			var msg = '';
			if (reason === 'unsupported-file-type') {
				msg = "Unsupported format " + detail;
			} else {
				console.log("error uploading file", reason, detail);
			}
			$(
					'<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
							+ '<strong>File upload error</strong> '
							+ msg
							+ ' </div>').prependTo('#alerts');
		}
		;
		initToolbarBootstrapBindings();
		$('#editor').wysiwyg({
			fileUploadError : showErrorAlert
		});
		window.prettyPrint && prettyPrint();
	});
	/* 将editor内容提交到隐藏表单 */
	$("#submit").click(function() {
		$("#content").val($("#editor").html());
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
