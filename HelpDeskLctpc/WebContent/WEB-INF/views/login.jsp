<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>INSPINIA | Login 2</title>

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/pages/login/css/style_login.css" rel="stylesheet">

</head>

<body class="login-layout lctpc-01">

	<div class="loginColumns animated fadeInDown">
		<div class="row login-box visible widget-box no-border">

			<div class="col-md-6">

				<h2 class="font-bold">Welcome to Helpdesk Hutchison Port LCT+</h2>
				<hr />
				<p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.</p>

			</div>
			<div class="col-md-6">
			     
				<div>
				<c:if test="${param.login_error != null}">
                        
                        <div class="alert alert-danger">Error de autentificacion!!</div>
                        <hr />
                    </c:if>
					<c:url var="loginUrl" value="/appLogin" />
					<form class="m-t" role="form" name="f" action="${loginUrl}" method="POST">
						<div class="form-group">
							<input type="text" class="form-control" name="username" placeholder="Username" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name=password placeholder="Password" required>
						</div>
						<button type="submit" class="btn btn-primary block full-width m-b">Login</button>
						<a href="#"> <small>Forgot password?</small>
						</a>
					</form>

				</div>
			</div>
		</div>
		<hr />
		<div class="row" style="color: white">
			<div class="col-md-6">
				<strong>Copyright</strong> Hutchison Ports LCT
			</div>
			<div class="col-md-6 text-right">
				<small>© 2017</small>
			</div>
		</div>
	</div>

</body>

</html>
