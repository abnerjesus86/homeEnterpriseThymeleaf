<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Soy el index</h1>
	
	<a href='<c:url value="/monitoreoEdi"/>'>Acerca de Monitor Edi</a> <br/>
	<a href='<c:url value="/users"/>'>Usuarios</a> <br/>
	<a href='<c:url value="/secretQuestions"/>'>Secret Question</a> <br/>
	<a href='<c:url value="/roles"/>'>Roles</a> <br/>
	<a href='<c:url value="/applications"/>'>Applications</a> <br/>
	<a href='<c:url value="/pages"/>'>Pages</a> <br/>
	<a href='<c:url value="/entities"/>'>Entities</a> <br/>
	<a href='<c:url value="/permissions"/>'>Permissions</a> <br/>
	<a href='<c:url value="/exampleTemplate"/>'>Template</a> <br/>
	<a href='<c:url value="/getUser"/>'>Usuario</a> <br/>
	<a href='<c:url value="/catalogManager"/>'>Catalogs</a> <br/>
	<a href='<c:url value="/applicationWizard"/>'>Setup your application</a> <br/>
	<a href='<c:url value="/appWizards/3"/>'>Setup your wizard application </a> <br/>
	<a href='<c:url value="/appWizard/3"/>'>Setup your application 2</a> <br/>
	<br/>
	
	<c:out value="${p_cntrForm}" /> <br/>
	
</body>
</html>