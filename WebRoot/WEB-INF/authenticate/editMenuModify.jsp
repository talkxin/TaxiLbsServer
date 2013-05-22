<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%   
response.setHeader("Pragma","No-cache");   
response.setHeader("Cache-Control","no-cache");   
response.setHeader("Cache-Control", "no-store");   
response.setDateHeader("Expires", 0);   
%>   
<meta http-equiv="Expires" content="0" />   
<meta http-equiv="Cache-Control" content="no-cache" />   
<meta http-equiv="Cache-Control" content="no-store" />   
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改功能菜单</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
    margin: 0;
    padding: 0;
}
</style>
<script type="text/javascript">
function checkAll(){
	var title=document.getElementById("title").value;
	if(title.length==0){
		document.getElementById("errorMsg").innerHTML="名称不能为空";
		return false;
	}else{
		document.getElementById("errorMsg").innerHTML="";
		return true;
	}
	
}
</script>
</head>
<body>

<div class="container">
    <form method="post" action="updateEditMenu.do" name="dataForm" id="dataForm" target="contentIframe" onsubmit="return checkAll();">
    <input type="hidden" name="menuId"  value="${menuModel.menuId }"/>
      	<h4 style="color:#627EB7">修改菜单信息 </h4>
	      <ul class="memlist fixwidth">
	        <li><em>菜单名称 ：</em>
          		<input type="text" style="width:250px;" maxlength="20"  class="txt required"  id="title" name="menuModel.menuTitle"  value="${menuModel.menuTitle}" onblur="checkAll();"/><span id="errorMsg" style="color:red">${errorMsg }</span>
        	</li>
	        <li><em>菜单类型 ：</em>
	       	    <s:if test="menuModel.menuType==1">
		          	<input type="radio" name="menuModel.menuType" id="menu_foloder" value="1" checked>文件夹
	          	</s:if>
	          	<s:if test="menuModel.menuType==2">
		          	<input type="radio" name="menuModel.menuType" id="menu_foloder" value="1" >文件夹
		          	<input type="radio" name="menuModel.menuType" id="menu_file" value="2" checked >功能
	          	</s:if>
	        </li>
	        <s:if test="menuModel.menuType==2">
	        <li><em>链接地址 ：</em>
	        	<input type="text" style="width:250px;" maxlength="100" class="txt required"  id="linkUrl" name="menuModel.menuLink" value="${menuModel.menuLink }">
	        </li>
	        <li><em>读写权限 ：</em>
	      	    <s:if test="menuModel.menuOperateType==1">
		          	<input type="radio" name="menuModel.menuOperateType" id="menuRW_0" value="1" checked>不限
	          		<input type="radio" name="menuModel.menuOperateType" id="menuRW_1" value="2">查询 + 增加 + 修改
	          		<input type="radio" name="menuModel.menuOperateType" id="menuRW_1" value="3">查询 
	          	</s:if>
	          	<s:if test="menuModel.menuOperateType==2">
		          	<input type="radio" name="menuModel.menuOperateType" id="menuRW_0" value="1" >不限
	          		<input type="radio" name="menuModel.menuOperateType" id="menuRW_1" value="2" checked>查询 + 增加 + 修改
	          		<input type="radio" name="menuModel.menuOperateType" id="menuRW_1" value="3">查询 
	          	</s:if>
	          	<s:if test="menuModel.menuOperateType==3">
		          	<input type="radio" name="menuModel.menuOperateType" id="menuRW_0" value="1" >不限
	          		<input type="radio" name="menuModel.menuOperateType" id="menuRW_1" value="2">查询 + 增加 + 修改
	          		<input type="radio" name="menuModel.menuOperateType" id="menuRW_1" value="3" checked>查询 
	          	</s:if>
	        </li>
	        <li><em>人员限制 ：</em>
	        	<s:if test="menuModel.menuPsersonScope==1">
		          	<input type="radio" name="menuModel.menuPsersonScope" id="menuRange_0" value="1" checked>不限
	          		<input type="radio" name="menuModel.menuPsersonScope" id="menuRange_1" value="2">按创建人限制
	          	</s:if>
	          	<s:if test="menuModel.menuPsersonScope==2">
		          	<input type="radio" name="menuModel.menuPsersonScope" id="menuRange_0" value="1" >不限
	          		<input type="radio" name="menuModel.menuPsersonScope" id="menuRange_1" value="2" checked>按创建人限制
	          	</s:if>
	        </li>
	        <li><em>地区限制 ：</em>
	           <s:if test="menuModel.menuAreaScope==1">
		          	<input type="radio" name="menuModel.menuAreaScope" id="menuRange_0" value="1" checked>不限
	          		<input type="radio" name="menuModel.menuAreaScope" id="menuRange_1" value="2">按地区限制
	          	</s:if>
	          	  <s:if test="menuModel.menuAreaScope==2">
		          	<input type="radio" name="menuModel.menuAreaScope" id="menuRange_0" value="1" >不限
	          		<input type="radio" name="menuModel.menuAreaScope" id="menuRange_1" value="2" checked>按地区限制
	          	</s:if>
	        </li>
	        </s:if>
	      </ul>
	      <div class="opt" style="float:left;margin-left:6%">
	      	<br/>
	      	
	      	<a class="btn_blue" href="javascript:;" onclick="document.getElementById('btn-submit').click();return false;"><span>提交</span></a><input type="submit" style="display:none" id="btn-submit"/>
	      </div>	
    </form>
</div>
</body>
</html>
