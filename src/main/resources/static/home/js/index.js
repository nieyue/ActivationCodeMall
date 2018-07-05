$(function(){
	
	getbannerlist();
	business.getCartMerList()
	business.getMerCateList();
	getmernewlist();
	getmerrenqilist();
	getmerhotlist();
//最新
function getmernewlist(){
		var info = {
			pageNum:1,
			pageSize:9,
			status:1,
			orderName:"create_date",
			orderWay:"desc"
		};
		business.ajax("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#newlist');
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



//人气商品
function getmerrenqilist(){
		var info = {
			pageNum:1,
			pageSize:9,
			status:1,
			orderName:"mer_score",
			orderWay:"desc"
		};
		business.ajax("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#renqilist');
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
	

//热销
function getmerhotlist(){
		var info = {
				pageNum:1,
				pageSize:10,
				status:1,
				orderName:"sale_number",
				orderWay:"desc"
		};
		business.ajax("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#hotlist');
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
//系统消息
business.getSystemNoticeList=function(){
	var info = {
			pageNum:1,
			pageSize:5,
			region:1,//全局
			type:1//系统
	};
	business.ajax("/notice/list",info,function(data){
		if(data.code==200){
			var html="";
			business.systemNoticeList=data.data;
			for (var i = 0; i < business.systemNoticeList.length; i++) {
				var child=business.systemNoticeList[i];
			html+='<li>'
					+'<a href="javascript:business.myTemplate('+"'"+child.content+"'"+');">'
					+'<p class="ellipsisp">'+child.content+'</p>'
					+'</a>'
				+'</li>';
			}
			$("#systemNoticeList").html(html);
			jQuery(".txtScroll_top").slide({mainCell:".bd ul",autoPage:true,effect:"top",autoPlay:true,vis:1});
			}});
};
business.getSystemNoticeList();
function getbannerlist(){
		var info = {
			pageNum:1,
			pageSize:20
		};
		business.ajax("/banner/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#indexbanner');
		        	var dian = $('#indexbannerdian');
		        	for(var i = 0; i < list.length; i++) {
		        		var banner = list[i];
		        		var li = document.createElement('li');
		        		li.className = "bannerli";
		        		
		        		li.style = ("background:url("+banner.imgAddress+") no-repeat center center;background-size: 100% 100%;height:auto;");
						li.id = banner.bannerId;
//						var html = '<a href="'+banner.link+'" title=""></a>';
//						li.innerHTML = html;

						table.append(li); 
						
						var diandian = document.createElement('li');
						dian.append(diandian);
						(function(x){
						li.onclick=function(){window.open(x)}
						})(banner.link)
		        	}
		        	jQuery(".banner").slide({mainCell:".bd ul",titCell:".hd li",effect:"fold",autoPlay:true}); //banner
			}
		});
		
	}



})
function getrexiaopaihang(){
	var info = {
			pageNum:1,
			pageSize:10,
			orderName:'sale_number',
			orderWay:'desc'
		};
		business.ajax("/mer/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var ul = $('.index_sortul');
		        	for(var i = 0; i < list.length; i++) {
		        		var mer = list[i];
		        		var liclass="";
		        		if(i==0){
		        			liclass='sort1';
		        		}else if(i==1){
		        			liclass='sort2';
		        		}else if(i==2){
		        			liclass='sort3';
		        		}
		        		$(".index_sortul").append("" +
		        				"<li class='"+liclass+"'>" +
									"<a href='gooddetail.html?goodid="+mer.merId+"' class='clearfix'>" +
										"<p class='fl num_p'>NO."+(i+1)+"</p>" +
										"<p class='fl ellipsisp'>"+mer.name+"</p>" +
									"</a>" +
								"</li>");
		        	}
			}
		});
	
}
function gomerdetail(id){
	console.log(id);
	window.location.href = "./gooddetail.html?goodid="+id;
}