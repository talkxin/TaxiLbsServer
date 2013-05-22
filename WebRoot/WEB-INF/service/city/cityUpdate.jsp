<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改区域</title>
<link href="css/tabs.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<script type="text/javascript">
	//提交验证
	function submitForm() {
		var a1 = $("#newC").val();
		var a2 = $("#goNumber").val();
		var a3 = $("#extraNumber").val();
		var a4 = $("#gokmNumber").val();
		var a5 = $("#kmNumber").val();
		var a6 = $("#cityName").val();
		if (a1 != 1)
			return false;
		if (a6.indexOf(",") == -1) {
			$("#newError").html("请选择城市");
			return false;
		} else {
			$("#newError").html("");
		}
		if (a2 == null || a2 == '' || isNaN(a2)|| a2.length>4) {
			$("#goNumberError").html("请正确填写起步价");
			return false;
		} else {
			$("#goNumberError").html("");
		}
		if (a3 == null || a3 == '' || isNaN(a3) || a3.length>4) {
			$("#extraNumberError").html("请正确填写燃油附加费");
			return false;
		} else {
			$("#extraNumberError").html("");
		}
		if (a4 == null || a4 == '' || isNaN(a4)|| a4.length>4) {
			$("#gokmNumberError").html("请正确填写公里价格");
			return false;
		} else {
			$("#gokmNumberError").html("");
		}
		if (a5 == null || a5 == '' || isNaN(a5)|| a5.length>4) {
			$("#kmNumberError").html("请正确填写起步公里数");
			return false;
		} else {
			$("#kmNumberError").html("");
		}
		document.getElementById('form1').submit();
		return true;
	}
	function isNew() {
		var phone = $('#cityName').val();
		var companyName = $("#companyName").val();
		if (phone != null && phone != '') {
			if (companyName == null || companyName == '') {
				$.ajax({
					url : "isNewCity.do",
					type : "post",
					dataType : "json",
					data : 'cityTaxi.cityName=' + phone,
					success : function(newCity) {
						if (newCity == 1) {
							$('#newError').html('该区域已有信息');
							$("#newC").val("0");
						} else {
							$('#newError').html('');
							$('#companyNameError').html('');
							$("#newC").val("1");
						}
					}
				});
			} else {
				$.ajax({
					url : "isNewCity.do",
					type : "post",
					dataType : "json",
					data : 'cityTaxi.cityName=' + phone
							+ '&cityTaxi.companyName=' + companyName,
					success : function(newCity) {
						if (newCity == 1) {
							$('#newError').html('该区域已有信息');
							$('#companyNameError').html('该区域已存在该公司');
							$("#newC").val("0");
						} else {
							$('#newError').html('');
							$('#companyNameError').html('');
							$("#newC").val("1");
						}
					}
				});
			}
		}
	}
	function isNew2(obj) {
		var phone = $('#cityName').val();
		var companyName = $("#companyName").val();
		if (obj.value != null && obj.value != ''
				&& obj.value != $("#oldcompanyName").val()) {
			$.ajax({
				url : "isNewCity.do",
				type : "post",
				dataType : "json",
				data : 'cityTaxi.cityName=' + phone + '&cityTaxi.companyName='
						+ companyName,
				success : function(newCity) {
					if (newCity == 1) {
						$('#newError').html('该区域已有信息');
						$('#companyNameError').html('该区域已存在该公司');
						$("#newC").val("0");
					} else {
						$('#newError').html('');
						$('#companyNameError').html('');
						$("#newC").val("1");
					}
				}
			});
		}
	}
	function getCity(obj) {
		$("#oldProvince").remove();
		$("#oldCity").remove();
		$("#oldDistrict").remove();
		$("#newC").val("0");
		if (obj.value != null && obj.value != '') {
			var pid = 0;
			var html = "<option	value=''>请选择</option>";
			eval("pid=" + obj.value + ".pid");
			$
					.ajax({
						url : "getSysCityList.do",
						type : "get",
						dataType : "json",
						data : 'pid=' + pid,
						success : function(sysCity) {
							for ( var i = 0; i < sysCity.length; i++) {
								html += "<option value='{pid:"+sysCity[i].cityId+",name:\""+sysCity[i].cityName+"\"}'>"
										+ sysCity[i].cityName + "</option>";
							}
							$("#city").html(html);
						}
					});
		}
	}
	function getDistrict(obj) {
		$("#oldProvince").remove();
		$("#oldCity").remove();
		$("#oldDistrict").remove();
		if (obj.value != null && obj.value != '') {
			var pid = 0;
			var html = "<option	value=''>请选择</option>";
			eval("pid=" + obj.value + ".pid");
			var name = '';
			eval("name=" + $("#province").val() + ".name");
			eval("name+=','+" + obj.value + ".name");
			$("#cityName").val(name);
			isNew();
			$.ajax({
				url : "getSysDistrictList.do",
				type : "get",
				dataType : "json",
				data : 'pid=' + pid,
				success : function(sysDistrict) {
					for ( var i = 0; i < sysDistrict.length; i++) {
						html += "<option value='"+sysDistrict[i].disName+"' >"
								+ sysDistrict[i].disName + "</option>";
					}
					$("#district").html(html);
				}
			});
		} else {
			$("#cityName").val("");
			$("#newC").val("0");
		}
	}
	function isDistrict(obj) {
		$("#oldProvince").remove();
		$("#oldCity").remove();
		$("#oldDistrict").remove();
		if (obj.value != null && obj.value != '') {
			var name = $("#cityName").val();
			name += "," + obj.value;
			$("#cityName").val(name);
			isNew();
		} else {
			var name = $("#city").val();
			$("#cityName").val(name);
			isNew();
		}
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
			<li id="current"><a href="inCityTaxi.do?menuId=${menuId }"><span>区域管理</span>
			</a>
			</li>
			<li><a href="addsCity.do?menuId=${menuId }"><span>用户提交区域审核</span>
			</a>
			</li>
			<li><a href="#"><span>手动修改区域</span> </a>
			</li>
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
			<form id="form1" name="form1" action="saveUpdateCity.do"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="menuId" value="${menuId }" /><input
					type="hidden" name="seach[0]" value="${seach[0] }" /><input
					type="hidden" name="seach[1]" value="${seach[1] }" /><input
					type="hidden" name="seach[2]" value="${seach[2] }" /><input
					type="hidden" name="cityTaxi.cid" value="${cityTaxi.cid }" /> <input
					type="hidden" name="cityTaxi.struts" value="0" /> <input
					type="hidden" id="newC" value="1" /> <input id="cityName"
					type="hidden" name="cityTaxi.cityName"
					value="${cityTaxi.cityName }" />
				<h3>手动修改区域</h3>
				<ul class="memlist fixwidth">
					<li><em><span style="color: red">*</span>适应区域：</em> <select
						id="province" onchange="getCity(this)"><option
								id="oldProvince" style="display: block;">${rcity[0] }</option>
							<option value="">请选择</option>
							<s:iterator value="sysProvince" var="um" status="index">
								<option value="{pid:${um.proId },name:'${um.proName }'}">${um.proName
									}</option>
							</s:iterator>
					</select><select id="city" onchange="getDistrict(this)">
							<option id="oldCity" style="display: block;">${rcity[1]
								}</option>
							<option value="">请选择</option>
					</select><select id="district" onchange="isDistrict(this)">

							<option id="oldDistrict" style="display: block;">
								<s:if test="rcity[2]!=null">${rcity[2] }</s:if>
								<s:else>未选择</s:else>
							</option>

							<option value="">请选择</option>
					</select> <span id="newError" style="color: red">精确至市级单位</span></li>
					<li><em><span style="color: red">&nbsp;&nbsp;</span>适应公司:</em><input
						id="companyName" name="cityTaxi.companyName" maxlength="25"
						value="${cityTaxi.companyName }" onblur="isNew2(this)" /><span
						id="companyNameError" style="color: red">为空则适应所选区域</span> <input
						type="hidden" id="oldcompanyName" value="${cityTaxi.companyName }" />
					</li>
					<li><em><span style="color: red">*</span>起步价:</em><input
						id="goNumber" name="cityTaxi.goNumber" maxlength="11"
						value="${cityTaxi.goNumber }" /><span id="goNumberError"
						style="color: red"></span></li>
					<li><em><span style="color: red">*</span>燃油附加费：</em><input
						id="extraNumber" name="cityTaxi.extraNumber"
						value="${cityTaxi.extraNumber }" /><span id="extraNumberError"
						style="color: red"></span></li>
					<li><em><span style="color: red">*</span>每公里价格：</em><input
						id="gokmNumber" name="cityTaxi.gokmNumber"
						value="${cityTaxi.gokmNumber }" /><span id="gokmNumberError"
						style="color: red"></span></li>
					<li><em><span style="color: red">*</span>起步公里数：</em><input
						id="kmNumber" name="cityTaxi.kmNumber"
						value="${cityTaxi.kmNumber }" /><span id="kmNumberError"
						style="color: red"></span></li>
				</ul>

				<div class="opt">
					<a href="#" onclick="submitForm();"><span>提交</span> </a>
					<!-- a class="btn_blue" href="javascript:;"
						onclick="document.getElementById('btn-submit').click();"><span>提交</span>
					</a -->
					<input type="button" style="display: none" id="btn-submit"
						onclick="submitForm();" /> <a class="btn_blue"
						href="inCityTaxi.do?menuId=${menuId}&pageNo=${pageNo }&seach[0]=${seach[0]}&seach[1]=${seach[1]}&seach[2]=${seach[2]}"
						target="_self"><span>返回</span> </a>
				</div>

			</form>

		</div>
	</div>
</body>
</html>