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
<h1>Entites:</h1>
<br />
    <a href='<c:url value="/entityForm"/>'>Add Entity</a>
    
    <br />
    <table>
        <tr>
            <th>ID</th>
            <th>Entity</th>
            <th>Descripcion</th>
            <th>Active</th>
            <th>Usuario Creacion</th>
            <th>Fecha Creacion</th>
            <th>Modificado Por</th>
            <th>Modificado El</th>
            <th colspan="2"></th>
        </tr>
        <c:forEach items="${entts}" var="entt">
            <tr>
                <td>${entt.enttId}</td>
                <td>${entt.enttName}</td>
                <td>${entt.enttDescription}</td>
                <td>${entt.enttActive}</td>
                <td>${entt.enttCreatedBy}</td>
                <td>${entt.enttCreatedDate}</td>
                <td>${entt.enttUpdateBy}</td>
                <td>${entt.enttUpdateDate}</td>
                <td><a
                    href='<c:url value="/entityForm/${entt.enttId}/update"/>'>Update</a>
                </td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>