$(document).ready(function(){
//初始化,保障
sessionStorage.setItem("selectOrderList",JSON.stringify([]));
sessionStorage.setItem("selectOrderTotalPrice",JSON.stringify(0));
//订单计总
	business.orderSum=function(status,substatus){
		//订单清零
		$("#allOrderNumber").html(0);
		$("#allOrderTotalPrice").html(0);
		//计算订单数量
		$("#allOrderNumber").html($("input[name='checkbox"+status+substatus+"']:checked").size())
		//计算订单总价
		var totalPrice=0;
		//选中的订单商品
		business.selectOrderList=[];
		$("input[name='checkbox"+status+substatus+"']:checked").each(function(){
			for (var i = 0; i < business["orderList"+status+substatus].length; i++) {
				if(business["orderList"+status+substatus][i].orderId==$(this).parent().parent().parent().attr("id")){
					business.selectOrderList.push(business["orderList"+status+substatus][i])
					totalPrice+=parseFloat(business["orderList"+status+substatus][i].orderDetailList[0].totalPrice);
				}
			}
		});
		$("#allOrderTotalPrice").html(totalPrice.toFixed(2));
		//总选择的列表
		sessionStorage.setItem("selectOrderList",JSON.stringify(business.selectOrderList));
		//总金额
		sessionStorage.setItem("selectOrderTotalPrice",JSON.stringify(totalPrice.toFixed(2)));
	}	
	//点击全选
	business.checkboxAll("input[name='checkboxAll']","input[name='checkbox21']",
			function(){
		business.orderSum(2,1);
		//business.orderSum(3,null);
		//business.orderSum(4,1);
		//business.orderSum(5,null);
		//business.orderSum(6,null);
		//business.orderSum(5,4);
	});
	//点击提交订单,批量
	$("#commitBatchOrderBtn").click(function(){
		if(JSON.parse(sessionStorage.getItem("selectOrderList")).length<=0){
			business.myLoadingToast("最少选中一个");
			return;
		}
		window.location.href = "myordercommit.html";
	});
	
var divarr = new Array($("#nozhifu"),$("#nozhifu"),$("#havezhifu"),$("#wantbuy"),$("#wentidan"),$("#yiquxiao"),$("#yituikuan"));
//导航点击效果
$(".orderbanner_positionul li").click(function(){
	$('.orderbanner_positionul li').removeClass('bg_573c1e');
	var showid = $(this).index()+1;
	$(".orderbanner_positionul li:nth-child("+showid+")").addClass('bg_573c1e');
	show(showid);
	$("input[name='checkboxAll']").prop("checked",false);
	$("input[name='checkbox']").prop("checked",false);
	var jjjj="";
	if(showid==2){
		//3,null
		jjjj="3null";
		business.checkboxAll("input[name='checkboxAll']","input[name='checkbox"+jjjj+"']",
				function(){
			business.orderSum(3,null);
		});
	}else
	if(showid==4){
		//5,null
		jjjj+="5null";
		business.checkboxAll("input[name='checkboxAll']","input[name='checkbox"+jjjj+"']",
				function(){
			business.orderSum(5,null);
		});
	}else if(showid==5){
		//6,null
		jjjj+="6null";
		business.checkboxAll("input[name='checkboxAll']","input[name='checkbox"+jjjj+"']",
				function(){
			business.orderSum(6,null);
		});
	}else if(showid==6){
		//5,4
		jjjj+="54";
		business.checkboxAll("input[name='checkboxAll']","input[name='checkbox"+jjjj+"']",
				function(){
			business.orderSum(5,4);
		});
	}else{
		jjjj+=""+(showid+1)+1;
		business.checkboxAll("input[name='checkboxAll']","input[name='checkbox"+jjjj+"']",
				function(){
			business.orderSum(showid+1,1);
		});
	}
});


//切换导航显示
function show(id){
	for (var i=0;i<divarr.length;i++) {
		if(i==id){
				if(divarr[i].is(":hidden")){
				divarr[i].toggle();
			}
		}else{
			if(!divarr[i].is(":hidden")){
				divarr[i].toggle();
			}
		}
	}
}
//订单支付
$(document).on("click",".topaybtn", function() {
	//console.log($(this).parent().parent().children().children().children("input[name='checkbox']"))
	$(this).parent().parent().children().children().children("input[name='checkbox21']").prop("checked","checked")
	business.orderSum(2,1);
	window.location.href = "myordercommit.html";
});

//取消购物商品
business.cancelOrder=function(orderId){
	var info = {
		orderId:orderId,
		status:6,
		substatus:1,
		accountId:business.account!=null?business.account.accountId:null
	};
	business.myConfirm("确定取消？",function(){
	ajxget("/order/update",info,function(data){
		if(data.code==200){
			business.myLoadingToast("取消成功");
			location.reload();
		}
	});
	});
}
//批量删除
$("#deleteBatchOrderBtn21,#deleteBatchOrderBtn31,#deleteBatchOrderBtn61,#deleteBatchOrderBtn54").on("click",function(){
	if(JSON.parse(sessionStorage.getItem("selectOrderList")).length<=0){
		business.myLoadingToast("最少选中一个");
		return;
	}
	var trs=JSON.parse(sessionStorage.getItem("selectOrderList"));
	var orderIdsarray=[];
	for (var i = 0; i < trs.length; i++) {
		orderIdsarray.push(trs[i].orderId);
	}
	business.myConfirm("批量删除，确定？",function(){
		//var cartMerIds=$("input[name='checkbox']:checked")
		var info = {
				orderIds:orderIdsarray.toString(),
				accountId:business.account!=null?business.account.accountId:null
			};
			
			ajxget("/order/deleteBatch",info,function(data){
				if(data.code==200){
					business.myLoadingToast("删除成功");
					location.reload();
				}
			});
	});
});

});
//获取订单列表
business.getOrderList=function(status,substatus){
	//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
	//子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），
	//5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），
	//6（1正常取消,2订单商品库存不够），7（1已删除）
	var info = {
		status:status,
		substatus:substatus,
		pageNum:1,
		pageSize:100,
		accountId:business.accountId
		};
	
	
	ajxget("/order/list",info,function(data){
		if(data.code==200){
			//business.orderList2,business.orderList3等等
			business["orderList"+status+substatus]=data.data;
			var list = data.data||[];
			var table;
			if(status==2){
				var table = $('#nopay_tb');
				$("#daizhifu>a").text("待支付("+list.length+")");
			}else if(status==3){
				var table = $('#havepay_tb');
				$("#yizhifu>a").text("已支付("+list.length+")");
			}else if(status==4){
				var table = $('#yugou_tb');
				$("#wantbuygood>a").text("预购商品("+list.length+")");
			}else if(status==5){
				var table = $('#question_tb');
				$("#quesionorder>a").text("我的问题单("+list.length+")");
			}else if(status==6){
				var table = $('#quxiao_tb');
				$("#quxiaoorder>a").text("已取消订单("+list.length+")");
			}else if(status==5&&substatus==4){
				var table = $('#tuikuan_tb');
				$("#tuikuan_tb>a").text("已退款订单("+list.length+")");
			}
			for(var i = 0; i < list.length; i++) {
			    var child = list[i];
			    var tr = document.createElement('tr');
			    tr.className = "td shopeitem";
				tr.id = child.orderId;
				var html;
				if(status==2){
					//html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">取消</a><a class="topaybtn">支付</a></td>';
					html='<td >'
					      	+'<div >'
					      		+'<input name="checkbox'+status+substatus+'" type="checkbox" style="" class="ordercheck"/>'
					      		+'<p class="ordercode" style="width:60%;word-break:break-all;">'+child.orderNumber+'</p>'
					      	+'</div>'
					      +'</td>'
					      +'<td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td>'
					      +'<td >'
					      	+'<p class="ordertimeday">'+child.createDate+'</p>'
					      +'</td>'
					      +'<td>'+child.orderReceiptInfo.name+'</td>'
					      +'<td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td>'
					      +'<td>¥'+child.orderDetailList[0].totalPrice+'</td>'
					      +'<td>'
					      	+'<a class="deletorderbtn" onclick="business.cancelOrder('+child.orderId+')">取消</a>'
					      	+'<a class="topaybtn" >支付</a>'
					      +'</td>'
				}else if(status==3){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">评价订单</a><a class="topaybtn">提取卡密</a><a class="shouhoubtn">售后服务</a></td>';
				}else if(status==4){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">等待发货</a></td>';
				}else if(status==5){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>'+child.content+'</td><td><a class="deletorderbtn">待解决</a></td>';
				}else if(status==6){
					//html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">已取消</a></td><td ><p class="ordertimeday">'+child.updateDate+'</p></td>';
						var substatusvalue="正常取消";
						if(child.substatus==1){
							substatusvalue="正常取消";
						}else if(child.substatus==2){
							substatusvalue="订单商品库存不够取消";
						}
						
					html='<td >'
					      		+'<div >'
					      			+'<input name="checkbox'+status+substatus+'" type="checkbox" style="" class="ordercheck"/>'
					      			+'<p class="ordercode" style="width:60%;word-break:break-all;">'+child.orderNumber+'</p>'
					      		+'</div>'
					      	+'</td>'
					      	+'<td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td>'
					      	+'<td>'+child.orderReceiptInfo.name+'</td>'
					      	+'<td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td>'
						    +'<td>¥'+child.orderDetailList[0].totalPrice+'</td>'
					      	+'<td>'
					      		+'<span class="deletorderbtn">'+substatusvalue+'</span>'
					      	+'</td>'
					      	+'<td >'
					      		+'<p class="ordertimeday">'+child.createDate+'</p>'
					      	+'</td>';
				}else if(status==5&&substatus==4){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">已取消</a></td><td ><p class="ordertimeday">'+child.updateDate+'</p></td>';
				}
				
				tr.innerHTML = html;
	
				table.append(tr); 
			 }
			
			
			
		}
	})
};
//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
business.getOrderList(2,1);
business.getOrderList(3,null);
business.getOrderList(4,1);
business.getOrderList(5,null);
business.getOrderList(6,null);
business.getOrderList(5,4);
//商品推荐
function gettuijian(){
	var info = {
		pageNum:1,
		pageSize:5,
		recommend:2//推荐，默认0不推，1封推，2推荐
	};
	ajxget("/mer/list",info,function(data){
		if(data.code==200){
			var list = data.data;
		        	var table = $('.cargood');
		        	for(var i = 0; i < list.length; i++) {
		        		var good = list[i];
		        		var li = document.createElement('div');
		        		li.className = 'cargooddiv';
						li.id = good.merId;
						var img = good.imgAddress;
							if(img==null||img==""){
								img = "img/index_goods1.jpg";
							}
						var html = '<div style="text-align: center;"><img src="'+img+'" style="width: 220px;height: 160px;" /></div><div style="width: 220px;"><p  class="color_333 tuijianname">'+good.name+'</p></div><div class="pdiv"><p class="goodoldprice">市场价¥'+good.oldUnitPrice+'</p><p class="goodnewprice">¥'+good.unitPrice+'</p></div><div class="pdiv"><p class="buynum">'+good.saleNumber+'人付款</p><p class="commentnum">'+good.merCommentNumber+'人评论</p></div>';
						li.innerHTML = html;

						table.append(li); 
		        	}
		        	$(".cargood .cargooddiv").each(function(){
	        			$(this).click(function(){
	        			location.href="gooddetail.html?goodid="+$(this).attr("id");
	        			});
	        		});
		}
	})
}

gettuijian();