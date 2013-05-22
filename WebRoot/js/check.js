//去掉字串左边的空格
function lTrim(str)
{
        if (str.charAt(0) == " ") {
                //如果字串左边第一个字符为空格
                str = str.slice(1);
                str = lTrim(str); //递归调用
        }
        return str;
}

//去掉字串右边的空格
function rTrim(str)
{
        var iLength;

        iLength = str.length;
        if (str.charAt(iLength - 1) == " ") {
                //如果字串右边第一个字符为空格
                str = str.slice(0, iLength - 1);//将空格从字串中去掉
                str = rTrim(str); //递归调用
        }
        return str;
}

//去掉字串两边的空格
function trim(str)
{
        return lTrim(rTrim(str));
}

//检查帐号的有效性
function IsValid(value){
	var str=new String(value);
	var NUM=new String("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_");
	for(var I=0;I<str.length;I++){
		if(NUM.indexOf(str.charAt(I))<0)
			return false;
	}
	return true;
}

//检查是否都是字母
function IsLetter(value){
	var str=new String(value);
	var NUM=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	for(var I=0;I<str.length;I++){
		if(NUM.indexOf(str.charAt(I))<0)
			return false;
	}
	return true;
}

//检查是否都是大写字母
function IsBigLetter(value){
	var str=new String(value);
	var NUM=new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	for(var I=0;I<str.length;I++){
		if(NUM.indexOf(str.charAt(I))<0)
			return false;
	}
	return true;
}

function selectall(objform,objname) {
        with (eval(objform)) {
            for (var i=0;i<elements.length;i++) {
                if (elements[i].name==objname) {
                    elements[i].checked = all.checked;
                }
            }
        }
    }

function selectPoint( vbigId )
{
	var i,j;
	var ob = form_info.paramStr;
	var len = NameArray.length;
	var oOption;

	j = ob.childNodes.length-1;
	for (i = 0; i < j; i++) {
		ob.removeChild(ob.children[0]);
	}

	oOption = document.createElement('OPTION')
	oOption.value = ''
	oOption.innerText = '===请选择==='
	oOption.selected = true;
	form_info.paramStr.appendChild(oOption);

	for (i = 0; i < len; i++) {
	    if (bidArray[i] == vbigId) {
		oOption = document.createElement('OPTION')
		oOption.value = sidArray[i];
		oOption.innerText = NameArray[i];
		form_info.paramStr.appendChild(oOption);
            }
	}
}

function chkmaxsms(vobj1,vobj2,vmax) {
    var str=eval(vobj1+'.value');
    var strlen=str.length;
    if(strlen<=vmax){
        eval(vobj2+'.value='+(vmax-strlen));
    }
    if(strlen>vmax) {
        eval(vobj2+'.value=0');
    }
}

//检查表单项
function checkform(vobj,f,msg) {
	  if (f=="1") {//检查是否为空
		if (eval(vobj+".value")=="") {
		  alert("请输入"+msg);
		  eval(vobj+".focus()");
                  return false;
		}
	  }
	  if (f=="2") {//检查是否是数字
		if (isNaN(eval(vobj+".value"))) {
                  alert(msg+"必须是数字");
                  eval(vobj+".focus()");
                  return false;
		}
	  }
          return true;
}

function isNumber(value){
	var str=new String(value);
	var NUM=new String("0123456789");
	for(var I=0;I<str.length;I++){
		if(NUM.indexOf(str.charAt(I))<0)
			return false;
	}
	return true;
}

function isMobile(mobile) {
	  if ( mobile.length!=11 ) {
		return false;
	  }
	  
	  if( !isNumber(mobile) ){
		return false;
	  }
      
      return true;
}



//取当天日期，格式：YYYY-MM-DD
function getToday() {
	var today = new Date();
	var y = today.getYear();
	var m = today.getMonth()+1;
	var d = today.getDate();
	if (m<10) {
		m = "0"+m;
	}
	if (d<10) {
		d = "0"+d;
	}
	return y+"-"+m+"-"+d;
}
function isInt(ob)
{
	var reg = /^-?([1-9]\d+|\d)$/;
 	return reg.test(ob.value);
}

