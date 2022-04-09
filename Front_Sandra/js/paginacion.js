/** Función para saber el número de páginas necesarias para ROL **/
function paginadorRol(totalResults, funcionalidad){
	var paginas="";

    var numeroPaginas = totalResults / tamanhoPaginaRol ;

    $('#itemPaginacion').html('');

    var paginacionPrevio = '<nav aria-label= "Page navigation example">' +
    					'<ul class="pagination">' +
    						'<li class="page-item disabled tooltip">' +
    							'<a class="page-link" href="#" aria-label="Previous">' +
    								'<span aria-hidden="true">&laquo;</span>' +
    								'<span class="sr-only">Previous</span>' +
    								'<span class="tooltiptext"> Anterior</span>' +
    							'</a>' +
    						'</li>';

    switch(funcionalidad){
    	case 'cargarRoles': 
    		for(var i = 0; i< numeroPaginas; i++){
    			paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="cargarRoles(' 
   					+ (i+1) + ',' + tamanhoPaginaRol + ')">' + (i+1) + '</a></li>';
    		}
    	break;

    	case 'buscarRol' : 
    		for(var i = 0; i< numeroPaginas; i++){
    			paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarRol(' 
   					+ (i+1) + ',' + tamanhoPaginaRol + ')">' + (i+1) + '</a></li>';
    		}
    	break;

    	case 'buscarEliminadosRol' :
    		for(var i = 0; i< numeroPaginas; i++){
    			paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarEliminados(' 
   					+ (i+1) + ',' + tamanhoPaginaRol + ')">' + (i+1) + '</a></li>';
    		}
    	break;
    }

    /*if(numeroPaginas > 3){
    	if(numeroPaginas==4){
    		$('#4').prop('hidden', true);
    	}else{
    		for(var i = 4; i<=numeroPaginas; i++){
    			$('#' + i).prop('hidden', true);
    		}
    	}
    	
    }*/

   	var paginacionSiguiente = '<li class="page-item navegacion tooltip">' +
	   								'<a class="page-link" href="#" aria-label="Next">' + 
	   									'<span aria-hidden="true">&raquo;</span>' + 
	   									'<span class="sr-only">Next</span>' +
	   									'<span class="tooltiptext">Siguiente</span>' + 
	   								'</a>' +
	   							'</li>' + 
	   						'</ul>' +
	   					'</nav>';

   
	var pag = paginacionPrevio + paginas + paginacionSiguiente;

	$('#itemPaginacion').append(pag);
}

/** Funcion para calcular el inicio de las peticiones **/
function calculaInicio(numeroPagina, tamanhoPagina){
	var inicio = 0;

	switch(numeroPagina){
		case 0:
			inicio = 0;
		break;
		case 1:
			inicio = 0;
		break;
		case 2 : 
			inicio = tamanhoPagina;
		break;
		default:
			inicio = (numeroPagina-1)*tamanhoPagina;
		break;
	}

	return inicio;
}