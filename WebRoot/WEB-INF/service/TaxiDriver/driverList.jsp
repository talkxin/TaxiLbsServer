<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>司机列表</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
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
	//全选方法
	function quanxuanChex() {
		var chex = document.getElementById("quanxuan");
		var chexs = document.getElementsByName("phoneNumbers");
		var c;
		if (chex.checked == true) {
			c = true;
		} else {
			c = false;
		}
		for ( var i = 0; i < chexs.length; i++) {
			chexs[i].checked = c;
		}
	}
	function submitForm() {
		document.getElementById('queryForm').submit();
		return true;
	}
	//翻页方法 
	function sub2(obj) {
		if (obj != null)
			document.getElementById("inPageNo").value = obj.value;
		document.getElementById("queryForm").submit();
	};
	//修改
	function update(str) {
		$.ajax({
			url : "newDriverTo.do",
			type : "post",
			async : false,
			dataType : "json",
			data : 'driverApply.id=' + str + '&driverApply.isTrue=1'
		});
		sub2(document.getElementById("pageNo"));
	}
	//删除单个
	function delOne(str) {
		if (window.confirm("您是否需要删除该司机认证？")) {
			var a1 = $("#driverName").val();
			var a2 = $("#driverCompany").val();
			var a3 = $("#plateNumber").val();
			window.location.href = str + "&seach[0]=" + a1 + "&seach[1]=" + a2
					+ "&seach[2]=" + a3;
		}
	}
</script>
</head>
<body>
	<div id="tabs1">
		<ul>
			<li id="current"><a href="#"><span>用户管理</span> </a>
			</li>
			<s:if
				test="roleVsMenu.menuOperateType==1 || roleVsMenu.menuOperateType==2">
				<li><a href="inDriverApply.do?menuId=${menuId }"><span>司机实名认证申请</span>
				</a>
				</li>
			</s:if>
		</ul>
	</div>
	<div class="clearfix"></div>

	<div class="container">
		<!--  搜索条件区域 -->
		<div id="idHasTabMenu" class="querymenu"
			style="width: 997px; background-color: #EAEAEA">
			<form name="queryForm" id="queryForm" action="inDriver.do"
				method="post">
				<input type="hidden" name="menuId" value="${menuId }" /> <input
					type="hidden" id="inPageNo" name='pageNo' value="1">
				<table width="50%">
					<tr>
						<td align="left"><input type="hidden" name="pageNum"
							value="1" /> 真实姓名: <input type="text" id="driverName"
							name="seach[0]" style="width: 70" maxlength="50" size="10"
							value="${seach[0]=='null'?'':seach[0]}" />公司名：<input type="text"
							id="driverCompany" name="seach[1]" style="width: 70"
							maxlength="50" size="10" value="${seach[1]=='null'?'':seach[1]}" />车牌号：<input
							type="text" id="plateNumber" name="seach[2]" style="width: 70"
							maxlength="50" size="10" value="${seach[2]=='null'?'':seach[1]}" />
						</td>
						<td><a class="btn_blue" href="#" onclick="submitForm();"><span>查询</span>
						</a>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<form action="delAllUser.do" name="name1" id="delForm" method="post">
			<input type="hidden" name="menuId" value="${menuId }" /> <input
				type="hidden" name="userId" value="${userId }" />
			<div id="msgdiv" class="errormsg" style="display: none;">
				<p>&nbsp;</p>
			</div>

			<div class="mainbox" style="width: 100%">
				<table class="datalist fixwidth">
					<tr>
						<th>真实姓名</th>
						<th>手机号</th>
						<th>车牌号</th>
						<th>所属公司</th>
						<th>操作</th>
					</tr>
					<s:if test="pageWapSite.totalRowNum>0">
						<s:iterator value="driverList" var="um" status="index">
							<tr>
								<td><s:property value="#um.driverName" /></td>
								<td><s:property value="#um.phoneNumber" />
								</td>
								<td><s:property value="#um.plateNumber" />
								</td>
								<td><s:property value="#um.driverCompany" />
								</td>
								<td><s:if
										test="roleVsMenu.menuOperateType==1 || roleVsMenu.menuOperateType==2">
										<a href="#" onclick="update('${um.id }');">驳回认证</a>
									&nbsp;
											<a href="#"
											onclick="delOne('delDriver.do?menuId=${menuId}&driverInfo.id=${um.id }&pageNo=${pageNo }');">删除认证</a>
									</s:if></td>
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