//检查指令是否为大写大母
function IsdValid(value){
    var str=new String(value);

    var NUM=new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ*");//0123456789abcdefghijklmnopqrstuvwxyz
	for(i=0;i<str.length;i++){
        if(NUM.indexOf(str.charAt(i))<0) {        
            return false;
        }

	}
	return true;
}

function IsValidNum(value){
  var str=new String(value);
  var NUM=new String("0123456789");
  for(var I=0;I<str.length;I++){
    if(NUM.indexOf(str.charAt(I))<0)
      return false;
  }
  return true;
}

function checkValueLength(value) {
  var len = value.length;
  var real_len = 0;
  for (var i=0;i<len;i++){
    real_len++;
    if (value.charCodeAt(i)>255) {
      real_len++;
    }
  }
  return real_len;
}

//获取选定的对象的文本
function getSelectText(obj) {
	var a = eval(obj+".options["+obj+".selectedIndex].text");
	return a;
}


//检查日历文本格式是否是正确的时间格式
function checkTextTime1(timevalue) {//YYYY-MM-DD
	var year = time_array[0];
	var month = time_array[1];
	var day = time_array[2];
	if (year.length!=4 || !IsValidNum(year) || parseInt(year)<1000) {
		return false;
	}
	if (month.length!=2 || !IsValidNum(month) || parseInt(month)>12) {
		return false;
	}
	if (day.length!=2 || !IsValidNum(day) || parseInt(day)>31) {
		return false;
	}
	
	var day = new Date();
	day.setYear(parseInt(year));
	day.setMonth(parseInt(month)-1);
	day.setDate(parseInt(day));
	if (isNaN(day.getDay())) {
		return true;
	} else {
		return false;
	}

	
}


/*
function isEmail(value) 
{ 
	var email = value; 
　	var pattern = "/^([a-zA-Z0-9_-]) @([a-zA-Z0-9_-]) (\.[a-zA-Z0-9_-]) /"; 
　	flag = pattern.test(email); 
　	if(!flag) { 
	　	return false; 
　	} 
	return true; 
}


*/

function checkTextTime(sDate) {//YYYY-MM-DD
	if (sDate.length!=10) {
		return false;
	}
	var time_array = sDate.split("-");
	if (time_array.length!=3) {
		return false;
	}
	
	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31]
	var iaDate = new Array(3)
	var year, month, day

	if (arguments.length != 1) return false
	iaDate = sDate.toString().split("-")
	if (iaDate.length != 3) return false
	if (iaDate[1].length > 2 || iaDate[2].length > 2) return false
	if (isNaN(iaDate[0])||isNaN(iaDate[1])||isNaN(iaDate[2])) return false

	year = parseFloat(iaDate[0])
	month = parseFloat(iaDate[1])
	day=parseFloat(iaDate[2])
	
	if (year < 1900 || year > 2100) return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12){
//		alert('月份不合法') 
	 	return false
	}
	if (day < 1 || day > iaMonthDays[month - 1]){
//		alert('日期不合法') 
	 	return false
	}
	return true	
}

//校验是否是有效终端号码
function isValidTerminalId(value) {
    var str = new String(value);
    if (str == "") {
        return false;
    }
    if (str.length != 11) {
        return false;
    }
    if (str.substr(0, 2) != '13' && str.substr(0, 2) != '15' && str.substr(0, 2) != '18') {
        return false;
    }
    var NUM = new String("0123456789");
    for (var I = 0; I < str.length; I++) {
        if (NUM.indexOf(str.charAt(I)) < 0)
            return false;
    }
    return true;
}
//校验是否有效验证码
function isValidVerifyCode(value) {
    var str = new String(value);
    if (str == "") {
        return false;
    }
    if (str.length != 6) {
        return false;
    }
    var NUM = new String("0123456789");
    for (var I = 0; I < str.length; I++) {
        if (NUM.indexOf(str.charAt(I)) < 0)
            return false;
    }
    return true;
}

function chkmaxcharCN(obj1,obj2,maxLength) {
	var str = obj1.value;
    if (str.length>maxLength) {
       alert("信息内容长度已超过"+maxLength);
       obj2.value = "0";
       obj1.value = str.substring(0,maxLength);
       obj1.focus();
    } else {
       obj2.value = maxLength - str.length;
	}
}