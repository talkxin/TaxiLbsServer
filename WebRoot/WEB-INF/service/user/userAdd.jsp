<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<script type="text/javascript">
	//$(document)
	//.ready(
	//		function() {
	//			$("#osType")
	//					.change(
	//							function() {
	//								//alert($("#osType").val());
	//								$
	//										.ajax({
	//											url : "getOsmobule.do",
	//											type : "get",
	//											dataType : "json",
	//											data : "osType="
	//													+ $("#osType")
	//															.val(),
	//											success : function(
	//													osMobuleType) {
	//												var ostypes = $("#osDiv");
	//												ostypes.empty();
	//												var html = '';
	//												for ( var i = 0; i < osMobuleType.length; i++) {
	//													html = html
	//															+ "<input type='checkbox' id='"+osMobuleType[i][0]+"' name='osTypes' value='"+osMobuleType[i][2]+"'/><label for='checkbox' id='"+osMobuleType[i][0]+"'>"
	//															+ osMobuleType[i][2]
	//															+ "</label>";
	//												}
	//												ostypes.html(html);
	//											}
	//										});
	//							});
	//		});
	//function isOrderdis() {
	//	var a = document.getElementById("innorSoft.isOrder").value;
	//	if (a == 1) {
	//		document.getElementById("orderL").style.display = "block";
	//	} else {
	//		document.getElementById("orderL").style.display = "none";
	//	}
	//}
	//提交验证
	function submitForm() {
		var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
		var a1 = document.getElementById("loginName").value;
		var a2 = document.getElementById("password").value;
		var a3 = document.getElementById("email").value;
		var a4 = document.getElementById("phoneNumber").value;
		var a5 = $("#phoneNumber1").val();
		var a6 = $("#loginName1").val();
		isNew();
		if (a5 != '01' && a6 != 01) {
			return false;
		}
		if (a4 == null || a4 == '') {
			$('#phoneNumberError').html('请填写手机号');
			return false;
		} else {
			if (isNaN(a4) || a4.length > 14 || a4.length < 11) {
				$('#phoneNumberError').html('请正确填写手机号');
				return false;
			} else {
				$('#phoneNumberError').html('');
			}
		}
		if (a1 == null || a1 == '') {
			$('#loginNameError').html("请填写登录名");
			return false;
		} else {
			if (!patrn.exec(a1)) {
				$('#loginNameError').html("请正确填写登录名");
				return false;
			}
			$('#loginNameError').html('');
		}
		if (a2 == null || a2 == '') {
			$('#passwordError').html('请填写密码');
			return false;
		} else {
			if (!patrn.exec(a2)) {
				$('#passwordError').html('请正确填写密码');
				return false;
			}
			$('#passwordError').html('');
		}
		if (a3 != null && a3 != '') {
			//对电子邮件的验证
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9_-]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if (!myreg.test(a3)) {
				$('#emailError').html('请输入有效的E_mail！');
				return false;
			} else {
				$('#emailError').html('');
			}
		}
		document.getElementById('form1').submit();
		return true;
	}

	function isNew() {
		var phone = $('#phoneNumber').val();
		var login = $('#loginName').val();
		if (phone != null && phone != '' && !isNaN(phone)) {
			$.ajax({
				url : "isNewUser.do",
				type : "get",
				dataType : "json",
				data : 'taxiUser.phoneNumber=' + phone,
				success : function(newUser) {
					if (newUser == 1) {
						$('#phoneNumberError').html('该手机号已注册');
					} else {
						$('#phoneNumberError').html('');
						$("#phoneNumber1").val("01");
					}
				}
			});
		} else {
			$('#phoneNumberError').html('请正确填写手机号');
		}
		var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
		if (patrn.exec(login)) {
			$.ajax({
				url : "isNewUser.do",
				type : "get",
				dataType : "json",
				data : 'taxiUser.loginName=' + login,
				success : function(newUser) {
					if (newUser == 1) {
						$('#loginNameError').html('该用户已注册');
					} else {
						$('#loginNameError').html('');
						$("#loginName1").val("01");
						$('#userName').val(login);
					}
				}
			});
		} else {
			$('#loginNameError').html('请正确填写登录名');
		}
	}

	function kk(obj) {
		if (obj.value.length > 50)
			obj.value = obj.value.substr(0, 100);
	}
