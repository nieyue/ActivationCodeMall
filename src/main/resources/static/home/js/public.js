var business={
	url:"http://localhost:9000",
	//url:"http://app.nalu888.cn",
	//验证规则
	userVerification:{
		username:{code:/(^[\u4E00-\u9FA5]{2,16}$)|(^[a-zA-Z\/ ]{2,16}$)/,value:'中文或英文2-16位'},//中文或英文2-16位
		merDiscount:{code:/^(0\.(0[1-9]{1}|[1-9]\d?)|1(\.0{1,2})?)$/,value:'0.01-1.00之间'},//0.01-1.00之间
		scale:{code:/^[0]$|^(0\.(0[1-9]{1}|[1-9]\d?)|1(\.0{1,2})?)$/,value:'0-1.00之间'},//0-1.00之间 ，比例
		merPrice:{code:/(^[+]?[1-9]\d*(\.\d{1,2})?$)|(^[+]?[0]{1}(\.\d{1,2})?$)/,value:'大于0最多两位小数'},//商品价格正则,大于0最多两位小数
		catNum:{code:/^\+?[1-9][0-9]*$/,value:'非零正整数'},// 非零正整数
		postNum:{code:/^\+?[0-9][0-9]*$/,value:'含零正整数'},// 含零正整数
		nicename:{code:/^[^\s]{1,10}$/,value:'1-10位,不包含空格'},// 1-10位,不包含空格。
		signature:{code:/^[^\s]{1,15}$/,value:'1-15位,不包含空格'},// 1-15位,不包含空格.
		email:{code:/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/,value:'邮箱格式错误'}, // 邮箱
		phone:{code:/^1[0-9]{10}$/,value:'中文或英文2-16位'}, // 手机
		identity:{code:/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,value:'15位和18位身份证号码'}, // 15位和18位身份证号码
		password:{code:/^[0-9_a-zA-Z]{6,20}$/,value:'数字、字母、下划线，6-20长度'} // 数字、字母、下划线，6-20长度
	},
	account:null,//登录账户
	finance:null,//财务
	integral:null,//登录积分
	payTypeList:["","支付宝","微信","百度钱包","Paypal","网银"],//方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
	accountLevelList:[],//等级列表
	merId:1,
	mer:{},
	//全局封装ajax
	ajax:function(url,info,success,async){
				$.ajax({
					url:business.url+url,
					data:info,
					async:async==false?false:true,
					dataType:'json',//服务器返回json格式数据
					type:'post',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					xhrFields: {withCredentials: true},
					success: function(data){
			        success(data);
			        if(data.code==200){
			        	
			        }else{
			        	if(/^[0-9]*$/.test(data)){
			        		return;
			        	}
			        	if(data.code!=30002&&data.code!=40004){
			        		//alert(data.msg);
			        		console.log(data.msg)
			        		//location.href = "/";
			        	} 
			        	if(data.code==40004||data.code==70000){
			        		//alert("您的账号登录超时,请重新登录!");
			        		//location.href = "login.html";
			        	}
			        	
			        }
			      }
				})
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
	   * 冻结剩余天数，从支付当日开始计算，算第一天，第freezeDayNumber+1天解冻
	   * paymentDate 支付日期
	   * freezeDayNumber 冻结天数
	   */
	frozenRemainDay:function(paymentDate,freezeDayNumber){ 
		var alreadyDayNumber=0;//已经用了多少天
		var paymentDate=new Date(paymentDate);//支付日期
		paymentDate.setHours(23);
		paymentDate.setMinutes(59);
		paymentDate.setSeconds(59);
		var date=new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		alreadyDayNumber=(parseInt(date.getTime()/1000)-parseInt(paymentDate.getTime()/1000))/(60*60*24);
		console.log(alreadyDayNumber)
		if(freezeDayNumber-alreadyDayNumber<0){
			return 0;
		}
		return freezeDayNumber-alreadyDayNumber;
	},
	/**
	 * 单文件上传组件
	 * options:输入项
	 * options.inputfile 文件元素
	 * options.ajaxObj 数组对象1，formData{key,value} 2,url 3,success 4,error
	 * options.dragFn 拖拽的对象
	 */
	fileUpload:function(options){
		var _this=this;
		var initPhotoExt=options.photoExt||[".jpg",".png",".gif",".apk",".xls",".xlsx"];
		var isPhotoExt=false;
		if(!options&&typeof options!='object' ){
			_this.myLoadingToast("操作失败",null);
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
			  _this.myLoadingToast("请上传后缀名为"+initPhotoExt.join("").replace(/\./g,"/")+"的文件!");
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
			  _this.myLoadingToast("图片大小为"+fileSize+"KB，超过最大尺寸为200KB，请重新上传!");
		      return false;
		  }		  
	    	if (file.files && file.files[0])  
	    	 {
	         var reader = new FileReader(); 
			 reader.readAsDataURL(file.files[0]);
	      	reader.onload = function(e){
	      		if(typeof options.ajaxObj!='object'){
	      			_this.myLoadingToast("上传失败",null);
	      		return;
		      }

				
			if(options.proportion){//是否有宽高比
			 var img = new Image;
            img.src = reader.result;
            img.onload = function () {
                var width = img.width;
                var height = img.height;
               if((width/height).toFixed(2)!=(options.proportion).toFixed(2)){
            	   _this.myLoadingToast("图片宽高比"+(width/height).toFixed(2)+"，应为"+options.proportion);
			   }else{
				   myajax();	
			   }

            };
		}else{
		myajax();	
		}
				function myajax(){
					_this.myPrevToast("上传中",function(){
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
			                    _this.myPrevToast("上传失败",null,"remove");
			                  }
			                });
			                },"add");
	      				
	      		if(typeof options.dragFn=='function'){
	      			options.dragFn(e);
	      		}
				}
	    	}
	      
	      }else{
	    	  _this.myPrevToast("浏览器不支持",null,"add");
	      	setTimeout(function(){
	      		_this.myPrevToast("浏览器不支持",null,"remove");
	      	},1000);
	      }
			},
	//发送验证码
	sendValidCode:function(clickbtn,adminName,teamplateCode){
		var countdown=60;  
		var cbtn = $(clickbtn);
		//获取代码
		function getcode(){
	      var phone = $(adminName).val();
	      if(!phone){
	    	  phone=$(adminName).text().trim();
	    	  console.log(phone)
	      }
	      if(phone.length != 11 ){  
	    	  business.myLoadingToast("请填写正确的手机号！");  
	        return false;  
	      }
	      cbtn.attr("disabled", true);
	      	settime(cbtn);
	      	var info = {
						adminName:phone,
						templateCode:teamplateCode
					}
	      	business.ajax("/account/validCode",info,function(data){
                if(data.code==200){
                	business.myLoadingToast("验证码发送成功，请注意查收");
                }
	      });
		};
		getcode();
		//倒计时
		function settime() {  
	      if (countdown == 1) {  
	    	  cbtn.attr("disabled",false);  
	    	  cbtn.val("获取验证码");  
	    	  cbtn.text("获取验证码");  
	        countdown = 60;  
	        return false;  
	      } else {  
	    	  countdown--;  
	    	  cbtn.attr("disabled", true);  
	    	  cbtn.text(countdown + " S");  
	      }  
	      setTimeout(function() {  
	        settime();  
	      },1000);  
	    };
	},
	//点击全选
	checkboxAll:function(parent,childs,callback){
		//重置	
		$(parent).prop("checked",false);
		$(childs).prop("checked",false);
			$(document).off();
			//主
			$(document).on('click',parent,function(){
			$(childs).prop("checked",$(this).prop("checked"));
			if(typeof callback =='function'){
				callback();
			}
			});
			//从
			$(document).on('click',childs,function(){
				//只要有一个不选，则没有全选
				if(!$(this).prop("checked")){
					$(parent).prop("checked",$(this).prop("checked"));					
				}
				if(typeof callback =='function'){
					callback();
				}
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
	//快速loading
	myLoadingToast:function(value, fn){
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
	myTemplate : function(value,selfCloseElement) {
		var myTemplateWidth= 330;
		var myTemplateHeight= '60%';
		var myTemplateMarginWidth= 165;
		if(document.querySelector("html").offsetWidth>640){
			myTemplateWidth= 860;
			myTemplateMarginWidth= 430;
		}
		$("body")
		.append(
				"<div id='myTemplateDiv' style='position:fixed;width:100%;height:100%;background-color:#ccc;opacity:0.5;left:0;top:0;z-index:9998;'></div><div id='myTemplate' style='z-index:9999;color:#000;background-color:#fff;text-align:center;line-height:30px;border:1px solid #fff;border-radius:5px;height:"+myTemplateHeight+";width:"+myTemplateWidth+"px;margin:-100px -"+myTemplateMarginWidth+"px;top:20%;left:50%;position:fixed;font-size:20px;'>"
				+ "<div style='overflow:auto;position:absolute;width:100%;height:100%;text-align:center;'>"+value+"</div><div class='btn btn-default' style='position:absolute;right:15px;bottom:15px;width:80px;' id='myTemplateNo'>关闭</div></div>");
		//如果selfCloseElement是替换关闭按钮
		var close=$('#myTemplateNo');
		if(typeof selfCloseElement=='string'){
			$("#myTemplateNo").remove();
			close=$(selfCloseElement);
		}
		close.click(function(){
			$('#myTemplateDiv').remove();
			$('#myTemplate').remove();	
		});
		
	},
	
};

//初始化
business.init=function(){
	function injection(a){
		if(JSON.parse(sessionStorage.getItem(a))){
			business[a]=JSON.parse(sessionStorage.getItem(a));
		}
	}
	injection("account");
	injection("finance");
	injection("integral");
	injection("accountLevelList");
	injection("config");
	
	//加载七牛
	$("body").append($("<script src='"+business.url+"/home/js/qiniu.min.js'></script>"));
	//加载三级联动
	$("body").append($("<script src='"+business.url+"/home/js/distpicker.data.min.js'></script>"));
	$("body").append($("<script src='"+business.url+"/home/js/distpicker.min.js'></script>"));
	//$("body").append($('<script src="https://api.kuaishangkf.com/script/PNkP6BHhYf6eFXmm3tiSezmt" defer async></script>'));
	business.Qiniu=Qiniu;
	 
}
business.init();
//获取配置
business.getConfig=function(){
	var info = {
			accountId:business.account?business.account.accountId:null,
			pageNum:1,
			pageSize:10
		}
		business.ajax("/config/list",info,function(data){
						if(data.code==200){
							business.config=data.data[0];
							sessionStorage.setItem("config",JSON.stringify(business.config))
						}},false);
};
business.getConfig();
//获取财务
business.getFiance=function(callback){
	var info = {
			accountId:business.account?business.account.accountId:null,
			pageNum:1,
			pageSize:10
	}
	business.ajax("/finance/list",info,function(data){
		if(data.code==200){
			business.finance=data.data[0];
			sessionStorage.setItem("finance",JSON.stringify(business.finance));
			if(typeof callback =='function' ){
				callback();				
			}
		}});
};
//获取诚信等级
business.getSincerity=function(){
	var info = {
			accountId:business.account?business.account.accountId:null,
			pageNum:1,
			pageSize:10
	}
	business.ajax("/sincerity/list",info,function(data){
		if(data.code==200){
			business.sincerity=data.data[0];
			sessionStorage.setItem("sincerity",JSON.stringify(business.sincerity))
		}},false);
};
//获取银行卡
business.getBankCardList=function(){
	var info = {
   			accountId:business.account.accountId,
   			pageSize:100
   		};
	business.ajax("/bankCard/list",info,function(data){
		if(data.code==200){
			business.bankCardList=data.data;
			sessionStorage.setItem("bankCardList",JSON.stringify(business.bankCardList));
			$("#bankCardCount").text(business.bankCardList.length);
		}else{
			$("#bankCardCount").text(0);
			sessionStorage.setItem("bankCardList",JSON.stringify([]));
			
		}
	});
};
$(function(){
	$(".tab_bigbox .tab_box").eq(0).show();
	$(".tab_bigbox1 .tab_box1").eq(0).show();
    //显示全部游戏厂商
	$(".friendslink_more").click(function  () {
		if($(this).hasClass("this")){
			$(this).removeClass("this");
			business.getInitGameFactory();
			$(".friendslink_more img").css("transform","rotate(0deg)")
		}else{
			$(this).addClass("this");
			$(".friendslink_more img").css("transform","rotate(180deg)")
			$(".friendslink_ul li").each(function  (index) {
				if(index>9){
			       $(this).show();
				}
			})
		}
	})
	
	//已经登录状态栏改变
	$(".alreadyLogin_centerBox ").hover(function  () {
		$(".alreadyLogin_centerul").stop().show();
		$(".alreadyLogin_infop").addClass("this");
	},function  () {
		$(".alreadyLogin_centerul").stop().hide();
		$(".alreadyLogin_infop").removeClass("this");
	})
	
	$(".alreadyLogin_carli").hover(function () {
		$(".alreadyLogin_goodsul").stop().show();
		$(this).addClass("this");
	},function  () {
		$(".alreadyLogin_goodsul").stop().hide();
		$(this).removeClass("this");
	})
	
	
	
	$('#searchbox_input').bind('input propertychange', function() {  
    	var value = $(this).val();
    	if(value.length>1){
    		var info = {
    			name:value,
				pageNum:1,
				pageSize:20
    		};
    		business.ajax("/mer/list",info,function(data){
    			var table = $('#seachlist');
		        	table.empty();
					if(data.code==200){
						var list = data.data;
				        	
				        	for(var i = 0; i < list.length; i++) {
				        		var good = list[i];
				        		var li = document.createElement('li');
				        		
								li.id = good.merId;
								var html = '<i class="color_1b1b1b onlyone">'+good.name+'</i>';
								li.innerHTML = html;
		
								table.append(li); 
				        	}
				        	$(".searchbox_alert").stop().show();
					}else{
						table.empty();
					}
				//搜索
				$(".searchbox_alert ul li").click(function  () {
				    var thistext=$(this).text();
				    $("#searchbox_input").val(thistext);
				    $(".searchbox_alert").stop().hide();
				})
		});
    	}
	});
	$("#searchbox_input").focus(function  () {
		
	})
	
    //全部商品分类
	$(".classify_p").click(function  () {
		if($(".banner_classiul").is(":hidden")){
			$(".banner_classiul").stop().slideDown();
			$(".classifyi").css("transform","rotate(180deg)")
		}else{
			$(".banner_classiul").stop().slideUp();
			$(".classifyi").css("transform","rotate(0deg)")
		}
	})
	
	$("#gomyshopcar").click(function(){
		window.location.href = "./shopcar.html";
	});
	
	
	
})

//显示全部游戏厂商
business.getInitGameFactory=function () {
	$(".friendslink_ul li").each(function  (index) {
		if(index>9){
			$(this).hide();
		}
	})
}
business.getInitGameFactory();

function tabs(tabTit,on,tabCon,event){
    $(tabTit).on(event,function(){
        $(this).addClass(on).siblings().removeClass(on);
        var index = $(tabTit).index(this);
        $(tabCon).children().eq(index).show().siblings().hide();
    });
};

function isHasthis (obj,event) {
	$(obj).on(event,function () {
	  	if($(this).hasClass("this")){
	  	  $(this).removeClass("this");
	  	}else{
	  	  $(this).addClass("this");
	  	}
	})
}
function isHasthis_sibling(obj,event) {
	$(obj).on(event,function () {
	  	if($(this).hasClass("this")){
	  	  $(this).removeClass("this");
	  	}else{
	  	  $(this).addClass("this").siblings().removeClass("this");
	  	}
	})
}
function isHasthisHover(obj) {
	$(obj).hover(function () {
	  	if($(this).hasClass("this")){
	  	  $(this).removeClass("this");
	  	}else{
	  	  $(this).addClass("this");
	  	}
	})
}

function isShow(obj,toshow,myclass) {
	$(obj).hover(function () {
	  	$(this).find(toshow).stop().slideDown();
		$(this).addClass(myclass);
	},function(){
	    $(this).find(toshow).stop().slideUp();
		$(this).removeClass(myclass);
	})
}
//个人中心
$(".alreadyLogin_centerul>li").click(function  () {
	    var thistext=$(this).text();
	    
	    var showid = $(this).index()+1;
	    window.location.href= "./buyuserinfo.html?show="+thistext+"&showid="+showid;
	})


$(function(){
	/*右侧固定*/
	var _height = $(window).height()/2;
	$(window).scroll(function(){
		var scroll_top = $(this).scrollTop();
		if(scroll_top>_height)
		{
			$('.fixed_box').fadeIn();
		}else{
			$('.fixed_box').fadeOut();
		}
	})
	$('.fixed_box .backtop').click(function(){
		$('body,html').animate({scrollTop:0},1000)
	})
})

/*新闻点击*/
//$('.newsparent').click(function(){
//	window.location.href='newsdetail.html';
//})



//---------------------------------------------悬停左边的
$(window).scroll(function  () {
	function autoFixed() {
        var rh = $("#fixed_left").offset().top;
        var fh = $(".myfooter").offset().top;
        if($(window).scrollTop() > rh && $(document).scrollTop() < fh - rh-150){
            $("#fixed_right").css({ position: 'fixed', left: '50%', marginLeft: '320px',top:'0px',marginTop: '0px'});
        }
        else if ($(document).scrollTop() > fh - rh-150) {
            $("#fixed_right").css({ position: 'absolute', left: '50%', marginLeft: '320px'});
        }
        else{
        	$("#fixed_right").css({ position: 'static',top:'0px',left: '0', marginLeft: '0',marginTop: '67px'});
        }
    }
    if ($('#fixed_left').height() > $('#fixed_right').height()) {
        autoFixed();
    }
})


business.checkEmail=function (email){
　　
　　var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
 	if(email.length>0){
 		if(myReg.test(email)){
	
	　　　　return true;
	　　}else{
	　　　　business.myLoadingToast("邮箱格式不正确")
	　　　　return false;
		}
 	}else{
 		business.myLoadingToast("请输入邮箱")
	　　return false;
 	}
　　
}

business.getUrlInfo=function(url){
	var Request = new Object();
	if(url.indexOf("?")!=-1){
	    var str = url.substr(1)　//去掉?号
	    strs = str.split("&");
	    for(var i=0;i<strs.length;i++){
	        Request[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);
	    }
	}
	return Request;
}



//商品搜索
$("#seachbtn").click(function(){
	var merName = $("#searchbox_input").val();
	if(merName.length>0){
		window.location.href=business.url+'/home/goodsClassify.html?merName='+merName;
	}
	
});
//商品类型
business.getMerCateList=function(){
	var info = {
		pageNum:1,
		pageSize:10
	}
	business.ajax("/merCate/list",info,function(data){
					var list = data.data;
					var table = $('.banner_classiul');
		        	for(var i = 0; i < list.length; i++) {
		        		var cate = list[i];
		        		var li = document.createElement('li');
						li.id = cate.merCateId;
						var html = '<a href="goodsClassify.html?merCateId='+cate.merCateId+'" title="">'+cate.name+'</a>';
						li.innerHTML = html;

						table.append(li); 
		        	}
	});
}

//购物车商品
business.getCartMerList=function(){
	var info = {
		accountId:business.account?business.account.accountId:null,
		pageNum:1,
		pageSize:10
	}
	business.ajax("/cartMer/list",info,function(data){
					if(data.code==200){
					
					var list = data.data;
					if(list.length>0){
						$("#nogood").toggle();
					}
					var table = $('.alreadyLogin_goodsul');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var li = document.createElement('li');
						li.id = child.cartMerId;
						var html = '<li><a href="shopcar.html" title="" class="clearfix"><div class="imgbox fl"><img style="width: 80px;height: 50px;" src="'+child.mer.imgAddress+'" alt="" title=""/></div><div class="fl padding_left20 font_size12 fontfamily_song lineheight20 alreadyLogin_goodsinfo"><p>'+child.mer.name+'</p><span class="color_eb6100">￥'+child.mer.unitPrice+'</span></div></a></li>';
						li.innerHTML = html;

						table.append(li); 
		        	}
		        		
					}
	});
}
//商家登录
$("#sellgologin").click(function(){
	window.location.href = "login.html?roletype=1";
});
//推广登录
$("#tuiguanggologin").click(function(){
	window.location.href = "login.html?roletype=2";
});
//用户登录
$("#usergologin").click(function(){
	window.location.href = "login.html?roletype=3";
});
//热门搜索单词
business.getMerSearchList=function(){
	
	var info = {
		pageNum:1,
		pageSize:10000
	};
	business.ajax("/merSearch/list",info,function(data){
		if(data.code==200){
			var table = $('#hotseachlist');
			var list = data.data;
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var a = document.createElement('a');
						a.id = child.merSearchId;
						a.innerText = child.name;
						a.href = business.url+'/home/goodsClassify.html?merName='+child.name;
						table.append(a); 
		        	}
		}
	})
}
//判断是否登录
business.islogin=function(){
//只要一个不存在就没登录
	if(!business.account
			||!business.finance
			||!business.integral
			||!business.accountLevelList
			){
		$("#nologindiv").css("display","block");
		$("#havelogindiv").css("display","none");
		sessionStorage.clear();
		return;
	}
	business.ajax("/account/islogin",null,function(data){
		if(data.code==200){
			//$("#nologindiv").remove();
			$("#nologindiv").css("display","none");
			$("#havelogindiv").css("display","block");
			if(!business.account){
				$("#nologindiv").css("display","block");
				$("#havelogindiv").css("display","none");
				return;
			}
			if(business.account.roleName=="用户"){
				$("#usernav").show();
				$("#sellernav").remove();
				$("#honglinav").remove();
				
			}else if(business.account.roleName=="商户"){
				$("#usernav").remove();
				$("#sellernav").show();
				$("#honglinav").remove();
				
			}else if(business.account.roleName=="推广户"){
				$("#usernav").remove();
				$("#sellernav").remove();
				$("#honglinav").show();
				
			}
			$(".alreadyLogin_namep").text(business.account.nickname);
			$("#userleve").text(business.integral.name);
			$("#exit").click(function(){
				business.ajax("/account/loginout",null,function(data){
					if(data.code==200){
						sessionStorage.clear();
						location.href="/";				
					}
				});
			});
			//推广户
			//点击进入红利用户
			$("#gomayuserinfo").click(function(){
				window.location.href =business.url+ "/home/hongli/hongli_userinfo.html";
			});
			//消息
			$("#hongliMessage").click(function(){
				console.log(1111111)
			});
			
			//商户
			//点击进入商户
			$("#gosellerinfo").click(function(){
				window.location.href =business.url+ "/home/sell/sell_index.html";
			});
			
		}else{
			$("#nologindiv").css("display","block");
			$("#havelogindiv").css("display","none");
			sessionStorage.clear();
			//$("#havelogindiv").remove();
			$("#nologindiv a").click(function(){
				if(url.startsWith("hongli")||url.startsWith("sell")){
					window.location.href='../login.html';
				}else{
					window.location.href='./login.html';
				}
			});
		}
	})
}
business.islogin();
//验证第二次密码
	business.equalspassword=function(password1,password2){
		if(password1.length>5){
			if(password1==password2){
				return true;
			}else{
				business.myLoadingToast("两次密码不匹配");
				return false;
			}
		}else{
			business.myLoadingToast("密码不能小于6位");
			return false;
		}
	};

//七牛云
/*
 *递归 把array数组递归成属性挂载在$this对象上
 *p.str='b.c.d.ee.cc.22' 字符串
 *p.resource  该对象需要接受的值
 *$this 输入对象
 * 结果：$this.b.c.d.ee.cc.22=p.resource
 */
 business.recursion=function($this,p){
   var array=p.str.split('.');
     function temp(obj){
        var oldArrayElement=array[0];
       if(array.length!=1){
         obj[oldArrayElement]=obj[oldArrayElement]||{};
         array.shift()
         temp(obj[oldArrayElement]); //递归遍历
        }else{
          obj[oldArrayElement]=p.resource;
        }
      }
      temp($this);//初始化
  }
/**
   * 获取七牛云  token
   *p.url 获取token url 
   *p.qiniuToken 变量qiniuToken
   *p.success 回调
   */
 business.getQiniuToken=function($this,p) {
	  business.ajax(
		p.url,
		null,
      function(d){ 
			$this.qiniuToken=d.data[0]
        if(typeof p.success=='function'){
        p.success();
        }
      }
    )
  };
  /**
   * 七牛云js-sdk 上传
   *p.url 获取token url  (可选，自定义)
   *p.qiniuToken 变量qiniuToken
   *p.browseButton 上传按钮
   *p.container  box包裹层(可选)
   *p.dropElement 删除按钮
   *p.maxFileSize 最大上传文件限制 (可选，默认100mb)
   *p.chunkSize 分块大小 (可选，默认4mb)
   *p.multiSelection 是否允许同时选择多文件 (可选，默认false)
   *p.fileTypes 上传文件类型 (可选，默认'jpg,jpeg,gif,png')
   *p.filesAdded 上传文件类型 (可选，默认'jpg,jpeg,gif,png'
   *
   *p.filesAdded(up,files) 文件添加进队列后，处理相关的事情 (可选)
   *p.beforeUpload(up, file) 每个文件上传前，处理相关的事情 (可选)
   *p.uploadProgress(up, file) 每个文件上传时，处理相关的事情 (可选)
   *p.uploadComplete() 队列文件处理完毕后，处理相关的事情 (可选)
   *p.fileUploaded(up, file, info) 每个文件上传成功后，处理相关的事情 
   *p.error(up, err, errTip) 上传出错时，处理相关的事情 (可选)
   *p.success 回调
   */
  business.getQiniuUploader=function($this,p) {
    // /* eslint-disable no-undef */
    var uploader = business.Qiniu.uploader({
      runtimes: 'html5,flash,html4',
      browse_button: p.browseButton, // 上传按钮的ID
      /* eslint-disable no-undef */
      container:p.container|| (p.browseButton + 'Box'), // 上传按钮的上级元素ID
      drop_element: p.dropElement,
      max_file_size: p.maxFileSize||'500mb', // 最大文件限制
      dragdrop: true,
      chunk_size: p.chunkSize||'4mb', // 分块大小
      // Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
      uptoken: p.qiniuToken,
      //  默认 false，key为文件名。若开启该选项，SDK会为每个文件自动生成key（文件名）
      //  save_key: true,
      //  默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK在前端将不对key进行任何处理
      domain: p.url||'http://p55v5f6od.bkt.clouddn.com', // 自己的七牛云存储空间域名
      multi_selection: false, // 是否允许同时选择多文件
      // 文件类型过滤，这里限制为图片类型
      filters: {
        mime_types: [
          {title: 'Image files', extensions: p.fileTypes||'jpg,jpeg,gif,png,flv,mp4,wmv,avi,rm,rmvb,swf'}
        ]
      },
      auto_start: true,
      unique_names: true,
      init: {
        'FilesAdded': function (up, files) {
          if(typeof p.filesAdded=='function'){
            p.filesAdded(up,files);
          }
        },
        'BeforeUpload': function (up, file) {
          if(typeof p.beforeUpload=='function'){
            p.beforeUpload(up, file);
          }
        },
        'UploadProgress': function (up, file) {
          // 可以在这里控制上传进度的显示
          // 可参考七牛的例子
          if(typeof p.uploadProgress=='function'){
            p.uploadProgress(up, file);
          }
        },
        'UploadComplete': function () {
          
          if(typeof p.uploadComplete=='function'){
            p.uploadComplete();
          }
        },
        'FileUploaded': function (up, file, info) {
          // 每个文件上传成功后,处理相关的事情
          if(typeof p.fileUploaded=='function'){
            p.fileUploaded(up, file, info);
          } 
        },
        'Error': function (up, err, errTip) {
          if(typeof p.error=='function'){
            p.error(up, err, errTip);
            } 
        },
        'Key': function (up, file) {
          // 当save_key和unique_names设为false时，该方法将被调用
          return ''
        }
      }
    })
    uploader.start()
  };
   /**
   *文件上传简易版  token
   *p.url 后台请求qiniuToken链接地址（可选） 
   *p.qiniuToken qiniuToken名称 （可选）
   *p.browseButton 点击按钮 
   *p.container  box包裹层(可选)
   *p.dropElement 拖拽框
   *p.resource 返回接收地址变量 (可选,一般在 编辑器里面用不需要)
   *p.success 回调
   *p.fileUploaded(up, file, info) 每个文件上传成功后，处理相关的事情 
   */
  business.getQiniuSimpleUploader=function($this,p) {
      business.getQiniuToken($this,{
       url:p.url||'/tool/qiniuToken',
       qiniuToken:p.qiniuToken||'qiniuToken',
       success:function(){
    	   
    	   business.getQiniuUploader($this,{
        qiniuToken:$this[p.qiniuToken]||$this.qiniuToken,
        browseButton:p.browseButton,
        container:p.container||null,
        dropElement:p.dropElement,
        fileUploaded:function(up, file, info){
            var domain = up.getOption('domain')
            var res = JSON.parse(info.response)
            var url=domain +"/"+  res.key;
            if(p.resource){
              // if(p.resource.indexOf(".")>-1){
              //   let nr=p.resource.split('.')//分割
              //   $this[p.resource.substr(0,p.resource.indexOf("."))][p.resource.substr(p.resource.indexOf(".")+1)]=url;
              // }else{
              //   $this[p.resource] = url
              // }
              //递归调用
              business.recursion($this,{
                str:p.resource,
                resource:url
              });
            }
            if(typeof p.success=='function'){
            	console.log(url)
              p.success(url);
            } 
            if(typeof p.fileUploaded=='function'){
              p.fileUploaded(up, file, info);
            } 
            }
           //let temp= this.recursion($this,nr)
              
      })
       }
      });
  };  
  /**
   * 上传图片
   * clickElementBox包裹元素
   * clickElement点击元素
   * fileElement 文件元素
   * imgElement 图片元素
   */    
  business.qiniuUploadImg=function(clickElementBox,clickElement,fileElement,imgElement,callback){
	  var bb=clickElement.replace(/\#/g, "").replace(/\./g, "");
	  var de=clickElementBox.replace(/\#/g, "").replace(/\./g, "");
	  //上传图片  
	  if($(fileElement)[0]){
		  business.getQiniuSimpleUploader(business,{
			  browseButton:bb,
			  dropElement:de,
			  container:de,
			  //resource:'business.account.icon',
			  success:function(url){
				  if(imgElement){
					  $(imgElement).attr("src",url);					  
				  }
				  if(typeof callback=='function'){
					  callback(url);
				  }
			  }
		  });
	  }
  };      
  /*
   *获取时间
   */
  business.getTime=function(time) {
	  var timec = new Date(Number(time));
	  var year = timec.getFullYear();
	  var month = timec.getMonth() + 1;
	  var day = timec.getDate();
	  var hours = timec.getHours();
	  var minutes = timec.getMinutes();
	  var seconds = timec.getSeconds();
	  var timer = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
	  return timer;
  };
//获取积分列表
business.getintarll=function(){
	var info = {
  			accountId:business.account.accountId,
  			pageNum:1,
  			pageSize:100
  		};
  		business.ajax("/integralDetail/list",info,function(data){
			if(data.code==200){
					var list = data.data;
		        	var table = $('#chengzhang_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'chengzhangtd height40';
						tr.id = child.integralDetailId;
						var html = '<td >'+child.createDate+'</td><td >'+child.integral+'</td>';
						tr.innerHTML = html;

						table.append(tr); 
		        	}
			}
		})
};
	/**
	 * 通用
	 */
	business.common=function(){
	/**
	 * 客服
	 */
	//设置客服电话<span class="customerServicePhone"></span>
	$(".customerServicePhone").text(business.config.customerServicePhone);
		
	//点击客服
	/*$(".kefu_a").on("click", function() {
	})*/
		
	if(!business.account){
		return;
	}
	//修改昵称
	$("#nicknametext").text(business.account.nickname);
	$("#nicknameinput").val(business.account.nickname);
	$("#nicknameinput").hide();
	$("#updatenicknamesave").hide();
	$("#updatenickname").unbind();
	$("#updatenickname").click(function() {
		$("#nicknameinput").show();
		$("#updatenicknamesave").show();
		$("#nicknametext").hide();
		$("#updatenickname").hide();
	})
	$("#updatenicknamesave").unbind();
	$("#updatenicknamesave").click(function() {
		var info = {
	   			accountId:business.account.accountId,
	   			nickname:$("#nicknameinput").val()
	   		};
		business.ajax("/account/updateInfo",info,function(data){
			if(data.code==200){
				$("#nicknameinput").hide();
				$("#updatenicknamesave").hide();
				$("#nicknametext").show();
				$("#updatenickname").show();
				business.account.nickname = info.nickname;
				sessionStorage.setItem("account",JSON.stringify(business.account));
				$("#nicknametext").text(business.account.nickname)
				$("#nicknameinput").val(business.account.nickname);
			}
		})
	});
	//初始化图像
	if(business.account.icon){
		$("#updateIcon").attr("src",business.account.icon);	
	}
	//修改头像
	business.updataimg=function(){
		console.log(1111)
	   //	$('#updateIconFile').click();
	   }
	 //上传图像   
	if($('#updateIconFile')[0]){
	business.getQiniuSimpleUploader(business,{
		browseButton:'updateIcon',
		dropElement:'updateIconFileBox',
		container:'updateIconFileBox',
		resource:'business.account.icon',
		success:function(url){
			var info = {
		   			accountId:business.account.accountId,
		   			icon:url
		   		};
			business.ajax("/account/updateInfo",info,function(data){
				if(data.code==200){
					business.account.icon=url
					sessionStorage.setItem("account",JSON.stringify(business.account));
					$("#updateIcon").attr("src",business.account.icon);
				}
			})
		}
	});
	}
	//邮箱
	$(".useremail").text(business.account.email);

	if(business.account.phone!=null&&business.account.phone!=""){
		$(".userphone").text(business.account.phone);
		$(".bindphone").toggle();
	}
	/**
	 * 实名认证
	 */
	var authvalue="没认证";
	if(business.account.auth==1){
		authvalue="审核中";
	}else if(business.account.auth==2){
		authvalue="已认证";
	}
	$("#auth").text(authvalue);
	//点击认证
	$("#auth").on("click", function() {
		if(business.account.auth==1){
			business.myLoadingToast("审核中");
			return;
		}
		if(business.account.auth==2){
			business.myLoadingToast("已认证");
			return;
		}
		var html='<div style="width: 830px;margin-left: 10px;float: left;margin-top: 10px;padding-bottom: 100px;" class="bg_fff">'
					+'<p style="margin-top: 60px;font-size: 20px;">'+business.account.roleName+'实名认证</p>'
					+'<div style="margin-left: 100px;margin-top: 30px;">'
						+'<div class="infodiv clearfix">'
							+'<p class="font_size14 fl">真实姓名：</p>'
							+'<input class="bankinput" id="realname" placeholder="  输入您的真实姓名"/>'
						+'</div>'
						+'<div class="infodiv clearfix">'
							+'<p class="font_size14 fl">身份证号：</p>'
							+'<input class="bankinput" id="identityCards" placeholder="  输入您的身份证号码"/>'
						+'</div>'
						+'<div class="infodiv clearfix" style="margin-left: 5px;" id="identityCardsHoldImgBox">'
							+'<p class="font_size14 fl" >手持身份证上半身照：</p>'
							+'<input type="file" id="identityCardsHoldImgFile" accept="image/*" style="display: none;" />'
							+'<img style="width:192px;height:122px;margin-left:-225px;" src="../img/bansheng.png" id="identityCardsHoldImg" />'
						+'</div>'
						+'<div class="infodiv clearfix" id="identityCardsFrontImgBox">'
							+'<p class="font_size14 fl">身份证正面照：</p>'
							+'<input type="file" id="identityCardsFrontImgFile" accept="image/*" style="display: none;"  />'
							+'<img style="width:192px;height:122px;margin-left:-225px;" src="../img/zheng.png" id="identityCardsFrontImg" />'
						+'</div>'
					+'<div class="infodiv clearfix" id="identityCardsBackImgBox">'
						+'<p class="font_size14 fl">身份证反面照：</p>'
						+'<input type="file" id="identityCardsBackImgFile" accept="image/*" style="display: none;" />'
						+'<img style="width:192px;height:122px;margin-left:-225px;" src="../img/fan.png" id="identityCardsBackImg"/>'
					+'</div>'
				+'</div>'
				+'<div style="width: 270px;margin-left: 220px;margin-top: 100px;">'
					+'<a class="bankbtn" style="background: #b5b5b5;float: left;" id="authClose">取消</a>'
					+'<a class="bankbtn" style="background: #FF7400;float: right;" id="authCommit">提交审核</a>'
				+'</div>'
			+'</div>';
		business.myTemplate(html,"#authClose");//显示模板
		//图片上传
		business.qiniuUploadImg("#identityCardsHoldImgBox", "#identityCardsHoldImg", "#identityCardsHoldImgFile", "#identityCardsHoldImg")
		business.qiniuUploadImg("#identityCardsFrontImgBox", "#identityCardsFrontImg", "#identityCardsFrontImgFile", "#identityCardsFrontImg")
		business.qiniuUploadImg("#identityCardsBackImgBox", "#identityCardsBackImg", "#identityCardsBackImgFile", "#identityCardsBackImg")
		//提交
		$("#authCommit").on("click", function() {
			var realname=$("#realname").val().trim();
			var identityCards=$("#identityCards").val().trim();
			var identityCardsHoldImg=$("#identityCardsHoldImg").attr("src");
			var identityCardsFrontImg=$("#identityCardsFrontImg").attr("src");
			var identityCardsBackImg=$("#identityCardsBackImg").attr("src");
			if(!realname||realname.length<2||realname.length>16){
				business.myLoadingToast("请填写真实姓名");
				return;
			}
			if(!identityCards||(identityCards.length!=15&&identityCards.length!=18)){
				business.myLoadingToast("请填写正确身份证");
				return;
			}
			if(identityCardsHoldImg.indexOf("http")<0||identityCardsFrontImg.indexOf("http")<0||identityCardsBackImg.indexOf("http")<0){
				business.myLoadingToast("缺少上传图片");
				return;
			}
			var info = {
		   			accountId:business.account.accountId,
		   			realname:realname,
		   			identityCards:identityCards,
		   			identityCardsHoldImg:identityCardsHoldImg,
		   			identityCardsFrontImg:identityCardsFrontImg,
		   			identityCardsBackImg:identityCardsBackImg,
		   		};
			business.ajax("/account/auth",info,function(data){
				if(data.code==200){
					business.myLoadingToast("上传成功，请等待审核");
					business.account=data.data[0];
					sessionStorage.setItem("account",JSON.stringify(business.account));
					$("#authClose").click();
				}else{
					business.myLoadingToast(data.msg);
				}
			})
		});
	})
	//安全等级
	if(business.account.safetyGrade==1){
		$(".safetyGrade").text("低");
		$(".safediv").width("20%");
	}else if(business.account.safetyGrade==2){
		$(".safetyGrade").text("中");
		$(".safediv").width("50%");
	}else if(business.account.safetyGrade==3){
		$(".safetyGrade").text("高");
		$(".safediv").width("100%");
	}
	/**
	 * 修改密码
	 */
	//显示修改密码界面
	$("#updatePasswordWrap").hide();
	$(".updatapassword").unbind();
	$(".updatapassword").click(function(){	
		//隐藏安全设置
		$("#setdiv").hide();
		//显示修改密码栏
		$("#updatePasswordWrap").show();
		//显示修改密码栏第一步
		$("#updatePassword1").show();
		//显示邮箱
		$(".updatePasswordGetEmail").text(business.account.email);
		
	});
	//点击修改密码发送邮箱
	$("#updatePassword1SendEmail").click(function(){
		//显示修改密码栏第2步
		if(business.checkEmail(business.account.email)){
		var info = {
				accountId:business.account.accountId,
				adminName:business.account.email,
				templateCode:2//修改密码
			}
		business.ajax("/account/validCode",info,function(data){
			if(data.code==200){
	        	if(data.msg=="已经验证"){
	        		//alert(data.msg);
	        		business.myLoadingToast(data.msg);
	        		//显示修改密码栏第2步
		        	$("#updatePassword1").hide();
					$("#updatePassword2").show();
		        	return;
		        }else{
		        	business.myLoadingToast("验证码发送成功，请注意查收");
		        	//alert("验证码发送成功，请注意查收");		
		        	//显示修改密码栏第2步
		        	$("#updatePassword1").hide();
					$("#updatePassword2").show();
		        }
		        }else{
		        	//alert(data.msg);
		        	business.myLoadingToast(data.msg);
		        }
            });
		}
	});
	//点击进入第三步，激活
	$("#updatePassword2ValidEmail").click(function(){
		//显示修改密码栏第3步
    	$("#updatePassword2").hide();
		$("#updatePassword3").show();
	});
	//取消修改密码
	$("#updatePassword3Cancel").click(function(){
		$("#setdiv").show();
		$("#updatePasswordWrap").hide();
		$("#updatePassword3").hide();
	});
	//确认修改密码
	$("#updatePassword3Sure").click(function(){
		var password1 = $("#password1").val();
		var password2 = $("#password2").val();
		var email = Request["email"];
		if(business.equalspassword(password1,password2)){
			var info = {
					accountId:business.account.accountId,
					adminName:business.account.email,
					password:password1
				}
			business.ajax("/account/updatePassword",info,function(data){
				if(data.code==200){
					business.account = data.data[0];
					sessionStorage.setItem("account",JSON.stringify(business.account));
					//alert("修改成功");
					business.myLoadingToast("修改成功");
					$("#setdiv").show();
					$("#updatePasswordWrap").hide();
					$("#updatePassword3").hide();
				}else{
					//alert(data.msg);
					business.myLoadingToast(data.msg);
				}
                    
			        
                });
			
		}
	});
	/**
	 * 修改提现密码
	 */
	if(business.account.roleName!="用户"){
		//发送验证码
		$(".getcodebtn").on("click", function() {
		business.sendValidCode(".getcodebtn",".userphone",3);//修改提现密码
		});
		//下一步
		$("#updatePayPasswordNext").click(function() {
			if(!$("#validCode").val()){
				business.myLoadingToast("请填写验证码")
				return;
			}
			$("#updatePayPassword1").hide();
			$("#updatePayPassword2").show();
		});
		//上一步
		$("#updatePayPasswordPrev").click(function() {
			$("#updatePayPassword2").hide();
			$("#updatePayPassword1").show();
		});
		//提交
		$("#updatePayPasswordCommit").click(function() {
			var p1=$("#paypassword1").val().trim();
			var p2=$("#paypassword2").val().trim();
			if(p1!=p2){
				business.myLoadingToast("两次密码不一致")
				return;
			}else if(p1.length!=6){
				business.myLoadingToast("密码位数必须为6")
				return;
			}
			var info = {
					accountId:business.account.accountId,
					password:p1,
					validCode:$("#validCode").val().trim()
				}
			business.ajax("/finance/updatePassword",info,function(data){
				if(data.code==200){
					business.myLoadingToast("修改成功");
					business.getFiance(function(){
						if(business.account.roleName=='商户'){
							location.href="../sell/sell_setinfo.html";													
						}else if(business.account.roleName=='推广户'){
							location.href="../hongli/hongli_userinfo.html";													
						}else {
							location.href="/";													
						}
					});
				}else{
					business.myLoadingToast(data.msg)
				}
				});
		});
	}
	/**
	 * 提现
	 */
	   //窗口效果
	    business.withdrawalsWindow=function(){
		//点击登录class为tc 显示
		$(".tixianbtn").click(function(){
			$("#gray").show();
			$("#popup").show();//查找ID为popup的DIV show()显示#gray
			var _top=($(window).height()-$(".popup").height())/2;
			var _left=($(window).width()-$(".popup").width())/2;
			$(".popup").css({top:_top,left:_left});
		});
		//点击关闭按钮
		$(".guanbi").click(function(){
			$("#gray").hide();
			$("#popup").hide();//查找ID为popup的DIV hide()隐藏
		});
		//窗口水平居中
		$(window).resize(function(){
			var _top=($(window).height()-$(".popup").height())/2;
			var _left=($(window).width()-$(".popup").width())/2;
			$(".popup").css({top:_top,left:_left});
		});
		//窗口移动
	/*	$(".popup").mousedown(function(e){ 
				$(this).css("cursor","move");//改变鼠标指针的形状 
				var offset = $(this).offset();//DIV在页面的位置 
				var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离 
				var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
				$(document).bind("mousemove",function(ev){ //绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件 
					$(".popup").stop();//加上这个之后 
					var _x = ev.pageX - x;//获得X轴方向移动的值 
					var _y = ev.pageY - y;//获得Y轴方向移动的值 
					$(".popup").animate({left:_x+"px",top:_y+"px"},10); 
				}); 
	
			}); 
			$(document).mouseup(function() { 
				$(".popup").css("cursor","default"); 
				$(this).unbind("mousemove"); 
			});*/
	    }
	    business.withdrawalsWindow();//待优化
	//无提现手续费最低额度
	$(".withdrawalsMinBrokerage").text(business.config.withdrawalsMinBrokerage);
	//提现手续费比例，单位%
	$(".withdrawalsProportion").text(business.config.withdrawalsProportion);
	//余额
	$(".financeMoney").text(business.finance.money);
	//冻结金额
	$(".financeFrozen").text(business.finance.frozen);
	//提现金额
	$(".withdrawalsMoney").attr("placeholder","输入金额必须大于"+business.config.minWithdrawals);
	
	//选择支付
	business.payType=1;//默认是1，支付宝
	$(".tixian_positionul li").click(function(){
		$('.tixian_positionul li').removeClass('tixianborder7400');
		$(this).addClass('tixianborder7400');
		//console.log(parseInt($(this).attr("id").replace("payType", "")))
		business.payType=parseInt($(this).attr("id").replace("payType", ""));
	});
	//提现
	$("#withdrawalsCommit").click(function(){
		if(business.finance.money<business.config.minWithdrawals){
			business.myLoadingToast("账户余额小于最小提现金额"+business.config.minWithdrawals)
			return;
		}
		if(business.payType!=1&&business.payType!=2&&business.payType!=5){
			business.myLoadingToast("请选择支付")
			return;
		}
		var withdrawalsMoney=$(".withdrawalsMoney").val().trim();
		if(!business.userVerification.merPrice.code.test(withdrawalsMoney)){
			business.myLoadingToast(business.userVerification.merPrice.value)
			return ;
		}
		if(withdrawalsMoney<business.config.minWithdrawals){
			business.myLoadingToast("提现金额必须大于"+business.config.minWithdrawals)
			return;
		}
	});
	/**
	 * 积分
	 */
	//获取积分等级
	$("#integralLevel").text(business.integral.name);
	//现有积分
	$("#integralIntegral").text(business.integral.integral)
	//升级积分
	$("#integralUpgradeIntegral").text(parseFloat(business.integral.upgradeIntegral-business.integral.integral).toFixed(2));
	//获取积分列表
	if($("#chengzhang_tb")[0]){
		business.getintarll();		
	}
	}
	business.common();