<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>海南移动换机服务系统登录</title>

<style type="text/css">
<!--
body {
	font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
	margin: 0;
	padding: 0;
	color: #000;
}

ul,ol,dl {
	padding: 0;
	margin: 0;
}

h1,h2,h3,h4,h5,h6,p {
	margin-top: 0;
	padding-right: 15px;
	padding-left: 15px;
}

a img {
	border: 0;
}

}
a:hover,a:active,a:focus {
	text-decoration: none;
}

.container {
	width: 926px;
	height: 576px;
	background-image: url(images/login_bg.jpg);
	background-repeat: no-repeat;
	margin: 18% auto auto auto;
	position: relative;
}

.content {
	position: absolute;
	left: 420px;
	top: 170px;
	color: #7e7e7e;
	font-size: 16px;
}

li {
	list-style-type: none;
	height: 30px;
	margin-bottom: 16px;
	padding: 0;
}

.i1 {
	margin-bottom: 16px;
	height: 30px;
	padding: 0
}

.i2 {
	margin-bottom: 16px;
	height: 30px;
	padding: 0
}

.i3 {
	margin-bottom: 16px;
	height: 30px;
	padding: 0
}

.i4 {
	margin-bottom: 16px;
	height: 30px;
	padding: 0
}

.i1 input {
	border: 0;
	background-color: transparent;
	font: 12px "arial";
	background-image: url(images/text_1.jpg);
	background-repeat: no-repeat;
	height: 30px;
	width: 167px;
	padding-left: 6px;
	padding-right: 6px;
	line-height: 28px;
}

.i2 input {
	border: 0;
	background-color: transparent;
	font: 12px "arial";
	background-image: url(images/text_2.jpg);
	background-repeat: no-repeat;
	height: 30px;
	width: 167px;
	padding-left: 6px;
	padding-right: 6px;
	line-height: 28px;
}

.i3 input {
	border: 0;
	background-color: transparent;
	font: 12px "arial";
	background-image: url(images/text_3.jpg);
	background-repeat: no-repeat;
	height: 30px;
	width: 167px;
	padding-left: 6px;
	padding-right: 6px;
	line-height: 28px;
}

.i4 input {
	border: 0;
	background-color: transparent;
	font: 12px "arial";
	background-image: url(images/text_4.jpg);
	background-repeat: no-repeat;
	height: 30px;
	width: 94px;
	padding-left: 6px;
	padding-right: 6px;
	line-height: 28px;
}
</style>
<script language="javascript" src="js/prototype.js"></script>
<script language="javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/jquery-1.4.1.min.js"></script>

<script type="text/javascript">
	function btnDown() {
		document.getElementById("sub").style.backgroundImage = "url(images/logind.jpg)";
	}
	function btnOut() {
		document.getElementById("sub").style.backgroundImage = "url(images/loginup.jpg)";
	}

	function fun() {

		var but = document.getElementById('but');
		but.style.backgroundImage = 'url(images/emaild.jpg)';
	}

	function fun1() {
		var but = document.getElementById('but');
		but.style.backgroundImage = 'url(images/emailup.jpg)';
	}

	if (top.location != self.location) {
		top.location = self.location;
	}

	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义   
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码   
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
		obj.src = "rand.do?d=" + timenow;
	}

	//密码以hidden方式传入
	function hiddenPass(evt) {
		var pass = document.getElementById("password");
		var j_pass = document.getElementById("loginPassword");

		var keycode = window.event ? evt.keyCode : evt.which;

		if (keycode == 13) {//回车
			checkform();
		}
		var keychar = String.fromCharCode(keycode);
		j_pass.value = j_pass.value + keychar;
		j_pass.value = j_pass.value.substring(0, pass.length);
		//document.getElementById("loginPassword").value = document.getElementById("password").value;
	}

	//点击密码框时，清楚密码
	function clearPass() {
		//$("#password").val("");
		//$("#loginPassword").val("");
		document.getElementById("password").value = "";
		document.getElementById("loginPassword").value = "";
	}

	//向邮箱发送验证码
	function sendVerifycode() {
		//取得输入邮箱地址
		var mailAdress = document.getElementById("mailAdress").value;
		//获取登录名
		var userName = document.getElementById("loginName").value;

		//验证邮箱地址
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.-]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\-.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		//var myreg = /^([a-zA-Z0-9]+[_|\_-|\.]?)*[a-zA-Z0-9]+@139.com$/;
		if (userName == null || userName == '') {
			if (mailAdress == null || mailAdress == '') {
				alert("请输入邮箱地址！");
				return;
			} else if (!myreg.test(mailAdress)) {
				alert("请输入有效的邮箱地址！");
				return;
			}
		}
		$.ajax({
			url : 'sendVerifycode.do?to=' + mailAdress
					+ '&userModel.loginName=' + userName,
			type : 'POST',
			async : false,//同步执行
			success : function(data) {
				alert("发送成功！请到邮箱中查看验证码！如果长时间未收到验证码请重新发送！");
				return;
			},
			error : function() {
				alert("发送失败！请重新发送！");
				return;
			}
		});
	}
