//板块配图
function upIndexPic(){
	var ue = UE.getEditor('UEcontent');
	
	$("#indexPic_id").val($("#indexPicId").val());
	$("#indexPic_title").val($("#title").val());
	$("#indexPic_secondTitle").val($("#secondTitle").val());
	$("#indexPic_buttonName").val($("#buttonName").val());
	$("#indexPic_buttonLink").val($("#buttonLink").val());
	//$("#indexPic_picUrl").val($("#picUrl").val());
	$("#indexPic_picLink").val($("#picLink").val());
	$("#indexPic_menuOrder").val($("#menuOrder").val());
	$("#indexPic_isUse").val($("#isUse").val());
	$("#menuPic_text").val(ue.getContentTxt());


	
	$('#indexPicImageModal').modal('show');
	
};

var IndexPicEdit = function(){
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
	return{
		init:function(){
			UE.getEditor('UEcontent');
			$("#indexPicImage_submit").click(function(){
				if($("#indexPicImage").val() == ""){
					$("#indexPicImage_help").html("请选择图片文件！");
				}else{
					$("#indexPicImage_help").html("");
					$("#indexPicImageForm").submit();
				}

			});
			//$('#myModal').modal('hide');

			//$('#myModal').modal('toogle');

			//$('#myModal').on('hidden', function () { });
		}
	}
}();
