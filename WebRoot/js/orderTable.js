//排序器
//lon：按照tabel的第几列排序
//orderflug：升降序标志
//lonType：排序数据类型
function compare(lon, orderflug, lonType) {
	return function compareTRs(oTR1, oTR2) { 
		//var sValue1 = oTR1.cells[lon].childNodes[1].textContent;
		//var sValue2 = oTR2.cells[lon].childNodes[1].textContent;
		var sValue1 = oTR1.cells[lon].innerHTML.replace(/<[^>]+>/g,"");
		var sValue2 = oTR2.cells[lon].innerHTML.replace(/<[^>]+>/g,"");
		sValue1 = sValue1.replace(/^\s+|\s+$/g,"");//去空格
		sValue2 = sValue2.replace(/^\s+|\s+$/g,"");//去空格
		if(lonType == "String") {//字符串类型
			if(orderflug == 0) {//升序
				return sValue1.localeCompare(sValue2);
			} else {
				return sValue2.localeCompare(sValue1);
			}
			
		} else if (lonType == "int") {//数字类型
			if(orderflug == 0) {//升序
				return sValue1 - sValue2;
			} else {
				return sValue2 - sValue1;
			}
		}
		
	}
}

/**
tableId，第几列，升降序标志，排序数据类型
*/
function order(tableId, lon, orderflug, lonType) {
	var oTable = document.getElementById(tableId);
	var oBody = oTable.tBodies[0];
	var colDataRows = oBody.rows;
	var aTRs = new Array;
	for (var i=0; i < colDataRows.length; i++) {
		aTRs[i] = colDataRows[i];
	}

	aTRs.sort(compare(lon, orderflug, lonType));

	var oFragment = document.createDocumentFragment();
	for (var i=0; i < aTRs.length; i++) {
		oFragment.appendChild(aTRs[i]);
	}
	oBody.appendChild(oFragment);
}