package com.sds.service.common;

public class Constantes {

	// Constantes generales
	public static final String SI = "Si";
	public static final String NO = "No";

	// Constantes para las funcionalidades
	public static final String LOGIN = "Login";
	public static final String REGISTRAR = "Registrar";
	public static final String RECUPERAR_PASS = "Recuperar contrasena usuario";
	public static final String GESTION_ROLES = "Gestión de roles";
	public static final String GESTION_ACCIONES = "Gestión de acciones";
	public static final String GESTION_FUNCIONALIDADES = "Gestión de funcionalidades";
	public static final String GESTION_USUARIOS = "Gestión de usuarios";
	public static final String GESTION_PERSONAS = "Gestión de personas";
	public static final String GESTION_EMPRESAS = "Gestión de empresas";
	public static final String GESTION_NOTICIAS = "Gestión de noticias";
	public static final String GESTION_OBJETIVOS = "Gestión de objetivos";

	public static final String ACCION_AÑADIR_ROL = "Añadir rol";
	public static final String ACCION_MODIFICAR_ROL = "Modificar rol";
	public static final String ACCION_ELIMINAR_ROL = "Eliminar rol";
	public static final String ACCION_BUSCAR_ROL = "Buscar rol";
	public static final String ACCION_REACTIVAR_ROL = "Reactivar rol";

	public static final String ACCION_AÑADIR_USUARIO = "Añadir usuario";
	public static final String ACCION_MODIFICAR_USUARIO = "Modificar usuario";
	public static final String ACCION_ELIMINAR_USUARIO = "Eliminar usuario";
	public static final String ACCION_BUSCAR_USUARIO = "Buscar usuario";
	public static final String ACCION_REACTIVAR_USUARIO = "Reactivar usuario";

	public static final String ACCION_AÑADIR_PERSONA = "Añadir persona";
	public static final String ACCION_MODIFICAR_PERSONA = "Modificar persona";
	public static final String ACCION_ELIMINAR_PERSONA = "Eliminar persona";
	public static final String ACCION_BUSCAR_PERSONA = "Buscar persona";
	public static final String ACCION_REACTIVAR_PERSONA = "Reactivar persona";

	public static final String ACCION_AÑADIR_ACCION = "Añadir acción";
	public static final String ACCION_BUSCAR_ACCION = "Buscar acción";
	public static final String ACCION_MODIFICAR_ACCION = "Modificar acción";
	public static final String ACCION_ELIMINAR_ACCION = "Eliminar acción";
	public static final String ACCION_REACTIVAR_ACCION = "Reactivar acción";
	public static final String ACCION_ASIGNAR_ACCION = "Asignar acción";
	public static final String ACCION_REVOCAR_ACCION = "Revocar acción";

	public static final String ACCION_AÑADIR_FUNCIONALIDAD = "Añadir funcionalidad";
	public static final String ACCION_MODIFICAR_FUNCIONALIDAD = "Modificar funcionalidad";
	public static final String ACCION_ELIMINAR_FUNCIONALIDAD = "Eliminar funcionalidad";
	public static final String ACCION_BUSCAR_FUNCIONALIDAD = "Buscar funcionalidad";
	public static final String ACCION_REACTIVAR_FUNCIONALIDAD = "Reactivar funcionalidad";

	public static final String ACCION_AÑADIR_EMPRESA = "Añadir empresa";
	public static final String ACCION_BUSCAR_EMPRESA = "Buscar empresa";
	public static final String ACCION_MODIFICAR_EMPRESA = "Modificar empresa";
	public static final String ACCION_ELIMINAR_EMPRESA = "Eliminar empresa";
	public static final String ACCION_REACTIVAR_EMPRESA = "Reactivar empresa";

	public static final String ACCION_MODIFICAR_ROL_USUARIO = "Modificar rol de usuario";
	public static final String ACCION_MODIFICAR_PASSWD_USUARIO = "Modificar contraseña de usuario";

	public static final String ACCION_AÑADIR_NOTICIA = "Añadir noticia";
	public static final String ACCION_BUSCAR_NOTICIA = "Buscar noticia";
	public static final String ACCION_MODIFICAR_NOTICIA = "Modificar noticia";
	public static final String ACCION_ELIMINAR_NOTICIA = "Eliminar noticia";

