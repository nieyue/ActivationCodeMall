/**
 * 上架的商品
 */
//商户总金额=余额+提现金额+冻结金额
$("#sellerTotalPrice").text(parseFloat(business.finance.money+business.finance.withdrawals+business.finance.frozen).toFixed(2))
//上架的商品列表
business.getSellerMerList=function(){
	var info = {
			pageNum:1,
			pageSize:100,
			accountId:business.account.accountId,//商户id
			sellerAccountId:business.account.accountId,//商户id
			status:1//状态0下架,默认1上架
		};
	business.merStatusList=["下架","上架"]
		business.ajax("/mer/list",info,function(data){
			if(data.code==200){
				var html="";
				business.sellerMerList=data.data;
				for (var i = 0; i < business.sellerMerList.length; i++) {
					var child=business.sellerMerList[i];
					var ziying="none;"//商户自营
						if(child.region==3){
							ziying="inline-block";
						}
					html='<tr class="tabletd">'
							+'<td>'
								+'<p style="width: auto;">'
								+'<img class="itemimg" src="'+child.imgAddress+'"/>'
								+'</p>'
							+'</td>'
							+'<td>'+child.name+'<span style="display:'+ziying+';background-color:#FF7400;color:#fff;width:38px;box-shadow:0 0 5px #FF7400;;">自营</span></td>'
							+'<td style="color: #e04600;">￥'+child.unitPrice+'</td>'
							+'<td>'+child.stockNumber+'</td>'
							+'<td>'+child.saleNumber+'</td>'
							+'<td style="color: #e04600;">'+business.merStatusList[child.status]+'</td>'
							+'<td >'
								+'<a style="text-decoration: none;color: #4cafe9;" href="sell_gooddetail.html?goodid='+child.merId+'">详情</a>	'
							+'</td>'
						+'</tr>';
					$("#sellerMerList").append(html);
				}
			}else{
				business.myLoadingToast(data.msg)
			}
			});
}
business.getSellerMerList();