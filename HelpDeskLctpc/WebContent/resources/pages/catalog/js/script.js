/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery( function( $ ) {
	
	jQuery.fn
			.extend( {
				initializeTable : function() {
					// Activated the table
					var tableApps = $( '#tableApplications' )
							.DataTable(
									{
										autoWidth : true,
										searching : false,
										ordering : false,
										stateSave : true,
										/* scrollY : '25vh', */
										scrollCollapse : true,
										fixedColumns : {
											heightMatch : 'auto'
										},
										iDisplayLength : 10,
										lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
										ajax : {
											url : "./getJsonApps",
											type : "GET",
											contentType : "application/json; charset=utf-8",
											dataType : "json"
										},
										columns : [
												{
													title : "ID",
													data : null,
													render : function( data, type, row ) {
														return "<div class='action-buttons center'><a href='#formularioModal' role='button' data-toggle='modal'><b>"
																+ data.appnId + "</b></a></div>";
													},
													className : "colApplicationModal center"
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
													render : function( data, type, row ) {
														var l_check = data.appnActive == false ? "" : " checked";
														return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='"
																+ data.appnActive + "' " + l_check + "/><span class='lbl'></span></label>";
													}
												},
												{
													title : "Actions",
													data : null,
													render : function( data, type, row ) {
														return "<div class='hidden-sm hidden-xs action-buttons'>"
																+ "<a class='green' id='btn-editApplication' href='#formularioModal' role='button' data-toggle='modal'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
																+ "<a class='red' id='id-btn-dialog2' href='./appForm/"
																+ data.appnId
																+ "/delete'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
																+ "</div> "
																+ "<div class='hidden-md hidden-lg'>"
																+ "<div class='inline pos-rel'>"
																+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
																+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>"
																+ "</button>"
																+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
																+ "<li>"
																+ "<a href='#formularioModal' id='btn-editApplication' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
																+ "<span class='green'>"
																+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
																+ "</li>"
																+ "<li>"
																+ "<a href='./appForm/"
																+ data.appnId
																+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
																+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>"
																+ "</li>"

																+ "</ul>" + "</div>" + "</div>";
													},
													className : "colLinkApplicationModal center"
												} ]

									} );

					$( '#tableApplications tbody' ).on( "click", ".colLinkApplicationModal a#btn-editApplication", function() {
						var FilaActual = tableApps.row( $( this ).parents( 'tr' ) ).data();
						var link = "./appForm/" + FilaActual.appnId + "/update";
						$( this ).callAjax( link, "#appn" );
						$( "#formularioModal .modal-header h4" ).text( FilaActual.appnName );
						
					} );

					var tablePages = $( '#tablePages' )
							.DataTable(
									{
										autoWidth : true,
										searching : false,
										ordering : false,
										stateSave : true,
										// scrollY : '25vh',
										scrollCollapse : true,
										iDisplayLength : 10,
										lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
										/*
										 * ajax : { url : "./getJsonPages", type : "GET", contentType : "application/json; charset=utf-8", dataType :
										 * "json" },
										 */
										columns : [
												{
													title : "ID",
													data : null,
													render : function( data, type, row ) {
														return "<div class='action-buttons center'><a href='#' role='button'><b>"
																+ data.pageId + "</b></a></div>";
													},
													className : "details-control center"
												},
												/*{
													title : "Page Father",
													data : "pagePageId"
												},
												{
													title : "Nomenclature",
													data : "pageNomenclature"
												},*/
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
												/*{
													title : "Entities",
													data : null,
													render : function( data, type, row ) {
														var l_check = data.pageActive == false ? "" : " checked";

															<select id="food" class="multiselect" multiple="">
																<option value="cheese">Cheese</option>
																<option value="tomatoes">Tomatoes</option>
																<option value="mozarella">Mozzarella</option>
																<option value="mushrooms">Mushrooms</option>
																<option value="pepperoni">Pepperoni</option>
															</select>

														return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='"
																+ data.pageActive + "' " + l_check + "/><span class='lbl'></span></label>";
													}
												},*/
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
													render : function( data, type, row ) {
														var l_check = data.pageActive == false ? "" : " checked";
														return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='"
																+ data.pageActive + "' " + l_check + "/><span class='lbl'></span></label>";
													}
												},
												{
													title : "Actions",
													data : null,
													render : function( data, type, row ) {
														return "<div class='hidden-sm hidden-xs action-buttons'>"
																+ "<a class='green' id='btn-editPage' href='#formularioModal' role='button' data-toggle='modal'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
																+ "<a class='red' id='id-btn-dialog2' href='./pageForm/"
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
																+ "<a href='#formularioModal' id='btn-editPage' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
																+ "<span class='green'>"
																+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
																+ "</li>"
																+ "<li>"
																+ "<a href='./pageForm/"
																+ data.pageId
																+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
																+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>"
																+ "</li>"

																+ "</ul>" + "</div>" + "</div>";
													},
													className : "colLinkPageModal center"
												}

										]

									} );

					$( '#tablePages tbody' ).on( "click", ".colLinkPageModal a#btn-editPage", function() {
						var FilaActual = tablePages.row( $( this ).parents( 'tr' ) ).data();
						var link = "./pageForm/" + FilaActual.pageId + "/update";
						$( this ).callAjax( link, "#page" );
						$( "#formularioModal .modal-header h4" ).text( FilaActual.pageNomenclature );
					} );

					var tablePermission = $( '#tablePermission' )
							.DataTable(
									{
										deferRender : false,
										paging : false,
										info : false,
										autoWidth : true,
										searching : false,
										ordering : false,
										stateSave : true,
										// scrollY : '25vh',
										scrollCollapse : true,
										fixedColumns : {
											heightMatch : 'auto'
										},
										columns : [
												{
													title : "ID",
													data : null,
													render : function( data, type, row ) {
														return "<div class='action-buttons center'><a href='#formularioModal' role='button' data-toggle='modal'><b>"
																+ data.prmnId + "</b></a></div>";
													},
													className : "colPermissionModal center"
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
													render : function( data, type, row ) {
														var l_check = data.prmnActive == false ? "" : " checked";
														return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='"
																+ data.prmnActive + "' " + l_check + "/><span class='lbl'></span></label>";
													}
												},
												{
													title : "Actions",
													data : null,
													render : function( data, type, row ) {
														return "<div class='hidden-sm hidden-xs action-buttons'>"
																+ "<a class='green' id='btn-editPermission' href='#formularioModal' role='button' data-toggle='modal'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
																+ "<a class='red' id='id-btn-dialog2' href='./permForm/"
																+ data.prmnId
																+ "/delete'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
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
																+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
																+ "</li>"
																+ "<li>"
																+ "<a href='./permForm/"
																+ data.prmnId
																+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
																+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>"
																+ "</li>"

																+ "</ul>" + "</div>" + "</div>";
													},
													className : "colLinkPermissionModal center"
												} ]

									} );

					$( '#tablePermission tbody' ).on( "click", ".colLinkPermissionModal a#btn-editPermission", function() {
						var FilaActual = tablePermission.row( $( this ).parents( 'tr' ) ).data();
						var link = "./permForm/" + FilaActual.prmnId + "/update";
						$( this ).callAjax( "permForm" + link, "#perm" );
						$( "#formularioModal .modal-header h4" ).text( FilaActual.pageNomenclature );
					} );

					var tableSecQues = $( '#tableSecretQuestion' )
							.DataTable(
									{
										autoWidth : true,
										searching : false,
										ordering : false,
										stateSave : true,
										// scrollY : '25vh',
										scrollCollapse : true,
										iDisplayLength : 10,
										lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
										columns : [
												{
													title : "ID",
													data : null,
													render : function( data, type, row ) {
														return "<div class='action-buttons center'><a href='#formularioModal' role='button' data-toggle='modal'><b>"
																+ data.sequId + "</b></a></div>";
													},
													className : "colSecretQuestionModal center"
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
													render : function( data, type, row ) {
														var l_check = data.sequActive == false ? "" : " checked";
														return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='"
																+ data.sequActive + "' " + l_check + "/><span class='lbl'></span></label>";
													}
												},
												{
													title : "Actions",
													data : null,
													render : function( data, type, row ) {
														return "<div class='hidden-sm hidden-xs action-buttons'>"
																+ "<a class='green' id='btn-editSecQuest' href='#formularioModal' role='button' data-toggle='modal'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
																+ "<a class='red' id='id-btn-dialog2' href='./secretQuestionForm/"
																+ data.sequId
																+ "/delete'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
																+ "</div> "
																+ "<div class='hidden-md hidden-lg'>"
																+ "<div class='inline pos-rel'>"
																+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
																+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>"
																+ "</button>"
																+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
																+ "<li>"
																+ "<a href='#formularioModal' id='btn-editSecQuest' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
																+ "<span class='green'>"
																+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
																+ "</li>"
																+ "<li>"
																+ "<a href='./secretQuestionForm/"
																+ data.sequId
																+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
																+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>"
																+ "</li>"

																+ "</ul>" + "</div>" + "</div>";
													},
													className : "colLinkSecretQuestionModal center"
												}

										]

									} );

					$( '#tableSecretQuestion tbody' ).on( "click", ".colLinkSecretQuestionModal a#btn-editSecQuest", function() {
						var FilaActual = tableSecQues.row( $( this ).parents( 'tr' ) ).data();
						var link = "./secretQuestionForm/" + FilaActual.sequId + "/update";
						$( this ).callAjax( link, "#sctQ" );
						$( "#formularioModal .modal-header h4" ).text( FilaActual.sequId );
					} );

					var tableEntity = $( '#tableEntity' )
							.DataTable(
									{
										autoWidth : true,
										searching : false,
										ordering : false,
										stateSave : true,
										// scrollY : '25vh',
										scrollCollapse : true,
										iDisplayLength : 10,
										lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
										columns : [
												{
													title : "ID",
													data : null,
													render : function( data, type, row ) {
														return "<div class='action-buttons center'><a href='#formularioModal' role='button' data-toggle='modal'><b>"
																+ data.enttId + "</b></a></div>";
													},
													className : "colEntityModal center"
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
													render : function( data, type, row ) {
														var l_check = data.enttActive == false ? "" : " checked";
														return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='"
																+ data.enttActive + "' " + l_check + "/><span class='lbl'></span></label>";
													}
												},
												{
													title : "Actions",
													data : null,
													render : function( data, type, row ) {
														return "<div class='hidden-sm hidden-xs action-buttons'>"
																+ "<a class='green' id='btn-editEntity' href='#formularioModal' role='button' data-toggle='modal'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
																+ "<a class='red' id='id-btn-dialog2' href='./entityForm/"
																+ data.enttId
																+ "/delete'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
																+ "</div> "
																+ "<div class='hidden-md hidden-lg'>"
																+ "<div class='inline pos-rel'>"
																+ "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
																+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>"
																+ "</button>"
																+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
																+ "<li>"
																+ "<a href='#formularioModal' id='btn-editEntity' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
																+ "<span class='green'>"
																+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
																+ "</li>"
																+ "<li>"
																+ "<a href='./entityForm/"
																+ data.enttId
																+ "/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
																+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>"
																+ "</li>"

																+ "</ul>" + "</div>" + "</div>";
													},
													className : "colLinkEntityModal center"
												}

										]

									} );

					$( '#tableEntity tbody' ).on( "click", ".colLinkEntityModal a#btn-editEntity", function() {
						var FilaActual = tableEntity.row( $( this ).parents( 'tr' ) ).data();
						var link = "./entityForm/" + FilaActual.enttId + "/update";
						$( this ).callAjax( link, "#entt" );
						$( "#formularioModal .modal-header h4" ).text( FilaActual.enttName );
					} );

					$( '#tableApplications tbody' ).on( "click", ".colLinkApplicationModal a#id-btn-dialog2", function( e ) {
						var FilaActual = tableApps.row( $( this ).parents( 'tr' ) ).data();
						var linkDelete = this;
						e.preventDefault(); // elimina el evento del link.
						if ( FilaActual.appnActive ) { // Verifica si el registro se encuentra activo.
							showModalConfirmation( linkDelete, tableApps );
						}// Fin del If para revisar si esta activo el registro

					} );

					$( '#tablePages tbody' ).on( "click", ".colLinkPageModal a#id-btn-dialog2", function( e ) {
						var FilaActual = tablePages.row( $( this ).parents( 'tr' ) ).data();
						var linkDelete = this;
						e.preventDefault(); // elimina el evento del link.
						if ( FilaActual.pageActive ) { // Verifica si el registro se encuentra activo.
							showModalConfirmation( linkDelete, tablePages );
						}// Fin del If para revisar si esta activo el registro

					} );

					$( '#tablePermission tbody' ).on( "click", ".colLinkPermissionModal a#id-btn-dialog2", function( e ) {
						var FilaActual = tablePermission.row( $( this ).parents( 'tr' ) ).data();
						var linkDelete = this;
						e.preventDefault(); // elimina el evento del link.
						if ( FilaActual.prmnActive ) { // Verifica si el registro se encuentra activo.
							showModalConfirmation( linkDelete, tablePermission );
						}// Fin del If para revisar si esta activo el registro

					} );

					$( '#tableSecretQuestion tbody' ).on( "click", ".colLinkSecretQuestionModal a#id-btn-dialog2", function( e ) {
						var FilaActual = tableSecQues.row( $( this ).parents( 'tr' ) ).data();
						var linkDelete = this;
						e.preventDefault(); // elimina el evento del link.
						if ( FilaActual.sequActive ) { // Verifica si el registro se encuentra activo.
							showModalConfirmation( linkDelete, tableSecQues );
						}// Fin del If para revisar si esta activo el registro

					} );

					$( '#tableEntity tbody' ).on( "click", ".colLinkEntityModal a#id-btn-dialog2", function( e ) {
						var FilaActual = tableEntity.row( $( this ).parents( 'tr' ) ).data();
						var linkDelete = this;
						e.preventDefault(); // elimina el evento del link.
						if ( FilaActual.enttActive ) { // Verifica si el registro se encuentra activo.
							showModalConfirmation( linkDelete, tableEntity );
						}// Fin del If para revisar si esta activo el registro

					} );
					
					/* Formatting function for row details - modify as you need */
					function format ( d ) {
					    // `d` is the original data object for the row
					    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
					        '<tr>'+
					            '<td>Full name:</td>'+
					            '<td>'+d.pageId+'</td>'+
					        '</tr>'+
					        '<tr>'+
					            '<td>Extension number:</td>'+
					            '<td>'+d.pageDisplay+'</td>'+
					        '</tr>'+
					        '<tr>'+
					            '<td>Extra info:</td>'+
					            '<td>And any further details here (images etc)...</td>'+
					        '</tr>'+
					    '</table>';
					}
					
					// Add event listener for opening and closing details
				    $('#tablePages tbody').on('click', 'td.details-control', function () {
				        var tr = $(this).closest('tr');
				        var row = tablePages.row( tr );
				 
				        if ( row.child.isShown() ) {
				            // This row is already open - close it
				            row.child.hide();
				            tr.removeClass('shown');
				        }
				        else {
				            // Open this row
				            row.child( format(row.data()) ).show();
				            tr.addClass('shown');
				        }
				    } );
					
					
					// $( '#btn-addApplication' ).on( "click", showModal( "./appForm/", "#appn" ) );
					// $( '#btn-addPage' ).on( "click", showModal( "./pageForm/", "#page" ) );
					// $( '#btn-addPermission' ).on( "click", showModal( "./permForm/", "#perm" ) );
					// $( '#btn-addSecretQuestion' ).on( "click", showModal( "./secretQuestionForm/", "#sctQ" ) );
					// $( '#btn-addEntities' ).on( "click", showModal( "./entityForm/", "#entt" ) );

					
					$( '#btn-addApplication' ).on( "click", function() {
						showModal( "./appForm/", "#appn" );
					} );

					$( '#btn-addPage' ).on( "click", function() {
						showModal( "./pageForm/", "#page" );
					} );
					
					$( '#btn-addPermission' ).on( "click", function() {
						showModal( "./permForm/", "#perm" );
					} );

					$( '#btn-addSecretQuestion' ).on( "click", function() {
						showModal( "./secretQuestionForm/", "#sctQ" );
					} );
					
					$( '#btn-addEntities' ).on( "click", function() {
						showModal( "./entityForm/", "#entt" );
					} );
					
					$( '#myTab a[data-toggle="tab"]' ).on( 'shown.bs.tab', function( e ) {
						if ( $( e.target ).attr( 'href' ) == "#faq-tab-1" ) {
							// doSomethingNow();
							tableApps.clear().draw();
							tableApps.ajax.url( "./getJsonApps" ).load();
						} else if ( $( e.target ).attr( 'href' ) == "#faq-tab-2" ) {
							tablePages.clear().draw();
							tablePages.ajax.url( "./getJsonPages" ).load();
						} else if ( $( e.target ).attr( 'href' ) == "#faq-tab-3" ) {
							tablePermission.clear().draw();
							tablePermission.ajax.url( "./getJsonPermisisons" ).load();
						} else if ( $( e.target ).attr( 'href' ) == "#faq-tab-4" ) {
							tableSecQues.clear().draw();
							tableSecQues.ajax.url( "./getJsonSecretQuestions" ).load();
						} else if ( $( e.target ).attr( 'href' ) == "#faq-tab-5" ) {
							tableEntity.clear().draw();
							tableEntity.ajax.url( "./getJsonEntities" ).load();
						}
					} );
				}
			} );

	$( this ).initializeTable();

} );

