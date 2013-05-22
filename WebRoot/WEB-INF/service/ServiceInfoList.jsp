<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务流水列表</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td_center {
	text-align: center;
	margin: 0px auto 0px auto;
}
</style>
<style type="text/css">
.td_center {
	text-align: center;
	margin: 0px auto 0px auto;
}

.jqmWindow {
	display: none;
	position: fixed;
	top: 20%;
	left: 60%;
	margin-left: -300px;
	width: 300px;
	background-image: url("images/window.gif");
	height: 88px;
	color: #333;
	border: 1px solid black;
	border-color: #99BBE8;
	border-style: solid;
	border-width: 0;
}

.jqmOverlay {
	background-color: #000;
}

.dialog_header {
	background-position: 0px -1px;
	color: #15428B;
	font-family: tahoma, arial, verdana, sans-serif;
	font-size: 11px;
	font-weight: bold;
	overflow: hidden;
	line-height: 15px;
	padding: 7px 3px 4px 0px;
	width: 300px;
	border-bottom: 1px solid #99BBE8;
}

.dialog_body {
	background: none repeat scroll 0 0 transparent;
	overflow: hidden;
	position: relative;
	line-height: 35px;
}

.dialog_text {
	padding-bottom: 0;
	padding-top: 2px;
	background-color: #FFFFFF;
	background-image: url("images/dialog_btn_bg.gif");
	border-color: #B5B8C8;
}

.dialog_lable {
	display: block;
	float: left;
	padding: 3px 3px 3px 0;
	position: relative;
	font: 11px tahoma, arial, helvetica, sans-serif;
	border-style: none solid solid;
}

* html .jqmWindow {
	position: absolute;
	top: expression((                                                  
		                        document.documentElement.scrollTop ||        
		                 
		
		  
		        
		                                     document.body.scrollTop)+   
		           
		                       Math.round(17*                
		
		
		
		
		
		
		
		   
		           (document.documentElement.offsetHeight||        
		                   
		
		
		
		
		
		
		
		
		
		 document.body.clientHeight )/100 )+    
		                                  'px' );
}
</style>
<script type="text/javascript">
	//翻页方法 
	function sub2() {
		var a = document.getElementById("pageNo").value;
		var b = document.getElementById("menuId").value;
		var from = document.getElementById("queryForm");
		from.action += "?pageNo=" + a + "&menuId=" + b;
		from.submit();
	};
</script>
</head>
<body>
	<div id="tabs1">
		<ul>
			<li id="current"><a href="#"><span>软件列表</span> </a></li>
			<s:if test="menuId==1 || menuId==2">
			</s:if>
		</ul>
	</div>
	<div class="clearfix"></div>

	<div class="container">
		<!--  搜索条件区域 -->
		<div id="idHasTabMenu" class="querymenu"
			style="width: 997px; background-color: #EAEAEA">
			<form name="queryForm" id="queryForm" action="inServiceInfo.do"
				method="post">
				<input type="hidden" name="menuId" value="${menuId }" /> <input
					type="hidden" name="pageNum" value="1" />
				<table width="50%">
					<tr>
						<td align="left">所属公司: <input type="text"
							id="serviceObject.driverCompany"
							name="serviceObject.driverCompany" style="width: 120"
							maxlength="50" size="10" value="${serviceObject.driverCompany }" />
						</td>
						<td>&nbsp; 车牌号: <input type="text"
							id="serviceObject.plateNumber" name="serviceObject.plateNumber"
							style="width: 120" maxlength="50" size="10"
							value="${serviceObject.plateNumber }" />
						</td>
						<td><a class="btn_blue" href="javascript:;"
							onclick="document.getElementById('btn-search').click();return false;"><span>查
									询</span> </a><input type="submit" style="display: none" id="btn-search" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<form action="" name="name1" method="post">
			<input type="hidden" name="userId" value="${userId }" />
			<div id="msgdiv" class="errormsg" style="display: none;">
				<p>&nbsp;</p>
			</div>

			<div class="mainbox" style="width: 100%">
				<table class="datalist fixwidth">
					<tr>
						<th>支付流水</th>
						<th>支付类型</th>
						<th>支付形式</th>
						<th>所属公司</th>
						<th>车牌号</th>
					</tr>
					<s:if test="pageWapSite.totalRowNum>0">
						<s:iterator value="serviceList" var="soft" status="index">
							<tr>
								<td><s:property value="#soft.serviceID" /></td>
								<td><s:property value="#soft.PayType" /></td>
								<td><s:property value="#soft.PayType2" /></td>
								<td><s:property value="#soft.driverCompany" /></td>
								<td><s:property value="#soft.plateNumber" /></td>
							</tr>
						</s:iterator>
					</s:if>
					<s:if test="pageWapSite.totalRowNum==0">
						<tr>
							<td colspan="6">
								<p class="i">目前没有相关记录!</p>
							</td>
						</tr>
					</s:if>
					<s:if test="pageWapSite.totalRowNum>0">
						<s:if test="pageWapSite.totalRowNum>pageSize">
							<tr>
								<td colspan="6"><span style="float: right"><s:property
											value="pageWapSite.display" escape="false" /> </span>
								</td>
							</tr>
						</s:if>
					</s:if>

				</table>
			</div>
		</form>
	</div>
</body>
</html>