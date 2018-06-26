//购物车商品转订单
//console.log(JSON.parse(sessionStorage.getItem("selectCartMerList")))
// 获取收货地址
   business.getReceiptInfoList=function(){
	   //console.log(business.account.accountId)
   		var info = {
   			accountId:business.account.accountId,
   			pageNum:1,
   			pageSize:100
   		};
   		ajxget("/receiptInfo/list",info,function(data){
			if(data.code==200){
					var list = data.data;
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
						$("#receiptInfoList").append(
								'<div style="width: 100%;height: 122px;">'
								+'<div style="width: 100%;height: 100%;position:relative;float: left;">'
									+'<div style="width:100%;position: absolute;top: 50%;transform: translateY(-50%);">'
										+'<input name="receiptInfoRadio" type="radio" style="margin-left:10px;float: left;margin-top: 30px;" id="receiptInfo'+child.receiptInfoId+'"/>'
										+'<div style="width:96%;float: right;margin-left: 10px;">'
											+'<p style="color: #666666;font-size: 12px;">是否默认：否</p>'
											+'<p style="color: #666666;font-size: 12px;">收货人：'+child.name+'</p>'
											+'<p style="color: #666666;font-size: 12px;">所在地区：中国大陆'+child.province+'/'+child.city+'/'+child.area+'</p>'
											+'<p style="color: #666666;font-size: 12px;">详细地址：'+child.address+'</p>'
											+'<p style="color: #666666;font-size: 12px;">邮政编码：'+child.postcode+'</p>'
											+'<p style="color: #666666;font-size: 12px;">手机号：'+child.phone+'</p>'
											+'<p style="color: #666666;font-size: 12px;">固定电话：'+child.telephone+'</p>'
										+'</div>'
									+'</div>'
								+'</div>'
						  +'</div><br/>'); 
						if(child.isDefault==1){
							$("#receiptInfo"+child.receiptInfoId).prop("checked","checked");
							$("#receiptInfo"+child.receiptInfoId).next().children().first().text("是否默认：是");
						}
		        	}
			}else{
				$("#receiptInfoList").append('<div style="width: 100%;height: 122px;text-align:center;line-height:122px">没有收货地址？<a style="color:red;" href="buyuserinfo.html?show=我的收货地址&showid=6">去填收货地址</a></div>')
			}
		})
   };
   business.getReceiptInfoList();
//购物车商品转订单商品
business.getSelectCartMerList=function(){
	business.selectCartMerList=JSON.parse(sessionStorage.getItem("selectCartMerList"));
	
	for (var i = 0; i < business.selectCartMerList.length; i++) {
		$("#cartMerList").append(
			'<div style="width: 100%;height: 80px;">'
				+'<div style="width: 50%;height: 100%;position:relative;float: left;">'
					+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
						+'<p style="font-size: 14px;margin-left: 10px;">'+business.selectCartMerList[i].mer.name+'</p>'
						+'<p style="font-size: 14px;margin-left: 10px;margin-top: 5px;">单价：¥'+business.selectCartMerList[i].mer.unitPrice+'</p>'
					+'</div>'
				
				+'</div>'
				+'<div style="width: 40%;height: 100%;position:relative;float: left;">'
					+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
						+'<p style="font-size: 14px;margin-left: 10px;">商品种类：'+business.selectCartMerList[i].mer.merCate.name+'</p>'
						+'<p style="font-size: 14px;margin-left: 10px;margin-top: 5px;">数量：'+business.selectCartMerList[i].number+'</p>'
					+'</div>'
				
				+'</div>'
			
				+'<div style="width: 10%;height: 100%;position:relative;float: left;">'
					+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
						+'<p style="font-size: 14px;margin-left: 10px;">总金额：</p>'
						+'<p style="font-size: 14px;margin-left: 10px;color: #BB0404;">¥'+business.selectCartMerList[i].totalPrice+'</p>'
					+'</div>'
				
				+'</div>'
			+'</div>');
	}
}
business.getSelectCartMerList();

//购物车商品转订单 订单优惠卷
business.getOrderCouponList=function(){
	var info = {
			accountId:business.account!=null?business.account.accountId:null,
			status:1//状态，默认1可用，2已用，3已失效
		}
		ajxget("/coupon/list",info,function(data){
						if(data.code==200){
							business.orderCouponList=data.data;
	for (var i = 0; i < business.orderCouponList.length; i++) {
		$("#cartMerList").append(
			  '<div style="width: 100%;height: 122px;">'
					+'<div style="width: 100%;height: 100%;position:relative;float: left;">'
						+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
							+'<input name="couponradio" type="radio" style="margin-left:  10px;float: left;margin-top: 30px;" id="coupon'+business.orderCouponList[i].couponId+'"/>'
							+'<img style="width: 130px;height: 74px;margin-left: 20px;margin-left: 10px;float: left;" src="'+business.orderCouponList[i].imgAddress+'"/>'
							+'<div style="float: left;margin-left: 10px;">'
								+'<p style="color: #1b1b1b;font-size: 14px;">'+business.orderCouponList[i].discount+'折优惠券，'+business.orderCouponList[i].name+'</p>'
								+'<p style="color: #666666;font-size: 12px;">优惠券使用说明：</p>'
								+'<p style="color: #666666;font-size: 12px;">'
								+business.orderCouponList[i].content
								+'</p>'
							+'</div>'
						+'</div>'
					+'</div>'
			  +'</div>');
	}
	}
						})
}
business.getOrderCouponList();

