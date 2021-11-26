package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosBlank {

	public String comprobarAtributoBlank(final String atributo, final Funcionalidad funcionalidad, final Atributo atr) {

		String resultado = StringUtils.EMPTY;

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
				
			case REGISTRAR:
				switch(atr) {
				case DNI_PERSONA:
					resultado = CodigosMensajes.DNI_PERSONA_VACIO + " - "
						+ Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_VACIO + " - "
						+ Mensajes.NOMBRE_NO_PUEDE_SER_VACIO;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - "
						+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;
					break;
				case FECHA_NACIMIENTO:
					resultado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - "
						+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;
				case EMAIL:
					resultado = CodigosMensajes.EMAIL_VACIO + " - "
						+ Mensajes.EMAIL_NO_PUEDE_SER_VACIO;
					break;
				case TELEFONO:
					resultado = CodigosMensajes.TELEFONO_VACIO + " - "
						+ Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;
					break;
				case DIRECCION:
					resultado = CodigosMensajes.DIRECCION_VACIO + " - "
						+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;
					break;
				case USUARIO:
					resultado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - " + Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;
					break;
				case PASSWD_USUARIO:
					resultado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
							+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;
					break;
				case CIF_EMPRESA:
					resultado = CodigosMensajes.CIF_EMPRESA_VACIO + " - "
							+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;
					break;
				}
			default:
				break;
			}
		}
		return resultado;
	}

}
