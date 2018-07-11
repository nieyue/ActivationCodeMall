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
			pageSize:20
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
											+'<span class="messagesumbp">商 品 名：</span>'
											+'<span class="color_1b1b1b">'+c.merCateName+'</span>'
										+'</div>'
										+'<div style="margin-top: 5px;">'
											+'<span class="messagesumbp">商 品 类型介绍：</span>'
											+'<span  class="color_1b1b1b">'+c.merCateSummary+'</span>'
										+'</div>'
										+'<div style="margin-top: 5px;">'
											+'<p class="messagesumbp" style="width: auto;">申请状态：'+business.noticeStatusList[child.status]+'</p>'
										+'</div>';
							}else if(child.type==4){
								//4商品申请自营
								var c=JSON.parse(child.content);
								content='<div style="margin-top: 5px;">'
											+'<span class="messagesumbp">商 品 类型名：</span>'
											+'<span class="color_1b1b1b">'+c.merName+'</span>'
										+'</div>'
										+'<div style="margin-top: 5px;">'
											+'<span class="messagesumbp">商 品 类型名：</span>'
											+'<span  class="color_1b1b1b">'+c.merCateName+'</span>'
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