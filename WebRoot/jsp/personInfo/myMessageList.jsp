<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="<%=path%>/media/css/select2_metro.css" />
	<link rel="stylesheet" href="<%=path%>/media/css/DT_bootstrap.css" />
	<link href="<%=path%>/media/css/glyphicons.css" rel="stylesheet" />
	<link href="<%=path%>/media/css/halflings.css" rel="stylesheet" />
	
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
				<li class="start ">
					<a href="<%=path%>/blog/toMyBlog?id=${isLogin.id}">
					<i class="icon-stackexchange"></i> 
					<span class="title">我的发布</span>					
					</a>
				</li>
				<li class="start active">
					<a href="<%=path%>/message/toMyMessageList?id=${isLogin.id}">
					<i class="icon-comment"></i> 
					<span class="title">我的评论</span>
					<span class="selected"></span>				
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
							我的评论
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

							<li><a href="#">我的评论</a></li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
					<div class="span12">
						<div class="portlet box light-grey">

							<div class="portlet-title">						
								<div class="caption"><i class="icon-globe"></i>评论列表</div>
							</div>

							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover" id="blogList">

									<thead>
										<tr>
											<th width="5%">序号</th>
											<th width="30%">帖子标题</th>
											<th width="50%">评论内容</th>																					
											<th width="15%">操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${messageList.beanList }" var="messageList" varStatus="status" >										
											<tr class="odd gradeX">
												<td>${status.count} </td>
												<td><a href="<%=path %>/blog/toMyBlogDetail?id=${messageList.blogId}">${messageList.blogName}</a></td>
												<td class="messageText">${messageList.text}</td>
												<td>
													<a href="javascript:void(0);" onclick="showDetail('${messageList.id}')" class="btn mini purple icn-only" title="查看"><i class="halflings-icon white eye-open "></i></a>													
													<a href="<%=path %>/message/deleteMyMessage?id=${messageList.id}" class="btn mini blue icn-only" title="禁用"><i class="halflings-icon white remove "></i></a>
												</td>										
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%-- 分页 --%>
								<div class="pull-right">
									<div class="pagination">
									<ul>	
										<%-- 如果总页数大于1页，那么《显示出来！ --%>									
										<c:if test="${messageList.pc > 1 }">						
											<li>
						            			  <a href="<%=path%>/message/getMyMessageList?pc=${messageList.pc-1}&id=${isLogin.id}" aria-label="上一页">
						            			    <span aria-hidden="true">&laquo;</span>
						              			  <span class="sr-only">Previous</span>
						             			 </a>
						           			 </li>
										</c:if>
										<c:choose>
											<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
											<c:when test="${messageList.tp <= 10 }">
												<c:set var="begin" value="1" />
												<c:set var="end" value="${messageList.tp }" />
											</c:when>
											<c:otherwise>
												<%-- 当总页数>10时，通过公式计算出begin和end --%>
												<c:set var="begin" value="${messageList.pc-5 }" />
												<c:set var="end" value="${messageList.pc+4 }" />
												<%-- 头溢出 --%>
												<c:if test="${begin < 1 }">
													<c:set var="begin" value="1" />
													<c:set var="end" value="10" />
												</c:if>
												<%-- 尾溢出 --%>
												<c:if test="${end > messageList.tp }">
													<c:set var="begin" value="${messageList.tp - 9 }" />
													<c:set var="end" value="${messageList.tp }" />
												</c:if>
											</c:otherwise>
										</c:choose>
           								<%-- 循环遍历页码列表 --%>
										<c:forEach var="i" begin="${begin }" end="${end }">
											<c:choose>
												<c:when test="${i eq messageList.pc }">
													 <li><a>${i }</a></li>
												</c:when>
												<c:otherwise>
												 <li><a href="<%=path%>/message/getMyMessageList?pc=${i}&id=${isLogin.id}">${i }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>        
							            <c:if test="${messageList.pc < messageList.tp }">															
											<li>
					             			 <a href="<%=path%>/message/getMyMessageList?pc=${messageList.pc+1}&id=${isLogin.id}" aria-label="下一页">
					              			  <span aria-hidden="true">&raquo;</span>
					              			  <span class="sr-only">Next</span>
					             			 </a>
					           				 </li>
										</c:if> 										
									</ul>
									</div>																
								</div>
								<%-- 分页结束 --%>								
							</div>
						</div>
					</div>


					<!-- 查看模态框 -->
					<div class="modal fade" id="showDetailModal" tabindex="-1" role="dialog" aria-labelledby="showDetailLabel" aria-hidden="true">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					                <h4 class="modal-title" id="showDetailLabel">评论内容</h4>
					            </div>
					            <div class="modal-body">
									<form id="showDetailForm" class="form-horizontal">
										<div class="row-fluid">
											<div class="control-group span12" >
												<label class="control-label span2">评论内容</label>		
												<div class="controls span10">
													<input type="hidden" id="message_id"/>
													<p id="message_text"></p>
												</div>
											</div>									
										</div>
									</form>
								</div>
					            <div class="modal-footer">            
					                <button type="button"  id="showDetail_delete" class="btn blue btn-primary">删除该评论</button>
					            	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					            </div>
					        </div><!-- /.modal-content -->
					    </div><!-- /.modal -->
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
	<script type="text/javascript" src="<%=path%>/media/js/select2.min.js"></script>



	<script type="text/javascript" src="<%=path%>/media/js/DT_bootstrap.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=path%>/media/js/app.js" type="text/javascript"></script>
	<script src="<%=path%>/media/js/personInfo/myMessageList.js" type="text/javascript"></script>  

  


	<!-- END PAGE LEVEL SCRIPTS -->  

	<script>

		jQuery(document).ready(function() {    

		   	App.init(); // initlayout and core plugins
		   	MyMessageList.init();
			
		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>