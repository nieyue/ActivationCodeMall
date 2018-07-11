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
      	business.ajax("/bankCard/addOrUpdate",info,function(data){
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