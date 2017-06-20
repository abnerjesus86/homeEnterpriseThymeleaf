<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/permForm/save" method="post" commandName="perm" cssClass="form-horizontal" role="form">

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="form-control" path="prmnId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnName"> NAME </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="prmnName" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnDescription"> DESCRIPTION </label>
		<div class="col-sm-9">
			<sf:textarea class="form-control" path="prmnDescription" rows="3" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnActive"> ACTIVE </label>
		<div class="col-sm-9">
		<div class="i-checks">
                  <label> 
                      <sf:checkbox path="prmnActive" value="true" />
                      <i></i>
                  </label>
              </div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="prmnCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="prmnUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="prmnUpdateBy" />
		</div>
	</div>
</sf:form>
