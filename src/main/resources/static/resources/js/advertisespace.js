;(function(window){
document.querySelector('html body').style.cssText="margin:0;padding:0;-webkit-overflow-scrolling:touch;overflow:auto;";
var urlhost="http://advertiseserver.yayao8.com";
//var urlhost="http://localhost";
var advertiseSpace=(function(){
	/**
	**对象初始化
	*/
    function advertiseSpace(UI,advertiseSpaceConfig){
       //广告UI ，默认移动端配置
       this.UI={
        width:'92%',//宽度
        height:'100%',//高度
        fontSize:'16px',//字体大小
        color:'#000',//字体颜色
        backgroundColor:'#fff',//背景颜色
        position:"relative",//默认相对定位
        left:'0',//默认0
        top:'0',//默认0
        bottom:'auto',//默认取消，与top切换用
        margin:'0',//默认为0
        padding:'0',//默认为0
        textLine:2,//文字行数
        borderBottom:'0 solid #ececec',//下划线
        display:'block',
        zindex:'1001'//深度
       };
     
      //多个广告位对象配置
    	this.advertiseSpaceConfig=[
      {
       	advertise_space_id:window.advertise_space_id||'',//广告位id
       	name:'',//名称
        platform:'移动端',//平台
        type:'悬浮',//类型
        business_type:'男性网站',
        billing_mode:'CPC',
        region:'全国',
        location:'顶部',
        unit_price:'',
        now_unit_delivery_number:'',
        now_unit_money:'',
        status:'',//状态
        close:true,//是否开启关闭按钮，默认开启
        update_time:timeStampToDate(new Date()),//更新时间
        admin_id:'',//渠道主id
        advertiseList:[ //多个广告对象配置
        { advertise_id:'',//广告id
         name:'',//名称
         type:'',//类型
         subtype :'',//子类型
         billing_mode:'CPC',
         region:'全国',
         text:"新浪娱乐讯 今日（1月17日），刘恺威、王鸥领衔主演的《莽荒纪》曝光了IMAX版人物海报。刘恺威、王鸥、陈亦飞等人化身莽荒英雄，手握神兵利器，破幕而出，利用出屏的动作设计以及横向的观看形式，摄人心魄可玩性十足。",
         img:['http://www.yayao8.com/resources/img/logo.jpg','http://www.yayao8.com/resources/img/logo.jpg','http://www.yayao8.com/resources/img/logo.jpg'],//图片路径
         link:'',//链接地址
         unit_price:'',
         unit_delivery_number:'',
         now_unit_delivery_number:'',
         unit_money:'',
         now_unit_money:'',
         status:'',//状态
         start_delivery_date:'',//投放开始时间
         end_delivery_date:'',//投放结束时间
         update_date:timeStampToDate(new Date()),//更新时间
         admin_id:''//广告主id
       }]
       }
       ];
      
		//$.extend(this.UI,UI);
    if(UI){
		this.UI=extendObj(this.UI,UI);
    }
     if(advertiseSpaceConfig){
    this.advertiseSpaceConfig=transform(extendObj(this.advertiseSpaceConfig,advertiseSpaceConfig));
    }
    }
      
    /**
    **对象方法定义
    */
    advertiseSpace.prototype={
/**
**配置整体广告
*/
        getAdvertiseSpaceConfig:function(){
          var _this=this;
    //_this.advertiseSpaceConfig.location='顶部';
   // _this.advertiseSpaceConfig.location='底部';
    //_this.advertiseSpaceConfig.type="悬浮";
  // _this.advertiseSpaceConfig.type="内嵌";
          //获取广告位
          myAjax({
                 url: urlhost+"/advertiseSpace/"+advertise_space_id,     //请求地址
                 type: "GET",
                 async:false,                       //请求方式
                 dataType: "json",
                 success: function (response, xml) {
                     // 此处放成功后执行的代码
                  // _this.UI.imgUrl="http://dadi.c0563.com/MMUUAA/3536/0e2f79263a938ab70b62514bf7df487a";
                    _this.advertiseSpaceConfig[0].advertise_space_id=JSON.parse(response).advertise_space_id;
                    _this.advertiseSpaceConfig[0].name=JSON.parse(response).name;
                    _this.advertiseSpaceConfig[0].platform=JSON.parse(response).platform;
                    _this.advertiseSpaceConfig[0].type=JSON.parse(response).type;
                    _this.advertiseSpaceConfig[0].business_type=JSON.parse(response).business_type;
                    _this.advertiseSpaceConfig[0].billing_mode=JSON.parse(response).billing_mode;
                    _this.advertiseSpaceConfig[0].region=JSON.parse(response).region;
                    _this.advertiseSpaceConfig[0].location=JSON.parse(response).location;
                    _this.advertiseSpaceConfig[0].status=JSON.parse(response).status;
                    _this.advertiseSpaceConfig[0].admin_id=JSON.parse(response).admin_id;
                    if(_this.advertiseSpaceConfig[0].type=="内嵌"){
                    	_this.advertiseSpaceConfig[0].close=false;
                    }
                    if(_this.advertiseSpaceConfig[0].type=="悬浮"){
                    	_this.advertiseSpaceConfig[0].close=true;
                    }
                     console.log(JSON.parse(response))
                 },
                 fail: function (status) {
                     // 此处放失败后执行的代码
                 }
             });
          //获取广告
          myAjax({
        	  url: urlhost+"/advertise/advertiseSpaceShowAdvertise",     //请求地址
        	  type: "GET",
        	  async:false,  
        	  data:{
        		  advertiseSpaceId:_this.advertiseSpaceConfig[0].advertise_space_id
        	  },
        	  dataType: "json",
        	  success: function (response, xml) {
        		  // 此处放成功后执行的代码
        		  // _this.UI.imgUrl="http://dadi.c0563.com/MMUUAA/3536/0e2f79263a938ab70b62514bf7df487a";
        		  if(response.code==40000){
        			  throw "服务错误";
        		  }
        		  if(!response){
        			  _this.advertiseSpaceConfig[0]=[];
        			 
        			  throw "暂无广告！";
        		  }
        		 // console.log(response)
        		  _this.advertiseSpaceConfig[0].advertiseList[0].advertise_id=JSON.parse(response).advertise_id;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].name=JSON.parse(response).name;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].type=JSON.parse(response).type;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].subtype=JSON.parse(response).subtype;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].billing_mode=JSON.parse(response).billing_mode;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].region=JSON.parse(response).region;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].text="";
        		  if(JSON.parse(response).title){
        			  _this.advertiseSpaceConfig[0].advertiseList[0].text=JSON.parse(response).title;
        		  }
        		  if(_this.advertiseSpaceConfig[0].platform=="移动端"){
        		  _this.advertiseSpaceConfig[0].advertiseList[0].img=[JSON.parse(response).img];
        		  }else if(_this.advertiseSpaceConfig[0].platform=="PC端"){
        			  _this.advertiseSpaceConfig[0].advertiseList[0].img=[JSON.parse(response).img];
        		  }
        		  _this.advertiseSpaceConfig[0].advertiseList[0].link=JSON.parse(response).link;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].link=JSON.parse(response).link;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].status=JSON.parse(response).status;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].start_delivery_date=JSON.parse(response).start_delivery_date;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].end_delivery_date=JSON.parse(response).end_delivery_date;
        		  _this.advertiseSpaceConfig[0].advertiseList[0].admin_id=JSON.parse(response).admin_id;
        		  console.log(JSON.parse(response))
        	  },
        	  fail: function (status) {
        		  // 此处放失败后执行的代码
        	  }
          });
     return _this.advertiseSpaceConfig;
        },
