<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统</title>
<link href="../static/layui/css/layui.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../static/layui/layui.js"></script>
<script type="text/javascript" src="../static/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="../static/js/config.js"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo"><a id="index" href="javascript:;" style="color:yellow;">图书管理系统</a></div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a  href="javascript:;"> <img
						id="adminImg" class="layui-nav-img"> <span id="adminName">张三</span>
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a id="admin-info" href="javascript:;">基本资料</a>
						</dd>
						<dd>
							<a id="admin-logout" href="javascript:;">安全退出</a>
						</dd>
					</dl></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item"><a  id="book_manage" href="javascript:;">图书管理</a></li>
					<li class="layui-nav-item"><a id="type_manage" href="javascript:;">类型管理</a></li>
					<li class="layui-nav-item"><a id="test_html" href="javascript:;">测试导航</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 5px;" id="main_content">内容主体区域</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>
	<script>
		//JavaScript代码区域
		layui.use(['element','layer'], function() {
			var element = layui.element;
			var layer = layui.layer; 
			
			//丛cookie中获取管理员信息
			var adminName = $.cookie("adminName");
			var adminId = $.cookie("adminId");
			var adminImg = $.cookie("adminImg");
			$("#adminName").text(adminName);
			$("#adminImg").attr('src',adminImg);
			$("#adminImg").css('height',30);
			$("#adminImg").css('width',30);
			
			$("#admin-info").click(function(){
				layer.open({
					  type: 2,
					  area: ['700px', '450px'],
					  fixed: false, //不固定
					  maxmin: true,
					  content: 'admin_info.jsp'
				});
			});
			$("#admin-logout").click(function(){
				layer.msg("退出成功",{
					icon: 6,
					time: 1200,
					shade: 0.4,
				},function(){
					adminLogout();
					window.location.href="../login.jsp"; //返回登录页
				});
				
			});
			
		});
	</script>
</body>
</html>