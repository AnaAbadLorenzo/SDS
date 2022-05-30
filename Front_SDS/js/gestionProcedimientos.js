/**Función para que el usuario pueda iniciar un plan*/
function iniciarProcedimiento(){
  setCookie('accesoDesdePlan', 'false');
  window.location.href = "GestionDeProcedimientos.html";
}

/**Función para añadir botones e información cuando el usuario accede a sus procedimientos*/
function cambiarDatosProcedimientos() {
  var accesoDesdePlan = getCookie('accesoDesdePlan');

  if (accesoDesdePlan === 'false'){
    document.getElementById("iconoIniciarProcedimiento").style.marginLeft = "250%";
    document.getElementById("procesos").style.display = "block"; 
    document.getElementById("puntuacion").style.display = "block"; 
    document.getElementById("fecha").style.display = "block";
    document.getElementById("continuarProcedimiento").style.display = "inline-block"; 
    document.getElementById("finalizadoProcedimiento").style.display = "inline-block";
    $("#iconoIniciarProcedimiento").attr('onclick', 'javascript:iniciarProcedimientoUsuario()'); 

    document.getElementById("iconoIniciarProcedimiento2").style.marginLeft = "250%";
    document.getElementById("procesos2").style.display = "block"; 
    document.getElementById("puntuacion2").style.display = "block"; 
    document.getElementById("fecha2").style.display = "block";
    document.getElementById("continuarProcedimiento2").style.display = "inline-block"; 
    document.getElementById("finalizadoProcedimiento2").style.display = "inline-block";

    document.getElementById("iconoIniciarProcedimiento3").style.marginLeft = "250%";
    document.getElementById("procesos3").style.display = "block"; 
    document.getElementById("puntuacion3").style.display = "block"; 
    document.getElementById("fecha3").style.display = "block";
    document.getElementById("continuarProcedimiento3").style.display = "inline-block"; 
    document.getElementById("finalizadoProcedimiento3").style.display = "inline-block";
  } else {
    $("#iconoIniciarProcedimiento").attr('onclick', 'javascript:iniciarProcedimiento()');
    document.getElementById("iconoIniciarProcedimiento").style.marginLeft = "290%"; 
    document.getElementById("iconoIniciarProcedimiento").style.marginTop = "-18%";
    $("#iniciarProcedimiento").removeClass();
    $("#iniciarProcedimiento").addClass('tooltip13');
    $("#iniciarProcedimiento").addClass('procedimientoIcon');

    document.getElementById("iconoIniciarProcedimiento2").style.marginLeft = "290%"; 
    document.getElementById("iconoIniciarProcedimiento2").style.marginTop = "-18%";
    $("#iniciarProcedimiento2").removeClass();
    $("#iniciarProcedimiento2").addClass('tooltip13');
    $("#iniciarProcedimiento2").addClass('procedimientoIcon');

    document.getElementById("iconoIniciarProcedimiento3").style.marginLeft = "290%"; 
    document.getElementById("iconoIniciarProcedimiento3").style.marginTop = "-18%";
    $("#iniciarProcedimiento3").removeClass();
    $("#iniciarProcedimiento3").addClass('tooltip13');
    $("#iniciarProcedimiento3").addClass('procedimientoIcon');
  }
}

/**Función para que el usuario pueda iniciar un procedimiento*/
function iniciarProcedimientoUsuario(){
  window.location.href = "GestionDeProcesos.html";
}
