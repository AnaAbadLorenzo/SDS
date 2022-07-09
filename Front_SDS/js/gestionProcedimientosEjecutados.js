/* Función para obtener las noticias del sistema */
async function cargarProcedimientosEjecutados(numeroPagina, tamanhoPagina, paginadorCreado){
  try{
	    const res = await cargarProcedimientosEjecutadosAjaxPromesa(numeroPagina, tamanhoPagina)
	  
      var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
        var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
      }else{
        inicio = parseInt(res.data.inicio)+1;
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
      }else{
        $('#itemPaginacion').attr('hidden',false);
      }
	   	
      $("#datosProcedimientoEjecutado").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
          try{
          const numerosProcesos = await cargarProcesosProcedimientoAjaxPromesa(0, 0, res.data.listaBusquedas[i].idProcedimientoUsuario);
          for(var j = 0; j< numerosProcesos.data.listaBusquedas.length; j++){
            try{
              const numerosProcesosEjecutados = await cargarProcesosOfProcedimientoUsuario(numeroProcesos.data.listaBusquedas[j].idProcedimientoUsuario)
              var tr = construyeFilaProcedimientoEjecutado('PROCEDIMIENTOSEJECUTADOS', res.data.listaBusquedas[i], numeroProcesos.data.listaBusquedas.length, numerosProcesosEjecutados.procesos.length);
              $("#datosProcedimientoEjecutado").append(tr);
            }catch(numeroProcesosEjecutados){
              respuestaAjaxKO(res.code);
              document.getElementById("modal").style.display = "block";
            }
          }
        }catch(numerosProcesos){
          respuestaAjaxKO(res.code);
          document.getElementById("modal").style.display = "block";
        }
    	}
    	
    	var div = createHideShowColumnsWindow({NOMBRE_PROCEDIMIENTO_COLUMN : 1, LOGIN_USUARIO_COLUMN:2, DATE_COLUMN: 3, ESTADO_PROCEDIMIENTO_EJECUTADO_COLUMN: 4});
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
      	setLang(getCookie('lang'));

        if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'cargarProcedimientosEjecutados', 'PROCEDIMIENTOSEJECUTADOS');
        }
        
        if(numeroPagina == 0){
          $('#' + (numeroPagina+1)).addClass("active");
          var numPagCookie = numeroPagina+1;
        }else{
          $('#' + numeroPagina).addClass("active");
           var numPagCookie = numeroPagina;
        }

        setCookie('numeroPagina', numPagCookie);
	  
		}catch(res){
		    respuestaAjaxKO(res.code);
		    document.getElementById("modal").style.display = "block";
		};
}

function cargarProcesosProcedimientoAjaxPromesa(numeroPagina, tamanhoPagina, idProcedimiento){
   return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var procesoProcedimientoPaginacion = {
      idProcedimiento : idProcedimiento,
      inicio : '',
      tamanhoPagina : ''
    }

     $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoProcesosProcedimientoByIdProcedimiento,
      contentType : "application/json",
      data: JSON.stringify(procesoProcedimientoPaginacion),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCESOS_PROCEDIMIENTOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

function cargarProcedimientosEjecutadosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaProcedimientosEjecutados),
      tamanhoPagina : tamanhoPaginaProcedimientosEjecutados
    }

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxListarTodosProcedimientosUsuario,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCEDIMIENTOS_USUARIO_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para obtener los procedimientos de un usuario con ajax y promesas**/
function cargarProcesosOfProcedimientoUsuario(idProcedimientoUsuario){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarProcedimientoUsuarioProcesoOfProcedimientoUsuario,
      contentType : "application/json",
      data: idProcedimientoUsuario.toString(),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCEDIMIENTOS_USUARIOS_PROCESOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}
