	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;


//打开改密码模态框
function changePassword(){	
	$('#changePasswordModal').modal('show');
	
};

var PersonInfo = function(){
	
	return{
		init:function(){
			var allOk = false;
			$("#newPassword")
			
			$("#changePassword_submit").click(function(){
				$("#newPassword2_help").text("");
				$("#oldPassword_help").text("");
				if($("#newPassword").val() == $("#newPassword2").val() && $("#newPassword").val() !=""){
					var userId = $("#userId").serialize();
					var oldPassword = $("#oldPassword").serialize();
					var newPassword = $("#newPassword").serialize();
					var data = userId+"&"+oldPassword+"&"+newPassword;
					$.ajax({
						url: basePath+"/user/changePassword",  
				    	type: "post",                         
				        data:data,
				        success:function(result){				        	
				        	if(result == "success"){
				        		$('#changePasswordModal').modal('hide');
								$("#changePasswordAlert").attr("class","alert");
								$("#changePasswordAlertStrong").html("密码修改成功");							
				        	}else if("oldPasswordError" == result){
				        		$("#oldPassword_help").text("旧密码输入错误！");
				        	}else if("error" == result){
								$("#changePasswordAlert").attr("class","alert");
								$("#changePasswordAlertStrong").html("系统忙，请稍后再试");
				        	}
				        }
					})									
				}else{
					$("#newPassword2_help").text("新密码两次输入不一致！");
				}
				
			});
			
			//$('#myModal').modal('hide');

			//$('#myModal').modal('toogle');

			//$('#myModal').on('hidden', function () { });
		}
	}
}();
