$(document).ready(function(){
	//如果是商户，没有登录就不能访问
	if(location.href.indexOf("/sell/")>=0){
		if(!business.account){
			location.href="/";
		}
	}
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
			location.href=business.url+"/home/sell/sell_recordtuikuan.html";
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
	//诚信等级
	$("#sincerityLevel").text(levelname);
	
	//当前页面功能
	if(location.href.indexOf("/sell/sell_index.html")>=0){
	//上架的商品	
	}else if(location.href.indexOf("/sell/sell_merchantsinfo.html")>=0){
	/**
	 * 商户信息
	 */
		//注册时间
		$("#createDate").text(business.account.createDate);
		//注册账户id
		$("#accountId").text(business.account.accountId);
	}else if(location.href.indexOf("/sell/sell_chengxinhistory.html")>=0){
	/**
	 * 等级成长历史
	 */	
	}else if(location.href.indexOf("/sell/sell_chengxinup.html")>=0){
	/**
	 * 诚信等级升级
	 */		
		//已充值金额financeRecharge
		$("#financeRecharge").text(business.finance.recharge+"元");
		//诚信升级金额
		$("#sincerityUpgradeMoney").text(parseFloat(business.config.sellerSincerityUpgradeMoney-business.finance.recharge).toFixed(2)+"元");
		//申请退款
		$("#financeRefund").on("click", function() {
			business.myLoadingToast("申请退款")
		})
		//选择支付
		business.payType=1;//默认是1，支付宝
		$(".pay_positionul li").click(function(){
			$('.pay_positionul li').removeClass('payborder7400');
			$(this).addClass('payborder7400');
			business.payType=parseInt($(this).attr("id").replace("payType", ""));
		});
		//点击充值
		$(".rechargebtn").on("click",function(){
			var money=$("input[name='rechargeradio']:checked").val();
			business.myLoadingToast("充值金额："+money+",充值类型："+business.payType);
		});
	}else if(location.href.indexOf("/sell/sell_setinfo.html")>=0){
	/**
	 * 安全设置
	 */	
		//获取银行卡
		business.getBankCardList();
		//修改提现密码
		$("#updatePayPassword").on("click",function(){
			location.href="../sell/sell_updatepaypassword.html";
		});
	}else if(location.href.indexOf("/sell/sell_userbank.html")>=0){
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
			location.href="../sell/sell_bindbank.html";
		});
		//修改
		
	}else if(location.href.indexOf("/sell/sell_bindbank.html")>=0){
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
	}else if(location.href.indexOf("/sell/sell_updatepaypassword.html")>=0){
	/**
	 * 修改提现密码
	 */
	
		
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