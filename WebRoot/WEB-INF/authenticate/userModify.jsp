<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript">
	        //校验
	        function checkNotNull(valId,errId,warm){
	          var val = document.getElementById(valId).value;
	          var err = document.getElementById(errId);
	            if(val == null || val == ""){
	                err.innerHTML = warm;
	                return false;
	            }
	            err.innerHTML = "";
	            return true;
	        }
	        
	        //校验登录密码输入
        	function checkPassword(){
	  			var loginPassword=document.getElementById("userModel.password").value;
	  			var error = document.getElementById("passwordError");
	  			var r = true ;
	   			if(loginPassword.length<6 && loginPassword.length>0){
	   				error.innerHTML= "密码不能小于6位";
	   				r = false;
	   			}else{
	      			error.innerHTML="";
	      			r = true;
	  		 	}
	   				return r;
			}
	       
	         //校验邮箱 
	         function checkEmail(){
		       var email = document.getElementById("userModel.email");
			   var error = document.getElementById("emailError");
			   //对电子邮件的验证
			   var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9_-]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			   if(email.value!=""){
			     if(!myreg.test(email.value)){
			  	   error.innerHTML="请输入有效的E_mail！";
		  	 	   return false;
		  	     }else{
		  	       error.innerHTML="";
			       return true;
		  	     }
	  	       }else{
				 error.innerHTML="请输入E_mail！";
				 return false;	
			   }
		 	}
			
		    function promptLoginName(result){
   	    	  var err = document.getElementById("loginNameError");
   	    	  if(result == -1){
   	  	        err.innerHTML="该登录名已存在";
   	  	        return false;
   		      }
  			  err.innerHTML="";
   			  return true;
		    }
		    
  		   
	       //校验登录名
           function checkLoginName(){
             var loginName = document.getElementById('userModel.loginName');
             var  err = document.getElementById('loginNameError');
             if(loginName.value == null || loginName.value == ""){
               err.innerHTML = '请输入登录名';
               return false;
             }else{
               new Ajax.Request("checkLoginName.do",{
		       method:"post",
		       parameters:'userModel.loginName='+loginName.value+'&type=update',
		       onSuccess:function(req){
		         var isUnique = req.responseText;
		         if(isUnique == 0){
		        	 
		           err.innerHTML = '该登录名已存在，请重新输入登录名';
		           return false;
		         }else{
		           err.innerHTML = '';
		           return true;
		         }
		        }
	          });
		    }
           }

          
           //表单提交 
           function submitForm(){
             if(checkNotNull('userModel.userName','userNameError','请输入用户姓名')){
                var loginName = document.getElementById('userModel.loginName');
               var  err = document.getElementById('loginNameError');
               if(loginName.value == null || loginName.value == ""){
                 err.innerHTML = '请输入登录名';
                 return false;
               }else{
             
                 new Ajax.Request("checkLoginName.do",{
		         method:"post",
		         parameters:'userModel.loginName='+loginName.value+'&type=update',
		         onSuccess:function(req){
		           var isUnique = req.responseText;
		           if(isUnique == 0){
		             err.innerHTML = '该登录名已存在，请重新输入登录名';
		             return false;
		           }else{
		             err.innerHTML = '';
		             if(checkEmail()){
                       document.getElementById('dataForm').submit();
                     }
		           }
		          }
	            });
		      }
             } 
	       }
           
           
           //回显
           function echoRole(){
               var roleId = <s:property value='userModel.roleId.roleId' />;
               var role = document.getElementById("userModel.roleId.roleId");
               for(var i = 0; i < role.length; i++){
                   if(role[i].value == roleId){
                  	 role[i].selected = true;
                       return ;
                   }
               }
           }
           
        </script>
</head>
<body>
<div id="tabs1">
	<ul>
		<li><a href="listUser.do?menuId=${menuId }"><span>用户信息列表</span></a></li>
		<li id="current"><a href="#"><span>修改用户信息</span></a></li>
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
      <form id="dataForm" name="dataForm" action="updateUser.do" method="post">
	      <input type="hidden" name="userId" value="${userId }"/>
	      <input type="hidden" name="menuId"  value="${menuId }"/>
	      <input type="hidden" name="pageNo"  value="${pageNo }"/>
	      <h3>修改用户信息</h3>
	       <ul class="memlist fixwidth">
	        <li><em><span class="red">*</span>用户姓名：</em>
          		<input type="text" name="userModel.userName" id="userModel.userName"  class="txt required" maxlength="20" style="width:200px;" value= "${userModel.userName }" onblur="checkNotNull('userModel.userName','userNameError','请输入用户姓名');"/>
        		<span id="userNameError" style="color: red"></span>
        	</li>
	        <li><em><span class="red">*</span>登录名称：</em>

	          	<input type="text" name="userModel.loginName" id="userModel.loginName" class="txt required" maxlength="20" style="width:200px;" value= "${userModel.loginName }"  disabled />
	        	<span id="loginNameError" style="color: red"></span>
	        </li>
	        <li>
	            <em><span class="red">*</span>重置密码<span class="red">&nbsp;&nbsp;</span>：</em>
       	  		<input type="password" name="userModel.password" id="userModel.password" class="txt required" style="width:200px;" maxlength="25" value="" onblur="checkPassword();"/>
          		<span id="passwordError" style="color:red"></span>
	        </li>
	        <li><em><span class="red">*</span>用户邮箱：</em>
          		<input type="text" name="userModel.email" id="userModel.email" class="txt required" maxlength="100" style="width:200px;" value= "${userModel.email }" onblur="checkEmail();"/>
        		<span id="emailError" style="color:red"></span>
        	</li>
	        <li><em><span class="red">*</span>所属角色：</em>
	        	<select name="userModel.roleId.roleId"  id="userModel.roleId.roleId">
	        		<s:iterator value="listRole">
					  <option value="<s:property value='roleId' />">
					      <s:property value="roleTitle" />
					  </option>
					</s:iterator>
				</select>
			    <script type="text/javascript">
			   		 echoRole();
			    </script>
        	</li>
	      </ul>
	      <div class="opt">
	      	<a class="btn_blue" href="javascript:;" onclick="document.getElementById('btn-submit').click();return false;"><span>提交</span></a><input type="button" style="display:none" id="btn-submit" onclick="return submitForm();"/>
	      	<a class="btn_blue" href="listUser.do?menuId=${menuId }&pageNo=${pageNo}" target="_self"><span>返回</span></a> 
	      </div>
      </form>
    </div>
  </div>
</body>
</html>

