/**
 * 诚信等级升级
 */		
	//已充值金额financeRecharge
	$("#financeRecharge").text(business.finance.recharge+"元");
	//诚信升级金额
	$("#sincerityUpgradeMoney").text(parseFloat(business.config.sellerSincerityUpgradeMoney-business.finance.recharge).toFixed(2)+"元");
	//申请退款
	$("#financeRefund").on("click", function() {
		business.myLoadingToast("申请退款")
	})
	//选择支付
	business.payType=1;//默认是1，支付宝
	$(".pay_positionul li").click(function(){
		$('.pay_positionul li').removeClass('payborder7400');
		$(this).addClass('payborder7400');
		business.payType=parseInt($(this).attr("id").replace("payType", ""));
	});
	//点击充值
	$(".rechargebtn").on("click",function(){
		var money=$("input[name='rechargeradio']:checked").val();
		business.myLoadingToast("充值金额："+money+",充值类型："+business.payType);
	});