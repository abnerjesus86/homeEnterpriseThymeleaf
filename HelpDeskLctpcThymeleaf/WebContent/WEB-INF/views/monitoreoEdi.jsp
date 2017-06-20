<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
/* function initializeDatePicker() {
    $(".datepicker").datepicker({
           dateFormat : "yy-mm-dd",
           changeMonth : true,
           changeYear : true,
           showButtonPanel : true
    });
    $(".datepicker2").datepicker({
        dateFormat : "yy-mm-dd",
        changeMonth : true,
        changeYear : true,
        showButtonPanel : true
 });
} */

$(function() {
    $( "#datepicker1" ).datepicker({
        dateFormat: "mm/dd/yy"
    });
  });
$(function() {
    $( "#datepicker2" ).datepicker({
        dateFormat: "mm/dd/yy"
    });
  });

</script>
</head>
<body>
    <h1>MoniteoEDI</h1>
    <br>
    
    <sf:form action="${pageContext.request.contextPath}/monitoreoEdi/executeCoarri" method="post" commandName="containerEdi">
	    <table>
	     
	        <tr>
	            <td>Fecha Inicio:</td>
	            <td><sf:input path="initialDate" id="datepicker1" type="date"/> </td>
	            <td>Fecha Final:</td>
                <td><sf:input path="finalDate"  id="datepicker2"/> </td>
	        </tr>
	        <tr>
                <td>Event:</td>
                <td><sf:input path="event"/> </td>
            </tr>
            <tr>
                <td>Cliente:</td>
                <td><sf:input path="client"/> </td>
            </tr>
            <tr>
                <td>Referencia de Buque:</td>
                <td><sf:input path="referenceVessel"/> </td>
            </tr>
            <tr>
                <td>Directorio Virtual:</td>
                <td><sf:input path="directory"/> </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Executar Coarri"/> </td>
            </tr>
	    </table>
    </sf:form>
    <br/>
    <c:out value="${resultado}"/>
    
    
    
    
    
    
</body>
</html>