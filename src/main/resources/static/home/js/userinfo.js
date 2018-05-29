$(function(){
	$.ms_DatePicker({
            YearSelector: ".sel_year",
            MonthSelector: ".sel_month",
            DaySelector: ".sel_day"
   });
  	var request  = getUrlInfo(location.search);
	var showtext = request["show"];
	var showid = request["showid"];
	console.log("showid="+showid);
	var cardSecretReceive = new Array("全部接收","本账号内接收","邮箱接收","手机接收");
	var paytype = new Array("","支付宝","微信","百度钱包","Paypal","网银");
	
   	if(showtext==undefined){
   		showtext = "个人资料";
   		showid = 1;
   	}
   	$('.userinfoul li').removeClass('bg_573c1e');
   	$(".userinfoul li:nth-child("+showid+")").addClass('bg_573c1e');
   	show(showtext);
    $(".userinfoul li").click(function(){
//		$('.userinfoul li').removeClass('bg_573c1e');
//		$(this).addClass('bg_573c1e');
//		var value = $(this).text();
//		console.log(value);
//		show(value);
		var thistext=$(this).text();
	    var showid = $(this).index()+1;
	    window.location.href= "./buyuserinfo.html?show="+thistext+"&showid="+showid;
});
   var host = localStorage.getItem("hostUrl");
   function show(value){
   	var user = JSON.parse(localStorage.getItem("user"));
   	
   	if(value=="个人资料"){
		if($(".userinfocontent").is(":hidden")){
			$(".userinfocontent").toggle();
		}
		if(!$(".youhuiquandiv").is(":hidden")){
			$(".youhuiquandiv").toggle();
		}
		if(!$(".chengzhangdiv").is(":hidden")){
			$(".chengzhangdiv").toggle();
		}
		if(!$(".jiaoyidiv").is(":hidden")){
			$(".jiaoyidiv").toggle();
		}
		if(!$(".setdiv").is(":hidden")){
			$(".setdiv").toggle();
		}
		if(!$(".shouhuodiv").is(":hidden")){
			$(".shouhuodiv").toggle();
		}
		
		$(".username").text(user.nickname);
		$(".useremail").text(user.email);
		if(user.phone!=null&&user.phone!=""){
			$(".userphone").text("手机号码："+user.phone);
			$(".bindphone").toggle();
		}
		
		if(user.icon!=null&&user.icon!=""){
			$(".userheadimg").src = user.icon;
		}
		
		if(user.sex==1){
			$("#radioman").attr("checked","checked");;
		}else if(user.sex==2){
			$("#radiowoman").attr("checked","checked");
		}
	}else if(value=="我的优惠券"){
		if(!$(".userinfocontent").is(":hidden")){
			$(".userinfocontent").toggle();
		}
		if($(".youhuiquandiv").is(":hidden")){
			$(".youhuiquandiv").toggle();
		}
		if(!$(".chengzhangdiv").is(":hidden")){
			$(".chengzhangdiv").toggle();
		}
		if(!$(".jiaoyidiv").is(":hidden")){
			$(".jiaoyidiv").toggle();
		}
		if(!$(".setdiv").is(":hidden")){
			$(".setdiv").toggle();
		}
		if(!$(".shouhuodiv").is(":hidden")){
			$(".shouhuodiv").toggle();
		}
		getcoupon();
	}else if(value=="我的成长"){
		if(!$(".userinfocontent").is(":hidden")){
			$(".userinfocontent").toggle();
		}
		if(!$(".youhuiquandiv").is(":hidden")){
			$(".youhuiquandiv").toggle();
		}
		if($(".chengzhangdiv").is(":hidden")){
			$(".chengzhangdiv").toggle();
		}
		if(!$(".jiaoyidiv").is(":hidden")){
			$(".jiaoyidiv").toggle();
		}
		if(!$(".setdiv").is(":hidden")){
			$(".setdiv").toggle();
		}
		if(!$(".shouhuodiv").is(":hidden")){
			$(".shouhuodiv").toggle();
		}
		
		getintarll();
	
	}else if(value=="交易账单"){
		if(!$(".userinfocontent").is(":hidden")){
			$(".userinfocontent").toggle();
		}
		if(!$(".youhuiquandiv").is(":hidden")){
			$(".youhuiquandiv").toggle();
		}
		if(!$(".chengzhangdiv").is(":hidden")){
			$(".chengzhangdiv").toggle();
		}
		if($(".jiaoyidiv").is(":hidden")){
			$(".jiaoyidiv").toggle();
		}
		if(!$(".setdiv").is(":hidden")){
			$(".setdiv").toggle();
		}
		if(!$(".shouhuodiv").is(":hidden")){
			$(".shouhuodiv").toggle();
		}
		getorderlist();
	}else if(value=="安全设置"){
		if(!$(".userinfocontent").is(":hidden")){
			$(".userinfocontent").toggle();
		}
		if(!$(".youhuiquandiv").is(":hidden")){
			$(".youhuiquandiv").toggle();
		}
		if(!$(".chengzhangdiv").is(":hidden")){
			$(".chengzhangdiv").toggle();
		}
		if(!$(".jiaoyidiv").is(":hidden")){
			$(".jiaoyidiv").toggle();
		}
		if($(".setdiv").is(":hidden")){
			$(".setdiv").toggle();
		}
		if(!$(".shouhuodiv").is(":hidden")){
			$(".shouhuodiv").toggle();
		}
		
		$(".setkami").text("接受卡密设置："+cardSecretReceive[user.cardSecretReceive]);
		$(".safeuseremail").text("注册邮箱："+user.email);
		$('.kamiselect').find('option:eq('+user.cardSecretReceive+')').attr('selected','selected');
		$(".kamiselect").change(function(){
				
				var value=$(this).children('option:selected').val();
				
				var info = {
					accountId:user.accountId,
					cardSecretReceive:value
				};
				
				ajxget("/account/update",info,function(data){
					if(data.code==200){
						user.cardSecretReceive = value;
						$(".setkami").text("接受卡密设置："+cardSecretReceive[value]);
						localStorage.setItem("user",JSON.stringify(user));
					}
				})
		});
		
		
		if(user.phone!=null&&user.phone!=""){
			$(".safephone").text("绑定手机："+user.phone);
			$(".safebindphone").toggle();
		}
		if(user.safetyGrade==1){
			$("#safetyGrade").text("账号安全等级：低");
			$(".safediv").width("20%");
		}else if(user.safetyGrade==2){
			$("#safetyGrade").text("账号安全等级：中");
			$(".safediv").width("50%");
		}else if(user.safetyGrade==3){
			$("#safetyGrade").text("账号安全等级：高");
			$(".safediv").width("100%");
		}
	}else if(value=="我的收货地址"){
		if(!$(".userinfocontent").is(":hidden")){
			$(".userinfocontent").toggle();
		}
		if(!$(".youhuiquandiv").is(":hidden")){
			$(".youhuiquandiv").toggle();
		}
		if(!$(".chengzhangdiv").is(":hidden")){
			$(".chengzhangdiv").toggle();
		}
		if(!$(".jiaoyidiv").is(":hidden")){
			$(".jiaoyidiv").toggle();
		}
		if(!$(".setdiv").is(":hidden")){
			$(".setdiv").toggle();
		}
		if($(".shouhuodiv").is(":hidden")){
			$(".shouhuodiv").toggle();
		}
		
		getaddress();
		var country = "中国大陆";
		$(".kamiselect").change(function(){
					country=$(this).children('option:selected').text();
			});
		$(".savebtn").click(function(){
			var city = $("#city").val();
			var address = $("#address").val();
			var postcode = $("#postcode").val();
			var name = $("#addressusername").val();
			var phone = $("#phone").val();
			var telphone = $("#telphone").val();
			
			if(city.length<3){
				alert("请输入所在地区");
				return false;
			}
			if(address==''){
				alert("请输入详细地址");
				return false;
			}
			if(postcode==''){
				alert("请输入邮政编码");
				return false;
			}
			if(name==''){
				alert("请输入收货人姓名");
				return false;
			}
			if(phone.length!=11&&telphone==''){
				alert("请输入正确的手机号或电话号码");
				return false;
			}
			
			var phonenum = phone;
			if(phone==''){
				phonenum = telphone;
			}
			var isDefault = 0;
			if($("#isdefult").is(':checked')){
				isDefault = 1;
			}
			
			
			var info = {
				name:name,
				phone:phonenum,
				postcode:postcode,
				country:country,
				city:city,
				address:address,
				isDefault:isDefault,
				accountId:user.accountId
			};
			ajxget("/receiptInfo/add",info,function(data){
				if(data.code==200){
					alert("添加成功")
					location.reload();
				}
			})
			
			
			
		});
		
		
	}
   }
   
	$("#radioman").click(function(){
		upsex(user.accountId,1);
	});
  
   $("#radiowoman").click(function(){
		upsex(user.accountId,2);
	});
   
   
// 修改用户性别
   function upsex(userid,sex){
   	var info = {
			accountId:userid,
			sex:sex
		};
		ajxget("/account/update",info,function(data){
			if(data.code==200){
				user.sex = sex;
				localStorage.setItem("user",JSON.stringify(user));
			}
		})
   }
// 获取优惠券
   function getcoupon(){
   		var info = {
   			accountId:user.accountId,
   			pageNum:1,
   			pageSize:100
   		};
   		ajxget("/coupon/list",info,function(data){
			if(data.code==200){
					var couponlist = data.data;
					
		        	var table = $('.youhuiquandiv');
		        	for(var i = 0; i < couponlist.length; i++) {
		        		var coupon = couponlist[i];
		        		var div = document.createElement('div');
		        		div.className = 'youhuiquanitem';
						div.id = coupon.couponId;
						var html = '<div style="width: 100%;height: 150px;"><div style="width: 100%;height: 100%;position:relative;float: left;"><div style="position: absolute;top: 50%;transform: translateY(-50%);"><img class="youhuiquanimg" src="'+coupon.imgAddress+'"/><div style="float: left;margin-left: 10px;"><p style="color: #1b1b1b;font-size: 14px;">'+coupon.name+'</p><p style="color: #666666;font-size: 12px;">优惠券使用说明：</p><p style="color: #666666;font-size: 12px;">'+coupon.content+'</p></div></div></div></div><hr align="center" width="100%" color="#ededed" size="1" /><p class="youquanyouxiaoqi">有效期至'+coupon.endDate+'</p>';
						div.innerHTML = html;

						table.append(div); 
		        	}
			}
		})
   }
   
// 获取积分列表

function getintarll(){
	var info = {
   			accountId:user.accountId,
   			pageNum:1,
   			pageSize:100
   		};
   		ajxget("/integralDetail/list",info,function(data){
			if(data.code==200){
					var list = data.data;
		        	var table = $('#chengzhang_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'chengzhangtd height40';
						tr.id = child.integralDetailId;
						var html = '<td >'+child.createDate+'></td><td >'+child.integral+'</td>';
						tr.innerHTML = html;

						table.append(tr); 
		        	}
			}
		})
   		
}


// 获取交易列表

function getorderlist(){
	var info = {
   			accountId:user.accountId,
   			type:1,
   			status:3,
   			pageNum:1,
   			pageSize:100
   		};
   		ajxget("/order/list",info,function(data){
			if(data.code==200){
					var list = data.data;
		        	var table = $('#jiaoyi_tb');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'chengzhangtd height40';
						tr.id = child.integralDetailId;
						var html = '<td >'+child.paymentDate+'</td><td style="padding-left: 10px;padding-right: 10px;">'+child.orderDetailList[0].name+'</td><td >'+paytype[child.payType]+'</td><td >'+child.orderDetailList[0].totalPrice+'</td>';
						tr.innerHTML = html;

						table.append(tr); 
		        	}
			}
		})
   		
}

// 获取收货地址
   function getaddress(){
   	var table = $('#address_tb');
   	table.innerHTML = "";
   		var info = {
   			accountId:user.accountId,
   			pageNum:1,
   			pageSize:100
   		};
   		ajxget("/receiptInfo/list",info,function(data){
			if(data.code==200){
					var list = data.data;
					$("#addressnum").html(list.length);
		        	for(var i = 0; i < list.length; i++) {
		        		
		        		var child = list[i];
		        		var tr = document.createElement('tr');
		        		tr.className = 'addresstd height90';
		        		var phonenum = child.phone;
		        		
		        		if(child.phone==null||child.phone==''){
		        			phonenum = child.telephone;
		        		}
//						tr.id = child.integralDetailId;
						var html = '<tr class="addresstd height90" ><td >'+child.name+'</td><td  class="font_size14 padding_10">'+child.country+'  '+child.city+'</td><td class="font_size14 padding_10">'+child.address+'</td><td >000000</td><td >'+phonenum+'</td><td ><a class="fl margin_left60" style="color: #4cafe9;">修改</a><a class="fl margin_left20" style="color: #ff7400;" onclick="deletaddress('+user.accountId+','+child.receiptInfoId+')">删除</a></td></tr>';
						tr.innerHTML = html;

						table.append(tr); 
		        	}
			}
		})
   }


   
})


function deletaddress(userid,id){
	var info = {
		receiptInfoId:id,
		accountId:userid
	};
	
	ajxget("/receiptInfo/delete",info,function(data){
		if(data.code==200){
			location.reload();
		}
	})
}


function updataimg(){
   	$('#up').click();
   }

$('#up').change(function(){
    //获取input file的files文件数组;
    //$('#filed')获取的是jQuery对象，.get(0)转为原生对象;
    //这边默认只能选一个，但是存放形式仍然是数组，所以取第一个元素使用[0];
      var file = $('#up').get(0).files[0];
    //创建用来读取此文件的对象
      var reader = new FileReader();
    //使用该对象读取file文件
      reader.readAsDataURL(file);
    //读取文件成功后执行的方法函数
      reader.onload=function(e){
    //读取成功后返回的一个参数e，整个的一个进度事件
        console.log(e);
    //选择所要显示图片的img，要赋值给img的src就是e中target下result里面
    //的base64编码格式的地址
        $('.updataimg').attr("src",e.target.result);
      }
    })

