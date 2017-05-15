<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/entityForm/save"
        method="post" commandName="entt" cssClass="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttId"> ID </label>
            <div class="col-sm-9">
                <sf:input type="text" placeholder="ID" class="form-control" path="enttId" readonly="true" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttName"> Name </label>
            <div class="col-sm-9">
                <sf:input type="text" class="form-control" path="enttName" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttDescription"> Descripcion </label>
            <div class="col-sm-9">
                <sf:textarea class="form-control" path="enttDescription" rows="3" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 col-xs-3 control-label no-padding-right" for="enttActive"> ACTIVE </label>
            <div class="col-sm-9">
                <div class="i-checks">
                  <label> 
                      <sf:checkbox path="enttActive" value="true" />
                      <i></i>
                  </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttCreatedBy"> CREATED BY </label>
            <div class="col-sm-9">
                <sf:input type="text" class="form-control" path="enttCreatedBy" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="enttUpdateBy"> UPDATE BY </label>
            <div class="col-sm-9">
                <sf:input type="text" class="form-control" path="enttUpdateBy" />
            </div>
        </div>
    </sf:form>
