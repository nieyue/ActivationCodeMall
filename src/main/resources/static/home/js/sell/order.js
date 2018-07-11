/**
 * 已完成订单
 */	
	//订单数量
	$(".orderCount32").text(0);
	//获取已完成订单列表
	business.getOrderList32=function(){
		var info = {
			merchantAccountId:business.account.accountId,//商户id
			status:3,//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
			substatus:2,//子状态，2（1待支付），3（1冻结单，2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）
			pageNum:1,
			pageSize:100
		};
		
		business.ajax("/order/list",info,function(data){
			if(data.code==200){
				var html="";
					business.orderList32=data.data;
					
				for (var i = 0; i < business.orderList32.length; i++) {
					var child=business.orderList32[i];
						html='<tr class="ordertabletd">'
								+'<td>'+child.orderNumber+'</td>'
								+'<td>'+child.orderDetailList[0].merCateName+'</td>'
								+'<td>'+child.orderDetailList[0].number+'</td>'
								+'<td style="color: #e04600;">￥'+child.orderDetailList[0].totalPrice+'</td>'
								+'<td>'+child.paymentDate+'</td>'
								+'<td >'
									+'<a style="text-decoration: none;color: #4cafe9;" href="../sell/sell_orderdetail.html?orderId='+child.orderId+'">详情</a>'	
								+'</td>'
							+'</tr>';
						$("#orderList32").append(html);
					}
				}
			});
			
	};
	business.getOrderList32();