	public static final String ACCION_AÑADIR_OBJETIVO = "Añadir objetivo";
	public static final String ACCION_MODIFICAR_OBJETIVO = "Modificar objetivo";
	public static final String ACCION_BUSCAR_OBJETIVO = "Buscar objetivo";
	public static final String ACCION_ELIMINAR_OBJETIVO = "Eliminar objetivo";
	public static final String ACCION_REACTIVAR_OBJETIVO = "Reactivar objetivo";

	public static final String EMISOR_EMAIL = "sustanaibledevelopmentsystem@gmail.com";
	public static final String ASUNTO_EMAIL_RECU = "Recuperación de contraseña";
	public static final String TIPO_CONTENIDO = "text/plain";

	// Contantes para el mensaje de email por idioma
	public static final String ASUNTO_ES = "Recuperación de contraseña";
	public static final String ASUNTO_GA = "Recuperación de contrasinal";
	public static final String ASUNTO_EN = "Password recovery";
	public static final String FECHA_ES = "Ourense, %s de %s de %s";
	public static final String FECHA_GA = "Ourense, %s de %s de %s";
	public static final String FECHA_EN = "Ourense, %s  %s, %s";
	public static final String SALUDO_ES = "Estimado/a usuario/a,";
	public static final String SALUDO_GA = "Estimado/a usuario/a,";
	public static final String SALUDO_EN = "Dear user,";
	public static final String CUERPO_ES = "Su contraseña temporal es %s para cambiarlo acceda a la aplicación con él y modifique sus datos.";
	public static final String CUERPO_GA = "O seu contrasinal temporal é %s para cambialo acceda á aplicación con él e modifique os seus datos.";
	public static final String CUERPO_EN = "Your temporary password is %s to change it access the application with it and modify your data.";
	public static final String DESPEDIDA_ES = "Atententamente,";
	public static final String DESPEDIDA_GA = "Atententamente,";
	public static final String DESPEDIDA_EN = "Sincerely,";
	public static final String FIRMA_ES = "El administador de Sustanaile Development Society";
	public static final String FIRMA_GA = "O administador de Sustanaile Development Society";
	public static final String FIRMA_EN = "The Sustanaile Development Society Administrator";

	// Constantes para las acciones
	public static final String AÑADIR = "Añadir";
	public static final String BUSCAR = "Buscar";
	public static final String MODIFICAR = "Modificar";

	// Contantes para nombres de atributos de base de datos
	public static final String PASSWD_USUARIO = "passwdUsuario";
	public static final String USUARIO = "usuario";
	public static final String DNIP = "dniP";
	public static final String DNI_USUARIO = "dniUsuario";
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
	public static final String SELECCIONAR_EMPRESA = "seleccionarEmpresa";
	public static final String USUARIOBUSCAR = "usuarioBuscar";

	public static final String ROL_ID = "idRol";
	public static final String ROL_NAME = "rolName";
	public static final String ROL_DESCRIPTION = "rolDescription";

	public static final String ACCION_ID = "idAccion";
	public static final String ACCION_NAME = "nombreAccion";
	public static final String ACCION_DESCRIPTION = "descripAccion";

	public static final String FUNCIONALIDAD_ID = "idFuncionalidad";
	public static final String FUNCIONALIDAD_NAME = "nombreFuncionalidad";
	public static final String FUNCIONALIDAD_DESCRIPTION = "descripFuncionalidad";

	public static final String USUARIO_DNI = "dniUsuario";
	public static final String USER = "user";
	public static final String ROL = "rol";

	public static final String ID_EMPRESA = "idEmpresa";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String FECHA_FIN = "fechaFin";
	public static final String USUARIO_LOG = "usuarioLog";

	public static final String DNI_NO_VALIDO = "dniNoValido";
	public static final String DNI_VALIDO = "dniValido";

	public static final String NOTICIA_ID = "idNoticia";
	public static final String TITULO_NOTICIA = "tituloNoticia";
	public static final String TEXTO_NOTICIA = "textoNoticia";
	public static final String FECHA_NOTICIA = "fechaNoticia";

	public static final String OBJETIVO_ID = "idObjetivo";
	public static final String NOMBRE_OBJETIVO = "nombreObjetivo";
	public static final String DESCRIPCION_OBJETIVO = "descripObjetivo";

