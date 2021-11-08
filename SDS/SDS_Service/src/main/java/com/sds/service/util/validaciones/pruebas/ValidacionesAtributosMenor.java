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
			default:
				break;
			}
		}

		return resultado;
	}

}
