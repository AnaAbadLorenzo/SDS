/** Función para saber el número de páginas necesarias para ROL **/
function paginador(totalResults, funcionalidad, entidad){
	var paginas="";

    var numeroPaginas = totalResults / tamanhoPaginaRol ;

    var arrayPaginas = [];

    var arrayElementos = [];

    var numElementos = 0;

    var elementInsert = "(";

    var j=0;

    for(var i = 0; i<numeroPaginas; i++){
        var elementArray = ["(" + (i+1), calculaInicio((i+1), tamanhoPaginaRol),tamanhoPaginaRol + ")"];

        if(numElementos<3){
            arrayElementos.push(elementArray);
            numElementos++;
        }else{
           numElementos = 0;
           arrayPaginas.push(arrayElementos);
           arrayElementos=[];
        }

    }

    $('#itemPaginacion').html('');

    var paginacionPrevio = '<nav aria-label= "Page navigation example">' +
    					'<ul class="pagination">' +
    						'<li class="page-item navegacion tooltip">' +
    							'<a class="page-link" href="#" aria-label="Previous">' +
    								'<span aria-hidden="true">&laquo;</span>' +
    								'<span class="sr-only">Previous</span>' +
    								'<span class="tooltiptext"> Anterior</span>' +
    							'</a>' +
    						'</li>';

    paginas = escogeEntidadPaginacion(entidad, funcionalidad);

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

    cargarPermisosSegunEntidad(entidad);

	$('#itemPaginacion').append(pag);

    setCookie('arrayPaginas', arrayPaginas);
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

function escogeEntidadPaginacion(entidad, funcionalidad){

    var paginas ="";

    switch(entidad){
        case 'ROL':
            switch(funcionalidad){
                case 'cargarRoles': 
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="cargarRoles(' 
                            + (i+1) + ',' + tamanhoPaginaRol + ')">' + (i+1) + '</a></li>';
                    }
                break;

                case 'buscarRol' : 
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarRol(' 
                            + (i+1) + ',' + tamanhoPaginaRol + ", \'buscarPaginacion\'" + ')">' + (i+1) + '</a></li>';
                    }
                break;

                case 'buscarEliminadosRol' :
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarEliminados(' 
                            + (i+1) + ',' + tamanhoPaginaRol + ')">' + (i+1) + '</a></li>';
                    }
                break;
            }
        break;

        case 'ACCION' : 
            switch(funcionalidad){
                case 'cargarAcciones': 
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="cargarAcciones(' 
                            + (i+1) + ',' + tamanhoPaginaAccion + ')">' + (i+1) + '</a></li>';
                    }
                break;

                case 'buscarAccion' : 
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarAccion(' 
                            + (i+1) + ',' + tamanhoPaginaAccion + ", \'buscarPaginacion\'" + ')">' + (i+1) + '</a></li>';
                    }
                break;

                case 'buscarEliminadosAccion' :
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarEliminados(' 
                            + (i+1) + ',' + tamanhoPaginaAccion + ')">' + (i+1) + '</a></li>';
                    }
                break;
            }
        break;

        case 'FUNCIONALIDAD':
            switch(funcionalidad){
                case 'cargarFuncionalidades': 
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="cargarFuncionalidades(' 
                            + (i+1) + ',' + tamanhoPaginaFuncionalidad + ')">' + (i+1) + '</a></li>';
                    }
                break;

                case 'buscarFuncionalidad' : 
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarFuncionalidad(' 
                            + (i+1) + ',' + tamanhoPaginaFuncionalidad + ", \'buscarPaginacion\'" + ')">' + (i+1) + '</a></li>';
                    }
                break;

                case 'buscarEliminadosFuncionalidad' :
                    for(var i = 0; i< 3; i++){
                        paginas += '<li id="' + (i+1) + '" class="page-item"><a class="page-link" href="#" onclick="buscarEliminados(' 
                            + (i+1) + ',' + tamanhoPaginaFuncionalidad + ')">' + (i+1) + '</a></li>';
                    }
                break;
            }
        break;

    }

    return paginas;
        
}