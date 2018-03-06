     angular.module('mainApp', ['ngAnimate','ui.router'])
       .config(function ($stateProvider, $urlRouterProvider,$httpProvider) {
    	   $httpProvider.defaults.transformRequest=function(obj){
    			var str=[];
    			for ( var p in obj) {
    				str.push(encodeURIComponent(p)+"="+encodeURIComponent(obj[p]))
    			}
    			return str.join("&");
    		}
    		$httpProvider.defaults.headers.post={
    			'Content-Type':'application/x-www-form-urlencoded'
    		};
    		
     	$urlRouterProvider.when("", "main");
     	$stateProvider
     	.state("main", {
            url: "/main",
            views: {
                '': {
                    templateUrl: '/admin/templates/main.html'
                },
                'topbar@main': {
                    templateUrl: '/admin/templates/topbar.html'
                },
                'leftbar@main': {
                    templateUrl: '/admin/templates/leftbar.html',
                    controller:function(){
                    	myUtils.myClickRotate("a.toCaret","span.caret");//箭头旋转
                    }
                } ,
                'rightbody@main': {
                    templateUrl: '/admin/templates/notice.html'
                } 
            }
        })
         .state("main.notice", {
            url:"/notice",
            views: {
            	'rightbody@main': {
                    templateUrl: "/admin/templates/notice.html"
                } 
            }
        })
        .state("main.channelinfo", {
            url:"/channelinfo",
            views: {
            	'rightbody@main': {
                    templateUrl: "/admin/templates/channel_info.html",
                    controller:function($scope,$state){
                    	$state.go("main.channelinfo.channellist");   
                    	
                    }
                }
            }
        })
        .state("main.channelinfo.channellist", {
        	url:"/channellist",
        			templateUrl: "/admin/templates/channel_list.html",
        			controller:function($scope){
        				//数据列表
                    	myUtils.myPrevToast("加载中...", function(){
                    	$.get("/channel/list",function(data){
                    	$scope.channelList=data;
                    	$scope.$apply();
                    	console.log(data)
                    	myUtils.myPrevToast("加载完成",null,"remove");
                    	});
                    	},"add");
                    	//删除渠道
                    	$scope.channelDel=function(){
                    		var channelId=this.channel.channel_id;
                    		myUtils.myConfirm("确定删除吗？",function(){
                    			$.post("/channel/"+channelId+"/delete",function(data){
                    				if(data.code==200){
                    					for (var int = 0; int < $scope.channelList.length; int++) {
                    						if(channelId==$scope.channelList[int].channel_id){
                    							$scope.channelList.splice(int,1);
                    						}
											
										}
                    					$scope.$apply();
                    					noChannelList();
                    					myUtils.myLoadingToast("删除成功", null);
                    					return ;
                    				}
                    				myUtils.myLoadingToast("删除失败", null);
                    			});
                    			
                    		});
                    	};
                    	//显示模态框
                    	$scope.channelUpdate=function(){
                    		var channelId=this.channel.channel_id;
                    		$("#mySellerModalLabel").text("修改渠道名称");
                    		$("#mySellerModalBody").html("<input type='text' class='form-control' id='channelNameUpdate' ng-model='channelNameUpdate'/>");
                    		$("#mySellerModal").click();
                    		console.log($scope.channelNameUpdate)
                    	//修改渠道
                    	$("#sellerSubmit").unbind().click(function(){
                    		if($scope.channelNameUpdate==""){
                    			myUtils.myLoadingToast("不能为空", null);
                    			return; 
                    		}
                    		$.post("/channel/update",{
                    			channel_id:channelId,
                    			name:$("#channelNameUpdate").val().trim()},function(data){
                				if(data.code==200){
                					for (var int = 0; int < $scope.channelList.length; int++) {
                						if(channelId==$scope.channelList[int].channel_id){
                							$scope.channelList[int].name=$("#channelNameUpdate").val().trim();
                						}
									}
                					$scope.$apply();
                					myUtils.myLoadingToast("修改成功", null);
                					$("#closeModal").click();
                					return ;
                				}
                				myUtils.myLoadingToast("修改失败", null);
                			});
                    	});
                    	};
                    	
                    	function noChannelList(){
                    		if($scope.channelList==''||$scope.channelList==null||$scope.channelList==undefined){
                    			$("#noChannelList").text("还有没渠道，赶紧添加吧！");
                    		}else{
                    			$("#noChannelList").text("");
                    		}
                    	}
                    	//监听数据列表
                    	$scope.$watch("channelList",function(){
                    		noChannelList();
                    	});
        	}
        })
        .state("main.channelinfo.channeladd", {
        	url:"/channeladd",
			templateUrl: "/admin/templates/channel_add.html",
			controller:function($scope,$http){
				//添加渠道
				$scope.channelSubmit=function(){
					if($scope.channelName==null){
						return myUtils.myLoadingToast("提交错误", null);
					}
					myUtils.myPrevToast("提交中...", function(){
						$.get("/channel/add?name="+$scope.channelName+"&url=http://"+location.hostname+"/pos/index",function(data){
	                    	if(data.code==200){
	                    		myUtils.myPrevToast("增加成功",null,"remove");
	                    		$scope.channelName="";
	                    		$scope.$apply();
	                    		return;
	                    	}
	                    	myUtils.myLoadingToast("增加失败", null);
	                    	});
					}, "add");
				};
			}
        })
        .state("main.userinfo", {
            url:"/userinfo",
            views: {
            	'rightbody@main': {
                    templateUrl: "/admin/templates/user_info.html",
                    controller:function($scope){
                    	//数据列表
                    	$scope.listUserByChannel=function(){
                    		if($scope.userChannelId==null||$scope.userChannelId==""||$scope.userChannelId==undefined){
        						return myUtils.myLoadingToast("请指定渠道id", null);
        					}
                    		if(!(myUtils.userVerification.catNum.test($scope.userChannelId))||(($scope.userChannelId)<1000)){
                    			return myUtils.myLoadingToast("渠道id大于等于1000", null);
                    		}
        					myUtils.myPrevToast("查询中...", function(){
        						$.get("/user/"+$scope.userChannelId+"/list",function(data){
        							$scope.userList=[];
        							$scope.$apply();
        							console.log(data)
        							if(data.length==0){
        								return myUtils.myPrevToast("没有客户",null,"remove");
        							}
        							if(data!=null&&data!=""&&data!=undefined){
        								$scope.userList=data;
        								$scope.$apply();
        								console.log(data)
        								myUtils.myPrevToast("加载完成",null,"remove");
        								return;
        							}
        							myUtils.myPrevToast("加载失败",null,"remove");
        							
        	                    	});
        					}, "add");
                    	};
                    	//PDF下载
                    	$scope.downloadPDF=function(){
                    		if($scope.userList&&$scope.userList.length>0){
                    			window.open("http://"+location.hostname+"/user/downloadPDF/"+$scope.userList[0].channel_id,"_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=800, height=800");
                    		}
                    	};
                    	//xls下载
                    	$scope.downloadXLS=function(){
                    		if($scope.userList&&$scope.userList.length>0){
                    			location.replace("http://"+location.hostname+"/user/downloadXLS/"+$scope.userList[0].channel_id);
                    		}
                    	};
                    	//没有客户
                    	function noUserList(){
                    		if($scope.userList==''||$scope.userList==null||$scope.userList==undefined){
                    			$("#noUserList").text("还有没客户！");
                    		}else{
                    			$("#noUserList").text("");
                    		}
                    	}
                    	//监听数据列表
                    	$scope.$watch("userList",function(){
                    		noUserList();
                    	});
                    }
                } 
            }
        });
		})
       .controller('mainCtrl', function($scope,$http) {
    	   myUtils.idNotExistence("admin_id", "/404");
    	   if(myUtils.GetQueryString("admin_id")!=null){
    	   $.get("/Admin/isLogin?admin_id="+myUtils.GetQueryString("admin_id"),
    			   function(data){
    		   if(!data.code || data.code!=200){
    			   location.replace("/admin");
    		   }
    	   });
    	   }else{
    		   location.replace("/admin");
    	   }
    	   //退出
   			$(document).on("click","#sellerLoginOut",function(){
   				myUtils.myConfirm("登录退出吗？",function(){
        			$.post("/Admin/loginOut?admin_id="+myUtils.GetQueryString("admin_id"),function(data){
        				if(data.code==200){
        					myUtils.myLoadingToast("退出成功", null);
        					location.replace("/admin")
        					return ;
        				}
        				myUtils.myLoadingToast("退出失败", null);
        			});
   			});
   		});		
       });		