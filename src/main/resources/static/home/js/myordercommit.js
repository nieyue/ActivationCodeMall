//购物车商品转订单
//console.log(JSON.parse(sessionStorage.getItem("selectOrderList")))
//显示金额
$("#orderTotalPrice").text(JSON.parse(sessionStorage.getItem("selectOrderTotalPrice")));
 //订单商品支付
   business.getSelectOrderList=function(){
   	business.selectOrderList=JSON.parse(sessionStorage.getItem("selectOrderList"));
   	
   	for (var i = 0; i < business.selectOrderList.length; i++) {
   		var coupon="未使用";
   		if(business.selectOrderList[i].orderDetailList[0].coupon){
   			coupon=business.selectOrderList[i].orderDetailList[0].coupon.name+","+business.selectOrderList[i].orderDetailList[0].coupon.discount+"折"
   		}
   		$("#orderList").append(
   			'<div style="width: 100%;height: 120px;">'
   				+'<div style="width: 30%;height: 100%;position:relative;float: left;">'
   					+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
   						+'<p style="font-size: 14px;margin-left: 10px;">订单编号: '+business.selectOrderList[i].orderNumber+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;margin-top: 5px;">单价：¥'+business.selectOrderList[i].orderDetailList[0].unitPrice+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">收货人: '+business.selectOrderList[i].orderReceiptInfo.name+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">邮编: '+business.selectOrderList[i].orderReceiptInfo.postcode+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">折扣劵: '+coupon+'</p>'
   					+'</div>'
   				
   				+'</div>'
   				+'<div style="width: 40%;height: 100%;position:relative;float: left;">'
   					+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
   						+'<p style="font-size: 14px;margin-left: 10px;">'+business.selectOrderList[i].orderDetailList[0].name+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;margin-top: 5px;">数量：'+business.selectOrderList[i].orderDetailList[0].number+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">手机号: '+business.selectOrderList[i].orderReceiptInfo.phone+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">收货地址: '+'中国大陆/'+business.selectOrderList[i].orderReceiptInfo.province+"/"+business.selectOrderList[i].orderReceiptInfo.city+"/"+business.selectOrderList[i].orderReceiptInfo.area+'</p>'
   					+'</div>'
   				
   				+'</div>'
   			
   				+'<div style="width: 30%;height: 100%;position:relative;float: left;">'
   					+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
   						+'<p style="font-size: 14px;margin-left: 10px;">'+business.selectOrderList[i].createDate+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;color: #BB0404;">总金额:¥'+business.selectOrderList[i].orderDetailList[0].totalPrice+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">电话: '+business.selectOrderList[i].orderReceiptInfo.telephone+'</p>'
   						+'<p style="font-size: 14px;margin-left: 10px;">收货地址详细: '+business.selectOrderList[i].orderReceiptInfo.address+'</p>'
   					+'</div>'
   				
   				+'</div>'
   			+'</div><hr/>');
   	}
   }
   business.getSelectOrderList();  
   
//选择支付
business.payType=1;//默认是1，支付宝
$(".pay_positionul li").click(function(){
	$('.pay_positionul li').removeClass('payborder7400');
	$(this).addClass('payborder7400');
	business.payType=parseInt($(this).attr("id").replace("payType", ""));
});

//取消
$("#cancelOrder").on("click", function() {
	business.myConfirm("取消订单支付？",function(){
		location.href="myorder.html";		
	});
});
//确定 购物车商品转订单
$("#orderPayment").on("click", function() {
	var orderIds=[];
	//去掉多余的
	for (var i = 0; i < business.selectOrderList.length; i++) {
		orderIds.push(business.selectOrderList[i].orderId);
	}
	var info = {
			accountId:business.account!=null?business.account.accountId:null,
			orderIds:orderIds.toString(),//订单id列表
			payType:business.payType//支付方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
		}
	//购物车商品批量转未支付订单商品
	ajxget("/order/payment",info,function(data){
		if(data.code==200){
			console.log(data)
			sessionStorage.removeItem("selectOrderTotalPrice");
			sessionStorage.removeItem("selectOrderList");
			location.href="myorder.html";
		}else{
			business.myLoadingToast(data.msg)
		}
	});
});
