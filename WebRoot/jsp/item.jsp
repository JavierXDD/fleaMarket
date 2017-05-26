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

	<link href="<%=path%>/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=path%>/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<link href="<%=path%>/media/css/jquery.fancybox.css" rel="stylesheet" />

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="<%=path%>/media/css/blog.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=path%>/media/image/favicon.ico" />
	<link href="<%=path%>/media/css/halflings.css" rel="stylesheet" />

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

						<input type="hidden" id="isLoginId" value="${isLogin.id}"/>

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

	<div class="page-container row-fluid">


		<!-- BEGIN PAGE -->

		<div class="page-content">


			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">
				<div class="alert hidden">
					<button class="close" data-dismiss="alert"></button>
					<strong id="alert_text"></strong> 
				</div>
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							信息详情
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="<%=path%>/">首页</a> 
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="<%=path%>/toListByType?menuId=${blog.menuId }&pc=1">${blog.menuName }</a>
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="<%=path%>/getListByType?typeId=${blog.typeId}&pc=1">${blog.typeName }</a>
								<i class="icon-angle-right"></i>
							</li>
							<li><a href="#">信息详情</a></li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">

					<div class="span12 blog-page">

						<div class="row-fluid">

							<div class="span9 article-block">

								<h1>${blog.title}
								
								<c:if test="${(blog.isUse eq 0 || blog.isUse eq 1) && (isLogin.isManager eq 1 || isLogin.isManager eq 2)}">
									<button onclick="location='<%=path %>/blog/closeBlog?id=${blog.id }'" class="btn red pull-right">
									关闭此交易信息 <i class="icon-remove"></i>
									</button>
								</c:if>
								
								<c:if test="${(blog.isUse eq 0 || blog.isUse eq 2) && (isLogin.isManager eq 1 || isLogin.isManager eq 2)}">
									<button onclick="location='<%=path %>/blog/passBlog?id=${blog.id }'" class="btn blue pull-right">
									审核通过 <i class="icon-ok"></i>
									</button>
								</c:if>								
								</h1>
								<input type="hidden" id="blogId" value="${blog.id }"/>

								<div class="blog-tag-data">
								    
								    <div class="row-fluid">
								        <div class="span6">
								            <img style="height: 350px"  src="<%=path%>/${blog.surfacePicUrl}" alt="">
								        </div>
								        <div class="span6">
                                            <h1>${blog.name}</h1>
                                            <div class="row-fluid">
                                               <h3>价格:${blog.price}</h3> 
                                            </div>
                                            <div class="row-fluid">
                                               <h3>成色:${blog.quality}</h3> 
                                            </div>                                           
                                            <div class="row-fluid">
                                               <h3>联系人:${blog.userName}</h3> 
                                            </div>
                                            <div class="row-fluid">
                                               <h3>联系方式:${blog.userEmail}</h3> 
                                            </div>
                                            <div class="row-fluid">
                                                <ul class="unstyled inline blog-tags">
                                                    <li>
                                                        <i class="icon-tags" id="icon-tags"></i> 
    													<input type="hidden" id="blog_tags" value="${blog.tags}"/>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="row-fluid">
                                                <ul class="unstyled inline">    
                                                    <li><i class="icon-calendar"></i> ${blog.modifyTime}</li>       
                                                </ul>
                                            </div>
                                        </div>
								    </div>

								</div>

								<!--end news-tag-data-->

								<div>
									<blockquote class="hero">
										<p style="color:red;">特别提示：谨防通过微信、支付宝加好友进行商品支付、转账、付押金等欺诈行为！</p>									
									</blockquote>
								</div>
								<div>${blog.text}</div>

								<hr>
								<h2 ><i class="icon-comments" style="color:#4d90fe;"></i>&nbsp;用户评论</h2>
								<div id="userMessage">
									<c:if test="${empty messageList}">
										<div class="alert">暂无评论！</div>
									</c:if>
									<c:if test="${not empty messageList }">
										<c:forEach items="${messageList.beanList}" var="messageList" varStatus="status">									
											<blockquote class="hero">
												<div class="media">
													<div class="media-body">			
														<h4 class="media-heading"> <i class="icon-user" style="color:#4d90fe;"></i>&nbsp;${messageList.senderName} 
															<span>${messageList.sendTime } / <a href="javascript:void(0);" onclick="replyButton('${messageList.id}','${messageList.senderId}','${messageList.senderName}')">回复</a>
																<c:if test="${isLogin.isManager eq 1 or isLogin.isManager eq 2 }">
																	/ <a href="javascript:void(0);" onclick="deleteBlogMessage('${messageList.id}')">删除此评论</a>
																</c:if>
															</span>
														</h4>
														<p>&nbsp;&nbsp;&nbsp;&nbsp;${messageList.text }</p>

													</div>
												</div>
											</blockquote><hr>												
										</c:forEach>
									</c:if>
								</div>
								<!--end media-->
								<hr>

								<div class="post-comment">

									<h3><i class="icon-comment" style="color:#4d90fe;"></i> 留言</h3>
									<div class="alert hidden " id="message_alert">
										<button class="close" data-dismiss="alert"></button>
										<strong id="message_alertText"></strong> 
									</div>
									<form id="messageForm">
										<input type="hidden" id="message_senderId" name="senderId" value="${isLogin.id}">
										<input type="hidden" id="message_senderName" name="senderName" value="${isLogin.nickname}">
										<input type="hidden" id="message_blogId" name="blogId" value="${blog.id}">
										<input type="hidden" id="message_blogName" name="blogName" value="${blog.title}">
										<input type="hidden" id="message_messageType" name="messageType" value="0">	
										<input type="hidden" id="message_isUse" name="isUse" value="0">
										<input type="hidden" id="message_parentId" name="parentId" value="">
										<input type="hidden" id="message_receiverId" name="receiverId" value="">
										<input type="hidden" id="message_receiverName" name="receiverName" value="">							
										<textarea id="message_text" name="text" class="span10 m-wrap" rows="8"></textarea>
										<p><button class="btn blue" id="message_submit" type="button">提交留言</button></p>
									</form>

								</div>

							</div>

							<!--end span9-->

							<div class="span3 blog-sidebar">

								<div class="space20"></div>

								<h2><i class="icon-trophy" style="color:#4d90fe;"></i>&nbsp;其他专场</h2>

								<div class="top-news">								
									<c:forEach items="${menuList}" var="menu">
										<a href="<%=path %>/toListByType?menuId=${menu.id}&pc=1" class="menuShow btn red">
										<span>${menu.name }</span>
										<i class="${menu.thumbnailUrl } top-news-icon"></i>
										</a>									
									</c:forEach>
								</div>

								<div class="space20"></div>

								<h2><i class="icon-bookmark" style="color:#4d90fe;"></i>&nbsp;其他品类</h2>

								<ul class="unstyled inline sidebar-tags">
									<c:forEach items="${sonMenuList }" var="sonMenu">
										<li><a href="<%=path %>/getListByType?typeId=${sonMenu.id}&pc=1"><i class="icon-tags"></i> ${sonMenu.name }</a></li>									
									</c:forEach>
								</ul>

								<div class="space20"></div>


								<div class="space20"></div>

								<h2><i class=" icon-star" style="color:#4d90fe;"></i>&nbsp;最新发帖</h2>
								<div class="blog-twitter">
									<c:forEach items="${newBlogList }" var="newBlog">
										<div class="blog-twitter-block">
											<a href="<%=path%>/blog/toMyBlogDetail?id=${newBlog.id}">${newBlog.title }</a> 
											<p>${newBlog.name }</p>
											<em>${newBlog.quality }</em>
											<span>${newBlog.createTime }</span>
											<i class="icon-twitter blog-twiiter-icon"></i>
										</div>									
									</c:forEach>							
								</div>

							</div>

							<!--end span3-->

						</div>

					</div>

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

	<script src="<%=path%>/media/js/app.js"></script>      
	<script src="<%=path%>/media/js/welcome/item.js" type="text/javascript" ></script>

	<script>

		jQuery(document).ready(function() {    

		   App.init();
		   Item.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>