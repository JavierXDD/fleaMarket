//板块配图
function menuPic(){
	var ue = UE.getEditor('UEcontent');
	$("#menuPic_menuId").val($("#menuId").val());
	$("#menuPic_name").val($("#name").val());
	$("#menuPic_url").val($("#url").val());
	$("#menuPic_menuOrder").val($("#menuOrder").val());
	$("#menuPic_isUse").val($("#isUse").val());
	$("#menuPic_isShow").val($("#isShow").val());
	$("#menuPic_text").val(ue.getContentTxt());
	$("#menuPic_thumbnailUrl").val($("#thumbnailUrl").val());
	$('#menuPicModal').modal('show');
	
};

var MenuEdit = function(){
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
	return{
		init:function(){
			UE.getEditor('UEcontent');
			$("#menuPic_submit").click(function(){
				if($("#menuPic").val() == ""){
					$("#menuPic_help").html("请选择图片文件！");
				}else{
					$("#menuPic_help").html("");
					$("#menuPicForm").submit();
				}

			});
			//$('#myModal').modal('hide');

			//$('#myModal').modal('toogle');

			//$('#myModal').on('hidden', function () { });
		}
	}
}();
