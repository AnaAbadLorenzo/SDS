/** Funcion para obtener la informacion del usuario **/
async function cargarUsuarios(numeroPagina, tamanhoPagina, paginadorCreado){
	if(getCookie('rolUsuario') == "usuario" || getCookie('rolUsuario') == 'gestor'){
		await buscarUsuarioAjaxPromesa(numeroPagina, tamanhoPaginaUsuario, "buscarInfo")
		.then((res) => {
		
			$("#cardUsuario").attr('hidden', false);
			$('#infoAdmin').attr('hidden', true);
			$("#cardUsuario").html('');

			for(var i = 0; i<res.data.listaBusquedas.length; i++){
				if(res.data.listaBusquedas[i].usuario == getCookie('usuario')){
					var datosUsuario = cargaInformacion(res.data.listaBusquedas[i]);
				}
			}

			$("#cardUsuario").html(datosUsuario);

		  }).catch((res) => {
		      respuestaAjaxKO(res.code);
			  setLang(getCookie('lang'));

		});
	}else if(getCookie('rolUsuario') == "admin"){
		await cargarUsuariosAjaxPromesa(numeroPagina, tamanhoPagina)
		.then((res) => {
		
			$("#cardUsuario").attr('hidden', true);
			$('#infoAdmin').attr('hidden', false);

			var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	    var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
      }else{
        inicio = parseInt(res.data.inicio)+1;
      }
	   	var textPaginacion = inicio +  " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults; 

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
        document.getElementById("itemPaginacion").style.display = "none";
      }else{
        $('#itemPaginacion').attr('hidden',false);
        document.getElementById("itemPaginacion").style.display = "block";
      }

      $("#datosUsuarios").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('USUARIO', res.data.listaBusquedas[i]);
    			$("#datosUsuarios").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({DNI_COLUMN:1,ACTIVO_COLUMN:3,ROL_COLUMN:4});
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
      	setLang(getCookie('lang'));

        if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'cargarUsuarios', 'USUARIO');
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
}

