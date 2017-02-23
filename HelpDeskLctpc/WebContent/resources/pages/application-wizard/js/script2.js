/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			// Codigo para tabla de pagina
			$("#wizard").steps({
				onStepChanging : function(event, currentIndex, newIndex) {

					// Always allow going backward even if the current step contains invalid fields!
					/*
					 * if (currentIndex > newIndex) { return true; }
					 */

					// Forbid suppressing "Warning" step if the user is to young
					if (newIndex === 1) // paso roles
					{
						if (idApp !== null && idApp !== undefined && idApp != '') {
							$('#tableRoles').DataTable().clear().draw();
							$('#tableRoles').DataTable().ajax.url($(location).attr('origin') + "/HelpDeskLctpc/getJsonRolesApps/" + idApp).load();
						}

						return true;
					}
					if (newIndex === 2) { // paso Pages
						
						buildStep2Page();
						clearFormPage();
						
						return true;
					}
					if (newIndex === 3) { // paso Permission Page
						buildDivLstPages($('#divColPrincipalPages'));
						//buildStep3RolesPage();
						
						return true;
					}
				},
				onStepChanged : function(event, currentIndex, priorIndex) {
					// Suppress (skip) "Warning" step if the user is old enough.
					/*
					 * if (currentIndex === 2) { if (idApp !== null && idApp !== undefined && idApp != '') {
					 * $('#tableRoles').DataTable().clear().draw();
					 * $('#tableRoles').DataTable().ajax.url($(location).attr('origin') +
					 * "/HelpDeskLctpc/getJsonRolesApps/" + idApp).load(); } console.log("entro al index 2 -> " +
					 * currentIndex); $(this).steps("next"); }
					 */

					/*
					 * // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step. if
					 * (currentIndex === 2 && priorIndex === 3) { $(this).steps("previous"); }
					 */
				},

			});

			var idApp = 3;

			var linkApp = $(location).attr('origin') + "/HelpDeskLctpc/getJsonApp/";
			if (idApp !== null && idApp !== undefined && idApp != '') {
				linkApp = linkApp + "/" + idApp;
			}

			$.ajax({
				url : linkApp,
				type : 'GET',
				contentType : "application/json",
				// data : d,
				success : function(data) {
					$('#appnId').val(data.appnId);
					$('#appnName').val(data.appnName);
					$('#appnDescription').val(data.appnDescription);
					$('#appnUrl').val(data.appnUrl);

					getListValuesText({
						idList : '#appnAppnId',
						methodType : 'GET',
						urlWs : $(location).attr('origin') + "/HelpDeskLctpc/getJsonAppsForSelect",
						optionSelect : [ data.appnAppnId ],
						parameters : null,
						errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
						chosen : true
					});

					// getJsonPlatformForSelect
					getListValuesText({
						idList : '#appnPlfmId',
						methodType : 'GET',
						urlWs : $(location).attr('origin') + "/HelpDeskLctpc/getJsonPlatformForSelect",
						optionSelect : [ data.appnPlfmId ],
						parameters : null,
						errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
						chosen : true
					});
				},
				error : function(e) {
					alert("ERROR: ", e);
				}
			});
			
			// ----------------------------------------------------------------------------------------------------------
			// Codigo para tabla de roles appnId
			var tableRoles = $('#tableRoles').DataTable(
					{
						deferRender : false,
						paging : false,
						info : false,
						autoWidth : true,
						select : false,
						searching : false,
						ordering : false,
						stateSave : true,
						scrollY : '25vh',
						scrollCollapse : false,
						columns : [
								{
									title : "Consecutivo",
									data : "roleId"
								},
								{
									title : "Account User",
									data : "roleName"
								},
								{
									title : "Description",
									data : "roleDescription"
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>" + "<a class='green' id='id-btn-edit' href='#' role='button'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
												+ "<a class='red' id='id-btn-delete' href='"
												+ $(location).attr('origin')
												+ "/HelpDeskLctpc/appForm/"
												+ data.roleId
												+ "/delete'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
												+ "</div> "
												+ "<div class='hidden-md hidden-lg'>"
												+ "<div class='inline pos-rel'>"
												+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
												+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>"
												+ "</button>"
												+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
												+ "<li>"
												+ "<a href='#' id='id-btn-edit' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
												+ "<span class='green'>"
												+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='"
												+ $(location).attr('origin')
												+ "/HelpDeskLctpc/appForm/"
												+ data.roleId
												+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "gridSystemModal center"
								} ],
						initComplete : function() {
						},
						fnDrawCallback : function() {
						}
					});

			var counterRoles = 0;
			var isEditRoles = false;

			var RowRoles = null;
			$('#btn-addRole').on('click', function() {
				var d = JSON.stringify({
					roleId : $('#roleId').val() != '' ? new Number($('#roleId').val()) : null,
					roleName : $('#roleName').val(),
					roleDescription : $('#roleDescription').val()
				});

				var link = $(location).attr('origin') + "/HelpDeskLctpc/appWizard/roles/save";
				if (idApp !== null && idApp !== undefined && idApp != '') {
					link = link + "/" + idApp;
				}

				$.ajax({
					url : link,
					type : $('#roleId').val() != '' ? 'PUT' : 'POST',
					contentType : "application/json",
					data : d,
					success : function(data) {
						tableRoles.clear().draw();
						tableRoles.ajax.reload();
					},
					error : function(e) {
						alert("ERROR: ", e);
					}
				});

				$('#roleId').val("");
				$('#roleName').val("");
				$('#roleDescription').val("");
				var row = "";
			});

			$('#tableRoles tbody').on("click", ".gridSystemModal a#id-btn-edit", function() {
				RowRoles = tableRoles.row($(this).parents('tr'));
				FilaActualRoles = RowRoles.data();
				$('#roleId').val(FilaActualRoles.roleId);
				$('#roleName').val(FilaActualRoles.roleName);
				$('#roleDescription').val(FilaActualRoles.roleDescription);

				isEditRoles = true;
			});

			$('#tableRoles tbody').on("click", ".gridSystemModal a#id-btn-delete", function() {
				tableRoles.row($(this).parents('tr')).remove().draw(false);
			});

			
		} // fin funcion de initializeTable

	});
	var idApp = $('#appnId').val();
	$(this).initializeTable();

});

