/** Función que valida el formulario de cambio de contraseña **/
function comprobarChangePass(){
	if(comprobarPass('passChangePass1', 'errorFormatoChangePass1', 'passwordChange') && comprobarPass('passChangePass2', 'errorFormatoChangePass2', 'passwordChange')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el formulario add rol **/
function comprobarAddRol(){
	if(comprobarNombreRol('nombreRol', 'errorFormatoNombreRol', 'nombreRol') && comprobarDescripcionRol('descripcionRol','errorFormatoDescripcionRol', 'descripcionRol')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el formulario add rol **/
function comprobarEditRol(){
	if(comprobarNombreRol('nombreRol', 'errorFormatoNombreRol', 'nombreRol') && comprobarDescripcionRol('descripcionRol','errorFormatoDescripcionRol', 'descripcionRol')){
		return true;
	}else{
		return false;
	}
}



/** Función que valida el formulario add funcionalidad **/
function comprobarAddFuncionalidad(){
	if(comprobarNombreFuncionalidad('nombreFuncionalidad', 'errorFormatoNombreFuncionalidad', 'nombreFuncionalidad') && 
		comprobarDescripcionFuncionalidad('descripcionFuncionalidad','errorFormatoDescripcionFuncionalidad', 'descripcionFuncionalidad')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el formulario edit funcionalidad **/
function comprobarEditFuncionalidad(){
	if(comprobarNombreFuncionalidad('nombreFuncionalidad', 'errorFormatoNombreFuncionalidad', 'nombreFuncionalidad') && 
		comprobarDescripcionFuncionalidad('descripcionFuncionalidad','errorFormatoDescripcionFuncionalidad', 'descripcionFuncionalidad')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el formulario add accion **/
function comprobarAddAccion(){
	if(comprobarNombreAccion('nombreAccion', 'errorFormatoNombreAccion', 'nombreAccion') && 
		comprobarDescripcionAccion('descripcionAccion','errorFormatoDescripcionAccion', 'descripcionAccion')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el formulario edit acción **/
function comprobarEditAccion(){
	if(comprobarNombreAccion('nombreAccion', 'errorFormatoNombreAccion', 'nombreAccion') && 
		comprobarDescripcionFuncionalidad('descripcionAccion','errorFormatoDescripcionAccion', 'descripcionAccion')){
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
		comprobarPassRepetida('passwdUsuario2', 'errorFormatoPassRegistro2', 'passwdUsuarioRegistro')){
	
		if($('#formRegistroEmpresa').is(':hidden')){
			comprobarErroresTabs();
			return true;
		
		}else{
			if(comprobarCIF('cifEmpresa', 'errorFormatoCifEmpresa', 'cifEmpresaRegistro') && 
				comprobarNombreEmpresa('nombreEmpresa', 'errorFormatoNombreEmpresa', 'nombreEmpresaRegistro') && 
				comprobarDireccion('direccionEmpresa', 'errorFormatoDireccionEmpresa', 'direccionEmpresaRegistro') && 
				comprobarTelefono('telefono', 'errorFormatoTelefonoEmpresa', 'telefonoEmpresaRegistro')){
				return true;
			}else{
				comprobarErroresTabs();
				return false;
			}
		}

		
	}else{
		comprobarErroresTabs();
		return false;
	}
}

/** Función que valida el editar de una empresa **/
function comprobarEditEmpresa(){
	if(comprobarCIF('cifEmpresa', 'errorFormatoCifEmpresa', 'cifEmpresaRegistro') && comprobarNombreEmpresa('nombreEmpresa', 'errorFormatoNombreEmpresa', 'nombreEmpresaRegistro') && 
		comprobarDireccion('direccionEmpresa', 'errorFormatoDireccionEmpresa', 'direccionEmpresaRegistro') && comprobarTelefono('telefonoEmpresa', 'errorFormatoTelefonoEmpresa', 'telefonoEmpresaRegistro')){
		return true;
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

/**Función que valida el login de usuario*/
function comprobarUserLog(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo) && comprobarUserLog(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45, idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
		return true;
	} else {
		validacionKO(idElemento, idElementoError);		
		return false;
	}

}

/**Función que valida el login de usuario en el buscar*/
function comprobarUserSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarLetrasNumeros(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 45, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida el login de usuario en el buscar*/
function comprobarUserLogSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarUserLog(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 45, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
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
/**Función que valida el cambio de contraseña de un  usuario*/
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

function comprobarPassRepetida(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo) && comprobarLetrasNumeros(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45,  idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
		if($('#passwdUsuario1').val() != $('#passwdUsuario2').val()){
			addCodeError('error', 'CONTRASEÑAS_NO_COINCIDEN');
			return false;
		}else{
			$("#error").removeClass();
			$("#error").html('');
			$("#error").css("display", "none");
			return true;
		}
	} else {
		validacionKO(idElemento, idElementoError);		
		if($('#passwdUsuario1').val() != $('#passwdUsuario2').val()){
			addCodeError('error', 'CONTRASEÑAS_NO_COINCIDEN');
			return false;
		}else{
			$("#error").removeClass();
			$("#error").html('');
			$("#error").css("display", "none");
		}
		return false;
	}

}

function comprobarPassConfirmChangePass(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo) && comprobarLetrasNumeros(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 45,  idElementoError, campo) && comprobarEnhe(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
		if($('#passChangePass1').val() != $('#passChangePass2').val()){
			addCodeError('error', 'CONTRASEÑAS_NO_COINCIDEN');
			return false;
		}else{
			$("#error").removeClass();
			$("#error").html('');
			$("#error").css("display", "none");
			return true;
		}
	} else {
		validacionKO(idElemento, idElementoError);		
		if($('#passChangePass1').val() != $('#passChangePass2').val()){
			addCodeError('error', 'CONTRASEÑAS_NO_COINCIDEN');
			return false;
		}else{
			$("#error").removeClass();
			$("#error").html('');
			$("#error").css("display", "none");
		}
		return false;
	}

}

/** Función que comprueba el Add Persona **/
function comprobarAddPersona(){
	if(comprobarDNI('dniP', 'errorFormatoDni', 'dniPersona') && 
		comprobarNombre('nombreP', 'errorFormatoNombrePersona', 'nombrePersonaRegistro') && 
		comprobarApellidos('apellidosP', 'errorFormatoApellidosP', 'apellidosPersonaRegistro')
		&& comprobarFechaNacimiento('fechaNacP', 'errorFormatoFecha', 'fechaPersonaRegistro') && 
		comprobarDireccion('direccionP', 'errorFormatoDireccion', 'direccionPersonaRegistro') && 
		comprobarTelefono('telefonoP', 'errorFormatoTelefono', 'telefonoPersonaRegistro')
		&& comprobarEmail('emailP', 'errorFormatoEmail', 'emailPersonaRegistro') && 
		comprobarUser('usuario', 'errorFormatoUserRegistro', 'loginUsuario') && 
		comprobarPass('passwdUsuario1', 'errorFormatoPassRegistro', 'passwdUsuarioRegistro')  && 
		comprobarPassRepetida('passwdUsuario2', 'errorFormatoPassRegistro2', 'passwdUsuarioRegistro')){
	
		comprobarErroresTabs();
		return true;
		
		}else{
			
			comprobarErroresTabs();
			return false;
		}
	
}

/** Función que comprueba el Edit Persona **/
function comprobarEditPersona(){
	if(comprobarDNI('dniP', 'errorFormatoDni', 'dniPersona') && 
		comprobarNombre('nombreP', 'errorFormatoNombrePersona', 'nombrePersonaRegistro') && 
		comprobarApellidos('apellidosP', 'errorFormatoApellidosP', 'apellidosPersonaRegistro')
		&& comprobarFechaNacimiento('fechaNacP', 'errorFormatoFecha', 'fechaPersonaRegistro') && 
		comprobarDireccion('direccionP', 'errorFormatoDireccion', 'direccionPersonaRegistro') && 
		comprobarTelefono('telefonoP', 'errorFormatoTelefono', 'telefonoPersonaRegistro')
		&& comprobarEmail('emailP', 'errorFormatoEmail', 'emailPersonaRegistro')){
	
		comprobarErroresTabs();
		return true;
		
		}else{
			
			comprobarErroresTabs();
			return false;
		}
	
}

/** Función que comprueba el Edit Persona **/
function comprobarBuscarPersona(){
	if(comprobarDNISearch('dniP', 'errorFormatoDni', 'dniPersona') && 
		comprobarNombreSearch('nombreP', 'errorFormatoNombrePersona', 'nombrePersonaRegistro') && 
		comprobarApellidosSearch('apellidosP', 'errorFormatoApellidosP', 'apellidosPersonaRegistro')
		&& comprobarFechaInicioSearch('fechaNacP', 'errorFormatoFecha', 'fechaPersonaRegistro') && 
		comprobarDireccionSearch('direccionP', 'errorFormatoDireccion', 'direccionPersonaRegistro') && 
		comporbarTelefonoSearch('telefonoP', 'errorFormatoTelefono', 'telefonoPersonaRegistro')
		&& comprobarEmailSearch('emailP', 'errorFormatoEmail', 'emailPersonaRegistro')){

		return true;
		
		}else{
			
			return false;
		}
	
}

/** Función que valida el email **/
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

/** Función que valida el email **/
function comprobarEmailSearch(idElemento, idElementoError, campo) {

    document.getElementById(idElemento).style.borderWidth = "2px";
        
   if (validaNoVacio(idElemento, idElementoError, campo)) {
			if (comprobarFormatoEmail(idElemento, idElementoError, campo)) {
				if(!comprobarTamañoMaximo(idElemento, 48, idElementoError, campo)){
					validacionKO(idElemento, idElementoError);
					return false;
				}else{
					validacionOK(idElemento, idElementoError);
					return true;
				}
			}
			else {
				validacionKO(idElemento, idElementoError);
				return false;
			}
		}
		else {
			validacionOK(idElemento, idElementoError);
			return true;
		}

}


/** Funcion que valida el formato del DNI **/
function comprobarDNI(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarFormatoDNI(idElemento, idElementoError, campo) 
		&& comprobarEnhe(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 9, idElementoError, campo) 
		&& comprobarTamañoMaximo(idElemento, 9,  idElementoError, campo) && comprobarDNICorrecto(idElemento, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/**Función que valida el login de usuario en el buscar*/
function comprobarDNISearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarEnhe(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 9, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				if(comprobarDNICorrecto(idElemento, idElementoError, campo)){
					validacionOK(idElemento, idElementoError);
					return true;
				}else{
					validacionKO(idElemento, idElementoError);
					return false;
				}
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
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

/** Funcion que valida el formato del Nombre **/
function comprobarNombreSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 56, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				
					validacionOK(idElemento, idElementoError);
					return true;
				
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/** Funcion que valida el formato del Nombre del rol **/
function comprobarNombreRol(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetrasSinEspacios(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 32,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de la descripcion del rol **/
function comprobarDescripcionRol(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato del Nombre de la funcionalidad **/
function comprobarNombreFuncionalidad(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 48,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de la descripcion de la funcionalidad **/
function comprobarDescripcionFuncionalidad(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato del Nombre de la accion **/
function comprobarNombreAccion(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetrasSinEspacios(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 48,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de la descripcion de la funcionalidad **/
function comprobarDescripcionAccion(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo)) {
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

/**Función que valida los apellidos en el buscar*/
function comprobarApellidosSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 128, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
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
function comprobarFechaInicioSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarFormatoFechas(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 10, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/** Funcion que valida el formato de la fecha de nacimiento **/
function comprobarFechaFinSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarFormatoFechas(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 10, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Funcion para comprobar el buscar de los logs de excepciones **/
function comprobarBuscarLogExcepciones(){
	if(comprobarUserLogSearch('loginUsuario', 'errorFormatoLoginUsuario', 'loginUsuario') && comprobarFechaInicioSearch('fechaInicio', 'errorFormatoFechaInicio', 'fecha')
		&& comprobarFechaFinSearch('fechaFin', 'errorFormatoFechaFin', 'fecha')){
        return true;
	} else{
        return false;
	
	}
}

/**Funcion para comprobar el buscar de los logs de acciones **/
function comprobarBuscarLogAcciones(){
	if(comprobarUserSearch('loginUsuario', 'errorFormatoLoginUsuario', 'loginUsuario') && comprobarFechaInicioSearch('fechaInicio', 'errorFormatoFechaInicio', 'fecha')
		&& comprobarFechaFinSearch('fechaFin', 'errorFormatoFechaFin', 'fecha')){
        return true;
	} else{
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

/** Funcion que valida el format para buscar la direccion **/
function comprobarDireccionSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth="2px";

	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarLetrasNumerosCaracteres(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 128, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
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

/**Función que valida el formato del teléfono al buscar **/
function comporbarTelefonoSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

		if (validaNoVacio(idElemento, idElementoError, campo)) {
			if (comprobarSoloNumeros(idElemento, idElementoError, campo)) {
				if(!comprobarTamañoMaximo(idElemento, 9, idElementoError, campo)){
					validacionKO(idElemento, idElementoError);
					return false;
				}else{
					validacionOK(idElemento, idElementoError);
					return true;
				}
			}
			else {
				validacionKO(idElemento, idElementoError);
				return false;
			}
		}
		else {
			validacionOK(idElemento, idElementoError);
			return true;
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

 /**Función que valida el nombre del rol en el buscar*/
function comprobarNombreRolSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetrasSinEspacios(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 32, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida la descripcion del rol en el buscar*/
function comprobarDescripcionRolSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			validacionOK(idElemento, idElementoError);
			return true;
		
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

 /**Función que valida el nombre de la funcionalidad en el buscar*/
function comprobarNombreFuncionalidadSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 48, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida la descripcion de la funcionalidad en el buscar*/
function comprobarDescripcionFuncionalidadSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			validacionOK(idElemento, idElementoError);
			return true;
		
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida el nombre de la accion en el buscar*/
function comprobarNombreAccionSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetrasSinEspacios(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 48, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida la descripcion de la accion en el buscar*/
function comprobarDescripcionAccionSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			validacionOK(idElemento, idElementoError);
			return true;
		
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}


/*Función que verifica el formulario de buscar de rol*/
function comprobarBuscarRol(){
	if(comprobarNombreRolSearch('nombreRol', 'errorFormatoNombreRol', 'nombreRol') &&
		comprobarDescripcionRolSearch('descripcionRol', 'errorFormatoDescripcionRol', 'descripcionRol')){
		return true;
	}else{
		return false;
	}
}

/*Función que verifica el formulario de buscar de la funcionalidad*/
function comprobarBuscarFuncionalidad(){
	if(comprobarNombreFuncionalidadSearch('nombreFuncionalidad', 'errorFormatoNombreFuncionalidad', 'nombreFuncionalidad') &&
		comprobarDescripcionFuncionalidadSearch('descripcionFuncionalidad', 'errorFormatoDescripcionFuncionalidad', 'descripcionFuncionalidad')){
		return true;
	}else{
		return false;
	}
}

/*Función que verifica el formulario de buscar de la accion*/
function comprobarBuscarAccion(){
	if(comprobarNombreAccionSearch('nombreAccion', 'errorFormatoNombreAccion', 'nombreAccion') &&
		comprobarDescripcionAccionSearch('descripcionAccion', 'errorFormatoDescripcionAccion', 'descripcionAccion')){
		return true;
	}else{
		return false;
	}
}

/** Función que verifica el formulario de buscar usuario **/
function comprobarBuscarUsuario(){
	if(comprobarDNISearch('dniUsuario', 'errorFormatoDni', 'dniPersona') && 
		comprobarUserSearch('loginUsuario', 'errorFormatoLoginUsuario', 'loginUsuario')){
		return true;
	}else{
		return false;
	}
}

/**Funcion que verifica que el select de los roles está cubierto **/
function comprobarRolUser(idElemento, idElementoError, campo){
	if(idElemento.val == 0){
		addCodeError(idElementoError, 'OPCION_DEFECTO_ROL');
		validacionKO(idElemento, idElementoError);
		return false;
	}else{
		return true;
	}
}

/** Funcion que valida el formulario de editar un rol de un usuario **/
function comprobarEditRolUsuario(){
	if(comprobarUser('loginUsuario', 'errorFormatoLoginUsuario', 'loginUsuario') &&
		comprobarRolUser('selectRoles', 'errorFormatoRol', 'rolUsuario')){
			return true;
		}else{
			return false;
		}	
}

/** Función que valida el título de la noticia **/
function comprobarTituloNoticia(idElemento, idElementoError, campo){
	if(validaNoVacio(idElemento, idElementoError, campo) && 
		comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) 
		&& comprobarTamañoMaximo(idElemento, 255, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Función que valida el texto de la noticia **/
function comprobarTextoNoticia(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarTextoAlfanumericoSignosPuntuacion(idElemento, idElementoError, campo) && 
		comprobarTamañoMinimo(idElemento, 3, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Función que valida el añadir de la noticia **/
function comprobarAddNoticia(){
	if(comprobarTituloNoticia('tituloNoticia', 'errorFormatoTituloNoticia', 'tituloNoticia') &&
		comprobarTextoNoticia('textoNoticia', 'errorFormatoTextoNoticia', 'textoNoticia')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el editar de la noticia **/
function comprobarEditNoticia(){
	if(comprobarTituloNoticia('tituloNoticia', 'errorFormatoTituloNoticia', 'tituloNoticia') &&
		comprobarTextoNoticia('textoNoticia', 'errorFormatoTextoNoticia', 'textoNoticia')){
		return true;
	}else{
		return false;
	}
}

/** Función que valida el titulo de la noticia al buscar **/
function comprobarTituloNoticiaSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 256, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/** Función que valida el texto de la noticia al buscar **/
function comprobarTextoNoticiaSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarTextoAlfanumericoSignosPuntuacion(idElemento, idElementoError, campo)) {
			validacionOK(idElemento, idElementoError);
			return true;
		
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true
	}
}

/** Función que valida el buscar de la noticia **/
function comprobarBuscarNoticia(){
	if(comprobarTituloNoticiaSearch('tituloNoticia', 'errorFormatoTituloNoticia', 'tituloNoticia') &&
		comprobarTextoNoticiaSearch('textoNoticia', 'errorFormatoTextoNoticia', 'textoNoticia')){
		return true;
	}else{
		return false;
	}
}

/** Funcion que valida el formato del CIF de la empresa **/
function comprobarCifEmpresaSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarEnhe(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 9, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				if(comprobarFormatoCIF(idElemento, idElementoError, campo)){
					validacionOK(idElemento, idElementoError);
					return true;
				}else{
					validacionKO(idElemento, idElementoError);
					return false;
				}
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}


/** Funcion que valida el formato del Nombre de la empresa **/
function comprobarNombreEmpresaSearch(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 48, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				
					validacionOK(idElemento, idElementoError);
					return true;
				
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/** Funcion que valida el buscar de una empresa **/
function comprobarBuscarEmpresa(){
	if(comprobarCifEmpresaSearch('cifEmpresa', 'errorFormatoCifEmpresa', 'cifEmpresaRegistro') && comprobarNombreEmpresaSearch('nombreEmpresa', 'errorFormatoNombreEmpresa', 'nombreEmpresaRegistro') &&
		comprobarDireccionSearch('direccionEmpresa', 'errorFormatoDireccionEmpresa', 'direccionEmpresaRegistro') && comprobarTelefonoSearch('telefonoEmpresa', 'errorFormatoTelefonoEmpresa', 'telefonoEmpresaRegistro')){
		return true;
	}else{
		return false;
	}
}

/** Funcion que valida el añadir de una empresa **/
function comprobarAddEmpresa(){
	if(comprobarCIF('cifEmpresa', 'errorFormatoCifEmpresa', 'cifEmpresaRegistro') && comprobarNombreEmpresa('nombreEmpresa', 'errorFormatoNombreEmpresa', 'nombreEmpresaRegistro') && 
		comprobarDireccion('direccionEmpresa', 'errorFormatoDireccionEmpresa', 'direccionEmpresaRegistro') && comprobarTelefono('telefonoEmpresa', 'errorFormatoTelefonoEmpresa', 'telefonoEmpresaRegistro')){
		return true;
	}else{
		return false;
	}
}

/** Funcion que valida el formato del Nombre del objetivo **/
function comprobarNombreObjetivo(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo) && comprobarTamañoMaximo(idElemento, 48,  idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/** Funcion que valida el formato de la descripcion del objetivo **/
function comprobarDescripcionObjetivo(idElemento, idElementoError, campo){
	document.getElementById(idElemento).style.borderWidth = "2px";

	if(validaNoVacio(idElemento, idElementoError, campo) && comprobarSoloLetras(idElemento, idElementoError, campo) && comprobarTamañoMinimo(idElemento, 3, idElementoError, campo)) {
		validacionOK(idElemento, idElementoError);
        return true;
	} else{
		validacionKO(idElemento, idElementoError);
        return false;
	}
}

/**Función que valida el editar de un objetivo **/
function comprobarEditObjetivo(){
	if(comprobarNombreObjetivo('nombreObjetivo', 'errorFormatoNombreObjetivo', 'nombreObjetivo') &&
		comprobarDescripcionObjetivo('descripcionObjetivo', 'errorFormatoDescripcionObjetivo', 'descripcionObjetivo')){
		return true;
	}else{
		return false;
	}
}

/**Función que valida el añadir de un objetivo **/
function comprobarAddObjetivo(){
	if(comprobarNombreObjetivo('nombreObjetivo', 'errorFormatoNombreObjetivo', 'nombreObjetivo') &&
		comprobarDescripcionObjetivo('descripcionObjetivo', 'errorFormatoDescripcionObjetivo', 'descripcionObjetivo')){
		return true;
	}else{
		return false;
	}
}

/**Función que valida el nombre del objetivo en el buscar*/
function comprobarNombreObjetivoSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			if(!comprobarTamañoMaximo(idElemento, 48, idElementoError, campo)){
				validacionKO(idElemento, idElementoError);
				return false;
			}else{
				validacionOK(idElemento, idElementoError);
				return true;
			}
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida la descripcion del objetivo en el buscar*/
function comprobarDescripcionObjetivoSearch(idElemento, idElementoError, campo) {

	document.getElementById(idElemento).style.borderWidth = "2px";
		
	if (validaNoVacio(idElemento, idElementoError, campo)) {
		if (comprobarSoloLetras(idElemento, idElementoError, campo)) {
			validacionOK(idElemento, idElementoError);
			return true;
		
		}
		else {
			validacionKO(idElemento, idElementoError);
			return false;
		}
	}
	else {
		validacionOK(idElemento, idElementoError);
		return true;
	}
}

/**Función que valida el buscar de un objetivo **/
function comprobarBuscarObjetivo(){
	if(comprobarNombreObjetivoSearch('nombreObjetivo', 'errorFormatoNombreObjetivo', 'nombreObjetivo') &&
		comprobarDescripcionObjetivoSearch('descripcionObjetivo', 'errorFormatoDescripcionObjetivo', 'descripcionObjetivo')){
		return true;
	}else{
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
			case 'nombreRol':
				codigo="ROL_NAME_VACIO";
			break;
			case 'descripcionRol':
				codigo="ROL_DESCRIPTION_VACIO";
			break;
			case 'nombreFuncionalidad':
				codigo = "FUNCIONALIDAD_NAME_VACIA";
			break;
			case 'descripcionFuncionalidad':
				codigo = "FUNCIONALIDAD_DESCRIPTION_VACIA";
			break;
			case 'nombreAccion':
				codigo = "ACCION_NAME_VACIO";
			break;
			case 'descripcionAccion':
				codigo = "ACCION_DESCRIPTION_VACIO";
			break;
			case 'fecha':
				codigo = "FECHA_VACIA";
			break;
			case 'tituloNoticia':
				codigo = "TITULO_NOTICIA_VACIO";
			break;
			case 'textoNoticia':
				codigo = "TEXTO_NOTICIA_VACIO";
			break; 
			case 'nombreObjetivo':
				codigo = "OBJETIVO_NAME_VACIO" ;
			break;
			case 'descripcionObjetivo':
				codigo = "OBJETIVO_DESCRIPTION_VACIO";
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
			case 'nombreRol' : 
				codigo = "ROL_NAME_MENOR_QUE_3";
			break;
			case 'descripcionRol' : 
				codigo = "ROL_DESCRIPTION_MENOR_QUE_3";
			break;
			case 'nombreFuncionalidad' : 
				codigo = "FUNCIONALIDAD_NAME_MENOR_QUE_3";
			break;
			case 'descripcionFuncionalidad' : 
				codigo = "FUNCIONALIDAD_DESCRIPTION_MENOR_QUE_3";
			break;
			case 'nombreAccion' : 
				codigo = "ACCION_NAME_MENOR_QUE_3";
			break;
			case 'descripcionAccion' : 
				codigo = "ACCION_DESCRIPTION_MENOR_QUE_3";
			break;
			case 'tituloNoticia' :
				codigo = "TITULO_NOTICIA_MENOR_QUE_3";
			break;
			case "textoNoticia" :
				codigo = "TEXTO_NOTICIA_MENOR_QUE_3";
			break;
			case 'nombreObjetivo':
				codigo = "OBJETIVO_NAME_MENOR_QUE_3" ;
			break;
			case 'descripcionObjetivo':
				codigo = "OBJETIVO_DESCRIPTION_MENOR_QUE_3";
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
			case 'nombreRol':
				codigo="ROL_NAME_MAYOR_QUE_32";
			break;
			case 'nombreFuncionalidad':
				codigo="FUNCIONALIDAD_NAME_MAYOR_QUE_48";
			break;
			case 'nombreAccion' : 
				codigo = "ACCION_NAME_MAYOR_QUE_48";
			break;
			case 'fecha' : 
				codigo = "FECHA_MAYOR_QUE_8";
			break;
			case 'tituloNoticia' :
				codigo = "TITULO_NOTICIA_MAYOR_QUE_256";
			break;
			case 'nombreObjetivo':
				codigo = "OBJETIVO_NAME_MAYOR_QUE_48" ;
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
function comprobarUserLog(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
 
 	var patron = /^[a-zA-Z0-9_\u00f1\u00d1]+$/;
		
	if (!patron.test(valor)) { 
    	switch(campo) {
	    	case 'loginUsuario' : 
		  		codigo = "LOGIN_ALFANUMERICO_INCORRECTO";
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
			case 'descripcionRol':
				codigo="ROL_DESCRIPTION_ALFABETICO_INCORRECTO";
			break;
			case 'nombreFuncionalidad':
				codigo="FUNCIONALIDAD_NAME_ALFABETICO_INCORRECTO";
			break;
			case 'descripcionFuncionalidad':
				codigo="FUNCIONALIDAD_DESCRIPTION_ALFABETICO_INCORRECTO";
			break;
			case 'descripcionAccion' : 
				codigo = "ACCION_DESCRIPTION_ALFABETICO_INCORRECTO";
			break;
			case 'tituloNoticia' :
				codigo = "TITULO_NOTICIA_ALFABETICO_INCORRECTO";
			break;
			case 'nombreObjetivo' :
				codigo = "OBJETIVO_NAME_ALFABETICO_INCORRECTO";
			break;
			case 'descripcionObjetivo':
				codigo = "OBJETIVO_DESCRIPTION_ALFABETICO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
        return false;
    }

    return true;

}

/**Función que valida la longitud del texto y que esté compuesto por letras**/
function comprobarSoloLetrasSinEspacios(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
  	var nombre = document.getElementById(idElemento).name;
  	var longitud = document.getElementById(idElemento).value.length;

    var patron = /^[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/g;
      
    if (!patron.test(valor)) { 
    	switch(campo) {
			case 'nombreRol':
				codigo="ROL_NAME_ALFABETICO_INCORRECTO";
			break;
			case 'nombreAccion' : 
				codigo = "ACCION_NAME_ALFABETICO_INCORRECTO";
			break;
		}
		addCodeError(idElementoError, codigo);
        return false;
    }

    return true;

}

/**Función que valida la longitud del texto y que esté compuesto por letras**/
function comprobarTextoAlfanumericoSignosPuntuacion(idElemento, idElementoError, campo) {

	var codigo = "";

	var valor = document.getElementById(idElemento).value;
  	var nombre = document.getElementById(idElemento).name;
  	var longitud = document.getElementById(idElemento).value.length;

    var patron = /^[a-zA-ZÀ-ÿ0-9\u00f1\u00d1\u002E\u003B\u002C\s]+$/g;
      
    if (!patron.test(valor)) { 
    	switch(campo) {
			case 'textoNoticia' :
				codigo = "TEXTO_NOTICIA_ALFANMERICO_SIGNOS_PUNTUACION_INCORRECTO";
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
			case 'fecha' : 
		  		codigo = "FECHA_NUMERICA_INCORRECTA";
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

/**Función para comprobar el DNI correcto **/
function comprobarDNICorrecto(idElemento, idElementoError, campo) {
	
	var codigo = "";

	var letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKET";

	var valor = document.getElementById(idElemento).value;
      
	var patron = /^[0-9]{8}[A-Z]{1}$/; // establecemos un patron para el DNI

	if(patron.test(valor)){
		var dniSinLetra = valor.substring(0, valor.length-1);
		var letraIntroUsuario = valor.substring(valor.length-1, valor.length);
		var posicion = dniSinLetra % 23;
		var letra = letrasDNI.substring(posicion,posicion+1);

		if(letra != letraIntroUsuario){
			switch(campo) {
				case 'dniPersona' :
					codigo = "DNI_PERSONA_NO_VALIDO";
				break;
			}

			addCodeError(idElementoError, codigo);
			return false;
		}
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
