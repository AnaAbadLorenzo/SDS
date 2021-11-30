package com.sds.service.common;

public class Constantes {

	// Constantes para las funcionalidades
	public static final String LOGIN = "Login";
	public static final String REGISTRAR = "Registrar";

	// Contantes para nombres de atributos de base de datos
	public static final String PASSWD_USUARIO = "passwdUsuario";
	public static final String USUARIO = "usuario";
	public static final String DNIP = "dniP";
	public static final String NOMBREP = "nombreP";
	public static final String APELLIDOSP = "apellidosP";
	public static final String FECHANACP = "fechaNacP";
	public static final String DIRECCIONP = "direccionP";
	public static final String TELEFONOP = "telefonoP";
	public static final String EMAILP = "emailP";
	public static final String CIF_EMPRESA = "cifEmpresa";
	public static final String NOMBRE_EMPRESA = "nombreEmpresa";
	public static final String DIRECCION_EMPRESA = "direccionEmpresa";
	public static final String TELEFONO_EMPRESA = "telefonoEmpresa";

	// Constantes para los JSON
	public static final String URL_JSON_LOGIN_DATA = "classpath:LoginServiceData.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_USUARIO = "classpath:LoginServiceAtributosUsuario.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA = "classpath:LoginServiceAtributosContrasena.json";
	public static final String URL_JSON_REGISTRAR_DATA = "classpath:RegistroServiceData.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_DNIP = "classpath:RegistrarServiceAtributosDniP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP = "classpath:RegistrarServiceAtributosNombreP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP = "classpath:RegistrarServiceAtributosApellidosP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP = "classpath:RegistrarServiceAtributosFechaNacP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP = "classpath:RegistrarServiceAtributosDireccionP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP = "classpath:RegistrarServiceAtributosTelefonoP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP = "classpath:RegistrarServiceAtributosEmailP.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO = "classpath:RegistrarServiceAtributosUsuario.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA = "classpath:RegistrarServiceAtributosContrasena.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA = "classpath:RegistrarServiceAtributosCifEmpresa.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA = "classpath:RegistrarServiceAtributosNombreEmpresa.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA = "classpath:RegistrarServiceAtributosDireccionEmpresa.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA = "classpath:RegistrarServiceAtributosTelefonoEmpresa.json";
	public static final String URL_JSON_LOGIN_ACCIONES = "classpath:LoginServiceAcciones.json";
	public static final String URL_JSON_REGISTRAR_ACCIONES = "classpath:RegistrarServiceAcciones.json";

	public static final String USUARIO_CONTRASENA_VACIOS_DATA = "usuarioContrasenaVacios";
	public static final String USUARIO_CONTRASENA_CORRECTOS = "usuarioContrasenaCorrectos";
	public static final String REGISTRO_PERSONA_USUARIO_EMPRESA_VACIOS_DATA = "registroPersonaUsuarioEmpresaVacio";
	public static final String REGISTRO_PERSONA_USUARIO_EMPRESA_CORRECTOS = "registroPersonaUsuarioEmpresaCorrectos";

	public static final String USUARIO_VACIO_DATA = "usuarioVacio";
	public static final String CONTRASENA_VACIA_DATA = "contrasenaVacia";
	public static final String DNIP_VACIO_DATA = "dniPVacio";
	public static final String NOMBREPERSONA_VACIO_DATA = "nombrePersonaVacio";
	public static final String APELLIDOSPERSONA_VACIOS_DATA = "apellidosPersonaVacios";
	public static final String FECHANACPERSONA_VACIA_DATA = "fechaNacPersonaVacia";
	public static final String DIRECCIONPERSONA_VACIA_DATA = "direccionPersonaVacia";
	public static final String TELEFONOPERSONA_VACIO_DATA = "telefonoPersonaVacio";
	public static final String EMAIL_VACIO_DATA = "emailVacio";
	public static final String CIFEMPRESA_VACIO_DATA = "cifEmpresaVacio";
	public static final String NOMBREEMPRESA_VACIO_DATA = "nombreEmpresaVacio";
	public static final String DIRECCIONEMPRESA_VACIO_DATA = "direccionEmpresaVacio";
	public static final String TELEFONOEMPRESA_VACIO_DATA = "telefonoEmpresaVacio";
	public static final String PERSONA_VACIO_DATA = "personaVacia";
	public static final String EMPRESA_VACIO_DATA = "empresaVacio";