	// Constantes para los JSON
	public static final String URL_JSON_LOGIN_DATA = "classpath:LoginServiceData.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_USUARIO = "classpath:LoginServiceAtributosUsuario.json";
	public static final String URL_JSON_LOGIN_ATRIBUTOS_CONTRASENA = "classpath:LoginServiceAtributosContrasena.json";
	public static final String URL_JSON_RECUPERARPASS_DATA = "classpath:RecuperarPassServiceData.json";
	public static final String URL_JSON_RECUPERARPASS_ATRIBUTOS_USUARIO = "classpath:RecuperarPassServiceAtributosUsuario.json";
	public static final String URL_JSON_RECUPERARPASS_ATRIBUTOS_EMAIL = "classpath:RecuperarPassServiceAtributosEmail.json";
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
	public static final String URL_JSON_USUARIO_ATRIBUTOS_DNIUSUARIO = "classpath:UsuarioServiceAtributosDniUsuario.json";
	public static final String URL_JSON_USUARIO_ATRIBUTOS_NOMBREUSUARIO = "classpath:UsuarioServiceAtributosNombreUsuario.json";
	public static final String URL_JSON_USUARIO_ATRIBUTOS_PASSWDUSUARIO = "classpath:UsuarioServiceAtributosPasswdUsuario.json";
	public static final String URL_JSON_USUARIO_DATA = "classpath:UsuarioServiceData.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_DNIP = "classpath:PersonaServiceAtributosDniPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_NOMBREP = "classpath:PersonaServiceAtributosNombrePersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_APELLIDOSP = "classpath:PersonaServiceAtributosApellidosPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_DIRECCIONP = "classpath:PersonaServiceAtributosDireccionPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_TELEFONOP = "classpath:PersonaServiceAtributosTelefonoPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_EMAILP = "classpath:PersonaServiceAtributosEmailPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_FECHANACP = "classpath:PersonaServiceAtributosFechaNacPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_USUARIO = "classpath:PersonaServiceAtributosUsuarioPersona.json";
	public static final String URL_JSON_PERSONA_ATRIBUTOS_CONTRASENA = "classpath:PersonaServiceAtributosContrasenaPersona.json";
	public static final String URL_JSON_PERSONA_DATA = "classpath:PersonaServiceData.json";
	public static final String URL_JSON_EMPRESA_ATRIBUTOS_CIFEMPRESA = "classpath:EmpresaServiceAtributosCifEmpresa.json";
	public static final String URL_JSON_EMPRESA_ATRIBUTOS_NOMBREEMPRESA = "classpath:EmpresaServiceAtributosNombreEmpresa.json";
	public static final String URL_JSON_EMPRESA_ATRIBUTOS_DIRECCIONEMPRESA = "classpath:EmpresaServiceAtributosDireccionEmpresa.json";
	public static final String URL_JSON_EMPRESA_ATRIBUTOS_TELEFONOEMPRESA = "classpath:EmpresaServiceAtributosTelefonoEmpresa.json";
	public static final String URL_JSON_EMPRESA_DATA = "classpath:EmpresaServiceData.json";
	public static final String URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA = "classpath:NoticiasServiceAtributosTituloNoticia.json";
	public static final String URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA = "classpath:NoticiasServiceAtributosTextoNoticia.json";
	public static final String URL_JSON_NOTICIA_DATA = "classpath:NoticiasServiceData.json";
	public static final String URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO = "classpath:ObjetivoServiceAtributosNombreObjetivo.json";
	public static final String URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO = "classpath:ObjetivoServiceAtributosDescripcionObjetivo.json";
	public static final String URL_JSON_OBJETIVO_DATA = "classpath:ObjetivoServiceData.json";

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
	public static final String ELIMINAR_ROL_ASOCIADO_ACCION_FUNCIONALIDAD = "eliminarRolAsociadoAccionFuncionalidad";
	public static final String MODIFICAR_ROL = "modificarRol";
	public static final String ROL_ASIGNAR_CORRECTO = "asignarAccionRolCorrecto";
	public static final String REACTIVAR_ROL = "reactivarRolCorrecto";

	public static final String BUSCAR_ACCION = "buscarAccionCorrecto";
	public static final String ACCION_NO_EXISTE = "accionNoExiste";
	public static final String ACCION_YA_EXISTE = "accionYaExiste";
	public static final String GUARDAR_ACCION = "guardarAccionCorrecto";
	public static final String ACCION_NAME_DESCRIPTION_VACIOS = "accionNameDescriptionVacios";
	public static final String ELIMINAR_ACCION = "eliminarAccion";
	public static final String ELIMINAR_ACCION_NO_EXISTE = "eliminarAccionNoExiste";
	public static final String MODIFICAR_ACCION = "modificarAccion";
	public static final String ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD = "eliminarAccionAsociadaRolFuncionalidad";
	public static final String REACTIVAR_ACCION_CORRECTO = "reactivarAccionCorrecto";
	public static final String ASIGNAR_ACCION_CORRECTO = "asignarAccionCorrecto";

