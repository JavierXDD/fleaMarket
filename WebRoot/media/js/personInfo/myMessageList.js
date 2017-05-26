	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;


//打开模态框
function showDetail(id){
	
	var data = "id="+id;
	$.ajax({
		url: basePath+"/message/viewMessageDetail",  
    	type: "post",                         
    	data:data,
        success:function(result){        	
        	if(result != "error"){       		
        		$("#message_text").html(result);
        		$("#message_id").val(id);
        		$('#showDetailModal').modal('show');
        	}else{
        		alert("请稍后再试！");
        	}
        }
	})			

};

var MyMessageList = function(){
	
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

			
			$("#showDetail_delete").click(function(){
        		location.href = basePath+"/message/deleteMyMessage?id="+$("#message_id").val();
			});
		}
	}
}();
