/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			// Desactivar la alert de error de los DataTables
			$.fn.dataTable.ext.errMode = 'none';
			// Activated the table
			var tableUser = $('#tableUsers').DataTable({
				// serverSide: true,
				// stateSave : true,
				paging : false,
				autoWidth : false,
				ordering : false,
				destroy : true,
				responsive : true,
				dom : "Tgt",// + "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
				select : {
					style : 'single',
					selector : 'td:first-child',
					blurable : true
				},
				ajax : {
					url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/user/all",
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error : function(error) {
						console.log(error.statusText);
					}
				},
				columns : [ {
					// title : "Id",
					data : "userId"
				}, {
					// title : "Bu",
					data : "userEmesCompany"
				}, {
					// title : "Num Employee",
					data : "userEmesId"
				}, {
					// title : "Account User",
					data : "userUsername"
				}, /*
					 * { //title : "Created By", data : "passwords[0].pswdActive", render : function(data, type, row){
					 * 
					 * var l_checkLabel = data == 'true' ? "Active" : "Inactive"; var l_labelSpan = data == 'true' ?
					 * "info" : "default"; return "<span class='label label-"+l_labelSpan+"'>"+l_checkLabel+"</span>"; } },
					 */{
					// title : "Update By",
					data : "userUpdateBy"
				}, {
					// title : "Active",
					data : null,
					render : function(data, type, row) {
						var l_checkLabel = data.userActive == false ? "Inactive" : " Active";
						var l_labelSpan = data.userActive == false ? "danger" : "primary";
						return "<span class='label label-" + l_labelSpan + "'>" + l_checkLabel + "</span>";

					},
					className : "client-status"
				} ]

			});

			var tableUserRoles = $('#tableRoles').DataTable(
					{
						paging : false,
						info : false,
						autoWidth : false,
						searching : false,
						ordering : false,
						destroy : true,
						language : {
							infoEmpty : "No entries to show"
						},
						createdRow : function(row, data, dataIndex) {
							$(row).attr("title", "Description Role: " + data.roleDescription);
						},
						columns : [
								{
									data : "usroId"
								},
								{
									data : null,// "roleName",
									render : function(data, type, row) {
										return "[" + data.roleId + "] " + data.roleName;
									}
								},
								{
									data : null,// "roleAppnId.appnName",
									render : function(data, type, row) {
										return "[" + data.roleAppnId.appnId + "] " + data.roleAppnId.appnName;
									}
								},
								{
									data : null,
									render : function(data, type, row) {
										var l_check = data.usroActive == false ? "" : " checked";
										return " <div class='checkbox checkbox-success pull-right'>" + " <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usroActive + "' " + l_check
												+ " aria-label='Single checkbox Two'>" + " <label></label>" + " </div>";
									}
								} ]

					});

			// $('#tableApps').on('xhr.dt', dataTableAjaxReturn).DataTable
			var tableUserApps = $('#tableApps').DataTable(
					{
						paging : false,
						info : false,
						processing : false,
						autoWidth : false,
						select : false,
						searching : false,
						ordering : false,
						destroy : true,
						createdRow : function(row, data, dataIndex) {
							$(row).attr("title", "Description Application: " + data.appnDescription);
						},
						columns : [
								{
									// title : "Consecutivo",
									data : "usapId"
								},
								{
									// title : "Application",
									data : null,// "appnName",
									render : function(data, type, row) {
										return "[" + data.appnId + "] " + data.appnName;
									}
								},
								{
									// title : "Active",
									data : // "usapActive"
									null,
									render : function(data, type, row) {
										var l_check = data.usapActive == false ? "" : " checked";
										return " <div class='checkbox checkbox-success pull-right'>" + " <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usapActive + "' " + l_check
												+ " aria-label='Single checkbox Two'>" + " <label></label>" + " </div>";

									}
								} ]
					});

			var tableRolesActive = $('#tableRolesActive').DataTable({
				paging : true,
				info : true,
				autoWidth : false,
				searching : true,
				ordering : false,
				destroy : true,
				language : {
					infoEmpty : "No entries to show"
				},
				ajax : {
					url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonRolesActive",
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error : function(error) {
						console.log(error.statusText);
					}
				},
				columns : [ {
					title : "Id",
					data : "roleId"
				}, {
					title : "Rol Name",
					data : "roleName"
				}, {
					title : "Rol Description",
					data : "roleDescription"
				}, {
					title : "Application Name",
					data : null,
					render : function(data, type, row) {
						return "[" + data.roleAppnId.appnId + "] " + data.roleAppnId.appnName;
					}
				} ]
			});

			var tableAppsActive = $('#tableAppsActive').DataTable({
				paging : true,
				info : true,
				autoWidth : false,
				searching : true,
				ordering : false,
				destroy : true,
				language : {
					infoEmpty : "No entries to show"
				},
				ajax : {
					url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonApps",
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				// error : handleAjaxError

				},
				columns : [ {
					title : "Id",
					data : "appnId"
				}, {
					title : "App Name",
					data : "appnName"
				}, {
					title : "App Description",
					data : "appnDescription"
				} ]
			});

			$('#tableUsers tbody').on("click", "tr", selectedRowUser);

			$('#tableRolesActive tbody').on("click", "tr", function() {
				if (!$(this).hasClass('selected')) {
					tableRolesActive.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});

			$('#tableAppsActive tbody').on("click", "tr", function() {
				if (!$(this).hasClass('selected')) {
					tableAppsActive.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});

			// Eventos de boton de Usuarios
			$('#sUsers').on("click", function() {
				var txtSearch = $('#txtSearch').val();
				tableUser.search(txtSearch).draw();
			});

			$('#btn-loading').on("click", function() {
				tableUser.clear().draw();
				tableUser.ajax.reload();
			});

			// Eventos de boton para modificar informacion
			$('#btn-edit').on("click", getUserEditPop);

			$('#btn-New').on("click", getUserNewPop);
			
			$("#btnSave").on("click", saveUserPop);

			$('#btn-resetPass').on("click", resetFrmReset);

			$('#btn-delete').on("click", deleteUser);

			$('#ckbPass').change(function() {
				if ($(this).is(':checked')) {
					$('#txtPass').removeAttr('disabled').attr("required", true).attr("placeholder", "Enter New Password");
					$('#txtPass').focus();
				} else {
					$('#txtPass').attr('disabled', 'disabled').removeAttr("placeholder").attr("required", false).val(null);
					$("#txtPass").parents().removeClass("has-error");
				}
			});

			$('#btnSaveReset').on("click", saveReset);

			$('#btn-AssignRol').on("click", function() {
				tableRolesActive.ajax.reload();
			});

			$('#btn-AssignAppn').on("click", function() {
				tableAppsActive.ajax.reload();
			});

			$('#btnSaveRolesActive').on("click", saveAssignRole);

			$('#btnSaveAppsActive').on("click", saveAssignApplication);

			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});

			$('#txtPass').on('change', function() {
				$(this).parent().addClass("has-error");
			});

			$('[data-trigger="hover"]').tooltip();

			toastr.options = {
				closeButton : true,
				debug : false,
				progressBar : true,
				preventDuplicates : true,
				positionClass : 'toast-top-right',
				onclick : null,
				showDuration : 400,
				hideDuration : 1000,
				timeOut : 7000,
				extendedTimeOut : 1000,
				showEasing : "swing",
				hideEasing : "linear",
				showMethod : "fadeIn",
				hideMethod : "fadeOut"
			};
			
			
			
			clearInfoUser();
		}

	});

	$(this).initializeTable();

});

