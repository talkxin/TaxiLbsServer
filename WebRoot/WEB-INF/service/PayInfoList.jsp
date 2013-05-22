<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付流水列表</title>
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
			<form name="queryForm" id="queryForm" action="inPayinfo.do"
				method="post">
				<input type="hidden" name="menuId" value="${menuId }" /> <input
					type="hidden" name="pageNum" value="1" />
				<table width="50%">
					<tr>
						<td align="left">支付类型: <select id="payInfoObject.PayType"
							name="payInfoObject.PayType">
								<s:if test="payInfoObject==null || payInfoObject.PayType==null">
									<option value="">全部</option>
									<option value="0">现金</option>
									<option value="1">支付宝</option>
									<option value="2">刷卡</option>
									<option value="3">其他</option>
								</s:if>
								<s:else>
									<s:if test="payInfoObject.PayType==0">
										<option value="0">现金</option>
										<option value="1">支付宝</option>
										<option value="2">刷卡</option>
										<option value="3">其他</option>
									</s:if>
									<s:if test="payInfoObject.PayType==1">
										<option value="1">支付宝</option>
										<option value="0">现金</option>
										<option value="2">刷卡</option>
										<option value="3">其他</option>
									</s:if>
									<s:if test="payInfoObject.PayType==2">
										<option value="2">刷卡</option>
										<option value="0">现金</option>
										<option value="1">支付宝</option>
										<option value="3">其他</option>
									</s:if>
									<s:if test="payInfoObject.PayType==3">
										<option value="3">其他</option>
										<option value="0">现金</option>
										<option value="1">支付宝</option>
										<option value="2">刷卡</option>
									</s:if>
								</s:else>
						</select>&nbsp; 支付形式: <select id="payInfoObject.PayType2"
							name="payInfoObject.PayType2">
								<s:if test="payInfoObject==null || payInfoObject.PayType2==null">
									<option value="">全部</option>
									<option value="0">服务</option>
									<option value="1">定金</option>
								</s:if>
								<s:else>
									<s:if test="payInfoObject.PayType2==0">
										<option value="0">服务</option>
										<option value="">全部</option>
										<option value="1">定金</option>
									</s:if>
									<s:if test="payInfoObject.PayType2==1">
										<option value="1">定金</option>
										<option value="">全部</option>
										<option value="0">服务</option>
									</s:if>
								</s:else>
						</select>&nbsp; 所属公司: <input type="text" id="payInfoObject.driverCompany"
							name="payInfoObject.driverCompany" style="width: 50"
							maxlength="20" size="10" value="${payInfoObject.driverCompany }" />&nbsp;
							车牌号: <input type="text" id="payInfoObject.plateNumber"
							name="payInfoObject.plateNumber" style="width: 50" maxlength="20"
							size="10" value="${payInfoObject.plateNumber }" />
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
						<s:iterator value="payInfoList" var="soft" status="index">
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