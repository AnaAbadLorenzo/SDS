package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosMenor {

	public String comprobarAtributoMenor(final String atributo, final Funcionalidad funcionalidad, final Atributo atr,
			final Integer tamanhoMinimo) throws ParseException {

		String resultado = StringUtils.EMPTY;

		if (atr.equals(Atributo.FECHA_NACIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NACIMIENTO_MENOR_QUE_8 + " - "
						+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MENOR_QUE_8;

			}
		}

		if (atr.equals(Atributo.FECHA_NOTICIA)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NOTICIA_MENOR_QUE_8 + " - "
						+ Mensajes.FECHA_NOTICIA_NO_PUEDE_SER_MENOR_QUE_8;

			}
		}

		if (atr.equals(Atributo.FECHA_PLAN)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PLAN_MENOR_QUE_8 + " - "
						+ Mensajes.FECHA_PLAN_NO_PUEDE_SER_MENOR_QUE_8;

			}
		}

		if (atr.equals(Atributo.FECHA_PROCEDIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PROCEDIMIENTO_MENOR_QUE_8 + " - "
						+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_MENOR_QUE_8;

			}
		}

		if (atr.equals(Atributo.FECHA_PROCESO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PROCESO_MENOR_QUE_8 + " - "
						+ Mensajes.FECHA_PROCESO_NO_PUEDE_SER_MENOR_QUE_8;

			}
		}

		if (atributo.length() < tamanhoMinimo) {
			switch (funcionalidad) {
			case LOGIN:
				switch (atr) {
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case REGISTRAR:
				switch (atr) {
				case DNI_PERSONA:
					resultado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - " + Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_MENOR_QUE_3 + " - "
							+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - " + Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
							+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
							+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case CIF_EMPRESA:
					resultado = CodigosMensajes.CIF_EMPRESA_MENOR_QUE_9 + " - "
							+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				default:
					break;
				}
				break;

			case GESTION_ROLES:
				switch (atr) {
				case ROL_NAME:
					resultado = CodigosMensajes.ROL_NAME_MENOR_QUE_3 + " - "
							+ Mensajes.ROL_NAME_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case ROL_DESCRIPTION:
					resultado = CodigosMensajes.ROL_DESCRIPTION_MENOR_QUE_3 + " - "
							+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case GESTION_ACCIONES:
				switch (atr) {
				case ACCION_NAME:
					resultado = CodigosMensajes.ACCION_NAME_MENOR_QUE_3 + " - "
							+ Mensajes.ACCION_NAME_NO_PUEDE_SER_MENOR_QUE_3;
					break;

				case ACCION_DESCRIPTION:
					resultado = CodigosMensajes.ACCION_DESCRIPTION_MENOR_QUE_3 + " - "
							+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case GESTION_FUNCIONALIDADES:
				switch (atr) {
				case FUNCIONALIDAD_NAME:
					resultado = CodigosMensajes.FUNCIONALIDAD_NAME_MENOR_QUE_3 + " - "
							+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case FUNCIONALIDAD_DESCRIPTION:
					resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_MENOR_QUE_3 + " - "
							+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}

				break;
			case GESTION_USUARIOS:
				switch (atr) {
				case DNI_USUARIO:
					resultado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - " + Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}

				break;
			case GESTION_PERSONAS:
				switch (atr) {
				case DNI_PERSONA:
					resultado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - " + Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_MENOR_QUE_3 + " - "
							+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - " + Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
							+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
							+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case RECUPERAR_PASS:
				switch (atr) {
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - " + Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;
					break;
				default:
					break;
				}
				break;
			case GESTION_EMPRESAS:
				switch (atr) {
				case CIF_EMPRESA:
					resultado = CodigosMensajes.CIF_EMPRESA_MENOR_QUE_9 + " - "
							+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
							+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
							+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case GESTION_NOTICIAS:
				switch (atr) {
				case TITULO_NOTICIA:
					resultado = CodigosMensajes.TITULO_NOTICIA_MENOR_QUE_3 + " - "
							+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case TEXTO_NOTICIA:
					resultado = CodigosMensajes.TEXTO_NOTICIA_MENOR_QUE_3 + " - "
							+ Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case GESTION_OBJETIVOS:
				switch (atr) {
				case NOMBRE_OBJETIVO:
					resultado = CodigosMensajes.OBJETIVO_NAME_MENOR_QUE_3 + " - "
							+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case DESCRIPCION_OBJETIVO:
					resultado = CodigosMensajes.OBJETIVO_DESCRIPTION_MENOR_QUE_3 + " - "
							+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
			case GESTION_RESPUESTA_POSIBLE:
				switch (atr) {
				case TEXTO_RESPUESTA_POSIBLE:
					resultado = CodigosMensajes.TEXTO_RESPUESTA_MENOR_QUE_2 + " - "
							+ Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_MENOR_QUE_2;
					break;
				default:
					break;
				}
				break;
			case GESTION_PLANES:
				switch (atr) {
				case NOMBRE_PLAN:
					resultado = CodigosMensajes.NOMBRE_PLAN_MENOR_QUE_3 + " - "
							+ Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case DESCRIP_PLAN:
					resultado = CodigosMensajes.DESCRIPCION_PLAN_MENOR_QUE_3 + " - "
							+ Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case GESTION_PROCEDIMIENTOS:
				switch (atr) {
				case NOMBRE_PROCEDIMIENTO:
					resultado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_MENOR_QUE_3 + " - "
							+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case DESCRIP_PROCEDIMIENTO:
					resultado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_MENOR_QUE_3 + " - "
							+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
				break;
			case GESTION_PROCESOS:
				switch (atr) {
				case NOMBRE_PROCESO:
					resultado = CodigosMensajes.NOMBRE_PROCESO_MENOR_QUE_3 + " - "
							+ Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case DESCRIP_PROCESO:
					resultado = CodigosMensajes.DESCRIPCION_PROCESO_MENOR_QUE_3 + " - "
							+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				default:
					break;
				}
			default:
				break;
			}
		}

		return resultado;
	}

}
