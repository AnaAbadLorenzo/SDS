/**
 * Ana Abad Lorenzo
 * 
 * Funciones para login.html
 * 
 */

/**Función login ajax con promesas*/
function loginAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var data = {
      usuario : $('#userLogin').val(),
      passwdUsuario : $('#passLogin').val()
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxLogin,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      }).done(res => {
        if (res.code != 'LOGIN_OK') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR, textStatus, errorThrown ) {
      alert( 'Error!!' );
    });
  });
}

/**Función recuperar pass ajax con promesas*/
function recuperarPassAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var data = {
      usuario : $('#userRecuperarPass').val(),
      emailUsuario : $('#emailUser').val(),
      idioma: getCookie('lang')
    }

    $("#modal-title").removeClass();
    $(".imagenAviso").attr('src', 'images/carga.gif');
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass('CARGANDO');
    document.getElementById("modal").style.display = "block";
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxRecuperarPass,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      }).done(res => {
        if (res.code != 'RECUPERAR_PASS_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

async function login() {
  await loginAjaxPromesa()
  .then((res) => {
    setCookie('tokenUsuario',res.data.tokenUsuario);
    setCookie('rolUsuario', res.data.rolUsuario);
    setCookie('usuario', res.data.usuario);

    window.location.href = "menu.html";
  })
  .catch((res) => {
    $("#login-modal").modal('toggle'); 
    $("#modal-title").removeClass();
    $("#modal-title").addClass("ERROR");
    document.getElementById("modal-title").style.color = "#a50707";
    $(".imagenAviso").attr('src', 'images/failed.png');
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass(res.code);

    let idElementoList = ["userLogin", "passLogin"];
    resetearFormulario("formularioLogin", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
});
}

async function recuperarPass(){
  await recuperarPassAjaxPromesa()
  .then((res) => {
  	$("#recuperarcontrasena-modal").modal('toggle');
  	$("#login-modal").modal('toggle'); 
	  $(".imagenAviso").attr('src', 'images/ok.png');
    document.getElementById("modal-title").style.color = "#238f2a";
    document.getElementById("modal-title").style.top = "3%";
    $("#modal-title").removeClass();
    $("#modal-title").addClass("CONTRASEÑA_REC_OK");
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass(res.code);

    let idElementoList = ["userRecuperarPass", "emailUser"];
    resetearFormulario("formularioRecuperarPass", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    })
    
  .catch((res) => {
    $("#recuperarcontrasena-modal").modal('toggle'); 
    $("#login-modal").modal('toggle');
    $("#modal-title").removeClass();
    $("#modal-title").addClass("ERROR");
    document.getElementById("modal-title").style.color = "#a50707";
    $(".imagenAviso").attr('src', 'images/failed.png');
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass(res.code);
    let idElementoList = ["userRecuperarPass", "emailUser"];
    resetearFormulario("formularioRecuperarPass", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
  });
}

$(document).ready(function(){
  $("#login-modal").on('hidden.bs.modal', function () {
  	let idElementoErrorList = ["errorFormatoUser", "errorFormatoPass", "bloqueoMayusculas"];
  	let idElementoList = ["userLogin","passLogin"];
    limpiarFormulario(idElementoList);
  	eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
  	setLang(getCookie('lang'));
  });

	$("#recuperarcontrasena-modal").on('hidden.bs.modal', function () {
		let idElementoErrorList = ["errorFormatoUserPass", "errorFormatoEmail"];
		let idElementoList = ["userRecuperarPass","emailUser"];
    limpiarFormulario(idElementoList);
		eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
		setLang(getCookie('lang'));
    });
});

        