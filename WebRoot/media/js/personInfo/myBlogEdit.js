	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	var select = $("#typeId");
	var menuId = $("#menuId").val();
	if(menuId != "" && menuId != null){
		var data = "menuId="+menuId;
		$.ajax({
			url: basePath+"/menu/chooseType",  
	    	type: "post",                         
	        data:data,
	        success:function(result){				        	
	        	select.empty();
	        	$.each(result, function(idx, obj) {				        		
	        	    select.append("<option value='"+obj.id+"'>"+obj.name+"</option>"); 
	        	});
	        }
		})
	}else{
		$("#type_help").Text("choose menu first!");
	}	

//打开配图模态框
function upSurfacePic(){
	var ue = UE.getEditor('UEcontent');
	
	$("#surfacePic_id").val($("#blogId").val());
	$("#surfacePic_title").val($("#title").val());
	$("#surfacePic_name").val($("#name").val());
	$("#surfacePic_price").val($("#price").val());
	$("#surfacePic_quality").val($("#quality").val());
	$("#surfacePic_userTelephone").val($("#userTelephone").val());
	$("#surfacePic_userEmail").val($("#userEmail").val());
	$("#surfacePic_createTime").val($("#createTime").val());
	$("#surfacePic_modifyTime").val($("#modifyTime").val());
	$("#surfacePic_text").val(ue.getContent());
	$("#surfacePic_userId").val($("#userId").val());
	$("#surfacePic_userName").val($("#userName").val());
	$("#surfacePic_menuId").val($("#menuId").val());
	$("#surfacePic_menuName").val($("#menuName").val());
	$("#surfacePic_typeId").val($("#typeId").val());
	$("#surfacePic_typeName").val($("#typeName").val());
	$("#surfacePic_tags").val($("#tags").val());
	$("#surfacePic_isUse").val($("#isUse").val());
	
	$('#surfacePicModal').modal('show');
	
};

var MyBlogEdit = function(){
	
	return{
		init:function(){
			UE.getEditor('UEcontent');
			$("#surfacePic_submit").click(function(){
				if($("#surfacePic").val() == ""){
					$("#surfacePic_help").html("请选择图片文件！");
				}else{
					$("#surfacePic_help").html("");
					$("#surfacePicForm").submit();
				}

			});
			
			$("#menuId").change(function(){
				var select = $("#typeId");
				var menuId = $("#menuId").val();
				if(menuId != "" && menuId != null){
					var data = "menuId="+menuId;
					$.ajax({
						url: basePath+"/menu/chooseType",  
				    	type: "post",                         
				        data:data,
				        success:function(result){				        	
				        	select.empty();
				        	$.each(result, function(idx, obj) {				        		
				        	    select.append("<option value='"+obj.id+"'>"+obj.name+"</option>"); 
				        	});
				        }
					})
				}else{
					$("#type_help").Text("choose menu first!");
				}								
			});
			//check
			var formCheck = true;				
           $("#userTelephone").blur(function(){
        	   var telephone = $.trim($("#userTelephone").val());
        	   var re =/(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/;
        	   if(telephone == ""){
        		   $("#userTelephone_help").html("请输入电话或手机号码!");
        		   formCheck = false;
        	   }else if( re.test(telephone)){
        		   $("#userTelephone_help").html("");
        		   formCheck = true;
        	   }else{
        		   $("#userTelephone_help").html("请输入正确的电话或手机号码!");
        		   formCheck = false;
        	   }
           });
           $("#userEmail").blur(function(){
        	   var email = $.trim($("#userEmail").val());
        	   var re =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	        	   
        	   if(email == ""){
        		   $("#userEmail_help").html("电子邮箱为必填!");
        		   formCheck = false;
        	   }else if( re.test( email )){
        		   $("#userEmail_help").html("");
        		   formCheck = true;
        	   }else{
        		   $("#userEmail_help").html("请输入正确的电子邮箱!");
        		   formCheck = false;
        	   }
           });
           $("#price").blur(function(){
        	   var price = $.trim($("#price").val());
        	   var re =/^[0-9]*$/;	        	   
        	   if(price == ""){
        		   $("#price_help").html("价格为必填!");
        		   formCheck = false;
        	   }else if( re.test( price )){
        		   $("#price_help").html("");
        		   formCheck = true;
        	   }else{
        		   $("#price_help").html("只能填写数字！");
        		   formCheck = false;
        	   }
           });
           $("#title").blur(function(){
        	   var title = $.trim($("#title").val());        	   
        	   if(title == ""){
        		   $("#title_help").html("标题为必填!");
        		   formCheck = false;
        	   }else if( re.test( price )){
        		   $("#title_help").html("");
        		   formCheck = true;
        	   }
           }); 
           $("#name").blur(function(){
        	   var name = $.trim($("#name").val());        	   
        	   if(name == ""){
        		   $("#name_help").html("品名为必填!");
        		   formCheck = false;
        	   }else if( re.test( price )){
        		   $("#name_help").html("");
        		   formCheck = true;
        	   }
           });
           $("#quality").blur(function(){
        	   var quality = $.trim($("#quality").val());        	   
        	   if(quality == ""){
        		   $("#quality_help").html("成色为必填!");
        		   formCheck = false;
        	   }else if( re.test( price )){
        		   $("#quality_help").html("");
        		   formCheck = true;
        	   }
           });
			//submit
			$("#save_myBlog").click(function(){
				var ue = UE.getEditor('UEcontent');
        	   var title = $.trim($("#title").val());
        	   var name = $.trim($("#name").val());
        	   var price = $.trim($("#price").val());
        	   var quality = $.trim($("#quality").val());
        	   var userTelephone = $.trim($("#userTelephone").val());
        	   var userEmail = $.trim($("#userEmail").val());
        	   var text = $.trim(ue.getContent());
        	   if(userEmail == ""){
        		   $("#userEmail_help").html("电子邮箱为必填!");
        		   formCheck = false;
        	   }
        	   if(title == ""){
        		   $("#title_help").html("标题为必填!");
        		   formCheck = false;
        	   }
        	   if(name == ""){
        		   $("#name_help").html("品名不能为空！");
        		   formCheck = false;
        	   }
        	   if(price == ""){
        		   $("#price_help").html("价格不能为空！");
        		   formCheck = false;
        	   }
        	   if(quality == ""){
        		   $("#quality_help").html("成色不能为空！");
        		   formCheck = false;
        	   }
        	   if(quality == ""){
        		   $("#userTelephone_help").html("联系人电话不能为空！");
        		   formCheck = false;
        	   }
        	   if(text == ""){
        		   $("#text_help").html("详细信息不能为空！");
        		   formCheck = false;
        	   }
        	   
        	   
        	   if(formCheck){
        		   $("#blogEditForm").submit();
        	   }					
			});			
			
			
			
			//$('#myModal').modal('hide');

			//$('#myModal').modal('toogle');

			//$('#myModal').on('hidden', function () { });
		}
	}
}();
