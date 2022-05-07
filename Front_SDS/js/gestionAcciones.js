/** Función para añadir acciones con ajax y promesas **/
function anadirAccionAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var accionEntity = {
      idAccion : "",
      nombreAccion : $("#nombreAccion").val(),
      descripAccion : $("#descripcionAccion").val(),
      borradoAccion : 0
    }
  
    var data = {
      usuario : getCookie('usuario'),
      accion : accionEntity
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxAccionGuardar,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCION_GUARDADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para buscar funcionalidades con ajax y promesas **/
function buscarAccionAjaxPromesa(numeroPagina, tamanhoPagina, accion){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    if(accion == "buscarModal"){
      var data = {
        nombreAccion : $("#nombreAccion").val(),
        descripAccion : $("#descripcionAccion").val(),
        inicio : calculaInicio(numeroPagina, tamanhoPaginaAccion),
        tamanhoPagina : tamanhoPaginaAccion
      }
    }

    if(accion == "buscarPaginacion"){
       if(getCookie('nombreAccion') == null || getCookie('nombreAccion') == ""){
        var nombreAcc = "";
      }else{
        var nombreAcc = getCookie('nombreAccion');
      }

      if(getCookie('descripAccion') == null || getCookie('descripAccion') == ""){
        var descripAcc = "";
      }else{
        var descripAcc = getCookie('descripAccion');
      }

      var data = {
        nombreAccion : nombreAcc,
        descripAccion : descripAcc,
        inicio : calculaInicio(numeroPagina, tamanhoPaginaAccion),
        tamanhoPagina : tamanhoPaginaAccion 
      }
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarAccion,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCION_ENCONTRADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para recuperar los permisos de un usuario sobre la funcionalidad **/
function cargarPermisosFuncAccionAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxAccionesUsuario,
      contentType : "application/json",
      data: { usuario : usuario, funcionalidad : 'Gestión de acciones'},  
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

/**Función para eliminar una accion con ajax y promesas*/
function eliminarAccionAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var accionEntity = {
      idAccion : $("input[name=idAccion]").val(),
      nombreAccion : $("#nombreAccion").val(),
      descripAccion : $("#descripcionAccion").val(),
      borradoAccion : 1
    }
    
    var data = {
      usuario : getCookie('usuario'),
      accion : accionEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxDeleteAccion,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCION_ELIMINADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para editar una accion con ajax y promesa **/
function editarAccionAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var accionEntity = {
      idAccion : $("input[name=idAccion]").val(),
      nombreAccion : $("#nombreAccion").val(),
      descripAccion : $("#descripcionAccion").val(),
      borradoAccion : 0
    }
    
    var data = {
      usuario : getCookie('usuario'),
      accion : accionEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxEditAccion,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCION_MODIFICADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/*Función que comprueba los permisos del usuario sobre la accion*/
async function cargarPermisosFuncAccion(){
  await cargarPermisosFuncAccionAjaxPromesa()
  .then((res) => {
    gestionarPermisosAccion(res.data);
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}


/** Función para recuperar las acciones con ajax y promesas **/
function cargarAccionesAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaAccion),
      tamanhoPagina : tamanhoPaginaAccion
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoAcciones,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCIONES_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar las acciones eliminadas con ajax y promesas*/
function buscarEliminadosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaAccion),
      tamanhoPagina : tamanhoPaginaAccion
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoAccionesEliminadas,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCIONES_ELIMINADAS_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para ver en detalle una accion con ajax y promesas*/
function detalleAccionAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var data = {
      nombreAccion : $('#nombreAccion').val(),
      descripAccion : $('#descripAccion').val(),
      inicio : 0,
      tamanhoPagina : 1
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarAccion,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCION_ENCONTRADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


/**Función para reactivar una acción con ajax y promesas*/
function reactivarAccionesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var accionEntity = {
      idAccion : $("input[name=idAccion]").val(),
      nombreAccion : $('#nombreAccion').val(),
      descripAccion : $('#descripcionAccion').val(),
      borradoAccion : 0
    }

    var data = {
      usuario: getCookie('usuario'),
      accion : accionEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxReactivarAccion,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCION_REACTIVADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/* Función para obtener las acciones del sistema */
async function cargarAcciones(numeroPagina, tamanhoPagina, paginadorCreado){
	await cargarAccionesAjaxPromesa(numeroPagina, tamanhoPagina)
	  .then((res) => {
	  	
      var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	  	var textPaginacion = parseInt(res.data.inicio)+1 + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
      }else{
        $('#itemPaginacion').attr('hidden',false);
      }
	   	
      $("#datosAccion").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('ACCION', res.data.listaBusquedas[i]);
    			$("#datosAccion").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({ACCION_DESCRIPTION_COLUMN:2});
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
      	setLang(getCookie('lang'));

        if(paginadorCreado != 'PaginadorCreado'){
            paginador(totalResults, 'cargarAcciones', 'ACCION');
        }

        if(numeroPagina == 0){
          $('#' + (numeroPagina+1)).addClass("active");
          var numPagCookie =  numeroPagina + 1;
        }else{
          $('#' + numeroPagina).addClass("active");
          var numPagCookie =  numeroPagina;
        }
	  
        setCookie('numeroPagina', numPagCookie);

		}).catch((res) => {
		    respuestaAjaxKO(res.code);
		    document.getElementById("modal").style.display = "block";
		});
}

/** Funcion añadir accion **/
async function addAccion(){
  await anadirAccionAjaxPromesa()
  .then((res) => {
    
    $("#form-modal").modal('toggle');
    respuestaAjaxOK("ACCION_GUARDADA_OK", res.code);

    let idElementoList = ["nombreAccion", "descripcionAccion"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    
    $('#nombreAccion').val(getCookie('nombreAccion'));
    $('#descripcionAccion').val(getCookie('descripAccion'));
    buscarAccion(getCookie('numeroPagina'), tamanhoPaginaAccion, 'buscarPaginacion', 'PaginadorNo');

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreAccion", "descripcionAccion"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}


/** Funcion buscar accion **/
async function buscarAccion(numeroPagina, tamanhoPagina, accion, paginadorCreado){
  await buscarAccionAjaxPromesa(numeroPagina, tamanhoPagina,accion)
  .then((res) => {
      cargarPermisosFuncAccion();
      if($('#form-modal').is(':visible')) {
         $("#form-modal").modal('toggle');
      };
      guardarParametrosBusqueda(res.data.datosBusqueda);
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

      $("#datosAccion").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('ACCION', res.data.listaBusquedas[i]);
          $("#datosAccion").append(tr);
        }
      
      var div = createHideShowColumnsWindow({ACCION_DESCRIPTION_COLUMN:2});
      
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      if(paginadorCreado != 'PaginadorCreado'){
        paginador(totalResults, 'buscarAccion', 'ACCION');
      }

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
        var numPagCookie = numeroPagina+1;
      }else{
        $('#' + numeroPagina).addClass("active");
        var numPagCookie = numeroPagina;
      }
      setCookie('numeroPagina', numPagCookie);

  
  }).catch((res) => {
      cargarPermisosFuncAccion();
      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreAccion", "descripcionAccion"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/*Función que refresca la tabla por si hay algún cambio en BD */
async function refrescarTabla(numeroPagina, tamanhoPagina){
  await cargarAccionesAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncAccion();
      setCookie('nombreAccion', '');
      setCookie('descripcionAccion', '');
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
      
      $("#datosAccion").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('ACCION', res.data.listaBusquedas[i]);
          $("#datosAccion").append(tr);
        }
      
      var div = createHideShowColumnsWindow({ACCION_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('nombreAccion', '');
      setCookie('descripAccion', '');

      paginador(totalResults, 'cargarAcciones', 'ACCION');

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
        var numPagCookie = numeroPagina + 1 ;
      }else{
        $('#' + numeroPagina).addClass("active");
         var numPagCookie = numeroPagina;
      }

      setCookie('numeroPagina', numPagCookie);
    
    }).catch((res) => {
      
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/*Función que busca los eliminados de la tabla de rol*/
async function buscarEliminados(numeroPagina, tamanhoPagina, paginadorCreado){
  await buscarEliminadosAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncAccion();
      var numResults = res.data.numResultados + '';
      var totalResults = res.data.tamanhoTotal + '';
      var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
        $('#itemPaginacion').attr('hidden', true);
      }else{
        inicio = parseInt(res.data.inicio)+1;
        $('#itemPaginacion').attr('hidden', false);
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;
     
      

      $("#datosAccion").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFilaEliminados('ACCION', res.data.listaBusquedas[i]);
          $("#datosAccion").append(tr);
        }

        if(res.data.listaBusquedas.length == 0){
          $('.cabecera').attr('hidden', true);
          $('.cabeceraEliminados').attr('hidden', false);
        }
      
      var div = createHideShowColumnsWindow({ACCION_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('nombreAccion', '');
      setCookie('descripAccion', '');

      if(paginadorCreado != 'PaginadorCreado'){
        paginador(totalResults, 'buscarEliminadosAccion', 'ACCION');
      }

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
      }else{
        $('#' + numeroPagina).addClass("active");
      }
    
    }).catch((res) => {
      
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  
  });
}

/** Función que visualiza una accion **/
async function detalleAccion(){
  await detalleAccionAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    let idElementoList = ["nombreAccion", "descripcionAccion"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    $('#nombreAccion').val(getCookie('nombreAccion'));
    $('#descripcionAccion').val(getCookie('descripAccion'));

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreAccion", "descripcionAccion"];
      resetearFormulario("formularioGenerico", idElementoList);
      
      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/** Función que edita una accion **/
async function editAccion(){
  await editarAccionAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("ACCION_EDITADA_OK", res.code);

    let idElementoList = ["nombreAccion", "descripcionAccion"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    $('#nombreAccion').val(getCookie('nombreAccion'));
    $('#descripcionAccion').val(getCookie('descripAccion'));
    buscarAccion(getCookie('numeroPagina'), tamanhoPaginaAccion, 'buscarPaginacion', 'PaginadorCreado');

  }).catch((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxKO(res.code);

    let idElementoList = ["nombreAccion", "descripcionAccion"];
    resetearFormulario("formularioGenerico", idElementoList);

    setLang(getCookie('lang'));

    document.getElementById("modal").style.display = "block";


  });
}

/** Función que elimina una accion **/
async function deleteAccion(){
  await eliminarAccionAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("ACCION_ELIMINADA_OK", res.code);

    let idElementoList = ["nombreAccion", "descripcionAccion"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
   
    refrescarTabla(0, tamanhoPaginaAccion);

  }).catch((res) => {
     
     $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreAccion", "descripcionAccion"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";


  });
}

/*Función que reactiva los eliminados de la tabla de acciones*/
async function reactivarAccion(){
  await reactivarAccionesAjaxPromesa()
  .then((res) => {

    cargarPermisosFuncAccion();

    $("#form-modal").modal('toggle');

    respuestaAjaxOK("ACCION_REACTIVADA_OK", res.code);

    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
      
    buscarEliminados(0, tamanhoPaginaAccion, 'PaginadorNo');
    
    }).catch((res) => {
      $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/** Funcion para mostrar el formulario para añadir una acción **/
function showAddAcciones() {
  var idioma = getCookie('lang');
  cambiarFormulario('ADD_ACCION', 'javascript:addAccion();', 'return comprobarAddAccion();');
  cambiarOnBlurCampos('return comprobarNombreAccion(\'nombreAccion\', \'errorFormatoNombreAccion\', \'nombreAccion\')', 
  'return comprobarDescripcionAccion(\'descripcionAccion\', \'errorFormatoDescripcionAccion\', \'descripcionAccion\')');
  cambiarIcono('images/add.png', 'ICONO_ADD', 'iconoAddAccion', 'Añadir');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelAccionName').attr('hidden', true);
  $('#labelAccionDescription').attr('hidden', true);

  let campos = ["nombreAccion", "descripcionAccion"];
  let obligatorios = ["obligatorioAccionName", "obligatorioAccionDescription"];
  eliminarReadonly(campos);
  mostrarObligatorios(obligatorios);
  habilitaCampos(campos);

}

/** Funcion para buscar una accion **/
function showBuscarAccion() {
  var idioma = getCookie('lang');

  cambiarFormulario('SEARCH_ACCION', 'javascript:buscarAccion(0,' + tamanhoPaginaAccion + ', \'buscarModal\'' + ', \'PaginadorNo\');', 'return comprobarBuscarAccion();');
  cambiarOnBlurCampos('return comprobarNombreAccionSearch(\'nombreAccion\', \'errorFormatoNombreAccion\', \'nombreAccion\')', 
  'return comprobarDescripcionAccionSearch(\'descripcionAccion\', \'errorFormatoDescripcionAccion\', \'descripcionAccion\')');
  cambiarIcono('images/search.png', 'ICONO_SEARCH', 'iconoSearchAccion', 'Buscar');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelAccionName').attr('hidden', true);
  $('#labelAccionDescription').attr('hidden', true);

  let campos = ["nombreAccion", "descripcionAccion"];
  let obligatorios = ["obligatorioAccionName", "obligatorioAccionDescription"];
  eliminarReadonly(campos);
  ocultarObligatorios(obligatorios);
  habilitaCampos(campos);

}

/** Funcion para visualizar una accion **/
function showDetalle(nombreAccion, descripAccion) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DETAIL_ACTION', 'javascript:detalleAccion();', '');
    cambiarIcono('images/close2.png', 'ICONO_CERRAR', 'iconoCerrar', 'Detalle');
   
    setLang(idioma);
    
    $('#labelAccionName').removeAttr('hidden');
    $('#labelAccionDescription').removeAttr('hidden');
    $('#subtitulo').attr('hidden', '');

    rellenarFormulario(nombreAccion, descripAccion);

    let campos = ["nombreAccion", "descripcionAccion"];
    let obligatorios = ["obligatorioAccionName", "obligatorioAccionDescription"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Funcion para editar una accion **/
function showEditar(nombreAccion, descripAccion, idAccion) {
  var idioma = getCookie('lang');

    cambiarFormulario('EDIT_ACCION', 'javascript:editAccion();', 'return comprobarEditAccion();');
    cambiarOnBlurCampos('return comprobarNombreAccion(\'nombreAccion\', \'errorFormatoNombreAccion\', \'nombreAccion\')', 
      'return comprobarDescripcionAccion(\'descripcionAccion\', \'errorFormatoDescripcionAccion\', \'descripcionAccion\')');
    cambiarIcono('images/edit.png', 'ICONO_EDIT', 'iconoEditarAccion', 'Editar');
   
    setLang(idioma);
    
    $('#subtitulo').attr('hidden', true);
    $('#labelAccionName').attr('hidden', true);
    $('#labelAccionDescription').attr('hidden', true);

    rellenarFormulario(nombreAccion, descripAccion);
    insertacampo(document.formularioGenerico,'idAccion', idAccion);

    let campos = ["nombreAccion", "descripcionAccion"];
    let obligatorios = ["obligatorioAccionName", "obligatorioAccionDescription"];
    eliminarReadonly(campos);
    mostrarObligatorios(obligatorios);
    habilitaCampos(campos);
    deshabilitaCampos(["nombreAccion"]);
    anadirReadonly(["nombreAccion"]);
}

/** Función para eliminar una accion **/
function showEliminar(nombreAccion, descripAccion, idAccion) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DELETE_ACCION', 'javascript:deleteAccion();', '');
    cambiarIcono('images/delete.png', 'ICONO_ELIMINAR', 'iconoEliminar', 'Eliminar');
   
    setLang(idioma);
    
    $('#labelAccionName').removeAttr('hidden');
    $('#labelAccionDescription').removeAttr('hidden');
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_ELIMINAR_ACCION');
    $('#subtitulo').attr('hidden', false);
    

    rellenarFormulario(nombreAccion, descripAccion);
    insertacampo(document.formularioGenerico,'idAccion', idAccion);

    let campos = ["nombreAccion", "descripcionAccion"];
    let obligatorios = ["obligatorioAccionName", "obligatorioAccionDescription"];
    eliminarReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Función para reactivar una accion **/
function showReactivar(nombreAccion, descripAccion , idAccion) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('REACTIVATE_ACCION', 'javascript:reactivarAccion();', '');
    cambiarIcono('images/reactivar2.png', 'ICONO_REACTIVAR', 'iconoReactivar', 'Reactivar');
   
    setLang(idioma);
    
    $('#labelAccionName').removeAttr('hidden');
    $('#labelAccionDescription').removeAttr('hidden');
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_REACTIVAR_ACCION');
    $('#subtitulo').attr('hidden', false);
    

    rellenarFormulario(nombreAccion, descripAccion);
    insertacampo(document.formularioGenerico,'idAccion', idAccion);

    let campos = ["nombreAccion", "descripcionAccion"];
    let obligatorios = ["obligatorioAccionName", "obligatorioAccionDescription"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/**Función para cambiar onBlur de los campos*/
function cambiarOnBlurCampos(onBlurNombreAccion, onBlurDescripcionAccion) {
    
    if (onBlurNombreAccion != ''){
        $("#nombreAccion").attr('onblur', onBlurNombreAccion);
    }

    if (onBlurDescripcionAccion != ''){
        $("#descripcionAccion").attr('onblur', onBlurDescripcionAccion);
    }
}

/**Función que rellenado los datos del formulario*/
function rellenarFormulario(nombreAccion, descripAccion) {

    $("#nombreAccion").val(nombreAccion);
    $("#descripcionAccion").val(descripAccion); 

}

/** Función para gestionar los iconos dependiendo de los permisos de los usuarios **/
function gestionarPermisosAccion(idElementoList) {
  idElementoList.forEach( function (idElemento) {
    switch(idElemento){
      case "Añadir":
        $('#btnAddAccion').attr('src', 'images/add3.png');
        $('#btnAddAccion').css("cursor", "pointer");
        $('#divAddAccion').attr("data-toggle", "modal");
        $('#divAddAccion').attr("data-target", "#form-modal");
      break;

      case "Modificar" : 
        $('.editarPermiso').attr('src', 'images/edit3.png');
        $('.editarPermiso').css("cursor", "pointer");
        $('.editarPermiso').attr("data-toggle", "modal");
        $('.editarPermiso').attr("data-target", "#form-modal");
      break;

      case "Eliminar" :
        $('.eliminarPermiso').attr('src', 'images/delete3.png');
        $('.eliminarPermiso').css("cursor", "pointer");
        $('.eliminarPermiso').attr("data-toggle", "modal");
        $('.eliminarPermiso').attr("data-target", "#form-modal");
      break;

      case 'Listar' :
        $('#btnListarAcciones').attr('src', 'images/search3.png');
        $('#btnSearchDelete').attr('src', 'images/searchDelete3.png');
        $('#btnListarAcciones').css("cursor", "pointer");
        $('.iconoSearchDelete').css("cursor", "pointer");
        $('#divSearchDelete').attr("onclick", "javascript:buscarEliminados(0,\'tamanhoPaginaAccion\')");
        $('#divListarAcciones').attr("data-toggle", "modal");
        $('#divListarAcciones').attr("data-target", "#form-modal");
      break;
      case "Visualizar" :
        $('.detallePermiso').attr('src', 'images/detail3.png');
        $('.detallePermiso').css("cursor", "pointer");
        $('.detallePermiso').attr("data-toggle", "modal");
        $('.detallePermiso').attr("data-target", "#form-modal");
      break;

      case "Reactivar" : 
        $('.reactivarPermiso').attr('src', 'images/reactivar.png');
        $('.reactivarPermiso').css("cursor", "pointer");
        $('.reactivarPermiso').attr("data-toggle", "modal");
        $('.reactivarPermiso').attr("data-target", "#form-modal");
      break;

    } 
    }); 
}

function permisosUsuarios(){

  var funcionalidades = [];
  var acciones = [];

  $('#tablaDatos').html('');

  $('#itemPaginacion').attr('hidden', true);

  var tabla = '<table class="table table-bordered" id="tablaPermisosAcciones">' + 
                '<thead id ="encabezadoPermisos">' + 
                '</thead>' + 
                '<tbody id="cuerpoPermisos">' +
                '</tbody>' + 
              '</table>';

  $('#encabezadoPermisos').html('');
  $('#cuerpoPermisos').html('');

  var data={
    inicio : 0,
    tamanhoPagina: 100
  }

  var token = getCookie('tokenUsuario');

  $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoRoles,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(function( response ) {       
        if (response.code == 'ROLES_LISTADOS') {
          $('#encabezadoPermisos').append('<th></th>');
          for (var i = 0; i < response.data.listaBusquedas.length; i++){
            var th = response.data.listaBusquedas[i].rolName;
            $('#encabezadoPermisos').append('<th>' + th + '</th>');
          }
        } else { 
            respuestaAjaxKO(response.code);    
        }              
        
      });

  $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoFuncionalidades,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(function( response ) {     
        for(var i = 0; i<response.data.listaBusquedas.length; i++){
          funcionalidades.push(response.data.listaBusquedas[i].nombreFuncionalidad);
        }  
      });

  $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoAcciones,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
  }).done(function( response ) {       
        for(var i = 0; i<response.data.listaBusquedas.length; i++){
          acciones.push(response.data.listaBusquedas[i].nombreAccion);
        } 
        var elementosConcatenados = []; 
        for(var i=0; i<funcionalidades.length; i++){
          for(var j = 0; j<acciones.length; j++){
            var concatenado = funcionalidades[i] + "/" + acciones[j];
          }
        }

        $('#cuerpoPermisos').append('<tr><th>')

        for(var i = 0; i<elementosConcatenados.length; i++){

        }

  });



$('#tablaDatos').append(tabla);



}

$(document).ready(function() {
  $("#form-modal").on('hidden.bs.modal', function() {
    
    let idElementoErrorList = ["errorFormatoNombreAccion", "errorFormatoDescripcionAccion"];
    
    let idElementoList = ["nombreAccion", "descripcionAccion"];

    limpiarFormulario(idElementoList);
    eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
    setLang(getCookie('lang'));
  });

});