/**
**获取广告层结构
*thisAdvertiseSpaceConfig getAdvertiseSpaceConfig()的返回值
*location  广告位置
*/
 getAdvertiseSpaceDiv:function(){
    var thisAdvertiseSpaceConfig=this.getAdvertiseSpaceConfig();
    var thisAdvertiseSpaceConfigLength=thisAdvertiseSpaceConfig.length;
           var  oldWidth=this.UI.width;
    for(var i=0;i<thisAdvertiseSpaceConfigLength;i++){      
      //移动端
      if(thisAdvertiseSpaceConfig[i].platform=="移动端"){

           //位置
           var location;
           this.UI.left=parseInt((100-parseInt(oldWidth.slice(0,-1)))/2)+'%';
          if(thisAdvertiseSpaceConfig[i].type=='内嵌'){
            this.UI.position='relative';
            this.UI.zindex='9999'; 
            this.UI.width=oldWidth;  
            this.UI.padding="5px 0px";
          }

          if(thisAdvertiseSpaceConfig[i].type=='悬浮'){
               this.UI.position='fixed';
               this.UI.zindex='10002';
               this.UI.margin='0';
               this.UI.width="100%";
               this.UI.left='0';
               this.UI.padding='0';
                 
          }
           if(thisAdvertiseSpaceConfig[i].location=='顶部'){
              location='insertBefore';
              this.UI.top='0';
              this.UI.bottom='auto';
            }
            if(thisAdvertiseSpaceConfig[i].location=='底部'){
              location='appendChild';
              this.UI.top='auto';
              this.UI.bottom='0';
            } 

            //创建文字与图片高度
            var divHeight="auto";//div高度
            var firstaHeight='auto';//外包裹a高度
            var imgHeight='200px';//图片高度
            var thisDiv=document.createElement("div");//创建div
            var firsta=document.createElement("a");//创建第一个a

             //创建第一个a里面的text
            if(thisAdvertiseSpaceConfig[i].advertiseList[0].text){
             var firsttext=document.createElement("span");
             var firsttextwidth="100%";
             var firsttextmargin="1px";
              if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==1){
                firsttextwidth="100%";
                firsttextmargin="3px 0";
              }else  if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==2){
              firsttextwidth="98%";
              firsttextmargin="3px 4px";
              }else  if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==3){
              firsttextwidth="97%";
              firsttextmargin="3px 6px";
              }

             firsttext.setAttribute("style","margin:"+firsttextmargin+";text-align:left;width:"+firsttextwidth+";color:#000;overflow:hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp:"+this.UI.textLine+";-webkit-box-orient: vertical; z-index:"+this.UI.zindex+";font-size:"+this.UI.fontSize+";color:"+this.UI.color);
             firsttext.innerHTML=thisAdvertiseSpaceConfig[i].advertiseList[0].text;
             firsta.appendChild(firsttext);
            }
           

             if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==1){
              var oneImgMargin='0';
                  imgHeight='100%';
              //悬浮的大图变小图
               if(thisAdvertiseSpaceConfig[i].type=='悬浮'){
                //imgHeight="100px";
                imgHeight="31.25%";//宽高比16:5

              }

              var firstimg=document.createElement("img");
              firstimg.setAttribute("style","vertical-align:middle;width:100%;height:"+imgHeight+";z-index:"+this.UI.zindex);
              firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[0]);
              firsta.appendChild(firstimg);
              }else if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==2){
                //imgHeight='80px';
                imgHeight="100%";
               
              //创建第一个a里面的第一个img
              var firstimg=document.createElement("img");
              firstimg.setAttribute("style","margin:1px;width:49%;height:"+imgHeight+";z-index:"+this.UI.zindex);
              firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[0]);
              firsta.appendChild(firstimg);
             // //创建第一个a里面的第二个img
              var firstimg2=document.createElement("img");
              firstimg2.setAttribute("style","margin:1px;width:49%;height:"+imgHeight+";z-index:"+this.UI.zindex);
              firstimg2.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[1]);
              firsta.appendChild(firstimg2);
               console.log(firsta)
              }else if(thisAdvertiseSpaceConfig[i].advertiseList[0].img.length==3){
                //imgHeight='75px';
                imgHeight="100%";
              //创建第一个a里面的第n个img
              for(var k=0;k<thisAdvertiseSpaceConfig[i].advertiseList[0].img.length;k++){ 
             var firstimg=document.createElement("img");
             firstimg.setAttribute("style","margin:1px;width:32%;height:"+imgHeight+";z-index:"+this.UI.zindex);
             firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[0].img[k]);
             firsta.appendChild(firstimg);
              }
                  
              }
              else{
             throw new Error("图片数目1~3！");
              }

              //第一个a属性
             firsta.setAttribute("href",thisAdvertiseSpaceConfig[i].advertiseList[0].link);
             firsta.setAttribute("target",'view_window');
             firsta.setAttribute("id",'aopen');
             firsta.setAttribute("style","text-align:center;text-decoration:none !important;display:block;width:100%;height:"+firstaHeight+";padding:"+this.UI.padding);

            //div 属性 
             thisDiv.setAttribute("style","display:"+this.UI.display+";z-index:"+this.UI.zindex+";border-bottom:"+this.UI.borderBottom+";background-color:"+this.UI.backgroundColor+";position:"+this.UI.position+";left:"+this.UI.left+";top:"+this.UI.top+";bottom:"+this.UI.bottom+";height:"+divHeight+";width:"+this.UI.width+";margin:"+this.UI.margin);
             thisDiv.setAttribute('id',"ui");
            
             //把a导入div,把div导入body
             thisDiv.appendChild(firsta);
             var bodyNode=document.getElementsByTagName("body")[0];
             //bodyNode.appendChild(thisDiv);
             if(thisAdvertiseSpaceConfig[i].type=='内嵌'){
                 scriptlocation(bodyNode,advertise_space_id,thisDiv);
                 }else{
                 //动态添加
                   bodyNode[location](thisDiv,bodyNode.childNodes[0]);
                 }
             //创建第二个a
             if(thisAdvertiseSpaceConfig[i].close){ 
             var seconda=document.createElement("a");
             seconda.innerHTML="×";
             seconda.setAttribute("href",'javascript:;');
             seconda.setAttribute("id",'aclose');
             seconda.setAttribute("style","opacity:0.5;position:absolute;top:0;right:1px;border:1px solid #000; background-color:#000;color:white;height:20px;width:20px;line-height:20px;text-align:center;font-size:22px;text-decoration:none;");
             thisDiv.appendChild(seconda);
             }
    }//移动端 end
      //PC端
      else if(thisAdvertiseSpaceConfig[i].platform=="PC端"){
        //pc端初始化
        this.UI.width='320px';
        this.UI.height='300px';
        this.UI.left='0';
        this.UI.top='0';
        this.UI.margin='auto';
        this.UI.padding='5px';
        this.UI.display="inline-block";
         var location;
          if(thisAdvertiseSpaceConfig[i].type=='内嵌'){
            this.UI.zindex='9999';
            this.UI.position='relative';
            this.UI.height='auto';
            if(thisAdvertiseSpaceConfig[i].location=='顶部'){
              location='insertBefore';
            }
            if(thisAdvertiseSpaceConfig[i].location=='底部'){
              location='appendChild';
            }
          }

            if(thisAdvertiseSpaceConfig[i].type=='悬浮'){
            this.UI.position='fixed';
               this.UI.zindex='10002';
               this.UI.left='100%';
               this.UI.width='300px';
               this.UI.height='300px';
            if(thisAdvertiseSpaceConfig[i].location=='顶部'){
              location='insertBefore';
            }
            if(thisAdvertiseSpaceConfig[i].location=='底部'){
              location='appendChild';
              this.UI.top="100%";
              this.UI.margin="-"+this.UI.height+" -300px";
            }       
          }

            //创建div
             var thisDiv=document.createElement("div");
             thisDiv.setAttribute("style","display:"+this.UI.display+";border-bottom:"+this.UI.borderBottom+";background-color:"+this.UI.backgroundColor+";position:"+this.UI.position+";left:"+this.UI.left+";top:"+this.UI.top+";height:"+this.UI.height+";width:"+this.UI.width+";margin:"+this.UI.margin+";z-index:"+this.UI.zindex+";margin-bottom:0px;");
             thisDiv.setAttribute('id',"ui");
             
               

              for(var k=0;k<thisAdvertiseSpaceConfig[i].advertiseList.length;k++){                 
                if(thisAdvertiseSpaceConfig[i].advertiseList.length<=3){
                        var width="100%";
                        var height="100%";
                        if(thisAdvertiseSpaceConfig[i].advertiseList.length==1){
                           // width="960px";
                        	width="100%";
                            //height="360px";
                             if(thisAdvertiseSpaceConfig[i].type=='悬浮'){
                              width="100%";
                              height=(248-parseInt(this.UI.padding.slice(0,-2))*2)+'px';
                              //height="225px";
                             }
                        }
                         if(thisAdvertiseSpaceConfig[i].advertiseList.length==2){
                           //width="480px";
                           width="50%";
                           //height="180px";
                         }
                          if(thisAdvertiseSpaceConfig[i].advertiseList.length==3){
                          // width="320px";
                           width="33.33%";
                           //height="120px";
                         }
                        //有图片
                      if(thisAdvertiseSpaceConfig[i].advertiseList[k].img && thisAdvertiseSpaceConfig[i].advertiseList[k].img.length>0){
                   var  firsta=document.createElement("a");
                          firsta.setAttribute("href",thisAdvertiseSpaceConfig[i].advertiseList[k].link);
                        firsta.setAttribute("id",'aopen');
                      firsta.setAttribute("style","padding:"+this.UI.padding+";text-decoration:none !important;box-sizing:border-box;display:inline-block;width:"+width+";height:auto;");
                       var firstimg=document.createElement("img");
                       firstimg.setAttribute("style","width:100%;height:"+height+";z-index:"+this.UI.zindex);
                       firstimg.setAttribute("src",thisAdvertiseSpaceConfig[i].advertiseList[k].img[0]);
                       firsta.appendChild(firstimg);
                      
                      if(thisAdvertiseSpaceConfig[i].advertiseList[k].text){
                     var firsttext=document.createElement("span");
                     firsttext.setAttribute("style","height:24px;overflow:hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp:2;-webkit-box-orient: vertical; text-align:center;width:100%;height:15%;color:#000;z-index:"+this.UI.zindex+";font-size:"+this.UI.fontSize+";color:"+this.UI.color);
                     firsttext.innerHTML=thisAdvertiseSpaceConfig[i].advertiseList[k].text;
                     firsta.appendChild(firsttext);
                      }
                      //悬浮没有文字
                      if(!thisAdvertiseSpaceConfig[i].advertiseList[k].text && thisAdvertiseSpaceConfig[i].type=='悬浮'){
                        thisDiv.style.height='250px';
                        thisDiv.style.top='auto';
                        thisDiv.style.bottom='0px';
                      }

                     //把a导入div,把div导入body
                  thisDiv.appendChild(firsta);
                      }else{
                        //没图片
                       var  firstspan=document.createElement("span");
                      firstspan.setAttribute("style","vertical-align:top;line-height:30px;padding:"+this.UI.padding+";box-sizing:border-box;display:inline-block;width:"+width+";height:"+this.UI.height+";");
                        if(thisAdvertiseSpaceConfig[i].advertiseList[k].length>5||thisAdvertiseSpaceConfig[i].advertiseList[k].length<1){
                          throw new Error("文字链数目1~5！");
                        }
                        for(var y=0;y<thisAdvertiseSpaceConfig[i].advertiseList[k].length;y++){  
                       var firsta=document.createElement("a");
                     firsta.setAttribute("style","overflow:hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp:1;-webkit-box-orient: vertical; text-decoration:none;width:100%;z-index:"+this.UI.zindex+";font-size:"+this.UI.fontSize+";color:"+this.UI.color);
                     firsta.innerHTML=thisAdvertiseSpaceConfig[i].advertiseList[k][y].text;
                     firsta.setAttribute("href",thisAdvertiseSpaceConfig[i].advertiseList[k][y].link);
                     firsta.setAttribute("id",'aopen');
                     firstspan.appendChild(firsta);
                   }
                     //把a导入div,把div导入body
                      thisDiv.appendChild(firstspan);
                      }

                        }
                    else{
                    throw new Error("图片数目1~3！");
                         }

                 
               }
 
             
             var bodyNode=document.getElementsByTagName("body")[0];
             //bodyNode.appendChild(thisDiv);
             if(thisAdvertiseSpaceConfig[i].type=='内嵌'){
                 scriptlocation(bodyNode,advertise_space_id,thisDiv);
                 }else{
                 //动态添加
                   bodyNode[location](thisDiv,bodyNode.childNodes[0]);
                 }
             //创建第二个a 
            if(thisAdvertiseSpaceConfig[i].close){
             var seconda=document.createElement("a");
             seconda.innerHTML="×";
             seconda.setAttribute("href",'javascript:;');
             seconda.setAttribute("id",'aclose');
             seconda.setAttribute("style","opacity:0.5;position:absolute;top:-22px;right:1px;border:1px solid #000; background-color:#000;color:white;height:20px;width:20px;line-height:20px;text-align:center;font-size:22px;text-decoration:none;");
             thisDiv.appendChild(seconda);
            }

      }//PC端 end
      } 

             //监听第二个a的事件，删除整个div
             var _this=this;
          var acloseAll = document.querySelectorAll("#ui #aclose");
            for( var alcose = 0 ;alcose<acloseAll.length;alcose++ ){
             acloseAll[alcose].addEventListener('click',function(){
                     this.parentNode.remove();
              },false);
             }


            //监听第一个
             var aopenAll = document.querySelectorAll("#ui #aopen");
             var aopenAllBackgroundColor;//背景颜色
             var aopenTapHighlightColor;//tap颜色
            for( var aopen = 0 ;aopen<aopenAll.length;aopen++ ){   
             aopenAll[aopen].addEventListener('mouseover',function(){
                    // this.parentNode.remove();                
                    aopenAllBackgroundColor=this.style.backgroundColor;
                    this.style.backgroundColor='#f1eff0';
              },false);
             
             aopenAll[aopen].addEventListener('mouseout',function(){
                    // this.parentNode.remove();
                    this.style.backgroundColor=aopenAllBackgroundColor;
              },false);
             
             aopenAll[aopen].addEventListener('click',function(){
            	 var uv=0;//默认为0不是独立访客，没有uv,1为有uv
            	 //只有不存在，才是新访客
            	if(!getCookie("advertiseUv"+_this.advertiseSpaceConfig[0].advertiseList[0].advertise_id+"advertiseSpaceUv"+_this.advertiseSpaceConfig[0].advertise_space_id)){
            		setCookie("advertiseUv"+_this.advertiseSpaceConfig[0].advertiseList[0].advertise_id+"advertiseSpaceUv"+_this.advertiseSpaceConfig[0].advertise_space_id, "advertiseUv"+_this.advertiseSpaceConfig[0].advertiseList[0].advertise_id+"advertiseSpaceUv"+_this.advertiseSpaceConfig[0].advertise_space_id, currentToEndTime());
            		uv=1;
            	} 
               myAjax({
                  url: urlhost+"/advertise/click",     //请求地址
                  type: "POST",                       //请求方式
                  data: {
                    advertiseId:_this.advertiseSpaceConfig[0].advertiseList[0].advertise_id,
                    advertiseSpaceId:_this.advertiseSpaceConfig[0].advertise_space_id,
                    uv:uv
                    },        //请求参数
                  dataType: "json",
                  success: function (response, xml) {
                      // 此处放成功后执行的代码
                      console.log(JSON.parse(response))
                     
                     // console.log(xml)
                  },
                  fail: function (status) {
                      // 此处放失败后执行的代码
                  }
              });
             },false);
             }
            }//prototype end

    };

    return advertiseSpace;
}());
  //工具方法
