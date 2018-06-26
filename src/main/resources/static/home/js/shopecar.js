$(document).ready(function(){
	//初始化,保障
	sessionStorage.setItem("selectCartMerList",JSON.stringify([]));
	sessionStorage.setItem("selectCartMerTotalPrice",JSON.stringify(0));
	//购物车计总
	business.cartMerSum=function(){
		//购物车清零
		$("#allMerNumber").html(0)
		$("#allMerTotalPrice").html(0)
		//计算商品数量
		$("#allMerNumber").html($("input[name='checkbox']:checked").size())
		//计算商品总价
		var totalPrice=0;
		//选中的购物车商品
		business.selectCartMerList=[];
		$("input[name='checkbox']:checked").each(function(){
			//console.log(parseFloat($(this).parent().parent().parent().children().get(3).innerText.replace("¥","")))
			//console.log($(this).parent().parent().parent().attr("id"))
			for (var i = 0; i < business.cartMerListAll.length; i++) {
				if(business.cartMerListAll[i].cartMerId==$(this).parent().parent().parent().attr("id")){
					business.selectCartMerList.push(business.cartMerListAll[i])
					totalPrice+=parseFloat(business.cartMerListAll[i].totalPrice);
				}
			}
		});
		$("#allMerTotalPrice").html(totalPrice.toFixed(2));
		//总选择的列表
		sessionStorage.setItem("selectCartMerList",JSON.stringify(business.selectCartMerList));
		//总金额
		sessionStorage.setItem("selectCartMerTotalPrice",JSON.stringify(totalPrice.toFixed(2)));
	}
	//点击全选
	business.checkboxAll("input[name='checkboxAll']","input[name='checkbox']",
			function(){
		business.cartMerSum();
		
	});
	//点击提交订单,批量
	$(".commitbtn").click(function(){
		if(JSON.parse(sessionStorage.getItem("selectCartMerList")).length<=0){
			business.myLoadingToast("最少选中一个");
			return;
		}
		window.location.href = "mycartmerturnorder.html";
	});

//点击所有商品
$("#allgood").click(function(){
	$("#allgood").css("background-color","#573c1e");
	$("#jiangjiagood").css("background-color","");
	$("#wantbuygood").css("background-color","");
	getcarlist();
	$("input[name='checkboxAll']").prop("checked",false);
	business.cartMerSum();
});
//点击降价商品
$("#jiangjiagood").click(function(){
	$("#allgood").css("background-color","");
	$("#jiangjiagood").css("background-color","#573c1e");
	$("#wantbuygood").css("background-color","");
	getjiangjialist();
	$("input[name='checkboxAll']").prop("checked",false);
		business.cartMerSum();
		
});
//点击预购商品
$("#wantbuygood").click(function(){
	$("#allgood").css("background-color","");
	$("#jiangjiagood").css("background-color","");
	$("#wantbuygood").css("background-color","#573c1e");
	getyugoulist();
	$("input[name='checkboxAll']").prop("checked",false);
		business.cartMerSum();
		
});

//获取所有购物车数据
business.getCartMerList=function(){
	business.cartMerCountAll=0;
	business.cartMerCount2=0;
	business.cartMerCount3=0;
	if(business.account==null){
		return;
	}
	var info = {
			accountId:business.account!=null?business.account.accountId:null,
		}
		ajxget("/cartMer/userlist",info,function(data){
						if(data.code==200){
						//总数
						business.cartMerCountAll=data.data[0].cartMerCountAll||0;	
						//总列表
						business.cartMerListAll=data.data[0].cartMerListAll;	
						//降价总数
						business.cartMerCount2=data.data[0].cartMerCount2||0;	
						//降价列表
						business.cartMerList2=data.data[0].cartMerList2;	
						//预购总数
						business.cartMerCount3=data.data[0].cartMerCount3||0;	
						//预购列表
						business.cartMerList3=data.data[0].cartMerList3;	
						console.log(business.cartMerListAll)
						//降价商品
						getjiangjialist();
						//预购商品
						getyugoulist();
						//所有商品
						getcarlist();
					}
			}
		)
}
business.getCartMerList();


//全部
function getcarlist(){
		var list = business.cartMerListAll;
		$("#allcarnum").text("所有商品("+business.cartMerCountAll+")")
			var table = $('#shopecarlist');
			table.html("");
        	for(var i = 0; i < list.length; i++) {
        		var child = list[i];
        		var tr = document.createElement('tr');
        		tr.className = "td shopeitem";
				tr.id = child.cartMerId;
				var html = '<td  style="position:relative;"><div style="position: absolute;top: 50%;transform:translateY(-50%);"><input name="checkbox" type="checkbox" style="margin-left: 10px;float: left;margin-top: 25px;" /><img class="caritemimg" src="'+child.mer.imgAddress+'" /><p class="caritemname">'+child.mer.name+'</p><p class="caritemname" id="caryugouwrap'+i+'"></p></div></td><td >¥'+child.mer.unitPrice+'</td><td ><div class="gw_num"><em class="jian" onclick='+"'"+'jian("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>-</em><input type="text" disabled="disabled" value="'+child.number+'" class="num" id="goodnum'+i+'"/><em class="add" onclick='+"'"+'jia("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>+</em></div></td><td id="goodsummoney'+i+'">¥'+child.totalPrice+'</td><td><a class="commitorder">提交订单</a><a class="deletshopcar" onclick="deleteCartMer('+child.cartMerId+')">删除</a></td>';
				tr.innerHTML = html;
				table.append(tr); 
				//必须是类型3且有预购时间
				if(child.mer.type==3&&child.mer.deliverEndDate){
					$("#caryugouwrap"+i).html("预购商品      "+child.mer.deliverEndDate+"前发货");					
				}
		}
    	
}
//降价
function getjiangjialist(){
			var list = business.cartMerList2;
			$("#jiangjianum").text("降价商品("+business.cartMerCount2+")");
				var table = $('#shopecarlist');
				table.html("");
	        	for(var i = 0; i < list.length; i++) {
	        		var child = list[i];
	        		var tr = document.createElement('tr');
	        		tr.className = "td item";
					tr.id = child.cartMerId;
					var img = child.mer.imgAddress;
					if(img==null||img==""){
						img = "img/index_goods1.jpg";
					}
					var html = '<td style="position:relative;"><div style="position: absolute;top: 50%;transform:translateY(-50%);"><input name="checkbox" type="checkbox" style="margin-left: 10px;float: left;margin-top: 25px;" /><img class="caritemimg" src="'+img+'" /><p class="caritemname">'+child.mer.name+'</p></div></td><td >¥'+child.mer.unitPrice+'</td><td ><div class="gw_num"><em class="jian" onclick='+"'"+'jian("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>-</em><input type="text" disabled="disabled" value="'+child.number+'" class="num" id="goodnum'+i+'"/><em class="add" onclick='+"'"+'jia("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>+</em></div></td><td id="goodsummoney'+i+'">¥'+child.totalPrice+'</td><td><a class="commitorder">提交订单</a><a class="deletshopcar" onclick="deleteCartMer('+child.cartMerId+')">删除</a></td>';
					tr.innerHTML = html;
					table.append(tr); 
	        	}
        	
}
//预购
function getyugoulist(){
		var list = business.cartMerList3;
		$("#yugounum").text("预购商品("+business.cartMerCount3+")");
			var table = $('#shopecarlist');
			table.html("");
        	for(var i = 0; i < list.length; i++) {
        		var child = list[i];
        		var tr = document.createElement('tr');
        		tr.className = "td shopeitem";
				tr.id = child.cartMerId;
				var html =
				'<td style="position:relative;">'
					+'<div style="position: absolute;top: 50%;transform:translateY(-50%);">'
						+'<input name="checkbox" type="checkbox" style="margin-left: 10px;float: left;margin-top: 25px;" />'
							+'<img class="caritemimg" src="'+child.mer.imgAddress+'" />'
							+'<p class="caritemname">'+child.mer.name+'</p>'
							+'<p class="caritemname" id="yugouwrap'+i+'"></p>'
					+'</div>'
				+'</td>'
				+'<td >¥'+child.mer.unitPrice+'</td>'
				+'<td >'
					+'<div class="gw_num">'
						+'<em class="jian" onclick='+"'"+'jian("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>-</em><input type="text" disabled="disabled" value="'+child.number+'" class="num" id="goodnum'+i+'"/><em class="add" onclick='+"'"+'jia("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>+</em>'
					+'</div>'
				+'</td>'
				+'<td id="goodsummoney'+i+'">¥'+child.totalPrice+'</td>'
				+'<td>'
					+'<a class="commitorder">提交订单</a>'
					+'<a class="deletshopcar" onclick="deleteCartMer('+child.cartMerId+')">删除</a>'
				+'</td>';
				//预购商品的
				tr.innerHTML = html;
				
				table.append(tr); 
				$("#yugouwrap"+i).html("预购商品      "+child.mer.deliverEndDate+"前发货");
        	}
}



//购物商品提交订单
$(document).on("click",".commitorder", function() {
	//console.log($(this).parent().parent().children().children().children("input[name='checkbox']"))
	$(this).parent().parent().children().children().children("input[name='checkbox']").prop("checked","checked")
	business.cartMerSum();
	window.location.href = "mycartmerturnorder.html";
});
});
//删除购物商品
function deleteCartMer(cartMerId){
	var info = {
		cartMerId:cartMerId,
		accountId:business.account!=null?business.account.accountId:null
	};
	business.myConfirm("确定删除？",function(){
	ajxget("/cartMer/delete",info,function(data){
		if(data.code==200){
			business.myLoadingToast("删除成功");
			location.reload();
		}
	});
	});
}
//批量删除
$("#cartMerDeleteBatch").on("click",function(){
	//console.log($("input[name='checkbox']:checked").parent().parent().parent())
	/*
	 * var trs=$("input[name='checkbox']:checked").parent().parent().parent();
	if(trs.length<=0){
		business.myLoadingToast("最少选择一个");
		return;
	}*/
	if(JSON.parse(sessionStorage.getItem("selectCartMerList")).length<=0){
		business.myLoadingToast("最少选中一个");
		return;
	}
	var trs=JSON.parse(sessionStorage.getItem("selectCartMerList"));
	var cartMerIdsarray=[];
	for (var i = 0; i < trs.length; i++) {
		cartMerIdsarray.push(trs[i].cartMerId);
	}
	business.myConfirm("批量删除，确定？",function(){
		//var cartMerIds=$("input[name='checkbox']:checked")
		var info = {
				cartMerIds:cartMerIdsarray.toString(),
				accountId:business.account!=null?business.account.accountId:null
			};
			
			ajxget("/cartMer/deleteBatch",info,function(data){
				if(data.code==200){
					business.myLoadingToast("删除成功");
					location.reload();
				}
			});
	});
});

