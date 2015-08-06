<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发布信息</title>
	<script src="js/plupload/jquery-1.10.2.min.js"></script>
	<script src="js/plupload/plupload.full.min.js"></script>
</head>
<body>
	<form action = "publish" method = "post">
		<!-- 分为卖书和买书两方面 -->
		<span>类型：</span>
		<select name = "type" >
			<option value = "2">卖</option>
			<option value = "1">买</option>
		</select><br>
		<span>书名： </span>
		<input type = "text" name = "bookName" id = "bookName"/><br>
		
		<span>作者： </span>
		<input type = "text" name = "author" id = "author" /><br>
		
		<span>出版社： </span>
		<input type = "text" name = "poster" id = "poster" /><br>
		
		<span>ISBN号： </span>
		<input type = "text" name = "isbn" id = "isbn" /><br>
		
		
		<button id = "pickfiles" >选择图片</button>
		&nbsp;&nbsp;&nbsp;
		<div id = "file-list"  style = "display:inline;"></div>
		<button id = "uploadfiles">上传图片</button>
		<br>
		
		<!-- 该处需要使用js做弹出选择框 -->
		<span>类别： </span>
		<input type = "text" name = "category" id = "category" /><br>
		<span>描述: </span>
		<textarea name = "desc" cols="20" rows="5"></textarea><br>
		<span >价格： </span>
		<input type = "text" name = "price" /><br>
		<span>联系人姓名： </span>
		<input type = "text" name = "linkmanName" /><br>
		<span>联系人电话： </span>
		<input type = "text"  name = "linkmanTel" /><br>
		<input type = "submit" value = "submit" /><br>
	</form>
	
		
	<!-- 当在外部引入图片上传js文件时，必须将这段js代码放在页面元素加载完以后的地方，否则会出现按钮没有被绑定相应事件的错误 -->
	<script src="js/plupload/uploadImage.js"></script>
</body>
</html>