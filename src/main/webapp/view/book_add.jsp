<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加图书信息</title>
<link rel="stylesheet" href="../static/layui/css/layui.css">
<script src="../static/layui/layui.js"></script>
<script src="../static/js/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../static/js/config.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<form class="layui-form layui-form-pane" action="" lay-filter="form1">
		<div class="layui-form-item">
			<label class="layui-form-label">图书书名</label>
			<div class="layui-input-block">
				<input type="text" name="bookName" class="layui-input" lay-verify="bookName">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">作者</label>
			<div class="layui-input-block">
				<input type="text" name="author" class="layui-input" lay-verify="author">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出版社</label>
			<div class="layui-input-block">
				<input type="text" name="publishHouse" class="layui-input" lay-verify="publishHouse">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出版时间</label>
			<div class="layui-input-block">
		    	<input type="text" name="publishDate1" id="date1" autocomplete="off" class="layui-input" placeholder="yyyy-MM-dd">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">ISBN</label>
			<div class="layui-input-block">
				<input type="text" name="isbn" class="layui-input" lay-verify="isbn">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">价格(元)</label>
			<div class="layui-input-block">
				<input type="text" name="price" class="layui-input" lay-verify="price">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
    		<label class="layui-form-label">图书简介</label>
   			<div class="layui-input-block">
      			<textarea placeholder="请输入内容" class="layui-textarea" name="briefIntr"></textarea>
    		</div>
  		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">图书类型</label>
			<div class="layui-input-block">
			  <select name="typeId" lay-verify="required" id="choseType" lay-filter="choseType">
			    <option value="">图书类型选择</option>
			  </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">上传封面:</label>
		    <div class="layui-inline">
		      <div class="layui-upload">
				<button type="button" class="layui-btn" id="upImg">选择</button>选择后自动上传<br><br>
		      	<img class="layui-upload-img" id="bookImg"><br><br>
				<p id="demoText"></p>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">图片地址:</label>
			<div class="layui-input-block">
				<input name="imgName" class="layui-input" type="text" placeholder="选择上方图片后地址自动显示" id="imgName" disabled title="不可更改">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="addForm">提交数据</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	layui.use([ 'table','element','layedit', 'laydate','form','upload'],function() {
		var table = layui.table; 
		var layer = layui.layer; 
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var form = layui.form;
		var element = layui.element;
		var upload = layui.upload;
		//日期
		laydate.render({
			elem : '#date1'
			,trigger: 'click' //防止日期组件闪退
		});
		
		/*  表单验证 */
		form.verify({
			bookName: function(value){
				if(value.length<1){return '图书书名至少1个字';}
			},
			author: function(value){
				if(value.length<2){return '作者姓名至少2个字符';}
			},
			isbn: function(value){
				if(value.length<0){return 'ISBN不能为空';}
			}
		});
		
		
		/* 表单提交 */
		form.on('submit(addForm)',function(data){
			$.ajax({
				url: '../book/add',
				method: 'post',
				data: data.field,
				success: function(data){  
					if(data.code==0){
						layer.msg("添加成功", {
							icon: 6,
							time: 1200,
							shade: 0.4
						}, function() {
							var layerIndex = parent.layer.getFrameIndex(window.name);//获取当前iframe的索引
							parent.layer.close(layerIndex); //执行当前弹出窗口
							parent.layui.table.reload('bookinfo');//重新加载父页面的表格数据
						});
					}else if(data.code == 1){
						layer.msg(data.msg); 
						parent.window.location.href="../login.jsp"; //session失效返回登录页
					}else{
						layer.msg(data.msg);
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
			 url: '../book/imageName',
			 auto: true, //自动上传
			 before: function(obj){
				obj.preview(function(index, file, result){
					 $("#bookImg").attr('src',result);
					 $("#bookImg").css('height',120);
					 $("#bookImg").css('width',100);
				});
			 },
			 done: function(res){
				 if(res.code==0){
					 layer.msg("图片已上传");
					 console.log(res.data);
					 $("#imgName").val(res.data);
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
			//加载图书类型
			$(document).ready(function() {
				$.ajax({
					url: '../type/list',
					success: function(data) {
						if (data.code == 0) {
							$('#choseType').empty();
							var btype = data.data;
							//$('#choseType').append(new Option("图书类型选择","0"));
							$.each(btype, function(index, item) {
								$('#choseType').append(new Option(item.tname,item.id));
							});
							form.render();
						} else {
							layer.alert("请求失败，未找到数据");
						}
					},
					error: function(data) {
						layer.alert("类型加载失败！");
					}
				})
			});
			
			
			
	});
	</script>

</body>
</html>