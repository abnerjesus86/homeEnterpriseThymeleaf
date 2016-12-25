/*
	Proposito:	Codigo Javascript necesario para hacer funcionar el modulo EIR
	Autor:	AESPANA - Abelardo Sanchez España
	Fecha: 	24/09/2015		
*/
jQuery(function($) {

	var Option = '';
	var Parametro1 = '';
	var InitTableSearch = false, InitTableEvents = false;
	var CntrConsecutivo;
	var content = "";
	var title = "";
	var side = "";
	var TablaEventos = "";
	var Galeria = "";
	var GaleriaVideos = "";
	var GaleriaFiltros = "";
	var DateNow;
	var tableEvents;
	var lastrowchild = '',lastrow = '';

	//Mantenemos algunos elementos ocultos hasta su uso
	$( "#TablaResultados" ).hide();
	$("#AlertaBusqueda").hide();
	$("#RangoBusqueda").hide();				
	$("#IconoBusqueda").hide();
		
	jQuery.fn.extend
		({		
			setSearch: function () //Funcion: Comienza el proceso de Busqueda
			{
				var Sel = $("#FiltrosBusqueda").val();
				var CampoBusqueda = false;
			
				if(Sel == 3) //Fechas				 
				{
					CampoBusqueda = ($("#RangoBusqueda input").val() == '')?false:true;	
					$("#AlertaBusqueda span").text("Favor de seleccionar un rango de fechas valido e intentar nuevamente.");	
					
					if (CampoBusqueda)
						Parametro1 = $('#RangoBusqueda #start').val()+','+$('#RangoBusqueda #end').val();									
				}					
				else
				{
					CampoBusqueda = ($("#TxtBusqueda").val() == '')?false:true;
					$("#AlertaBusqueda span").text("Favor de rellenar el campo de busqueda e intentar nuevamente.");
						
					if (CampoBusqueda)
						Parametro1 = $('#TxtBusqueda').val();				
				}	
				
				if (CampoBusqueda == false) //Validamos si el campo esta vacio o lleno
				{
					$('.form-group').removeClass('has-info').addClass('has-error');
					$("#AlertaBusqueda").show("fast");
					$( "#TablaResultados" ).hide("fast");						
				}
				else
				{
					$('.form-group').removeClass('has-error').addClass('has-info');
					$("#AlertaBusqueda").hide("false");
					
					var jsonDataObject = new Object();
					jsonDataObject.opcion = $("#FiltrosBusqueda").val()==null?"":$("#FiltrosBusqueda").val();
					jsonDataObject.parameter1 = Parametro1==null?"":Parametro1;
					var jsonData = JSON.stringify(jsonDataObject);
					jQuery.support.cors = true;
					
					$( "#TablaResultados" ).show( "fast", function() 
					{
						$("#SPBusqueda").text($("#TxtBusqueda").val());
						$(this).getSearchResults(jsonData);						
					});		
				}
				
				return false;
			},
			getSearchResults: function (p_Data) //Funcion: Obtenemos los resultados de la busqueda
			{
				if (InitTableSearch)		
					return true;
				
				InitTableSearch = true;

				//Inicializamos el plugin Datetable
				var oTable1 = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.dataTable( 
				{
					bAutoWidth: false,
					/*"aoColumns": [
					  null,null,null,null,null,null,null,null,null,null,
					  //null, null,null, null,null, null,null, null, null, null, null, null, null,
					  { "sTitle": "Fecha","bSortable": false }
					],*/
					"aaSorting": [[0,'desc']],
					//"ajax": 'includes/content/pages/containers-files/data_containers.js',
					"ajax": 'includes/content/pages/containers-files/containers.js',
					//"processing": true,
					//"serverSide": true,
					/*"ajax": {
						type: "POST",
						dataType: "json",
						data :p_Data,
						contentType: "application/json",
						url:"http://10.130.6.1:7001/wseir/wstracking/Containers"
						},*/
					columns: [ 
						{
							title: "Consecutivo",
							data: "CntrConsecutivo",
							visible: false
						},
						{
							title: "Contenedor",
							data: "Container"
						},
						{
							title: "Buque",
							data: "VesselName"
						},
						{
							title:"Trafico",
							data: "CntrType",
							className: "Trafico"
						},
						{
							title:"Tipo",
							data: "TyszType"
						},
						{
							title:"Tamaño",
							data: "TyszSize"
						},
						{
							title:"Lleno",
							data: "FullEmptyCode"
						},
						{
							title:"Status",
							data: "YardStatus"
						},
						{
							title:"Sello",
							data: "CntrSeal1"
						},
						{
							title:"Referencia",
							data: "VeprReference"
						},
						{
							title:"Destino",
							data: "CityName"
						},
						{
							title:"Fecha Llegada",
							data: "Date"
						},
						{
							title: "Evento",
							data: null,
							defaultContent: "<div class='action-buttons center'><a class='blue' href='#modal-table' role='button' data-toggle='modal'><i class='ace-icon fa fa-search-plus bigger-150'></i></a></div>",
							sortable: false,
							className: "center"
						} 
					],
					initComplete: function(oSettings, json) 
					{
						  console.log('Tabla Resultados Cargada Correctamente');
						  if (oSettings.fnRecordsTotal() > 1) 
						  {
							
							/*$('#dynamic-table').DataTable().rows( function ( idx, data, node ) {
									var type = data.CntrType.charAt(0) === 'E'?'label-success':'label-info';
									data.CntrType = "<span class='label label-sm "+type+" arrowed arrowed-righ'>"+data.CntrType+"</span>";
							} ).data();*/

							//console.log($('#dynamic-table tbody tr td:first').html());
							
							$('#dynamic-table tbody tr').find('td:first-child').each(function(){ //Agregamos la opcion de enlace a la columna contenedor
								$(this).html("<div class='action-buttons center'><span class='selectedlink'></span><a href='#modal-table' role='button' data-toggle='modal'><b>"+$(this).html()+"</b></a></div>");
								
								/*$(this).html("<div class='action-buttons center'><a href='#modal-table' role='button' data-toggle='modal'><i class='ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue'></i>&nbsp;<b>"+$(this).html()+"</b></a></div>");*/
							});

							$('#dynamic-table tbody tr .Trafico').each(function() { //Agregamos el label a la columna trafico que corresponde
								$(this).html("<span class='label label-sm "+ ($(this).html() === 'IMPO'?'label-success':'label-info')+" arrowed arrowed-righ'>"+$(this).html()+"</span>");
							});
							
							$('#dynamic-table tbody tr a').click(function()
							{
								//console.log( $('#dynamic-table').DataTable().row( this ).data().CntrConsecutivo );	
								
								var ColumnaActual = $(this).parent().parent().parent().get(0);	
								var	FilaActual = $('#dynamic-table').DataTable().row( ColumnaActual ).data();							
								$(this).getEvents(FilaActual);
							});		

							//$("div.toolbar").html('<b>Custom tool bar! Text/images etc.</b>');							

						  }
					},
					//dom: '<"toolbar">frtip',	
					//"sScrollY": "200px",
					//"bPaginate": false,
			
					//"sScrollX": "100%",
					//"sScrollXInner": "120%",
					//"bScrollCollapse": true,
					//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
					//you may want to wrap the table inside a "div.dataTables_borderWrap" element			
					//"iDisplayLength": 50
					"oLanguage": {
					  "sSearch": "Busqueda:",
					  "sInfo": "Mostrando de _START_ a _END_ de un total de _TOTAL_ registros",
					  "sInfoFiltered": " - filtrados de un total de _MAX_ registros",
					  "sLengthMenu": "Mostrar _MENU_ Registros",
					  "sInfoEmpty": "No hay registros para mostrar",
					  "sNext": "Siguiente",
					  "sPrevious": "Anterior",
					  "sEmptyTable": "No hay datos disponibles en la tabla",
					  "sInfoEmpty": "No hay registros a mostrar",
					  "sZeroRecords": "No hay registros a mostrar"
					}
			    } );
				//oTable1.fnAdjustColumnSizing();
			
		
				//TableTools settings
				TableTools.classes.container = "btn-group btn-overlap";
				TableTools.classes.print = {
					"body": "DTTT_Print",
					"info": "tableTools-alert gritter-item-wrapper gritter-info gritter-center white",
					"message": "tableTools-print-navbar"
				}
			
				//initiate TableTools extension
				var tableTools_obj = new $.fn.dataTable.TableTools( oTable1, {
					"sSwfPath": "../assets/js/dataTables/extensions/TableTools/swf/copy_csv_xls_pdf.swf", //in Ace demo ../assets will be replaced by correct assets path
					"sRowSelector": "td:not(:last-child)",
					"sRowSelect": "multi",
					"fnRowSelected": function(row) {
						//check checkbox when row is selected
						try { $(row).find('input[type=checkbox]').get(0).checked = true }
						catch(e) {}
					},
					"fnRowDeselected": function(row) {
						//uncheck checkbox
						try { $(row).find('input[type=checkbox]').get(0).checked = false }
						catch(e) {}
					},
			
					"sSelectedClass": "success",
			        "aButtons": [
						/*{
							"sExtends": "copy",
							"sToolTip": "Copy to clipboard",
							"sButtonClass": "btn btn-white btn-primary btn-bold",
							"sButtonText": "<i class='fa fa-copy bigger-110 pink'></i>",
							"fnComplete": function() {
								this.fnInfo( '<h3 class="no-margin-top smaller">Table copied</h3>\
									<p>Copied '+(oTable1.fnSettings().fnRecordsTotal())+' row(s) to the clipboard.</p>',
									1500
								);
							}
						},
						{
							"sExtends": "csv",
							"sToolTip": "Export to CSV",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-file-excel-o bigger-110 green'></i>"
						},
						{
							"sExtends": "pdf",
							"sToolTip": "Export to PDF",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-file-pdf-o bigger-110 red'></i>"
						},*/
						{
							"sExtends": "print",
							"sToolTip": "Vista de Impresion",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-print bigger-110 grey'></i>",
							
							"sMessage": "<div class='navbar navbar-default'><div class='navbar-header pull-left'><a class='navbar-brand' href='#'><small>Vista de Impresion</small></a></div></div>",
							
							"sInfo": "<h3 class='no-margin-top'>Vista de Impresion</h3>\
									  <p>Por favor imprima esta tabla desde su navegador web\
									  <br />Presione la tecla <b>escape</b> para salir.</p>",
						}
			        ]
			    } );
				
				
				//we put a container before our table and append TableTools element to it
			    $(tableTools_obj.fnContainer()).appendTo($('.tableTools-container'));
				
				//also add tooltips to table tools buttons
				//addding tooltips directly to "A" buttons results in buttons disappearing (weired! don't know why!)
				//so we add tooltips to the "DIV" child after it becomes inserted
				//flash objects inside table tools buttons are inserted with some delay (100ms) (for some reason)
				setTimeout(function() {
					$(tableTools_obj.fnContainer()).find('a.DTTT_button').each(function() {
						var div = $(this).find('> div');
						if(div.length > 0) div.tooltip({container: 'body'});
						else $(this).tooltip({container: 'body'});
					});
				}, 200);

				//ColVis extension
				var colvis = new $.fn.dataTable.ColVis( oTable1, {
					"buttonText": "<i class='fa fa-search'></i>",
					"aiExclude": [0, 6],
					"bShowAll": true,
					//"bRestore": true,
					"sAlign": "right",
					"fnLabel": function(i, title, th) {
						return $(th).text();//remove icons, etc
					}
					
				}); 
				
				//style it
				$(colvis.button()).addClass('btn-group').find('button').addClass('btn btn-white btn-info btn-bold')
				
				//and append it to our table tools btn-group, also add tooltip
				$(colvis.button())
				.prependTo('.tableTools-container .btn-group')
				.attr('title', 'Mostrar/Ocultar Columnas').tooltip({container: 'body'});
				
				//and make the list, buttons and checkboxed Ace-like
				$(colvis.dom.collection)
				.addClass('dropdown-menu dropdown-light dropdown-caret dropdown-caret-right')
				.find('li').wrapInner('<a href="javascript:void(0)" />') //'A' tag is required for better styling
				.find('input[type=checkbox]').addClass('ace').next().addClass('lbl padding-8');

				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
				
				//select/deselect all rows according to table header checkbox
				$('#dynamic-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) tableTools_obj.fnSelect(row);
						else tableTools_obj.fnDeselect(row);
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(!this.checked) tableTools_obj.fnSelect(row);
					else tableTools_obj.fnDeselect($(this).closest('tr').get(0));
				});

					$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});

				//And for the first simple table, which doesn't have TableTools or dataTables
				//select/deselect all rows according to table header checkbox
				var active_class = 'active';
				$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
						else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#simple-table').on('click', 'td input[type=checkbox]' , function(){
					var $row = $(this).closest('tr');
					if(this.checked) $row.addClass(active_class);
					else $row.removeClass(active_class);
				});

				//Cargamos el contenido del Modal
				//$("#modal-table").load("includes/content/pages/containers-files/modal2.html");
				
				return false;
			},
			getEvents:  function (p_Data) //Funcion: Se obtienen los eventos para el consecutivo del contenedor
			{	

				//$(".Galeria").hide();
				$(".modal-header h3").text(p_Data.Container);

				if (InitTableEvents)		
					return true;				
				InitTableEvents = true;				
				
				var tableEvents = $('#TablaEventos').dataTable(
				{
					ajax: 'includes/content/pages/containers-files/data_container_images.js',
					columns: [ 
						{
							title: "Evento",
							data: "evento",
							visible: false
						},
						{
							title: "Evento",
							data: "tipoevento",
							className: "center details-control"
						},
						{
							title: "Trafico",
							data: "trafico",
							className: "center"
						},
						{
							title: "Transporte",
							data: "transporte",
							className: "center"
						},
						{
							title:"Fecha",
							data: "fecha",
							className: "center"
						},
						{
							data: null,
							defaultContent: "<div class='action-buttons center'><a class='blue' href='#'><i class='ace-icon fa fa-plus-circle bigger-150'></i></a></div>",
							sortable: false,
							className: "details-control"
						} 
					],
					initComplete: function(oSettings, json) 
					{
						console.log('Tabla Eventos Cargada Correctamente');
						  
						$('#TablaEventos tbody tr').find('td:first-child').each(function(){ //Agregamos la opcion de enlace a la columna inicial
							$(this).html("<div class='action-buttons center'><a href='#' role='button'><b>"+$(this).html()+"</b></a></div>");
						});							
					},
					bAutoWidth: false,
					aaSorting: [[0,'desc']],
					bPaginate: false,
					bFilter: false,
					bInfo: false				
				});
				
				$('#TablaEventos tbody').on('click', 'td.details-control', function () {
					var tr = $(this).closest('tr');
					var row = $('#TablaEventos').DataTable().row( tr );
			 
					if ( row.child.isShown() ) { //Contraer
						row.child.hide("fast");
						$(this).parent().find('td:last-child').find('a').prepend("<i class='ace-icon fa fa-plus-circle bigger-150'>&nbsp;</i>");
						$(this).parent().find('td:last-child').find('a').find("i:last").remove();
					}
					else { //Expander
					
						if (lastrowchild)//contraemos el contenido anterior
						{
							lastrowchild.hide("fast");
							lastrow.prepend("<i class='ace-icon fa fa-plus-circle bigger-150'>&nbsp;</i>");
							lastrow.find("i:last").remove();
						}

						row.child( getGalery(row.data()) ).show("fast");
						$(this).parent().find('td:last-child').find('a').prepend("<i class='ace-icon fa fa-minus-circle bigger-150'>&nbsp;</i>");
						$(this).parent().find('td:last-child').find('a').find("i:last").remove();
						
												
						lastrowchild = row.child;
						lastrow = $(this).parent().find('td:last-child').find('a');
					}
				} );
								
				function getGalery(d) //Formamos la galeria
				{
					
					var Galery = '', GaleryFilters = '', GaleryVideos = '';

					GaleryFilters	= 	GaleryFilters +
					"<div id='sp-simpleportfolio' class='sp-simpleportfolio sp-simpleportfolio-view-items layout-gallery-space'>"+
						'<div class="row">'+
							'<div class="widget-toolbar pull-left">'+
								'<label>'+
									'<span class="blue"><i class="ace-icon fa fa-camera bigger-150"></i></span>&nbsp;'+
									'<input id="filtroImagenes" type="checkbox" class="ace ace-switch btn-rotate" />'+
									'<span class="lbl middle"></span>'+
								'</label>'+
								'&nbsp;&nbsp;'+
								'<label>'+
									'<span class="blue"><i class="ace-icon fa fa-video-camera bigger-150"></i></span>&nbsp;'+
									'<input id="filtroVideos" type="checkbox" class="ace ace-switch btn-rotate" />'+
									'<span class="lbl middle"></span>'+
								'</label>'+
							'</div>'+
							"<div class='sp-simpleportfolio-filter' >"+
								"<ul class='pull-right'>"+
									"<li class='active' data-group='all'><a href='#'>Todo</a></li>";
						
									$.each(d.sides, function(index, element) 
									{						
										side = element.side;
										title = element.sidename;							
										GaleryFilters = GaleryFilters + "<li data-group='"+side+"'><a href='#'>"+title+"</a></li>";

										$.each(element.images, function(index, element) //[IMAGENES]
										{ 											
											Galery = Galery + 
											'<div class="sp-simpleportfolio-item" data-groups=\'["'+side+'"]\'   >'+													
												'<div class="sp-simpleportfolio-overlay-wrapper clearfix ">'+
													'<span class="sp-simpleportfolio-icon-image"></span>'+
													'<img class="sp-simpleportfolio-img" src="'+element.url+'" >'+								
													'<div class="sp-simpleportfolio-overlay">'+			
														'<a class="gallery btn-zoom" href="'+element.url+'" data-featherlight="image">'+	
														'<div class="sp-vertical-middle">'+												
															'<h3 class="sp-simpleportfolio-title">'+													
																title+
																'<BR/><BR/><BR/><BR/><BR/>'+
																'<i class="ace-icon fa fa-search-plus bigger-250 white"></i>'+	
															'</h3>'+															
														'</div>'+	
														'</a>'+	
													'</div>'+										
												'</div>'+
											'</div>';
										});
							
										$.each(element.videos, function(index, element) //[VIDEOS]
										{ 											

											DateNow = Math.floor((Math.random() * 1000000) + 1); 
																					
											//Anexamos los videos											
											GaleryVideos = GaleryVideos +
															"<video id='VIDEO_"+DateNow+"' class='sp-simpleportfolio-lightbox' controls preload='metadata' <!--poster='../assets/images/posterVideo.png'-->  data-setup='{}'>"+ //
																"<source src='"+element.url+"' type='video/mp4'>"+
															"</video>";
																							
											//Anexamos los tumbnails de los videos		
											Galery = 	Galery + 
														'<div class="sp-simpleportfolio-item"  data-groups=\'["'+side+'"]\'  >'+ 
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
						
						GaleryFilters 	= 	GaleryFilters + 
								'</ul>'+
							'</div>'+
						'</div>'+
						'<div class="row">'+
							'<div id="GaleriaFotos" class="sp-simpleportfolio-items sp-simpleportfolio-columns-fix">'+
								Galery +
							'</div>'+
						'</div>';					
										
					$("#GaleriaVideos").html(GaleryVideos);
					
					$(this).loadScripts();	
					
					return GaleryFilters;
				}
			},								
			loadScripts: function () //Funcion: Recargamos los scripts de la galeria 
			{	
				$('#ScriptsGallery').getScripts("../assets/plugins/loadimg/loadImg.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/spsimpleportfolio.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.min.js");
				//$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.gallery.min.js");//Marca error de hide		
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/jquery.shuffle.modernizr.min.js");
				
				return false;
			},
			getScripts: function (p_Script) //Funcion: nos carga los scripts
			{
				$.getScript(p_Script, function( data, textStatus, jqxhr ) {
				  //console.log( data ); // Data returned
				  //console.log( p_Script );
				  //console.log( textStatus ); // Success
				  //console.log( jqxhr.status ); // 200
				  //console.log( "El script se ha cargado correctamente" );
				  //console.log( "__________________________________________________________" );
				});					
				return false;
			},
			setIconsSearch: function (p_Selection) //Funcion: Cambiamos el icono de la busqueda en cuestion
			{	
				if(p_Selection == 1) //Contenedor
				{
					//Mostrar
					$("#IconoBusquedaSVG").show("fast");
					$("#TxtBusqueda").show();
					
					//Ocultar
					$("#RangoBusqueda").hide();
					$("#IconoBusqueda").hide("fast");
				}	
				else if(p_Selection == 2) //Referencia
				{
					//Mostrar
					$("#TxtBusqueda").show();
																	
					//Ocultar
					$("#IconoBusquedaSVG").hide("fast");						
					$("#RangoBusqueda").hide();
					
					$("#IconoBusqueda").show("fast").removeClass("fa-calendar").addClass("fa-slack");
				}		
				else if(p_Selection == 3) //Fechas
				{
					//Mostrar
					$("#RangoBusqueda").show();
					
					//Ocultar
					$("#TxtBusqueda").hide();
					$("#IconoBusquedaSVG").hide("fast");
					
					$("#IconoBusqueda").show("fast").removeClass("fa-slack").addClass("fa-calendar");
				}	

				return false;
			}
		});

		//datepicker plugin
		$('.date-picker').datepicker({
			autoclose: true,
			todayHighlight: true
		}).next().on(ace.click_event, function()//Mostrar Datepicker
		{
			$(this).prev().focus();
		});
			
		//or change it into a date range picker
		$('.input-daterange').datepicker({autoclose:true});
			
		//Controlamos la opcion del Select				
	
		$( "#FiltrosBusqueda" ).change(function() 
		{
			$("#FiltrosBusqueda").setIconsSearch($("#FiltrosBusqueda").val());
		});
		
		$("#BotonBusqueda").click(function()
		{
			$(this).setSearch();
		});
		
		//add tooltip for small view action buttons in dropdown menu
		$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
		
		//tooltip placement on right or left
		function tooltip_placement(context, source) {
			var $source = $(source);
			var $parent = $source.closest('table')
			var off1 = $parent.offset();
			var w1 = $parent.width();
	
			var off2 = $source.offset();
			//var w2 = $source.width();
	
			if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
			return 'left';
		}	
				
			
})
		