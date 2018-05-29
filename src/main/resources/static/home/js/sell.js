$(document).ready(function(){
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