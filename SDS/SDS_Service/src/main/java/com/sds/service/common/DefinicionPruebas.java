package com.sds.service.common;

public class DefinicionPruebas {

	public static final String VACIO = "Vacío";
	public static final String ALFANUMERICO_ENHE = "Alfanumérico con ñ";
	public static final String ALFANUMERICO_ACENTOS = "Alfanumérico con acentos";
	public static final String ALFANUMERICO_CARACTERES_ESPECIALES = "Alfanumérico y caracteres especiales";
	public static final String ALFABETICO_CARACTERES_ESPECIALES = "Alfabético y caracteres especiales";
	public static final String ALFABETICO_ESPACIOS = "Alfabético y espacios";
	public static final String ALFANUMERICO_ESPACIOS = "Alfanumérico y espacios";
	public static final String ALFANUMERICO_MENOR_2 = "Alfanumérico menor de 2 caracteres";
	public static final String ALFANUMERICO_MENOR_3 = "Alfanumérico menor de 3 caracteres";
	public static final String ALFABETICO_MENOR_3 = "Alfabético menor de 3 caracteres";
	public static final String ALFANUMERICO_MENOR_9 = "Alfanumérico menor de 9 caracteres";
	public static final String ALFANUMERICO_MENOR_4 = "Alfanumérico menor de 4 caracteres";
	public static final String ALFANUMERICO_MAYOR_9 = "Alfanumérico mayor de 9 caracteres";
	public static final String ALFANUMERICO_MAYOR_45 = "Alfanumérico mayor de 45 caracteres";
	public static final String ALFANUMERICO_MAYOR_48 = "Alfanumérico mayor de 48 caracteres";
	public static final String ALFABETICO_MAYOR_56 = "Alfabético mayor de 56 caracteres";
	public static final String ALFANUMERICO_MAYOR_56 = "Alfanumérico mayor de 56 caracteres";
	public static final String ALFANUMERICO_MAYOR_128 = "Alfanumérico mayor de 128 caracteres";
	public static final String ALFABETICO_MAYOR_128 = "Alfabético mayor de 128 caracteres";
	public static final String ALFABETICO_MAYOR_32 = "Alfabético mayor de 32 caracteres";
	public static final String ALFABETICO_MAYOR_48 = "Alfabético mayor de 48 caracteres";
	public static final String ALFABETICO_MAYOR_256 = "Alfabético mayor de 256 caracteres";
	public static final String ALFABETICO_CORRECTO = "Alfabético sin caracateres especiales, tamaño mayor que 3";
	public static final String ALFANUMERICO_SIGNOS_PUNTUACION = "Alfanumérico que puede contener signos de puntuación";
	public static final String NOMBRE_ALFABETICO_CORRECTO = "Alfabético sin caracateres especiales, tamaño mayor que 3 y menor que 56";
	public static final String ALFANUMERICO_CORRECTO = "Alfanumérico sin acentos, ni ñ, ni caracteres especiales, tamaño mayor que 3 y menor que 45";
	public static final String DIRECCION_CORRECTO = "Alfanumerico que puede contener acentos, ñ, caracteres especiales, tamaño mayor que 3 y menor que 128";
	public static final String IDENTIFICADOR_CORRECTO = "Alfanumérico sin acentos, ni ñ, ni caracteres especiales, tamaño 9";
	public static final String IDENTIFICADOR_INCORRECTO = "Alfanumérico sin acentos, ni ñ, ni caracteres especiales, tamaño 9 pero no válido";
	public static final String FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL = "La fecha introducida por el usuario es anterior a la fecha actual";

	public static final String NUMERICO = "Numerico";
	public static final String NUMERICO_ENHE = "Numérico con ñ";
	public static final String NUMERICO_ACENTOS = "Numérico con acentos";
	public static final String NUMERICO_CARACTERES_ESPECIALES = "Numérico y caracteres especiales";
	public static final String NUMERICO_ESPACIOS = "Numérico y espacios";
	public static final String NUMERICO_MENOR_8 = "Numérico menor de 8 caracteres";
	public static final String NUMERICO_MAYOR_9 = "Númerico mayor de 9 caracteres";
	public static final String NUMERICO_MENOR_9 = "Númerico menor de 9 caracteres";
	public static final String NUMERICO_MAYOR_8 = "Numérico mayor de 8 caracteres";
	public static final String NUMERICO_FECHA_CORRECTO = "Numérico sin acentos, ni ñ, ni caracteres especiales, tamaño 8";
	public static final String NUMERICO_TELEFONO_CORRECTO = "Numérico sin acentos, ni ñ, ni caracteres especiales, tamaño 9";

	public static final String USUARIO_NO_EXISTE = "El usuario no existe";
	public static final String CONTRASENA_INCORRECTA = "La contraseña no es correcta";
	public static final String LOGIN_CORRECTO = "Login correcto";

