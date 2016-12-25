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
<h1>Permissions:</h1>
<br/>
<a href='<c:url value="/permForm"/>'>Add Permission</a> <br/>
<table>
       <tr>
           <th>ID</th>
           <th>Permision Name</th>
           <th>Descripcion</th>
           <th>Active</th>
           <th>Usuario Creacion</th>
           <th>Fecha Creacion</th>
           <th>Modificado Por</th>
           <th>Modificado El</th>
           <th colspan="2"></th>
       </tr>
       <c:forEach items="${perms}" var="perm">
           <tr>
               <td>${perm.prmnId}</td>
               <td>${perm.prmnName}</td>
               <td>${perm.prmnDescription}</td>
               <td>${perm.prmnActive}</td>
               <td>${perm.prmnCreatedBy}</td>
               <td>${perm.prmnCreatedDate}</td>
               <td>${perm.prmnUpdateBy}</td>
               <td>${perm.prmnUpdateDate}</td>
               <td><a href='<c:url value="/permForm/${perm.prmnId}/update"/>'>Update</a> </td>
               <td></td>
           </tr>
    </c:forEach>
    </table>

</body>
</html>