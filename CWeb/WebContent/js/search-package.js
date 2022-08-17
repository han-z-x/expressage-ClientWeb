$(function(){
        	$("#btnPackage").click(function(){
        		//alert("点击成功");
        		var PackageID=$("#ID").val();
        		
        		//写入设置Cookie值
        		//$.cookie(cookieName,cookieValue);cookieName:要设置的cookie名称，cookieValue表示相对应的值。
        		//读取cookie值
        		//$.cookie(cookieName)　cookieName:要读取的cookie名称
        		//删除cookie的值
        		$.cookie('mapID',null,{path:'/'});//删除cookie中mapID的值
        		//将PackageID的值保存1天在cookie中的mapID中
        		$.cookie('mapID',PackageID,{path:'/',expires:1});
        		
        		$('#iframe').attr('src', $('#iframe').attr('src'));//刷新地图，iframe可传参
        		//alert("点击成功"+PackageID);
        		if(!PackageID)alert("请输入快递单号");
        		else 
        			{
        			//alert("点击成功"+PackageID+"111");
        			searchpackage();
        			search();
        			}
        		
        	});
        });
function myTime(time){
	//获取当前时间
    
    var dateee = new Date(time).toJSON();

//  var dateee = new Date("2017-07-09T09:46:49.667").toJSON();
 
 var date = new Date(+new Date(dateee)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'')  
 
    return date;

}
function searchpackage(){
	//alert("快递查询成功!");
	var PackageID=$("#ID").val();
	//alert("快递单号"+PackageID);
	$.ajax({
        type:"get",
        url:'http://localhost:8080/TestCxfHibernate/REST/Domain/getExpressSheet/'+PackageID,//快递单号
        datatype:"json",
        async:false,
        data:{
        	id:"PackageID"
        },//data的
        success:function(data){
            //alert("获取成功");
            var con="";
            var accepter=data.accepter;
            var deliver=data.deliver;
            var accepteTime=data.accepteTime;
            var deliveTime=data.deliveTime;
            	
            	  if(accepter==undefined||accepter==''||accepter==null)
	                		accepter="尚未揽收";
	                	if(deliver==undefined||deliver==''||deliver==null)
	                		deliver="尚未派送";
	                	if(accepteTime==undefined||accepteTime==''||accepteTime==null)
	                		accepteTime="尚未揽收";
	                	else
	                		accepteTime=myTime(accepteTime);
	                	if(deliveTime==undefined||deliveTime==''||deliveTime==null)
	                		deliveTime="尚未派送";
	                	else
	                		deliveTime=myTime(deliveTime);//派送时间
	                	sender={
	                    		 "ID":data.sender.ID,
	                    		"name":data.sender.name,
	                    			"telCode":data.sender.telCode,
	                    		"postCode":data.sender.postCode,
	                    		"regionCode":data.sender.regionCode,
	                    			"regionString":data.sender.regionString,
	                    		"address":data.sender.address,
	                    			"department":data.sender.department
	                    			
	                     };
	                 	receiver={
	                    		 "ID":data.recever.ID,
	                    		"name":data.recever.name,
	                    			"telCode":data.recever.telCode,
	                    		"postCode":data.recever.postCode,
	                    		"regionCode":data.recever.regionCode,
	                    			"regionString":data.recever.regionString,
	                    		"address":data.recever.address,
	                    			"department":data.recever.department
	                    			
	                     };
	                 	express={
	                 			"ID":data.ID,
	                 			"sender":sender,
	                 			"receiver":receiver,
	                 			"tranFee":data.tranFee,
	                 			"insuFee":data.insuFee,
	                 			"packageFee":data.packageFee,
	                 			"status":data.status,
	                 			"weight":data.weight,
	                 			"type":data.type,
	                 			"accepter":accepter,
	                 			"deliver":deliver,
	                 			"accepteTime":accepteTime,
	                 			"deliveTime":deliveTime
	                 			
	                 	};
            con = "<tr><td>" + data.ID + "</td><td>" + data.sender.name +  "</td><td>" + data.recever.name +"</td><td>" + accepter+ "</td><td>" + accepteTime + "</td><td>" + deliver  + "</td><td>" + deliveTime +"</td></tr>";
            $("#table").html(con);
            //alter(con);
        },//success的
        error:function(){
            alert("请输入正确的快递单号!");
        }
    });
}

