<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>文理小跳蚤 </title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="<%=path%>/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=path%>/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES --> 

	<link href="<%=path%>/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />

	<link href="<%=path%>/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

	<link href="<%=path%>/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

	<link rel="stylesheet" type="text/css" href="<%=path%>/media/css/multi-select-metro.css" />
	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=path%>/media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		 <div class="navbar-inner">

                <div class="container">

                    <!-- BEGIN LOGO -->

                    <a class="brand" href="<%=path %>/"> <img src="<%=path%>/media/image/logo.png" alt="logo" /> </a>

                    <!-- END LOGO -->

                    <!-- BEGIN HORIZANTAL MENU -->

                    <!-- END HORIZANTAL MENU -->

                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->

                    <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse"> <img src="<%=path%>/media/image/menu-toggler.png" alt="" /> </a>

                    <!-- END RESPONSIVE MENU TOGGLER -->

                    <!-- BEGIN TOP NAVIGATION MENU -->

                    <ul class="nav pull-right">



                        <!-- BEGIN USER LOGIN DROPDOWN -->
                        <c:choose>
                        	<c:when test="${empty sessionScope.isLogin}">
	              				<li class="dropdown user">
	              				    <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img alt="" src="<%=path%>/media/image/avatar1_small.jpg" /> <span class="username">游客您好</span> <i class="icon-angle-down"></i> </a> 
		                        	<ul class="dropdown-menu">
		                        		<li>
		                                    <a href="<%=path%>/user/toLogin"><i class="icon-user"></i> 登录/注册</a>
		                                </li>
		                                <li>
		                                    <a href="<%=path%>/toHelp"><i class="icon-question-sign"></i> 帮助</a>
		                                </li>
		                        	</ul>
		                        </li>          	
                        	</c:when>
                        	<c:otherwise>
								<li class="dropdown user">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img alt="" src="<%=path%>/media/image/avatar1_small.jpg" /> <span class="username">${isLogin.nickname }</span> <i class="icon-angle-down"></i> </a>
		                            <ul class="dropdown-menu">		
		                                <li>
		                                    <a href="<%=path%>/user/toPersonInfo?id=${isLogin.id}"><i class="icon-user"></i>个人中心</a>
		                                </li>		
		                                <li>
		                                    <a href="<%=path%>/blog/toMyBlog?id=${isLogin.id}"><i class="icon-stackexchange"></i> 我的发布</a>
		                                </li>		
		                                <li>
		                                    <a href="<%=path%>/message/toMyMessageList?id=${isLogin.id}"><i class="icon-comment"></i> 我的评论</a>
		                                </li>
		                                <li>
		                                    <a href="<%=path%>/message/toReplyMyMessageList?id=${isLogin.id}"><i class="icon-envelope"></i> 回复我的</a>
		                                </li>		
		
		                                <li class="divider"></li>
										<c:if test="${isLogin.isManager eq 1 || isLogin.isManager eq 2}">
			                                <li>
			                                    <a href="<%=path%>/toManagerIndex"><i class="icon-lock"></i> 后台管理</a>
			                                </li>																				
										</c:if>		
		                                <li>
		                                    <a href="<%=path%>/user/loginOut"><i class="icon-key"></i> 注销</a>
		                                </li>		
		           		                <li>
		                                    <a href="<%=path%>/toHelp"><i class="icon-question-sign"></i> 帮助</a>
		                                </li>  
		                            </ul>
                        		</li>                        	
                        	</c:otherwise>
                        </c:choose>
                        <!-- END USER LOGIN DROPDOWN -->

                    </ul>

                    <!-- END TOP NAVIGATION MENU -->

                </div>

            </div>

		<!-- END TOP NAVIGATION BAR -->

	</div>

	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->

	<div class="page-container">

		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">

			<!-- BEGIN SIDEBAR MENU -->        
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>

				<li class="start  ">
					<a href="<%=path%>/user/toPersonInfo?id=${isLogin.id}">
					<i class="icon-user"></i> 
					<span class="title">我的信息</span>
					<span class="selected"></span>
					</a>
				</li>
				<li class="start  active">
					<a href="<%=path%>/blog/toMyBlog?id=${isLogin.id}">
					<i class="icon-stackexchange"></i> 
					<span class="title">我的发布</span>
					<span class="selected"></span>					
					</a>
				</li>
				<li class="start">
					<a href="<%=path%>/message/toMyMessageList?id=${isLogin.id}">
					<i class="icon-comment"></i> 
					<span class="title">我的评论</span>				
					</a>
				</li>
				<li class="start">
					<a href="<%=path%>/message/toReplyMyMessageList?id=${isLogin.id}">
					<i class="icon-envelope"></i> 
					<span class="title">回复我的</span>				
					</a>
				</li>													
			</ul>
			<!-- END SIDEBAR MENU -->

		</div>
		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE -->

		<div class="page-content">

			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->

				<div class="row-fluid">

					<div class="span12">						

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

							我的发布

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="<%=path%>/">首页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="#">个人中心</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">我的发布</a></li>

						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="portlet box green">

					<div class="portlet-title">
						<div class="caption"><i class="icon-reorder"></i>交易信息编辑</div>
					</div>

					<div class="portlet-body form">

						<!-- BEGIN FORM-->

						<form action="<%=path %>/blog/saveOrUpdateMyBlog" id="blogEditForm" method="post"  class="form-horizontal">
						<input type="hidden" id="blogId" name="id" value="${blog.id }">
						<input type="hidden" id="userId" name="userId" value="${isLogin.id }">
						<input type="hidden" id="userName" name="userName" value="${isLogin.name }">
							<h3 class="form-section">发布交易信息</h3>							

							<div class="row-fluid">

								<div class="span6 ">
									<div class="control-group">

										<label class="control-label"><i style="color:red">* </i>标题</label>

										<div class="controls">

											<input type="text" class="m-wrap span12" id="title" name="title" placeholder="${blog.title }" value="${blog.title}">

											<span class="help-block" style="color:red;" style="color:red;" id="title_help"></span>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">

										<label class="control-label"><i style="color:red">* </i>品名</label>

										<div class="controls">

											<input type="text" class="m-wrap span12" id="name" name="name" placeholder="${blog.name}" value="${blog.name}">

											<span class="help-block" style="color:red;" style="color:red;" id="name_help"></span>
										</div>
									</div>
								</div>
																
							</div>

							<!--/row-->

							<div class="row-fluid">

								<div class="span6 ">

									<div class="control-group">

										<label class="control-label"><i style="color:red">* </i>价格</label>

										<div class="controls">
											<input type="text" class="m-wrap span12" id="price" name="price" placeholder="${blog.price }" value="${blog.price }">
											<span class="help-block" style="color:red;" id="price_help"></span>
										</div>

									</div>

								</div>

								<!--/span-->

								<div class="span6 ">

									<div class="control-group">

										<label class="control-label"><i style="color:red">* </i>成色</label>

										<div class="controls">
											<input type="text" class="m-wrap span12" id="quality" name="quality" placeholder="例：九成新" value="${blog.quality }">										
											<span class="help-block" style="color:red;" id="quality_help"></span>
										</div>
									</div>

								</div>

								<!--/span-->

							</div>

							<!--/row-->        

							<div class="row-fluid">
								
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label"><i style="color:red">* </i>联系人电话</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="userTelephone" name="userTelephone" placeholder="${blog.userTelephone }" value="${blog.userTelephone }">										
											<span class="help-block" style="color:red;" id="userTelephone_help"></span>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label"><i style="color:red">* </i>联系人电子邮箱</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="userEmail" name="userEmail" placeholder="${blog.userEmail }" value="${blog.userEmail }">										
											<span class="help-block" style="color:red;" id="userEmail_help"></span>
										</div>
									</div>
								</div>								
							
							</div>
							
							<div class="row-fluid hidden">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">发布时间</label>
										<div class="controls">
											<input type="text" readonly="readonly" class="m-wrap span12" id="createTime" name="createTime" placeholder="${blog.createTime }" value="${blog.createTime }">										
											<span class="help-block" style="color:red;" id="menuOrder_help"></span>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">修改时间</label>
										<div class="controls">
											<input type="text" readonly="readonly" class="m-wrap span12" id="modifyTime" name="modifyTime" placeholder="${blog.modifyTime }" value="${blog.modifyTime }">										
											<span class="help-block" style="color:red;" id="menuOrder_help"></span>
										</div>
									</div>
								</div>															
							</div>														
								
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">类别</label>
										<div class="controls">
											<select name="menuId" id="menuId" class="m-wrap span12">
												<c:forEach items="${menuList}" var="menu">
													<option value="${menu.id}">${menu.name }</option>
													<c:if test="${menu.id eq blog.menuId}">
														<option value="${menu.id}" selected="selected">${menu.name }</option>
													</c:if>													
												</c:forEach>
											</select>	
											<span class="help-block" style="color:red;" id="menuId_help"></span>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">细类</label>
										<div class="controls">
											<select name="typeId" id="typeId" class="m-wrap span12" >
											</select>
											<span class="help-block" style="color:red;" id="type_help"></span>
										</div>
									</div>
								</div>															
							</div>
							
							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<label class="control-label">标签</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="tags" name="tags" placeholder="例：手机，数码" value="${blog.tags }">										
											<span class="help-block" style="color:red;" id="tags_help"></span>
										</div>
									</div>
								</div>														
							</div>

							<!--/span-->
							<div class="row-fluid">
								<div class="span12 ">		
									<div class="control-group">		
										<label class="control-label"><i style="color:red">* </i>详细内容</label>		
										<div class="controls">	
										<script id="UEcontent" name="text" id="text" type="text/plain" >${blog.text }</script>																
										<span class="help-block" style="color:red;" id="text_help"></span>
										</div>
									</div>		
								</div>		
		
								<!--/span-->
							</div>
							
							<h3 class="form-section">其他相关</h3>
							<div class="row-fluid">
								<div class="span12 ">		
									<div class="control-group">		
										<label class="control-label"><i style="color:red">* </i>审核状况</label>		
										<div class="controls">	
										<c:if test="${blog.isUse eq 0 }">
											<input type="text" class="m-wrap span4" readonly="readonly"	value="未审核"/>
											<input type="hidden" id="isUse" name="isUse" value="0"/>
										</c:if>
										<c:if test="${blog.isUse eq 1 }">
											<input type="text" class="m-wrap span4" readonly="readonly"	value="审核通过"/> 
											<input type="hidden" id="isUse" name="isUse" value="1"/>
										</c:if>
										<c:if test="${blog.isUse eq 2 }">
											<input type="text" class="m-wrap span4" readonly="readonly"	value="审核未通过"/> 
											<input type="hidden" id="isUse" name="isUse" value="2"/>
										</c:if>
										</div>										
									</div>		
								</div>
							</div>		
							
							<div class="row-fluid">
								<div class="span12 ">
									<div class="control-group">
										<label class="control-label">封面图:</label>
										<div class="controls">
											<div class="fileupload fileupload-new" data-provides="fileupload">

												<div class="fileupload-new thumbnail" style="width: 250px; ">

													<img src="<%=path%>/${blog.surfacePicUrl }" alt="" />

												</div><br>
												<input type="hidden" name="surfacePicUrl" id="surfacePicUrl" value="${blog.surfacePicUrl }"/>

												
												<a style="width: 210px;" href="javascript:void(0);" onclick="upSurfacePic()" class="btn green btn-block">上传图片<i class=" m-icon-white"></i></a>
												
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="form-actions">
								<button type="button" id="save_myBlog" class="btn blue"><i class="icon-ok"></i>保存</button>
								<button type="button" class="btn" onclick="history.go(-1)">取消</button>
							</div>

						</form>

						<!-- END FORM-->                

					</div>


					<!-- 模态框（） -->
					<div class="modal fade" id="surfacePicModal" tabindex="-1" role="dialog" aria-labelledby="surfacePicLabel" aria-hidden="true">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					                <h4 class="modal-title" >上传博客封面</h4>
					            </div>
					            <div class="modal-body">
									<form id="surfacePicForm"  action="<%=path%>/blog/upSurfacePic" method="post" class="form-horizontal" enctype="multipart/form-data" >
										<div class="row-fluid">
											<div class="control-group span10" >
												<label class="control-label">上传交易信息封面</label>		
													
												<div class="controls">											 	
													<input name="surfacePic" id="surfacePic"  class=" btn btn-theme" type="file" accept = 'image/*'>
													<input name="surfacePic_id" type="hidden" id="surfacePic_id" >
													<input name="surfacePic_title" type="hidden" id="surfacePic_title" >
													<input name="surfacePic_name" type="hidden" id="surfacePic_name" >
													<input name="surfacePic_price" type="hidden" id="surfacePic_price" >
													<input name="surfacePic_quality" type="hidden" id="surfacePic_quality" >													
													<input name="surfacePic_userTelephone" type="hidden" id="surfacePic_userTelephone" >
													<input name="surfacePic_userEmail" type="hidden" id="surfacePic_userEmail" >													
													<input name="surfacePic_createTime" type="hidden" id="surfacePic_createTime" >													
													<input name="surfacePic_modifyTime" type="hidden" id="surfacePic_modifyTime" >
													<input name="surfacePic_text" type="hidden" id="surfacePic_text" >
													<input name="surfacePic_userId" type="hidden" id="surfacePic_userId" >
													<input name="surfacePic_userName" type="hidden" id="surfacePic_userName" >
													<input name="surfacePic_menuId" type="hidden" id="surfacePic_menuId" >
													<input name="surfacePic_menuName" type="hidden" id="surfacePic_menuName" >
													<input name="surfacePic_typeId" type="hidden" id="surfacePic_typeId" >
													<input name="surfacePic_typeName" type="hidden" id="surfacePic_typeName" >
													<input name="surfacePic_tags" type="hidden" id="surfacePic_tags" >
													<input name="surfacePic_isUse" type="hidden" id="surfacePic_isUse" >
													
													<span class="help-block" style="color:red;" style="color:red;" id="surfacePic_help"></span>	
												</div>
											</div>									
										</div>
									</form>
								</div>
					            <div class="modal-footer">            
					                <button type="button " id="surfacePic_submit" class="btn blue btn-primary">提交</button>
					            	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            	
					            </div>
					        </div><!-- /.modal-content -->				        
					    </div>
					</div>
					<!-- /.modal -->
					
				</div>
				<!-- END PAGE CONTENT-->

			</div>

			<!-- END PAGE CONTAINER--> 

		</div>

		<!-- END PAGE -->    

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			2017 &copy; 文理小跳蚤 by xjw 

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=path%>/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="<%=path%>/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="<%=path%>/media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=path%>/media/js/excanvas.min.js"></script>

	<script src="<%=path%>/media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="<%=path%>/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="<%=path%>/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="<%=path%>/media/js/jquery.vmap.js" type="text/javascript"></script>   

	<script src="<%=path%>/media/js/jquery.vmap.russia.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.vmap.world.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.vmap.europe.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.vmap.germany.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.vmap.usa.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>  

	<script src="<%=path%>/media/js/jquery.flot.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.flot.resize.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.pulsate.min.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/date.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/daterangepicker.js" type="text/javascript"></script>     

	<script src="<%=path%>/media/js/jquery.gritter.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/fullcalendar.min.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/jquery.sparkline.min.js" type="text/javascript"></script>  

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=path%>/media/js/app.js" type="text/javascript"></script>

	<script src="<%=path%>/media/js/index.js" type="text/javascript"></script>        

	<!-- END PAGE LEVEL SCRIPTS -->  
	<script src="<%=path%>/media/js/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/media/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/media/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/media/ueditor/lang/zh-cn/zh-cn.js"></script>
 	<script type="text/javascript" charset="utf-8" src="<%=path%>/media/js/personInfo/myBlogEdit.js"></script>
	<script>

		jQuery(document).ready(function() { 
			
		   //var ue = UE.getEditor('UEcontent');
			
		   App.init(); // initlayout and core plugins
		   MyBlogEdit.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>