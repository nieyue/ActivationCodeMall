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
					sessionStorage.removeItem("sellerMer");
					setTimeout(function(){
						location.href="../sell/sell_index.html";
					},1000);
				}else{
					business.myLoadingToast(data.msg)
				}
				});
		
	});