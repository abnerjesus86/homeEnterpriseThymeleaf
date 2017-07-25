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
				autoWidth : true,
				ordering : false,
				destroy : true,
				responsive : true,
				//iDisplayLength : 10,
				//lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
				dom : "Tgt" ,//+ "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
				select : {
			        style:    'single',
			        selector: 'td:first-child'
			    },
				columns : [ {
					//title : "Id",
					data : "userId"
				}, {
					//title : "Account User",
					data : null,//"userUsername"
					render : function(data, type, row){
						return "<a data-toggle='tab' href='#contact-1' class='client-link'>"+data.userUsername+"</a>";
					}
				}, {
					//title : "Bu",
					data : "userEmesCompany"
				}, {
					//title : "Num Employee",
					data : "userEmesId"
				}, {
					//title : "Created By",
					data : "passwords[0].pswdActive",
					render : function(data, type, row){
						
						var l_checkLabel = data == 'true' ? "Active" : "Inactive";
						var l_labelSpan = data == 'true' ? "info" : "default";
						return "<span class='label label-"+l_labelSpan+"'>"+l_checkLabel+"</span>";
					}
				}, {
					//title : "Update By",
					data : "userUpdateBy"
				}, {
					//title : "Active",
					data : null,
					render : function(data, type, row) {
						var l_checkLabel = data.userActive == false ? "Inactive" : " Active";
						var l_labelSpan = data.userActive == false ? "danger" : "primary";
						return "<span class='label label-"+l_labelSpan+"'>"+l_checkLabel+"</span>";
						
					},
					className : "client-status"
				},
				{
					//title : "Actions",
					data : null,
					render : function(data, type, row) {
						
						return "<div class='btn-group'>"
						+ "<a class='btn-xs' id='btn-edit' href='#myModalUsers' role='button' data-toggle='modal'><i class='fa fa-pencil fa-lg'></i></a>"
						+ "<a class='btn-xs' id='btn-delete' href='./userFormulario/"+ data.userId	+ "/delete'><i class='fa fa-trash-o fa-lg'></i></a>"
						+ "</div> ";
						
					},
					className : "gridSystemModal1 center"
				}
				
				]
				
			});
			
			$('#sUsers').on("click", function(){
				var txtSearch = $('#txtSearch').val();
				tableUser.search( txtSearch ).draw();
			} );
			
			$('#loading-example-btn').on("click", function(){
				tableUser.clear().draw();
				tableUser.ajax.reload();
			});
			
			$('#tableUsers tbody').on("click", ".gridSystemModal1 a#btn-edit", function() {
				//var ColumnaActual = $(this).parent().parent().parent().get(0), FilaActual = $('#tableUsers').DataTable().row(ColumnaActual).data();
				var FilaActual = tableUser.row($(this).parents('tr')).data();
				var link = "/" + FilaActual.userId + "/update";
				$.ajax({
					url : "./userFormulario" + link,
					type : "POST",
					dataType: "html",
					timeout : 100000,
					success : function(result) {
						
						if (!(result === null)) {
							$(".modal-body .row").html(result);
							$("#btnSave").on("click", function() {
								$("#user").submit();
							});
						}
					},
					error : function(e) {
						alert("ERROR: ", e);
					}
				});

				$("#gridSystemModal .modal-header h4 span").text(FilaActual.userUsername);
			});

			var tableUserRoles = $('#tableRoles').DataTable({
				paging: false,
				info: false,
				autoWidth : true,
				select : false,
				searching : false,
				ordering : false,
				columns : [ {
					//title : "Id",
					data : "usroId"
				}, {
					//title : "Account User",
					data : null,//"roleName",
					render : function(data, type, row){
						return "["+data.roleId+"] " +data.roleName;
					}
				}, {
					data : null,//"roleAppnId.appnName",
					render : function(data, type, row){
						return "["+data.roleAppnId.appnId+"] " + data.roleAppnId.appnName;
					}
				}, {
					//title : "Active",
					width: "20px",
					data : null,
					render : function(data, type, row){
						var l_check = data.usroActive == false ? "" : " checked";
						return " <div class='checkbox checkbox-primary'>"+
			            			" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usroActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
			            			" <label></label>"+
			            	   " </div>";
					}
				}

				]
			});

			var tableUserApps = $('#tableApps').DataTable({
				paging: false,
				info: false,
				autoWidth : true,
				select : false,
				searching : false,
				ordering : false,
				columns : [ {
					//title : "Consecutivo",
					data : "usapId"
				}, {
					//title : "Application",
					data : null,//"appnName",
					render : function(data, type, row){
						return "["+data.appnId+"] " + data.appnName ;
					}
				}, {
					//title : "Active",
					//width: "20px",
					data : //"usapActive"
						null,
					render : function(data, type, row) {
						var l_check = data.usapActive == false ? "" : " checked";
						return " <div class='checkbox checkbox-primary'>"+
                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usapActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                		" <label></label>"+
                		" </div>";
						
					}
				}
				]
			});

			$('#tableUsers tbody').on("click", "tr", function() {
				var FilaActual = $('#tableUsers').DataTable().row(this).data();
				console.log(FilaActual);
				$('#lblUser').text( FilaActual.userUsername != null ? FilaActual.userUsername : '' );
				var txtName = (FilaActual.acinName != null ? FilaActual.acinName : "") + (FilaActual.acinLastName != null ? FilaActual.acinLastName : "");
				$('#lblName').text(txtName);
				$('#lblEmail').text( FilaActual.acinEmail != null ? FilaActual.acinEmail : '' );
				$('#lblAltEmail').text(FilaActual.acinAlternateEmail != null ? FilaActual.acinAlternateEmail : '');

				var	l_checkLabel = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "Active" : "Inactive") :  "No Password";
				var	l_labelSpan = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "info" : "default") : "warning";

				$('#lblPass').addClass("label label-"+l_labelSpan) .text( l_checkLabel );
				
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
												/*tableUserApps.clear().draw();
												tableUserRoles.clear().draw();*/
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
			tableUser.clear().draw();
			tableUser.ajax.url($(location).attr('origin') + "/HelpDeskLctpcThymeleaf/getJsonUsers2").load();
		}
	
	});

	$(this).initializeTable();
	
	
	
});