//选择支付
business.payType=1;//默认是1，支付宝
$(".pay_positionul li").click(function(){
	$('.pay_positionul li').removeClass('payborder7400');
	$(this).addClass('payborder7400');
	//console.log(parseInt($(this).attr("id").replace("payType", "")))
	business.payType=parseInt($(this).attr("id").replace("payType", ""));
});
//监听是否使用折扣卷
$("input[name='couponradio']").on("click",function(){
	//console.log($(this).next()[0].tagName.toLowerCase())
	//直接使用的
	if($(this).next()[0].tagName.toLowerCase()=='img'){
		//有可用优惠卷
		for (var i = 0; i < business.orderCouponList.length; i++) {
			if("coupon"+business.orderCouponList[i].couponId==$(this).attr("id")){
				business.selectOrderCoupon=business.orderCouponList[i];
				break;
			}
		}
		//$("#isHaveCoupon").text("已享"+$(this).next().next().children().children().text()+"折优惠");
		$("#isHaveCoupon").text("已享"+business.selectOrderCoupon.discount+"折优惠");
		$("#orderTotalPrice").text(parseFloat(JSON.parse(sessionStorage.getItem("selectCartMerTotalPrice")))*parseFloat(business.selectOrderCoupon.discount));
	}else if($(this).next()[0].tagName.toLowerCase()=='p'){
		//默认没有使用优惠券
		$("#isHaveCoupon").text("");
		//使用需要检测的"inputCouponCode"
		$("#checkInputCoupon").on("click", function() {
			if(!$("#inputCouponCode").val()){
				business.myLoadingToast("请填写优惠券号码")
				return;
			}
			var info = {
					code:$("#inputCouponCode").val(),
					status:1//状态，默认1可用，2已用，3已失效
				}
			ajxget("/coupon/list",info,function(data){
				if(data.code==200){
					//选中的优惠券
					business.selectOrderCoupon=data.data[0];
					$("#inputCouponWrap").after(
							  '<div style="width: 100%;height: 122px;">'
									+'<div style="width: 100%;height: 100%;position:relative;float: left;">'
										+'<div style="position: absolute;top: 50%;transform: translateY(-50%);">'
											+'<input name="couponradio" type="radio" style="margin-left: 10px;float: left;margin-top: 30px;" />'
											+'<img style="width: 130px;height: 74px;margin-left: 20px;margin-left: 10px;float: left;" src="'+data.data[0].imgAddress+'"/>'
											+'<div style="float: left;margin-left: 10px;">'
												+'<p style="color: #1b1b1b;font-size: 14px;"><span>'+data.data[0].discount+'</span>折优惠券，'+data.data[0].name+'</p>'
												+'<p style="color: #666666;font-size: 12px;">优惠券使用说明：</p>'
												+'<p style="color: #666666;font-size: 12px;">'
												+data.data[0].content
												+'</p>'
											+'</div>'
										+'</div>'
									+'</div>'
							  +'</div>');
					//有可用优惠卷
					$("#isHaveCoupon").text("已享"+data.data[0].discount+"折优惠");
					$("#orderTotalPrice").text(parseFloat(JSON.parse(sessionStorage.getItem("selectCartMerTotalPrice")))*data.data[0].discount);
				}else{
					business.myLoadingToast("无可用优惠卷")
				}
			});
		})
	}
});

//显示金额
$("#orderTotalPrice").text(JSON.parse(sessionStorage.getItem("selectCartMerTotalPrice")));
//取消
$("#cancelCartMerTurnOrder").on("click", function() {
	business.myConfirm("确定取消商品订单？",function(){
		location.href="shopcar.html";		
	});
});
//确定 购物车商品转订单
$("#cartMerTurnOrder").on("click", function() {
	//console.log(business.selectCartMerList)
	//console.log(business.selectOrderCoupon)
	//console.log(business.payType);
	//console.log($("input[name='receiptInfoRadio']:checked").size())
	if($("input[name='receiptInfoRadio']:checked").size()<=0){
		business.myLoadingToast("请选择收货地址")
		return ;
	}
	//去掉多余的
	for (var i = 0; i < business.selectCartMerList.length; i++) {
		business.selectCartMerList[i].mer=null
	}
	var info = {
			accountId:business.account!=null?business.account.accountId:null,
			cartMerList:JSON.stringify(business.selectCartMerList),//购物车商品列表
			couponId:business.selectOrderCoupon!=null?business.selectOrderCoupon.couponId:null,//优惠券Id
			payType:business.payType,//支付方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
			receiptInfoId:parseInt($("input[name='receiptInfoRadio']:checked").attr("id").replace("receiptInfo",""))//收货地址Id
		}
	//购物车商品批量转未支付订单商品
	ajxget("/cartMer/turnOrderBatch",info,function(data){
		if(data.code==200){
			sessionStorage.removeItem("selectCartMerTotalPrice");
			sessionStorage.removeItem("selectCartMerList");
			location.href="myorder.html";
		}
	});
})

