package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosEspacios {

	public String comprobarAtributoEspacios(final String atributo, final Funcionalidad funcionalidad,
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

		if (atributo.contains(Constantes.ESPACIO)) {
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
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
							+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
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
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
							+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
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
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
							+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
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
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
							+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
							+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}

		return resultado;
	}

}
