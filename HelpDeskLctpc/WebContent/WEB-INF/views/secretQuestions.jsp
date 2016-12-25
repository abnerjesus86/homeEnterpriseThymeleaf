<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Secret Questions:</h1>
<a href='<c:url value="/secretQuestionForm"/>'>Add Page</a> <br/>
<table>
       <tr>
           <th>ID</th>
           <th>Secret Question</th>
           <th>Active</th>
           <th>Usuario Creacion</th>
           <th>Fecha Creacion</th>
           <th>Modificado Por</th>
           <th>Modificado El</th>
           <th colspan="2"></th>
       </tr>
       <c:forEach items="${sctQs}" var="scq">
           <tr>
               <td>${scq.sequId}</td>
               <td>${scq.sequQuestion}</td>
               <td>${scq.sequActive}</td>
               <td>${scq.sequCreatedBy}</td>
               <td>${scq.sequCreatedDate}</td>
               <td>${scq.sequUpdateBy}</td>
               <td>${scq.sequUpdateDate}</td>
               <td><a href='<c:url value="/secretQuestionForm/${scq.sequId}/update"/>'>Update</a> </td>
               <td></td>
           </tr>
    </c:forEach>
    </table>

</body>
</html>