/**
 * 网点地图的js
 */
// 百度地图API功能
	//var provice = $("#pro").attr("")+$("#city").attr("")+$("#town").attr("");//网点所在区域名字

    var map = new BMapGL.Map("iframe");    // 创建Map实例
    var ip = new BMapGL.Point(116.331398,39.897445);
    // 创建点坐标 ，Point描述坐标点（经纬度坐标）
    map.centerAndZoom(ip, 13);
    // 初始化地图，设置中心点坐标和地图级别

     //创建地址解析器实例
    var myGeo = new BMapGL.Geocoder();
    // 将地址解析结果显示在地图上，并调整地图视野
    myGeo.getPoint('北京市海淀区上地10街', function(point){
    if(point){
    	alert('解析到结果！');
        map.centerAndZoom(point, 16);
        map.addOverlay(new BMapGL.Marker(point, {title: '北京市海淀区上地10街'}))
        
    }else{
        alert('您选择的地址没有解析到结果！');
    }
}, '北京市');