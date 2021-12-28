<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书管理系统</title>
<meta charset=UTF-8>
<link rel="stylesheet" href="static/css/bootstrap.css">
<link href="static/iconfont/style.css" type="text/css" rel="stylesheet">
<link href="static/css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="static/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
</head>
<body>
	<div class="container wrap1" style="height: 450px;">
		<h2 class="mg-b20 text-center">图书管理系统</h2>
		<div
			class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
			<p class="text-center font16">管理员</p>
			<form action="">
				<div class="form-group mg-t20">
					<i class="icon-user icon_font"></i> <input type="text"
						class="login_input" required="required" placeholder="请输入用户名"
						id="adminNum" />
				</div>
				<div class="form-group mg-t20">
					<i class="icon-lock icon_font"></i> <input type="password"
						class="login_input" required="required" placeholder="请输入密码"
						id="adminPwd" />
				</div>
				<button type="button" class="login_btn" id="loginBtn">登 录</button>
			</form>
		</div>
		<!--row end-->
	</div>
	<!--container end-->

	<script>
		$("#loginBtn").click(function() {
			var adminNum = $("#adminNum").val();
			var adminPwd = $("#adminPwd").val();
			if (adminNum.length == 0 || adminPwd.length == 0) {
				alert("账号或密码不能为空");
				return;
			};
			$.ajax({
				url : 'admin/login',
				method : 'post',
				data : {
					adminNum : adminNum,
					adminPwd : adminPwd
				},
				success : function(data) {
					if (data.code == 0) {
						//登录成功，保存用户名，id到cookie，不设置有效时常，默认关闭浏览器即cookie失效
						$.cookie("adminName",data.data.adminName);
						$.cookie("adminId",data.data.aid);
						$.cookie("adminImg",data.data.headImg);
						//跳转页面
						window.location.href = "view/index.jsp";
					} else {
						alert(data.msg); //登录失败显示失败信息
						$("#adminPwd").val(""); //密码重新输入
					}
				},
				error : function(data) {
					alert(data.msg);
				}
			});
		});
	</script>

</body>
</html>
