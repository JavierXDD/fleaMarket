	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
//删除评论
function deleteBlogMessage(messageId){
	var data = "messageId="+messageId;
	$.ajax({
		url: basePath+"/message/deleteBlogMessage",  
    	type: "post",                         
    	data:data,
        success:function(result){        	
        	if(result == "success"){
        		
        		location.href = basePath+"/blog/toMyBlogDetail?id="+$("#blogId").val();
        	}else{
        		$("#alert_text").html("出错了，请稍后再试。")
        		$("#alert").attr("class","alert alert-error");
        	}
        }
	})		
};

function replyButton(messageId,senderId,senderName){
	var isLogin = $.trim($("#isLoginId").val());
	if(isLogin == null || isLogin == ""){
		location.href = basePath+"/user/toLogin";
	}else{
		var str = "回复@"+senderName+": ";
		$("#message_text").val(str);
		$("html,body").animate({scrollTop: $("#message_text").offset().top}, 500);
		$("#message_parentId").val(messageId);
		$("#message_receiverId").val(senderId);
		$("#message_receiverName").val(senderName);		
	}
	

}

var Item = function(){
	
	return{
		init:function(){
			
			//显示信息标签
			var tags = $("#blog_tags").val();
			var arr=new Array();
			arr=tags.split(',');
			$.each(arr, function(i, value) {
				$("#icon-tags").after("<a href='#'>"+arr[i]+"</a>")

			});
			
			//分配大类颜色
			$(".menuShow").each(function(index,element){
				if(index % 5 == 0){
					$(element).attr("class","menuShow btn red");
				}else if(index % 5 == 1){
					$(element).attr("class","menuShow btn green");
				}else if(index % 5 == 2){
					$(element).attr("class","menuShow btn blue");
				}else if(index % 5 == 3){
					$(element).attr("class","menuShow btn yellow");
				}else{
					$(element).attr("class","menuShow btn purple");
				}				
			})
				
			//提交评论
			$("#message_submit").click(function(){
				var isLogin = $.trim($("#isLoginId").val());
				if(isLogin == null || isLogin == ""){
		    		location.href = basePath+"/user/toLogin";
				}else{
					if($("#message_text").val().length>80){
		        		$("#message_alertText").html("字数不能多于80字！");
		        		$("#message_alert").attr("class","alert alert-error span10");	
					}else{				
						var  data = $("#messageForm").serialize();
						$.ajax({
							url: basePath+"/message/publishMessage",  
					    	type: "post",                         
					        data:data,
					        success:function(result){
					        	if(result == "success"){
					        		location.href = basePath+"/blog/toMyBlogDetail?id="+$("#message_blogId").val();
					        	}else{
					        		$("#message_alertText").html("服务器忙，请稍后再试！");
					        		$("#message_alert").attr("class","alert alert-error");
					        	}
					        }
						})						
					}					
				}
			});
		}
	}
}();
