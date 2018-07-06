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
	}else if(location.href.indexOf("/sell/sell_newgood.html")>=0){
	/**
	 * 销售新产品
	 */	
		if(!sessionStorage.getItem("sellerMer")){
			location.href="/";
		}
		business.mer=JSON.parse(sessionStorage.getItem("sellerMer"));
		//显示商品图片
		business.imgAddressList=[];
		if(!business.mer.merImgList||business.mer.merImgList.length<=0){
			business.imgAddressList=[{imgAddress:business.mer.imgAddress}]
		}else{
			business.imgAddressList=business.mer.merImgList;
		}
		var imghtml="";
		for (var i = 0; i < business.imgAddressList.length; i++) {
			imghtml='<img class="detailimg" src="'+business.imgAddressList[i].imgAddress+'" />';
			$("#imgAddressList").append(imghtml);
		}
		//商品名称
		$("#merName").text(business.mer.name);
		//商品种类名称
		$("#merCateName").text(business.mer.merCate.name);
		//商品原始单价
		$("#merOldUnitPrice").val(business.mer.oldUnitPrice);
		//优惠折扣
		$("#merDiscount").val(business.mer.discount);
		//商品单价
		$("#merUnitPrice").text(parseFloat($("#merOldUnitPrice").val()*$("#merDiscount").val()).toFixed(2));
		$("#merOldUnitPrice").change(function(){
			//商品单价
			$("#merUnitPrice").text(parseFloat($("#merOldUnitPrice").val()*$("#merDiscount").val()).toFixed(2));
		});
		$("#merDiscount").change(function(){
			//商品单价
			$("#merUnitPrice").text(parseFloat($("#merOldUnitPrice").val()*$("#merDiscount").val()).toFixed(2));
		});
		//商品手续费收取
		$("#platformProportion").text(business.mer.platformProportion);
		//默认可以切换，不锁定
		business.addMerCardCipherIsClock=false;
		//卡密类型
		business.merCardCipherType=1;//默认是1上传文件的字符串文字，2字符串文字，3字符串图片
		//上传文件
		$("#addMerCardCipherFileUpload").click(function(){
			$("#addMerCardCipherFile").click();
		});
		//商品卡密
		business.merCardCipherCodes=[];
		$("#addMerCardCipherFile").change(function(){
			//
			business.fileUpload(
 				    {inputfile:$("#addMerCardCipherFile"),
 				    	photoExt:[".xls",".xlsx"],
 				    ajaxObj:{
 				        formData:[
 				            {key:"excel",value:$("#addMerCardCipherFile").get(0).files[0]}
 				            ],
 				        url:business.url+"/tool/importExcel",
 				        success:function(data){
 				            if(data.code==200){
 				            	console.log(data.data)
 				            //成功后锁
 				            	business.addMerCardCipherIsClock=true;
 				            for (var i = 0; i < data.data.length; i++) {
 				            	business.merCardCipherCodes.push(data.data[i])
 				            	}
 				            $("#addMerCardCipherFileCodes").html(business.merCardCipherCodes.toString());	
 				            business.myPrevToast("上传成功",null,"remove");
 				            }
 				        }
 				    }
 				}
 				);
		});
		//上传图片 ,自己服务器上传
	/*	$("#addMerCardCipherImgUpload").click(function(){
			$("#addMerCardCipherImg").click();
		});
		$("#addMerCardCipherImg").change(function(){
			//
			business.fileUpload(
 				    {inputfile:$("#addMerCardCipherImg"),
 				    	photoExt:[".jpg",".png"],
 				    ajaxObj:{
 				        formData:[
 				            {key:"editorUpload",value:$("#addMerCardCipherImg").get(0).files[0]}
 				            ],
 				        url:business.url+"/tool/file/add",
 				        success:function(data){
 				            if(data.code==200){
 				            	console.log(data.data)
 				            //成功后锁
 				            business.addMerCardCipherIsClock=true;
 				            var imghtml="";
 				            for (var i = 0; i < data.data.length; i++) {
 				            	business.merCardCipherCodes.push(data.data[i]);
 				            	imghtml='<img class="detailimg" style="float:none;" src="'+data.data[i]+'" />'
 				            	$("#merCardCipherImgList").append(imghtml);	
 				            }
 				            business.myPrevToast("上传成功",null,"remove");
 				            }
 				        }
 				    }
 				}
 				);
		});*/
		//七牛云上传图片
		business.qiniuUploadImg("#addMerCardCipherWrap3", "#addMerCardCipherImgUpload", "#addMerCardCipherImg",null,function(url){
			//成功后锁
            business.addMerCardCipherIsClock=true;
            var imghtml="";
            	business.merCardCipherCodes.push(url);
            	imghtml='<img class="detailimg" style="float:none;" src="'+url+'" />'
            	$("#merCardCipherImgList").append(imghtml);	
            	business.myPrevToast("上传成功",null,"remove");
		});
		//点击选择商品卡卡密上传类型
		$(".sellnew_positionul li").click(function(){
			if(business.addMerCardCipherIsClock){
				business.myLoadingToast("不能选多个种类上传");
				return;
			}
			$('.sellnew_positionul li').removeClass('clicknewli');
			$(this).addClass('clicknewli');
			if($(this).attr("id")=='addMerCardCipherType1'){
				$("#addMerCardCipherWrap1").show();
				$("#addMerCardCipherWrap2").hide();
				$("#addMerCardCipherWrap3").hide();
				business.merCardCipherType=1;
			}else if($(this).attr("id")=='addMerCardCipherType2'){
				$("#addMerCardCipherWrap1").hide();
				$("#addMerCardCipherWrap2").show();
				$("#addMerCardCipherWrap3").hide();
				business.merCardCipherType=2;
			}else if($(this).attr("id")=='addMerCardCipherType3'){
				$("#addMerCardCipherWrap1").hide();
				$("#addMerCardCipherWrap2").hide();
				$("#addMerCardCipherWrap3").show();
				business.merCardCipherType=3;
			}
			
		});
		//提交商品申请
		$("#addSellerMer").click(function(){
			//价格
			var merUnitPrice=$("#merUnitPrice").text().trim();
			//折扣
			var merDiscount=$("#merDiscount").val().trim();
			if(!business.userVerification.merPrice.code.test(merUnitPrice)){
				business.myLoadingToast(business.userVerification.merPrice.value);
				return;
			}
			if(!business.userVerification.merDiscount.code.test(merDiscount)){
				business.myLoadingToast(business.userVerification.merDiscount.value);
				return;
			}
			if(business.merCardCipherType!=1&&business.merCardCipherType!=2&&business.merCardCipherType!=3){
				business.myLoadingToast("卡密必填");
				return;
			}
			if(business.merCardCipherType==2){
				business.merCardCipherCodes=[];//制空
				var addMerCardCipherText=$("#addMerCardCipherText").val();
				var amcctarray=addMerCardCipherText.split(",");
				for (var i=0;i< amcctarray.length;i++) {
					if(amcctarray[i]){
						business.merCardCipherCodes.push(amcctarray[i]);						
					}
				}
			}
			if(business.merCardCipherCodes.length<=0){
				business.myLoadingToast("缺少卡密");
				return;
			}
			var info = {
					accountId:business.account.accountId,//商户id
					merId:business.mer.merId,
					unitPrice:merUnitPrice,
					discount:merDiscount,
					merCardCipherType:business.merCardCipherType,
					merCardCiphers:business.merCardCipherCodes.toString()
				};
				business.ajax("/mer/addSellerMer",info,function(data){
					if(data.code==200){
						business.myLoadingToast("添加成功")
						sessionStorage.removeItem("sellerMer")
					}else{
						business.myLoadingToast(data.msg)
					}
					});
			
		});
		
	}else if(location.href.indexOf("/sell/sell_applynewgood.html")>=0){
	/**
	 * 商品类型申请
	 */	
		//提交商品类型申请
		$("#addMerCateCommit").off().on("click",function(){
			var merCateName=$("#merCateName").val().trim();
			var merCateSummary=$("#merCateSummary").val().trim();
			if(!merCateName||merCateName.length<2){
				business.myLoadingToast("商品类型字数最少2个");
				return ;
			}
			if(!merCateSummary||merCateSummary.length<2||merCateSummary.length>200){
				business.myLoadingToast("商品类型介绍字数2-200");
				return ;
			}
			var info = {
					accountId:business.account.accountId,//商户id
					type:3,//新增商品类型
					content:'{"merCateName":"'+merCateName+'","merCateSummary":"'+merCateSummary+'"}'
				};
				business.ajax("/notice/add",info,function(data){
					if(data.code==200){
						business.myLoadingToast("添加成功");
						sessionStorage.removeItem("sellerMer");
					}else{
						business.myLoadingToast(data.msg)
					}
					});
			
		});
	}else if(location.href.indexOf("/sell/sell_order.html")>=0){
	/**
	 * 已完成订单
	 */	
		//订单数量
		$(".orderCount32").text(0);
		//获取已完成订单列表
		business.getOrderList32=function(){
			var info = {
				merchantAccountId:business.account.accountId,//商户id
				status:3,//订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
				substatus:2,//子状态，2（1待支付），3（1冻结单，2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）
				pageNum:1,
				pageSize:100
			};
			
			business.ajax("/order/list",info,function(data){
				if(data.code==200){
					var html="";
						business.orderList32=data.data;
					for (var i = 0; i < business.orderList32.length; i++) {
						var child=business.orderList32[i];
							html='<tr class="ordertabletd">'
									+'<td>'+child.orderNumber+'</td>'
									+'<td>'+child.orderDetail[0].merCateName+'</td>'
									+'<td>'+child.orderDetail[0].number+'</td>'
									+'<td style="color: #e04600;">￥'+child.orderDetail[0].totalPrice+'</td>'
									+'<td>'+child.paymentDate+'</td>'
									+'<td >'
										+'<a style="text-decoration: none;color: #4cafe9;" href="../sell/sell_orderdetail.html?orderId='+child.orderId+'">详情</a>'	
									+'</td>'
								+'</tr>';
							$("#orderList32").append(html);
						}
					}
				});
				
		};
		business.getOrderList32();
	}else if(location.href.indexOf("/sell/sell_record.html")>=0){
	/**
	 * 进账记录
	 */	
		//获取进账记录列表
		business.getFinanceRecordList5=function(){
			var info = {
				accountId:business.account.accountId,
				type:5,//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户），7申请退保证金
				pageNum:1,
				pageSize:100
			};
			
			business.ajax("/financeRecord/list",info,function(data){
				if(data.code==200){
					var html="";
						business.financeRecordList5=data.data;
					for (var i = 0; i < business.financeRecordList5.length; i++) {
						var child=business.financeRecordList5[i];
							html='<tr class="recordtabletd">'
										+'<td>'+child.createDate+'</td>'
										+'<td style="color: #4cafe9;">'+child.transactionNumber+'</td>'
										+'<td>'+business.payType[child.method]+'</td>'
										+'<td>'+child.realname+'</td>'
										+'<td>'+child.money+'</td>'
										+'<td>'+parseInt(Math.round((child.money-child.realMoney)/child.money))+'%</td>'
										+'<td>'+child.realMoney+'</td>'
									+'</tr>';
							$("#financeRecordList5").append(html);
						}
					}
				});
				
		};
		business.getFinanceRecordList5();	
	}else if(location.href.indexOf("/sell/sell_recordtixian.html")>=0){
	/**
	 * 提现记录
	 */	
		//获取提现记录列表
		business.getFinanceRecordList2=function(){
			var info = {
				accountId:business.account.accountId,
				type:2,//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户），7申请退保证金
				pageNum:1,
				pageSize:100
			};
			
			business.ajax("/financeRecord/list",info,function(data){
				if(data.code==200){
					var html="";
						business.financeRecordList2=data.data;
					for (var i = 0; i < business.financeRecordList2.length; i++) {
						var child=business.financeRecordList2[i];
							html='<tr class="recordtabletd">'
										+'<td>'+child.createDate+'</td>'
										+'<td style="color: #4cafe9;">'+child.transactionNumber+'</td>'
										+'<td>'+business.payType[child.method]+'</td>'
										+'<td>'+child.realname+'</td>'
										+'<td>'+child.money+'</td>'
										+'<td>'+parseFloat(child.money-child.realMoney).toFixed(2)+'</td>'
										+'<td>'+child.realMoney+'</td>'
									+'</tr>';
							$("#financeRecordList2").append(html);
						}
					}
				});
				
		};
		business.getFinanceRecordList2();
	}else if(location.href.indexOf("/sell/sell_recordtuikuan.html")>=0){
	/**
	 * 退款记录
	 */	
	//获取退款记录列表
	business.getFinanceRecordList6=function(){
		var info = {
			accountId:business.account.accountId,
			type:6,//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户），7申请退保证金
			pageNum:1,
			pageSize:100
		};
		
		business.ajax("/financeRecord/list",info,function(data){
			if(data.code==200){
				var html="";
					business.financeRecordList6=data.data;
				for (var i = 0; i < business.financeRecordList6.length; i++) {
					var child=business.financeRecordList6[i];
						html='<tr class="recordtabletd">'
									+'<td>'+child.createDate+'</td>'
									+'<td style="color: #4cafe9;">'+child.transactionNumber+'</td>'
									+'<td>'+business.payType[child.method]+'</td>'
									+'<td>'+child.realname+'</td>'
									+'<td>'+child.money+'</td>'
								+'</tr>';
						$("#financeRecordList6").append(html);
					}
				}
			});
			
	};
	business.getFinanceRecordList6();
	}else if(location.href.indexOf("/sell/sell_message.html")>=0){
	/**
	 * 我的消息
	 */
		//1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈，7订单商品动态
		business.noticeTypeList=[
			{},
			{id:1,value:"系统消息",icon:"../img/icon_xx.png"},
			{id:2,value:"申请新产品销售",icon:"../img/icon_sp.png"},
			{id:3,value:"新增商品类型",icon:"../img/icon_sp.png"},
			{id:4,value:"商品申请自营",icon:"../img/icon_sp.png"},
			{id:5,value:"提现申请",icon:"../img/tikuan.png"},
			{id:6,value:"问题单反馈",icon:"../img/icon_wtd.png"},
			{id:7,value:"订单商品动态",icon:"../img/icon_sp.png"},
			]
		//1审核中，2申请成功，3申请失败,个人为0，代表正常
		business.noticeStatusList=["正常","审核中","申请成功","申请失败"],
		//获取消息列表
		business.getNoticeList=function(){
			var info = {
				accountId:business.account.accountId,
				pageNum:1,
				pageSize:100
			};
			
			business.ajax("/notice/list",info,function(data){
				if(data.code==200){
							var list = data.data;
				        	var table = $('#messageparent');
				        	for(var i = 0; i < list.length; i++) {
				        		var child = list[i];
								var content="";
								if(child.type==1){
								//系统通知
									content='<div >'
												+'<p class="color_1b1b1b">'+child.content+'</p>'
											+'</div>';
								}else if(child.type==2){
									//申请新产品销售
									var c=JSON.parse(child.content);
									content='<div style="margin-top: 5px;">'
										+'<span class="messagesumbp">商 品 名：</span>'
										+'<span class="color_1b1b1b">'+c.merName+'</span>'
									+'</div>'
									+'<div style="margin-top: 5px;">'
										+'<span class="messagesumbp">商 品 种类：</span>'
										+'<span  class="color_1b1b1b">'+c.merCateName+'</span>'
									+'</div>'
									+'<div style="margin-top: 5px;">'
										+'<p class="messagesumbp" style="width: auto;">商品状态：'+business.noticeStatusList[child.status]+'</p>'
									+'</div>';
								}else if(child.type==3){
									//3新增商品类型
									var c=JSON.parse(child.content);
									content='<div style="margin-top: 5px;">'
												+'<span class="messagesumbp">商 品 类型名：</span>'
												+'<span class="color_1b1b1b">'+c.merCateName+'</span>'
											+'</div>'
											+'<div style="margin-top: 5px;">'
												+'<span class="messagesumbp">商 品 类型介绍：</span>'
												+'<span  class="color_1b1b1b">'+c.merCateSummary+'</span>'
											+'</div>'
											+'<div style="margin-top: 5px;">'
												+'<p class="messagesumbp" style="width: auto;">申请状态：'+business.noticeStatusList[child.status]+'</p>'
											+'</div>';
								}else if(child.type==6){
									//问题单反馈
									var c=JSON.parse(child.content);
									content='<div >'
										+'<p class="messagesumbp">订单编号：</p>'
										+'<p class="color_7400 ">'+c.orderNumber+'</p>'
									+'</div>'
									+'<div style="margin-top: 5px;">'
										+'<p class="messagesumbp">商 品 名：</p>'
										+'<p  class="color_1b1b1b">'+c.merName+'</p>'
									+'</div>'
									+'<div style="margin-top: 5px;">'
										+'<p class="messagesumbp" style="width: auto;">商家回复：'+c.content+'</p>'
										+'<a class="golook"  href="myorderdetail.html?orderId='+c.orderId+'">去查看>></a>'
									+'</div>';
								}else if(child.type==7){
									//订单商品动态
									var c=JSON.parse(child.content);
									content='<div >'
										+'<p class="messagesumbp">订单编号：</p>'
										+'<p class="color_7400 ">'+c.orderNumber+'</p>'
									+'</div>'
									+'<div style="margin-top: 5px;">'
										+'<p class="messagesumbp">商 品 名：</p>'
										+'<p  class="color_1b1b1b">'+c.merName+'</p>'
									+'</div>'
									+'<div style="margin-top: 5px;">'
										+'<p class="messagesumbp" style="width: auto;">商品状态：'+c.content+'</p>'
										+'<a class="golook" href="myorderdetail.html?orderId='+c.orderId+'">去查看>></a>'
									+'</div>';
								}else{
									
									continue;
								}
								var childImgAddress=child.imgAddress||business.noticeTypeList[child.type].icon;
								//架子
				        		var html='<div class="messagediv">'
											+'<div style="padding-top: 10px;margin-left: 20px;padding-right: 20px;">'
												+'<img style="width: 16px;height: 16px;float: left;margin-top: 2px;" src="'+business.noticeTypeList[child.type].icon+'"  />'
												+'<p class="messagep1" style="margin-left: 18px;">'+business.noticeTypeList[child.type].value+'</p>'
												+'<p class="messagep1" style="float: right;">'+child.updateDate+'</p>'
												+'<div style="clear:both"></div>'
											+'</div>'
											+'<div class="messagediv2">'
												+'<img style="width: 83px;height: 67px;float: left;" src="'+childImgAddress+'"/>'
												+'<div class="messagediv3">'
												+content
												+'</div>'
											+'</div>'
										+'</div>'
										+'<div style="height:10px;widht:100%;background-color:#ececec"></div>';
								table.append(html); 
				        	}
				}
			});
		}
		business.getNoticeList();
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
			      	business.ajax("/bankCard/delete",info,function(data){
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
	}
	
	
	
	$(".detail_positionul li").click(function(){
		$('.detail_positionul li').removeClass('clickli');
		$(this).addClass('clickli');
		var value1 = $(this).text();
		var value = value1.replace(/\s+/g, "");
		
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
	
	
	
})