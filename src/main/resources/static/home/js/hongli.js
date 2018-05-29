var user = JSON.parse(localStorage.getItem("user"));
var paytype = new Array("","支付宝","微信","百度钱包","Paypal","网银");
$(function(){
	
	var finance = JSON.parse(localStorage.getItem("finance"));
	
	if(user.icon!=null&&user.icon!=""){
		$("#hongli_userimg").src = user.icon;
	}
	$("#hongli_username").text(user.nickname);
	$(".yuep").text("￥"+finance.money);
	
	$("#gomayuserinfo").click(function(){
		window.location.href = "hongli_userinfo.html";
	});
	
	$("#hongli_sellgologin").click(function(){
	window.location.href = "../login.html?roletype=1";
	});
	$("#hongli_tuiguanggologin").click(function(){
		window.location.href = "../login.html?roletype=2";
	});
	$("#hongli_usergologin").click(function(){
		window.location.href = "../login.html?roletype=3";
	});
	
})
function gettuiguan(type){
	var pagesize=5;
		if(type==1){
			pagesize = 100;
		}
		var info = {
			accountId:user.accountId,
			pageNum:1,
			pageSize:pagesize
		};
		
		ajxget("/spreadLink/list",info,function(data){
			if(data.code==200){
				var list = data.data;
		        	var table = $('#tuiguang_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'tabletd';
						tr.id = child.spreadLinkId;
						var html = '<td><p style="width: auto;">'+(i+1)+'</p></td><td class="color_7400 ">'+child.link+'</td><td>'+child.spreadNumber+'</td>';
						tr.innerHTML = html;
						table.append(tr); 
		        	}
			}
		})
		
}


function getmyuser(type){
		var pagesize=5;
		if(type==1){
			pagesize = 100;
		}
		var info = {
			accountId:user.accountId,
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
						var html = '<td><p style="width: auto;">'+(i+1)+'</p></td><td>'+child.name+'</td><td>'+child.email+'</td><td>￥'+child.tradeNumber+'</td>';
						tr.innerHTML = html;
						table.append(tr); 
		        	}
			}
		})
		
}


function gettixian(status){
		var pagesize=5;
		if(status==1){
			pagesize = 100;
		}
		var info = {
			accountId:user.accountId,
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
