<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> -->
	<h1>Usuarios:</h1>
	<br />
	<c:out value="${resultado1}"></c:out>
	<a href='<c:url value="/userFormulario"/>'>Crear Usuario</a>
	<br />
	<table class="table table-striped table-bordered table-hover">
	   <thead>
		<tr>
			<th>ID</th>
			<th>Cuenta Usuario</th>
			<th>Compañia</th>
			<th>Num Empleado</th>
			<th>Activo</th>
			<th>Usuario Creacion</th>
			<th>Fecha Creacion</th>
			<th>Modificado Por</th>
			<th>Modificado El</th>
			<th colspan="2"></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
		  
			<tr>
				<td>${user.userId}</td>
				<td>${user.userUsername}</td>
				<td>${user.userEmesCompany}</td>
				<td>${user.userEmesId}</td>
				<td class="center">
				<label class="pos-rel">
                    <input type="checkbox" class="ace" value="${user.userActive}" <c:if test="${user.userActive}" > <c:out value="checked" /> </c:if>  />
                    <span class="lbl"></span>
                </label>
				</td>
				<td>${user.userCreatedBy}</td>
				<td>${user.userCreatedDate}</td>
				<td>${user.userUpdateBy}</td>
				<td>${user.userUpdateDate}</td>
				<td><a
					href='<c:url value="/userFormulario/${user.userId}/update"/>'>Update</a>
				</td>
				<td></td>
			</tr>
			
		</c:forEach>
		<tbody>
	</table>
<!-- 
</body>
</html> -->
