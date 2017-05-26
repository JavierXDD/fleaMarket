	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;


//发布
function editBlog(){
	
		var isLogin = $.trim($("#isLoginId").val());
		if(isLogin == null || isLogin == ""){
    		location.href = basePath+"/user/toLogin";
		}else{
			location.href = basePath+"/blog/toMyBlogEdit";
		}

};

var IndexDoor = function(){
	
	return{
		init:function(){
			var messageTextTD = $(".messageText");
			messageTextTD.each(function(){	
				if($(this).text().length>30){
					$(this).text($(this).text().substring(0,35));
					$(this).html($(this).text()+'...');
				}else{
					$(this).html($(this).text());
				}				
			});			
			
		}
	}
}();
