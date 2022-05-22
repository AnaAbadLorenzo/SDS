/**Función para recuperar los test con ajax y promesas*/
function test(urlPeticionAjax, code){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjax,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != code) {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}
/*Función que obtiene los test de de Login */
async function testLogin(tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      url = urlPeticionAjaxTestLoginAtributos;
      code = 'TEST_ATRIBUTOS_LOGIN_OK';
    break;
    case 'Acciones':
      url = urlPeticionAjaxTestLoginAcciones;
      code = 'TEST_ACCIONES_LOGIN_OK';
    break;
  }

	await test(url, code)
	.then((res) => {
    let idElementoList = ["iconoTestLogin", "iconoTestLogin" + tipoTest, "iconoTestLogin" + tipoTest + "Login"];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["usuario", "passwdUsuario"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesLogin", "cuerpoAccionesLogin", atributosValor, "Login", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
  		cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosLogin", "cuerpoAtributosLogin", "", "", idElementoList, "atributos");
    }
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de Recuperar Pass */
async function testRecuperarPass(tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      url = urlPeticionAjaxTestRecuperarPassAtributos;
      code = 'TEST_ATRIBUTOS_RECUPERARPASS_OK';
    break;
    case 'Acciones':
      url = urlPeticionAjaxTestRecuperarPassAcciones;
      code = 'TEST_ACCIONES_RECUPERARPASS_OK';
    break;
  }

	await test(url, code)
	.then((res) => {
    let idElementoList = ["iconoTestLogin", "iconoTestLogin" + tipoTest, "iconoTestLogin" + tipoTest + "RecuperarPass"];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["emailP", "usuario"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesRecuperarPass", "cuerpoAccionesRecuperarPass", atributosValor, "RecuperarPass", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosRecuperarPass", "cuerpoAtributosRecuperarPass", "", "", idElementoList, "atributos");
    }
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de Registrar */
async function testRegistrar(tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      url = urlPeticionAjaxTestRegistrarAtributos;
      code = 'TEST_ATRIBUTOS_REGISTRO_OK';
    break;
    case 'Acciones':
      url = urlPeticionAjaxTestRegistrarAcciones;
      code = 'TEST_ACCIONES_REGISTRO_OK';
    break;
  }

	await test(url, code)
	.then((res) => {
    let idElementoList = ["iconoTestRegistrar", "iconoTestRegistrar" + tipoTest];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["nombreP", "apellidosP", "fechaNacP", "telefonoEmpresa", "nombreEmpresa", "direccionP", "emailP", "cifEmpresa", "direccionEmpresa", "seleccionarEmpresa", "telefonoP", "usuario", "dniP", "passwdUsuario"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesRegistrar", "cuerpoAccionesRegistrar", atributosValor, "Registrar", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosRegistrar", "cuerpoAtributosRegistrar", "", "", idElementoList, "atributos");
    }
	  }).catch((res) => {
	    cargarModalErroresTest(res.code);
	});
}

/*Función que obtiene los test de rol */
async function testRol(accion, tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTO_ROL_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestRolAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestRolAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestRolAtributosAccionModificar;
        break;
      }
    break;
     case 'Acciones':
      code = 'TEST_ACCIONES_ROL_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestRolAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestRolAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestRolAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestRolAccionEliminar;
        break;
        case 'Reactivar':
          url = urlPeticionAjaxTestRolAccionReactivar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestRol", "iconoTestRol" + tipoTest, "iconoTestRol" + tipoTest + accion ];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["rolDescription", "rolName"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesRol" + accion, "cuerpoAccionesRol" + accion, atributosValor, "Rol", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosRol" + accion, "cuerpoAtributosRol" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de funcionalidad */
async function testFuncionalidad(accion, tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_FUNCIONALIDAD_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestFuncionalidadAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestFuncionalidadAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestFuncionalidadAtributosAccionModificar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCIONES_FUNCIONALIDAD_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestFuncionalidadAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestFuncionalidadAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestFuncionalidadAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestFuncionalidadAccionEliminar;
        break;
        case 'Reactivar':
          url = urlPeticionAjaxTestFuncionalidadAccionReactivar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestFuncionalidad", "iconoTestFuncionalidad" + tipoTest, "iconoTestFuncionalidad" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["descripFuncionalidad", "nombreFuncionalidad"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesFuncionalidad" + accion, "cuerpoAccionesFuncionalidad" + accion, atributosValor, "Funcionalidad", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosFuncionalidad" + accion, "cuerpoAtributosFuncionalidad" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de acción */
async function testAccion(accion, tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_ACCION_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestAccionAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestAccionAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestAccionAtributosAccionModificar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCION_ACCION_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestAccionAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestAccionAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestAccionAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestAccionAccionEliminar;
        break;
        case 'Reactivar':
          url = urlPeticionAjaxTestAccionAccionReactivar;
        break;
        case 'Asignar':
          url = urlPeticionAjaxTestAccionAccionAsignar;
        break;
        case 'Revocar':
          url = urlPeticionAjaxTestAccionAccionRevocar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestAccion", "iconoTestAccion" + tipoTest, "iconoTestAccion" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["descripAccion", "nombreAccion"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesAccion" + accion, "cuerpoAccionesAccion" + accion, atributosValor, "Accion", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosAccion" + accion, "cuerpoAtributosAccion" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de usuario */
async function testUsuario(accion, tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_USUARIO_OK';
      switch(accion){
        case 'ModificarRolUsuario':
          url = urlPeticionAjaxTestUsuarioAtributosAccionModificarRolUsuario;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestUsuarioAtributosAccionBuscar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCIONES_USUARIO_OK';
      switch(accion){
        case 'Buscar':
          url = urlPeticionAjaxTestUsuarioAccionBuscar;
        break;
        case 'ModificarRolUsuario':
          url = urlPeticionAjaxTestUsuarioAccionModificarRolUsuario;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestUsuarioAccionEliminar;
        break;
        case 'Reactivar':
          url = urlPeticionAjaxTestUsuarioAccionReactivar;
        break;
        case 'ModificarPasswdUsuario':
          url = urlPeticionAjaxTestUsuarioAccionModificarPasswdUsuario;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestUsuario", "iconoTestUsuario" + tipoTest, "iconoTestUsuario" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["dniUsuario", "usuario", "passwdUsuario"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesUsuario" + accion, "cuerpoAccionesUsuario" + accion, atributosValor, "Usuario", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosUsuario" + accion, "cuerpoAtributosUsuario" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test noticia */
async function testNoticia(accion, tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_NOTICIA_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestNoticiaAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestNoticiaAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestNoticiaAtributosAccionModificar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCIONES_NOTICIA_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestNoticiaAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestNoticiaAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestNoticiaAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestNoticiaAccionEliminar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestNoticia", "iconoTestNoticia" + tipoTest, "iconoTestNoticia" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["tituloNoticia", "textoNoticia", "fechaNoticia"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesNoticia" + accion, "cuerpoAccionesNoticia" + accion, atributosValor, "Noticia", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosNoticia" + accion, "cuerpoAtributosNoticia" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test de persona */
async function testPersona(accion, tipoTest){

  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_PERSONA_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestPersonaAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestPersonaAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestPersonaAtributosAccionModificar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCIONES_PERSONA_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestPersonaAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestPersonaAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestPersonaAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestPersonaAccionEliminar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestPersona", "iconoTestPersona" + tipoTest, "iconoTestPersona" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["dniP", "nombreP", "apellidosP", "fechaNacP", "direccionP", "emailP", "telefonoP", "borradoPersona"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesPersona" + accion, "cuerpoAccionesPersona" + accion, atributosValor, "Persona", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosPersona" + accion, "cuerpoAtributosPersona" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test empresa */
async function testEmpresa(accion, tipoTest){
  
  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_EMPRESA_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestEmpresaAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestEmpresaAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestEmpresaAtributosAccionModificar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCIONES_EMPRESA_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestEmpresaAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestEmpresaAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestEmpresaAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestEmpresaAccionEliminar;
        break;
        case 'Reactivar':
          url = urlPeticionAjaxTestEmpresaAccionReactivar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestEmpresa", "iconoTestEmpresa" + tipoTest, "iconoTestEmpresa" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["cifEmpresa", "nombreEmpresa", "direccionEmpresa", "telefonoEmpresa"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesEmpresa" + accion, "cuerpoAccionesEmpresa" + accion, atributosValor, "Empresa", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosEmpresa" + accion, "cuerpoAtributosEmpresa" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}

/*Función que obtiene los test objetivo */
async function testObjetivo(accion, tipoTest){
  
  var url = "";
  var code = "";

  switch(tipoTest){
    case 'Atributos':
      code = 'TEST_ATRIBUTOS_OBJETIVO_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestObjetivoAtributosAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestObjetivoAtributosAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestObjetivoAtributosAccionModificar;
        break;
      }
    break;
    case 'Acciones':
      code = 'TEST_ACCIONES_OBJETIVO_OK';
      switch(accion){
        case 'Guardar':
          url = urlPeticionAjaxTestObjetivoAccionGuardar;
        break;
        case 'Buscar':
          url = urlPeticionAjaxTestObjetivoAccionBuscar;
        break;
        case 'Modificar':
          url = urlPeticionAjaxTestObjetivoAccionModificar;
        break;
        case 'Eliminar':
          url = urlPeticionAjaxTestObjetivoAccionEliminar;
        break;
        case 'Reactivar':
          url = urlPeticionAjaxTestObjetivoAccionReactivar;
        break;
      }
    break;
  }

  await test(url, code)
  .then((res) => {
    let idElementoList = ["iconoTestObjetivo", "iconoTestObjetivo" + tipoTest, "iconoTestObjetivo" + tipoTest + accion];
    if (tipoTest === 'Acciones') {
      let atributosValor = ["nombreObjetivo", "descripObjetivo"];
      cargarRespuestaOkTest(res.data.datosPruebaAcciones, "cabeceraAccionesObjetivo" + accion, "cuerpoAccionesObjetivo" + accion, atributosValor, "Objetivo", idElementoList, "acciones");
    } else if (tipoTest === 'Atributos'){
      cargarRespuestaOkTest(res.data.datosPruebaAtributos, "cabeceraAtributosObjetivo" + accion, "cuerpoAtributosObjetivo" + accion, "", "", idElementoList, "atributos");
    }
    }).catch((res) => {
      cargarModalErroresTest(res.code);
  });
}