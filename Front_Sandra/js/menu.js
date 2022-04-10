/* Funcion para cambiar la contraseña */
async function changePass() {
  await changePassUsuarioAjaxPromesa()
    .then((res) => {
      $("#changePass-modal").modal('toggle');

      respuestaAjaxOK("CONTRASEÑA_CHANGE_OK", res.code);
    
      let idElementoList = ["passChangePass1", "passChangePass2"];
      resetearFormulario("formularioChangePass", idElementoList);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
      
      }).catch((res) => {
        
        $("#changePass-modal").modal('toggle');
        
        respuestaAjaxKO(res.code);

        let idElementoList = ["passChangePass1", "passChangePass2"];
        limpiarFormulario(idElementoList);
        resetearFormulario("formularioChangePass", idElementoList);
        setLang(getCookie('lang'));
        document.getElementById("modal").style.display = "block";
      });
}

/* Función para cambiar la contraseña con ajax y promesas */
function changePassUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    if(verificarPasswd()){
      encriptar('passChangePass1');
      var usuario = getCookie('usuario');
      var token = getCookie('tokenUsuario');
      var passwdUsuario =  $('#passChangePass1').val();

      var cambiarContrasena = {
        usuario : usuario,
        passwdUsuario : passwdUsuario
      };
  
      $.ajax({
      method: "POST",
      url: urlPeticionAjaxCambiarContrasenaUsuario,
      contentType : "application/json",
      data: JSON.stringify(cambiarContrasena),
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PASSWORD_CAMBIADA') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  }else{
    document.getElementById("error").setAttribute('style', "");
  }
  });
    
}

/**Función verificar passwd**/
function verificarPasswd() {
  passwdUsuario1 = $('#passChangePass1').val();
  passwdUsuario2 = $('#passChangePass2').val();

  if (passwdUsuario1 != passwdUsuario2) {
    addCodeError('error', 'CONTRASEÑAS_NO_COINCIDEN');
    return false;

  } else {
    $("#error").removeClass();
    $("#error").html('');
    $("#error").css("display", "none");
    return true;
  }
};


/*Funcion para obtener las funcionalidades de un usuario */
async function funcionalidadesUsuario() {
  await funcionalidadesUsuarioAjaxPromesa()
    .then((res) => {
        cargarFuncionalidadesUsuario(res.data);
    })
    .catch((res) => {
       if($('#login-modal').is(':visible')) {
         $("#login-modal").modal('toggle'); 
      };
       respuestaAjaxKO('ERROR_LISTAR_FUNCIONALIDADES_MENU');

  });
}

/**Función obtener las funcionalidades de un usuario con promesas*/
function funcionalidadesUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxFuncionalidadesUsuario,
      contentType : "application/json",
      data: { usuario : usuario},  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'MENU_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función que carga las funcionalidades asociadas al usuario**/
function cargarFuncionalidadesUsuario(datos){
  var i;

  $("#listadoFuncionalidades").html("");

  var htmlMenu = '';

  for(i = 0; i<(datos.funcionalidades.length) - 1; i++) {
    htmlMenu = htmlMenu + '<a class="dropdown-item ' + cargarClass(datos.funcionalidades[i]) + '" href="' + cargarHref(datos.funcionalidades[i]) + '">' + datos.funcionalidades[i] + '</a> <div class="dropdown-divider"></div>';
  }

  htmlMenu = htmlMenu + '<a class="dropdown-item ' + cargarClass(datos.funcionalidades[i]) + '" href="' + cargarHref(datos.funcionalidades[i]) + '">' + datos.funcionalidades[i] + '</a>';


  $("#listadoFuncionalidades").append(htmlMenu);


}

$(document).ready(function() {
  $("#changePass-modal").on('hidden.bs.modal', function() {
    
    let idElementoErrorList = ["errorFormatoChangePass1", "errorFormatoChangePass2", "bloqueoMayusculasChangePass", "error"];
    
    let idElementoList = ["passChangePass1", "passChangePass2"];

    limpiarFormulario(idElementoList);
    eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
    setLang(getCookie('lang'));
  });

});
