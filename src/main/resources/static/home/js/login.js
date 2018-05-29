$(function(){
	
	var request  = getUrlInfo(location.search);
	var roletype = request["roletype"];
	if(roletype==1){
		$("#whologin").text("点卡交易平台-商户登录");
	}else if(roletype==2){
		$("#whologin").text("点卡交易平台-推广人登录");
	}else if(roletype==3){
		$("#whologin").text("点卡交易平台-用户登录");
	}
	$("#goregiest").click(function(){
		window.location.href = "register_step1.html?roletype="+roletype;
	});
	
	$(".register_button").click(function(){
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
						var roleId = data.data[0].account.roleId;
						sessionStorage.setItem("account",JSON.stringify(data.data[0].account));
						sessionStorage.setItem("accountLevelList",JSON.stringify(data.data[0].accountLevelList));
						sessionStorage.setItem("finance",JSON.stringify(data.data[0].finance));
						sessionStorage.setItem("integrall",JSON.stringify(data.data[0].integrall));

						if(roleId==1003){
							window.location.href='index.html';
						}else if(roleId==1002){
							window.location.href='hongli/hongli_index.html';
						}else if(roleId==1001){
							window.location.href='sell/sell_index.html';
						}else if(roleId==1000){
							alert("您是管理员，请于管理员页面进行登录");
						}
					}else{
						$(".register_button").text("重新登录");
						$(".register_button").attr("disabled", false);
					}
					
					
				});
				
				
			}else{
				alert("请输入密码");
			}
			
		}
	});
	
	
	
	
	
	
	
})

