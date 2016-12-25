<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/pageForm/save" method="post" commandName="page" cssClass="form-horizontal" role="form">

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pageId"> ID </label>
		<div class="col-sm-9">
			<sf:input type="text" placeholder="ID" class="col-xs-11" path="pageId" readonly="true" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pagePageId"> PAGE FATHER </label>
		<div class="col-sm-9">
			<sf:select path="pagePageId" cssClass="chosen-select col-xs-11" data-placeholder="Choose a Page Father o Master...">
				<sf:option value="NONE" label="---- Select ----" />
				<sf:options items="${pagesMasterList}" itemLabel="pageDisplay" itemValue="pageId" />
			</sf:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pageDisplay"> Name </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="pageDisplay" />
		</div>
	</div>
	<%-- <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="pageNomenclature"> Nomenclature </label>
            <div class="col-sm-9">
                <sf:input type="text" class="col-xs-11" path="pageNomenclature" />
            </div>
        </div> --%>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pageDescription"> Descripcion </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="pageDescription" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pageUrl"> Url </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="pageUrl" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="food">Entities</label>

		<div class="col-sm-9">
			<!-- #section:plugins/input.multiselect -->
			<sf:select path="pageEntities" class="multiselect" multiple="true"  >
				<c:forEach items="${entitiesList}" var="ent">
					<c:set var="isSelected" value="false" />
					<c:forEach items="${page.pageEntities}" var="pagEnt">
						<c:if test="${pagEnt.paenEnttId.enttId eq ent.enttId}">
							<c:set var="isSelected" value="true" />
							<c:set var="idPageEntity" value="${pagEnt.paenId}" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${isSelected}">
							<option value="${idPageEntity}|${ent.enttId}" selected="selected">${ent.enttName}</option>
						</c:when>
						<c:otherwise>
							<option value="NEW|${ent.enttId}">${ent.enttName} hola</option>
						</c:otherwise>
					</c:choose>
				</c:forEach> 
				<%-- <sf:options items="${entitiesList}" itemLabel="enttName" itemValue="idAsString" /> --%>
			</sf:select>

			<!-- /section:plugins/input.multiselect -->
		</div>
	</div>
	<%-- <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="food">Entities</label>

        <div class="col-sm-9">
            <!-- #section:plugins/input.multiselect -->
            <sf:select path="entities" class="multiselect" multiple="true">
                <!-- itemLabel="enttName" class="multiselect" itemValue="enttId" ${page.pageEntities}  -->
                <sf:options items="${entitiesList}" itemLabel="enttName" itemValue="enttId" />
            </sf:select>

            <!-- /section:plugins/input.multiselect -->
        </div>
    </div> --%>

	<div class="form-group">
		<label class="col-sm-3 col-xs-3 control-label no-padding-right" for="pageActive"> ACTIVE </label>
		<div>
			<label class="middle col-sm-9 col-xs-9"> <sf:checkbox path="pageActive" class="ace ace-switch ace-switch-6" value="true" /> <span
				class="lbl"></span>
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pageCreatedBy"> CREATED BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="pageCreatedBy" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="pageUpdateBy"> UPDATE BY </label>
		<div class="col-sm-9">
			<sf:input type="text" class="col-xs-11" path="pageUpdateBy" />
		</div>
	</div>
</sf:form>


<script type="text/javascript">
	jQuery(function($) {
		$('.multiselect')
				.multiselect(
						{
							enableFiltering : true,
							enableHTML : true,
							buttonClass : 'btn btn-white btn-primary',
							templates : {
								button : '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
								ul : '<ul class="multiselect-container dropdown-menu"></ul>',
								filter : '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
								filterClearBtn : '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
								li : '<li><a tabindex="0"><label></label></a></li>',
								divider : '<li class="multiselect-item divider"></li>',
								liGroup : '<li class="multiselect-item multiselect-group"><label></label></li>'
							}
						});

		if (!ace.vars['touch']) {
			$('.chosen-select').chosen({
				allow_single_deselect : true
			});
			//resize the chosen on window resize

			$(window).off('resize.chosen').on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					var $this = $(this);
					$this.next().css({
						'width' : $this.parent().width()
					});
				})
			}).trigger('resize.chosen');
			//resize chosen on sidebar collapse/expand
			$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
				if (event_name != 'sidebar_collapsed')
					return;
				$('.chosen-select').each(function() {
					var $this = $(this);
					$this.next().css({
						'width' : $this.parent().width()
					});
				})
			});

		}

		//chosen plugin inside a modal will have a zero width because the select element is originally hidden
		//and its width cannot be determined.
		//so we set the width after modal is show
		$('#formularioModal').on('shown.bs.modal', function() {
			if (!ace.vars['touch']) {
				$(this).find('.chosen-container').each(function() {
					$(this).find('a:first-child').css('width', '210px');
					$(this).find('.chosen-drop').css('width', '210px');
					$(this).find('.chosen-search input').css('width', '200px');
				});
			}
		})

	});
</script>