<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
}
</style>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
<script type="text/javascript">
function menu_del(menuId) {
	if(confirm('您确定要删除吗？')){
		window.open('deleteEditMenu.do'+'?menuId='+menuId,'contentIframe');
	}else{
	}
}

function menu_modify(menuId,menuType,type) {
	if(type==1){
		window.open('addEditMenu.do'+'?menuId='+menuId,'modifyEditMenuIframe');
	}else if(type==2){
		window.open('modifyEditMenu.do'+'?menuId='+menuId,'modifyEditMenuIframe');
	}
	
}
</script>
</head>
<body>

<div class="dtree">
	<script type="text/javascript">
		<!--
		d = new dTree('d');
/* 		d.s=function(nodeId){
			document.getElementById(nodeId).;
			parent.contentIframe.location.href=link;
		} */
		d.add(0,-1,'换机平台功能菜单','javascript:menu_modify(0,1,1);','换机平台功能菜单','',false,false,' <a href="#" onclick="javascript:menu_modify(0,1,1);"><img src="images//dtree//add.gif"//></a>');
		${listMenuStr}
		document.write(d);
		//-->
	</script>
</div>
</body>
</html>