/* Función para obtener los procesos ejecutados del sistema */
async function cargarProcesosEjecutados(numeroPagina, tamanhoPagina, paginadorCreado){
  try{
	    const res = await cargarProcesosEjecutadosAjaxPromesa(numeroPagina, tamanhoPagina)
	  
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
	   	
      $("#datosProcesoEjecutado").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
          try{
            const datosProcedimientoAsociado = await cargarProcedimiento(res.data.listaBusquedas[i].procedimientoUsuario.idProcedimientoUsuario);
            var tr = construyeFilaProcesosEjecutado('PROCESOSEJECUTADOS', res.data.listaBusquedas[i], datosProcedimientoAsociado.data);
            $("#datosProcesoEjecutado").append(tr);
            
            }catch(datosProcedimientoAsociado){
              respuestaAjaxKO(datosProcedimientoAsociado.code);
              document.getElementById("modal").style.display = "block";
            }
       
    	}
    	
    	var div = createHideShowColumnsWindow({NOMBRE_PROCEDIMIENTO_COLUMN : 1, LOGIN_USUARIO_COLUMN:2, NOMBRE_PROCESO_COLUMN: 3, DATE_COLUMN: 4});
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
         document.getElementById('filasTabla').style.display = "block";
      	setLang(getCookie('lang'));

        if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'cargarProcesosEjecutados', 'PROCESOSEJECUTADOS');
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

/* Función para obtener los procesos ejecutados del sistema */
async function refrescarTabla(numeroPagina, tamanhoPagina){
  try{
      const res = await cargarProcesosEjecutadosAjaxPromesa(numeroPagina, tamanhoPagina)
    
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
      
      $("#datosProcesoEjecutado").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
          try{
            const datosProcedimientoAsociado = await cargarProcedimiento(res.data.listaBusquedas[i].procedimientoUsuario.idProcedimientoUsuario);
            var tr = construyeFilaProcesosEjecutado('PROCESOSEJECUTADOS', res.data.listaBusquedas[i], datosProcedimientoAsociado.data);
            $("#datosProcesoEjecutado").append(tr);
            
            }catch(datosProcedimientoAsociado){
              respuestaAjaxKO(datosProcedimientoAsociado.code);
              document.getElementById("modal").style.display = "block";
            }
       
      }
      
      var div = createHideShowColumnsWindow({NOMBRE_PROCEDIMIENTO_COLUMN : 1, LOGIN_USUARIO_COLUMN:2, NOMBRE_PROCESO_COLUMN: 3, DATE_COLUMN: 4});
        $("#checkboxColumnas").append(div);
        $("#paginacion").append(textPaginacion);
         document.getElementById('filasTabla').style.display = "block";
        setLang(getCookie('lang'));

        
        paginador(totalResults, 'cargarProcesosEjecutados', 'PROCESOSEJECUTADOS');
        
        
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

function cargarProcesosEjecutadosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaProcesosEjecutados),
      tamanhoPagina : tamanhoPaginaProcesosEjecutados
    }

    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarProcedimientosUsuariosProcesos,
      contentType : "application/json",
      data : JSON.stringify(data),
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

/** Función para obtener los procedimientos de un usuario con ajax y promesas**/
function cargarProcedimiento(idProcedimientoUsuario){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarProcedimientoUsuarioById,
      contentType : "application/json",
      data: idProcedimientoUsuario.toString(),  
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

function showDetalle(nombreProcedimiento, usuario, nombreProceso, fechaProcedimientoUsuarioProceso, respuesta, evidencia, idProcedimientoUsuarioProceso){
    var idioma = getCookie('lang');

    cambiarFormulario('DETAIL_PROCESO_EJECUTADO', 'javascript:detalleProcesoEjecutado();', '');
    
    cambiarIcono('images/close2.png', 'ICONO_CERRAR', 'iconoCerrar', 'Detalle');

    setLang(idioma);

    $('#labelNombreProcedimiento').removeAttr('hidden');
    $('#labelLoginUsuario').removeAttr('hidden');
    $('#labelFechaProcesoEjecutado').removeAttr('hidden');
    $('#labelNombreProceso').removeAttr('hidden');
    $('#labelPuntuacionProcedimientoEjecutado').removeAttr('hidden');
    $('#labelTextoRespuestaPosible').removeAttr('hidden');
    $('#labelEvidencia').removeAttr('hidden');
    

    rellenarFormulario(nombreProcedimiento, usuario, nombreProceso, fechaProcedimientoUsuarioProceso, respuesta, evidencia);

    let campos = ["nombreProcedimiento", "loginUsuario", "fechaProcesoEjecutado", "nombreProceso", "textoRespuestaPosible"];
    let obligatorios = ["obligatorioNombreProcedimiento", "obligatorioLoginUsuario", "obligatorioFechaProcesoEjecutado", "obligatorioNombreProceso", "obligatorioEvidencia"];
    
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/**Función para cerrar la modal de detalle de procedimiento*/
function detalleProcesoEjecutado(){
    $("#form-modal").modal('toggle');

    let idElementoList = ["nombreProcedimiento", "loginUsuario", "fechaProcesoEjecutado", "nombreProceso", "textoRespuestaPosible"];

    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
}

/** Función para recuperar los permisos de un usuario sobre la funcionalidad **/
function cargarPermisosFuncProcesosEjecutadosAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxAccionesUsuario,
      contentType : "application/json",
      data: { usuario : usuario, funcionalidad : 'Gestión de procesos ejecutados'},  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/*Función que comprueba los permisos del usuario sobre la funcionalidad*/
async function cargarPermisosFuncProcesosEjecutados(){
  await cargarPermisosFuncProcesosEjecutadosAjaxPromesa()
  .then((res) => {
    gestionarPermisosProcesosEjecutados(res.data);
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/** Función para gestionar los iconos dependiendo de los permisos de los usuarios **/
function gestionarPermisosProcesosEjecutados(idElementoList) {
  idElementoList.forEach( function (idElemento) {
    switch(idElemento){

      case 'Listar' :
        $('#btnListarProcesosEjecutados').attr('src', 'images/search3.png');
        $('#btnListarProcesosEjecutados').css("cursor", "pointer");
        $('#divListarProcesoEjecutado').attr("data-toggle", "modal");
        $('#divListarProcesoEjecutado').attr("data-target", "#form-modal");
        document.getElementById('tablaDatos').style.display = "block";

      case "Visualizar" :
        $('.detallePermiso').attr('src', 'images/detail3.png');
        $('.detallePermiso').css("cursor", "pointer");
        $('.detallePermiso').attr("data-toggle", "modal");
        $('.detallePermiso').attr("data-target", "#form-modal");
      break;

    } 
    }); 
}

function rellenarFormulario(nombreProcedimiento, usuario, nombreProceso, fechaProcedimientoUsuarioProceso, respuesta, evidencia){
  $('#nombreProcedimiento').val(nombreProcedimiento);
  $('#loginUsuario').val(usuario);
  $('#nombreProceso').val(nombreProceso);
  $('#fechaProcedimientoUsuarioProceso').val(fechaProcedimientoUsuarioProceso);
  $('#textoRespuestaPosible').val(respuesta);
}