Dropzone.options.dropzoneForm = {
        paramName: "file", // The name that will be used to transfer the file
        maxFilesize: 2, // MBc
        maxFiles : 1,
        acceptedFiles : "image/*",
        addRemoveLinks : true,
        thumbnailMethod: "contain",
        thumbnailWidth : 200,
        thumbnailHeight : 200,
        accept: function(file, done) {
            if (file.name == "justinbieber.jpg") {
              done("Naha, you don't.");
            }
            else { done(); }
          },
        dictDefaultMessage: "<strong>Drop files here or click to upload. </strong></br> (This is just a demo dropzone. Selected files are not actually uploaded.)"
    };

function shoModalConfirmation(p_url, p_table) {
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
					swal("Deleted!", "The record has been desable.", "success");
				},
				error : function(e) {
					// toastr.error(e.responseText, "Error");
					console.log(JSON.stringify(e));
					swal(e.status + " - " + e.statusText, e.responseText, "error");
				}
			});

		} else {
			swal("Cancelled", "The record has not been disable :)", "error");
		}
	});
}

function handleAjaxError(xhr, textStatus, error) {
	if (textStatus === 'timeout') {
		toastr.error('The server took too long to send the data.', "Error");
	} else if (textStatus === 'parsererror') {
		toastr.error('Requested JSON parse failed.', "Error");
	} else if (textStatus === 'abort') {
		toastr.error('Ajax request aborted.', "Error");
	}else{
//		toastr.error(xhr.responseText, "[HTTP "+xhr.status+"] "+error);
		toastr.error("[HTTP "+xhr.status+"] "+error, "Error");
	}
}