$( document ).on( 'click', 'table .dropdown-toggle', function( e ) {
	console.log( "Entro al clic de la tabla.." );
	e.stopImmediatePropagation();
	e.stopPropagation();
	e.preventDefault();
} );

// override dialog's title function to allow for HTML titles
$.widget( "ui.dialog", $.extend( {}, $.ui.dialog.prototype, {
	_title : function( title ) {
		var $title = this.options.title || '&nbsp;'
		if ( ( "title_html" in this.options ) && this.options.title_html == true )
			title.html( $title );
		else
			title.text( $title );
	}
} ) );

function showModal( p_url, p_form ) {
	$( this ).callAjax( p_url, p_form );
	$( "#formularioModal .modal-header h4" ).text( "New " );
}

function showModalConfirmation( p_url, p_table ) {
	$( "#dialog-confirm" )
			.removeClass( 'hide' )
			.dialog(
					{ // Abrir div del modal
						resizable : false,
						width : '320',
						modal : true,
						title : "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> Empty the recycle bin?</h4></div>",
						title_html : true,
						buttons : [ {
							html : "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; Delete",
							"class" : "btn btn-danger btn-minier",
							click : function() {
								$.ajax( {
									url : p_url,
									success : function( result ) {
										if ( !( result === null ) ) {
											p_table.clear().draw();
											p_table.ajax.reload();
										}
									}
								} );
								$( this ).dialog( "close" );
							}
						}, {
							html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; Cancel",
							"class" : "btn btn-minier",
							click : function() {
								$( this ).dialog( "close" );

							}
						} ]
					// Cierre de botones del modal
					} );// Fin del bloque para activar el cuadro de dialogo le remueve la clase oculta
}

$.fn.callAjax = function( p_url, p_form ) {
	$.ajax( {
		type : "POST",
		url : p_url,
		// dataType: "json",
		timeout : 100000,
		success : function( result ) {
			if ( !( result === null ) ) {
				$( ".modal-body .row" ).html( result );
				$( "#btnSaveConfiguration" ).on( "click", function() {
					$( p_form ).submit();
					// p_table.clear().draw();
					// p_table.ajax.reload();
				} );
			}
		},
		error : function( e ) {
			alert( "ERROR: ", e );
		}
	} );
}
