/**
 * 
 */

jQuery(function($){
	
	jQuery.fn.extend({
		initializeTable : function(){
			
			var menuDinamico = $("#side-menu");
			var liNavHeader = $("<li class='nav-header'>"+
							"<div class='dropdown profile-element'>"+
							 "<span> <img alt='image' class='img-circle' th:src='@{/res/plantilla/INSPINIA/img/profile_small.jpg}' />"+
                             "</span>"+ 
                             "<a data-toggle='dropdown' class='dropdown-toggle' href='#'>"+ 
	                             "<span class='clear'>"+ 
	                                "<span class='block m-t-xs'> <strong class='font-bold'> <span sec:authentication='principal.username'></span></strong></span>"+ 
									"<span class='text-muted text-xs block'><span sec:authentication='principal.authorities'> </span><b class='caret'></b></span>"+
								 "</span>"+
							 "</a>"+
							 "<ul class='dropdown-menu animated fadeInRight m-t-xs'>"+
								"<li><a th:href='@{/logout}'>Logout</a></li>"+
							 "</ul>"+
							"</div>"+
							"<div class='logo-element'>IN+</div>"+
						"</li> ");
			var appnId = 3;
			//var menuAjax;
			
			$.ajax({
				url : 'http://localhost:7101/ApplicationManager/resources/buildapplication/menu/'+appnId,
				type : 'GET',
				contentType : 'text/plain',
				data : {"p_username" : 'BENITEZ.ABNER'},
				crossDomain : true,
				success : function(dataJson){
					var menuAjax = $(dataJson);
					
					console.log(menuAjax);
					$(menuAjax.children()).each(function(i,itemChildren){
						console.log(i);
						menuDinamico.append(itemChildren);
						//$(itemChildren).appendTo(menuDinamico);
						//$(itemChildren).clone().appendTo(menuDinamico);
						//$(menuDinamico).appendChild(itemChildren);
					} );
					//menuDinamico.prepend(liNavHeader);
					//console.log(menuDinamico);
					//menuDinamico.append(menuAjax.children());
					//console.log("Menu");
					//console.log(menuDinamico);
				}
				
			});
			
			
		} // fin funcion de initializeTable
	});
	
	$(this).initializeTable();
	
} ); // fin funcion principal