function selectedRowUser(event) {
	var $row = $(this);
	var FilaActual = $('#tableUsers').DataTable().row($row).data();

	if (!$($row).hasClass('selected')) {
		clearInfoUser();
		$($row).addClass('selected');

		$('#btn-edit').attr('disabled', false);

		if (FilaActual.userEmesCompany != null && FilaActual.userEmesId != null) {
			$.ajax({
				url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonEmp/" + FilaActual.userEmesCompany + "/" + FilaActual.userEmesId,
				success : function(data) {
					$('#img-Emp').attr("src", "https://admportlct.lctpc.com.mx/Directorio/Fotos/" + FilaActual.userEmesCompany + "/emp_" + FilaActual.userEmesId + ".bmp");
					$('#lblName').text(data.nombre);
					$('#lblJob').text(data.nomPuesto);
					$('#lblDepto').text(data.nomDepartamento);
					d = new Date(data.fechaIngreso);
					var str, year, month, day, hour, minute, d, finalDate;
					year = d.getFullYear();
					month = pad(d.getMonth() + 1);
					day = pad(d.getDate());
					hour = pad(d.getHours());
					minutes = pad(d.getMinutes());

					finalDate = day + "/" + month + "/" + year;

					$('#lblAdmission').text(finalDate);
					var l_labelActive = (data.activo == 'Y' ? "Active" : "Inactive");
					var l_labelSpanActive = (data.activo == 'Y' ? "primary" : "danger");
					$('#lblNomActive').removeClass();
					$('#lblNomActive').addClass("label label-" + l_labelSpanActive).text(l_labelActive);
					// $('#lblReason').text(data.motivo);
				},
				error : handleAjaxError
			});
		}

		if (FilaActual.userActive) {
			$('#btn-delete')/*
							 * .attr('href', $(location) .attr('origin') + "/HelpDeskLctpcThymeleaf/userFormulario/"+
							 * FilaActual.userId )
							 */
			.removeAttr('disabled');
			$('#btn-resetPass').attr("data-target", "#myModalPass").removeAttr('disabled');

			$('#btn-AssignRol').removeAttr('disabled').attr('data-target', "#myModalRoles");
			$('#btn-AssignAppn').removeAttr('disabled').attr('data-target', "#myModalAppns");

		}
		$('#lblUser').text(FilaActual.userUsername != null ? FilaActual.userUsername : '');
		var txtName = (FilaActual.acinName != null ? FilaActual.acinName : "") + (FilaActual.acinLastName != null ? FilaActual.acinLastName : "");
		$('#lblEmail').text(FilaActual.acinEmail != null ? FilaActual.acinEmail : '');
		$('#lblAltEmail').text(FilaActual.acinAlternateEmail != null ? FilaActual.acinAlternateEmail : '');
		var l_checkLabel = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "Active" : "Inactive") : "No Password";
		var l_labelSpan = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "primary" : "default") : "warning";

		$('#lblPass').addClass("label label-" + l_labelSpan).text(l_checkLabel);

		// $('#tableRoles').DataTable().clear().draw();
		$('#tableRoles').DataTable().ajax.url("./api/v1.0/user/rolesAssigned/" + FilaActual.userId).load();

		// $('#tableApps').DataTable().clear().draw();
		$('#tableApps').DataTable().ajax.url("./api/v1.0/user/appAssigned/" + FilaActual.userId).load();

	}
}

