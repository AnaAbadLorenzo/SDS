/** Función que valida el formulario de cambio de contraseña **/
function comprobarChangePass(){
	if(comprobarPass('passChangePass1', 'errorFormatoChangePass1', 'passwordChange') && comprobarPass('passChangePass2', 'errorFormatoChangePass2', 'passwordChange')){
		return true;
	}else{
		return false;
	}
}


/** Función que valida el formulario de registro **/
function comprobarRegistro(){
	if(comprobarDNI('dniP', 'errorFormatoDni', 'dniPersona') && 
		comprobarNombre('nombreP', 'errorFormatoNombrePersona', 'nombrePersonaRegistro') && 
		comprobarApellidos('apellidosP', 'errorFormatoApellidosP', 'apellidosPersonaRegistro')
		&& comprobarFechaNacimiento('fechaNacP', 'errorFormatoFecha', 'fechaPersonaRegistro') && 
		comprobarDireccion('direccionP', 'errorFormatoDireccion', 'direccionPersonaRegistro') && 
		comprobarTelefono('telefonoP', 'errorFormatoTelefono', 'telefonoPersonaRegistro')
		&& comprobarEmail('emailP', 'errorFormatoEmail', 'emailPersonaRegistro') && 
		comprobarUser('usuario', 'errorFormatoUserRegistro', 'loginUsuario') && 
		comprobarPass('passwdUsuario1', 'errorFormatoPassRegistro', 'passwdUsuarioRegistro')  && 
		comprobarPass('passwdUsuario2', 'errorFormatoPassRegistro2', 'passwdUsuarioRegistro')){
	
		if($('#formRegistroEmpresa').is(':hidden')){
			return true;
		
		}else{
			if(comprobarCIF('cifEmpresa', 'errorFormatoCifEmpresa', 'cifEmpresaRegistro') && 
				comprobarNombreEmpresa('nombreEmpresa', 'errorFormatoNombreEmpresa', 'nombreEmpresaRegistro') && 
				comprobarDireccion('direccionEmpresa', 'errorFormatoDireccionEmpresa', 'direccionEmpresaRegistro') && 
				comprobarTelefono('telefono', 'errorFormatoTelefonoEmpresa', 'telefonoEmpresaRegistro')){
				return true;
			}else{
				
				return false;
			}
		}

		
	}else{
		return false;
	}
}

/** Función que valida el formulario de login **/
function comprobarLogin(){
	if(comprobarUser('userLogin', 'errorFormatoUser', 'loginUsuario') && comprobarPass('passLogin', 'errorFormatoPass', 'passwdUsuarioLogin')){
		encriptar('passLogin');
		return true;
	}else{
		return false;
	}
}

/** Función que valida el formulario de recuperación de contraseña **/
function comprobarRecuperarPass(){
	if(comprobarUser('userRecuperarPass', 'errorFormatoUserPass', 'loginUsuarioRecPass') && comprobarEmail('emailUser', 'errorFormatoEmail', 'emailUsuarioRecPass')){
		return true;
	}else{
		return false;
	}
}

/**Función que valida el login de usuario*/
function comprobarUser(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo) && comprobarLetrasNumeros(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45, idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo)) {
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
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45,  idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
		return true;
	} else {
		validacionKO(idElemento, idElementoError);		
		return false;
	}

}
/**Función que valida la password de usuario*/
function comprobarPassChanguePassword() {

	document.getElementById('passCambiarPass1').style.borderWidth = "2px";
		
	if (validaNoVacio('passCambiarPass1', 'errorFormatoPassCambiarRegistro', 'passwordChange') && comprobarLetrasNumeros('passCambiarPass1', 'errorFormatoPassCambiarRegistro', 'passwordChange') && 
		comprobarTamañoMinimo('passCambiarPass1',3, 'errorFormatoPassCambiarRegistro', 'passwordChange') && comprobarTamañoMaximo('passCambiarPass1', 45, 'errorFormatoPassCambiarRegistro', 'passwordChange') && comprobarEnhe('passCambiarPass1', 'errorFormatoPassCambiarRegistro', 'passwordChange')) {
		validacionOK('passCambiarPass1', 'errorFormatoPassCambiarRegistro');
		return true;
	} else {
		validacionKO('passCambiarPass1', 'errorFormatoPassCambiarRegistro');		
		return false;
	}

}

