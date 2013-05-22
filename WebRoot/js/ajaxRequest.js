/* 
* Function: Create a httpRequest object
*/ 
function HttpRequest() {
	this._httpRequest = null; 	//HttpRequest request object
	this._callBack = null; 			//Call back function
	this._domResult = true; 		//Result if dom object or text string
	this._requestData = null; 	//Request data
	this._requestResult = null; //HttpRequest result
	this._stateString = null; 	//Current request state string
	this._error = false; 				//Current if have error
	this._callBackPara = null; 	//Current callback function parama

	//internal method for get HttpRequestObject
	this.init = function() {
		//Judge if Not IE
		if(window.XMLHttpRequest) {
			this._httpRequest=new XMLHttpRequest();
			//Set request mime is text/xml
			if(this._httpRequest.overrideMimeType) {
				this._httpRequest.overrideMimeType('text/xml');
			}
		} else if(window.ActiveXObject) {
			try {
				this._httpRequest=new ActiveXObject("Msxml2.XMLHTTP");
			} catch(ex) {
				try {
					this._httpRequest=new ActiveXObject("Microsoft.XMLHTTP");
				} catch(ex) {
					this._setMessage(ex,true);
					return;
				}
			}
		}

		//Judge HttpRequest object create successful
		if(!this._httpRequest){
			this._setMessage("HttpRequest object create fail!Please try again......",true);
			return;
		} 
	}

	/* 
	* Function: Set the request header
	* namePar:request's header name
	* valuePar:request's header value
	*/
	this.doSetRequestHeader = function(namePar,valuePar) {
		if(this._error){
			return;
		}
		this._httpRequest.setRequestHeader(namePar,valuePar);
	}

	/* 
	* Function: Set the request data
	* dataPar:request's send data;
	*/
	this.doSetRequestData=function(dataPar){
		if(this._error){
			return;
		}
		this._requestData=dataPar;
	}

	/*
	*Function get RequestHttp Object
	*/
	this._getRequestObj=function(){
		if(this._error){
			return;
		}
		return this._httpRequest;
	}

	/*
	* Function:Set Callback function para
	*/
	this.doSetCallBack=function(callBack,paraData){
		this._callBack=(callBack)?callBack:null;
		this._callBackPara=(paraData)?paraData:null;
	};

	/*
	* Function: Get current stateString
	*/
	this.doGetState=function(){
		return this._stateString;
	}

	/*
	* Function: get current Error 
	*/
	this.doGetError=function(){
		return this._error;
	}

	/*
	*
	*/
	this.doCallBack=function(){
		this._callBack(this._requestResult,this._callBackPara);
	}
	
	/* 
	* Function: Send the request
	* urlPar: request's url path
	* [methodPar]:request's method
	* [domPar]: request's result is dom or string
	*/
	this.doSendResuest=function(urlPar,methodPar,obj,domPar,asyPar){
		if(obj._error) {
			return;
		}

		methodPar=((methodPar)?methodPar:"GET");
		asyPar=((asyPar)?asyPar:true);
		this._domResult=(domPar)?domPar:obj._domResult;

		try {
			var a=this._getRequestObj();

			a.onreadystatechange=function() {
				if(obj._error){
					return;
				}

				var readyStateTmp=a.readyState;

				if(readyStateTmp==0){
					obj._setMessage("No initialize");
				}else if(readyStateTmp==1){
					obj._setMessage("Reading......");
				}else if(readyStateTmp==2){
					obj._setMessage("Had read!");
				}else if(readyStateTmp==3){
					obj._setMessage("Turning each other...... ");
				}else if(readyStateTmp==4){
					var statusTmp=a.status;

					if(statusTmp==404){
						obj._setMessage("Not found request file!",true);
					}else if(statusTmp==200){
						obj._setMessage("Finished"); 

						if(this._domResult && window.XMLHttpRequest){
							obj._requestResult=a.responseXml;
						}else{
							obj._requestResult=a.responseText;
						}

						if(obj._callBack){
							obj.doCallBack();
						}
					} else {
						obj._setMessage("Unknow error!");
					}
				} else {
					obj._setMessage("Unknow error!");
				} 
			}

			a.open(methodPar,urlPar,asyPar);
			a.send(obj._requestData);
		} catch(ex) {
			obj._setMessage(ex,true);
		}
	}

	/*
	* Function: Deal exception error 
	* exPar:error string
	*/
	this._setMessage=function(exPar,mark) {
		this._stateString=exPar.toString();
		this._error=(mark)?mark:false;
	}
} 


function ajaxProcess(requestUrl,methods,callbackFunction,callbackFunctionParas) {
	var httpRequest=new HttpRequest();

	with(httpRequest){
		init();
		doSetCallBack(callbackFunction,callbackFunctionParas);
		doSendResuest(requestUrl,methods,httpRequest);
	}
}