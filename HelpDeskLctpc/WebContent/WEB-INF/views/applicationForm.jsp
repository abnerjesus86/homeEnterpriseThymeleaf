<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sf:form action="${pageContext.request.contextPath}/appForm/save" method="post" commandName="appn" cssClass="form-horizontal" role="form">
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="form-control" path="appnId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnName"> NAME </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="appnName" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnDescription"> DESCRIPTION </label>
		<div class="col-sm-9">
			<sf:textarea class="form-control" path="appnDescription" rows="3" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnUrl"> URL </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="appnUrl" />
		</div>
	</div>
	<div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="appnAppnId"> APPLICATION FATHER </label>
        <div class="col-sm-9">
            <sf:select path = "appnAppnId" cssClass="chosen-select col-xs-11" data-placeholder="Choose a Application Father o Master..." >
                <sf:option value="NONE" label="---- Select ----" />
                <sf:options items="${appnsMasterList}" itemLabel="appnName" itemValue="appnId" />
            </sf:select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="appnPlfmId"> PLATFORM </label>
        <div class="col-sm-9">
            <sf:select path = "appnPlfmId" items="${platformList}" cssClass="chosen-select" data-placeholder="Choose a Platform..."/>
        </div>
    </div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnActive"> ACTIVE </label>
		<div class="col-sm-9">
		      <div class="i-checks">
			      <label> 
			          <sf:checkbox path="appnActive" value="true" />
			          <i></i>
			      </label>
		      </div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="appnCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="appnUpdateBy" />
		</div>
	</div>
</sf:form>

<script type="text/javascript">
jQuery(function($) {
	
	/* $('.chosen-select').chosen({
        allow_single_deselect : true,
        width : "100%"
    });
	$('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    }); */
});
</script>

