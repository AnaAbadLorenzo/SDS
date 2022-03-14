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
    }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función que carga las funcionalidades asociadas al usuario**/
function cargarFuncionalidadesUsuario(datos){
  var i;

  $("#listadoFuncionalidades").html("");

  var htmlMenu = '';

  for(i = 0; i<(datos.funcionalidades.length) - 1; i++){
    htmlMenu = htmlMenu + '<a class="dropdown-item" href="#">' + datos.funcionalidades[i] + '</a> <div class="dropdown-divider"></div>';
  }

  htmlMenu = htmlMenu + '<a class="dropdown-item" href="#">' + datos.funcionalidades[i] + '</a>';

  $("#listadoFuncionalidades").append(htmlMenu);
}