	public static final String PERSONA_YA_EXISTE = "La persona ya existe";
	public static final String USUARIO_YA_EXISTE = "El usuario ya existe";
	public static final String EMPRESA_YA_EXISTE = "La empresa ya existe";
	public static final String PERSONA_VACIA = "Los datos de persona no pueden ser vacíos";
	public static final String USUARIO_VACIO = "Los datos de usuario no pueden ser vacíos";
	public static final String EMPRESA_VACIA = "Los datos de empresa no pueden ser vacíos";
	public static final String REGISTRO_CORRECTO = "Registro correcto";
	public static final String BUSCAR_CORRECTO = "Buscar correcto";

	public static final String BUSCAR_ROL_NAME_VACIO = "Buscar nombre de rol vacio";
	public static final String BUSCAR_ROL_NO_EXISTE = "Buscar un rol que no existe";
	public static final String GUARDAR_ROL_CORRECTO = "El rol se ha guardado correctamente";
	public static final String ROL_YA_EXISTE = "El rol ya existe";
	public static final String ROL_NAME_VACIO = "El nombre del rol es vacío";
	public static final String ROL_DESCRIPTION_VACIO = "La descripción del rol es vacía";
	public static final String ROL_VACIO = "El rol está vacío";
	public static final String ROL_NO_EXISTE = "El rol no existe";
	public static final String ELIMINAR_ROL_CORRECTO = "El rol se ha eliminado correctamente";
	public static final String ELIMINAR_ROL_ASOCIADO_USUARIO = "El rol no ha podido eliminarse porque tiene un usuario asociado";
	public static final String ELIMINAR_ROL_ASOCIADO_ACCION_FUNCIONALIDAD = "El rol no ha podido eliminarse porque tienen un rol y una funcionalidad asociado";
	public static final String ELIMINAR_ROL_NO_EXISTE = "El rol no ha podido eliminarse porque no existe";
	public static final String MODIFICAR_ROL = "EL rol se ha modificado correctamente";
	public static final String MODIFICAR_ROL_NO_EXISTE = "EL rol que desa modificar no existe";
	public static final String REACTIVAR_ROL_CORRECTO = "El rol se ha reactivado correctamente";

	public static final String ACCION_NAME_VACIO = "El nombre de la accion es vacio";
	public static final String ACCION_NO_EXISTE = "La acción que busca no existe";
	public static final String GUARDAR_ACCION_CORRECTO = "La acción se ha guardado correctamente";
	public static final String ACCION_YA_EXISTE = "La acción que busca ya existe";
	public static final String MODIFICAR_ACCION_CORRECTO = "La acción se ha modificado correctamente";
	public static final String ELIMINAR_ACCION_CORRECTO = "La acción se ha eliminado correctamente";
	public static final String ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD = "La acción tiene asociada un rol y una funcionalidad";
	public static final String REACTIVAR_ACCION_CORRECTO = "La acción se ha reactivado correctamente";
	public static final String ASIGNAR_ACCION_CORRECTO = "La asignación de la acción, el rol y la funcionalidad se ha realizado correctamente";
	public static final String ACCION_DESCRIPTION_VACIO = "La descripción de la acción es vacia";
	public static final String ACCION_VACIA = "El nombre y descripción de la acción son vacios";
	public static final String REVOCAR_ACCION_CORRECTO = "La acción se ha eliminado para el rol y la funcionalidad seleccionados";
	public static final String PERMISO_NO_EXISTE = "La acción no existe para el rol y la funcionalidad seleccionados";

	public static final String FUNCIONALIDAD_NAME_VACIO = "El nombre de la funcionalidad es vacio";
	public static final String FUNCIONALIDAD_NO_EXISTE = "La funcionalidad que busca no existe";
	public static final String FUNCIONALIDAD_ACCION_CORRECTO = "La funcionalidad se ha guardado correctamente";
	public static final String FUNCIONALIDAD_YA_EXISTE = "La funcionalidad que busca ya existe";
	public static final String GUARDAR_FUNCIONALIDAD_CORRECTO = "La funcionalidad se ha guardado correctamente";
	public static final String MODIFICAR_FUNCIONALIDAD_CORRECTO = "La funcionalidad se ha modificado correctamente";
	public static final String ELIMINAR_FUNCIONALIDAD_CORRECTO = "La funcionalidad se ha eliminado correctamente";
	public static final String ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION = "La funcionalidad tiene asociada un rol y una acción";
	public static final String REACTIVAR_FUNCIONALIDAD_CORRECTO = "La funcionalidad se ha reactivado correctamente";
	public static final String FUNCIONALIDAD_DESCRIPTION_VACIO = "La descripción de la funcionalidad es vacia";
	public static final String FUNCIONALIDAD_VACIA = "El nombre y descripción de la funcionalidad son vacios";

