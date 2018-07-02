$(document).ready(function(){
	//导航点击事件初始化
	$("#menu .itemchilder").on("click", function() {
		console.log($(this).text().trim())
		if($(this).text().trim()=="上架的商品"){
			location.href=business.url+"/home/sell/sell_index.html";
		}else if($(this).text().trim()=="商品销售历史"){
			location.href=business.url+"/home/sell/sell_history.html";
		}else if($(this).text().trim()=="销售新产品"){
			location.href=business.url+"/home/sell/sell_newgood.html";
		}else if($(this).text().trim()=="商品动态"){
			location.href=business.url+"/home/sell/sell_dynamic.html";
		}else if($(this).text().trim()=="商品动态"){
			location.href=business.url+"/home/sell/sell_dynamic.html";
		}else if($(this).text().trim()=="已完结订单"){
			location.href=business.url+"/home/sell/sell_order.html";
		}else if($(this).text().trim()=="冻结单"){
			location.href=business.url+"/home/sell/sell_orderblocking.html";
		}else if($(this).text().trim()=="问题单"){
			location.href=business.url+"/home/sell/sell_orderquestion.html";
		}else if($(this).text().trim()=="进账记录"){
			location.href=business.url+"/home/sell/sell_record.html";
		}else if($(this).text().trim()=="提现记录"){
			location.href=business.url+"/home/sell/sell_recordtixian.html";
		}else if($(this).text().trim()=="退款记录"){
			location.href=business.url+"/home/sell/sell_recordtukuan.html";
		}else if($(this).text().trim()=="我的消息"){
			location.href=business.url+"/home/sell/sell_message.html";
		}else if($(this).text().trim()=="商户信息"){
			location.href=business.url+"/home/sell/sell_merchantsinfo.html";
		}else if($(this).text().trim()=="等级成长历史"){
			location.href=business.url+"/home/sell/sell_chengxinhistory.html";
		}else if($(this).text().trim()=="诚信等级升级"){
			location.href=business.url+"/home/sell/sell_chengxinup.html";
		}else if($(this).text().trim()=="安全设置"){
			location.href=business.url+"/home/sell/sell_setinfo.html";
		}
});
	//初始化诚信
	business.sincerity=JSON.parse(sessionStorage.getItem("sincerity"));
	//页面功能
	if(location.href.indexOf("/sell/sell_index.html")>=0){
		
	}else if(location.href.indexOf("/sell/sell_merchantsinfo.html")>=0){
		//商户信息
		var levelname="零级";
		if(business.sincerity.level==0){
			levelname="零级";
		}else if(business.sincerity.level==1){
			levelname="一级";
		}else if(business.sincerity.level==2){
			levelname="二级";
		}else if(business.sincerity.level==3){
			levelname="三级";
		}else if(business.sincerity.level==4){
			levelname="四级";
		}else if(business.sincerity.level==5){
			levelname="五级";
		}else if(business.sincerity.level==6){
			levelname="六级";
		}else if(business.sincerity.level==7){
			levelname="七级";
		}else if(business.sincerity.level==8){
			levelname="八级";
		}else if(business.sincerity.level==9){
			levelname="九级";
		}else if(business.sincerity.level==10){
			levelname="十级";
		}else{
			levelname=business.sincerity.level+"级";
		}
		$("#sincerityLevel").text(levelname)
	}
	
	
	
	$(".detail_positionul li").click(function(){
		$('.detail_positionul li').removeClass('clickli');
		$(this).addClass('clickli');
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
	
	
	$(".sellnew_positionul li").click(function(){
		$('.sellnew_positionul li').removeClass('clicknewli');
		$(this).addClass('clicknewli');
		var value1 = $(this).text();
		var value = removeAllSpace(value1);
		
		console.log(value);
		
	});


		function removeAllSpace(str) {
		return str.replace(/\s+/g, "");
		}
})