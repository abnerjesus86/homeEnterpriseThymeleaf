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
<h1>Pages:</h1>
<br/>
<a href='<c:url value="/pageForm"/>'>Add Page</a> <br/>
<table>
       <tr>
           <th>ID</th>
           <th>Page</th>
           <th>Descripcion</th>
           <th>Url</th>
           <th>Page Type</th>
           <th>Active</th>
           <th>Usuario Creacion</th>
           <th>Fecha Creacion</th>
           <th>Modificado Por</th>
           <th>Modificado El</th>
           <th colspan="2"></th>
       </tr>
       <c:forEach items="${pages}" var="pag">
           <tr>
               <td>${pag.pageId}</td>
               <td>${pag.pageName}</td>
               <td>${pag.pageDescription}</td>
               <td>${pag.pageUrl}</td>
               <td>${pag.pageType}</td>
               <td>${pag.pageActive}</td>
               <td>${pag.pageCreatedBy}</td>
               <td>${pag.pageCreatedDate}</td>
               <td>${pag.pageUpdateBy}</td>
               <td>${pag.pageUpdateDate}</td>
               <td><a href='<c:url value="/pageForm/${pag.pageId}/update"/>'>Update</a> </td>
               <td></td>
           </tr>
    </c:forEach>
    </table>

</body>
</html>