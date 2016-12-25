<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sf:form action="${pageContext.request.contextPath}/appForm/save" method="post" commandName="appn" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="col-xs-11" path="appnId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnName"> NAME </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="appnName" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnDescription"> DESCRIPTION </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="appnDescription" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnUrl"> URL </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="appnUrl" />
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
			<label class="middle"> <sf:checkbox path="appnActive" class="ace ace-switch ace-switch-6" value="true" /> <span class="lbl"></span>
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="appnCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="appnUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="appnUpdateBy" />
		</div>
	</div>
</sf:form>

<script type="text/javascript">
jQuery(function($) {
    if(!ace.vars['touch']) {
        $('.chosen-select').chosen({allow_single_deselect:true}); 
        //resize the chosen on window resize

        $(window)
        .off('resize.chosen')
        .on('resize.chosen', function() {
            $('.chosen-select').each(function() {
                 var $this = $(this);
                 $this.next().css({'width': $this.parent().width()});
            })
        }).trigger('resize.chosen');
        //resize chosen on sidebar collapse/expand
        $(document).on('settings.ace.chosen', function(e, event_name, event_val) {
            if(event_name != 'sidebar_collapsed') return;
            $('.chosen-select').each(function() {
                 var $this = $(this);
                 $this.next().css({'width': $this.parent().width()});
            })
        });

    }
    
    //chosen plugin inside a modal will have a zero width because the select element is originally hidden
    //and its width cannot be determined.
    //so we set the width after modal is show
    $('#formularioModal').on('shown.bs.modal', function () {
        if(!ace.vars['touch']) {
            $(this).find('.chosen-container').each(function(){
                $(this).find('a:first-child').css('width' , '210px');
                $(this).find('.chosen-drop').css('width' , '210px');
                $(this).find('.chosen-search input').css('width' , '200px');
            });
        }
    })
    
});
</script>

