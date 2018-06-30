//加载七牛
$("body").append($("<script src='js/qiniu.min.js'></script>"))
//加载三级联动
$("body").append($("<script src='js/distpicker.data.min.js'></script>"))
$("body").append($("<script src='js/distpicker.min.js'></script>"))

var business={
	url:"http://localhost:9000",
	//url:"http://app.nalu888.cn",
	account:null,//登录账户
	finance:null,//财务
	integral:null,//登录积分
	accountLevelList:[],//等级列表
	merId:1,
	mer:{},
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
	myTemplate : function(value) {
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
	$('#myTemplateNo').click(function(){
		$('#myTemplateDiv').remove();
		$('#myTemplate').remove();	
	});
	},
	Qiniu:Qiniu
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
}
business.init();
$(function(){
	$(".tab_bigbox .tab_box").eq(0).show();
	$(".tab_bigbox1 .tab_box1").eq(0).show();
    //显示全部游戏厂商
	$(".friendslink_more").click(function  () {
		if($(this).hasClass("this")){
			$(this).removeClass("this");
			initHideli();
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
    		ajxget("/mer/list",info,function(data){
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
function initHideli () {
	$(".friendslink_ul li").each(function  (index) {
		if(index>9){
			$(this).hide();
		}
	})
}
initHideli();

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


function checkEmail(email){
　　
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

function getUrlInfo(url){
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
//全局封装ajax
function ajxget(url,info,success,async){
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
}



$("#seachbtn").click(function(){
	var merName = $("#searchbox_input").val();
	if(merName.length>0){
		window.location.href='./goodsClassify.html?merName='+merName;
	}
	
});
//商品类型
function getmercate(){
	var info = {
		pageNum:1,
		pageSize:10
	}
	ajxget("/merCate/list",info,function(data){
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
function getshopcarlist(){
	var info = {
		accountId:business.account?business.account.accountId:null,
		pageNum:1,
		pageSize:10
	}
	ajxget("/cartMer/list",info,function(data){
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
function gethotseachlist(){
	var info = {
		pageNum:1,
		pageSize:10000
	};
	ajxget("/merSearch/list",info,function(data){
		if(data.code==200){
			var table = $('#hotseachlist');
			var list = data.data;
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var a = document.createElement('a');
						a.id = child.merSearchId;
						a.innerText = child.name;
						a.href = './goodsClassify.html?merName='+child.name;

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
	ajxget("/account/islogin",null,function(data){
		if(data.code==200){
			//$("#nologindiv").remove();
			$("#nologindiv").css("display","none");
			$("#havelogindiv").css("display","block");
			if(!business.account){
				$("#nologindiv").css("display","block");
				$("#havelogindiv").css("display","none");
				return;
			}
			$(".alreadyLogin_namep").text(business.account.nickname);
			$("#userleve").text(business.integral.name);
			$("#exit").click(function(){
				ajxget("/account/loginout",null,function(data){
					if(data.code==200){
						sessionStorage.clear();
						location.href="/";				
					}
				});
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
	  ajxget(
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
  }  