function saveReset() {

	var divAlert = $('#divAlert');
	$('#divAlert').empty();
	$('#divAlert').removeClass("alert-danger").removeClass("alert-info");

	if ($("#txtPass").is(":required") && $("#txtPass").val() == "") {
		$("#txtPass").parent().addClass("has-error");

	} else {
		var l_data = jQuery.param({
			"username" : $("#lblUser").text(),
			"passNew" : $("#txtPass").val(),
			"reqNewPass" : $("#ckbReqNewPass").is(':checked')
		});

		$.ajax({
			url : "./api/v1.0/user/reset?" + l_data,
			type : "PUT",
			// contentType: "multipart/form-data",
			// processData: false,
			data : l_data,
			timeout : 100000,
			success : function(result) {
				divAlert.removeClass('alert-danger').addClass('alert-info');
				divAlert.append($("<h2 class='font-bold'>").text(result));

				if ($('#ckbPass').is(':checked'))
					$('#ckbPass').click(); // se manda el evento del click que removiendo el pincge attributo por que
											// no jala.
				$('#txtPass').attr('disabled', 'disabled').removeAttr("placeholder").attr("required", false).val(null);
				$("#txtPass").parents().removeClass("has-error");
			},
			error : function(e) {
				divAlert.removeClass('alert-info').addClass('alert-danger');
				divAlert.append($("<h4 class='font-bold'>").text(e.responseText));
			}
		});
	}

}

function getUserNewPop(event){
	resetForm($("#frmUser"));
	var $form = $("#frmUser");
	$form.attr("method","POST");
	$("#gridSystemModal .modal-header h4 span").text("New User");
}

