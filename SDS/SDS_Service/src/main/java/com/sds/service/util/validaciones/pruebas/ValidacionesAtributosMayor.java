package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosMayor {

	public String comprobarAtributoMayor(final String atributo, final Funcionalidad funcionalidad, final Atributo atr,
			final Integer tamanhoMiaximo) throws ParseException {

		String resultado = StringUtils.EMPTY;

		if (atr.equals(Atributo.FECHA_NACIMIENTO)) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha;
			java.sql.Date fechaSql = null;

			fecha = format.parse("0000-00-00");
			fechaSql = new java.sql.Date(fecha.getTime());
			if (atributo.equals(fechaSql.toString())) {
				resultado = CodigosMensajes.FECHA_NACIMIENTO_MAYOR_QUE_8 + " - "
						+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MAYOR_QUE_8;

			}

		}

		if (atributo.length() > tamanhoMiaximo) {
			switch (funcionalidad) {
			case LOGIN:
				switch (atr) {
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MAYOR_QUE_45 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_MAYOR_QUE_45 + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;
					break;
				default:
					break;
				}
				break;

			case REGISTRAR:
				switch (atr) {
				case DNI_PERSONA:
					resultado = CodigosMensajes.DNI_PERSONA_MAYOR_QUE_9 + " - " + Mensajes.DNI_NO_PUEDE_SER_MAYOR_QUE_9;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_MAYOR_QUE_56 + " - " + Mensajes.NOMBRE_NO_PUEDE_SER_MAYOR_QUE_56;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_MAYOR_QUE_128 + " - "
							+ Mensajes.APELlIDOS_PERSONA_NO_PUEDE_SER_MAYOR_QUE_128;
					break;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_MAYOR_QUE_48 + " - " + Mensajes.EMAIL_NO_PUEDE_SER_MAYOR_QUE_48;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_MAYOR_QUE_9 + " - "
							+ Mensajes.TELEFONO_NO_PUEDE_SER_MAYOR_QUE_9;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_MAYOR_QUE_128 + " - "
							+ Mensajes.DIRECCION_NO_PUEDE_SER_MAYOR_QUE_128;
					break;

				case USUARIO:
					resultado = CodigosMensajes.LOGIN_MAYOR_QUE_45 + " - "
							+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_MAYOR_QUE_45 + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;
					break;
				case CIF_EMPRESA:
					resultado = CodigosMensajes.CIF_EMPRESA_MAYOR_QUE_9 + " - "
							+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MAYOR_QUE_9;
					break;
				default:
					break;
				}
			case GESTION_ROLES:
				switch (atr) {
				case ROL_NAME:
					resultado = CodigosMensajes.ROL_NAME_MAYOR_QUE_32 + " - "
							+ Mensajes.ROL_NAME_NO_PUEDE_SER_MAYOR_QUE_32;
					break;
				default:
					break;
				}

			case GESTION_ACCIONES:
				switch (atr) {
				case ACCION_NAME:
					resultado = CodigosMensajes.ACCION_NAME_MAYOR_QUE_48 + " - "
							+ Mensajes.ACCION_NAME_NO_PUEDE_SER_MAYOR_QUE_48;
					break;
				default:
					break;
				}
			case GESTION_FUNCIONALIDADES:
				switch (atr) {
				case FUNCIONALIDAD_NAME:
					resultado = CodigosMensajes.FUNCIONALIDAD_NAME_MAYOR_QUE_48 + " - "
							+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_MAYOR_QUE_48;
				}

			default:
				break;
			}
		}

		return resultado;
	}

}
