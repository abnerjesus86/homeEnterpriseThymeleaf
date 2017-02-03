/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			// Codigo para tabla de pagina
			var tablePages = $('#tablePages').DataTable(
					{
						paging : false,
						info : false,
						autoWidth : true,
						searching : false,
						ordering : false,
						stateSave : true,
						scrollY : '57vh',
						scrollCollapse : true,
						columns : [
								{
									title : "ID",
									data : "pageId"
								},
								/*
								 * { title : "Nomenclature", data : "pageDisplay" },
								 */
								{
									title : "Name",
									data : "pageDisplay"
								},
								{
									title : "Description",
									data : "pageDescription"
								},
								{
									title : "Url",
									data : "pageUrl"
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>" + "<a class='green' id='id-btn-edit' href='#' role='button'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
												+ "<a class='purple' id='id-btn-deleteAssoc' href='"
												+ $(location).attr('origin')
												+ "/HelpDeskLctpc/appWizard/page/delete/"
												+ data.pageId
												+ "'> <i class='ace-icon fa fa-chain-broken bigger-130'></i></a>"
												+ "<a class='red' id='id-btn-delete' href='"
												+ $(location).attr('origin')
												+ "/HelpDeskLctpc/appWizard/page/delete/"
												+ data.pageId
												+ "'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
												+ "</div> "
												+ "<div class='hidden-md hidden-lg'>"
												+ "<div class='inline pos-rel'>"
												+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
												+ "<i class='ace-icon fa fa-cog icon-only bigger-120'></i>"
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
												+ "/HelpDeskLctpc/appWizard/page/delete/"
												+ data.pageId
												+ "' id='id-btn-deleteAssoc' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='purple'>"
												+ "<i class='ace-icon fa fa-chain-broken bigger-120'></i>"
												+ "</span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='"
												+ $(location).attr('origin')
												+ "/HelpDeskLctpc/appWizard/page/delete/"
												+ data.pageId
												+ "' id='id-btn-delete' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "gridSystemModal center",
									width : '95px'
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
					console.log("Tipo de dato " + item.paenEnttId);
					l_entityOptSel.push( item.paenEnttId instanceof Object ? item.paenEnttId.enttId : item.paenEnttId );
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
				showModalConfirmation(linkDelete, tablePages);
				clearFormPage();
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
				console.log(idApp);
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
			// --------------------------------------------------------------------------------------------------------------
			// Codigo tabla permison por Roles/Page

			// -------------------------------------------------------------------------------------------------------------
			$('[data-rel=tooltip]').tooltip();

			var $validation = false;
			$('#wizard-application').wizard({
			// step: 2 //optional argument. wizard will jump to step "2" at first
			// buttons: '.wizard-actions:eq(0)'
			}).on(
					'changed.fu.wizard',
					function(evt, item) {
						if (item.step == 2) {
							if (idApp !== null && idApp !== undefined && idApp != '') {
								$('#tableRoles').DataTable().clear().draw();
								$('#tableRoles').DataTable().ajax.url($(location).attr('origin') + "/HelpDeskLctpc/getJsonRolesApps/" + idApp).load();
							}
						}
						if (item.step == 3) {
							if (idApp !== null && idApp !== undefined && idApp != '') {
								$('#tablePages').DataTable().clear().draw();
								$('#tablePages').DataTable().ajax.url($(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesApps/" + idApp).load();
							}
							clearFormPage();
						}
						if (item.step == 4) {
							var divRoles = $('#rolesDiv').empty();
							var divCol = $("<div class='col-xs-12'>");
							
							buildDivLstPages(divCol);
							divRoles.append(divCol);
							
							// clearFormPage();
						}
					}).on('actionclicked.fu.wizard', function(e, item) {
				/*
				 * if (item.step == 1 && $validation) { if (!$('#validation-form').valid()) e.preventDefault(); }
				 */
			}).on('finished.fu.wizard', function(e) {
				bootbox.dialog({
					message : "Thank you! Your information was successfully saved!",
					buttons : {
						"success" : {
							"label" : "OK",
							"className" : "btn-sm btn-primary"
						}
					}
				});
			}).on('stepclick.fu.wizard', function(e) {
				// e.preventDefault();//this will prevent clicking and selecting steps
				console.log("da click");
			});

		} // fin funcion de initializeTable

	});
	var idApp = $('#appnId').val();
	$(this).initializeTable();

	// in ajax mode, remove remaining elements before leaving page
	/*
	 * $(document).one('ajaxloadstart.page', function(e) {
	 * $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox('destroy'); });
	 */
});

$(document).on('click', 'table .dropdown-toggle', function(e) {
	e.stopImmediatePropagation();
	e.stopPropagation();
	e.preventDefault();
});

// override dialog's title function to allow for HTML titles
$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
	_title : function(title) {
		var $title = this.options.title || '&nbsp;'
		if (("title_html" in this.options) && this.options.title_html == true)
			title.html($title);
		else
			title.text($title);
	}
}));

