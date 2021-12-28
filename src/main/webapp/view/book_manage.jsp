<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<div class="demoTable">
	<form class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<input class="layui-input" name="bookName" id="qBookName" autocomplete="off" placeholder="图书名">
			</div>
			<div class="layui-inline">
				<input class="layui-input" name="author" id="qAuthor" autocomplete="off" placeholder="作者">
			</div>
			<div class="layui-inline">
				<input class="layui-input" name="isbn" id="qIsbn" autocomplete="off" placeholder="isbn">
			</div>
		
		    <div class="layui-inline">
		    	<label class="layui-form-label">出版时间</label>
		    	<div class="layui-input-inline" style="width: 100px;">
		    		<input type="text" name="date1" id="date1" autocomplete="off" class="layui-input" placeholder="yyyy-MM-dd">
		    	</div>
		    	<div class="layui-form-mid">-</div>
		    	<div class="layui-input-inline" style="width: 100px;">
		    		<input type="text" name="date2" id="date2" autocomplete="off" class="layui-input" placeholder="yyyy-MM-dd">
		    	</div>
		    </div>
			<div class="layui-inline">
				<button class="layui-btn" type="button" id="searchBtn">查询</button>
				<button class="layui-btn layui-btn-normal" type="reset">重置</button>
			</div>
		</div>
	</form>
	</div>

	<table id="bookinfo" lay-filter="bookinfo"></table>

	<script type="text/html" id="toolBar">
		<div class="layui-btn-container">
			<button class="layui-btn layui-btn-sm" lay-event="addNewBook">添加新图书</button>
			<button class="layui-btn layui-btn-sm" style="background-color:#5FB878" lay-event="showDownBook">查看已下架图书</button>
			<button class="layui-btn layui-btn-sm" lay-event="reloadTable" style="background-color:#FF5722;color:black;">刷新表格</button>
		</div>
	</script>

	<script type="text/html" id="barDemo">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="down">下架</a>
	</script>
	<script type="text/html" id="upBtn">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="upAgain">重新上架</a>
	</script>
	
</div>
<script type="text/html" id="imgtmp">
    <img class="bookImg" src="{{d.imgName}}">
</script>
<style type="text/css">
.bookImg{
		width: 140px;
		height: 180px;
	}
.layui-table-tips-main {
	margin: 0;
	/* max-height: 150px; */
	padding: 0;
	overflow: visible;
	background-color: #fff;
	color: #666;
}

tr {
	height: 50px;
}
</style>
<script type="text/javascript">
	layui.use([ 'table','element','layedit', 'laydate','form'],function() {
		var table = layui.table; 
		var layer = layui.layer; 
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var form = layui.form;
		var element = layui.element;
		//日期
		laydate.render({
			elem : '#date1'
		});
		laydate.render({
			elem : '#date2'
		});				
		
		
		//数据表格数据渲染
		var tableIns = table.render({
					elem : '#bookinfo',
					toolbar : '#toolBar',
					height : 680,
					url : '../book/query', //数据接口
					page : true, //开启分页
					cols : [ [ //表头
							{field : 'bid',title : '图书ID',width : 90,sort : true},
							{field : 'bookName',title : '图书名',width : 140},
							{field : 'author',title : '作者',width : 100},
							{field : 'tName',title : '类型',width : 60},
							{field : 'publishHouse',title : '出版社',width : 130},
							{field : 'publishDate',title : '出版时间',width : 110},
							{field : 'isbn',title : 'ISBN',width : 140},
							{field : 'price',title : '价格(元)',width : 90},
							{field : 'briefIntr',title : '内容简介'},
							{field : 'imgName',title : '图片',templet: '#imgtmp',width : 110}, 
							{field : 'onDate',title : '上架时间',width : 110}, 
							{field : 'adminName',title : '上架管理员',width : 110}, 
							{fixed : 'right',width : 138,align : 'center',toolbar : '#barDemo'} 
					] ]
			});
			
		/* 查询按钮 */
		$('#searchBtn').click(function(){
			layer.msg('请稍后...',{
				icon:16,
				time:800,
				shade:0,
				},function(){
				tableIns.reload({
					where:{
						bookName: $('#qBookName').val(),
						author: $('#qAuthor').val(),
						isbn: $('#qIsbn').val(),
						date1: $('#date1').val(),
						date2: $('#date2').val(),
					},page:{
						page: 1
					}
				})
			})
		});
		
		
		//监听表格工具头
		table.on('toolbar(bookinfo)', function(obj){
		  var checkStatus = table.checkStatus(obj.config.id);
		  switch(obj.event){
		    case 'addNewBook': addBook(); break;
		    case 'showDownBook': showDownBook();break;
		    case 'reloadTable': tableIns.reload();break;
		  };
		});
		
		function addBook(){
			layer.open({
				  title: '增加新图书',
				  type: 2,
				  area: ['700px', '550px'],
				  fixed: false, //不固定
				  maxmin: true,
				  content: 'book_add.jsp'
			});
		};
		
		
		
		function showDownBook(){
			layer.open({
				  type: 1,
				  title: '下架图书列表',
				  area: ['830px', '360px'],
				  closeBtn: 0,
				  shadeClose: true,
				  skin: 'yourclass',
				  content: '<table id="downBookList" lay-filter="downBookList"></table>'
			}),
			table.render({
				elem : '#downBookList',
				url : '../book/downList', //数据接口
				cols : [ [ //表头
						{field : 'bid',title : 'ID',width : 120,sort : true},
						{field : 'bookName',title : '图书名'},
						{field : 'author',title : '作者'},
						{field : 'onDate',title : '上架时间',width : 110}, 
						{field : 'adminName',title : '上架管理员',width : 110}, 
						{fixed : 'right',width : 138,align : 'center',toolbar : '#upBtn'} 
				] ]
			});
		};
		
		//监听表格按钮事件
		table.on('tool(bookinfo)',function(obj){
			var data = obj.data; //获取表格行数据
			if(obj.event == 'edit'){
				editBook(data);
			}else if(obj.event == 'down'){
				obj.del();
				downBook(data);
			}
		});
		function editBook(data){
			layer.open({
				type: 2,
				title: '修改图书信息',
				area: ['800px','550px'],
				fixed: false,
				maxmin: true,
				content: 'book_update.jsp',
				success: function(layero,index){
					var body = layer.getChildFrame('body',index);
					body.find('#bid').val(data.bid);//传递图书id个到子页面
				}
			})
		};
		function downBook(data){
			$.ajax({
				url: '../book/down?bid='+data.bid,
				success: function(data1) {
					if (data1.code == 0) {
						layer.msg("图书: <b style='color:yellow'>"+data.bookName+"</b> 下架完成");
					} else {
						layer.alert("请求失败，请稍后重试");
					}
				},
				error: function(data1) {
					layer.alert("服务器内部错误");
				}
			})
		}
		
		
		//监听下架图书表格  按钮事件
		table.on('tool(downBookList)',function(obj){
			var data = obj.data; //获取表格行数据
			if(obj.event == 'edit'){
				editBook(data);
			}else if(obj.event == 'upAgain'){
				obj.del(); 
				upAgain(data);
			}
		});
		function upAgain(data){
			$.ajax({
				url: '../book/up?bid='+data.bid,
				success: function(data1) {
					if (data1.code == 0) {
						layer.msg("图书: <b style='color:yellow'>"+data.bookName+"</b> 重新上架");
					} else {
						layer.alert("请求失败，请稍后重试");
					}
				},
				error: function(data1) {
					layer.alert("服务器内部错误");
				}
			})
		}
		
	});
</script>