/*Función que busca los eliminados de la tabla de usuario*/
async function buscarEliminadosUsuario(numeroPagina, tamanhoPagina, paginadorCreado){
  await buscarEliminadosUsuarioAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncUsuario();
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

      if(res.data.listaBusquedas.length == 0){
        document.getElementById('cabecera').style.display = "none";
        document.getElementById('cabeceraEliminados').style.display = "block";
      }
      
      $("#datosUsuarios").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFilaEliminados('USUARIO', res.data.listaBusquedas[i]);
          $("#datosUsuarios").append(tr);
        }

      var div = createHideShowColumnsWindow({DNI_COLUMN:1,ACTIVO_COLUMN:3,ROL_COLUMN:4});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('usuarioBuscar', '');
      setCookie('rol', '');

      if(paginadorCreado != 'PaginadorCreado'){
         paginador(totalResults, 'buscarEliminadosUsuario', 'USUARIO');
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

/*Función que refresca la tabla por si hay algún cambio en BD */
async function refrescarTablaUsuario(numeroPagina, tamanhoPagina){
  await cargarUsuariosAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncUsuario();
      setCookie('usuarioBuscar', '');
      setCookie('rol', '');
      var numResults = res.data.numResultados + '';
      var totalResults = res.data.tamanhoTotal + '';
      var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
        document.getElementById('itemPaginacion').style.display = "none";
      }else{
        inicio = parseInt(res.data.inicio)+1;
        document.getElementById('itemPaginacion').style.display = "block";
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;
      
      document.getElementById('cabecera').style.display = "block";
      document.getElementById('cabeceraEliminados').style.display = "none";
      
      $("#datosUsuarios").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('USUARIO', res.data.listaBusquedas[i]);
          $("#datosUsuarios").append(tr);
        }
      
      var div = createHideShowColumnsWindow({DNI_COLUMN:1,ACTIVO_COLUMN:3,ROL_COLUMN:4});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('usuarioBuscar', '');
      setCookie('rol', '');

      paginador(totalResults, 'cargarUsuarios', 'USUARIO');

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
        var numPagCookie = numeroPagina + 1 ;
      }else{
        $('#' + numeroPagina).addClass("active");
         var numPagCookie = numeroPagina;
      }

      setCookie('numeroPagina', numPagCookie);
      comprobarOcultos();
    
    }).catch((res) => {
      
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}
/**Funcion para buscar un usuario **/
function buscarUsuarioAjaxPromesa(numeroPagina, tamanhoPagina, accion){
	return new Promise(function(resolve, reject) {
		var rol ="";
    var token = getCookie('tokenUsuario');

     if(accion == "buscarModal"){
     	rol = escogeRol($("#selectRoles").val());
     	
      if(rol == ""){
         var rolUser = {
          idRol : 0,
          rolName : "",
          rolDescription : "",
          borradoRol : ""
        };
      }else{
        rolUser = rol;
      }

      var data = {
          dniUsuario : $('#dniUsuario').val(),
          usuario : $('#loginUsuario').val(),
        	rol : rolUser,
        	inicio : calculaInicio(numeroPagina, tamanhoPaginaUsuario),
        	tamanhoPagina : tamanhoPaginaUsuario
        }
    
    }else if(accion == "buscarPaginacion"){
      if(getCookie('dniUsuario') == null || getCookie('dniUsuario') == ""){
        var dniU = "";
      }else{
        var dniU = getCookie('dniUsuario');
      }
    	
      if(getCookie('usuarioBuscar') == null || getCookie('usuarioBuscar') == ""){
        var usuarioBuscar = "";
      }else{
        var usuarioBuscar = getCookie('usuarioBuscar');
      }

      if(getCookie('rol') == null || getCookie('rol') == ""){
        var rolUser = {
          idRol : 0,
          rolName : "",
          rolDescription : "",
          borradoRol : ""
        };

      }else{
      	var rol = escogeRol(getCookie('rol'));
        var rolUser = rol;
      }

      var data = {
          dniUsuario : dniU,
          usuario : usuarioBuscar,
        	rol : rolUser,
        	inicio : calculaInicio(numeroPagina, tamanhoPaginaUsuario),
        	tamanhoPagina : tamanhoPaginaUsuario
        }
    }else if(accion == "buscarInfo"){
    	if(getCookie('rolUsuario') == "admin"){
					rol = {
				    	idRol : 1,
				    	rolName : getCookie('rolUsuario'),
				    	rolDescription : "Contendrá a todos los administradores de la aplicación",
				    	borradoRol : 0
			    	}
			    }
			    
    if(getCookie('rolUsuario') == "usuario"){
				rol = {
			    	idRol : 2,
			    	rolName : getCookie('rolUsuario'),
			    	rolDescription : "Contendrá a todos los usuarios de la aplicación",
			    	borradoRol : 0
		    	}
		    }

    if(getCookie('rolUsuario') == "gestor"){
        rol = {
            idRol : 3,
            rolName : getCookie('rolUsuario'),
            rolDescription : "Contendrá a todos los gestores de la aplicación",
            borradoRol : 0
          }
        }

	    var data = {
          dniUsuario : '',
	        usuario : getCookie('usuario'),
	        rol : rol,
	        inicio : 0,
	        tamanhoPagina : 1
	    }
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarUsuario,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'USUARIOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los usuarios eliminados con ajax y promesas*/
function buscarEliminadosUsuarioAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaUsuario),
      tamanhoPagina : tamanhoPaginaUsuario
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarTodosUsuariosEliminados,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'USUARIOS_ELIMINADOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

function editarRolUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var rolEntity = escogeRol($('#selectRoles').val());

    var usuarioEntity = {
      dniUsuario : $('#dniUsuario').val(),
      usuario : $('#loginUsuario').val(),
      passwdUsuario : "",
      borradoUsuario : $('#borradoUsuario').val()
    }

    var usuario = {
      usuario : getCookie('usuario'),
      usuarioEntity : usuarioEntity
    }

    var data = {
      rolEntity : rolEntity,
      usuario : usuario
    }

    $.ajax({
        method: "POST",
        url: urlPeticionAjaxEditarRolUsuario,
        contentType : "application/json",
        data: JSON.stringify(data),  
        dataType : 'json',
        headers: {'Authorization': token},
        }).done(res => {
          if (res.code != 'ROL_USUARIO_MODIFICADO_OK') {
            reject(res);
          }
          resolve(res);
        }).fail( function( jqXHR ) {
          errorFailAjax(jqXHR.status);
        });
    });

}

/**Función para obetener los usuarios con ajax y promesas*/
function cargarUsuariosAjaxPromesa(numeroPagina, tamanhoPagina){
		return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var data = {
        inicio: calculaInicio(numeroPagina, tamanhoPaginaUsuario),
        tamanhoPagina : tamanhoPaginaUsuario
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarTodosUsuarios,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'USUARIOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


/**Función para reactivar un usuario con ajax y promesas*/
function reactivarUsuariosAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
     var usuarioEntity = {
      dniUsuario : $('#dniUsuario').val(),
      usuario : $('#loginUsuario').val(),
      passwdUsuario : "",
      borradoUsuario : 1
    }

    var data = {
      usuario: getCookie('usuario'),
      usuarioEntity : usuarioEntity
    };

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxReactivarUsuario,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'USUARIO_REACTIVADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Funcion buscar accion **/
async function buscarUsuario(numeroPagina, tamanhoPagina, accion, paginadorCreado){
  await buscarUsuarioAjaxPromesa(numeroPagina, tamanhoPagina,accion)
  .then((res) => {
      cargarPermisosFuncUsuario();
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
      var textPaginacion = inicio +  " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults; 

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
      }else{
        $('#itemPaginacion').attr('hidden',false);
      }

      $("#datosUsuarios").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('USUARIO', res.data.listaBusquedas[i]);
          $("#datosUsuarios").append(tr);
        }
      
      var div = createHideShowColumnsWindow({DNI_COLUMN:1,ACTIVO_COLUMN:3,ROL_COLUMN:4});
      
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      if(paginadorCreado != 'PaginadorCreado'){
        paginador(totalResults, 'buscarUsuario', 'USUARIO');
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
      cargarPermisosFuncUsuario();
      respuestaAjaxKO(res.code);

      let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/** Función que edita un rol **/
async function editRolUsuario(){
  await editarRolUsuarioAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("ROL_USUARIO_EDITADO_OK", res.code);

    var loginUsuario = $('#loginUsuario').val();

    let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    
    $('#loginUsuario').val(getCookie('usuarioBuscar'));

    var rolAntiguo = getCookie('rolUsuario');

    if(loginUsuario == getCookie('usuario')){
      setCookie('rolUsuario', res.data);
    }

   var options = document.getElementById('selectRoles').options;

    for(var i = 0; i<options.length; i++){
      var text = options[i].text;
      if(options[i].text == getCookie('rol')){
        options[i].selected = true;
      }else{
        options[i].selected = false;
      }
    }

    if(rolAntiguo == "admin" && getCookie('rolUsuario') == "usuario" && loginUsuario == getCookie('usuario')){
      window.location.reload(true);

    }else if(rolAntiguo == "gestor" && getCookie('rolUsuario') == "usuario" && loginUsuario == getCookie('usuario')){
      window.location.reload(true);
    }else{
       buscarUsuario(getCookie('numeroPagina'), tamanhoPaginaUsuario, 'buscarPaginacion', 'PaginadorCreado');
    }

  }).catch((res) => {
    $("#form-modal").modal('toggle');

     respuestaAjaxKO(res.code);

    let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    resetearFormulario("formularioGenerico", idElementoList);

    setLang(getCookie('lang'));

    document.getElementById("modal").style.display = "block";


  });
}

/** Función que elimina una funcionalidad **/
async function deleteUser(){
  await eliminarUsuarioAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("USER_ELIMINADO_OK", res.code);

   let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
   
    refrescarTablaUsuario(0, tamanhoPaginaUsuario);

  }).catch((res) => {
     
     $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);

     let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";


  });
}

