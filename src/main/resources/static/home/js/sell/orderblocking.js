/**
 * 冻结单
 */
//冻结总金额
$("#financeFrozen").text(business.finance.frozen);
//冻结单数
business.getFrozenOrderCount=function(){
	var info = {
		merchantAccountId:business.account.accountId,//商户id
		status:3,//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
		substatus:1,//子状态，2（1待支付），3（1冻结单，2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）
	};
	
	business.ajax("/order/count",info,function(data){
		$("#frozenOrderCount").text(data);
		});
		
};
business.getFrozenOrderCount();
//获取冻结订单列表
business.getOrderList31=function(){
	var info = {
		merchantAccountId:business.account.accountId,//商户id
		status:3,//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
		substatus:1,//子状态，2（1待支付），3（1冻结单，2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）
		pageNum:1,
		pageSize:100
	};
	
	business.ajax("/order/list",info,function(data){
		if(data.code==200){
			var html="";
				business.orderList31=data.data;
			for (var i = 0; i < business.orderList31.length; i++) {
				var child=business.orderList31[i];
				//console.log(business.frozenRemainDay(new Date("2018/7/7 21:22:22"),business.config.freezeDayNumber))
					html='<tr class="ordertabletd">'
							+'<td style="whit">'+child.orderNumber+'</td>'
							+'<td>'+child.orderDetailList[0].merCateName+'</td>'
							+'<td>'+child.orderDetailList[0].number+'</td>'
							+'<td style="color: #e04600;">￥'+child.orderDetailList[0].totalPrice+'</td>'
							+'<td>'+child.paymentDate+'</td>'
							+'<td>'+business.frozenRemainDay(child.paymentDate,business.config.freezeDayNumber)+'天</td>'
							+'<td >'
								+'<a style="text-decoration: none;color: #4cafe9;" href="../sell/sell_orderdetail.html?orderId='+child.orderId+'">详情</a>'	
							+'</td>'
						+'</tr>';
					$("#orderList31").append(html);
				}
			}
		});
		
};
business.getOrderList31();