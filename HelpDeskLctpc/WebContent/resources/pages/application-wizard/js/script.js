/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			var idApp = $('#appnId').val();
			// Codigo para tabla de pagina
			var tablePages = $('#tablePages').DataTable({
				deferRender : false,
				paging : false,
				info : false,
				autoWidth : true,
				select : false,
				searching : false,
				ordering : false,
				stateSave : false,
				//scrollY : '25vh',
				scrollCollapse : false,
				fixedColumns : {
					heightMatch : 'auto'
				},/*
					 * ajax : { url : "./getJsonRolesApps/"+idApp, type : "GET", contentType :
					 * "application/json; charset=utf-8", dataType : "json" },
					 */
				columns : [
						{
							title : "Consecutivo",
							data : "pageId"
						},
						/*{
							title : "Nomenclature",
							data : "pageDisplay"
						},*/
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
										+ "<a class='red' id='id-btn-delete' href='./appForm/"
										+ data.pageId
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
										+ "<a href='./appForm/"
										+ data.pageId
										+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
										+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>" + "</li>"

										+ "</ul>" + "</div>" + "</div>";
							},
							className : "gridSystemModal center"
						}

				]
			});
			var counter = 0;
			var isEdit = false;

			$('#btn-addPage').on('click', function() {
				var d = '';
				var l_a = [];
				var l_methodType = 'POST';
				
				$.each($('#duallist').val() != null ? $('#duallist').val() : [], function(i, item) {
					l_a.push( { paenEnttId : { enttId : new Number(  item ) } } );
				});
				
				l_methodType = $('#pageId').val() != '' ? 'PUT' :  'POST';
				
				d = JSON.stringify({
					pageId : $('#pageId').val() != '' ? new Number($('#pageId').val()) : null,
					pagePageId : $('#pagePageId').val() != '' ? { pageId : new Number($('#pagePageId').val())} : null,
					pageDisplay : $('#pageDisplay').val(),
					pageDescription : $('#pageDescription').val(),
					pageUrl : $('#pageUrl').val(),
					pageActive : true,
					pageEntities : l_a,
					applications : [{ appnId : new Number(idApp) }]
				});
				
				console.log(d);
				var link = "../appWizard/page/save";
				if (idApp !== null && idApp !== undefined && idApp != '') {
					link = link + "/" + idApp;
				}
/*
				$.ajax({
					url : link,
					type : l_methodType,
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
*/				
				$('#pageId').val("");
				$('#pagePageId').val("");
				$('#pageDisplay').val("");
				$('#pageDescription').val("");
				$('#pageUrl').val(""); 
				//$('#duallist').val();
				//$('#duallist').bootstrapDualListbox('destroy');
				alert($('#bootstrap-duallistbox-selected-list_duallistbox_demo1[]"]').val());
				$('#bootstrap-duallistbox-nonselected-list_duallistbox_demo1[]').empty();
				$('#bootstrap-duallistbox-selected-list_duallistbox_demo1[]"]').empty();
				//$('#duallist').trigger('change');
				
				$(this).getListValuesText({
					idList : "#pagePageId",
					methodType : 'GET',
					urlWs : '../getJsonPagesForSelect/',
					optionSelect : '',
					parameters : null,
					errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
					chosen : true
				});

				$(this).getListValuesText({
					idList : "#duallist",
					methodType : 'GET',
					urlWs : '../getJsonEntitiesForSelect/',
					optionSelect : '',
					parameters : null,
					errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
					chosen : false,
					dualList : true
				});
				
				//var dualListbox = $('#duallist').bootstrapDualListbox();
				
			    $('#duallist').bootstrapDualListbox('refresh', true);
				
				
			});
			
			
			
			
			$('#tablePages tbody').on("click", ".gridSystemModal a#id-btn-edit", function() {
				
				var rowPage = tableRoles.row($(this).parents('tr'));
				var filaActualPage = rowPage.data();
				
				$('#pageDisplay').val(FilaActual[2]);
				$('#pageDescription').val(FilaActual[3]);
				$('#pageUrl').val(FilaActual[4]);
				
				
			});

			$('#tablePages tbody').on("click", ".gridSystemModal a#id-btn-delete", function() {
				tablePages.row($(this).parents('tr')).remove().draw(false);
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
						stateSave : false,
						scrollY : '25vh',
						scrollCollapse : false,
						fixedColumns : {
							heightMatch : 'auto'
						},/*
							 * ajax : { url : "./getJsonRolesApps/"+idApp, type : "GET", contentType :
							 * "application/json; charset=utf-8", dataType : "json" },
							 */
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
												+ "<a class='red' id='id-btn-delete' href='./appForm/"
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
												+ "<a href='./appForm/"
												+ data.roleId
												+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "gridSystemModal center"
								}

						]
					});

			if (idApp !== null && idApp !== undefined && idApp != '') {
				console.log("valor del idApp: " + idApp);
				tableRoles.clear().draw();
				tableRoles.ajax.url("../getJsonRolesApps/" + idApp).load();
				tablePages.clear().draw();
				tablePages.ajax.url("../getJsonPagesApps/" + idApp).load();
			}

			var counterRoles = 0;
			var isEditRoles = false;

			var RowRoles = null;
			$('#btn-addRole').on('click', function() {
				
				var d = '';
				if ($('#roleId').val() != '') {
					console.log("Entro al if donde tiene valor");
					d = JSON.stringify({
						roleId : $('#roleId').val(),
						roleName : $('#roleName').val(),
						roleDescription : $('#roleDescription').val()
					});
				} else {
					console.log("entro al else no hay valor");
					d = JSON.stringify({
						roleName : $('#roleName').val(),
						roleDescription : $('#roleDescription').val()
					});
				}

				var link = "../appWizard/roles/save";
				if (idApp !== null && idApp !== undefined && idApp != '') {
					console.log("valor del idApp: " + idApp);

					link = link + "/" + idApp;
				}

				$.ajax({
					url : link,
					type : "PUT",
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
			

			// -------------------------------------------------------------------------------------------------------------
			$('[data-rel=tooltip]').tooltip();

			$(".select2").css('width', '200px').select2({
				allowClear : true
			}).on('change', function() {
				$(this).closest('form').validate().element($(this));
			});

			var $validation = false;
			$('#wizard-application').wizard({
			// step: 2 //optional argument. wizard will jump to step "2" at first
			// buttons: '.wizard-actions:eq(0)'
			}).on('actionclicked.fu.wizard', function(e, info) {
				/*
				 * if (info.step == 1 && $validation) { if (!$('#validation-form').valid()) e.preventDefault(); }
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
			});

		} // fin funcion de initializeTable
		,
		getListValuesText : function(options) {
			// Funcion: Devolvemos la lista de valores correspondiente
			var options = $.extend({
				idList : '',
				methodType : '',
				urlWs : '',
				optionSelect : '',
				parameters : null,
				errorMessage : 'Ha ocurrido un error al cargar el listado',
				chosen : false,
				dualList : false
			}, options);

			return this.each(function() {

				var jsonDataList = '';
				if (options.parameters !== null)
					jsonDataList = JSON.stringify(options.parameters);
				jQuery.support.cors = true;

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

						$(options.idList).empty();

						if (options.chosen) { // Add Empty Option
							$(options.idList).append($('<option>', {
								value : "",
								text : ""
							}));
						}
						$.each(result.data, function(i, item) {
							$(options.idList).append($('<option>', {
								value : item.value,
								text : item.label
							}));

							if (options.optionSelect == item.l_valueFieldName)
								$(options.idList + ' option:eq(' + i + ')').prop('selected', true)
						});

						if (!ace.vars['touch'] && options.chosen) {

							$('.chosen-select').chosen({
								allow_single_deselect : true
							}).trigger("chosen:updated");

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
								preserveSelectionOnMove : 'moved',
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
							
						}

					},
					error : function(e) {
						console.log(options.errorMessage);
						console.log(e);
						swal("Oops...", options.errorMessage, "error");
					}
				});
			});
		}
	});

	$(this).initializeTable();
	$(this).getListValuesText({
		idList : "#pagePageId",
		methodType : 'GET',
		urlWs : '../getJsonPagesForSelect/',
		optionSelect : '',
		parameters : null,
		errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
		chosen : true
	});

	$(this).getListValuesText({
		idList : "#duallist",
		methodType : 'GET',
		urlWs : '../getJsonEntitiesForSelect/',
		optionSelect : '',
		parameters : null,
		errorMessage : "Ha ocurrido un error al cargar el listado de Líneas Navieras",
		chosen : false,
		dualList : true
	});

	$('#duallist').on('change', function() {
		// var dato = $('select[name="duallistbox_demo1[]_helper2"]').val();
		// alert( dato );
		// move($('#duallist').bootstrapDualListbox());
		// alert("Select 1 " + $('select[name="duallistbox_demo1[]_helper1"]').val());
		// alert("Seelct 2 " + $('select[name="duallistbox_demo1[]_helper2"]').val());

		//$('#duallist').bootstrapDualListbox('refresh', true);
	});
	
	// in ajax mode, remove remaining elements before leaving page
	/*$(document).one('ajaxloadstart.page', function(e) {
		$('select[name="duallistbox_demo1[]"]').bootstrapDualListbox('destroy');
	});*/

});
