/**Función para recuperar los test de atributo de login con ajax y promesas*/
function testLoginAtributos(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestLoginAtributos,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_LOGIN_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de recuperar pass con ajax y promesas*/
function testRecuperarPassAtributos(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRecuperarPassAtributos,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_RECUPERARPASS_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de registrar con ajax y promesas*/
function testRegistrarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRegistrarAtributos,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_REGISTRO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de añadir rol con ajax y promesas*/
function testRolGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTO_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar rol con ajax y promesas*/
function testRolBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTO_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar rol con ajax y promesas*/
function testRolModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTO_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de añadir funcionalidad con ajax y promesas*/
function testFuncionalidadGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar rol con ajax y promesas*/
function testFuncionalidadBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar funcionalidad con ajax y promesas*/
function testFuncionalidadModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de añadir accion con ajax y promesas*/
function testAccionGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar accion con ajax y promesas*/
function testAccionBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar accion con ajax y promesas*/
function testAccionModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar rol usuario con ajax y promesas*/
function testUsuarioModificarRolUsuarioAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAtributosAccionModificarRolUsuario,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar usuario con ajax y promesas*/
function testUsuarioBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de login con ajax y promesas*/
function testLoginAcciones(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestLoginAcciones,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_LOGIN_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de recuperar pass con ajax y promesas*/
function testRecuperarPassAcciones(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRecuperarPassAcciones,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_RECUPERARPASS_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de registrar con ajax y promesas*/
function testRegistrarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRegistrarAcciones,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_REGISTRO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de guardar rol con ajax y promesas*/
function testRolGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar rol con ajax y promesas*/
function testRolEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar rol con ajax y promesas*/
function testRolModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar rol con ajax y promesas*/
function testRolBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de reactivar rol con ajax y promesas*/
function testRolReactivarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRolAccionReactivar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_ROL_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de guardar rol con ajax y promesas*/
function testFuncionalidadGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar rol con ajax y promesas*/
function testFuncionalidadEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar funcionalidad con ajax y promesas*/
function testFuncionalidadModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar funcionalidad con ajax y promesas*/
function testFuncionalidadBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar funcionalidad con ajax y promesas*/
function testFuncionalidadReactivarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestFuncionalidadAccionReactivar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_FUNCIONALIDAD_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de añadir acción con ajax y promesas*/
function testAccionGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar acción con ajax y promesas*/
function testAccionModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar acción con ajax y promesas*/
function testAccionEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar acción con ajax y promesas*/
function testAccionBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de reactivar acción con ajax y promesas*/
function testAccionReactivarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionReactivar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de asignar acción con ajax y promesas*/
function testAccionAsignarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionAsignar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de revocar acción con ajax y promesas*/
function testAccionRevocarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestAccionAccionRevocar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCION_ACCION_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar usuario con ajax y promesas*/
function testUsuarioBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar usuario con ajax y promesas*/
function testUsuarioEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar rol usuario con ajax y promesas*/
function testUsuarioModificarRolUsuarioAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAccionModificarRolUsuario,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar password usuario con ajax y promesas*/
function testUsuarioModificarPasswdUsuarioAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAccionModificarPasswdUsuario,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de reactivar usuario con ajax y promesas*/
function testUsuarioReactivarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestUsuarioAccionReactivar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/*Función que obtiene los test de atributos de Login */
async function testAtributosLogin(){
	await testLoginAtributos()
	.then((res) => {
		cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosLogin", "cuerpoAtributosLogin", "atributos", "", "");
    let idElementoList = ["iconoTestLogin", "iconoTestLoginAtributos", "iconoTestLoginAtributosLogin"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de atributos de Recuperar Pass */
async function testAtributosRecuperarPass(){
	await testRecuperarPassAtributos()
	.then((res) => {
		cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosRecuperarPass", "cuerpoAtributosRecuperarPass", "atributos", "", "");
    let idElementoList = ["iconoTestLogin", "iconoTestLoginAtributos", "iconoTestLoginAtributosRecuperarPass"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de atributos de Registrar */
async function testAtributosRegistrar(){
	await testRegistrarAtributos()
	.then((res) => {
		cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosRegistrar", "cuerpoAtributosRegistrar", "atributos", "", "");
    let idElementoList = ["iconoTestRegistrar", "iconoTestRegistrarAtributos"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de guardar rol de Registrar */
async function testAtributosRolGuardar(){
  await testRolGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosRolGuardar", "cuerpoAtributosRolGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestRol", "iconoTestRolAtributos", "iconoTestRolAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar rol de Registrar */
async function testAtributosRolBuscar(){
  await testRolBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosRolBuscar", "cuerpoAtributosRolBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestRol", "iconoTestRolAtributos", "iconoTestRolAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar rol de Registrar */
async function testAtributosRolModificar(){
  await testRolModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosRolModificar", "cuerpoAtributosRolModificar", "atributos", "", "");
    let idElementoList = ["iconoTestRol", "iconoTestRolAtributos", "iconoTestRolAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de guardar funcionalidad de Registrar */
async function testAtributosFuncionalidadGuardar(){
  await testFuncionalidadGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosFuncionalidadGuardar", "cuerpoAtributosFuncionalidadGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAtributos", "iconoTestFuncionalidadAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar funcionalidad de Registrar */
async function testAtributosFuncionalidadBuscar(){
  await testFuncionalidadBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosFuncionalidadGuardar", "cuerpoAtributosFuncionalidadBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAtributos", "iconoTestFuncionalidadAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar rol de Registrar */
async function testAtributosFuncionalidadModificar(){
  await testFuncionalidadModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosFuncionalidadGuardar", "cuerpoAtributosFuncionalidadModificar", "atributos", "", "");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAtributos", "iconoTestFuncionalidadAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de guardar acción de Registrar */
async function testAtributosAccionGuardar(){
  await testAccionGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosAccionGuardar", "cuerpoAtributosAccionGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAtributos", "iconoTestAccionAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar funcionalidad de Registrar */
async function testAtributosAccionBuscar(){
  await testAccionBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosAccionBuscar", "cuerpoAtributosAccionBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAtributos", "iconoTestAccionAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar rol de Registrar */
async function testAtributosAccionModificar(){
  await testAccionModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosAccionModificar", "cuerpoAtributosAccionModificar", "atributos", "", "");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAtributos", "iconoTestAccionAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar rol usuario */
async function testAtributosUsuarioModificarRolUsuario(){
  await testUsuarioModificarRolUsuarioAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosUsuarioModificarRolUsuario", "cuerpoAtributosUsuarioModificarRolUsuario", "atributos", "", "");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAtributos", "iconoTestUsuarioAtributosModificarRolUsuario"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar usuario */
async function testAtributosUsuarioBuscar(){
  await testUsuarioBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosUsuarioBuscar", "cuerpoAtributosUsuarioBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAtributos", "iconoTestUsuarioAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Login */
async function testAccionesLogin(){
	await testLoginAcciones()
	.then((res) => {
		let atributosValor = ["usuario", "passwdUsuario"];
		cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesLogin", "cuerpoAccionesLogin", "acciones", atributosValor, "Login");
    let idElementoList = ["iconoTestLogin", "iconoTestLoginAcciones", "iconoTestLoginAccionesLogin"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de acciones de Recuperar Pass */
async function testAccionesRecuperarPass(){
	await testRecuperarPassAcciones()
	.then((res) => {
		let atributosValor = ["emailP", "usuario"];
		cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRecuperarPass", "cuerpoAccionesRecuperarPass", "acciones", atributosValor, "RecuperarPass");
    let idElementoList = ["iconoTestLogin", "iconoTestLoginAcciones", "iconoTestLoginAccionesRecuperarPass"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de acciones de Registrar */
async function testAccionesRegistrar(){
	await testRegistrarAcciones()
	.then((res) => {
		let atributosValor = ["nombreP", "apellidosP", "fechaNacP", "telefonoEmpresa", "nombreEmpresa", "direccionP", "emailP", "cifEmpresa", "direccionEmpresa", "seleccionarEmpresa", "telefonoP", "usuario", "dniP", "passwdUsuario"];
		cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRegistrar", "cuerpoAccionesRegistrar", "acciones", atributosValor, "Registrar");
    let idElementoList = ["iconoTestRegistrar", "iconoTestRegistrarAcciones"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de acciones de Guardar Rol */
async function testAccionesRolGuardar(){
  await testRolGuardarAcciones()
  .then((res) => {
    let atributosValor = ["rolDescription", "rolName"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRolGuardar", "cuerpoAccionesRolGuardar", "acciones", atributosValor, "Rol");
    let idElementoList = ["iconoTestRol", "iconoTestRolAcciones", "iconoTestRolAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Eliminar Rol */
async function testAccionesRolEliminar(){
  await testRolEliminarAcciones()
  .then((res) => {
    let atributosValor = ["rolDescription", "rolName"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRolEliminar", "cuerpoAccionesRolEliminar", "acciones", atributosValor, "Rol");
    let idElementoList = ["iconoTestRol", "iconoTestRolAcciones", "iconoTestRolAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Rol */
async function testAccionesRolModificar(){
  await testRolModificarAcciones()
  .then((res) => {
    let atributosValor = ["rolDescription", "rolName"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRolModificar", "cuerpoAccionesRolModificar", "acciones", atributosValor, "Rol");
    let idElementoList = ["iconoTestRol", "iconoTestRolAcciones", "iconoTestRolAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Buscar Rol */
async function testAccionesRolBuscar(){
  await testRolBuscarAcciones()
  .then((res) => {
    let atributosValor = ["rolDescription", "rolName"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRolBuscar", "cuerpoAccionesRolBuscar", "acciones", atributosValor, "Rol");
    let idElementoList = ["iconoTestRol", "iconoTestRolAcciones", "iconoTestRolAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Reactivar Rol */
async function testAccionesRolReactivar(){
  await testRolReactivarAcciones()
  .then((res) => {
    let atributosValor = ["rolDescription", "rolName"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesRolReactivar", "cuerpoAccionesRolReactivar", "acciones", atributosValor, "Rol");
    let idElementoList = ["iconoTestRol", "iconoTestRolAcciones", "iconoTestRolAccionesReactivar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Guardar Funcionalidad */
async function testAccionesFuncionalidadGuardar(){
  await testFuncionalidadGuardarAcciones()
  .then((res) => {
    let atributosValor = ["descripFuncionalidad", "nombreFuncionalidad"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesFuncionalidadGuardar", "cuerpoAccionesFuncionalidadGuardar", "acciones", atributosValor, "Funcionalidad");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAcciones", "iconoTestFuncionalidadAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Eliminar Funcionalidad */
async function testAccionesFuncionalidadEliminar(){
  await testFuncionalidadEliminarAcciones()
  .then((res) => {
    let atributosValor = ["descripFuncionalidad", "nombreFuncionalidad"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesFuncionalidadEliminar", "cuerpoAccionesFuncionalidadEliminar", "acciones", atributosValor, "Funcionalidad");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAcciones", "iconoTestFuncionalidadAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Funcionalidad */
async function testAccionesFuncionalidadModificar(){
  await testFuncionalidadModificarAcciones()
  .then((res) => {
    let atributosValor = ["descripFuncionalidad", "nombreFuncionalidad"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesFuncionalidadModificar", "cuerpoAccionesFuncionalidadModificar", "acciones", atributosValor, "Funcionalidad");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAcciones", "iconoTestFuncionalidadAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Buscar Funcionalidad */
async function testAccionesFuncionalidadBuscar(){
  await testFuncionalidadBuscarAcciones()
  .then((res) => {
     let atributosValor = ["descripFuncionalidad", "nombreFuncionalidad"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesFuncionalidadBuscar", "cuerpoAccionesFuncionalidadBuscar", "acciones", atributosValor, "Funcionalidad");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAcciones", "iconoTestFuncionalidadAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Reactivar Funcionalidad */
async function testAccionesFuncionalidadReactivar(){
  await testFuncionalidadReactivarAcciones()
  .then((res) => {
     let atributosValor = ["descripFuncionalidad", "nombreFuncionalidad"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesFuncionalidadReactivar", "cuerpoAccionesFuncionalidadReactivar", "acciones", atributosValor, "Funcionalidad");
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidadAcciones", "iconoTestFuncionalidadAccionesReactivar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Guardar Accion */
async function testAccionesAccionGuardar(){
  await testAccionGuardarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionGuardar", "cuerpoAccionesAccionGuardar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Accion */
async function testAccionesAccionModificar(){
  await testAccionModificarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionModificar", "cuerpoAccionesAccionModificar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Eliminar Accion */
async function testAccionesAccionEliminar(){
  await testAccionEliminarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionEliminar", "cuerpoAccionesAccionEliminar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Buscar Accion */
async function testAccionesAccionBuscar(){
  await testAccionBuscarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionBuscar", "cuerpoAccionesAccionBuscar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Reactivar Accion */
async function testAccionesAccionReactivar(){
  await testAccionReactivarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionReactivar", "cuerpoAccionesAccionReactivar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesReactivar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Asignar Accion */
async function testAccionesAccionAsignar(){
  await testAccionAsignarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionAsignar", "cuerpoAccionesAccionAsignar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesAsignar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Asignar Accion */
async function testAccionesAccionRevocar(){
  await testAccionRevocarAcciones()
  .then((res) => {
     let atributosValor = ["descripAccion", "nombreAccion"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccionRevocar", "cuerpoAccionesAccionRevocar", "acciones", atributosValor, "Accion");
    let idElementoList = ["iconoTestAccion", "iconoTestAccionAcciones", "iconoTestAccionAccionesRevocar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Buscar Usuario */
async function testAccionesUsuarioBuscar(){
  await testUsuarioBuscarAcciones()
  .then((res) => {
    let atributosValor = ["dniUsuario", "usuario", "passwdUsuario"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesUsuarioBuscar", "cuerpoAccionesUsuarioBuscar", "acciones", atributosValor, "Usuario");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAcciones", "iconoTestUsuarioAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Eliminar Usuario */
async function testAccionesUsuarioEliminar(){
  await testUsuarioEliminarAcciones()
  .then((res) => {
    let atributosValor = ["dniUsuario", "usuario", "passwdUsuario"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesUsuarioEliminar", "cuerpoAccionesUsuarioEliminar", "acciones", atributosValor, "Usuario");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAcciones", "iconoTestUsuarioAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Rol Usuario */
async function testAccionesUsuarioModificarRolUsuario(){
  await testUsuarioModificarRolUsuarioAcciones()
  .then((res) => {
    let atributosValor = ["dniUsuario", "usuario", "passwdUsuario"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesUsuarioModificarRolUsuario", "cuerpoAccionesUsuarioModificarRolUsuario", "acciones", atributosValor, "Usuario");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAcciones", "iconoTestUsuarioAccionesModificarRolUsuario"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Passwd Usuario */
async function testAccionesUsuarioModificarPasswdUsuario(){
  await testUsuarioModificarPasswdUsuarioAcciones()
  .then((res) => {
    let atributosValor = ["dniUsuario", "usuario", "passwdUsuario"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesUsuarioModificarPasswdUsuario", "cuerpoAccionesUsuarioModificarPasswdUsuario", "acciones", atributosValor, "Usuario");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAcciones", "iconoTestUsuarioAccionesModificarPasswdUsuario"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Reactivar Usuario */
async function testAccionesUsuarioReactivar(){
  await testUsuarioReactivarAcciones()
  .then((res) => {
    let atributosValor = ["dniUsuario", "usuario", "passwdUsuario"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesUsuarioReactivar", "cuerpoAccionesUsuarioReactivar", "acciones", atributosValor, "Usuario");
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuarioAcciones", "iconoTestUsuarioAccionesReactivar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });