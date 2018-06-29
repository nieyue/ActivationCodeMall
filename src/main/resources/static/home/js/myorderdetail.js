
//显示金额
$("#orderTotalPrice").text("¥"+JSON.parse(sessionStorage.getItem("selectOrderTotalPrice")));
 //订单详情
   business.getSelectOrderDetail=function(){
   	business.selectOrderDetail=JSON.parse(sessionStorage.getItem("selectOrderList"));
   	var request  = getUrlInfo(location.search);
	var orderId = request["orderId"];
	var info={
			orderId:orderId,
			accountId:business.account!=null?business.account.accountId:null
	};
   	ajxget("/order/load?orderId="+orderId,info,function(data){
		if(data.code==200){
			console.log(data)
		}})
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
   business.getSelectOrderDetail();  
   
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
            });
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
