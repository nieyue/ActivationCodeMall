/**
 * 商品动态
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
	//获取商品动态
	business.getNoticeList2=function(){
		var info = {
			accountId:business.account.accountId,
			isMerDynamic:1,
			pageNum:1,
			pageSize:100
		};
		
		business.ajax("/notice/list",info,function(data){
			if(data.code==200){
						var list = data.data;
			        	var table = $('#noticeList2');
			        	for(var i = 0; i < list.length; i++) {
			        		var child = list[i];
							var html="";
							var deleteNoticeString="";
							if(child.status==1){
								var cc =JSON.stringify(child).replace(/"/g,"'");
							 deleteNoticeString='<a style="border: solid;width: 150px;height: 40px;border-width: 1px;float: right;border-color: #FF7400;color: #FF7400;text-align: center;" onclick="business.deleteNotice('+cc+')">撤销申请</a>';
							}
							if(child.type==2){
								//申请新产品销售
								var c=JSON.parse(child.content);
								html='<div style="height: 220px;background: #FFFFFF;margin-top: 10px;padding-left: 20px;padding-right: 20px;">'
										+'<div class="clearfix" style="width: 910px;padding-top: 20px;">'
										+'<p style="font-size: 16px;float: left;">申请新产品销售</p>'
										+'<p style="font-size: 14px;float: right;" class="color_666">申请时间：'+child.createDate+'</p>'
									+'</div>'
									+'<p class="font_size14 color_666 ">商品名称：'+c.merName+'</p>'
									+'<p class="font_size14 color_666 ">商品价格：￥'+c.merPrice+'</p>'
									+'<p class="font_size14 color_666 ">商品种类：'+c.merCateName+'</p>'
									+'<div style="padding-bottom: 20px;">'
										+'<p class="font_size14 color_666 " style="float: left;">商品介绍：</p>'
										+'<a class="font_size14" style="float: left;color: #4cafe9;" href="/home/gooddetail.html?goodid='+child.businessId+'">查看详情</a>'
									+'</div>'
									+'<hr size="1" color="#EBEBEB" width="100%" />'
									+'<div style="width: 910px;height: 40px;line-height: 40px;">'
										+'<p class="color_7400 font_size16" style="float: left;">申请状态:'+business.noticeStatusList[child.status]+'</p>'
										+deleteNoticeString
									+'</div>'
								+'</div>';
							}else if(child.type==3){
								//3新增商品类型
								var c=JSON.parse(child.content);
								html='<div style="height: 220px;background: #FFFFFF;margin-top: 10px;padding-left: 20px;padding-right: 20px;">'
										+'<div class="clearfix" style="width: 910px;padding-top: 20px;">'
										+'<p style="font-size: 16px;float: left;">新增商品类型</p>'
										+'<p style="font-size: 14px;float: right;" class="color_666">申请时间：'+child.createDate+'</p>'
									+'</div>'
									+'<p class="font_size14 color_666 ">商品种类：'+c.merCateName+'</p>'
									+'<div style="padding-bottom: 20px;">'
										+'<p class="font_size14 color_666 " style="float: left;">商品种类介绍：</p>'
										+'<p class="font_size14 color_666 line2" >'+c.merCateSummary+'</p>'
									+'</div>'
									+'<hr size="1" color="#EBEBEB" width="100%" />'
									+'<div style="width: 910px;height: 40px;line-height: 40px;">'
										+'<p class="color_7400 font_size16 " style="float: left;">申请状态:'+business.noticeStatusList[child.status]+'</p>'
										+deleteNoticeString
									+'</div>'
								+'</div>';
							}else if(child.type==4){
								//商品申请自营
								var c=JSON.parse(child.content);
								html='<div style="height: 220px;background: #FFFFFF;margin-top: 10px;padding-left: 20px;padding-right: 20px;">'
										+'<div class="clearfix" style="width: 910px;padding-top: 20px;">'
										+'<p style="font-size: 16px;float: left;">商品申请自营</p>'
										+'<p style="font-size: 14px;float: right;" class="color_666">申请时间：'+child.createDate+'</p>'
									+'</div>'
									+'<p class="font_size14 color_666 ">商品名称：'+c.merName+'</p>'
									+'<p class="font_size14 color_666 ">商品价格：￥'+c.merPrice+'</p>'
									+'<p class="font_size14 color_666 ">商品种类：'+c.merCateName+'</p>'
									+'<div style="padding-bottom: 20px;">'
										+'<p class="font_size14 color_666 " style="float: left;">平台技术服务费：</p>'
										+'<p class="font_size14" style="float: left;color: #4cafe9;">每售出一笔需要给品台'+c.merPlatformProportion+'%的分成比例</p>'
									+'</div>'
									+'<hr size="1" color="#EBEBEB" width="100%" />'
									+'<div style="width: 910px;height: 40px;line-height: 40px;">'
										+'<p class="color_7400 font_size16" style="float: left;">申请状态:'+business.noticeStatusList[child.status]+'</p>'
										+deleteNoticeString
									+'</div>'
								+'</div>';
							}else if(child.type==7){
								//订单商品动态
								var c=JSON.parse(child.content);
								html='<div class="messagediv">'
									+'<div style="padding-top: 10px;margin-left: 20px;padding-right: 20px;">'
										+'<img style="width: 16px;height: 16px;float: left;margin-top: 2px;" src="'+business.noticeTypeList[child.type].icon+'"  />'
										+'<p class="messagep1" style="margin-left: 18px;">'+business.noticeTypeList[child.type].value+'</p>'
										+'<p class="messagep1" style="float: right;">'+child.updateDate+'</p>'
										+'<div style="clear:both"></div>'
									+'</div>'
									+'<div class="messagediv2">'
										+'<img style="width: 83px;height: 67px;float: left;" src="'+childImgAddress+'"/>'
										+'<div class="messagediv3">'
											'<div >'
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
										+'</div>'
									+'</div>'
								+'</div>'
								+'<div style="height:10px;widht:100%;background-color:#ececec"></div>';
							}else{
								continue;
							}

							table.append(html); 
			        	}
			        	//删除消息
			        	business.deleteNotice=function(cc){
			        		console.log(cc)
			        			var info = {
			        				accountId:business.account.accountId,//商户id
									noticeId:cc.noticeId//消息id
								};
			        		business.myConfirm("确定删除吗？",function(){
								business.ajax("/notice/delete",info,function(data){
									if(data.code==200){
										business.myLoadingToast(data.msg);
										location.reload();
									}else{
										business.myLoadingToast(data.msg);
									}
									});
			        		});
			        	};
			}
		});
	}
	business.getNoticeList2();	