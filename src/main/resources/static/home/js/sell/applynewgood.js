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