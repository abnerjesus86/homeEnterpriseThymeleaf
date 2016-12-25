<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Form  Secret Question:</h1> -->
<sf:form action="${pageContext.request.contextPath}/secretQuestionForm/save" method="post" commandName="sctQ" cssClass="form-horizontal" role="form">

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="col-xs-11" path="sequId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequQuestion"> Question </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="sequQuestion" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequActive"> ACTIVE </label>
		<div class="col-sm-9">
			<label class="middle"> <sf:checkbox path="sequActive" class="ace ace-switch ace-switch-6" value="true" /> <span class="lbl"></span>
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="sequCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="sequUpdateBy" />
		</div>
	</div>

</sf:form>
<!-- </body>
</html> -->