//var host = localStorage.getItem("urlHost");

$(function(){
	$("#step1_next").click(function(){
		var email = $("#useremail").val();
		
		if(checkEmail(email)){
			var info = {
					adminName:email,
					templateCode:1
				}
			ajxget("/account/validCode",info,function(data){
				if(data.code==200){
					$("#emaildiv").toggle();
		        	$("#haveemaildiv").toggle();
		        	$(".emailp").text(email);
		        	if(data.msg=="已经验证"){
		        		alert(data.msg);
			        	return;
			        }else{
			        	alert("验证码发送成功，请注意查收");			        	
			        }
			        }else{
			        	alert(data.msg);
			        }
                });
		
		}
	});
	
	$(".register_havejihuo").click(function(){
		var email = $("#useremail").val();
		var request  = getUrlInfo(location.search);
		var roletype = request["roletype"];
		window.location.href='register_step2.html?email='+email+"&roletype="+roletype;
	});
	
	
	
	
	
})


	var countdown=60;  
	var _generate_code = $(".register_yzm");
	function getcode(){
		  
      var phone = $("#userphone").val();
      if(phone.length != 11 ){  
        alert("请填写正确的手机号！");  
        return false;  
      }
      _generate_code.attr("disabled", true);
      	settime();
      	var info = {
					adminName:phone,
					templateCode:1
				}
      	
      	ajxget("/account/validCode",info,function(data){
                    if(data.code==200){
                    	alert("验证码发送成功，请注意查收");
                    }
		        	
			        
                });
      	
      	
      
	}

	function gonext(){
		var username = $("#username").val();
		var userphone = $("#userphone").val();
		var code = $("#code").val();
		var email = getUrlInfo(location.search);
		var roletype = email["roletype"];
		if(nonull(username,userphone,code)){
			window.location.href="register_step3.html?username="+username+"&userphone="+userphone+"&code="+code+"&email="+email["email"]+"&roletype="+roletype;
		}
	}

	function nonull(username,userphone,code){
		if(username.length<3){
			alert("用户名不能少于3个字符");
			return false;
		}
		if(userphone.length!=11){
			alert("请输入正确的手机号");
			return false;
		}
		if(code.length<0){
			alert("请输入验证码");
			return false;
		}
		return true;
	}
 	
	function settime() {  
      if (countdown == 1) {  
        _generate_code.attr("disabled",false);  
        _generate_code.val("发送");  
        countdown = 60;  
        return false;  
      } else {  
        _generate_code.attr("disabled", true);  
        _generate_code.text(countdown + " S");  
        countdown--;  
      }  
      setTimeout(function() {  
        settime();  
      },1000);  
    }
	

	function register(){
		var url = location.search;
		var Request = getUrlInfo(url);
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
			ajxget("/account/webregister",info,function(data){
				if(data.code==200){
					alert("注册成功")
					window.location.href='login.html?roletype='+roletype;
				}else{
					alert(data.msg);
				}
                    
			        
                });
			
			
			
		}
	}
	
	function equalspassword(password1,password2){
		if(password1.length>5){
			if(password1==password2){
				return true;
			}else{
				alert("两次密码不匹配");
				return false;
			}
		}else{
			alert("密码不能小于6位");
			return false;
		}
	}
