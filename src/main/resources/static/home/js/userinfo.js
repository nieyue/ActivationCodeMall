
$(function(){
	//没登陆就跳首页
	if(!business.account){
		location.href="/";
	}
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
   function show(value){
   	
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
		//修改昵称
		$("#nicknametext").text(business.account.nickname)
		$("#nicknameinput").val(business.account.nickname);
		$("#nicknameinput").hide();
		$("#updatenicknamesave").hide();
		$("#updatenickname").unbind();
		$("#updatenickname").click(function() {
			$("#nicknameinput").show();
			$("#updatenicknamesave").show();
			$("#nicknametext").hide();
			$("#updatenickname").hide();
		})
		$("#updatenicknamesave").unbind();
		$("#updatenicknamesave").click(function() {
			var info = {
		   			accountId:business.account.accountId,
		   			nickname:$("#nicknameinput").val()
		   		};
			ajxget("/account/updateInfo",info,function(data){
				if(data.code==200){
					$("#nicknameinput").hide();
					$("#updatenicknamesave").hide();
					$("#nicknametext").show();
					$("#updatenickname").show();
					business.account.nickname = info.nickname;
					sessionStorage.setItem("account",JSON.stringify(business.account));
					$("#nicknametext").text(business.account.nickname)
					$("#nicknameinput").val(business.account.nickname);
				}
			})
		})
		//初始化出生年月
		var selyear=new Date(business.account.birthday).getYear()+1900;
		$(".sel_year").find("option[value = '"+selyear+"']").attr("selected","selected");
		var selmonth=new Date(business.account.birthday).getMonth()+1;
		$(".sel_month").find("option[value = '"+selmonth+"']").attr("selected","selected");
		var selday=new Date(business.account.birthday).getDate();
		$(".sel_day").find("option[value = '"+selday+"']").attr("selected","selected");
		//保存出生年月
		$("#updatebirthdaysave").click(function(){
			if($(".sel_year option:selected").val()<=0
					||$(".sel_month option:selected").val()<=0
					||$(".sel_day option:selected").val()<=0){
				business.myLoadingToast("请选择正确日期");
				return ;
			}
			var nyr=$(".sel_year option:selected").val()+"/"+$(".sel_month option:selected").val()+"/"+$(".sel_day option:selected").val();
			var birthday=business.getTime(new Date(nyr))
			var info = {
		   			accountId:business.account.accountId,
		   			birthday:birthday
		   		};
			ajxget("/account/updateInfo",info,function(data){
				if(data.code==200){
					business.account.birthday = info.birthday;
					sessionStorage.setItem("account",JSON.stringify(business.account));
					business.myLoadingToast("修改成功");
				
				}
			})
			
		});
		
		$(".useremail").text(business.account.email);
		if(business.account.phone!=null&&business.account.phone!=""){
			$(".userphone").text("手机号码："+business.account.phone);
			$(".bindphone").toggle();
		}
		
		if(business.account.icon!=null&&business.account.icon!=""){
			$(".userheadimg").src = business.account.icon;
		}
		
		if(business.account.sex==1){
			$("#radioman").attr("checked","checked");;
		}else if(business.account.sex==2){
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
		
		$(".setkami").text("接受卡密设置："+cardSecretReceive[business.account.cardSecretReceive]);
		$(".safeuseremail").text("注册邮箱："+business.account.email);
		$('.kamiselect').find('option:eq('+business.account.cardSecretReceive+')').attr('selected','selected');
		//修改卡密
		$(".kamiselect").change(function(){
				
				var value=$(this).children('option:selected').val();
				
				var info = {
					accountId:business.account.accountId,
					cardSecretReceive:value
				};
				
				ajxget("/account/updateInfo",info,function(data){
					if(data.code==200){
						business.account.cardSecretReceive = value;
						$(".setkami").text("接受卡密设置："+cardSecretReceive[value]);
						sessionStorage.setItem("account",JSON.stringify(business.account));
						business.myLoadingToast("修改成功");
					}
				})
		});
		
		
		if(business.account.phone!=null&&business.account.phone!=""){
			$(".safephone").text("绑定手机："+business.account.phone);
			$(".safebindphone").toggle();
		}
		if(business.account.safetyGrade==1){
			$("#safetyGrade").text("账号安全等级：低");
			$(".safediv").width("20%");
		}else if(business.account.safetyGrade==2){
			$("#safetyGrade").text("账号安全等级：中");
			$(".safediv").width("50%");
		}else if(business.account.safetyGrade==3){
			$("#safetyGrade").text("账号安全等级：高");
			$(".safediv").width("100%");
		}
		//显示修改密码界面
		$("#updatePasswordWrap").hide();
		$(".updatapassword").unbind();
		$(".updatapassword").click(function(){	
			//隐藏安全设置
			$("#setdiv").hide();
			//显示修改密码栏
			$("#updatePasswordWrap").show();
			//显示修改密码栏第一步
			$("#updatePassword1").show();
			//显示邮箱
			$(".updatePasswordGetEmail").text(business.account.email);
			
		});
		//点击修改密码发送邮箱
		$("#updatePassword1SendEmail").click(function(){
			//显示修改密码栏第2步
			if(checkEmail(business.account.email)){
			var info = {
					adminName:business.account.email,
					templateCode:2//修改密码
				}
			ajxget("/account/validCode",info,function(data){
				if(data.code==200){
		        	if(data.msg=="已经验证"){
		        		//alert(data.msg);
		        		business.myLoadingToast(data.msg);
			        	return;
			        }else{
			        	business.myLoadingToast("验证码发送成功，请注意查收");
			        	//alert("验证码发送成功，请注意查收");		
			        	//显示修改密码栏第2步
			        	$("#updatePassword1").hide();
						$("#updatePassword2").show();
			        }
			        }else{
			        	//alert(data.msg);
			        	business.myLoadingToast(data.msg);
			        }
                });
			}
		});
		//点击进入第三步，激活
		$("#updatePassword2ValidEmail").click(function(){
			//显示修改密码栏第3步
        	$("#updatePassword2").hide();
			$("#updatePassword3").show();
		});
		//取消修改密码
		$("#updatePassword3Cancel").click(function(){
			$("#setdiv").show();
			$("#updatePasswordWrap").hide();
			$("#updatePassword3").hide();
		});
		//确认修改密码
		$("#updatePassword3Sure").click(function(){
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			var email = Request["email"];
			if(business.equalspassword(password1,password2)){
				var info = {
						adminName:business.account.email,
						password:password1
					}
				ajxget("/account/updatePassword",info,function(data){
					if(data.code==200){
						business.account = data.data[0];
						sessionStorage.setItem("account",JSON.stringify(business.account));
						//alert("修改成功");
						business.myLoadingToast("修改成功");
						$("#setdiv").show();
						$("#updatePasswordWrap").hide();
						$("#updatePassword3").hide();
					}else{
						//alert(data.msg);
						business.myLoadingToast(data.msg);
					}
	                    
				        
	                });
				
			}
		});
		
	}else if(value=="我的收货地址"){
		//初始化三级联动
		var $distpicker = $('#distpicker');
		  $distpicker.distpicker({
			  placeholder: false,
		    province: '湖南省',
		    city: '长沙市',
		    district: '岳麓区'
		  });
		
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
		//显示收货地址列表
		getaddress();
		
		var country = "中国大陆";
		
		$(".kamiselect").change(function(){
					country=$(this).children('option:selected').text();
			});
		//新增保存
		$("#savebtn").click(function(){
			var province = $("#province option:selected").val();
			var city = $("#city option:selected").val();
			var area = $("#district option:selected").val();
			var address = $("#address").val();
			var postcode = $("#postcode").val();
			var name = $("#addressusername").val();
			var phone = $("#phone").val();
			var telephone = $("#telephone").val();
			
			if(address==''){
				business.myLoadingToast("请输入详细地址");
				return false;
			}
			if(postcode==''){
				business.myLoadingToast("请输入邮政编码");
				return false;
			}
			if(name==''){
				business.myLoadingToast("请输入收货人姓名");
				return false;
			}
			if(phone.length!=11&&telephone==''){
				business.myLoadingToast("请输入正确的手机号或电话号码");
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
				telephone:telephone,
				postcode:postcode,
				country:country,
				province:province,
				city:city,
				area:area,
				address:address,
				isDefault:isDefault,
				accountId:business.account.accountId
			};
			ajxget("/receiptInfo/add",info,function(data){
				if(data.code==200){
					business.myLoadingToast("添加成功")
					location.reload();
				}
			})
		});
		
	}
   }
   //初始化性别
   if(business.account&&business.account.sex==1){
	   $("#radioman").prop("check",true);
   }else if(business.account.sex==2){
	   $("#radiowoman").prop("check",true);
   }
   //点击性别
	$("#radioman").click(function(){
		upsex(business.account.accountId,1);
	});
  
   $("#radiowoman").click(function(){
		upsex(business.account.accountId,2);
	});
   
   
// 修改用户性别
   function upsex(accountId,sex){
   	var info = {
			accountId:accountId,
			sex:sex
		};
		ajxget("/account/updateInfo",info,function(data){
			if(data.code==200){
				business.account.sex = sex;
				sessionStorage.setItem("account",JSON.stringify(business.account));
			}
		})
   }
// 获取优惠券
   function getcoupon(){
   		var info = {
   			accountId:business.account.accountId,
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
   			accountId:business.account.accountId,
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
   			accountId:business.account.accountId,
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
   			accountId:business.account.accountId,
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
		        		
		        		if(child.telephone){
		        			phonenum +="/"+ child.telephone;
		        		}
		        		var isD=child.isDefault==1?'默认':'';
//						tr.id = child.integralDetailId;
		        		var strchild=JSON.stringify(child);
		        		strchild=strchild.replace(/"/g, "\'");
						var html = '<tr class="addresstd height90" ><td >'+child.name+'</td><td  class="font_size14 padding_10">'+child.country+'/'+child.province+'/'+child.city+'/'+child.area+'</td><td class="font_size14 padding_10">'+child.address+'</td><td >'+child.postcode+'</td><td >'+phonenum+'</td><td ><div>'+isD+'</div><a class="fl margin_left60" style="color: #4cafe9;" onclick="updateaddress('+strchild+')">修改</a><a class="fl margin_left20" style="color: #ff7400;" onclick="deleteaddress('+business.account.accountId+','+child.receiptInfoId+')">删除</a></td></tr>';
						table.append(html); 
		        	}
			}
		})
   }


   
})
//更新收货地址显示
function updateaddress(child){
	console.log(child)
	var $distpicker2 = $('#distpicker2');
	$distpicker2.distpicker('destroy');
		$distpicker2.distpicker({
			placeholder: false,
			province: child.province,
			city: child.city,
			district: child.district
		});
	//$("#province2").find("option[value = '"+child.province+"']").attr("selected","selected");
	//$("#city2").find("option[value = '"+child.city+"']").attr("selected","selected");
	//$("#district2").find("option[value = '"+child.district+"']").attr("selected","selected");
	$("#address2").val(child.address);
	$("#postcode2").val(child.postcode);
	$("#addressusername2").val(child.name);
	$("#phone2").val(child.phone);
	$("#telephone2").val(child.telephone);
	$("#isdefult2").prop("checked",child.isDefault==1?true:false)
	//显示更新
	$("#updateReceiptInfoWrap").show();
	var country = "中国大陆";
	//修改保存
	$("#savebtn2").unbind();
	$("#savebtn2").click(function(){
		var province = $("#province2 option:selected").val();
		var city = $("#city2 option:selected").val();
		var area = $("#district2 option:selected").val();
		var address = $("#address2").val();
		var postcode = $("#postcode2").val();
		var name = $("#addressusername2").val();
		var phone = $("#phone2").val();
		var telephone = $("#telephone2").val();
		
		if(address==''){
			business.myLoadingToast("请输入详细地址");
			return false;
		}
		if(postcode==''){
			business.myLoadingToast("请输入邮政编码");
			return false;
		}
		if(name==''){
			business.myLoadingToast("请输入收货人姓名");
			return false;
		}
		if(phone.length!=11&&telephone==''){
			business.myLoadingToast("请输入正确的手机号或电话号码");
			return false;
		}
		
		var phonenum = phone;
		if(phone==''){
			phonenum = telphone;
		}
		var isDefault = 0;
		if($("#isdefult2").is(':checked')){
			isDefault = 1;
		}
		var info = {
			receiptInfoId:child.receiptInfoId,
			name:name,
			phone:phonenum,
			telephone:telephone,
			postcode:postcode,
			country:country,
			province:province,
			city:city,
			area:area,
			address:address,
			isDefault:isDefault,
			accountId:child.accountId
		};
		ajxget("/receiptInfo/update",info,function(data){
			if(data.code==200){
				business.myLoadingToast("修改成功")
				location.reload();
			}
		})
	});
}
//删除收货地址
function deleteaddress(accountId,id){
	var info = {
		receiptInfoId:id,
		accountId:accountId
	};
	
	ajxget("/receiptInfo/delete",info,function(data){
		if(data.code==200){
			location.reload();
		}
	})
}
//初始化图像
if(business.account.icon){
	$("#updateIcon").attr("src",business.account.icon);	
}
//修改头像
function updataimg(){
   	$('#updateIconFile').click();
   }

/*$('#updateIconFile').change(function(){
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
        $('#updateIcon').attr("src",e.target.result);
      }
    })*/
 //上传图像   
business.getQiniuSimpleUploader(business,{
	browseButton:'updateIconFile',
	dropElement:'updateIconFileBox',
	resource:'business.account.icon',
	success:function(url){
		var info = {
	   			accountId:business.account.accountId,
	   			icon:url
	   		};
		ajxget("/account/updateInfo",info,function(data){
			if(data.code==200){
				business.account.icon=url
				sessionStorage.setItem("account",JSON.stringify(business.account));
				$("#updateIcon").attr("src",business.account.icon);
			}
		})
	}
}); 