	public static final String BUSCAR_FUNCIONALIDAD = "buscarFuncionalidadCorrecto";
	public static final String FUNCIONALIDAD_NO_EXISTE = "funcionalidadNoExiste";
	public static final String FUNCIONALIDAD_YA_EXISTE = "funcionalidadYaExiste";
	public static final String GUARDAR_FUNCIONALIDAD = "guardarFuncionalidadCorrecto";
	public static final String FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS = "funcionalidadNameDescriptionVacios";
	public static final String ELIMINAR_FUNCIONALIDAD = "eliminarFuncionalidad";
	public static final String ELIMINAR_FUNCIONALIDAD_NO_EXISTE = "eliminarFuncionalidadNoExiste";
	public static final String MODIFICAR_FUNCIONALIDAD = "modificarFuncionalidad";
	public static final String ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION = "eliminarFuncionalidadAsociadaRolAccion";
	public static final String REACTIVAR_FUNCIONALIDAD_CORRECTO = "reactivarFuncionalidadCorrecto";
	public static final String ASIGNAR_FUNCIONALIDAD_CORRECTO = "asignarAccionFuncionalidadCorrecto";

	public static final String BUSCAR_USUARIO = "buscarUsuarioCorrecto";
	public static final String USUARIO_DNI_NOMBRE_VACIOS = "usuarioDniNombreVacios";
	public static final String ELIMINAR_USUARIO = "eliminarUsuario";
	public static final String GUARDAR_USUARIO = "guardarUsuario";
	public static final String MODIFICAR_ROL_USUARIO = "modificarRolUsuario";
	public static final String MODIFICAR_PASSWD_USUARIO = "modificarPasswdUsuario";
	public static final String ROL_USUARIO = "rolUsuario";
	public static final String REACTIVAR_USUARIO_CORRECTO = "reactivarUsuarioCorrecto";

	public static final String BUSCAR_PERSONA = "buscarPersonaCorrecto";
	public static final String GUARDAR_PERSONA = "guardarPersonaCorrecto";
	public static final String ELIMINAR_PERSONA = "eliminarPersonaCorrecto";
	public static final String PERSONA_NO_EXISTE = "personaNoExiste";
	public static final String REACTIVAR_PERSONA_CORRECTO = "reactivarPersonaCorrecto";

	public static final String BUSCAR_EMPRESA = "buscarEmpresaCorrecto";
	public static final String GUARDAR_EMPRESA_CORRECTO = "guardarEmpresaCorrecto";
	public static final String MODIFICAR_EMPRESA_CORRECTO = "modificarEmpresaCorrecto";
	public static final String EMPRESA_NO_EXISTE = "empresaNoExiste";
	public static final String ELIMINAR_EMPRESA_CORRECTO = "eliminarEmpresaCorrecto";
	public static final String ELIMINAR_EMPRESA_ASOCIADA_PERSONAS = "eliminarEmpresaAsociadaPersonas";
	public static final String REACTIVAR_EMPRESA_CORRECTO = "reactivarEmpresaCorrecto";

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
	public static final String PARAMETROS_PERSONA_VACIOS_DATA = "parametrosPersonaVacios";
	public static final String PARAMETROS_USUARIO_VACIOS = "parametrosUsuarioVacios";
	public static final String PARAMETROS_EMPRESA_VACIOS = "parametrosEmpresaVacios";
	public static final String USUARIO_ROL_VACIO = "usuarioRolVacio";

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

	public static final String EMAIL_NO_ENCONTRADO = "emailNoEncontrado";
	public static final String RECUPERAR_PASS_CORRECTO = "recuperarPassCorrecto";
	public static final String PERMISO_NO_EXISTE = "permisoNoExiste";

