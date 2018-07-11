/**
 * 绑定银行卡
 */
	//显示列表
	if(sessionStorage.getItem("bankCardList")){
		business.bankCardList=JSON.parse(sessionStorage.getItem("bankCardList"));
		var html="";
		for (var i = 0; i < business.bankCardList.length; i++) {
			var child= business.bankCardList[i];
			var number=child.number.substr(0,3)+" "+child.number.substr(3,3)+" "+child.number.substr(6,6)+" "+child.number.substr(12,3)+" "+child.number.substr(15);
			var bankCard=JSON.stringify(child).replace(/"/g,"'");
			html+= '<div class="bindbank">'
						+'<div style="background-color:#ececec;height:30px;line-height:30px;">'
							+'<h3 style="color:#f10b0b;height:30px;line-height:30px;text-align:center;">'+child.bankName+'</h3>'
						+'</div>'
						+'<div style="padding-top: 38px;">'
							+'<div style="text-align:center;line-height: 25px;">'+number+'</div>'
						+'</div>'
						+'<div style="padding-top: 20px;">'
							+'<div style="display:inline-block;width:50%;text-align:left;line-height: 25px;padding-left:20px;color:blue;cursor:pointer;" onclick="business.updateBankCard('+bankCard+')">修改</div>'
							+'<div style="display:inline-block;width:50%;text-align:right;line-height: 25px;padding-right:20px;color:red;cursor:pointer;" onclick="business.deleteBankCard('+child.bankCardId+')">删除</div>'
						+'</div>'
					+'</div>';
			}
		$("#addBank").before(html);
		//修改,跳转
		business.updateBankCard=function(bankCard){
			sessionStorage.setItem("selectBankCard",JSON.stringify(bankCard));
			location.href="../sell/sell_bindbank.html";
		}
		//删除
		business.deleteBankCard=function(bankCardId){
			business.myConfirm("确定删除吗？",function(){
				var info = {
						accountId:business.account.accountId,
						bankCardId:bankCardId
					}
		      	business.ajax("/bankCard/delete",info,function(data){
	                if(data.code==200){
	                	business.myLoadingToast("删除成功");
	                	business.getBankCardList();
	                	location.reload();
	                }else{
	                	business.myLoadingToast(data.msg);
	                }
		      });
			});
		}
	}
	//增加
	$("#addBank").on("click",function(){
		sessionStorage.setItem("selectBankCard",JSON.stringify({}));
		location.href="../sell/sell_bindbank.html";
	});
	//修改