	public static final String USUARIO_ALFANUMERICO_ENHE_DATA = "usuarioAlfanumericoEnhe";
	public static final String CONTRASENA_ALFANUMERICA_ENHE_DATA = "contrasenaAlfanumericaEnhe";
	public static final String DNIPERSONA_ALFANUMERICO_ENHE_DATA = "dniPersonaAlfanumericoEnhe";
	public static final String FECHANACPERSONA_NUMERICA_ENHE_DATA = "fechaNacimientoPersonaNumericaEnhe";
	public static final String TELEFONOPERSONA_NUMERICO_ENHE_DATA = "telefonoPersonaNumericoEnhe";
	public static final String EMAIL_ALFANUMERICO_ENHE_DATA = "emailAlfanumericoEnhe";
	public static final String CIFEMPRESA_ALFAUMERICO_ENHE_DATA = "cifEmpresaAlfanumericoEnhe";
	public static final String TELEFONOEMPRESA_NUMERICO_ENHE_DATA = "telefonoEmpresaAlfanumericoEnhe";

	public static final String USUARIO_ALFANUMERICO_ACENTOS_DATA = "usuarioAlfanumericoAcentos";
	public static final String CONTRASENA_ALFANUMERICA_ACENTOS_DATA = "contrasenaAlfanumericaAcentos";
	public static final String DNIPERSONA_ALFANUMERICO_ACENTOS_DATA = "dniPersonaAlfanumericoAcentos";
	public static final String FECHANACPERSONA_NUMERICA_ACENTOS_DATA = "fechaNacimientoPersonaNumericaAcentos";
	public static final String TELEFONOPERSONA_NUMERICO_ACENTOS_DATA = "telefonoPersonaNumericoAcentos";
	public static final String EMAIL_ALFANUMERICO_ACENTOS_DATA = "emailAlfanumericoAcentos";
	public static final String CIFEMPRESA_ALFANUMERICO_ACENTOS_DATA = "cifEmpresaAlfanumericoAcentos";
	public static final String TELEFONOEMPRESA_NUMERICO_ACENTOS_DATA = "telefonoEmpresaNumericoAcentos";

	public static final String USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA = "usuarioAlfanumericoCaracteresEspeciales";
	public static final String CONTRASENA_ALFANUMERICA_CARACTERES_ESPECIALES_DATA = "contrasenaAlfanumericaCaracteresEspeciales";
	public static final String DNIPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA = "dniPersonaAlfanumericoCaracteresEspeciales";
	public static final String NOMBREPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA = "nombrePersonaAlfanumericoCaracteresEspeciales";
	public static final String APELLIDOSPERSONA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA = "apellidosPersonaAlfanumericoCaracteresEspeciales";
	public static final String FECHANACPERSONA_NUMERICA_CARACTERES_ESPECIALES_DATA = "fechaNacimientoPersonaNumericaCaracteresEspeciales";
	public static final String TELEFONOPERSONA_NUMERICO_CARACTERES_ESPECIALES_DATA = "telefonoPersonaNumericoCaracteresExpeciales";
	public static final String CIFEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA = "cifEmpresaAlfanumericoCaracteresEspeciales";
	public static final String NOMBREEMPRESA_ALFANUMERICO_CARACTERES_ESPECIALES_DATA = "nombreEmpresaAlfanumericoCaracteresEspeciales";
	public static final String TELEFONOEMPRESA_NUMERICO_CARACTERES_ESPECIALES_DATA = "telefonoEmpresaNumericoCaracteresEspeciales";

	public static final String USUARIO_ALFANUMERICO_ESPACIOS_DATA = "usuarioAlfanumericoEspacios";
	public static final String CONTRASENA_ALFANUMERICA_ESPACIOS_DATA = "contrasenaAlfanumericaEspacios";
	public static final String DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA = "dniPersonaAlfanumericoEspacios";
	public static final String FECHANACPERSONA_NUMERICA_ESPACIOS_DATA = "fechaNacPersonaNumericaEspacios";
	public static final String TELEFONOPERSONA_NUMERICO_ESPACIOS_DATA = "telefonoPersonaNumericoEspacios";
	public static final String EMAIL_ALFANUMERICO_ESPACIOS_DATA = "emailPersonaAlfanumericoEspacios";
	public static final String CIFEMPRESA_ALFANUMERICO_ESPACIOS_DATA = "cifEmpresaNumericoEspacios";
	public static final String TELEFONOEMPRESA_NUMERICO_ESPACIOS_DATA = "telefonoEmpresaNumericoEspacios";

