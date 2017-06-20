<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Applications:</h1>
    <br />
    <a href='<c:url value="/appForm"/>'>Add Application:</a>
    <br />
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Descripcion</th>
            <th>Url</th>
            <th>Activo</th>
            <th>Usuario Creacion</th>
            <th>Fecha Creacion</th>
            <th>Modificado Por</th>
            <th>Modificado El</th>
            <th colspan="2"></th>
        </tr>
        <c:forEach items="${appns}" var="app">
            <tr>
                <td>${app.appnId}</td>
                <td>${app.appnName}</td>
                <td>${app.appnDescription}</td>
                <td>${app.appnUrl}</td>
                <td>${app.appnActive}</td>
                <td>${app.appnCreatedBy}</td>
                <td>${app.appnCreatedDate}</td>
                <td>${app.appnUpdateBy}</td>
                <td>${app.appnUpdateDate}</td>
                <td><a
                    href='<c:url value="/appForm/${app.appnId}/update"/>'>Update</a>
                </td>
                <td></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>