/**
 * 信息流位置工具类，
 *body body
 *advertise_space_id 广告位id
 *nextNode 需要显示的子Node 
 * 
 */
function scriptlocation(body,advertise_space_id,nextNode){
var scripts=document.getElementsByTagName("script");
for (var i = 0; i < scripts.length; i++) {
  if(advertise_space_id && scripts[i].innerHTML.indexOf('advertise_space_id='+advertise_space_id)>-1){
  //scripts[i].outerHTML=nextNode.outerHTML+scripts[i].outerHTML;
	  scripts[i].outerHTML=nextNode.outerHTML;
  return ; 
  }
}
  return body;
}
 /**
   * 时间戳转yyyy-MM-dd hh:mm:ss
   * 
   */
  function timeStampToDate(timeStamp){
    var date = new Date(timeStamp);
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds(); 
  return Y+M+D+h+m+s; 
  }
  /**
   * 获取当前时间到当日23:59:59的时间差
   * 单位 秒
   * 
   */
  function currentToEndTime(){  
		var enddate =new Date();
		var date=new Date();
		enddate.setHours(23);
		enddate.setMinutes(59);
		enddate.setSeconds(59);
	 	var miao = (enddate.getTime()-date.getTime())/1000;
		return miao;
	}
  /**
**复制对象
*/
function cloneObj(oldObj) { 
if (typeof(oldObj) != 'object') return oldObj;
if (oldObj == null) return oldObj;
var newObj = new Object();
for (var i in oldObj)
newObj[i] = cloneObj(oldObj[i]);
return newObj;
};
/**
**扩展对象
*/
function extendObj() { 
var args = arguments;
if (args.length < 2) return;
var temp = cloneObj(args[0]); //调用复制对象方法
for (var n = 1; n < args.length; n++) {
for (var i in args[n]) {
temp[i] = args[n][i];
}
}
return temp;
}
/**
**对象转数组
*/
function transform(obj){
    var arr = [];
    for(var item in obj){
        arr.push(obj[item]);
    }
    return arr;
}