/*Función que reactiva los eliminados de la tabla de usuarios*/
async function reactivarUsuario(){
  await reactivarUsuariosAjaxPromesa()
  .then((res) => {

    cargarPermisosFuncUsuario();

    $("#form-modal").modal('toggle');

    respuestaAjaxOK("USUARIO_REACTIVADO_OK", res.code);

    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
      
    buscarEliminadosUsuario(0, tamanhoPaginaUsuario, 'PaginadorNo');
    
    }).catch((res) => {
      $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/*Función que comprueba los permisos del usuario sobre la accion*/
async function cargarPermisosFuncUsuario(){
  await cargarPermisosFuncUsuarioAjaxPromesa()
  .then((res) => {
    gestionarPermisosUsuario(res.data);
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/** Función que visualiza un usuario **/
async function detalleUsuario(){
  await detalleUsuarioAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    $('#loginUsuario').val(getCookie('usuario'));
    
    var options = document.getElementById('selectRoles').options;

    for(var i = 0; i<options.length; i++){
      var text = options[i].text;
      if(options[i].text == getCookie('rolName')){
        options[i].selected = true;
      }else{
        options[i].selected = false;
      }
    }

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";

  });
}

/** Función para ver el detalle de un usuario con ajax y promesa **/
function detalleUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var rol = escogeRol($('#selectRoles').val());
   
    var data = {
          usuario : $('#loginUsuario').val(),
          rol : rol,
          inicio : 0,
          tamanhoPagina : 1
    }
    
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarUsuario,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'USUARIOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para recuperar los permisos de un usuario sobre el usuario **/
function cargarPermisosFuncUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxAccionesUsuario,
      contentType : "application/json",
      data: { usuario : usuario, funcionalidad : 'Gestión de usuarios'},  
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

/** Función eliminar usuario **/
function eliminarUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuarioEntity = {
      dniUsuario : $('#dniUsuario').val(),
      usuario : $('#loginUsuario').val(),
      passwdUsuario : "",
      borradoUsuario : 1
    }

    var data = {
      usuario: getCookie('usuario'),
      usuarioEntity : usuarioEntity
    };
  
     $.ajax({
      method: "POST",
      url: urlPeticionAjaxEliminarUsuario,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'USUARIO_ELIMINADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Funcion para buscar una accion **/
function showBuscarUsuario() {
	construyeSelect();
  var idioma = getCookie('lang');

  cambiarFormulario('SEARCH_USUARIO', 'javascript:buscarUsuario(0,' + tamanhoPaginaUsuario + ', \'buscarModal\'' + ', \'PaginadorNo\');', 'return comprobarBuscarUsuario();');
  cambiarOnBlurCampos('return comprobarDNISearch(\'dniUsuario\', \'errorFormatoDni\', \'dniPersona\')',
    'return comprobarUserSearch(\'loginUsuario\', \'errorFormatoLoginUsuario\', \'loginUsuario\')', '');
  cambiarIcono('images/search.png', 'ICONO_SEARCH', 'iconoSearchUsuario', 'Buscar');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelLoginUsuario').attr('hidden', true);
  $('#labelDNI').attr('hidden', true);
  $('#dniUsuario').attr('hidden', false);
  $('#labelActivo').attr('hidden', true);
  $('#esActivo').attr('hidden', true);


  let campos = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
  let obligatorios = ["obligatorioDNI", "obligatorioLoginUsuario", "obligatorioRolName", "obligatorioActivoUsuario"];
  
  ocultarObligatorios(obligatorios);
  eliminarReadonly(campos);
  habilitaCampos(campos);

}

/** Funcion para editar un rol **/
function showEditar(dniUsuario,usuario,borrado,rol) {

    var idioma = getCookie('lang');

    cambiarFormulario('EDIT_ROL_USER', 'javascript:editRolUsuario();', 'return comprobarEditRolUsuario();');
    cambiarOnBlurCampos('return comprobarDNI(\'dniUsuario\', \'errorFormatoDni\', \'dniPersona\');', 
      'return comprobarUser(\'loginUsuario\', \'errorFormatoLoginUsuario\', \'loginUsuario\')',
      'return comprobarRolUser(\'selectRoles\', \'errorFormatoRol\', \'rolUsuario\')');
    cambiarIcono('images/edit.png', 'ICONO_EDIT', 'iconoEditarRolUsuario', 'Editar');
   
    setLang(idioma);
    
    $('#subtitulo').attr('hidden', true);
    $('#labelLoginUsuario').attr('hidden', true);
    $('#labelDNI').attr('hidden', true);
    $('#dniUsuario').attr('hidden', false);
    $('#labelActivo').attr('hidden', true);
    $('#esActivo').attr('hidden', false);
    $('#labelRolName').attr('hidden', true);
    $('#selectRoles').removeAttr('readonly');

    rellenarFormulario(dniUsuario,usuario,borrado,rol);

    let campos = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    let obligatorios = ["obligatorioDNI", "obligatorioLoginUsuario", "obligatorioRolName", "obligatorioActivoUsuario"];
    
    anadirReadonly(campos);
    deshabilitaCampos(["dniUsuario", "loginUsuario", "esActivo"]);
    habilitaCampos(["selectRoles"]);
    mostrarObligatorios(obligatorios);

}

/** Función para eliminar una funcionalidad **/
function showEliminar(dniUsuario, usuario,borrado,rol) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DELETE_USER', 'javascript:deleteUser();', '');
    cambiarIcono('images/delete.png', 'ICONO_ELIMINAR', 'iconoEliminar', 'Eliminar');
   
    setLang(idioma);

    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_ELIMINAR_USUARIO');
    $('#subtitulo').removeAttr('hidden');
    $('#labelLoginUsuario').attr('hidden', false);
    $('#labelDNI').attr('hidden', false);
    $('#dniUsuario').attr('hidden', false);
    $('#labelActivo').attr('hidden', false);
    $('#esActivo').attr('hidden', false);
    $('#labelRolName').attr('hidden', false);

    rellenarFormulario(dniUsuario, usuario,borrado,rol);

    let campos = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    let obligatorios = ["obligatorioDNI", "obligatorioLoginUsuario", "obligatorioRolName", "obligatorioActivoUsuario"];
    ocultarObligatorios(obligatorios);
    anadirReadonly(campos);
    deshabilitaCampos(campos);

}

/** Función para reactivar un usuario **/
function showReactivar(dniUsuario, usuario,borrado,rol) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('REACTIVATE_USUARIO', 'javascript:reactivarUsuario();', '');
    cambiarIcono('images/reactivar2.png', 'ICONO_REACTIVAR', 'iconoReactivar', 'Reactivar');
   
    setLang(idioma);
    
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_REACTIVAR_USUARIO');
    $('#subtitulo').removeAttr('hidden');
    $('#labelLoginUsuario').attr('hidden', false);
    $('#labelDNI').attr('hidden', false);
    $('#dniUsuario').attr('hidden', false);
    $('#labelActivo').attr('hidden', false);
    $('#esActivo').attr('hidden', false);
    $('#labelRolName').attr('hidden', false);
    

    rellenarFormulario(dniUsuario, usuario,borrado,rol);

    let campos = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    let obligatorios = ["obligatorioDNI", "obligatorioLoginUsuario", "obligatorioRolName", "obligatorioActivoUsuario"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Función para ver el detalle de  un rol **/
function showDetalle(dniUsuario, usuario,borrado,rol) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DETAIL_USER', 'javascript:detalleUsuario();', '');
    cambiarIcono('images/close2.png', 'ICONO_CERRAR', 'iconoCerrar', 'Detalle');
   
    setLang(idioma);
    
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_ELIMINAR_USUARIO');
    $('#subtitulo').removeAttr('hidden');
    $('#labelLoginUsuario').attr('hidden', false);
    $('#labelDNI').attr('hidden', false);
    $('#dniUsuario').attr('hidden', false);
    $('#labelActivo').attr('hidden', false);
    $('#esActivo').attr('hidden', false);
    $('#labelRolName').attr('hidden', false);

    rellenarFormulario(dniUsuario, usuario,borrado,rol);

     let campos = ["dniUsuario", "loginUsuario", "selectRoles", "esActivo"];
    let obligatorios = ["obligatorioDNI", "obligatorioLoginUsuario", "obligatorioRolName", "obligatorioActivoUsuario"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}



/** Función para gestionar los iconos dependiendo de los permisos de los usuarios **/
function gestionarPermisosUsuario(idElementoList) {
  idElementoList.forEach( function (idElemento) {
    switch(idElemento){
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
        $('#btnListarUsuarios').attr('src', 'images/search3.png');
        $('#btnSearchDelete').attr('src', 'images/searchDelete3.png');
        $('#btnListarUsuarios').css("cursor", "pointer");
        $('.iconoSearchDelete').css("cursor", "pointer");
        $('#divSearchDelete').attr("onclick", "javascript:buscarEliminadosUsuario(0,\'tamanhoPaginaUsuario\')");
        $('#divListarUsuario').attr("data-toggle", "modal");
        $('#divListarUsuario').attr("data-target", "#form-modal");
        if(getCookie('rolUsuario') == "admin"){
          $('#infoAdmin').attr('hidden', false);
          document.getElementById('cabecera').style.display = "block";
          document.getElementById('tablaDatos').style.display = "block";
          document.getElementById('filasTabla').style.display = "block";
          $('#itemPaginacion').attr('hidden', false);

          if(document.getElementById('cabeceraEliminados').style.display == "block"){
            document.getElementById('cabecera').style.display = "none";

           var texto = document.getElementById('paginacion').innerHTML;
           if(texto == "0 - 0 total 0"){
            $('#itemPaginacion').attr('hidden', true);
           }
        }
      }
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

/** Funcion para visualizar la información del usuario **/
function cargaInformacion(usuario){
	var usuarioHTML = "";

	usuarioHTML = '<img class="card-img-top" src="images/user.png" alt="Card image">' +
					'<div class="card-body">' + 
						'<div class="userInfo">' + 
						'<img class="userImg" src="images/user.png" alt="usuario">' +
						'<h4 class="card-title user">' + usuario.usuario + '</h4>' +
					'</div>' + 
					'<div class="dniInfo">' + 
		      			'<img class="dniImg" src="images/dni.png" alt="dni">' + 
		      			'<p class="card-text dni">' + usuario.dniUsuario + '</p>' + 
	      			'</div>' + 
	      			'<div class="passInfo">' + 
	      				'<img class="passImg" src="images/pass.png" alt="pass">' + 
	      				'<p class="card-text pass">' + convertirPass(usuario.passwdUsuario) +  '</p>' + 
	      			'</div>' + 
	      			'<div class="rolInfo">' + 
	      				'<img class="rolImg" src="images/rol.png" alt="rol">' + 
	      				'<p class="card-text rol">' + usuario.rol.rolName + '</p>' + 
	      			'</div>';

	return usuarioHTML;

}

/** Función para construír el select **/
function construyeSelect(){
	var options = "";
	
	$('#selectRoles').html('');

	 var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxObtenerTodos,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ROLES_LISTADOS') {
        	respuestaAjaxKO(res.code);
        }
        options = '<option selected value=0><label class="OPCION_DEFECTO_ROL">Selecciona el rol</label></option>';
        for(var i = 0; i< res.data.length ; i++){
					options += '<option value=' + res.data[i].idRol + '>' + res.data[i].rolName + '</option>';
				}

				$('#selectRoles').append(options);
    		
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
}

/**Funcion para montar los datos del rol **/
function escogeRol(rolId){
	var rol = "";

	switch(rolId){
		case "1":
			rol = {
				idRol : 1,
	    	rolName : "admin",
	    	rolDescription : "Contendrá a todos los administradores de la aplicación",
	    	borradoRol : 0
			}
		break;
		case "2": 
			rol = {
				idRol : 2,
	    	rolName : "usuario",
	    	rolDescription : "Contendrá a todos los usuarios de la aplicación",
	    	borradoRol : 0
			}
		break;
		case "3":
			rol = {
				idRol : 3,
	    	rolName : "Gestor",
	    	rolDescription : "Contendrá a todos los gestores de planes y procedimientos de la aplicación",
	    	borradoRol : 0
			}
		break;

	}

	return rol;
}

/**Función que rellenado los datos del formulario*/
function rellenarFormulario(dniUsuario,usuario,borrado,rol) {

    $('#dniUsuario').val(dniUsuario);

    $("#loginUsuario").val(usuario);

    var options = document.getElementById('selectRoles').options;

    for(var i = 0; i<options.length; i++){
      var text = options[i].text;
      if(options[i].text == rol){
        options[i].selected = true;
      }else{

        options[i].selected = false;
      }
    }

    if(borrado == 0){
      $('#esActivo').val('Sí');
    }else{
      $('#esActivo').val('No');
    }

}

/**Función para cambiar onBlur de los campos*/
function cambiarOnBlurCampos(onBlurNombreUsuario, onBlurRol) {
    
    if (onBlurNombreUsuario != ''){
        $("#loginUsuario").attr('onblur', onBlurNombreUsuario);
    }
    if (onBlurRol != ''){
        $("selectRoles").attr('onblur', onBlurRol);
    }
}