	public static final String USUARIO_ALFANUMERICO_MENOR_3_DATA = "usuarioAlfanumericoMenor3";
	public static final String CONTRASENA_ALFANUMERICA_MENOR_3_DATA = "contrasenaAlfanumericaMenor3";
	public static final String DNIP_ALFANUMERICO_MENOR_9_DATA = "dniPAlfanumericoMenor9";
	public static final String NOMBREPERSONA_ALFANUMERICO_MENOR_3_DATA = "nombrePersonaAlfanumericoMenor3";
	public static final String APELLIDOSPERSONA_ALFANUMERICO_MENOR_3_DATA = "apellidosPersonaAlfanumericoMenor3";
	public static final String FECHANACPERSONA_NUMERICA_MENOR_8_DATA = "fechaNacPersonaNumericaMenor8";
	public static final String DIRECCIONPERSONA_ALFANUMERICA_MENOR_3_DATA = "direccionPersonaAlfanumericaMenor3";
	public static final String TELEFONOPERSONA_NUMERICO_MENOR_9_DATA = "telefonoPersonaNumericoMenor9";
	public static final String EMAIL_ALFANUMERICO_MENOR_4_DATA = "emailAlfanumericoMenor4";
	public static final String CIFEMPRESA_ALFANUMERICO_MENOR_9_DATA = "cifEmpresaAlfanumericoMenor9";
	public static final String NOMBREEMPRESA_ALFANUMERICO_MENOR_3_DATA = "nombreEmpresaAlfanumericoMenor3";
	public static final String DIRECCIONEMPRESA_ALFANUMERICO_MENOR_3_DATA = "direccionEmpresaAlfanumericoMenor3";
	public static final String TELEFONOEMPRESA_NUMERICO_MENOR_9_DATA = "telefonoEmpresaNumericoMenor9";

	public static final String USUARIO_ALFANUMERICO_MAYOR_45_DATA = "usuarioAlfanumericoMayor45";
	public static final String CONTRASENA_ALFANUMERICA_MAYOR_45_DATA = "contrasenaAlfanumericaMayor45";
	public static final String DNIP_ALFANUMERICO_MAYOR_9_DATA = "dniPAlfanumericoMayor9";
	public static final String NOMBREPERSONA_ALFANUMERICO_MAYOR_56_DATA = "nombrePersonaAlfanumericoMayor56";
	public static final String APELLIDOSPERSONA_ALFANUMERICO_MAYOR_128_DATA = "apellidosPersonaAlfanumericoMayor128";
	public static final String FECHANACPERSONA_NUMERICA_MAYOR_8_DATA = "fechaNacPersonaNumericaMayor8";
	public static final String DIRECCIONPERSONA_ALFANUMERICA_MAYOR_128_DATA = "direccionPersonaAlfanumericaMayor128";
	public static final String TELEFONOPERSONA_NUMERICO_MAYOR_9_DATA = "telefonoPersonaNumericoMayor9";
	public static final String EMAIL_ALFANUMERICO_MAYOR_128_DATA = "emailAlfanumericoMayor128";
	public static final String CIFEMPRESA_ALFANUMERICO_MAYOR_9_DATA = "cifEmpresaAlfanumericoMayor9";
	public static final String NOMBREEMPRESA_ALFANUMERICO_MAYOR_56_DATA = "nombreEmpresaAlfanumericoMayor56";
	public static final String DIRECCIONEMPRESA_ALFANUMERICO_MAYOR_128_DATA = "direccionEmpresaAlfanumericoMayor128";
	public static final String TELEFONOEMPRESA_NUMERICO_MAYOR_9_DATA = "telefonoEmpresaNumericoMayor9";

	public static final String USUARIO_ALFABETICO_DATA = "usuarioAlfabetico";
	public static final String CONTRASENA_ALFABETICA_DATA = "contrasenaAlfabetica";
	public static final String DNIPERSONA_ALFABETICO_DATA = "dniPersonaAlfabetico";
	public static final String NOMBREPERSONA_ALFABETICO_DATA = "nombrePersonaAlfabetico";
	public static final String APELLIDOSPERSONA_ALFABETICO_DATA = "apellidosPersonaAlfabetico";
	public static final String DIRECCIONPERSONA_ALFABETICO_DATA = "direccionPersonaAlfabetico";
	public static final String TELEFONOPERSONA_ALFABETICO_DATA = "telefonoPersonaAlfabetico";
	public static final String EMAILPERSONA_ALFABETICO_DATA = "emailPersonaAlfabetico";
	public static final String FECHANACIMIENTOPERSONA_ALFABETICO_DATA = "fechaNacimientoPersonaAlfabetico";
	public static final String CIFEMPRESA_ALFABETICO_DATA = "cifEmpresaAlfabetico";
	public static final String NOMBREEMPRESA_ALFABETICO_DATA = "nombreEmpresaAlfabetico";
	public static final String DIRECCIONEMPRESA_ALFABETICO_DATA = "direccionEmpresaAlfabetico";
	public static final String TELEFONOEMPRESA_ALFABETICO_DATA = "telefonoEmpresaAlfabetico";

