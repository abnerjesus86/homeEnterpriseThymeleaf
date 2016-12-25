<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/userFormulario/save" method="post" commandName="user" cssClass="form-horizontal" role="form">
	<div class="col-xs-12 col-sm-5">
		<label class="ace-file-input ace-file-multiple"><input type="file"><span class="ace-file-container"
			data-title="Drop files here or click to choose"><span class="ace-file-name" data-title="No File ..."><i
					class=" ace-icon ace-icon fa fa-cloud-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label>
	</div>
	<div class="col-xs-12 col-sm-7">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userId"> ID </label>
			<div class="col-sm-9">
				<sf:input type="text" placeholder="ID" class="col-xs-11" path="userId" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userUsername"> Account User </label>
			<div class="col-sm-9">
				<sf:input type="text" placeholder="LASTNAME.NAME" class="col-xs-11" path="userUsername" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userEmesCompany"> BU </label>
			<div class="col-sm-9">
				<sf:input type="text" placeholder="LCTPC" class="col-xs-11" path="userEmesCompany" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userEmesId"> NUM. EMPLOYEE </label>
			<div class="col-sm-9">
				<sf:input type="text" placeholder="# EMPLOYEE" class="col-xs-11" path="userEmesId" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userActive"> ACTIVE </label>
			<div class="col-sm-9">
				<label class="middle"> <sf:checkbox path="userActive" class="ace ace-switch ace-switch-6" value="true" /> <span class="lbl"></span>
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userCreatedBy"> CREATED BY </label>
			<div class="col-sm-9">
				<sf:input type="text" class="col-xs-11" path="userCreatedBy" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="userUpdateBy"> UPDATE BY </label>
			<div class="col-sm-9">
				<sf:input type="text" class="col-xs-11" path="userUpdateBy" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="accountInf.acinProfilePicture"> PROFILE PICTURE </label>
			<div class="col-sm-9">
				<sf:input type="text" placeholder="SELECT PICTURE" class="col-xs-11" path="accountInf.acinProfilePicture" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="accountInf.acinTelephone"> TELEPHONE </label>
			<div class="col-sm-9">
				<sf:input type="text" class="col-xs-11" path="accountInf.acinTelephone" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="accountInf.acinAlternateEmail"> ALTERNATE EMAIL </label>
			<div class="col-sm-9">
				<sf:input type="text" placeholder="example@domain.com" class="col-xs-11" path="accountInf.acinAlternateEmail" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="accountInf.acinCreatedBy"> CREATED BY </label>
			<div class="col-sm-9">
				<sf:input type="text" class="col-xs-11" path="accountInf.acinCreatedBy" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="accountInf.acinUpdateBy"> UPDATE BY </label>
			<div class="col-sm-9">
				<sf:input type="text" class="col-xs-11" path="accountInf.acinUpdateBy" />
			</div>
		</div>
	</div>
	<sf:input type="hidden" placeholder="ID" path="accountInf.acinUserId" readonly="true" />
</sf:form>
