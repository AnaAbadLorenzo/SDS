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

/** Funcion para cargar las noticias de BD **/
async function cargarNoticias(){
  await cargarNoticiasAjaxPromesa()
  .then((res) => {
      
      $('#noticias').html('');

      for(var i = 0; i<res.data.listaBusquedas.length; i++){
        var noticia = construyeNoticia(res.data.listaBusquedas[i]);
        $('#noticias').append(noticia);
      }
    
      
      }).catch((res) => {
    
        respuestaAjaxKO(res.code);
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

/** Función para obtener de back las noticias con ajax y promesas**/
function cargarNoticiasAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxListarTodasNoticias,
      contentType : "application/json", 
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'NOTICIAS_LISTADAS') {
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
    htmlMenu = htmlMenu + '<a class="dropdown-item ' + cargarClass(datos.funcionalidades[i], getCookie('rolUsuario')) + '" href="' + cargarHref(datos.funcionalidades[i]) + '">' + datos.funcionalidades[i] + '</a> <div class="dropdown-divider"></div>';
  }

  htmlMenu = htmlMenu + '<a class="dropdown-item ' + cargarClass(datos.funcionalidades[i], getCookie('rolUsuario')) + '" href="' + cargarHref(datos.funcionalidades[i]) + '">' + datos.funcionalidades[i] + '</a>';


  $("#listadoFuncionalidades").append(htmlMenu);


}

/** Funcion para construir las noticias **/
function construyeNoticia(noticia){
    var noticiaHTML = "";

    var fechaNoticia = new Date(noticia.fechaNoticia);

    noticiaHTML = '<div class="col-md-4 col-lg-6 col-xl-6 mb-4">' + 
                    '<div class="card">' + 
                      '<img src="images/news.png" class="card-img-top" alt="Noticias">' + 
                        '<div class="card-body-news">' + 
                          '<h4 class="card-title">' + noticia.tituloNoticia + '</h4>' + 
                          '<p class="card-text">' + noticia.textoNoticia + '</p>' + 
                        '</div>' + 
                        '<div class="card-footer">' + 
                          '<small class="text-muted">' + convertirFecha(fechaNoticia.toString()) + '</small>' + 
                        '</div>' + 
                    '</div>' + 
                  '</div>';

  return noticiaHTML;

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