	public static final String ELIMINAR_USUARIO_CORRECTO = "El usuario se ha eliminado correctamente";
	public static final String ELIMINAR_USUARIO_NO_EXISTE = "El usuario que desea eliminar no existe";
	public static final String REACTIVAR_USUARIO_CORRECTO = "El usuario se ha reactivado correctamente";
	public static final String MODIFICAR_ROL_USUARIO_OK = "El rol del usuario se ha modificado correctamente";
	public static final String MODIFICAR_ROL_USUARIO_ROL_NO_EXISTE = "El rol no existe";
	public static final String MODIFICAR_ROL_USUARIO_NO_EXISTE = "El usuario no existe";
	public static final String MODIFICAR_PASSWD_USUARIO_OK = "La contraseña del usuario se ha modificado corrrectamente";
	public static final String MODIFICAR_PASSWD_USUARIO_NO_EXISTE = "El usuario no existe";

	public static final String GUARDAR_PERSONA_CORRECTO = "La persona se ha guardado correctamente";
	public static final String ELIMINAR_PERSONA_CORRECTO = "La persona se ha eliminado correctamente";
	public static final String PERSONA_NO_EXISTE = "La persona no existe";
	public static final String MODIFICAR_PERSONA_CORRECTO = "La persona se ha modificado correctamente";
	public static final String RECUPERAR_PASS = "La contraseña se ha recuperado correctamente";
	public static final String EMAIL_NO_ENCONTRADO = "El email no se ha encontrado";
	public static final String REACTIVAR_PERSONA_CORRETO = "La persona se ha reactivado correctamente";

	public static final String GUARDAR_EMPRESA_CORRECTO = "La empresa se ha guardado correctamente";
	public static final String MODIFICAR_EMPRESA_CORRECTO = "La empresa se ha modificado correctamente";
	public static final String EMPRESA_NO_EXISTE = "La empresa no existe";
	public static final String ELIMINAR_EMPRESA_CORRECTO = "La empresa se ha eliminado correctamente";
	public static final String EMPRESA_ASOCIADA_PERSONAS = "La empresa tiene personas asociadas";
	public static final String REACTIVAR_EMPRESA_CORRECTO = "La empresa se ha reactivado correctamente";

	public static final String GUARDAR_NOTICIA_CORRECTO = "La noticia se ha guardado correctamente";
	public static final String MODIFICAR_NOTICIA_CORRECTO = "La noticia se ha modificado correctamente";
	public static final String NOTICIA_YA_EXISTE = "La noticia ya existe";
	public static final String NOTICIA_NO_EXISTE = "La noticia no existe";
	public static final String ELIMINAR_NOTICIA_CORRECTO = "La noticia se ha eliminado correctamente";

	public static final String GUARDAR_OBJETIVO_CORRECTO = "El objetivo se ha guardado correctamente";
	public static final String MODIFICAR_OBJETIVO_CORRECTO = "El objetivo se ha modificado correctamente";
	public static final String OBJETIVO_YA_EXISTE = "El objetivo ya existe";
	public static final String OBJETIVO_NO_EXISTE = "El objetivo no existe";
	public static final String ELIMINAR_OBJETIVO_CORRECTO = "El objetivo se ha eliminado correctamente";
	public static final String REACTIVAR_OBJETIVO_CORRECTO = "El objetivo se ha reactivado correctamente";
	public static final String OBJETIVO_ASOCIADO_PLAN = "El objetivo se encuentra asociado a un plan";

	public static final String GUARDAR_RESPUESTA_POSIBLE_CORRECTO = "La respuesta posible se ha guardado correctamente";
	public static final String MODIFICAR_RESPUESTA_POSIBLE_CORRECTO = "La respuesta posible se ha modificado correctamente";
	public static final String RESPUESTA_POSIBLE_YA_EXISTE = "La respuesta posible ya existe";
	public static final String RESPUESTA_POSIBLE_NO_EXISTE = "La respuesta posible no existe";
	public static final String ELIMINAR_RESPUESTA_POSIBLE_CORRECTO = "La respuesta posible se ha eliminado correctamente";
	public static final String REACTIVAR_RESPUESTA_POSIBLE_CORRECTO = "La respuesta posible se ha reactivado correctamente";

	public static final String GUARDAR_PLAN_CORRECTO = "El plan se ha guardado correctamente";
	public static final String MODIFICAR_PLAN_CORRECTO = "El plan se ha modificado correctamente";
	public static final String PLAN_YA_EXISTE = "El plan ya existe";
	public static final String PLAN_NO_EXISTE = "El plan no existe";
	public static final String ELIMINAR_PLAN_CORRECTO = "El plan se ha eliminado correctamente";
	public static final String REACTIVAR_PLAN_CORRECTO = "El plan se ha reactivado correctamente";

}
