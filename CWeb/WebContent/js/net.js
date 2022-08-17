$(function(){
	
	$("#nodeName").on("click",function(){
        var nodeName = $("#town").val();
        var node=nodeName+"00";
       
        $.ajax({
            type:"get",
            url:"http://localhost:8080/TestCxfHibernate/REST/Misc/getNode/"+node,
            dataType:"json",                     
            success:function(data){
            	var con="";                   	
	                if(data=='')
	                	alert("该网点不存在");
	                else{
                      con=data.nodeName;
                      alert("该网点存在，快去寄件吧!");
	                	//con = "<tr><td>" + data.ID + "</td><td>" + data.nodeName +  "</td><td>" + data.telCode +"</td></tr>"	                	
	                }
            },
            error:function(){
                alert("该网点不存在");
            }
        });
    });
	
});

//$("#nodeName").on("click",function(){
//                   var nodeName = $("#town").val();
//                   var node=nodeName+"00";
//                  
//                   $.ajax({
//                       type:"get",
//                       url:"http://localhost:8080/TestCxfHibernate/REST/Misc/getNode/"+node,
//                       dataType:"json",                     
//                       success:function(data){
//                       	var con="";                   	
//        	                if(data=='')
//        	                	alert("该网点不存在");
//        	                else{
//                                 con=data.nodeName;
//                                 alert("该网点存在，快去寄件吧!");
//        	                	//con = "<tr><td>" + data.ID + "</td><td>" + data.nodeName +  "</td><td>" + data.telCode +"</td></tr>"	                	
//        	                }
//                       },
//                       error:function(){
//                           alert("该网点不存在");
//                       }
//                   });
//               })

 