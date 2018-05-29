//localStorage.setItem("urlHost","http://app.jiehao9.com");
sessionStorage.setItem("urlHost","http://localhost:9000");
$(function(){
	$(".tab_bigbox .tab_box").eq(0).show();
	$(".tab_bigbox1 .tab_box1").eq(0).show();
    //显示全部游戏厂商
	$(".friendslink_more").click(function  () {
		if($(this).hasClass("this")){
			$(this).removeClass("this");
			initHideli();
		}else{
			$(this).addClass("this");
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
		}else{
			$(".banner_classiul").stop().slideUp();
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
	　　　　alert("邮箱格式不正确")
	　　　　return false;
		}
 	}else{
 		alert("请输入邮箱")
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

function ajxget(url,info,success){
	var host = sessionStorage.getItem("urlHost");
			$.ajax({
				url:host+url,
				data:info,
				dataType:'json',//服务器返回json格式数据
				type:'get',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；
				xhrFields: {withCredentials: true},
				success: function(data){
		        console.log(data);
		        success(data);
		        if(data.code==200){
		        	
		        }else{
		        	if(data.code!=30002&&data.code!=40004){
		        		alert(data.msg);
		        	} 
		        	if(data.code==40004){
		        		//alert("您的账号登录超时,请重新登录!");
		        		//window.location.href = "login.html";
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


function getshopcarlist(){
	var info = {
		accountId:JSON.parse(sessionStorage.getItem("account")).accountId,
		pageNum:1,
		pageSize:5
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
$("#sellgologin").click(function(){
	window.location.href = "login.html?roletype=1";
});
$("#tuiguanggologin").click(function(){
	window.location.href = "login.html?roletype=2";
});
$("#usergologin").click(function(){
	window.location.href = "login.html?roletype=3";
});
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

function islogin(){
	ajxget("/account/islogin",null,function(data){
		if(data.code==200){
			//$("#nologindiv").remove();
			$("#nologindiv").css("display","none");
			$("#havelogindiv").css("display","block");
			var account = JSON.parse(sessionStorage.getItem("account"));
			if(!account){
				$("#nologindiv").css("display","block");
				$("#havelogindiv").css("display","none");
				return;
			}
			var integrall = JSON.parse(sessionStorage.getItem("integrall"));
			$(".alreadyLogin_namep").text(account.nickname);
			$("#userleve").text(integrall.name);
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
islogin();
