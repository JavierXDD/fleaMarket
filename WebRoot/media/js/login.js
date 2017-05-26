var Login = function () {
	
	var localObj = window.location;

	var contextPath = localObj.pathname.split("/")[1];

	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

	var server_context=basePath;
    

	
    return {
        //main function to initiate the module
        init: function () {
        	
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                nickname: {
	                    required: true
	                },
	                password: {
	                    required: true,	                    
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "请输入用户名"
	                },
	                password: {
	                    required: "请输入密码"
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   	            	
	            	$('.alert-error', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	
	            	if(slider.innerText == ">>验证成功!"){
	            		var data = $("#loginForm").serialize();
		            	$.ajax({
		    			    type: "POST",
		    			    url: basePath+"/user/login",
		    			    data: data,
		    			    success: function(result) {
		    			      if(result =="success") {
		    			    	  location.href = basePath+"/user/toIndexDoor"
		    			      }else if(result == "defeat"){
		    			    	  $('.alert-error2', $('.login-form')).hide();
		    			    	  $('.alert-error', $('.login-form')).show();

		    			    	  $('#label').unbind().next('#labelTip').text("拖动滑块验证").css({'color': '#fff'});
		    			    	  var slider = new SliderUnlock("#slider",{
		    							successLabelTip : "验证成功!"	
		    						},function(){
		    							
		    			        	});
		    			    	  slider.init();
		    			      }else if(result == "notUse"){
		    			    	  $('.alert-error', $('.login-form')).hide();
		    			    	  $('.alert-error2', $('.login-form')).show();

		    			    	  $('#label').unbind().next('#labelTip').text("拖动滑块验证").css({'color': '#fff'});
		    			    	  var slider = new SliderUnlock("#slider",{
		    							successLabelTip : "验证成功!"	
		    						},function(){
		    							
		    			        	});
		    			    	  slider.init();		    			    	  
		    			      }
		    			    }
		            	})
	            	}else{
	            		$("#labelTip").text("拖动滑块验证").css({'color': 'red'});
	            		
	            	}
	            	
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                    window.location.href = "#";
	                }
	                return false;
	            }
	        });

	        
	        $('.register-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                nickname: {
	                    required: true,
	                    remote:{
	                    	url: basePath+"/user/nicknameCan",  
	                    	type: "post",     
	                        dataType: "json",
	                        data:{
	                        	nickname:function(){
	                        		return $("#register_nickname").val();
	                        	}
	                        }

	                    }
                           
	                },
	                password: {
	                    required: true
	                },
	                rpassword: {
	                    equalTo: "#register_password"
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	                tnc: {
	                    required: true
	                }
	            },

	            messages: { // custom messages for radio buttons and checkboxes
	           
	            	nickname:{
	            		remote:"用户名已存在！"
	            	},
	                tnc: {
	                    required: "请阅读协议并同意"
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
	                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
	                } else {
	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	                }
	            },

	            submitHandler: function (form) {
	            	form.submit();
	            }
	        });

	        jQuery('#register-btn').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.register-form').show();
	        });

	        jQuery('#register-back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.register-form').hide();
	        });
        }

    };

}();