</script>
<style type="text/css">
<!--
* {
	font-size: 12px
}

body {
	margin: 0
}

.line1 {
	position: relative;
	float: left;
	padding: 8px 0
}

.line1 span {
	float: left
}

input {
	border: 1px solid #888;
	vertical-align: middle
}

.file {
	position: absolute;
	left: 90px;
	top: 8px;
	display: none;
	filter: alpha(opacity =                
		                                                         
		                                                         
		                                                         
		                                                                     
		                                                                0);
	opacity: 0
}

.file1 {
	padding: 6px 10px;
	display: block;
	float: left;
	background: #fff;
	color: #00F;
	z-index: 1;
	margin-left: 5px;
	vertical-align: middle;
	cursor: pointer;
	text-decoration: underline;
}

.inputstyle {
	width: 150px;
	border: 1px solid #888;
	z-index: 99
}
-->
</style>
</head>
<body>
	<div id="tabs1">
		<ul>
			<li id="current"><a href="inUserInfo.do?menuId=${menuId }"><span>用户列表</span>
			</a>
			</li>
			<li><a href="addsUserInfo.do?menuId=${menuId }"><span>批量添加用户</span>
			</a>
			</li>
			<li><a href="#"><span>手动增加用户</span> </a>
			</li>
		</ul>
	</div>
	<div class="clearfix"></div>

	<div class="container">
		<div>
			<table width="100%">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>

		<div class="mainbox">
			<form id="form1" name="form1" action="saveAddUser.do" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="menuId" value="${menuId }" /> <input
					type="hidden" name="taxiUser.roleid" value="1" /> <input
					type="hidden" name="taxiUser.isLogin" value="0" /> <input
					type="hidden" id="phoneNumber1" value="0" /> <input type="hidden"
					id="loginName1" value="0" />
				<h3>手动增加号码</h3>
				<ul class="memlist fixwidth">
					<li><em><span style="color: red">*</span>手机号：</em><input
						maxlength="11" id="phoneNumber" name="taxiUser.phoneNumber"
						onblur="isNew();" /><span id="phoneNumberError"
						style="color: red"></span> <s:if
							test="massage!=null && massage!=\"\"">
							<span style="color: red">${massage }</span>
						</s:if>
					</li>
					<li><em><span style="color: red">*</span>登录名:</em><input
						id="loginName" name="taxiUser.loginName" onblur="isNew();"
						maxlength="11" /><span id="loginNameError" style="color: red"></span>
						<input type="hidden" name="taxiUser.userName" id="userName" />
					</li>
					<li><em><span style="color: red">*</span>密码：</em><input
						id="password" name="taxiUser.password" /><span id="passwordError"
						style="color: red"></span>
					</li>
					<li><em><span style="color: red">&nbsp;&nbsp;</span>邮箱：</em><input
						id="email" name="taxiUser.email" /><span id="emailError"
						style="color: red"></span>
					</li>
				</ul>

				<div class="opt">
					<a href="#" onclick="submitForm();"><span>提交</span> </a>
					<!-- a class="btn_blue" href="javascript:;"
						onclick="document.getElementById('btn-submit').click();"><span>提交</span>
					</a -->
					<input type="button" style="display: none" id="btn-submit"
						onclick="submitForm();" /> <a class="btn_blue"
						href="inUserInfo.do?menuId=${menuId}" target="_self"><span>返回</span>
					</a>
				</div>

			</form>

		</div>
	</div>
</body>
</html>