<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Application Manager | Main view</title>

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/iCheck/custom.css" rel="stylesheet">
<!-- Sweet Alert -->
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/dataTables/datatables.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/style.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
</head>

<body>

	<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav metismenu" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span class="clear"> <span class="block m-t-xs"> <strong
										class="font-bold">David Williams</strong>
								</span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="<c:url value="/logout"/>">Logout</a></li>
							</ul>
						</div>
						<div class="logo-element">IN+</div>
					</li>
					<li class="active"><a href='/'><i class="fa fa-th-large"></i> <span class="nav-label">Main view</span></a></li>
					<li><a href='<c:url value="/admin_user"/>'><i class="fa fa-th-large"></i> <span class="nav-label">Administration Users</span> </a></li>
					<li><a href='<c:url value="/applications"/>'><i class="fa fa-th-large"></i> <span class="nav-label">Administration Applications</span> </a>
					</li>
					<li><a href='<c:url value="/appWizards/?id=3"/>'><i class="fa fa-th-large"></i> <span class="nav-label">Applications Wizard</span> </a></li>
				</ul>

			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
						<form role="search" class="navbar-form-custom" method="post" action="#">
							<div class="form-group">
								<input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
							</div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message">Welcome to INSPINIA+ Admin Theme.</span></li>
						<li><a href="<c:url value="/logout"/>"> <i class="fa fa-sign-out"></i> Log out
						</a></li>
					</ul>

				</nav>
			</div>

			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="modal inmodal fade" id="myModalApplication" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title">Form User</h4>
									</div>
									<div class="modal-body">
										<div class="row"></div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
										<button id="#btnSaveConfiguration"  type="button" class="btn btn-primary">Save changes</button>
									</div>
								</div>
							</div>
						</div>
						
						<div id="myTab" class="tabs-container">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tab-1"><i class="fa fa-desktop"></i> Applications</a></li>
								<li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-files-o"></i>Pages</a></li>
								<li class=""><a data-toggle="tab" href="#tab-3"><i class="fa fa-sliders"></i>Permission</a></li>
								<li class=""><a data-toggle="tab" href="#tab-4"><i class="fa fa-id-card-o"></i>Secret Questions</a></li>
								<li class=""><a data-toggle="tab" href="#tab-5"><i class="fa fa-gears"></i>Entities</a></li>

							</ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="panel-body">
										<div id="divTbApplications" class="table-responsive">
											<table id="tableApplications" class="table table-striped table-bordered table-hover">
												<thead>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>

									</div>
								</div>
								<div id="tab-2" class="tab-pane">
									<div class="panel-body">
										<p>
											<a href="#formularioModal" class="btn btn-primary" id="btn-addPage" role="button" data-toggle="modal"> New </a>
										</p>
										<div id="divTbPages" class="table-responsive">
											<table id="tablePages" class="table table-striped table-bordered table-hover">
												<thead>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>

								</div>
								<div id="tab-3" class="tab-pane">
									<div class="panel-body">
										<div id="divTbPermission" class="table-responsive">
											<table id="tablePermission" class="table table-striped table-hover">
												<thead>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div id="tab-4" class="tab-pane">
									<div class="panel-body">
										<p>
											<a href="#formularioModal" class="btn btn-primary" id="btn-addSecretQuestion" role="button" data-toggle="modal"> <i
												class="glyphicon glyphicon-plus"></i> New
											</a>

										</p>
										<div id="divTbSecretQuestion" class="table-responsive">
											<table id="tableSecretQuestion" class="table table-striped table-hover">
												<thead>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div id="tab-5" class="tab-pane">
									<div class="panel-body">
										<p>
											<a href="#formularioModal" class="btn btn-primary" id="btn-addEntities" role="button" data-toggle="modal"> <i
												class="glyphicon glyphicon-plus"></i> New
											</a>
										</p>
										<div id="divTbEntity" class="table-responsive">
											<table id="tableEntity" class="table table-striped table-hover">
												<thead>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<div>
					<strong>Copyright</strong> Hutchison Ports LCT &copy; 2017
				</div>
			</div>

		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- DataTable -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/dataTables/datatables.min.js"></script>

	<!-- Chosen -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/chosen/chosen.jquery.js"></script>

	<!-- iCheck -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/iCheck/icheck.min.js"></script>

    <!-- Sweet alert -->
    <script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/inspinia.js"></script>
	<script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/plugins/pace/pace.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/pages/catalog/js/script.js"></script>

</body>

</html>
