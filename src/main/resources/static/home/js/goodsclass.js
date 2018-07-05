$(function(){
	
	business.request  = business.getUrlInfo(location.search);
	//根据商品类型id查询
	business.merCateId = business.request["merCateId"];
	//根据商品名称查询
	business.merName = business.request["merName"];
	//根据排序规则查询，create_date(最新商品)，mer_score(人气)，sale_number(销量)
	business.orderName = business.request["orderName"];
	//当前类型商品列表
	function getmerhotlist(type){
		var info = {
				status:1,
				pageNum:1,
				pageSize:20
			};
		if(type==0){
				info = {
				merCateId:business.merCateId,
				status:1,
				pageNum:1,
				pageSize:20
			};
		}else if(type==1){
			info = {
				name:business.merName,
				status:1,
				pageNum:1,
				pageSize:20
			};
		}else if(type==2){
			info = {
					status:1,
					pageNum:1,
					pageSize:20,
					orderName:business.orderName,
					orderWay:"desc"
				};
			}
		
		business.ajax("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#shoplist');
		        	$("#goodsumnum").text("共"+list.length+"件商品");
		        	for(var i = 0; i < list.length; i++) {
		        		var good = list[i];
		        		var li = document.createElement('li');
		        		li.className = 'width1200 newsparent';
						li.id = good.merId;
						var html = '<a  title="" onclick="gomerdetail('+good.merId+')"><div class="imgbox"><img src="'+good.imgAddress+'" alt="" title=""/></div><div class="clearfix lineheight40 padding_lar10"><p class="fl">'+good.saleNumber+'人付款&nbsp;&nbsp;'+good.merCommentNumber+'人评论</p><p class="fr color_bb0404">￥'+good.unitPrice+'</p></div><div class="top_mengban"></div><div class="top_p ellipsisp">'+good.name+'</div></a>';
						li.innerHTML = html;

						table.append(li); 
		        	}
			}
		});
		
	}
	if(business.merCateId){
		getmerhotlist(0);
	}else if(business.merName){
		getmerhotlist(1);
	}else if(business.orderName){
		$(".sortList").each(function(i){
			$(this).removeClass("color_7400");
			$(this).addClass("color_aaaaaa");
			console.log(i)
		})
		if(business.orderName=='create_date'){
			$($(".sortList").get(1)).removeClass("color_aaaaaa");
			$($(".sortList").get(1)).addClass("color_7400");
		}else if(business.orderName=='mer_score'){
			$($(".sortList").get(2)).removeClass("color_aaaaaa");
			$($(".sortList").get(2)).addClass("color_7400");
		}else if(business.orderName=='sale_number'){
			$($(".sortList").get(3)).removeClass("color_aaaaaa");
			$($(".sortList").get(3)).addClass("color_7400");
		}
		getmerhotlist(2);
	}else{
		getmerhotlist(-1);
	}
	
})
//点击商品详情
function gomerdetail(id){
	console.log(id);
	window.location.href = "./gooddetail.html?goodid="+id;
}