function jian(valueid,sumid,price){
	console.log(price);
	var n=$(sumid).val();
	var num=parseInt(n)-1;
	if(num==0){ return}
	$(sumid).val(num);
	var money = accMul(num,price);
	$(valueid).text("¥"+money);
	//计算business.cartMerListAll
	for (var i = 0; i < business.cartMerListAll.length; i++) {
		if(business.cartMerListAll[i].cartMerId==$(valueid).parent().attr("id")){
			business.cartMerListAll[i].number=num;
			business.cartMerListAll[i].totalPrice=money;
		}
	}
	
	business.cartMerSum();
}

function jia(valueid,sumid,price){
	console.log(price);
	var n=$(sumid).val();
	var num=parseInt(n)+1;
	if(num==0){ return}
	if(num>5){business.myLoadingToast("单品最大5个"); return;}
	$(sumid).val(num);
	var money = accMul(num,price);
	$(valueid).text("¥"+money);
	//计算business.cartMerListAll
		for (var i = 0; i < business.cartMerListAll.length; i++) {
			if(business.cartMerListAll[i].cartMerId==$(valueid).parent().attr("id")){
				business.cartMerListAll[i].number=num;
				business.cartMerListAll[i].totalPrice=money;
			}
		}
	business.cartMerSum();
}

