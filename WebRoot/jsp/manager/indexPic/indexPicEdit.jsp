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

				<li class="start ">
					<a href="<%=path%>/toManagerIndex">
						<i class="icon-home"></i> 
						<span class="title">首页</span>
						
					</a>
				</li>

				<li class="">
					<a href="javascript:;">
						<i class="icon-user"></i> 
						<span class="title">用户管理</span>
						<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="<%=path%>/user/toAdminList">
							管理员管理</a>
						</li>
						<li >
							<a href="<%=path%>/user/toUserList">
							用户管理</a>
						</li>						
					</ul>
				</li>

				<li class="active ">
					<a href="javascript:;">
						<i class="icon-cogs"></i> 
						<span class="title">网站管理</span>
						<span class="arrow "></span>
						<span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li class="active ">
							<a href="<%=path%>/indexPic/toIndexPicList">
							首页管理</a>
						</li>
						<li >
							<a href="<%=path%>/menu/toMenuList">
							板块管理</a>
						</li>
						<li >
							<a href="<%=path%>/menu/toSonMenuIndex">
							细类管理</a>
						</li>
					</ul>
				</li>

				<li class="">
					<a href="javascript:;">
						<i class="icon-table"></i> 
						<span class="title">交易信息管理</span>
						<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="<%=path%>/blog/toUncheckBlogList">
							交易信息审核</a>
						</li>
						<li >
							<a href="<%=path%>/blog/toManagerBlogList">
							交易信息管理</a>
						</li>
						<li >
							<a href="<%=path%>/message/toManagerMessageList">
							评论管理</a>
						</li>						
					</ul>
				</li>
				<li class="">
					<a href="javascript:;">
						<i class="icon-bar-chart"></i> 
						<span class="title">统计分析</span>
						<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="<%=path%>/toUserAnalysis">
							用户信息统计</a>
						</li>
						<li >
							<a href="<%=path%>/toBlogAnalysis">
							交易信息统计</a>
						</li>
						<li >
							<a href="<%=path%>/toMessageAnalysis">
							评论信息统计</a>
						</li>											
					</ul>
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

							首页板块${msg }页 

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="<%=path%>/toManagerIndex">首页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="<%=path%>/indexPic/toIndexPicList">网站管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="<%=path%>/indexPic/toIndexPicList">首页管理</a></li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>
				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="portlet box green">

					<div class="portlet-title">
						<div class="caption"><i class="icon-reorder"></i>首页板块信息编辑</div>
					</div>

					<div class="portlet-body form">

						<!-- BEGIN FORM-->

						<form action="<%=path %>/indexPic/saveOrUpdateIndexPic" id="indexPicEditForm" method="post"  class="form-horizontal">
						<input type="hidden" id="indexPicId" name="id" value="${indexPic.id }">
						
							<h3 class="form-section">基本信息</h3>							

							<div class="row-fluid">

								<div class="span6 ">
									<div class="control-group">

										<label class="control-label"><i style="color:red">* </i>标题</label>

										<div class="controls">

											<input type="text" class="m-wrap span12" id="title" name="title" placeholder="${indexPic.title }" value="${indexPic.title}">

											<span class="help-block" style="color:red;" style="color:red;" id="title_help"></span>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">

										<label class="control-label"><i style="color:red">* </i>二级标题</label>

										<div class="controls">

											<input type="text" class="m-wrap span12" id="secondTitle" name="secondTitle" placeholder="${indexPic.secondTitle}" value="${indexPic.secondTitle}">

											<span class="help-block" style="color:red;" style="color:red;" id="secondTitle_help"></span>
										</div>
									</div>
								</div>
																
							</div>

							<!--/row-->

							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label"><i style="color:red">* </i>按钮名</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="buttonName" name="buttonName" placeholder="${indexPic.buttonName }" value="${indexPic.buttonName }">
											<span class="help-block" style="color:red;" id="buttonName_help"></span>
										</div>
									</div>
								</div>
								<!--/span-->
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">按钮链接</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="buttonLink" name="buttonLink" placeholder="${indexPic.buttonLink }" value="${indexPic.buttonLink }">										
											<span class="help-block" style="color:red;" id="menuOrder_help"></span>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->        
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">图片链接</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="picLink" name="picLink" placeholder="${indexPic.picLink }" value="${indexPic.picLink }">										
											<span class="help-block" style="color:red;" id="menuOrder_help"></span>
										</div>
									</div>
								</div>							
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label"><i style="color:red">* </i>是否启用</label>
										<div class="controls">
											<select class="m-wrap span12" id="isUse" name="isUse">
												<c:choose>
													<c:when test="${indexPic.isUse eq 0 }">
														<option value="0" selected = "selected">启用</option>
														<option value="1">禁用</option>																								
													</c:when>											
													<c:when test="${indexPic.isUse eq 1 }">
														<option value="0">启用</option>
														<option value="1" selected = "selected">禁用</option>																								
													</c:when>
													<c:otherwise>
														<option value="0">启用</option>
														<option value="1">禁用</option>												
													</c:otherwise>																							
												</c:choose>
											</select>
										</div>																		
									</div>
								</div>					
							</div>							
								
							<!--/span-->
							<div class="row-fluid">
								<div class="span12 ">		
									<div class="control-group">		
										<label class="control-label"><i style="color:red">* </i>板块内容</label>		
										<div class="controls">	
										<script id="UEcontent" name="text" id="text" type="text/plain" >${indexPic.text }</script>																

										</div>
									</div>		
								</div>				
								<!--/span-->
							</div>
							
							<h3 class="form-section">其他相关</h3>

							<div class="row-fluid">
								<div class="span12 ">
									<div class="control-group">
										<label class="control-label">板块配图:</label>
										<div class="controls">
											<div class="fileupload fileupload-new" data-provides="fileupload">

												<div class="fileupload-new thumbnail" style="width: 250px; ">

													<img src="<%=path%>/${indexPic.picUrl }" alt="" />

												</div><br>
												<input type="hidden" name="picUrl" id="picUrl" value="${indexPic.picUrl }"/>
												<c:if test="${empty indexPic.id}">
													<input type="hidden" name="menuOrder" id="menuOrder" value="${maxOrder}"/>																								
												</c:if>
												<c:if test="${not empty indexPic.id}">
													<input type="hidden" name="menuOrder" id="menuOrder" value="${indexPic.menuOrder }"/>												
												</c:if>
												
												<a style="width: 210px;" href="javascript:void(0);" onclick="upIndexPic()" class="btn green btn-block">上传图片<i class=" m-icon-white"></i></a>
												
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="form-actions">
								<button type="submit" id="save_indexPic" class="btn blue"><i class="icon-ok"></i>保存</button>
								<button type="button" class="btn" onclick="history.go(-1)">取消</button>
							</div>

						</form>

						<!-- END FORM-->                

					</div>


					<!-- 模态框（禁用用户） -->
					<div class="modal fade" id="indexPicImageModal" tabindex="-1" role="dialog" aria-labelledby="menuPicLabel" aria-hidden="true">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					                <h4 class="modal-title" id="forbiddenLabel">上传板块配图</h4>
					            </div>
					            <div class="modal-body">
									<form id="indexPicImageForm"  action="<%=path%>/indexPic/upIndexPic" method="post" class="form-horizontal" enctype="multipart/form-data" >
										<div class="row-fluid">
											<div class="control-group span10" >
												<label class="control-label">上传板块配图</label>		
													
												<div class="controls">											 	
													<input name="indexPicImage" id="indexPicImage"  class=" btn btn-theme" type="file" accept = 'image/*'>
													<input name="indexPic_id" type="hidden" id="indexPic_id" >
													<input name="indexPic_title" type="hidden" id="indexPic_title" >
													<input name="indexPic_secondTitle" type="hidden" id="indexPic_secondTitle" >
													<input name="indexPic_buttonName" type="hidden" id="indexPic_buttonName" >
													<input name="indexPic_buttonLink" type="hidden" id="indexPic_buttonLink" >
													
													<input name="indexPic_picLink" type="hidden" id="indexPic_picLink" >
													<input name="indexPic_menuOrder" type="hidden" id="indexPic_menuOrder" >													
													<input name="indexPic_isUse" type="hidden" id="indexPic_isUse" >													
													<input name="indexPic_text" type="hidden" id="menuPic_text" >
													<span class="help-block" style="color:red;" style="color:red;" id="indexPicImage_help"></span>	
												</div>
											</div>									
										</div>
									</form>
								</div>
					            <div class="modal-footer">            
					                <button type="button " id="indexPicImage_submit" class="btn blue btn-primary">提交</button>
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
 	<script type="text/javascript" charset="utf-8" src="<%=path%>/media/js/manager/indexPic/indexPicEdit.js"></script>
	<script>

		jQuery(document).ready(function() { 
			
		   //var ue = UE.getEditor('UEcontent');
			
		   App.init(); // initlayout and core plugins
		   IndexPicEdit.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>