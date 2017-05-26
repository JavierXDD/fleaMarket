	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var clickButton = true;

function forbiddenMenu(id){
	var data = "menuId="+id;
	var pid = $("#parentsId").val();

	$.ajax({
		url: basePath+"/menu/forbiddenMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/menu/toSonMenuList?pId="+pid;
        	}else{
        		alert("error!");
        	}
        }
	})
}
//启用
function usingMenu(menuId){

		
	var data = "menuId="+menuId;
	var pid = $("#parentsId").val();

	$.ajax({
		url: basePath+"/menu/usingMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/menu/toSonMenuList?pId="+pid;
        	}else{
        		alert("error!");
        	}
        }
	})
	
};	
//编辑菜单点击事件
function editSonMenu(id,obj){
	if(clickButton){
		var tr = $(obj).parent().parent();
		var nameTD = $(tr.children().get(1));
		var urlTD = $(tr.children().get(2));
		var isUseTD = $(tr.children().get(3));
		var optTD = $(tr.children().get(4));
		
		nameTD.html("<input type='text' id='name' name='name' value='"+ nameTD.text() +"' /> " +
				"<input type='hidden' id='menuId' name='id' value='"+ id +"' />");
		urlTD.html("<input type='text' id='url' style='width:90%' name='url' value='"+ urlTD.text() +"' /> ");	
		if(isUseTD.text() == "启用"){
			isUseTD.html("<select name='isUse' id='isUse'><option value='0' selected='selected'>启用</option> <option value='1'>禁用</option></select>");
		}else{
			isUseTD.html("<select name='isUse' id='isUse'><option value='0'>启用</option> <option value='1' selected='selected'>禁用</option></select>");	
		}
		optTD.html("<a href='javascript:void(0);' onclick='updateSonMenu(this)' class='btn mini blue icn-only' title='保存更改'><i class='halflings-icon white ok	'></i></a>&nbsp; "
				+"<a href='"+basePath+"/menu/toSonMenuList?pId="+$("#parentsId").val()+"' class='btn mini red icn-only' title='取消更改'><i class='halflings-icon white remove'></i></a></td>");	
		clickButton=false;
	}else{
		$("#alert").attr("class","alert alert-error");
	}
};

function updateSonMenu(obj){
	
	var parentsId = $("#parentsId").serialize();
	var name = $("#name").serialize();
	var isUse = $("#isUse").serialize();
	var url = $("#url").serialize();
	var id = $("#menuId").serialize();
	
	var data = parentsId + "&" + name + "&" + isUse + "&" + url + "&" + id ;
	$.ajax({
		url: basePath+"/menu/updateSonMenu",  
    	type: "post",                         
        data:data,
        success:function(result){
        	if(result == "success"){
        		location.href = basePath+"/menu/toSonMenuList?pId="+$("#parentsId").val()+"";
        	}else{
        		$("#alert").attr("class","alert alert-error");
        	}
        }
	})	
}

function addSonMenu(){
	if(clickButton){
		var order = $("#sonMenuList tr").length;
		$("#tbody").append("<tr>"
				+"<td>"+order+"</td>"
				+"<td><input type='text' id='name' name='name'/></td>"
				+"<td><input type='text' id='url' style='width:90%' name='url'/></td>"
				+"<td><select name='isUse' id='isUse'><option value='0' selected='selected'>启用</option> <option value='1' >禁用</option></select></td>"
				+"<td><a href='javascript:void(0);' onclick='updateSonMenu(this)' class='btn mini blue icn-only' title='保存更改'><i class='halflings-icon white ok'></i></a>&nbsp;" 
				+"<a href='"+basePath+"/menu/toSonMenuList?pId="+$("#parentsId").val()+"' class='btn mini red icn-only' title='取消更改'><i class='halflings-icon white remove'></i></a></td>"
				+"</tr>");
		clickButton=false;		
	}else{
		$("#alert").attr("class","alert alert-error");
	}

}



var SonMenuList = function(){
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
	
	return{
		init:function(){
			

			//location.href = basePath+"/menu/toMenuList";
		}
	}
}();
