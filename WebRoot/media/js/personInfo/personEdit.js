var PersonEdit = function(){
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
	return{
		init:function(){
				var formCheck = true;
	           
	           $("#telephone").blur(function(){
	        	   var telephone = $.trim($("#telephone").val());
	        	   var re =/(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/;
	        	   if(telephone == ""){
	        		   $("#telephone_help").html("");
	        		   formCheck = true;
	        	   }else if( re.test(telephone)){
	        		   $("#telephone_help").html("");
	        		   formCheck = true;
	        	   }else{
	        		   $("#telephone_help").html("请输入正确的电话或手机号码!");
	        		   formCheck = false;
	        	   }
	           })
	           
	           $("#email").blur(function(){
	        	   var email = $.trim($("#email").val());
	        	   var re =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	        	   
	        	   if(email == ""){
	        		   $("#email_help").html("电子邮箱为必填!");
	        		   formCheck = false;
	        	   }else if( re.test( email )){
	        		   $("#email_help").html("");
	        		   formCheck = true;
	        	   }else{
	        		   $("#email_help").html("请输入正确的电子邮箱!");
	        		   formCheck = false;
	        	   }
	           })
	           
	           $("#edit_sub").click(function(){
	        	   var email = $.trim($("#email").val());
	        	   var nickname = $.trim($("#userEdit_nickname").val());
	        	   if(email == ""){
	        		   $("#email_help").html("电子邮箱为必填!");
	        		   formCheck = false;
	        	   }
	        	   if(nickname == ""){
	        		   $("#userEdit_nickname_help").html("用户名不能为空！");
	        		   formCheck = false;
	        	   }
	        	   
	        	   if(formCheck){
	        		   $("#personEditForm").submit();
	        	   }
	           })
		}
	}
}();
