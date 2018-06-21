$(function(){
	
	
	business.request  = getUrlInfo(location.search);
	business.merId = business.request["goodid"];
	//我要出售此商品
	if(business.account&&business.account.roleName=="商户"){		
	$("#sellerMer").click(function(){
		console.log("销售")
	});
	}else{
		$("#sellerMer").remove();	
	}
	//我要推荐此商品
	if(business.account&&business.account.roleName=="推广户"){		
		$("#spreadMer").click(function(){
			console.log("推广")
		});
		}else{
			$("#spreadMer").remove();	
		}
	
	//获取商品订单评价
	business.merOrderCommentList;
	function getmerordercomment(){
			var info={
					merId:business.merId
			};
			ajxget("/merOrderComment/list",info,function(data){
				if(data.code==200){
					business.merOrderCommentList=data.data;
				}
			},false);	
		}
	//获取问题反馈
	business.orderProblemList;
	function getorderproblem(){
		var info={
				merId:business.merId
		};
		ajxget("/orderProblem/list",info,function(data){
			if(data.code==200){
				business.orderProblemList=data.data;
			}
		},false);	
	}
	//获取商品公共
	business.merCommon;
	function getmercommon(){
			ajxget("/merCommon/list",null,function(data){
				if(data.code==200){
					business.merCommon=data.data[0];
				}
			},false);	
	}
	//获取商品公告
	business.merNoticeList;
	function getmernotice(){
		var info={
				merId:business.merId
		};
		ajxget("/merNotice/list",info,function(data){
			if(data.code==200){
				business.merNoticeList=data.data;
			}
		},false);	
	}
	
	
	getdetail();
	
	//加入购物车
	$("#addtoshopcar").click(function(){
		console.log(11)
		if(!business.account||business.account.roleName!="用户"){	
			console.log(1122)
			alert("对不起，请注册买家账户购买")
			return ;
		}
		var info = {
			number:1,
			accountId:business.acount!=null?business.account.accountId:null,
			merId:business.merId
		};
		
		ajxget("/cartMer/add",info,function(data){
			if(data.code==200){
				alert("添加成功");
				location.reload();
			}
		});
	});
	
	//立即购买
	$("#buyfast").click(function(){
		if(!business.account||business.account.roleName!="用户"){	
			alert("对不起，请注册买家账户购买")
			return ;
		}
		var info = {
			number:1,
			accountId:business.acount!=null?business.account.accountId:null,
			merId:business.merId
		};
		
		ajxget("/cartMer/add",info,function(data){
			if(data.code==200){
				alert("添加成功");
				location.reload();
			}
		});
	});
	

		function removeAllSpace(str) {
		return str.replace(/\s+/g, "");
		}
		
	//获取商品详情	
	function getdetail(){
		var info = {
			merId:business.merId,
		
		};
		ajxget("/mer/load",info,function(data){
			if(data.code==200){
				var good = data.data[0];
				business.mer=good;
				
				//设置全局类型
				business.mer.type=good.type;
				//判断用户是否有
				$("#detailname").text(good.name);
				$("#pingtai").html(good.platform);
				$("#nowprice").text("￥"+good.unitPrice);
				$("#oldprice").text("￥"+good.oldUnitPrice);
				$("#zhekou").text(good.discount+"折");
				$("#kucun").text("库存："+good.stockNumber);
				$("#commentnum").text(good.merCommentNumber+"人评论");
				$("#paynum").text(good.saleNumber+"人付款");
				$(".detailhomeimg").attr("src",good.imgAddress);
				$(".goodjieshao").html(good.details);
				//qq分享
				(function(){
				var p = {
				url:location.href, /*获取URL，可加上来自分享到QQ标识，方便统计*/
				desc:'好的东西，共同分享', /*分享理由(风格应模拟用户对话),支持多分享语随机展现（使用|分隔）*/
				title:business.mer.name, /*分享标题(可选)*/
				summary:'',//||business.mer.summary, /*分享摘要(可选)*/
				pics:business.mer.imgAddress?business.mer.imgAddress:'', /*分享图片(可选)*/
				flash: '', /*视频地址(可选)*/
				site:'QQ分享', /*分享来源(可选) 如：QQ分享*/
				style:'201',
				width:32,
				height:32
				};
				var s = [];
				for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
				}
				var qqq=['<a class="qcShareQQDiv" href="http://connect.qq.com/widget/shareqq/index.html?',s.join('&'),'" target="_blank"></a>'].join('');
				$("#sharedQQ").append(qqq);
				})();
				//判断商品是否自营
				if(good.region==1){
					$(".detailhomeregionwrap").show();
				}else{
					$(".detailhomeregionwrap").hide();
				}
				//console.log(good.merImgList)
				for (var int = 0; int < good.merImgList.length; int++) {
					$("#merImgsWrap").children().get(int).setAttribute("src",good.merImgList[int].imgAddress);
			}
				
				$(".detail_positionul li").click(function(){
					$('.detail_positionul li').removeClass('detailclickli');
					$(this).addClass('detailclickli');
					var value1 = $(this).text();
					var value = removeAllSpace(value1);
					
					console.log(value);
					$(".goodjieshao").html("");
					if(value!="商品评价"){
						//if(value=="商品介绍"){
						
						if($(".goodjieshao").is(":hidden")){
							$(".goodjieshao").toggle();
							
						}
						if(!$(".pingjia").is(":hidden")){
							$(".pingjia").toggle();
						}
						if(value=="商品介绍"){
							$(".goodjieshao").html(good.details);
						}else if(value=="配置要求"){
							$(".goodjieshao").html(good.configuration);
						}else if(value=="安装激活"){
							$(".goodjieshao").html(good.installActivation);
						}else if(value=="问题反馈"){
							if(!business.orderProblemList){
								getorderproblem();								
							}
							if(!business.orderProblemList||business.orderProblemList.length<=0){
								$(".goodjieshao").html("<a style='display:block;text-align:center'>该商品问题反馈</a>");
								return;
							}
							for (var int = 0; int < business.orderProblemList.length; int++) {
								$(".goodjieshao").append("<div>问题：<a orderProblem='"+business.orderProblemList[int].orderProblemId+"' class='orderProblem'>"+business.orderProblemList[int].content+"</a><div ></div></div><hr/>");
							}
							$(".orderProblem").click(function(){
								var orderProblemId=$(this).attr("orderProblem");
								console.log(orderProblemId)
								for (var int = 0; int <business.orderProblemList.length; int++) {
								if(business.orderProblemList[int].orderProblemId==orderProblemId){
									if($(this).next().html()){										
									$(this).next().html("");
									}else{
										if(business.orderProblemList[int].orderProblemAnswerList.length>0){											
										$(this).next().html("回答："+business.orderProblemList[int].orderProblemAnswerList[0].content);
										}
									}
									break;
								}
								}
							});
						}else if(value=="购物指南"){
							if(!business.merCommon){
								getmercommon();								
							}
							$(".goodjieshao").html(business.merCommon.guide);
						}else if(value=="售后保障"){
							if(!business.merCommon){
								getmercommon();								
							}
							$(".goodjieshao").html(business.merCommon.guarantee);
						}else if(value=="相关公告"){
							if(!business.merNoticeList){
								getmernotice();								
							}
							if(!business.merNoticeList||business.merNoticeList.length<=0){
								$(".goodjieshao").html("<a style='display:block;text-align:center'>该商品暂无公告</a>");
								return;
							}
							for (var int = 0; int < business.merNoticeList.length; int++) {
								$(".goodjieshao").append("<div>"+(int+1)+".<a merNotice='"+business.merNoticeList[int].merNoticeId+"' class='merNotice'>"+business.merNoticeList[int].title+"</a><div ></div></div><hr/>");
							}
							$(".merNotice").click(function(){
								var merNoticeId=$(this).attr("merNotice");
								console.log(merNoticeId)
								for (var int = 0; int < business.merNoticeList.length; int++) {
								if(business.merNoticeList[int].merNoticeId==merNoticeId){
									if($(this).next().html()){										
									$(this).next().html("");
									}else{
										$(this).next().html(business.merNoticeList[int].content);
									}
									//$(".goodjieshao").html(merNoticeList[int].content);									
									break;
								}
								}
							});
						}
						
					}else if(value=="商品评价"){
						if(!$(".goodjieshao").is(":hidden")){
							$(".goodjieshao").toggle();
						}
						if($(".pingjia").is(":hidden")){
							$(".pingjia").toggle();
							
						}
						console.log(value1);
						if(!business.merOrderCommentList){
							getmerordercomment();								
						}
						if(!business.merOrderCommentList||business.merOrderCommentList.length<=0){
							$(".pingjia").html("<a style='display:block;text-align:center'>该商品暂无评价</a>");
							return;
						}
						//评价数
						$("#merOrderCommentNumber").text(good.merCommentNumber);
						//星星
						$("#merScore").text(good.merScore);
						$("#merScoreXing li").css("color","#000");
						$("#merScoreXing li").each(function(index){
							if(good.merScore<1){
							}else if(good.merScore<2){
								if(index<=0){
									$(this).css("color","#FF7400")									
								}
							}else if(good.merScore<3){
								if(index<=1){
									$(this).css("color","#FF7400")									
								}
							}else if(good.merScore<4){
								if(index<=2){
									$(this).css("color","#FF7400")									
								}
							}else if(good.merScore<5){
								if(index<=3){
									$(this).css("color","#FF7400")									
								}
							}else if(good.merScore>=5){
								if(index<=4){
									$(this).css("color","#FF7400")									
								}
							}
						});
						//评价
						$("#pingjialist").html("");
						for (var i = 0; i < business.merOrderCommentList.length; i++) {
							var singleMerScore=business.merOrderCommentList[i].merScore;
							$("#pingjialist").append('' +
						'<div class="clearfix" style="border: solid;border-color: #EBEBEB;border-width: 0px 0px 1px 0px;">' +
							'<img style="margin-top: 20px;margin-left: 20px;width: 60px;height: 60px;float: left;"  src="'+business.merOrderCommentList[i].account.icon+'"/>'+
							'<div style="float: left;margin-left: 20px;margin-top: 20px;width: 780px;">' +
								'<div style="" class="clearfix">' +
									'<p style="font-size: 14px;float: left;">'+business.merOrderCommentList[i].account.nickname+'</p>' +
									'<p style="font-size: 14px;float: left;margin-left: 15px;color: #a6a6a6;">'+business.merOrderCommentList[i].account.country+'</p>' +
									'<p style="font-size: 14px;float: right;color: #a6a6a6;">'+business.merOrderCommentList[i].createDate+'</p>' +
								'</div>' +
								'<div class=" clearfix" >' +
									'<div style="float: left;">' +
										'<ul class="comment" id="singleMerScore">' +
										    '<li class="commentli">☆</li>' +
										    '<li class="commentli">☆</li>' +
										    '<li class="commentli">☆</li>' +
										    '<li class="commentli">☆</li>' +
										    '<li class="commentli">☆</li>' +
										'</ul>' +
									'</div>' +
									'<p style="float: left;line-height: 30px;margin-left: 10px;">'+business.merOrderCommentList[i].merScore+'</p>' +
								'</div>' +
								'<p style="font-size: 16px;margin-bottom: 10px;">'+business.merOrderCommentList[i].content+'</p>' +
							'</div>' +
						'</div>');
							$("#singleMerScore li").css("color","#000");
							$("#singleMerScore li").each(function(index){
								if(singleMerScore<1){
								}else if(singleMerScore<2){
									if(index<=0){
										$(this).css("color","#FF7400")									
									}
								}else if(singleMerScore<3){
									if(index<=1){
										$(this).css("color","#FF7400")									
									}
								}else if(singleMerScore<4){
									if(index<=2){
										$(this).css("color","#FF7400")									
									}
								}else if(singleMerScore<5){
									if(index<=3){
										$(this).css("color","#FF7400")									
									}
								}else if(singleMerScore>=5){
									if(index<=4){
										$(this).css("color","#FF7400")									
									}
								}
							});
						}
						
						
					}
				});
				
			}
			
		},false)
	}
//获取推荐商品
	function gettuijian(){
		var info = {
				type:business.mer.type,
				pageNum:1,
				pageSize:5
		};
		ajxget("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
				var table = $('.detailgood');
				for(var i = 0; i < list.length; i++) {
					var good = list[i];
					var li = document.createElement('div');
					li.className = 'detailgooddiv clearfix merRecommend';
					li.style.cursor="pointer";
					li.id = good.merId;
					var html = '<div style="text-align: center;" ><img src="'+good.imgAddress+'"  class="width220 height160"/></div><div class="width220"><p class="color_333 tuijiangoodname">'+good.name+'</p></div><div class="width220 margin_top10"><p class="goodoldprice">市场价¥'+good.oldUnitPrice+'</p><p class="goodnewprice">¥'+good.unitPrice+'</p></div><div class="width220 margin_top10"><p class="tuijianbuynum">'+good.saleNumber+'人付款</p><p class="tuijiancommentnum">'+good.merCommentNumber+'人评论</p></div>';
					li.innerHTML = html;
					table.append(li); 
				}
				$(".merRecommend").each(function(){
					$(this).click(function() {
						location.href="gooddetail.html?goodid="+$(this).attr("id");
					});
				});
			}
		})
	}
	
	gettuijian();
});
