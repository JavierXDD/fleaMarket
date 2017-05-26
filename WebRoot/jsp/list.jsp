<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

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

	<link href="<%=path%>/media/css/font-awesome.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=path%>/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES --> 

	<link rel="stylesheet" type="text/css" href="<%=path%>/media/css/datepicker.css" />

	<link href="<%=path%>/media/css/jquery.fancybox.css" rel="stylesheet" />

	<link href="<%=path%>/media/css/search.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=path%>/media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed page-full-width">

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
                    <!-- END TOP NAVIGATION MENU -->

                </div>

            </div>

            <!-- END TOP NAVIGATION BAR -->

        </div>

	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->   

	<div class="page-container row-fluid">

		<!-- BEGIN PAGE -->

		<div class="page-content">

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div id="portlet-config" class="modal hide">

				<div class="modal-header">

					<button data-dismiss="modal" class="close" type="button"></button>

					<h3>portlet Settings</h3>

				</div>

				<div class="modal-body">

					<p>Here will be a configuration form</p>

				</div>

			</div>

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							交易信息列表
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="<%=path%>/">首页</a> 
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="#">${chooseMenu.name }</a>
								<i class="icon-angle-right"></i>
							</li>

							<li><a href="#">交易信息列表</a></li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">

					<div class="tabbable tabbable-custom tabbable-full-width">

						<ul class="nav nav-tabs">
							<li class="active" id="allTypeTab"><a  href="<%=path%>/toListByType?menuId=${chooseMenu.id }&pc=1">全部</a></li>															
							
							<c:forEach items="${typeList}" var="type" varStatus="status">
								<c:choose>
									<c:when test="${type.id eq chooseType.id}">
										<li class="active" id="activeTab"><a  href="<%=path%>/getListByType?typeId=${type.id}&pc=1">${type.name }</a></li>									
									</c:when>
									<c:otherwise>
										<li><a  href="<%=path%>/getListByType?typeId=${type.id}&pc=1">${type.name }</a></li>									
									</c:otherwise>
								</c:choose>
							</c:forEach>				
						</ul>

						<div class="tab-content">
						<c:if test="${not empty blogList.beanList}">
							<div id="tab_1_2" class="tab-pane active">
							
								<div class="row-fluid search-forms search-default">
									<h4>&nbsp;在文理小跳蚤，大家努力让买卖闲置物品这件事变得更轻松！</h4>
								</div>
								
								<c:forEach items="${blogList.beanList}" var="blogList" varStatus="status">
									<div class="row-fluid portfolio-block">
										<div class="span4 portfolio-text">
											<img style="height:80px;width:80px" src="<%=path%>/${blogList.surfacePicUrl}" alt="交易信息封面" />
											<div class="portfolio-text-info">
												<h4>${blogList.title }</h4>
												<p>${blogList.name }</p>
											</div>
										</div>
										<div class="span6">
											<div class="portfolio-info span2">
												售价
												<span>${blogList.price}</span>
											</div>
											<div class="portfolio-info span3">
												成色
												<span>${blogList.quality}</span>
											</div>
											<div class="portfolio-info span7">
												更新时间
												<span>${blogList.modifyTime}</span>
											</div>
										</div>
										<div class="span2 portfolio-btn">
											<a href="<%=path %>/blog/toMyBlogDetail?id=${blogList.id}" class="btn bigicn-only"><span>查看详情</span></a>                        
										</div>
									</div>								
								</c:forEach>

								<div class="pagination pagination-right">
									<c:if test="${empty chooseType}">
										<ul>	
											<%-- 如果总页数大于1页，那么《显示出来！ --%>									
											<c:if test="${blogList.pc > 1 }">						
												<li>
							            			  <a href="<%=path%>/toListByType?menuId=${chooseMenu.id}&pc=${blogList.pc-1}" aria-label="Previous">
							            			    <span aria-hidden="true">&laquo;</span>
							              			  <span class="sr-only">上一页</span>
							             			 </a>
							           			 </li>
											</c:if>
											<c:choose>
												<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
												<c:when test="${blogList.tp <= 10 }">
													<c:set var="begin" value="1" />
													<c:set var="end" value="${blogList.tp }" />
												</c:when>
												<c:otherwise>
													<%-- 当总页数>10时，通过公式计算出begin和end --%>
													<c:set var="begin" value="${blogList.pc-5 }" />
													<c:set var="end" value="${blogList.pc+4 }" />
													<%-- 头溢出 --%>
													<c:if test="${begin < 1 }">
														<c:set var="begin" value="1" />
														<c:set var="end" value="10" />
													</c:if>
													<%-- 尾溢出 --%>
													<c:if test="${end > blogList.tp }">
														<c:set var="begin" value="${blogList.tp - 9 }" />
														<c:set var="end" value="${blogList.tp }" />
													</c:if>
												</c:otherwise>
											</c:choose>
	           								<%-- 循环遍历页码列表 --%>
											<c:forEach var="i" begin="${begin }" end="${end }">
												<c:choose>
													<c:when test="${i eq blogList.pc }">
														 <li><a>${i }</a></li>
													</c:when>
													<c:otherwise>
													 <li><a href="<%=path%>/toListByType?menuId=${chooseMenu.id}&pc=${i}">${i }</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>        
								            <c:if test="${blogList.pc < blogList.tp }">															
												<li>
						             			 <a href="<%=path%>/toListByType?menuId=${chooseMenu.id}&pc=${blogList.pc+1}" aria-label="Next">
						              			  <span aria-hidden="true">&raquo;</span>
						              			  <span class="sr-only">下一页</span>
						             			 </a>
						           				 </li>
											</c:if> 										
										</ul>
									</c:if>
									
									<c:if test="${not empty chooseType}">
										<ul>	
											<%-- 如果总页数大于1页，那么《显示出来！ --%>									
											<c:if test="${blogList.pc > 1 }">						
												<li>
							            			  <a href="<%=path%>/getListByType?typeId=${chooseType.id}&pc=${blogList.pc-1}" aria-label="Previous">
							            			    <span aria-hidden="true">&laquo;</span>
							              			  <span class="sr-only">上一页</span>
							             			 </a>
							           			 </li>
											</c:if>
											<c:choose>
												<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
												<c:when test="${blogList.tp <= 10 }">
													<c:set var="begin" value="1" />
													<c:set var="end" value="${blogList.tp }" />
												</c:when>
												<c:otherwise>
													<%-- 当总页数>10时，通过公式计算出begin和end --%>
													<c:set var="begin" value="${blogList.pc-5 }" />
													<c:set var="end" value="${blogList.pc+4 }" />
													<%-- 头溢出 --%>
													<c:if test="${begin < 1 }">
														<c:set var="begin" value="1" />
														<c:set var="end" value="10" />
													</c:if>
													<%-- 尾溢出 --%>
													<c:if test="${end > blogList.tp }">
														<c:set var="begin" value="${blogList.tp - 9 }" />
														<c:set var="end" value="${blogList.tp }" />
													</c:if>
												</c:otherwise>
											</c:choose>
	           								<%-- 循环遍历页码列表 --%>
											<c:forEach var="i" begin="${begin }" end="${end }">
												<c:choose>
													<c:when test="${i eq blogList.pc }">
														 <li><a>${i }</a></li>
													</c:when>
													<c:otherwise>
													 <li><a href="<%=path%>/getListByType?typeId=${chooseType.id}&pc=${i}">${i }</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>        
								            <c:if test="${blogList.pc < blogList.tp }">															
												<li>
						             			 <a href="<%=path%>/getListByType?typeId=${chooseType.id}&pc=${blogList.pc+1}" aria-label="Next">
						              			  <span aria-hidden="true">&raquo;</span>
						              			  <span class="sr-only">下一页</span>
						             			 </a>
						           				 </li>
											</c:if> 										
										</ul>
									</c:if>									
								</div>
							</div>
							</c:if>
							<c:if test="${empty blogList.beanList}">
								<div class="row-fluid search-forms search-default">
									<h4>&nbsp;抱歉，暂无交易信息。</h4>
								</div>							
							</c:if>
						</div>
					</div>
					<!--end tabbable-->           
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

	<script type="text/javascript" src="<%=path%>/media/js/bootstrap-datepicker.js"></script>

	<script src="<%=path%>/media/js/jquery.fancybox.pack.js"></script>

	<script src="<%=path%>/media/js/app.js"></script>

	<script src="<%=path%>/media/js/search.js"></script>      
	<script type="text/javascript" src="<%=path%>/media/js/welcome/list.js"></script>
	<script>

		jQuery(document).ready(function() {    

		   App.init();

		   Search.init();
		   
		   List.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>