
 //订单详情
   business.getSelectOrderDetail=function(){
   	var request  = business.getUrlInfo(location.search);
	var orderId = request["orderId"];
	var info={
			orderId:orderId,
			accountId:business.account!=null?business.account.accountId:null
	};
   	business.ajax("/order/load",info,function(data){
		if(data.code==200){
			console.log(data)
			var child=data.data[0];
			//折扣卷
			var couponvalue="未使用";
			if(child.orderDetailList[0].coupon){
				couponvalue=child.orderDetailList[0].coupon.discount+"折";
			}
			$("#orderDetailWrap").html(
					'<div style="width: 100%;height: 80px;">'
					+'<div style="width: 50%;height: 100%;position:relative;float: left;">'
						+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
							+'<p style="font-size: 14px;margin-left: 10px;">商品名称：'+child.orderDetailList[0].name+'</p>'
							+'<div style="margin-left: 10px;margin-top: 20px;">'
								+'<p style="font-size: 14px;float: left;">单价：¥'+child.orderDetailList[0].unitPrice+'</p>'
								+'<p style="font-size: 14px;float: right;">数量：'+child.orderDetailList[0].number+'</p>'
							+'</div>'
						+'</div>'
					+'</div>'
					+'<div style="width: 40%;height: 100%;position:relative;float: left;">'
						+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
							+'<p style="font-size: 14px;margin-left: 10px;">商品种类：'+child.orderDetailList[0].merCateName+'</p>'
							+'<p style="font-size: 14px;margin-left: 10px;margin-top: 20px;">折扣券：'+couponvalue+'</p>'
						+'</div>'
					+'</div>'
					+'<div style="width: 10%;height: 100%;position:relative;float: left;">'
						+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
							+'<p style="font-size: 14px;margin-left: 10px;float: left;">总金额：</p>'
							+'<p style="font-size: 14px;margin-left: 10px;float: left;color: #BB0404;" id="orderTotalPrice">¥'+child.orderDetailList[0].totalPrice+'</p>'
						+'</div>'
					+'</div>'
				+'</div>'
				+'<hr align="center" width="100%" color="#E6E6E6" size="1" />'
				+'<div style="width: 100%;height: 200px;">'
					+'<div style="width: 30%;height: 80px;float: left;margin-top: 10px;">'
						+'<div style="position: absolute;">'
							+'<p style="font-size: 14px;margin-left: 10px;">订单编号：'+child.orderNumber+'</p>'
							+'<div style="margin-left: 10px;margin-top: 20px;">'
								+'<p style="font-size: 14px;float: left;">卡密：<span id="cardChiperList">******</span></p>'
								+'<a style="font-size: 14px;color: #FF7400;margin-left: 30px;float: left;display: block;" id="getCardChiperList">提取卡密</a>'
							+'</div>'
						+'</div>'
					+'</div>'
					+'<div style="width: 40%;height: 80px;float: left;margin-top: 10px;">'
						+'<div style="position: absolute;">'
							+'<p style="font-size: 14px;margin-left: 10px;">下单时间：'+child.createDate+'</p>'
							+'<p style="font-size: 14px;margin-left: 10px;margin-top: 20px;">收货人：'+child.orderReceiptInfo.name+'</p>'
						+'</div>'
					+'</div>'
					+'<div style="width: 30%;height: 80px;float: left;margin-top: 10px;">'
						+'<div style="position: absolute;">'
							+'<p style="font-size: 14px;margin-left: 10px;">支付时间：'+child.paymentDate+'</p>'
							+'<p style="font-size: 14px;margin-left: 10px;margin-top: 20px;">收货邮箱：'+business.account.email+'</p>'
						+'</div>'
					+'</div>'
					+'<div style="margin-left: 10px;margin-top: 20px;">'
						+'<p style="font-size: 14px;float: left;">我的评价：<span  id="merOrderCommentList"></span></p>'
						+'<a class="addcommentbtn">添加评价</a>'
					+'<div style="clear: both;"></div>'
					+'</div>'
					+'<div style="width: 100%;margin-top: 20px;height: auto;" id="orderProblemBtnWrap">'
						+'<a class="questionbtn" >创建问题单</a>'
						+'<p style="font-size: 14px;color: #a6a6a6;float: right;line-height: 30px;">对此单有疑问？</p>'
					+'</div>'
				+'</div>'	
			);
			//提取卡密
			$("#getCardChiperList").on("click", function() {
				
				business.merOrderCardCipherList(child);
			})
			//评论显示隐藏
			$(".addcommentbtn").click(function(){
				if(!$("#question").is(":hidden")){
			       $("#question").toggle();    //如果元素为隐藏,则将它显现
				}
				$("#comment").toggle();
			});
			$("#canclecomment").click(function(){
				$("#comment").toggle();
			});
			//问题单显示隐藏
			$(".questionbtn").click(function(){
				if(!$("#comment").is(":hidden")){
					$("#comment").toggle();    //如果元素为隐藏,则将它显现
				}
				$("#question").toggle();
			});
			$("#canclequestion").click(function(){
				$("#question").toggle();
			});
			//订单问题选项,默认
			$($("input[name='orderProblemRadio']")[3]).prop("checked", "checked");
			//订单问题列表
			business.orderProblemList(child);
			//订单问题提交
			$("#orderProblemAdd").on("click", function() {
				var reason=0;
				$("input[name='orderProblemRadio']").each(function(i, element) {
					if($(this).prop("checked")){
						reason=$(this).val();
					}
				})
				var content=$("#orderProblemContent").val().trim();
				if(content.length<=0||content.length>200){
					business.myLoadingToast("字数1-200")
					return;
				}
				business.orderProblemAdd(child,reason,content);
			})
			//订单商品列表
			business.merOrderCommentList(child);
			//订单商品评价
			$("#merOrderCommentAdd").on("click", function() {
				if(!business.merScore){
					business.myLoadingToast("还没评分!")
					return;
				}
				var content=$("#merOrderCommentContent").val().trim();
				if(content.length<=0||content.length>200){
					business.myLoadingToast("字数1-200")
					return;
				}
				business.merOrderCommentAdd(child,content);
			})
		}});
   	
   }
   business.getSelectOrderDetail(); 
   //评价星星
   business.xinxin=function(){
	   
			var shixin = "★";
            var kongxin = "☆";
            /*var flag = false;//没有点击*/
            $(".commentli").mouseenter(function(){
                /*$(this).text(shixin).prevAll().text(shixin);
                $(this).nextAll().text(kongxin);*/
                $(this).text(shixin).prevAll().text(shixin).end().nextAll().text(kongxin);
            });
            $(".comment").mouseleave(function(){
               /* if(!flag){
                    $("li").text(kongxin);
                }*/
                $(".commentli").text(kongxin);
                $(".clicked").text(shixin).prevAll().text(shixin);
            });
            $(".commentli").on("click",function(){
               /* $(this).text(shixin).prevAll().text(shixin);
                $(this).nextAll().text(kongxin);
                flag = true;*/
                $(this).addClass("clicked").siblings().removeClass("clicked");
                business.merScore=parseFloat($(this).index()+1).toFixed(2);
                //console.log( business.merScore)
                
            });
   }
   business.xinxin();
 //商品订单卡密列表
   business.merOrderCardCipherList=function(order){
   	var info={
   			orderId:order.orderId,
   			accountId:business.account!=null?business.account.accountId:null
   	};
    	business.ajax("/merOrderCardCipher/list",info,function(data){
   		if(data.code==200){
   			var moccl=data.data;
   			console.log(moccl)
   			var c="";
   			for (var i = 0; i < moccl.length; i++) {
   				var ia=moccl[i].imgAddress;
   				if(!ia){
				c+='<div style="margin:10px;">'
   					+'<span style="display:inline-block;vertical-align:middle;">卡密码：'+moccl[i].code+'</span>'
   					+'</div>'
   					+'<br/>';
   				}else{
   				c+='<div style="margin:10px;">'
   					+'<span style="display:inline-block;vertical-align:middle;">卡密码：'+moccl[i].code+'</span>'
   					+'&nbsp;'
   					+'<span  style="display:inline-block;vertical-align:middle;"><img style="width:300px;height:200px;" src="'+ia+'"/></span>'
   					+'</div>'
   					+'<br/>';
   				}
   			}
   			
   			$("#cardChiperList").html("");
   			business.myTemplate(c);
   		}else{
   			
   		}
   		});
   };

