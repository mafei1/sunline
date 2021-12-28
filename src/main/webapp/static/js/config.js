/*总配置文件*/


/* 声明全局注销方法 */
function adminLogout(){
	$.ajax({
		url: "../admin/logout",
		success: function(data){
			if(data.code==0){
				//移除cookie
				$.cookie("adminName",null,{path:"/BookMSv4",expires:-1});
				$.cookie("adminId",null,{path:"/BookMSv4",expires:-1});
				$.cookie("adminImg",null,{path:"/BookMSv4",expires:-1});
				//$.removeCookie("adminName"); 
				//$.removeCookie("adminId"); 
				//$.removeCookie("adminImg"); 
				console.log("cookie移除成功");
			}else{
				alert("退出失败，请重试");
			}
		},
		error: function(data){
			alert("服务器内部错误");
		}
	});
}

/* 页面加载 */
$(document).ready(function() {
	$("#index").click(function() {
		window.location.hash="index-page"; //改变地址栏 hash地址，页面不刷新
		window.location.reload();  //刷新页面回到首页
	});
	$("#book_manage").click(function() {
		$("#main_content").load("book_manage.jsp");  //加载页面
		window.location.hash='/#/book-manage'; //改变地址 index.html#book-manage
	});
	$("#type_manage").click(function() {
		$("#main_content").load("type_manage.jsp");  //加载页面
		window.location.hash='/#/type_manage'; //改变地址 index.html#book-manage
	});
	$("#test_html").click(function() {
		$("#main_content").load("test_html.jsp");  //加载页面
		window.location.hash='/#/test_html'; //改变地址 index.html#book-manage
	});
	
});
