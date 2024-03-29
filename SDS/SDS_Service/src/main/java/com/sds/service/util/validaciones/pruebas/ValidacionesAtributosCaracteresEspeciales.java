package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosCaracteresEspeciales {

	public String comprobarAtributoCaracteresEspeciales(final String atributo, final Funcionalidad funcionalidad,
			final Atributo atr) throws ParseException {

		String resultado = StringUtils.EMPTY;

		if (atr.equals(Atributo.FECHA_NACIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
						+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

			}

		}

		if (atr.equals(Atributo.FECHA_NOTICIA)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NOTICIA_NUMERICA_INCORRECTA + " - "
						+ Mensajes.FECHA_NOTICIA_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

			}

		}

		if (atr.equals(Atributo.FECHA_PLAN)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PLAN_NUMERICA_INCORRECTA + " - "
						+ Mensajes.FECHA_PLAN_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

			}

		}

		if (atr.equals(Atributo.FECHA_PROCEDIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PROCEDIMIENTO_NUMERICA_INCORRECTA + " - "
						+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

			}

		}

		if (atr.equals(Atributo.FECHA_PROCESO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PROCESO_NUMERICA_INCORRECTA + " - "
						+ Mensajes.FECHA_PROCESO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

			}

		}

		for (int i = 0; i < atributo.length(); i++) {

			final String letra = atributo.charAt(i) + "";

			final Pattern patron = Pattern.compile(Constantes.PATRON_CARACTERES_ESPECIALES);
			final Matcher comprobacion = patron.matcher(letra);

			if (comprobacion.matches()) {
				switch (funcionalidad) {
				case LOGIN:
					switch (atr) {
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case PASSWD_USUARIO:
						resultado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					default:
						break;
					}
					break;
				case REGISTRAR:
					switch (atr) {
					case DNI_PERSONA:
						resultado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case NOMBRE:
						resultado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case APELLIDOS_PERSONA:
						resultado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case TELEFONO:
						resultado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
								+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;
						break;
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case PASSWD_USUARIO:
						resultado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case CIF_EMPRESA:
						resultado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					default:
						break;
					}
					break;
				case GESTION_ROLES:
					switch (atr) {
					case ROL_NAME:
						resultado = CodigosMensajes.ROL_NAME_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.ROL_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
						break;
					case ROL_DESCRIPTION:
						resultado = CodigosMensajes.ROL_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
						break;
					default:
						break;
					}
					break;
				case GESTION_ACCIONES:
					switch (atr) {
					case ACCION_NAME:
						resultado = CodigosMensajes.ACCION_NAME_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.ACCION_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
						break;

					case ACCION_DESCRIPTION:
						resultado = CodigosMensajes.ACCION_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
						break;
					default:
						break;
					}
					break;
				case GESTION_FUNCIONALIDADES:
					switch (atr) {
					case FUNCIONALIDAD_NAME:
						resultado = CodigosMensajes.FUNCIONALIDAD_NAME_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.FUNCIONALIDAD_NAME_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;
						break;
					case FUNCIONALIDAD_DESCRIPTION:
						resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.FUNCIONALIDAD_DESCRIPTION_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;
						break;
					default:
						break;
					}
					break;
				case GESTION_USUARIOS:
					switch (atr) {
					case DNI_USUARIO:
						resultado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case PASSWD_USUARIO:
						resultado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					default:
						break;
					}
					break;
				case GESTION_PERSONAS:
					switch (atr) {
					case DNI_PERSONA:
						resultado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case NOMBRE:
						resultado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case APELLIDOS_PERSONA:
						resultado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case TELEFONO:
						resultado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
								+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;
						break;
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case PASSWD_USUARIO:
						resultado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					default:
						break;
					}
					break;
				case RECUPERAR_PASS:
					switch (atr) {
					case USUARIO:
						resultado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					default:
						break;
					}
					break;
				case GESTION_EMPRESAS:
					switch (atr) {
					case CIF_EMPRESA:
						resultado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
						break;
					case NOMBRE:
						resultado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
								+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
						break;
					case TELEFONO:
						resultado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
								+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;
						break;
					default:
						break;
					}
					break;

				case GESTION_NOTICIAS:
					switch (atr) {
					case TITULO_NOTICIA:
						resultado = CodigosMensajes.TITULO_NOTICIA_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.TITULO_NOTICIA_SOLO_PUEDE_CONTENER_LETRAS;
						break;
					case TEXTO_NOTICIA:
						resultado = CodigosMensajes.TEXTO_NOTICIA_ALFANMERICO_SIGNOS_PUNTUACION_INCORRECTO + " - "
								+ Mensajes.TEXTO_NOTICIA_PUEDE_CONTENER_LETRAS_NUMEROS_SIGNOS_PUNTUACION;
						break;
					default:
						break;
					}
					break;

				case GESTION_OBJETIVOS:
					switch (atr) {
					case NOMBRE_OBJETIVO:
						resultado = CodigosMensajes.OBJETIVO_NAME_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.NOMBRE_OBJETIVO_SOLO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;
						break;
					case DESCRIPCION_OBJETIVO:
						resultado = CodigosMensajes.OBJETIVO_DESCRIPTION_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DESCRIPCION_OBJETIVO_SOLO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;
						break;
					default:
						break;
					}
					break;
				case GESTION_RESPUESTA_POSIBLE:
					switch (atr) {
					case TEXTO_RESPUESTA_POSIBLE:
						resultado = CodigosMensajes.TEXTO_RESPUESTA_ALFANUMERICO_SIGNOS_PUNTUACION_INCORRECTO + " - "
								+ Mensajes.TEXTO_RESPUESTA_SOLO_PUEDE_SER_ALFANUMERICO_CON_SIGNOS_PUNTUACION;
						break;
					default:
						break;
					}
					break;
				case GESTION_PLANES:
					switch (atr) {
					case NOMBRE_PLAN:
						resultado = CodigosMensajes.NOMBRE_PLAN_ALFABETICO_INCORRECTO + " - "
								+ Mensajes.NOMBRE_PLAN_SOLO_PUEDE_CONTENER_LETRAS_Y_ESPACIOS;
						break;
					case DESCRIP_PLAN:
						resultado = CodigosMensajes.DESCRIPCION_PLAN_ALBAFETICO_INCORRECTO + " - "
								+ Mensajes.DESCRIPCION_PLAN_PUEDE_CONTENER_LETRAS_Y_ESPACIOS;
						break;
					default:
						break;
					}
					break;
				case GESTION_PROCEDIMIENTOS:
					switch (atr) {
					case NOMBRE_PROCEDIMIENTO:
						resultado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.NOMBRE_PROCEDIMIENTO_SOLO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;
						break;
					case DESCRIP_PROCEDIMIENTO:
						resultado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DESCRIPCION_PROCEDIMIENTO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;
						break;
					default:
						break;
					}
				case GESTION_PROCESOS:
					switch (atr) {
					case NOMBRE_PROCESO:
						resultado = CodigosMensajes.NOMBRE_PROCESO_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.NOMBRE_PROCESO_SOLO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;
						break;
					case DESCRIP_PROCESO:
						resultado = CodigosMensajes.DESCRIPCION_PROCESO_ALFANUMERICO_INCORRECTO + " - "
								+ Mensajes.DESCRIPCION_PROCESO_PUEDE_CONTENER_LETRAS_NUMEROS_SIGNOS_PUNTUACION_Y_ESPACIOS;
						break;
					default:
						break;
					}

				default:
					break;
				}

			}
		}

		return resultado;
	}

}