function getUserEditPop(event) {
	resetForm($("#frmUser"));
	var l_rowData = $('#tableUsers').DataTable().row('tbody tr.selected').data();
	$.ajax({
		url : "./api/v1.0/user/" + l_rowData.userId,
		type : "GET",
		contentType : "application/json",
		timeout : 100000,
		success : function(result) {

			if (!(result === null)) {
				var l_frm = $("#frmUser");
				populateForm(l_frm, result);
				l_frm.attr("method", 'PUT');
				l_frm.attr("action", $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/user/");
			}
		},
		error : handleAjaxError
	});
	
	$("#gridSystemModal .modal-header h4 span").text("Edit User");
}

function saveUserPop(event) {
	// $.parseJSON(l_frm.serialize()); --convertir String a JSON
	// JSON.stringify(); --convertir JSON a String
	var $form = $("#frmUser");

	$.ajax({
		type : $form.attr("method"),
		url : './api/v1.0/user/',
		data : JSON.stringify($form.serializeObject()),
		contentType : "application/json",
		success : function(dataResult, status) {
			console.log(status);
			toastr.success("User ["+dataResult.userId+"]"+dataResult.userUsername+" Created", 'Successfully');
			$('#tableUsers').DataTable().clear().draw();
			$('#tableUsers').DataTable().ajax.reload();
			clearInfoUser();
			$('#myModalUsers').modal('hide');
		},
		error : handleAjaxError

	}); // Ajax submit

}

function deleteUser(event) {

	event.preventDefault(); // elimina el evento del link.
	var $l_row = $('#tableUsers').DataTable().row('tbody tr.selected');
	var l_rowData = $l_row.data();

	var l_linkDelete = "./api/v1.0/user/" + l_rowData.userId;
	if (!$(this).attr("disabled") && l_rowData.userActive) { // Verificar que el boton no este disabled
		var l_tableUser = $('#tableUsers').DataTable();
		shoModalConfirmation(l_linkDelete, l_tableUser);
		l_tableUser.clear().draw();
		l_tableUser.ajax.reload();
		clearInfoUser();
	}
}

function resetForm($form) {
	$form.find('input:text, input:password, input:file, input:hidden, select, textarea').val('');
	$form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
	var $chks = $form.find('input:checkbox');
	$('.iradio_square-green').removeClass('checked');
	$('.icheckbox_square-green').removeClass('checked');
	
}

function resetFrmReset() {

	resetForm($("#frmReset"));

	$('#divAlert').empty();
	$('#divAlert').removeClass("alert-danger").removeClass("alert-info");
	$('#txtPass').attr('disabled', 'disabled').removeAttr("placeholder").attr("required", false);// .val(null);
	$("#txtPass").parents().removeClass("has-error");
}

function saveAssignRole() {
	var l_tableRolesActive = $('#tableRolesActive').DataTable();
	var l_tableUsers = $('#tableUsers').DataTable();

	var l_rowRoleData = l_tableRolesActive.row(l_tableRolesActive.$('tr.selected')).data();
	var l_rowUserData = l_tableUsers.row(l_tableUsers.$('tr.selected')).data();
	$.ajax({
		url : "./api/v1.0/userRole/" + l_rowUserData.userId + "/" + l_rowRoleData.roleId,
		type : "POST",
		timeout : 100000,
		success : function(result) {
			$('#tableRoles').DataTable().clear().draw();
			$('#tableRoles').DataTable().ajax.url("./api/v1.0/user/rolesAssigned/" + l_rowUserData.userId).load();

			toastr.success("Role " + l_rowRoleData.roleName + " assigned to the user [" + l_rowUserData.userId + "] " + l_rowUserData.userUsername, 'Successfully');
		},
		error : handleAjaxError
		/*function(e) {
			toastr.error(e.responseText, "Error");
		}*/
	});
}

function saveAssignApplication() {
	var l_tableAppsActive = $('#tableAppsActive').DataTable();
	var l_tableUsers = $('#tableUsers').DataTable();

	var l_rowAppData = l_tableAppsActive.row(l_tableAppsActive.$('tr.selected')).data();
	var l_rowUserData = l_tableUsers.row(l_tableUsers.$('tr.selected')).data();
	$.ajax({
		url : "./api/v1.0/appn/userAppn/" + l_rowUserData.userId + "/" + l_rowAppData.appnId,
		type : "POST",
		timeout : 100000,
		success : function(result) {
			$('#tableApps').DataTable().clear().draw();
			$('#tableApps').DataTable().ajax.url("./api/v1.0/user/appAssigned/" + l_rowUserData.userId).load();

			toastr.success("Application " + l_rowAppData.appnName + " assigned to the user [" + l_rowUserData.userId + "] " + l_rowUserData.userUsername, 'Successfully');
		},
		error : handleAjaxError/*function(e) {
			toastr.error(e.responseText, "Error");
		}*/
	});
}

function clearInfoUser() {

	$('#btn-edit').attr('disabled', true);
	$('#img-Emp').attr("src", "./images/user1.png");
	$('#tableUsers').DataTable().$('tr.selected').removeClass('selected');
	$('#lblUser').text("");
	$('#lblEmail').text("");
	$('#lblAltEmail').text("");
	$('#lblName').text("");
	$('#lblJob').text("");
	$('#lblDepto').text("");
	$('#lblAdmission').text("");
	$('#lblNomActive').removeClass();
	$('#lblPass').removeClass();
	$('#lblNomActive').removeClass().text("");
	// $('#lblReason').text(data.motivo);
	$('#btn-delete').attr("disabled", "disabled");
	$('#btn-resetPass').removeAttr("data-target").removeAttr('href').attr("disabled", "disabled");

	$('#btn-AssignRol').attr('disabled', true).removeAttr('data-target');
	$('#btn-AssignAppn').attr('disabled', true).removeAttr('data-target');

	// Limpieza de tablas de roles y aplicaciones asignadas
	$('#tableRoles').DataTable().clear().draw();
	$('#tableApps').DataTable().clear().draw();

}

function populateForm($form, data) {
	resetForm($form);
	$.each(data, function(key, value) {

		var $ctrl = $form.find('[id=' + key + ']');

		if ($ctrl.is('select')) {
			$('option', $ctrl).each(function() {
				if (this.value == value)
					this.selected = true;
			});
		} else if ($ctrl.is('textarea')) {
			$ctrl.val(value);
		} else {
			switch ($ctrl.attr("type")) {
				case "text":
					$ctrl.val(value);
					break;
				case "hidden":
					$ctrl.val(value);
					break;
				case 'textarea' :
					console.log("Enro al textarea");
					break;
				case "checkbox":

					if (value) {
						$('.icheckbox_square-green').addClass('checked');
						$ctrl.attr('checked', true);

					} else {
						$('.icheckbox_square-green').removeClass('checked');
						$ctrl.attr('checked', false);
					}
					break;
				case "radio":
					var $elem = $('input[type="radio"][id='+key+'][value='+value+']');
					$elem.attr('checked', true);
					$elem.parent().addClass('checked');//Esto para la plantilla INSPINIA
					break;
			}
		}
	});
};

function pad(p_num) {
	p_num = "0" + p_num;
	return p_num.slice(-2);
}

$.fn.serializeObject = function() {
	var o = {};

	// var a = this.serializeArray();
	$(this).find('input[type="hidden"], input[type="text"], input[type="password"], input[type="checkbox"], input[type="radio"]:checked, select').each(function() {
		var elemValue = null;

		/*
		 * if ($(this).attr('type') == 'hidden') { // If checkbox is checked do not take the hidden field var $parent =
		 * $(this).parent(); var $chb = $parent.find('input[type="checkbox"][name="' + this.name.replace(/\[/g,
		 * '\[').replace(/\]/g, '\]') + '"]'); if ($chb != null) { if ($chb.prop('checked')) return; } }
		 */
		// console.log("Dento del log.." + this.id);
		if (this.id === null || this.id === undefined || this.id === '')
			return;

		switch ($(this).attr("type")) {
			case "text":
				elemValue = $(this).val();
				break;
			case "hidden":
				elemValue = $(this).val();
				/*
				 * var $parent = $(this).parent(); var $chb = $parent.find('input[type="checkbox"][name="' +
				 * this.name.replace(/\[/g, '\[').replace(/\]/g, '\]') + '"]'); if ($chb != null) { if
				 * ($chb.prop('checked')) return; }
				 */
				break;
			case "checkbox":
				elemValue = $(this).is(':checked');
				break;
			case "radio" :
				elemValue = $(this).val();
				break;
			default:
				if ($(this).is('select'))
					elemValue = $(this).find('option:selected').val();
				else
					elemValue = $(this).val();
				break;
		}
		
		if(elemValue === null ||  elemValue === '' || elemValue === undefined){
			return;
		}
		
		if (o[this.id] !== undefined) {
			if (!o[this.id].push) {
				o[this.id] = [ o[this.id] ];
			}
			o[this.id].push(elemValue);
		} else {

			o[this.id] = elemValue;
		}

	});
	return o;
}
