/**
 * 商品销售历史
 */	
//获取商品销售历史总数量
business.getSellerStatus0MerCount=function(){
	var info = {
			accountId:business.account.accountId,//商户id
			sellerAccountId:business.account.accountId,//商户id
			status:0//下架
		};
		business.ajax("/mer/count",info,function(data){
			$("#sellerStatus0MerCount").text(data)	
			});
};
business.getSellerStatus0MerCount();
//获取商品销售历史列表
business.getSellerStatus0MerList=function(){
	var info = {
			accountId:business.account.accountId,//商户id
			sellerAccountId:business.account.accountId,//商户id
			status:0//下架
	};
	business.ajax("/mer/list",info,function(data){
		if(data.code==200){
			var html="";
			business.sellerStatus0MerList=data.data;
			for (var i = 0; i < business.sellerStatus0MerList.length; i++) {
				var child=business.sellerStatus0MerList[i];
				html='<tr class="tabletd">'
						+'<td>'
							+'<p style="width: auto;">'
							+'<img class="itemimg" src="'+child.imgAddress+'"/>'
						+'</p>'
						+'</td>'
						+'<td>'+child.name+'</td>'
						+'<td style="color: #e04600;">￥'+child.unitPrice+'</td>'
						+'<td>'+child.saleNumber+'</td>'
						+'<td>'+child.updateDate+'</td>'
						+'<td >'
							+'<a style="text-decoration: none;color: #4cafe9;" onclick="business.changeToStatus1Mer('+child.merId+')">上架</a>'
						+'</td>'
					+'</tr>';
		$("#sellerStatus0MerList").append(html);
				}
		}else{
			business.myLoadingToast(data.msg)
		}
	});
};
business.getSellerStatus0MerList();
//上架
business.changeToStatus1Mer=function(merId){
	business.myConfirm("确定上架吗？",function(){
		var info = {
				accountId:business.account.accountId,//商户id
				merId:merId,
				status:1//上架
		};
		business.ajax("/mer/update",info,function(data){
			if(data.code==200){
				business.myLoadingToast("上架成功");
				setTimeout(function(){
					location.reload();
				},1000);
			}});
	});
};