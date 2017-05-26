//禁用
function forbiddenMenu(menuId){
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		
	
	var data = "menuId="+menuId;

	$.ajax({
		url: basePath+"/menu/forbiddenMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/menu/toMenuList";
        	}else{
        		alert("error!");
        	}
        }
	})
	
};


//启用
function usingMenu(menuId){
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		
	var data = "menuId="+menuId;

	$.ajax({
		url: basePath+"/menu/usingMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/menu/toMenuList";
        	}else{
        		alert("error!");
        	}
        }
	})
	
};

var MenuList = function(){
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
	return{
		init:function(){
			
			var menuTextTD = $(".menuText");
			menuTextTD.each(function(){	
				if($(this).text().length>30){
					$(this).text($(this).text().substring(0,30));
					$(this).html($(this).text()+'...');
				}else{
					$(this).html($(this).text());
				}
				
			});

		}
	}
}();
