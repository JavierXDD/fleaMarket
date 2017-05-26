//禁用用户
function forbidden(userID,userNickname){
	
	$("#frbiddenUserId").val(userID);
	$("#frbiddenUserNickname").val(userNickname);
	$('#forbiddenModal').modal('show');
	
};

//启用用户
function using(userID,userNickname){
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	
	
	var adminId = $("#adminId").serialize();
	var adminNickname = $("#adminNickname").serialize();
	
	var data = adminId + "&" + adminNickname + "&" + "userId=" + userID + "&userNickname=" +userNickname;

	$.ajax({
		url: basePath+"/userRecord/usingUser",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success_user"){
        		location.href = basePath+"/user/toUserList";
        	}else if(result == "success_admin"){
        		location.href = basePath+"/user/toAdminList";
        	}else if(result == "error"){
        		alert("error!");
        	}
        }
	})
	
	
};
var AdminList = function(){
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
	return{
		init:function(){
			
			$("#forbidden_submit").click(function(){
				var userId = $("#frbiddenUserId").serialize();
				var reason = $("#frbiddenReason").serialize();
				var adminId = $("#adminId").serialize();
				var adminNickname = $("#adminNickname").serialize();
				var userNickname = $("#frbiddenUserNickname").serialize();
				
				var data = userId + "&" + reason + "&" + adminId + "&" + adminNickname + "&" + userNickname;
				$("#frbiddenUserId").val("");
				$("#frbiddenUserNickname").val("");
				$.ajax({
					url: basePath+"/userRecord/forbiddenUser",  
                	type: "post",                         
                    data:data,
                    success:function(result){
                    	if(result == "success_user"){
                    		location.href = basePath+"/user/toUserList";
                    	}else if(result == "success_admin"){
                    		location.href = basePath+"/user/toAdminList";
                    	}else if(result == "error"){
                    		alert("error!");
                    	}
                    }
				})
			});
			//$('#myModal').modal('hide');

			//$('#myModal').modal('toogle');

			//$('#myModal').on('hidden', function () { });
		}
	}
}();