/**
   * cookie
   * 
   */
  // 写cookie
  function setCookie (name,value,expires)
  {
  var exp = new Date();
  exp.setTime(exp.getTime() + expires*1000);
  document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
  }
  // 读取cookie
  function getCookie (name)
  {
  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
  if(arr=document.cookie.match(reg))
  return unescape(arr[2]);
  else
  return null;
  }
  // 删除cookie
  function delCookie(name)
  {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval=myUtils.getCookie(name);
  if(cval!=null)
  document.cookie= name + "="+cval+";expires="+exp.toGMTString();
  }

  //ajax

    function myAjax(options) {
        options = options || {};
        options.type = (options.type || "GET").toUpperCase();
        options.dataType = options.dataType || "json";
        options.async==false?options.async:options.async=true;
        var params = formatParams(options.data);
        //创建 - 非IE6 - 第一步
        if (window.XMLHttpRequest) {
            var xhr = new XMLHttpRequest();
        } else { //IE6及其以下版本浏览器
            var xhr = new ActiveXObject('Microsoft.XMLHTTP');
        }

        //接收 - 第三步
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                var status = xhr.status;
                if (status >= 200 && status < 300) {
                    options.success && options.success(xhr.responseText, xhr.responseXML);
                } else {
                    options.fail && options.fail(status);
                }
            }
        }

        //连接 和 发送 - 第二步
        if (options.type == "GET") {
            xhr.open("GET", options.url + "?" + params, options.async);
            xhr.send(null);
        } else if (options.type == "POST") {
            xhr.open("POST", options.url, options.async);
            //设置表单提交时的内容类型
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(params);
        }
    //格式化参数
    function formatParams(data) {
        var arr = [];
        for (var name in data) {
            arr.push(encodeURIComponent(name) + "=" + encodeURIComponent(data[name]));
        }
        arr.push(("v=" + Math.random()).replace(".",""));
        return arr.join("&");
    }
    }

  //滚动条在Y轴上的滚动距离
