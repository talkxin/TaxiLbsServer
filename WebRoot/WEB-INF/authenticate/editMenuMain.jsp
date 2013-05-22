<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能菜单管理</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="tabs1">
	<ul>
		<li id="current"><a href="#""><span>功能菜单管理</span></a></li>
	</ul>
</div>

<div class="container">
	 <div>
		<table width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	
    <div class="mainbox">
      <form id="dataForm" name="dataForm" action="role_control.jsp" method="post" target="targetFrame">
	      <br/>
	      <div>
	        	<div style="float:left; height:570px; width:35%; overflow:auto; border:solid 1px #B1C9EC;">
                     <iframe id="listEditMenuIframe" src="listEditMenu.do" width="100%" height="100%" frameborder="no" border="0"></iframe>
                </div>
                <div style="float:left; height:570px; width:60%;margin-left: 2%; overflow:auto; border:solid 1px #B1C9EC;">
                      <iframe id="modifyEditMenuIframe" name="modifyEditMenuIframe" src="addEditMenu.do" width="100%" height="100%" frameborder="no" border="0"></iframe>
                </div>                
	      </div>
      </form>
    </div>
</div>
</body>
</html>