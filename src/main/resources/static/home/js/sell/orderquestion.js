/**
 * 问题订单
 */
//问题订单数
business.getOrderCount5=function(){
	var info = {
		merchantAccountId:business.account.accountId,//商户id
		status:5,//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
	};
	
	business.ajax("/order/count",info,function(data){
		$("#orderCount5").text(data);
		});
		
};
business.getOrderCount5();
//获取问题订单列表
business.getOrderList5=function(){
	var info = {
		merchantAccountId:business.account.accountId,//商户id
		status:5,//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
		pageNum:1,
		pageSize:100
	};
	//原因，0其他，1不能充值，2卡密无效，3提示卡密错误
	business.reasonList=["其他","不能充值","卡密无效","提示卡密错误"];
	//子状态，5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决）
	business.substatusList=["","待解决","解决中","申请退款","已退款","已解决"];
	business.ajax("/order/list",info,function(data){
		if(data.code==200){
			var html="";
				business.orderList5=data.data;
			for (var i = 0; i < business.orderList5.length; i++) {
				var child=business.orderList5[i];
					html='<tr class="ordertabletd">'
							+'<td style="whit">'+child.orderNumber+'</td>'
							+'<td>'+child.orderDetailList[0].merCateName+'</td>'
							+'<td>'+child.orderProblemList[0].createDate+'</td>'
							+'<td>'+business.reasonList[child.orderProblemList[0].reason]+'</td>'
							+'<td style="color: #e04600;">'+business.substatusList[child.substatus]+'</td>'
							+'<td >'
								+'<a style="text-decoration: none;color: #4cafe9;" href="../sell/sell_questiondetail.html?orderId='+child.orderId+'">详情</a>'	
							+'</td>'
						+'</tr>';
					$("#orderList5").append(html);
				}
			}
		});
		
};
business.getOrderList5();