function getScrollTop(){
　　var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
　　if(document.body){
　　　　bodyScrollTop = document.body.scrollTop;
　　}
　　if(document.documentElement){
　　　　documentScrollTop = document.documentElement.scrollTop;
　　}
　　scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
　　return scrollTop;
}
//文档的总高度
function getScrollHeight(){
　　var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
　　if(document.body){
　　　　bodyScrollHeight = document.body.scrollHeight;
　　}
　　if(document.documentElement){
　　　　documentScrollHeight = document.documentElement.scrollHeight;
　　}
　　scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
　　return scrollHeight;
}
//浏览器视口的高度
function getWindowHeight(){
　　var windowHeight = 0;
　　if(document.compatMode == "CSS1Compat"){
　　　　windowHeight = document.documentElement.clientHeight;
　　}else{
　　　　windowHeight = document.body.clientHeight;
　　}
　　return windowHeight;
}
window.onscroll = function(){
　　if(getScrollTop() + getWindowHeight() == getScrollHeight()){
　　　　//alert("you are in the bottom!");
        //console.log(channelAdvertiseWap)
　　}
};

//对象调用
var oldAdvertiseSpace=new advertiseSpace();
oldAdvertiseSpace.getAdvertiseSpaceDiv();
  //window.advertiseSpace= advertiseSpace;

})(window);