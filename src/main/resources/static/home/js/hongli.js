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
	//获取银行卡信息
	if(business.account&&location.href.indexOf("/hongli/hongli_userinfo.html")>=0){
		//获取银行卡
		business.getBankCardList();
		//修改提现密码
		//修改提现密码
		$("#updatePayPassword").on("click",function(){
			location.href="../hongli/hongli_updatepaypassword.html";
		});
	}else if(location.href.indexOf("/hongli/hongli_userbank.html")>=0){
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
					location.href="../hongli/hongli_bindbank.html";
				}
				//删除
				business.deleteBankCard=function(bankCardId){
					business.myConfirm("确定删除吗？",function(){
						var info = {
								accountId:business.account.accountId,
								bankCardId:bankCardId
							}
				      	ajxget("/bankCard/delete",info,function(data){
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
				location.href="../hongli/hongli_bindbank.html";
			});
			//修改
			
		}else if(location.href.indexOf("/hongli/hongli_bindbank.html")>=0){
		/**
		 * 增加或修改银行卡
		 */
			if(sessionStorage.getItem("selectBankCard")){
				//修改
				business.selectBankCard=JSON.parse(sessionStorage.getItem("selectBankCard"));
				$("#realname").val(business.selectBankCard.realname);
				$("#identity").val(business.selectBankCard.identity);
				$("#bankName").val(business.selectBankCard.bankName);
				$("#number").val(business.selectBankCard.number);
				$("#phone").val(business.selectBankCard.phone);
			}else{
				
			}
			//发送验证码
			$(".getcodebtn").on("click", function() {
			business.sendValidCode(".getcodebtn","#phone",5);
			});
			//增加银行卡
			$("#addBankCardBtn").on("click", function() {
				var realname=$("#realname").val().trim();
				var identity=$("#identity").val().trim();
				var bankName=$("#bankName").val().trim();
				var number=$("#number").val().trim();
				var phone=$("#phone").val().trim();
				var validCode=$("#validCode").val().trim();
				if(realname.length<0
						||identity.length<0
						||bankName.length<0
						||number.length<0
						||phone.length<0
						||validCode.length<0
						){
					business.myLoadingToast("所有的必填");
					return;
				}
				var info = {
						accountId:business.account.accountId,
						realname:realname,
						identity:identity,
						bankName:bankName,
						number:number,
						phone:phone,
						validCode:validCode
					}
				//如果是更新
				if(business.selectBankCard.bankCardId){
					info.bankCardId=business.selectBankCard.bankCardId;
				}
		      	ajxget("/bankCard/addOrUpdate",info,function(data){
	                if(data.code==200){
	                	business.getBankCardList();
	                	if(business.selectBankCard.bankCardId){
	                		business.myLoadingToast("修改成功");
	                		return;
	                	}
	                	business.myLoadingToast("添加成功");
	                	$("#realname").val("");
	        			$("#identity").val("");
	        			$("#bankName").val("");
	        			$("#number").val("");
	        			$("#phone").val("");
	        			$("#validCode").val("");
	                }else{
	                	business.myLoadingToast(data.msg);
	                }
		      });
			});
		}
	var paytype = new Array("","支付宝","微信","百度钱包","Paypal","网银");
	//图像
	if(business.account.icon!=null&&business.account.icon!=""){
		$("#hongli_userimg").src = business.account.icon;
	}
	//名称
	$("#hongli_username").text(business.account.realname||business.account.nickname);
	$(".yuep").text("￥"+business.finance.money);
	
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
