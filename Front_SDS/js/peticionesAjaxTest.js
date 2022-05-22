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

/**Función para recuperar los test de atributo de guardar noticia con ajax y promesas*/
function testNoticiaGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar noticia con ajax y promesas*/
function testNoticiaBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar noticia con ajax y promesas*/
function testNoticiaModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar persona con ajax y promesas*/
function testPersonaBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de guardar persona con ajax y promesas*/
function testPersonaGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar persona con ajax y promesas*/
function testPersonaModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar empresa con ajax y promesas*/
function testEmpresaBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de guardar empresa con ajax y promesas*/
function testEmpresaGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar empresa con ajax y promesas*/
function testEmpresaModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de buscar objetivo con ajax y promesas*/
function testObjetivoBuscarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAtributosAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_OBJETIVO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de guardar objetivo con ajax y promesas*/
function testObjetivoGuardarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAtributosAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_OBJETIVO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de atributo de modificar objetivo con ajax y promesas*/
function testObjetivoModificarAtributos(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAtributosAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_OBJETIVO_OK') {
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

/**Función para recuperar los test de acciones de buscar noticia con ajax y promesas*/
function testNoticiaBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de guardar noticia con ajax y promesas*/
function testNoticiaGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar noticia con ajax y promesas*/
function testNoticiaModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar noticia con ajax y promesas*/
function testNoticiaEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestNoticiaAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_NOTICIA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de guardar persona con ajax y promesas*/
function testPersonaGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar persona con ajax y promesas*/
function testPersonaBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar persona con ajax y promesas*/
function testPersonaModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar persona con ajax y promesas*/
function testPersonaEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestPersonaAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_PERSONA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar empresa con ajax y promesas*/
function testEmpresaBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de guardar empresa con ajax y promesas*/
function testEmpresaGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de reactivar empresa con ajax y promesas*/
function testEmpresaModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar empresa con ajax y promesas*/
function testEmpresaEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de reactivar empresa con ajax y promesas*/
function testEmpresaReactivarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestEmpresaAccionReactivar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_EMPRESA_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de buscar objetivo con ajax y promesas*/
function testObjetivoBuscarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAccionBuscar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_OBJETIVO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de guardar objetivo con ajax y promesas*/
function testObjetivoGuardarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAccionGuardar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_OBJETIVO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de modificar objetivo con ajax y promesas*/
function testObjetivoModificarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAccionModificar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_OBJETIVO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de eliminar objetivo con ajax y promesas*/
function testObjetivoEliminarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAccionEliminar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_OBJETIVO_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar los test de acciones de reactivar objetivo con ajax y promesas*/
function testObjetivoReactivarAcciones(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestObjetivoAccionReactivar,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_OBJETIVO_OK') {
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

/*Función que obtiene los test de guardar noticia */
async function testAtributosNoticiaGuardar(){
  await testNoticiaGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosNoticiaGuardar", "cuerpoAtributosNoticiaGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAtributos", "iconoTestNoticiaAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar noticia */
async function testAtributosNoticiaBuscar(){
  await testNoticiaBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosNoticiaBuscar", "cuerpoAtributosNoticiaBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAtributos", "iconoTestNoticiaAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar noticia */
async function testAtributosNoticiaModificar(){
  await testNoticiaModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosNoticiaModificar", "cuerpoAtributosNoticiaModificar", "atributos", "", "");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAtributos", "iconoTestNoticiaAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar persona */
async function testAtributosPersonaBuscar(){
  await testPersonaBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosPersonaBuscar", "cuerpoAtributosPersonaBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAtributos", "iconoTestPersonaAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de guardar persona */
async function testAtributosPersonaGuardar(){
  await testPersonaGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosPersonaGuardar", "cuerpoAtributosPersonaGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAtributos", "iconoTestPersonaAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar persona */
async function testAtributosPersonaModificar(){
  await testPersonaModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosPersonaModificar", "cuerpoAtributosPersonaModificar", "atributos", "", "");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAtributos", "iconoTestPersonaAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar empresa */
async function testAtributosEmpresaBuscar(){
  await testEmpresaBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosEmpresaBuscar", "cuerpoAtributosEmpresaBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAtributos", "iconoTestEmpresaAtributosBuscar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de guardar empresa */
async function testAtributosEmpresaGuardar(){
  await testEmpresaGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosEmpresaGuardar", "cuerpoAtributosEmpresaGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAtributos", "iconoTestEmpresaAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar empresa */
async function testAtributosEmpresaModificar(){
  await testEmpresaModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosEmpresaModificar", "cuerpoAtributosEmpresaModificar", "atributos", "", "");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAtributos", "iconoTestEmpresaAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de buscar objetivo */
async function testAtributosObjetivoBuscar(){
  await testObjetivoBuscarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosObjetivoBuscar", "cuerpoAtributosObjetivoBuscar", "atributos", "", "");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAtributos", "iconoTestObjetivoAtributosModificar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de añadir objetivo */
async function testAtributosObjetivoGuardar(){
  await testObjetivoGuardarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosObjetivoGuardar", "cuerpoAtributosObjetivoGuardar", "atributos", "", "");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAtributos", "iconoTestObjetivoAtributosGuardar"];
    validarDatosTabla(res.data.datosPruebaAtributos, idElementoList, "atributos");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de modificar objetivo */
async function testAtributosObjetivoModificar(){
  await testObjetivoModificarAtributos()
  .then((res) => {
    cargarTablasTest(res.data.datosPruebaAtributos, "cabeceraAtributosObjetivoModificar", "cuerpoAtributosObjetivoModificar", "atributos", "", "");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAtributos", "iconoTestObjetivoAtributosModificar"];
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
}

/*Función que obtiene los test de acciones de Buscar Noticia */
async function testAccionesNoticiaBuscar(){
  await testNoticiaBuscarAcciones()
  .then((res) => {
    let atributosValor = ["tituloNoticia", "textoNoticia", "fechaNoticia"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesNoticiaBuscar", "cuerpoAccionesNoticiaBuscar", "acciones", atributosValor, "Noticia");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAcciones", "iconoTestNoticiaAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Guardar Noticia */
async function testAccionesNoticiaGuardar(){
  await testNoticiaGuardarAcciones()
  .then((res) => {
    let atributosValor = ["tituloNoticia", "textoNoticia", "fechaNoticia"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesNoticiaGuardar", "cuerpoAccionesNoticiaGuardar", "acciones", atributosValor, "Noticia");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAcciones", "iconoTestNoticiaAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Noticia */
async function testAccionesNoticiaModificar(){
  await testNoticiaModificarAcciones()
  .then((res) => {
    let atributosValor = ["tituloNoticia", "textoNoticia", "fechaNoticia"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesNoticiaModificar", "cuerpoAccionesNoticiaModificar", "acciones", atributosValor, "Noticia");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAcciones", "iconoTestNoticiaAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Eliminar Noticia */
async function testAccionesNoticiaEliminar(){
  await testNoticiaEliminarAcciones()
  .then((res) => {
    let atributosValor = ["tituloNoticia", "textoNoticia", "fechaNoticia"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesNoticiaEliminar", "cuerpoAccionesNoticiaEliminar", "acciones", atributosValor, "Noticia");
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticiaAcciones", "iconoTestNoticiaAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Buscar Persona */
async function testAccionesPersonaBuscar(){
  await testPersonaBuscarAcciones()
  .then((res) => {
    let atributosValor = ["dniP", "nombreP", "apellidosP", "fechaNacP", "direccionP", "emailP", "telefonoP", "borradoPersona"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesPersonaBuscar", "cuerpoAccionesPersonaBuscar", "acciones", atributosValor, "Persona");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAcciones", "iconoTestPersonaAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Guardar Persona */
async function testAccionesPersonaGuardar(){
  await testPersonaGuardarAcciones()
  .then((res) => {
    let atributosValor = ["dniP", "nombreP", "apellidosP", "fechaNacP", "direccionP", "emailP", "telefonoP", "borradoPersona"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesPersonaGuardar", "cuerpoAccionesPersonaGuardar", "acciones", atributosValor, "Persona");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAcciones", "iconoTestPersonaAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Eliminar Persona */
async function testAccionesPersonaEliminar(){
  await testPersonaEliminarAcciones()
  .then((res) => {
    let atributosValor = ["dniP", "nombreP", "apellidosP", "fechaNacP", "direccionP", "emailP", "telefonoP", "borradoPersona"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesPersonaEliminar", "cuerpoAccionesPersonaEliminar", "acciones", atributosValor, "Persona");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAcciones", "iconoTestPersonaAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de Modificar Persona */
async function testAccionesPersonaModificar(){
  await testPersonaModificarAcciones()
  .then((res) => {
    let atributosValor = ["dniP", "nombreP", "apellidosP", "fechaNacP", "direccionP", "emailP", "telefonoP", "borradoPersona"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesPersonaModificar", "cuerpoAccionesPersonaModificar", "acciones", atributosValor, "Persona");
    let idElementoList = ["iconoTestPersona", "iconoTestPersonaAcciones", "iconoTestPersonaAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de buscar empresa */
async function testAccionesEmpresaBuscar(){
  await testEmpresaBuscarAcciones()
  .then((res) => {
    let atributosValor = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesEmpresaBuscar", "cuerpoAccionesEmpresaBuscar", "acciones", atributosValor, "Empresa");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAcciones", "iconoTestEmpresaAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de guardar empresa */
async function testAccionesEmpresaGuardar(){
  await testEmpresaGuardarAcciones()
  .then((res) => {
    let atributosValor = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesEmpresaGuardar", "cuerpoAccionesEmpresaGuardar", "acciones", atributosValor, "Empresa");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAcciones", "iconoTestEmpresaAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de modificar empresa */
async function testAccionesEmpresaModificar(){
  await testEmpresaModificarAcciones()
  .then((res) => {
    let atributosValor = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesEmpresaModificar", "cuerpoAccionesEmpresaModificar", "acciones", atributosValor, "Empresa");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAcciones", "iconoTestEmpresaAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de eliminar empresa */
async function testAccionesEmpresaEliminar(){
  await testEmpresaEliminarAcciones()
  .then((res) => {
    let atributosValor = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesEmpresaEliminar", "cuerpoAccionesEmpresaEliminar", "acciones", atributosValor, "Empresa");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAcciones", "iconoTestEmpresaAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de reactivar empresa */
async function testAccionesEmpresaReactivar(){
  await testEmpresaReactivarAcciones()
  .then((res) => {
    let atributosValor = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesEmpresaReactivar", "cuerpoAccionesEmpresaReactivar", "acciones", atributosValor, "Empresa");
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresaAcciones", "iconoTestEmpresaAccionesReactivar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de buscar objetivo */
async function testAccionesObjetivoBuscar(){
  await testObjetivoBuscarAcciones()
  .then((res) => {
    let atributosValor = ["nombreObjetivo", "descripObjetivo"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesObjetivoBuscar", "cuerpoAccionesObjetivoBuscar", "acciones", atributosValor, "Objetivo");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAcciones", "iconoTestObjetivoAccionesBuscar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de guardar objetivo */
async function testAccionesObjetivoGuardar(){
  await testObjetivoGuardarAcciones()
  .then((res) => {
    let atributosValor = ["nombreObjetivo", "descripObjetivo"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesObjetivoGuardar", "cuerpoAccionesObjetivoGuardar", "acciones", atributosValor, "Objetivo");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAcciones", "iconoTestObjetivoAccionesGuardar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de modificar objetivo */
async function testAccionesObjetivoModificar(){
  await testObjetivoModificarAcciones()
  .then((res) => {
    let atributosValor = ["nombreObjetivo", "descripObjetivo"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesObjetivoModificar", "cuerpoAccionesObjetivoModificar", "acciones", atributosValor, "Objetivo");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAcciones", "iconoTestObjetivoAccionesModificar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de eliminar objetivo */
async function testAccionesObjetivoEliminar(){
  await testObjetivoEliminarAcciones()
  .then((res) => {
    let atributosValor = ["nombreObjetivo", "descripObjetivo"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesObjetivoEliminar", "cuerpoAccionesObjetivoEliminar", "acciones", atributosValor, "Objetivo");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAcciones", "iconoTestObjetivoAccionesEliminar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acciones de reactivar objetivo */
async function testAccionesObjetivoReactivar(){
  await testObjetivoReactivarAcciones()
  .then((res) => {
    let atributosValor = ["nombreObjetivo", "descripObjetivo"];
    cargarTablasTest(res.data.datosPruebaAcciones, "cabeceraAccionesObjetivoReactivar", "cuerpoAccionesObjetivoReactivar", "acciones", atributosValor, "Objetivo");
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivoAcciones", "iconoTestObjetivoAccionesReactivar"];
    validarDatosTabla(res.data.datosPruebaAcciones, idElementoList, "acciones");
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}