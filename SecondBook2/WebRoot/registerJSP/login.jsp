<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
<script type = "text/javascript" src = "js/xhr.js" ></script>
<script type = "text/javascript">
function userLogin(){
	/**获取参数*/
	var dept = document.getElementById("dept").value ;
	var name = document.getElementById("name").value ;
	var password = document.getElementById("password").value ;
	/**获取XMLHttpRequest对象*/
	var xhr = createXMLHttpRequest() ;
	if(xhr == null ) {
		alert("无法创建XMLHttpRequest对象！");
		return ;
	}
	/**设置XHR的回调函数*/
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.responseText == "success"){
				alert("登陆成功！");
			}else{
				alert("登陆不成功，状态码为：" + xhr.responseText);
			}
		}
	} ;
	xhr.open("POST","login",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var param = "dept=" + dept + 
				"&name=" + name +
				"&password=" + password;
	xhr.send(param);
}
</script>
</head>
<body>
<table align = "center" width = "100%">	
	<table width = "250" align = "center">
	
		<form action = "login" method = "get">
		<tr>
			<td colspan="2" align = "center">登陆</td>
		</tr>
		<tr>
			<td>
				学院
			</td>
			<td>
				<input type = "text" name = "dept" id = "dept" value = "信息科学与工程学院" />
			</td>
		</tr>
		<tr>
			<td>昵称</td>
			<td>
				<input type = "text" name = "name" id = "name" value = "caiyao" />
			</td>
		</tr>
		<tr>
			<td>密码</td>
			<td>
				<input type = "password" name = "password" id = "password" value = "caiyao" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align = "center">
				<input type = "button" value = "登陆" onclick = "userLogin()"/>
			</td>
		</tr>
	</form>
	</table>
	<form action = "" >
	<input type = "button" value = "注销" onclick = "loginout()" />
	</form>
	<script type = "text/javascript" >
		function loginout(){
			/**获取XMLHttpRequest对象*/
			var xhr = createXMLHttpRequest() ;
			if(xhr == null ) {
				alert("无法创建XMLHttpRequest对象！");
				return ;
			}
			/**设置XHR的回调函数*/
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					alert("Ajax成功，返回值为： " + xhr.responseText);
				}
			} ;
			xhr.open("GET","loginout",true);
			xhr.send();
		}
	</script>
</table>
</body>
<script type = "text/javascript">
	/**监听浏览器关闭事件，当浏览器关闭的时候，向服务器发送下线请求*/
	window.onbeforeunload = function(){
		alert("onbeforeunload!");
		loginout();
	} ;
</script>
</html>