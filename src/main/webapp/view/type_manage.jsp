<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table id="bookType" lay-filter="bookType"></table>

	<script type="text/html" id="toolBar">
		<div class="layui-btn-container">
			<button class="layui-btn layui-btn-sm" lay-event="addNewType">添加图书类型</button>
		</div>
	</script>

	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-bg-red" lay-event="delete">删除</a>
	</script>

</div>
<script type="text/javascript">
layui.use([ 'form', 'layedit', 'laydate', 'upload', 'table' ], function() {
		var form = layui.form;
		var layer = layui.layer;
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var upload = layui.upload;
		var $ = layui.jquery;
		var table = layui.table;
		
		//数据表格数据渲染
		var tableIns = table.render({
			elem: '#bookType',
			toolbar: '#toolBar',
			//height: 550,
			url: '../type/list', //数据接口
			cols: [
				[ //表头
					{field: 'id',title: 'ID',sort: true}, 
					{field: 'tname',title: '类型名称'},
					{fixed: 'right', width:138, align:'center', toolbar: '#barDemo'}
				]
			],

		});
		
		//监听表格工具头
		table.on('toolbar(bookType)', function(obj){
		  var checkStatus = table.checkStatus(obj.config.id);
		  switch(obj.event){
		    case 'addNewType': add(); break;
		  };
		});
		
		function add(){
			layer.prompt({title: '填写新图书类型'},function(val, index){
			 	 addType(val,index);
			  	 layer.close(index);
			});
		}
		
		function addType(val){
			console.log("获取："+val);
			$.ajax({
				url: '../type/add?tname='+val,
				success: function(data){  
					if(data.code==0){
						layer.msg("添加成功", {
							icon: 6,
							time: 800,
							shade: 0.4
						}, function() {
							tableIns.reload();//重载表格
						});
					}else{
						layer.msg(data.msg);
					}
				},
				error: function(){
					layer.msg(data.msg);
				}
			});
		};
		
		 //监听表格内操作
		 table.on('tool(bookType)', function(obj){
				var data = obj.data;
				if(obj.event === 'addNewType'){
					layer.msg("cess");
				}
			    if(obj.event === 'edit'){
			    	layer.prompt({title: '修改类型: <b>'+data.tname+'</b>'},function(val, index){
			    		updateType(data.id,val)
					  	 layer.close(index);
					});
			    }
				if(obj.event === 'delete'){
					layer.confirm('确认删除图书类型？'+data.tname, {
					  btn: ['确定','取消'] //按钮
					}, function(){
					  layer.msg('删除成功', {icon: 1, time: 1000},function(){
						  deleteType(data.id)
					  });
					}, function(index){
					  layer.close(index);
					});
				}
		 });
		 function deleteType(id){
			 $.ajax({
			 	url: '../type/delete?id='+id,
			 	success: function(data){  
			 		if(data.code==0){
			 			tableIns.reload();//重载表格
			 		}else{
			 			layer.msg("修改失败，请重试");
			 		}
			 	},
			 	error: function(){
			 		layer.msg("请求失败，请稍后重试");
			 	}
			 });
		 };
		 
		 //修改
		 function updateType(id,val){
				$.ajax({
					url: '../type/update?id='+id+'&tname='+val,
					success: function(data){  
						if(data.code==0){
							layer.msg("修改成功", {
								icon: 6,
								time: 800,
								shade: 0.4
							}, function() {
								tableIns.reload();//重载表格
							});
						}else{
							layer.msg("修改失败，请重试");
						}
					},
					error: function(){
						layer.msg("请求失败，请稍后重试");
					}
				});
		};
	});
	
</script>