<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/secretQuestionForm/save" method="post" commandName="sctQ" cssClass="form-horizontal" role="form">

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="form-control" path="sequId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequQuestion"> Question </label>
		<div class="col-sm-9">
			<sf:textarea class="form-control" path="sequQuestion" rows="3" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequActive"> ACTIVE </label>
		<div class="col-sm-9">
		  <div class="i-checks">
                  <label> 
                      <sf:checkbox path="sequActive" value="true" />
                      <i></i>
                  </label>
              </div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="sequCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sequUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="form-control" path="sequUpdateBy" />
		</div>
	</div>

</sf:form>
<!-- </body>
</html> -->