/*
 * $(document).on('click', 'table .dropdown-toggle', function(e) { e.stopImmediatePropagation(); e.stopPropagation();
 * e.preventDefault(); });
 */

function clearFormPage() {
	var idApp = $('#appnId').val();

	$('#divTbPages').addClass('hidden');
	$('#capaLoader').removeClass('hidden');

	$('#tablePages').DataTable().clear().draw();
	$('#tablePages').DataTable().ajax.url($(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesApps/" + idApp).load(function() {

		$('#divTbPages').removeClass('hidden');
		$('#capaLoader').addClass('hidden');

	});

	$('#pageId').val("");
	$('#pageDisplay').val("");
	$('#pageDescription').val("");
	$('#pageUrl').val("");

	getListValuesText({
		idList : '#pagePageId',
		methodType : 'GET',
		urlWs : $(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesForSelect/",
		optionSelect : null,
		parameters : null,
		errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
		chosen : true
	});

	$('#duallist').trigger('bootstrapDualListbox.refresh', true);

	getListValuesText({
		idList : '#duallist',
		methodType : 'GET',
		urlWs : $(location).attr('origin') + "/HelpDeskLctpc/getJsonEntitiesForSelect/",
		optionSelect : null,
		parameters : null,
		errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
		chosen : false,
		dualList : true
	});

}

function getListValuesText(options) {
	// Funcion: Devolvemos la lista de valores correspondiente
	var options = $.extend({
		idList : '',
		methodType : '',
		urlWs : '',
		optionSelect : null,
		parameters : null,
		errorMessage : 'Ha ocurrido un error al cargar el listado',
		chosen : false,
		dualList : false
	}, options);

	var select = $(options.idList);
	var jsonDataList = '';
	if (options.parameters !== null)
		jsonDataList = JSON.stringify(options.parameters);
	jQuery.support.cors = true;

	select.empty();
	select.val(null);

	$.ajax({
		headers : {
			'Content-Type' : "application/json; charset=utf-8"
		},
		crossDomain : true,
		type : options.methodType,
		url : options.urlWs,
		dataType : 'json',
		data : jsonDataList,
		success : function(result) {
			if (options.chosen) { // Add Empty Option
				$(options.idList).append($('<option>', {
					value : "",
					text : ""
				}));
			}
			$.each(result.data, function(i, item) {
				select.append($('<option>', {
					value : item.value,
					text : item.label
				}));

				$.each(options.optionSelect != null ? options.optionSelect : [], function(iOS, itemOptionSelect) {

					if (itemOptionSelect == item.value) {
						var l_x = select.find('option:eq(' + (i + 1) + ')').attr('selected', 'selected');
					}

				});

			});
			select.val(options.optionSelect);
			if (options.chosen)
				select.trigger("chosen:updated");

			if (options.chosen) {
				$('.chosen-select').chosen({
					allow_single_deselect : true,
					width : "100%"
				});
				// .trigger('resize.chosen');
			}

			if (options.dualList) {
				var demo1 = $(options.idList).bootstrapDualListbox({
					infoTextFiltered : '<span class="label label-purple label-lg">Filtered</span>',
					// preserveSelectionOnMove : 'moved',
					moveOnSelect : false,
					eventMoveOverride : true, // boolean, allows user to unbind default event behaviour
					// and run their own instead
					eventMoveAllOverride : true, // boolean, allows user to unbind default event
					// behaviour and run their own instead
					eventRemoveOverride : true, // boolean, allows user to unbind default event behaviour
					// and run their own instead
					eventRemoveAllOverride : true
				});

				var container1 = demo1.bootstrapDualListbox('getContainer');
				container1.find('.btn').addClass('btn-white btn-info btn-bold');

				demo1.trigger('bootstrapDualListbox.refresh', true);
			}

		},
		error : function(e) {
			console.log(options.errorMessage);
			console.log(e);
			swal("Oops...", options.errorMessage, "error");
		}
	});
}

function callAjax(p_url, p_methodType, successFuction) {
	var result = null;
	$.ajax({
		type : p_methodType,
		url : p_url,
		contentType : "application/json",
		async : false,
		// dataType: "json",
		// timeout : 100000,
		success : function(dataJson) {
			successFuction(dataJson);
		},
		error : function(e) {
			alert("ERROR: ", e);
		}
	});

}

function buildPermissionPageRoles() {
	var idApp = $('#appnId').val();
	var idApp = 3;
	var jsonData = new Object();
	var arrayData = [];
	var linkStep4 = $(location).attr('origin') + "/HelpDeskLctpc/getJsonRolesApps/" + idApp;
	var vData = new Object();

	callAjax($(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesApps/" + idApp, 'GET', function(dataJson) {

		$.each(dataJson.data, function(iPage, itemPage) {
			/*
			 * if(itemPage.pageEntities.length <= 0){ callAjax(linkStep4, 'GET', function(dJsonRol) {
			 * 
			 * $.each(dJsonRol.data, function(i, itemRol) { jsonData = new Object(); jsonData.pageId = itemPage.pageId;
			 * jsonData.pageDisplay = itemPage.pageDisplay;
			 * 
			 * jsonData.paenId = '-'; jsonData.enttName = '-'; jsonData.enttId = '-';
			 * 
			 * jsonData.roleId = itemRol.roleId; jsonData.roleName = itemRol.roleName;
			 * 
			 * arrayData.push(jsonData); });
			 * 
			 * }); } else{
			 */
			$.each(itemPage.pageEntities, function(iEnt, itemEntity) {
				callAjax(linkStep4, 'GET', function(dJsonRol) {
					$.each(dJsonRol.data, function(i, itemRol) {
						jsonData = new Object();
						jsonData.pageId = itemPage.pageId;
						jsonData.pageDisplay = itemPage.pageDisplay;

						jsonData.paenId = itemEntity.paenId;
						jsonData.enttName = ((itemEntity.paenEnttId instanceof Object) ? itemEntity.paenEnttId.enttName : itemEntity.enttName);
						jsonData.enttId = ((itemEntity.paenEnttId instanceof Object) ? itemEntity.paenEnttId.enttId : itemEntity.enttId);

						jsonData.roleId = itemRol.roleId;
						jsonData.roleName = itemRol.roleName;

						arrayData.push(jsonData);
					});
				});
			});
			// }//fin else
		});

	});
	vData.data = arrayData;
	return vData;

}

function buildDivLstPages(p_divFather) {
	var idApp = $('#appnId').val();

	callAjax($(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesApps/" + idApp, 'GET', function(dataJson) {
		// jsonPages = dataJson;

		var divRowPage = $("<div class='row'>");
		$.each(dataJson.data, function(iPage, itemPage) {

			//if (iPage % 2 === 0)
				divRowPage = $("<div class='row'>");

			var divColPage = $("<div class='col-lg-12'>");
			
			var divIbox = $("<div class='ibox float-e-margins'>");
			
			var divIboxTitle = $("<div class='ibox-title'>").append("<h5> ["+itemPage.pageId+"] "+itemPage.pageDisplay+"</h5>");
			var divIboxContent = $("<div class='ibox-content'>");
			
			var divPanelBody = $("<div class='panel-body' id='panel"+itemPage.pageId+"'>");
			divIboxContent.append(divPanelBody);
			
			divIbox.append(divIboxTitle);
			divIbox.append(divIboxContent);
			divColPage.append(divIbox);
			divRowPage.append(divColPage);
			
			buildDivRoles(divPanelBody, itemPage.pageEntities);
			
			p_divFather.append(divRowPage);

			// -------------------------------------------------------------------
			/*
			 * var titlePage = $("<h3 class='header smaller red'><i class='ace-icon fa fa-list-alt'></i>"+itemPage.pageDisplay+"</h3>");
			 * 
			 * p_divFather.append(titlePage); p_divFather.append(divRowPage);
			 * 
			 * p_divFather.append("<div class='space-24'></div>");
			 * 
			 * var divColPage = $("<div class='col-xs-4 col-sm-3 pricing-span-header'>"); var divWidgetBoxPage = $("<div
			 * class='widget-box transparent'>"); var divWidgetHeaderPage = $("<div class='widget-header'>").append( "<h5 class='widget-title'>Permission
			 * of Page</h5>"); var divWidgetMain = $("<div class='widget-main no-padding'>"); var divWidgetBodyPage =
			 * $("<div class='widget-body'>").append(divWidgetMain); var ulLstPermission = $("<ul class='list-unstyled list-striped pricing-table-header' id='ul_Permission'>");
			 * 
			 * buildDivLstPermission(ulLstPermission);
			 * 
			 * var divColRoles = $("<div class='col-xs-8 col-sm-9 pricing-span-body'>"); buildDivRoles(divColRoles,
			 * itemPage.pageEntities);
			 * 
			 * divWidgetMain.append(ulLstPermission); divWidgetBoxPage.append(divWidgetHeaderPage);
			 * divWidgetBoxPage.append(divWidgetBodyPage);
			 * 
			 * 
			 * divColPage.append(divWidgetBoxPage); divRowPage.append(divColPage); divRowPage.append(divColRoles);
			 */
		}); // fin earch Pages
	}); // callAjax
	// return p_divFather;
}

function buildDivLstPermission(p_divFather) {
	var linkStep4Permission = $(location).attr('origin') + "/HelpDeskLctpc/getJsonPermissionActive/";

	callAjax(linkStep4Permission, 'GET', function(dJsonPerm) {
		
		var divFormGroup = $("<div class='form-group'>");
		$.each(dJsonPerm.data, function(iPerm, itemPerm) {
			//var div = $("<div>"); i-checks
			var divLabelPermission = $("<label class='checkbox-inline '>");
			var inputCheckBox = $('<input>', {
			    type:"checkbox",
			    id : "cbx_"+itemPerm.prmnId,
			    //"checked":"checked"
			}).val(itemPerm.prmnId);
			
			
			divLabelPermission.append(inputCheckBox);
			divLabelPermission.append(itemPerm.prmnName);
			//div.append(divLabelPermission);
			//divFormGroup.append(div);
			divFormGroup.append(divLabelPermission);
			p_divFather.append(divFormGroup);
		});// Fin ciclo Permission
		
	});
	
	$('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });

}

function buildDivRoles(p_divFather, p_Entities) {
	
	var idApp = $('#appnId').val();
	var linkStep4 = $(location).attr('origin') + "/HelpDeskLctpc/getJsonRolesApps/" + idApp;
	var jsonRoles;
	var widgetColor = [ 'danger', 'primary', 'success', 'info', 'warning', 'default' ];
	callAjax(linkStep4, 'GET', function(dJsonRol) {
		jsonRoles = dJsonRol;
	});
	//var lstTemp = $("<ul class='list-unstyled list-striped pricing-table-header' id='tempPermission'>");
	//buildDivLstPermission(lstTemp);
	 var divPanelGroupAccordion = $("<div class='panel-group' id='accordion'>");
	$.each(jsonRoles.data, function(i, itemRol) {
		var jsonPages;
		var divPanelRol = $("<div class='panel panel-"+widgetColor[i]+"'>");
		var divPanelRolHeading = $("<div class='panel-heading'>").append("<h5 class='panel-title'><a data-toggle='collapse' data-parent='#accordion' href='#collapse_"+p_divFather.attr("id")+"_"+itemRol.roleId+"'>"+itemRol.roleName+"</a></h5>");
		
		var divPanelCollapse =$("<div id='collapse_"+p_divFather.attr("id")+"_"+itemRol.roleId+"' class='panel-collapse collapse'>");
		//var divPanelBody = $("<div class='panel-body'>").text("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		var divPanelBody = $("<div class='panel-body'>");
		
		var divTableEntity = $("<table class='table small m-b-xs'>");
		var divTableBodyEntity = $("<tbody>");
		$.each(p_Entities, function(iEnt, itemEntity){
			var divRowEntity = $('<tr>');
			divRowEntity.append("<td><strong>"+ ((itemEntity.paenEnttId instanceof Object) ? itemEntity.paenEnttId.enttName : itemEntity.enttName)+"</strong></td>" );
			var divColEntity = $('<td>');
			buildDivLstPermission(divColEntity);
			divRowEntity.append(divColEntity);
			divTableBodyEntity.append(divRowEntity);
		});
		
		divTableEntity.append(divTableBodyEntity);
		divPanelBody.append(divTableEntity);
		
		
		divPanelCollapse.append(divPanelBody);
		divPanelRol.append(divPanelRolHeading);
		divPanelRol.append(divPanelCollapse);
		divPanelGroupAccordion.append(divPanelRol);
		
		
		
	});
	p_divFather.append(divPanelGroupAccordion);
}

function buildStep2Page() {
	
	var tablePages = $('#tablePages').DataTable(
			{
				// paging : false,
				// info : false,
				autoWidth : true,
				searching : true,
				ordering : true,
				destroy : true,
				lengthChange : false,
				pageLength : 6,
				responsive : true,
				dom : "<'row'<'col-lg-5'f><'col-lg-7 html5buttons'B> lTgt>" + "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
				buttons : [ {
					text : 'Actualizar',
					action : function(e, dt, node, config) {
						dt.ajax.reload();
					}
				}, {
					"extend" : 'copy',
					"text" : 'Copiar'
				}, {
					"extend" : 'csv'
				}, {
					"extend" : 'pdf',
					"text" : 'Pdf'
				}, {
					"extend" : 'print',
					"customize" : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size', '10px');
						$(win.document.body).find('table').addClass('compact').css('font-size', 'inherit');
					}
				} ],
				columns : [
						{
							title : "ID",
							data : "pageId",
							orderable : true
						},
						{
							title : "Name",
							data : "pageDisplay",
							orderable : true
						},
						{
							title : "Description",
							data : "pageDescription",
							orderable : false
						},
						{
							title : "Url",
							data : "pageUrl",
							orderable : false
						},
						{
							title : "Action",
							data : null,
							orderable : false,
							render : function(data, type, row) {
								return "<div class='hidden-sm hidden-xs action-buttons'>" + "<a class='' id='id-btn-edit' href='#' ><i class='fa fa-pencil'></i></a>" + "<a class='' id='id-btn-deleteAssoc' href='"
										+ $(location).attr('origin') + "/HelpDeskLctpc/appWizard/page/delete/" + data.pageId + "' role='button'> <i class='fa fa-chain-broken'></i></a>"
										+ "<a class='' id='id-btn-delete' href='" + $(location).attr('origin') + "/HelpDeskLctpc/appWizard/page/delete/" + data.pageId + "'><i class='fa fa-trash-o'></i></a>" + "</div> "
										+ "<div class='hidden-md hidden-lg'>" + "<div class='btn-group'>" + "<button class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>"
										+ "<i class='fa fa-cog icon-only'></i>" + "</button>" + "<ul class='dropdown-menu'>" + "<li>"
										+ "<a href='#' id='id-btn-edit' role='button' data-toggle='modal' class='btn-circle tooltip-success' data-rel='tooltip' title='Edit'>"
										+ "<i class='fa fa-pencil-square-o'></i></a>" + "</li>" + "<li>" + "<a href='" + $(location).attr('origin') + "/HelpDeskLctpc/appWizard/page/delete/" + data.pageId
										+ "' id='id-btn-deleteAssoc' class='btn-circle tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>" + "<i class='fa fa-chain-broken'></i>" + "</a>" + "</li>"
										+ "<li>" + "<a href='" + $(location).attr('origin') + "/HelpDeskLctpc/appWizard/page/delete/" + data.pageId
										+ "' id='id-btn-delete' class='btn-circle tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>" + "<i class='fa fa-trash-o'></i></a>" + "</li>"

										+ "</ul>" + "</div>" + "</div>";
							},
							className : "gridSystemModal center"
						// width : '70px'
						}

				],

				initComplete : function() {

				},
				fnDrawCallback : function() {

				}
			});

	$('#tablePages tbody').on("click", ".gridSystemModal a#id-btn-edit", function() {
		var filaActualPage = tablePages.row($(this).parents('tr')).data();
		var l_entityOptSel = [];
		$('#pageId').val(filaActualPage.pageId);
		$('#pageDisplay').val(filaActualPage.pageDisplay);
		$('#pageDescription').val(filaActualPage.pageDescription);
		$('#pageUrl').val(filaActualPage.pageUrl);

		$.each(filaActualPage.pageEntities != null ? filaActualPage.pageEntities : [], function(i, item) {
			l_entityOptSel.push(item.paenEnttId instanceof Object ? item.paenEnttId.enttId : item.paenEnttId);
		});

		getListValuesText({
			idList : '#pagePageId',
			methodType : 'GET',
			urlWs : $(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesForSelect/",
			optionSelect : [ (filaActualPage.pagePageId instanceof Object ? filaActualPage.pagePageId.pageId : filaActualPage.pagePageId) ],
			parameters : null,
			errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
			chosen : true
		});

		$('#duallist').trigger('bootstrapDualListbox.refresh', true);

		getListValuesText({
			idList : '#duallist',
			methodType : 'GET',
			urlWs : $(location).attr('origin') + "/HelpDeskLctpc/getJsonEntitiesForSelect/",
			optionSelect : l_entityOptSel.length !== 0 ? l_entityOptSel : null,
			parameters : null,
			errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
			chosen : false,
			dualList : true
		});

	});

	$('#tablePages tbody').on("click", ".gridSystemModal a#id-btn-deleteAssoc", function(e) {
		var linkDelete = this;
		if (idApp !== null && idApp !== undefined && idApp != '') {
			linkDelete = linkDelete + "/" + idApp;
		}
		e.preventDefault(); // elimina el evento del link.

		swal({
			title : "Are you sure?",
			text : "Do you want to disassociate the application of page ?, The page will not be deleted!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, disassociated it!",
			cancelButtonText : "No, cancel plx!",
			closeOnConfirm : false,
			closeOnCancel : false
		}, function(isConfirm) {
			if (isConfirm) {
				$.ajax({
					url : linkDelete,
					success : function(result) {
						clearFormPage();
					}
				});
				swal("Deleted!", "The application of page has been disassociated.", "success");
			} else {
				swal("Cancelled", "The application of page has not been disassociated :)", "error");
			}
		});

	});

	$('#btn-addPage').on('click', function() {
		var d = '';
		var l_a = [];
		var l_methodType = 'POST';

		$.each($('#duallist').val() != null ? $('#duallist').val() : [], function(i, item) {
			l_a.push({
				paenEnttId : {
					enttId : new Number(item)
				}
			});
		});

		l_methodType = $('#pageId').val() != '' ? 'PUT' : 'POST';

		d = JSON.stringify({
			pageId : $('#pageId').val() != '' ? new Number($('#pageId').val()) : null,
			pagePageId : $('#pagePageId').val() != '' ? {
				pageId : new Number($('#pagePageId').val())
			} : null,
			pageDisplay : $('#pageDisplay').val(),
			pageDescription : $('#pageDescription').val(),
			pageUrl : $('#pageUrl').val(),
			pageActive : true,
			pageEntities : l_a,
			applications : [ {
				appnId : new Number(idApp)
			} ]
		});

		var link = $(location).attr('origin') + "/HelpDeskLctpc/appWizard/page/save";
		if (idApp !== null && idApp !== undefined && idApp != '') {
			link = link + "/" + idApp;
		}

		$.ajax({
			url : link,
			type : $('#pageId').val() != '' ? 'PUT' : 'POST',
			contentType : "application/json",
			data : d,
			success : function(data) {
				tablePages.clear().draw();
				tablePages.ajax.reload();
			},
			error : function(e) {
				alert("ERROR: ", e);
			}
		});

		clearFormPage();

	});

	$('#btn-resetPage').on('click', function() {
		clearFormPage();
	});
}

function buildStep3RolesPage() {
	var idApp = $('#appnId').val();
	// Table Permission
	var v_columns = [];
	callAjax($(location).attr('origin') + "/HelpDeskLctpc/getJsonPermissionRolPageActive/" + idApp, 'GET', function(dataJson) {
		$.each(dataJson.columns, function(i, item) {
			var title = new Object();
			title.title = item.title;

			if (item.render === true) {
				title.data = null;
				title.render = function(data, type, row) {
					$.each(item.data, function(i, itemPerm){
						console.log(itemPerm);
					});
					return "<div><label class='checkbox-inline'> <input type='checkbox' value='option1' id='inlineCheckbox1'>" + item.data + "</label> </div>";
				};
			} else {
				title.data = item.data;
			}

			v_columns.push(title);

		});
	});

	var tablePermission = $('#dataTables-Permission').DataTable({
		// paging : false,
		info : false,
		autoWidth : true,
		searching : true,
		ordering : true,
		destroy : true,
		lengthChange : true,
		pageLength : 10,
		responsive : true,
		dom : "<'row'<'col-lg-5'f><'col-lg-7 html5buttons'B> lTgt>" + "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
		dom : '<"html5buttons"B>lTfgitp',
		buttons : [ {
			text : 'Actualizar',
			action : function(e, dt, node, config) {
				dt.ajax.reload();
			}
		}, {
			"extend" : 'copy',
			"text" : 'Copiar'
		}, {
			"extend" : 'csv'
		}, {
			"extend" : 'pdf',
			"text" : 'Pdf'
		}, {
			"extend" : 'print',
			"customize" : function(win) {
				$(win.document.body).addClass('white-bg');
				$(win.document.body).css('font-size', '10px');
				$(win.document.body).find('table').addClass('compact').css('font-size', 'inherit');
			}
		} ],
		ajax : {
			url : $(location).attr('origin') + "/HelpDeskLctpc/getJsonPermissionRolPageActive/3",
			type : "GET"

		},
		columns : v_columns,
		initComplete : function() {

		},
		fnDrawCallback : function() {

		}
	});
}

function buildStep3RolPages(){
	var v_columns = [];
	callAjax($(location).attr('origin') + "/HelpDeskLctpc/getJsonPermissionRolPageActive/" + idApp, 'GET', function(dataJson) {
		$.each(dataJson.columns, function(i, item) {
			var title = new Object();
			title.title = item.title;

			if (item.render === true) {
				title.data = null;
				title.render = function(data, type, row) {
					$.each(item.data, function(i, itemPerm){
						console.log(itemPerm);
					});
					return "<div><label class='checkbox-inline'> <input type='checkbox' value='option1' id='inlineCheckbox1'>" + item.data + "</label> </div>";
				};
			} else {
				title.data = item.data;
			}

			v_columns.push(title);

		});
	});
}




