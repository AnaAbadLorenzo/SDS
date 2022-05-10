/** Función para añadir funcionalidades con ajax y promesas **/
function anadirFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var funcionalidadEntity = {
      idFuncionalidad : "",
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 0
    }

    var data = {
      usuario : getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxAddFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_GUARDADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para añadir funcionalidades con ajax y promesas **/
function buscarFuncionalidadAjaxPromesa(numeroPagina, tamanhoPagina, accion){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    if(accion == "buscarModal"){
      var data = {
        nombreFuncionalidad : $('#nombreFuncionalidad').val(),
        descripFuncionalidad : $('#descripcionFuncionalidad').val(),
        inicio : calculaInicio(numeroPagina, tamanhoPaginaFuncionalidad),
        tamanhoPagina : tamanhoPaginaFuncionalidad 
      }
    }

    if(accion == "buscarPaginacion"){
      if(getCookie('nombreFuncionalidad') == null || getCookie('nombreFuncionalidad') == ""){
        var nombreFunc = "";
      }else{
        var nombreFunc = getCookie('nombreFuncionalidad');
      }

      if(getCookie('descripFuncionalidad') == null || getCookie('descripFuncionalidad') == ""){
        var descripFunc = "";
      }else{
        var descripFunc = getCookie('descripFuncionalidad');
      }

      var data = {
        nombreFuncionalidad : nombreFunc,
        descripFuncionalidad : descripFunc,
        inicio : calculaInicio(numeroPagina, tamanhoPaginaFuncionalidad),
        tamanhoPagina : tamanhoPaginaFuncionalidad 
      }
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_ENCONTRADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para recuperar los permisos de un usuario sobre la funcionalidad **/
function cargarPermisosFuncFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxAccionesUsuario,
      contentType : "application/json",
      data: { usuario : usuario, funcionalidad : 'Gestión de funcionalidades'},  
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

/**Función para editar una funcionalidad con ajax y promesas*/
function editarFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var funcionalidadEntity = {
      idFuncionalidad : $("input[name=idFuncionalidad]").val(),
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 0
    }
    
    var data = {
      usuario : getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxEditFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_MODIFICADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para eliminar un rol un rol con ajax y promesas*/
function eliminarFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var funcionalidadEntity = {
      idFuncionalidad : $("input[name=idFuncionalidad]").val(),
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 1
    }
    
    var data = {
      usuario : getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxDeleteFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_ELIMINADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/*Función que comprueba los permisos del usuario sobre la funcionalidad*/
async function cargarPermisosFuncFuncionalidad(){
  await cargarPermisosFuncFuncionalidadAjaxPromesa()
  .then((res) => {
    gestionarPermisosFuncionalidad(res.data);
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}


/** Función para recuperar las funcionalidades con ajax y promesas **/
function cargarFuncionalidadesAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaFuncionalidad),
      tamanhoPagina : tamanhoPaginaFuncionalidad
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoFuncionalidades,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDADES_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar las funcionalidades eliminadas con ajax y promesas*/
function buscarEliminadosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaFuncionalidad),
      tamanhoPagina : tamanhoPaginaFuncionalidad
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoFuncionalidadesEliminadas,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDADES_ELIMINADAS_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para ver en detalle una funcionalidad con ajax y promesas*/
function detalleFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var data = {
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripFuncionalidad').val(),
      inicio : 0,
      tamanhoPagina : 1
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_ENCONTRADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


/**Función para reactivar una funcionalidad con ajax y promesas*/
function reactivarFuncionalidadesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var funcionalidadEntity = {
      idFuncionalidad : $("input[name=idFuncionalidad]").val(),
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 0
    }

    var data = {
      usuario: getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxReactivarFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_REACTIVADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/* Función para obtener las funcionalidades del sistema */
async function cargarFuncionalidades(numeroPagina, tamanhoPagina, paginadorCreado){
	await cargarFuncionalidadesAjaxPromesa(numeroPagina, tamanhoPagina)
	  .then((res) => {
	  	
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
	   	
      $("#datosFuncionalidad").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('FUNCIONALIDAD', res.data.listaBusquedas[i]);
    			$("#datosFuncionalidad").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({FUNCIONALIDAD_DESCRIPTION_COLUMN:2});
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
      	setLang(getCookie('lang'));

        if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'cargarFuncionalidades', 'FUNCIONALIDAD');
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
		    respuestaAjaxKO(res.code);
		    document.getElementById("modal").style.display = "block";
		});
}

/** Funcion añadir funcionalidad **/
async function addFuncionalidad(){
  await anadirFuncionalidadAjaxPromesa()
  .then((res) => {
    
    $("#form-modal").modal('toggle');
    respuestaAjaxOK("FUNCIONALIDAD_GUARDADA_OK", res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    
    $('#nombreFuncionalidad').val(getCookie('nombreFuncionalidad'));
    $('#descripcionFuncionalidad').val(getCookie('descripFuncionalidad'));
    buscarFuncionalidad(getCookie('numeroPagina'), tamanhoPaginaFuncionalidad, 'buscarPaginacion', 'PaginadorNo');

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}


/** Funcion buscar funcionalidad **/
async function buscarFuncionalidad(numeroPagina, tamanhoPagina, accion, paginadorCreado){
  await buscarFuncionalidadAjaxPromesa(numeroPagina, tamanhoPagina,accion)
  .then((res) => {
      cargarPermisosFuncFuncionalidad();
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

      $("#datosFuncionalidad").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('FUNCIONALIDAD', res.data.listaBusquedas[i]);
          $("#datosFuncionalidad").append(tr);
        }
      
      var div = createHideShowColumnsWindow({FUNCIONALIDAD_DESCRIPTION_COLUMN:2});
      
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'buscarFuncionalidad', 'FUNCIONALIDAD');
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
      cargarPermisosFuncFuncionalidad();
      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/*Función que refresca la tabla por si hay algún cambio en BD */
async function refrescarTabla(numeroPagina, tamanhoPagina){
  await cargarFuncionalidadesAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncFuncionalidad();
      setCookie('nombreFuncionalidad', '');
      setCookie('descripFuncionalidad', '');
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
      
      $("#datosFuncionalidad").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('FUNCIONALIDAD', res.data.listaBusquedas[i]);
          $("#datosFuncionalidad").append(tr);
        }
      
      var div = createHideShowColumnsWindow({FUNCIONALIDAD_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('nombreFuncionalidad', '');
      setCookie('descripFuncionalidad', '');

      paginador(totalResults, 'cargarFuncionalidades', 'FUNCIONALIDAD');

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
      cargarPermisosFuncFuncionalidad();
      var numResults = res.data.numResultados + '';
      var totalResults = res.data.tamanhoTotal + '';
      var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
        $('#itemPaginacion').attr('hidden', true);
      }else{
        inicio = parseInt(res.data.inicio)+1;
        $('#itemPaginacion').attr('hidden', true);
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
      }else{
        $('#itemPaginacion').attr('hidden',false);
      }
      
      if(res.data.listaBusquedas.length == 0){
          $('.cabecera').attr('hidden', true);
          $('.cabeceraEliminados').attr('hidden', false);
      }

      $("#datosFuncionalidad").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFilaEliminados('FUNCIONALIDAD', res.data.listaBusquedas[i]);
          $("#datosFuncionalidad").append(tr);
        }
      
      var div = createHideShowColumnsWindow({FUNCIONALIDAD_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('nombreFuncionalidad', '');
      setCookie('descripFuncionalidad', '');

      if(paginadorCreado != 'PaginadorCreado'){
         paginador(totalResults, 'buscarEliminadosFuncionalidad', 'FUNCIONALIDAD');
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

/** Función que visualiza una funcionalidad **/
async function detalleFuncionalidad(){
  await detalleFuncionalidadAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    $('#nombreFuncionalidad').val(getCookie('nombreFuncionalidad'));
    $('#descripcionFuncionalidad').val(getCookie('descripFuncionalidad'));

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
      resetearFormulario("formularioGenerico", idElementoList);
      
      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/** Función que edita un rol **/
async function editFuncionalidad(){
  await editarFuncionalidadAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("FUNCIONALIDAD_EDITADA_OK", res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    $('#nombreFuncionalidad').val(getCookie('nombreFuncionalidad'));
    $('#descripcionFuncionalidad').val(getCookie('descripFuncionalidad'));
    buscarFuncionalidad(getCookie('numeroPagina'), tamanhoPaginaFuncionalidad, 'buscarPaginacion', 'PaginadorCreado');

  }).catch((res) => {
    $("#form-modal").modal('toggle');

     respuestaAjaxKO(res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);

    setLang(getCookie('lang'));

    document.getElementById("modal").style.display = "block";


  });
}

/** Función que elimina una funcionalidad **/
async function deleteFuncionalidad(){
  await eliminarFuncionalidadAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("FUNCIONALIDAD_ELIMINADA_OK", res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
   
    refrescarTabla(0, tamanhoPaginaFuncionalidad);

  }).catch((res) => {
     
     $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";


  });
}

/*Función que reactiva los eliminados de la tabla de funcionalidades*/
async function reactivarFuncionalidad(){
  await reactivarFuncionalidadesAjaxPromesa()
  .then((res) => {

    cargarPermisosFuncFuncionalidad();

    $("#form-modal").modal('toggle');

    respuestaAjaxOK("FUNCIONALIDAD_REACTIVADA_OK", res.code);

    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
      
    buscarEliminados(0, tamanhoPaginaFuncionalidad, 'PaginadorNo');
    
    }).catch((res) => {
      $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/** Funcion para mostrar el formulario para añadir una funcionalidad **/
function showAddFuncionalidades() {
  var idioma = getCookie('lang');
  cambiarFormulario('ADD_FUNCIONALIDAD', 'javascript:addFuncionalidad();', 'return comprobarAddFuncionalidad();');
  cambiarOnBlurCampos('return comprobarNombreFuncionalidad(\'nombreFuncionalidad\', \'errorFormatoNombreFuncionalidad\', \'nombreFuncionalidad\')', 
  'return comprobarDescripcionFuncionalidad(\'descripcionFuncionalidad\', \'errorFormatoDescripcionFuncionalidad\', \'descripcionFuncionalidad\')');
  cambiarIcono('images/add.png', 'ICONO_ADD', 'iconoAddFuncionalidad', 'Añadir');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelFuncionalidadName').attr('hidden', true);
  $('#labelFuncionalidadDescription').attr('hidden', true);

  let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
  let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
  eliminarReadonly(campos);
  mostrarObligatorios(obligatorios);
  habilitaCampos(campos);

}

/** Funcion para buscar una funcionalidad **/
function showBuscarFuncionalidad() {
  var idioma = getCookie('lang');

  cambiarFormulario('SEARCH_FUNCIONALIDAD', 'javascript:buscarFuncionalidad(0,' + tamanhoPaginaFuncionalidad + ', \'buscarModal\'' + ',\'PaginadorNo\');', 'return comprobarBuscarFuncionalidad();');
  cambiarOnBlurCampos('return comprobarNombreFuncionalidadSearch(\'nombreFuncionalidad\', \'errorFormatoNombreFuncionalidad\', \'nombreFuncionalidad\')', 
  'return comprobarDescripcionFuncionalidadSearch(\'descripcionFuncionalidad\', \'errorFormatoDescripcionFuncionalidad\', \'descripcionFuncionalidad\')');
  cambiarIcono('images/search.png', 'ICONO_SEARCH', 'iconoSearchFuncionalidad', 'Buscar');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelFuncionalidadName').attr('hidden', true);
  $('#labelFuncionalidadDescription').attr('hidden', true);

  let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
  let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
  eliminarReadonly(campos);
  ocultarObligatorios(obligatorios);
  habilitaCampos(campos);

}

/** Funcion para visualizar una funcionalidad **/
function showDetalle(nombreFuncionalidad, descripFuncionalidad) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DETAIL_FUNCIONALITY', 'javascript:detalleFuncionalidad();', '');
    cambiarIcono('images/close2.png', 'ICONO_CERRAR', 'iconoCerrar', 'Detalle');
   
    setLang(idioma);
    
    $('#labelFuncionalidadName').removeAttr('hidden');
    $('#labelFuncionalidadDescription').removeAttr('hidden');
    $('#subtitulo').attr('hidden', '');

    rellenarFormulario(nombreFuncionalidad, descripFuncionalidad);

    let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Funcion para editar una empresa **/
function showEditar(cifEmpresa, nombreEmpresa , direccionEmpresa, telefonoEmpresa, idEmpresa) {
  var idioma = getCookie('lang');

    cambiarFormulario('EDIT_ENTERPRISE', 'javascript:editEmpresa();', 'return comprobarEditEmpresa  ();');
    cambiarOnBlurCampos('return comprobarNombreFuncionalidad(\'nombreFuncionalidad\', \'errorFormatoNombreFuncionalidad\', \'nombreFuncionalidad\')', 
      'return comprobarDescripcionFuncionalidad(\'descripcionFuncionalidad\', \'errorFormatoDescripcionFuncionalidad\', \'descripcionFuncionalidad\')');
    cambiarIcono('images/edit.png', 'ICONO_EDIT', 'iconoEditarFuncionalidad', 'Editar');
   
    setLang(idioma);
    
    $('#subtitulo').attr('hidden', true);
    $('#labelFuncionalidadName').attr('hidden', true);
    $('#labelFuncionalidadDescription').attr('hidden', true);

    rellenarFormulario(nombreFuncionalidad, descripFuncionalidad);
    insertacampo(document.formularioGenerico,'idFuncionalidad', idFuncionalidad);

    let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
    eliminarReadonly(campos);
    mostrarObligatorios(obligatorios);
    habilitaCampos(campos);
    deshabilitaCampos(["nombreFuncionalidad"]);
    anadirReadonly(["nombreFuncionalidad"]);

}

/** Función para eliminar una empresa **/
function showEliminar(cifEmpresa, nombreEmpresa , direccionEmpresa, telefonoEmpresa, idEmpresa) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DELETE_EMPRESA', 'javascript:deleteEmpresa();', '');
    cambiarIcono('images/delete.png', 'ICONO_ELIMINAR', 'iconoEliminar', 'Eliminar');
   
    setLang(idioma);
    
     $('#labelCifEmpresa').removeAttr('hidden');
    $('#labelNombreEmpresa').removeAttr('hidden');
    $('#labelDireccionEmpresa').removeAttr('hidden');
    $('#labelTelefonoEmpresa').removeAttr('hidden');
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_ELIMINAR_EMPRESA');
    $('#subtitulo').attr('hidden', false);
    
    rellenarFormulario(cifEmpresa, nombreEmpresa , direccionEmpresa, telefonoEmpresa);
    insertacampo(document.formularioGenerico,'idEmpresa', idEmpresa);

    let campos = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    let obligatorios = ["errorFormatoCifEmpresa", "errorFormatoNombreEmpresa", "errorFormatoDireccionEmpresa", 
                        "errorFormatoTelefonoEmpresa"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Función para reactivar una empresa **/
function showReactivar(cifEmpresa, nombreEmpresa , direccionEmpresa, telefonoEmpresa, idEmpresa) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('REACTIVATE_ENTERPRISE', 'javascript:reactivarEmpresa();', '');
    cambiarIcono('images/reactivar2.png', 'ICONO_REACTIVAR', 'iconoReactivar', 'Reactivar');
   
    setLang(idioma);
    
    $('#labelCifEmpresa').removeAttr('hidden');
    $('#labelNombreEmpresa').removeAttr('hidden');
    $('#labelDireccionEmpresa').removeAttr('hidden');
    $('#labelTelefonoEmpresa').removeAttr('hidden');
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_REACTIVAR_EMPRESA');
    $('#subtitulo').attr('hidden', false);
    

    rellenarFormulario(cifEmpresa, nombreEmpresa , direccionEmpresa, telefonoEmpresa);
    insertacampo(document.formularioGenerico,'idEmpresa', idEmpresa);

    let campos = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    let obligatorios = ["errorFormatoCifEmpresa", "errorFormatoNombreEmpresa", "errorFormatoDireccionEmpresa", "errorFormatoTelefonoEmpresa"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/**Función para cambiar onBlur de los campos*/
function cambiarOnBlurCampos(onBlurCifEmpresa, onBlurNombreEmpresa, onBlurDireccionEmpresa, onBlurTelefonoEmpresa) {
    
    if (onBlurCifEmpresa != ''){
        $("#cifEmpresa").attr('onblur', onBlurCifEmpresa);
    }

    if (onBlurNombreEmpresa != ''){
        $("#nombreEmpresa").attr('onblur', onBlurNombreEmpresa);
    }

    if (onBlurDireccionEmpresa != ''){
        $("#direccionEmpresa").attr('onblur', onBlurDireccionEmpresa);
    }

    if (onBlurTelefonoEmpresa != ''){
        $("#telefonoEmpresa").attr('onblur', onBlurTelefonoEmpresa);
    }
}

/**Función que rellenado los datos del formulario*/
function rellenarFormulario(cifEmpresa, nombreEmpresa, direccionEmpresa, telefonoEmpresa) {

    $("#cifEmpresa").val(cifEmpresa);
    $("#nombreEmpresa").val(nombreEmpresa); 
    $("#direccionEmpresa").val(direccionEmpresa);
    $("#telefonoEmpresa").val(telefonoEmpresa);

}

/** Función para gestionar los iconos dependiendo de los permisos de los usuarios **/
function gestionarPermisosEmpresa(idElementoList) {
  idElementoList.forEach( function (idElemento) {
    switch(idElemento){
      case "Añadir":
        $('#btnAddEmpresa').attr('src', 'images/add3.png');
        $('#btnAddEmpresa').css("cursor", "pointer");
        $('#divAddEmpresa').attr("data-toggle", "modal");
        $('#divAddEmpresa').attr("data-target", "#form-modal");
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
        $('#btnListarEmpresas').attr('src', 'images/search3.png');
        $('#btnSearchDelete').attr('src', 'images/searchDelete3.png');
        $('#btnListarEmpresas').css("cursor", "pointer");
        $('.iconoSearchDelete').css("cursor", "pointer");
        $('#divSearchDelete').attr("onclick", "javascript:buscarEliminados(0,\'tamanhoPaginaEmpresa\', \'PaginadorNo\')");
        $('#divListarEmpresas').attr("data-toggle", "modal");
        $('#divListarEmpresas').attr("data-target", "#form-modal");

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

$(document).ready(function() {
  $("#form-modal").on('hidden.bs.modal', function() {
    
    let idElementoErrorList = ["errorFormatoCifEmpresa", "errorFormatoNombreEmpresa", "errorFormatoDireccionEmpresa", "errorFormatoTelefonoEmpresa"];
    
    let idElementoList = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];

    limpiarFormulario(idElementoList);
    eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
    setLang(getCookie('lang'));
  });

});