function clearFormPage() {
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

			if (!ace.vars['touch'] && options.chosen) {
				$('.chosen-select').chosen({
					allow_single_deselect : true
				});
				$(window).off('resize.chosen').on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						var $this = $(this);
						$this.next().css({
							'width' : $this.parent().width()
						});
					})
				}).trigger('resize.chosen');
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

function showModalConfirmation(p_url, p_table) {
	$("#dialog-confirm").removeClass('hide').dialog({ // Abrir div del modal
		resizable : false,
		width : '320',
		modal : true,
		title : "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> Empty the recycle bin?</h4></div>",
		title_html : true,
		buttons : [ {
			html : "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; Delete",
			"class" : "btn btn-danger btn-minier",
			click : function() {
				$.ajax({
					url : p_url,
					success : function(result) {
						if (!(result === null)) {
							p_table.clear().draw();
							p_table.ajax.reload();
						}
					}
				});
				$(this).dialog("close");
			}
		}, {
			html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; Cancel",
			"class" : "btn btn-minier",
			click : function() {
				$(this).dialog("close");

			}
		} ]
	// Cierre de botones del modal
	});// Fin del bloque para activar el cuadro de dialogo le remueve la clase oculta
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

function buildDivLstPages(p_divFather){
	var idApp = $('#appnId').val();
	
	callAjax($(location).attr('origin') + "/HelpDeskLctpc/getJsonPagesApps/" + idApp, 'GET', function(dataJson) {
		//jsonPages = dataJson;
		
		
		$.each(dataJson.data, function(iPage, itemPage) {
			var titlePage = $("<h3 class='header smaller red'><i class='ace-icon fa fa-list-alt'></i>"+itemPage.pageDisplay+"</h3>");		
			var divRowPage = $("<div class='row'>");
			p_divFather.append(titlePage);
			p_divFather.append(divRowPage);

			p_divFather.append("<div class='space-24'></div>");
			
			var divColPage = $("<div class='col-xs-4 col-sm-3 pricing-span-header'>");
			var divWidgetBoxPage = $("<div class='widget-box transparent'>");
			var divWidgetHeaderPage = $("<div class='widget-header'>").append(
					"<h5 class='widget-title'>Permission of Page</h5>");
			var divWidgetMain = $("<div class='widget-main no-padding'>");
			var divWidgetBodyPage = $("<div class='widget-body'>").append(divWidgetMain);
			var ulLstPermission = $("<ul class='list-unstyled list-striped pricing-table-header' id='ul_Permission'>");
			
			buildDivLstPermission(ulLstPermission);
			
			var divColRoles = $("<div class='col-xs-8 col-sm-9 pricing-span-body'>");
			buildDivRoles(divColRoles, itemPage.pageEntities);
	
			divWidgetMain.append(ulLstPermission);
			divWidgetBoxPage.append(divWidgetHeaderPage);
			divWidgetBoxPage.append(divWidgetBodyPage);
			
			
			divColPage.append(divWidgetBoxPage);
			divRowPage.append(divColPage);
			divRowPage.append(divColRoles);
			
		}); // fin earch Pages
	}); // callAjax
	//return p_divFather;
}

function buildDivLstPermission(p_divFather){
	var linkStep4Permission = $(location).attr('origin') + "/HelpDeskLctpc/getJsonPermissionActive/";

	callAjax(linkStep4Permission, 'GET', function(dJsonPerm){
		$.each(dJsonPerm.data, function(iPerm, itemPerm){
			var li = $("<li id='liPerm_"+itemPerm.prmnId+"'>").text(itemPerm.prmnName);	
			//li.append("<div class='space-14'></div>");
			p_divFather.append(li);
		});//Fin ciclo Permission
	});
	
}

function buildDivRoles(p_divFather, p_Entities){
	var idApp = $('#appnId').val();
	var linkStep4 = $(location).attr('origin') + "/HelpDeskLctpc/getJsonRolesApps/" + idApp;
	var jsonRoles;
	var widgetColor = ['blue','green','red','orange','purple','pink','dark','grey',
		'blue2','green2','red2','blue3','green3','red3'];
	callAjax(linkStep4, 'GET', function(dJsonRol){
		jsonRoles = dJsonRol;
	});
	var lstTemp = $("<ul class='list-unstyled list-striped pricing-table-header' id='tempPermission'>");
	buildDivLstPermission(lstTemp);
	
	$.each(jsonRoles.data, function(i, itemRol) {
		var jsonPages;
		var divSpanRol = $("<div class='pricing-span'>");
		
		//((itemRol.roleName.length >10) ? itemRol.roleName.substr(0, 12) + "..." : itemRol.roleName)
		var titleRol = $("<h5 class='widget-title bigger lighter'><i class='ace-icon fa fa-user bigger-120'></i> "+itemRol.roleName +"</h5>");
		
		var divWidgetBoxRol= $("<div class='widget-box pricing-box-small widget-color-"+widgetColor[i]+"'>");
		var divWidgetHeaderRol = $("<div class='widget-header'>").append(titleRol);
		var divWidgetMainRol = $("<div class='widget-main no-padding'>");
		var divWidgetBodyRol = $("<div class='widget-body'>").append(divWidgetMainRol); 
		var ulLstPermission = $("<ul class='list-unstyled list-striped pricing-table-header' id='ul_Perm"+itemRol.roleId+"' >");
		
		$.each(lstTemp.children(), function(iPerm, itemPerm){
			var li = $("<li>");
			
			var liPerm=$(itemPerm);
			
			if(p_Entities.length <= 0){
				console.log(p_Entities.length);
				var labelCboxEnt = $("<label class='abnerRoles'>");
				var divCboxEnt = $("<span class='help-inline'>").append(labelCboxEnt);
				li.append(divCboxEnt);
			}else{
				$.each(p_Entities, function(iEnt, itemEntity){
					li.attr("id","liPagEnt_" + itemEntity.paenId);
					var inputCboxEnt = $("<input name='form-field-checkbox' type='checkbox' class='ace'>"); 
					var labelCboxEnt = $("<label class='abnerRoles'>").append(inputCboxEnt).append("<span class='lbl'>"+ ((itemEntity.paenEnttId instanceof Object) ? itemEntity.paenEnttId.enttName : itemEntity.enttName) +"</span>");
					//var divCboxEnt = $("<div class='checkbox'>").append(labelCboxEnt);
					var divCboxEnt = $("<span class='help-inline'>").append(labelCboxEnt);
					
					li.append(liPerm.text());
					li.append(divCboxEnt);
				} );
			}
			ulLstPermission.append(li);
			
		});
	
		divWidgetMainRol.append(ulLstPermission);
		divWidgetBoxRol.append(divWidgetHeaderRol);
		divWidgetBoxRol.append(divWidgetBodyRol);
		divSpanRol.append(divWidgetBoxRol);
		
		p_divFather.append(divSpanRol);
		
		
	});


}

