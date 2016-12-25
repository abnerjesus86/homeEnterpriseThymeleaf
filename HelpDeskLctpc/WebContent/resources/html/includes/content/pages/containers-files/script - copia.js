/*
	Proposito:	Codigo Javascript necesario para hacer funcionar el modulo EIR
	Autor:	AESPANA - Abelardo Sanchez España
	Fecha: 	24/09/2015		
*/
jQuery(function($) {

	var Option = '';
	var Parametro1 = '';
	var InitTable = false;
	var CntrConsecutivo;
	var content = "";
	var title = "";
	var side = "";
	var TablaEventos = "";
	var Galeria = "";
	var GaleriaVideos = "";
	var GaleriaFiltros = "";
	var DateNow;

	//Mantenemos algunos elementos ocultos hasta su uso
	$( "#TablaResultados" ).hide();
	$("#AlertaBusqueda").hide();
	$('#TablaEventos').hide();
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
						
						//var table = $('#dynamic-table').DataTable();
							
						/*$('#dynamic-table tbody').on( 'click', 'tr', function () {
								console.log( $('#dynamic-table').DataTable().row( this ).data() );
							} );*/
						
					});		
				}
				
				return false;
			},
			getSearchResults: function (p_Data) //Funcion: Obtenemos los resultados de la busqueda
			{
				if (InitTable)		
					return true;
				
				InitTable = true;

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
							defaultContent: "<a class='blue' href='#modal-table' role='button' data-toggle='modal'><i class='ace-icon fa fa-search-plus bigger-150'></i></a>",
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
								$(this).html("<a href='#modal-table' role='button' data-toggle='modal'>"+$(this).html()+"</a>");
							});
							
							$('#dynamic-table tbody tr .Trafico').each(function() { //Agregamos el label a la columna trafico que corresponde
								$(this).html("<span class='label label-sm "+ ($(this).html() === 'IMPO'?'label-success':'label-info')+" arrowed arrowed-righ'>"+$(this).html()+"</span>");
							});
							
							$('#dynamic-table tbody tr a').click(function()
							{
								//console.log( $('#dynamic-table').DataTable().row( this ).data().CntrConsecutivo );	
								
								var ColumnaActual = $(this).parent().parent().get(0);	
								var	FilaActual = $('#dynamic-table').DataTable().row( ColumnaActual ).data();//Guardamos todo el contenido de la fila actual							
								$(this).getEvents(FilaActual);
							});			

						  }
					},
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
				.attr('title', 'Show/hide columns').tooltip({container: 'body'});
				
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
				$("#modal-table").load("includes/content/pages/containers-files/modal2.html");
				
				return false;
			},
			getEvents:  function (p_Data) //Funcion: Se obtienen los eventos para el consecutivo del contenedor
			{	

				$(".Galeria").hide();
				$(".modal-header h3").text(p_Data.Container);	
				
				$('#TablaEventos').dataTable(
				{
					bAutoWidth: false,
					"aaSorting": [[0,'desc']],
					"ajax": 'includes/content/pages/containers-files/containers.js',
					columns: [ 
						{
							title: "Evento",
							data: "CntrConsecutivo",
							visible: false
						},
						{
							title: "Tipo Evento",
							data: "Container"
						},
						{
							title: "Trafico",
							data: "VesselName"
						},
						{
							title: "Transporte",
							data: "VesselName"
						},
						{
							title:"Fecha",
							data: "CntrType"
						},
						{
							title: "Galeria",
							data: null,
							defaultContent: "<a class='blue' href='#modal-table' role='button' data-toggle='modal'><i class='ace-icon fa fa-search-plus bigger-150'></i></a>",
							sortable: false,
							className: "center"
						} 
					]	
				});
				
				
				//p_Data.CntrConsecutivo
				TablaEventos = '';
			
				$.ajax({ 
					headers: {'Content-Type': "application/json; charset=utf-8"},
					crossDomain : true,
					type: 'get', 
					url: 'includes/content/pages/containers-files/data_container_images.js', 
					dataType: 'json',
					data: null,
					success: function(data)
						{ 
							
							//$('#TablaEventos').show();
							
																			
							$.each(data.content, function(index, element) 
							{	
								TablaEventos = TablaEventos +
									"<tr>"+
										"<td class='hidden'>"+element.evento+"</td>"+
										"<td class='center'><i class='ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue'></i> <b><a href='#' onclick='$(this).getGallery(\""+element.evento+"\")' >"+element.tipoevento+"</a></b></td>"+
										"<td>"+element.trafico+"</td>"+
										"<td>"+element.transporte+"</td>"+
										"<td>"+element.fecha+"</td>"+
										"<td>"+
											"<div class='action-buttons center'>"+
												"<a class='blue' href='#' onclick='$(this).getGallery(\""+element.evento+"\")'><i class='ace-icon fa fa-search-plus bigger-200'></i></a>"+
											"</div>"+
										"</td>"+
									"</tr>";
							});
													
							$('#TablaEventos tbody').html(TablaEventos);
						}
						,error: function(e)
						{
							TablaEventos = '<div class="alert alert-danger col-sm-10" role="alert">'+e.message+'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>';						
							$('#TablaEventos').html(TablaEventos);
						}
				});				
			},						
			getGallery: function (p_Evento) //Funcion: Cargamos el contenido de la galeria de acuerdo al consecutivo del evento seleccionado
			{						
				//$(".Galeria .widget-main").html('<div><img src="../assets/images/loaders/gears.svg" width="50px" </div>');

				$.ajax({ 
				headers: {'Content-Type': "application/json; charset=utf-8"},
				crossDomain : true,
				type: 'get', 
				url: 'includes/content/pages/containers-files/data_container_images.js', 
				dataType: 'json',
				data: null,
				success: function(data)
					{ 
					
						$(".Galeria").show();
						
						GaleriaFiltros = 	"<div id='sp-simpleportfolio' class='sp-simpleportfolio sp-simpleportfolio-view-items layout-gallery-space'>"+
												"<div class='sp-simpleportfolio-filter'>"+
													"<ul>"+
														"<li class='active' data-group='all'><a href='#'>Ver Todas</a></li>";
															
						$.each(data.content, function(index, element) 
						{	

							if ( element.evento == p_Evento)
							{
								Galeria = '';
								GaleriaVideos = '';
								$.each(data.content[index].sides, function(index, element) 
								{									
									side = element.side;
									title = element.sidename;
									
									GaleriaFiltros = GaleriaFiltros + "<li data-group='"+side+"'><a href='#'>"+title+"</a></li>";

									$.each(element.images, function(index, element) 
									{ 											
										Galeria = Galeria + 
										'<div class="sp-simpleportfolio-item" data-groups=\'["'+side+'"]\'   >'+		//data-groups=\'[\"'+side+'\"]\'													
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
																		
									$.each(element.videos, function(index, element) //----
									{ 											
											
										////player.vimeo.com/video/66882085
										// poster='../assets/images/tnVideo.png' 
										// width='584px' height='776px' 
										
										DateNow = Math.floor((Math.random() * 1000000) + 1); //jQuery.now();
																				
										//Anexamos los videos											
										GaleriaVideos = GaleriaVideos +
														"<video id='VIDEO_"+DateNow+"' class='sp-simpleportfolio-lightbox' controls preload='metadata' poster='../assets/images/posterVideo.png'  data-setup='{}'>"+ //
															"<source src='"+element.url+"' type='video/mp4'>"+
														"</video>";
										 
										//"<iframe class='sp-simpleportfolio-lightbox' src='"+element.url+"' width='500' height='281' id='sp-simpleportfolio-video7' style='border:none;' webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>"+
												
										//Anexamos los tumbnails de los videos		
										Galeria = 	Galeria + 
													'<div class="sp-simpleportfolio-item"  data-groups=\'["'+side+'"]\'  >'+ 
														'<div class="sp-simpleportfolio-overlay-wrapper clearfix">'+
															'<span class="sp-simpleportfolio-icon-video"></span>'+
															'<img class="sp-simpleportfolio-img" src="../assets/images/tnVideo.png" >'+
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
							}												
						});
																		
						GaleriaFiltros = 	GaleriaFiltros + 
													'</ul>'+
												'</div>'+
											'<div id="GaleriaFotos" class="sp-simpleportfolio-items sp-simpleportfolio-columns-6">'+
												Galeria +
											'</div>';
								
						$("#GaleriaVideos").html(GaleriaVideos);		
						$(".Galeria .widget-main").html(GaleriaFiltros);
						
						$('#GaleriaFotos').loadScripts();
						
						/*$('.sp-simpleportfolio-item a').click(function()
						{
							var IDVideo = $(this).attr("data-featherlight");
							//document.getElementById(IDVideo).play();
							
							$(IDVideo).get(0).play();
						});*/
						
						// $('a.gallery').featherlightGallery({
							// openSpeed: 300
						// });
																	
					}
					,error: function(e)
					{
						Galeria = '<div class="alert alert-danger col-sm-10" role="alert">'+e.message+'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>';
						
						$('#GaleriaFotos').html(Galeria);
					}
				});
				
				return false;
			},			
			loadScripts: function () //Funcion: Recargamos los scripts de la galeria 
			{	
				$('#ScriptsGallery').getScripts("../assets/plugins/loadimg/loadImg.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/spsimpleportfolio.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.min.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.gallery.min.js");				
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
		