//var host = localStorage.getItem("urlHost");

$(function(){
	//发送验证码
	$("#step1_next").click(function(){
		var email = $("#useremail").val();
		if(!haveUnlocked){
			business.myLoadingToast("没有验证通过")
			return;
		}
		if(business.checkEmail(email)){
			var info = {
					adminName:email,
					templateCode:1
				}
			business.ajax("/account/validCode",info,function(data){
				if(data.code==200){
					$("#emaildiv").toggle();
		        	$("#haveemaildiv").toggle();
		        	$(".emailp").text(email);
		        	if(data.msg=="已经验证"){
		        		business.myLoadingToast(data.msg);
			        	return;
			        }else{
			        	business.myLoadingToast("验证码发送成功，请注意查收");			        	
			        }
			        }else{
			        	business.myLoadingToast(data.msg);
			        }
                });
		
		}
	});
	//点击到第二步
	$(".register_havejihuo").click(function(){
		
		var email = $("#useremail").val();
		var request  = business.getUrlInfo(location.search);
		var roletype = request["roletype"];
		window.location.href='register_step2.html?email='+email+"&roletype="+roletype;
	});
	
	
	
	
	
});


	var countdown=60;  
	var _generate_code = $(".register_yzm");
	//获取代码
	function getcode(){
      var phone = $("#userphone").val();
      if(phone.length != 11 ){  
    	  business.myLoadingToast("请填写正确的手机号！");  
        return false;  
      }
      _generate_code.attr("disabled", true);
      	settime();
      	var info = {
					adminName:phone,
					templateCode:1
				}
      	
      	business.ajax("/account/validCode",info,function(data){
                    if(data.code==200){
                    	business.myLoadingToast("验证码发送成功，请注意查收");
                    }
		        	
			        
                });
      	
      	
      
	}
	//第三步
	function gonext(){
		var username = $("#username").val();
		var userphone = $("#userphone").val();
		var code = $("#code").val();
		var email = business.getUrlInfo(location.search);
		var roletype = email["roletype"];
		if(nonull(username,userphone,code)){
			window.location.href="register_step3.html?username="+username+"&userphone="+userphone+"&code="+code+"&email="+email["email"]+"&roletype="+roletype;
		}
	}
	//验证规则
	function nonull(username,userphone,code){
		if(username.length<3){
			business.myLoadingToast("用户名不能少于3个字符");
			return false;
		}
		if(userphone.length!=11){
			business.myLoadingToast("请输入正确的手机号");
			return false;
		}
		if(code.length<0){
			business.myLoadingToast("请输入验证码");
			return false;
		}
		return true;
	}
 	//倒计时
	function settime() {  
      if (countdown == 1) {  
        _generate_code.attr("disabled",false);  
        _generate_code.val("发送");  
        countdown = 60;  
        return false;  
      } else {  
    	  countdown--;  
        _generate_code.attr("disabled", true);  
        _generate_code.text(countdown + " S");  
      }  
      setTimeout(function() {  
        settime();  
      },1000);  
    }
	
	//注册
	function register(){
		var url = location.search;
		var Request = business.getUrlInfo(url);
		var password1 = $("#password1").val();
		var password2 = $("#password2").val();
		var roletype = Request["roletype"];
		if(equalspassword(password1,password2)){
			
			var info = {
					phone:Request["userphone"],
					roleType:roletype,
					nickname:Request["username"],
					password:password1,
					email:Request["email"],
					validCode:Request["code"]
				}
			business.ajax("/account/webregister",info,function(data){
				if(data.code==200){
					business.myLoadingToast("注册成功")
					window.location.href='login.html?roletype='+roletype;
				}else{
					business.myLoadingToast(data.msg);
				}
                    
			        
                });
			
			
			
		}
	}
	//验证第二次密码
	function equalspassword(password1,password2){
		if(password1.length>5){
			if(password1==password2){
				return true;
			}else{
				business.myLoadingToast("两次密码不匹配");
				return false;
			}
		}else{
			business.myLoadingToast("密码不能小于6位");
			return false;
		}
	}