	public static final String TITULO_NOTICIA_VACIO = "tituloNoticiaVacio";
	public static final String TITULO_NOTICIA_CARACTERESESPECIALES = "tituloNoticiaCaracteresEspeciales";
	public static final String TITULO_NOTICIA_MENOR_3 = "tituloNoticiaMenor3";
	public static final String TITULO_NOTICIA_MAYOR_256 = "tituloNoticiaMayor256";
	public static final String TITULO_NOTICIA_NUMERICO = "tituloNoticiaNumerico";
	public static final String TITULO_NOTICIA_CORRECTO = "tituloNoticiaAlfabeticoCorrecto";

	public static final String TEXTO_NOTICIA_VACIO = "textoNoticiaVacio";
	public static final String TEXTO_NOTICIA_CARACTERESESPECIALES = "textoNoticiaCaracteresEspeciales";
	public static final String TEXTO_NOTICIA_MENOR_3 = "textoNoticiaMenor3";
	public static final String TEXTO_NOTICIA_NUMERICO = "textoNoticiaNumerico";
	public static final String TEXTO_NOTICIA_CORRECTO = "textoNoticiaAlfabeticoCorrecto";

	public static final String FECHA_NOTICIA_VACIA = "fechaNoticiaVacia";
	public static final String DATOS_NOTICIA_VACIOS = "datosNoticiaVacios";

	public static final String BUSCAR_NOTICIA = "buscarNoticia";
	public static final String GUARDAR_NOTICIA = "guardarNoticia";
	public static final String NOTICIA_YA_EXISTE = "noticiaYaExiste";
	public static final String MODIFICAR_NOTICIA = "modificarNoticia";
	public static final String NOTICIA_NO_EXISTE = "noticiaNoExiste";
	public static final String ELIMINAR_NOTICIA = "eliminarNoticia";

	public static final String NOMBRE_OBJETIVO_VACIO = "nombreObjetivoVacio";
	public static final String NOMBRE_OBJETIVO_CARACTERESESPECIALES = "nombreObjetivoCaracteresEspeciales";
	public static final String NOMBRE_OBJETIVO_MENOR_3 = "nombreObjetivoMenor3";
	public static final String NOMBRE_OBJETIVO_MAYOR_48 = "nombreObjetivoMayor48";
	public static final String NOMBRE_OBJETIVO_NUMERICO = "nombreObjetivoNumerico";
	public static final String NOMBRE_OBJETIVO_CORRECTO = "nombreObjetivoCorrecto";

	public static final String DESCRIPCION_OBJETIVO_VACIO = "descripcionObjetivoVacio";
	public static final String DESCRIPCION_OBJETIVO_CARACTERESESPECIALES = "descripcionObjetivoCaracteresEspeciales";
	public static final String DESCRIPCION_OBJETIVO_MENOR_3 = "descripcionObjetivoMenor3";
	public static final String DESCRIPCION_OBJETIVO_NUMERICO = "descripcionObjetivoNumerico";
	public static final String DESCRIPCION_OBJETIVO_CORRECTO = "descripcionObjetivoAlfabeticoCorrecto";

	public static final String DATOS_OBJETIVO_VACIOS = "datosObjetivoVacios";

	public static final String BUSCAR_OBJETIVO = "buscarObjetivo";
	public static final String GUARDAR_OBJETIVO = "guardarObjetivo";
	public static final String MODIFICAR_OBJETIVO = "modificarObjetivo";
	public static final String OBJETIVO_YA_EXISTE = "objetivoYaExiste";
	public static final String OBJETIVO_NO_EXISTE = "objetivoNoExiste";
	public static final String ELIMINAR_OBJETIVO = "eliminarObjetivo";
	public static final String REACTIVAR_OBJETIVO = "reactivarObjetivo";

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
	public static final String FORMATO_DNI = "^[0-9]{8}[A-Z]{1}$";
	public static final String FORMATO_CIF = "^[A-Z]{1}[0-9]{8}$";

	// Constantes caracteres
	public static final String COMA = ",";
	public static final String DOS_PUNTOS = ": ";
	public static final String SALTO_LINEA = "\n";
	public static final String TABULACION_FECHA = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	public static final String TABULACION_DESPEDIDA = "\t\t\t\t\t\t\t\t\t\t\t";
	public static final String TABULACION_FIRMA = "\t\t\t\t\t\t\t\t\t\t\t\t";

	// Constantes queries
	public static final String EMPRESA_QUERY_FINDALL = "EmpresaEntity.findAllEmpresas";
	public static final String EMPRESA_QUERY_FINDEMPRESA = "EmpresaEntity.findEmpresa";
	public static final String EMPRESA_QUERY_FINDELIMINADAS = "EmpresaEntity.findEmpresasEliminadas";

