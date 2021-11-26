package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosMenor {

	public String comprobarAtributoMenor(final String atributo, final Funcionalidad funcionalidad, final Atributo atr,
			final Integer tamanhoMinimo) {

		String resultado = StringUtils.EMPTY;

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
					resultado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - "
						+ Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_MENOR_QUE_3 + " - "
					+ Mensajes.NOMBRE_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_MENOR_QUE_3 + " - "
						+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_MENOR_QUE_3;
					break;
				case FECHA_NACIMIENTO:
					resultado = CodigosMensajes.FECHA_NACIMIENTO_MENOR_QUE_8 + " - "
						+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MENOR_QUE_8;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - "
						+ Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;
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
				}
			default:
				break;
			}
		}

		return resultado;
	}

}
