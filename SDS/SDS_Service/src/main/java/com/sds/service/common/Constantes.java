package com.sds.service.common;

public class Constantes {

	// Constantes para las funcionalidades
	public static final String LOGIN = "Login";
	public static final String REGISTRAR = "Registrar";
	public static final String GESTION_ROLES = "Gestión de roles";
	public static final String GESTION_ACCIONES = "Gestión de acciones";
	public static final String GESTION_FUNCIONALIDADES = "Gestión de funcionalidades";
	public static final String ACCION_AÑADIR_ROL = "Añadir rol";
	public static final String ACCION_MODIFICAR_ROL = "Modificar rol";
	public static final String ACCION_ELIMINAR_ROL = "Eliminar rol";
	public static final String ACCION_BUSCAR_ROL = "Buscar rol";
	public static final String ACCION_AÑADIR_USUARIO = "Añadir usuario";
	public static final String ACCION_MODIFICAR_USUARIO = "Modificar usuario";
	public static final String ACCION_ELIMINAR_USUARIO = "Eliminar usuario";
	public static final String ACCION_AÑADIR_PERSONA = "Añadir persona";
	public static final String ACCION_MODIFICAR_PERSONA = "Modificar persona";
	public static final String ACCION_ELIMINAR_PERSONA = "Eliminar persona";
	public static final String ACCION_BUSCAR_PERSONA = "Buscar persona";
	public static final String ACCION_BUSCAR_EMPRESA = "Buscar empresa";
	public static final String ACCION_MODIFICAR_EMPRESA = "Modificar empresa";
	public static final String ACCION_AÑADIR_ACCION = "Añadir usuario";
	public static final String ACCION_BUSCAR_ACCION = "Buscar accion";
	public static final String ACCION_MODIFICAR_ACCION = "Modificar accion";

	public static final String ACCION_AÑADIR_FUNCIONALIDAD = "Añadir funcionalidad";
	public static final String ACCION_MODIFICAR_FUNCIONALIDAD = "Modificar funcionalidad";
	public static final String ACCION_ELIMINAR_FUNCIONALIDAD = "Eliminar funcionalidad";
	public static final String ACCION_BUSCAR_FUNCIONALIDAD = "Buscar funcionalidad";

	public static final String EMISOR_EMAIL = "sustanaibledevelopmentsystem@gmail.com";
	public static final String ASUNTO_EMAIL_RECU = "Recuperación de contraseña";
	public static final String TIPO_CONTENIDO = "text/plain";

	// Constantes para las acciones
	public static final String AÑADIR = "Añadir";
	public static final String BUSCAR = "Buscar";
	public static final String MODIFICAR = "Modificar";

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
	public static final String BORRADOP = "borradoP";
	public static final String BORRADOU = "borradoUsuario";
	public static final String CIF_EMPRESA = "cifEmpresa";
	public static final String NOMBRE_EMPRESA = "nombreEmpresa";
	public static final String DIRECCION_EMPRESA = "direccionEmpresa";
	public static final String TELEFONO_EMPRESA = "telefonoEmpresa";

	public static final String ROL_ID = "idRol";
	public static final String ROL_NAME = "rolName";
	public static final String ROL_DESCRIPTION = "rolDescription";

	public static final String ACCION_ID = "idAccion";
	public static final String ACCION_NAME = "nombreAccion";
	public static final String ACCION_DESCRIPTION = "descripAccion";

	public static final String FUNCIONALIDAD_ID = "idFuncionalidad";
	public static final String FUNCIONALIDAD_NAME = "nombreFuncionalidad";
	public static final String FUNCIONALIDAD_DESCRIPTION = "descripFuncionalidad";

