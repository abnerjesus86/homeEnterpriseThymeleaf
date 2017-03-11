<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>INSPINIA | Empty Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/pages/application-wizard/css/style2.css" />
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/dataTables/datatables.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/steps/jquery.steps.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<!-- Dual Listbox -->
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/style.css" rel="stylesheet">



</head>

<body class="skin-1">

	<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav metismenu" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span> <img alt="image" class="img-circle" src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/img/profile_small.jpg" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span class="clear"> <span class="block m-t-xs"> <strong
										class="font-bold">David Williams</strong>
								</span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="profile.html">Profile</a></li>
								<li><a href="contacts.html">Contacts</a></li>
								<li><a href="mailbox.html">Mailbox</a></li>
								<li class="divider"></li>
								<li><a href="login.html">Logout</a></li>
							</ul>
						</div>
						<div class="logo-element">IN+</div>
					</li>
					<li><a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboards</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="index.html">Dashboard v.1</a></li>
							<li><a href="dashboard_2.html">Dashboard v.2</a></li>
							<li><a href="dashboard_3.html">Dashboard v.3</a></li>
							<li><a href="dashboard_4_1.html">Dashboard v.4</a></li>
							<li><a href="dashboard_5.html">Dashboard v.5 </a></li>
						</ul></li>
					<li><a href="layouts.html"><i class="fa fa-diamond"></i> <span class="nav-label">Layouts</span></a></li>
					<li><a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Graphs</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="graph_flot.html">Flot Charts</a></li>
							<li><a href="graph_morris.html">Morris.js Charts</a></li>
							<li><a href="graph_rickshaw.html">Rickshaw Charts</a></li>
							<li><a href="graph_chartjs.html">Chart.js</a></li>
							<li><a href="graph_chartist.html">Chartist</a></li>
							<li><a href="c3.html">c3 charts</a></li>
							<li><a href="graph_peity.html">Peity Charts</a></li>
							<li><a href="graph_sparkline.html">Sparkline Charts</a></li>
						</ul></li>
					<li><a href="mailbox.html"><i class="fa fa-envelope"></i> <span class="nav-label">Mailbox </span><span
							class="label label-warning pull-right">16/24</span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="mailbox.html">Inbox</a></li>
							<li><a href="mail_detail.html">Email view</a></li>
							<li><a href="mail_compose.html">Compose email</a></li>
							<li><a href="email_template.html">Email templates</a></li>
						</ul></li>
					<li><a href="metrics.html"><i class="fa fa-pie-chart"></i> <span class="nav-label">Metrics</span> </a></li>
					<li><a href="widgets.html"><i class="fa fa-flask"></i> <span class="nav-label">Widgets</span></a></li>
					<li><a href="#"><i class="fa fa-edit"></i> <span class="nav-label">Forms</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="form_basic.html">Basic form</a></li>
							<li><a href="form_advanced.html">Advanced Plugins</a></li>
							<li><a href="form_wizard.html">Wizard</a></li>
							<li><a href="form_file_upload.html">File Upload</a></li>
							<li><a href="form_editors.html">Text Editor</a></li>
							<li><a href="form_autocomplete.html">Autocomplete</a></li>
							<li><a href="form_markdown.html">Markdown</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">App Views</span> <span class="pull-right label label-primary">SPECIAL</span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="contacts.html">Contacts</a></li>
							<li><a href="profile.html">Profile</a></li>
							<li><a href="profile_2.html">Profile v.2</a></li>
							<li><a href="contacts_2.html">Contacts v.2</a></li>
							<li><a href="projects.html">Projects</a></li>
							<li><a href="project_detail.html">Project detail</a></li>
							<li><a href="activity_stream.html">Activity stream</a></li>
							<li><a href="teams_board.html">Teams board</a></li>
							<li><a href="social_feed.html">Social feed</a></li>
							<li><a href="clients.html">Clients</a></li>
							<li><a href="full_height.html">Outlook view</a></li>
							<li><a href="vote_list.html">Vote list</a></li>
							<li><a href="file_manager.html">File manager</a></li>
							<li><a href="calendar.html">Calendar</a></li>
							<li><a href="issue_tracker.html">Issue tracker</a></li>
							<li><a href="blog.html">Blog</a></li>
							<li><a href="article.html">Article</a></li>
							<li><a href="faq.html">FAQ</a></li>
							<li><a href="timeline.html">Timeline</a></li>
							<li><a href="pin_board.html">Pin board</a></li>
						</ul></li>
					<li class="active"><a href="#"><i class="fa fa-files-o"></i> <span class="nav-label">Other Pages</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="search_results.html">Search results</a></li>
							<li><a href="lockscreen.html">Lockscreen</a></li>
							<li><a href="invoice.html">Invoice</a></li>
							<li><a href="login.html">Login</a></li>
							<li><a href="login_two_columns.html">Login v.2</a></li>
							<li><a href="forgot_password.html">Forget password</a></li>
							<li><a href="register.html">Register</a></li>
							<li><a href="404.html">404 Page</a></li>
							<li><a href="500.html">500 Page</a></li>
							<li class="active"><a href="empty_page.html">Empty page</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-globe"></i> <span class="nav-label">Miscellaneous</span><span class="label label-info pull-right">NEW</span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="toastr_notifications.html">Notification</a></li>
							<li><a href="nestable_list.html">Nestable list</a></li>
							<li><a href="agile_board.html">Agile board</a></li>
							<li><a href="timeline_2.html">Timeline v.2</a></li>
							<li><a href="diff.html">Diff</a></li>
							<li><a href="pdf_viewer.html">PDF viewer</a></li>
							<li><a href="i18support.html">i18 support</a></li>
							<li><a href="sweetalert.html">Sweet alert</a></li>
							<li><a href="idle_timer.html">Idle timer</a></li>
							<li><a href="truncate.html">Truncate</a></li>
							<li><a href="password_meter.html">Password meter</a></li>
							<li><a href="spinners.html">Spinners</a></li>
							<li><a href="spinners_usage.html">Spinners usage</a></li>
							<li><a href="tinycon.html">Live favicon</a></li>
							<li><a href="google_maps.html">Google maps</a></li>
							<li><a href="datamaps.html">Datamaps</a></li>
							<li><a href="social_buttons.html">Social buttons</a></li>
							<li><a href="code_editor.html">Code editor</a></li>
							<li><a href="modal_window.html">Modal window</a></li>
							<li><a href="clipboard.html">Clipboard</a></li>
							<li><a href="text_spinners.html">Text spinners</a></li>
							<li><a href="forum_main.html">Forum view</a></li>
							<li><a href="validation.html">Validation</a></li>
							<li><a href="tree_view.html">Tree view</a></li>
							<li><a href="loading_buttons.html">Loading buttons</a></li>
							<li><a href="chat_view.html">Chat view</a></li>
							<li><a href="masonry.html">Masonry</a></li>
							<li><a href="tour.html">Tour</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-flask"></i> <span class="nav-label">UI Elements</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="typography.html">Typography</a></li>
							<li><a href="icons.html">Icons</a></li>
							<li><a href="draggable_panels.html">Draggable Panels</a></li>
							<li><a href="resizeable_panels.html">Resizeable Panels</a></li>
							<li><a href="buttons.html">Buttons</a></li>
							<li><a href="video.html">Video</a></li>
							<li><a href="tabs_panels.html">Panels</a></li>
							<li><a href="tabs.html">Tabs</a></li>
							<li><a href="notifications.html">Notifications & Tooltips</a></li>
							<li><a href="helper_classes.html">Helper css classes</a></li>
							<li><a href="badges_labels.html">Badges, Labels, Progress</a></li>
						</ul></li>

					<li><a href="grid_options.html"><i class="fa fa-laptop"></i> <span class="nav-label">Grid options</span></a></li>
					<li><a href="#"><i class="fa fa-table"></i> <span class="nav-label">Tables</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="table_basic.html">Static Tables</a></li>
							<li><a href="table_data_tables.html">Data Tables</a></li>
							<li><a href="table_foo_table.html">Foo Tables</a></li>
							<li><a href="jq_grid.html">jqGrid</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-shopping-cart"></i> <span class="nav-label">E-commerce</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="ecommerce_products_grid.html">Products grid</a></li>
							<li><a href="ecommerce_product_list.html">Products list</a></li>
							<li><a href="ecommerce_product.html">Product edit</a></li>
							<li><a href="ecommerce_product_detail.html">Product detail</a></li>
							<li><a href="ecommerce-cart.html">Cart</a></li>
							<li><a href="ecommerce-orders.html">Orders</a></li>
							<li><a href="ecommerce_payments.html">Credit Card form</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-picture-o"></i> <span class="nav-label">Gallery</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="basic_gallery.html">Lightbox Gallery</a></li>
							<li><a href="slick_carousel.html">Slick Carousel</a></li>
							<li><a href="carousel.html">Bootstrap Carousel</a></li>

						</ul></li>
					<li><a href="#"><i class="fa fa-sitemap"></i> <span class="nav-label">Menu Levels </span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="#">Third Level <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>

								</ul></li>
							<li><a href="#">Second Level Item</a></li>
							<li><a href="#">Second Level Item</a></li>
							<li><a href="#">Second Level Item</a></li>
						</ul></li>
					<li><a href="css_animation.html"><i class="fa fa-magic"></i> <span class="nav-label">CSS Animations </span><span
							class="label label-info pull-right">62</span></a></li>
					<li class="landing_link"><a target="_blank" href="landing.html"><i class="fa fa-star"></i> <span class="nav-label">Landing Page</span> <span
							class="label label-warning pull-right">NEW</span></a></li>
					<li class="special_link"><a href="package.html"><i class="fa fa-database"></i> <span class="nav-label">Package</span></a></li>
				</ul>

			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
						<form role="search" class="navbar-form-custom" action="search_results.html">
							<div class="form-group">
								<input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
							</div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">Welcome to INSPINIA+ Admin Theme.</span></li>
						<li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i> <span
								class="label label-warning">16</span>
						</a>
							<ul class="dropdown-menu dropdown-messages">
								<li>
									<div class="dropdown-messages-box">
										<a href="profile.html" class="pull-left"> <img alt="image" class="img-circle"
											src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/img/a7.jpg">
										</a>
										<div class="media-body">
											<small class="pull-right">46h ago</small> <strong>Mike Loreipsum</strong> started following <strong>Monica Smith</strong>. <br> <small
												class="text-muted">3 days ago at 7:58 pm - 10.06.2014</small>
										</div>
									</div>
								</li>
								<li class="divider"></li>
								<li>
									<div class="dropdown-messages-box">
										<a href="profile.html" class="pull-left"> <img alt="image" class="img-circle"
											src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/img/a4.jpg">
										</a>
										<div class="media-body ">
											<small class="pull-right text-navy">5h ago</small> <strong>Chris Johnatan Overtunk</strong> started following <strong>Monica Smith</strong>.
											<br> <small class="text-muted">Yesterday 1:21 pm - 11.06.2014</small>
										</div>
									</div>
								</li>
								<li class="divider"></li>
								<li>
									<div class="dropdown-messages-box">
										<a href="profile.html" class="pull-left"> <img alt="image" class="img-circle"
											src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/img/profile.jpg">
										</a>
										<div class="media-body ">
											<small class="pull-right">23h ago</small> <strong>Monica Smith</strong> love <strong>Kim Smith</strong>. <br> <small
												class="text-muted">2 days ago at 2:30 am - 11.06.2014</small>
										</div>
									</div>
								</li>
								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										<a href="mailbox.html"> <i class="fa fa-envelope"></i> <strong>Read All Messages</strong>
										</a>
									</div>
								</li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
								class="label label-primary">8</span>
						</a>
							<ul class="dropdown-menu dropdown-alerts">
								<li><a href="mailbox.html">
										<div>
											<i class="fa fa-envelope fa-fw"></i> You have 16 messages <span class="pull-right text-muted small">4 minutes ago</span>
										</div>
								</a></li>
								<li class="divider"></li>
								<li><a href="profile.html">
										<div>
											<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span class="pull-right text-muted small">12 minutes ago</span>
										</div>
								</a></li>
								<li class="divider"></li>
								<li><a href="grid_options.html">
										<div>
											<i class="fa fa-upload fa-fw"></i> Server Rebooted <span class="pull-right text-muted small">4 minutes ago</span>
										</div>
								</a></li>
								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										<a href="notifications.html"> <strong>See All Alerts</strong> <i class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul></li>


						<li><a href="login.html"> <i class="fa fa-sign-out"></i> Log out
						</a></li>
					</ul>

				</nav>
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>This is main title</h2>
					<ol class="breadcrumb">
						<li><a href="index.html">This is</a></li>
						<li class="active"><strong>Breadcrumb</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>

			<div class="wrapper wrapper-content">
				<!-- <div class="middle-box text-center animated fadeInRightBig"> -->
				<!-- <div class="animated fadeInRightBig"> -->

				<div class="row">
					<!-- Configuracion del wizard... -->
					<div id="wizard" class="wizard-big wizard">
						<h1>Application</h1>
						<div class="step-content">
							<!-- <div class="text-center m-t-md"> -->
							<h2>Create your application</h2>
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="appnId"> ID </label>
									<div class="col-sm-9">
										<input type="text" placeholder="ID" class="form-control" id="appnId" readonly="true" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="appnAppnId"> Application Father </label>
									<div class="col-sm-9">
										<select id="appnAppnId" class="chosen-select" size="5" data-placeholder="Choose a Application Father o Master...">
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="appnPlfmId"> Platform </label>
									<div class="col-sm-9">
										<select id="appnPlfmId" class="chosen-select" data-placeholder="Choose a Platform...">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="appnName"> Name </label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="appnName" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="appnDescription"> Description </label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="appnDescription" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="appnUrl"> Url </label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="appnUrl" />
									</div>
								</div>

							</form>

						</div>

						<h1>Roles</h1>
						<div class="step-content">
							<!-- <div class="text-center m-t-md"> -->
							<h2>Create Roles for your Application</h2>
							<div class="row">
								<div class="col-xs-12 col-sm-6">
									<div class="table-responsive">
										<table id="tableRoles" class="table table-striped table-bordered table-hover dataTables-example">
											<thead>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>

								</div>
								<div class="col-xs-12 col-sm-6">
									<form class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="userId"> ID </label>
											<div class="col-sm-9">
												<input type="text" placeholder="ID" class="form-control" id="roleId" readonly="true" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="roleName"> Name </label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="roleName" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="roleDescription"> Description </label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="roleDescription" />
											</div>
										</div>

										<div class="hr-line-dashed"></div>
										<div>
											<div class="btn-group col-sm-offset-3">
												<button class="btn btn-success btn-sm" type="button" id="btn-addRole">
													<i class="fa fa-floppy-o bigger-110"></i> New
												</button>

												<button class="btn btn-sm" type="reset" id="btn-resetRole">
													<i class="fa fa-undo bigger-110"></i> Reset
												</button>
											</div>
										</div>
									</form>
								</div>

							</div>
							<!-- </div> -->
						</div>

						<h1>Pages</h1>
						<div class="step-content">
							<!-- <div class="text-center m-t-md"> -->
							<h2>Create Page for your Application</h2>
							<div class="row">
								<div class="col-xs-12 col-sm-7">
									<div id="capaLoader" class="text-center hidden" style="margin-top: 100px">
										<img class='loader-gear' src='../res/assets/images/loading1.gif' />
									</div>
									<div id="divTbPages" class="table-responsive hidden">
										<!-- dataTables-example -->
										<table id="tablePages" class="table table-striped table-bordered table-hover ">
										</table>
									</div>

								</div>

								<div class="col-xs-12 col-sm-5">
									<form class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="pageId"> ID </label>
											<div class="col-sm-9">
												<input type="text" placeholder="ID" class="form-control" id="pageId" readonly="true" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="pagePageId"> Page Father </label>
											<div class="col-sm-9">
												<select id="pagePageId" class="chosen-select" data-placeholder="Choose a Page Father o Master...">
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="pageDisplay"> Name </label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="pageDisplay" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="pageDescription"> Descripcion </label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="pageDescription" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="pageUrl"> Url </label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="pageUrl" />
											</div>
										</div>
										<!-- <div class="hr hr-18 dotted hr-double"></div> -->
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-top" for="duallist"> Select Entities </label>

											<div class="col-sm-9">
												<!-- #section:plugins/input.duallist -->
												<select multiple="multiple" size="6" class="form-control dual_select" name="duallistbox_demo1[]" id="duallist">
												</select>

												<!-- /section:plugins/input.duallist -->
											</div>
										</div>
										<div class="hr-line-dashed"></div>
										<div>
											<div class="btn-group col-sm-offset-3">
												<button class="btn btn-success btn-sm" type="button" id="btn-addPage">
													<i class="fa fa-floppy-o bigger-110"></i> New
												</button>
												<button class="btn btn-sm" type="reset" id="btn-resetPage">
													<i class="fa fa-undo bigger-110"></i> Reset
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<!-- </div> -->
						</div>

						<h1>Assign page to roles</h1>
						<div class="step-content">
							<!-- <div class="text-center m-t-md"> -->
							<h2>Associates permissions to pages</h2>
							<div id="divPages" class="row">
								<div class="col-lg-12" id="divColPrincipalPages">
								</div>
							</div>
                           
							<!-- </div> -->
						</div>
						<!-- fin div wizard -->
					</div>
					<!-- Fin div row -->
				</div>
				<!-- middle-box text-center animated fadeInRightBig... -->
				<!-- </div> -->
			</div>
			<div class="footer">
				<div class="pull-right">
					10GB of <strong>250GB</strong> Free.
				</div>
				<div>
					<strong>Copyright</strong> Example Company &copy; 2014-2017
				</div>
			</div>

		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/inspinia.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/pace/pace.min.js"></script>
	<!-- Chosen -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/chosen/chosen.jquery.js"></script>
	<!-- DataTable -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/dataTables/datatables.min.js"></script>
	<!-- Dual Listbox -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
	<!-- Steps -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/steps/jquery.steps.min.js"></script>
	<!-- Sweet alert -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/iCheck/icheck.min.js"></script>
    
	<!-- Jquery Validate -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/validate/jquery.validate.min.js"></script>

	<script src="${pageContext.request.contextPath}/res/pages/application-wizard/js/script2.js"></script>
	
	
</body>

</html>
