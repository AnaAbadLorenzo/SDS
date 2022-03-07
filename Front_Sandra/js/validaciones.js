/**Función que valida el login de usuario*/
function comprobarUser(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo) && comprobarLetrasNumeros(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
		return true;
	} else {
		validacionKO(idElemento, idElementoError);		
		return false;
	}

}

/**Función que valida la password de usuario*/
function comprobarPass(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo) && comprobarLetrasNumeros(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
		return true;
	} else {
		validacionKO(idElemento, idElementoError);		
		return false;
	}

}

/**Función que valida el email*/
function comprobarEmail(idElemento, idElementoError, campo) {

    document.getElementById(idElemento).style.borderWidth = "2px";
        
    if (validaNoVacio(idElemento, idElementoError, campo) && comprobarFormatoEmail(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 4, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 48,  idElementoError, campo)) {
       	validacionOK(idElemento, idElementoError);
        return true;
    } else {
       	validacionKO(idElemento, idElementoError);
        return false;
    }

}

/**Función que valida si un campo está vacío*/
function validaNoVacio(idElemento, idElementoError, campo) {

	var codigo = "";

  	var valor = document.getElementById(idElemento).value;
  	var longitud = document.getElementById(idElemento).value.length;

  	if ((valor == null) || (longitud == 0)) { 		
  		switch(campo) {
	    	case 'loginUsuario' : 
		  		codigo = "LOGIN_USUARIO_VACIO";
			break;
			case 'passwdUsuarioLogin' :
				codigo = "PASS_USUARIO_VACIO";
			break;
			case 'loginUsuarioRecPass' :
				codigo = "LOGIN_USUARIO_VACIO";
			break;
			case 'emailUsuarioRecPass' :
				codigo = "EMAIL_VACIO";
			break;
		}
		addCodeError(idElementoError, codigo);
	    return false;
	} else {
	    return true;
	 }

}

/**Función que valida el tamaño minimo de un campo**/
function comprobarTamañoMinimo(idElemento, sizeMin, idElementoError, campo){
	var codigo = "";

  	var longitud = document.getElementById(idElemento).value.length;

  	if (longitud < sizeMin) {
		switch(campo) {
	    	case 'loginUsuario' : 
		  		codigo = "LOGIN_MENOR_QUE_3";
			break;
			case 'passwdUsuarioLogin' :
				codigo = "PASS_MENOR_QUE_3";
			break;
			case 'loginUsuarioRecPass' :
				codigo = "LOGIN_MENOR_QUE_3";
			break;
			case 'emailUsuarioRecPass' :
				codigo = "EMAIL_MENOR_QUE_4";
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
    }else{
    	return true;
    }
}

/**Función que valida el tamaño maximo de un campo**/
function comprobarTamañoMaximo(idElemento, sizeMax, idElementoError, campo){
	var codigo = "";

  	var longitud = document.getElementById(idElemento).value.length;

  	if (longitud > sizeMax) {
		switch(campo) {
	    	case 'loginUsuario' : 
		  		codigo = "LOGIN_MAYOR_QUE_45";
			break;
			case 'passwdUsuarioLogin' :
				codigo = "PASS_MAYOR_QUE_45";
			break;
			case 'loginUsuarioRecPass' :
				codigo = "LOGIN_USUARIO_VACIO";
			break;
			case 'emailUsuarioRecPass' :
				codigo = "EMAIL_MAYOR_QUE_48";
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
    }else{
    	return true;
    }
}

/**Función que valida que un campo esté compuesto por letras y números*/
function comprobarLetrasNumeros(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
 
 	var patron = /^[a-zA-Z0-9\u00f1\u00d1]+$/;
		
	if (!patron.test(valor)) { 
    	switch(campo) {
	    	case 'loginUsuario' : 
		  		codigo = "LOGIN_ALFANUMERICO_INCORRECTO";
			break;
			case 'passwdUsuarioLogin' :
				codigo = "PASS_ALFANUMERICO_INCORRECTO"
			break;
			case 'loginUsuarioRecPass' :
				codigo = "LOGIN_USUARIO_VACIO";
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
  	}else{
  		return true;  
  	}	
}

function validacionOK(idElemento, idElementoError) {

	document.getElementById(idElementoError).style.display = "none";
	document.getElementById(idElemento).style.borderColor = "#00e600";
}

/**Función que muestra el mensaje de error y colorea el borde del input del formulario de rojo*/
function validacionKO(idElemento, idElementoError) { 

	document.getElementById(idElementoError).setAttribute('style', "");
	document.getElementById(idElemento).style.borderColor = "#ff0000";
}

/**Función para añadir los mensajes de error*/
function addCodeError(idElementoError, codigo) {

	var idioma = getCookie('lang');

	$("#" + idElementoError).removeClass();
	$("#" + idElementoError).addClass(codigo);
	$("#" + idElementoError).addClass("alert alert-danger");
	
	setLang(idioma);

}

/**Función para comprobar el formato del Email*/
function comprobarFormatoEmail(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
      
	var patron = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/; // establecemos un patron para un email
	if (!patron.test(valor)) {
		switch(campo) {
	    	case 'emailUsuarioRecPass' :
				codigo = "EMAIL_ALFANUMERICO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
		return false;
	}

      return true;

}