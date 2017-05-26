	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

//禁用
function forbiddenIndexPic(id){

	var data = "id="+id;

	$.ajax({
		url: basePath+"/indexPic/forbiddenIndexPic",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/indexPic/toIndexPicList";
        	}else{
        		alert("error!");
        	}
        }
	})
	
};

//展示菜单
function showMenu(id){
	var data = "id="+id;

	$.ajax({
		url: basePath+"/menu/showMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/indexPic/toIndexPicList";
        	}else{
        		alert("error!");
        	}
        }
	})
	
};

//不展示菜单
function notShowMenu(id){
	var data = "id="+id;

	$.ajax({
		url: basePath+"/menu/notShowMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/indexPic/toIndexPicList";
        	}else{
        		alert("error!");
        	}
        }
	})
	
};



//启用
function usingIndexPic(id){

		
	var data = "id="+id;

	$.ajax({
		url: basePath+"/indexPic/usingIndexPic",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/indexPic/toIndexPicList";
        	}else{
        		alert("error!");
        	}
        }
	})
	
};

var IndexPicList = function(){	
	return{
		init:function(){
			
			var indexPicText = $(".indexPicText");
			indexPicText.each(function(){	
				if($(this).text().length>30){
					$(this).text($(this).text().substring(0,30));
					$(this).html($(this).text()+'...');
				}else{
					$(this).html($(this).text());
				}
				
			});
			
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
