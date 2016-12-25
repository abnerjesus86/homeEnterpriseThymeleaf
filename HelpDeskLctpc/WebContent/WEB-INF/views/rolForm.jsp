<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/rolFormulario/save"
	method="post" commandName="rol" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="userId">
			ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="col-xs-11"
				path="roleId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="roleName">
			Name </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="roleName" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="roleDescription"> Description </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="roleDescription" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 col-xs-3 control-label no-padding-right"
			for="roleActive"> ACTIVE </label>
		<div>
			<label class="middle col-sm-9 col-xs-9"> <sf:checkbox
					path="roleActive" class="ace ace-switch ace-switch-6" value="true" />
				<span class="lbl"></span>
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="roleCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="roleCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="roleUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="roleUpdateBy" />
		</div>
	</div>
</sf:form>
<!--
	<c:if test="${not empty appAsigned}">
        <table>
            <tr>
                <th>ID Application</th>
                <th>Application</th>
                <th>Asignado por:</th>
                <th>Asignado el:</th>
                <th>Modificado por:</th>
                <th>Modificado el:</th>
                <th>Activo</th>
            </tr>
            <c:forEach items="${appAsigned}" var="appRol">
                <tr>
                    <td>${appRol.aproId}</td>
                    <td>${appRol.aproAppnId.appnName}</td>
                    <td>${appRol.aproCreatedBy}</td>
                    <td>${appRol.aproCreatedDate}</td>
                    <td>${appRol.aproUpdateBy}</td>
                    <td>${appRol.aproUpdateDate}</td>
                    <td><input type="checkbox" value="${appRol.aproActive}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br />
    <br />
    <c:if test="${not empty usersAsigned}">
        <table class="table">
            <tr>
                <th>ID User</th>
                <th>Name</th>
                <th>Num Employee</th>
                <th>BU</th>
                <th>Asignado por:</th>
                <th>Asignado el:</th>
                <th>Modificado por:</th>
                <th>Modificado el:</th>
                <th>Activo</th>
            </tr>
            <c:forEach items="${usersAsigned}" var="appUser">
                <tr>
                    <td>${appUser.usroId}</td>
                    <td>${appUser.usroUserId.userUsername}</td>
                    <td>${appUser.usroUserId.userEmesId}</td>
                    <td>${appUser.usroUserId.userEmesCompany}</td>
                    <td>${appUser.usroCreatedBy}</td>
                    <td>${appUser.usroCreatedDate}</td>
                    <td>${appUser.usroUpdateBy}</td>
                    <td>${appUser.usroUpdateDate}</td>
                    <c:set value="checked" var="isActivoUser"></c:set>
                    <td><input type="checkbox" value="${appUser.usroActive}"
                        <c:if test="${appUser.usroActive}" > <c:out value="checked" /> </c:if> /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
 </body>
</html> -->