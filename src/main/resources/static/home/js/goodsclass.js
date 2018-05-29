$(function(){
	
	var request  = getUrlInfo(location.search);
	var id = request["merCateId"];
	var name = request["merName"];
	
	function getmerhotlist(type){
		var info;
		if(type==0){
				info = {
				merCateId:id,
				pageNum:1,
				pageSize:20
			};
		}else{
			info = {
				name:name,
				pageNum:1,
				pageSize:20
			};
		}
		
		ajxget("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#shoplist');
		        	$("#goodsumnum").text("共"+list.length+"件商品");
		        	for(var i = 0; i < list.length; i++) {
		        		var good = list[i];
		        		var li = document.createElement('li');
		        		li.className = 'width1200 newsparent';
						li.id = good.merId;
						var html = '<a  title="" onclick="gomerdetail('+good.merId+','+good.unitPrice+')"><div class="imgbox"><img src="'+good.imgAddress+'" alt="" title=""/></div><div class="clearfix lineheight40 padding_lar10"><p class="fl">'+good.saleNumber+'人付款&nbsp;&nbsp;'+good.merCommentNumber+'人评论</p><p class="fr color_bb0404">￥'+good.unitPrice+'</p></div><div class="top_mengban"></div><div class="top_p ellipsisp">'+good.name+'</div></a>';
						li.innerHTML = html;

						table.append(li); 
		        	}
			}
		});
		
	}
	if(id==undefined){
		getmerhotlist(1);
	}else{
		getmerhotlist(0);
	}
	
})
function gomerdetail(id,money){
	console.log(id);
	window.location.href = "./gooddetail.html?goodid="+id+"&unitPrice="+money;
}