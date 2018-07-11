/**
 * 商户商品详情
 */	
	business.request  = business.getUrlInfo(location.search);
	business.merId = business.request["goodid"];
	if(!business.merId){
		history.go(-1);
	}
	//获取商品订单评价
	business.merOrderCommentList;
	business.getMerOrderCommentList=function(){
			var info={
					merId:business.merId
			};
			business.ajax("/merOrderComment/list",info,function(data){
				if(data.code==200){
					business.merOrderCommentList=data.data;
				}
			},false);	
		}
	//获取问题反馈
	business.orderProblemList;
	business.getOrderProblemList=function(){
		var info={
				merId:business.merId
		};
		business.ajax("/orderProblem/list",info,function(data){
			if(data.code==200){
				business.orderProblemList=data.data;
			}
		},false);	
	}
	//获取商品公共
	business.merCommon;
	business.getMerCommon=function(){
			business.ajax("/merCommon/list",null,function(data){
				if(data.code==200){
					business.merCommon=data.data[0];
				}
			},false);	
	}
	//获取商品公告
	business.merNoticeList;
	business.getMerNoticeList=function(){
		var info={
				merId:business.merId
		};
		business.ajax("/merNotice/list",info,function(data){
			if(data.code==200){
				business.merNoticeList=data.data;
			}
		},false);	
	}
	//获取商品详情	
	business.getMerDetail=function(){
	var info = {
		merId:business.merId,
	
	};
	business.ajax("/mer/load",info,function(data){
		if(data.code==200){
			var good = data.data[0];
			business.mer=good;
			//设置全局类型
			business.mer.type=good.type;
			//下架
			$("#undercarriage").off().click(function() {
				business.myConfirm("确定下架吗？",function(){
					business.ajax("/mer/update",{
						accountId:business.account.accountId,
						merId:business.merId,
						status:0//下架
					},function(data){
						if(data.code==200){
							business.myLoadingToast("下架成功");
							location.href="../sell/sell_index.html";
						}else{
							business.myLoadingToast(data.msg);
							
						}
						});
				});
			})
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
			//销售总收入=商品价格*销售数
			$("#merSalePrice").text(parseFloat(good.unitPrice*good.saleNumber).toFixed(2));
			//销量
			$("#merSaleNumber").text(good.saleNumber);
			//库存
			$("#merStockNumber").text(good.stockNumber);
			//商品种类
			$("#merCateName").text(good.merCate.name);
			//平台分成
			$("#platformProportion").text(good.platformProportion);
			//更新时间
			$("#updateDate").text(good.updateDate);
			
			//console.log(good.merImgList)
			for (var int = 0; int < good.merImgList.length; int++) {
				$("#merImgsWrap").append('<img class="detailimg" src="'+good.merImgList[int].imgAddress+'" />');
				//$("#merImgsWrap").children().get(int).setAttribute("src",good.merImgList[int].imgAddress);
		}
			
			$(".detail_positionul li").click(function(){
				$('.detail_positionul li').removeClass('detailclickli');
				$(this).addClass('detailclickli');
				var value1 = $(this).text();
				var value = value1.replace(/\s+/g, "");
				
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
							business.getOrderProblemList();								
						}
						if(!business.orderProblemList||business.orderProblemList.length<=0){
							$(".goodjieshao").html("<div style='display:block;text-align:center;'>该&nbsp;商&nbsp;品&nbsp;暂&nbsp;无&nbsp;问&nbsp;题&nbsp;反&nbsp;馈</div>");
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
							business.getMerCommon();								
						}
						$(".goodjieshao").html(business.merCommon.guide);
					}else if(value=="售后保障"){
						if(!business.merCommon){
							business.getMerCommon();								
						}
						$(".goodjieshao").html(business.merCommon.guarantee);
					}else if(value=="相关公告"){
						if(!business.merNoticeList){
							business.getMerNoticeList();								
						}
						if(!business.merNoticeList||business.merNoticeList.length<=0){
							$(".goodjieshao").html("<div style='display:block;text-align:center'>该&nbsp;商&nbsp;品&nbsp;暂&nbsp;无&nbsp;公&nbsp;告</div>");
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
						business.getMerOrderCommentList();								
					}
					if(!business.merOrderCommentList||business.merOrderCommentList.length<=0){
						$(".pingjia").html("<div style='display:block;text-align:center;height:60px;line-height:60px;'>该&nbsp;商&nbsp;品&nbsp;暂&nbsp;无&nbsp;评&nbsp;价</div>");
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
						var　country=business.merOrderCommentList[i].account.country||'中国';
						var　icon=business.merOrderCommentList[i].account.icon||'img/touxiang1.png';
						$("#pingjialist").append('' +
					'<div class="clearfix" style="border: solid;border-color: #EBEBEB;border-width: 0px 0px 1px 0px;">' +
						'<img style="margin-top: 20px;margin-left: 20px;width: 60px;height: 60px;float: left;"  src="'+icon+'"/>'+
						'<div style="float: left;margin-left: 20px;margin-top: 20px;width: 780px;">' +
							'<div style="" class="clearfix">' +
								'<p style="font-size: 14px;float: left;">'+business.merOrderCommentList[i].account.nickname+'</p>' +
								'<p style="font-size: 14px;float: left;margin-left: 15px;color: #a6a6a6;">国籍：'+country+'</p>' +
								'<p style="font-size: 14px;float: right;color: #a6a6a6;">'+business.merOrderCommentList[i].createDate+'</p>' +
							'</div>' +
							'<div class=" clearfix" >' +
								'<div style="float: left;">' +
									'<ul class="comment" id="singleMerScore'+i+'">' +
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
						$("#singleMerScore"+i+" li").css("color","#000");
						$("#singleMerScore"+i+" li").each(function(index){
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
								console.log(index)
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
business.getMerDetail();
//点击补货，显示增加卡密
$("#addMerCardCipherBtn").off().click(function(){
	$("#addCardCipherWrap").show();
});
//增加卡密
//默认可以切换，不锁定
business.addMerCardCipherIsClock=false;
//卡密类型
business.merCardCipherType=1;//默认是1上传文件的字符串文字，2字符串文字，3字符串图片
//上传文件
$("#addMerCardCipherFileUpload").click(function(){
	$("#addMerCardCipherFile").click();
});
//商品卡密
business.merCardCipherCodes=[];
$("#addMerCardCipherFile").change(function(){
	//
	business.fileUpload(
		    {inputfile:$("#addMerCardCipherFile"),
		    	photoExt:[".xls",".xlsx"],
		    ajaxObj:{
		        formData:[
		            {key:"excel",value:$("#addMerCardCipherFile").get(0).files[0]}
		            ],
		        url:business.url+"/tool/importExcel",
		        success:function(data){
		            if(data.code==200){
		            	console.log(data.data)
		            //成功后锁
		            	business.addMerCardCipherIsClock=true;
		            for (var i = 0; i < data.data.length; i++) {
		            	business.merCardCipherCodes.push(data.data[i])
		            	}
		            $("#addMerCardCipherFileCodes").html(business.merCardCipherCodes.toString());	
		            business.myPrevToast("上传成功",null,"remove");
		            }
		        }
		    }
		}
		);
});
//上传图片 ,自己服务器上传
/*	$("#addMerCardCipherImgUpload").click(function(){
		$("#addMerCardCipherImg").click();
	});
	$("#addMerCardCipherImg").change(function(){
		//
		business.fileUpload(
			    {inputfile:$("#addMerCardCipherImg"),
			    	photoExt:[".jpg",".png"],
			    ajaxObj:{
			        formData:[
			            {key:"editorUpload",value:$("#addMerCardCipherImg").get(0).files[0]}
			            ],
			        url:business.url+"/tool/file/add",
			        success:function(data){
			            if(data.code==200){
			            	console.log(data.data)
			            //成功后锁
			            business.addMerCardCipherIsClock=true;
			            var imghtml="";
			            for (var i = 0; i < data.data.length; i++) {
			            	business.merCardCipherCodes.push(data.data[i]);
			            	imghtml='<img class="detailimg" style="float:none;" src="'+data.data[i]+'" />'
			            	$("#merCardCipherImgList").append(imghtml);	
			            }
			            business.myPrevToast("上传成功",null,"remove");
			            }
			        }
			    }
			}
			);
	});*/
//七牛云上传图片
business.qiniuUploadImg("#addMerCardCipherWrap3", "#addMerCardCipherImgUpload", "#addMerCardCipherImg",null,function(url){
//成功后锁
business.addMerCardCipherIsClock=true;
var imghtml="";
	business.merCardCipherCodes.push(url);
	imghtml='<img class="detailimg" style="float:none;" src="'+url+'" />'
	$("#merCardCipherImgList").append(imghtml);	
	business.myPrevToast("上传成功",null,"remove");
});
//点击选择商品卡卡密上传类型
$(".sellnew_positionul li").click(function(){
	if(business.addMerCardCipherIsClock){
		business.myLoadingToast("不能选多个种类上传");
		return;
	}
	$('.sellnew_positionul li').removeClass('clicknewli');
	$(this).addClass('clicknewli');
	if($(this).attr("id")=='addMerCardCipherType1'){
		$("#addMerCardCipherWrap1").show();
		$("#addMerCardCipherWrap2").hide();
		$("#addMerCardCipherWrap3").hide();
		business.merCardCipherType=1;
	}else if($(this).attr("id")=='addMerCardCipherType2'){
		$("#addMerCardCipherWrap1").hide();
		$("#addMerCardCipherWrap2").show();
		$("#addMerCardCipherWrap3").hide();
		business.merCardCipherType=2;
	}else if($(this).attr("id")=='addMerCardCipherType3'){
		$("#addMerCardCipherWrap1").hide();
		$("#addMerCardCipherWrap2").hide();
		$("#addMerCardCipherWrap3").show();
		business.merCardCipherType=3;
	}
	
});
//增加商品卡密
$("#addMerCardCipher").click(function(){
	if(business.merCardCipherType!=1&&business.merCardCipherType!=2&&business.merCardCipherType!=3){
		business.myLoadingToast("卡密必填");
		return;
	}
	if(business.merCardCipherType==2){
		business.merCardCipherCodes=[];//制空
		var addMerCardCipherText=$("#addMerCardCipherText").val();
		var amcctarray=addMerCardCipherText.split(",");
		for (var i=0;i< amcctarray.length;i++) {
			if(amcctarray[i]){
				business.merCardCipherCodes.push(amcctarray[i]);						
			}
		}
	}
	if(business.merCardCipherCodes.length<=0){
		business.myLoadingToast("缺少卡密");
		return;
	}
	var info = {
			accountId:business.account.accountId,//商户id
			merId:business.mer.merId,
			merCardCipherType:business.merCardCipherType,
			merCardCiphers:business.merCardCipherCodes.toString()
		};
		business.ajax("/mer/addMerCardCipher",info,function(data){
			if(data.code==200){
				business.myLoadingToast("添加成功");
				setTimeout(function(){
					location.reload();						
				},1000)
			}else{
				business.myLoadingToast(data.msg)
			}
			});
});



//申请自营
$("#applyRegion3").off().click(function(){
	var applyMerImgList="";
	for (var i = 0; i < business.mer.merImgList.length; i++) {
		applyMerImgList+='<img style="width:180px;height:180px;" src="'+business.mer.merImgList[i].imgAddress+'"/>';
	}
	var html='<div style="text-align:left;">'
				+'<div style="height:50px;line-height:50px;background-color:#ececec;color:#FF7400;text-align:left;text-indent:2rem;">商品自营申请</div>'
				+'<div style="height:180px;margin-top:10px;padding-left:50px;">'
					+'<span style="vertical-align:top;display:inline-block;height:100%;line-height:180px;">商品图片：</span>'
					+'<span id="applyMerImgList" style="display:inline-block;height:100%;line-height:180px;">'
						+applyMerImgList
					+'</span>'
				+'</div>'
				+'<div style="height:20px;margin-top:10px;padding-left:50px;">'
					+'<span>商品名称：</span>'
					+'<span>'+business.mer.name+'</span>'
				+'</div>'
				+'<div style="height:20px;margin-top:10px;padding-left:50px;">'
					+'<span>商品种类：</span>'
					+'<span>'+business.mer.merCate.name+'</span>'
				+'</div>'
				+'<div style="height:20px;margin-top:10px;padding-left:50px;">'
					+'<span>商品价格：</span>'
					+'<span style="color:red;">￥'+business.mer.unitPrice+'</span>'
				+'</div>'
				+'<div style="height:20px;margin-top:10px;padding-left:50px;">'
					+'<span>平台技术服务费：</span>'
					+'<input id="applyPlatformProportion" style="height:38px;width:366px;border-bottom:1px solid #ccc;" placeholder="您需要填写每售出一笔需要给平台的分成比例"/>&nbsp;&nbsp;%'
				+'</div>'
				+'<div style="height:50px;margin-top:50px;text-align:center;">'
					+'<button id="closeApplyRegion3" style="height:50px;width:100px;color:#fff;margin:20px;">取消</button>'
					+'<button id="applyRegion3Commit" style="height:50px;width:100px;color:red;margin:20px;border:1px solid red;background-color:#fff;">确定</button>'
				+'</div>'
			+'</div>';
	//显示模板
	business.myTemplate(html,"#closeApplyRegion3");
	//提交自营申请
	$("#applyRegion3Commit").off().click(function(){
		var applyPlatformProportion=$("#applyPlatformProportion").val().trim();
		if(!business.userVerification.merPrice.code.test(applyPlatformProportion)||applyPlatformProportion<=0||applyPlatformProportion>100){
			business.myLoadingToast("自营申请平台分成比例必须大于0，小于等于100");
			return;
		}
		var info = {
				accountId:business.account.accountId,//商户id
				type:4,//4商品申请自营
				businessId:business.mer.merId,
				imgAddress:business.mer.imgAddress,
				content:'{"merName":"'+business.mer.name+'","merCateName":"'+business.mer.merCate.name+'","merPrice":"'+business.mer.unitPrice+'","merPlatformProportion":"'+applyPlatformProportion+'"}'
			};
			business.ajax("/notice/add",info,function(data){
				if(data.code==200){
					business.myLoadingToast("申请成功");
					$("#closeApplyRegion3").click();
				}else{
					business.myLoadingToast(data.msg)
				}
				});
	});
});