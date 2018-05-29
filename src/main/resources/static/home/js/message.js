$(function(){
	
	var messagetype = new Array("","系统消息","商品动态","问题单反馈");
	var messagetypeimg = new Array("","img/icon_sp.png","img/icon_wtd.png","img/icon_xx.png");
	var user = JSON.parse(localStorage.getItem("user"));
	




getmessagelist()







function getmessagelist(){
	var info = {
		accountId:user.accountId,
		pageNum:1,
		pageSize:100
	};
	
	ajxget("/notice/list",info,function(data){
		if(data.code==200){
					var list = data.data;
		        	var table = $('#messageparent');
		        	for(var i = 0; i < list.length; i++) {
		        		var child = list[i];
		        		var div = document.createElement('div');
		        		div.className = 'messagediv';
						var html = '<div class="messagediv1"><img class="messagetitleimg" src="'+messagetypeimg[child.type]+'"  /><p class="messagep1" style="margin-left: 18px;">'+messagetype[child.type]+'</p><p class="messagep1" style="margin-left: 66px;">'+child.updateDate+'</p><div style="clear:both"></div></div><div class="messagediv2"><img class="messageimg" src="'+child.imgAddress+'"/><div class="messagediv3"><div ><p class="messagesumbp">订单编号：</p><p class="color_7400 ">'+child.businessId+'</p></div><div style="margin-top: 5px;"><p class="messagesumbp">商 品 名：</p><p  class="color_1b1b1b">'+child.content+'</p></div><div style="margin-top: 5px;"><p class="messagesumbp" style="width: auto;">商品状态：'+child.content+'  </p><p class="golook">去查看>></p></div></div></div>';
						div.innerHTML = html;
						table.append(div); 
		        	}
		}
	});
}



})