	// Constantes para los JSON
	public static final String URL_JSON_LOGIN_DATA = "classpath:LoginServiceData.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_USUARIO = "classpath:LoginServiceAtributosUsuario.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA = "classpath:LoginServiceAtributosContrasena.json";
	public static final String URL_JSON_REGISTRAR_DATA = "classpath:RegistroServiceData.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_DNIP = "classpath:RegistroServiceAtributosDniPersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREP = "classpath:RegistroServiceAtributosNombrePersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_APELLIDOSP = "classpath:RegistroServiceAtributosApellidosPersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_FECHANACP = "classpath:RegistroServiceAtributosFechaNacPersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONP = "classpath:RegistroServiceAtributosDireccionPersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOP = "classpath:RegistroServiceAtributosTelefonoPersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_EMAILP = "classpath:RegistroServiceAtributosEmailPersona.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_USUARIO = "classpath:RegistroServiceAtributosUsuario.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_CONTRASENA = "classpath:RegistroServiceAtributosContrasena.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_CIFEMPRESA = "classpath:RegistroServiceAtributosCifEmpresa.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_NOMBREEMPRESA = "classpath:RegistroServiceAtributosNombreEmpresa.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_DIRECCIONEMPRESA = "classpath:RegistroServiceAtributosDireccionEmpresa.json";
	public static final String URL_JSON_REGISTRAR_ATRIBUTOS_TELEFONOEMPRESA = "classpath:RegistroServiceAtributosTelefonoEmpresa.json";
	public static final String URL_JSON_LOGIN_ACCIONES = "classpath:LoginServiceAcciones.json";
	public static final String URL_JSON_REGISTRAR_ACCIONES = "classpath:RegistroServiceAcciones.json";
	public static final String URL_JSON_ROL_DATA = "classpath:RolServiceData.json";
	public static final String URL_JSON_ROL_ATRIBUTOS_ROLNAME = "classpath:RolServiceAtributosRolName.json";
	public static final String URL_JSON_ROL_ATRIBUTOS_ROLDESCRIPTION = "classpath:RolServiceAtributosRolDescription.json";
	public static final String URL_JSON_ACCION_DATA = "classpath:AccionServiceData.json";
	public static final String URL_JSON_ACCION_ATRIBUTOS_ACCIONNAME = "classpath:AccionServiceAtributosAccionName.json";
	public static final String URL_JSON_ACCION_ATRIBUTOS_ACCIONDESCRIPTION = "classpath:AccionServiceAtributosAccionDescription.json";
	public static final String URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADNAME = "classpath:FuncionalidadServiceAtributosFuncionalidadName.json";
	public static final String URL_JSON_FUNCIONALIDAD_ATRIBUTOS_FUNCIONALIDADDESCRIPTION = "classpath:FuncionalidadServiceAtributosFuncionalidadDescription.json";
	public static final String URL_JSON_FUNCIONALIDAD_DATA = "classpath:FuncionalidadServiceData.json";
	public static final String URL_JSON_FUNCIONALIDAD_ATRIBUTOS_ACCIONNAME = "classpath:FuncionalidadServiceAtributosFuncionalidadName.json";
	public static final String URL_JSON_FUNCIONALIDAD_ATRIBUTOS_ACCIONDESCRIPTION = "classpath:FuncionalidadServiceAtributosFuncionalidadDescription.json";

	public static final String USUARIO_CONTRASENA_VACIOS_DATA = "usuarioContrasenaVacios";
	public static final String USUARIO_CONTRASENA_CORRECTOS = "usuarioContrasenaCorrectos";
	public static final String REGISTRO_PERSONA_USUARIO_EMPRESA_VACIOS_DATA = "registroPersonaUsuarioEmpresaVacio";
	public static final String REGISTRO_PERSONA_USUARIO_EMPRESA_CORRECTOS = "registroPersonaUsuarioEmpresaCorrectos";

	public static final String BUSCAR_ROL = "buscarRolCorrecto";
	public static final String ROL_NO_EXISTE = "rolNoExiste";
	public static final String ROL_YA_EXISTE = "rolYaExiste";
	public static final String GUARDAR_ROL = "guardarRolCorrecto";
	public static final String ROL_NAME_DESCRIPTION_VACIOS = "rolNameDescriptionVacios";
	public static final String ELIMINAR_ROL = "eliminarRol";
	public static final String ELIMINAR_ROL_NO_EXISTE = "eliminarRolNoExiste";
	public static final String ELIMINAR_ROL_ASOCIADO_USUARIO = "eliminarRolAsociadoUsuario";
	public static final String MODIFICAR_ROL = "modificarRol";

