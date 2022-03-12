/*Funcion para obtener las funcionalidades de un usuario */
async function funcionalidadesUsuario() {
  await funcionalidadesUsuarioAjaxPromesa()
    .then((res) => {
        cargarFuncionalidadesUsuario(res.data);
    })
    .catch((res) => {
        $("#login-modal").modal('toggle'); 
        $("#modal-title").removeClass();
        $("#modal-title").addClass("ERROR");
		    document.getElementById("modal-title").style.color = "#a50707";
        $(".imagenAviso").attr('src', 'images/failed.png');
        $("#mensajeError").removeClass();
        $("#mensajeError").addClass('ERROR_LISTAR_FUNCIONALIDADES_MENU');

  });
}

/**Función obtener las funcionalidades de un usuario con promesas*/
function funcionalidadesUsuarioAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxFuncionalidadesUsuario,
      contentType : "application/json",
      data: usuario,  
      dataType : 'json',
      }).done(res => {
          if (res.code != 'MENU_USUARIO_OK') {
            reject(res);
          }
          resolve(res);
    }).fail(errorGenerico());
  });
}

function cargarFuncionalidadesUsuario(datos){
  var i;

  var htmlMenu = '<div class="dropdown-menu">';

  for(i = 0; i<(datos.funcionalidades.length) - 1; i++){
    htmlMenu.append('<a class="dropdown-item" href="#">' + datos.funcionalidades[i]);
    htmlMenu.append('<div class="dropdown-divider"></div>');
  }

  htmlMenu.append('<a class="dropdown-item" href="#">' + datos.funcionalidades[i] + '</div>');

}