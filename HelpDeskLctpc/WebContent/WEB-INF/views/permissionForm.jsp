<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/permForm/save" method="post" commandName="perm" cssClass="form-horizontal" role="form">

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="col-xs-11" path="prmnId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnName"> NAME </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="prmnName" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnDescription"> DESCRIPTION </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="prmnDescription" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnActive"> ACTIVE </label>
		<div class="col-sm-9">
			<label class="middle"> <sf:checkbox path="prmnActive" class="ace ace-switch ace-switch-6" value="true" /> <span class="lbl"></span>
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="prmnCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="prmnUpdateBy" />
		</div>
	</div>
</sf:form>
