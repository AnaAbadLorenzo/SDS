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
			default:
				break;
			}
		}
		return resultado;
	}

}
