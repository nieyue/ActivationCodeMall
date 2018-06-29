$(function(){
	business.request  = getUrlInfo(location.search);
	business.roletype = business.request["roletype"];
	if(business.roletype==1){
		$("#whologin").text("点卡交易平台-商户登录");
	}else if(business.roletype==2){
		$("#whologin").text("点卡交易平台-推广人登录");
	}else if(business.roletype==3){
		$("#whologin").text("点卡交易平台-用户登录");
	}else{
		location.href="/"
	}
	$(".goregister").click(function(){
		window.location.href = "register_step1.html?roletype="+business.roletype;
	});
	
	$(".register_button").click(function(){
		if(!haveUnlocked){
			business.myLoadingToast("没有验证通过")
			return;
		}
		var email = $("#useremail").val();
		var userpassword = $("#userpassword").val();
		
		if(checkEmail(email,userpassword)){
			if(userpassword.length>0){
				var info = {adminName:email,
					password:userpassword}
				$(".register_button").text("正在登录...");
				$(".register_button").attr("disabled", true);
				ajxget("/account/weblogin",info,function(data){
					if(data.code==200){
						var roleName = data.data[0].account.roleName;
						sessionStorage.setItem("account",JSON.stringify(data.data[0].account));
						sessionStorage.setItem("accountLevelList",JSON.stringify(data.data[0].accountLevelList));
						sessionStorage.setItem("finance",JSON.stringify(data.data[0].finance));
						sessionStorage.setItem("integral",JSON.stringify(data.data[0].integral));

						if(roleName=="用户"){
							window.location.href='index.html';
						}else if(roleName=="推广户"){
							window.location.href='hongli/hongli_index.html';
						}else if(roleName=="商户"){
							window.location.href='sell/sell_index.html';
						}else if(roleName=="超级管理员"){
							business.myLoadingToast("您是管理员，请于管理员页面进行登录");
						}
					}else{
						$(".register_button").text("重新登录");
						$(".register_button").attr("disabled", false);
						business.myLoadingToast(data.msg)
						return;
					}
					
					
				});
				
				
			}else{
				business.myLoadingToast("请输入密码");
			}
			
		}
	});
	
	
	
	
	
	
	
})

