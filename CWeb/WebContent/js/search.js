/**
 * 省市区三级联动
 */

 function initProvice() {
	//清空下拉数据
	$("#pro").html("");

	var str = "<option>--请选择--</option>";
	
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/TestCxfHibernate/REST/Misc/getProvinceList",
		datatype: "json",
		async: false,
		data:{
			prv:"provice",
			type:"json"
		},
		success: function (data) {
			//从服务器获取数据进行绑定
			$.each(data,function (i, item) {
				$("#pro").append("<option value="+item.Code+">"+item.Name+"</option>"); 
			});
		},
		error: function () { alert("错误1"); }
	});
}


$(function(){
	
	//初始化provice
	initProvice();

	var str = "<option>--请选择--</option>";
	$("#city").append(str);
	$("#area").append(str);

	$("#pro").change(function () {
		var proCode = $("#pro").val();
		//判断省份这个下拉框选中的值是否为空
		if (proCode == "") {
			return;
		}
		$("#city").html("");

	
		$.ajax({
			method: "GET",
			url: `http://localhost:8080/TestCxfHibernate/REST/Misc/getCityList/${proCode}/`,
			datatype: "json",
			async: false,
			success: function (data) {
				//从服务器获取数据进行绑定
				$.each(data, function (i, item) {
					str = "<option value=" + item.Code + ">" + item.Name + "</option>";
					$("#city").append(str);
				})
				//将数据添加到省份这个下拉框里面
				
			},
			error: function () { alert("错误2"); }
		});
	});

	$("#city").change(function() {
		var city = $("#city").val();
		//判断市这个下拉框选中的值是否为空
		if (city == "") {
			return;
		}
		$("#area").html("");
		var str = "<option>--请选择--</option>";
		//将市的id拿到数据库进行查询，查询出他的下级进行绑定
		$.ajax({
			method: "get",
			url: `http://localhost:8080/TestCxfHibernate/REST/Misc/getTownList/${city}/`,
			datatype: "json",
			async: false,
			success: function (data) {
				//从服务器获取数据进行绑定
				$.each(data, function (i, item) {
					str = "<option value=" + item.Code + ">" + item.Name + "</option>";
					$("#town").append(str);
				})
			},
			error: function () { alert("错误3"); }
		});
		
	});
});


/**** 

$(function () {
    
	$("#pro").change(() => {
		
	});

       	 
       	 $("#pro").change( function () {
       		 //var provCode = $("#pro").val();
       	     
       	 })
	//默认绑定省
       	 provicebind();
       	 //绑定事件
       	 //市
       	 //区 
       	 $("#city").change(function () {
       		 var cityCode = $("#city").val();
       	     villagebind();
       	 })
       	 
           })
       	function bind(str) {
       	     alert($("#pro").html());
       	     $("#pro").val(str);
       	     
       	}//function的}
           
       	//选择省份
       	
       	//选择城市
       	function citybind() {
       	 
       	     var provice = $("#pro").attr("value");
       	     //判断省份这个下拉框选中的值是否为空
       	     if (provice == "") {
       	         return;
       	     }
       	     $("#city").html("");
       	     var str = "<option>--请选择--</option>";
       	 
       	 
       	     $.ajax({
       	         method: "GET",
       	         url: "http://localhost:8080/TestCxfHibernate/REST/Misc/getCityList/",
       	         /* data: { "parentid": provice, "mycolums": "city" },
       	         data:{
       	        	 prv:"provice",
       	        	 type:"json"
       	         },
       	         datatype: "json",
       	         async: false,
       	         success: function (data) {
       	             //从服务器获取数据进行绑定
       	             $.each(data.data, function (i, item) {
       	                 str += "<option value=" + item.Code + ">" + item.Name + "</option>";
       	             })
       	             //将数据添加到省份这个下拉框里面
       	             $("#city").append(str);
       	         },
       	         error: function () { alert("错误2"); }
       	     });
       	}
       	//选择乡镇区
       	function villagebind() {
       	 
       	    
       	}

*/