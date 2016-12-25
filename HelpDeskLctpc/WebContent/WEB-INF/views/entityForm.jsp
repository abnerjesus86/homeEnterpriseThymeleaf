<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Form  Entity:</h1> -->
<sf:form action="${pageContext.request.contextPath}/entityForm/save"
        method="post" commandName="entt" cssClass="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttId"> ID </label>
            <div class="col-sm-9">
                <sf:input type="text" placeholder="ID" class="col-xs-11" path="enttId" readonly="true" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttName"> Name </label>
            <div class="col-sm-9">
                <sf:input type="text" class="col-xs-11" path="enttName" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttDescription"> Descripcion </label>
            <div class="col-sm-9">
                <sf:input type="text" class="col-xs-11" path="enttDescription" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 col-xs-3 control-label no-padding-right" for="enttActive"> ACTIVE </label>
            <div>
                <label class="middle col-sm-9 col-xs-9">
                    <sf:checkbox path="enttActive" class="ace ace-switch ace-switch-6" value="true" /> <span class="lbl"></span>
                </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttCreatedBy"> CREATED BY </label>
            <div class="col-sm-9">
                <sf:input type="text" class="col-xs-11" path="enttCreatedBy" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttUpdateBy"> UPDATE BY </label>
            <div class="col-sm-9">
                <sf:input type="text" class="col-xs-11" path="enttUpdateBy" />
            </div>
        </div>
    </sf:form>
<!-- </body>
</html> -->