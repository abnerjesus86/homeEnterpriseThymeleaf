
	jQuery(function($) 
	{	
		var CntrConsecutivo;
		var content = "";
		var title = "";
		var side = "";
		var TablaEventos = "";
		var Galeria = "";
		var GaleriaVideos = "";
		var GaleriaFiltros = "";
		var DateNow;
		

		$('#TablaEventos').hide();
		$(".Galeria").hide();
		
		jQuery.fn.extend
		({		
			//Funcion: Se obtienen los eventos para el consecutivo del contenedor
			getEvents:  function ()
			{	
	
				$('#dynamic-table tbody tr').each(function() {					
					CntrConsecutivo = $(this).find("td:first-child a").html();	
					console.log('consecutivo: '+CntrConsecutivo);	
				});
				console.log('consecutivo: '+CntrConsecutivo);	
				//$(".modal-header h3").text(CntrConsecutivo);	
			
				$.ajax({ 
					headers: {'Content-Type': "application/json; charset=utf-8"},
					crossDomain : true,
					type: 'get', 
					url: 'includes/content/pages/containers-files/data_container_images.js', 
					dataType: 'json',
					data: null,
					success: function(data)
						{ 
							
							$('#TablaEventos').show();
																			
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
			//Funcion: Cargamos el contenido de la galeria de acuerdo al consecutivo del evento seleccionado
			getGallery: function (p_Evento) 
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
			//Funcion: Recargamos los scripts de la galeria 
			loadScripts: function () 
			{	
				$('#ScriptsGallery').getScripts("../assets/plugins/loadimg/loadImg.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/spsimpleportfolio.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.min.js");
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/featherlight.gallery.min.js");				
				$('#ScriptsGallery').getScripts("../assets/plugins/simpleportafolio/js/jquery.shuffle.modernizr.min.js");
				
				return false;
			},
			getScripts: function (p_Script)
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
			playVideo: function (p_Video)
			{
				p_Video.play(); 
				return false;
			}
		});
				
		$("#TablaEventos").getEvents();			
	});
		