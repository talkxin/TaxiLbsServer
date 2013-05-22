<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量增加司机</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
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
		var fileName = document.getElementById("viewfile1").value;
		var filepathtype = fileName.substring(fileName.lastIndexOf('.') + 1,
				fileName.length);
		if (fileName != null && fileName != '') {
			if (filepathtype.toLowerCase() == 'xls'.toLowerCase()) {
				document.getElementById('downloadLinkError2').innerHTML = '';
			} else {
				document.getElementById('downloadLinkError2').innerHTML = '只支持xls格式';
				return false;
			}
		} else {
			document.getElementById('downloadLinkError2').innerHTML = '提交文件为空';
			return false;
		}
		document.getElementById("tijiao").onclick = function() {
		};
		document.getElementById('form1').submit();
		return true;
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
			<li><a href="inDriver.do?menuId=${menuId }"><span>用户管理</span> </a></li>
			<s:if
				test="roleVsMenu.menuOperateType==1 || roleVsMenu.menuOperateType==2">
				<li id="current"><a href="#"><span>批量添加司机</span>
				</a></li>
				<li><a href="addDriver.do?menuId=${menuId }"><span>手动增加司机</span>
				</a></li>
				<li><a href="inDriverApply.do?menuId=${menuId }"><span>司机实名认证申请</span></a></li>
			</s:if>
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
			<form id="form1" action="saveAddsUser.do" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="menuId" value="${menuId }" />
				<h3>批量增加号段</h3>
				<ul class="memlist fixwidth">
					<li id="upFile"><span style="color: red">*</span><em>上传文件</em>
						<div class="line1">
							<span> <input name="" readonly type="text" id="viewfile1"
								onmouseout="document.getElementById('fileNames').style.display='none';"
								class="inputstyle" /> </span> <label for="unload"
								onmouseover="document.getElementById('fileNames').style.display='block';"
								class="file1">浏览...</label> <input type="file" name="upload"
								id="fileNames"
								onchange="document.getElementById('viewfile1').value=this.value;this.style.display='none';"
								class="file" /> <span id="downloadLinkError2"
								style="color: red"></span>
							<s:if test="masager!=null && masager!=\"\"">
								<span id="tijiaozhong" style="color: red">${masager }</span>
							</s:if>
						</div></li>
					<li>上传示例：“13012345678,丽江,描述”</li>
				</ul>
				<div class="opt">
					<a class="btn_blue" id="tijiao" href="#" onclick="submitForm();"><span>提交</span>
					</a> <a class="btn_blue" href="inUserInfo.do?menuId=${menuId}"
						target="_self"><span>返回</span> </a>
				</div>

			</form>

		</div>
	</div>
</body>
</html>