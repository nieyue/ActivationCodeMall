/**
 * 进账记录
 */	
	//获取进账记录列表
	business.getFinanceRecordList5=function(){
		var info = {
			accountId:business.account.accountId,
			type:5,//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户），7申请退保证金
			pageNum:1,
			pageSize:100
		};
		
		business.ajax("/financeRecord/list",info,function(data){
			if(data.code==200){
				var html="";
					business.financeRecordList5=data.data;
				for (var i = 0; i < business.financeRecordList5.length; i++) {
					var child=business.financeRecordList5[i];
						html='<tr class="recordtabletd">'
									+'<td>'+child.createDate+'</td>'
									+'<td style="color: #4cafe9;">'+child.transactionNumber+'</td>'
									+'<td>'+business.payTypeList[child.method]+'</td>'
									+'<td>'+child.realname+'</td>'
									+'<td>'+child.money+'</td>'
									+'<td>'+parseFloat(Math.round(100*(child.money-child.realMoney)/child.money)).toFixed(2)+'%</td>'
									+'<td>'+child.realMoney+'</td>'
								+'</tr>';
						$("#financeRecordList5").append(html);
					}
				}
			});
			
	};
	business.getFinanceRecordList5();