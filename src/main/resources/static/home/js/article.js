//文章列表
function getarticle(){
	var info = {
					pageNum:1,
					pageSize:10
				}
	
	ajxget("/article/list",info,function(data){
                  var articlelist = data.data;
		        	var table = $('.newsgroup');
		        	for(var i = 0; i < articlelist.length; i++) {
		        		var article = articlelist[i];
		        		var div = document.createElement('div');
		        		div.className = 'width1200 newsparent';
						div.id = article.articleId;
						var html = '<ul class="news_positionul" ><li class="newsli" ><img src='+article.imgAddress+' class="acticleimg" /><div class="articletitlediv"><p class="newstitle">'+article.title+'</p><p class="newsramk">'+article.subtitle+'</p><div class="margin_top30"><p class="readnum">浏览次数：'+article.readingNumber+'</p><a class="readmore" href="newsdetail.html?articleid='+article.articleId+'">查看更多 >></a></div></div></li></ul>';
						div.innerHTML = html;

						table.append(div); 
		        	}
			        
                });
		
	}

if(location.pathname.indexOf("news.html")>-1){
	getarticle();
}


//文章详情
function getarticledetail(){
	var request  = getUrlInfo(location.search);
	var id = request["articleid"];
	console.log(id);
	var info = {
		articleId:id
	}
	ajxget("/article/load",info,function(data){
		if(data.code==200){
			var article = data.data[0];
			business.articleCateId = article.articleCateId;
			$("#articletime").text(article.createDate+"   来源："+article.resource);
			$(".detailtitle").text(article.title);
			var an=article.readingNumber;
			an=an>100000000?parseFloat(an/100000000).toFixed(2)+'亿':an>10000?parseFloat(an/10000).toFixed(2)+'万':an;
			$('.newscontent').html(article.content+"<br/><br/><div style='font-size:20px;font-weight:500;'>阅读："+an+"</div>");
			getaboutarticle();
		}
	});
}

if(location.pathname.indexOf("newsdetail.html")>-1){
	getarticledetail();
}
//相关新闻
function getaboutarticle(){
	
	var info = {
		pageNum:1,
		pageSize:5,
		articleCateId:business.articleCateId
	}
	ajxget("/article/list",info,function(data){
		if(data.code==200){
			var articlelist = data.data;
		        	var table = $('.detailnews');
		        	for(var i = 0; i < articlelist.length; i++) {
		        		var article = articlelist[i];
		        		var div = document.createElement('div');
		        		div.className = 'detailnewsdiv';
						div.id = article.articleId;
						var html = '<a href="newsdetail.html?articleid='+article.articleId+'"><img src='+article.imgAddress+' style="width: 319px;" /><div style="width: 318px;height: 40px;"><p class="aboutdetail">'+article.title+'</p></div></a>';
						
						div.innerHTML = html;

						table.append(div); 
		        	}
		}
	});
}

