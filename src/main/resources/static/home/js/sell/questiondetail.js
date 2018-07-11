/**
 * 问题单详情
 */	
	business.request  = business.getUrlInfo(location.search);
	business.orderId = business.request["orderId"];
	if(!business.orderId){
		history.go(-1);
	}
	//获取订单详情 问题单
	business.getOrderDetailByStatus5=function(){
		var info = {
			accountId:business.account.accountId,//商户id
			orderId:business.orderId
		};
		
		business.ajax("/order/load",info,function(data){
			if(data.code==200){
				var html="";
					business.order=data.data[0];
					//商品订单问题
					business.orderProblemList(business.order);
					//获取问题人信息
					$("#orderProblemAccountName").text(business.order.orderProblemList[0].account.nickname||business.order.orderProblemList[0].account.email)
					//总金额
					$(".totalPrice").text(business.order.orderDetailList[0].totalPrice);
					//商品名称
					$("#merName").text(business.order.orderDetailList[0].name);
					//商品种类
					$("#merCateName").text(business.order.orderDetailList[0].merCateName);
					//单价
					$("#unitPrice").text(business.order.orderDetailList[0].unitPrice);
					//数量
					$("#number").text(business.order.orderDetailList[0].number);
					//折扣券
					var coupondiscount="未使用";
					if(business.order.orderDetailList[0].coupon){
						coupondiscount=business.order.orderDetailList[0].coupon.discount;
					}
					$("#coupon").text(coupondiscount);
					//订单编号
					$("#orderNumber").text(business.order.orderNumber);
					//下单时间
					$("#createDate").text(business.order.createDate);
					//支付日期
					$("#paymentDate").text(business.order.paymentDate);
					//收货人
					$("#orderReceiptInfoName").text(business.order.orderReceiptInfo.name);
					//收货邮箱
					$("#orderReceiptInfoEmail").text(business.order.orderProblemList[0].account.email);
					var info2 = {
						accountId:business.account.accountId,//商户id
						merId:business.order.orderDetailList[0].merId
					};
					
					business.ajax("/mer/load",info2,function(data2){
						if(data2.code==200){
							var html="";
								business.mer=data2.data[0];
								console.log(business.mer)
								//提成比例
								$("#platformProportion").text(business.mer.platformProportion);
								//平台提成
								$("#platformPrice").text(parseFloat(business.order.orderDetailList[0].totalPrice*business.mer.platformProportion/100).toFixed(2))
								//订单收入
								$("#sellerOrderPrice").text((business.order.orderDetailList[0].totalPrice-parseFloat(business.order.orderDetailList[0].totalPrice*business.mer.platformProportion/100)).toFixed(2));
								}});
					var info3 = {
							accountId:business.account.accountId,//商户id
							orderId:business.order.orderId
					};
					
					business.ajax("/merOrderCardCipher/list",info3,function(data3){
						if(data3.code==200){
							var html="";
							business.merOrderCardCipherList=data3.data;
							console.log(business.merOrderCardCipherList);
							var coccl="";
							for (var i = 0; i < business.merOrderCardCipherList.length; i++) {
								var mocc=business.merOrderCardCipherList[i];
								if(mocc.code){
									coccl+=" "+mocc.code+" ";													
								}else{
									coccl+=" <img style='width:10px;height:10px;' src='"+mocc.imgAddress+"'/>";
								}
							}
							//卡密
							$("#merOrderCardCipherList").html(coccl);
						}});
					//商品订单评价列表
					var info4={
							orderId:business.order.orderId,
							accountId:business.order.orderProblemList[0].account.accountId//问题人的账户id
					};
				   	business.ajax("/merOrderComment/list",info4,function(data){
						if(data.code==200){
							business.merOrderCommentList=data.data;
							//评分
							var merScore= business.merOrderCommentList[0].merScore;
							$(".comment .commentli").each(function(){
								if(merScore==5){
									$(this).text("★");
								}else if(merScore<5&&merScore>=4){
									if($(this).index()+1<5){
										$(this).text("★");										
									}
								}else if(merScore<4&&merScore>=3){
									if($(this).index()+1<4){
										$(this).text("★");										
									}
								}else if(merScore<3&&merScore>=2){
									if($(this).index()+1<3){
										$(this).text("★");										
									}
								}else if(merScore<2&&merScore>=1){
									if($(this).index()+1<2){
										$(this).text("★");										
									}
								}else if(merScore<1&&merScore>=0){
									if($(this).index()+1<1){
										$(this).text("★");										
									}
								}
							});
							//商品订单评论时间
							$("#merOrderCommentCreateDate").text(business.merOrderCommentList[0].createDate);
							//评论内容
							$("#merOrderCommentContent").text(business.merOrderCommentList[0].content);
						}});
					}});
	};
	business.getOrderDetailByStatus5();	
	/**
	 *商品订单评价列表
	 */
	business.orderProblemList=function(order){
		var info={
				orderId:order.orderId,
				accountId:business.order.orderProblemList[0].account.accountId,//问题人的账户id
				pageSize:100
		};
	 	business.ajax("/orderProblem/list",info,function(data){
			if(data.code==200){
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
					$("#refundSuccess").hide();
					$("#alreadyResolve").hide();
				}else if(order.substatus==5){
					substatusvalue="已解决";
					$("#refundSuccess").hide();
					$("#alreadyResolve").hide();
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
					var cc=JSON.stringify(child);
					cc=cc.replace(/"/g, "'");
					var answercontent='<button style="background-color:red;color:#fff;" onclick="business.orderProblemAnswerAdd('+cc+')">回复</button>';
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
				
				var html=html1+html2;
				//显示问题列表
				$("#orderProblemListWrap").html(html);
				//回复
				business.orderProblemAnswerAdd=function(cc){
						var html='<div style="text-align:left;">'
									+'<div style="margin-top:10px;padding-left:50px;">'
										+'<textarea id="orderProblemAnswerContent" style="height:400px;width:766px;border:1px solid #ccc;cursor:text;" placeholder="回复1-200"></textarea>'
									+'</div>'
									+'<div style="height:50px;margin-bottom:50px;text-align:center;">'
										+'<button id="closeTemplate" style="height:50px;width:100px;color:#fff;margin:20px;">取消</button>'
										+'<button id="orderProblemAnswerCommit" style="height:50px;width:100px;color:red;margin:20px;border:1px solid red;background-color:#fff;">确定</button>'
									+'</div>'
								+'</div>';
						//显示模板
						business.myTemplate(html,"#closeTemplate");
						//提交回复
						$("#orderProblemAnswerCommit").off().click(function(){
							var orderProblemAnswerContent=$("#orderProblemAnswerContent").val().trim();
							if(orderProblemAnswerContent<=0||orderProblemAnswerContent>200){
								business.myLoadingToast("内容必须大于0，小于等于200");
								return;
							}
							var info = {
									accountId:business.account.accountId,//商户id
									orderProblemId:cc.orderProblemId,
									content:orderProblemAnswerContent
								};
								business.ajax("/orderProblemAnswer/add",info,function(data){
									if(data.code==200){
										business.myLoadingToast("回复成功");
										setTimeout(function(){
											location.reload();
										},500)
									}else{
										business.myLoadingToast(data.msg)
									}
									});
						});
				}
				//退款给买家
				$("#refundSuccess").click(function() {
					business.myConfirm("确定退款给买家？",function(){
						var info={
								orderId:order.orderId,
								status:5,
								substatus:4,
								accountId:business.account!=null?business.account.accountId:null
						};
						business.ajax("/order/update",info,function(data){
							if(data.code==200){
								//console.log(data)
								business.myLoadingToast("退款给买家成功！")
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