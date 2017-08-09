/**
 * OCR VIEW - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abelardo Sanchez España LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			// Activated the table
			var tableUser = $('#tableUsers').DataTable(
					{
						"autoWidth" : false,
						"columnDefs" : [ {
							"targets" : [ 0 ],
							"visible" : false,
							"searchable" : false
						} ],
						processing : true,
						serverSide : true,
						ajax : {
							url : "./getJsonUsers",
							type : "POST",
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								$.each(data, function(ind, obj) {
									var l_check = obj.userActive == false ? "" : " checked";
									tableUser.row.add(
											[
													obj.userId,
													obj.userUsername,
													obj.userEmesCompany,
													obj.userEmesId,
													obj.userCreatedBy,
													obj.userCreatedDate,
													obj.userUpdateBy,
													obj.userUpdateDate,
													"<input type='checkbox' value='" + obj.userActive + "' " + l_check + " />"
															+ " <button type='button' class='btn btn-primary' data-toggle='modal' data-target='#gridSystemModal' data-whatever='update' >Editar</button>",
													obj.userId + " <button type='button' class='btn btn-primary' data-toggle='modal' data-target='#gridSystemModal' data-whatever='create' >Create</button>" ]).draw();
								});
							}
						},
					});
		}
	});
	$(this).initializeTable();

	$('#gridSystemModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var idUser = button.data('whatever'); // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do
		// the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use
		// a data binding library or other methods instead.
		var link = (idUser === 'update' ? '/{13}/update' : '');
		$.ajax({
			url : "./userFormulario" + link,
			type : "POST",
			success : function(result) {
				if (!(result === null))
					$(".modal-body").html(result);
			}
		});
		var modal = $(this);
		modal.find('.modal-title').text('New message to ' + idUser);
		// modal.find('.modal-body input').val(idUser)
	});
});
