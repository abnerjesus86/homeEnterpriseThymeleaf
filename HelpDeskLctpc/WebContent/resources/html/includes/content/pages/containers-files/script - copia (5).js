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
	var	oTable1 = '';

	//Mantenemos algunos elementos ocultos hasta su uso
	//$("#TablaResultados" ).hide();
	//$('#loader-search').hide();
	//$("#AlertaBusqueda").hide();
	//$("#RangoBusqueda").hide();				
	//$("#IconoBusqueda").hide();
		
	$("[data-toggle=popover]").popover();
		
	$.mask.definitions['~']='[+-]';
	//$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
	//$('.input-mask-container').mask("***********");//aaaa9999999
	//$('.input-mask-reference').mask("aaa9999999");
	//$('.input-mask-date').mask('99/99/9999');
	
	jQuery.fn.extend
		({		
			setSearch: function () //Funcion: Comienza el proceso de Busqueda
			{
				var valContainer = $('#TxtContainer').val();
				var valReference = $('#TxtReference').val();
				var valStartDate = $('#RangoBusqueda #start').val();
				var valEndDate	 = $('#RangoBusqueda #end').val();
				
				if ((valContainer+valReference+valStartDate+valEndDate).length == 0)
				{
					$('#alert-search').hide().html($(this).getAlert('¡Busqueda incompleta!','Debes de ingresar un valor en los campos de busqueda para obtener resultados')).fadeIn('slow'); //.cleanTable();
					$('#TablaResultados').addClass('hidden');
					console.log('¡Busqueda incompleta! Debes de ingresar un valor en los campos de busqueda para obtener resultados');
				}	
				else
				{	
					$('#alert-search').hide("slow");
					var p_Container =  	"CONTAINER:"+(valContainer.length==0?"":valContainer);
					var p_Reference = 	"REFERENCE:"+(valReference.length==0?"":valReference);
					var p_Dates =  		"DATE:"+((valStartDate+valEndDate).length==0?"":(valStartDate+','+valEndDate)); 
						
					var jsonDataObject = new Object();
					jsonDataObject.parameter1 = p_Container+'|'+p_Reference+'|'+p_Dates;
					var jsonData = JSON.stringify(jsonDataObject);
					jQuery.support.cors = true;
										
					$(this).getSearchResults(jsonData);						
				}

				return false;
			},
			getSearchResults: function (p_Data) //Funcion: Obtenemos los resultados de la busqueda
			{
				
				//console.log(p_Data);
				console.log('Realizando Busqueda...');
				$('#loader-search').removeClass('hidden').show("fast");
				$('#loader-search').css({top: 200, left: (($( window ).width()/2)-130), position:'absolute'});

				if (InitTableSearch)
				{
					//console.log('InitTableSearch');
					$('#dynamic-table').cleanTable();
				}	
				else				
					InitTableSearch = true;
				
				$.ajax({
						url: "http://10.130.6.1:7001/wseir/wsOCR/Containers",
						data: p_Data,
						dataType: "json",
						type: "post",
						contentType: "application/json; charset=utf-8",
						success: function(result) 
						{
								
							//console.log(result)	;
														
							oTable1 = 
							$('#dynamic-table')
							//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
							.dataTable( 
							{
								bAutoWidth: false,
								aaSorting: [[0,'desc']],
								aaData: result,
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
									$('#loader-search').addClass('hidden');
									  
									  if (oSettings.fnRecordsTotal() > 1) 
									  {
										console.log('Tabla Resultados Cargada Correctamente');									
										
										$("#TablaResultados" ).removeClass('hidden').show("fast");
																					
										$('#dynamic-table tbody tr').find('td:first-child').each(function(){ //Agregamos la opcion de enlace a la columna contenedor
											$(this).html("<div class='action-buttons center'><span class='selectedlink'></span><a href='#modal-table' role='button' data-toggle='modal'><b>"+$(this).html()+"</b></a></div>");
										});

										$('#dynamic-table tbody tr .Trafico').each(function() { //Agregamos el label a la columna trafico que corresponde
											$(this).html("<span class='label label-sm "+ ($(this).html() === 'IMPO'?'label-success':'label-info')+" arrowed arrowed-righ'>"+$(this).html()+"</span>");
										});
										
										$('#dynamic-table tbody tr a').click(function()
										{
											var ColumnaActual = $(this).parent().parent().parent().get(0);	
											var	FilaActual = $('#dynamic-table').DataTable().row( ColumnaActual ).data();							
											$(this).getEvents(FilaActual);
										});	
										
										$('.paginate_button').click(function()
										{
											$('#dynamic-table tbody tr').find('td:first-child').each(function(){ //Agregamos la opcion de enlace a la columna contenedor
												$(this).html("<div class='action-buttons center'><span class='selectedlink'></span><a href='#modal-table' role='button' data-toggle='modal'><b>"+$(this).html()+"</b></a></div>");
											});
											
											$('#dynamic-table tbody tr .Trafico').each(function() { //Agregamos el label a la columna trafico que corresponde
												$(this).html("<span class='label label-sm "+ ($(this).html() === 'IMPO'?'label-success':'label-info')+" arrowed arrowed-righ'>"+$(this).html()+"</span>");
											});
										});	
													

									  }
									  else
									  {
										console.log('No se encontraron resultados');
										$('#alert-search').hide().html($(this).getAlert('¡No se encontraron resultados!','Realiza una nueva busqueda con diferentes valores')).fadeIn('slow');
										$('#TablaResultados').addClass('hidden');
										//$(this).cleanTable();
										//$('#dynamic-table').DataTable().destroy();
										//$('#dynamic-table').empty();
										//$('.tableTools-container').empty();
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
						},
						  error: function(e){
							alert('error!: '+e);
						  }
					});
					
				return false;
			},
			getEvents:  function (p_Data) //Funcion: Se obtienen los eventos para el consecutivo del contenedor
			{	

				//$(".Galeria").hide();
				$(".modal-header h3").text(p_Data.Container);
				console.log(p_Data.CntrConsecutivo);

				if (InitTableEvents)		
					return true;				
				InitTableEvents = true;				
				
				var tableEvents = $('#TablaEventos').dataTable(
				{
					ajax: 'includes/content/pages/containers-files/data_container_images_new.js',
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
															"<video id='VIDEO_"+DateNow+"' class='sp-simpleportfolio-lightbox' controls preload='metadata' poster='../assets/images/posterVideo.png'  data-setup='{}'>"+ //
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
			getDate: function (p_MonthView)
			{	
				var d = new Date();
				var month = d.getMonth()+1-p_MonthView;
				var day = d.getDate();

				var startdate = ((''+day).length<2 ? '0' : '') + day +'/'+((''+month).length<2 ? '0' : '') + month + '/' + d.getFullYear();
				
				return startdate
			},
			getAlert: function (p_PreMessage,p_Message)
			{
				return 	'<br/>'+
						'<div id="AlertaBusqueda" class="alert alert-danger col-xs-12 col-sm-12 col-lg-8">'+
							'<button type="button" class="close" data-dismiss="alert">'+
								'<i class="ace-icon fa fa-times"></i>&nbsp;'+
							'</button>'+
							'<strong>'+
								'<i class="ace-icon fa fa-times "></i>'+
								p_PreMessage+
							'</strong>&nbsp;'+
							
							'<span>'+p_Message+'</span>'+	
						'</div>';
			},
			cleanTable: function ()
			{
				$('#TablaResultados').addClass('hidden');
				$('#dynamic-table').DataTable().destroy();
				$('#dynamic-table').empty();
				$('.tableTools-container').empty();
				return false;
			}
		});

		//datepicker plugin
		/*$('.date-picker').datepicker({
			autoclose: true,
			todayHighlight: true
		}).next().on(ace.click_event, function()//Mostrar Datepicker
		{
			$(this).prev().focus();
		});*/
		
		$.fn.datepicker.dates['es'] = {
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
		
		//or change it into a date range picker
		$('.input-daterange').datepicker({
			format: "dd/mm/yyyy"
			,weekStart: 0
			//,startDate: $(this).getDate(3)
			//,endDate: $(this).getDate(0)
			,todayBtn: true
			,clearBtn: true
			,language: "es"
			,autoclose: false
			//,datesDisabled: ['05/10/2015', '02/10/2015']
			});
		
		$("#BotonBusqueda").click(function()
		{			
			var Input = $(this);		
			Input.prop('disabled',true).setSearch();		
			window.setTimeout(function(){ Input.prop('disabled',false).focus();},1000);	 
		});
		
		$('#FormularioBusqueda input').on('keypress', function (event) 
		{
			if(event.which === 13)
			{ 
				var Input = $(this);		
				Input.prop('disabled',true).setSearch();		
				window.setTimeout(function(){ Input.prop('disabled',false).focus();},1000);	
			}		
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
		