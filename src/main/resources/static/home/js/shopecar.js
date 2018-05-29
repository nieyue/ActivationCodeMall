$(document).ready(function(){
	
	
	
	
	
	
	$(".commitbtn").click(function(){
		window.location.href = "myordercommit.html";
	});


$("#allgood").click(function(){
	$("#allgood").css("background-color","#573c1e");
	$("#jiangjiagood").css("background-color","");
	$("#wantbuygood").css("background-color","");
	getcarlist(0);
});

$("#jiangjiagood").click(function(){
	$("#allgood").css("background-color","");
	$("#jiangjiagood").css("background-color","#573c1e");
	$("#wantbuygood").css("background-color","");
	getjiangjialist(0);
});

$("#wantbuygood").click(function(){
	$("#allgood").css("background-color","");
	$("#jiangjiagood").css("background-color","");
	$("#wantbuygood").css("background-color","#573c1e");
	getyugoulist(0);
});


getcarlist(1);
getjiangjialist(1);
getyugoulist(1);
function getjiangjialist(typeid){
	
	
	var info = {
		accountId:id,
		pageNum:1,
		pageSize:5
	}
	ajxget("/cartMer/list",info,function(data){
					if(data.code==200){
					
					var list = data.data;
					$("#jiangjianum").text("降价商品("+list.length+")");
					if(typeid==0){
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
							var html = '<td style="position:relative;"><div style="position: absolute;top: 50%;transform:translateY(-50%);"><input name="checkbox" type="checkbox" style="margin-left: 10px;float: left;margin-top: 25px;" /><img class="caritemimg" src="'+img+'" /><p class="caritemname">'+child.mer.name+'</p></div></td><td >¥'+child.mer.unitPrice+'</td><td ><div class="gw_num"><em class="jian" onclick='+"'"+'jian("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>-</em><input type="text" value="'+child.number+'" class="num" id="goodnum'+i+'"/><em class="add" onclick='+"'"+'jia("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>+</em></div></td><td id="goodsummoney'+i+'">¥'+child.totalPrice+'</td><td><a class="commitorder">提交订单</a><a class="deletshopcar" onclick="delectcar('+child.cartMerId+')">删除</a></td>';
							tr.innerHTML = html;
	
							table.append(tr); 
			        	}
					}
		        	
				}
	});
	
}
	function getyugoulist(type){
	
	
	var info = {
		accountId:id,
		pageNum:1,
		pageSize:5
	}
	ajxget("/cartMer/list",info,function(data){
					if(data.code==200){
					
					var list = data.data;
					
					$("#yugounum").text("预购商品("+list.length+")");
					if(type==0){
						var table = $('#shopecarlist');
						table.html("");
			        	for(var i = 0; i < list.length; i++) {
			        		var child = list[i];
			        		var tr = document.createElement('tr');
			        		tr.className = "td shopeitem";
							tr.id = child.cartMerId;
							var html = '<td style="position:relative;"><div style="position: absolute;top: 50%;transform:translateY(-50%);"><input name="checkbox" type="checkbox" style="margin-left: 10px;float: left;margin-top: 25px;" /><img class="caritemimg" src="'+child.mer.imgAddress+'" /><p class="caritemname">'+child.mer.name+'</p></div></td><td >¥'+child.mer.unitPrice+'</td><td ><div class="gw_num"><em class="jian" onclick='+"'"+'jian("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>-</em><input type="text" value="'+child.number+'" class="num" id="goodnum'+i+'"/><em class="add" onclick='+"'"+'jia("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>+</em></div></td><td id="goodsummoney'+i+'">¥'+child.totalPrice+'</td><td><a class="commitorder">提交订单</a><a class="deletshopcar" onclick="delectcar('+child.cartMerId+')">删除</a></td>';
							tr.innerHTML = html;
	
							table.append(tr); 
			        	}
					}
					
		        	
				}
	});
}




function getcarlist(typeid){
	
	var info = {
		accountId:id,
		pageNum:1,
		pageSize:5
	}
	ajxget("/cartMer/list",info,function(data){
					if(data.code==200){
					
					var list = data.data;
					$("#allcarnum").text("所有商品("+list.length+")")
					
		        	
						var table = $('#shopecarlist');
						table.html("");
			        	for(var i = 0; i < list.length; i++) {
			        		var child = list[i];
			        		var tr = document.createElement('tr');
			        		tr.className = "td shopeitem";
							tr.id = child.cartMerId;
							var html = '<td style="position:relative;"><div style="position: absolute;top: 50%;transform:translateY(-50%);"><input name="checkbox" type="checkbox" style="margin-left: 10px;float: left;margin-top: 25px;" /><img class="caritemimg" src="'+child.mer.imgAddress+'" /><p class="caritemname">'+child.mer.name+'</p></div></td><td >¥'+child.mer.unitPrice+'</td><td ><div class="gw_num"><em class="jian" onclick='+"'"+'jian("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>-</em><input type="text" value="'+child.number+'" class="num" id="goodnum'+i+'"/><em class="add" onclick='+"'"+'jia("#goodsummoney'+i+'","#goodnum'+i+'",'+child.mer.unitPrice+')'+"'"+'>+</em></div></td><td id="goodsummoney'+i+'">¥'+child.totalPrice+'</td><td><a class="commitorder">提交订单</a><a class="deletshopcar" onclick="delectcar('+child.cartMerId+')">删除</a></td>';
							tr.innerHTML = html;
	
							table.append(tr); 
			        	
					}
		        	
				}
	});
}


})
var acountid = localStorage.getItem("acountId");
function delectcar(id){
	var info = {
		cartMerId:id,
		accountId:acountid
	};
	
	ajxget("/cartMer/delete",info,function(data){
		if(data.code==200){
			alert("删除成功");
			location.reload();
		}
	});
}

function jian(valueid,sumid,price){
	console.log(price);
	var n=$(sumid).val();
	var num=parseInt(n)-1;
	if(num==0){ return}
	$(sumid).val(num);
	var money = accMul(num,price);
	
	$(valueid).text("¥"+money);
}

function jia(valueid,sumid,price){
	console.log(price);
	var n=$(sumid).val();
	var num=parseInt(n)+1;
	if(num==0){ return}
	$(sumid).val(num);
	var money = accMul(num,price);
	
	$(valueid).text("¥"+money);
}

function accMul(arg1,arg2)
{
var m=0,s1=arg1.toString(),s2=arg2.toString();
try{m+=s1.split(".")[1].length}catch(e){}
try{m+=s2.split(".")[1].length}catch(e){}
return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

function addorder(){
	
}
			
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