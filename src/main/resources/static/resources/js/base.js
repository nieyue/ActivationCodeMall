/**
 * 修改原型
 */
	/*String.prototype.trim=function(){
		console.log(22)
		    return this.replace(/(^\s*)|(\s*$)/g,"");
		}*/
	
/**
 * *工具包
 */
var myUtils = {
	slice:function(obj){
		return Array.prototype.slice.call(obj);
	},
	trim:function(){
		return this.replace(/(^\s*)|(\s*$)/g,"");
	},
	/**
	 * 验证规则
	 */	
	userVerification:{
		username:/(^[\u4E00-\u9FA5]{2,16}$)|(^[a-zA-Z\/ ]{2,16}$)/,//中文或英文2-16位
		merDiscount:/^(0\.(0[1-9]{1}|[1-9]\d?)|1(\.0{1,2})?)$/,//0.01-1.00之间
		scale:/^[0]$|^(0\.(0[1-9]{1}|[1-9]\d?)|1(\.0{1,2})?)$/,//0-1.00之间 ，比例
		merPrice:/(^[+]?[1-9]\d*(\.\d{1,2})?$)|(^[+]?[0]{1}(\.\d{1,2})?$)/,//商品价格正则,大于0最多两位小数
		catNum:/^\+?[1-9][0-9]*$/,// 非零正整数
		postNum:/^\+?[0-9][0-9]*$/,// 含零正整数
		nicename: /^[^\s]{1,10}$/,// 1-10位,不包含空格。
		signature:/^[^\s]{1,15}$/,// 1-15位,不包含空格.
		email:/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/, // 邮箱
		phone:/^1[0-9]{10}$/, // 手机
		identity:/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/, // 15位和18位身份证号码
		password:/^[0-9_a-zA-Z]{6,20}$/ // 数字、字母、下划线，6-20长度
	},
	/**
	 * 执行js代码
	 * param o js文件或js地址或js代码
	 * 
	 */
	executeJS:function(o){
		if(o==''||o==null||o==undefined){
			return;
		}
		if(o.indexOf("<script")>-1){
		var d=document.createElement("div");
		d.innerHTML=o;
		var body =document.querySelector("body");
		for (var i = 0; i < d.childNodes.length; i++) {
				var oldScript=d.childNodes[i];
				d.removeChild(oldScript);
		var newScript = document.createElement('script');
		if(oldScript.getAttribute("src")){
		newScript.src=oldScript.src;
		}
		newScript.innerHTML = oldScript.innerHTML;
		if(oldScript.innerHTML){
		d.insertBefore(newScript,d.childNodes[0]);
		}else{
		d.appendChild(newScript);	
		}	
		};
		body.appendChild(d);
		}else if(o.indexOf(".js")>-1){
			var body =document.querySelector("body");
			var d=document.createElement("div");
			var newScript = document.createElement('script');
			newScript.src=o;
			d.appendChild(newScript);	
			body.appendChild(d);
		}else{
			try{
			eval(o);
		}catch (e){
			throw new Error("js解析错误");
		}
		}
	},
	/**
	 * 是否微信浏览器
	 * return true 是
	 * return false 否
	 */
	isWeiXinBrowse:function(){
		var wx=navigator.userAgent.match(/MicroMessenger/);
		if(wx){
		if(wx[0]==='MicroMessenger'){
			return true;
		}
		}
		return false;
	},
	/**
	 * 检查微信浏览器版本
	 * return 
	 */
	weiXinBrowseVersion:function(){
		return navigator.userAgent.match(/MicroMessenger\/([\d\.]+)/)[1];
	},
	/**
	 * 微信支付
	 * 
	 */
	wxPay:function(appId, timeStamp, nonceStr, package, signType, paySign,callback){
		wx.chooseWXPay({
		        appId:appId, 
		        timestamp:timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
		        nonceStr:nonceStr, // 支付签名随机串，不长于 32 位
		        package:package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
		        signType:signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
		        paySign:paySign, // 支付签名
		        complete: function (res) {
		           if(res.errMsg == "chooseWXPay:ok" ) {
		        	  // alert(res.errMsg)
		        	   myUtils.myLoadingToast("支付成功", null);
		        	   callback();
		        	//location.replace("/wx/index")
		           }else if(res.errMsg == "chooseWXPay:cancel"){
		        	   //alert(res.errMsg)
		        	   myUtils.myLoadingToast("取消支付", null);
		           }else if(res.errMsg == "chooseWXPay:fail"){
		        	  // alert(res.errMsg)
		        	   myUtils.myLoadingToast("支付失败", null);
		           }    
		        }
			});
	},
	/**
	 * 设置域名
	 * 
	 */
	getDomain:function(){
		//return "http://advertiseserver.yayao8.com";
		return "http://"+location.host;
	},
	/**
	 * 如果没选择店铺就404
	 * 
	 */
	sellerNotExistence:function(){
		if(myUtils.GetQueryString("seller_id")==null||myUtils.GetQueryString("seller_id")==''){
			if(location.href.indexOf("404")==-1){
				location.replace("/mall/mobile/404");
			}
		};
	},
	/**
	 * 旋转180度
	 * clickObj 点击的对象元素属性
	 * rotateObj 旋转的对象,null为自身
	 */
	myClickRotate:function(clickObj,rotateObj){
		var isClick=false;
    	$(clickObj).off("click").on("click",function(){
    		var $this=$(this).next(rotateObj);
    		if(rotateObj==null){
    			$this=$(this);
    		}
    	if(isClick){
    		$this.css('transform','rotate(0deg)');
			  isClick=false;
    	}else{
    		$this.css('transform','rotate(180deg)');
			  isClick=true;
    	}
    	});
	},
	/**
	 * 刚刚/分钟前/小时前/天前/周前/月前/年前
	 * dateTimeStamp :时间戳
	 */
	getDateDiff:function(dateTimeStamp){
		var minute = 1000 * 60;
		var hour = minute * 60;
		var day = hour * 24;
		var halfamonth = day * 15;
		var month = day * 30;
		var year = day * 365;
		function dateDiff(dateTimeStamp){
		var now = new Date().getTime();
		var diffValue = now - dateTimeStamp;
		if(diffValue < 0){
		 //若日期不符则弹出窗口告之
		 //alert("结束日期不能小于开始日期！");
		 }
		var yearC =diffValue/year;
		var monthC =diffValue/month;
		var weekC =diffValue/(7*day);
		var dayC =diffValue/day;
		var hourC =diffValue/hour;
		var minC =diffValue/minute;
		if(yearC>=1){
		 result=parseInt(yearC) + "年前";
		 }else if(monthC>=1){
		 result= parseInt(monthC) + "月前";
		 }
		 else if(weekC>=1){
		 result= parseInt(weekC) + "周前";
		 }
		 else if(dayC>=1){
		 result= parseInt(dayC) +"天前";
		 }
		 else if(hourC>=1){
		 result= parseInt(hourC) +"小时前";
		 }
		 else if(minC>=1){
		 result= parseInt(minC) +"分钟前";
		 }else
		 result="刚刚";
		return result;
		}
		return dateDiff(new Date(dateTimeStamp));
	},
	/**
	 * 时间戳转yyyy-MM-dd hh:mm:ss
	 * 
	 */
	timeStampToDate:function(timeStamp){
		var date = new Date(timeStamp);
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate() + ' ';
		h = date.getHours() + ':';
		m = (date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes()) + ':';
		s = (date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds()); 
	return Y+M+D+h+m+s; 
	},
	/**
	 * 时间戳转yyyy-MM-dd
	 * 
	 */
	timeStampToDayDate:function(timeStamp){
		var date = new Date(timeStamp);
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate();
	return Y+M+D; 
	},
	/**
	 * 时间戳转MM-dd
	 * 
	 */
	timeStampToSimpleDate:function(timeStamp){
		var date = new Date(timeStamp);
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate() + ' ';
	return M+D; 
	},
   /**
   * 获取当前当日00:00:00的时间
   * 
   */
  todayStartTime:function(){  
		var date=new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	},
	/**
   * 获取当前当日23:59:59的时间
   * 
   */
  todayEndTime:function(){  
		var date=new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	},
	/**
   * 获取n天前的00:00:00的时间
   * 
   */
  beforeDayToTodayTime:function(n){  
		var date=new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		if(isNaN(n)||n<=0){
			n=0;
		}
		var ndate=date.getTime()-1000*60*60*24*n;
		return new Date(ndate);
	},
	/**
   * 获取n天后的最后23:59:59的时间
   * 
   */
  todayToNDayEndTime:function(n){  
		var date=new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		if(isNaN(n)||n<=0){
			n=0;
		}
		var ndate=date.getTime()+1000*60*60*24*n;
		return new Date(ndate);
	},
	  /**
	   * 获取当前时间到当日23:59:59的时间差
	   * 单位 秒
	   * 
	   */
	currentToEndTime:function (){  
			var enddate =new Date();
			var date=new Date();
			enddate.setHours(23);
			enddate.setMinutes(59);
			enddate.setSeconds(59);
		 	var miao = (enddate.getTime()-date.getTime())/1000;
			return miao;
		},
	/**
	 * 获取当前url的参数
	 * 
	 */
	GetQueryString: function (name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  decodeURIComponent(r[2]); return null;
	     //if(r!=null)return  unescape(r[2]); return null;
	},
	/**
	 * cookie
	 * 
	 */
	// 写cookie
	setCookie:function (name,value,expires)
	{
	var exp = new Date();
	exp.setTime(exp.getTime() + expires*1000);
	document.cookie = name + "="+ escape (value) + ";path=/;expires=" + exp.toGMTString();
	},
	// 读取cookie
	getCookie:function (name)
	{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
	},
	// 删除cookie
	delCookie:function(name)
	{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=myUtils.getCookie(name);
	if(cval!=null)
	document.cookie= name + "="+cval+";path=/;expires="+exp.toGMTString();
	},
	
	/**
	 * 表单验证
	 * 
	 */
	registerFormValid:function(userName,password,rePassword,validBtn,validCode){
		
		// 账号
		if(typeof userName=='object'&&userName!=null){
      	$(userName.userNameValue).on("change",function(){
      		$(userName.userNameError).text("");
      		var  userNameInfo=$(userName.userNameValue).val().trim();
			if(!userName.verification.test(userNameInfo)){
      			$(userName.userNameError).text(userName.userNameErrorValue);
      			return ;
      		}
      		$.ajax({
      			type:"GET",
      			dataType:"json",
      			url:userName.userNameURL+"?account_name="+userNameInfo,
      			success:function(data){
      				console.log(data)
      				if(data=="用户不存在"||data=="商户不存在"){
      					$(userName.userNameError).text("");
      				}else{
      			$(userName.userNameError).text(data);
      		}
      	},
      	error:function(responseText){
      		console.log(responseText)
      	}
      		});
      	});
		}
      	// 密码
		if(typeof password=='object'&&password!=null){
      	$(password.userPasswordValue).on("change",function(){
      		var userPasswordInfo=$(password.userPasswordValue).val().trim();
      		if(!myUtils.userVerification.password.test(userPasswordInfo)){
      			$(password.userPasswordError).text(password.userPasswordErrorValue);
      		}else{
      			$(password.userPasswordError).text("");
      		}
      	});
		}
      	// 重复密码
		if(typeof rePassword=='object'&&rePassword!=null){
      	$(rePassword.userRePasswordValue).on("change",function(){
      		var reUserPasswordInfo=$(rePassword.userRePasswordValue).val().trim();
      		var userPasswordInfo=$(password.userPasswordValue).val().trim();
      		if(reUserPasswordInfo===userPasswordInfo){
      			$(rePassword.userRePasswordError).text("");
      		}else{
      			$(rePassword.userRePasswordError).text(rePassword.userRePasswordErrorValue);
      		}
      	});
		}
		// 请求服务器发送给邮箱验证码
      	$(validBtn).click(function(){
      		var  userNameInfo=$(userName.userNameValue).val().trim();
      		var  userNameInfoError=$(userName.userNameError).text().trim();
      		if(userNameInfoError){
      			return;
      		}
			if(!userName.verification.test(userNameInfo)){
      			$(validCode.userNameError).text(userName.userNameErrorValue);
      			return ;
      		}
			
      		$.get(validCode.userValidCodeSendURL,
      				{
      				account_name:userNameInfo
      				},
      				function(data){
      		if(data=="200"){
      		var timer=60;
      		var setinterval=setInterval(function(){
      		if(timer==0){
      			clearInterval(setinterval);
      			timer=60;
      			$(validBtn).val("获取验证码").removeAttr("disabled");
      		}else{
      			timer--;
      			$(validBtn).attr("disabled",true);
      			$(validBtn).val(timer+"s重新发送");
      			
      			
      		}
      		},1000);
      		}
      		});
      	});
		// 验证码
		if(typeof validCode=='object'&&validCode!=null){
      	$(validCode.userValidCodeValue).on("change",function(){
      		var  userNameInfo=$(userName.userNameValue).val().trim();
			if(!userName.verification.test(userNameInfo)){
      			$(validCode.userValidCodeError).text(userName.userNameErrorValue);
      			return ;
      		}
			if($(validCode.userValidCodeValue).val()==null||$(validCode.userValidCodeValue).val().trim()==""||$(validCode.userValidCodeValue).val()==undefined){
				return ;
			}
			$(validCode.userValidCodeError).text("");
      		$.get(validCode.userValidCodeCheckedURL,{
      			validCode:$(validCode.userValidCodeValue).val().trim()
      		},
      			function(data){
      			console.log(data)
      				if(data=='200'){
      				$(validCode.userValidCodeError).text("");
      				}else{
      					$(validCode.userValidCodeError).text(data);
      		}
      				
      	});
      	});
		}
	},
	
	/**
	 * 修改一行的组件 clickDiv:点击的对象 modalTitle:模型的标题 userInfoOne:数据库实际的值
	 * userInfoOneModal:修改的值 errorMessage:错误信息
	 */
	updateOne:function(clickDiv,modalTitle,userInfoOne,userInfoOneModal,errorMessage){
		$(clickDiv).click(function(){
				// 判断是否能够点击
			if($(userInfoOne).text()!=userData.userInit[userInfoOne.slice(1)]){
				if(userInfoOne.indexOf("Email")>-1||userInfoOne.indexOf("Phone")>-1||userInfoOne.indexOf("Identity")>-1){
  		  		return;
  		  		}
			}
				$("#errorInfo").text('');
	  			$('#mymodal').click();
	  			console.log(userInfoOneModal.slice(1))
	  			$('#myModalLabel').text(modalTitle);
	  			$('.modal-body').html("<input type='text' class='form-control' placeholder='"+$(userInfoOne).text()+"' id='"+userInfoOneModal.slice(1)+"'/>");
	  			$('#submit').unbind('click');
	  		$('#submit').click(function(){
	  			var uiom=$(userInfoOneModal).val();
	  			if(userInfoOne.indexOf('NiceName')>-1){
	  				if(!myUtils.userVerification.nicename.test(uiom)){
		  				$("#errorInfo").text(errorMessage);
		  				return;
		  			}
	  			}else if(userInfoOne.indexOf('Signature')>-1){
	  				if(!myUtils.userVerification.signature.test(uiom)){
		  				$("#errorInfo").text(errorMessage);
		  				return;
		  			}
  			}else if(userInfoOne.indexOf('Email')>-1){
  				if(!myUtils.userVerification.email.test(uiom)){
	  				$("#errorInfo").text(errorMessage);
	  				return;
	  			}
	  		}else if(userInfoOne.indexOf('Phone')>-1){
	  			if(!myUtils.userVerification.phone.test(uiom)){
	  				$("#errorInfo").text(errorMessage);
	  				return;
	  			}
	  		}else if(userInfoOne.indexOf('Identity')>-1){
	  			if(!myUtils.userVerification.identity.test(uiom)){
	  				$("#errorInfo").text(errorMessage);
	  				return;
	  			}
	  		}
	  		$.post("/user/updateUserInfo",{
	  			user_id:myUtils.getCookie("user_id"),
	  			user_info_one:userInfoOne,
	  			change_value:uiom
	  		},
	  		function(){
	  			$(userInfoOne).text(uiom);
	  			$('#closeModal').click();
	  			myUtils.myLoadingToast("修改成功");
	  			//重新加载新数据
	  			$.get("/user/loadUser",{user_id:myUtils.getCookie("user_id")},function(data){
	  				sessionStorage.setItem("user",encode64(JSON.stringify(data)));
	  			});
	  		});	
	  		});
	  		});
	},
	/**
	 * 单文件上传组件
	 * options:输入项
	 * options.inputfile 文件元素
	 * options.ajaxObj 数组对象1，formData{key,value} 2,url 3,success 4,error
	 * options.dragFn 拖拽的对象
	 */
	fileUpload:function(options){
		var initPhotoExt=options.photoExt||[".jpg",".png",".gif",".apk"];
		var isPhotoExt=false;
		if(!options&&typeof options!='object' ){
			myUtils.myLoadingToast("操作失败",null);
			return;
		}
		var file=options.inputfile.get(0);
		//console.log(file.files)
		  photoExt=file.value.substr(file.value.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
		// 判断格式
		  for (var i = 0; i < initPhotoExt.length; i++) {
		  if(photoExt==initPhotoExt[i])	{
			  isPhotoExt=true;
		  }
		  }
		  if(!isPhotoExt){
			  myUtils.myLoadingToast("请上传后缀名为"+initPhotoExt.join("").replace(/\./g,"/")+"的照片!");
		      return false;
		  }
		 /* if(photoExt!='.jpg'&&photoExt!='.png'&&photoExt!='.gif'&&photoExt!='.apk'){
			  myUtils.myLoadingToast("请上传后缀名为jpg/png/gif的照片!");
		      return false;
		  }*/
		  var fileSize = 0;
		  var isIE = /msie/i.test(navigator.userAgent) && !window.opera;            
		  if (isIE && !file.files) {          
		       var filePath = file.value;            
		       var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
		       var file = fileSystem.GetFile (filePath);               
		       fileSize = file.Size;         
		  }else {  
		       fileSize = file.files[0].size;     
		  } 
		  fileSize=Math.round(fileSize/1024*100)/100; // 单位为KB
		  if((photoExt=='.apk'&&fileSize>30000)||(photoExt!='.apk'&&fileSize>200)){
			  myUtils.myLoadingToast("图片大小为"+fileSize+"KB，超过最大尺寸为200KB，请重新上传!");
		      return false;
		  }		  
	    	if (file.files && file.files[0])  
	    	 {
	         var reader = new FileReader(); 
			 reader.readAsDataURL(file.files[0]);
	      	reader.onload = function(e){
	      		if(typeof options.ajaxObj!='object'){
	      		myUtils.myLoadingToast("上传失败",null);
	      		return;
		      }

				
			if(options.proportion){//是否有宽高比
			 var img = new Image;
            img.src = reader.result;
            img.onload = function () {
                var width = img.width;
                var height = img.height;
               if((width/height).toFixed(2)!=(options.proportion).toFixed(2)){
				   myUtils.myLoadingToast("图片宽高比"+(width/height).toFixed(2)+"，应为"+options.proportion);
			   }else{
				   myajax();	
			   }

            };
		}else{
		myajax();	
		}
				function myajax(){
	      		myUtils.myPrevToast("上传中",function(){
	      			var fd=new FormData();
	      			if(typeof options.ajaxObj.formData=='object'){
	      			for (var i = 0; i < options.ajaxObj.formData.length; i++) {
	      				fd.append(options.ajaxObj.formData[i].key,options.ajaxObj.formData[i].value);
	      			}
	      			}
			                $.ajax({
			                  url:options.ajaxObj.url,
			                  type:"POST",
			                  data:fd,
			                  timeout:30000,
			                  enctype:'multipart/form-data',
			                  processData:false,// 告诉jQuery不要去处理发送的数据
			                  contentType:false, // 告诉jQuery不要去设置Content-Type请求头
			                  success:function(src){// 获取最新图片更新
			                	  if(typeof options.ajaxObj.success=='function'){
			                		  options.ajaxObj.success(src);
			      	      		}
			                  },
			                  error:function(d){
			                	  if(typeof options.ajaxObj.error=='function'){
			                		  options.ajaxObj.error();
				      	      		}
			                	  console.log(d)
			                    myUtils.myPrevToast("上传失败",null,"remove");
			                  }
			                });
			                },"add");
	      				
	      		if(typeof options.dragFn=='function'){
	      			options.dragFn(e);
	      		}
				}
	    	}
	      
	      }else{
	      	myUtils.myPrevToast("浏览器不支持",null,"add");
	      	setTimeout(function(){
	      	myUtils.myPrevToast("浏览器不支持",null,"remove");
	      	},1000);
	    	//$(imgelement).attr("src",file.value);
	      }
			},
	/**
	 * 自动a标签滑过透明
	 */
	ahover : function() {
		for (var int = 0; int < document.getElementsByTagName('a').length; int++) {
			// var divthis=document.getElementsByTagName('div')[int];
			// console.log(divthis)
			(function(int) {
				document.getElementsByTagName('a')[int].addEventListener(
						'mouseover', function aaa() {
							this.style.opacity = 0.5;
						});
				document.getElementsByTagName('a')[int].addEventListener(
						'mouseout', function aaa() {
							this.style.opacity = 1;
						});
			})(int);
		}
	},
	/**
	 * 全选/全不选
	 */
	changeAllChecked: function(elementid,cName) {
		
		$(document).on('click',elementid,function(){
			var checkboxs = $(cName);
			//console.log(checkboxs.length)
			//console.log($("body").find("*").children(cName));
			//console.log($(this).prop("checked"))
			//for ( var i = 0; i < checkboxs.length; i++) {
		    checkboxs.prop("checked",$(this).prop("checked"));
		 //}  
		});
	},
	/**
	 * 弹性滑动
	 */
	elasticSlide:function(backgroundElement ,contentElement,height){
		var startY,endY,moveY,speed,startTime,endTime,moveTime;
			$(backgroundElement).on(myTouchEvents.touchstart,function(event){
				//event.stopPropagation();
				speed=0;
				startTime=new Date().getTime();
				// event.preventDefault();
				// console.log(event.originalEvent.touches[0].pageX);
	           // console.log(event.originalEvent.targetTouches);
	           // console.log(event.originalEvent.changedTouches);
				startY=event.pageY||event.originalEvent.targetTouches[0].pageY;
				thismove();
			});
			function thismove(){
		$(backgroundElement).on(myTouchEvents.touchmove,function(event){
			//event.stopPropagation();
			endY=event.pageY||event.originalEvent.targetTouches[0].pageY;
			if(endY-startY<height&&endY-startY>=0){
				if(myUtils.isScrollTop()){
					event.preventDefault();
					$(".myScrollHide").removeClass("hide");
					$(".myScrollHide").eq(0).css("margin-left",(parseFloat($("body").css("width"))-parseFloat($(".myScrollHide").eq(0).css("width")))/2+"px");
					
					// if($(".myScrollHide").length<=0){
					// $(contentElement).before("<div class='text-center myScrollHide ' style='color:white'>雅耀（湖南）科技有限公司提供技术支持</div>");
					// }
					$(contentElement).css("transform","translateY("+(endY-startY)+"px)");
				}else {
					
					$(backgroundElement).unbind(myTouchEvents.touchmove);
					
				}
			}
			else if(endY-startY<0&&endY-startY>-height){
				console.log(myUtils.isScrollBottom(contentElement))
				if(myUtils.isScrollBottom(contentElement)){
					event.preventDefault();
					$(".myScrollHide").removeClass("hide");
					$(".myScrollHide").eq(1).css("margin-left",(parseFloat($("body").css("width"))-parseFloat($(".myScrollHide").eq(1).css("width")))/2+"px");
				$(contentElement).css("transform","translateY("+(endY-startY)+"px)");
				}else{
					
					$(backgroundElement).unbind(myTouchEvents.touchmove);
					
				
				}
			}
		});
			//}
		$(backgroundElement).on(myTouchEvents.touchend,function(event){
			// event.preventDefault();
			event.stopPropagation();
			// endTime=new Date().getTime();
			// moveTime=endTime-startTime;
			// moveY=endY-startY;
			// speed=moveY/moveTime;
			// console.log(moveTime);
			// var backSpeed=speed;//返回每次速度
			// var backTime=1;//返回每次耗时
			// var backTotalTime=moveTime;//返回剩余时间
			// var a=speed/moveTime;//加速度
			// var backMoveY=moveY;//返回剩余距离
			// var isTop=true;//是否顶部滑动
			// if(backMoveY<0){
			// 	isTop=false;
			// 	}
			// var s=setInterval(function(){
			// 	$(contentElement).css("transform","translateY("+backMoveY+"px)");
			// 	 backSpeed=backSpeed+a;//速度加加速度
			// 	 backMoveY=backMoveY-backSpeed*backTime;//剩余返回距离
			// 	if(isTop&&backMoveY<=0||!isTop&&backMoveY>=0){//到位
			// 		clearInterval(s);
			// 	}
				
			// },0.001);
			$(contentElement).css("transform","translateY(0)");
			$(".myScrollHide").addClass("hide");
			$(backgroundElement).unbind(myTouchEvents.touchmove);
			$(backgroundElement).unbind(myTouchEvents.touchend);
		
		});
		}
	},
	/**
	 * 判断是否滑动到顶部
	 */
	isScrollTop:function(){
			var isscrolltop=false;
			var scrollTop = $(window).scrollTop();
			if(scrollTop == 0){
				isscrolltop=true;
			}
			return isscrolltop;
	},
	/**
	 * 判断是否滑动到底部
	 */
	isScrollBottom:function(element){
		var isscrollbottom=false;
	      	var scrollTop = $(window).scrollTop();
	      	var scrollHeight = $(document).height();
	      	var elementHeight = $(element).height();
	      	var windowHeight = $(window).height();
	      	if((scrollTop + windowHeight >= scrollHeight-20)||(elementHeight<=windowHeight)){
	      		isscrollbottom=true;
	      		// console.log((scrollTop + windowHeight ==
				// scrollHeight)||(elementHeight<=windowHeight))
	      	}
	      	return isscrollbottom;
},
/**
 * 提示
 */
myTipToast : function(imgUrl ) {
	$("body")
	.append(
			"<div id='tipToastWarp' style='background-color:#000;position:fixed;width:100%;height:100%;top:0;left:0;opacity:0.9; z-index:999999999'>"
			+"<img style='width:100%' src='"+imgUrl+"'/></div>"
			);
	$("#tipToastWarp").click(function(){
		$("#tipToastWarp").remove();
	});

},
	/**
	 * 实现慢事件执行的toast
	 */
	myPrevToast : function(value,fn,motion) {
		// 如果存在，remove
		if(document.querySelector("#prevToastWarp")){
			document.querySelector("#prevToastValue").innerText=value;
			if(motion=="add"){
				$("#prevToastWarp").fadeIn();
				$("#prevToastWarp").attr("display","block");
			}else if(motion=="remove"){
				setTimeout(function() {
					$("#prevToastWarp").fadeOut('slow');
					$("#prevToastWarp").attr("display","none");
					}, 1000);
			}
			if(typeof fn=='function'){
				fn();
			}
			return;
		}
		$("body")
				.append(
						"<div id='prevToastWarp' style='display:none;position:fixed;width:100%;height:100%;top:0;left:0; z-index:999999999'><div id='prevToast' style='color:#fff;background-color:#000;text-align:center;line-height:30px;border:1px solid black;border-radius:5px;min-height:30px;margin:-15px -50px;top:50%;left:50%;position:fixed;'><canvas id='prevloading'  height='30px' width='30px' style='display:inline-block;margin-bottom:-10px;' >您的浏览器不支持html5</canvas>"
						+"<span id='prevToastValue'>"+ value +"</span>&nbsp;&nbsp; </div></div>");
		if(typeof fn=='function'){
			fn();
		}
	},
	/**
	 * 事件快速完成的toast
	 */
	myLoadingToast : function(value, fn) {
		console.log(value.length*4)
		$("body")
				.append(
						"<div id='loadingToast' style='display:none;color:#fff;background-color:black;text-align:center;line-height:30px;border:1px solid black;border-radius:5px;min-height:30px;max-width:200px;padding:0 10px;margin:-5px -"+value.length*7+"px;top:50%;left:50%;position:fixed;z-index:999999999'>"
						+ value + "</div>");
		$("#loadingToast").fadeIn();
		setTimeout(function() {
			$("#loadingToast").fadeOut('slow');
			$("#loadingToast").remove();
			if(typeof fn=='function'){
				fn();
			}
			}, 1000);
	},
	/**
	 * 底部加载toast
	 */
	myFootLoadingToast : function(position,bottom, fn,motion) {
		if(!position){
			position="fixed";
		}
		if(typeof bottom!='number'||isNaN(bottom)){
			bottom=0;
		}
		// 如果存在，remove
		if(document.querySelector("#footToast")){
			if(motion=="add"){
				$("#footToast").css("bottom",bottom);
				$("#footToast").fadeIn();
				$("#footToast").attr("display","block");
			}else if(motion=="remove"){
				setTimeout(function() {
					$("#footToast").fadeOut('slow');
					$("#footToast").attr("display","none");
					}, 1000);
			}
			if(typeof fn=='function'){
				fn();
			}
			return;
		}
		var appendOrAfter="append";
		if(position=="relative"){
			appendOrAfter="after";
		}
		$("body")[appendOrAfter](
						"<div id='footToast' style='display:none;color:#fff;background-color:#ccc;text-align:center;line-height:30px;border:0px solid black;min-height:30px;width:100%;bottom:"+bottom+";left:0;position:"+position+";'><canvas id='bottomloading'  height='30px' width='30px' style='display:inline-block;margin-bottom:-10px;' >您的浏览器不支持html5</canvas><span id='footToastValue'>正在努力加载中...</span></div>");
		
			if(typeof fn=='function'){
				fn();
			}
			
	},
	/**
	 * loading小图片
	 */
	loading:function (canvas,options){
	      this.canvas = canvas;
	      if(options){
	        this.loading.radius = options.radius||12;
	        this.loading.circleLineWidth = options.circleLineWidth||4;
	        this.loading.circleColor = options.circleColor||'#00db00';
	        this.loading.moveArcColor = options.moveArcColor||'#a6ffa6';
	        this.loading.__loading=options.__loading!=null?options.__loading!=true?false:true:true;
	      }else{     
	        this.loading.radius = 12;
	        this.loading.circelLineWidth = 4;
	        this.loading.circleColor = '#00db00';
	        this.loading.moveArcColor = '#a6ffa6';
	        this.loading.__loading=true;
	      }
	      
	       function show(myutil){
	        var canvas = myutil.canvas;
	        if(!canvas)return;
	        if(!canvas.getContext)return;
	        if(canvas.__loading)return;
	        canvas.__loading = myutil.loading;
	        var ctx = canvas.getContext('2d');
	        var radius = myutil.loading.radius;     
	        var me = myutil.loading;
	        var rotatorAngle = Math.PI*1.5;
	        var step = Math.PI/6;
	        canvas.loadingInterval = setInterval(function(){
	          ctx.clearRect(0,0,canvas.width,canvas.height);        
	          var lineWidth = me.circleLineWidth;
	          var center = {x:canvas.width/2 ,y:canvas.height/2};         
	          ctx.beginPath();
	          ctx.lineWidth = lineWidth;
	          ctx.strokeStyle = me.circleColor;
	          ctx.arc(center.x,center.y,radius,0,Math.PI*2);
	          ctx.closePath();
	          ctx.stroke();
	          // 在圆圈上面画小圆
	          ctx.beginPath();
	          ctx.strokeStyle = me.moveArcColor;
	          ctx.arc(center.x,center.y,radius,rotatorAngle,rotatorAngle+Math.PI*.45);
	          ctx.stroke();
	          rotatorAngle+=step;
	          
	        },66);
	      }
	    function hide(myutil){
	        var canvas=myutil.canvas;
	        canvas.__loading = false;
	        if(canvas.loadingInterval){
	          window.clearInterval(canvas.loadingInterval);
	        }
	        var ctx = canvas.getContext('2d');
	        if(ctx)ctx.clearRect(0,0,canvas.width,canvas.height);
	      }
	      if(this.loading.__loading){
	        show(this);
	      }else{
	      hide(this);
	    }
	      },
	/**
	 * 自定义confirm
	 */
	myConfirm : function(value,fn) {
		$("body")
		.append(
				"<div id='confirmDiv' style='position:fixed;width:100%;height:100%;background-color:#ccc;opacity:0.5;left:0;top:0;'></div><div id='confirm' style='z-index:9999;color:#fff;background-color:black;text-align:center;line-height:30px;border:1px solid black;border-radius:5px;height:200px;width:200px;margin:-100px -100px;top:50%;left:50%;position:fixed;font-size:20px;'>"
				+ "<div class='glyphicon glyphicon-exclamation-sign' style='text-align:center;width:50%;height:50%;font-size:66px;margin-top:10px;'></div><div style='position:absolute;top:100px;width:100%;text-align:center;'>"+value+"</div><div class='btn btn-primary' style='position:absolute;left:15px;bottom:15px;width:80px;' id='confirmYes'>确定</div><div class='btn btn-default' style='position:absolute;right:15px;bottom:15px;width:80px;' id='confirmNo'>取消</div></div>");
	$('#confirmYes').click(function(){
		$('#confirmDiv').remove();
		$('#confirm').remove();
		if(typeof fn=='function'){
			fn();
		}
	});
	$('#confirmNo').click(function(){
		$('#confirmDiv').remove();
		$('#confirm').remove();
		
	});
	},
	/**
	 * 自定义template
	 */
	myTemplate : function(value) {
		var myTemplateWidth= 330;
		var myTemplateMarginWidth= 165;
		if(document.querySelector("html").offsetWidth>640){
			myTemplateWidth= 860;
			myTemplateMarginWidth= 430;
		}
		$("body")
		.append(
				"<div id='myTemplateDiv' style='position:fixed;width:100%;height:100%;background-color:#ccc;opacity:0.5;left:0;top:0;z-index:9998;'></div><div id='myTemplate' style='z-index:9999;color:#000;background-color:#fff;text-align:center;line-height:30px;border:1px solid #fff;border-radius:5px;height:300px;width:"+myTemplateWidth+"px;margin:-100px -"+myTemplateMarginWidth+"px;top:50%;left:50%;position:fixed;font-size:20px;'>"
				+ "<div style='position:absolute;top:20px;width:100%;text-align:center;'>"+value+"</div><div class='btn btn-default' style='position:absolute;right:15px;bottom:15px;width:80px;' id='myTemplateNo'>关闭</div></div>");
	$('#myTemplateNo').click(function(){
		$('#myTemplateDiv').remove();
		$('#myTemplate').remove();	
	});
	},
	/**
	 * 自定义fullTemplate
	 */
	fullTemplate : function(value) {
		$("body")
		.append(
				"<div id='fullTemplateDiv' style='position:fixed;width:100%;height:100%;background-color:#ccc;opacity:0.5;left:0;top:0;z-index:9998;'></div><div id='fullTemplate' style='z-index:9999;color:#000;background-color:#fff;text-align:center;line-height:30px;border:1px solid #fff;border-radius:5px;height:100%;width:100%;top:0;left:0;position:fixed;font-size:20px;'>"
				+ "<div style='position:absolute;top:20px;width:100%;text-align:center;'>"+value+"</div></div>");
	},
	/**
	 * 自定义登录退出
	 */
	myLoginOut : function(value,fn) {
		$("body")
		.append(
				"<div id='confirmDiv' style='position:fixed;width:100%;height:100%;background-color:#ccc;opacity:0.5;left:0;top:0;'></div><div id='confirm' style='z-index:9999;color:#fff;background-color:black;text-align:center;line-height:30px;border:1px solid black;border-radius:5px;height:200px;width:200px;margin:-100px -100px;top:50%;left:50%;position:fixed;font-size:20px;'>"
				+ "<div class='glyphicon glyphicon-off' style='text-align:center;width:50%;height:50%;font-size:66px;margin-top:10px;'></div><div style='position:absolute;top:100px;width:100%;text-align:center;'>"+value+"</div><div class='btn btn-primary' style='position:absolute;left:15px;bottom:15px;width:80px;' id='confirmYes'>确定</div><div class='btn btn-default' style='position:absolute;right:15px;bottom:15px;width:80px;' id='confirmNo'>取消</div></div>");
		$('#confirmYes').click(function(){
			$('#confirmDiv').remove();
			$('#confirm').remove();
			if(typeof fn=='function'){
				fn();
			}
		});
		$('#confirmNo').click(function(){
			$('#confirmDiv').remove();
			$('#confirm').remove();
			
		});
	},
	/**
	 * 回到顶部按钮
	 */
	goBackTop : function() {
		$("body").append("<div id='goBackTop' style='display:none;color:#fff;background-color:black;text-align:center;border:20px solid blue;border-left-color:red;border-right-color:green;border-top-color:yellow;border-radius:50%;height:20px;width:20px;top:80%;left:88%;position:fixed;-moz-transition: -moz-transform 1s ease 0s;-webkit-transition: -webkit-transform 1s ease 0s;transition: transform 1s ease 0s;'></div>");
		$(window).on('scroll',function(){
		if(!myUtils.isScrollTop()){
			$("#goBackTop").css("display","block");
		}else{
			$("#goBackTop").css("display","none");
			$("#goBackTop").css({"-webkit-transform":"rotate(-1000deg)","-moz-transform":"rotate(-1000deg)","transform":"rotate(-1000deg)"});
		}
		});
		$("#goBackTop").on('click',function(){
			$("html,body").animate({scrollTop:0},1000);
			$("#goBackTop").css({"-webkit-transform":"rotate(1000deg)","-moz-transform":"rotate(1000deg)","transform":"rotate(1000deg)"});
			return false;
		});
	},
	/**
	 * 商品数量选定组件
	 */
	merchandiseNumber:function (mun,add,minus,stock){
		
	// 商品选购数量
		$(mun).on('change',function(){
			var munthis=$(this);
			if(myUtils.userVerification.catNum.test(munthis.val())&&(munthis.val()<=stock)){
				return;
			}else{
				munthis.val(1);
			}
		});
			// 商品增加数量
		$(add).on('click',function(){
			var addthis=$(this);
			var numthis=addthis.prev();
			if(myUtils.userVerification.catNum.test(numthis.val())&&(numthis.val()<stock)){
				numthis.val(parseInt(numthis.val())+1);
				return;
			}else{
				numthis.val(1);
			}
		});
			// 商品减少数量
		$(minus).on('click',function(){
			var minusthis=$(this);
			var numthis=minusthis.next();
			if(myUtils.userVerification.catNum.test(numthis.val())&&(numthis.val()<=stock)){
				if(numthis.val()==1){
					return;
				}
				numthis.val(parseInt(numthis.val())-1);
				return;
			}else{
				numthis.val(1);
			}
		});
	},
	/**
	 * 分页组件
	 * @param obj
	 */
	myPaginationHandler:function(){
		var Pagination=(function(){
			function Pagination(){
				
			}
			Pagination.prototype={
					currentPage:1,//当前页
					totalPage:0,//总页数
					showPageNumberArray:[],//显示页码数组
					 //获取总页码
				    getTotalPage:function(totalNumber,pageNumber){
				     return totalNumber%pageNumber==0?totalNumber/pageNumber:Math.ceil(totalNumber/pageNumber);
				    },
				    //获取当前页
				    getCurrentPage:function(currentPage){
				      return currentPage;
				    },
				     //获取显示数目
				    getShowPageNumber:function(totalPage,pageNumber,mostShowPageNumber,currentPage){
				      this.showPageNumberArray=[];//清空
				      //小于设定的最大值的情况返回实际值
				      if(totalPage<=mostShowPageNumber){
				         for(var i=0;i< totalPage;i++){
				        	 this.showPageNumberArray.push(i+1);
				          }
				          return this.showPageNumberArray;
				      }
				      //大于设定的最大值
				      if(totalPage>mostShowPageNumber){
				           //小于最大值一半的情况
				           if(currentPage<=Math.ceil(mostShowPageNumber/2)){
				                for(var i=0;i< mostShowPageNumber;i++){
				                	this.showPageNumberArray.push(i+1);
				              }
				              return this.showPageNumberArray;    
				           }
				           //大于设定的最大值一半
				           if(currentPage>Math.ceil(mostShowPageNumber/2)){
				                   //如果最大值-当前值小于mostShowPageNumber，则显示(最大值-当前值)+1个
				               if(totalPage<mostShowPageNumber+currentPage){
				                   var lastPages=totalPage-currentPage;
				               for(var i=0;i<lastPages+1;i++){
				            	   this.showPageNumberArray.push(currentPage+i);
				              }
				              console.log( this.showPageNumberArray)
				              return this.showPageNumberArray;
				               }
				               //如果当前值+最大的显示小于等于最大值，则显示mostShowPageNumber个
				               if(currentPage+mostShowPageNumber<=totalPage){
				               for(var j=0;j<mostShowPageNumber;j++){
				            	   this.showPageNumberArray.push(currentPage+j-Math.floor(mostShowPageNumber/2));
				              }
				              return this.showPageNumberArray;
				               }  
				           }

				      }
				    return this.showPageNumberArray;
				    },
				    //到达的页面
				     /**
				      * content :点击的当前页目
				      *currentPage:当前页
				      *totalNumber:总页数
				      */
				    toPage:function(content,currentPage,totalPage){
				    if(content=="首页"){
				       if(currentPage==1){
				          return false;
				      }
				      this.currentPage=1;
				      return true;
				    }
				    if(content=="尾页"){
				        if(currentPage==totalPage){
				      return false;
				      }
				        this.currentPage=totalPage;
				        return true;
				    }
				    if(content=="previous"){
				      if(currentPage==1){
				      return false;
				      }
				      currentPage--;
				      this.currentPage=currentPage;
				        return true;
				    }
				    if(content=="next"){
				       if(currentPage==totalPage){
				      return false;
				      }
				      currentPage++;
				      this.currentPage=currentPage;
				        return true;
				    }
				    if(content){
				      currentPage=content;//当前页
				      this.currentPage=currentPage;
				        return true;
				    }
				    }	
			};
			
			return new Pagination();
		})();
		return Pagination;
	}


};
/**
 * *设置全局变量事件
 */
var myTouchEvents = {
	touchstart : "touchstart",
	touchmove : "touchmove",
	touchend : "touchend",
	/**
	 * 判断手机还是PC
	 */
	isPC : function() {
		var userAgentInfo = navigator.userAgent;
		var Agents = [ "Android", "iPhone", "SymbianOS", "Windows Phone",
				"iPad", "iPod" ];
		var flag = true;
		for (var v = 0; v < Agents.length; v++) {
			if (userAgentInfo.indexOf(Agents[v]) > 0) {
				flag = false;
				break;
			}
		}
		return flag;
	},
	/**
	 * 过滤ie10以下版本
	 */
	isIE : function() {
		if (navigator.userAgent.indexOf("MSIE")>0){
			return true;
		}
	},
	/**
	 * 判断手机还是PC,更改touch为鼠标事件
	 */
	initTouchEvents : function() {
		if (this.isPC()) {
			this.touchstart = "mousedown";
			this.touchmove = "mousemove";
			this.touchend = "mouseup";
			

		}
	},
	/**
	 * 拖拽事件
	 * element 多个被拖拽元素
	 * bar element中触发拖拽的元素
	 */
	myDraggable:function(element,bar,startFn,moveFn,endFn){
		myTouchEvents.initTouchEvents();
		 $(element).each(function(){
         var thiselement=this;
         var dragging = false;
         var sX=0, sY=0,mX=0,mY=0,eX=0,eY=0;
         var thiszindex= $(thiselement).css("z-index");
		  //$(thiselement).off();
         $(bar).on(myTouchEvents.touchstart,function(e) {
        	 e.preventDefault();
             dragging = true;
             $(thiselement).css("z-index","999999999");
             e.clientX==undefined?e.clientX=e.originalEvent.targetTouches[0].pageX:e.clientX;
             e.clientY==undefined?e.clientY=e.originalEvent.targetTouches[0].pageY:e.clientY;
             sX = e.clientX ;
             sY = e.clientY ;
             if(typeof startFn=='function'){
            	 startFn();
             }
             return false;
         });
         $(bar).on(myTouchEvents.touchmove,function(e) {
        	 e.preventDefault();
             if (dragging) {
             //var e = e || window.event;
             e.clientX==undefined?e.clientX=e.originalEvent.targetTouches[0].pageX:e.clientX;
             e.clientY==undefined?e.clientY=e.originalEvent.targetTouches[0].pageY:e.clientY;
             mX = e.clientX - sX+eX;
             mY= e.clientY - sY+eY;
			// console.log(mX)
             $(thiselement).css({"left":mX + "px", "top":mY + "px"});
             if(typeof moveFn=='function'){
            	 moveFn();
             }
             return false;
             }
         });
         $(bar).on(myTouchEvents.touchend,function(e) {
        	 e.preventDefault();
             dragging = false;
			 eX =parseInt( $(thiselement).css("left"));
             eY = parseInt($(thiselement).css("top"));
			//  eX = e.clientX ;
            //  eY = e.clientY ;
             if(typeof endFn=='function'){
            	 endFn();
             }
             $(thiselement).css("z-index",thiszindex);
             //$(thiselement).css("position",thisposition);
         });

           });
	}
};


/**
 * 初始化
 * 
 */
	
// 触摸事件
myTouchEvents.initTouchEvents();
// 阻塞Loading
myUtils.myPrevToast("加载中");
// 阻塞loading圆圈
myUtils.loading(document.querySelector('#prevloading'),{radius:8,circleLineWidth:2});
// 底部加载loading
myUtils.myFootLoadingToast("55px");
// 底部加载圆圈
myUtils.loading(document.querySelector('#bottomloading'),{radius:8,circleLineWidth:2});

// 底部footer a设置sellerid
/*
 * $(".fixed-footer a").each(function(){
 * if(myUtils.GetQueryString("sellerid")!=null&&myUtils.GetQueryString("sellerid")!=''){
 * $(this).attr("href",$(this).attr("href")+"?sellerid="+myUtils.GetQueryString("sellerid")); }
 * });
 */
//模态框
//myTouchEvents.myDraggable(".modal-dialog",".modal-header",null,null,null)
