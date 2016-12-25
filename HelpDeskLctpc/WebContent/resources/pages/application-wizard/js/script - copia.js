/**
 * Application Manager - Codigo Javascript necesario para hacer funcionar el modulo. Version 1.1
 * 
 * Copyright 2016, Abner Jesus Benitez Yañez LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
 */
jQuery(function($) {
	jQuery.fn.extend({
		initializeTable : function() {
			// Codigo para tabla de pagina
			var tablePages = $('#tablePages').DataTable();
			var counter = 0;
			var isEdit = false;
			
			var RowA = null;
			$('#btn-addPage').on('click', function() {
							var colAction = $('<td class="gridSystemModal">').html(
									"<div class='hidden-sm hidden-xs action-buttons'>" + "<a class='green' id='id-btn-edit' href='#' role='button'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
											+ "<a class='red' id='id-btn-delete' href='#'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>" + "</div> " + "<div class='hidden-md hidden-lg'>"
											+ "<div class='inline pos-rel'>" + "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
											+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>" + "</button>"
											+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>" + "<li>"
											+ "<a href='#' id='id-btn-edit' class='tooltip-success' data-rel='tooltip' title='Edit'>" + "<span class='green'>"
											+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>" + "</li>" + "<li>"
											+ "<a href='#' id='id-btn-delete' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
											+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>" + "</li>"
											+ "</ul>" + "</div>" + "</div>");
							
						if (!isEdit && (RowA === null) ) {
							row = $('<tr>').append("<td>" + counter + "</td>", "<td>" + $('#pageNomenclature').val() + "</td>", "<td>" + $('#pageDisplay').val() + "</td>",
									"<td>" + $('#pageDescription').val() + "</td>", "<td>" + $('#pageUrl').val() + "</td>", colAction);
							counter++;
						} else {
							isEdit =  false;
							var rowData = RowA.data();
							row = $('<tr>').append("<td>" + rowData[0] + "</td>", "<td>" + $('#pageNomenclature').val() + "</td>", "<td>" + $('#pageDisplay').val() + "</td>",
									"<td>" + $('#pageDescription').val() + "</td>", "<td>" + $('#pageUrl').val() + "</td>", colAction);
							RowA.remove().draw(false);
							RowA = null;
						}
						tablePages.row.add(row).draw(false);
						$('#pageDisplay').val("");
						$('#pageNomenclature').val("");
						$('#pageDescription').val("");
						$('#pageUrl').val("");
						var row = "";
					});

			$('#tablePages tbody').on("click", ".gridSystemModal a#id-btn-edit", function() {
				// var ColumnaActual = $(this).parent().parent().parent().get(0), FilaActual =
				// $('#tableUsers').DataTable().row(ColumnaActual).data();
				RowA = tablePages.row($(this).parents('tr'));
				FilaActual = tablePages.row($(this).parents('tr')).data();
				
				$('#pageNomenclature').val(FilaActual[1]);
				$('#pageDisplay').val(FilaActual[2]);
				$('#pageDescription').val(FilaActual[3]);
				$('#pageUrl').val(FilaActual[4]);

				isEdit = true;
			});

			$('#tablePages tbody').on("click", ".gridSystemModal a#id-btn-delete", function() {
				tablePages.row($(this).parents('tr')).remove().draw(false);
			});
//----------------------------------------------------------------------------------------------------------
			// Codigo para tabla de roles
			var tableRoles = $('#tableRoles').DataTable();
			var counterRoles = 0;
			var isEditRoles= false;
			
			var RowRoles = null;
			$('#btn-addRole').on('click', function() {
							var colAction = $('<td class="gridSystemModal">').html(
									"<div class='hidden-sm hidden-xs action-buttons'>" + "<a class='green' id='id-btn-edit' href='#' role='button'><i class='ace-icon fa fa-pencil bigger-130'></i></a>"
											+ "<a class='red' id='id-btn-delete' href='#'><i class='ace-icon fa fa-trash-o bigger-130'></i></a>" + "</div> " + "<div class='hidden-md hidden-lg'>"
											+ "<div class='inline pos-rel'>" + "<button class='btn btn-minier btn-primary dropdown-toggle' data-toggle='dropdown' data-position='auto'>"
											+ "<i class='ace-icon fa fa-cog icon-only bigger-110'></i>" + "</button>"
											+ "<ul class='dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close'>" + "<li>"
											+ "<a href='#' id='id-btn-edit' class='tooltip-success' data-rel='tooltip' title='Edit'>" + "<span class='green'>"
											+ "<i class='ace-icon fa fa-pencil-square-o bigger-120'></i></span></a>" + "</li>" + "<li>"
											+ "<a href='#' id='id-btn-delete' class='tooltip-error' data-rel='tooltip' title='' data-original-title='Delete'>"
											+ "<span class='red'> <i class='ace-icon fa fa-trash-o bigger-120'></i></span></a>" + "</li>"
											+ "</ul>" + "</div>" + "</div>");
							
						if (!isEditRoles && (RowRoles === null) ) {
							row = $('<tr>').append("<td>" + counterRoles + "</td>", "<td>" + $('#roleName').val() + "</td>", "<td>" + $('#roleDescription').val() + "</td>",
									colAction);
							counterRoles++;
						} else {
							isEditRoles =  false;
							var rowData = RowRoles.data();
							row = $('<tr>').append("<td>" + rowData[0] + "</td>", "<td>" + $('#roleName').val() + "</td>", "<td>" + $('#roleDescription').val() + "</td>",
									colAction);
							RowRoles.remove().draw(false);
							RowRoles = null;
						}
						tableRoles.row.add(row).draw(false);
						$('#roleId').val("");
						$('#roleName').val("");
						$('#roleDescription').val("");
						var row = "";
					});

			$('#tableRoles tbody').on("click", ".gridSystemModal a#id-btn-edit", function() {
				// var ColumnaActual = $(this).parent().parent().parent().get(0), FilaActual =
				// $('#tableUsers').DataTable().row(ColumnaActual).data();
				RowRoles = tableRoles.row($(this).parents('tr'));
				FilaActualRoles = RowRoles.data();
				$('#roleId').val(FilaActualRoles[0]);
				$('#roleName').val(FilaActualRoles[1]);
				$('#roleDescription').val(FilaActualRoles[2]);

				isEditRoles = true;
			});

			$('#tableRoles tbody').on("click", ".gridSystemModal a#id-btn-delete", function() {
				tableRoles.row($(this).parents('tr')).remove().draw(false);
			});
			
			//-------------------------------------------------------------------------------------------------------------
			$('[data-rel=tooltip]').tooltip();

			$(".select2").css('width', '200px').select2({
				allowClear : true
			}).on('change', function() {
				$(this).closest('form').validate().element($(this));
			});

			var $validation = false;
			$('#fuelux-wizard-container').ace_wizard({
			// step: 2 //optional argument. wizard will jump to step "2" at first
			// buttons: '.wizard-actions:eq(0)'
			}).on('actionclicked.fu.wizard', function(e, info) {
				if (info.step == 1 && $validation) {
					if (!$('#validation-form').valid())
						e.preventDefault();
				}
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

	});

	$(this).initializeTable();

});
