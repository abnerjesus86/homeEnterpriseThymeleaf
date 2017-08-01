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
				//serverSide: true,
				autoWidth : true,
				ordering : false,
				destroy : true,
				responsive : true,
				//iDisplayLength : 10,
				//lengthMenu : [ [ 10, 25, 50, 100, 500, 1000, 2000 ], [ 10, 25, 50, 100, 500, 1000, 2000 ] ],
				dom : "Tgt" ,//+ "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
				select : {
			        style:    'single',
			        //selector: 'td:first-child',
			        blurable: true
			    },
				columns : [ {
					//title : "Id",
					data : "userId"
				}, {
					//title : "Bu",
					data : "userEmesCompany"
				}, {
					//title : "Num Employee",
					data : "userEmesId"
				}, {
					//title : "Account User",
					data : null,//"userUsername"
					render : function(data, type, row){
						return "<a data-toggle='tab' href='#contact-1' class='client-link'>"+data.userUsername+"</a>";
					}
				},  /*{
					//title : "Created By",
					data : "passwords[0].pswdActive",
					render : function(data, type, row){
						
						var l_checkLabel = data == 'true' ? "Active" : "Inactive";
						var l_labelSpan = data == 'true' ? "info" : "default";
						return "<span class='label label-"+l_labelSpan+"'>"+l_checkLabel+"</span>";
					}
				},*/ {
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
			
			$('#btn-edit').on("click", function() {
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
				searching : false,
				ordering : false,
				destroy : true,
				createdRow : function(row, data, dataIndex){
					$(row).attr("title","Description Role: "+data.roleDescription);
				},
				columns : [ {
						data : "usroId"
					}, {
						data : null,//"roleName",
						render : function(data, type, row){
							return "["+data.roleId+"] "+data.roleName;
						}
					}, {
						data : null,//"roleAppnId.appnName",
						render : function(data, type, row){
							return "["+data.roleAppnId.appnId+"] " + data.roleAppnId.appnName;
						}
					}, {
	
						data : null,
						render : function(data, type, row){
							var l_check = data.usroActive == false ? "" : " checked";
							return " <div class='checkbox checkbox-primary pull-right'>"+
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
				destroy : true,
				createdRow : function(row, data, dataIndex){
					$(row).attr("title","Description Application: "+data.appnDescription);
				},
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
						return " <div class='checkbox checkbox-primary pull-right'>"+
                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usapActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                		" <label></label>"+
                		" </div>";
						
					}
				}
				]
			});
			
			$('#tableUsers tbody').on("click", "tr", function() {
				var FilaActual = $('#tableUsers').DataTable().row(this).data();
				
				if (!$(this).hasClass('selected')) {
					tableUser.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
					$('#lblUser').text( "" );
					$('#lblEmail').text( "" );
					$('#lblAltEmail').text( "" );
					$('#lblName').text( "" );
					$('#lblJob').text( "" );
					$('#lblDepto').text( "" );
					$('#lblAdmission').text( "" );
					$('#lblNomActive').removeClass();
					$('#lblPass').removeClass();
					//$('#lblReason').text(data.motivo);
					
					if(FilaActual.userEmesCompany != null && FilaActual.userEmesId != null){
						$.ajax({
							url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/getJsonEmp/"+FilaActual.userEmesCompany+"/"+FilaActual.userEmesId,
							success : function(data) {
								$('#lblName').text(data.nombre);
								$('#lblJob').text(data.nomPuesto);
								$('#lblDepto').text(data.nomDepartamento);
								$('#lblAdmission').text(data.fechaIngreso);
								var	l_labelActive = (data.activo == 'Y'? "Active" : "Inactive");
								var	l_labelSpanActive  = (data.activo == 'Y' ? "success" : "danger");
								$('#lblNomActive').removeClass();
								$('#lblNomActive').addClass("label label-"+l_labelSpanActive).text( l_labelActive );
								//$('#lblReason').text(data.motivo);
							}
						});
					}
					
					if(FilaActual.userActive == false){
						$('#btn-delete').removeAttr('href').attr("disabled", "disabled");
						$('#btn-resetPass').removeAttr('href').attr("disabled", "disabled");
						
					} else{
						$('#btn-delete').attr('href', $(location)
							.attr('origin') + "/HelpDeskLctpcThymeleaf/userFormulario/"+ FilaActual.userId )
							.removeAttr('disabled');
						$('#btn-resetPass')
								.removeAttr('disabled');
					}
					$('#lblUser').text( FilaActual.userUsername != null ? FilaActual.userUsername : '' );
					var txtName = (FilaActual.acinName != null ? FilaActual.acinName : "") + (FilaActual.acinLastName != null ? FilaActual.acinLastName : "");
					$('#lblEmail').text( FilaActual.acinEmail != null ? FilaActual.acinEmail : '' );
					$('#lblAltEmail').text(FilaActual.acinAlternateEmail != null ? FilaActual.acinAlternateEmail : '');
					var	l_checkLabel = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "Active" : "Inactive") :  "No Password";
					var	l_labelSpan  = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "success" : "default") : "warning";
					
					$('#lblPass').addClass("label label-"+l_labelSpan).text( l_checkLabel );
					
					tableUserRoles.clear().draw();
					tableUserRoles.ajax.url("./getJsonUserRoles/" + FilaActual.userId).load();
					
					tableUserApps.clear().draw();
					tableUserApps.ajax.url("./getJsonUserApps/" + FilaActual.userId).load();
					
				}
				
			});
			
			$('#btn-delete').on("click", function(e) {
				//var FilaActual = tableUser.row($(this).parents('tr')).data();
				var linkDelete = this;
				e.preventDefault(); //elimina el evento del link.
		        
				if( !$(this).attr("disabled") ) //Verificar que el boton no este disabled
					shoModalConfirmation(linkDelete, tableUser);
					
			});
			
			tableUser.clear().draw();
			tableUser.ajax.url($(location).attr('origin') + "/HelpDeskLctpcThymeleaf/getJsonUsers2").load();
		}
	
	});

	$(this).initializeTable();
	
	
	
});

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
				type : "DELETE",
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

