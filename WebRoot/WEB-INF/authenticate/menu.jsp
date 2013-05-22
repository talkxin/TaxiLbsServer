<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
<style type="text/css">
.tree_header {
   	background-image: url("images/menu-header.gif");
   	background-position:0px -1px;
    color: #15428B;
    font-family: tahoma,arial,verdana,sans-serif;
    font-size: 11px;
    font-weight: bold;
    overflow: hidden;
    line-height: 15px;
    padding: 5px 3px 4px 5px;
    -moz-user-select: none;
    border-bottom:1px solid #99BBE8; 
}
.button {
    height: 16px;
    width: 16px;
    padding-left: 0;
    padding-right: 0;
    background-position: center center;
    background-repeat: no-repeat;
    color: #333333;
    font: 11px arial,tahoma,verdana,helvetica;
    border: 0 none;
    cursor: pointer;
    margin: 0;
    outline: 0 none;
    overflow: visible;
}
#btn1 {
	background-image: url("images/menu_icon.png") !important;
}
#btn2 {
	background-image: url("images/expand-all.gif") !important;
	
}
#btn3 {
	background-image: url("images/collapse-all.gif") !important;
}
body {
    margin: 0;
    padding: 0;
}
</style>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
</head>
<body>
<div class="tree_header">
	<button id="btn1" type="button" class="button">&nbsp;</button>
	<span class="tree-header-text">功能菜单&nbsp; </span>
	<button id="btn2" type="button" class="button" onclick="javascript: d.openAll();">&nbsp;</button>
	<button id="btn3" type="button" class="button" onclick="javascript: d.closeAll();">&nbsp;</button>
</div>
<div class="dtree">
	<script type="text/javascript">
		<!--
		d = new dTree('d');
/* 		d.s=function(nodeId){
			document.getElementById(nodeId).;
			parent.contentIframe.location.href=link;
		} */
		d.add(0,-1,'功能菜单');
		${listMenuStr}
		document.write(d);
		//-->
	</script>
</div>
</body>
</html>