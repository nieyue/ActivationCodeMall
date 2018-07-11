/**
 * 提现记录
 */	
	//获取提现记录列表
	business.getFinanceRecordList2=function(){
		var info = {
			accountId:business.account.accountId,
			type:2,//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户），7申请退保证金
			pageNum:1,
			pageSize:100
		};
		
		business.ajax("/financeRecord/list",info,function(data){
			if(data.code==200){
				var html="";
					business.financeRecordList2=data.data;
				for (var i = 0; i < business.financeRecordList2.length; i++) {
					var child=business.financeRecordList2[i];
						html='<tr class="recordtabletd">'
									+'<td>'+child.createDate+'</td>'
									+'<td style="color: #4cafe9;">'+child.transactionNumber+'</td>'
									+'<td>'+business.payTypeList[child.method]+'</td>'
									+'<td>'+child.realname+'</td>'
									+'<td>'+child.money+'</td>'
									+'<td>'+parseFloat(child.money-child.realMoney).toFixed(2)+'</td>'
									+'<td>'+child.realMoney+'</td>'
								+'</tr>';
						$("#financeRecordList2").append(html);
					}
				}
			});
			
	};
	business.getFinanceRecordList2();