/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			// Activated the table
			var tableUser = $('#tableUsers').DataTable({

				//autoWidth : true,
				//paging : false,
				//info : false,
				//searching : true,
				//ordering : true,
				destroy : true,
				//stateSave : true,
				responsive : true,
				lengthChange : false,
				//iDisplayLength : 10,
				//lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
				dom : "<'row' lTgt>" ,//+ "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
				select : {
			        style:    'single',
			        selector: 'td:first-child'
			    },
				ajax : {
					url : "./getJsonUsers2",
					type : "POST",
					contentType : "application/json; charset=utf-8",
					dataType : "json"
						
				},
				columns : [ {
					title : "Id",
					data : "userId"
				}, {
					title : "Account User",
					data : "userUsername"
				}, {
					title : "Bu",
					data : "userEmesCompany"
				}, {
					title : "Num Employee",
					data : "userEmesId"
				}, {
					title : "Created By",
					data : "userCreatedBy"
				}, {
					title : "Update By",
					data : "userUpdateBy"
				}, {
					title : "Active",
					data : null,
					render : function(data, type, row) {
						var l_check = data.userActive == false ? "" : " checked";
						var l_checkLabel = data.userActive == false ? "Inactive" : " Active";
						var l_labelSpan = data.userActive == false ? "warning" : "primary";
						return "<span class='label label-"+l_labelSpan+"'>"+l_checkLabel+"</span>";
						//return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='" + data.userActive + "' " + l_check + "/><span class='lbl'></span></label>";
					},
					className : "client-status"
				},
				{
					title : "Actions",
					data : null,
					render : function(data, type, row) {
						return "<div class='hidden-sm hidden-xs action-buttons'>"
							+"<a class='green' id='id-btn-edit' href='#gridSystemModal' role='button' data-toggle='modal'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"   	
							+"<a class='red' id='id-btn-dialog2' href='./userFormulario/"+ data.userId+"/delete'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>"
						+"</div> "
						+"<div class='hidden-md hidden-lg'>"
						+"<div class='inline pos-rel'>"
							+"<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
								+"<i class='ace-icon fa fa-cog icon-only bigger-110'></i>"
							+"</button>"
							+"<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>"
							+"<li>"
							+"<a href='#gridSystemModal' id='id-btn-edit' role='button' data-toggle='modal' class='tooltip-success' data-rel='tooltip' title='Edit'>"
								+"<span class='green'>"
									+"<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>"
						+"</li>"
							+"<li>"
								+"<a href='./userFormulario/"+ data.userId+"/delete' id='id-btn-dialog2' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
									+"<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>"
							+"</li>"
							
							+"</ul>"
						+"</div>"
					+"</div>";
					},
					className : "gridSystemModal1 center"
				},
				
				]
				
			});
			
			$('#sUsers').on("click", function(){
				var txtSearch = $('#txtSearch123').val();
				console.log(txtSearch);
				tableUser.search( txtSearch ).draw();
				
			} );
			
			
			$('#tableUsers tbody').on("click", ".gridSystemModal1 a#id-btn-edit", function() {
				//var ColumnaActual = $(this).parent().parent().parent().get(0), FilaActual = $('#tableUsers').DataTable().row(ColumnaActual).data();
				var FilaActual = tableUser.row($(this).parents('tr')).data();
				var link = "/" + FilaActual.userId + "/update";
				$.ajax({
					url : "./userFormulario" + link,
					type : "POST",
					success : function(result) {
						if (!(result === null)) {
							$(".modal-body .row").html(result);
							$("#btnSaveConfiguration").on("click", function() {
								$("#user").submit();
							});
						}
					}
				});

				$("#gridSystemModal .modal-header h4 span").text(FilaActual.userUsername);
			});

			var tableUserRoles = $('#tableUserRoles').DataTable({
				deferRender: false,
				paging: false,
				info: false,
				autoWidth : true,
				select : false,
				searching : false,
				ordering : false,
				stateSave : false,
				scrollY : '25vh',
				scrollCollapse : false,
				fixedColumns: {
			        heightMatch: 'auto'
			    },
				columns : [ {
					title : "Consecutivo",
					data : "usroId"
				}, {
					title : "Account User",
					data : "roleName"
				}, {
					title : "Active",
					data : null,
					render : function(data, type, row) {
						var l_check = data.usroActive == false ? "" : " checked";
						return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='" + data.usroActive + "' " + l_check + "/><span class='lbl'></span></label>";
					}
				}

				]
			});

			var tableUserApps = $('#tableUserApps').DataTable({
				deferRender: false,
				paging: false,
				info: false,
				autoWidth : true,
				select : false,
				searching : false,
				ordering : false,
				stateSave : false,
				scrollY : '25vh',
				scrollCollapse : false,
				fixedColumns: {
			        heightMatch: 'auto'
			    },
				columns : [ {
					title : "Consecutivo",
					data : "usapId"
				}, {
					title : "Application",
					data : "appnName"
				}, /*{
					title : "Created By",
					data : "usapCreatedBy"
				}, {
					title : "Update By",
					data : "usapUpdateBy"
				}, */{
					title : "Active",
					data : null,
					render : function(data, type, row) {
						var l_check = data.usapActive == false ? "" : " checked";
						return "<label><input type='checkbox' onclick='return false' class='ace ace-switch ace-switch-6' value='" + data.usapActive + "' " + l_check + "/><span class='lbl'></span></label>";
					}
				}
				]
			});

			$('#tableUsers tbody').on("click", "tr", function() {
				var FilaActual = $('#tableUsers').DataTable().row(this).data();
				
				if (!$(this).hasClass('selected')) {
					tableUser.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
					tableUserRoles.clear().draw();
					tableUserRoles.ajax.url("./getJsonUserRoles/" + FilaActual.userId).load();
					tableUserApps.clear().draw();
					tableUserApps.ajax.url("./getJsonUserApps/" + FilaActual.userId).load();
				}
				
			});
			
			$('#tableUsers tbody').on("click", ".gridSystemModal1 a#id-btn-dialog2", function(e) {
				var FilaActual = tableUser.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); //elimina el evento del link.
		        if(FilaActual.userActive){ //Verifica si el registro se encuentra activo.
		        	$( "#dialog-confirm" ).removeClass('hide').dialog({ //Abrir div del modal
						resizable: false,
						width: '320',
						modal: true,
						title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> Empty the recycle bin?</h4></div>",
						title_html: true,
						buttons: [
							{
								html: "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; Delete User",
								"class" : "btn btn-danger btn-minier",
								click: function() {
									$.ajax({
										url : linkDelete,
										success : function(result) {
											if (!(result === null)) {
												console.log("Guardo...");
												tableUser.clear().draw();
												tableUser.ajax.reload();
												tableUserApps.clear().draw();
												tableUserRoles.clear().draw();
											}
										}
									});
									$( this ).dialog( "close" );
								}
							}
							,
							{
								html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; Cancel",
								"class" : "btn btn-minier",
								click: function() {
									$( this ).dialog( "close" );

								}
							}
						]//Cierre de botones del modal
					});//Fin del bloque para activar el cuadro de dialogo le remueve la clase oculta
		        }//Fin del If para revisar si esta activo el registro
				
					
			});

		}
	});

	$(this).initializeTable();
	
	
	
});