	public static final String BUSCAR_ACCION = "buscarAccionCorrecto";
	public static final String ACCION_NO_EXISTE = "accionNoExiste";
	public static final String ACCION_YA_EXISTE = "accionYaExiste";
	public static final String GUARDAR_ACCION = "guardarAccionCorrecto";
	public static final String ACCION_NAME_DESCRIPTION_VACIOS = "accionNameDescriptionVacios";
	public static final String ELIMINAR_ACCION = "eliminarAccion";
	public static final String ELIMINAR_ACCION_NO_EXISTE = "eliminarAccionNoExiste";
	public static final String MODIFICAR_ACCION = "modificarAccion";
	public static final String ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD = "eliminarAccionAsociadaRolFuncionalidad";

	public static final String BUSCAR_FUNCIONALIDAD = "buscarFuncionalidadCorrecto";
	public static final String FUNCIONALIDAD_NO_EXISTE = "funcionalidadNoExiste";
	public static final String FUNCIONALIDAD_YA_EXISTE = "funcionalidadYaExiste";
	public static final String GUARDAR_FUNCIONALIDAD = "guardarFuncionalidadCorrecto";
	public static final String FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS = "funcionalidadNameDescriptionVacios";
	public static final String ELIMINAR_FUNCIONALIDAD = "eliminarFuncionalidad";
	public static final String ELIMINAR_FUNCIONALIDAD_NO_EXISTE = "eliminarFuncionalidadNoExiste";
	public static final String MODIFICAR_FUNCIONALIDAD = "modificarFuncionalidad";
	public static final String ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION = "eliminarFuncionalidadAsociadaRolAccion";

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
	public static final String ROL_NAME_VACIO_DATA = "rolNameVacio";
	public static final String ROL_DESCRIPTION_VACIO_DATA = "rolDescriptionVacio";
	public static final String ACCION_NAME_VACIO_DATA = "accionNameVacio";
	public static final String ACCION_DESCRIPTION_VACIO_DATA = "accionDescriptionVacio";
	public static final String FUNCIONALIDAD_NAME_VACIO_DATA = "funcionalidadNameVacio";
	public static final String FUNCIONALIDAD_DESCRIPTION_VACIO_DATA = "funcionalidadDescriptionVacio";

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
	public static final String ROLNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA = "rolNameAlfabeticoCaracteresEspeciales";
	public static final String ROLDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA = "rolDescriptionAlfabeticoCaracteresEspeciales";
	public static final String ACCIONNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA = "accionNameAlfabeticoCaracteresEspeciales";
	public static final String ACCIONDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA = "accionDescriptionAlfabeticoCaracteresEspeciales";
	public static final String FUNCIONALIDADNAME_ALFABETICO_CARACTERES_ESPECIALES_DATA = "funcionalidadNameAlfabeticoCaracteresEspeciales";
	public static final String FUNCIONALIDADDESCRIPTION_ALFABETICO_CARACTERES_ESPECIALES_DATA = "funcionalidadDescriptionAlfabeticoCaracteresEspeciales";

