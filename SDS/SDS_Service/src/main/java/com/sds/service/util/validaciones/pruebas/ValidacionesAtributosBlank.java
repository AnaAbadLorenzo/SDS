package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosBlank {

	public String comprobarAtributoBlank(final String atributo, final Funcionalidad funcionalidad, final Atributo atr)
			throws ParseException {

		String resultado = StringUtils.EMPTY;

		if (atr.equals(Atributo.FECHA_NACIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;
			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - "
						+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;
			}

		}

		if (atr.equals(Atributo.FECHA_NOTICIA)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NOTICIA_VACIA + " - " + Mensajes.FECHA_NOTICIA_NO_PUEDE_SER_VACIA;

			}

		}

		if (atr.equals(Atributo.FECHA_PLAN)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_PLAN_VACIA + " - " + Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;

			}

		}

		if (StringUtils.isBlank(atributo)) {
			switch (funcionalidad) {
			case LOGIN:
				switch (atr) {
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
					break;
				default:
					break;
				}
				break;
			case REGISTRAR:
				switch (atr) {
				case DNI_PERSONA:
					resultado = CodigosMensajes.DNI_PERSONA_VACIO + " - " + Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - "
							+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
					break;
				case CIF_EMPRESA:
					resultado = CodigosMensajes.CIF_EMPRESA_VACIO + " - " + Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;
					break;
				default:
					break;
				}

				break;
			case GESTION_ROLES:
				switch (atr) {
				case ROL_NAME:
					resultado = CodigosMensajes.ROL_NAME_VACIO + " - " + Mensajes.ROL_NAME_NO_PUEDE_SER_VACIO;
					break;

				case ROL_DESCRIPTION:
					resultado = CodigosMensajes.ROL_DESCRIPTION_VACIO + " - "
							+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_SER_VACIO;
					break;
				default:
					break;
				}

				break;
			case GESTION_ACCIONES:
				switch (atr) {
				case ACCION_NAME:
					resultado = CodigosMensajes.ACCION_NAME_VACIO + " - " + Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;
					break;

				case ACCION_DESCRIPTION:
					resultado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
							+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;
					break;
				default:
					break;
				}
				break;
			case GESTION_FUNCIONALIDADES:
				switch (atr) {
				case FUNCIONALIDAD_NAME:
					resultado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
							+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;
					break;
				case FUNCIONALIDAD_DESCRIPTION:
					resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
							+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;
					break;
				default:
					break;
				}

				break;
			case GESTION_USUARIOS:
				switch (atr) {
				case DNI_USUARIO:
					resultado = CodigosMensajes.DNI_PERSONA_VACIO + " - " + Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
					break;
				default:
					break;
				}

				break;
			case GESTION_PERSONAS:
				switch (atr) {
				case DNI_PERSONA:
					resultado = CodigosMensajes.DNI_PERSONA_VACIO + " - " + Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - "
							+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
					break;
				default:
					break;
				}
				break;

			case RECUPERAR_PASS:
				switch (atr) {
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
					break;
				default:
					break;
				}
				break;
			case GESTION_EMPRESAS:
				switch (atr) {
				case CIF_EMPRESA:
					resultado = CodigosMensajes.CIF_EMPRESA_VACIO + " - " + Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_VACIO + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_VACIO + " - " + Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
					break;
				default:
					break;
				}
				break;
			case GESTION_NOTICIAS:
				switch (atr) {
				case TITULO_NOTICIA:
					resultado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - "
							+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;
					break;
				case TEXTO_NOTICIA:
					resultado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - " + Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;
				default:
					break;
				}
				break;

			case GESTION_OBJETIVOS:
				switch (atr) {
				case NOMBRE_OBJETIVO:
					resultado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - "
							+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;
					break;
				case DESCRIPCION_OBJETIVO:
					resultado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
							+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;
					break;
				default:
					break;
				}
				break;
			case GESTION_RESPUESTA_POSIBLE:
				switch (atr) {
				case TEXTO_RESPUESTA_POSIBLE:
					resultado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - "
							+ Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;
					break;
				default:
					break;
				}
				break;
			case GESTION_PLANES:
				switch (atr) {
				case NOMBRE_PLAN:
					resultado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - " + Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;
					break;
				case DESCRIP_PLAN:
					resultado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - "
							+ Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;
					break;
				default:
					break;
				}
				break;
			}
		}
		return resultado;
	}
}
