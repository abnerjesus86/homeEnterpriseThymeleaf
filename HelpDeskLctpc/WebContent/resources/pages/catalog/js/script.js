/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {

	jQuery.fn.extend({
		initializeTable : function() {
			// Activated the table
			var tableApps = $('#tableApplications').DataTable(
					{
						autoWidth : true,
						searching : true,
						ordering : true,
						destroy : true,
						// lengthChange : false,
						pageLength : 25,
						responsive : true,
						/*
						 * dom : "<'row' <'col-md-6'f> <'col-md-6 text-right'B>>" + "<'row' <'col-md-12'tr>>" + "<'row'<'col-md-6'i><'col-md-6
						 * text-right'p>>",
						 */
						dom : '<"html5buttons"B>lTfgtrip',
						buttons : {
							dom : {
								button : {
									tag : 'a',
									className : 'btn btn-sm btn-default'
								}
							},
							buttons : [ {
								text : 'Refresh',
								action : function(e, dt, node, config) {
									dt.ajax.reload();
								}
							}, {
								"extend" : 'copy',
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
							}, {
								text : "<i class='glyphicon glyphicon-plus'></i>",
								action : function(e, dt, node, config) {

									showModal("./appForm/", "#appn");
								},
								init : function(dt, node, config) {
									node.attr("href", "#myModalApplication");
									node.attr("data-toggle", "modal");
									node.attr("role", "modal");
								}
							} ]
						},
						ajax : {
							url : "./getJsonApps",
							type : "GET",
							contentType : "application/json; charset=utf-8",
							dataType : "json"
						},
						columns : [
								{
									title : "ID",
									data : "appnId"
								},
								{
									title : "Name",
									data : "appnName"
								},
								{
									title : "Description",
									data : "appnDescription"
								},
								{
									title : "Url",
									data : "appnUrl"
								},
								{
									title : "Created By",
									data : "appnCreatedBy"
								},
								{
									title : "Update By",
									data : "appnUpdateBy"
								},
								{
									title : "Active",
									data : null,
									render : function(data, type, row) {
										var l_check = data.appnActive == false ? "" : " checked";
										
                                    return 	" <div class='checkbox checkbox-success'>"+
                                    		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.appnActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                                    		" <label></label>"+
                                    		" </div>";
									}
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>"
												+ "<a class='' id='btn-edit' href='#myModalApplication' role='button' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
												+ "<a class='' id='btn-delete' href='./appForm/"
												+ data.appnId
												+ "/delete'><i class='fa fa-trash-o'></i></a>"
												+ "</div> "
												+ "<div class='hidden-md hidden-lg'>"
												+ "<div class='inline pos-rel'>"
												+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
												+ "<i class='fa fa-cog icon-only'></i>"
												+ "</button>"
												+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
												+ "<li>"
												+ "<a href='#myModalApplication' id='btn-editApplication' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
												+ "<span class=''>"
												+ "<i class='fa fa-pencil-square-o'></i></span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='./appForm/"
												+ data.appnId
												+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class=''> <i class='fa fa-trash-o'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "colLinkModal center"
								} ]

					});
			
			

			var tablePages = $('#tablePages').DataTable(
					{
						autoWidth : true,
						searching : true,
						ordering : true,
						destroy : true,
						stateSave : true,
						pageLength : 25,
						responsive : true,
						// iDisplayLength : 10,
						// lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
						/*
						 * ajax : { url : "./getJsonPages", type : "GET", contentType : "application/json;
						 * charset=utf-8", dataType : "json" },
						 */
						dom : '<"html5buttons"B>lTfgtrip',
						buttons : {
							dom : {
								button : {
									tag : 'a',
									className : 'btn btn-sm btn-default'
								}
							},
							buttons : [ {
								text : 'Refresh',
								action : function(e, dt, node, config) {
									dt.ajax.reload();
								}
							}, {
								"extend" : 'copy',
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
							}, {
								text : "<i class='glyphicon glyphicon-plus'></i>",
								action : function(e, dt, node, config) {
									showModal("./pageForm/", "#page");
								},
								init : function(dt, node, config) {
									node.attr("href", "#myModalApplication");
									node.attr("data-toggle", "modal");
									node.attr("role", "modal");
								}
							} ]
						},
						columns : [
								{
									title : "ID",
									data : "pageId"
								},
								{
									title : "Page Father",
									data : "pagePageId"
								},
								{
									title : "Display",
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
									title : "Created By",
									data : "pageCreatedBy"
								},
								{
									title : "Update By",
									data : "pageUpdateBy"
								},
								{
									title : "Active",
									data : null,
									render : function(data, type, row) {
										var l_check = data.pageActive == false ? "" : " checked";
										return " <div class='checkbox checkbox-success'>"+
                                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.pageActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                                		" <label></label>"+
                                		" </div>";
									}
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>"
												+ "<a class='' id='btn-edit' href='#myModalApplication' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
												+ "<a class='' id='btn-delete' href='./pageForm/"
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
												+ "<a href='#myModalApplication' id='btn-editPage' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
												+ "<span class='green'>"
												+ "<i class='fa fa-pencil-square-o'></i></span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='./pageForm/"
												+ data.pageId
												+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='fa fa-trash-o'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "colLinkModal center"
								}

						]

					});
			
			//#tableApplications tbody
			$('.table-responsive table tbody').on("click", ".colLinkModal a#btn-edit", function() {
				
				var table = $(this).parents('table');
				var tableData = table.DataTable();
				var FilaActual = tableData.row($(this).parents('tr')).data();
				var link = $(location).attr('origin') + "/HelpDeskLctpc/";
				switch(table.attr('id')){
					case 'tableApplications':
						link = link + "appForm/" + FilaActual.appnId + "/update";
						$(this).callAjax(link, "#appn");
						$("#myModalApplication .modal-header h4").text("Form Application [" + FilaActual.appnName + "]");
					break;
					case 'tablePages':
						link = link + "pageForm/" + FilaActual.pageId + "/update";
						$(this).callAjax(link, "#page");
						$("#myModalApplication .modal-header h4").text("Form Page [" + FilaActual.pageDisplay + "]");
					break;
					case 'tablePermission':
						link = link + "permForm/" + FilaActual.prmnId + "/update";
						$(this).callAjax(link, "#perm");
						$("#myModalApplication .modal-header h4").text("Form Permission [" + FilaActual.prmnName + "]");
					break;
					case 'tableSecretQuestion':
						link = link + "secretQuestionForm/" + FilaActual.sequId + "/update";
						$(this).callAjax(link, "#sctQ");
						$("#myModalApplication .modal-header h4").text("Form Secret Question [" + FilaActual.sequId + "]");
					break;
					case 'tableEntity':
						link = link + "entityForm/" + FilaActual.enttId + "/update";
						$(this).callAjax(link, "#entt");
						$("#myModalApplication .modal-header h4").text("Form Entity [" + FilaActual.enttName + "]");
					break;
				}
				
				//var link = $(location).attr('origin') + "/HelpDeskLctpc/appForm/" + FilaActual.appnId + "/update";
				//$(this).callAjax(link, "#appn");
				//$("#myModalApplication .modal-header h4").text("Form Application [" + FilaActual.appnName + "]");
			});
			
			/*$('#tablePages tbody').on("click", ".colLinkPageModal a#btn-editPage", function() {
				var FilaActual = tablePages.row($(this).parents('tr')).data();
				var link = $(location).attr('origin') + "/HelpDeskLctpc/pageForm/" + FilaActual.pageId + "/update";
				$(this).callAjax(link, "#page");
				$("#myModalApplication .modal-header h4").text("Form Page [" + FilaActual.pageDisplay + "]");
				
			});*/

			var tablePermission = $('#tablePermission').DataTable(
					{
						autoWidth : true,
						searching : true,
						ordering : true,
						destroy : true,
						stateSave : true,
						pageLength : 25,
						responsive : true,
						dom : '<"html5buttons"B>lTfgtrip',
						buttons : {
							dom : {
								button : {
									tag : 'a',
									className : 'btn btn-sm btn-default'
								}
							},
							buttons : [ {
								text : 'Refresh',
								action : function(e, dt, node, config) {
									dt.ajax.reload();
								}
							}, {
								"extend" : 'copy',
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
							}, {
								text : "<i class='glyphicon glyphicon-plus'></i>",
								action : function(e, dt, node, config) {
									showModal("./permForm/", "#perm");
								},
								init : function(dt, node, config) {
									node.attr("href", "#myModalApplication");
									node.attr("data-toggle", "modal");
									node.attr("role", "modal");
								}
							} ]
						},
						columns : [
								{
									title : "ID",
									data : "prmnId"
								},
								{
									title : "Name",
									data : "prmnName"
								},
								{
									title : "Description",
									data : "prmnDescription"
								},
								{
									title : "Created By",
									data : "prmnCreatedBy"
								},
								{
									title : "Update By",
									data : "prmnUpdateBy"
								},
								{
									title : "Active",
									data : null,
									render : function(data, type, row) {
										var l_check = data.prmnActive == false ? "" : " checked";
										return " <div class='checkbox checkbox-success'>"+
                                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.prmnActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                                		" <label></label>"+
                                		" </div>";
									}
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>"
												+ "<a class='green' id='btn-editPermission' href='#formularioModal' role='button' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
												+ "<a class='red' id='id-btn-dialog2' href='./permForm/"
												+ data.prmnId
												+ "/delete'><i class='fa fa-trash-o bigger-130'></i></a>"
												+ "</div> "
												+ "<div class='hidden-md hidden-lg'>"
												+ "<div class='inline pos-rel'>"
												+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
												+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>"
												+ "</button>"
												+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
												+ "<li>"
												+ "<a href='#formularioModal' id='btn-editPermission' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
												+ "<span class='green'>"
												+ "<i class='fa fa-pencil-square-o'></i></span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='./permForm/"
												+ data.prmnId
												+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='fa fa-trash-o'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "colLinkPermissionModal center"
								} ]

					});

			/*$('#tablePermission tbody').on("click", ".colLinkPermissionModal a#btn-editPermission", function() {
				var FilaActual = tablePermission.row($(this).parents('tr')).data();
				var link = $(location).attr('origin') + "/HelpDeskLctpc/permForm/" + FilaActual.prmnId + "/update";
				$(this).callAjax("permForm" + link, "#perm");
				$("#myModalApplication .modal-header h4").text("Form Permission [" + FilaActual.prmnName + "]");
			});*/

			var tableSecQues = $('#tableSecretQuestion').DataTable(
					{
						autoWidth : true,
						searching : true,
						ordering : true,
						destroy : true,
						stateSave : true,
						pageLength : 25,
						responsive : true,
						dom : '<"html5buttons"B>lTfgtrip',
						buttons : {
							dom : {
								button : {
									tag : 'a',
									className : 'btn btn-sm btn-default'
								}
							},
							buttons : [ {
								text : 'Refresh',
								action : function(e, dt, node, config) {
									dt.ajax.reload();
								}
							}, {
								"extend" : 'copy',
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
							}, {
								text : "<i class='glyphicon glyphicon-plus'></i>",
								action : function(e, dt, node, config) {
									showModal("./secretQuestionForm/", "#sctQ");
								},
								init : function(dt, node, config) {
									node.attr("href", "#myModalApplication");
									node.attr("data-toggle", "modal");
									node.attr("role", "modal");
								}
							} ]
						},
						columns : [
								{
									title : "ID",
									data : "sequId"
								},
								{
									title : "Secret Question",
									data : "sequQuestion"
								},
								{
									title : "Created By",
									data : "sequCreatedBy"
								},
								{
									title : "Update By",
									data : "sequUpdateBy"
								},
								{
									title : "Active",
									data : null,
									render : function(data, type, row) {
										var l_check = data.sequActive == false ? "" : " checked";
										return " <div class='checkbox checkbox-success'>"+
                                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.sequActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                                		" <label></label>"+
                                		" </div>";
									}
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>"
												+ "<a class='' id='btn-editSecQuest' href='#myModalApplication' role='button' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
												+ "<a class='' id='id-btn-dialog2' href='./secretQuestionForm/"
												+ data.sequId
												+ "/delete'><i class='fa fa-trash-o'></i></a>"
												+ "</div> "
												+ "<div class='hidden-md hidden-lg'>"
												+ "<div class='inline pos-rel'>"
												+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
												+ "<i class='fa fa-cog icon-only'></i>"
												+ "</button>"
												+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
												+ "<li>"
												+ "<a href='#myModalApplication' id='btn-editSecQuest' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
												+ "<span class='green'>"
												+ "<i class='fa fa-pencil-square-o'></i></span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='./secretQuestionForm/"
												+ data.sequId
												+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='fa fa-trash-o'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "colLinkSecretQuestionModal center"
								}

						]

					});

			/*$('#tableSecretQuestion tbody').on("click", ".colLinkSecretQuestionModal a#btn-editSecQuest", function() {
				var FilaActual = tableSecQues.row($(this).parents('tr')).data();
				var link = $(location).attr('origin') + "/HelpDeskLctpc/secretQuestionForm/" + FilaActual.sequId + "/update";
				$(this).callAjax(link, "#sctQ");
				$("#myModalApplication .modal-header h4").text("Form Secret Question [" + FilaActual.sequId + "]");
			});*/

			var tableEntity = $('#tableEntity').DataTable(
					{
						autoWidth : true,
						searching : true,
						ordering : true,
						destroy : true,
						stateSave : true,
						pageLength : 25,
						responsive : true,
						dom : '<"html5buttons"B>lTfgtrip',
						buttons : {
							dom : {
								button : {
									tag : 'a',
									className : 'btn btn-sm btn-default'
								}
							},
							buttons : [ {
								text : 'Refresh',
								action : function(e, dt, node, config) {
									dt.ajax.reload();
								}
							}, {
								"extend" : 'copy',
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
							}, {
								text : "<i class='glyphicon glyphicon-plus'></i>",
								action : function(e, dt, node, config) {
									showModal("./entityForm/", "#entt");
								},
								init : function(dt, node, config) {
									node.attr("href", "#myModalApplication");
									node.attr("data-toggle", "modal");
									node.attr("role", "modal");
								}
							} ]
						},
						columns : [
								{
									title : "ID",
									data : "enttId"
								},
								{
									title : "Name",
									data : "enttName"
								},
								{
									title : "Description",
									data : "enttDescription"
								},
								{
									title : "Created By",
									data : "enttCreatedBy"
								},
								{
									title : "Update By",
									data : "enttUpdateBy"
								},
								{
									title : "Active",
									data : null,
									render : function(data, type, row) {
										var l_check = data.enttActive == false ? "" : " checked";
										return " <div class='checkbox checkbox-success'>"+
                                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.enttActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                                		" <label></label>"+
                                		" </div>";
									}
								},
								{
									title : "Actions",
									data : null,
									render : function(data, type, row) {
										return "<div class='hidden-sm hidden-xs action-buttons'>"
												+ "<a class='' id='btn-editEntity' href='#myModalApplication' role='button' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
												+ "<a class='' id='id-btn-dialog2' href='./entityForm/"
												+ data.enttId
												+ "/delete' role='button'><i class='fa fa-trash-o'></i></a>"
												+ "</div> "
												+ "<div class='hidden-md hidden-lg'>"
												+ "<div class='inline pos-rel'>"
												+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
												+ "<i class='fa fa-cog icon-only'></i>"
												+ "</button>"
												+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
												+ "<li>"
												+ "<a href='#myModalApplication' id='btn-editEntity' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
												+ "<span class='green'>"
												+ "<i class='fa fa-pencil-square-o'></i></span></a>"
												+ "</li>"
												+ "<li>"
												+ "<a href='./entityForm/"
												+ data.enttId
												+ "/delete' role='button' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
												+ "<span class='red'> <i class='fa fa-trash-o'></i></span></a>" + "</li>"

												+ "</ul>" + "</div>" + "</div>";
									},
									className : "colLinkEntityModal center"
								}

						]

					});

			/*$('#tableEntity tbody').on("click", ".colLinkEntityModal a#btn-editEntity", function() {
				var FilaActual = tableEntity.row($(this).parents('tr')).data();
				var link = $(location).attr('origin') + "/HelpDeskLctpc/entityForm/" + FilaActual.enttId + "/update";
				$(this).callAjax(link, "#entt");
				$("#myModalApplication .modal-header h4").text("Form Entity [" + FilaActual.enttName + "]");
			});*/

			$('#tableApplications tbody').on("click", ".colLinkApplicationModal a#id-btn-dialog2", function(e) {
				var FilaActual = tableApps.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); // elimina el evento del link.
				
				if (FilaActual.appnActive) { // Verifica si el registro se encuentra activo.
					shoModalConfirmation(linkDelete, tableApps);
					
				}// Fin del If para revisar si esta activo el registro
				
				
			});

			$('#tablePages tbody').on("click", ".colLinkPageModal a#id-btn-dialog2", function(e) {
				var FilaActual = tablePages.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); // elimina el evento del link.
				if (FilaActual.pageActive) { // Verifica si el registro se encuentra activo.
					shoModalConfirmation(linkDelete, tablePages);
					
				}// Fin del If para revisar si esta activo el registro

			});

			$('#tablePermission tbody').on("click", ".colLinkPermissionModal a#id-btn-dialog2", function(e) {
				var FilaActual = tablePermission.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); // elimina el evento del link.
				if (FilaActual.prmnActive) { // Verifica si el registro se encuentra activo.
					shoModalConfirmation(linkDelete, tablePermission);
					
				}// Fin del If para revisar si esta activo el registro

			});

			$('#tableSecretQuestion tbody').on("click", ".colLinkSecretQuestionModal a#id-btn-dialog2", function(e) {
				var FilaActual = tableSecQues.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); // elimina el evento del link.
				if (FilaActual.sequActive) { // Verifica si el registro se encuentra activo.
					shoModalConfirmation(linkDelete, tableSecQues);
					
				}// Fin del If para revisar si esta activo el registro

			});

			$('#tableEntity tbody').on("click", ".colLinkEntityModal a#id-btn-dialog2", function(e) {
				var FilaActual = tableEntity.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); // elimina el evento del link.
				if (FilaActual.enttActive) { // Verifica si el registro se encuentra activo.
					shoModalConfirmation(linkDelete, tableEntity);
					
				}// Fin del If para revisar si esta activo el registro

			});

			/* Formatting function for row details - modify as you need */
			function format(d) {
				// `d` is the original data object for the row
				return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' + '<tr>' + '<td>Full name:</td>' + '<td>' + d.pageId + '</td>' + '</tr>' + '<tr>' + '<td>Extension number:</td>'
						+ '<td>' + d.pageDisplay + '</td>' + '</tr>' + '<tr>' + '<td>Extra info:</td>' + '<td>And any further details here (images etc)...</td>' + '</tr>' + '</table>';
			}

			// Add event listener for opening and closing details
			$('#tablePages tbody').on('click', 'td.details-control', function() {
				var tr = $(this).closest('tr');
				var row = tablePages.row(tr);

				if (row.child.isShown()) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				} else {
					// Open this row
					row.child(format(row.data())).show();
					tr.addClass('shown');
				}
			});

			
			$('#btn-addPage').on("click", function() {
				showModal("./pageForm/", "#page");
			});

			$('#myTab a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
				if ($(e.target).attr('href') == "#tab-1") {
					// doSomethingNow();
					tableApps.clear().draw();
					tableApps.ajax.url("./getJsonApps").load();
				} else if ($(e.target).attr('href') == "#tab-2") {
					tablePages.clear().draw();
					tablePages.ajax.url("./getJsonPages").load();
				} else if ($(e.target).attr('href') == "#tab-3") {
					tablePermission.clear().draw();
					tablePermission.ajax.url("./getJsonPermisisons").load();
				} else if ($(e.target).attr('href') == "#tab-4") {
					tableSecQues.clear().draw();
					tableSecQues.ajax.url("./getJsonSecretQuestions").load();
				} else if ($(e.target).attr('href') == "#tab-5") {
					tableEntity.clear().draw();
					tableEntity.ajax.url("./getJsonEntities").load();
				}
			});
		}
	});

	$(this).initializeTable();

});