//商品订单评价列表
business.merOrderCommentList=function(order){
	var info={
			orderId:order.orderId,
			accountId:business.account!=null?business.account.accountId:null
	};
   	business.ajax("/merOrderComment/list",info,function(data){
		if(data.code==200){
			//console.log(data)
			var content="已评价！";
			content+="<div>" +
			"<div>最新评分："+data.data[0].merScore+"星</div>" +
			"<div>最新内容："+data.data[0].content+"</div>" +
			"</div>";
			$("#merOrderCommentList").append(content);
			$(".addcommentbtn").remove();
		}else{
			$("#merOrderCommentList").text("未评价");
		}
		});
};
//商品订单评价
business.merOrderCommentAdd=function(order,content){
	var info={
			orderId:order.orderId,
			merId:order.orderDetailList[0].merId,
			merScore:business.merScore,
			content:content,
			accountId:business.account!=null?business.account.accountId:null
	};
	business.ajax("/merOrderComment/add",info,function(data){
		if(data.code==200){
			//console.log(data)
			business.myLoadingToast("评价成功！")
			location.reload();
		}
	});
};
//商品订单问题列表
business.orderProblemList=function(order){
	var info={
			orderId:order.orderId,
			accountId:business.account!=null?business.account.accountId:null,
			pageSize:100
	};
 	business.ajax("/orderProblem/list",info,function(data){
		if(data.code==200){
			//隐藏创建按钮
			$("#orderProblemBtnWrap").hide();
			var opl=data.data;
			var substatusvalue="待解决";
			if(order.substatus==1){
				substatusvalue="待解决";
			}else if(order.substatus==2){
				substatusvalue="解决中";
			}else if(order.substatus==3){
				substatusvalue="申请退款";
			}else if(order.substatus==4){
				substatusvalue="已退款";
			}else if(order.substatus==5){
				substatusvalue="已解决";
			}
			var html1='<div class="clearfix" style="line-height: 20px;">'
						+'<p class="titlep" style="float: left;width: 140px;">问题申请历史</p>'
						+'<p style="float: left;margin-left: 30px;">申请次数：'+opl.length+'</p>'
						+'<p style="float: left;margin-left: 20px;">问题状态：</p>'
						+'<p style="float: left;color: #e04600;">'+substatusvalue+'</p>'
					+'</div>';
			var html2='';
			for (var i = 0; i < opl.length; i++) {
				var child=opl[i];
				var djc="初";
				if(child.number==1){
					djc="初";
				}else if(child.number==2){
					djc="二";
				}else if(child.number==3){
					djc="三";
				}else if(child.number==4){
					djc="四";
				}else if(child.number==5){
					djc="五";
				}else if(child.number==6){
					djc="六";
				}else if(child.number==7){
					djc="七";
				}else if(child.number==8){
					djc="八";
				}else if(child.number==9){
					djc="九";
				}else if(child.number==10){
					djc="十";
				}else {
					djc=(i+1);
				}
				var reason="其他";
				if(child.reason==0){
					reason="其他";
				}else if(child.reason==1){
					reason="不能充值";
				}else if(child.reason==2){
					reason="卡密无效";
				}else if(child.reason==3){
					reason="提示卡密错误";
				}
				var answercontent="商家还没回复，请耐心等待商家回复.";
				if(child.orderProblemAnswerList[0]){
					answercontent=child.orderProblemAnswerList[0].content;
				}
				html2+='<div class="clearfix" style="margin-top: 20px;">'
					+'<p style="width: 16%;float: left;padding-left: 20px;">'+djc+'次申请</p>'
					+'<div style="width: 59%;float: left;padding-left: 20px;">'
					+'<p style="font-size: 14px;">申请原因：'+reason+'</p>'
					+'<p style="font-size: 14px;">问题描述：'+child.content+'</p>'
					+'<p style="font-size: 14px;">商家回复：'+answercontent+'</p>'
					+'</div>'
					+'<p style="width: 25%;float: left;padding-left: 20px;">申请时间：'+child.createDate+'</p>'
					+'</div>';
			}
			
			var html3='<div class="clearfix" style="margin-top: 20px;">'
						+'<div  style="width: auto;text-align: center;margin: 50px 0 5px 0;float:right;">'
						+'<a class="tuikuanbtn" style="margin: 0 5px;border:1px solid green;color:green;" id="questionbtn2">再次申请</a>'
						+'<a class="tuikuanbtn" style="margin: 0 5px" id="applyRefund">申请退款</a>'
						+'<a class="finishquestionbtn" id="alreadyResolve">已解决</a>'
						+'</div>'
					+'</div>';
			var html=html1+html2+html3;
			if(order.substatus==3||order.substatus==4||order.substatus==5){
				html=html1+html2;
			}
			//显示问题列表
			$("#orderProblemListWrap").html(html);
			//再次申请
			$("#questionbtn2").click(function() {
				$(".questionbtn").click();
			});
			//申请退款
			$("#applyRefund").click(function() {
				business.myConfirm("确定申请退款？",function(){
					var info={
							orderId:order.orderId,
							status:5,
							substatus:3,
							accountId:business.account!=null?business.account.accountId:null
					};
					business.ajax("/order/update",info,function(data){
						if(data.code==200){
							//console.log(data)
							business.myLoadingToast("申请成功！")
							location.reload();
						}
					});
				});
			});
			//已解决
			$("#alreadyResolve").click(function() {
				business.myConfirm("确定已经解决？",function(){
					var info={
							orderId:order.orderId,
							status:5,
							substatus:5,
							accountId:business.account!=null?business.account.accountId:null
					};
					business.ajax("/order/update",info,function(data){
						if(data.code==200){
							//console.log(data)
							business.myLoadingToast("提交成功！")
							location.reload();
						}
					});
				});
			});
			
		}else{
			$("#orderProblemListWrap").hide();
		}
		});
};
//商品订单问题提交
business.orderProblemAdd=function(order,reason,content){
	var info={
			orderId:order.orderId,
			merId:order.orderDetailList[0].merId,
			reason:reason,
			content:content,
			accountId:business.account!=null?business.account.accountId:null
	};
	business.ajax("/orderProblem/add",info,function(data){
		if(data.code==200){
			//console.log(data)
			business.myLoadingToast("问题提交成功！")
			location.reload();
		}
	});
};