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
			
			$('#img-Emp').attr("src", "./images/user1.png");
			
			var tableUser = $('#tableUsers').DataTable({
				// serverSide: true,
				saveStatus : true,
				autoWidth : false,
				ordering : false,
				destroy : true,
				responsive : true,
				dom : "Tgt" ,// + "<'row'<'col-md-5'i><'col-md-7 text-right'p>>",
				select : {
			        style:    'single',
			        // selector: 'td:first-child',
			        blurable: true
			    },
			    ajax : {
					url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonUsers2",
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error: function(error){
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
				},  /*
					 * { //title : "Created By", data : "passwords[0].pswdActive", render : function(data, type, row){
					 * 
					 * var l_checkLabel = data == 'true' ? "Active" : "Inactive"; var l_labelSpan = data == 'true' ?
					 * "info" : "default"; return "<span class='label label-"+l_labelSpan+"'>"+l_checkLabel+"</span>"; } },
					 */ {
					// title : "Update By",
					data : "userUpdateBy"
				}, {
					// title : "Active",
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
			
			var tableUserRoles = $('#tableRoles').DataTable({
				paging: false,
				info: false,
				autoWidth : false,
				searching : false,
				ordering : false,
				destroy : true,
				language: {
				    infoEmpty: "No entries to show"
				  },
				createdRow : function(row, data, dataIndex){
					$(row).attr("title","Description Role: "+data.roleDescription);
				},
				
				columns : [ {
						data : "usroId"
					}, {
						data : null,// "roleName",
						render : function(data, type, row){
							return "["+data.roleId+"] "+data.roleName;
						}
					}, {
						data : null,// "roleAppnId.appnName",
						render : function(data, type, row){
							return "["+data.roleAppnId.appnId+"] " + data.roleAppnId.appnName;
						}
					}, {
						data : null,
						render : function(data, type, row){
							var l_check = data.usroActive == false ? "" : " checked";
							return " <div class='checkbox checkbox-success pull-right'>"+
				            			" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usroActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
				            			" <label></label>"+
				            	   " </div>";
						}
					}
				]
				
			});
			
			// $('#tableApps').on('xhr.dt', dataTableAjaxReturn).DataTable
			var tableUserApps = $('#tableApps').DataTable({
				paging: false,
				info: false,
				processing: false,
				autoWidth : false,
				select : false,
				searching : false,
				ordering : false,
				destroy : true,
				createdRow : function(row, data, dataIndex){
					$(row).attr("title","Description Application: "+data.appnDescription);
				},
				columns : [ {
					// title : "Consecutivo",
					data : "usapId"
				}, {
					// title : "Application",
					data : null,// "appnName",
					render : function(data, type, row){
						return "["+data.appnId+"] " + data.appnName ;
					}
				}, {
					// title : "Active",
					data : // "usapActive"
						null,
					render : function(data, type, row) {
						var l_check = data.usapActive == false ? "" : " checked";
						return " <div class='checkbox checkbox-success pull-right'>"+
                		" <input type='checkbox' id='singleCheckbox2' onclick='return false' value='" + data.usapActive +"' "+ l_check+" aria-label='Single checkbox Two'>"+
                		" <label></label>"+
                		" </div>";
						
					}
				}
				]
			});
			
			var tableRolesActive = $('#tableRolesActive').DataTable({
				paging: true,
				info: true,
				autoWidth : false,
				searching : true,
				ordering : false,
				destroy : true,
				language: {
				    infoEmpty: "No entries to show"
				},
				ajax : {
					url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonRolesActive",
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error: function(error){
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
					},{
						title : "Application Name",
						data : null,
						render : function(data, type, row){
							return "["+data.roleAppnId.appnId+"] " + data.roleAppnId.appnName;
						}
					}
				]
			});
			
			var tableAppsActive = $('#tableAppsActive').DataTable({
				paging: true,
				info: true,
				autoWidth : false,
				searching : true,
				ordering : false,
				destroy : true,
				language: {
				    infoEmpty: "No entries to show"
				},
				ajax : {
					url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonApps",
					type : "GET",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					error: function(error){
						console.log(error.statusText);
                    }
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
					}
				]
			});
			
			$('#tableUsers tbody').on("click", "tr", function() {
				selectedRowUser($(this));
			});
			
			$('#tableRolesActive tbody').on("click", "tr", function() {
				if (!$(this).hasClass('selected')){
					tableRolesActive.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});
			
			$('#tableAppsActive tbody').on("click", "tr", function() {
				if (!$(this).hasClass('selected')){
					tableAppsActive.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});
			
			// Eventos de boton de Usuarios
			$('#sUsers').on("click", function(){
				var txtSearch = $('#txtSearch').val();
				tableUser.search( txtSearch ).draw();
			} );
			
			$('#btn-loading').on("click", function(){
				tableUser.clear().draw();
				tableUser.ajax.reload();
			});
			
			// Eventos de boton para modificar informacion
			$('#btn-edit').on("click", function() {
				var FilaActual = $('#tableUsers').DataTable().row('tbody tr.selected').data();
				var link = "/" + FilaActual.userId;
				$.ajax({
					url : "./userFormulario" + link,
					type : "GET",
					contentType : "application/json",
					timeout : 100000,
					success : function(result) {
						
						if (!(result === null)) {
							var l_frm = $("#user");
							l_frm.attr("method", 'post');
							populateForm(l_frm, result);
						}
					},
					error : function(e) {
						alert("ERROR: ", e);
					}
				});

				$("#gridSystemModal .modal-header h4 span").text(FilaActual.userUsername);
			});
			
			$("#btnSave").on("click", function() {
				// $.parseJSON(l_frm.serialize()); --convertir String a JSON
				// JSON.stringify(); --convertir JSON a String
				var l_frm = $("#user");
				console.log(l_frm.find('[id=userId]').val());
				console.log(l_frm.serializeArray());
				/*
				 * $.ajax({ type : 'PUT', url : './userFormulario', data : $.parseJSON(l_frm.serialize()), contentType :
				 * "application/json", success : function(dataResult){ console.log(dataResult); }, error :
				 * function(dataResult){ console.log(dataResult); }
				 * 
				 * }); //Ajax submit
				 */
			});
			
			
			$('#btn-resetPass').on("click", function(e) {
				resetFrmReset($("#frmReset"));
			});
			
			$('#btn-delete').on("click", function(e) {
				var linkDelete = this;
				e.preventDefault(); // elimina el evento del link.
		        
				if( !$(this).attr("disabled") ) // Verificar que el boton no este disabled
					shoModalConfirmation(linkDelete, tableUser);
			});
			
			$('#ckbPass').change(function() {
		        if($(this).is(':checked')){
		        	$('#txtPass').removeAttr('disabled').attr("required",true).attr("placeholder","Enter New Password");
		        	$('#txtPass').focus();
		        }else{
		        	$('#txtPass').attr('disabled','disabled').removeAttr("placeholder").attr("required",false).val(null);
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
			
			$('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
			
			$('[data-trigger="hover"]').tooltip();
			
			
			
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

jQuery.fn.dataTableExt.oApi.fnProcessingIndicator = function ( oSettings, onoff ) {
	if ( typeof( onoff ) == 'undefined' ) {
		onoff = true;
	}
	this.oApi._fnProcessingDisplay( oSettings, onoff );
};

function handleAjaxError( xhr, textStatus, error ) {
	if ( textStatus === 'timeout' ) {
		alert( 'The server took too long to send the data.' );
	}
	console.log("gola "+error);
	// $('#tableApps').DataTable().fnProcessingIndicator( false );
}

function dataTableAjaxReturn(e, settings, json) {

	console.log("Entosd  ");
	if (json == null){
		console.log("Entri al null");
	}
	else if (typeof json.error == 'undefined') {
	   // handle or ignore your error
	   console.log("Entri al type json.error");
   }
   else
   {
     // no error
   }
 
}

function resetForm($form)
{
    $form.find('input:text, input:password, input:file, select, textarea').val('');
    $form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    var $chks = $form.find('input:radio, input:checkbox');
    $chks.each(function(){
    	if( $(this).is(':checked') )
    		$(this).click(); // se manda el evento del click que removiendo el pincge attributo por que no jala.
    });
}

function resetFrmReset($form){
	resetForm($form);
	
	$('#divAlert').empty();
	$('#divAlert').removeClass("alert-danger").removeClass("alert-info");
	/*if( $('#ckbPass').is(':checked') )
		$('#ckbPass').click(); // se manda el evento del click que removiendo el pincge attributo por que no jala.
*/	$('#txtPass').attr('disabled','disabled').removeAttr("placeholder").attr("required",false);//.val(null);
	$("#txtPass").parents().removeClass("has-error");
}

function populateForm($form, data)
{
    resetForm($form);
    $.each(data, function(key, value) {
    	
        var $ctrl = $form.find('[id='+key+']');
        
        if ($ctrl.is('select')){
            $('option', $ctrl).each(function() {
                if (this.value == value)
                    this.selected = true;
            });
        } else if ($ctrl.is('textarea')) {
            $ctrl.val(value);
        } else {
            switch($ctrl.attr("type")) {
                case "text":
                	$ctrl.val(value); 
                break;
                case "hidden":
                    $ctrl.val(value);   
                    break;
                case "checkbox":
                	
                    if (value ){
                    	$('.icheckbox_square-green').addClass('checked');
                        $ctrl.attr('checked', true);
                        
                    }
                    else{
                    	$('.icheckbox_square-green').removeClass('checked');
                    	$ctrl.attr('checked', false);
                    }
                        
                    break;
            } 
        }
    });
};

$.fn.serializeObject = function() {
    var o = {};
    // var a = this.serializeArray();
    $(this).find('input[type="hidden"], input[type="text"], input[type="password"], input[type="checkbox"]:checked, input[type="radio"]:checked, select').each(function() {
        if ($(this).attr('type') == 'hidden') { // If checkbox is checked do not take the hidden field
            var $parent = $(this).parent();
            var $chb = $parent.find('input[type="checkbox"][name="' + this.name.replace(/\[/g, '\[').replace(/\]/g, '\]') + '"]');
            if ($chb != null) {
                if ($chb.prop('checked')) return;
            }
        }
        if (this.name === null || this.name === undefined || this.name === '')
            return;
        var elemValue = null;
        if ($(this).is('select'))
            elemValue = $(this).find('option:selected').val();
        else
            elemValue = this.value;
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(elemValue || '');
        }
        else {
            o[this.name] = elemValue || '';
        }
    });
    return o;
}

// Para cuando se utiliza el submit
function form_to_json ($selector) {
	  var ary = $selector.serializeArray();
	  console.log(ary);
	  var obj = {};
	  for (var a = 0; a < ary.length; a++){
		  
		  if(ary[a].name == "_userActive")
			  continue;
		  
		  obj[ary[a].name] = ary[a].value;
	  } 
		  
	  return obj;
	}

function selectedRowUser($row){
	
	var FilaActual = $('#tableUsers').DataTable().row($row).data();
	
	if (!$($row).hasClass('selected')) {
		
		$('#btn-edit').attr('disabled', $($row).hasClass('selected'));
		
		$('#tableUsers').DataTable().$('tr.selected').removeClass('selected');
		$($row).addClass('selected');
		$('#lblUser').text( "" );
		$('#lblEmail').text( "" );
		$('#lblAltEmail').text( "" );
		$('#lblName').text( "" );
		$('#lblJob').text( "" );
		$('#lblDepto').text( "" );
		$('#lblAdmission').text( "" );
		$('#lblNomActive').removeClass();
		$('#lblPass').removeClass();
		// $('#lblReason').text(data.motivo);
		
		if(FilaActual.userEmesCompany != null && FilaActual.userEmesId != null){
			$.ajax({
				url : $(location).attr('origin') + "/HelpDeskLctpcThymeleaf/api/v1.0/getJsonEmp/"+FilaActual.userEmesCompany+"/"+FilaActual.userEmesId,
				success : function(data) {
					$('#img-Emp').attr("src", "https://admportlct.lctpc.com.mx/Directorio/Fotos/"+FilaActual.userEmesCompany+"/emp_"+FilaActual.userEmesId+".bmp");
					$('#lblName').text(data.nombre);
					$('#lblJob').text(data.nomPuesto);
					$('#lblDepto').text(data.nomDepartamento);
					$('#lblAdmission').text(data.fechaIngreso);
					var	l_labelActive = (data.activo == 'Y'? "Active" : "Inactive");
					var	l_labelSpanActive  = (data.activo == 'Y' ? "primary" : "danger");
					$('#lblNomActive').removeClass();
					$('#lblNomActive').addClass("label label-"+l_labelSpanActive).text( l_labelActive );
					// $('#lblReason').text(data.motivo);
				}
			});
		}else{
			$('#img-Emp').attr("src", "./images/user1.png");
			$('#lblNomActive').removeClass().text( "" );
		}
		
		if(FilaActual.userActive == false){
			$('#btn-delete').removeAttr('href').attr("disabled", "disabled");
			$('#btn-resetPass').removeAttr("data-target").removeAttr('href').attr("disabled", "disabled");
			
			$('#btn-AssignRol').attr('disabled', true).removeAttr('data-target');
			$('#btn-AssignAppn').attr('disabled', true).removeAttr('data-target');
			
			
		} else{
			$('#btn-delete').attr('href', $(location)
				.attr('origin') + "/HelpDeskLctpcThymeleaf/userFormulario/"+ FilaActual.userId )
				.removeAttr('disabled');
			$('#btn-resetPass').attr("data-target", "#myModalPass").removeAttr('disabled');
			
			$('#btn-AssignRol').removeAttr('disabled').attr('data-target', "#myModalRoles");
			$('#btn-AssignAppn').removeAttr('disabled').attr('data-target', "#myModalAppns");
			
			
		}
		$('#lblUser').text( FilaActual.userUsername != null ? FilaActual.userUsername : '' );
		var txtName = (FilaActual.acinName != null ? FilaActual.acinName : "") + (FilaActual.acinLastName != null ? FilaActual.acinLastName : "");
		$('#lblEmail').text( FilaActual.acinEmail != null ? FilaActual.acinEmail : '' );
		$('#lblAltEmail').text(FilaActual.acinAlternateEmail != null ? FilaActual.acinAlternateEmail : '');
		var	l_checkLabel = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "Active" : "Inactive") :  "No Password";
		var	l_labelSpan  = FilaActual.passwords[0] != null ? (FilaActual.passwords[0].pswdActive ? "primary" : "default") : "warning";
		
		$('#lblPass').addClass("label label-"+l_labelSpan).text( l_checkLabel );
		
		$('#tableRoles').DataTable().clear().draw();
		$('#tableRoles').DataTable().ajax.url("./api/v1.0/getJsonUserRoles/" + FilaActual.userId).load();
		
		$('#tableApps').DataTable().clear().draw();
		$('#tableApps').DataTable().ajax.url("./api/v1.0/getJsonUserApps/" + FilaActual.userId).load();
		
	}
}

function saveReset(){
	
	var divAlert = $('#divAlert');
	$('#divAlert').empty();
	
	if($("#txtPass").is(":required")&&$("#txtPass").val()==""){
		$("#txtPass").parents().addClass("has-error");
		
	} else{
		var l_data = jQuery.param({
        	"username": $("#lblUser").text(),
        	"passNew": $("#txtPass").val(),
        	"reqNewPass" : $("#ckbReqNewPass").is(':checked')
    		});
		
		$.ajax({
			url : "./api/v1.0/reset?"+l_data,
			type : "PUT",
			// contentType: "multipart/form-data",
			// processData: false,
			data : l_data,
			timeout : 100000,
			success : function(result) {
				divAlert.removeClass('alert-danger').addClass('alert-info');
				divAlert.append($("<h2 class='font-bold'>").text(result));
				
				if( $('#ckbPass').is(':checked') )
					$('#ckbPass').click(); // se manda el evento del click que removiendo el pincge attributo por que no jala.
				$('#txtPass').attr('disabled','disabled').removeAttr("placeholder").attr("required",false).val(null);
				$("#txtPass").parents().removeClass("has-error");
			},
			error : function(e) {
				divAlert.removeClass('alert-info').addClass('alert-danger');
				divAlert.append($("<h4 class='font-bold'>").text(e.responseText));
			}
		});
	}
	
}

/*$("#frmReset").validate({
rules: {
	 txtPass: {
        required: true,
        minlength: 4
    }
},
messages:
{
	 txtPass:
    {
       required: "Please enter your email address."

    }

}
});*/