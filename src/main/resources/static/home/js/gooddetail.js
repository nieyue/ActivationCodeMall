$(function(){
	
	
	var request  = getUrlInfo(location.search);
	var id = request["goodid"];
	var money = request["unitPrice"];
	var userid = localStorage.getItem("acountId");
	
	
	
	getdetail();
	$("#addtoshopcar").click(function(){
		var info = {
			number:1,
			totalPrice:money,
			accountId:userid,
			merId:id
		};
		
		ajxget("/cartMer/add",info,function(data){
			if(data.code==200){
				alert("添加成功");
				location.reload();
			}
		});
		
		
	});
	
	$(".detail_positionul li").click(function(){
		$('.detail_positionul li').removeClass('detailclickli');
		$(this).addClass('detailclickli');
		var value1 = $(this).text();
		var value = removeAllSpace(value1);
		
		console.log(value);
		if(value=="商品介绍"){
			
			if($(".goodjieshao").is(":hidden")){
				$(".goodjieshao").toggle();
				
			}
			if(!$(".pingjia").is(":hidden")){
				$(".pingjia").toggle();
			}
		}else if(value=="商品评价"){
			if(!$(".goodjieshao").is(":hidden")){
				$(".goodjieshao").toggle();
			}
			if($(".pingjia").is(":hidden")){
				$(".pingjia").toggle();
				
			}
			console.log(value1);
		}
	});
	


		function removeAllSpace(str) {
		return str.replace(/\s+/g, "");
		}
		
		
		
	function getdetail(){
		var info = {
			merId:id,
		
		};
		ajxget("/mer/load",info,function(data){
			if(data.code==200){
				var good = data.data[0];
				$("#detailname").text(good.name);
				$("#pingtai").html(good.platform);
				$("#nowprice").text("￥"+good.unitPrice);
				$("#oldprice").text("￥"+good.oldUnitPrice);
				$("#zhekou").text(good.discount+"折");
				$("#kucun").text("库存："+good.stockNumber);
				$("#commentnum").text(good.merCommentNumber+"人评论");
				$("#paynum").text(good.saleNumber+"人付款");
				$(".detailhomeimg").attr("src",good.imgAddress);
				$(".goodjieshao").html(good.configuration);
			}
			
		})
	}
});


function gettuijian(){
	var info = {
		
		pageNum:1,
		pageSize:5
	};
	ajxget("/mer/list",info,function(data){
		if(data.code==200){
			var list = data.data;
		        	var table = $('.detailgood');
		        	for(var i = 0; i < list.length; i++) {
		        		var good = list[i];
		        		var li = document.createElement('div');
		        		li.className = 'detailgooddiv clearfix';
						li.id = good.merId;
						var html = '<div style="text-align: center;"><img src="'+good.imgAddress+'"  class="width220 height160"/></div><div class="width220"><p class="color_333 tuijiangoodname">'+good.name+'</p></div><div class="width220 margin_top10"><p class="goodoldprice">市场价¥'+good.oldUnitPrice+'</p><p class="goodnewprice">¥'+good.unitPrice+'</p></div><div class="width220 margin_top10"><p class="tuijianbuynum">'+good.saleNumber+'人付款</p><p class="tuijiancommentnum">'+good.merCommentNumber+'人评论</p></div>';
						li.innerHTML = html;

						table.append(li); 
		        	}
		}
	})
}