</script>

</head>
<body>
	<%
		//更新会话标识
		//System.out.println(request.getSession().getId());
		request.getSession().invalidate();//清空session
		if (request.getCookies() != null) {
			Cookie cookie = request.getCookies()[0];// 获取cookie
			cookie.setMaxAge(0);// 让cookie过期
		}
	%>
	<div class="container">
		<form method="post" id="loginform" name="loginform" action="check.do">
			<input id="loginPassword" type="hidden" name="userModel.password"
				value="" />
			<div class="content">

				<div class="i1">
					用户名: <input type="text" id="loginName" name="userModel.loginName">
				</div>
				<div class="i2">
					密&nbsp;&nbsp;&nbsp;码: <input type="password" id="password"
						name="password" onfocus="javascript:clearPass();"
						onkeypress="javascript:hiddenPass(event)" />
				</div>
				<div class="i3">
					邮&nbsp;&nbsp;&nbsp;箱: <INPUT type="text" name="userModel.email"
						id="mailAdress" value="${to }"> <input type="button"
						id="but" value=" " onmousedown="fun()" onmouseup="fun1()"
						style="background-image:url(images/emailup.jpg); width:28px; height:28px;cursor:pointer"
						title="请输入您的邮箱以获取验证码。" onClick="sendVerifycode();" />
				</div>
				<div class="i4">
					验证码: <input type="text" id="verifycode" name="verifycode"
						maxlength="6"><input type="button" name="sub" id="sub"
						value=""
						style="background-image:url(images/loginup.jpg); height:28px; width:106px; cursor:pointer;"
						onmousedown="btnDown()" onmouseup="btnOut()"
						onClick="return checkform();" /> <span id="errorMsg"
						style="color:red;font-size: 12">${ errorMsg}</span>
				</div>
			</div>
	</div>
</body>
</html>
<script language=javascript>
	document.onreadystatechange = function() {
		var app = navigator.appName;
		var verstr = navigator.appVersion;

		document.getElementById("loginName").focus();
	}

	function checkform() {
		var code = document.getElementById('verifycode').value;
		var bool = true;
		if (document.getElementById("loginName").value == '') {
			alert('请输入管理账号！');
			document.getElementById("loginName").focus();
			bool = false;
			return false;
		}
		if (document.getElementById("password").value == '') {
			alert('请输入密码！');
			document.getElementById("password").focus();
			bool = false;
			return false;
		}
		if (document.getElementById("verifycode").value == '') {
			alert('请输入验证码！');
			document.getElementById("verifycode").focus();
			bool = false;
			return false;
		}
		if (bool) {
			document.getElementById("loginform").submit();
		}
	}
</script>