	public static final String ACCION_QUERY_FINDALL = "AccionEntity.findAllAccion";
	public static final String ACCION_QUERY_FINDACCION = "AccionEntity.findAccion";
	public static final String ACCION_QUERY_FINDELIMINADAS = "AccionEntity.findAccionesEliminadas";

	public static final String FUNCIONALIDAD_QUERY_FINDALL = "FuncionalidadEntity.findAllFuncionalities";
	public static final String FUNCIONALIDAD_QUERY_FINDFUNCIONALIDAD = "FuncionalidadEntity.findFuncionality";
	public static final String FUNCIONALIDAD_QUERY_FINDELIMINADAS = "FuncionalidadEntity.findFuncionalidadesEliminadas";

	public static final String PERSONA_QUERY_FINDALL = "PersonaEntity.findAllPerson";
	public static final String PERSONA_QUERY_FINDPERSONA = "PersonaEntity.findPersona";
	public static final String PERSONA_QUERY_FINDELIMINADAS = "PersonaEntity.findPersonasEliminadas";
	public static final String PERSONA_QUERY_FINDPERSONABYUSUARIO = "PersonaEntity.findPersonaByUsuario";

	public static final String ROL_QUERY_FINDALL = "RolEntity.findAllRol";
	public static final String ROL_QUERY_FINDROL = "RolEntity.findRol";
	public static final String ROL_QUERY_FINDELIMINADAS = "RolEntity.findDeleteRol";

	public static final String USUARIO_QUERY_FINDALL = "UsuarioEntity.findAllUsuarios";
	public static final String USUARIO_QUERY_FINDUSUARIO = "UsuarioEntity.findUsuario";
	public static final String USUARIO_QUERY_FINDELIMINADAS = "UsuarioEntity.findUsuariosEliminados";

	public static final String LOGEXCEPCIONES_QUERY_FINDLOG = "LogExcepcionesEntity.findLogExcepciones";
	public static final String LOGEXCEPCIONES_QUERY_FINDALL = "LogExcepcionesEntity.findAllLogExcepciones";
	public static final String LOGACCIONES_QUERY_FINDLOG = "LogAccionesEntity.findLogAcciones";
	public static final String LOGACCIONES_QUERY_FINDALL = "LogAccionesEntity.findAllLogAcciones";

	public static final String ROLACCIONFUNCIONALIDAD_QUERY_FINDALL = "RolAccionFuncionalidadEntity.findAllPermissions";

	public static final String NOTICIA_FINDALL_QUERY = "NoticiasEntity.findAllNoticias";
	public static final String NOTICIA_FINDNOTICIA_QUERY = "NoticiasEntity.findNoticia";
	public static final String NOTICIA_FINDNOTICIA_WITHOUTDATE_QUERY = "NoticiasEntity.findNoticiaWithoutDate";

	public static final String OBJETIVO_QUERY_FINDALL = "ObjetivoEntity.findAllObjetivos";
	public static final String OBJETIVO_QUERY_FINDOBJETIVO = "ObjetivoEntity.findObjetivo";
	public static final String OBJETIVO_QUERY_FINDELIMINADOS = "ObjetivoEntity.findObjetivosEliminados";

	// Constantes funcionalidad-accion
	public static final String TEST_AÑADIR = "Test-Añadir";
	public static final String TEST_MODIFICAR = "Test-Modificar";
	public static final String TEST_ELIMINAR = "Test-Eliminar";
	public static final String TEST_REACTIVAR = "Test-Reactivar";

	public static final String LOGEXCEPCIONES_AÑADIR = "Log de excepciones-Añadir";
	public static final String LOGEXCEPCIONES_MODIFICAR = "Log de excepciones-Modificar";
	public static final String LOGEXCEPCIONES_ELIMINAR = "Log de excepciones-Eliminar";
	public static final String LOGEXCEPCIONES_REACTIVAR = "Log de excepciones-Reactivar";

	public static final String LOGACCIONES_AÑADIR = "Log de acciones-Añadir";
	public static final String LOGACCIONES_MODIFICAR = "Log de acciones-Modificar";
	public static final String LOGACCIONES_ELIMINAR = "Log de acciones-Eliminar";
	public static final String LOGACCIONES_REACTIVAR = "Log de acciones-Reactivar";
}
