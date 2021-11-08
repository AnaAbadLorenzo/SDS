package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosMayor {

	public String comprobarAtributoMayor(final String atributo, final Funcionalidad funcionalidad, final Atributo atr,
			final Integer tamanhoMiaximo) {

		String resultado = StringUtils.EMPTY;

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
			default:
				break;
			}
		}

		return resultado;
	}

}
