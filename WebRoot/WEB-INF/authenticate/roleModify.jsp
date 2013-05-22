<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改角色</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
<script type="text/javascript">
function checkAll(){
	if(checkTitle()&&checkDescription()){
		return true;
	}else{
		return false;
	}
}
function checkTitle(){
	var title=document.getElementById("title").value;
	if(title.length==0){
		document.getElementById("errorTitle").innerHTML="角色名称不能为空";
		return false;
	}else{
		document.getElementById("errorTitle").innerHTML="";
		return true;
	}
}
function checkDescription(){
	var description=document.getElementById("remark").value;
	if(description.length>100){
		document.getElementById("errorRemark").innerHTML="角色描述不能超过100个字";
		return false;
	}else{
		document.getElementById("errorRemark").innerHTML="";
		return true;
	}
}
</script>
</head>
<body>
<div id="tabs1">
	<ul>
		<li><a href="listRole.do?menuId=${menuId }"><span>角色列表</span></a></li>
		<li id="current"><a href="#"><span>修改角色</span></a></li>
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
      <form id="dataForm" name="dataForm" action="updateRole.do" method="post" target="contentIframe" onsubmit="return checkAll();">
	      <input type="hidden" name="roleId"  value="${roleId }"/>
	      <input type="hidden" name="menuId"  value="${menuId }"/>
	       <input type="hidden" name="pageNo"  value="${pageNo }"/>
	      <h3>修改角色信息</h3>
	      <ul class="memlist fixwidth">
	        <li><em>角色名称<span class="red">*</span>：</em>
          		<input type="text" name="roleModel.roleTitle" id="title" value="${roleModel.roleTitle }" class="txt required" maxlength="25" style="width:200px;" onblur="checkTitle();"/><span id="errorTitle" style="color:red"></span>
        	</li>
	        <li><em>&nbsp; 角色描述：</em>
	          	<textarea name="roleModel.roleDescription" id="remark" class="bigarea min-length-0 max-length-100 textv" onblur="checkDescription();">${roleModel.roleDescription }</textarea><span id="errorRemark" style="color:red"></span>
	        </li>
	        <li><em>权限选择：<span class="red">*</span></em>
	        	<div style="height:190px; overflow:auto; border-style:solid; border-width:1px">
				<script type="text/javascript">
					<!--
					d = new dTree('d');
			/* 		d.s=function(nodeId){
						document.getElementById(nodeId).;
						parent.contentIframe.location.href=link;
					} */
					d.add(0,-1,'换机系统平台菜单','####','','',true,true,'','','',true);
					${listMenuStr}
					document.write(d);
					//-->
				</script>          
                </div>
	        </li>
	      </ul>
	      <div class="opt">
	      <span id="errorMsg" style="color:red">${errorMsg }</span>
	      	<a class="btn_blue" href="javascript:;" onclick="document.getElementById('btn-submit').click();return false;"><span>提交</span></a><input type="submit" style="display:none" id="btn-submit"/>
	      	<a class="btn_blue" href="listRole.do?menuId=${menuId }&pageNo=${pageNo}" target="_self"><span>返回</span></a> 
	      </div>
	      
      </form>
    
    </div>
  </div>
</body>
</html>
