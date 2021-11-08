package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosEspacios {

	public String comprobarAtributoEspacios(final String atributo, final Funcionalidad funcionalidad,
			final Atributo atr) {

		String resultado = StringUtils.EMPTY;

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
			default:
				break;
			}
		}

		return resultado;
	}

}
