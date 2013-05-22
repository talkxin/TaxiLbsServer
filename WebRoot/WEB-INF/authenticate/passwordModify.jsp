<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	        //校验
	        function checkNotNull(valId,errId,warm){
	            var val = document.getElementById(valId).value;
	            var err = document.getElementById(errId);
	            var checkPassword = true;
	            var checkNewPassword = true;
	            var checkPasswordLength  = true;
	            if(val == null || val == ""){
	                err.innerHTML = warm;
	                return false;
	            }
	            err.innerHTML = "";
	           
	            if("newPassword"==valId){
	                checkPasswordLength = checkPWLength();
	            }
	            if("renewPassword"==valId){
	                checkNewPassword = checkRePassword("newPassword","renewPassword","renewPasswordError");
	            }
	            return checkPassword && checkNewPassword && checkPasswordLength;
	        }
	        
	        function checkForm(){
	            var check1 = checkNotNull('oldPassword','oldPasswordError','请输请输原密码');
	            var check2 = checkNotNull('newPassword','newPasswordError','请输请输新密码');
	            var check3 = checkNotNull('renewPassword','renewPasswordError','请再次输入密码');
	            return check1&&check2&&check3;
	        }
	        function checkRePassword(newPassword,rePassword,renewPasswordError){
	            var newpass = document.getElementById(newPassword);
	            var repass = document.getElementById(rePassword);
	            var error = document.getElementById(renewPasswordError);

				if(repass.value.length<6) {
					error.innerHTML="密码不能小于6位";
					return false;
				} else if (repass.value.match("^[A-Za-z]+$")
						|| !isNaN(repass.value)
						|| isTrue(repass.value)) {
					error.innerHTML = "密码必须是英文字母、数字、特殊字符中任意两项以上组合";
					return false;
				} else if(newpass.value!=repass.value){
	                error.innerHTML = "新密码与重复密码不一致";
	                return false;
	            }
	            error.innerHTML ="";
	            return true;
	        }
	        //校验密码长度,规则
	        function checkPWLength(){
	            var newPassword = document.getElementById("newPassword").value;
	            var error = document.getElementById("newPasswordError");
	            if(newPassword.length<6){
	                error.innerHTML = "密码长度不得小于6位";
	                return false;
	            } else if (newPassword.match("^[A-Za-z]+$")
						|| !isNaN(newPassword)
						|| isTrue(newPassword)) {
		   			error.innerHTML = "密码必须是英文字母、数字、特殊字符中任意两项以上组合";
		   			return false;
		   		}
	            return true;
	        }

			//判断字符串中是否包含字母和数字
			function isTrue(loginPassword) {
				for (var i = 0; i < loginPassword.length; i++) {
					if (loginPassword.substring(i, i + 1).match("^[A-Za-z0-9]+$")) {
						return false;
					} else {
						continue;
					}
				}
				return true;
			}

	        function checkModifyPassWord(){
	        	var isSuccess = ${result};
	        	if(0!=Number(isSuccess)){
	        	    if(1==Number(isSuccess)){
	        	        alert("密码修改成功！");
	        	    }if(2==Number(isSuccess)){
	        	        alert("密码修改失败！");
	        	    }
	        	    document.getElementById("goBack").innerHTML = "<input type='submit' value='确认修改 ' class='button'>&nbsp;<input type='button' value=' 返回' class='button' onclick='window.history.go(-2);' id='goBack'>"
	        	}
	        }
	        window.onload =function getFocus(){document.getElementById("oldPassword").focus()}  ;
        </script>
</head>
<body>
<div id="tabs1">
	<ul>
		<li id="current"><a href="#""><span>修改用户密码</span></a></li>
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
      <form id="dataForm" name="dataForm" action="updatePassword.do" method="post" onsubmit="return checkForm();">
	      
	      <h3>修改用户密码</h3>
	      <ul class="memlist fixwidth">
	        <li><em>旧密码<span class="red">*</span>：</em>
	          	<input type="password" name="passwordOld" id="oldPassword" class="txt required" maxlength="50" style="width:200px;" onblur="checkNotNull('oldPassword','oldPasswordError','请输原密码');"/>
	       		<span id="oldPasswordError" style="color: red"></span>
	        </li>
	        <li><em>新密码<span class="red">*</span>：</em>
          		<input type="password" name="passwordNew" id="newPassword"  class="txt required" maxlength="25" style="width:200px;" onblur="checkNotNull('newPassword','newPasswordError','请输新密码');"/>
        		<span id="newPasswordError" style="color: red"></span>
        	</li>
	        <li><em>确认密码<span class="red">*</span>：</em>
          		<input type="password" name="passwordNewR" id="renewPassword"  class="txt required" maxlength="25" style="width:200px;" onblur="checkNotNull('renewPassword','renewPasswordError','请再次输入密码');"/>
        		<span id="renewPasswordError" style="color: red"></span>
        	</li>
	      </ul>
	      
	      <div class="opt">
	      	<a class="btn_blue" href="javascript:;" onclick="document.getElementById('btn-submit').click();return false;"><span>提交</span></a><input type="submit" style="display:none" id="btn-submit"/>
	      </div>
	      <span id="errorMsg" style="color:red;font-size: 12">${ errorMsg}</span>
      </form>
    
    </div>
  </div>
</body>
</html>