/**Función que valida el email*/
function comprobarEmail(idElemento, idElementoError, campo) {

    document.getElementById(idElemento).style.borderWidth = "2px";
        
    if (validaNoVacio(idElemento, idElementoError, campo) && 
    	comprobarFormatoEmail(idElemento, idElementoError, campo) && 
    	comprobarTamañoMinimo(idElemento, 4, idElementoError, campo) && 
    	comprobarTamañoMaximo(idElemento, 48,  idElementoError, campo)) {
       	validacionOK(idElemento, idElementoError);
        return true;
    } else {
       	validacionKO(idElemento, idElementoError);
        return false;
    }

}

/** Funcion que valida el formato del DNI **/
function comprobarDNI(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarFormatoDNI(idElemento, idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 9, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 9,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato del Nombre **/
function comprobarNombre(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 56,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de los apellidos **/
function comprobarApellidos(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 128,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de la fecha de nacimiento **/
function comprobarFechaNacimiento(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 128,  idElementoError, campo) && comprobarFormatoFechas(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError); 
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de la fecha de nacimiento **/
function comprobarDireccion(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 128,  idElementoError, campo) && comprobarLetrasNumerosCaracteres(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError); 
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato del telefono**/
function comprobarTelefono(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 9, idElementoError, campo) && 
		comprobarTamañoMaximo(idElemento, 9,  idElementoError, campo) && comprobarSoloNumeros(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError); 
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato del CIF **/
function comprobarCIF(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarFormatoCIF(idElemento, idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 9, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 9,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato del Nombre de la empresa **/
function comprobarNombreEmpresa(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 48,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
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
			case 'dniPersona' :
				codigo = "DNI_PERSONA_VACIO";
			break;
			case 'nombrePersonaRegistro' : 
		  		codigo = "NOMBRE_VACIO";
			break;
			case 'apellidosPersonaRegistro' : 
		  		codigo = "APELLIDOS_PERSONA_VACIO";
			break;
			case 'fechaPersonaRegistro' : 
		  		codigo = "FECHA_NACIMIENTO_VACIA";
			break;
			case 'direccionPersonaRegistro' : 
		  		codigo = "DIRECCION_VACIO";
			break;
			case 'telefonoPersonaRegistro' : 
		  		codigo = "TELEFONO_VACIO";
			break;
			case 'emailPersonaRegistro' :
				codigo = "EMAIL_VACIO";
			break;
			case 'cifEmpresaRegistro' :
				codigo = "CIF_EMPRESA_VACIO";
			break;
			case 'nombreEmpresaRegistro' : 
		  		codigo = "NOMBRE_VACIO";
			break;
			case 'direccionEmpresaRegistro' : 
		  		codigo = "DIRECCION_VACIO";
			break;
			case 'telefonoEmpresaRegistro' : 
		  		codigo = "TELEFONO_VACIO";
			break;
			case 'passwdUsuarioRegistro':
				codigo = "PASS_USUARIO_VACIO";
			break;
			case 'passwordChange':
				codigo="PASS_USUARIO_VACIO";
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
			case 'dniPersona' :
				codigo = "DNI_PERSONA_MENOR_QUE_9";
			break;
			case 'nombrePersonaRegistro' : 
		  		codigo = "NOMBRE_MENOR_QUE_3";
			break;
			case 'apellidosPersonaRegistro' : 
		  		codigo = "APELLIDOS_PERSONA_MENOR_QUE_3";
			break;
			case 'fechaPersonaRegistro' : 
		  		codigo = "FECHA_NACIMIENTO_MENOR_QUE_8";
			break;
			case 'direccionPersonaRegistro' : 
		  		codigo = "DIRECCION_MENOR_QUE_3";
			break;
			case 'telefonoPersonaRegistro' : 
		  		codigo = "TELEFONO_MENOR_QUE_9";
			break;
			case 'emailPersonaRegistro' :
				codigo = "EMAIL_MENOR_QUE_4";
			break;
			case 'cifEmpresaRegistro' :
				codigo = "CIF_EMPRESA_MENOR_QUE_9";
			break;
			case 'nombreEmpresaRegistro' : 
		  		codigo = "NOMBRE_MENOR_QUE_3";
			break;
			case 'direccionEmpresaRegistro' : 
		  		codigo = "DIRECCION_MENOR_QUE_3";
			break;
			case 'telefonoEmpresaRegistro' : 
		  		codigo = "TELEFONO_MENOR_QUE_9";
			break;
			case 'passwdUsuarioRegistro':
				codigo = "PASS_MENOR_QUE_3";
			break;
			case 'passwordChange' :
				codigo = "PASS_MENOR_QUE_3";
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
			case 'dniPersona' :
				codigo = "DNI_PERSONA_MAYOR_QUE_9";
			break;
			case 'nombrePersonaRegistro' : 
		  		codigo = "NOMBRE_MAYOR_QUE_56";
			break;
			case 'apellidosPersonaRegistro' : 
		  		codigo = "APELLIDOS_PERSONA_MAYOR_QUE_128";
			break;
			case 'fechaPersonaRegistro' : 
		  		codigo = "FECHA_NACIMIENTO_MAYOR_QUE_8";
			break;
			case 'direccionPersonaRegistro' : 
		  		codigo = "DIRECCION_MAYOR_QUE_128";
			break;
			case 'telefonoPersonaRegistro' : 
		  		codigo = "TELEFONO_MAYOR_QUE_9";
			break;
			case 'emailPersonaRegistro' :
				codigo = "EMAIL_MAYOR_QUE_48";
			break;
			case 'cifEmpresaRegistro' :
				codigo = "CIF_EMPRESA_MAYOR_QUE_9";
			break;
			case 'nombreEmpresaRegistro' : 
		  		codigo = "NOMBRE_MAYOR_QUE_48";
			break;
			case 'direccionEmpresaRegistro' : 
		  		codigo = "DIRECCION_MAYOR_QUE_128";
			break;
			case 'telefonoEmpresaRegistro' : 
		  		codigo = "TELEFONO_MAYOR_QUE_9";
			break;
			case 'passwdUsuarioRegistro':
				codigo = "PASS_MAYOR_QUE_45";
			break;
			case 'passwordChange':
				codigo = "PASS_MAYOR_QUE_45";
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
    }else{
    	return true;
    }
}

/**Función que valida que un campo esté compuesto por letras y números**/
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
				codigo = "LOGIN_ALFANUMERICO_INCORRECTO";
			break;
			case 'passwdUsuarioRegistro':
				codigo = "PASS_ALFANUMERICO_INCORRECTO";
			break;
			case 'passwordChange':
				codigo = "PASS_ALFANUMERICO_INCORRECTO";
			break;

		}
		addCodeError(idElementoError, codigo);
    	return false;
  	}else{
  		return true;  
  	}	
}

/**Función que valida que un campo esté compuesto por letras y números**/
function comprobarLetrasNumerosCaracteres(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
 
 	var patron = /^[a-zA-Z0-9À-ÿ\u00f1\u00d1\u00AA\u00BA///-\s]+$/;
		
	if (!patron.test(valor)) { 
    	switch(campo) {
			case 'direccionPersonaRegistro' : 
		  		codigo = "DIRECCION_FORMATO_INCORRECTO";
			break;
			case 'direccionEmpresaRegistro' : 
		  		codigo = "DIRECCION_FORMATO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
  	}else{
  		return true;  
  	}	
}

/**Función que valida que un campo esté compuesto por letras y números**/
function comprobarSoloNumeros(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
 
 	var patron = /^[0-9]+$/;
		
	if (!patron.test(valor)) { 
    	switch(campo) {
			case 'telefonoPersonaRegistro' : 
		  		codigo = "TELEFONO_NUMERICO_INCORRECTO";
			break;
			case 'telefonoEmpresaRegistro' : 
		  		codigo = "TELEFONO_NUMERICO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
  	}else{
  		return true;  
  	}	
}


/**Función que valida que un campo no contenga ñ **/
function comprobarEnhe(idElemento, idElementoError,campo){
	var codigo = "";

	var valor = document.getElementById(idElemento).value;
 
 	var patron = /[ñÑ]/;
		
	if (patron.test(valor)) { 
    	switch(campo) {
	    	case 'loginUsuario' : 
		  		codigo = "LOGIN_ALFANUMERICO_INCORRECTO";
			break;
			case 'passwdUsuarioLogin' :
				codigo = "PASS_ALFANUMERICO_INCORRECTO"
			break;
			case 'loginUsuarioRecPass' :
				codigo = "LOGIN_ALFANUMERICO_INCORRECTO";
			break;
			case 'dniPersona' :
				codigo = "DNI_PERSONA_INCORRECTO";
			break;
			case 'passwdUsuarioRegistro':
				codigo = "PASS_ALFANUMERICO_INCORRECTO";
			break;
			case 'passwordChange' :
				codigo = "PASS_ALFANUMERICO_INCORRECTO"
			break;
		}
		addCodeError(idElementoError, codigo);
    	return false;
  	}else{
  		return true;  
  	}	
}

/**Función que valida la longitud del texto y que esté compuesto por letras**/
function comprobarSoloLetras(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
  	var nombre = document.getElementById(idElemento).name;
  	var longitud = document.getElementById(idElemento).value.length;

    var patron = /^[a-zA-ZÀ-ÿ\u00f1\u00d1\s]+$/g;
      
    if (!patron.test(valor)) { 
    	switch(campo) {
	    	case 'nombrePersonaRegistro' : 
		  		codigo = "NOMBRE_LETRAS_ACENTOS_INCORRECTO";
			break;
			case 'apellidosPersonaRegistro' : 
		  		codigo = "APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO";
			break;
			case 'nombreEmpresaRegistro' : 
		  		codigo = "NOMBRE_LETRAS_ACENTOS_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
        return false;
    }

    return true;

}

/**Función que valida las fechas **/
function comprobarFormatoFechas(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
  	var nombre = document.getElementById(idElemento).name;
  	var longitud = document.getElementById(idElemento).value.length;

    var patron = /^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}/g;
      
    if (!patron.test(valor)) { 
    	switch(campo) {
	    	case 'fechaPersonaRegistro' : 
		  		codigo = "FECHA_NACIMIENTO_NUMERICA_INCORRECTA";
			break;
		}
		addCodeError(idElementoError, codigo);
        return false;
    }

    return true;

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
			case 'emailPersonaRegistro' :
				codigo = "EMAIL_ALFANUMERICO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
		return false;
	}

    return true;
}

/**Función para comprobar el formato del DNI **/
function comprobarFormatoDNI(idElemento, idElementoError, campo) {
	
	var codigo = "";

	var valor = document.getElementById(idElemento).value;
      
	var patron = /^[0-9]{8}[A-Z]{1}$/; // establecemos un patron para un email
	if (!patron.test(valor)) {
		switch(campo) {
	    	case 'dniPersona' :
				codigo = "DNI_PERSONA_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
		return false;
	}

    return true;
}


/**Función para comprobar el formato del CIF **/
function comprobarFormatoCIF(idElemento, idElementoError, campo) {
	
	var codigo = "";

	var valor = document.getElementById(idElemento).value;
      
	var patron = /^[A-Z]{1}[0-9]{8}$/; // establecemos un patron para un email
	if (!patron.test(valor)) {
		switch(campo) {
	    	case 'cifEmpresaRegistro' :
				codigo = "CIF_EMPRESA_ALFANUMERICO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
		return false;
	}

    return true;
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
