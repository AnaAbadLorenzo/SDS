/**Función para recuperar los roles con ajax y promesas*/
function cargarRolesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

    var data = {
      inicio : 0,
      tamanhoPagina : 16,
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoRoles,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROLES_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para recuperar un rol en base a parámetros con ajax y promesas*/
function buscarRolAjaxPromesa(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

    var data = {
      rolName : $('#nombreRol').val(),
      rolDescription : $('#descripcionRol').val(),
      inicio:0,
      tamanhoPagina:16
    }

    setCookie('nombreRol', $('#nombreRol').val());
    setCookie('descripcionRol', $('#descripcionRol').val());
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarRol,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROL_ENCONTRADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para anadir un rol un rol con ajax y promesas*/
function anadirRolAjaxPromesa(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

  	var rol = {
  		idRol : "",
  		rolName : $('#nombreRol').val(),
      rolDescription : $('#descripcionRol').val(),
      borradoRol : 0
  	}
    
    var data = {
    	usuario : getCookie('usuario'),
      rol : rol
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxAddRol,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROL_GUARDADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para editar un rol un rol con ajax y promesas*/
function editarRolAjaxPromesa(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

  	var rol = {
  		idRol : $("input[name=idRol]").val(),
  		rolName : $('#nombreRol').val(),
      rolDescription : $('#descripcionRol').val(),
      borradoRol : 0
  	}
    
    var data = {
    	usuario : getCookie('usuario'),
      rol : rol
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxEditRol,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROL_MODIFICADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para eliminar un rol un rol con ajax y promesas*/
function eliminarRolAjaxPromesa(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

  	var rol = {
  		idRol : $("input[name=idRol]").val(),
  		rolName : $('#nombreRol').val(),
      rolDescription : $('#descripcionRol').val(),
      borradoRol : 1
  	}
    
    var data = {
    	usuario : getCookie('usuario'),
      rol : rol
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxDeleteRol,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROL_ELIMINADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para ver en detalle un rol un rol con ajax y promesas*/
function detalleRolAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var data = {
      rolName : $('#nombreRol').val(),
      rolDescription : $('#descripcionRol').val(),
      inicio : 0,
      tamanhoPagina : 1
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarRol,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROL_ENCONTRADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/* Función para obtener los roles del sistema */
async function cargarRoles(){
	await cargarRolesAjaxPromesa()
	  .then((res) => {
	  	var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	  	var textPaginacion = "1 - " + numResults  + " de " + totalResults 
	   	$("#datosRol").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('ROL', res.data.listaBusquedas[i]);
    			$("#datosRol").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({ROL_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));
	  
	  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);
	    setLang(getCookie('lang'));
	    document.getElementById("modal").style.display = "block";
	});
}

/*Función que refresca la tabla por si hay algún cambio en BD */
async function refrescarTabla(){
	await cargarRolesAjaxPromesa()
	.then((res) => {
	  	var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	  	var textPaginacion = "1 - " + numResults  + " de " + totalResults 
	   	$("#datosRol").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('ROL', res.data.listaBusquedas[i]);
    			$("#datosRol").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({ROL_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));
	  
	  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);
	    setLang(getCookie('lang'));
	    document.getElementById("modal").style.display = "block";
	});
}

/** Función que buscar un rol **/
async function buscarRol(){
	await buscarRolAjaxPromesa()
	  .then((res) => {
	  	var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	  	var textPaginacion = "1 - " + numResults  + " de " + totalResults

	   	$("#datosRol").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('ROL', res.data.listaBusquedas[i]);
    			$("#datosRol").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({ROL_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));
	  
	  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);
	    setLang(getCookie('lang'));
	    document.getElementById("modal").style.display = "block";
	});
}

/** Función que añade un rol **/
async function addRol(){
	await anadirRolAjaxPromesa()
	.then((res) => {
		$("#form-modal").modal('toggle');

    let idElementoList = ["nombreRol", "descripcionRol"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    refrescarTabla();

  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);

	    let idElementoList = ["nombreRol", "descripcionRol"];
    	resetearFormulario("formularioGenerico", idElementoList);

	    setLang(getCookie('lang'));

	    document.getElementById("modal").style.display = "block";


	});
}

/** Función que añade un rol **/
async function editRol(){
	await editarRolAjaxPromesa()
	.then((res) => {
		$("#form-modal").modal('toggle');
	  $(".imagenAviso").attr('src', 'images/ok.png');
    document.getElementById("modal-title").style.color = "#238f2a";
    document.getElementById("modal-title").style.top = "3%";
    $("#modal-title").removeClass();
    $("#modal-title").addClass("ROL_EDITADO_OK");
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass(res.code);

    let idElementoList = ["nombreRol", "descripcionRol"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    refrescarTabla();

  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);

	    let idElementoList = ["nombreRol", "descripcionRol"];
    	resetearFormulario("formularioGenerico", idElementoList);

	    setLang(getCookie('lang'));

	    document.getElementById("modal").style.display = "block";


	});
}

/** Función que añade un rol **/
async function deleteRol(){
	await eliminarRolAjaxPromesa()
	.then((res) => {
		$("#form-modal").modal('toggle');
	  $(".imagenAviso").attr('src', 'images/ok.png');
    document.getElementById("modal-title").style.color = "#238f2a";
    document.getElementById("modal-title").style.top = "3%";
    $("#modal-title").removeClass();
    $("#modal-title").addClass("ROL_ELIMINADO_OK");
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass(res.code);

    let idElementoList = ["nombreRol", "descripcionRol"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    refrescarTabla();

  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);

	    let idElementoList = ["nombreRol", "descripcionRol"];
    	resetearFormulario("formularioGenerico", idElementoList);

	    setLang(getCookie('lang'));

	    document.getElementById("modal").style.display = "block";


	});
}

/** Función que añade un rol **/
async function detalleRol(){
  await detalleRolAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    let idElementoList = ["nombreRol", "descripcionRol"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    refrescarTabla();

  }).catch((res) => {
      $("#modal-title").removeClass();
      $("#modal-title").addClass("ERROR");
      document.getElementById("modal-title").style.color = "#a50707";
      $(".imagenAviso").attr('src', 'images/failed.png');
      $("#mensajeError").removeClass();
      $("#mensajeError").addClass(res.code);

      let idElementoList = ["nombreRol", "descripcionRol"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";


  });
}


/** Funcion para buscar un rol **/
function showBuscarRol() {
	var idioma = getCookie('lang');

	cambiarFormulario('SEARCH_ROL', 'javascript:buscarRol();', 'return comprobarBuscarRol();');
	cambiarOnBlurCampos('return comprobarNombreRolSearch(\'nombreRol\', \'errorFormatoNombreRol\', \'nombreRol\')', 
	'return comprobarDescripcionRolSearch(\'descripcionRol\', \'errorFormatoDescripcionRol\', \'descripcionRol\')');
	cambiarIcono('images/search.png', 'ICONO_SEARCH', 'iconoSearchRol', 'Buscar');
	setLang(idioma);

	$('#subtitulo').attr('hidden', true);
	$('#labelRolName').attr('hidden', true);
	$('#labelRolDescription').attr('hidden', true);

}

/** Funcion para añadir un rol **/
function showAddRol() {
	var idioma = getCookie('lang');
	cambiarFormulario('ADD_ROL', 'javascript:addRol();', 'return comprobarAddRol();');
	cambiarOnBlurCampos('return comprobarNombreRol(\'nombreRol\', \'errorFormatoNombreRol\', \'nombreRol\')', 
	'return comprobarDescripcionRol(\'descripcionRol\', \'errorFormatoDescripcionRol\', \'descripcionRol\')');
	cambiarIcono('images/add.png', 'ICONO_ADD', 'iconoAddRol', 'Añadir');
	setLang(idioma);

	$('#subtitulo').attr('hidden', true);
	$('#labelRolName').attr('hidden', true);
	$('#labelRolDescription').attr('hidden', true);

}

/** Funcion para editar un rol **/
function showEditar(rolName, rolDescription, idRol) {
	var idioma = getCookie('lang');

    cambiarFormulario('EDIT_ROLE', 'javascript:editRol();', 'return comprobarEditRol();');
    cambiarOnBlurCampos('return comprobarNombreRol(\'nombreRol\', \'errorFormatoNombreRol\', \'nombreRol\')',
    	'return comprobarDescripcionRol(\'descripcionRol\', \'errorFormatoDescripcionRol\', \'descripcionRol\')');
    cambiarIcono('images/edit.png', 'ICONO_EDIT', 'iconoEditarRol', 'Editar');
   
    setLang(idioma);
    
    $('#subtitulo').attr('hidden', true);
    $('#labelRolName').attr('hidden', true);
		$('#labelRolDescription').attr('hidden', true);

    rellenarFormulario(rolName, rolDescription);
    insertacampo(document.formularioGenerico,'idRol', idRol);

}

/** Función para eliminar un rol **/
function showEliminar(rolName, rolDescription, idRol) {
	
		var idioma = getCookie('lang');

    cambiarFormulario('DELETE_ROLE', 'javascript:deleteRol();', '');
    cambiarIcono('images/delete.png', 'ICONO_ELIMINAR', 'iconoEliminar', 'Eliminar');
   
    setLang(idioma);
    
    $('#labelRolName').removeAttr('hidden');
    $('#labelRolDescription').removeAttr('hidden');
    $('#subtitulo').removeAttr('hidden');

    rellenarFormulario(rolName, rolDescription);
    insertacampo(document.formularioGenerico,'idRol', idRol);

    let campos = ["nombreRol", "descripcionRol"];
    deshabilitaCampos(campos);

}

/** Función para eliminar un rol **/
function showDetalle(rolName, rolDescription) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DETAIL_ROLE', 'javascript:detalleRol();', '');
    cambiarIcono('images/close2.png', 'ICONO_CERRAR', 'iconoCerrar', 'Detalle');
   
    setLang(idioma);
    
    $('#labelRolName').removeAttr('hidden');
    $('#labelRolDescription').removeAttr('hidden');
    $('#subtitulo').attr('hidden', '');

    rellenarFormulario(rolName, rolDescription);

    let campos = ["nombreRol", "descripcionRol"];
    deshabilitaCampos(campos);

}

/**Función para cambiar onBlur de los campos*/
function cambiarOnBlurCampos(onBlurNombreRol, onBlurDescripcionRol) {
    
    if (onBlurNombreRol != ''){
        $("#nombreRol").attr('onblur', onBlurNombreRol);
    }

    if (onBlurDescripcionRol != ''){
        $("#descripcionRol").attr('onblur', onBlurDescripcionRol);
    }
}

/**Función que rellenado los datos del formulario*/
function rellenarFormulario(rolName, rolDescription, idRol) {

    $("#nombreRol").val(rolName);
    $("#descripcionRol").val(rolDescription); 

}

$(document).ready(function() {
	$("#form-modal").on('hidden.bs.modal', function() {
		
		let idElementoErrorList = ["errorFormatoNombreRol", "errorFormatoDescripcionRol"];
		
		let idElementoList = ["nombreRol", "descripcionRol"];

		limpiarFormulario(idElementoList);
		eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
		setLang(getCookie('lang'));
	});

});