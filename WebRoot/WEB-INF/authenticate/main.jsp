<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>taxiLBS管理系统</title>
<style type="text/css">
.top_bg {
    background: url("images/nettop_05.jpg") repeat-x scroll 0 0 transparent;
    height: 68px;
}
.topleft {
    background: url("images/nettop_03.jpg") no-repeat scroll 0 0 transparent;
    float: left;
    height: 68px;
    width: 650px;
}
.topright {
    color: #FFFFFF;
    display: inline;
    float: right;
    height: 68px;
    padding-right: 10px;
    text-align: right;
}
.top_bottom {
    background-color: #DFE8F6;
    font-size: 1px;
    line-height: 1px;
    position: absolute;
    z-index: 3;
    margin: 0 auto;
    padding: 0;    
    border-top:1px solid white; 
    width: 100%;
    height: 5px;		
}
.menu-layout {
    background-color: white;
    top: 74px;
    font-size: 1px;
    line-height: 1px;
    position: absolute;
    z-index: 3;
    margin-left: 5px;
    padding: 0;    
    width: 190px;
    border: 1px solid #99BBE8;
}
.split-layout {
    background-color: #DFE8F6;
    top: 74px;
    left: 207px;
    font-size: 1px;
    line-height: 1px;
    position: absolute;
    z-index: 3;
    cursor: col-resize;
    margin: 0 auto;
    padding: 0;    
    width: 5px;
    height: 99%;
}
.split-layout-botton {
    
    top: 48%;
    cursor: pointer;
    display: block;
    height: 35px;
    left: 0;
    opacity: 0.5;
    position: absolute;
    width: 5px;
}
.content-layout {
    background-color: white;
    top: 74px;
    left: 207px;
    line-height: 1px;
    position: absolute;
    z-index: 3;
    margin-left: 5px;
    padding: 0;    
    height: 99%;
    border: 1px solid #99BBE8;
}
.bottom-layout {
    background-color: #DFE8F6;
    font-size: 12px;
    line-height: 1px;
    position: absolute;
    z-index: 3;
    margin: 0 auto;
    padding: 0;    
    width: 100%;
    height: 20px;
    text-align: center;
    vertical-align:middle;		
}
body {
    background-color: #DFE8F6;
    color: #666666;
    font: 12px Arial,Verdana,Lucida,Helvetica,sans-serif;
    margin: 0 auto;
    padding: 0;
    overflow-x:hidden;
    overflow-y:hidden;
}
</style>
<script type="text/javascript">
window.onload = function() {
	resize_iframe();
}

function resize_iframe() {
	document.getElementById("content-iframe").style.width = document.body.clientWidth - (205 + 14);
	document.getElementById("menu-iframe").style.height = document.body.clientHeight - (74 + 7 + 20);
	document.getElementById("split-iframe").style.height = document.body.clientHeight - (74 + 7 + 20);
	document.getElementById("content-iframe").style.height = document.body.clientHeight - (74 + 7 + 20);
	document.getElementById("bottom-iframe").style.top = document.body.clientHeight - 15;
}

function logout_click() {
	if(window.confirm("你确定要退出系统吗？")) { 
		document.location.href="logout.do";
	}
}
</script>
</head>
<body onResize="resize_iframe();">
<div class="top_bg">
    <div class="topleft"></div>
	<div class="topright">您好, <strong>${loginName }</strong> 
		<img border="0" align="absmiddle" usemap="#Map" src="images/nettop_07.jpg">
		<map id="Map" name="Map">
		  <area target="contentIframe" title="首页" href="welcome.do" coords="21,30,16" shape="circle">
		  <area target="contentIframe" title="修改密码" href="modifyPassword.do" coords="64,31,14" shape="circle">
		  <area title="退出" href="#" onclick="logout_click();"  coords="109,30,15" shape="circle">
		</map>
	</div>
</div>
<div class="top_bottom">&nbsp;</div>
<div id="menu-iframe" class="menu-layout">
	<iframe id="menuIframe" src="menu.do" width="100%" height="100%" frameborder="no" border="0"></iframe>
</div>
<div id="split-iframe" class="split-layout">
	<div class="split-layout-botton">&nbsp;</div>
</div>
<div id="content-iframe" class="content-layout">
	<iframe id="contentIframe" name="contentIframe" src="welcome.do" width="100%" height="100%" frameborder="no" border="0"></iframe>
</div>
<div id="bottom-iframe" class="bottom-layout">
	<span>©2012&nbsp;  &nbsp; &nbsp;   </span>
</div>
</body>
</html>