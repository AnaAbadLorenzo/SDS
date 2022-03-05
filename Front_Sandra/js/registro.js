/**
 * Ana Abad Lorenzo
 * 
 * Funciones para registro.html
 * 
 */

/**Habilitar o desahbilitar el select para seleccionar la empresa**/
 $( function() {
    $("input[name=seleccionarEmpresa]").change( function() {
        if ($(this).val() === "si") {
            $("#empresasDisponibles").removeAttr('disabled')
        } else {
            $("#empresasDisponibles").prop('disabled', true);
        }
    });
});

/**Función ajax con promesas*/

function registroAjaxPromesa(){
  
  return new Promise(function(resolve, reject) {
    if(verificarPasswd()){
      
      var datosPersona = {
        dniP : $('#dniP').val(),
        nombreP : $('#nombreP').val(),
        apellidosP : $('#apellidosP').val(),
        fechaNacP : $('#fechaNacP').val(),
        direccionP : $('#direccionP').val(),
        telefonoP : $('#telefonoP').val(),
        emailP : $('#emailP').val(),
        borradoP : $('#borradoP').val()
      };

      var datosUsuario = {
        usuario : $('#usuario').val(),
        passwdUsuario : $('#passwdUsuario1').val()
      };

      var perteneceEmpresa = $('input[name=perteneceEmpresa]:checked').val();
      var asociarEmpresa = $('input[name=asociarEmpresa]:checked').val();
      var seleccionarEmpresa = $('input[name=seleccionarEmpresa]:checked').val();

      $.ajax({
        method: "POST",
        url: "http://localhost:8090/registrar",
        contentType : "application/json",
        data: JSON.stringify(data),  
        dataType : 'Json',
      }).done(res => {
          if (res.code != 'LOGIN_OK') {
            reject(res);
      }
            resolve(res);
      }).fail(errorGenerico());
    }
  });
}
  

function errorGenerico() {
  $("#mensajeError").removeClass();
  $("#mensajeError").addClass('02109');
  /*document.getElementById("modal").style.display = "block";*/
}

async function registro() {
  await registroAjaxPromesa()
  .then((res) => {
    window.location.href = "menu.html";
  })
  .catch((res) => {
    $("#mensajeError").removeClass();
    $("#mensajeError").addClass(res.code);
    /*document.getElementById("modal").style.display = "block";*/
  });
}
        

/**Función verificar passwd**/
function verificarPasswd(){
  passwdUsuario1 = $('#passwdUsuario1').val();
  passwdUsuario2 = $('#passwdUsuario2').val();

  if (passwdUsuario1 != passwdUsuario2) {
      
    document.getElementById("error").classList.add("mostrar");
 
    return false;
    
  } else {
      
    document.getElementById("error").classList.remove("mostrar");

    return true;
  }
}
