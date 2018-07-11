/**
 * 订单详情
 */		
	business.request  = business.getUrlInfo(location.search);
	business.orderId = business.request["orderId"];
	if(!business.orderId){
		history.go(-1);
	}
	//获取订单详情
	business.getOrderDetail=function(){
		var info = {
			accountId:business.account.accountId,//商户id
			orderId:business.orderId
		};
		
		business.ajax("/order/load",info,function(data){
			if(data.code==200){
				var html="";
					business.order=data.data[0];
					console.log(business.order)
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
					$("#orderReceiptInfoEmail").text(business.account.email);
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
							//accountId:business.order.orderProblemList[0].account.accountId//问题人的账户id
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
	business.getOrderDetail();