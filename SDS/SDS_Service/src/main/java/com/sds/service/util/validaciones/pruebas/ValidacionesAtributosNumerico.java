package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;

public class ValidacionesAtributosNumerico {

	public String comprobarAtributoNumerico(final String atributo, final Funcionalidad funcionalidad,
			final Atributo atr) {
		String resultado = StringUtils.EMPTY;

		if (atributo.matches(Constantes.NUMERICO)) {
			switch (funcionalidad) {
			case REGISTRAR:
				switch (atr) {
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
							+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
							+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
					break;

				default:
					break;
				}

			case GESTION_ROLES:
				switch (atr) {
				case ROL_NAME:
					resultado = CodigosMensajes.ROL_NAME_ALFABETICO_INCORRECTO + " - "
							+ Mensajes.ROL_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
					break;
				case ROL_DESCRIPTION:
					resultado = CodigosMensajes.ROL_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
							+ Mensajes.ROL_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

				default:
					break;
				}

			case GESTION_ACCIONES:
				switch (atr) {
				case ACCION_NAME:
					resultado = CodigosMensajes.ACCION_NAME_ALFABETICO_INCORRECTO + " - "
							+ Mensajes.ACCION_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
					break;
				case ACCION_DESCRIPTION:
					resultado = CodigosMensajes.ACCION_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
							+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;
					break;
				default:
					break;
				}
			case GESTION_FUNCIONALIDADES:
				switch (atr) {
				case FUNCIONALIDAD_NAME:
					resultado = CodigosMensajes.FUNCIONALIDAD_NAME_ALFABETICO_INCORRECTO + " - "
							+ Mensajes.FUNCIONALIDAD_NAME_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;
					break;
				case FUNCIONALIDAD_DESCRIPTION:
					resultado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
							+ Mensajes.FUNCIONALIDAD_DESCRIPTION_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;
					break;
				default:
					break;
				}

			case GESTION_PERSONAS:
				switch (atr) {
				case NOMBRE:
					resultado = CodigosMensajes.NOMBRE_LETRAS_ACENTOS_INCORRECTO + " - "
							+ Mensajes.NOMBRE_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
					break;
				case APELLIDOS_PERSONA:
					resultado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
							+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;
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