	public static final String USUARIO_ALFANUMERICO_DATA = "usuarioAlfanumerico";
	public static final String CONTRASENA_ALFANUMERICA_DATA = "contrasenaAlfanumerica";
	public static final String DNIPERSONA_ALFANUMERICO_DATA = "dniPersonaAlfanumerico";
	public static final String NOMBREPERSONA_ALFANUMERICO_DATA = "nombrePersonaAlfanumerico";
	public static final String APELLIDOSPERSONA_ALFANUMERICO_DATA = "apellidosPersonaAlfanumerico";
	public static final String DIRECCIONPERSONA_ALFANUMERICO_DATA = "direccionPersonaAlfanumerico";
	public static final String TELEFONOPERSONA_ALFANUMERICO_DATA = "telefonoPersonaAlfanumerico";
	public static final String EMAILPERSONA_ALFANUMERICO_DATA = "emailPersonaAlfanumerico";
	public static final String FECHANACIMIENTOPERSONA_ALFANUMERICO_DATA = "fechaNacimientoPersonaAlfanumerico";
	public static final String CIFEMPRESA_ALFANUMERICO_DATA = "cifEmpresaAlfanumerico";
	public static final String NOMBREEMPRESA_ALFANUMERICO_DATA = "nombreEmpresaAlfanumerico";
	public static final String DIRECCIONEMPRESA_ALFANUMERICO_DATA = "direccionEmpresaAlfanumerico";
	public static final String TELEFONOEMPRESA_ALFANUMERICO_DATA = "telefonoEmpresaAlfanumerico";

	public static final String USUARIO_NUMERICO_DATA = "usuarioNumerico";
	public static final String CONTRASENA_NUMERICA_DATA = "contrasenaNumerica";
	public static final String DNIPERSONA_NUMERICO_DATA = "dniPersonaNumerico";
	public static final String NOMBREPERSONA_NUMERICO_DATA = "nombrePersonaNumerico";
	public static final String APELLIDOSPERSONA_NUMERICO_DATA = "apellidosPersonaNumerico";
	public static final String DIRECCIONPERSONA_NUMERICO_DATA = "direccionPersonaNumerico";
	public static final String TELEFONOPERSONA_NUMERICO_DATA = "telefonoPersonaNumerico";
	public static final String EMAILPERSONA_NUMERICO_DATA = "emailPersonaNumerico";
	public static final String FECHANACIMIENTOPERSONA_NUMERICO_DATA = "fechaNacimientoPersonaNumerico";
	public static final String CIFEMPRESA_NUMERICO_DATA = "cifEmpresaNumerico";
	public static final String NOMBREEMPRESA_NUMERICO_DATA = "nombreEmpresaNumerico";
	public static final String DIRECCIONEMPRESA_NUMERICO_DATA = "direccionEmpresaNumerico";
	public static final String TELEFONOEMPRESA_NUMERICO_DATA = "telefonoEmpresaNumerico";

	public static final String USUARIO_NO_EXISTE = "usuarioNoExiste";
	public static final String CONTRASENA_INCORRECTA = "contrasenaIncorrecta";
	public static final String LOGIN_CORRECTO = "loginCorrecto";

	public static final String PERSONA_YA_EXISTE = "personaYaExiste";
	public static final String USUARIO_YA_EXISTE = "usuarioYaExiste";
	public static final String EMPRESA_YA_EXISTE = "empresaYaExiste";
	public static final String REGISTRO_CORRECTO = "registroCorrecto";

	public static final String OK = "OK";

	// Constantes tipo de pruebas
	public static final String ERROR = "Error";
	public static final String EXITO = "Éxito";

	// Constantes validaciones
	public static final String ENHE = "ñ";
	public static final String PATRON_ACENTOS = "[áéíóúÁÉÍÓÚ]";
	public static final String PATRON_CARACTERES_ESPECIALES = "[/¡/!/¿/?/@/#/$/%/(/)/=/+/-/€/./,//]";
	public static final String ESPACIO = " ";
	public static final String ALFANUMERICO = "^[a-zA-Z0-9]+$";
}
