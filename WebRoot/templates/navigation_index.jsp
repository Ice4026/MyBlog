<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- <div class="blog-masthead">
	<div class="container">
		<nav class="blog-nav">
			<div class="col-xs-9">
				<a class="blog-nav-item active" href="#">Ice's Blog</a> <a
					class="blog-nav-item" href="#">专题</a> <a class="blog-nav-item"
					href="#">标签</a> <a class="blog-nav-item" href="#">关于</a>
			</div>
			<div class="col-xs-3">
				<s:if test="#session.user_id > 0"><a class="blog-nav-item">
				<s:property value="#session.user_name"/></a></s:if>
				<s:else><a class="blog-nav-item" href="" id="showLogin">登录</a></s:else>
			</div>
		</nav>
	</div>
</div> -->

<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="col-xs-2"></div>
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="col-xs-8">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Ice's Blog</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">主页</a></li>
					<li><a href="#">专题</a></li>
					<li><a href="#">标签</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<s:if test="#session.user_id > 0">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><s:property value="#session.user_name" /><span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="admin/writer_new">新增日志</a></li>
								<li class="divider"></li>
								<li><a href="validate/logoutValidate">退出登录</a></li>
							</ul></li>
					</s:if>
					<s:else>
						<li><a href="" id="showLogin">登录</a></li>
					</s:else>
				</ul>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

<div class="modal fade" id="loginModal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">请登录</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="loginForm">
					<div class="form-group">
						<label>用户名</label> <input type="text" class="form-control"
							id="username" name="name" placeholder="Enter username">
					</div>
					<div class="form-group">
						<label>密码</label> <input type="password" class="form-control"
							id="password" name="password" placeholder="Enter password">
					</div>
					<div class="checkbox">
						<label><input type="checkbox" id="rememberPw"
							name="rememberPw" value="1" />记住密码</label>
					</div>
					<p class="text-danger" id="loginErrorText">用户名或密码错误！</p>

					<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-1">
							<button type="button" class="btn btn-primary" id="loginBtn">登录</button>
						</div>
						<div class="col-xs-2"></div>
						<div class="col-xs-1">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
						<div class="col-xs-7"></div>
					</div>
				</form>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
	$("p#loginErrorText").hide();
	$("a#showLogin").click(function() {
		$("#loginModal").modal();
		return false;
	});
	$("button#loginBtn").click(
			function() {
				$("p#loginErrorText").hide();
				$(this).attr("disabled", "disabled");
				/* 验证 */
				$.post("validate/loginValidate", $("form#loginForm")
						.serialize(), function(data) {
					if (data.login == "fail") {
						$("p#loginErrorText").show();
						$("button#loginBtn").removeAttr("disabled");
					} else {
						window.location.reload();
					}
				});
			});
</script>