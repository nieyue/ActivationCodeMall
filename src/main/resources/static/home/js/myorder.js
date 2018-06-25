$(document).ready(function(){
	//加的效果
	$(".add").click(function(){
	var n=$(this).prev().val();
	var num=parseInt(n)+1;
	if(num==0){ return;}
	$(this).prev().val(num);
	});
	//减的效果
	$(".jian").click(function(){
	var n=$(this).next().val();
	var num=parseInt(n)-1;
	if(num==0){ return}
	$(this).next().val(num);
	});
var divarr = new Array($("#nozhifu"),$("#nozhifu"),$("#havezhifu"),$("#wantbuy"),$("#wentidan"),$("#yituikuan"),$("#yiquxiao"));
//导航点击效果
$(".orderbanner_positionul li").click(function(){
	$('.orderbanner_positionul li').removeClass('bg_573c1e');
	var showid = $(this).index()+1;
	$(".orderbanner_positionul li:nth-child("+showid+")").addClass('bg_573c1e');
	show(showid);
	
});



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

//选择支付
$(".pay_positionul li").click(function(){
	
	$('.pay_positionul li').removeClass('payborder7400');
	$(this).addClass('payborder7400');
	
});

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
})
function getlist(status){
	var info;
	if(status==7){
		info = {
		status:5,
		substatus:4,
		pageNum:1,
		pageSize:100,
		accountId:business.accountId
		};
	}else{
		info = {
		status:status,
		pageNum:1,
		pageSize:100,
		accountId:business.accountId
		};
	}
	
	
	ajxget("/order/list",info,function(data){
		if(data.code==200){
			var list = data.data;
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
			}else if(status==7){
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
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">取消</a><a class="topaybtn">支付</a></td>';
				}else if(status==3){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">评价订单</a><a class="topaybtn">提取卡密</a><a class="shouhoubtn">售后服务</a></td>';
				}else if(status==4){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">等待发货</a></td>';
				}else if(status==5){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td ><p class="ordertimeday">'+child.updateDate+'</p></td><td>'+child.content+'</td><td><a class="deletorderbtn">待解决</a></td>';
				}else if(status==6){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">已取消</a></td><td ><p class="ordertimeday">'+child.updateDate+'</p></td>';
				}else if(status==7){
					html = '<td ><div ><input name="checkbox" type="checkbox" style="" class="ordercheck"/><p class="ordercode">'+child.orderNumber+'</p></div></td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td>啦啦啦</td><td>¥'+child.orderDetailList[0].unitPrice+'*'+child.orderDetailList[0].number+'</td><td>¥'+child.orderDetailList[0].totalPrice+'</td><td><a class="deletorderbtn">已取消</a></td><td ><p class="ordertimeday">'+child.updateDate+'</p></td>';
				}
				
				tr.innerHTML = html;
	
				table.append(tr); 
			 }
			
			
			
		}
	})
}

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
