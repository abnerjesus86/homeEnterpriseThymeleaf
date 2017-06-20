<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | 404 Error</title>

    <link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plantilla/INSPINIA/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center animated fadeInDown">
        <h1>404</h1>
        <h3 class="font-bold">Page Not Found</h3>

        <div class="error-desc">
            Sorry, but the page you are looking for has note been found. Try checking the URL for error, then hit the refresh button on your browser or try found something else in our app.
            <a href='<c:url value="/"/>' class="btn btn-primary m-t">Index</a>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/plantilla/INSPINIA/js/bootstrap.min.js"></script>

</body>

</html>