function search(){
	//alert("快递查询成功!");
	var PackageID=$("#ID").val();
	//alert("快递单号"+PackageID);
	//var str = "";
	//$("#table1").append(str);
	$.ajax({
        type:"get",
        url:'http://localhost:8080/TestCxfHibernate/REST/Domain/getTransHistory/id/'+PackageID,//快递单号
        datatype:"json",
        async:false,
        data:{
        	id:"PackageID"
        },//data的
        success:function(data){
        	$.each(data, function (i, item) {
				/*con = "<tr><td>" + item.actTime + "</td><td>" + item.action +  "</td><td>" + item.x + "</td><td>" + item.y+"</td></tr>";*/
        		con='<li class="time-axis-item">'+
				 '<div class="time-axis-date">'+item.actTime+'<span></span></div>'+
				 '<div class="time-axis-title">'+item.action+"  "+item.x+"  "+item.y+'</div>'+
				 '<p class="time-axis-achievement">'+"  "+'</p>'+
			 '</li>';
        		/*$("#table1").prepend(con);*/
        		$("#timeline").prepend(con);
			})
            /*con = "<tr><td>" + data.ID + "</td><td>" + data.sender.name +  "</td><td>" + data.recever.name +"</td><td>" + accepter+ "</td><td>" + accepteTime + "</td><td>" + deliver  + "</td><td>" + deliveTime +"</td></tr>";
            $("#table1").html(con);*/
            //alter(con);
        },//success的
        error:function(){
            alert("失败2");
        }
    });
}
////获取运单信息
//        $("#btn").click(function(){
//        	$.cookie('mapID',null,{path:'/'});
//            var $ID=$("#Express_id").val();
//            $.cookie('mapID',$ID,{path:'/',expires:1});
//          
//         
//            $('#iframe').attr('src', $('#iframe').attr('src'));
//            var name;
//            var tel;
//            $.ajax({
//                type:"get",
//                url:"http://localhost:8080/TestCxfHibernate/REST/Domain/getExpressSheet/"+$ID,
//                dataType:"json",
//                success:function(data){
//                    alert("获取成功");
//                    var con="";
//                    var accepter=data.accepter;
//	                	var deliver=data.deliver;
//	                	var accepteTime=data.accepteTime;
//	                	var deliveTime=data.deliveTime;
//	                	
//	                	  if(accepter==undefined||accepter==''||accepter==null)
//	 	                		accepter="尚未揽收";
//	 	                	if(deliver==undefined||deliver==''||deliver==null)
//	 	                		deliver="尚未派送";
//	 	                	if(accepteTime==undefined||accepteTime==''||accepteTime==null)
//	 	                		accepteTime="尚未揽收";
//	 	                	else
//	 	                		accepteTime=myTime(accepteTime);
//	 	                	if(deliveTime==undefined||deliveTime==''||deliveTime==null)
//	 	                		deliveTime="尚未派送";
//	 	                	else
//	 	                		deliveTime=myTime(deliveTime);
//	 	                	sender={
//	 	                    		 "ID":data.sender.ID,
//	 	                    		"name":data.sender.name,
//	 	                    			"telCode":data.sender.telCode,
//	 	                    		"postCode":data.sender.postCode,
//	 	                    		"regionCode":data.sender.regionCode,
//	 	                    			"regionString":data.sender.regionString,
//	 	                    		"address":data.sender.address,
//	 	                    			"department":data.sender.department
//	 	                    			
//	 	                     };
//	 	                 	receiver={
//	 	                    		 "ID":data.recever.ID,
//	 	                    		"name":data.recever.name,
//	 	                    			"telCode":data.recever.telCode,
//	 	                    		"postCode":data.recever.postCode,
//	 	                    		"regionCode":data.recever.regionCode,
//	 	                    			"regionString":data.recever.regionString,
//	 	                    		"address":data.recever.address,
//	 	                    			"department":data.recever.department
//	 	                    			
//	 	                     };
//	 	                 	express={
//	 	                 			"ID":data.ID,
//	 	                 			"sender":sender,
//	 	                 			"receiver":receiver,
//	 	                 			"tranFee":data.tranFee,
//	 	                 			"insuFee":data.insuFee,
//	 	                 			"packageFee":data.packageFee,
//	 	                 			"status":data.status,
//	 	                 			"weight":data.weight,
//	 	                 			"type":data.type,
//	 	                 			"accepter":accepter,
//	 	                 			"deliver":deliver,
//	 	                 			"accepteTime":accepteTime,
//	 	                 			"deliveTime":deliveTime
//	 	                 			
//	 	                 	};
//	                		
//                con += "<tr><td><a class='eID'>" + data.ID + "</a></td><td><a class='senderD'>" + data.sender.name +  "</a></td><td><a class='receiverD'>" + data.recever.name +"</a></td><td>" + accepter+ "</td><td>" + accepteTime + "</td><td>" + deliver  + "</td><td>" + deliveTime +"</td></tr>";
//                    $("#table").html(con);
//                },
//                error:function(){
//                    alert("获取失败a");
//                }
//            });
//
//            $.ajax({
//                type:"get",
//                url:"http://localhost:8080/TestCxfHibernate/REST/Domain/getPath/"+$ID,
//                dataType:"json",
//                success:function(data){
//                    alert("获取成功");
//                    var con = "";
//                    var next = "";
//                    for (var i = 0; i < data.length; i++) {
//                    	if(i==data.length-1)
//                			data[i].start=data[i].end;
//                    	if(data[i].status==1)
//                    		{
//                            con += "<tr><td>" + myTime(data[i].start) + "</td><td>"+data[i].nodeName +"</p>"+"<span>已揽收</span></p></td><td>" + data[i].userInfo+"</td></tr>"
//                    		}
//                    	if(data[i].status==2)
//                		{
//                            con += "<tr><td>" + myTime(data[i].start) + "</td><td>"+data[i].nodeName +"</p>"+"<span>已分拣</span></p></td><td>" + data[i].userInfo+"</td></tr>"
//                		}
//                    	if(data[i].status==3)
//                    		{
//                    		con += "<tr><td>" + myTime(data[i].start) + "</td><td>"+data[i].nodeName +"</p>"+"<span>已发出，将发往</span></p>"+data[i].nextNodeName+"</td><td>" + data[i].userInfo+"</td></tr>"
//                             } 
//                    	if(data[i].status==4)
//                		{
//                		    con += "<tr><td>" + myTime(data[i].start) + "</td><td>"+data[i].nodeName +"</p>"+"<span>正在派送</span></p></td><td>" + data[i].userInfo+"</td></tr>"
//                         } 
//                    	if(data[i].status==5)
//                		{
//                		    con += "<tr><td>" + myTime(data[i].end) + "</td><td>"+data[i].nodeName +"</p>"+"<span>已签收</span></p></td><td>" + data[i].userInfo+"</td></tr>"
//                         } 
//                    }
//                    $("#table1").html(con);
//                },
//                error:function(){
//                    alert("获取失败");
//                }
//            });
//        });
//        $(document).on('click','.eID',function(){
//        	
//       	 var eID=$(this).parents("tr").find("td").eq(0).text();
//       	 $.ajax({
//                type:"get",
//                async:false,
//                url:"http://localhost:8080/TestCxfHibernate/REST/Domain/getExpressSheet/"+eID,
//                dataType:"json",
//                success:function(data){
//               	
//                	                  	
//                     if(data=='')
//                     	alert("该运单不存在");
//                     else{
//                   	  var accepter=data.accepter;
//                       	var deliver=data.deliver;
//                       	var accepteTime=data.accepteTime;
//                       	var deliveTime=data.deliveTime;
//                       	var status=data.status;
//                       	if(status==0)
//                       		status="刚创建";
//                       	else if(status==1)
//                       		status="刚揽收";
//                       	else if(status==2)
//                       		status="分拣中";
//                       	else if(status==3)
//                       		status="转运中";
//                       	else if(status==4)
//                       		status="派送中";
//                       	else if(status==5)
//                       		status="已签收";
//                       	
//                       	  if(accepter==undefined||accepter==''||accepter==null)
//                            		accepter="尚未揽收";
//                            	if(deliver==undefined||deliver==''||deliver==null)
//                            		deliver="尚未派送";
//                            	if(accepteTime==undefined||accepteTime==''||accepteTime==null)
//                            		accepteTime="尚未揽收";
//                            	else
//                            		accepteTime=myTime(accepteTime);
//                            	if(deliveTime==undefined||deliveTime==''||deliveTime==null)
//                            		deliveTime="尚未派送";
//                            	else
//                            		deliveTime=myTime(deliveTime);
//                      	sender={
//                         		 "ID":data.sender.ID,
//                         		"name":data.sender.name,
//                         			"telCode":data.sender.telCode,
//                         		"postCode":data.sender.postCode,
//                         		"regionCode":data.sender.regionCode,
//                         			"regionString":data.sender.regionString,
//                         		"address":data.sender.address,
//                         			"department":data.sender.department
//                         			
//                          };
//                      	receiver={
//                         		 "ID":data.recever.ID,
//                         		"name":data.recever.name,
//                         			"telCode":data.recever.telCode,
//                         		"postCode":data.recever.postCode,
//                         		"regionCode":data.recever.regionCode,
//                         			"regionString":data.recever.regionString,
//                         		"address":data.recever.address,
//                         			"department":data.recever.department
//                         			
//                          };
//                      	express={
//                      			"ID":data.ID,
//                      			"sender":sender,
//                      			"receiver":receiver,
//                      			"tranFee":data.tranFee,
//                      			"insuFee":data.insuFee,
//                      			"packageFee":data.packageFee,
//                      			"status":status,
//                      			"weight":data.weight,
//                      		
//                      			"accepter":accepter,
//                      			"deliver":deliver,
//                      			"accepteTime":accepteTime,
//                      			"deliveTime":deliveTime
//                      			
//                      	};
//                      
//                       $("#eid").text(express.ID);
//             		 //寄件人详细信息
//                       $("#esid").text(express.sender.ID);
//                       $("#esname").text(express.sender.name);
//                       $("#estel").text(express.sender.telCode);
//                       $("#espos").text(express.sender.postCode);
//                       $("#esreg").text(express.sender.regionCode);
//                       $("#esregS").text(express.sender.regionString);
//                       $("#esadd").text(express.sender.address);
//                       $("#esdpt").text(express.sender.department);
//                       //收件人详细信息
//                       $("#erid").text(express.receiver.ID);
//                       $("#ername").text(express.receiver.name);
//                       $("#ertel").text(express.receiver.telCode);
//                       $("#erpos").text(express.receiver.postCode);
//                       $("#erreg").text(express.receiver.regionCode);
//                       $("#erregS").text(express.receiver.regionString);
//                       $("#eradd").text(express.receiver.address);
//                       $("#erdpt").text(express.receiver.department);
//                       
//             		  $("#ewei").text(express.weight);
//             		  $("#etrf").text(express.tranFee);
//             		  $("#epaf").text(express.packageFee);
//             		  $("#einf").text(express.insuFee);
//             		  $("#eacc").text(express.accepter);
//             		  $("#eact").text(express.accepteTime);
//             		 $("#edel").text(express.deliver);
//            		  $("#edet").text(express.deliveTime);
//            		 
//            		  $("#esta").text(express.status);
//            		  
//             		  $("#expModal").modal('show');
//                     		
//                     }
//                    
//                },
//                error:function(){
//                    alert("该运单不存在");
//                }
//            });
//       	  return false;
//        });
//       
//
//         $(document).on('click','.senderD',function(){
//       	
//       	  if(sender!=null){
//       		  $("#cid").text(sender.ID);
//       		  $("#cname").text(sender.name);
//       		  $("#ctel").text(sender.telCode);
//       		  $("#cpos").text(sender.postCode);
//       		  $("#creg").text(sender.regionCode);
//       		  $("#cregS").text(sender.regionString);
//       		  $("#cdep").text(sender.department);
//       		  $("#cadd").text(sender.address);
//       		  $("#detailModal").modal('show');
//       		  
//       	  }
//       	  else{
//       		  alert("用户信息获取出错！");
//       	  }
//       	  return false;
//         });
//         
//         $(document).on('click','.receiverD',function(){
//       	  if(receiver!=null){
//       		  $("#cid").text(receiver.ID);
//       		  $("#cname").text(receiver.name);
//       		  $("#ctel").text(receiver.telCode);
//       		  $("#cpos").text(receiver.postCode);
//       		  $("#creg").text(receiver.regionCode);
//       		  $("#cregS").text(receiver.regionString);
//       		  $("#cdep").text(receiver.department);
//       		  $("#cadd").text(receiver.address);
//       		  $("#detailModal").modal('show');
//       		  
//       	  }
//       	  else{
//       		  alert("用户信息获取出错！");
//       	  }
//       	  return false;
//         });

 