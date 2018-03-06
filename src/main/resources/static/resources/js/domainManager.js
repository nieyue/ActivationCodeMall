;(function(window){
function isProd(b){
	var domainManager;
	if(b){
	//prod
	 domainManager={
		CatchGoods:'goods.newzhuan.cn',	
		MyWangEditor:'img.newzhuan.cn'
		};
	return domainManager;
	}else{
	//dev
	domainManager={
		CatchGoods:'192.168.11.111',	//物品服务器
		MyWangEditor:'img.newzhuan.cn',	//图片服务器
		};
	}
	return domainManager;
}
//window.domainManager=isProd(true);//生产
window.domainManager=isProd(false);//本地
})(window);