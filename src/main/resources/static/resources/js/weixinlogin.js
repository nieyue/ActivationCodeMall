//判断是否在微信浏览其中
if(myUtils.isWeiXinBrowse()){
//微信授权登录
  if(!sessionStorage.getItem("weixinlogin")){
	  sessionStorage.setItem("weixinlogin",1);
	  //location.replace("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6482f21128769e54&redirect_uri=http://"+location.host+"/weixin/authorization/baselogin&response_type=code&scope=snsapi_base&state="+location.href+"#wechat_redirect");
	  location.replace("/weixin/authorize");
	  }else{
	  	//location.replace("/user/login");
	  }
				
						//请求jsapi
						$.ajax({
							url:"/weixin/js/connection?url="+encodeURIComponent(location.href.split('#')[0]),
							type:"GET",
							success:function(dd){
							console.log( $.parseJSON(dd));
							var appid=$.parseJSON(dd).appid;
							var timeStamp=$.parseJSON(dd).timestamp;
							var nonceStr=$.parseJSON(dd).noncestr;
							var signature=$.parseJSON(dd).signature;
							//time_stamp=timeStamp;
							//配置jsapi
							//alert(nonceStr)
							wx.config({
						    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						    appId: appid, // 必填，公众号的唯一标识
						    timestamp: timeStamp, // 必填，生成签名的时间戳
						    nonceStr: nonceStr, // 必填，生成签名的随机串
						    signature: signature,// 必填，签名，见附录1
						    jsApiList: [
						                'checkJsApi',
						                'onMenuShareTimeline',
						                'onMenuShareAppMessage',
						                'onMenuShareQQ',
						                'onMenuShareWeibo',
						                'onMenuShareQZone',
						                'hideMenuItems',
						                'showMenuItems',
						                'hideAllNonBaseMenuItem',
						                'showAllNonBaseMenuItem',
						                'translateVoice',
						                'startRecord',
						                'stopRecord',
						                'onVoiceRecordEnd',
						                'playVoice',
						                'onVoicePlayEnd',
						                'pauseVoice',
						                'stopVoice',
						                'uploadVoice',
						                'downloadVoice',
						                'chooseImage',
						                'previewImage',
						                'uploadImage',
						                'downloadImage',
						                'getNetworkType',
						                'openLocation',
						                'getLocation',
						                'hideOptionMenu',
						                'showOptionMenu',
						                'closeWindow',
						                'scanQRCode',
						                'chooseWXPay',
						                'openProductSpecificView',
						                'addCard',
						                'chooseCard',
						                'openCard'
						              ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						});  
						}});
						
						 wx.ready(function(){
							    wx.onMenuShareAppMessage({
							      title: '黑马黑茶',
							      desc: '了解黑茶，就看这里！',
							     link: location.href.split('#')[0],
							      imgUrl: 'http://'+location.host+'/resources/img/images/img_main_5.jpg',
							      trigger: function (res) {
							        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
							       // alert('用户点击发送给朋友');
							      },
							      success: function (res) {
							        myUtils.myLoadingToast("分享成功", null);
							      },
							      cancel: function (res) {
							        myUtils.myLoadingToast("取消分享", null);
							      },
							      fail: function (res) {
							        myUtils.myLoadingToast("分享失败", null);
							      }
							    });	
							    $("#unified").on("click",function(){
							    	
							    	$.get("http://"+location.host+"/weixin/test/33",
							    			function(data){
							    		data=JSON.parse(data);
							    		//console.log(data)
							    		if(data.agent<5){
							    			myUtils.myLoadingToast("请使用5.0以上的微信版本支付", null);
							    			return;
							    		}
							    $(document).off("click","#payway");
								 $(document).on("click","#payway",function(){
								myUtils.wxPay(data.appid, data.time_stamp, 
											data.nonce_str, data.package_value, data.sign_type, data.pay_sign,
											function(){
										console.log("200");
									});
							});
			}); 
	 						});
		});

}