function showModal(p_url, p_form) {
	$(this).callAjax(p_url, p_form);
	$("#myModalApplication .modal-header h4").text("New ");
	
	
}

function shoModalConfirmation(p_url, p_table){
	swal({
		title : "Are you sure?",
		text : "Do you want to disable the record?, The entity will not be deleted!",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, delete it!",
		cancelButtonText : "No, cancel plx!",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : p_url,
				success : function(result) {
					p_table.clear().draw();
					p_table.ajax.reload();
				}
			});
			swal("Deleted!", "The record has been desable.", "success");
		} else {
			swal("Cancelled", "The record has not been disable :)", "error");
		}
	});
}


function getListValuesText(p_form){
	
	$('.chosen-select').chosen({
        allow_single_deselect : true,
        width : "100%"
    });
    
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });

    var demo1 = $('.dual_select').bootstrapDualListbox({
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

$.fn.callAjax = function(p_url, p_form) {
	$.ajax({
		type : "POST",
		url : p_url,
		 dataType: "html",
		timeout : 100000,
		success : function(result) {
			console.log(p_form + " 0");
			if (!(result === null)) {
				$(".modal-body .row").html(result);
				
				console.log(p_form + " 1");
				getListValuesText();
				
				$('#btnSaveConfiguration').on('click', function() {
					console.log("hola mundo...");
					$(p_form).submit();
					
				});
				
			}
		},
		error : function(e) {
			alert("ERROR: ", e);
		}
	});
}
