<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->

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

        <link href="<%=path%>/media/css/promo.css" rel="stylesheet" type="text/css"/>

        <link href="<%=path%>/media/css/animate.css" rel="stylesheet" type="text/css"/>

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
                        	    <input type="hidden" id="isLoginId" value="${isLogin.id}"/>
                        	
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

        <div class="page-container row-fluid">

            <!-- BEGIN PAGE -->

            <div class="page-content no-min-height">


                <!-- BEGIN PAGE CONTAINER-->

                <div class="container-fluid promo-page">

                    <!-- BEGIN PAGE CONTENT-->

                    <div class="row-fluid">

                        <div class="span12">

                            <div class="block-grey">

                                <div class="container">

                                    <div id="promo_carousel" class="carousel slide">

                                        <div class="carousel-inner">
											<c:forEach items="${indexPicList }" var="indexPic" varStatus="status">
												<c:choose>
													<c:when test="${status.count eq 1 }">
														<div class="active item">
			                                                <div class="row-fluid">
			                                                    <div class="span7 margin-bottom-20 margin-top-20 animated rotateInUpRight">
			                                                        <h1 style="color:#d84a38;">${indexPic.title }</h1>
			                                                        <h3>
			                                                        	${indexPic.secondTitle }
			                                                        </h3>
			                                                        <p>
																		${indexPic.text }
			                                                        </p>
			                                                        <br>
			                                                        <a href="<%=path%>/${indexPic.buttonLink }" class="btn red big xlarge">${indexPic.buttonName } <i class="m-icon-big-swapright m-icon-white"></i> </a>
			                                                    </div>
			                                                    <div class="span5 animated rotateInDownLeft">
			                                                        <a href="<%=path%>/${indexPic.picLink }"><img src="<%=path%>/${indexPic.picUrl }" alt=""></a>
			                                                    </div>
			                                                </div>
			                                            </div>	
													</c:when>
													<c:otherwise>
														<div class="item">
			                                                <div class="row-fluid">
			                                                	<div class="span5 animated rotateInDownLeft">
			                                                        <a href="<%=path%>/${indexPic.picLink }"><img src="<%=path%>/${indexPic.picUrl }" alt=""></a>
			                                                    </div>
			                                                    <div class="span7 margin-bottom-20 margin-top-20 animated rotateInUpRight">
			                                                        <h1 style="color:#d84a38;">${indexPic.title }</h1>
			                                                        <h3>
			                                                        	${indexPic.secondTitle }
			                                                        </h3>
			                                                        <p>
																		${indexPic.text }
			                                                        </p>
			                                                        <br>
			                                                        <a href="<%=path%>/${indexPic.buttonLink }" class="btn red big xlarge">${indexPic.buttonName } <i class="m-icon-big-swapright m-icon-white"></i> </a>
			                                                    </div>
			                                                </div>
			                                            </div>													
													</c:otherwise>				
												</c:choose>	                                      																				
											</c:forEach>                                           

                                        </div>

                                        <a class="carousel-control left" href="#promo_carousel" data-slide="prev"> <i class="m-icon-big-swapleft m-icon-white"></i> </a>

                                        <a class="carousel-control right" href="#promo_carousel" data-slide="next"> <i class="m-icon-big-swapright m-icon-white"></i> </a>

                                    </div>

                                </div>

                            </div>

                            <div class="block-yellow">
                                <div class="container">
                                    <div class="row-fluid">
                                        <div class="span5 margin-bottom-20">
                                            <a href="#"><img src="<%=path%>/media/image/img2.png" alt=""></a>
                                        </div>
                                        <div class="span7">
                                            <h2>断舍离从未变得如此美好...</h2><br>
                                            <h3>
                                            	放心发布，易主亦有情！
                                            </h3>
                                            <p>
                                            	还在后悔买来了没用的东西吗？<br>
                                            	还在后悔旧东西没地方放吗？<br>
                                           		还在犹豫某些东西要不要扔吗？<br>
                                           		不用担心<br>
                                           		有文理小跳蚤<br><br><br><br>
                                            </p>
                                            <a href="javascript:void(0);" onclick="editBlog()" class="btn blue big xlarge"> 发布物品 <i class="m-icon-big-swapright m-icon-white"></i> </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="block-transparent" id="showMenuDiv">

                                <div class="container">
									<c:forEach items="${menuList}" var="menu" varStatus="status">
										<c:if test="${status.count%2 == 1 }">
											<div class="row-fluid">
			                                   <div class="row-fluid margin-bottom-20">
			                                        <div class="span6 margin-bottom-20">
			                                            <h2><i class="${menu.thumbnailUrl }"></i>&nbsp;${menu.name }</h2><br>
			                                            <p>${menu.text}</p>                                         
			                                        </div>
			                                        <div class="span6 margin-bottom-20">
			                                            <a href="<%=path%>/toListByType?menuId=${menu.id }&pc=1"><img src="<%=path%>/${menu.picUrl}" alt=""></a>
			                                        </div>
			                                    </div>
			                                </div>										
										</c:if>
										<c:if test="${status.count%2 == 0 }">
		                                  	<div class="row-fluid">
		                                        <div class="span6 margin-bottom-20">
		                                            <a href="<%=path%>/toListByType?menuId=${menu.id }&pc=1"><img src="<%=path%>/${menu.picUrl}" alt=""></a>
		                                        </div>
			                                   <div class="row-fluid margin-bottom-20">
			                                        <div class="span6 margin-bottom-20">
			                                            <h2><i class="${menu.thumbnailUrl }"></i>&nbsp;${menu.name }</h2><br>
			                                            <p>${menu.text}</p>                                         
			                                        </div>
			                                    </div>
		                                    </div>										
										</c:if>
																		
									</c:forEach>
                                    <hr>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- END PAGE CONTENT-->

            </div>

            <!-- END PAGE CONTAINER-->

        </div>

        <!-- END PAGE -->

        <!-- END CONTAINER -->

        <!-- BEGIN FOOTER1 -->

        <div class="footer ">

            <div class="container ">

                <div class="footer-inner">

                    2017 &copy; wenLiFleaMarket by xjw

                </div>

                <div class="footer-tools">

                    <span class="go-top"> <i class="icon-angle-up"></i> </span>

                </div>

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

        <script src="<%=path%>/media/js/app.js"></script>
        <script src="<%=path%>/media/js/welcome/indexDoor.js" type="text/javascript" ></script>

        <script>
			jQuery(document).ready(function() {

				App.init();
				IndexDoor.init();
				jQuery('#promo_carousel').carousel({

					interval : 10000,

					pause : 'hover'

				});

			});

        </script>

        <!-- END JAVASCRIPTS -->

    </body>

    <!-- END BODY -->

</html>