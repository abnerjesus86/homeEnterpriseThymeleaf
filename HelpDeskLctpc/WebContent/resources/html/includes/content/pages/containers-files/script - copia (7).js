/**
 * EIR - Codigo Javascript necesario para hacer funcionar el modulo EIR
 * Version 1.1 
 *
 * Copyright 2015, Abelardo Sanchez España
 * LCTPC - Lazaro Cardenas Terminal Portuaria de Contenedores.
**/
jQuery(function($) {

    var Option = '',
        Parametro1 = '',
        InitTableSearch = false,
        InitTableEvents = false,
        CntrConsecutivo,
        content = "",
        title = "",
        side = "",
        TablaEventos = "",
        Galeria = "",
        GaleriaVideos = "",
        GaleriaFiltros = "",
        DateNow,
        tableEvents,
        lastrowchild = '',
        lastrow = '',
        oTable1 = '',
		opsInet = true,
		op_Zoom = false,
		op_GalleryImages = true,
		op_GalleryVideos = false,
		op_Helps = false,
		op_FiltersSearch = false,
		op_FilterColvis = false,
		op_FilterCopy = false,
		op_FilterCSV = false,
		op_FilterExcel = false,
		op_FilterPDF = false,
		op_FilterPrint = false,
		op_WSContainers	= '',
		op_WSEvents = '';

    jQuery.fn.extend({
        initialize: function() {//Funcion: Inicializa alguno de los componentes		
			if(op_Helps)
			{
				$('#spIconContainer').setHelps('Contenedor','top','Captura el contenedor del que deseas obtener informacion');
				$('#spIconReference').setHelps('Referencia de Buque','bottom','Captura la referencia del buque al que pertenecen los contenedores que deseas buscar');
				$('#spIconDates').setHelps('Fechas de Operacion','top','Captura el rango de fechas del que deseas obtener informacion');
				$("[data-toggle=popover]").popover();
			}
						
			 $.fn.datepicker.dates['es'] = 
			 {
				days: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
				daysShort: ["Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"],
				daysMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"],
				months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
				monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
				today: "Mes Actual",
				clear: "Limpiar",
				weekStart: 1,
				format: "dd/mm/yyyy"
			};
			
			$('.input-daterange').datepicker({
				format: "dd/mm/yyyy",
				weekStart: 0,
				//,startDate: $(this).getDate(3)
				//,endDate: $(this).getDate(0)
				todayBtn: true,
				clearBtn: true,
				language: "es",
				autoclose: false,
				orientation: "top left"
				//datesDisabled: ['05/10/2015', '02/10/2015']
			});
			
			$("#BotonBusqueda").click(function() {
				var Input = $(this);
				Input.prop('disabled', true).setSearch();
				window.setTimeout(function() {
					Input.prop('disabled', false).focus();
				}, 1000);						
			});

			$('#FormularioBusqueda input').on('keypress', function(event) {
				if (event.which === 13) {
					var Input = $(this);
					Input.prop('disabled', true).setSearch();
					window.setTimeout(function() {
						Input.prop('disabled', false).focus();
					}, 1000);
				}
			});

			//add tooltip for small view action buttons in dropdown menu
			$('[data-rel="tooltip"]').tooltip({
				placement: tooltip_placement
			});

			//tooltip placement on right or left
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('table')
				var off1 = $parent.offset();
				var w1 = $parent.width();

				var off2 = $source.offset();
				//var w2 = $source.width();

				if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
				return 'left';
			}
			
			//console.log($('#nav_Configuracion').html());
			
			$('#nav_Configuracion').attr('href','#modal-configuration').attr('role','button').attr('data-toggle','modal');
			
			$("#modal-configuration").getSettings();
			
			//$.mask.definitions['~'] = '[+-]';
			//$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
			//$('.input-mask-container').mask("***********");//aaaa9999999
			//$('.input-mask-reference').mask("aaa9999999");
			//$('.input-mask-date').mask('99/99/9999');
			
			
			console.log('Inicializacion Completa')
			return false;
		},		
		setSearch: function() {//Funcion: Comienza el proceso de Busqueda
			var valContainer = $('#TxtContainer').val().toUpperCase(),
				valReference = $('#TxtReference').val().toUpperCase(),
				valStartDate = $('#RangoBusqueda #start').val(),
				valEndDate = $('#RangoBusqueda #end').val();

			if ((valContainer + valReference + valStartDate + valEndDate).length == 0) {
				$('#alert-search').hide().html($(this).getAlert(' ¡Busqueda incompleta!', 'Debes de ingresar un valor en los campos de busqueda para obtener resultados')).fadeIn('slow'); //.cleanTable();
				$('#TablaResultados').addClass('hidden');
				console.log('¡Busqueda incompleta! Debes de ingresar un valor en los campos de busqueda para obtener resultados');
			} else {
				$('#alert-search').hide("slow");
				var p_Container = "CONTAINER:" + (valContainer.length == 0 ? "" : valContainer),
					p_Reference = "REFERENCE:" + (valReference.length == 0 ? "" : valReference),
					p_Dates = "DATE:" + ((valStartDate + valEndDate).length == 0 ? "" : (valStartDate + ',' + valEndDate));

				var jsonDataObject = new Object();
				jsonDataObject.parameter1 = p_Container + '|' + p_Reference + '|' + p_Dates;
				var jsonData = JSON.stringify(jsonDataObject);
				jQuery.support.cors = true;

				$(this).getSearchResults(jsonData);
			}

			return false;
		},
        getSearchResults: function(p_Data) {//Funcion: Obtenemos los resultados de la busqueda
			console.log('Realizando Busqueda...');
			
			$('#loader-search').setCenter('200px').removeClass('hidden').show("fast"); //.fadeIn('slow')

			if (InitTableSearch) {
				$('#dynamic-table').cleanTable('Resultados');
			} else
				InitTableSearch = true;

			$.ajax({
				url: op_WSContainers,
				data: p_Data,
				dataType: "json",
				type: "post",
				contentType: "application/json; charset=utf-8",
				success: function(result) {
				//console.log(result.value.Procedure.ErrorDescription);
					oTable1 =
						$('#dynamic-table')
						//.wrap("<div class='dataTables_borderWrap' />")   //Aplicar Scroll Horizontal (sScrollX)
						.DataTable({
							aaData: result,
							columns: [{
								title: "Consecutivo",
								data: "CntrConsecutivo",
								visible: false
							}, {
								title: "Contenedor",
								data: null,
								render: function(data, type, row) {
									return "<div class='action-buttons center'><span class='selectedlink'></span><a href='#modal-table' role='button' data-toggle='modal'><b>" + data.Container + "</b></a></div>";
								}
							}, {
								title: "Trafico",
								data: "CntrType"
							}, {
								title: "Tipo",
								data: "TyszType",
								className: "hidden-xs"
							}, {
								title: "Tamaño",
								data: "TyszSize",
								className: "hidden-xs"
							}, {
								title: "Lleno",
								data: "FullEmptyCode",
								className: "hidden-xs"
							}, {
								title: "Status",
								data: "YardStatus",
								className: "hidden-xs"
							}, {
								title: "Sello",
								data: "CntrSeal1",
								className: "hidden-sm hidden-xs"
							}, {
								title: "Referencia",
								data: "VeprReference",
								className: "hidden-sm hidden-xs"
							},  {
								title: "Buque",
								data: "VesselName",
								className: "hidden-xs"
							}, {
								title: "Destino",
								data: "CityName",
								className: "hidden-xs"
							}, {
								title: "Fecha Llegada",
								data: "Date",
								className: "hidden-xs"
							}, {
								title: "Evento",
								data: null,
								defaultContent: "<div class='action-buttons center'><a class='blue' href='#modal-table' role='button' data-toggle='modal'><i class='ace-icon fa fa-search-plus bigger-150'></i></a></div>",
								sortable: false,
								className: "center"
							}],
							fnCreatedRow: function(nRow, aData, iDataIndex) {
								$('td:eq(1)', nRow).html("<span class='label label-sm " + (aData.CntrType === 'IMPO' ? 'label-success' : 'label-info') + " arrowed arrowed-righ'>" + aData.CntrType + "</span>");
							},
							initComplete: function(oSettings, json) {
								$('#loader-search').addClass('hidden');

								if (oSettings.fnRecordsTotal() > 1) {
									console.log('Tabla Resultados Cargada Correctamente');

									$("#TablaResultados").removeClass('hidden').show("fast");
									
									if (op_FiltersSearch)
										$("#TablaResultados .table-header").prepend('<div class="clearfix pull-right col-md-4 hidden-xs"><div class="pull-right tableTools-container"></div></div>');


									$('#dynamic-table tbody tr a').click(function() {
										var ColumnaActual = $(this).parent().parent().parent().get(0),
											FilaActual = $('#dynamic-table').DataTable().row(ColumnaActual).data();
											
											$(this).getEvents(FilaActual);
									});

								} else {
									console.log('No se encontraron resultados');
									$('#alert-search').hide().html($(this).getAlert('¡No se encontraron resultados!', 'Realiza una nueva busqueda con diferentes valores')).fadeIn('slow');
									$('#TablaResultados').addClass('hidden');
								}

							},
							select: false,
							bAutoWidth: false
								//,aaSorting: [[0,'desc']]								
								,
							iDisplayLength: 10,
							oLanguage: {
								sSearch: "Busqueda:",
								sInfo: "Mostrando de _START_ a _END_ de un total de _TOTAL_ registros",
								sInfoFiltered: " - filtrados de un total de _MAX_ registros",
								sLengthMenu: "Mostrar _MENU_ Registros",
								sInfoEmpty: "No hay registros para mostrar",
								sEmptyTable: "No hay datos disponibles en la tabla",
								sInfoEmpty: "No hay registros a mostrar",
								sZeroRecords: "No hay registros a mostrar",
								oPaginate: {
									"sPrevious": "Anterior",
									"sNext": "Siguiente"
								}
							},
							//,dom: '<"toolbar">frtip',
							//",sScrollY": "200px"
							//",bPaginate": false						
							//",sScrollX": "100%"
							//",sScrollXInner": "120%"
							//",bScrollCollapse": true
							//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
							//you may want to wrap the table inside a "div.dataTables_borderWrap" element	
						});
					//oTable1.fnAdjustColumnSizing();

					$('#dynamic-table').set_FiltersSearch(op_FiltersSearch);
				},
				error: function(e) {
					alert('error!: ' + e);
				}
			});

			return false;
		},
        getEvents: function(p_Data) {//Funcion: Se obtienen los eventos para el consecutivo del contenedor
                console.log('Realizando Busqueda de Eventos...');

                $(".modal-header h3").text(p_Data.Container);
                $('#loader-search-events').setCenter('50%').removeClass('hidden').show("fast");

                if (InitTableEvents)
                    $('#TablaEventos').cleanTable('Eventos');
                InitTableEvents = true;

                var jsonDataObject = new Object();
                jsonDataObject.parameter1 = p_Data.CntrConsecutivo;
                var jsonData = JSON.stringify(jsonDataObject);
                jQuery.support.cors = true;

                $.ajax({
                    url: op_WSEvents,
                    data: jsonData,
                    dataType: "json",
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    success: function(result) {

						var tableEvents = $('#TablaEventos').dataTable({
                            aaData: result.data,
                            columns: [{
                                title: "Evento",
                                data: "evento",
                                visible: false
                            }, {
                                title: "Evento",
                                data: "tipoevento",
                                className: "center"
                            }, {
                                title: "Trafico",
                                data: "trafico",
                                className: "center"
                            }, {
                                title: "Transporte",
                                data: "transporte",
                                className: "center"
                            }, {
                                title: "Fecha",
                                data: "fecha",
                                className: "center"
                            }, {
                                data: null,
                                defaultContent: '',
                                sortable: false
                            }],
                            fnCreatedRow: function(nRow, aData, iDataIndex) {
                                $('td:first-child', nRow).html("<div class='action-buttons center'><a href='#' role='button'><b>" + aData.tipoevento + "</b></a></div>");
                                $('td:eq(1)', nRow).html("<span class='label label-sm " + (aData.trafico === 'IMPO' ? 'label-success' : 'label-info') + " arrowed arrowed-righ'>" + aData.trafico + "</span>");
                                $('td:last-child', nRow).html("<div class='action-buttons center'>" + ((aData.sides.length > 0) ? "<a class='blue' href='#'><i class='ace-icon fa fa-plus-circle bigger-150'></i></a>" : "<a class='red' href='#'><i class='fa fa-times-circle bigger-150'></i></a>") + "</div>");

                                if (aData.sides.length > 0) {
                                    $('td:first-child', nRow).addClass("details-control");
                                    $('td:last-child', nRow).addClass("details-control");
                                }
                            },
                            initComplete: function(oSettings, json) {
                                console.log('Tabla Eventos Cargada Correctamente');
                                $('#loader-search-events').addClass('hidden');

                                $('#TablaEventos tbody').on('click', 'td.details-control', function() {
                                    var tr = $(this).closest('tr');
                                    var row = $('#TablaEventos').DataTable().row(tr);

                                    if (row.child.isShown()) { //Contraer
                                        row.child.hide("fast");
                                        $(this).parent().find('td:last-child a').prepend("<i class='ace-icon fa fa-plus-circle bigger-150'></i>").find('i:last').remove();
                                    } else { //Expander

                                        if (lastrowchild) //contraemos el contenido anterior
                                        {
                                            lastrowchild.hide("fast");
                                            lastrow.prepend("<i class='ace-icon fa fa-plus-circle bigger-150'></i>").find("i:last").remove();
                                        }

                                        row.child($(this).getGallery(row.data())).show("fast");
                                        $(this).parent().find('td:last-child a').prepend("<i class='ace-icon fa fa-minus-circle bigger-150'></i>").find('i:last').remove();
                                        lastrowchild = row.child;
                                        lastrow = $(this).parent().find('td:last-child a');
										
										$(this).setColorBox();

										$( "input[type=checkbox]" ).on( "click", function() {
										 // console.log( $( "input:checked" ).val() + " is checked!" );										  
											if ($("#filtroImagenes").prop("checked"))
												$('.imagesgroup').show();
											else
												$('.imagesgroup').hide();
										});																		
									}
                                });
                            },
                            select: false,
                            bAutoWidth: false,
                            aaSorting: [
                                [0, 'asc']
                            ],
                            bPaginate: false,
                            bFilter: false,
                            bInfo: false
                        });
                    }
                });
            },
        loadScripts: function() {//Funcion: Recargamos los scripts de la galeria
                $('#ScriptsGallery').getScripts("../assets/plugins/loadimg/loadImg.js");
                $('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/spsimpleportfolio.js");
                //$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.min.js");
                //$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.gallery.min.js");//Marca error de hide		
                $('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/jquery.shuffle.modernizr.min.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/elevatezoom/jquery.elevatezoom.js");

                return false;
            },
        getScripts: function(p_Script) { //Funcion: nos carga los scripts
                $.getScript(p_Script, function(data, textStatus, jqxhr) {
                    //console.log( data ); // Data returned
                    //console.log( p_Script );
                    //console.log( textStatus ); // Success
                    //console.log( jqxhr.status ); // 200
                    //console.log( "El script se ha cargado correctamente" );
                    //console.log( "__________________________________________________________" );
                });
                return false;
            },
        getDate: function(p_MonthView) { //Funcion: Obtenemos una fecha especificada de acuerdo a un rango de Meses Atras
            var d = new Date();
            var month = d.getMonth() + 1 - p_MonthView;
            var day = d.getDate();

            var startdate = (('' + day).length < 2 ? '0' : '') + day + '/' + (('' + month).length < 2 ? '0' : '') + month + '/' + d.getFullYear();

            return startdate
        },
        getAlert: function(p_PreMessage, p_Message) { //Funcion: Genera una alerta
            return '<br/>' +
                '<div id="AlertaBusqueda" class="alert alert-danger col-xs-12 col-sm-12 col-lg-8">' +
                '<button type="button" class="close" data-dismiss="alert">' +
                '<i class="ace-icon fa fa-times"></i>&nbsp;' +
                '</button>' +
                '<strong>' +
                '<h3 class="margin-top margin-bottom-lg"><i class="fa fa-meh-o"></i>' +
                p_PreMessage +
                '</h3>' +
                '<span>' + p_Message + '</span>' +
                '</div>';
        },
        cleanTable: function(p_Table) { //Funcion: Reinicializa las tablas DataTable

            if (p_Table == 'Resultados') {
                $('#TablaResultados').addClass('hidden');
                $('#dynamic-table').DataTable().destroy();
                $('#dynamic-table').empty();
                $('.tableTools-container').empty().remove();
            } else if (p_Table = 'Eventos') {
                $('#TablaEventos').DataTable().destroy();
                $('#TablaEventos').empty();
            }

            return false;
        },
        setCenter: function(p_Top) { //Funcion: Centra un elemento de acuerdo a su padre
            this.css({
                'position': 'relative',
                'left': '50%',
                'top': p_Top
            });
            this.css({
                'margin-left': -this.outerWidth() / 2 + 'px',
                'margin-top': -this.outerHeight() / 2 + 'px'
            });

            return this;
        },
		setHelps: function(p_Title,p_Placement,p_Content) { //Funcion: Genera el Toltip que muestra la ayuda del elemento
			this.attr('title',p_Title);
			this.attr('data-toggle','popover');
			this.attr('data-placement',p_Placement);
			this.attr('data-content',p_Content);
			return false;
		},
		set_FiltersSearch: function(p_FiltersSearch){ //Funcion: Genera los filtros para la tabla de busqueda principal
			if (p_FiltersSearch)
			{
				$.fn.dataTable.Buttons.swfPath = "../assets/js/dataTables/extensions/buttons/swf/flashExport.swf"; //in Ace demo ../assets will be replaced by correct assets path
				$.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';

				var OptionsButtons = [] ;
				
				if(op_FilterColvis)
					OptionsButtons.push({	extend: "colvis",
											text: "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Mostrar/Ocultar Columnas</span>",
											className: "btn btn-white btn-primary btn-bold",
											columns: ':not(:first):not(:last)'});
				if(op_FilterCopy)
					OptionsButtons.push({	extend: "copyHtml5",
											text: "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copiar al Portapapeles</span>",
											className: "btn btn-white btn-primary btn-bold",
											exportOptions: {
												columns: ':visible:not(:last)'
											}
										});
				if(op_FilterCSV)
					OptionsButtons.push({	extend: "csvHtml5",
											text: "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Exportar a CSV</span>",
											className: "btn btn-white btn-primary btn-bold",
											exportOptions: {
												columns: ':visible:not(:last)'
											}
										});
				if(op_FilterExcel)
					OptionsButtons.push({	extend: "excel",
											text: "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Exportar a Excel</span>",
											className: "btn btn-white btn-primary btn-bold",
											exportOptions: {
												columns: ':visible:not(:last)'
											}
										});
				if(op_FilterPDF)
					OptionsButtons.push({	extend: "pdfHtml5",
											text: "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Exportar a PDF</span>",
											className: "btn btn-white btn-primary btn-bold",
											orientation: 'landscape',
											pageSize: 'LETTER',
											exportOptions: {
												columns: ':visible:not(:last)'
											},
											customize: function ( doc ) {
												doc.content.splice( 1, 0, {
													margin: [ 0, 0, 0, 12 ],
													alignment: 'right',
													image: 'data:image/png;base64,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'
												} );
											}	
										});
				if(op_FilterPrint)
					OptionsButtons.push({	extend: "print",
											title: "EIR - Reporte",
											text: "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Imprimir</span>",
											className: "btn btn-white btn-primary btn-bold",
											autoPrint: true,
											//message: 'Vista en modo Impresion',
											exportOptions: {
												columns: ':visible:not(:last)'
											},
											customize: function ( win ) {
												$(win.document.body).css( 'font-size', '10pt' ).prepend('<img src="http://10.130.66.11:8887/assets/images/Logo_LCTPC_Sin_Letras_Fade.png" style="position:absolute; top:300; left:350;" />');
												$(win.document.body).find( 'table' ).addClass( 'compact' ).css( 'font-size', 'inherit' );}
										});
				
				new $.fn.dataTable.Buttons(oTable1, {buttons: OptionsButtons});
											
				oTable1.buttons().container().appendTo($('.tableTools-container'));
				
				//style the message box
				var defaultCopyAction = oTable1.button(1).action();
				oTable1.button(1).action(function(e, dt, button, config) {
					defaultCopyAction(e, dt, button, config);
					$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
				});

				var defaultColvisAction = oTable1.button(0).action();
				
				oTable1.button(0).action(function(e, dt, button, config)
				{
					defaultColvisAction(e, dt, button, config);

					if ($('.dt-button-collection > .dropdown-menu').length == 0) {
						$('.dt-button-collection')
							.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
							.find('a').attr('href', '#').wrap("<li />")
					}
					$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
				});

				setTimeout(function() {
					$($('.tableTools-container')).find('a.dt-button').each(function() {
						var div = $(this).find(' > div').first();
						if (div.length == 1) div.tooltip({
							container: 'body',
							title: div.parent().text()
						});
						else $(this).tooltip({
							container: 'body',
							title: $(this).text()
						});
					});
				}, 500);
			}
			return false;
		},
		setColorBox: function() { //Funcion: Creamos la ventana emergente de la imagen seleccionada
			var $overflow = '';
			var colorbox_params = {
				rel: 'colorbox',
				reposition:true,
				scalePhotos:true,
				scrolling:false,
				slideshow:true,
				slideshowAuto: false,
				slideshowSpeed: 3000,
				current:'{current} de {total}',
				maxWidth:'98%',
				maxHeight:'85%',
				onOpen:function(){
					$overflow = document.body.style.overflow;
					document.body.style.overflow = 'hidden';
				},
				onClosed:function(){
					document.body.style.overflow = $overflow;
					$('.zoomContainer').remove();
				},
				onLoad: function()
				{					
					$('.zoomContainer').remove();
				},
				onComplete:function(){
					$.colorbox.resize();
					
					if (op_Zoom)
					{								
						$('.cboxPhoto').addClass('image-zoom').attr('data-zoom-image',$('.cboxPhoto').attr('src'));
						
						$('.cboxPhoto').elevateZoom(
						{
							zoomType: "inner",
							cursor: "crosshair",
							zoomWindowFadeIn: 500,
							zoomWindowFadeOut: 750,
							scrollZoom: true
						});
					}
				}
			};

			$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
			$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner blue fa-spin bigger-150'></i>");
			$(document).one('ajaxloadstart.page', function(e) {$('#colorbox, #cboxOverlay').remove();});
			
			return false;	
		},
		getGallery : function(d) { //Funcion: Creamos la galeria de imagenes y videos.			

			var Galery = '',
				GaleryFilters = '',
				GaleryVideos = '';

				GaleryFilters = GaleryFilters +
				"<div id='sp-simpleportfolio' class='sp-simpleportfolio sp-simpleportfolio-view-items layout-gallery-space'>" +
					'<div class="row">' +
						'<div class="widget-toolbar pull-left">' +
							'<label>' +
								'<span class="blue"><i class="ace-icon fa fa-camera bigger-150"></i></span>&nbsp;' +
								'<input id="filtroImagenes" type="checkbox" class="ace ace-switch btn-rotate" '+((op_GalleryImages)?'checked':'')+' value="1"/>' +
								'<span class="lbl middle"></span>' +
							'</label>' +
							'&nbsp;&nbsp;' +
							'<label>' +
								'<span class="blue"><i class="ace-icon fa fa-video-camera bigger-150"></i></span>&nbsp;' +
							'	<input id="filtroVideos" type="checkbox" class="ace ace-switch btn-rotate" '+((op_GalleryVideos)?'checked':'')+' '+((d.tipoocr == 'CRANE')?'disabled':'')+' value="2"/>' +
								'<span class="lbl middle"></span>' +
							'</label>' +
						'</div>' +
						"<div class='sp-simpleportfolio-filter' >" +
							"<ul class='pull-right'>" +
							"<li class='active' data-group='all'><a href='#'>Todo</a></li>";

			$.each(d.sides, function(index, element) {
				side = element.side;
				title = element.sidename;
				GaleryFilters = GaleryFilters + "<li data-group='" + side + "'><a href='#'>" + title + "</a></li>";
   
				if (op_GalleryImages)
				   $.each(element.images, function(index, element)
					{
						var ImageURL = element.url;
						//"../assets/images/tnImagenNoEncontrada.png";
											
						Galery = Galery +										
								'<div class="sp-simpleportfolio-item imagesgroup" data-groups=\'["' + side + '"]\'   >' +
								'<div class="sp-simpleportfolio-overlay-wrapper clearfix ">' +
									'<span class="sp-simpleportfolio-icon-image"></span>' +
									'<img class="sp-simpleportfolio-img" src="' + ImageURL + '" >' +
									'<div class="sp-simpleportfolio-overlay">' +
										'<a class="btn-zoom" href="'+ ImageURL +'" data-rel="colorbox">' +
											'<div class="sp-vertical-middle">' +
												'<h3 class="sp-simpleportfolio-title">' +
												title +
												'<BR/><BR/><BR/><BR/><BR/>' +
												'<i class="ace-icon fa fa-search-plus bigger-250 white"></i>' +
												'</h3>' +
											'</div>' +
										'</a>' +
									'</div>' +
								'</div>' +
						'</div>';
					});
				
				if (op_GalleryVideos)
					$.each(element.videos, function(index, element) 
					{ 											
						DateNow = Math.floor((Math.random() * 1000000) + 1); 
									
						GaleryVideos = GaleryVideos +
										"<video id='VIDEO_"+DateNow+"' class='sp-simpleportfolio-lightbox' controls preload='metadata' poster='../assets/images/posterVideo.png'  data-setup='{}'>"+ //
											"<source src='"+element.url+"' type='video/mp4'>"+
										"</video>";
																			
						Galery = 	Galery + 
									'<div class="sp-simpleportfolio-item videosgroup"  data-groups=\'["'+side+'"]\'  >'+ 
										'<div class="sp-simpleportfolio-overlay-wrapper clearfix">'+
											'<span class="sp-simpleportfolio-icon-video"></span>'+
											'<img class="sp-simpleportfolio-img" src="../assets/images/tnVideo'+Math.floor((Math.random() * 4) + 1)+'.png" >'+
											'<div class="sp-simpleportfolio-overlay">'+
												'<a class="btn-zoom" href="#" data-featherlight="#VIDEO_'+DateNow+'" >'+
													'<div class="sp-vertical-middle">'+
														'<h3 class="sp-simpleportfolio-title">'+
															title+
														'</h3>'+
													'</div>'+
												'</a>'+
											'</div>'+
										'</div>'+
									"</div>";											
					});
			});

			GaleryFilters = GaleryFilters +
						'</ul>' +
					'</div>' +
				'</div>' +
				'<div class="row">' +
					'<div id="GaleriaFotos" class="sp-simpleportfolio-items sp-simpleportfolio-columns-fix ace-thumbnails">' +
						Galery +
					'</div>' +
				'</div>';
				

			$("#GaleriaVideos").html(GaleryVideos);

			$(this).loadScripts();
												
			return GaleryFilters;
		},
		getSettings: function(){ //Funcion: Inicializamos los valores de la configuracion.
			/*OPCIONES PRINCIPALES*/
			$('#cb_Helps').prop( "checked", (op_Helps)?true:false) ;
			$('#cb_FiltersSearch').prop( "checked", (op_FiltersSearch)?true:false );
			if(op_FiltersSearch)
			{
				$('#cb_FilterColvis').prop( "checked", (op_FilterColvis)?true:false );
				$('#cb_FilterCopy').prop( "checked", (op_FilterCopy)?true:false );
				$('#cb_FilterCSV').prop( "checked", (op_FilterCSV)?true:false );
				$('#cb_FilterExcel').prop( "checked", (op_FilterExcel)?true:false );
				$('#cb_FilterPDF').prop( "checked", (op_FilterPDF)?true:false );
				$('#cb_FilterPrint').prop( "checked", (op_FilterPrint)?true:false );
			}
			
			/*OPCIONES DE LA GALERIA*/
			$('#cb_Zoom').prop( "checked", (op_Zoom)?true:false );
			$('#cb_GalleryImages').prop( "checked", (op_GalleryImages)?true:false );
			$('#cb_GalleryVideos').prop( "checked", (op_GalleryVideos)?true:false );
			
			/*WEBSERVICES*/
			$('#txt_WSContainers').val(op_WSContainers);
			$('#txt_WSEvents').val(op_WSEvents);
		
			return false;
		}
    });
	
	
	if (opsInet) {/*Cargamos la configuracion*/
		var jsonDataObject = new Object();
		jsonDataObject.user = 'AESPANA';
		var jsonData = JSON.stringify(jsonDataObject);
		jQuery.support.cors = true;
		$.ajax({
					headers: {'Content-Type': "application/json; charset=utf-8"},
					crossDomain : true,
					type: 'get', 
					url: 'includes/content/pages/containers-files/configuration.js', 
					dataType: 'json',
					data: null,
					success: function (data)
					{			
						/*WEBSERVICES*/
						op_WSContainers		=	data.WebServicesURLS.op_WSContainers;		//Servicio WEB de Contenedores
						op_WSEvents			=	data.WebServicesURLS.op_WSEvents;			//Servicio WEB de Eventos OCR

						/*OPCIONES PRINCIPALES*/
						op_Helps			= 	data.MainOptions.op_Helps;					//Activar las Ayudas de la pagina
						op_FiltersSearch	=	data.MainOptions.op_FiltersSearch;			//Filtros de la tabla de busqueda						
						if (op_FiltersSearch)
						{
							op_FilterColvis		=	data.MainOptions.op_ActiveFilters.op_FilterColvis;
							op_FilterCopy		=	data.MainOptions.op_ActiveFilters.op_FilterCopy;
							op_FilterCSV		=	data.MainOptions.op_ActiveFilters.op_FilterCSV;
							op_FilterExcel		=	data.MainOptions.op_ActiveFilters.op_FilterExcel;
							op_FilterPDF		=	data.MainOptions.op_ActiveFilters.op_FilterPDF;
							op_FilterPrint		=	data.MainOptions.op_ActiveFilters.op_FilterPrint;
						}
						
						/*OPCIONES DE LA GALERIA*/
						op_Zoom 			= 	data.GalleryOptions.op_Zoom;				//Activar el zoom en las imagenes de la galeria
						op_GalleryImages 	= 	data.GalleryOptions.op_GalleryImages;		//Ver Imagenes en la galeria
						op_GalleryVideos 	= 	data.GalleryOptions.op_GalleryVideos;		//Ver videos en la galeria			

						console.log('Configuracion del usuario cargada correctamente');
						$(this).initialize();						
					},
					error: function(e)
					{
						console.log('Ocurrio un error al cargar la configuraion del usuario');
						console.log(e);
					}
		});
	}


	$.fn.modal.Constructor.prototype.enforceFocus = function () {}; //Fix Modal Uncaught RangeError
})