<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员信息更改</title>
<link rel="stylesheet" href="../static/layui/css/layui.css">
<script src="../static/js/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../static/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
<script src="../static/layui/layui.js"></script>
<script src="../static/js/config.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 5px;">
		<legend>管理员信息修改</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" action=""  lay-filter="form1">
		<input type="text" name="aid" hidden="hidden">
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" name="adminName" class="layui-input" lay-verify="adminName">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">登录账号</label>
			<div class="layui-input-block">
				<input type="text" name="adminNum" class="layui-input" disabled="" title="不允许修改">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">登录密码</label>
			<div class="layui-input-block">
				<input type="password" name="adminPwd" class="layui-input" lay-verify="adminPwd" placeholder="不修改则不填">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">email</label>
			<div class="layui-input-block">
				<input type="text" name="email" class="layui-input" lay-verify="email">
			</div>
		</div>
		<div class="layui-form-item" pane>
    		<label class="layui-form-label">性别</label>
    		<div class="layui-input-block">
      			<input type="radio" name="sex" value="男" title="男" checked="">
      			<input type="radio" name="sex" value="女" title="女">
    		</div>
  		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">年龄</label>
			<div class="layui-input-block">
				<input type="text" name="age" class="layui-input" lay-verify="age">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">身份证号</label>
			<div class="layui-input-block">
				<input type="text" name="idcard" class="layui-input" lay-verify="idcard">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<input type="text" name="note" class="layui-input" lay-verify="note" disabled="" title="不允许修改">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">更换头像:</label>
		    <div class="layui-inline">
		      <div class="layui-upload">
				<button type="button" class="layui-btn" id="upImg">选择</button>选择后自动上传<br><br>
		      	<img class="layui-upload-img" id="hImg"><br><br>
				<p id="demoText"></p>
		      </div>
		    </div>
		</div>
		
		<input name="headImg" class="layui-input" type="hidden" id="headImg">
		
		<div class="layui-form-item">
    		<div class="layui-input-block">
      		<button class="layui-btn" lay-submit lay-filter="formDemo">提交修改</button>
    		</div>
 	 	</div>
	</form>
	<script type="text/javascript">
	layui.use(['element','layer','jquery','form','form','upload'], function() {
		var form = layui.form;
		var element = layui.element;
		var layer = layui.layer; 
		var $ = layui.jquery;
		var upload = layui.upload;
		var form = layui.form;
		
		
		var adminId = $.cookie("adminId");
		
		$.ajax({
			url: '../admin/one?aid='+adminId,
			method: 'get',
			success: function(data) {
				if(data.code==0){
					/* 表单赋值 */
					form.val('form1',{
						aid: data.data.aid,
						adminName: data.data.adminName,
						adminNum: data.data.adminNum,
						//adminPwd: data.data.adminPwd,
						email:data.data.email,
						sex: data.data.sex,
						age:data.data.age,
						idcard:data.data.idcard,
						headImg:data.data.headImg,
						note:data.data.note
					});
					$('#hImg').attr('src',data.data.headImg);
				}else{
					layer.alert(data.msg);
				}
			},
			error: function(data){
				layer.alert(data.msg);
			}
		});
		
		/*  表单验证 */
		form.verify({
			adminName: function(value){
				if(value.length<2){return '名字至少两个字';}
			},
			/* adminPwd: function(value){
				if(value.length<6){return '密码至少6个字符';}
			}, */
			age: function(value){
				if(value<26||value>55){return '年龄不符合规定，26-55之间';}
			}
		});
		
		/* 表单提交 */
		form.on('submit(formDemo)',function(data){
			$.ajax({
				url: '../admin/update',
				method: 'post',
				data: data.field,
				success: function(data){  
					if(data.code==0){
						adminLogout();
						layer.msg("修改成功,请重新登录。", {
							icon: 6,
							time: 1200,
							shade: 0.4
						}, function() {
							var layerIndex = parent.layer.getFrameIndex(window.name);//获取当前iframe的索引
							parent.layer.close(layerIndex); //执行当前弹出窗口
							parent.window.location.href="../login.jsp"; //返回登录页
						});
					}else{
						layer.msg("修改失败，请重试"); 
					}
				},
				error: function(){
					layer.msg("服务器内部错误");
				}
			});
			return false; //去掉该行layer不关闭   
		});
		
		/* 上传图片  
		 *  选择文件后会自动上传到后端，然后返回一个新的文件名，赋值表单
		 */
		 var uploadInst = upload.render({
			 elem: "#upImg",
			 url: '../admin/headImg',
			 auto: true, //自动上传
			 before: function(obj){
				obj.preview(function(index, file, result){
					 $("#hImg").attr('src',result);
					 $("#hImg").css('height',120);
					 $("#hImg").css('width',100);
				});
			 },
			 done: function(res){
				 if(res.code==0){
					 layer.msg("图片已上传");
					 console.log(res.data);
					 $("#headImg").val(res.data);
				 }else{
					 return layer.msg('上传失败');
				 }
			 },
			 error: function(){
				 // 服务器异常
				 var demoText = $("#demoText");
				 demoText.html('<span style="color:#FF5722">上传失败</span><a class="layui-btn layui-btn-xs demo-reload">重试</a>');
				 demoText.find('.demo-reload'.on('click',function(){uploadInst.upload();}));
			 }
		 });
		
		
	});
	
	</script>

</body>
</html>