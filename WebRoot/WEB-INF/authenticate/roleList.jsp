<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td_center {
	text-align: center;
	margin: 0px auto 0px auto;
}
</style>
<script type="text/javascript">
	function sub2(){
		document.name1.action="listRole.do";
		document.name1.submit();
	}
</script>
</head>
<body>
<div id="tabs1">
	<ul>
		<li id="current"><a href="#"><span>角色列表</span></a></li>
		<s:if test="roleVsMenu.menuOperateType==1 || roleVsMenu.menuOperateType==2">
		<li><a href="addRole.do?menuId=${menuId }"><span>增加角色</span></a></li>
		</s:if>
	</ul>
</div>
<div class="clearfix"></div>

<form action="" name="name1" method="post">
<input type="hidden" name="userId" value="${userId }"/>
<input type="hidden"  name="menuId" value="${menuId }"/>
<div class="container">	
	 <div>
		<table width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>

	<div id="msgdiv" class="errormsg" style="display: none;"><p>&nbsp;</p></div>
	<span style="color:red">${errorMsg }</span>
	<div class="mainbox" style="width:100%">
		<table class="datalist fixwidth">
			<tr>
				<th>角色名称</th>
				<th>角色描述</th>
				<th>操作</th>
			</tr>
			<s:if test="pageWapSite.totalRowNum>0">
			<s:iterator value="listRole">
			<tr>
				<td>
					<s:property value="roleTitle"/>
				</td>
				<td>
					<s:property value="roleDescription"/>
				</td>
				<td class="td_center">
				<s:if test="roleVsMenu.menuOperateType==1">
					<a href="modifyRole.do?roleId=${roleId }&menuId=${menuId}&pageNo=${pageNo}">修改</a> &nbsp; 
					<a href="deleteRole.do?roleId=${roleId }&menuId=${menuId}&pageNo=${pageNo}" onclick="javascript:if(confirm('您确定删除吗？')){return true;}else{return false;}">删除</a>
				</s:if>
				<s:if test="roleVsMenu.menuOperateType==2">
					<a href="modifyRole.do?roleId=${roleId }&menuId=${menuId}&pageNo=${pageNo}">修改</a> &nbsp; 
				</s:if>
				</td>
			</tr>
			</s:iterator>
			</s:if>
			<s:if test="pageWapSite.totalRowNum==0">
			<tr>
				<td colspan="3">
					<p class="i">
						目前没有相关记录!
					</p>
				</td>
			</tr>
			</s:if>
			<s:if test="pageWapSite.totalRowNum>0">
			   <s:if test="pageWapSite.totalRowNum>pageSize">
			      <tr>
			         <td colspan="3">
			           <span style="float:right"><s:property value="pageWapSite.display" escape="false" /></span>
			       	 </td>
			      </tr>
			   </s:if>
			</s:if>
		</table>
	</div>
</div>
</form>
</body>
</html>