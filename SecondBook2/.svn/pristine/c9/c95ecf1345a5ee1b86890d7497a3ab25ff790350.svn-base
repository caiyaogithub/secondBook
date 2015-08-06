<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>register</title>
</head>
<script type = "text/javascript" language = "javascript">
	function changeCaptcha() {
	var img = document.getElementById("imgCaptcha");
	img.src = "captcha";
}
	function formSubmit() {
	alert("执行submit方法!");
	var dept = document.getElementById("dept").value;
	var name = document.getElementById("name").value;
	var password = document.getElementById("password").value;
	var repassword = document.getElementById("repassword").value;
	var email = document.getElementById("email").value;
	var captcha = document.getElementById("captcha").value;
	var xmlhttp = createXMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			switch(responsedata){
				case "success" : {
					/** 表示成功注册并登陆，这个success是登陆action返回的  **/
					document.getElementsByTagName("body")[0].innerHTML = "成功注册并已经登陆！" ;
					break ;
				} 
				case "error" : {
					alert("出现不可解决的错误！");
					changeCaptcha() ;
					break  ;
				}
				case "exist" : {
					alert("该用户名已经被注册！") ;
					changeCaptcha() ;
					break ;
				}
				default : {
					alert( "表单信息有误！ " + responsedata);
					changeCaptcha() ;
				}
			}
		}
	};
	xmlhttp.open("POST","register",true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var param = "dept=" + dept + 
				"&name=" + name + 
				"&password=" + password +
				"&repassword=" + repassword +
				"&email=" + email +
				"&captcha=" + captcha;
	xmlhttp.send(param);
}
function createXMLHttpRequest() {
	var xmlhttp ;
	if (window.XMLHttpRequest)
    	{// code for IE7+, Firefox, Chrome, Opera, Safari
  		xmlhttp=new XMLHttpRequest();
  		return xmlhttp ;
   	 }
	else
 	 {// code for IE6, IE5
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  		return xmlhttp ;
  	 }
  	 
}
</script>
<body>

<form action = "Register.action" method = "POST">
	<table align = "center" width = "500">
	<tr>
	<td colspan="2" align = "center">请填写注册信息：</td>
	</tr>
	
	<tr>
		<td>学院：</td>
		<td>
		<input type = "text" name = "dept" id = "dept" value = "信息科学与工程学院"/>
		</td>
	</tr>
	<tr>
		<td>姓名：</td>
		<td>
		<input type = "text" name = "name" id = "name" value = "caiyao"/>
		</td>
	</tr>
	<tr>
		<td>密码：</td>
		<td>
		<input type = "text" name = "password" id = "password" value = "123456"/>
		</td>
	</tr>
	<tr>
		<td>再输入一遍密码：</td>
		<td>
		<input type = "text" name = "repassword" id = "repassword" value = "123456"/>
		</td>
	</tr>
	<tr>
		<td>邮箱：</td>
		<td>
		<input type = "text" name = "email" id = "email" value = "1258184568@qq.com"/>
		</td> 
	</tr>
	<tr>
		<td>验证码</td>
		<td>
			<input type = "text" name = "captcha" id = "captcha"/>
		</td>
		<td><img src = "captcha" id = "imgCaptcha" onclick = "changeCaptcha()" alt = "改变验证码" style="cursor:hand"/></td>
	</tr>
	<tr >
		<td colspan="2" align = "center"><input type = "button" value = "提交" id = "submit" onclick = "formSubmit()"/></td>
	</tr>
	</table>
</form>
</body>
</html>