function accMul(arg1,arg2)
{
var m=0,s1=arg1.toString(),s2=arg2.toString();
try{m+=s1.split(".")[1].length}catch(e){}
try{m+=s2.split(".")[1].length}catch(e){}
return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

//获取推荐列表		
function gettuijian(){
	var info = {
		pageNum:1,
		pageSize:5,
		recommend:2//推荐，默认0不推，1封推，2推荐
	};
	ajxget("/mer/list",info,function(data){
		if(data.code==200){
			var list = data.data;
		        	var table = $('.cargood');
		        	for(var i = 0; i < list.length; i++) {
		        		var good = list[i];
		        		var li = document.createElement('div');
		        		li.className = 'cargooddiv';
						li.id = good.merId;
						var img = good.imgAddress;
							if(img==null||img==""){
								img = "img/index_goods1.jpg";
							}
						var html = '<div style="text-align: center;"><img src="'+img+'" style="width: 218px;height: 160px;" /></div><div style="width: 220px;"><p  class="color_333 tuijianname">'+good.name+'</p></div><div class="pdiv"><p class="goodoldprice">市场价¥'+good.oldUnitPrice+'</p><p class="goodnewprice">¥'+good.unitPrice+'</p></div><div class="pdiv"><p class="buynum">'+good.saleNumber+'人付款</p><p class="commentnum">'+good.merCommentNumber+'人评论</p></div>';
						li.innerHTML = html;
						table.append(li); 
		        	}
		        		$(".cargood .cargooddiv").each(function(){
		        			$(this).click(function(){
		        			location.href="gooddetail.html?goodid="+$(this).attr("id");
		        			});
		        		});
		}
	})
}