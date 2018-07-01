//红利
$("#hongli_sellgologin").click(function(){
	window.location.href ="../login.html?roletype=1";
});
$("#hongli_tuiguanggologin").click(function(){
	window.location.href = "../login.html?roletype=2";
});
$("#hongli_usergologin").click(function(){
	window.location.href = "../login.html?roletype=3";
});
if(!business.account){
	location.href="/"
}
$(function(){
	var paytype = new Array("","支付宝","微信","百度钱包","Paypal","网银");
	//图像
	if(business.account.icon!=null&&business.account.icon!=""){
		$("#hongli_userimg").src = business.account.icon;
	}
	//名称
	$("#hongli_username").text(business.account.realname||business.account.nickname);
	$(".yuep").text("￥"+business.finance.money);
	//实名认证
	var authvalue="没认证";
	if(business.account.auth==1){
		authvalue="审核中";
	}else if(business.account.auth==2){
		authvalue="已认证";
	}
	$("#auth").text(authvalue);
	//无提现手续费最低额度
	$("#withdrawalsMinBrokerage").text(business.config.withdrawalsMinBrokerage);
	//提现手续费比例，单位%
	$("#withdrawalsProportion").text(business.config.withdrawalsProportion);
	//余额
	$("#financeMoney").text(business.finance.money);
	//冻结金额
	$("#financeFrozen").text(business.finance.frozen);
	//提现金额
	$("#withdrawalsMoney").attr("placeholder","输入金额必须大于"+business.config.minWithdrawals);
	//选择支付
	business.payType=1;//默认是1，支付宝
	$(".tixian_positionul li").click(function(){
		$('.tixian_positionul li').removeClass('tixianborder7400');
		$(this).addClass('tixianborder7400');
		//console.log(parseInt($(this).attr("id").replace("payType", "")))
		business.payType=parseInt($(this).attr("id").replace("payType", ""));
	});
	
	//提现
	$("#withdrawalsCommit").click(function(){
		if(business.finance.money<business.config.minWithdrawals){
			business.myLoadingToast("账户余额小于最小提现金额"+business.config.minWithdrawals)
			return;
		}
		if(business.payType!=1&&business.payType!=2&&business.payType!=5){
			business.myLoadingToast("请选择支付")
			return;
		}
		var withdrawalsMoney=$("#withdrawalsMoney").val().trim();
		if(withdrawalsMoney<business.config.minWithdrawals){
			business.myLoadingToast("提现金额必须大于"+business.config.minWithdrawals)
			return;
		}
	});
	
})
//推广链接
function gettuiguan(type){
	var pagesize=5;
		if(type==1){
			pagesize = 100;
		}
		var info = {
			accountId:business.account?business.account.accountId:null,
			pageNum:1,
			pageSize:pagesize
		};
		
		ajxget("/spreadLink/list",info,function(data){
			if(data.code==200){
				var list = data.data;
				business.spreadLinkList=data.data;
				var spreadLinkMostNumber=business.spreadLinkList[0].spreadNumber;
				var spreadLinkMost=business.spreadLinkList[0].link;
		        	var table = $('#tuiguang_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		if(child.spreadNumber>spreadLinkMostNumber){
		        			spreadLinkMostNumber=child.spreadNumber;
		        			spreadLinkMost=child.link;
		        		}
		        		var tr = document.createElement('tr');
		        		tr.className = 'tabletd';
						tr.id = child.spreadLinkId;
						var html = '<td><p style="width: auto;">'+(i+1)+'</p></td><td class="color_7400 ">'+child.link+'</td><td>'+child.spreadNumber+'</td>';
						tr.innerHTML = html;
						table.append(tr); 
		        	}
		        //链接
		        $("#spreadLinkMost").text(spreadLinkMost)
		        //次数
		        $("#spreadLinkMostNumber").text("推广次数："+spreadLinkMostNumber)
			}
		})
		
}

//我的用户
function getmyuser(type){
		var pagesize=5;
		if(type==1){
			pagesize = 100;
		}
		var info = {
			accountId:business.account.accountId,
			pageNum:1,
			pageSize:pagesize
		};
		
		ajxget("/spreadOrderAccount/list",info,function(data){
			if(data.code==200){
					var list = data.data;
		        	var table = $('#myuser_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'tabletd';
						tr.id = child.spreadLinkId;
						var html = '<td><p style="width: auto;">'+(i+1)+'</p></td><td>'+child.name+'</td><td>'+child.email+'</td><td>'+child.tradeNumber+'</td>';
						tr.innerHTML = html;
						table.append(tr); 
		        	}
			}
		})
		
}
//进账历史
business.getSpreadRecordList=function(type){
			var pagesize=5;
			if(type==1){
				pagesize = 100;
			}
			var info = {
				accountId:business.account.accountId,
				pageNum:1,
				pageSize:pagesize
			};
 	ajxget("/spreadRecord/list",info,function(data){
		if(data.code==200){
			business.spreadRecordList=data.data;
			var html="";
				for (var i = 0; i < business.spreadRecordList.length; i++) {
					var child=business.spreadRecordList[i];
				html+='<tr class="tabletd">'
						+'<td>'
							+'<p style="width: auto;">'+child.createDate+'</p>'
						+'</td>'
						+'<td>'+child.name+'</td>'
						+'<td>￥'+child.money+'</td>'
						+'<td>￥'+child.spreadMoney+'</td>'
					+'</tr>';
				}
			$("#spreadRecordList").html(html);
			}
});
};
//提现
function gettixian(status){
		var pagesize=5;
		if(status==1){
			pagesize = 100;
		}
		var info = {
			accountId:business.account.accountId,
			type:2,
			pageNum:1,
			pageSize:pagesize
		};
		
		ajxget("/financeRecord/list",info,function(data){
			if(data.code==200){
					var list = data.data;
		        	var table = $('#tixian_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'tabletd';
						tr.id = child.financeRecordId;
						var html = '<td><p style="width: auto;">'+child.createDate+'</p></td><td>'+paytype[child.method]+'</td><td>'+child.realname+'</td><td>￥'+child.realMoney+'</td>';
						tr.innerHTML = html;
						table.append(tr); 
		        	}
			}
		})
		
}