	public static final String USUARIO_ALFANUMERICO_ESPACIOS_DATA = "usuarioAlfanumericoEspacios";
	public static final String CONTRASENA_ALFANUMERICA_ESPACIOS_DATA = "contrasenaAlfanumericaEspacios";
	public static final String DNIPERSONA_ALFANUMERICO_ESPACIOS_DATA = "dniPersonaAlfanumericoEspacios";
	public static final String FECHANACPERSONA_NUMERICA_ESPACIOS_DATA = "fechaNacPersonaNumericaEspacios";
	public static final String TELEFONOPERSONA_NUMERICO_ESPACIOS_DATA = "telefonoPersonaNumericoEspacios";
	public static final String EMAIL_ALFANUMERICO_ESPACIOS_DATA = "emailPersonaAlfanumericoEspacios";
	public static final String CIFEMPRESA_ALFANUMERICO_ESPACIOS_DATA = "cifEmpresaNumericoEspacios";
	public static final String TELEFONOEMPRESA_NUMERICO_ESPACIOS_DATA = "telefonoEmpresaNumericoEspacios";
	public static final String ROLNAME_ALFABETICO_ESPACIOS_DATA = "rolNameAlfabeticoEspacios";
	public static final String ACCIONNAME_ALFABETICO_ESPACIOS_DATA = "accionNameAlfabeticoEspacios";
	public static final String FUNCIONALIDADNAME_ALFABETICO_ESPACIOS_DATA = "funcionalidadNameAlfabeticoEspacios";

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
	public static final String ROLNAME_ALFABETICO_MENOR_3_DATA = "rolNameAlfabeticoMenor3";
	public static final String ROLDESCRIPTION_ALFABETICO_MENOR_3_DATA = "rolDescriptionAlfabeticoMenor3";
	public static final String ACCIONNAME_ALFABETICO_MENOR_3_DATA = "accionNameAlfabeticoMenor3";
	public static final String ACCIONDESCRIPTION_ALFABETICO_MENOR_3_DATA = "accionDescriptionAlfabeticoMenor3";
	public static final String FUNCIONALIDADNAME_ALFABETICO_MENOR_3_DATA = "funcionalidadNameAlfabeticoMenor3";
	public static final String FUNCIONALIDADDESCRIPTION_ALFABETICO_MENOR_3_DATA = "funcionalidadDescriptionAlfabeticoMenor3";

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
	public static final String ROLNAME_ALFABETICO_MAYOR_32_DATA = "rolNameAlfabeticoMayor32";
	public static final String ACCIONNAME_ALFABETICO_MAYOR_48_DATA = "accionNameAlfabeticoMayor48";
	public static final String FUNCIONALIDADNAME_ALFABETICO_MAYOR_48_DATA = "funcionalidadNameAlfabeticoMayor48";

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
	public static final String ROLNAME_ALFABETICO_DATA = "rolNameAlfabetico";
	public static final String ROLDESCRIPTION_ALFABETICO_DATA = "rolDescriptionAlfabetico";
	public static final String ACCIONNAME_ALFABETICO_DATA = "accionNameAlfabetico";
	public static final String ACCIONDESCRIPTION_ALFABETICO_DATA = "accionDescriptionAlfabetico";
	public static final String FUNCIONALIDADNAME_ALFABETICO_DATA = "funcionalidadNameAlfabetico";
	public static final String FUNCIONALIDADDESCRIPTION_ALFABETICO_DATA = "funcionalidadDescriptionAlfabetico";

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
	public static final String ROLNAME_NUMERICO_DATA = "rolNameNumerico";
	public static final String ROLDESCRIPTION_NUMERICO_DATA = "rolDescriptionNumerico";
	public static final String ACCIONNAME_NUMERICO_DATA = "accionNameNumerico";
	public static final String ACCIONDESCRIPTION_NUMERICO_DATA = "accionDescriptionNumerico";
	public static final String FUNCIONALIDADNAME_NUMERICO_DATA = "funcionalidadNameNumerico";
	public static final String FUNCIONALIDADDESCRIPTION_NUMERICO_DATA = "funcionalidadDescriptionNumerico";

	public static final String USUARIO_NO_EXISTE = "usuarioNoExiste";
	public static final String CONTRASENA_INCORRECTA = "contrasenaIncorrecta";
	public static final String LOGIN_CORRECTO = "loginCorrecto";

	public static final String PERSONA_YA_EXISTE = "personaYaExiste";
	public static final String USUARIO_YA_EXISTE = "usuarioYaExiste";
	public static final String EMPRESA_YA_EXISTE = "empresaYaExiste";
	public static final String REGISTRO_CORRECTO = "registroCorrecto";

	public static final String OK = "OK";

	public static final String USUARIO_GENERICO = "usuario_generico";

	// Constantes tipo de pruebas
	public static final String ERROR = "Error";
	public static final String EXITO = "Éxito";

	// Constantes validaciones
	public static final String ENHE = "ñ";
	public static final String PATRON_ACENTOS = "[áéíóúÁÉÍÓÚ]";
	public static final String PATRON_CARACTERES_ESPECIALES = "[/¡/!/¿/?/@/#/$/%/(/)/=/+/-/€/./,//]";
	public static final String ESPACIO = " ";
	public static final String ALFANUMERICO = "^[a-zA-Z0-9]+$";
	public static final String ALFABETICO = "^[a-zA-Z]+$";
	public static final String NUMERICO = "^[0-9]+$";
	public static final String FORMATO_FECHAS = "^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}";
	public static final String FORMATO_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
}
