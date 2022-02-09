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
				}

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

				}

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
			case RECUPERAR_PASS:
				switch (atr) {
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - " + Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;
					break;
				}
			default:
				break;
			}
		}

		return resultado;
	}

}
