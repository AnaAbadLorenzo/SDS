package com.sds.service.common;

public class Constantes {

	// Constantes para las funcionalidades
	public static final String LOGIN = "Login";

	// Contantes para nombres de atributos de base de datos
	public static final String PASSWD_USUARIO = "passwdUsuario";
	public static final String USUARIO = "usuario";

	// Constantes para los JSON
	public static final String URL_JSON_LOGIN_DATA = "classpath:LoginServiceData.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_USUARIO = "classpath:LoginServiceAtributosUsuario.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA = "classpath:LoginServiceAtributosContrasena.json";
	public static final String URL_JSON_LOGIN_ACCIONES = "classpath:LoginServiceAcciones.json";

	public static final String USUARIO_CONTRASENA_VACIOS_DATA = "usuarioContrasenaVacios";
	public static final String USUARIO_CONTRASENA_CORRECTOS = "usuarioContrasenaCorrectos";

	public static final String USUARIO_VACIO_DATA = "usuarioVacio";
	public static final String CONTRASENA_VACIA_DATA = "contrasenaVacia";

	public static final String USUARIO_ALFANUMERICO_ENHE_DATA = "usuarioAlfanumericoEnhe";
	public static final String CONTRASENA_ALFANUMERICA_ENHE_DATA = "contrasenaAlfanumericaEnhe";

	public static final String USUARIO_ALFANUMERICO_ACENTOS_DATA = "usuarioAlfanumericoAcentos";
	public static final String CONTRASENA_ALFANUMERICA_ACENTOS_DATA = "contrasenaAlfanumericaAcentos";

	public static final String USUARIO_ALFANUMERICO_CARACTERES_ESPECIALES_DATA = "usuarioAlfanumericoCaracteresEspeciales";
	public static final String CONTRASENA_ALFANUMERICA_CARACTERES_ESPECIALES_DATA = "contrasenaAlfanumericaCaracteresEspeciales";

	public static final String USUARIO_ALFANUMERICO_ESPACIOS_DATA = "usuarioAlfanumericoEspacios";
	public static final String CONTRASENA_ALFANUMERICA_ESPACIOS_DATA = "contrasenaAlfanumericaEspacios";

	public static final String USUARIO_ALFANUMERICO_MENOR_3_DATA = "usuarioAlfanumericoMenor3";
	public static final String CONTRASENA_ALFANUMERICA_MENOR_3_DATA = "contrasenaAlfanumericaMenor3";

	public static final String USUARIO_ALFANUMERICO_MAYOR_45_DATA = "usuarioAlfanumericoMayor45";
	public static final String CONTRASENA_ALFANUMERICA_MAYOR_45_DATA = "contrasenaAlfanumericaMayor45";

	public static final String USUARIO_ALFABETICO_DATA = "usuarioAlfabetico";
	public static final String CONTRASENA_ALFABETICA_DATA = "contrasenaAlfabetica";

	public static final String USUARIO_ALFANUMERICO_DATA = "usuarioAlfanumerico";
	public static final String CONTRASENA_ALFANUMERICA_DATA = "contrasenaAlfanumerica";

	public static final String USUARIO_NUMERICO_DATA = "usuarioNumerico";
	public static final String CONTRASENA_NUMERICA_DATA = "contrasenaNumerica";

	public static final String USUARIO_NO_EXISTE = "usuarioNoExiste";
	public static final String CONTRASENA_INCORRECTA = "contrasenaIncorrecta";
	public static final String LOGIN_CORRECTO = "loginCorrecto";

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
